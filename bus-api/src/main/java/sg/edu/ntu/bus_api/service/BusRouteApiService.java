package sg.edu.ntu.bus_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import sg.edu.ntu.bus_api.entity.BusRoute;

@Service
public class BusRouteApiService {
    private final RestTemplate restTemplate;

    public BusRouteApiService() {
        this.restTemplate = new RestTemplate();
    }

    public List<BusRoute> fetchBusRoutes() {
        String API_URL = "https://datamall2.mytransport.sg/ltaodataservice/BusRoutes?$skip=";
        String API_KEY = "V/dLDh/xTX6Z41u9GTWrPg==";

        int skip = 0; // start at 0
        int increment = 500; // increase by 500

        List<BusRoute> busRoutes = new ArrayList<>();
        boolean moreRecords = true; // for while loop

        // Set up headers with the API key
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("accountkey", API_KEY);

        while (moreRecords) {
            String url = API_URL + skip;

            // Create the HTTP request
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Fetch data from the API
            ResponseEntity<JsonNode> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET,
                    entity,
                    JsonNode.class);
            JsonNode busRoutesNode = response.getBody();
            if (busRoutesNode != null && busRoutesNode.has("value")) {
                JsonNode busRoutesArray = busRoutesNode.get("value");
                if (busRoutesArray.isEmpty()) {
                    // No more records, stop the loop
                    moreRecords = false;
                } else {
                    for (JsonNode busRouteNode : busRoutesArray) {
                        // Extract bus Service details
                        String serviceNo = busRouteNode.path("ServiceNo").asText();
                        String operator = busRouteNode.path("Operator").asText();
                        String direction = busRouteNode.path("Direction").asText();
                        String stopSequence = busRouteNode.path("StopSequence").asText();
                        String busStopCode = busRouteNode.path("BusStopCode").asText();
                        String distance = busRouteNode.path("Distance").asText();
                        String wdFirstBus = busRouteNode.path("WD_FirstBus").asText();
                        String wdLastBus = busRouteNode.path("WD_LastBus").asText();
                        String satFirstBus = busRouteNode.path("SAT_FirstBus").asText();
                        String satLastBus = busRouteNode.path("SAT_LastBus").asText();
                        String sunFirstBus = busRouteNode.path("SUN_FirstBus").asText();
                        String sunLastBus = busRouteNode.path("SUN_LastBus").asText();

                        // Create and populate BusService object
                        BusRoute busRoute = new BusRoute();
                        busRoute.setServiceNo(serviceNo);
                        busRoute.setOperator(operator);
                        busRoute.setDirection(direction);
                        busRoute.setStopSequence(stopSequence);
                        busRoute.setBusStopCode(busStopCode);
                        busRoute.setDistance(distance);
                        busRoute.setWD_FirstBus(wdFirstBus);
                        busRoute.setWD_LastBus(wdLastBus);
                        busRoute.setSAT_FirstBus(satFirstBus);
                        busRoute.setSAT_LastBus(satLastBus);
                        busRoute.setSUN_FirstBus(sunFirstBus);
                        busRoute.setSUN_LastBus(sunLastBus);
                        // Add the BusService object to a list or process it as needed
                        busRoutes.add(busRoute);
                    }
                    // Increment the skip value for the next batch
                    skip += increment;
                }
            } else {
                // Stop the loop if no valid data is returned
                moreRecords = false;
            }
        }
        return busRoutes;
    }
}
