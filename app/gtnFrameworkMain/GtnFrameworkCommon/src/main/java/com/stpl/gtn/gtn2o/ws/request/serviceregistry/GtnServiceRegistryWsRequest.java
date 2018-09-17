package com.stpl.gtn.gtn2o.ws.request.serviceregistry;

import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnServiceRegistryWsRequest {

    public GtnServiceRegistryWsRequest() {
        super();
    }

    private GtnWsServiceRegistryBean gtnWsServiceRegistryBean;

    public GtnWsServiceRegistryBean getGtnWsServiceRegistryBean() {
        return gtnWsServiceRegistryBean;
    }

    public void setGtnWsServiceRegistryBean(GtnWsServiceRegistryBean gtnWsServiceRegistryBean) {
        this.gtnWsServiceRegistryBean = gtnWsServiceRegistryBean;
    }

    }
