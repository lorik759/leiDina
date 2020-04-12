package main.java.leiDina.tec.core.env;

import java.util.List;
import main.java.leiDina.tec.core.service.IntegrationSystemService;
import main.java.leiDina.tec.core.service.SystemService;

/**
 * @author vitor.alves
 */
public interface SystemLoader {

    List<SystemService> loadSystemServices();

    List<IntegrationSystemService> loadIntegrationSystemServices();

}
