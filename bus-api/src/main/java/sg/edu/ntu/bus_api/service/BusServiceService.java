package sg.edu.ntu.bus_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.bus_api.entity.BusService;
import sg.edu.ntu.bus_api.repository.BusServiceRepository;

@Service
public class BusServiceService {
    private final BusServiceRepository busServiceRepository;
    private final BusServiceApiService busServiceApiService;

    public BusServiceService(BusServiceRepository busServiceRepository, BusServiceApiService busServiceApiService) {
        this.busServiceRepository = busServiceRepository;
        this.busServiceApiService = busServiceApiService;
    }

    public void populateBusServiceDatabase() {
        List<BusService> busServices = busServiceApiService.fetchBusServices();
        busServiceRepository.saveAll(busServices);
    }

}
