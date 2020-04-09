package main.java.leiDina.tec.core.service;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * @author vitor.alves
 */
public abstract class BaseSystemService implements SystemService {

    private Map<Class<?>, SystemProperty<?>> systemPropertyByType = new HashMap<>();

    private Map<String, SystemProperty<?>> systemPropertyByName = new HashMap<>();

    protected void add(SystemProperty<?> systemProperty) {
        systemPropertyByName.put(systemProperty.getName(), systemProperty);
        systemPropertyByType.put(systemProperty.getType(), systemProperty);
    }

    protected void addByType(Class<?> type, SystemProperty<?> systemProperty){
        systemPropertyByType.put(type, systemProperty);
    }

    protected void addByName(String name, SystemProperty<?> systemProperty) {
        this.systemPropertyByName.put(name, systemProperty);
    }

    @Override
    public <T extends SystemProperty<?>> T getPropertyByType(Class<?> type) {
        return (T) this.systemPropertyByType.get(type);
    }

    @Override
    public <T extends SystemProperty<?>> T getPropertyByName(String name) {
        return (T) this.systemPropertyByName.get(name);
    }

    @Override
    public <T extends SystemProperty<?>> void addProperty(T property) {
        SystemProperty<?> systemProperty = this.systemPropertyByName.get(property.getName());
        if (systemProperty != null) {
            systemProperty.addProperties(property.getProperties());
        }
        systemProperty = this.systemPropertyByType.get(property.getType());
        if (systemProperty != null) {
            systemProperty.addProperties(property.getProperties());
        }
    }
}
