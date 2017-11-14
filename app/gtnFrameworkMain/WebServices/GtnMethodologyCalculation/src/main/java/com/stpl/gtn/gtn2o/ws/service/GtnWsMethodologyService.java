package com.stpl.gtn.gtn2o.ws.service;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.methodology.GtnWsMethodology;

public class GtnWsMethodologyService {

	public void calculate(GtnWsMethodologyBean methodologyBean) {

		GtnWsMethodology methodology = methodologyBean.getMethodologyType().getMethdology();

		methodology.initiateMethodologyCalculation(methodologyBean);
		
	}

	
	
}
