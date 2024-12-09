package sg.edu.ntu.bus_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import sg.edu.ntu.bus_api.entity.BusStop;

@Service
public class BusStopApiService {
    private final RestTemplate restTemplate;

    public BusStopApiService() {
        this.restTemplate = new RestTemplate();
    }
    
    public List<BusStop> fetchBusStops() {
        String API_URL = "https://datamall2.mytransport.sg/ltaodataservice/BusStops?$skip=";
        String API_KEY = "V/dLDh/xTX6Z41u9GTWrPg==";
    
        int skip = 0;       // start at 0
        int increment = 500; // increase by 500
    
        List<BusStop> busStops = new ArrayList<>();
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
            ResponseEntity<JsonNode> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity,
                    JsonNode.class);
            JsonNode busStopsNode = response.getBody();
    
            if (busStopsNode != null && busStopsNode.has("value")) {
                JsonNode busStopArray = busStopsNode.get("value");
    
                if (busStopArray.isEmpty()) {
                    // No more records, stop the loop
                    moreRecords = false;
                } else {
                    for (JsonNode busStopNode : busStopArray) {
                        // Extract bus stop details
                        String busStopCode = busStopNode.path("BusStopCode").asText();
                        String roadName = busStopNode.path("RoadName").asText();
                        String description = busStopNode.path("Description").asText();
                        String latitude = busStopNode.path("Latitude").asText();
                        String longitude = busStopNode.path("Longitude").asText();
    
                        // Create and add BusStop object
                        BusStop busStop = new BusStop();
                        busStop.setBusStopCode(busStopCode);
                        busStop.setRoadName(roadName);
                        busStop.setDescription(description);
                        busStop.setLatitude(latitude);
                        busStop.setLongitude(longitude);
    
                        busStops.add(busStop);
                    }
                    // Increment the skip value for the next batch
                    skip += increment;
                }
            } else {
                // Stop the loop if no valid data is returned
                moreRecords = false;
            }
        }
        return busStops;
    }
}