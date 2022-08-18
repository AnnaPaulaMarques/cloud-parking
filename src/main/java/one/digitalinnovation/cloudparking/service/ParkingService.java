package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking>parkingMap = new HashMap<>();

    static {
        var id= getUUId();
        Parking parking = new Parking(id, "DMS-1111","SC", "DELTA" , "Preto");
        parkingMap.put(id,parking);

    }

    @GetMapping
    public List<Parking> findAll() {


        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUId() {
        return UUID.randomUUID().toString().replace("-","");

    }
}
