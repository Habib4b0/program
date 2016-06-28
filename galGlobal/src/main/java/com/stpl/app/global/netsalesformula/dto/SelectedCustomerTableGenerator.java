/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.dto;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.netsalesformula.logic.SalesLogic;
import com.stpl.app.global.netsalesformula.ui.form.NetSalesRuleLookUp;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import com.stpl.app.global.netsalesformula.ui.form.SalesBasis;

/**
 *
 * @author Asha
 */
public class SelectedCustomerTableGenerator extends DefaultFieldFactory {

    /**
     * Default serial version ID.
     */
    private static final long serialVersionUID = 1L;

    ExtPagedTable selectedCustomersTable;
    
    BeanItemContainer<SalesBasisDto> selectedCustomers;
        
    SessionDTO sessiondto;
    
    final SalesLogic logic = new SalesLogic();
    final NetSalesRuleLookupDto ruleDto = new NetSalesRuleLookupDto();   
    SalesBasis salesBasis;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SelectedCustomerTableGenerator.class.getName());

    public SelectedCustomerTableGenerator(final ExtPagedTable selectedCustomersTable, final BeanItemContainer<SalesBasisDto> selectedCustomers, final SessionDTO sessiondto, final SalesBasis salesBasis) {
        this.selectedCustomersTable = selectedCustomersTable;
        this.selectedCustomers = selectedCustomers;
        this.sessiondto = sessiondto;
        this.salesBasis = salesBasis;
    }

    /**
     * This method is used to set the components to the column in the Table
     * container.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field<?>
     */
    @Override
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
 
        try {
            final SalesBasisDto dto=(SalesBasisDto) itemId;
             boolean isEnabled=true;
            if (ConstantsUtils.VIEW.equalsIgnoreCase(sessiondto.getMode())) {
                isEnabled = false;
            }
                      
            if ("netSalesRuleNo".equals(propertyId)) {
                final CustomTextField netSalesRule = new CustomTextField();
                netSalesRule.setImmediate(true);
                netSalesRule.setValue(dto.getNetSalesRuleNo());
                netSalesRule.addStyleName("searchicon");

                netSalesRule.addClickListener(new CustomTextField.ClickListener() {
                    @Override
                    public void click(CustomTextField.ClickEvent event) {
                        try {
                           
                            final NetSalesRuleLookUp lookUp = new NetSalesRuleLookUp(netSalesRule, ruleDto,"Sales Basis");
                            UI.getCurrent().addWindow(lookUp);
                            lookUp.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(final Window.CloseEvent e) {
                                    if (!"".equals(lookUp.getDetailsDto().getRuleSystemId())) {
                                        logic.updateRuleToTempTable(lookUp.getDetailsDto().getRuleSystemId(), (SalesBasisDto) itemId, sessiondto.getUiSessionId());
                                        salesBasis.refreshLazyTable();
                                        selectedCustomersTable.setCurrentPage(selectedCustomersTable.getCurrentPage());
                                    }
                                }
                            });
                        } catch (Exception e) {
                            LOGGER.error(e);

                        }
                    }
                });
                netSalesRule.setEnabled(isEnabled);
                return netSalesRule;
            }
            else if ("netSalesRuleName".equals(propertyId)) {
 
                 final CustomTextField netSalesRuleName = new CustomTextField();
                  netSalesRuleName.setPrimaryStyleName("link-text-field");
                    netSalesRuleName.setImmediate(true);
            
                    netSalesRuleName.addClickListener(new CustomTextField.ClickListener() {
            
                        @Override
                        public void click(CustomTextField.ClickEvent event) {
                            NetSalesRuleLookUp ruleDetailsLookup = new NetSalesRuleLookUp(netSalesRuleName, ruleDto,"Sales Basis");
                            ruleDetailsLookup.ruleName.setValue(dto.getNetSalesRuleName());
                            ruleDetailsLookup.ruleNo.setValue(dto.getNetSalesRuleNo());
                            ruleDetailsLookup.ruleType.setValue(dto.getRuleType());
                            ruleDetailsLookup.ruleCategory.setValue(dto.getRuleCategory());
                            ruleDetailsLookup.configureSearchdata(dto.getCrdModelSid());
                            ruleDetailsLookup.disableComponents(false);
                            UI.getCurrent().addWindow(ruleDetailsLookup);
                            ruleDetailsLookup.addCloseListener(new Window.CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                }
                            });
                        
                        }
                    });
                return netSalesRuleName;
        }    
            else if ("marketType".equals(propertyId)) {
                return null;
            }
            else if ("availableCheck".equals(propertyId)) {
                final CheckBox check = new CheckBox();
                check.setValue(dto.isAvailableCheck());
                check.setEnabled(true);
                check.setCaption("");
                check.addValueChangeListener(new Property.ValueChangeListener() {
                    public void valueChange(Property.ValueChangeEvent event) {
                        LOGGER.info("check.getValue()"+check.getValue());
                        logic.updateTempTable(check.getValue(), (SalesBasisDto)itemId, sessiondto.getUiSessionId());
                    }
                });
                check.setEnabled(isEnabled);
                return check;
            }
            
        } catch (Exception exception) {
            LOGGER.error(exception);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing     
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);

        }
      
        
        final Field field = super.createField(container, itemId, propertyId, uiContext);
        field.setReadOnly(true);
        return field;
    }
}
