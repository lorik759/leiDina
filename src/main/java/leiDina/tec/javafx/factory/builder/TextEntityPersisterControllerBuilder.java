package main.java.leiDina.tec.javafx.factory.builder;

import java.lang.reflect.Field;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.persist.TextBasePersister;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.controller.EntityPersisterController;
import main.java.leiDina.tec.javafx.exception.BuilderException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;

/**
 * A builder of controller of type {@link EntityPersisterController}, that set persister with a {@link TextBasePersister}.
 *
 * @author vitor.alves
 */
public class TextEntityPersisterControllerBuilder extends AbstractControllerBuilder<EntityPersisterController> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doSpecificStuff(EntityPersisterController instance) {
        Class<?> superClass = instance.getClass().getSuperclass();
        while (superClass != null && !superClass.isAssignableFrom(EntityPersisterController.class)) {
            superClass = superClass.getSuperclass();
        }
        if (superClass != null) {
            Field[] declaredFields = superClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Resource.class)) {
                    Resource resource = declaredField.getDeclaredAnnotation(Resource.class);
                    if (resource.name().equals("persister")) {
                        try {
                            ReflectionUtils.set(declaredField, instance, new TextBasePersister());
                        } catch (IllegalAccessException e) {
                            throw new BuilderException(FXSystemMessages.FAILED_TO_BUILD_CONTROLLER.create(instance.getClass().getName()), e);
                        }
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<EntityPersisterController> getType() {
        return EntityPersisterController.class;
    }
}
