package main.java.leiDina.tec.fxpersister.builder;

import java.lang.reflect.Field;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.context.ApplicationThreadContext;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.controller.BaseModelController;
import main.java.leiDina.tec.javafx.exception.BuilderException;
import main.java.leiDina.tec.javafx.factory.builder.AbstractControllerBuilder;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import main.java.leiDina.tec.persister.factory.DAOFactory;
import main.java.leiDina.tec.persister.service.PersisterSystemKey;

/**
 * @author vitor.alves
 */
@Deprecated
public class DAOFactoryWireControllerBuilder extends AbstractControllerBuilder<BaseModelController> {

    @Override
    protected void doSpecificStuff(BaseModelController instance) {
        Class<? extends BaseModelController> aClass = instance.getClass();
        while (aClass != null) {
            for (Field declaredField : aClass.getDeclaredFields()) {
                Resource annotation = declaredField.getAnnotation(Resource.class);
                if (annotation != null && annotation.name().equals("daoFactory")) {
                    SystemService service = ApplicationThreadContext.getService(new PersisterSystemKey());
                    DAOFactory daoFactory = (DAOFactory) service.getPropertyByName(DAOFactory.class.getSimpleName()).getProperty();
                    try {
                        ReflectionUtils.set(declaredField, instance, daoFactory);
                    } catch (IllegalAccessException e) {
                        throw new BuilderException(FXSystemMessages.FAILED_TO_BUILD_CONTROLLER.create(instance.getClass()));
                    }
                }
            }
            aClass = (Class<? extends BaseModelController>) aClass.getSuperclass();
        }
    }

    @Override
    public Class<BaseModelController> getType() {
        return BaseModelController.class;
    }
}
