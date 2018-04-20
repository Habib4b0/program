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
            final TextField priceoverride = new TextField();
            priceoverride.setData(itemId);
            priceoverride.setImmediate(true);
            priceoverride.addStyleName("txtRightAlign");
            priceoverride.setConverter(curthree);
            priceoverride.addFocusListener(new FocusListener() {
                @Override
                public void focus(FocusEvent event) {
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
    private Property.ValueChangeListener priceOverrideListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                AdjustmentDTO dto = (AdjustmentDTO) ((TextField) event.getProperty()).getData();
                Object val = event.getProperty().getValue();
                boolean isEmptied = false;
                if (StringUtils.EMPTY.equals(val)) {
                    isEmptied = true;
                }
                Double value = Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\d.]", ""));
                List input = new ArrayList();
                input.add(selection.getSessionDTO().getCurrentTableNames().get(ST_ARM_PIPELINE_SALES.toString()));
                input.add(isEmptied ? "NULL" : value.toString());
                input.add(Integer.valueOf(dto.getBranditemmasterSid()));
                input.add(projectionId);
                service.submit(new UpdateOverride(input));
            } catch (Exception e) {
                LOGGER.error("Error in priceOverrideListener :" + e);
            }
        }
    };

    class UpdateOverride implements Runnable {

        private List input;
        private boolean updateSuccess;

        public UpdateOverride(List input) {
            this.input = CommonLogic.getInstance().getArrayListCloned(input);

        }

        @Override
        public void run() {
            updateSuccess = logic.updatePriceOverride(input);
        }

        public boolean isUpdateSuccess() {
            return updateSuccess;
        }

    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
