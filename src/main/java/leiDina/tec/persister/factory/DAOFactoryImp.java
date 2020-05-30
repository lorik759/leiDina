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
public class DAOFactoryImp implements DAOFactory {

    private final Map<Class<? extends DAO>, DAO> cache = new HashMap<>();

    private BeanWire beanWire;

    @Override
    public <T extends DAO> T getDAOByClass(Class<T> daoClass) {
        DAO dao = cache.get(daoClass);
        if (dao == null) {
            dao = createDAO(daoClass);
            this.addToCach(daoClass, dao);
        }
        return (T) dao;
    }

    private <T extends DAO> void addToCach(Class<T> daoClass, DAO dao) {
        this.cache.put(daoClass, dao);
    }

    private <T extends DAO> DAO createDAO(Class<T> daoClass) {
        DAO dao = createInstance(daoClass);
        beanWire.wire(dao);
        return dao;
    }

    private <T extends DAO> T createInstance(Class<T> daoClass) throws PersistenceException{
        try {
            return ReflectionUtils.newInstance(daoClass);
        } catch (Exception e) {
            throw new PersistenceException(PersisterSystemMessages.FAILED_TO_CREATE_DAD.create(daoClass), e);
        }
    }

    public void setBeanWire(BeanWire beanWire) {
        this.beanWire = beanWire;
    }
}
