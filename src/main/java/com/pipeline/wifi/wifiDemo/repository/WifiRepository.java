package com.pipeline.wifi.wifiDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pipeline.wifi.wifiDemo.entity.WifiAccessPoint;

/**
 * Repositorio para realizar las búsquedas en la base de datos.
 * @author mahon
 *
 */
@Repository
public interface WifiRepository extends JpaRepository<WifiAccessPoint, String> {

}
