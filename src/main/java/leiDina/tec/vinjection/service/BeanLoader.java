package main.java.leiDina.tec.vinjection.service;

import java.util.List;
import main.java.leiDina.tec.vinjection.model.BeanDefinition;

/**
 * @author vitor.alves
 */
public interface BeanLoader {

    List<BeanDefinition> loadBeanDefinitionFrom(String location);

}
