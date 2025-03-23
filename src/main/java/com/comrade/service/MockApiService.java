package com.comrade.service;

import com.comrade.model.ShipmentAction;
import com.comrade.model.ShipmentInfo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
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

     Random randomGenerator = new Random();
     shipmentInfos.addAll(IntStream.range(1,5).mapToObj(value -> {
         ShipmentInfo shipmentInfo = new ShipmentInfo();
         shipmentInfo.setTrackingNumber(String.format("23712421%s",value));
         shipmentInfo.setShipmentStatus(value%2 ==0 ? "Delivered": "Dispatched");
         shipmentInfo.setDeliveryLocation(value%2 ==0 ?"Hyderabad":"Thalla Malka Puram");
         shipmentInfo.setShipperName(value%2 ==0 ? "Books": "Pens");
         shipmentInfo.setConsigneeName(value%2 ==0 ? "Shiva Dasari": "Dasari Satish");
         shipmentInfo.setAmount(randomGenerator.nextInt(10_000));

         ShipmentAction shipmentActionOne = new ShipmentAction();
         shipmentActionOne.setActionDescription("Shipped from New Yark");
         shipmentActionOne.setActionDate(Date.from(Instant.ofEpochMilli(System.currentTimeMillis()-1000000)));

         ShipmentAction shipmentActionTwo = new ShipmentAction();
         shipmentActionTwo.setActionDescription("Reached New Yark airport");
         shipmentActionTwo.setActionDate(Date.from(Instant.ofEpochMilli(System.currentTimeMillis()-10000)));

         ShipmentAction shipmentActionThree= new ShipmentAction();
         shipmentActionThree.setActionDescription("Reached New Hyderabad airport");
         shipmentActionThree.setActionDate(Date.from(Instant.ofEpochMilli(System.currentTimeMillis()-1000)));

         ShipmentAction shipmentActionFour= new ShipmentAction();
         shipmentActionFour.setActionDescription("Delivered to destination");
         shipmentActionFour.setActionDate(Date.from(Instant.ofEpochMilli(System.currentTimeMillis()-100)));
         if (value ==1){
             shipmentInfo.setShipmentActions(List.of(shipmentActionOne,shipmentActionTwo,shipmentActionThree,shipmentActionFour));
         }
         return shipmentInfo;
     }).collect(Collectors.toSet()));
     shipmentInfos.forEach(shipmentInfo -> {
         log.info("{}",shipmentInfo);
     });
 }
}
