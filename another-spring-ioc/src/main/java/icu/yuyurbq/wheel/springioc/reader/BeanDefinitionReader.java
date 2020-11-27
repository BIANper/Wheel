package icu.yuyurbq.wheel.springioc.reader;

import icu.yuyurbq.wheel.springioc.BeanDefinition;

import java.util.Map;

public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;

    Map<String, BeanDefinition> getBeanDefinitionMap();
}
