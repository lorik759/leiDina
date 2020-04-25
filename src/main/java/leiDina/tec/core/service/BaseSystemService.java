package main.java.leiDina.tec.core.service;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * A base implementation of the {@link SystemService} interface. Objects that extends this class has the default behavior of a System Service.
 *
 * @author vitor.alves
 */
@Deprecated
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

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends SystemProperty<?>> T getPropertyByType(Class<?> type) {
        return (T) this.systemPropertyByType.get(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends SystemProperty<?>> T getPropertyByName(String name) {
        return (T) this.systemPropertyByName.get(name);
    }
}
