package com.cholewinskimichal.route;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class RootFinder {

    public static final String DATA_FILE_PATH = "/data/example";
    private final Set<Route> routes;


    public RootFinder() throws IOException {
        this.routes = new HashSet<>();
        loadRoutes();
    }

    private void loadRoutes() throws IOException {
        Stream<String> stream = Files.lines(Paths.get(DATA_FILE_PATH));

        //TODO create routes
    }
}
