package icu.yuyurbq.wheel.springioc.reader;

import java.io.FileNotFoundException;

public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
