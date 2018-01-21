package com.cholewinskimichal.route;


import com.cholewinskimichal.BusRouteChallengeApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BusRouteChallengeApp.class
)
@AutoConfigureMockMvc
public class RouteControllerIntegrationTest {

    static {
        try {
            createTestfile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldFindAnyDirectRoute() throws Exception {
        mvc.perform(get("/api/direct?dep_sid=15&arr_sid=995"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("direct_bus_route", is(true)))
                .andExpect(jsonPath("dep_sid", is(15)))
                .andExpect(jsonPath("arr_sid", is(995)));

    }

    @Test
    public void shouldFindAnyDirectRouteFromBottomOfDataFile() throws Exception {
        mvc.perform(get("/api/direct?dep_sid=3&arr_sid=78"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("direct_bus_route", is(true)))
                .andExpect(jsonPath("dep_sid", is(3)))
                .andExpect(jsonPath("arr_sid", is(78)));

    }

    @Test
    public void shouldNotFindAnyDirectRouteDueToLackOfGivenStop() throws Exception {
        mvc.perform(get("/api/direct?dep_sid=3&arr_sid=15002"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("direct_bus_route", is(false)))
                .andExpect(jsonPath("dep_sid", is(3)))
                .andExpect(jsonPath("arr_sid", is(15002)));

    }


    private static void createTestfile() throws IOException {
        File fout = new File("src/test/resources/data/test");
        FileOutputStream fos = new FileOutputStream(fout);

        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write("10000");
        int i = 0;
        osw.write((++i) + " 12 15 46 13 116 445 785 885 995 445");

        for (i = i; i < 9999; ++i) {
            osw.write(i + " " + createRouteLine() + "\n");
        }
        osw.write((++i) + " 1 2 3 4 6 5 7 8 41 9 88 55 22 33 66 99 11 44 77 45 78 12 32 65 998 745");

        osw.close();
    }

    private static String createRouteLine() {
        Random rand = new Random();
        Random rand2 = new Random();
        Set<Integer> ints = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (rand2.nextInt(5) + 150); i++) {
            int nextRand = (rand.nextInt(15000) + 1);
            if (ints.add(nextRand)) {
                sb.append(nextRand).append(" ");
            }
        }

        return sb.toString();
    }

}
