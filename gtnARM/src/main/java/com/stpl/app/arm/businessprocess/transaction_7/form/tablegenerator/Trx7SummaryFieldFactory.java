/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.form.tablegenerator;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.drools.core.util.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Nimisha.Rakesh
 */
public class Trx7SummaryFieldFactory implements TableFieldFactory {

    protected final AbstractSummaryLogic logic;
    private final AbstractSelectionDTO selection;
    static final Logger LOGGER = Logger.getLogger(Trx7SummaryFieldFactory.class);
    final ExecutorService service = ThreadPool.getInstance().getService();
    private Boolean isFieldRequire;

    public Trx7SummaryFieldFactory(AbstractSummaryLogic logic, AbstractSelectionDTO selection, Boolean isFieldRequire) {
        this.logic = logic;
        this.selection = selection;
        this.isFieldRequire = isFieldRequire;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        String total = ((ExtCustomTable) uiContext).getDoubleHeaderForSingleHeader(propertyId.toString());
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (propertyId.toString().contains("override") && dto.getLevelNo() == NumericConstants.FIVE && isFieldRequire && !total.toString().startsWith("total")) {
            List items = new ArrayList();
            items.add(itemId);
            items.add(propertyId);
            items.add(uiContext);
            final TextField override = new TextField();
            override.setData(items);
            override.addFocusListener(new FieldEvents.FocusListener() {
                @Override
                public void focus(FieldEvents.FocusEvent event) {
                    override.addValueChangeListener(overrideListener);
                    override.removeFocusListener(this);
                }
            });
            return override;
        }

        return null;
    }
    Property.ValueChangeListener overrideListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                AdjustmentDTO dto = (AdjustmentDTO) ((List) ((TextField) event.getProperty()).getData()).get(0);
                Object val = event.getProperty().getValue();
                valueChangeLogic(dto, val, null, null);

            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    };

    protected void valueChangeLogic(AdjustmentDTO dto, Object val, Object propertyId, Component uiContext) {
        Double value = 0.0;
        try {
            value = Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
        } catch (NumberFormatException e) {
            LOGGER.error("User is supposed to give Double value " + e);
            return;
        }
        List input = new ArrayList();
        //Added this for GAL-5809
        input.add(selection.getProjectionMasterSid());

        input.add(Integer.valueOf(dto.getBrand_item_masterSid()));
        input.add(value.toString());
        input.add(dto.getMasterSids()[1] == null ? "%" : dto.getMasterSids()[1]);
        input.add(dto.getMasterSids()[NumericConstants.TWO] == null ? "%" : dto.getMasterSids()[NumericConstants.TWO]);
        input.add(dto.getMasterSids()[NumericConstants.THREE] == null ? "%" : dto.getMasterSids()[NumericConstants.THREE]);
        input.addAll(logic.getTableInput(selection.getSessionDTO()));
        service.submit(new UpdateOverride(input));
    }

    class UpdateOverride implements Runnable {

        List input;
        boolean updateSuccess;

        public UpdateOverride(List input) {
            this.input = input;

        }

        @Override
        public void run() {
            updateSuccess = logic.updateOverride(input);
        }

        public boolean isUpdateSuccess() {
            return updateSuccess;
        }

    }

    AbstractSummaryLogic getLogic() {
        return logic;
    }

}

