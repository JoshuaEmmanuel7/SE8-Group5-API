package sg.edu.ntu.bus_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.bus_api.service.BusStopService;

@RestController
public class BusStopController {
    private final BusStopService busStopService;

    public BusStopController(BusStopService busStopService){
        this.busStopService = busStopService;
    }
    @GetMapping("/populatebusstops")
    public String populateBusStops(){
        busStopService.populateBusDatabase();
        return "Bus Stops populated successfully!";
    }
    
}
