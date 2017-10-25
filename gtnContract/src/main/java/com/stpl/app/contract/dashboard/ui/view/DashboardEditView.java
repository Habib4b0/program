package com.stpl.app.contract.dashboard.ui.view;

import com.stpl.app.contract.common.dto.SessionDTO;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;

import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.dashboard.dto.PriceScheduleDto;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import com.stpl.app.contract.dashboard.logic.DashBoardLogic;
import com.stpl.app.contract.dashboard.ui.form.ContractDashboardEditForm;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.stpl.app.contract.global.dto.RsItemDetailsDTO;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.global.logic.RebateScheduleLogic;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.CfpContract;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.CHFieldNameUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import java.text.ParseException;

/**
 * The Class DashboardEditView.
 */
public class DashboardEditView extends VerticalLayout implements View {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(DashboardEditView.class);
    /**
     * The Constant NAME.
     */
    public static final String NAME = "CpDashboardAdd";
    public static final String WORFLOW_NAME = "";
    /**
     * The dash board logic.
     */
    private final DashBoardLogic dashBoardLogic;
    /**
     * The cfp company dto.
     */
    private CFPCompanyDTO cfpCompanyDTO = new CFPCompanyDTO();
    /**
     * The rs item details dto.
     */
    private RsItemDetailsDTO rsItemDetailsDTO = new RsItemDetailsDTO();
    /**
     * The contract price info dto.
     */
    private VwContractPriceInfoDTO contractPriceInfoDTO = new VwContractPriceInfoDTO();
    /**
     * The contract master.
     */
    private ContractMasterDTO contractMaster = new ContractMasterDTO();
    private CustomFieldGroup cfpContractBinderedit = new CustomFieldGroup(new BeanItem<CFPCompanyDTO>(cfpCompanyDTO));
    /**
     * The contract master binder edit.
     */
    private CustomFieldGroup contractMasterBinderEdit = new CustomFieldGroup(new BeanItem<ContractMasterDTO>(contractMaster));
    /**
     * The alias master results beans.
     */
    private final BeanItemContainer<ContractAliasMasterDTO> aliasMasterResultsBeans = new BeanItemContainer<ContractAliasMasterDTO>(ContractAliasMasterDTO.class);
    /**
     * The available company result bean.
     */
    private final BeanItemContainer<CompanyMasterDTO> availableCompanyResultBean = new BeanItemContainer<CompanyMasterDTO>(CompanyMasterDTO.class);
    /**
     * The selected company result bean.
     */
    private final BeanItemContainer<CompanyMasterDTO> selectedCompanyResultBean = new BeanItemContainer<CompanyMasterDTO>(CompanyMasterDTO.class);
    /**
     * The cfp results bean.
     */
    private final BeanItemContainer<CFPCompanyDTO> cfpResultsBean = new BeanItemContainer<CFPCompanyDTO>(CFPCompanyDTO.class);

