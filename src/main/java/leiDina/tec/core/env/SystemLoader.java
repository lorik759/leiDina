package main.java.leiDina.tec.core.env;

import java.util.List;
import main.java.leiDina.tec.core.service.SystemService;

/**
 * @author vitor.alves
 */
@Deprecated
public interface SystemLoader {

    List<SystemService> loadSystemServices();

}
