
package sg.edu.ntu.bus_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.bus_api.entity.BusStop;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {

}