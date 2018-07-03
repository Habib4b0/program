/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.form.tablegenerator;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.util.NumericConstants;
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
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nimisha.Rakesh
 */
public class Trx7SummaryFieldFactory implements TableFieldFactory {

    protected final AbstractSummaryLogic logic;
    private final AbstractSelectionDTO selection;
    private static final Logger LOGGER = LoggerFactory.getLogger(Trx7SummaryFieldFactory.class);
    public final ExecutorService service = ThreadPool.getInstance().getService();
    private Boolean isFieldRequire;

    public Trx7SummaryFieldFactory(AbstractSummaryLogic logic, AbstractSelectionDTO selection, Boolean isFieldRequire) {
        this.logic = logic;
        this.selection = selection;
        this.isFieldRequire = isFieldRequire;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        String total = ((ExtPagedTable) uiContext).getDoubleHeaderForSingleHeader(propertyId.toString());
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (propertyId.toString().contains("override") && dto.getLevelNo() == NumericConstants.FIVE && isFieldRequire && !total.startsWith("total")) {
            List items = new ArrayList();
            items.add(itemId);
            items.add(propertyId);
            items.add(uiContext);
            final TextField override = new TextField();
            override.setData(items);
            override.addFocusListener(new FocusListener() {
                @Override
                public void focus(FocusEvent event) {
                    override.addValueChangeListener(overrideListener);
                    override.removeFocusListener(this);
                }
            });
            return override;
        }

        return null;
    }
    private Property.ValueChangeListener overrideListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                AdjustmentDTO dto = (AdjustmentDTO) ((List) ((TextField) event.getProperty()).getData()).get(0);
                Object val = event.getProperty().getValue();
                valueChangeLogic(dto, val, null, null);

            } catch (Exception e) {
                LOGGER.error("Error in overrideListener :" + e);
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

        input.add(Integer.valueOf(dto.getBranditemmasterSid()));
        input.add(value.toString());
        input.add(dto.getMasterSids()[1] == null ? "%" : dto.getMasterSids()[1]);
        input.add(dto.getMasterSids()[NumericConstants.TWO] == null ? "%" : dto.getMasterSids()[NumericConstants.TWO]);
        input.add(dto.getMasterSids()[NumericConstants.THREE] == null ? "%" : dto.getMasterSids()[NumericConstants.THREE]);
        input.addAll(logic.getTableInput(selection.getSessionDTO()));
        service.submit(new UpdateOverride(input));
    }

    class UpdateOverride implements Runnable {

        private List input;
        private boolean updateSuccess;

        public UpdateOverride(List input) {
            this.input = CommonLogic.getInstance().getArrayListCloned(input);

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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
