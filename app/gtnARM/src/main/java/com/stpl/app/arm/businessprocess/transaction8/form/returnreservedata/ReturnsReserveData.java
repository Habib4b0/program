/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.form.returnreservedata;

import com.stpl.app.arm.businessprocess.transaction8.dto.RRSelectionDTO;
import com.stpl.app.arm.businessprocess.transaction8.logic.RRDataLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author
 */
public class ReturnsReserveData extends VerticalLayout {

    @UiField("variables")
    private CustomMenuBar variables;

    @UiField("originalSaleLimiter")
    private PopupDateField originalSaleLimiter;

    @UiField("removeClosedBatches")
    private CheckBox removeClosedBatches;

    @UiField("excudeBasedOnLoeDate")
    private CheckBox excudeBasedOnLoeDate;

    @UiField("generate")
    private Button generate;

    private ReturnsReserveDataSearchResults searchResults;

    private CustomMenuBar.CustomMenuItem customMenuItem;

    private RRSelectionDTO selection;

    private RRDataLogic logic = new RRDataLogic();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ReturnsReserveData.CustomNotification notifier = new ReturnsReserveData.CustomNotification();

    public ReturnsReserveData(RRSelectionDTO selection) {
        this.selection = selection;
        init();
        configureWorkFlow();
    }

    private void init() {
        searchResults = new ReturnsReserveDataSearchResults(logic, selection);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/returnsReserve/returnsreservedata.xml"), this));
        searchResults.getResults();
        addComponent(searchResults);
        configureFields();
    }

    private void configureFields() {
        customMenuItem = variables.addItem(GlobalConstants.getSelectOne(), null);
        CommonUtils.loadCustomMenu(customMenuItem, ARMUtils.getReturnReserveDataHeaders(), ARMUtils.getReturnReserveDataColumns());
        removeClosedBatches.setCaption("Remove Closed Batches");
        excudeBasedOnLoeDate.setCaption("Excude Based On LOE Date");
        removeClosedBatches.setValue(true);
    }

    @UiHandler("reset")
    public void resetBtnLogic(Button.ClickEvent event) {
        notifier.setButtonName("reset");
        notifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID004());
    }

    public Focusable getDefaultFocusComponent() {
        return variables;
    }

    public class CustomNotification extends AbstractNotificationUtils {

        private String buttonName;

        public CustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName && "reset".equals(buttonName)) {
                originalSaleLimiter.setValue(null);
                removeClosedBatches.setValue(true);
                excudeBasedOnLoeDate.setValue(false);
                CommonUtils.unCheckMenuBarItem(customMenuItem);
            }

        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    @UiHandler("generate")
    public void generateBtnLogic(Button.ClickEvent event) {
        logger.debug("Inside generate ButtonClick Btn");
        try {
            setSelection();
            if (selection.isReturnsReserveDataGenerated()) {
                searchResults.setValueChangeAllowed(false);
                searchResults.generateLogic(selection);
                searchResults.setValueChangeAllowed(true);
            } else {
                CustomNotification.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_MsgId_002());
            }
        } catch (Exception e) {
            logger.error("error in generate :" + e);
        }
    }

    private void setSelection() {
        selection.setReturnReserveDataVariables(CommonUtils.getSelectedVariables(customMenuItem, true));
        selection.setOriginalSaleLimiter(originalSaleLimiter.getValue());
        selection.setOriginalSaleLimiterVal(selection.getOriginalSaleLimiter() != null ? ARMUtils.getInstance().getDbDate().format(selection.getOriginalSaleLimiter()) : StringUtils.EMPTY);
        selection.setRemoveClosedBatches(removeClosedBatches.getValue() ? NumericConstants.ONE : NumericConstants.ZERO);
        selection.setExcludeBasedOnLoeDate(excudeBasedOnLoeDate.getValue() ? NumericConstants.ONE : NumericConstants.ZERO);
        List input = new ArrayList<>(Arrays.asList(selection.getDataSelectionDTO().getProjectionId(), "A.RS_MODEL_SID,A.RS_ID+' ~ '+ A.RS_NAME AS COMBINATION ,''''+A.RS_NAME+'''' AS RS_NAME", "order by A.RS_ID+' ~ '+ A.RS_NAME"));
        List<Object[]> listSize = QueryUtils.getItemData(input, "loadDeductionValue", null);
        String deductionValue = StringUtils.EMPTY;
        for (Object[] objects : listSize) {
            deductionValue = deductionValue + ARMUtils.COMMA + objects[NumericConstants.TWO];
        }
        deductionValue = deductionValue.substring(1);
        selection.setReturnReserveDeductionValue(deductionValue);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean isGenerated() {
        return searchResults.isGenerated();
    }

    public void loadDetails() {
        try {
            StringBuilder variablesBuilder = new StringBuilder();
            variablesBuilder.append(VariableConstants.RETURN_RESERVE_DATA_VARIABLES_FIELD).append(ARMUtils.COMMA)
                    .append(VariableConstants.REMOVE_CLOSED_BATCHES_FIELD).append(ARMUtils.COMMA)
                    .append(VariableConstants.EXCLUDE_BASED_ON_LOE_DATE_FIELD).append(ARMUtils.COMMA)
                    .append(VariableConstants.ORIGINAL_SALE_LIMITER_VAL_FIELD);
            List<Object[]> list = CommonLogic.loadReturnReserve(selection.getDataSelectionDTO().getProjectionId(), variablesBuilder.toString());
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = list.get(i);
                if (VariableConstants.RETURN_RESERVE_DATA_VARIABLES.equals(String.valueOf(obj[0]))) {
                    String str1 = (String) obj[1];
                    String[] str2 = str1.split(",");
                    String str3;
                    for (String strings : str2) {
                        str3 = strings;
                        CommonUtils.checkMenuBarItemCaption(customMenuItem, str3);
                    }
                } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {

                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException ex) {

            logger.error(ex.getMessage());
        }
    }

    private void configureWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            try {
                loadDetails();
                originalSaleLimiter.setValue(selection.getOriginalSaleLimiterVal().isEmpty() ? null : new SimpleDateFormat("yyyy-MM-dd").parse(selection.getOriginalSaleLimiterVal()));
                removeClosedBatches.setValue(selection.getRemoveClosedBatches() == 1);
                excudeBasedOnLoeDate.setValue(selection.getExcludeBasedOnLoeDate() == 1);
                generateBtnLogic(null);
                configureFieldsOnViewMode();
            } catch (ParseException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    private void configureFieldsOnViewMode() {
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        originalSaleLimiter.setReadOnly(isView);
        removeClosedBatches.setReadOnly(isView);
        excudeBasedOnLoeDate.setReadOnly(isView);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
