package com.pipeline.wifi.wifiDemo.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pipeline.wifi.wifiDemo.entity.WifiAccessPoint;
import com.pipeline.wifi.wifiDemo.repository.WifiRepository;

@Service
public class WifiService {	
	
	private final WifiRepository wifiRepository;
	
	public WifiService (final WifiRepository wifiRepository) {
		this.wifiRepository = wifiRepository;		
	}

	@Transactional(readOnly = true)
    public WifiAccessPoint getWifiAccessPointsById(final String id) {
        return wifiRepository.findAll().stream()
				.filter(wifiAccessPoint -> wifiAccessPoint.getId().equals(id))
				.findFirst()
				.orElse(null);
    }

	public List<WifiAccessPoint> findAllWifi() {
		return wifiRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<WifiAccessPoint> findAllWifiPaginated(final int pageSize, final int offset) {
		return wifiRepository.findAll(PageRequest.of(offset, pageSize)).getContent();
	}

	public List<WifiAccessPoint> findAllWifiByColoniaPaginated(
			final String colonia,
			final int pageSize,
			final int offset 
			) {
		return wifiRepository.findAll(PageRequest.of(offset, pageSize))
				.getContent()
				.stream()
				.filter(
					wifiAccessPoint -> wifiAccessPoint.getColonia().equals(colonia)
				)
				.toList();
	}
	
}
