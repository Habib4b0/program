/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.ui.form;

import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Window;
import org.jboss.logging.Logger;

/**
 *
 * @author nimisha.rakesh
 */
public class InformationPopup extends Window{
    
    
    CommonLogic logic = new CommonLogic();
    /** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(InformationPopup.class);
    
    public InformationPopup(int systemId,String tableName){
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        center();
        setClosable(true);
        setModal(true);
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        setWidth(NumericConstants.FLOAT_SEVENTY, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_SIXTY, Sizeable.Unit.PERCENTAGE);
        setCaption("Invalid Table < "+tableName+" >");

        try {
                        
            final int tabCount = (Integer) VaadinSession.getCurrent().getAttribute(ConstantUtil.TABCOUNT);
            if (tabCount == NumericConstants.TWO) {
                Object[] ob = logic.getFiledNames(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.DDLB_NAME)), "View");
                  setContent(new ViewForm(ob, logic.getValuesById(Integer.valueOf(systemId), ob)));
                } else {

                    setContent(new TabSheetForm(systemId));
                    
                }
       
        } catch (Exception ex) {
            LOGGER.error(ex);
}
    }
}
