package com.cholewinskimichal.route;


import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RouteService {





    public RouteResponse isDirectRoutePresent(int depSid, int arrSid) {
        return buildRouterResponse(depSid,arrSid, !findDirectRoutes(depSid,arrSid).isEmpty());
    }

    private RouteResponse buildRouterResponse(int depSid, int arrSid, boolean isDirectRoutePresent) {
        RouteResponse routeResponse = new RouteResponse();
        routeResponse.setDepSid(depSid);
        routeResponse.setArrSid(arrSid);
        routeResponse.setDirectBusRoute(isDirectRoutePresent);
        return  routeResponse;
    }

    private Set<Route> findDirectRoutes(int depSid, int arrSid) {
        return null;
    }


}
