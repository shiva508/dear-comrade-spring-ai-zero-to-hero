package com.comrade.service;

import com.comrade.model.ShipmentInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class MockApiService {

 List<ShipmentInfo>  shipmentInfos = new ArrayList<>();

 public ShipmentInfo getShipmentByAwbNumber(String awbNumber){

    return shipmentInfos.stream().filter(shipmentInfo -> shipmentInfo.getTrackingNumber().equals(awbNumber)).findAny()
             .orElseGet(ShipmentInfo::new);
 }

    public List<ShipmentInfo> getShipmentsInfo(){
        return shipmentInfos;
    }

 @PostConstruct
 public void init(){

     shipmentInfos.addAll(IntStream.range(1,5).mapToObj(value -> {
         ShipmentInfo shipmentInfo = new ShipmentInfo();
         shipmentInfo.setTrackingNumber(String.format("23712421%s",value));
         shipmentInfo.setShipmentStatus(value%2 ==0 ? "Delivered": "Dispatched");
         shipmentInfo.setDeliveryLocation(value%2 ==0 ?"Hyderabad":"Thalla Malka Puram");
         shipmentInfo.setShipperName(value%2 ==0 ? "Books": "Pens");
         shipmentInfo.setConsigneeName(value%2 ==0 ? "Shiva Dasari": "Dasari Satish");
         return shipmentInfo;
     }).collect(Collectors.toSet()));

 }
}
