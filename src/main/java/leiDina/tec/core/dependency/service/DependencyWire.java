package main.java.leiDina.tec.core.dependency.service;

/**
 * @author vitor.alves
 */
public interface DependencyWire {

    <T> void wire(T object);

}
