package com.example.bean.post.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SystemIdBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // Intercepts the bean and change it
        if (bean instanceof Trackable) {
            Trackable trackableBean = (Trackable) bean;
            trackableBean.setSystemId("PAYPAL-TRACK-001");

            System.out.println("BPP Intercepted: Injected System ID into " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}