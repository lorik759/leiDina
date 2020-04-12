package main.java.leiDina.tec.persister.factory;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.persister.TextBasePersister;
import main.java.leiDina.tec.persister.dao.BaseDAO;
import main.java.leiDina.tec.persister.dao.DAO;
import main.java.leiDina.tec.persister.exception.PersistenceException;
import main.java.leiDina.tec.persister.messages.PersisterSystemMessages;

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
            if (daoClass.isAssignableFrom(BaseDAO.class)) {
                dao = ReflectionUtils.newInstance(daoClass, new TextBasePersister());
            } else {
                dao = ReflectionUtils.newInstance(daoClass);
            }
        } catch (Exception e) {
            throw new PersistenceException(PersisterSystemMessages.FAILED_TO_CREATE_DAD.create(daoClass), e);
        }
        return (T) dao;
    }
}
