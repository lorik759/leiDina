package main.java.leiDina.tec.core.persist;

import java.io.Serializable;
import java.util.List;
import main.java.leiDina.tec.core.annotations.Entity;
import main.java.leiDina.tec.core.exception.PersistenceException;
import main.java.leiDina.tec.core.io.TextFileEntityActor;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class TextBasePersister implements Persister {

    @Override
    public void save(Persistable entity) {
        Entity entityAnnotation = entity.getClass().getAnnotation(Entity.class);
        if (entityAnnotation == null) {
            throw new PersistenceException(BaseSystemMessages.OBJECT_NOT_ENTITY.create(entity.getClass()));
        }
        String name = entityAnnotation.name();
        if (!StringUtils.isNotEmpty(name)) {
            name = entity.getClass().getSimpleName();
        }
        TextFileEntityActor textFileEntityActor = new TextFileEntityActor(name);
        try {
            boolean existe = textFileEntityActor.entityWithIdExists(entity.getId());
            if (existe) {
                textFileEntityActor.upDateEntity(entity);
            } else {
            }
        } catch (Exception e) {
            throw new PersistenceException(BaseSystemMessages.UNABLE_TO_SAVE_ENTITY.create(entity.getClass()), e);
        }
    }

    @Override
    public void remove(Persistable entity) {

    }

    @Override
    public <T extends Persistable> T get(Serializable id, Class<T> type) {
        return null;
    }

    @Override
    public <T extends Persistable> List<T> getAll(Class<T> type) {
        return null;
    }
}
