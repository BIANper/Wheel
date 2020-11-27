package icu.yuyurbq.wheel;

import icu.yuyurbq.wheel.springioc.factory.BeanFactory;
import icu.yuyurbq.wheel.springioc.factory.DefaultBeanFactory;

import java.util.Objects;

public class IocTest {

    public static void main(String[] args) {
        try {
            String location = Objects.requireNonNull(IocTest.class.getClassLoader().getResource("bean.xml")).getFile();
            BeanFactory beanFactory = new DefaultBeanFactory(location);
            MainBean bean1 = (MainBean)beanFactory.getBean("mainBean");
            MainBean bean2 = (MainBean)beanFactory.getBean("mainBean");
            System.out.println(bean1.getData());
            System.out.println(bean2.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
