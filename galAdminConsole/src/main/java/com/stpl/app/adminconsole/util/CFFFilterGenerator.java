/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import static com.stpl.app.adminconsole.util.ConstantsUtils.CFF_TYPE;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author ahalya
 */
public class CFFFilterGenerator implements ExtFilterGenerator {

    ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
    private final CommonUtils commonUtils = new CommonUtils();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CFFFilterGenerator.class);

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
                LOGGER.error(ex);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        if ("statusDesc".equals(propertyId)) {

            try {
                ComboBox statusDdlb = new ComboBox();
                statusDdlb.addItem("Show All");
                statusDdlb.addItems(logic.getDropDownList(CommonUtils.WORKFLOW_STATUS));
                statusDdlb.setDebugId("testing");
                statusDdlb.setImmediate(true);
                statusDdlb.setNullSelectionItemId("Show All");
                return statusDdlb;
            } catch (SystemException ex) {
                LOGGER.error(ex);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return null;
    }

    public void filterRemoved(Object propertyId) {

    }

    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {

    }

    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

}
