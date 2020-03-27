package main.java.leiDina.tec.core.service;

/**
 * A base interface of objects that wires objects to something.
 *
 * @author vitor.alves
 */
public interface Wire<T> {

    /**
     * Wires an object to something.
     *
     * @param object object to be wired.
     * @throws Exception An exception.
     */
    void wire(T object) throws Exception;
}
