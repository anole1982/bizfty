package com.bizfty.service.container.editor.application.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ContainerServiceImpl implements ApplicationContextAware, InitializingBean, ContainerService {
    
    private ApplicationContext applicationContext;
    private String[] names;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        this.names = applicationContext.getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanNames() {
        return this.applicationContext.getBeanDefinitionNames();
    }

    @Override
    public Object getBean(Class clazz) {
        return this.applicationContext.getBean(clazz);
    }
    
    
}
