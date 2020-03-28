package main.java.leiDina.tec.core.dao;

import java.io.Serializable;
import java.util.List;
import main.java.leiDina.tec.core.exception.EntityNotFoundException;

/**
 * @author vitor.alves
 */
public interface DAO<T> {

    T createEntity();

    T findById(Serializable pk) throws EntityNotFoundException;

    List<T> findAll();
}
