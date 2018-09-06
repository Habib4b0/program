/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.constants.ARMConstants;
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
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nimisha.Rakesh
 */
public class SummaryFieldFactory implements TableFieldFactory, LeaveCheckAble {

    protected final AbstractSummaryLogic logic;
    private final AbstractSelectionDTO selection;
    public static final Logger LOGGER = LoggerFactory.getLogger(SummaryFieldFactory.class);
    public final ExecutorService service = ThreadPool.getInstance().getService();
    private Boolean isFieldRequire;
    private final DataFormatConverter curZero = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_DOLLAR);
    private boolean checkLeave = false;
    private boolean restrictLeave = false;

    public SummaryFieldFactory(AbstractSummaryLogic logic, AbstractSelectionDTO selection, Boolean isFieldRequire) {
        this.logic = logic;
        this.selection = selection;
        this.isFieldRequire = isFieldRequire;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        String total = ((ExtCustomTable) uiContext).getDoubleHeaderForSingleHeader(propertyId.toString());
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (propertyId.toString().contains("override") && dto.getLevelNo() == NumericConstants.FIVE && isFieldRequire && !total.startsWith("total")) {
            List items = new ArrayList();
            items.add(itemId);
            items.add(propertyId);
            items.add(uiContext);
            final TextField override = new TextField();
            override.setData(items);
            override.addStyleName("align-right");
            override.setConverter(curZero);
            override.addFocusListener(new FocusListener() {
                @Override
                public void focus(FocusEvent event) {
                    override.addValueChangeListener(overrideListener);
                    override.removeFocusListener(this);
                }
            });
            if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL) || total.startsWith("~Total")) {
                override.setEnabled(false);
            }
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
                Object propertyId = ((List) ((TextField) event.getProperty()).getData()).get(1);
                Component uiContext = (Component) ((List) ((TextField) event.getProperty()).getData()).get(NumericConstants.TWO);
                valueChangeLogic(dto, val, propertyId, uiContext);
            } catch (Exception e) {
                LOGGER.error("Error in overrideListener :", e);
            }
        }
    };

    protected void valueChangeLogic(AdjustmentDTO dto, Object val, Object propertyId, Component uiContext) {
        ExtCustomTable table = (ExtCustomTable) uiContext;
        int singleVisibleColumn = Integer.valueOf(((String[]) (table.getDoubleHeaderForSingleHeader(propertyId.toString())).split("\\~"))[0]);
        if (singleVisibleColumn == (dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()))) {
            Double value = 0.0;
            boolean isEmptied = false;
            try {
                if (StringUtils.EMPTY.equals(val)) {
                    isEmptied = true;
                }
                value = Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
            } catch (NumberFormatException e) {
                LOGGER.debug("User is supposed to give Double value {}", e.getMessage());
                if (!isEmptied) {
                    return;
                }
            }
            List input = new ArrayList();
            //Added this for GAL-5809
            input.add(selection.getProjectionMasterSid());

            input.add(Integer.valueOf(dto.getBranditemmasterSid()));
            input.add(isEmptied ? "NULL" : value.toString());
            input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
            input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
            input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
            input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
            input.addAll(logic.getTableInput(selection.getSessionDTO()));
            checkLeave = true;
            service.submit(new UpdateOverride(input));
        }
    }

    @Override
    public boolean checkLeave() {
        return !checkLeave;
    }

    @Override
    public boolean isRestrict() {
        return restrictLeave;
    }

    public void setRestrictLeave() {
        this.restrictLeave = restrictLeave;
    }

    public void setCheckLeave(boolean checkLeave) {
        this.checkLeave = checkLeave;
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
