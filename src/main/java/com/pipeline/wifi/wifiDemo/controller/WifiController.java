package com.pipeline.wifi.wifiDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.pipeline.wifi.wifiDemo.entity.WifiAccessPoint;
import com.pipeline.wifi.wifiDemo.service.WifiService;

@Controller
public class WifiController {
	
	@Autowired
	private final WifiService wifiService;
	
	public WifiController (final WifiService wifiService) {
		this.wifiService = wifiService;		
	}
	
    @QueryMapping("wifiAccessPointsPaginated")
    public List<WifiAccessPoint> wifiAccessPointsPaginated(@Argument int pageSize, @Argument int offset) {
        return wifiService.findAllWifiPaginated(pageSize, offset);
    }
	
    @QueryMapping("wifiAccessPointsByColoniaPaginated")
    public List<WifiAccessPoint> wifiAccessPointsByColoniaPaginated(
    		@Argument String colonia,
    		@Argument int pageSize, 
    		@Argument int offset) {
        return wifiService.findAllWifiByColoniaPaginated(colonia, pageSize, offset);
    }
    
    @QueryMapping("wifiAccessPointById")
    public WifiAccessPoint wifiAccessPointById(@Argument String id) {
        return wifiService.getWifiAccessPointsById(id);
    }

    @QueryMapping("wifiAccessPoints")
    public List<WifiAccessPoint> getWifiAccessPoints() {
        return wifiService.findAllWifi();
    }
    
}
