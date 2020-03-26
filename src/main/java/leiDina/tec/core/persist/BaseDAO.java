package main.java.leiDina.tec.core.persist;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import main.java.leiDina.tec.core.exception.EntityCreationException;
import main.java.leiDina.tec.core.exception.EntityNotFoundException;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public abstract class BaseDAO<T extends Persistable> implements DAO<T> {

    private Persister persister;

    protected abstract Class<T> getPersistableType();

    @Override
    public T createEntity() {
        Class<T> entityClass = getPersistableType();
        try {
            return ReflectionUtils.newInstance(entityClass);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new EntityCreationException(e);
        }
    }

    @Override
    public T findById(Serializable pk) throws EntityNotFoundException {
        return persister.get(pk, getPersistableType());
    }

    @Override
    public List<T> findAll() {
        return persister.getAll(getPersistableType());
    }
}
