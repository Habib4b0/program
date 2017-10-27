/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.transaction7.logic.Trx7SalesLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class Trx7SalesFieldFactory implements TableFieldFactory {

    private final Trx7SalesLogic logic;
    private final int projectionId;
    private static final Logger LOGGER = Logger.getLogger(Trx7SalesFieldFactory.class);
    private final ExecutorService service = ThreadPool.getInstance().getService();
    DataFormatConverter curThree = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    Trx7SelectionDTO selection;

    public Trx7SalesFieldFactory(Trx7SalesLogic logic, int projectionId, int sessionId, Trx7SelectionDTO selection) {
        this.logic = logic;
        this.projectionId = projectionId;
        this.selection = selection;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (!dto.getChildrenAllowed() && VariableConstants.PRICE_OVERRIDE.equals(propertyId.toString()) && !ARMUtils.levelVariablesVarables.CUSTOMER.toString().equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue())) && !ARMUtils.levelVariablesVarables.BRAND.toString().equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
            final TextField priceoverride = new TextField();
            priceoverride.setData(itemId);
            priceoverride.setImmediate(true);
            priceoverride.addStyleName("txtRightAlign");
            priceoverride.setConverter(curThree);
            priceoverride.addFocusListener(new FieldEvents.FocusListener() {
                @Override
                public void focus(FieldEvents.FocusEvent event) {
                    priceoverride.addValueChangeListener(priceOverrideListener);
                    priceoverride.removeFocusListener(this);
                }
            });
            return priceoverride;
        }
        if (dto.getChildrenAllowed()) {
            dto.addStringProperties("priceOverride.7", StringUtils.EMPTY);
        }
        return null;
    }
    Property.ValueChangeListener priceOverrideListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                AdjustmentDTO dto = (AdjustmentDTO) ((TextField) event.getProperty()).getData();
                Object val = event.getProperty().getValue();
                Double value;
                value = Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\d.]", ""));

                List input = new ArrayList();
                input.add(selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_DISTRIBUTION_FEES_SALES"));
                input.add(value.toString());
                input.add(Integer.valueOf(dto.getCompSids()));
                input.add(Integer.valueOf(dto.getBranditemmasterSid()));
                input.add(projectionId);
                service.submit(new UpdateOverride(input));
            } catch (NumberFormatException e) {
                LOGGER.debug("User is supposed to give Double value");
                LOGGER.error("Error in priceOverrideListener :"+e);
            }
        }
    };

    class UpdateOverride implements Runnable {

        List input;
        boolean updateSuccess;

        public UpdateOverride(List input) {
            this.input = input;

        }

        @Override
        public void run() {
            updateSuccess = logic.updatePriceOverride(input);
        }

        public boolean isUpdateSuccess() {
            return updateSuccess;
        }

    }

}
