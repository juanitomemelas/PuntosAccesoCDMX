package com.pipeline.wifi.wifiDemo.entity;

import com.pipeline.wifi.wifiDemo.service.WifiService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class WifiAccessPointQueryResolver implements GraphQLQueryResolver {

	@Autowired
    private WifiService wifiService;


    public WifiAccessPointQueryResolver(WifiService wifiService) {
        this.wifiService = wifiService;
    }
    
    public WifiAccessPoint wifiAccessPointById(final String id) {
        return wifiService.getWifiAccessPointsById(id);
    }
    
    public List<WifiAccessPoint> getWifiAccessPoints() {
        return wifiService.findAllWifi();
    }
    
    public List<WifiAccessPoint> getWifiAccessPointsPaginated(final int pageSize,final int offset) {
        return wifiService.findAllWifiPaginated(pageSize, offset);
    }
    
    public List<WifiAccessPoint> wifiAccessPointsByColoniaPaginated(
    		final String colonia,
    		final int pageSize,
    		final int offset) {
        return wifiService.findAllWifiByColoniaPaginated(colonia, pageSize, offset);
    }
    
}
