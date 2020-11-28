package icu.yuyurbq.wheel.springaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Advice implements InvocationHandler {
    private final Object target;
    private final MethodToDo toDo;

    public Advice(Object target, MethodToDo toDo) {
        this.target = target;
        this.toDo = toDo;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        toDo.beforeAdvice();
        toDo.aroundAdvice();
        Object result = method.invoke(target,args);
        toDo.aroundAdvice();
        toDo.afterReturnAdvice();
        return result;
    }
}
