/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.v7.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Maheshj
 */
public class WorkFlowLookup extends CustomWindow {

    SessionDTO session;
    String projectionId = StringUtils.EMPTY;

    public WorkFlowLookup(SessionDTO session, String projectionId) {
        this.session = session;
        this.projectionId = projectionId;
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(Constants.ZERO);
        setPositionY(Constants.ZERO);
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        setCaption("Work Flow Inbox");
        setClosable(true);

        addContent();
    }

    public void addContent() {
        final VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponent(new CustomInboxDashBoard(projectionId, this));

        setContent(vLayout);

    }
}
