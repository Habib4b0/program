/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.accrualrateprojection.ui.view;

import com.stpl.app.galforecasting.accrualrateprojection.ui.form.AccrualRateProjectionForm;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.form.DataSelectionForm;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class AccrualRateProjectionView extends VerticalLayout implements View {

    private AccrualRateProjectionForm accrualRateProjectionForm;

    public static final String ARP_VIEW = "ARPVIEW";
    String projectionName = StringUtils.EMPTY;
    SessionDTO session;
    String screenName;
    final DataSelectionForm dataSelectionForm;
    DataSelectionDTO dataSelectionDTO;
    private static final Logger LOGGER = Logger.getLogger(AccrualRateProjectionView.class);

    public AccrualRateProjectionView(String projectionName, SessionDTO session, final String screenName, final DataSelectionForm dataSelectionForm) {
        this.projectionName = projectionName;
        this.session = session;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;
        loadSessionDTO();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            if (this.getComponentCount() != 0) {
                this.removeAllComponents();
            }
            accrualRateProjectionForm = new AccrualRateProjectionForm(dataSelectionDTO, session, screenName, dataSelectionForm);
            this.addComponent(accrualRateProjectionForm);
            accrualRateProjectionForm.sales.historyDdlb.focus();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void loadSessionDTO() {
        try {
            int projectionId = session.getProjectionId();
            Map<String, Object> parameters = new HashMap<String, Object>();
            NonMandatedLogic logic = new NonMandatedLogic();
            if (projectionId != 0) {
                try {
                    parameters.put(Constant.PROJECTION_ID, projectionId);
                    dataSelectionDTO = logic.getProjection(projectionId);
                    session.setProjectionName(dataSelectionDTO.getProjectionName());
                    session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                    session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                } catch (Exception ex) {

                    LOGGER.error(ex.getMessage() + " EditWindow - loadSessionDTO");
                }
            } else {
                dataSelectionDTO = new DataSelectionDTO();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }
    }

}
