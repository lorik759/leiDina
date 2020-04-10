package main.java.leiDina.tec.core.mock;

import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.SystemServiceKey;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.service.SystemService;

/**
 * @author vitor.alves
 */
public class SystemServiceMock implements SystemService {

    @Override
    public String getServiceName() {
        return "";
    }

    @Override
    public void init(ConfigurableApplicationEnvironment environment) {

    }

    @Override
    public SystemServiceKey getKey() {
        return new MockServiceServiceKey();
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
    public <T extends SystemProperty<?>> void addProperty(T property) {

    }

    @Override
    public String getEnvironmentName() {
        return null;
    }
}