    private final BeanItemContainer<CFPCompanyDTO> saveContainer = new BeanItemContainer<CFPCompanyDTO>(CFPCompanyDTO.class);
    private final BeanItemContainer<TempPricingDTO> saveIFPContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);
    private final BeanItemContainer<TempPricingDTO> savePSContainer = new BeanItemContainer<TempPricingDTO>(TempPricingDTO.class);
    /**
     * The available item result bean.
     */
    private final BeanItemContainer<ItemMasterDTO> availableItemResultBean = new BeanItemContainer<ItemMasterDTO>(ItemMasterDTO.class);
    /**
     * The selected item result bean.
     */
    private final BeanItemContainer<ItemMasterDTO> selectedItemResultBean = new BeanItemContainer<ItemMasterDTO>(ItemMasterDTO.class);
    /**
     * The item details results bean.
     */
    private final BeanItemContainer<VwContractPriceInfoDTO> itemDetailsResultsBean = new BeanItemContainer<VwContractPriceInfoDTO>(VwContractPriceInfoDTO.class);
    /**
     * The rs details results bean.
     */
    private final BeanItemContainer<TempRebateDTO> rsDetailsResultsBean = new BeanItemContainer<TempRebateDTO>(TempRebateDTO.class);
    /**
     * The rebate binder.
     */
    private CustomFieldGroup rebateBinder = new CustomFieldGroup(new BeanItem<RsItemDetailsDTO>(rsItemDetailsDTO));

    private boolean isEditable;
    private Date tempDate;
    SessionDTO sessionDTO;

    /**
     * The priceScheduleMaster
     */
    private PriceScheduleDto priceScheduleMaster = new PriceScheduleDto();
    /**
     * The priceScheduleMaster binder edit.
     */
    private CustomFieldGroup pricingBinderEdit = new CustomFieldGroup(new BeanItem<PriceScheduleDto>(priceScheduleMaster));

    /**
     * Gets the cfp company dto.
     *
     * @return the cfp company dto
     */
    public CFPCompanyDTO getCfpCompanyDTO() {
        return cfpCompanyDTO;
    }

    /**
     * Sets the cfp company dto.
     *
     * @param cfpCompanyDTO the cfp company dto
     */
    public void setCfpCompanyDTO(final CFPCompanyDTO cfpCompanyDTO) {
        this.cfpCompanyDTO = cfpCompanyDTO;
    }

    /**
     * Gets the rs item details dto.
     *
     * @return the rs item details dto
     */
    public RsItemDetailsDTO getRsItemDetailsDTO() {
        return rsItemDetailsDTO;
    }

    /**
     * Sets the rs item details dto.
     *
     * @param rsItemDetailsDTO the rs item details dto
     */
    public void setRsItemDetailsDTO(final RsItemDetailsDTO rsItemDetailsDTO) {
        this.rsItemDetailsDTO = rsItemDetailsDTO;
    }

    /**
     * Gets the contract price info dto.
     *
     * @return the contract price info dto
     */
    public VwContractPriceInfoDTO getContractPriceInfoDTO() {
        return contractPriceInfoDTO;
    }

    /**
     * Sets the contract price info dto.
     *
     * @param contractPriceInfoDTO the contract price info dto
     */
    public void setContractPriceInfoDTO(final VwContractPriceInfoDTO contractPriceInfoDTO) {
        this.contractPriceInfoDTO = contractPriceInfoDTO;
    }

    /**
     * Gets the contract master.
     *
     * @return the contract master
     */
    public ContractMasterDTO getContractMaster() {
        return contractMaster;
    }

    /**
     * Sets the contract master.
     *
     * @param contractMaster the contract master
     */
    public void setContractMaster(final ContractMasterDTO contractMaster) {
        this.contractMaster = contractMaster;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public static String getName() {
        return NAME;
    }

    /**
     * Gets the dash board logic.
     *
     * @return the dash board logic
     */
    public DashBoardLogic getDashBoardLogic() {
        return dashBoardLogic;
    }

    /**
     * Gets the contract master binder edit.
     *
     * @return the contract master binder edit
     */
    public CustomFieldGroup getContractMasterBinderEdit() {
        return contractMasterBinderEdit;
    }

    /**
     * Gets the price schedule master binder edit.
     *
     * @return the contract master binder edit
     */
    public CustomFieldGroup getPricingBinderEdit() {
        return pricingBinderEdit;
    }

    public CustomFieldGroup getCfpContractBinderedit() {
        return cfpContractBinderedit;
    }

    public void setCfpContractBinderedit(CustomFieldGroup cfpContractBinderedit) {
        this.cfpContractBinderedit = cfpContractBinderedit;
    }

    /**
     * Gets the alias master results beans.
     *
     * @return the alias master results beans
     */
    public BeanItemContainer<ContractAliasMasterDTO> getAliasMasterResultsBeans() {
        return aliasMasterResultsBeans;
    }

    /**
     * Gets the available company result bean.
     *
     * @return the available company result bean
     */
    public BeanItemContainer<CompanyMasterDTO> getAvailableCompanyResultBean() {
        return availableCompanyResultBean;
    }

    /**
     * Gets the selected company result bean.
     *
     * @return the selected company result bean
     */
    public BeanItemContainer<CompanyMasterDTO> getSelectedCompanyResultBean() {
        return selectedCompanyResultBean;
    }

    /**
     * Gets the cfp results bean.
     *
     * @return the cfp results bean
     */
    public BeanItemContainer<CFPCompanyDTO> getCfpResultsBean() {
        return cfpResultsBean;
    }

    /**
     * Gets the available item result bean.
     *
     * @return the available item result bean
     */
    public BeanItemContainer<ItemMasterDTO> getAvailableItemResultBean() {
        return availableItemResultBean;
    }

    /**
     * Gets the selected item result bean.
     *
     * @return the selected item result bean
     */
    public BeanItemContainer<ItemMasterDTO> getSelectedItemResultBean() {
        return selectedItemResultBean;
    }

    /**
     * Gets the item details results bean.
     *
     * @return the item details results bean
     */
    public BeanItemContainer<VwContractPriceInfoDTO> getItemDetailsResultsBean() {
        return itemDetailsResultsBean;
    }

    /**
     * Gets the rs details results bean.
     *
     * @return the rs details results bean
     */
    public BeanItemContainer<TempRebateDTO> getRsDetailsResultsBean() {
        return rsDetailsResultsBean;
    }

    /**
     * Gets the rebate binder.
     *
     * @return the rebate binder
     */
    public CustomFieldGroup getRebateBinder() {
        return rebateBinder;
    }

    /**
     * Instantiates a new dashboard edit view.
     */
    public DashboardEditView(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        LOGGER.debug("Entering DashboardEditView ");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        this.sessionDTO = sessionDTO;
        dashBoardLogic = new DashBoardLogic(this.sessionDTO);
        LOGGER.debug("End of DashboardEditView ");
    }

    /**
     * This view is navigated to. This method is always called before the view
     * is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeEvent event) {
        try {
            LOGGER.debug("Entering  DashboardEditView enter method");
            this.removeAllComponents();
            contractMasterBinderEdit = new CustomFieldGroup(new BeanItem<ContractMasterDTO>(contractMaster));
            pricingBinderEdit = new CustomFieldGroup(new BeanItem<PriceScheduleDto>(priceScheduleMaster));
            cfpContractBinderedit = new CustomFieldGroup(new BeanItem<CFPCompanyDTO>(cfpCompanyDTO));

            rebateBinder = new CustomFieldGroup(new BeanItem<RsItemDetailsDTO>(rsItemDetailsDTO));
            isEditable = "Y".equals(sessionDTO.getEdit());

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            final int contractSystemId = (Integer) (sessionDTO.getContractSystemId());
            final int cfpContractSId = (Integer) (sessionDTO.getCfpSystemId());
            final int ifpSystemId = (Integer) (sessionDTO.getIfpSystemId());
            final int psSystemId = (Integer) (sessionDTO.getPsSystemId());
            final int rsSystemId = (Integer) (sessionDTO.getRsSystemId());
            tempDate = new Date();
            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
            sessionDTO.setSessionDate(fmt.format(tempDate));
            sessionDTO.setUiSessionId(fmtID.format(tempDate));
            aliasMasterResultsBeans.removeAllItems();
            availableCompanyResultBean.removeAllItems();
            selectedCompanyResultBean.removeAllItems();
            cfpResultsBean.removeAllItems();
            saveContainer.removeAllItems();
            saveIFPContainer.removeAllItems();
            savePSContainer.removeAllItems();
            availableItemResultBean.removeAllItems();
            selectedItemResultBean.removeAllItems();
            itemDetailsResultsBean.removeAllItems();
            rsDetailsResultsBean.removeAllItems();

            contractMaster = dashBoardLogic.getContractMasterById(contractSystemId);
            priceScheduleMaster = dashBoardLogic.getPriceScheduleMasterById(psSystemId);
            contractMasterBinderEdit.setItemDataSource(new BeanItem<ContractMasterDTO>(contractMaster));
            pricingBinderEdit.setItemDataSource(new BeanItem<PriceScheduleDto>(priceScheduleMaster));
            cfpCompanyDTO = dashBoardLogic.getCfpContract();
            cfpContractBinderedit.setItemDataSource(new BeanItem<CFPCompanyDTO>(cfpCompanyDTO));

            if (cfpContractSId != Constants.ZERO) {
                CfpContract cfpContract = CfpContractLocalServiceUtil.getCfpContract(cfpContractSId);
                new CFPSearchLogic(sessionDTO).loadTempCFP(cfpContract.getCfpContractSid(), contractSystemId);
            }
            new IfpLogic(sessionDTO).loadImtdItemPriceRebateDetails(contractSystemId, cfpContractSId, ifpSystemId, psSystemId, rsSystemId);
            if (rsSystemId != 0) {
                new RebateScheduleLogic(sessionDTO).loadFormulaToImtdRsdFr(Integer.valueOf(rsSystemId));
            }
            if (rsSystemId != Constants.ZERO) {
                LOGGER.debug(" Logic.getRsDetails(final int contractSystemId=" + contractSystemId + ", int cfpId="
                        + cfpContractSId + ",int ifpId=" + ifpSystemId + ",int psId"
                        + psSystemId + ", int rsId=" + rsSystemId + ") ");
                rsItemDetailsDTO = dashBoardLogic.getRsDetails(contractSystemId,
                        cfpContractSId, ifpSystemId, psSystemId, rsSystemId);
                rsDetailsResultsBean.removeAllItems();
            }
            aliasMasterResultsBeans.addAll(dashBoardLogic.getContractAliasMasterById(contractSystemId));
            rebateBinder.setItemDataSource(new BeanItem<RsItemDetailsDTO>(rsItemDetailsDTO));
            sessionDTO.setUserId(userId);
            addComponent(new ContractDashboardEditForm(contractMaster, contractMasterBinderEdit, cfpCompanyDTO, cfpContractBinderedit, aliasMasterResultsBeans, availableCompanyResultBean, selectedCompanyResultBean, cfpResultsBean, saveContainer,
                    availableItemResultBean, selectedItemResultBean, saveIFPContainer, savePSContainer, itemDetailsResultsBean, rsItemDetailsDTO, rebateBinder, rsDetailsResultsBean, isEditable, sessionDTO, pricingBinderEdit, priceScheduleMaster));
            contractMasterBinderEdit.getField(CHFieldNameUtils.term).setReadOnly(true);
            contractMasterBinderEdit.getField(CHFieldNameUtils.term).setEnabled(false);
            contractMasterBinderEdit.getField(CHFieldNameUtils.contractId).focus();
            contractMasterBinderEdit.setReadOnly(!isEditable);
            rebateBinder.setReadOnly(!isEditable);
            rebateBinder.getField("parentRebateScheduleName").setReadOnly(true);
            contractMasterBinderEdit.getField("internalNotes").setReadOnly(true);
            pricingBinderEdit.getField("priceScheduleId").setReadOnly(true);
            pricingBinderEdit.getField("priceScheduleNo").setReadOnly(false);
            pricingBinderEdit.getField("priceScheduleName").setReadOnly(false);
            pricingBinderEdit.getField("parentPriceScheduleName").setReadOnly(true);
            pricingBinderEdit.getField("parentPriceScheduleId").setReadOnly(true);
            pricingBinderEdit.getField("modifiedDate").setReadOnly(true);
            pricingBinderEdit.getField("createdDate").setReadOnly(true);
            pricingBinderEdit.getField("createdBy").setReadOnly(true);
            pricingBinderEdit.getField("modifiedBy").setReadOnly(true);
            rebateBinder.getField("rebateScheduleId").setReadOnly(true);
            rebateBinder.getField("rebateScheduleNo").setReadOnly(false);
            rebateBinder.getField("rebateScheduleName").setReadOnly(false);
            contractMasterBinderEdit.getField("contractId").setReadOnly(true);
            contractMasterBinderEdit.getField(Constants.CONTRACT_NO).setReadOnly(false);
            contractMasterBinderEdit.getField("contractName").setReadOnly(false);
            contractMasterBinderEdit.getField("internalNotes").setReadOnly(true);            
            String caption = String.valueOf(pricingBinderEdit.getField("priceScheduleDesignation").getValue());
            String parentcaption = String.valueOf(cfpContractBinderedit.getField("cfpDesignation").getValue());
            if ("child".equalsIgnoreCase(caption)) {
                pricingBinderEdit.getField("parentPriceScheduleName").setEnabled(true);
                pricingBinderEdit.getField("parentPriceScheduleId").setEnabled(true);
            } else {
                pricingBinderEdit.getField("parentPriceScheduleName").setEnabled(false);
                pricingBinderEdit.getField("parentPriceScheduleId").setEnabled(false);
            }

            if ("child".equalsIgnoreCase(parentcaption)) {
                cfpContractBinderedit.getField("parentCfp").setEnabled(true);
                cfpContractBinderedit.getField("parentCfpName").setEnabled(true);

            } else {
                cfpContractBinderedit.getField("parentCfp").setEnabled(false);
                cfpContractBinderedit.getField("parentCfpName").setEnabled(false);
            }
            if (!isEditable) {
                contractMasterBinderEdit.getField("companyLabel").setEnabled(false);
                contractMasterBinderEdit.getField("tradingPartnerName").setEnabled(false);
                cfpContractBinderedit.getField("companyFamilyPlanNo").setEnabled(false);
                cfpContractBinderedit.getField("companyFamilyPlanName").setEnabled(false);
                cfpContractBinderedit.getField("cfpStatus").setEnabled(false);
                cfpContractBinderedit.getField("cfpDesignation").setEnabled(false);
                cfpContractBinderedit.getField("cfpCategory").setEnabled(false);
                cfpContractBinderedit.getField("parentCfp").setEnabled(false);
                cfpContractBinderedit.getField("parentCfpName").setEnabled(false);
                cfpContractBinderedit.getField("salesInclusion").setEnabled(false);
                cfpContractBinderedit.getField("companyFamilyPlanStartDate").setEnabled(false);
                cfpContractBinderedit.getField("companyFamilyPlanEndDate").setEnabled(false);
                cfpContractBinderedit.getField("cfptype").setEnabled(false);
                cfpContractBinderedit.getField("cfptrade").setEnabled(false);
                contractMasterBinderEdit.getField(Constants.CONTRACT_NO).setEnabled(false);
                contractMasterBinderEdit.getField("contractName").setEnabled(false);
                rebateBinder.getField("parentRebateScheduleId").setEnabled(false);
                rebateBinder.getField("parentRebateScheduleName").setEnabled(false);
                rebateBinder.getField("calculationRule").setEnabled(false);
                rebateBinder.getField("evaluationRuleAssociation").setEnabled(false);
                rebateBinder.getField("rebateScheduleTransRefName").setEnabled(false);
                rebateBinder.getField("rebateScheduleTransRefNo").setEnabled(false);
                rebateBinder.getField("rebateScheduleNo").setEnabled(false);
                rebateBinder.getField("rebateScheduleName").setEnabled(false);
                pricingBinderEdit.getField("priceScheduleNo").setEnabled(false);
                pricingBinderEdit.getField("priceScheduleName").setEnabled(false);
            }
            LOGGER.debug("End of DashboardEditView enter method");

        } catch (SystemException e) {
            LOGGER.error(e);
        } catch (ParseException e) {
            LOGGER.error(e);
        }catch (PortalException e) {
            LOGGER.error(e);
        }
    }
}
