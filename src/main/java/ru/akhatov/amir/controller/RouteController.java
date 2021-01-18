package ru.akhatov.amir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akhatov.amir.model.dto.FindRouteDto;
import ru.akhatov.amir.service.route.RouteFinderService;

@Controller
@RequestMapping(RouteController.BASE_MAPPING)
public class RouteController {

    protected final static String BASE_MAPPING = "/route/v1";
    private final String FIND_ROUTE_MAPPING = "/find";

    @Autowired
    private RouteFinderService routeFinderService;

    @PostMapping(FIND_ROUTE_MAPPING)
    public ResponseEntity<?> findRoute(@RequestBody FindRouteDto dto) {
        return ResponseEntity.ok(routeFinderService.findRoute(dto.getFrom(), dto.getTo()));
    }
}
