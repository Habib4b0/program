package com.stpl.gtn.gtn2o.ws.methodology;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;

public interface GtnWsMethodology {	
	
	public void initiateMethodologyCalculation(GtnWsMethodologyBean methodologyBean);
	
	public void registerMethodologyBean(GtnWsMethodologyBean methodologyBean);

	public void calculateBaseline();

	public void cummulativeMultiplication();

}
