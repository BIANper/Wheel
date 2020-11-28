package icu.yuyurbq.wheel.springaop;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static Object getProxyInstance(Object bean, MethodToDo toDo){
        ClassLoader classLoader = bean.getClass().getClassLoader();
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        Advice advice = new Advice(bean, toDo);
        return Proxy.newProxyInstance(classLoader, interfaces, advice);
    }
}
