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
import com.stpl.app.arm.utils.ARMCheckUtils;
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
    private boolean summaryCheckLeave = false;
    private boolean summaryRestrictLeave = false;

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
            final TextField summaryOverride = new TextField();
            summaryOverride.setData(items);
            summaryOverride.addStyleName("align-right");
            summaryOverride.setConverter(curZero);
            summaryOverride.addFocusListener(new FocusListener() {
                @Override
                public void focus(FocusEvent event) {
                    summaryOverride.addValueChangeListener(overrideListener);
                    summaryOverride.removeFocusListener(this);
                }
            });
            if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL) || total.startsWith("~Total")) {
                summaryOverride.setEnabled(false);
            }
            return summaryOverride;
        }

        return null;
    }
    private Property.ValueChangeListener overrideListener = new Property.ValueChangeListener() {

        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                AdjustmentDTO summaryAdjDto = (AdjustmentDTO) ((List) ((TextField) event.getProperty()).getData()).get(0);
                Object val = event.getProperty().getValue();
                Object propertyId = ((List) ((TextField) event.getProperty()).getData()).get(1);
                Component uiContext = (Component) ((List) ((TextField) event.getProperty()).getData()).get(NumericConstants.TWO);
                valueChangeLogic(summaryAdjDto, val, propertyId, uiContext);
            } catch (Exception e) {
                LOGGER.error("Error in overrideListener :", e);
            }
        }
    };

    protected void valueChangeLogic(AdjustmentDTO dto, Object val, Object propertyId, Component uiContext) {
        ExtCustomTable table = (ExtCustomTable) uiContext;
        int singleVisibleColumn = Integer.parseInt(((String[]) (table.getDoubleHeaderForSingleHeader(propertyId.toString())).split("\\~"))[0]);
        if (ARMCheckUtils.isSingleVisibleColumnPresentInDto(singleVisibleColumn, dto)
                || (ARMCheckUtils.checkIsSummaryTypeDeductionCustomerContract(selection) && ARMCheckUtils.checkIsProductFilterLevel(selection))) {
            Double value = 0.0;
            boolean isEmptiedSummary = false;
            try {
                if (StringUtils.EMPTY.equals(val)) {
                    isEmptiedSummary = true;
                }
                value = Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
            } catch (NumberFormatException e) {
                LOGGER.debug("User is supposed to give Double value {}", e.getMessage());
                if (!isEmptiedSummary) {
                    return;
                }
            }
            List input = new ArrayList();
            //Added this for GAL-5809
            input.add(selection.getProjectionMasterSid());

            input.add(Integer.valueOf(dto.getBranditemmasterSid()));
            input.add(isEmptiedSummary ? "NULL" : value.toString());
            input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
            input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
            input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
            input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
            input.addAll(logic.getTableInput(selection.getSessionDTO()));
            summaryCheckLeave = true;
            service.submit(new SummaryUpdateOverride(input));
        }
    }

    @Override
    public boolean checkLeave() {
        return !summaryCheckLeave;
    }

    @Override
    public boolean isRestrict() {
        return summaryRestrictLeave;
    }

    public void setRestrictLeave() {
        this.summaryRestrictLeave = summaryRestrictLeave;
    }

    public void setCheckLeave(boolean checkLeave) {
        this.summaryCheckLeave = checkLeave;
    }

    class SummaryUpdateOverride implements Runnable {

        private List summaryInput;
        private boolean summaryUpdateSuccess;

        public SummaryUpdateOverride(List input) {
            this.summaryInput = CommonLogic.getInstance().getArrayListCloned(input);

        }

        @Override
        public void run() {
            summaryUpdateSuccess = logic.updateOverride(summaryInput);
        }

        public boolean isUpdateSuccess() {
            return summaryUpdateSuccess;
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
