package sg.edu.ntu.bus_api.service;

import sg.edu.ntu.bus_api.entity.BusStop;

public interface BusStopServiceDB {
    BusStop createBusStop (BusStop busStop);

    BusStop getBusStop(String BusStopCode);
    
}
