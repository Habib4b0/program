/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.ParseException;
import org.asi.ui.customwindow.CustomWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author soundarrajan
 */
public class NonMandatedViewWindow extends CustomWindow {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NonMandatedViewWindow.class);

    protected SessionDTO session;
    protected DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();

    public NonMandatedViewWindow(String projectionName, SessionDTO session) {

        super(projectionName);
        this.session = session;
        init();
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init() {
        try {
            center();
            setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            setPositionX(Constant.ZERO);
            setPositionY(Constant.ZERO);
            addStyleName(Constant.BOOTSTRAP_UI);
            addStyleName(Constant.BOOTSTRAP);
            addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
            setClosable(false);
            loadSessionDTO();
            setContent(new NonMandatedView(session, dataSelectionDTO));
        }  catch (Exception ex) {
            LOGGER.error(ex+" NonMandatedViewWindow");
        }
    }

    private void loadSessionDTO() {
        int projectionId = session.getProjectionId();
        NonMandatedLogic logic = new NonMandatedLogic();
        if (projectionId != 0) {
            try {
                dataSelectionDTO = logic.getProjection(projectionId);
                session.setProjectionName(dataSelectionDTO.getProjectionName());
                session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
            } catch (ParseException ex) {
            LOGGER.error(ex+" NonMandatedViewWindow - loadSessionDTO");
            }

        } else {
            dataSelectionDTO = new DataSelectionDTO();
        }
    }
}
