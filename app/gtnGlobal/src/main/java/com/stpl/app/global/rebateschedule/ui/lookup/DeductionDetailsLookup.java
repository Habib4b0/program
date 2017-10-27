/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.rebateschedule.ui.lookup;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.deductioncalendar.dto.DeductionCalendarDTO;
import com.stpl.app.global.deductioncalendar.logic.DeductionCalendarLogic;
import com.stpl.app.global.deductioncalendar.ui.form.DeductionCalendarForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jboss.logging.Logger;

/**
 *
 * @author lokeshwari
 */
public class DeductionDetailsLookup extends Window {

    String mode;
    int systemId;
    private static final Logger LOGGER = Logger.getLogger(DeductionDetailsLookup.class);
    SessionDTO sessionDTO = new SessionDTO();
    DeductionCalendarLogic deductionCalendarLogic = new DeductionCalendarLogic();
    DeductionCalendarDTO dto = new DeductionCalendarDTO();
    ErrorfulFieldGroup binder;
    DeductionCalendarForm dcform;
    Panel vLayout=new Panel();

    public DeductionDetailsLookup(String mode, int systemId) {
        super("Deduction Calendar Details");
        center();
        setModal(true);
        setHeight("760px");
        setWidth("960px");
        addStyleName(ConstantsUtils.BOOTSTRAP);
        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
        this.mode = mode;
        this.systemId = systemId;
        init();
    }

    public void init() {
        try {
            final Date tempDate = new Date();
            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
            sessionDTO.setUserId(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
            sessionDTO.setSessionDate(fmt.format(tempDate));
            sessionDTO.setUiSessionId(fmtID.format(tempDate));
            sessionDTO.setMode(mode);
            sessionDTO.setSystemId(systemId);
            sessionDTO.setFlagForDeduction("RS");
            dto = deductionCalendarLogic.getDeductionCalendarById(sessionDTO.getSystemId(), dto);
            binder = new ErrorfulFieldGroup(new BeanItem<>(dto));
            dto.setMasterTableSid(sessionDTO.getSystemId());
            deductionCalendarLogic.insertToTempSelectionForCust(sessionDTO.getUserId(), sessionDTO.getUiSessionId(), sessionDTO.getSystemId());
            deductionCalendarLogic.insertToTempSelectionForProd(sessionDTO.getUserId(), sessionDTO.getUiSessionId(), sessionDTO.getSystemId());
            
            dcform = new DeductionCalendarForm(sessionDTO, binder);

            setContent(dcform);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
