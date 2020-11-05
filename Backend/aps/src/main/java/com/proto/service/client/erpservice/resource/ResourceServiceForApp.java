package com.proto.service.client.erpservice.resource;

import java.util.List;

public class ResourceServiceForApp {
    private Resource resource;

    public ResourceServiceForApp(){
        ResourceService rs=new ResourceService();
        resource=rs.getResourcePort();
    }

    public List<ResourceItem> getLineResource(){
        return resource.getLineResource();
    }

    public List<ResourceItem> getHumanResource(){
        return resource.getHumanResource();
    }
}
