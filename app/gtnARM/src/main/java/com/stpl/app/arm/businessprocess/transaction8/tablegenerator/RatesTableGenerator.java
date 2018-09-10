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
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.app.utils.SetComparator;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sathya.Seelan
 */
public class RatesTableGenerator implements TableFieldFactory, LeaveCheckAble {

    protected final AbstractPipelineLogic logic;
    private final AbstractSelectionDTO selection;
    private static final Logger LOGGER = LoggerFactory.getLogger(RatesTableGenerator.class);

    private final DataFormatConverter CUR_ZERO = new DataFormatConverter(ARMConstants.getTwoDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
    private final DataFormatConverter CUR_THREE = new DataFormatConverter(ARMConstants.getThreeDecFormat(), DataFormatConverter.INDICATOR_PERCENT);
    private boolean checkLeave = false;
    private boolean restrictLeave = false;
    private final boolean isFieldRequired;
    private final Object[] visibleColumns;
    private AdjustmentTableLogic tableLogic = null;
    private boolean staticFlag = false;

    public RatesTableGenerator(AbstractPipelineLogic logic, AbstractSelectionDTO selection,
            boolean isFieldRequired, Object[] visibleColumns, AdjustmentTableLogic tableLogic, boolean staticFlag) {
        this.logic = logic;
        this.selection = selection;
        this.isFieldRequired = isFieldRequired;
        this.visibleColumns = CommonLogic.getInstance().getObjectArrayCloned(visibleColumns);
        this.tableLogic = tableLogic;
        this.staticFlag = staticFlag;
    }

    @Override
    public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        AdjustmentDTO dto = (AdjustmentDTO) itemId;
        if (logic.getCondition(dto, propertyId, selection) && isFieldRequired && Arrays.asList(visibleColumns).contains(propertyId) && !propertyId.toString().startsWith("total")) {
            List items = new ArrayList();
            items.add(itemId);
            items.add(propertyId);
            items.add(uiContext);
            final TextField override = new TextField();
            override.setData(items);
            override.addStyleName("align-right");
            override.setConverter(staticFlag ? CUR_ZERO : CUR_THREE);
            override.addFocusListener(new FocusListener() {
                @Override
                public void focus(FocusEvent event) {
                    override.addValueChangeListener(overrideListener);
                    override.removeFocusListener(this);
                }
            });
            if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
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
                int id = selection.getRateColumnList().get(NumericConstants.ZERO).indexOf(propertyId);
                int rsSid = id == -1 ? NumericConstants.ZERO : (Integer) selection.getRateColumnList().get(NumericConstants.TWO).get(id);
               if (selection.getRateLevelName().equalsIgnoreCase("Product")) {
                    String rsId = String.valueOf(rsSid);
                    if(propertyId.toString().contains("override")){
                      rsId = "%";   
                    }
                    selection.setRatesOverrideFlag(NumericConstants.ONE);
                    valueChangeLogic(dto, val,Boolean.FALSE,rsId);
                } else if (rsSid == dto.getDeductionSID() || staticFlag) {
                    selection.setRatesOverrideFlag(NumericConstants.ONE);
                    valueChangeLogic(dto, val, Boolean.TRUE,"0");
                }
                refreshTable(tableLogic);
            } catch (Exception e) {
                LOGGER.error("Error in overrideListener :", e);
            }
        }
    };

    protected void valueChangeLogic(AdjustmentDTO dto, Object val, Boolean flag,String rsSid) {
        final ExecutorService service = ThreadPool.getInstance().getService();
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

        input.add(dto.getBranditemmasterSid());
        input.add(isEmptied ? "NULL" : value.toString());
        getinputs(flag,dto,rsSid,input);
        checkLeave = true;
         if(flag){
            service.submit(new UpdateOverride(input));
        }else{
          service.submit(new UpdateOverrideLevelFilter(input));  
        }
    }
     private void getinputs(Boolean flag, AdjustmentDTO dto, String rsSid,List input) {
        if (flag) {
            input.add(dto.getContractSID() == 0 ? "%" : dto.getContractSID());
            input.add(dto.getCustomerSID() == 0 ? "%" : dto.getCustomerSID());
            input.add(dto.getBrandSID() == 0 ? "%" : dto.getBrandSID());
            input.add(dto.getDeductionSID() == 0 ? "%" : dto.getDeductionSID());
            input.addAll(logic.getTableInput(selection.getSessionDTO()));
            input.add(ARMUtils.getInstance().getSidValue(selection.getRateDeductionLevelName()));
        } else {
            if (rsSid.equals("%")) {
                input.addAll(logic.getTableInput(selection.getSessionDTO()));
            } else {
                input.add(rsSid);
                input.addAll(logic.getTableInput(selection.getSessionDTO()));
                input.add(ARMUtils.getInstance().getSidValue(selection.getRateDeductionLevelName()));
            }

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
     class UpdateOverrideLevelFilter implements Runnable {

        private List input;
        private boolean updateSuccess;

        public UpdateOverrideLevelFilter(List input) {
            this.input = CommonLogic.getInstance().getArrayListCloned(input);

        }

        @Override
        public void run() {
            updateSuccess = logic.updateOverrideLevelFilter(input);
        }

        public boolean isUpdateSuccess() {
            return updateSuccess;
        }

    }

    AbstractPipelineLogic getLogic() {
        return logic;
    }

    protected void refreshTableData(Set<String> finalHierarchyNo, int pgNo) {
        tableLogic.setHierarchyToRefresh(finalHierarchyNo);
        tableLogic.setCurrentPage(pgNo);
    }

    protected Set<String> getAllExpandedHierarchyNo(List<String> allLevels) {
        Set<String> finalHierarchyNo = new HashSet<>();
        for (String tableTreeLevelNo : allLevels) {
            finalHierarchyNo.add(tableTreeLevelNo);
        }
        return finalHierarchyNo;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public void refreshTable(AdjustmentTableLogic tableLogic) {
        if (tableLogic != null) {
            List<String> allLevels = tableLogic.getAllLevels();
            int pgNo = tableLogic.getCurrentPage();
            tableLogic.setRefresh(true);
            Set<String> hierarchySet = new SetComparator().compareList(getAllExpandedHierarchyNo(allLevels));
            refreshTableData(hierarchySet, pgNo);
            refreshTableData(hierarchySet, pgNo);
        }
    }
}
