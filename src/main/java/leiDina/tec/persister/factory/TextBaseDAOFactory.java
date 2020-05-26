package main.java.leiDina.tec.persister.factory;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.beans.service.BeanWire;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.persister.dao.DAO;
import main.java.leiDina.tec.persister.exception.PersistenceException;
import main.java.leiDina.tec.persister.messages.PersisterSystemMessages;

/**
 * @author vitor.alves
 */
public class TextBaseDAOFactory implements DAOFactory {

    private final Map<Class<? extends DAO>, DAO> cache = new HashMap<>();

    private BeanWire beanWire;

    @Override
    public <T extends DAO> T getDAOByClass(Class<T> daoClass) {
        DAO dao = cache.get(daoClass);
        if (dao != null) {
            return (T) dao;
        }
        try {
            dao = ReflectionUtils.newInstance(daoClass);
            beanWire.wire(dao);
        } catch (Exception e) {
            throw new PersistenceException(PersisterSystemMessages.FAILED_TO_CREATE_DAD.create(daoClass), e);
        }
        return (T) dao;
    }

    public void setBeanWire(BeanWire beanWire) {
        this.beanWire = beanWire;
    }
}
