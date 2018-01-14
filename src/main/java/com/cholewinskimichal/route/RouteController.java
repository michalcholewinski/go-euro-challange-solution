package com.cholewinskimichal.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/direct")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    public RouteResponse isDirect(@RequestParam("dep_sid") int depSid, @RequestParam("arr_sid") int arrSid) {
        return routeService.isDirectRoutePresent(depSid, arrSid);
    }
}
