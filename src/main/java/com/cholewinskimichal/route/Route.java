package com.cholewinskimichal.route;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Route {
    private final String id;

    @Getter(AccessLevel.NONE)
    private final List<Stop> stops;

    public boolean hasStops(String... stopIds) {
        List<String> ids = stops.stream()
                .map(Stop::getId)
                .collect(Collectors.toList());
        for (String id : stopIds) {
            if (!ids.contains(id)) {
                return false;
            }
        }
        return true;

    }

}
