package sg.edu.ntu.bus_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import sg.edu.ntu.bus_api.entity.BusService;

@Service
public class BusServiceApiService {
    private final RestTemplate restTemplate;

    public BusServiceApiService() {
        this.restTemplate = new RestTemplate();
    }

    public List<BusService> fetchBusServices() {
        String API_URL = "https://datamall2.mytransport.sg/ltaodataservice/BusServices?$skip=";
        String API_KEY = "V/dLDh/xTX6Z41u9GTWrPg==";

        int skip = 0; // start at 0
        int increment = 500; // increase by 500

        List<BusService> busServices = new ArrayList<>();
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
            JsonNode busServicesNode = response.getBody();

            if (busServicesNode != null && busServicesNode.has("value")) {
                JsonNode busServicesArray = busServicesNode.get("value");

                if (busServicesArray.isEmpty()) {
                    // No more records, stop the loop
                    moreRecords = false;
                } else {
                    for (JsonNode busServiceNode : busServicesArray) {
                        // Extract bus Service details
                        String serviceNo = busServiceNode.path("ServiceNo").asText();
                        String operator = busServiceNode.path("Operator").asText();
                        String direction = busServiceNode.path("Direction").asText();
                        String category = busServiceNode.path("Category").asText();
                        String originCode = busServiceNode.path("OriginCode").asText();
                        String destinationCode = busServiceNode.path("DestinationCode").asText();
                        String amPeakFreq = busServiceNode.path("AM_Peak_Freq").asText();
                        String amOffpeakFreq = busServiceNode.path("AM_Offpeak_Freq").asText();
                        String pmPeakFreq = busServiceNode.path("PM_Peak_Freq").asText();
                        String pmOffpeakFreq = busServiceNode.path("PM_Offpeak_Freq").asText();
                        String loopDesc = busServiceNode.path("LoopDesc").asText();

                        // Create and populate BusService object
                        BusService busService = new BusService();
                        busService.setServiceNo(serviceNo);
                        busService.setOperator(operator);
                        busService.setDirection(direction);
                        busService.setCategory(category);
                        busService.setOriginCode(originCode);
                        busService.setDestinationCode(destinationCode);
                        busService.setAM_Peak_Freq(amPeakFreq);
                        busService.setAM_Offpeak_Freq(amOffpeakFreq);
                        busService.setPM_Peak_Freq(pmPeakFreq);
                        busService.setPM_Offpeak_Freq(pmOffpeakFreq);
                        busService.setLoopDesc(loopDesc);

                        // Add the BusService object to a list or process it as needed
                        busServices.add(busService);
                    }
                    // Increment the skip value for the next batch
                    skip += increment;
                }
            } else {
                // Stop the loop if no valid data is returned
                moreRecords = false;
            }
        }
        return busServices;
    }
}
