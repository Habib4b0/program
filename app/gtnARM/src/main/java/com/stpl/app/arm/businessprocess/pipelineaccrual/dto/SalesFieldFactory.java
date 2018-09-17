/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.SalesLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import static com.stpl.app.arm.utils.ARMUtils.SalesVariables.ST_ARM_PIPELINE_SALES;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class SalesFieldFactory implements TableFieldFactory {

    private final SalesLogic logic;
    private final int projectionId;
    private static final Logger LOGGER = LoggerFactory.getLogger(SalesFieldFactory.class);
    private final ExecutorService service = ThreadPool.getInstance().getService();
    private DataFormatConverter curthree = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);
    private PipelineAccrualSelectionDTO selection;

    public SalesFieldFactory(SalesLogic logic, PipelineAccrualSelectionDTO selection) {
        this.logic = logic;
        this.projectionId = selection.getProjectionMasterSid();
        this.selection = selection;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (!dto.getChildrenAllowed() && VariableConstants.PRICE_OVERRIDE.equals(propertyId.toString()) && !ARMUtils.levelVariablesVarables.BRAND.toString().equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
            final TextField salesPriceoverride = new TextField();
            salesPriceoverride.setData(itemId);
            salesPriceoverride.setImmediate(true);
            salesPriceoverride.addStyleName("txtRightAlign");
            salesPriceoverride.setConverter(curthree);
            salesPriceoverride.addFocusListener(new FocusListener() {
                @Override
                public void focus(FocusEvent event) {
                    salesPriceoverride.addValueChangeListener(priceOverrideListener);
                    salesPriceoverride.removeFocusListener(this);
                }
            });
            return salesPriceoverride;
        }
        if (dto.getChildrenAllowed()) {
            dto.addStringProperties("priceOverride.7", StringUtils.EMPTY);
        }
        return null;
    }
    private Property.ValueChangeListener priceOverrideListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                AdjustmentDTO dto = (AdjustmentDTO) ((TextField) event.getProperty()).getData();
                Object salesVal = event.getProperty().getValue();
                boolean isEmptied = false;
                if (StringUtils.EMPTY.equals(salesVal)) {
                    isEmptied = true;
                }
                Double value = Double.valueOf(salesVal == null ? "0" : salesVal.toString().trim().replaceAll("[^\\d.]", ""));
                List salesInput = new ArrayList();
                salesInput.add(selection.getSessionDTO().getCurrentTableNames().get(ST_ARM_PIPELINE_SALES.toString()));
                salesInput.add(isEmptied ? "NULL" : value.toString());
                salesInput.add(Integer.valueOf(dto.getBranditemmasterSid()));
                salesInput.add(projectionId);
                service.submit(new SalesUpdateOverride(salesInput));
            } catch (Exception e) {
                LOGGER.error("Error in priceOverrideListener :", e);
            }
        }
    };

    class SalesUpdateOverride implements Runnable {

        private List salesInput;
        private boolean salesUpdateSuccess;

        public SalesUpdateOverride(List input) {
            this.salesInput = CommonLogic.getInstance().getArrayListCloned(input);

        }

        @Override
        public void run() {
            salesUpdateSuccess = logic.updatePriceOverride(salesInput);
        }

        public boolean isUpdateSuccess() {
            return salesUpdateSuccess;
        }

    }

    private void writeObject(ObjectOutputStream salesout) throws IOException {
        salesout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream salesin) throws IOException, ClassNotFoundException {
        salesin.defaultReadObject();
    }
}
