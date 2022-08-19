package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.exepttion.ParkingNotFoundException;
import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking>parkingMap = new HashMap<>();

    static {
        var id= getUUId();
        var id1= getUUId();
        Parking parking = new Parking(id, "DMS-1111","SC", "CELTA" , "Preto");
        Parking parking1 = new Parking(id1, "BAS-1212","SC", "VW GOL" , "BRANCO");
        parkingMap.put(id,parking);
        parkingMap.put(id1,parking1);

    }

    @GetMapping
    public List<Parking> findAll() {


        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUId() {
        return UUID.randomUUID().toString().replace("-","");

    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking ==  null){
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUId();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid,parkingCreate);
        return parkingCreate;
    }
}
