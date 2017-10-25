/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author shrihariharan
 */
public class ItemDetailsGenerator implements ExtFilterGenerator {

    CommonUtils commonsUtil = new CommonUtils();
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        if ("tradingPartnerName".equals(propertyId)) {

            return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) value).getId()), false, false);
        }

        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {

        if (originatingField instanceof ComboBox) {

            if (originatingField.getValue() != null) {
                if ("priceType".equals(propertyId)) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                } else {
                    return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
                }
            } else {
                return null;
            }
        }

        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {

        ContractUtils tempUtil = new ContractUtils();
        if ("packageSize".equals(propertyId)) {
            ComboBox packageSize = new ComboBox();
            try {
                commonUtil.loadComboBox(packageSize, "PACKAGE_SIZE", true);

            } catch (SystemException ex) {
                 Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            return packageSize;
        }
        if ("primaryUom".equals(propertyId)) {
            ComboBox primaryUom = new ComboBox();
            try {
                commonUtil.loadComboBox(primaryUom, "UOM", true);

            } catch (SystemException ex) {
                 Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            return primaryUom;
        }
        if ("priceType".equals(propertyId)) {
            try {
                final ComboBox priceType = new ComboBox();
                List<HelperDTO> list = tempUtil.getFilterPriceType();
                commonsUtil.getNativeSelect(priceType, list);
                priceType.setImmediate(true);
                priceType.setNullSelectionAllowed(true);
                priceType.setNullSelectionItemId(Constants.ZEROSTRING);
                priceType.setPageLength(NumericConstants.SEVEN);
                return priceType;
            } catch (SystemException ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ("globalitemstatus".equals(propertyId)) {
            ComboBox itemStatus = new ComboBox();
            try {
                commonUtil.loadComboBox(itemStatus, UIUtils.STATUS, true);

            } catch (SystemException ex) {
                 Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            return itemStatus;

        }

        if ("priceToleranceType".equals(propertyId)) {
            try {
                final ComboBox priceToleranceType = new ComboBox();
                commonUtil.loadComboBox(priceToleranceType, ContractUtils.PRICE_TOLERANCE_TYPE, true);
                return priceToleranceType;
            } catch (SystemException ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ("priceToleranceInterval".equals(propertyId)) {
            try {
                final ComboBox priceToleranceInterval = new ComboBox();
                commonUtil.loadComboBox(priceToleranceInterval, ContractUtils.PRICE_TOLERANCE_INTERVAL, true);
                return priceToleranceInterval;
            } catch (SystemException ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ("priceToleranceFrequency".equals(propertyId)) {
            try {
                final ComboBox priceToleranceFrequency = new ComboBox();
                commonUtil.loadComboBox(priceToleranceFrequency, ContractUtils.PRICE_TOLERANCE_FRERQUENCY, true);
                return priceToleranceFrequency;
            } catch (SystemException ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ("attachedStatus".equals(propertyId)) {
            ComboBox attachedStatus = new ComboBox();
            try {
                commonUtil.loadComboBox(attachedStatus, UIUtils.STATUS, true);

            } catch (SystemException ex) {
                 Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(ItemDetailsGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            return attachedStatus;

        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

}
