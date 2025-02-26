package com.pipeline.wifi.wifiDemo.entity;

import com.pipeline.wifi.wifiDemo.service.WifiService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Query resolver para obtener los querys necesarios para GraphQL. tienen que coincidir con el archivo schema.graphqls
 * @author mahon
 *
 */
@Component
public class WifiAccessPointQueryResolver implements GraphQLQueryResolver {

	@Autowired
    private WifiService wifiService;


    public WifiAccessPointQueryResolver(WifiService wifiService) {
        this.wifiService = wifiService;
    }
    
    /**
     * Query para obtener todos los puntos de acceso por ID.
     * @param id
     * El ID que se quiere buscar en la tabla.
     * @return
     * Lista con los datos que coinciden.
     */
    public WifiAccessPoint wifiAccessPointById(final String id) {
        return wifiService.getWifiAccessPointsById(id);
    }
    
    /**
     * Query para obtener todos los puntos de acceso.
     * @return
     * Lista con todos los resultados de la tabla de puntos de acceso wifi.
     */
    public List<WifiAccessPoint> getWifiAccessPoints() {
        return wifiService.findAllWifi();
    }
    
    /**
     * Query para obtener todos los puntos de acceso paginados.
     * @param pageSize
     * Para definir el numero de resultados por página.
     * @param offset
     * Para el número de página.
     * @return
     * Arreglo de puntos de acceso definido por resultados por página y por página.
     */
    public List<WifiAccessPoint> getWifiAccessPointsPaginated(final int pageSize,final int offset) {
        return wifiService.findAllWifiPaginated(pageSize, offset);
    }
    
    /**
     * Query para obtener todos los puntos de acceso por colonia y paginados.
     * @param colonia
     * La colonia a buscar.
     * @param pageSize
     * Para definir el numero de resultados por página.
     * @param offset
     * Para el número de página.
     * @return
     * Arreglo de puntos de acceso definido por resultados por página y por página.
     */
    public List<WifiAccessPoint> wifiAccessPointsByColoniaPaginated(
    		final String colonia,
    		final int pageSize,
    		final int offset) {
        return wifiService.findAllWifiByColoniaPaginated(colonia, pageSize, offset);
    }
    
}
