package ru.akhatov.amir.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JacksonUtils {

    @Autowired
    private ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = this.objectMapper;
        JsonDeserializer<Double[][]> jsonDeserializer = new JsonDeserializer<Double[][]>() {
            @Override
            public Double[][] deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
                String matrix = parser.readValueAsTree().toString();
                String[] values = matrix.split("],\\[");
                Double doubles[][] = new Double[values.length][2];
                for (int i = 0; i < values.length; i++) {
                    String[] valuesAtI = values[i].replace("[", "").replace("]", "").split(",");
                    doubles[i][0] = Double.valueOf(valuesAtI[0]);
                    doubles[i][1] = Double.valueOf(valuesAtI[1]);
                }
                return doubles;
            }
        };
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Double[][].class, jsonDeserializer);
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
