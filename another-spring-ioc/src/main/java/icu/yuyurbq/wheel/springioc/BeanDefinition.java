package icu.yuyurbq.wheel.springioc;

import java.util.Map;

public class BeanDefinition {

    private String beanClassName;
    private Map<String,Object> propertyValues;
    private Map<String,String> beanReference;

    public String getBeanClass() {
        return beanClassName;
    }

    public void setBeanClass(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public Map<String, Object> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Map<String, Object> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Map<String, String> getBeanReference() {
        return beanReference;
    }

    public void setBeanReference(Map<String, String> beanReference) {
        this.beanReference = beanReference;
    }
}
