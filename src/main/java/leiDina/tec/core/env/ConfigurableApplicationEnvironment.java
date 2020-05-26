package main.java.leiDina.tec.core.env;

import java.util.List;

/**
 * @author vitor.alves
 */
public interface ConfigurableApplicationEnvironment {

    List<ApplicationProperty> loadApplicationProperties();

}
