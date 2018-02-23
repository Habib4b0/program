/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.DataSelectionForm;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.ParseException;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForecastEditWindow extends CustomWindow {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastEditWindow.class);

    protected SessionDTO session;
    private DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();
    protected ExtFilterTable resultTable;
    protected String screenName;
    protected final DataSelectionForm dataSelectionForm;

    public ForecastEditWindow(String projectionName, SessionDTO session, final ExtFilterTable resultTable, final String screenName, final DataSelectionForm dataSelectionForm) throws Exception {
        super(projectionName);
        this.session = session;
        this.resultTable = resultTable;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;
        init();
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init() throws Exception {
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(Constant.ZERO);
        setPositionY(Constant.ZERO);
        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(false);        
        loadSessionDTO();
        setContent(new ForecastEditView(session, dataSelectionDTO, this, resultTable, screenName, dataSelectionForm));
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
                LOGGER.error("NonMandatedEditWindow - loadSessionDTO= {}", ex);
            }
        } else {
            dataSelectionDTO = new DataSelectionDTO();
        }
    }
}
