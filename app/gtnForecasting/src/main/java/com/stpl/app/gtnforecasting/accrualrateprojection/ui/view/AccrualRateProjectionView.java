/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.ui.view;

import com.stpl.app.gtnforecasting.accrualrateprojection.ui.form.AccrualRateProjectionForm;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.DataSelectionForm;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.VerticalLayout;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sibi
 */
public class AccrualRateProjectionView extends VerticalLayout implements View {

    private AccrualRateProjectionForm accrualRateProjectionForm;

    public static final String ARP_VIEW = "ARPVIEW";
    protected String projectionName = StringUtils.EMPTY;
    protected SessionDTO session;
    protected String screenName;
    protected final DataSelectionForm dataSelectionForm;
    protected DataSelectionDTO dataSelectionDTO;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccrualRateProjectionView.class);
    private final boolean isOpenedFromWorkflow;

    public AccrualRateProjectionView(String projectionName, SessionDTO session, final String screenName, final DataSelectionForm dataSelectionForm, final boolean isOpenedFromWorkflow) {
        this.projectionName = projectionName;
        this.session = session;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;
        this.isOpenedFromWorkflow = isOpenedFromWorkflow;
        loadSessionDTO();
        if(session.getCurrentTableNames().isEmpty() && !session.getAction().equalsIgnoreCase(Constant.VIEW)){
            session.setScreenName(screenName);
            QueryUtils.createTempTables(session);
        }
        if(this.isOpenedFromWorkflow){
            loadForm();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (!this.isOpenedFromWorkflow) {
            loadForm();
        }
    }

    private void loadForm() {
        try {
            if (this.getComponentCount() != 0) {
                this.removeAllComponents();
            }
            accrualRateProjectionForm = new AccrualRateProjectionForm(dataSelectionDTO, session, screenName, dataSelectionForm);
            this.addComponent(accrualRateProjectionForm);
            accrualRateProjectionForm.getSales().historyDdlb.focus();
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
    }
    
    private void loadSessionDTO() {
        try {
            int projectionId = session.getProjectionId();
            Map<String, Object> parameters = new HashMap<>();
            NonMandatedLogic logic = new NonMandatedLogic();
            if (projectionId != 0) {
                try {
                    parameters.put(Constant.PROJECTION_ID, projectionId);
                    dataSelectionDTO = logic.getProjection(projectionId);
                    session.setProjectionName(dataSelectionDTO.getProjectionName());
                    session.setCustRelationshipBuilderSid(dataSelectionDTO.getCustRelationshipBuilderSid());
                    session.setProdRelationshipBuilderSid(dataSelectionDTO.getProdRelationshipBuilderSid());
                } catch (ParseException ex) {

                    LOGGER.error(ex.getMessage());
                }
            } else {
                dataSelectionDTO = new DataSelectionDTO();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }
    }

}
