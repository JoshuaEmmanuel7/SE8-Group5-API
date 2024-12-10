package sg.edu.ntu.bus_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    private String ServiceNo;
    private String Operator;
    private String Direction;
    private String StopSequence;
    private String BusStopCode;
    private String Distance;
    private String WD_FirstBus;
    private String WD_LastBus;
    private String SAT_FirstBus;
    private String SAT_LastBus;
    private String SUN_FirstBus;
    private String SUN_LastBus;
    
}
