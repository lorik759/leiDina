package main.java.leiDina.tec.core.persist;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        TextFileEntityActor textFileEntityActor = getActor(entity);
        try {
            boolean existe = textFileEntityActor.entityWithIdExists(entity.getId());
            if (existe) {
                textFileEntityActor.updateEntity(entity);
            } else {
                textFileEntityActor.saveNew(entity);
            }
        } catch (Exception e) {
            throw new PersistenceException(BaseSystemMessages.UNABLE_TO_SAVE_ENTITY.create(entity.getClass()), e);
        }
    }

    @Override
    public void remove(Persistable entity) {
        TextFileEntityActor textFileEntityActor = getActor(entity);
        try {
            if (!textFileEntityActor.entityWithIdExists(entity)) {
                throw new PersistenceException(BaseSystemMessages.ENTITY_NOT_FOUND.create(entity.getClass(), entity.getId()));
            }
            textFileEntityActor.removeEntity(entity);
        } catch (IOException e) {
            throw new PersistenceException(BaseSystemMessages.UNABLE_TO_REMOVE_ENTITY.create(entity.getClass()), e);
        }
    }

    @Override
    public <T extends Persistable> T get(Serializable id, Class<T> type) {
        TextFileEntityActor actor = getActor(type);
        try {
            if (actor.entityWithIdExists(id)) {
                return actor.get(id, type);
            } else {
                throw new PersistenceException(BaseSystemMessages.ENTITY_NOT_FOUND.create(type, id));
            }
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public <T extends Persistable> List<T> getAll(Class<T> type) {
        try {
            return getActor(type).getAll(type);
        } catch (FileNotFoundException e) {
            throw new PersistenceException(e);
        }
    }

    private TextFileEntityActor getActor(Persistable entity) {
        return this.getActor(entity.getClass());
    }

    private TextFileEntityActor getActor(Class<? extends Persistable> aClass) {
        Entity entityAnnotation = aClass.getAnnotation(Entity.class);
        if (entityAnnotation == null) {
            throw new PersistenceException(BaseSystemMessages.OBJECT_NOT_ENTITY.create(aClass));
        }
        String name = entityAnnotation.name();
        if (StringUtils.isEmpty(name)) {
            name = aClass.getSimpleName();
        }
        return new TextFileEntityActor(name);
    }
}
