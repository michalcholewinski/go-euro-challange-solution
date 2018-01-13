package com.cholewinskimichal.route;

import lombok.Data;

import java.util.Set;

@Data
public class Route {
    private final int id;
    private final Set<Stop> stops;

}
