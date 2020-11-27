package icu.yuyurbq.wheel.springioc.reader;

import icu.yuyurbq.wheel.springioc.BeanDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XmlBeanDefinitionReader implements BeanDefinitionReader{

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputStream resourceAsStream = new FileInputStream(location);
        Document parse = documentBuilder.parse(resourceAsStream);

        NodeList beanNodeList = parse.getElementsByTagName("bean");
        for (int i = 0; i < beanNodeList.getLength(); i++) {
            BeanDefinition beanDefinition = (BeanDefinition) Class.forName("icu.yuyurbq.wheel.springioc.BeanDefinition").newInstance();
            Node beanNode = beanNodeList.item(i);
            String beanId = beanNode.getAttributes().getNamedItem("id").getNodeValue();
            String beanClass = beanNode.getAttributes().getNamedItem("class").getNodeValue();
            beanDefinition.setBeanClass(beanClass);

            NodeList propertyNodeList = null;
            if (beanNode instanceof Element) {
                Element ele = (Element) beanNode;
                propertyNodeList = ele.getElementsByTagName("property");
            }
            HashMap<String, Object> propertyMap = new HashMap<>();
            HashMap<String, String> referenceMap = new HashMap<>();

            for (int j = 0; j < propertyNodeList.getLength(); j++) {
                Node propertyNode = propertyNodeList.item(j);
                String propertyName = propertyNode.getAttributes().getNamedItem("name").getNodeValue();
                if (propertyNode.getAttributes().getNamedItem("value") != null) {
                    String propertyValue = propertyNode.getAttributes().getNamedItem("value").getNodeValue();
                    propertyMap.put(propertyName,propertyValue);
                }
                if (propertyNode.getAttributes().getNamedItem("ref") != null) {
                    String referenceValue = propertyNode.getAttributes().getNamedItem("ref").getNodeValue();
                    referenceMap.put(propertyName,referenceValue);
                }
            }
            beanDefinition.setPropertyValues(propertyMap);
            beanDefinition.setBeanReference(referenceMap);

            beanDefinitionMap.put(beanId,beanDefinition);
        }
    }

    @Override
    public Map<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }
}
