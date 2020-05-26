package main.java.leiDina.tec.core.beans.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import main.java.leiDina.tec.core.beans.exception.BeanDefinitionException;
import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class DefaultBeanDefinitionHolder implements BeanDefinitionHolder {

    private final Map<String, BeanDefinition> beanDefinitionByName = new HashMap<>();

    private final Map<Class<?>, String> beanDefinitionNameByType = new HashMap<>();

    private final Set<String> beanDefinitionNames = new HashSet<>();

    private final Set<BeanDefinition> allBeanDefinitions = new HashSet<>();

    private boolean allowOverwrite = false;

    @Override
    public void addBeanDefinition(BeanDefinition beanDefinition) throws BeanException {
        this.validateBeanDefinition(beanDefinition);
        if (this.allowOverwrite) {
            this.doRemoveBeanDefinition(beanDefinition);
        }
        this.doAddBeanDefinition(beanDefinition);
    }

    private void validateBeanDefinition(BeanDefinition beanDefinition) {
        String name = beanDefinition.getName();
        if (this.existsBeanDefinition(beanDefinition) && this.isNotAllowOverwriteBeanDefinition()) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_DEFINITION_ALREADY_EXISTS.create(name, beanDefinition.getBeanClass()));
        }
        if (this.isBeanDefinitionNameInUse(name) && this.isNotAllowOverwriteBeanDefinition()) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_NAME_ALREADY_IN_USE.create(name, beanDefinition.getLocationName()));
        }
    }

    private boolean isNotAllowOverwriteBeanDefinition() {
        return !this.isAllowOverwriteBeanDefinition();
    }

    private void doAddBeanDefinition(BeanDefinition beanDefinition) {
        this.allBeanDefinitions.add(beanDefinition);
        String name = beanDefinition.getName();
        this.beanDefinitionNames.add(name);
        this.beanDefinitionNameByType.put(beanDefinition.getBeanClass(), name);
        this.beanDefinitionByName.put(name, beanDefinition);
    }

    @Override
    public void removeBeanDefinition(BeanDefinition beanDefinition) throws BeanException {
        if (!this.existsBeanDefinition(beanDefinition)) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_DEFINITION_DOESNT_EXIST.create(beanDefinition.getName()));
        }
        this.doRemoveBeanDefinition(beanDefinition);
    }

    private void doRemoveBeanDefinition(BeanDefinition beanDefinition) {
        String name = beanDefinition.getName();
        BeanDefinition beanDefinitionToRemove = this.beanDefinitionByName.get(name);
        this.allBeanDefinitions.remove(beanDefinitionToRemove);
        this.beanDefinitionNames.remove(name);
        this.beanDefinitionByName.remove(name);
        this.beanDefinitionNameByType.remove(beanDefinitionToRemove.getBeanClass());
    }

    @Override
    public boolean existsBeanDefinition(BeanDefinition beanDefinition) {
        return this.allBeanDefinitions.contains(beanDefinition);
    }

    @Override
    public boolean isBeanDefinitionNameInUse(String beanName) {
        return this.beanDefinitionNames.contains(beanName);
    }

    @Override
    public boolean isAllowOverwriteBeanDefinition() {
        return this.allowOverwrite;
    }

    @Override
    public void allowOverwriteBeanDefinition(boolean allowOverwrite) {
        this.allowOverwrite = allowOverwrite;
    }

    @Override
    public BeanDefinition getBeanDefinitionForName(String name) throws BeanException {
        BeanDefinition beanDefinition = this.beanDefinitionByName.get(name);
        if (beanDefinition == null) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_DEFINITION_DOESNT_EXIST.create(name));
        }
        return beanDefinition;
    }

    @Override
    public BeanDefinition getBeanDefinitionForType(Class<?> beanClass) throws BeanException {
        String name = this.beanDefinitionNameByType.get(beanClass);
        if (StringUtils.isEmpty(name)) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_DEFINITION_OF_TYPE_DOESNT_EXIST.create(beanClass));
        }
        return this.getBeanDefinitionForName(name);
    }

    @Override
    public String getBeanName(Class<?> type) {
        return this.beanDefinitionNameByType.get(type);
    }

    protected Set<String> getAllBeanNames() {
        return this.beanDefinitionNames;
    }
}
