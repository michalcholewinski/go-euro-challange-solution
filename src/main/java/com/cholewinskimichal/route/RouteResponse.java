package com.cholewinskimichal.route;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RouteResponse {
    @JsonProperty("dep_sid")
    private int depSid;

    @JsonProperty("arr_sid")
    private int arrSid;

    @JsonProperty("direct_bus_route")
    private boolean directBusRoute;

}
