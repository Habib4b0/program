package com.stpl.gtn.gtn2o.ws.baseline;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;

public interface GtnWsBaseline {

	/**
	 * Method used to calculate the baseline values for different
	 * methodologies.Please refer javadoc of particular methodology for more
	 * info.
	 * 
	 * @param methodologyBean
	 * @throws Exception
	 */
	public void calculateBaseline(GtnWsMethodologyBean methodologyBean) throws Exception;

}
