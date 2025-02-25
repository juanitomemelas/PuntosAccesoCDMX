package com.pipeline.wifi.wifiDemo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pipeline.wifi.wifiDemo.entity.WifiAccessPoint;

@Repository
public interface WifiRepository extends JpaRepository<WifiAccessPoint, String> {
	
    List<WifiAccessPoint> findByPrograma(final String programa);

}
