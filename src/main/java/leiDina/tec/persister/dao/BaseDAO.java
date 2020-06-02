package main.java.leiDina.tec.persister.dao;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import main.java.leiDina.tec.persister.exception.EntityCreationException;
import main.java.leiDina.tec.persister.exception.EntityNotFoundException;
import main.java.leiDina.tec.persister.Persistable;
import main.java.leiDina.tec.persister.Persister;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public abstract class BaseDAO<T extends Persistable> implements DAO<T> {

    @Resource(name = "persister")
    private Persister persister;

    protected abstract Class<T> getPersistableType();

    @Override
    public T createEntity() {
        Class<T> entityClass = getPersistableType();
        try {
            return ReflectionUtils.newInstance(entityClass);
        } catch (ReflectiveOperationException e) {
            throw new EntityCreationException(e);
        }
    }

    @Override
    public T findById(Serializable id) throws EntityNotFoundException {
        return persister.findBy(id, getPersistableType());
    }

    @Override
    public List<T> findAll() {
        return persister.getAll(getPersistableType());
    }
}
