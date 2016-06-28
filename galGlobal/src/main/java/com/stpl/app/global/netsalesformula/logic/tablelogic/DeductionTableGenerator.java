/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.logic.tablelogic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.netsalesformula.dto.DeductionDto;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.logic.DeductionsLogic;
import com.stpl.app.global.netsalesformula.ui.form.Deductions;
import com.stpl.app.global.netsalesformula.ui.form.NetSalesRuleLookUp;
import com.stpl.app.global.netsalesformula.utils.UIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;

/**
 *
 * @author karthikraja.k
 */
public class DeductionTableGenerator extends DefaultFieldFactory {

    /**
     * The company details save bean is used to save the company details before
     * performing any activity it will get the object form lazy container while
     * user trying to edit or changing any value in the table and it is useful
     * for saving in temp table
     */
    private final BeanItemContainer<DeductionDto> deductionResultBean;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DeductionTableGenerator.class);
    SessionDTO sessionDTO;
    ExtPagedTable table;
    NetSalesRuleLookupDto ruleDto = new NetSalesRuleLookupDto();
    final DeductionsLogic logic = new DeductionsLogic(sessionDTO);
    private String ruleName = StringUtils.EMPTY;
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    Deductions deductions;

    public DeductionTableGenerator(ExtPagedTable table, final BeanItemContainer<DeductionDto> deductionResultBean, final SessionDTO sessionDTO, Deductions deductions) {
        this.deductionResultBean = deductionResultBean;
        this.sessionDTO = sessionDTO;
        this.table = table;
        this.deductions = deductions;

    }

    /**
     * Creates the field based on PropertyId.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field<?>
     */
    @Override
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        final DeductionDto dedDTO = (DeductionDto) itemId;

        final NetSalesRuleLookUp formulaNameLookup = new NetSalesRuleLookUp(new CustomTextField(), new NetSalesRuleLookupDto(),"Deduction");
        final CustomTextField netSalesRuleName = new CustomTextField();

        boolean isEnabled = true;
        if (ConstantsUtils.VIEW.equalsIgnoreCase(sessionDTO.getMode())) {
            isEnabled = false;
        }


        if ("selectedCheck".equals(propertyId)) {

            final CheckBox checkbox = new CheckBox();
            checkbox.setReadOnly(false);
            checkbox.setValue(dedDTO.getSelectedCheck());
            checkbox.setImmediate(true);
            checkbox.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        logic.updateTempTable((boolean) event.getProperty().getValue(), (DeductionDto) itemId, sessionDTO.getUiSessionId());

                    } catch (Exception ex) {
                        LOGGER.error("Exception" + ex);
                    }
                }
            });
            checkbox.setEnabled(isEnabled);
            return checkbox;

        }


        if ("indicator".equals(propertyId)) {

            final ComboBox indicator = new ComboBox();

            UIUtils utils = new UIUtils();
            utils.loadIndicator(indicator, false);
            indicator.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    if (event.getProperty().getValue() != null && !StringUtils.EMPTY.equals(event.getProperty().getValue().toString())) {
                        String indicatorValue=String.valueOf(event.getProperty().getValue());
                        indicator.setValue(indicatorValue);
                          if (!ConstantsUtils.SELECT_ONE.equalsIgnoreCase(indicatorValue)) {
                            logic.manualUpdate(sessionDTO.getUiSessionId(), ConstantsUtils.INDICATOR, indicatorValue.equals("Add") ? "+" : "-", dedDTO);
                        }
                    }
                }
            });
            indicator.setEnabled(isEnabled);
            return indicator;
        }



        if (ConstantsUtils.NET_SALES_RULENO.equals(propertyId)) {
            final CustomTextField formulaNo = new CustomTextField();
            formulaNo.setImmediate(true);
            formulaNo.setValue(dedDTO.getNetSalesRuleNo());
            formulaNo.addStyleName("searchicon");
            final NetSalesRuleLookUp formulaLookup = new NetSalesRuleLookUp(formulaNo, ruleDto,"Deduction");
            formulaNo.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for formulaNo
                 */
                public void click(final CustomTextField.ClickEvent event) {

                    try {


                        UI.getCurrent().addWindow(formulaLookup);

                        formulaLookup.addCloseListener(new Window.CloseListener() {
                            @Override
                            public void windowClose(Window.CloseEvent e) {

                                final DeductionDto dedDTO = (DeductionDto) itemId;
                                if (!StringUtils.EMPTY.equals(formulaLookup.getDetailsDto().getRuleSystemId())) {
                                    logic.updateRuleToTempTable(formulaLookup.getDetailsDto().getRuleSystemId(), dedDTO, sessionDTO.getUiSessionId());

                                    List<Object> list = logic.getNetSalesRuleDetails(formulaNo.getValue());
                                    if (list != null) {

                                        for (int i = 0; i < list.size(); i++) {

                                            final Object[] obj = (Object[]) list.get(i);
                                            container.getContainerProperty(itemId, ConstantsUtils.NET_SALES_RULE_NAME).setValue(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);
                                            container.getContainerProperty(itemId, ConstantsUtils.NET_SALES_RULENO).setValue(obj[1] != null ? formulaNo.getValue() : StringUtils.EMPTY);
                                        }
                                    }
                                    formulaNo.setValue(formulaLookup.getDetailsDto().getRuleNo());
                                }

                            }
                        });


                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            formulaNo.setEnabled(isEnabled);
            return formulaNo;
        }

        if (ConstantsUtils.NET_SALES_RULE_NAME.equals(propertyId)) {

            netSalesRuleName.setPrimaryStyleName("link-text-field");
            netSalesRuleName.setValue(StringUtils.EMPTY);
            netSalesRuleName.setImmediate(true);
            List<Object> list = logic.getNetSalesRuleDetails( container.getContainerProperty(itemId, ConstantsUtils.NET_SALES_RULENO).getValue().toString());

            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    final Object[] obj = (Object[]) list.get(i);
                    formulaNameLookup.ruleName.setValue(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);
                    netSalesRuleName.setValue(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);
                    formulaNameLookup.ruleNo.setValue(obj[0] != null ? String.valueOf(obj[0]) : StringUtils.EMPTY);
                    formulaNameLookup.ruleType.setValue(((helperListUtil.getIdHelperDTOMap().get(obj[2] != null ? Integer.valueOf(String.valueOf(obj[2])) : 0))));
                    formulaNameLookup.ruleCategory.setValue((helperListUtil.getIdHelperDTOMap().get(obj[3] != null ? Integer.valueOf(String.valueOf(obj[3])) : 0)));
                    formulaNameLookup.configureSearchdata((obj[4] != null ? String.valueOf(obj[4]) : StringUtils.EMPTY));
                }
            }
            LOGGER.info("Entering NetSales Rule Name pop up upadate");
            formulaNameLookup.disableComponents(false);
            netSalesRuleName.addClickListener(new CustomTextField.ClickListener() {
                @Override
                public void click(CustomTextField.ClickEvent event) {

                    UI.getCurrent().addWindow(formulaNameLookup);
//                   
                    formulaNameLookup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                        }
                    });
                }
            });
            netSalesRuleName.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    LOGGER.info("Entering NetSales Rule Name value Change");
           
                    List<Object> list = logic.getNetSalesRuleDetails( container.getContainerProperty(itemId, ConstantsUtils.NET_SALES_RULENO).getValue().toString());

                    if (list != null) {

                        for (int i = 0; i < list.size(); i++) {

                            final Object[] obj = (Object[]) list.get(i);
                            formulaNameLookup.ruleName.setValue(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);
                            netSalesRuleName.setValue(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);
                            formulaNameLookup.ruleNo.setValue(obj[0] != null ? String.valueOf(obj[0]) : StringUtils.EMPTY);
                            formulaNameLookup.ruleType.setValue(((helperListUtil.getIdHelperDTOMap().get(obj[2] != null ? Integer.valueOf(String.valueOf(obj[2])) : 0))));
                            formulaNameLookup.ruleCategory.setValue((helperListUtil.getIdHelperDTOMap().get(obj[3] != null ? Integer.valueOf(String.valueOf(obj[3])) : 0)));
                            formulaNameLookup.configureSearchdata((obj[4] != null ? String.valueOf(obj[4]) : StringUtils.EMPTY));
                        }
                    }
                    LOGGER.info("Entering NetSales Rule Name pop up update");
                    formulaNameLookup.disableComponents(false);
                }
            });
            return netSalesRuleName;
        }

        return null;
    }
}
