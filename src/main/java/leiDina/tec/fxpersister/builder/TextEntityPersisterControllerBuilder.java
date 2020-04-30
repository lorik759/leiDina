package main.java.leiDina.tec.fxpersister.builder;

import java.lang.reflect.Field;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.context.ApplicationThreadContext;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.javafx.factory.builder.AbstractControllerBuilder;
import main.java.leiDina.tec.persister.TextBasePersister;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.fxpersister.controller.EntityPersisterController;
import main.java.leiDina.tec.javafx.exception.BuilderException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import main.java.leiDina.tec.persister.factory.DAOFactory;
import main.java.leiDina.tec.persister.service.PersisterSystemKey;

/**
 * A builder of controller of type {@link EntityPersisterController}, that set persister with a {@link TextBasePersister}.
 *
 * @author vitor.alves
 */
@Deprecated
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
                    Object arg = null;
                    if (resource.name().equals("persister")) {
                        arg = new TextBasePersister();
                    } else if (resource.name().equals("daoFactory")) {
                        SystemService service = ApplicationThreadContext.getService(new PersisterSystemKey());
                        arg = service.getPropertyByName(DAOFactory.class.getSimpleName()).getProperty();
                    }
                    try {
                        ReflectionUtils.set(declaredField, instance, arg);
                    } catch (IllegalAccessException e) {
                        throw new BuilderException(FXSystemMessages.FAILED_TO_BUILD_CONTROLLER.create(instance.getClass().getName()), e);
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
