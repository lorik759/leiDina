package main.java.leiDina.tec.persister.service;

import java.lang.reflect.Field;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.service.Wire;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.persister.TextBasePersister;
import main.java.leiDina.tec.persister.dao.BaseDAO;

/**
 * @author vitor.alves
 */
public class TextBaseDAOPersisterWire implements Wire<BaseDAO> {

    @Override
    public void wire(BaseDAO dao) throws Exception {
        Field[] declaredFields = dao.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Resource resource = declaredField.getAnnotation(Resource.class);
            if (resource != null && resource.name().equals("persister")) {
                ReflectionUtils.set(declaredField, dao, new TextBasePersister());
            }
        }
    }
}
