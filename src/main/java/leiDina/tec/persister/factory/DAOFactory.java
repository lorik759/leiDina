package main.java.leiDina.tec.persister.factory;

import main.java.leiDina.tec.persister.dao.DAO;

/**
 * @author vitor.alves
 */
public interface DAOFactory {

    <T extends DAO> T getDAOByClass(Class<T> daoClass);

}
