package icu.yuyurbq.wheel.springioc.factory;

import icu.yuyurbq.wheel.springioc.BeanDefinition;
import icu.yuyurbq.wheel.springioc.reader.BeanDefinitionReader;
import icu.yuyurbq.wheel.springioc.reader.XmlBeanDefinitionReader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanFactory implements BeanFactory{

    private BeanDefinitionReader beanDefinitionReader;
    private Map<String, BeanDefinition> beanDefinitionMap;
    private Map<String,Object> registe = new HashMap<>();

    public DefaultBeanFactory(String location) throws Exception {
        if (location.contains("xml")) {
            beanDefinitionReader = new XmlBeanDefinitionReader();
        }
        beanDefinitionReader.loadBeanDefinitions(location);
        beanDefinitionMap = beanDefinitionReader.getBeanDefinitionMap();
    }

    @Override
    public Object getBean(String beanId) throws Exception {
        if (registe.get(beanId) != null) {
            return registe.get(beanId);
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        Object bean = buildBean(beanDefinition);
        registe.put(beanId,bean);
        return bean;
    }

    private Object buildBean(BeanDefinition beanDefinition) throws Exception {
        Object instance = Class.forName(beanDefinition.getBeanClass()).newInstance();
        Map<String,Object> propertyValues = beanDefinition.getPropertyValues();
        Map<String,String> beanReference = beanDefinition.getBeanReference();
        for (Map.Entry<String,Object> property : propertyValues.entrySet()) {
            try {
                Method declaredMethod = instance.getClass().getDeclaredMethod("set" + property.getKey().substring(0, 1).toUpperCase() + property.getKey().substring(1), String.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(instance, property.getValue());
            } catch (NoSuchMethodException e) {
                Field declaredField = instance.getClass().getDeclaredField(property.getKey());
                declaredField.setAccessible(true);
                declaredField.set(instance, property.getValue());
            }
        }
        for (Map.Entry<String,String> beanRef : beanReference.entrySet()) {
            Object bean = getBean(beanRef.getValue());
            Method declaredMethod = null;
            try {
                declaredMethod = instance.getClass()
                        .getDeclaredMethod("set" + beanRef.getKey().substring(0, 1).toUpperCase() + beanRef.getKey().substring(1), bean.getClass());
            } catch (NoSuchMethodException e) {
                Field declaredField = instance.getClass().getDeclaredField(beanRef.getKey());
                declaredField.setAccessible(true);
                declaredField.set(instance, bean);
            }
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(instance, bean);
        }
        return instance;
    }
}
