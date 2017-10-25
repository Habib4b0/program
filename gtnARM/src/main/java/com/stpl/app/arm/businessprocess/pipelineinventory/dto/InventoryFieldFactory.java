/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.InventoryLogic;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.concurrent.ExecutorService;
import com.vaadin.ui.TableFieldFactory;
import org.jboss.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class InventoryFieldFactory implements TableFieldFactory {
    
     private final InventoryLogic logic;
     private final int projectionId;
     private final AbstractSelectionDTO selection;
     private static final Logger LOGGER = Logger.getLogger(InventoryFieldFactory.class);
     private final ExecutorService service = ThreadPool.getInstance().getService();
     DataFormatConverter CUR_FORMAT = new DataFormatConverter("#,##0", DataFormatConverter.INDICATOR_DOLLAR);
     private final DataFormatConverter CUR_TWO_FORMAT = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_DOLLAR);
    public InventoryFieldFactory(InventoryLogic logic ,AbstractSelectionDTO selection) {
        this.logic = logic;
        this.projectionId = selection.getProjectionMasterSid();
        this.selection = selection;
    }
   
    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (!dto.getChildrenAllowed() && VariableConstants.PRICE_OVERRIDE_INVENTORY.equals(propertyId.toString().split("\\.")[0])) {
            final TextField priceoverride = new TextField();
            priceoverride.setData(itemId);
            priceoverride.setImmediate(true);
            priceoverride.addStyleName("txtRightAlign");
            priceoverride.setConverter(CUR_TWO_FORMAT);
            priceoverride.addFocusListener(new FieldEvents.FocusListener() {
                @Override
                public void focus(FieldEvents.FocusEvent event) {
                    priceoverride.addValueChangeListener(priceOverrideListener);
                    priceoverride.removeFocusListener(this);
                }
            });
            return priceoverride;
        }
        if(dto.getChildrenAllowed()){
            dto.addStringProperties("priceOverride.",StringUtils.EMPTY);
        }
        return null;
    }
    Property.ValueChangeListener priceOverrideListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                AdjustmentDTO dto = (AdjustmentDTO) ((TextField) event.getProperty()).getData();
                Object val = event.getProperty().getValue();
                
                boolean isEmptied=false;
                if (StringUtils.EMPTY.equals(val)) {
                    isEmptied=true;
                }
                
                Double value = 0.0;
                try {
                    value = Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
                } catch (NumberFormatException e) {
                    LOGGER.debug("User is supposed to give Double value");
                    if(!isEmptied){
                    return;
                   }
                }
                List input=new ArrayList();
                
                input.add(selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INVENTORY"));
                input.add(isEmptied ? "NULL" :value.toString());
                input.add(Integer.valueOf(dto.getBrand_item_masterSid()));
                input.add(projectionId);
                service.submit(new UpdateOverride(input));
            } catch (Exception e) {
                LOGGER.error(e);
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
