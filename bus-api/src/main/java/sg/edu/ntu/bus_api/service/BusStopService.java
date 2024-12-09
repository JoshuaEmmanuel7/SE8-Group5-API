package sg.edu.ntu.bus_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.bus_api.entity.BusStop;
import sg.edu.ntu.bus_api.repository.BusStopRepository;

@Service
public class BusStopService {
    private final BusStopRepository busStopRepository;
    private final BusStopApiService busStopApiService;

    public BusStopService(BusStopRepository busStopRepository, BusStopApiService busStopApiService) {
        this.busStopRepository = busStopRepository;
        this.busStopApiService = busStopApiService;
    }
    public void populateBusDatabase(){
        List <BusStop> busStops = busStopApiService.fetchBusStops();
        busStopRepository.saveAll(busStops);// Save all to the database
    }

}
