package icu.yuyurbq.wheel.springioc;

import java.util.Map;

public class BeanDefinition {

    private String beanId;
    private Class<?> beanClass;
    private Map<String,String> propertyValues;
    private Map<String,String> beanReference;

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Map<String, String> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Map<String, String> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Map<String, String> getBeanReference() {
        return beanReference;
    }

    public void setBeanReference(Map<String, String> beanReference) {
        this.beanReference = beanReference;
    }
}
