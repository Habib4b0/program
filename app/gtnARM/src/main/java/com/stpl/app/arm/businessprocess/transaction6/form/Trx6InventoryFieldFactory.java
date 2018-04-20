/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.InventoryFieldFactory;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6InventoryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TableFieldFactory;
import com.vaadin.v7.ui.TextField;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class Trx6InventoryFieldFactory implements TableFieldFactory {

    private final Trx6InventoryLogic logic;
    private final int projectionId;
    private final AbstractSelectionDTO selection;
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryFieldFactory.class);
    private final ExecutorService service = ThreadPool.getInstance().getService();
    private DataFormatConverter curThree = new DataFormatConverter("#,##0.00", "");
    private DataFormatConverter curThreeDollar = new DataFormatConverter("#,##0.00", DataFormatConverter.INDICATOR_DOLLAR);

    public Trx6InventoryFieldFactory(Trx6InventoryLogic logic, AbstractSelectionDTO selection) {
        this.logic = logic;
        this.projectionId = selection.getProjectionMasterSid();
        this.selection = selection;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (!dto.getChildrenAllowed() && dto.getLevelNo() == NumericConstants.THREE && (propertyId.toString().contains(ARMUtils.Trx6_Variables.BASELINE_PRICE_OVERRIDE.getColumn())
                || propertyId.toString().contains(ARMUtils.Trx6_Variables.ADJUSTED_PRICE_OVERRIDE.getColumn())
                || propertyId.toString().contains(ARMUtils.Trx6_Variables.INFLATION_FACTOR.getColumn()))) {
            final TextField priceoverride = new TextField();
            Map map = new HashMap<>();
            map.put(ARMUtils.PROPERTY_ID, propertyId);
            map.put(ARMUtils.ITEM_ID, itemId);
            priceoverride.setData(map);
            priceoverride.setImmediate(true);
            priceoverride.addStyleName("txtRightAlign");
            if (propertyId.toString().contains(ARMUtils.Trx6_Variables.ADJUSTED_PRICE_OVERRIDE.getColumn()) || propertyId.toString().contains(ARMUtils.Trx6_Variables.BASELINE_PRICE_OVERRIDE.getColumn())) {
                priceoverride.setConverter(curThreeDollar);
            } else if (propertyId.toString().contains(ARMUtils.Trx6_Variables.INFLATION_FACTOR.getColumn())) {
                priceoverride.setConverter(curThree);
            }
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
            dto.addStringProperties("priceOverride.6", StringUtils.EMPTY);
        }
        return null;
    }
    private Property.ValueChangeListener priceOverrideListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                TextField text = (TextField) event.getProperty();
                Map map = (Map) text.getData();
                AdjustmentDTO dto = (AdjustmentDTO) map.get(ARMUtils.ITEM_ID);
                String propertyId = (String) map.get(ARMUtils.PROPERTY_ID);
                Object val = text.getValue();
                String value = StringUtils.EMPTY;
                if (val != null) {
                    value = val.toString().replace(ARMUtils.COMMA, StringUtils.EMPTY).replace("$", StringUtils.EMPTY).replace("%", StringUtils.EMPTY);
                }
                List input = new ArrayList();
                input.add(selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY"));
                if (propertyId.contains(ARMUtils.Trx6_Variables.BASELINE_PRICE_OVERRIDE.getColumn())) {
                    input.add(ARMUtils.getDBColumnMapForInventoyTrx6().get(ARMUtils.Trx6_Variables.BASELINE_PRICE_OVERRIDE.getColumn()));
                } else if (propertyId.contains(ARMUtils.Trx6_Variables.ADJUSTED_PRICE_OVERRIDE.getColumn())) {
                    input.add(ARMUtils.getDBColumnMapForInventoyTrx6().get(ARMUtils.Trx6_Variables.ADJUSTED_PRICE_OVERRIDE.getColumn()));
                } else if (propertyId.contains(ARMUtils.Trx6_Variables.INFLATION_FACTOR.getColumn())) {
                    input.add(ARMUtils.getDBColumnMapForInventoyTrx6().get(ARMUtils.Trx6_Variables.INFLATION_FACTOR.getColumn()));
                }
                if (val != null && !String.valueOf(val).isEmpty()) {
                    input.add(String.valueOf(Double.valueOf(value)));
                } else {
                    input.add(null);
                }
                input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
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
