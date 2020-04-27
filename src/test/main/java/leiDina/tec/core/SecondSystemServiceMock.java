package main.java.leiDina.tec.core;

import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.model.SystemServiceKey;
import main.java.leiDina.tec.core.service.SystemService;

/**
 * @author vitor.alves
 */
public class SecondSystemServiceMock implements SystemService {

    @Override
    public String getServiceName() {
        return "";
    }

    @Override
    public void init(ConfigurableApplicationEnvironment environment) {

    }

    @Override
    public SystemServiceKey getKey() {
        return new MockSecondServiceServiceKey();
    }

    @Override
    public <T extends SystemProperty<?>> T getPropertyByType(Class<?> type) {
        return null;
    }

    @Override
    public <T extends SystemProperty<?>> T getPropertyByName(String name) {
        return null;
    }

    @Override
    public String getEnvironmentName() {
        return null;
    }
}