package com.stpl.gtn.gtn2o.ws.periodconf.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.gtn.gtn2o.ws.periodconf.service.GtnWsPeriodConfigurationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = "/gtnPeriodConfigurationController")
public class GtnWsPeriodConfigurationController extends GtnServiceRegistryImplClass {

    private GtnWsPeriodConfigurationController() {
        super();
        initializeLogger();
    }

    @Autowired
    private GtnWsPeriodConfigurationService periodConfigurationService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public boolean test() {
        return true;
    }

    @PostConstruct
    private void initializeLogger() {
        super.logInformation(GtnWsPeriodConfigurationController.class);
    }

    private GtnFrameworkSingletonObjectBean singletonObjectBean = GtnFrameworkSingletonObjectBean.getInstance();

    @RequestMapping(value = "/loadDate", method = RequestMethod.POST)
    public GtnUIFrameworkWebserviceResponse loadDate(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        logger.info("Entering into PeriodConfiguration Controller to load From To Period");
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
        comboBoxResponse.setComboBoxList(singletonObjectBean.getPeriodConfigResultList());
        logger.info("Returning Resultlist from SingletonList from "
                + singletonObjectBean.getPeriodConfigResultList().get(0)[1] + "to"
                + singletonObjectBean.getPeriodConfigResultList()
                        .get(singletonObjectBean.getPeriodConfigResultList().size() - 1)[1]);
        gtnUIFrameworkWebserviceResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);

        return gtnUIFrameworkWebserviceResponse;
    }

    @RequestMapping(value = "/loadRefreshDate", method = RequestMethod.POST)
    public void loadRefreshDate(@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        logger.info("Reloading From To Results after Forecast Configuration Change");
        List<Object[]> resultList = periodConfigurationService
                .loadDate(gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest());
        singletonObjectBean.setPeriodConfigResultList(resultList);
    }
}
