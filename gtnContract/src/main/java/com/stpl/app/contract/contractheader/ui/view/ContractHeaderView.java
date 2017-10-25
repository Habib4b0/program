package com.stpl.app.contract.contractheader.ui.view;

import com.stpl.app.contract.common.dto.SessionDTO;
import org.jboss.logging.Logger;

import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.ui.form.ContractHeaderForm;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;


/**
 * Class holds the Add Form that is added to the UI.
 *
 * @author
 */
public class ContractHeaderView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "add";
    SessionDTO sessionDTO;
    /**
     * The results bean.
     */

    private final BeanItemContainer<ContractAliasMasterDTO> resultsBean = new BeanItemContainer<ContractAliasMasterDTO>(ContractAliasMasterDTO.class);
    /**
     * The contract master dto.
     */
    private ContractMasterDTO contractMasterDTO = new ContractMasterDTO();
    /**
     * The binder.
     */
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<ContractMasterDTO>(contractMasterDTO));
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractHeaderView.class);

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public CustomFieldGroup getBinder() {
        return binder;
    }

    /**
     * Sets the binder.
     *
     * @param binder the binder
     */
    public void setBinder(final CustomFieldGroup binder) {
        this.binder = binder;
    }

    /**
     * Gets the results bean.
     *
     * @return the results bean
     */
    public BeanItemContainer<ContractAliasMasterDTO> getResultsBean() {
        return resultsBean;
    }

    /**
     * Gets the contract master dto.
     *
     * @return the contract master dto
     */
    public ContractMasterDTO getContractMasterDTO() {
        return contractMasterDTO;
    }

    /**
     * Default constructor. Adds the Add form to this VerticalLayout.
     */
    public ContractHeaderView(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();        
        this.sessionDTO=sessionDTO;
    }

    /**
     * Method over ridden while implementing view.Removes all the components and
     * Adds the new form to this view.
     *
     * @param event - ViewChangeEvent
     */
    public void enter(final ViewChangeEvent event) {
        try {

            this.removeAllComponents();
            String mode = (String) sessionDTO.getMode();
            LOGGER.debug("Enters enter method with mode :" + mode);
            binder = new CustomFieldGroup(new BeanItem<ContractMasterDTO>(new ContractMasterDTO()));
            final ContractHeaderLogic contractHL = new ContractHeaderLogic(sessionDTO);
            if (Constants.ADD.equals(mode)) {
                resultsBean.removeAllItems();
                contractMasterDTO = new ContractMasterDTO();
                addComponent(new ContractHeaderForm(contractMasterDTO, binder, resultsBean, mode, sessionDTO));
                binder.getField("internalNotes").setReadOnly(true);
            }
            if (Constants.EDIT.equals(mode)) {
                final int systemId = (Integer) sessionDTO.getSystemId();
                LOGGER.debug("ContractsystemId=" + systemId);
                contractMasterDTO = contractHL.getContractMasterById(systemId);
                binder.setItemDataSource(new BeanItem<ContractMasterDTO>(contractMasterDTO));
                addComponent(new ContractHeaderForm(contractMasterDTO, binder, resultsBean, mode, sessionDTO));
                binder.setItemDataSource(new BeanItem<ContractMasterDTO>(contractMasterDTO));  
                binder.getField("internalNotes").setReadOnly(true);
                binder.getField("tradingPartnerName").setReadOnly(true);
                binder.getField("companyLabel").setReadOnly(true);
                resultsBean.removeAllItems();
                resultsBean.addAll(contractMasterDTO.getContracAliasMasterList());
            } else if (Constants.VIEW.equals(mode)) {
                final int contractSystemId = (Integer) sessionDTO.getSystemId();
                LOGGER.debug("contractSystemId=" + contractSystemId);
                contractMasterDTO = contractHL.getContractMasterById(contractSystemId);
                binder.setItemDataSource(new BeanItem<ContractMasterDTO>(contractMasterDTO));
                addComponent(new ContractHeaderForm(contractMasterDTO, binder, resultsBean, mode, sessionDTO));
                binder.setItemDataSource(new BeanItem<ContractMasterDTO>(contractMasterDTO));
                binder.setReadOnly(true);
                binder.setEnabled(false);
                resultsBean.removeAllItems();
                resultsBean.addAll(contractMasterDTO.getContracAliasMasterList());
            }        
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2011));
            LOGGER.error(ex);
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2011));
            LOGGER.error(ex);
        }
    }
}
