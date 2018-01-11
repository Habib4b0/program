/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.view;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.deductioncalendar.dto.DeductionCalendarDTO;
import com.stpl.app.global.deductioncalendar.logic.DeductionCalendarLogic;
import com.stpl.app.global.deductioncalendar.ui.form.DeductionCalendarForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.QueryUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class DeductionCalendarView extends VerticalLayout implements View {

    private static final Logger LOGGER = Logger.getLogger(DeductionCalendarView.class);

    public static final String NAME = "ADD";

    private DeductionCalendarForm dcform;
    
    private String sessionID = StringUtils.EMPTY;
    private final SessionDTO sessionDTO;
    private final DeductionCalendarLogic deductionCalendarLogic = new DeductionCalendarLogic();
    private DeductionCalendarDTO dto = new DeductionCalendarDTO();
    private ErrorfulFieldGroup binder;

    public DeductionCalendarView(final SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        setStyleName("bootstrap-company");
        this.removeAllComponents();
        setSpacing(true);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            this.removeAllComponents();
            final Date tempDate = new Date();
            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
            sessionDTO.setSessionDate(fmt.format(tempDate));
            sessionDTO.setUiSessionId(fmtID.format(tempDate));
            String mode = sessionDTO.getMode();
            sessionDTO.setScreenName(ConstantsUtils.DEDUCTION_CALENDAR);
            if (!sessionID.equals(sessionDTO.getUiSessionId())) {
                QueryUtils.createTempTables(sessionDTO);
                sessionID = sessionDTO.getUiSessionId();
            }
            if ((ConstantsUtils.ADD).equals(mode)) {
                sessionDTO.setSystemId(0);
                dcform = new DeductionCalendarForm(sessionDTO);
                addComponent(dcform);
            } else if ((ConstantsUtils.EDIT).equals(mode) || (ConstantsUtils.VIEW).equalsIgnoreCase(mode) || (ConstantsUtils.COPY).equals(mode)) {
                this.removeAllComponents();
                if (!(ConstantsUtils.COPY).equals(mode)) {
                    dto = deductionCalendarLogic.getDeductionCalendarById(sessionDTO.getSystemId(), dto);
                }
                binder = new ErrorfulFieldGroup(new BeanItem<>(dto));
                dto.setMasterTableSid(sessionDTO.getSystemId());
                sessionDTO.setAdditionalNotes(dto.getInternalNotes());
                deductionCalendarLogic.insertToTempSelectionForCust(sessionDTO.getUserId(), sessionDTO.getUiSessionId(), sessionDTO.getSystemId());
                deductionCalendarLogic.insertToTempSelectionForProd(sessionDTO.getUserId(), sessionDTO.getUiSessionId(), sessionDTO.getSystemId());
                dcform = new DeductionCalendarForm(sessionDTO, binder);
                addComponent(dcform);
            }
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }

    }

}
