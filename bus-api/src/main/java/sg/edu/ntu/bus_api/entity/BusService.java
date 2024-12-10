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
public class BusService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busServiceID;

    private String serviceNo;
    private String operator;
    private String direction;
    private String category;
    private String originCode;
    private String destinationCode;
    private String aM_Peak_Freq;
    private String aM_Offpeak_Freq;
    private String pM_Peak_Freq;
    private String pM_Offpeak_Freq;
    private String loopDesc;

}
