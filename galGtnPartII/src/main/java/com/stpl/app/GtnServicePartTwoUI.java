package com.stpl.app;

import com.stpl.app.parttwo.service.SlaCalendarDetailsLocalServiceUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 * Hello world!
 *
 */
public class GtnServicePartTwoUI extends UI{

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub
            
            
		try {
                    
                    int count = SlaCalendarDetailsLocalServiceUtil.getSlaCalendarDetailsesCount();
                    
			setContent(new Label(String.valueOf(count)));
                }catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

