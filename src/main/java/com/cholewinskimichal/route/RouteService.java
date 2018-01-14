package com.cholewinskimichal.route;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Service
public class RouteService {

    @Autowired
    private RouteFinder routeFinder;


    public RouteResponse isDirectRoutePresent(int depSid, int arrSid) {
        List<Route> directRoutes = findDirectRoutes(depSid, arrSid);
        return buildRouterResponse(depSid, arrSid, !directRoutes.isEmpty());
    }

    private RouteResponse buildRouterResponse(int depSid, int arrSid, boolean isDirectRoutePresent) {
        RouteResponse routeResponse = new RouteResponse();
        routeResponse.setDepSid(depSid);
        routeResponse.setArrSid(arrSid);
        routeResponse.setDirectBusRoute(isDirectRoutePresent);
        return routeResponse;
    }

    private List<Route> findDirectRoutes(int depSid, int arrSid) {
        return routeFinder.getRoutes()
                .stream()
                .filter(route -> route.hasStops(valueOf(depSid), valueOf(arrSid)))
                .collect(Collectors.toList());
    }

}
