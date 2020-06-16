package main.java.leiDina.tec.core.env;

import java.util.Set;
import main.java.leiDina.tec.core.dependency.model.ObjectDefinition;
import main.java.leiDina.tec.core.dependency.model.ObjectDefinitionHolder;
import main.java.leiDina.tec.core.dependency.service.MappedObjectResourceLoader;


/**
 * This is the default implementation of a {@link ApplicationProperty}, that represents a xml file that contais objects mapped within. This class loads
 * the mapped objects and converts them to a {@link ObjectDefinition} which will than be added to an {@link ObjectDefinitionHolder}. An example of a xml
 * file that contais mapped objects is:
 *
 * <code>
 *     <objects xmlns="http://www.vaplication.com/objects">
 *         <object id="simpleObject" class="main.java.vtec.SimpleObject"/>
 *
 *         <object id="complexObject" class="main.java.vtec.ComplexObject">
 *              <property name="mapProperty">
 *                  <map key-type="class">
 *                      <value key="main.java.vtec.SimpleObject">
 *                          <object ref="simpleObject"/>
 *                      </value>
 *                  </map>
 *              </property>
 *          </object>
 *
 *          <object id="diferenteObject" class="main.java.vtec.DiferenteObject">
 *              <property name="complexObject">
 *                  <object ref="complexObject"/>
 *              </property>
 *          </object>
 *     </objects>
 * </code>
 *
 * @author vitor.alves
 */
public class DefaultApplicationProperty implements ApplicationProperty {

    private final String resource;

    public DefaultApplicationProperty(String resource) {
        this.resource = resource;
    }

    /**
     * Registers the objects from the specified xml file to the {@link ObjectDefinitionHolder}.
     *
     * @param objectDefinitionHolder the {@link ObjectDefinitionHolder} in which the objects of the xml file will be registered to.
     */
    @Override
    public void registerObjectsTo(final ObjectDefinitionHolder objectDefinitionHolder) {
        this.getObjectDefinitionFromResource().forEach(objectDefinitionHolder::addObjectDefinition);
    }

    /**
     * @return a set of {@link ObjectDefinition} from the xml file.
     */
    private Set<ObjectDefinition> getObjectDefinitionFromResource() {
        return new MappedObjectResourceLoader(resource).load();
    }
}
