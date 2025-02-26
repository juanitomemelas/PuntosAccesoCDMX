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

	/**.
	 * Servicio para buscar y discriminar los puntos de acceso por ID.
	 * @param id
	 * El ID a comparar.
	 * @return
	 * Lista con puntos de acceso.
	 */
	@Transactional(readOnly = true)
    public WifiAccessPoint getWifiAccessPointsById(final String id) {
        return wifiRepository.findAll().stream()
				.filter(wifiAccessPoint -> wifiAccessPoint.getId().equals(id))
				.findFirst()
				.orElse(null);
    }

	/**
	 * Servicio que trae todos los puntos de acceso de la base de datos.
	 * @return
	 * Lista con los puntos de acceso que se tienen en la base.
	 */
	public List<WifiAccessPoint> findAllWifi() {
		return wifiRepository.findAll();
	}
	
	/**
	 * Servicio que obtiene los puntos de acceso que se tienen en la base de datos pero paginados.
	 * @param pageSize
	 * El tamaño de los resultados por página.
	 * @param offset
	 * La página de resultados.
	 * @return
	 * Arreglo con los puntos de acceso definido por las variables de pagesize y offset.
	 */
	@Transactional(readOnly = true)
	public List<WifiAccessPoint> findAllWifiPaginated(final int pageSize, final int offset) {
		return wifiRepository.findAll(PageRequest.of(offset, pageSize)).getContent();
	}

	/**
	 * Servicio que obtiene los puntos de acceso que se tienen en la base de datos por colonia y paginados.
	 * @param colonia
	 * La colonia a buscar.
	 * @param pageSize
	 * El tamaño de los resultados por página.
	 * @param offset
	 * La página de resultados.
	 * @return
	 * Arreglo con los puntos de acceso definido por las variables de pagesize y offset.
	 */
	public List<WifiAccessPoint> findAllWifiByColoniaPaginated(
			final String colonia,
			final int pageSize,
			final int offset 
			) {
		return wifiRepository.findAll(PageRequest.of(offset, pageSize))
				.getContent()
				.stream()
				.filter(
					wifiAccessPoint -> wifiAccessPoint.getColonia().equalsIgnoreCase(colonia)
				)
				.toList();
	}
	
}
