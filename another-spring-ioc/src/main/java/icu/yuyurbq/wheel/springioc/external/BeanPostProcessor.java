package icu.yuyurbq.wheel.springioc.external;

public interface BeanPostProcessor {

    Object beforeInitialization(Object bean, String beanName) throws Exception;

    Object afterInitialization(Object bean, String beanName) throws Exception;
}
