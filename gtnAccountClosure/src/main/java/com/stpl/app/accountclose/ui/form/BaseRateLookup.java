/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Maheshj
 */
public class BaseRateLookup  extends CustomWindow {
    

     String accMasSid=StringUtils.EMPTY;
     SessionDTO session;
     BaseRateDTO baseRateDTO;
     List<String> ccpIds=new ArrayList<String>();
     BaseRateCalculation baseRateCalculation=null;
     FixedDollarDTO fixedDto;

    public BaseRateLookup(final SessionDTO session, BaseRateDTO baseRateDTO,List<String> ccpIds,String accMasSid,FixedDollarDTO fixedDto) {
        this.session = session;
        this.baseRateDTO=baseRateDTO;
        this.ccpIds=ccpIds;
        this.accMasSid=accMasSid;
        this.fixedDto=fixedDto;
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
         baseRateCalculation= new BaseRateCalculation(session,baseRateDTO,true,ccpIds,accMasSid,this,fixedDto);
        vLayout.addComponent(baseRateCalculation);
       
        setContent(vLayout);

    }

    public BaseRateCalculation getBaseRateCalculation() {
        return baseRateCalculation;
    }

    public void setBaseRateCalculation(BaseRateCalculation baseRateCalculation) {
        this.baseRateCalculation = baseRateCalculation;
    }
    
    
}
