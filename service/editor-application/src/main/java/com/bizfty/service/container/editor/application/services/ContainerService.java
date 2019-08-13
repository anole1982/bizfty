package com.bizfty.service.container.editor.application.services;

public interface ContainerService {

    public String[] getBeanNames();
    public Object getBean(Class clazz);
}
