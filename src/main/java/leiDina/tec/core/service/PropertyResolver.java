package main.java.leiDina.tec.core.service;

import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * @author vitor.alves
 */
public interface PropertyResolver<T extends SystemProperty> {

    T resolve(String key, String values);
}
