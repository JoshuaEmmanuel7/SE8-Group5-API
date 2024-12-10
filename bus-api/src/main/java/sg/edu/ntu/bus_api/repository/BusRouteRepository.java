package sg.edu.ntu.bus_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.bus_api.entity.BusRoute;

public interface BusRouteRepository extends JpaRepository<BusRoute , Long> {
    
}
