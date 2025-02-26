package com.pipeline.wifi.wifiDemo.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.pipeline.wifi.wifiDemo.entity.WifiAccessPoint;
import com.pipeline.wifi.wifiDemo.service.WifiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class WifiControllerTest {

    @Mock
    private WifiService wifiService;

    private WifiController wifiController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        wifiController = new WifiController(wifiService);
    }

    // Test para wifiAccessPointsPaginated
    @Test
    public void testWifiAccessPointsPaginated() {
        // Datos de prueba
        int pageSize = 10;
        int offset = 0;

        // Creamos objetos WifiAccessPoint de ejemplo
        WifiAccessPoint accessPoint1 = new WifiAccessPoint();
        accessPoint1.setId("1");
        accessPoint1.setPrograma("Programa 1");
        accessPoint1.setLat(19.4326);
        accessPoint1.setLon(-99.1332);
        accessPoint1.setColonia("Colonia A");
        accessPoint1.setAlcaldia("Alcaldia 1");

        WifiAccessPoint accessPoint2 = new WifiAccessPoint();
        accessPoint2.setId("2");
        accessPoint2.setPrograma("Programa 2");
        accessPoint2.setLat(19.4330);
        accessPoint2.setLon(-99.1340);
        accessPoint2.setColonia("Colonia B");
        accessPoint2.setAlcaldia("Alcaldia 2");

        List<WifiAccessPoint> mockWifiList = List.of(accessPoint1, accessPoint2);

        // Simulamos el comportamiento del servicio
        when(wifiService.findAllWifiPaginated(pageSize, offset)).thenReturn(mockWifiList);

        // Ejecutamos el método
        List<WifiAccessPoint> result = wifiController.wifiAccessPointsPaginated(pageSize, offset);

        // Verificaciones
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Programa 1", result.get(0).getPrograma());
        assertEquals(19.4326, result.get(0).getLat());
        assertEquals(-99.1332, result.get(0).getLon());
        assertEquals("Colonia A", result.get(0).getColonia());
        assertEquals("Alcaldia 1", result.get(0).getAlcaldia());
        verify(wifiService, times(1)).findAllWifiPaginated(pageSize, offset);
    }

    // Test para wifiAccessPointsByColoniaPaginated
    @Test
    public void testWifiAccessPointsByColoniaPaginated() {
        // Datos de prueba
        String colonia = "Colonia A";
        int pageSize = 10;
        int offset = 0;

        // Creamos objetos WifiAccessPoint de ejemplo
        WifiAccessPoint accessPoint1 = new WifiAccessPoint();
        accessPoint1.setId("1");
        accessPoint1.setPrograma("Programa 1");
        accessPoint1.setLat(19.4326);
        accessPoint1.setLon(-99.1332);
        accessPoint1.setColonia("Colonia A");
        accessPoint1.setAlcaldia("Alcaldia 1");

        WifiAccessPoint accessPoint2 = new WifiAccessPoint();
        accessPoint2.setId("2");
        accessPoint2.setPrograma("Programa 2");
        accessPoint2.setLat(19.4330);
        accessPoint2.setLon(-99.1340);
        accessPoint2.setColonia("Colonia A");
        accessPoint2.setAlcaldia("Alcaldia 2");

        List<WifiAccessPoint> mockWifiList = List.of(accessPoint1, accessPoint2);

        // Simulamos el comportamiento del servicio
        when(wifiService.findAllWifiByColoniaPaginated(colonia, pageSize, offset)).thenReturn(mockWifiList);

        // Ejecutamos el método
        List<WifiAccessPoint> result = wifiController.wifiAccessPointsByColoniaPaginated(colonia, pageSize, offset);

        // Verificaciones
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(wifiService, times(1)).findAllWifiByColoniaPaginated(colonia, pageSize, offset);
    }

    // Test para wifiAccessPointById
    @Test
    public void testWifiAccessPointById() {
        // Datos de prueba
        String id = "1";

        WifiAccessPoint mockAccessPoint = new WifiAccessPoint();
        mockAccessPoint.setId(id);
        mockAccessPoint.setPrograma("Programa 1");
        mockAccessPoint.setLat(19.4326);
        mockAccessPoint.setLon(-99.1332);
        mockAccessPoint.setColonia("Colonia A");
        mockAccessPoint.setAlcaldia("Alcaldia 1");

        // Simulamos el comportamiento del servicio
        when(wifiService.getWifiAccessPointsById(id)).thenReturn(mockAccessPoint);

        // Ejecutamos el método
        WifiAccessPoint result = wifiController.wifiAccessPointById(id);

        // Verificaciones
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("Programa 1", result.getPrograma());
        assertEquals(19.4326, result.getLat());
        assertEquals(-99.1332, result.getLon());
        assertEquals("Colonia A", result.getColonia());
        assertEquals("Alcaldia 1", result.getAlcaldia());
        verify(wifiService, times(1)).getWifiAccessPointsById(id);
    }

    // Test para getWifiAccessPoints
    @Test
    public void testGetWifiAccessPoints() {
        // Datos de prueba
        WifiAccessPoint accessPoint1 = new WifiAccessPoint();
        accessPoint1.setId("1");
        accessPoint1.setPrograma("Programa 1");
        accessPoint1.setLat(19.4326);
        accessPoint1.setLon(-99.1332);
        accessPoint1.setColonia("Colonia A");
        accessPoint1.setAlcaldia("Alcaldia 1");

        WifiAccessPoint accessPoint2 = new WifiAccessPoint();
        accessPoint2.setId("2");
        accessPoint2.setPrograma("Programa 2");
        accessPoint2.setLat(19.4330);
        accessPoint2.setLon(-99.1340);
        accessPoint2.setColonia("Colonia B");
        accessPoint2.setAlcaldia("Alcaldia 2");

        List<WifiAccessPoint> mockWifiList = List.of(accessPoint1, accessPoint2);

        // Simulamos el comportamiento del servicio
        when(wifiService.findAllWifi()).thenReturn(mockWifiList);

        // Ejecutamos el método
        List<WifiAccessPoint> result = wifiController.getWifiAccessPoints();

        // Verificaciones
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(wifiService, times(1)).findAllWifi();
    }

    // Test para manejar una lista vacía
    @Test
    public void testWifiAccessPointsPaginatedEmpty() {
        // Datos de prueba
        int pageSize = 10;
        int offset = 0;

        // Simulamos el comportamiento del servicio
        when(wifiService.findAllWifiPaginated(pageSize, offset)).thenReturn(List.of());

        // Ejecutamos el método
        List<WifiAccessPoint> result = wifiController.wifiAccessPointsPaginated(pageSize, offset);

        // Verificaciones
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(wifiService, times(1)).findAllWifiPaginated(pageSize, offset);
    }
}
