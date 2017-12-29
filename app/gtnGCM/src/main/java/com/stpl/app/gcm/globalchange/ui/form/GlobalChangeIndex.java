
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.globalchange.ui.form;

import com.stpl.app.gcm.copycontract.ui.form.CopyContractindex;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.discount.ui.form.RemoveDiscountIndex;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.form.ItemManagementIndex;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.promotetptocontract.form.PromoteTP;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.tp.ui.form.CompanyAddForm;
import com.stpl.app.gcm.tp.ui.form.CompanySearch;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class GlobalChangeIndex extends CustomComponent implements View {

    private static final Logger LOGGER = Logger.getLogger(GlobalChangeIndex.class);
    SessionDTO sessionDTO = new SessionDTO();
    public CustomFieldGroup promoteTpToChDtoBinder;
    public CustomFieldGroup globalChangeBinder;
    @UiField("layout")
    public VerticalLayout layout;
    @UiField("customerSelectRadio")
    public OptionGroup customerSelectRadio;
    @UiField("modeSelectRadio")
    public OptionGroup modeSelectRadio;
    @UiField("process")
    public ComboBox process;
    @UiField("updateType")
    public ComboBox updateType;
    String processName;
    String updateTypeVal;
    SelectionDTO selection = new SelectionDTO();
    boolean valueChange;

    public GlobalChangeIndex() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/globalChange.xml"), this));
        configureFields();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //empty
    }

    protected void configureFields() {
        try {
            process.focus();
            process.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            process.setNullSelectionAllowed(true);
            process.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            process.addItem(CUSTOMER_MANAGEMENT.getConstant());
            process.addItem(CONTRACT_MANAGEMENT.getConstant());
            process.addItem(ITEM_MANAGEMENT.getConstant());

            updateType.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
            updateType.setNullSelectionAllowed(true);
            updateType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());

            customerSelectRadio.setImmediate(true);
            customerSelectRadio.addItem(CUSTOMER_EXISTING.getConstant());
            customerSelectRadio.addItem(CUSTOMER_NEW.getConstant());
            customerSelectRadio.select(CUSTOMER_EXISTING.getConstant());
            customerSelectRadio.setEnabled(true);

            modeSelectRadio.setImmediate(true);
            modeSelectRadio.addItem(COMPANY_FAMILY_PLAN.getConstant());
            modeSelectRadio.addItem(CONTRACT.getConstant());
            modeSelectRadio.select(CONTRACT.getConstant());
            modeSelectRadio.setEnabled(true);

            List<String> forecastDetails = CommonLogic.getLatestApprovedProjection();
            if(forecastDetails.size()!=0){
            sessionDTO.setProjectionId(Integer.valueOf(forecastDetails.get(0)));
            sessionDTO.setForecastingType(forecastDetails.get(1));
            }
            modeSelectRadio.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {

                    if (valueChange) {
                        String processName = String.valueOf(process.getValue());
                        String updateTypeVal = String.valueOf(updateType.getValue());                       
                        if (Constants.ITEM_MANAGEMENT.equals(processName) && "Item Update".equals(updateTypeVal)) {
                            if (modeSelectRadio.getValue().equals(ITEM_FAMILY_PLAN.getConstant())) {
                                selection.setIsIFP(Boolean.TRUE);
                            } else {
                                selection.setIsIFP(Boolean.FALSE);
                            }
                        }
                    }
                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Update Type Logic
     *
     * @param event
     */
    @UiHandler("updateType")
    public void updateTypeLogic(Property.ValueChangeEvent event) {
        try {
            String processName = String.valueOf(process.getValue());
            String updateTypeVal = String.valueOf(updateType.getValue());
            sessionDTO = CommonUtils.attachSessionId(sessionDTO);
            sessionDTO.setModuleName(updateTypeVal);
            if ("Customer Management".equals(processName)) {
                try {
                    if (PROMOTE_TRADING_PARTNER.getConstant().equals(updateTypeVal)) {

                        layout.removeAllComponents();
                        layout.addComponent(new PromoteTP());
                        modeSelectRadio.removeAllItems();
                        modeSelectRadio.addItem(COMPANY_FAMILY_PLAN.getConstant());
                        modeSelectRadio.addItem(CONTRACT.getConstant());
                        modeSelectRadio.select(CONTRACT.getConstant());

                    } else if (TRADING_PARTNER_REMOVE.getConstant().equals(updateTypeVal) || ADD_TRADING_PARTNER.getConstant().equals(updateTypeVal)
                            || TRANSFER_TRADING_PARTNER.getConstant().equals(updateTypeVal) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateTypeVal)
                            || TRADING_PARTNER_UPDATE.getConstant().equals(updateTypeVal)) {
                        layout.removeAllComponents();

                        if (ADD_TRADING_PARTNER.getConstant().equals(updateTypeVal) && String.valueOf(customerSelectRadio.getValue()).equals(CUSTOMER_NEW.getConstant())) {
                            layout.addComponent(new CompanyAddForm());
                        } else {
                            layout.addComponent(new CompanySearch(updateTypeVal));
                        }

                        modeSelectRadio.removeAllItems();
                        modeSelectRadio.addItem(COMPANY_FAMILY_PLAN.getConstant());
                        if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateTypeVal) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateTypeVal)) {
                            modeSelectRadio.addItem(CUSTOMER_CONTRACT.getConstant());
                            modeSelectRadio.select(CUSTOMER_CONTRACT.getConstant());
                        } else {
                            modeSelectRadio.addItem(CONTRACT.getConstant());
                            modeSelectRadio.select(CONTRACT.getConstant());
                        }

                        modeSelectRadio.setItemEnabled(COMPANY_FAMILY_PLAN.getConstant(), false);
                        customerSelectRadio.select(CUSTOMER_EXISTING.getConstant());
                        customerSelectRadio.setItemEnabled(CUSTOMER_NEW.getConstant(), false);

                    } else if (REMOVE_DISCOUNT.getConstant().equals(updateTypeVal) || ADD_DISCOUNT.getConstant().equals(updateTypeVal)) {
                        layout.removeAllComponents();
                        layout.addComponent(new RemoveDiscountIndex(updateTypeVal, sessionDTO));
                        modeSelectRadio.removeAllItems();
                        modeSelectRadio.addItem(COMPANY_FAMILY_PLAN.getConstant());
                        modeSelectRadio.addItem(CUSTOMER_CONTRACT.getConstant());
                        modeSelectRadio.select(CUSTOMER_CONTRACT.getConstant());
                        modeSelectRadio.setItemEnabled(COMPANY_FAMILY_PLAN.getConstant(), false);

                        customerSelectRadio.removeAllItems();
                        customerSelectRadio.addItem(CUSTOMER_EXISTING.getConstant());
                        customerSelectRadio.addItem(CUSTOMER_NEW.getConstant());
                        customerSelectRadio.select(CUSTOMER_EXISTING.getConstant());
                        customerSelectRadio.setItemEnabled(CUSTOMER_NEW.getConstant(), false);

                    } else {
                        layout.removeAllComponents();
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            } else if (Constants.ITEM_MANAGEMENT.equals(processName)) {
                sessionDTO.setProcessName(Constants.ITEM_MANAGEMENT);
                if ("Item Update".equals(updateTypeVal)) {
                    selection.setButtonMode(ConstantsUtil.EDIT);
                } else if ("Item Add".equals(updateTypeVal)) {
                    selection.setButtonMode(ConstantsUtil.ADD);
                } else if ("Item Remove".equals(updateTypeVal)) {
                    selection.setButtonMode(ConstantsUtil.DELETE);
                } else if ("Item Transfer".equals(updateTypeVal)) {
                    selection.setButtonMode(ConstantsUtil.TRANSFER);
                } else if ("Projection Transfer".equals(updateTypeVal)) {
                    selection.setButtonMode(ConstantsUtil.PROJECTIONTRANSFER);
                }
                layout.removeAllComponents();
                valueChange = Boolean.FALSE;
                modeSelectRadio.removeAllItems();
                modeSelectRadio.addItem(ITEM_FAMILY_PLAN.getConstant());
                modeSelectRadio.addItem(ITEM_CONTRACT.getConstant());
                modeSelectRadio.select(ITEM_CONTRACT.getConstant());
                modeSelectRadio.setEnabled(Boolean.FALSE);
                valueChange = Boolean.TRUE;
                if (modeSelectRadio.getValue().equals(ITEM_FAMILY_PLAN.getConstant())) {
                    selection.setIsIFP(Boolean.TRUE);
                }
                selection.setSessionDTO(sessionDTO);
                layout.addComponent(new ItemManagementIndex(selection));
                customerSelectRadio.setItemEnabled(CUSTOMER_NEW.getConstant(), Boolean.FALSE);

            } else if (CONTRACT_MANAGEMENT.getConstant().equals(processName)) {
                if ("Copy Contract".equals(updateTypeVal)) {
                    layout.removeAllComponents();
                    layout.addComponent(new CopyContractindex());
                    customerSelectRadio.setEnabled(false);
                    modeSelectRadio.setEnabled(false);
                } else {
                    layout.removeAllComponents();
                }
            } else {
                layout.removeAllComponents();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @UiHandler("process")
    public void processDdlbLogic(Property.ValueChangeEvent event) {
        processName = String.valueOf(process.getValue());
        if (CUSTOMER_MANAGEMENT.getConstant().equals(processName)) {
            setUpdateTypeDdlbToDefault();
            updateType.addItem(ADD_TRADING_PARTNER.getConstant());
            updateType.addItem(TRANSFER_TRADING_PARTNER.getConstant());
            updateType.addItem(PROJECTION_DETAILS_TRANSFER.getConstant());
            updateType.addItem(TRADING_PARTNER_REMOVE.getConstant());
            updateType.addItem(TRADING_PARTNER_UPDATE.getConstant());
            updateType.addItem(PROMOTE_TRADING_PARTNER.getConstant());
            updateType.addItem(ADD_DISCOUNT.getConstant());
            updateType.addItem(REMOVE_DISCOUNT.getConstant());
        } else if (CONTRACT_MANAGEMENT.getConstant().equals(processName)) {
            setUpdateTypeDdlbToDefault();
            updateType.addItem(COPY_CONTRACT.getConstant());
        } else if (ITEM_MANAGEMENT.getConstant().equals(processName)) {
            setUpdateTypeDdlbToDefault();
            updateType.addItem(ITEM_UPDATE.getConstant());
            updateType.addItem("Item Add");
            updateType.addItem("Item Remove");
            updateType.addItem("Item Transfer");
            updateType.addItem("Projection Transfer");
        } else {
            setUpdateTypeDdlbToDefault();
        }
    }

    private void setUpdateTypeDdlbToDefault() {
        updateType.removeAllItems();
        updateType.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        updateType.setNullSelectionAllowed(true);
        updateType.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
    }

    @UiHandler("customerSelectRadio")
    public void massUpdateEnDisLogic(Property.ValueChangeEvent event) {
        String processName = String.valueOf(process.getValue());
        String updateTypeVal = String.valueOf(updateType.getValue());        
        if ("Customer Management".equals(processName)) {
            try {
                if (ADD_TRADING_PARTNER.getConstant().equals(updateTypeVal)) {
                    layout.removeAllComponents();

                    if (ADD_TRADING_PARTNER.getConstant().equals(updateTypeVal) && String.valueOf(customerSelectRadio.getValue()).equals(CUSTOMER_NEW.getConstant())) {
                        layout.addComponent(new CompanyAddForm());
                    } else {
                        layout.addComponent(new CompanySearch(updateTypeVal));
                    }
                    modeSelectRadio.removeAllItems();

                    if (TRANSFER_TRADING_PARTNER.getConstant().equals(updateTypeVal) || PROJECTION_DETAILS_TRANSFER.getConstant().equals(updateTypeVal)) {
                        modeSelectRadio.addItem(COMPANY_FAMILY_PLAN.getConstant());
                        modeSelectRadio.addItem(CUSTOMER_CONTRACT.getConstant());
                        modeSelectRadio.select(CUSTOMER_CONTRACT.getConstant());
                    } else {
                        modeSelectRadio.addItem(COMPANY_FAMILY_PLAN.getConstant());
                        modeSelectRadio.addItem(CONTRACT.getConstant());
                        modeSelectRadio.select(CONTRACT.getConstant());
                        modeSelectRadio.setItemEnabled(COMPANY_FAMILY_PLAN.getConstant(), false);
                    }

                    if (TRADING_PARTNER_REMOVE.getConstant().equals(updateTypeVal)) {
                        customerSelectRadio.select(CUSTOMER_EXISTING.getConstant());
                        customerSelectRadio.setItemEnabled(CUSTOMER_NEW.getConstant(), false);
                    } else {
                        customerSelectRadio.setItemEnabled(CUSTOMER_NEW.getConstant(), true);
                    }

                }

            } catch (Exception ex) {
                LOGGER.error(ex);
            }

        }
    }
}
