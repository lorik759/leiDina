package main.java.leiDina.tec.core.beans.factory;


import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.beans.model.BeanDefinition;

/**
 * @author vitor.alves
 */
public interface BeanDefinitionFactory<D extends BeanDefinition, I> {

    D createFor(I info, String location) throws BeanException;

    D createFor(I info) throws BeanException;

}
