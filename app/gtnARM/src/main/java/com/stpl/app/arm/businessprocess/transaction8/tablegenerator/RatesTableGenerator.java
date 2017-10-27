/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.tablegenerator;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
import com.stpl.app.arm.businessprocess.commontemplates.AdjustmentTableLogic;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Sathya.Seelan
 */
public class RatesTableGenerator implements TableFieldFactory, LeaveCheckAble {

    protected final AbstractPipelineLogic logic;
    private final AbstractSelectionDTO selection;
    private static final Logger LOGGER = Logger.getLogger(RatesTableGenerator.class);

    private final DataFormatConverter CUR_ZERO = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
    private boolean checkLeave = false;
    private boolean restrictLeave = false;
    private final boolean isFieldRequired;
    private final Object[] visibleColumns;
    private AdjustmentTableLogic tableLogic = null;

    public RatesTableGenerator(AbstractPipelineLogic logic, AbstractSelectionDTO selection, boolean isFieldRequired, Object[] visibleColumns) {
        this.logic = logic;
        this.selection = selection;
        this.isFieldRequired = isFieldRequired;
        this.visibleColumns = visibleColumns;
    }

    public RatesTableGenerator(AbstractPipelineLogic logic, AbstractSelectionDTO selection, boolean isFieldRequired, Object[] visibleColumns, AdjustmentTableLogic tableLogic) {
        this.logic = logic;
        this.selection = selection;
        this.isFieldRequired = isFieldRequired;
        this.visibleColumns = visibleColumns;
        this.tableLogic = tableLogic;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (logic.getCondition(dto, propertyId, selection) && isFieldRequired && Arrays.asList(visibleColumns).contains(propertyId)) {
            List items = new ArrayList();
            items.add(itemId);
            items.add(propertyId);
            items.add(uiContext);
            final TextField override = new TextField();
            override.setData(items);
            override.addStyleName("align-right");
            override.setConverter(CUR_ZERO);
            override.addFocusListener(new FieldEvents.FocusListener() {
                @Override
                public void focus(FieldEvents.FocusEvent event) {
                    override.addValueChangeListener(overrideListener);
                    override.removeFocusListener(this);
                }
            });
            if(selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)){
                override.setEnabled(false);
            }
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
                Object propertyId = ((List) ((TextField) event.getProperty()).getData()).get(1);
                Component uiContext = (Component) ((List) ((TextField) event.getProperty()).getData()).get(NumericConstants.TWO);
                selection.setRatesOverrideFlag(NumericConstants.ONE);
                valueChangeLogic(dto, val, propertyId, uiContext);
                if (tableLogic != null) {
                    tableLogic.loadSetData(false);
                }
            } catch (Exception e) {
                LOGGER.error("Error in overrideListener :"+e);
            }
        }
    };

    protected void valueChangeLogic(AdjustmentDTO dto, Object val, Object propertyId, Component uiContext) {
        final ExecutorService service = ThreadPool.getInstance().getService();
        Double value = 0.0;
        boolean isEmptied = false;
        try {

            if (StringUtils.EMPTY.equals(val)) {
                isEmptied = true;
            }
            value = Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
        } catch (NumberFormatException e) {
            LOGGER.debug("User is supposed to give Double value " + e.getMessage());
            if (!isEmptied) {
                return;
            }
        }
        List input = new ArrayList();
        //Added this for GAL-5809
        input.add(selection.getProjectionMasterSid());

        input.add(dto.getBranditemmasterSid());
        input.add(isEmptied ? "NULL" : value.toString());
        input.add(dto.getContractSID() == 0 ? "%" : dto.getContractSID());
        input.add(dto.getCustomerSID() == 0 ? "%" : dto.getCustomerSID() );
        input.add(dto.getBrandSID() == 0 ? "%" : dto.getBrandSID());
        input.add(dto.getDeductionSID() == 0 ? "%" : dto.getDeductionSID());
        input.addAll(logic.getTableInput(selection.getSessionDTO()));
        checkLeave = true;
        service.submit(new UpdateOverride(input));
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

    AbstractPipelineLogic getLogic() {
        return logic;
    }
}
