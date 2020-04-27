package main.java.leiDina.tec.persister.factory;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.persister.dao.BaseDAO;
import main.java.leiDina.tec.persister.dao.DAO;
import main.java.leiDina.tec.persister.exception.PersistenceException;
import main.java.leiDina.tec.persister.messages.PersisterSystemMessages;
import main.java.leiDina.tec.persister.service.TextBaseDAOPersisterWire;
import main.java.leiDina.tec.vinjection.BeanWireThreadContext;
import main.java.leiDina.tec.vinjection.service.BeanWire;

/**
 * @author vitor.alves
 */
public class TextBaseDAOFactory implements DAOFactory {

    private final Map<Class<? extends DAO>, DAO> cache = new HashMap<>();

    @Override
    public <T extends DAO> T getDAOByClass(Class<T> daoClass) {
        DAO dao = cache.get(daoClass);
        if (dao != null) {
            return (T) dao;
        }
        try {
            dao = ReflectionUtils.newInstance(daoClass);
            BeanWire beanWire = BeanWireThreadContext.getBeanWire();
            beanWire.wire(dao);
        } catch (Exception e) {
            throw new PersistenceException(PersisterSystemMessages.FAILED_TO_CREATE_DAD.create(daoClass), e);
        }
        return (T) dao;
    }
}
