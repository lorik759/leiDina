package main.java.leiDina.tec.core.env;

import java.util.Set;
import main.java.leiDina.tec.core.beans.model.BeanDefinition;
import main.java.leiDina.tec.core.beans.model.BeanDefinitionHolder;
import main.java.leiDina.tec.core.beans.service.BeanResourceLoader;


/**
 * @author vitor.alves
 */
public class DefaultApplicationProperty implements ApplicationProperty {

    private final String resource;

    public DefaultApplicationProperty(String resource) {
        this.resource = resource;
    }

    @Override
    public void registerBeansTo(final BeanDefinitionHolder beanDefinitionHolder) {
        this.getBeanDefinitionFromResource().forEach(beanDefinitionHolder::addBeanDefinition);
    }

    private Set<BeanDefinition> getBeanDefinitionFromResource() {
        return new BeanResourceLoader(resource).load();
    }
}
