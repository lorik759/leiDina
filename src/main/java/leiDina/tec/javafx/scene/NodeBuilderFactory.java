package main.java.leiDina.tec.javafx.scene;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import main.java.leiDina.tec.core.model.ClassSystemProperties;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public class NodeBuilderFactory implements BuilderFactory {
    
    private final Map<Class<?>, Builder<?>> builders = new HashMap<>();

    public NodeBuilderFactory(ClassSystemProperties classSystemProperties) {
        getBuildersFromProperties(classSystemProperties);
    }

    private void getBuildersFromProperties(ClassSystemProperties classSystemProperties) {
        for (ClassSystemProperties systemProperty : classSystemProperties.getClassSystemProperties()) {
            Class<?> type = systemProperty.getType();
            if (type.isAssignableFrom(Builder.class)) {
                builders.put(type, (Builder<?>) ReflectionUtils.newInstance(type));
            }
        }
    }

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        Builder<?> builder = builders.get(type);
        if (builder == null) {
            builder = getGenericBuilder(type);
        }
        return builder;
    }

    private Builder<?> getGenericBuilder(Class<?> type) {
        return new GenericNodeBuilder(type);
    }
}
