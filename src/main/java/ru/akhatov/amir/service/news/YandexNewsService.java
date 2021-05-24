package ru.akhatov.amir.service.news;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.akhatov.amir.model.dto.NewsDto;
import ru.akhatov.amir.model.entity.News;
import ru.akhatov.amir.model.entity.Traffic;
import ru.akhatov.amir.model.mapper.NewsMapper;
import ru.akhatov.amir.repository.NewsRepository;
import ru.akhatov.amir.repository.TrafficRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class YandexNewsService implements INewsService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final String YANDEX_NEWS_API = "http://news.yandex.ru/yandsearch?grhow=clutop&rpt=nnews2&p=0&text=";

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private TrafficRepository trafficRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<NewsDto> getNewsByKeyWords(List<String> keyWords) {
        String keys = "";
        for (String key : keyWords) {
            keys += key + " ";
        }
        String url = YANDEX_NEWS_API + keys;
        HttpGet request = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            Document doc = getXmlDocument(entity);
            return parseNews(doc);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Document getXmlDocument(HttpEntity entity) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder;
        builder = builderFactory.newDocumentBuilder();
        return builder.parse(entity.getContent());
    }

    private List<NewsDto> parseNews(Document doc) throws XPathExpressionException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression titleXPath = xpath.compile("div[class='story-item']/h2[class='title']/a");
        XPathExpression bodyXPath = xpath.compile("div[class='story-item']/div[class='documents']/div[class='document']/div[class='document__snippet']");
        XPathExpression publishTimeXPath = xpath.compile("div[class='story-item']/div[class='documents']/div[class='document']/div[class='document__time']");
        NodeList titles = (NodeList) titleXPath.evaluate(doc, XPathConstants.NODESET);
        NodeList bodies = (NodeList) bodyXPath.evaluate(doc, XPathConstants.NODESET);
        NodeList times = (NodeList) publishTimeXPath.evaluate(doc, XPathConstants.NODESET);
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < titles.getLength(); i++) {
            String title = titles.item(i).getNodeValue();
            String body = bodies.item(i).getNodeValue();
            List<Traffic> traffics = getTrafficListIfContainsTrafficKeyWord(title + body);
            if (!traffics.isEmpty()) {
                News news = new News();
                news.setTitle(title);
                news.setBody(body);
                news.setPublishedAt(LocalDateTime.parse(times.item(i).getNodeValue()));
                news.setComment(traffics.toString());
                news = newsRepository.save(news);
                newsList.add(news);
            }
        }
        return newsMapper.toDtoList(newsList);
    }

    private List<Traffic> getTrafficListIfContainsTrafficKeyWord(String s) {
        List<Traffic> traffics = trafficRepository.findAll();
        List<Traffic> result = new ArrayList<>();
        for (Traffic traffic : traffics) {
            if (s.toLowerCase().contains(traffic.getName().toLowerCase())) {
                result.add(traffic);
            }
        }
        return result;
    }
}
