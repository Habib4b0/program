/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import static com.stpl.app.adminconsole.util.ConstantsUtils.CFF_TYPE;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ahalya
 */
public class CFFFilterGenerator implements ExtFilterGenerator {

    private ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
    private final CommonUtils commonUtils = new CommonUtils();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CFFFilterGenerator.class);

    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {
                HelperDTO dto = (HelperDTO) originatingField.getValue();
                return new SimpleStringFilter(propertyId, String.valueOf(dto.getId()), false, false);
            } else {
                return null;
            }
        }
        return null;
    }

    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        CommonUtils commonutil = new CommonUtils();
        if ("typeDesc".equals(propertyId)) {

            try {
                ComboBox typeDdlb = new ComboBox();
                typeDdlb.addItem("Show All");
                typeDdlb = commonUtils.getNativeSelect(typeDdlb, logic.getDropDownList(CFF_TYPE), StringUtils.EMPTY);
                typeDdlb.setDebugId("testing");
                typeDdlb.setImmediate(true);
                typeDdlb.setNullSelectionItemId("Show All");
                return typeDdlb;
            } catch (SystemException ex) {
               LOGGER.error(ex.getMessage());
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        }  else if ("deductionType".equals(propertyId)) {
            try {
                final ComboBox deductionType = new ComboBox();
                commonutil.loadComboBox(deductionType, "RS_TYPE", true);
                return deductionType;
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        } else if ("deductionProgram".equals(propertyId)) {
            try {
                final ComboBox deductionProgramType = new ComboBox();
                commonutil.loadComboBox(deductionProgramType, "REBATE_PROGRAM_TYPE", true);
                return deductionProgramType;
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        }

        return null;
    }

    public void filterRemoved(Object propertyId) {
        return;
    }

    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        return;
    }

    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

}
