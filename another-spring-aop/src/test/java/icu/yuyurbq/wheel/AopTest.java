package icu.yuyurbq.wheel;

import icu.yuyurbq.wheel.springaop.ProxyFactory;

public class AopTest {
    public static void main(String[] args) {
        ToolBean bean = new ToolBeanImpl();
        ToolBean instance = (ToolBean)ProxyFactory.getProxyInstance(bean, new TestTodo());
        instance.printStr();
    }
}
