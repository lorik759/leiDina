package main.java.leiDina.tec.core.service;

/**
 * @author vitor.alves
 */
public interface ValueResolver<T> {

    T resolve(String value) throws Exception;

}
