package main.java.leiDina.tec.persister.dao;

import java.io.Serializable;
import java.util.List;
import main.java.leiDina.tec.persister.exception.EntityNotFoundException;

/**
 * @author vitor.alves
 */
public interface DAO<T> {

    T createEntity();

    T findById(Serializable pk) throws EntityNotFoundException;

    List<T> findAll();
}
