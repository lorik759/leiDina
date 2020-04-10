package main.java.leiDina.tec.persister;

import java.io.Serializable;
import java.util.List;

/**
 * @author vitor.alves
 */
public interface Persister {

    void save(Persistable entity);

    void remove(Persistable entity);

    <T extends Persistable> T get(Serializable id, Class<T> type);

    <T extends Persistable> List<T> getAll(Class<T> type);
}
