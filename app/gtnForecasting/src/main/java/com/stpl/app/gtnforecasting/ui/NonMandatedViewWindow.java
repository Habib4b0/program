/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.DataSelectionForm;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;

import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author soundarrajan
 */
public class NonMandatedViewWindow extends CustomWindow {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(NonMandatedViewWindow.class);

    SessionDTO session;
    DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();

    public NonMandatedViewWindow(String projectionName, SessionDTO session,final DataSelectionForm form) {

        super(projectionName);
        this.session = session;
        init(form);
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init(final DataSelectionForm form) {
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
            setContent(new NonMandatedView(session, dataSelectionDTO, this,form));
        } catch (SystemException ex) {
            LOGGER.error(ex+" NonMandatedViewWindow");
        } catch (Exception ex) {
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
            } catch (Exception ex) {
            LOGGER.error(ex+" NonMandatedViewWindow - loadSessionDTO");
            }

        } else {
            dataSelectionDTO = new DataSelectionDTO();
        }
    }
}
