package com.cholewinskimichal.route;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RouteServiceTest {

    @TestConfiguration
    public static class RouteServiceImplTestContextConfiguration {
        @Bean
        public RouteService routeService() {
            return new RouteService();
        }
    }

    @Autowired
    private RouteService routeService;

    @MockBean
    private RouteFinder routeFinder;

    @Before
    public void setUp() {
        Mockito.when(routeFinder.getRoutes()).thenReturn(prepareRoutes());
    }


    @Test
    public void shouldFindDirectRoute() {
        //given
        int depSid = 8;
        int arrSid = 4;
        //when
        RouteResponse directRoutePresent = routeService.isDirectRoutePresent(depSid, arrSid);
        //then
        assertThat(directRoutePresent).isNotNull();
        assertThat(directRoutePresent.isDirectBusRoute()).isTrue();
    }

    @Test
    public void shouldNotFindDirectRoute() {
        //given
        int depSid = 25;
        int arrSid = 44;
        //when
        RouteResponse directRoutePresent = routeService.isDirectRoutePresent(depSid, arrSid);
        //then
        assertThat(directRoutePresent).isNotNull();
        assertThat(directRoutePresent.isDirectBusRoute()).isFalse();
    }

    @Test
    public void shouldNotFindDirectRouteBecauseOfMissingIds() {
        //given
        int depSid = 285;
        int arrSid = 666;
        //when
        RouteResponse directRoutePresent = routeService.isDirectRoutePresent(depSid, arrSid);
        //then
        assertThat(directRoutePresent).isNotNull();
        assertThat(directRoutePresent.isDirectBusRoute()).isFalse();
    }

    private Set<Route> prepareRoutes() {

        Set<Route> routes = Sets.newHashSet();
        routes.add(buildRoute("1", "2", "83", "103", "45", "21", "33", "929", "48", "5", "6"));
        routes.add(buildRoute("2", "25", "8", "10", "45", "133", "4", "919", "48", "5", "66"));
        routes.add(buildRoute("3", "2", "68", "10", "45", "13", "4", "99", "48", "5", "6"));
        routes.add(buildRoute("4", "244", "8", "160", "45", "19", "4", "99", "48", "5", "44"));
        routes.add(buildRoute("5", "24", "8", "224", "465", "10", "344", "99", "48", "5", "6"));
        routes.add(buildRoute("6", "23", "8", "10", "42", "16", "4", "99", "48", "5", "33"));
        routes.add(buildRoute("7", "2", "8", "484", "5", "13", "44", "99", "48", "5", "6"));
        routes.add(buildRoute("8", "23", "8", "10", "45", "1", "4", "9", "48", "5", "163"));
        routes.add(buildRoute("9", "2", "8", "10", "415", "1", "14", "19", "48", "5", "68"));
        routes.add(buildRoute("10", "2", "8", "110", "415", "11", "4", "99", "48", "5", "64"));
        routes.add(buildRoute("11", "2", "18", "10", "45", "166", "4", "99", "48", "5", "65"));
        routes.add(buildRoute("12", "2", "8", "10", "53", "61", "4", "99", "48", "5", "6"));
        routes.add(buildRoute("13", "12", "8", "10", "515", "17", "4", "99", "48", "5", "65"));
        routes.add(buildRoute("14", "2", "18", "10", "225", "51", "4", "99", "48", "5", "55"));
        routes.add(buildRoute("15", "22", "8", "122", "75", "14", "4", "99", "48", "5", "88"));
        routes.add(buildRoute("16", "12", "8", "146", "48", "13", "4", "939", "48", "5", "66"));
        routes.add(buildRoute("17", "2", "8", "50", "95", "21", "4", "99", "48", "5", "64"));
        routes.add(buildRoute("18", "12", "8", "860", "63", "1", "4", "919", "48", "5", "36"));
        routes.add(buildRoute("19", "2", "8", "465", "34", "13", "4", "99", "48", "5", "62"));
        routes.add(buildRoute("20", "2", "18", "330", "45", "1", "4", "9", "4", "5", "16"));

        return routes;
    }

    private Route buildRoute(String routeId, String... stopIds) {
        List<Stop> stops = Lists.newArrayList();
        for (String stopId : stopIds) {
            stops.add(new Stop(stopId));
        }
        return new Route(routeId, stops);
    }
}
