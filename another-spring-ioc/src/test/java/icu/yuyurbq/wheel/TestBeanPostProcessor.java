package icu.yuyurbq.wheel;

import icu.yuyurbq.wheel.springioc.external.BeanPostProcessor;

public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object beforeInitialization(Object bean, String beanName){
        System.out.println("beforeInitialization " + beanName);
        return bean;
    }

    @Override
    public Object afterInitialization(Object bean, String beanName) {
        System.out.println("afterInitialization " + beanName);
        return bean;
    }
}
