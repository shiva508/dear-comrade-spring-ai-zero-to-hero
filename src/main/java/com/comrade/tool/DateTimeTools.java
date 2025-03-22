package com.comrade.tool;

import com.comrade.model.ShipmentInfo;
import com.comrade.service.MockApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DateTimeTools {

    private final MockApiService mockApiService;


    @Tool(description = "Get the current date and time")
    String getCurrentDateTime() {
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    @Tool(description = "Get the shipment info for given awb")
    ShipmentInfo shipmentInfo(String awb){
        Map<String, String> stringStringMap = new HashMap<>();
        return mockApiService.getShipmentByAwbNumber(awb);
    }

    @Tool(description = "Get the shipments info")
    List<ShipmentInfo> shipmentsInfo(){
        Map<String, String> stringStringMap = new HashMap<>();
        return mockApiService.getShipmentsInfo();
    }
}
