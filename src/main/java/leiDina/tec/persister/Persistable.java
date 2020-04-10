package main.java.leiDina.tec.persister;

import java.io.Serializable;

/**
 * @author vitor.alves
 */
public interface Persistable extends Serializable {

    Long getId();

    void setId(Long id);

}
