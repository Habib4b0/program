/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.GTNbalanceLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.FixedDollarAdjustmentDetails;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
class GtnFixedDollarLookup extends CustomWindow {
    

     SessionDTO session;
     GTNbalanceLogic logic=new GTNbalanceLogic();
     private static final Logger LOGGER = Logger.getLogger(GtnFixedDollarLookup.class);
    public GtnFixedDollarLookup(SessionDTO session) {
        this.session = session;
        center();
        setWidth(100, Sizeable.Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-fe");
        setCaption("Suggested Adjustment Calculator");
        setClosable(true);
        setModal(true);
        addContent();
    }

    public void addContent() {
        final VerticalLayout vLayout = new VerticalLayout();
        FixedDollarDTO dollarDTO= new FixedDollarDTO();
        String ccpIdForFda=StringUtils.EMPTY;
        try{
        List<String> ccpIds=logic.getCcpIds(session);
        if(!ccpIds.isEmpty()){
            int size=ccpIds.size();
            for (int i = 0; i < size; i++) {
                if(i==0){
                    ccpIdForFda=String.valueOf(ccpIds.get(i));
                }else{
                  ccpIdForFda+=","+String.valueOf(ccpIds.get(i));
                }
            }
        dollarDTO.setCcpSidFromGtn(ccpIdForFda);
        }
        FixedDollarAdjustmentDetails details=new FixedDollarAdjustmentDetails(session,dollarDTO ,true);
        vLayout.addComponent(details);
        setContent(vLayout);
        }catch(Exception e){
            LOGGER.error(e);
        }

    }
}
