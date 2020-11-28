package icu.yuyurbq.wheel;

import icu.yuyurbq.wheel.springaop.MethodToDo;

public class TestTodo implements MethodToDo {
    @Override
    public void beforeAdvice() {
        System.out.println("beforeAdvice");
    }

    @Override
    public void afterReturnAdvice() {
        System.out.println("afterReturnAdvice");
    }

    @Override
    public void aroundAdvice() {

    }
}
