package sg.edu.ntu.bus_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.bus_api.entity.BusRoute;
import sg.edu.ntu.bus_api.repository.BusRouteRepository;

@Service
public class BusRouteService {
    private final BusRouteRepository busRouteRepository;
    private final BusRouteApiService busRouteApiService;

  
    public BusRouteService(BusRouteRepository busRouteRepository, BusRouteApiService busRouteApiService) {
        this.busRouteRepository = busRouteRepository;
        this.busRouteApiService = busRouteApiService;
    }


    public void populateBusRoutesDatabase(){
        List<BusRoute> busRoutes = busRouteApiService.fetchBusRoutes();
        busRouteRepository.saveAll(busRoutes);
    }
    
    
}
