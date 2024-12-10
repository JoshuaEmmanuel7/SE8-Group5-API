
package sg.edu.ntu.bus_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.bus_api.entity.BusService;

public interface BusServiceRepository extends JpaRepository <BusService, Long> {

}
