package com.bizfty.service.container.editor.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class ServiceMgmtServiceImpl implements ServiceMgmtService{
    public String  getServiceName(){
        return "ServiceManagementServiceImpl";
    }

}
