package com.comrade.model;

import lombok.Data;

@Data
public class ShipmentInfo {
   private String TrackingNumber;
   private String ShipperName;
   private String ConsigneeName;
   private String ShipmentStatus;
   private String DeliveryLocation;
   private Integer amount;
}
