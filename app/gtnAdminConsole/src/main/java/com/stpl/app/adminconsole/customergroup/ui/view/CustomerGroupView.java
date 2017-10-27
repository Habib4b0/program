/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.customergroup.dto.CustomerDetailsDTO;
import com.stpl.app.adminconsole.customergroup.dto.CustomerGroupDTO;
import com.stpl.app.adminconsole.customergroup.ui.form.CustomerGroupInfo;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerGroupView.
 *
 * @author vishalakshi
 */
public class CustomerGroupView extends VerticalLayout implements View {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CustomerGroupView.class);
    /**
     * The Constant NAME.
     */
    public static final String NAME = "CompanyGroup";
    /**
     * The customer group dto.
     */
    private CustomerGroupDTO customerGroupDTO = new CustomerGroupDTO();
    /**
     * The customer dto.
     */
    private CustomerDetailsDTO customerDTO = new CustomerDetailsDTO();
    /**
     * The customer group binder.
     */
    private ErrorfulFieldGroup customerGroupBinder = new ErrorfulFieldGroup(new BeanItem<>(customerGroupDTO));
    /**
     * The customer binder.
     */
    private ErrorfulFieldGroup customerBinder = new ErrorfulFieldGroup(new BeanItem<>(customerDTO));
    /**
     * The selected results bean.
     */
    private final BeanItemContainer<CustomerDetailsDTO> selectedResultsBean = new BeanItemContainer<>(CustomerDetailsDTO.class);
    private final BeanItemContainer<CustomerDetailsDTO> availableResultsBean = new BeanItemContainer<>(CustomerDetailsDTO.class);
    /**
     * The customer group info.
     */
    private CustomerGroupInfo customerGroupInfo;

    SessionDTO sessionDTO;

    /**
     * Gets the customer group dto.
     *
     * @return the customer group dto
     */
    public CustomerGroupDTO getCustomerGroupDTO() {
        return customerGroupDTO;
    }

    /**
     * Sets the customer group dto.
     *
     * @param customerGroupDTO the customer group dto
     */
    public void setCustomerGroupDTO(final CustomerGroupDTO customerGroupDTO) {
        this.customerGroupDTO = customerGroupDTO;
    }

    /**
     * Gets the customer group binder.
     *
     * @return the customer group binder
     */
    public ErrorfulFieldGroup getCustomerGroupBinder() {
        return customerGroupBinder;
    }

    /**
     * Sets the customer group binder.
     *
     * @param customerGroupBinder the customer group binder
     */
    public void setCustomerGroupBinder(final ErrorfulFieldGroup customerGroupBinder) {
        this.customerGroupBinder = customerGroupBinder;
    }

    /**
     * Gets the customer binder.
     *
     * @return the customer binder
     */
    public ErrorfulFieldGroup getCustomerBinder() {
        return customerBinder;
    }

    /**
     * Sets the customer binder.
     *
     * @param customerBinder the customer binder
     */
    public void setCustomerBinder(final ErrorfulFieldGroup customerBinder) {
        this.customerBinder = customerBinder;
    }

    /**
     * Gets the customer dto.
     *
     * @return the customer dto
     */
    public CustomerDetailsDTO getCustomerDTO() {
        return customerDTO;
    }

    /**
     * Gets the selected results bean.
     *
     * @return the selected results bean
     */
    public BeanItemContainer<CustomerDetailsDTO> getSelectedResultsBean() {
        return selectedResultsBean;
    }

    /**
     * Gets the available results bean.
     *
     * @return the available results bean
     */
    public BeanItemContainer<CustomerDetailsDTO> getAvailableResultsBean() {
        return availableResultsBean;
    }

    /**
     * Gets the customer group info.
     *
     * @return the customer group info
     */
    public CustomerGroupInfo getCustomerGroupInfo() {
        return customerGroupInfo;
    }

    /**
     * Instantiates a new customer group view.
     *
     * @param sessionDTO
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public CustomerGroupView(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        this.sessionDTO = sessionDTO;
        customerGroupInfo = new CustomerGroupInfo(customerGroupDTO, customerGroupBinder, customerBinder, customerDTO, selectedResultsBean, availableResultsBean, sessionDTO);
        LOGGER.debug("enter CustomerGroupView constructor");
        setSpacing(true);
        addComponent(customerGroupInfo);
        setStyleName("bootstrap");
        LOGGER.debug("CustomerGroupView constructor Ended");
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        try {
            LOGGER.debug("enter started");
            Boolean flag = false;
            final int companyGroupSystemId = sessionDTO.getSystemId();
            this.removeAllComponents();
            customerGroupBinder = new ErrorfulFieldGroup(new BeanItem<>(customerGroupDTO));
            customerBinder = new ErrorfulFieldGroup(new BeanItem<>(customerDTO));
            customerDTO = new CustomerDetailsDTO();
            customerGroupDTO = new CustomerGroupDTO();
            customerGroupInfo = new CustomerGroupInfo(customerGroupDTO, customerGroupBinder, customerBinder, customerDTO, selectedResultsBean, availableResultsBean, sessionDTO);
            addComponent(customerGroupInfo);
            setStyleName("bootstrap");
            if (companyGroupSystemId == ConstantsUtils.ZERO_NUM) {
                customerGroupBinder = new ErrorfulFieldGroup(new BeanItem<>(customerGroupDTO));
                customerBinder = new ErrorfulFieldGroup(new BeanItem<>(customerDTO));
            } else {
                customerBinder = new ErrorfulFieldGroup(new BeanItem<>(customerDTO));
                availableResultsBean.removeAllItems();
                final String pageName = sessionDTO.getLogic();
                if (ConstantsUtils.LOWERCASE_EDIT.equalsIgnoreCase(pageName) || ConstantsUtils.VIEW.equalsIgnoreCase(pageName) || ConstantsUtils.LOWERCASE_COPY.equalsIgnoreCase(pageName)) {
                    flag = true;
                }
                customerGroupBinder.setItemDataSource(new BeanItem<>(customerGroupDTO));
            }
            customerGroupInfo.entry(flag);
            LOGGER.debug("enter Ended");
        } catch (SystemException e) {

            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {

            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
        } catch (Exception e) {

            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
        }

    }
}
