package com.comrade.model;

import lombok.Data;

import java.util.List;

@Data
public class ShipmentInfo {
   private String TrackingNumber;
   private String ShipperName;
   private String ConsigneeName;
   private String ShipmentStatus;
   private String DeliveryLocation;
   private String shipperCountry;
   private String recipientCountry;
   List<ShipmentAction> shipmentActions;
   private Integer amount;
}
