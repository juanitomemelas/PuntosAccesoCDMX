package com.pipeline.wifi.wifiDemo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad para obtener el objeto de WifiAccesPoint desde la tabla de wifi_access_point.
 * @author mahonry
 *
 */
@Entity
@Table(name = "wifi_access_point")
@Data
public class WifiAccessPoint {	

	@Id
	@Column(name = "id")
	private String id;


	@Column(name = "programa")
    private String programa;
	
	@Column(name = "lat")
    private Double lat;
	
	@Column(name = "lon")
    private Double lon;
	
	@Column(name = "colonia")
    private String colonia;
	
	@Column(name = "alcaldia")
    private String alcaldia;

}
