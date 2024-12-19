package sg.edu.ntu.bus_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.bus_api.service.BusServiceService;
import sg.edu.ntu.bus_api.service.BusStopService;
import sg.edu.ntu.bus_api.service.BusRouteService;

@RestController
public class DatabasePopulator {
    private final BusStopService busStopService;
    private final BusServiceService busServiceService;
    private final BusRouteService busRouteService;

 

    public DatabasePopulator(BusStopService busStopService, BusServiceService busServiceService,
            BusRouteService busRouteService) {
        this.busStopService = busStopService;
        this.busServiceService = busServiceService;
        this.busRouteService = busRouteService;
    }

    @GetMapping("/populatedatabase")
    public String populateDatabses(){
        busStopService.populateBusDatabase();
        busServiceService.populateBusServiceDatabase();
        busRouteService.populateBusRoutesDatabase();
        return "All 3 Databases populated successfully!";
    }
    
}
