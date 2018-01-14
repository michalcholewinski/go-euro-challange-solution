package com.cholewinskimichal.route;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RouteFinder {

    public static final String DATA_FILE_NAME = "example";
    public static final String DATA_DIR = "data";
    public static final String STOP_ID_SEPARATOR = " ";
    public static final int LINE_ID_INDEX = 0;
    private final Set<Route> routes = new HashSet<>();


    public RouteFinder() throws IOException {
        loadRoutes();
    }

    private void loadRoutes() throws IOException {
        List<String> lines = Files.lines(Paths.get(DATA_DIR, DATA_FILE_NAME)).collect(Collectors.toList());

        int lineCounter = 0;
        for (String line : lines) {
            if (lineCounter > 0) {
                String[] stops = line.split(STOP_ID_SEPARATOR);
                routes.add(buildBusLine(stops));
            }
            lineCounter++;
        }
    }

    private Route buildBusLine(String[] stopsArray) {
        List<Stop> stops=new ArrayList<>();
        String routeId=null;
        for (int i = 0; i < stopsArray.length; i++) {
            if (i == LINE_ID_INDEX) {
                routeId=stopsArray[i];
            }else{
                stops.add(new Stop(stopsArray[i]));
            }
        }
        return new Route(routeId, stops);
    }

    public Set<Route> getRoutes() {
        return routes;
    }


}
