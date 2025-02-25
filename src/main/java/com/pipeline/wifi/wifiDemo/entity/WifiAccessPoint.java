package com.pipeline.wifi.wifiDemo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "wifi_access_point")
@Data
public class WifiAccessPoint {	

	//private static final long serialVersionUID = 7266139754715243383L;
	

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
