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
 * This is the default implementation af the {@link BeanDefinitionHolder} interface. This class contains and organizes the bean definitions of the
 * context.
 *
 * @author vitor.alves
 */
public class DefaultBeanDefinitionHolder implements BeanDefinitionHolder {

    private final Map<String, BeanDefinition> beanDefinitionByName = new HashMap<>();

    private final Map<Class<?>, String> beanDefinitionNameByType = new HashMap<>();

    private final Set<String> beanDefinitionNames = new HashSet<>();

    private final Set<BeanDefinition> allBeanDefinitions = new HashSet<>();

    private boolean allowOverwrite = false;

    /**
     * Adds the {@link BeanDefinition} to this context, validating: 1) If it already doesn't exist. 2) If the bean name is already in use. If this
     * {@link BeanDefinitionHolder} is set to overwrite, it will remove the old {@link BeanDefinition}, and add the new {@link BeanDefinition}.
     * Otherwise, it will throw a {@link BeanDefinitionException}.
     *
     * @param beanDefinition the {@link BeanDefinition} to be added.
     */
    @Override
    public void addBeanDefinition(BeanDefinition beanDefinition) throws BeanException {
        this.validateBeanDefinition(beanDefinition);
        if (this.allowOverwrite) {
            this.doRemoveBeanDefinition(beanDefinition);
        }
        this.doAddBeanDefinition(beanDefinition);
    }

    /**
     * Validates the {@link BeanDefinition} and checking if: 1) If it already doesn't exist. 2) If the bean name is already in use. If any of this
     * criterias are meet, and this {@link BeanDefinitionHolder is not set to overwrite, than an {@link BeanDefinitionException} is thrown.
     *
     * @param beanDefinition the {@link BeanDefinition} to Validate.
     */
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

    /**
     * Checks to see if the specified {@link BeanDefinition} exists. If it dose, than it is removed from this Holder. Otherwise, an {@link BeanDefinitionException}} is thrown.
     *
     * @param beanDefinition the {@link BeanDefinition} to be removed.
     * @throws BeanException a {@link BeanDefinitionException} if the specified {@link BeanDefinition} dose not existe.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsBeanDefinition(BeanDefinition beanDefinition) {
        return this.allBeanDefinitions.contains(beanDefinition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBeanDefinitionNameInUse(String beanName) {
        return this.beanDefinitionNames.contains(beanName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAllowOverwriteBeanDefinition() {
        return this.allowOverwrite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void allowOverwriteBeanDefinition(boolean allowOverwrite) {
        this.allowOverwrite = allowOverwrite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BeanDefinition getBeanDefinitionForName(String name) throws BeanException {
        BeanDefinition beanDefinition = this.beanDefinitionByName.get(name);
        if (beanDefinition == null) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_DEFINITION_DOESNT_EXIST.create(name));
        }
        return beanDefinition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BeanDefinition getBeanDefinitionForType(Class<?> beanClass) throws BeanException {
        String name = this.beanDefinitionNameByType.get(beanClass);
        if (StringUtils.isEmpty(name)) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_DEFINITION_OF_TYPE_DOESNT_EXIST.create(beanClass));
        }
        return this.getBeanDefinitionForName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBeanName(Class<?> type) {
        return this.beanDefinitionNameByType.get(type);
    }

    protected Set<String> getAllBeanNames() {
        return this.beanDefinitionNames;
    }
}
