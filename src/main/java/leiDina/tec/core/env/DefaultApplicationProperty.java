package main.java.leiDina.tec.core.env;

import java.util.Set;
import main.java.leiDina.tec.core.beans.model.BeanDefinition;
import main.java.leiDina.tec.core.beans.model.BeanDefinitionHolder;
import main.java.leiDina.tec.core.beans.service.BeanResourceLoader;


/**
 * This is the default implementation of a {@link ApplicationProperty}, that represents a xml file that contais beans mapped within. This class loads
 * the mapped beans and converts them to a {@link BeanDefinition} which will than be added to an {@link BeanDefinitionHolder}. An example of a xml
 * file that contais mapped beans:
 *
 * <code>
 *     <beans xmlns="http://www.vaplication.com/beans">
 *         <bean id="simpleBean" class="main.java.vtec.SimpleBean"/>
 *
 *         <bean id="complexBean" class="main.java.vtec.ComplexBean">
 *              <property name="mapProperty">
 *                  <map key-type="class">
 *                      <value key="main.java.vtec.SimpleBean">
 *                          <bean ref="simpleBean"/>
 *                      </value>
 *                  </map>
 *              </property>
 *          </bean>
 *
 *          <bean id="diferenteBean" class="main.java.vtec.DiferenteBean">
 *              <property name="complexBean">
 *                  <bean ref="complexBean"/>
 *              </property>
 *          </bean>
 *     </beans>
 * </code>
 *
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
