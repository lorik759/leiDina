package main.java.leiDina.tec.vinjection.model;

import main.java.leiDina.tec.core.context.ApplicationContext;
import main.java.leiDina.tec.core.context.ApplicationThreadContext;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.vinjection.InjectableApplicationContext;
import main.java.leiDina.tec.vinjection.exception.PropertyException;
import main.java.leiDina.tec.vinjection.messages.VInjectionSystemMessages;
import main.java.leiDina.tec.vinjection.xml.model.property.XmlProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.BeanProperty;

/**
 * @author vitor.alves
 */
public class BeanPropertyValue implements PropertyValue {

    private BeanProperty beanProperty;

    public BeanPropertyValue(XmlProperty beanProperty) {
        this.beanProperty = (BeanProperty) beanProperty;
    }

    @Override
    public Object getValue() {
        String ref = beanProperty.getRef();
        if (StringUtils.isEmpty(ref)) {
            throw new PropertyException(VInjectionSystemMessages.REFERENCE_TO_BEAN_IS_NULL.create());
        }
        InjectableApplicationContext applicationContext = (InjectableApplicationContext) ApplicationThreadContext.getApplicationContext();
        return applicationContext.getBeanById(ref);
    }
}
