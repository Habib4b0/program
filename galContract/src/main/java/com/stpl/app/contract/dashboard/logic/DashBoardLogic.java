package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.dto.AdditionalInfoDTO;
import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dao.impl.DashboardLogicDAOImpl;
import com.stpl.app.contract.dashboard.dto.PriceProtectionFormulaDTO;
import com.stpl.app.contract.dashboard.dto.PriceScheduleDto;
import com.stpl.app.contract.dashboard.dto.RebatePlanDTO;
import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.QueryUtil;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.stpl.app.contract.global.dto.RsItemDetailsDTO;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.global.logic.IfpLogic;
import static com.stpl.app.contract.global.logic.IfpLogic.DB_DATE;
import com.stpl.app.contract.global.logic.NotesTabLogic;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.ui.UDCIncrementCheck;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.CdrModel;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.model.ImtdItemPriceRebateDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.Udcs;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.service.CdrModelLocalServiceUtil;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.app.service.impl.CdrModelLocalServiceImpl;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.domain.contract.contractdashboard.DashboardDAO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.HelperUtils;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.search.ParseException;
import com.stpl.portal.model.User;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The Class DashBoardLogic.
 */
public class DashBoardLogic extends BeanItemContainer<ContractMaster> {

    /**
     * The dao.
     */
    private static final DashboardDAO dao = new DashboardLogicDAOImpl();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DashBoardLogic.class);
    /**
     * Object for udc check.
     */
    private final UDCIncrementCheck udcCheck = new UDCIncrementCheck();
    
    private final NotesTabLogic notesLogic = new NotesTabLogic();
    private final QueryUtil queryUtil = new QueryUtil();
    /**
     * Gets current date.
     */
    public final Date CURRENTDate = new Date();
    private static int formulaIdCount;
    SessionDTO sessionDTO;

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public DashboardDAO getDao() {
        return dao;
    }
    /**
     * Date Formatter
     */
    final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Gets the udc check.
     *
     * @return the udc check
     */
    public UDCIncrementCheck getUdcCheck() {
        return udcCheck;
    }

    /**
     * Gets the current date.
     *
     * @return the CURRENT date
     */
    public Date getCURRENTDate() {
        return CURRENTDate;
    }

    /**
     * The Constructor.
     */
    public DashBoardLogic() {
        super(ContractMaster.class);
    }
    
    public DashBoardLogic(final SessionDTO sessionDTO) {
        super(ContractMaster.class);
        this.sessionDTO = sessionDTO;
    }
    
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    /**
     * to search Contract Master
     *
     * @param searchItemForm
     * @return
     * @throws SystemException
     */
    @SuppressWarnings("unchecked")
    public List<ContractMasterDTO> searchContractMaster(final CustomFieldGroup searchItemForm) throws SystemException {
        LOGGER.info("Entering searchContractMaster method");
        
        List<ContractMasterDTO> searchList;
        String contractId;
        String contractNo;
        String contractName;
        String contractType;
        String contractStatus;
        String tradeClass;
        
        if (searchItemForm.getField(CommonUtils.CONTRACT_ID) == null || searchItemForm.getField(CommonUtils.CONTRACT_ID).getValue().toString() == null) {
            contractId = HelperUtils.EMPTY;
        } else {
            contractId = searchItemForm.getField(CommonUtils.CONTRACT_ID).getValue().toString();
        }
        if (searchItemForm.getField(CommonUtils.CONTRACT_NO) == null || searchItemForm.getField(CommonUtils.CONTRACT_NO).getValue().toString() == null) {
            contractNo = HelperUtils.EMPTY;
        } else {
            contractNo = searchItemForm.getField(CommonUtils.CONTRACT_NO).getValue().toString();
        }
        if (searchItemForm.getField(CommonUtils.CONTRACT_NAME) == null || searchItemForm.getField(CommonUtils.CONTRACT_NAME).getValue().toString() == null) {
            contractName = HelperUtils.EMPTY;
        } else {
            contractName = searchItemForm.getField(CommonUtils.CONTRACT_NAME).getValue().toString();
        }
        if (searchItemForm.getField(CommonUtils.CONTRACT_TYPE).getValue() == null || searchItemForm.getField(CommonUtils.CONTRACT_TYPE).getValue().toString() == null) {
            contractType = HelperUtils.EMPTY;
        } else {
            contractType = searchItemForm.getField(CommonUtils.CONTRACT_TYPE).getValue().toString();
        }
        if (searchItemForm.getField(CommonUtils.CONTRACT_STATUS).getValue() == null || searchItemForm.getField(CommonUtils.CONTRACT_STATUS).getValue().toString() == null) {
            contractStatus = HelperUtils.EMPTY;
        } else {
            contractStatus = searchItemForm.getField(CommonUtils.CONTRACT_STATUS).getValue().toString();
        }
        if (searchItemForm.getField(CommonUtils.TRADE_CLASS) == null || searchItemForm.getField(CommonUtils.TRADE_CLASS).getValue().toString() == null) {
            tradeClass = HelperUtils.EMPTY;
        } else {
            tradeClass = searchItemForm.getField(CommonUtils.TRADE_CLASS).getValue().toString();
        }
        final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
        
        if (StringUtils.isNotBlank(contractId)) {
            contractId = contractId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_ID, contractId));
        }
        if (StringUtils.isNotBlank(contractNo)) {
            contractNo = contractNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_NO, contractNo));
        }
        if (StringUtils.isNotBlank(contractName)) {
            contractName = contractName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_NAME, contractName));
        }
        if (StringUtils.isNotBlank(contractType)) {
            contractType = contractType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_TYPE, contractType));
        }
        if (StringUtils.isNotBlank(contractStatus)) {
            contractStatus = contractStatus.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.CONTRACT_STATUS, contractStatus));
        }
        if (StringUtils.isNotBlank(tradeClass)) {
            tradeClass = tradeClass.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            itemDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.TRADE_CLASS, tradeClass));
        }
        
        final List<ContractMaster> resultList = dao.contractMasterDynamicQuery(itemDynamicQuery);
        
        searchList = getCustomizedSearchFormFromModel(resultList);
        LOGGER.info("End of searchContractMaster method");
        
        return searchList;
    }

    /**
     * Method used for Gets the customized search form from model.
     *
     * @param list the list
     * @return the customized search form from model
     */
    public List<ContractMasterDTO> getCustomizedSearchFormFromModel(final List<ContractMaster> list) {
        LOGGER.info("Entering getCustomizedSearchFormFromModel method");
        
        final List<ContractMasterDTO> searchItemList = new ArrayList<ContractMasterDTO>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final ContractMasterDTO searchItemForm = new ContractMasterDTO();
                final ContractMaster obj = (ContractMaster) list.get(i);
                searchItemForm.setContractSystemId(obj.getContractMasterSid());
                searchItemForm.setContractId(obj.getContractId());
                searchItemForm.setContractNo(obj.getContractNo());
                searchItemForm.setContractName(obj.getContractName());
                searchItemForm.setContractType(helperListUtil.getIdHelperDTOMap().get(obj.getContractType()));
                searchItemForm.setContractStatus(helperListUtil.getIdHelperDTOMap().get(obj.getContractStatus()));
                searchItemForm.setTradeClass(helperListUtil.getIdHelperDTOMap().get(obj.getContractTradeClass()));
                searchItemForm.setStartDate(obj.getStartDate());
                searchItemForm.setEndDate(obj.getEndDate());
                searchItemForm.setRecordLockStatus(String.valueOf(obj.getRecordLockStatus()));
                searchItemList.add(searchItemForm);
            }
        }
        LOGGER.info("End of getCustomizedSearchFormFromModel method");
        return searchItemList;
    }

    /**
     * Method used for Save contract alias master list.
     *
     * @param aliasList the alias list
     * @param result the result
     * @throws SystemException
     */
    public void saveContractAliasMasterList(final List<ContractAliasMasterDTO> aliasList, final ContractMaster result) throws SystemException, java.text.ParseException {
        LOGGER.info("Entering saveContractAliasMasterList method");
        if (aliasList != null) {
            for (int i = 0; i < aliasList.size(); i++) {
                final ContractAliasMasterDTO aliasForm = aliasList.get(i);
                final ContractAliasMaster alias = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);
                
                alias.setContractMasterSid(result.getContractMasterSid());
                
                alias.setTpCompanyMasterSid(aliasForm.getTradingPartner());
                
                alias.setContractAliasNo(aliasForm.getContractAliasNo());
                
                alias.setContractAliasName(aliasForm.getContractAliasName());
                
                alias.setContractAliasType(((HelperDTO) (aliasForm.getContractAliasType())).getId());
                if (StringUtils.isNotBlank(aliasForm.getStartDate())) {
                    alias.setStartDate(dateFormat.parse(aliasForm.getStartDate()));
                }
                if (StringUtils.isNotBlank(aliasForm.getEndDate())) {
                    alias.setEndDate(dateFormat.parse(aliasForm.getEndDate()));
                }
                alias.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
                alias.setCreatedDate(new Date());
                alias.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
                alias.setModifiedDate(new Date());
                alias.setSource("GTN");
                final ContractAliasMaster udcIncCheck = dao.addContractAliasMaster(alias);
                
                if (udcIncCheck.getContractAliasType() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(udcIncCheck.getContractAliasType());
                    udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_ALIAS_TYPE);
                }
            }
        }
        LOGGER.info("End of saveContractAliasMasterList method");
        
    }

    /**
     * to get Contract Master bu Id
     *
     * @param idValue
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public ContractMasterDTO getContractMasterById(final int idValue) throws SystemException, PortalException, java.text.ParseException {
        LOGGER.info("Entering getContractMasterById method");
        
        final ContractMasterDTO contractMasterDTO = new ContractMasterDTO();
        final ContractMaster contractMaster = dao.getContractMaster(idValue);
        contractMasterDTO.setContractSystemId(idValue);
        contractMasterDTO.setContractId(contractMaster.getContractId());
        contractMasterDTO.setContractNo(contractMaster.getContractNo());
        contractMasterDTO.setContractName(contractMaster.getContractName());
        contractMasterDTO.setContractType(helperListUtil.getIdHelperDTOMap().get(contractMaster.getContractType()));
        contractMasterDTO.setContractStatus(helperListUtil.getIdHelperDTOMap().get(contractMaster.getContractStatus()));
        contractMasterDTO.setTradeClass(helperListUtil.getIdHelperDTOMap().get(contractMaster.getContractTradeClass()));
        contractMasterDTO.setStartDate(contractMaster.getStartDate());
        contractMasterDTO.setEndDate(contractMaster.getEndDate());
        contractMasterDTO.setTerm(String.valueOf(contractMaster.getTerm()));
        contractMasterDTO.setRenegotiationStartDate(contractMaster.getRenegotiationStartDate());
        contractMasterDTO.setRenegotiationEndDate(contractMaster.getRenegotiationEndDate());
        contractMasterDTO.setDocType(helperListUtil.getIdHelperDTOMap().get(contractMaster.getDocumentType()));
        contractMasterDTO.setPriceprotectionStartDate(contractMaster.getPriceprotectionStartDate());
        contractMasterDTO.setPriceprotectionEndDate(contractMaster.getPriceprotectionEndDate());
        contractMasterDTO.setDocClass(helperListUtil.getIdHelperDTOMap().get(contractMaster.getDocumentClass()));
        contractMasterDTO.setInternalNotes(contractMaster.getInternalNotes());
        if (contractMaster.getBunitCompanyMasterSid() != null && !StringUtils.isBlank(contractMaster.getBunitCompanyMasterSid())) {
            
            final CompanyMaster compName = dao.getCompanyMaster(Integer.valueOf(contractMaster.getBunitCompanyMasterSid()));
            contractMasterDTO.setCompanyName(Integer.valueOf(contractMaster.getBunitCompanyMasterSid()));
            contractMasterDTO.setCompanyLabel(compName.getCompanyName());
        }
        if (contractMaster.getContHoldCompanyMasterSid() != null && !StringUtils.isBlank(contractMaster.getContHoldCompanyMasterSid())) {
            contractMasterDTO.setTradingPartnerSystemId(Integer.valueOf(contractMaster.getContHoldCompanyMasterSid()));
        }
        if (contractMasterDTO.getTradingPartnerSystemId() != ContractUtils.ZERO) {
            final CompanyMaster forName = dao.getCompanyMaster(Integer.valueOf(contractMaster.getContHoldCompanyMasterSid()));
            contractMasterDTO.setTradingPartnerName(forName.getCompanyName());
        }
        contractMasterDTO.setRecordLockStatus(String.valueOf(contractMaster.getRecordLockStatus()));
        contractMasterDTO.setCreatedBy(String.valueOf(contractMaster.getCreatedBy()));
        contractMasterDTO.setCreatedDate(contractMaster.getCreatedDate());
        if (contractMaster.getManfCompanyMasterSid() != null && !StringUtils.isBlank(contractMaster.getManfCompanyMasterSid())) {
            contractMasterDTO.setCompanySystemId(new CFPSearchLogic().getCompanyId(Integer.valueOf(contractMaster.getManfCompanyMasterSid())));
        } else {
            contractMasterDTO.setCompanySystemId(new HelperDTO(Constants.SELECT_ONE));
        }
        contractMasterDTO.setInsideOwner(StringUtils.isEmpty(contractMaster.getInsideOwner()) ? StringUtils.EMPTY : contractMaster.getInsideOwner());
        contractMasterDTO.setInsidePhone(StringUtils.isEmpty(contractMaster.getInsidePhone()) ? StringUtils.EMPTY : contractMaster.getInsidePhone());
        contractMasterDTO.setInsideAuthor(StringUtils.isEmpty(contractMaster.getInsideAuthor()) ? StringUtils.EMPTY : contractMaster.getInsideAuthor());
        contractMasterDTO.setInsideAdditional(StringUtils.isEmpty(contractMaster.getInsideAdditionalName()) ? StringUtils.EMPTY : contractMaster.getInsideAdditionalName());
        contractMasterDTO.setInsideAdditionalName(StringUtils.isEmpty(contractMaster.getInsideAdditionalName()) ? StringUtils.EMPTY : contractMaster.getInsideAdditionalName());
        contractMasterDTO.setInsideAdditionalPhone(StringUtils.isEmpty(contractMaster.getInsideAdditionalPhone()) ? StringUtils.EMPTY : contractMaster.getInsideAdditionalPhone());
        contractMasterDTO.setOutsideOwner(StringUtils.isEmpty(contractMaster.getOutsideOwner()) ? StringUtils.EMPTY : contractMaster.getOutsideOwner());
        contractMasterDTO.setOutsidePhone(StringUtils.isEmpty(contractMaster.getOutsidePhone()) ? StringUtils.EMPTY : contractMaster.getOutsidePhone());
        contractMasterDTO.setOutsideAuthor(StringUtils.isEmpty(contractMaster.getOutsideAuthor()) ? StringUtils.EMPTY : contractMaster.getOutsideAuthor());
        contractMasterDTO.setOutsideAdditional(StringUtils.isEmpty(contractMaster.getOutsideAdditional()) ? StringUtils.EMPTY : contractMaster.getOutsideAdditional());
        contractMasterDTO.setOutsideAdditionalName(StringUtils.isEmpty(contractMaster.getOutsideAdditionalName()) ? StringUtils.EMPTY : contractMaster.getOutsideAdditionalName());
        contractMasterDTO.setOutsideAdditionalPhone(StringUtils.isEmpty(contractMaster.getOutsideAdditionalPhone()) ? StringUtils.EMPTY : contractMaster.getOutsideAdditionalPhone());
        if (!"null".equals(String.valueOf(contractMaster.getAdvanceNoticeDays())) && !"0.0".equals(String.valueOf(contractMaster.getAdvanceNoticeDays()))) {
            contractMasterDTO.setAdvanceNoticeDays(String.valueOf(contractMaster.getAdvanceNoticeDays()));
            //  contractMasterDTO.setAdvanceNoticeDays(Double.isNaN(contractMaster.getAdvanceNoticeDays()) ? 0.0 : contractMaster.getAdvanceNoticeDays());
        } else {
            contractMasterDTO.setAdvanceNoticeDays("0");
        }
        contractMasterDTO.setAffiliatedContractInfo(StringUtils.isEmpty(contractMaster.getAffiliatedContractInfo()) ? StringUtils.EMPTY : contractMaster.getAffiliatedContractInfo());
        contractMasterDTO.setShippingTerms(StringUtils.isEmpty(contractMaster.getShippingTerms()) ? StringUtils.EMPTY : contractMaster.getShippingTerms());
        contractMasterDTO.setProposedStartDate(contractMaster.getProposalStartDate() == null ? null : dateFormat.parse(dateFormat.format(contractMaster.getProposalStartDate())));
        contractMasterDTO.setProposedEndDate(contractMaster.getProposalEndDate() == null ? null : dateFormat.parse(dateFormat.format(contractMaster.getProposalEndDate())));
        contractMasterDTO.setOriginalStartDate(contractMaster.getOriginalStartDate() == null ? null : dateFormat.parse(dateFormat.format(contractMaster.getOriginalStartDate())));
        contractMasterDTO.setOriginalEndDate(contractMaster.getOriginalEndDate() == null ? null : dateFormat.parse(dateFormat.format(contractMaster.getOriginalEndDate())));
        if (contractMaster.getAwardStatus() != 0) {
            contractMasterDTO.setAwardStatus(helperListUtil.getIdHelperDTOMap().get(contractMaster.getAwardStatus()));
        }
        contractMasterDTO.setLastUpdatedDate(contractMaster.getLastUpdatedDate() == null ? null : dateFormat.parse(dateFormat.format(contractMaster.getLastUpdatedDate())));
        contractMasterDTO.setPriceEscalationClause(StringUtils.isEmpty(contractMaster.getPriceEscalationClause()) ? StringUtils.EMPTY : contractMaster.getPriceEscalationClause());
        contractMasterDTO.setExemptFromLowPrice(StringUtils.isEmpty(contractMaster.getExemptFromLowPrice()) ? StringUtils.EMPTY : contractMaster.getExemptFromLowPrice());
        if (!validation(String.valueOf(contractMaster.getPriceResetIndicator())) && Integer.valueOf(contractMaster.getPriceResetIndicator()) != 0) {
            contractMasterDTO.setPriceResetIndicator(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(contractMaster.getPriceResetIndicator())));
        }
        contractMasterDTO.setCancellationClause(StringUtils.isEmpty(contractMaster.getCancellationClause()) ? StringUtils.EMPTY : contractMaster.getCancellationClause());
        contractMasterDTO.setMostFavoredNation(StringUtils.isEmpty(contractMaster.getMostFavoredNation()) ? StringUtils.EMPTY : contractMaster.getMostFavoredNation());
        contractMasterDTO.setCategory(StringUtils.isEmpty(contractMaster.getCategory()) ? StringUtils.EMPTY : contractMaster.getCategory());
        contractMasterDTO.setCurrency(StringUtils.isEmpty(contractMaster.getCurrency()) ? StringUtils.EMPTY : contractMaster.getCurrency());
        contractMasterDTO.setMinimumOrder(StringUtils.isEmpty(contractMaster.getMinimumOrder()) ? StringUtils.EMPTY : contractMaster.getMinimumOrder());
        if (contractMaster.getPaymentTerms() != 0) {
            contractMasterDTO.setPaymentTerms(helperListUtil.getIdHelperDTOMap().get(contractMaster.getPaymentTerms()));
        }
        contractMasterDTO.setModifiedDate(contractMaster.getModifiedDate());
        
        LOGGER.info("End of getContractMasterById method");
        return contractMasterDTO;
    }

    /**
     * to get Contract Master bu Id
     *
     * @param idValue
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public PriceScheduleDto getPriceScheduleMasterById(final int idValue) throws SystemException, PortalException, java.text.ParseException {
        LOGGER.info("Entering getPriceScheduleMasterById method");
        final PriceScheduleDto psDTO = new PriceScheduleDto();
        if (idValue != 0) {
            Map<Integer, String> userMap = StplSecurity.getUserName();
            PsContract PScontract = PsContractLocalServiceUtil.getPsContract(idValue);
            //  PsModel priceSchedule = PsModelLocalServiceUtil.getPsModel(idValue);
            PsModel priceSchedule = PsModelLocalServiceUtil.getPsModel(PScontract.getPsModelSid());
            psDTO.setPriceScheduleSystemId(String.valueOf(idValue));
            psDTO.setPriceScheduleId(priceSchedule.getPsId());
            psDTO.setPriceScheduleNo(priceSchedule.getPsNo());
            psDTO.setPriceScheduleName(PScontract.getPsName());
            psDTO.setPriceScheduleStatus(helperListUtil.getIdHelperDTOMap().get(PScontract.getPsStatus()));
            psDTO.setPriceScheduleStartDate(PScontract.getPsStartDate());
            psDTO.setPriceScheduleEndDate(PScontract.getPsEndDate());
            if (!PScontract.getPsDesignation().isEmpty()) {
                psDTO.setPriceScheduleDesignation(helperListUtil.getIdHelperDTOMap().get(Integer.parseInt(PScontract.getPsDesignation())));
            }
            psDTO.setParentPriceScheduleId(PScontract.getParentPsId());
            psDTO.setParentPriceScheduleName(PScontract.getParentPsName());
            psDTO.setPriceScheduleType(helperListUtil.getIdHelperDTOMap().get(PScontract.getPsType()));
            psDTO.setPriceScheduleCategory(helperListUtil.getIdHelperDTOMap().get(PScontract.getPsCategory()));
            psDTO.setTradeClass(helperListUtil.getIdHelperDTOMap().get(PScontract.getPsTradeClass()));
            psDTO.setCreatedBy(userMap.get(PScontract.getCreatedBy()) == null ? StringUtils.EMPTY : userMap.get(PScontract.getCreatedBy()));
            psDTO.setCreatedDate(PScontract.getCreatedDate());
            psDTO.setModifiedDate(PScontract.getModifiedDate());
            psDTO.setModifiedBy(userMap.get(PScontract.getModifiedBy()) == null ? StringUtils.EMPTY : userMap.get(PScontract.getModifiedBy()));
        }
        LOGGER.info("End of getPriceScheduleMasterById method");
        return psDTO;
    }

    /**
     * to get Contract Alias Master By Id
     *
     * @param idValue
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public List<ContractAliasMasterDTO> getContractAliasMasterById(final int idValue) throws SystemException, PortalException {
        LOGGER.info("Entering getContractAliasMasterById method");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        final List<ContractAliasMasterDTO> aliasListDTO = new ArrayList<ContractAliasMasterDTO>();
        final List<ContractAliasMaster> aliasList = dao.findByContractSystemId(idValue);
        ContractAliasMasterDTO aliasMasterDTO;
        for (int i = 0; i < aliasList.size(); i++) {
            aliasMasterDTO = new ContractAliasMasterDTO();
            final ContractAliasMaster contractAM = aliasList.get(i);
            
            aliasMasterDTO.setContractSystemId(contractAM.getContractMasterSid());
            
            aliasMasterDTO.setContractAliasSystemId(contractAM.getContractAliasMasterSid());
            
            aliasMasterDTO.setContractAliasName(contractAM.getContractAliasName());
            
            aliasMasterDTO.setContractAliasNo(contractAM.getContractAliasNo());
            
            aliasMasterDTO.setTradingPartner(contractAM.getTpCompanyMasterSid());
            if (aliasMasterDTO.getTradingPartner() != ContractUtils.ZERO) {
                
                final CompanyMaster forId = dao.getCompanyMaster(aliasMasterDTO.getTradingPartner());
                
                aliasMasterDTO.setTpCompanyMasterSid(forId.getCompanyName());
                aliasMasterDTO.setTradingName(forId.getCompanyName());
            }
            aliasMasterDTO.setContractAliasType(helperListUtil.getIdHelperDTOMap().get(contractAM.getContractAliasType()));
            aliasMasterDTO.setContractAliasDesc(aliasMasterDTO.getContractAliasType().getDescription());
            if (contractAM.getStartDate() != null) {
                aliasMasterDTO.setStartDate(dateFormat.format(contractAM.getStartDate()));
            }
            if (contractAM.getEndDate() != null) {
                aliasMasterDTO.setEndDate(dateFormat.format(contractAM.getEndDate()));
            }
            aliasMasterDTO.setRecordLockStatus(String.valueOf(contractAM.getRecordLockStatus()));
            aliasListDTO.add(aliasMasterDTO);
            
        }
        LOGGER.info("End of getContractAliasMasterById method");
        return aliasListDTO;
    }

    /**
     * to get CFP Details
     *
     * @param contractSystemId
     * @param cfpId
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public CFPCompanyDTO getCfpContract() {
        final CFPCompanyDTO cfpCompanyDTO = new CFPCompanyDTO();
        try {
            if ((Integer) sessionDTO.getCfpSystemId() != ContractUtils.ZERO) {
                CfpContract cfpContract = CfpContractLocalServiceUtil.getCfpContract((Integer) sessionDTO.getCfpSystemId());
            CfpModel cfpModel = CfpModelLocalServiceUtil.getCfpModel(cfpContract.getCfpModelSid());
            cfpCompanyDTO.setCompanyFamilyPlanNo(cfpModel.getCfpNo());
            cfpCompanyDTO.setCompanyFamilyPlanId(cfpModel.getCfpId());
            cfpCompanyDTO.setCompanyFamilyPlanName(cfpContract.getCfpName());
            cfpCompanyDTO.setCompanyFamilyPlanStartDate(cfpContract.getCfpStartDate());
            cfpCompanyDTO.setCfpStatus(helperListUtil.getIdHelperDTOMap().get(cfpContract.getCfpStatus()));
            cfpCompanyDTO.setCompanyFamilyPlanStartDate(cfpContract.getCfpStartDate());
            cfpCompanyDTO.setCompanyFamilyPlanEndDate(cfpContract.getCfpEndDate());
            cfpCompanyDTO.setCreatedDate(new SimpleDateFormat("MM/dd/YYYY").format(cfpContract.getCreatedDate()));
            cfpCompanyDTO.setCreatedBy(getUserFLName(String.valueOf(cfpContract.getCreatedBy())));
            cfpCompanyDTO.setModifiedDate(new SimpleDateFormat("MM/dd/YYYY").format(cfpContract.getModifiedDate()));
            cfpCompanyDTO.setModifiedBy(getUserFLName(String.valueOf(cfpContract.getModifiedBy())));
            cfpCompanyDTO.setCfptype(helperListUtil.getIdHelperDTOMap().get(cfpContract.getCfpType()));
            cfpCompanyDTO.setCfpCategory(helperListUtil.getIdHelperDTOMap().get(cfpContract.getCfpCategory()));
            if (!cfpContract.getCfpDesignation().equals("")) {
                cfpCompanyDTO.setCfpDesignation(helperListUtil.getIdHelperDTOMap().get(Integer.parseInt(cfpContract.getCfpDesignation())));
            }
            cfpCompanyDTO.setCfptrade(helperListUtil.getIdHelperDTOMap().get(cfpContract.getCfpTradeClass()));
            if (cfpContract.getParentCfpId() != 0) {
                cfpCompanyDTO.setParentFlagSID(String.valueOf(cfpContract.getParentCfpId()));
                cfpCompanyDTO.setParentCfp(CfpModelLocalServiceUtil.getCfpModel(cfpContract.getParentCfpId()).getCfpId());
                cfpCompanyDTO.setParentCfpName(CfpModelLocalServiceUtil.getCfpModel(cfpContract.getParentCfpId()).getCfpName());
            }
            
            if (cfpContract.getSalesInclusion() != 0) {
                cfpCompanyDTO.setSalesInclusion(helperListUtil.getIdHelperDTOMap().get(cfpContract.getSalesInclusion()));
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DashBoardLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cfpCompanyDTO;
        
    }
    
    public CFPCompanyDTO getCfpDetails(final int contractSystemId, final int cfpId) throws PortalException, SystemException {
        LOGGER.info("Entering getCfpDetails method contractSystemId=" + contractSystemId + " cfpId=" + cfpId);
        DateFormat df=new SimpleDateFormat("MM/dd/YYYY");
        final CFPCompanyDTO cfpCompanyDTO = new CFPCompanyDTO();
        Map<Integer, String> userMap = StplSecurity.getUserName();
        final List<CFPCompanyDTO> cfpList = new ArrayList<CFPCompanyDTO>();
        final List<CompanyMasterDTO> companyList = new ArrayList<CompanyMasterDTO>();
        int cfpModelSid = 0;
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractSid", cfpId));
        dynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
        ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
        projectonList.add(ProjectionFactoryUtil.property("cfpModelSid"));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectonList));
        List<Integer> listInt = CfpContractLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (!listInt.isEmpty()) {
            for (int i = 0; i < listInt.size(); i++) {
                cfpModelSid = listInt.get(0);
            }
        }
        final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class);
        itemDynamicQuery.add(RestrictionsFactoryUtil.eq("sessionId", String.valueOf(sessionDTO.getUiSessionId())));
        itemDynamicQuery.add(RestrictionsFactoryUtil.eq("usersSid", String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID))));
        itemDynamicQuery.add(RestrictionsFactoryUtil.ne("operation", "D"));
        itemDynamicQuery.addOrder(OrderFactoryUtil.asc("companyNo"));
        final List<ImtdCfpDetails> resultList = dao.tempCfpDetailsDynamicQuery(itemDynamicQuery);
        
        for (int i = 0; i < resultList.size(); i++) {
            final ImtdCfpDetails cfpDA = resultList.get(i);
            final CompanyMaster company = dao.getCompanyMaster(cfpDA.getCompanyMasterSid());
            
            final CFPCompanyDTO cFPCompanyDTO = new CFPCompanyDTO();
            cFPCompanyDTO.setCompanySystemId(String.valueOf(company.getCompanyMasterSid()).replaceAll(",", ""));
            cFPCompanyDTO.setCompanyFamilyPlanStartDate(cfpDA.getCfpDetailsStartDate());
            cFPCompanyDTO.setCompanyFamilyPlanEndDate(cfpDA.getCfpDetailsEndDate());
            cFPCompanyDTO.setCompanyId(company.getCompanyId());
            cFPCompanyDTO.setCompanyNo(company.getCompanyNo());
            cFPCompanyDTO.setCompanyName(company.getCompanyName());
            cFPCompanyDTO.setCompanyStatus(loadDescription(company.getCompanyStatus()));
            cFPCompanyDTO.setCompanyType(loadDescription(company.getCompanyType()));
            cFPCompanyDTO.setCompanyCategory(loadDescription(company.getCompanyCategory()));
//            cFPCompanyDTO.setTradingPartnerContractNo(cfpDA.getTradingPartnerContractNo());
            cFPCompanyDTO.setAttachedDate(cfpDA.getCfpDetailsAttachedDate());
            cFPCompanyDTO.setCompanyStartDate(company.getCompanyStartDate());
            cFPCompanyDTO.setCompanyEndDate(company.getCompanyEndDate());
            cFPCompanyDTO.setCompanyFamilyPlanStatus(loadDescription(cfpDA.getCfpDetailsAttachedStatus()));
            cFPCompanyDTO.setCheckbox(true);
            cFPCompanyDTO.setModifiedDate(df.format(cfpDA.getModifiedDate()));
            cFPCompanyDTO.setCfpDetailsModifiedBy(userMap.get(cfpDA.getModifiedBy()));
            cFPCompanyDTO.setCreatedBy(userMap.get(cfpDA.getCreatedBy()));
            /**
             * To implement uniqueness logic
             */
            final CompanyMasterDTO comp = new CompanyMasterDTO();
            comp.setCompanySystemId(String.valueOf(company.getCompanyMasterSid()).replaceAll(",", ""));
            comp.setCompanyNo(company.getCompanyNo());
            comp.setCompanyName(company.getCompanyName());
            comp.setCompanyStatus(String.valueOf(company.getCompanyStatus()));
            comp.setCompanyType(String.valueOf(company.getCompanyType()));
            comp.setCreatedDate(String.valueOf(CURRENTDate) + "`" + i);
            cFPCompanyDTO.setCreatedDate(df.format(cfpDA.getCreatedDate()));
            /**
             *
             */
            cfpList.add(cFPCompanyDTO);
            companyList.add(comp);
        }
        cfpCompanyDTO.setCfpcompanyList(cfpList);
        cfpCompanyDTO.setCompanyList(companyList);
        LOGGER.info("End of getCfpDetails method");
        return cfpCompanyDTO;
    }
    
    public List<RsItemDetailsDTO> getRsSetupViewDetails(final int contractSystemId, final int rsId) throws SystemException, PortalException {
        LOGGER.info("Entering getRsSetupViewDetails method");
        final List<RsItemDetailsDTO> rsList = new ArrayList<RsItemDetailsDTO>();
        final RsItemDetailsDTO rsItemDetailsDTO = new RsItemDetailsDTO();
        try {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);// need to check
            itemDynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", rsId));
            itemDynamicQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            final List<RsContractDetails> resultList = dao.rsDetailsDynamicQuery(itemDynamicQuery);
            
            for (int i = 0; i < resultList.size(); i++) {
                final RsContractDetails rs = resultList.get(i);
                final ItemMaster item = dao.getItemMaster(rs.getItemMasterSid());
                final RsItemDetailsDTO rsDetailsDTO = new RsItemDetailsDTO();
                RebatePlanMaster rp = null;
                rsDetailsDTO.setItemName(item.getItemName());
                rsDetailsDTO.setItemNo(item.getItemNo());
                rsDetailsDTO.setItemType(item.getItemType() == 0 ? StringUtils.EMPTY : loadDescription(item.getItemType()));
                
                if (rs.getRsContractSid() != 0) {
                    rp = RebatePlanMasterLocalServiceUtil.getRebatePlanMaster(rs.getRsContractSid());
                }
                
                rsDetailsDTO.setFormulaId(rs.getFormulaId() == 0 ? StringUtils.EMPTY : String.valueOf(rs.getFormulaId()));
                rsDetailsDTO.setFormulaName(StringUtils.EMPTY);
                if (rp != null) {
                    rsDetailsDTO.setRebatePlanName(rp.getRebatePlanName());
                }
                rsDetailsDTO.setRebateAmount(String.valueOf(rs.getRebateAmount()));
                rsDetailsDTO.setStartDate(rs.getItemRebateStartDate());
                rsDetailsDTO.setEndDate(rs.getItemRebateEndDate());
                rsDetailsDTO.setBundleNo(rs.getBundleNo());
                rsDetailsDTO.setFormulaId(rs.getFormulaId() == 0 ? StringUtils.EMPTY : String.valueOf(rs.getFormulaId()));
                rsList.add(rsDetailsDTO);
            }
            rsItemDetailsDTO.setRsList(rsList);
            LOGGER.info("End of getRsSetupViewDetails method");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
        }
        return rsList;
    }

    /**
     * to get Rs Details
     *
     * @param contractSystemId
     * @param cfpId
     * @param ifpId
     * @param psId
     * @param rsId
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public RsItemDetailsDTO getRsDetails(final int contractSystemId, final int cfpId, final int ifpId, final int psId, final int rsId) throws SystemException, PortalException {
        LOGGER.info("Entering getRsDetails method");
        final RsItemDetailsDTO rsItemDetailsDTO = new RsItemDetailsDTO();
        CdrModel rule = null;
        try {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
            if (cfpId == 0) {
                itemDynamicQuery.add(RestrictionsFactoryUtil.isNull("cfpContractSid"));
            } else {
                itemDynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractSid", String.valueOf(cfpId)));
            }
            if (ifpId == 0) {
                itemDynamicQuery.add(RestrictionsFactoryUtil.isNull("ifpContractSid"));
            } else {
                itemDynamicQuery.add(RestrictionsFactoryUtil.eq("ifpContractSid", String.valueOf(ifpId)));
            }
            if (psId == 0) {
                itemDynamicQuery.add(RestrictionsFactoryUtil.isNull("psContractSid"));
            } else {
                itemDynamicQuery.add(RestrictionsFactoryUtil.eq("psContractSid", String.valueOf(psId)));
            }
            itemDynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", rsId));
            final List<RsContract> rebateSM = dao.rsMasterDynamicQuery(itemDynamicQuery);
            
            RsContract rtsContract = rebateSM.get(0);
            
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(Udcs.class);
            query.add(RestrictionsFactoryUtil.eq("masterSid", rsId));
            query.add(RestrictionsFactoryUtil.eq("masterType", ContractUtils.RS_CONTRACT));
            final List<Udcs> udcsList = UdcsLocalServiceUtil.dynamicQuery(query);
            
            rsItemDetailsDTO.setRebateScheduleId(rtsContract.getRsId());
            rsItemDetailsDTO.setRebateScheduleNo(rtsContract.getRsNo());
            rsItemDetailsDTO.setRebateScheduleName(rtsContract.getRsName());
            rsItemDetailsDTO.setRebateScheduleStatus(helperListUtil.getIdHelperDTOMap().get(rtsContract.getRsStatus()));
            rsItemDetailsDTO.setRebateScheduleType(helperListUtil.getIdHelperDTOMap().get(rtsContract.getRsType()));
            rsItemDetailsDTO.setRebateProgramType(helperListUtil.getIdHelperDTOMap().get(rtsContract.getRebateProgramType()));
            rsItemDetailsDTO.setRebateScheduleCategory(helperListUtil.getIdHelperDTOMap().get(rtsContract.getRsCategory()));
            rsItemDetailsDTO.setTradeClass(helperListUtil.getIdHelperDTOMap().get(rtsContract.getRsTradeClass()));
            if (rtsContract.getRsDesignation() != null && !"".equals(rtsContract.getRsDesignation())) {
                rsItemDetailsDTO.setRebateScheduleDesignation(helperListUtil.getIdHelperDTOMap().get(Integer.parseInt(rtsContract.getRsDesignation())));
            } else {
                rsItemDetailsDTO.setRebateScheduleDesignation(helperListUtil.getIdHelperDTOMap().get(0));
            }
            rsItemDetailsDTO.setRebateScheduleAlias(rtsContract.getRsAlias());
            rsItemDetailsDTO.setRebateScheduleTransRefNo(StringUtils.isBlank(rtsContract.getRsTransRefNo()) || "null".equals(rtsContract.getRsTransRefNo()) ? StringUtils.EMPTY : rtsContract.getRsTransRefNo());
            rsItemDetailsDTO.setRebateScheduleTransRefName(StringUtils.isBlank(rtsContract.getRsTransRefName()) || "null".equals(rtsContract.getRsTransRefName()) ? StringUtils.EMPTY : rtsContract.getRsTransRefName());
            rsItemDetailsDTO.setParentRebateScheduleId(rtsContract.getParentRsId());
            rsItemDetailsDTO.setParentRebateScheduleName(rtsContract.getParentRsName());
            if (rtsContract.getDeductionInclusion() != null && !"null".equals(String.valueOf(rtsContract.getDeductionInclusion()))) {
                rsItemDetailsDTO.setDeductionInclusion(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(rtsContract.getDeductionInclusion())));
            }
            rsItemDetailsDTO.setInterestBearingBasis(helperListUtil.getHelperDTObyID(rtsContract.getInterestBearingBasis()));
            rsItemDetailsDTO.setInterestBearingIndicator(helperListUtil.getHelperDTObyID(rtsContract.getInterestBearingIndicator()));
            if (rtsContract.getEvaluationRuleLevel() != null && !"null".equals(String.valueOf(rtsContract.getEvaluationRuleLevel()))) {
                rsItemDetailsDTO.setEvaluationRuleLevel(helperListUtil.getHelperDTObyID(Integer.valueOf(rtsContract.getEvaluationRuleLevel())));
            }
            if (rtsContract.getEvaluationRuleType() != null && !"null".equals(String.valueOf(rtsContract.getEvaluationRuleType()))) {
                rsItemDetailsDTO.setEvaluationRuleType(helperListUtil.getHelperDTObyID(Integer.valueOf(rtsContract.getEvaluationRuleType())));
            }
            if (rtsContract.getEvaluationRuleOrAssociation() != null && !"null".equals(String.valueOf(rtsContract.getEvaluationRuleOrAssociation()))) {
                rule = CdrModelLocalServiceUtil.getCdrModel(Integer.valueOf(rtsContract.getEvaluationRuleOrAssociation()));
                rsItemDetailsDTO.setEvaluationRuleAssociation(rule.getRuleNo());
            }
            
            if (rtsContract.getCalculationRuleLevel() != null && !"null".equals(String.valueOf(rtsContract.getCalculationRuleLevel()))) {
                rsItemDetailsDTO.setCalculationRuleLevel(helperListUtil.getHelperDTObyID(Integer.valueOf(rtsContract.getCalculationRuleLevel())));
            }
            rsItemDetailsDTO.setCalculationType(helperListUtil.getHelperDTObyID(rtsContract.getCalculationType()));
            if (rtsContract.getCalculationRule() != null && !"null".equals(String.valueOf(rtsContract.getCalculationRule()))) {
                rule = CdrModelLocalServiceUtil.getCdrModel(Integer.valueOf(rtsContract.getCalculationRule()));
                rsItemDetailsDTO.setCalculationRule(rule.getRuleNo());
            }
            rsItemDetailsDTO.setCalculationLevel(helperListUtil.getHelperDTObyID(rtsContract.getCalculationLevel()));
            rsItemDetailsDTO.setValidationProfile(helperListUtil.getIdHelperDTOMap().get(rtsContract.getRsValidationProfile()));
            rsItemDetailsDTO.setRebateProcessingType(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(StringUtils.isEmpty(String.valueOf(rtsContract.getRebateProcessingType())) ? "0" : String.valueOf(rtsContract.getRebateProcessingType()))));
            rsItemDetailsDTO.setRebatePlanLevel(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(StringUtils.isEmpty(rtsContract.getRebatePlanLevel()) ? "0" : rtsContract.getRebatePlanLevel())));
            rsItemDetailsDTO.setRebateRuleAssociation(rtsContract.getRebateRuleAssociation());
            if (rtsContract.getRsStartDate().equals(rtsContract.getRsEndDate())) {
                rsItemDetailsDTO.setStartDate(rtsContract.getRsStartDate());
                rsItemDetailsDTO.setItemRebateStartDate(rtsContract.getRsStartDate());
                rsItemDetailsDTO.setItemRebateEndDate(null);
                rsItemDetailsDTO.setEndDate(null);
            } else {
                rsItemDetailsDTO.setStartDate(rtsContract.getRsStartDate());
                rsItemDetailsDTO.setItemRebateStartDate(rtsContract.getRsStartDate());
                rsItemDetailsDTO.setItemRebateEndDate(rtsContract.getRsEndDate());
                rsItemDetailsDTO.setEndDate(rtsContract.getRsEndDate());
            }
            rsItemDetailsDTO.setPaymentMethod(helperListUtil.getIdHelperDTOMap().get(rtsContract.getPaymentMethod()));
            rsItemDetailsDTO.setRebateFrequency(helperListUtil.getIdHelperDTOMap().get(rtsContract.getRebateFrequency()));
            rsItemDetailsDTO.setPaymentFrequency(helperListUtil.getIdHelperDTOMap().get(rtsContract.getPaymentFrequency()));
            rsItemDetailsDTO.setPaymentTerms(helperListUtil.getIdHelperDTOMap().get(rtsContract.getPaymentTerms()));
            System.out.println("rtsContract.getRsCalendar()"+rtsContract.getRsCalendar());
            if (rtsContract.getRsCalendar() != null && !"".equals(rtsContract.getRsCalendar())) {
                rsItemDetailsDTO.setCalendar(helperListUtil.getIdHelperDTOMap().get(Integer.parseInt(rtsContract.getRsCalendar())));
            }
            rsItemDetailsDTO.setPaymentGracePeriod(rtsContract.getPaymentGracePeriod());
            if (!udcsList.isEmpty()) {
                Udcs udcs = udcsList.get(0);
            } else {
                rsItemDetailsDTO.setCalendar(helperListUtil.getIdHelperDTOMap().get(0));
            }
            rsItemDetailsDTO.setPaymentGracePeriod(rtsContract.getPaymentGracePeriod());
            if (!udcsList.isEmpty()) {
                Udcs udcs = udcsList.get(0);
                rsItemDetailsDTO.setUdc1(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc1()));
                rsItemDetailsDTO.setUdc2(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc2()));
                rsItemDetailsDTO.setUdc3(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc3()));
                rsItemDetailsDTO.setUdc4(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc4()));
                rsItemDetailsDTO.setUdc5(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc5()));
                rsItemDetailsDTO.setUdc6(helperListUtil.getIdHelperDTOMap().get(udcs.getUdc6()));
            }
            rsItemDetailsDTO.setRebateScheduleMaster(rebateSM.get(0));
            rsItemDetailsDTO.setEvaluationSystemId(rtsContract.getEvaluationRuleOrAssociation() != null && !rtsContract.getEvaluationRuleOrAssociation().equals("0") ? rtsContract.getEvaluationRuleOrAssociation() : "");
            rsItemDetailsDTO.setCalculationSystemId(rtsContract.getCalculationRule() != null && !rtsContract.getCalculationRule().equals("0") ? rtsContract.getCalculationRule() : "");
            
            LOGGER.info("End of getRsDetails method");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
        }
        return rsItemDetailsDTO;
    }

    /**
     * to get Contract Price Details
     *
     * @param contractSystemId
     * @param cfpId
     * @param ifpId
     * @param psId
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public VwContractPriceInfoDTO getContractPriceDetails(final int contractSystemId, final int cfpId, final int ifpId, final int psId) throws SystemException, PortalException {
        LOGGER.info("Entering getContractPriceDetails method");
        
        final VwContractPriceInfoDTO contractPIDTO = new VwContractPriceInfoDTO();
        final List<VwContractPriceInfoDTO> contractpriceList = new ArrayList<VwContractPriceInfoDTO>();
        final List<ItemMasterDTO> itemList = new ArrayList<ItemMasterDTO>();
        final List contractPrice = dao.getContractPriceInfo(contractSystemId, cfpId, ifpId, psId);
        for (int i = 0; i < contractPrice.size(); i++) {
            final Object[] obj = (Object[]) contractPrice.get(i);
            final VwContractPriceInfoDTO contractPriceDTO = new VwContractPriceInfoDTO();
            contractPriceDTO.setContractSystemId(String.valueOf(obj[0]));
            contractPriceDTO.setItemSystemId(String.valueOf(obj[1]).replaceAll(",", ""));
            
            contractPriceDTO.setContractprice(String.valueOf(obj[2]));
            contractPriceDTO.setCpStartDate((Date) obj[3]);
            contractPriceDTO.setCpEndDate((Date) obj[4]);
            contractPriceDTO.setStartDate((Date) obj[5]);
            contractPriceDTO.setEndDate((Date) obj[6]);
            contractPriceDTO.setGlobalitemstatus(String.valueOf(obj[7]));
            contractPriceDTO.setItemNo(String.valueOf(obj[8]));
            contractPriceDTO.setItemName(String.valueOf(obj[9]));
            contractPriceDTO.setPackageSize(String.valueOf(obj[10]));
            contractPriceDTO.setPpStartDate((Date) obj[11]);
            contractPriceDTO.setPpEndDate((Date) obj[12]);
            contractPriceDTO.setPriceToleranceType(String.valueOf(obj[13]));
            contractPriceDTO.setPriceTolerance(String.valueOf(obj[14]));
            contractPriceDTO.setBasePrice(String.valueOf(obj[15]));
            contractPriceDTO.setPriceToleranceFrequency(String.valueOf(obj[16]));
            contractPriceDTO.setPriceToleranceInterval(String.valueOf(obj[17]));
            contractPriceDTO.setPriceType(String.valueOf(obj[18]));
            contractPriceDTO.setDisplayPriceType(StringUtils.isBlank(contractPriceDTO.getPriceType()) || "0".equals(contractPriceDTO.getPriceType()) ? StringUtils.EMPTY : getPricingQualifierName(Integer.valueOf(contractPriceDTO.getPriceType())));
            contractPriceDTO.setPrice(String.valueOf(obj[19]));
            contractPriceDTO.setPrimaryUom(String.valueOf(obj[20]));
            contractPriceDTO.setSecondaryUom(String.valueOf(obj[21]));
            contractPriceDTO.setPsDetailsSystemId(String.valueOf(obj[22]).replaceAll(",", ""));
            contractPriceDTO.setIfpDetailsSystemId(String.valueOf(obj[23]).replaceAll(",", ""));
            
            contractPriceDTO.setItemId(String.valueOf(obj[24]));
            
            final ItemMaster item = dao.getItemMaster(Integer.valueOf(String.valueOf(obj[1])));
            
            final ItemMasterDTO itemDTOObj = new ItemMasterDTO();
            itemDTOObj.setItemSystemId(String.valueOf(item.getItemMasterSid()));
            itemDTOObj.setItemId(item.getItemId());
            itemDTOObj.setItemNo(item.getItemNo());
            itemDTOObj.setItemName(item.getItemName());
            itemDTOObj.setPackageSize(item.getPackageSize());
            itemDTOObj.setPrimaryUom(String.valueOf(item.getPrimaryUom()));
            itemDTOObj.setRevisionDate(new Date());
            itemDTOObj.setUniqueDate(String.valueOf(new Date() + "`" + i));
            contractPriceDTO.setRevisionDate(itemDTOObj.getRevisionDate());
            contractPriceDTO.setUniqueDate(itemDTOObj.getUniqueDate());
            contractpriceList.add(contractPriceDTO);
            itemList.add(itemDTOObj);
        }
        contractPIDTO.setContractPriceInfoDTO(contractpriceList);
        contractPIDTO.setItemList(itemList);
        LOGGER.info("End of getContractPriceDetails method");
        return contractPIDTO;
    }

    /**
     * Method used for Save contract dash board.
     *
     * @param contractMB the contract mb
     * @param aliasMRB the alias mrb
     * @param cfpResultsBean the cfp results bean
     * @param itemDRB the item drb
     * @param rebateBinder the rebate binder
     * @param rsDRB the rs drb
     * @return the string
     * @throws SystemException
     * @throws PortalException
     */
    public String saveContractDashBoard(final CustomFieldGroup contractMB, final List<ContractAliasMasterDTO> aliasMRB, final List<CFPCompanyDTO> cfpResultsBean,
            final List<VwContractPriceInfoDTO> itemDRB, final CustomFieldGroup rebateBinder, final List<TempRebateDTO> rsDRB, final List<NotesDTO> availableUploadedInformation, final String addedNotes, final CustomFieldGroup pricingBinderEdit, final CustomFieldGroup cfPContract, CustomFieldGroup ifpItemsBinder) throws SystemException, PortalException, ParseException,
            java.text.ParseException {
        LOGGER.info("Entering saveContractDashBoard method");
        final int rsSystemId = (Integer) sessionDTO.getRsSystemId();
        final int psSystemId = (Integer) sessionDTO.getPsSystemId();
        final int ifpSystemId = (Integer) sessionDTO.getIfpSystemId();
        final int cfpSystemId = (Integer) sessionDTO.getCfpSystemId();
        final int contractSystemId = (Integer) sessionDTO.getContractSystemId();
        
        saveMasterDetails(contractMB, aliasMRB, contractSystemId, addedNotes);
        notesLogic.saveUploadedInformation(availableUploadedInformation, "CONTRACT_MASTER", contractSystemId);
        if (cfpSystemId != ContractUtils.ZERO) {
            CfpContract cfpContract = CfpContractLocalServiceUtil.getCfpContract((Integer) sessionDTO.getCfpSystemId());
            cfpContract.setCfpStartDate((Date) cfPContract.getField("companyFamilyPlanStartDate").getValue());
            cfpContract.setCfpEndDate((Date) cfPContract.getField("companyFamilyPlanEndDate").getValue());
            cfpContract.setCfpType(getHelperCode(UIUtils.CFP_TYPE, cfPContract.getField("cfptype").toString()));
            cfpContract.setCfpStatus(getHelperCode(UIUtils.STATUS, cfPContract.getField("cfpStatus").toString()));
            cfpContract.setCfpTradeClass(getHelperCode(UIUtils.CFP_TRADE_CLASS, cfPContract.getField("cfptrade").toString()));
            cfpContract.setCfpCategory(getHelperCode(UIUtils.CFP_CATEGORY, cfPContract.getField("cfpCategory").toString()));
            cfpContract.setCfpDesignation(String.valueOf(getHelperCode(UIUtils.CFP_DESIGNATION, cfPContract.getField("cfpDesignation").toString())));            
            if (StringUtils.isNotBlank(String.valueOf(cfPContract.getField("parentFlagSID").getValue()))) {
                cfpContract.setParentCfpId(Integer.parseInt(cfPContract.getField("parentFlagSID").toString()));
            }            
            cfpContract.setParentCfpName(cfPContract.getField("parentCfpName").toString());
            cfpContract.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
            cfpContract.setModifiedDate(new Date());
            cfpContract.setSalesInclusion(getHelperCode(UIUtils.LOCKED_STATUS, cfPContract.getField("salesInclusion").toString()));
            CfpContractLocalServiceUtil.updateCfpContract(cfpContract);
            saveCFPDetails(cfpResultsBean, contractSystemId, cfpSystemId);
        }
        if (ifpSystemId != ContractUtils.ZERO) {
            try {
                IfpLogic ifplogic = new IfpLogic();
                ifplogic.saveIFP(ifpItemsBinder, ifpSystemId);
                
                saveIFPDetails(itemDRB, contractSystemId, cfpSystemId, ifpSystemId, psSystemId, rsSystemId);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if (psSystemId != ContractUtils.ZERO) {
            savePriceScheduleMaster(psSystemId, pricingBinderEdit);
            savePSDetails(itemDRB, contractSystemId, cfpSystemId, ifpSystemId, psSystemId);
        }
        if (rsSystemId != ContractUtils.ZERO) {
            saveRSDetails(rebateBinder, rsDRB, contractSystemId, cfpSystemId, ifpSystemId, psSystemId, rsSystemId);
        }
        
        LOGGER.info("End of saveContractDashBoard method");
        
        ContractDashboardLogic logic = new ContractDashboardLogic();
        try {
            logic.callCcpProcedure();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        
        return Constants.SUCCESS;
    }

    /**
     * Method used for Save master details.
     *
     * @param contractMF the contract mf
     * @param aliasMasterBeans the alias master beans
     * @param contractsystemId the contractsystem id
     * @throws PortalException
     * @throws SystemException
     */
    public void saveMasterDetails(final CustomFieldGroup contractMF, final List<ContractAliasMasterDTO> aliasMasterBeans, final int contractsystemId, final String addedNotes) throws SystemException, PortalException, java.text.ParseException {
        LOGGER.info("Entering saveMasterDetails method with contractsystemId=" + contractsystemId);
        
        final ContractMaster contract = dao.getContractMaster(contractsystemId);
        contract.setContractId(String.valueOf(contractMF.getField(Constants.CONTRACT_ID)));
        contract.setContractNo(String.valueOf(contractMF.getField(Constants.CONTRACT_NO)));
        contract.setContractName(String.valueOf(contractMF.getField(Constants.CONTRACT_NAME)));
        contract.setContractType(getHelperCode(UIUtils.CONTRACT_TYPE, contractMF.getField(Constants.CONTRACT_TYPE).toString()));
        contract.setContractStatus(getHelperCode(Constants.STATUS, contractMF.getField(Constants.CONTRACT_STATUS).toString()));
        contract.setInternalNotes(addedNotes);
        if (contractMF.getField(Constants.COMPANY_SYSTEM_ID).getValue() != null) {
            int compID = ((HelperDTO) contractMF.getField(Constants.COMPANY_SYSTEM_ID).getValue()).getId();
            contract.setManfCompanyMasterSid(compID == 0 ? null : String.valueOf(compID));
            
        }
        final ContractMaster temp = dao.getContractMaster(contractsystemId);
        
        if (temp.getContractStatus() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(temp.getContractStatus());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_STATUS);
        }
        if (temp.getContractType() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(temp.getContractType());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_TYPE);
        }
        if (temp.getContractTradeClass() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(temp.getContractTradeClass());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.TRADE_CLASS);
        }
        if (temp.getDocumentType() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(temp.getDocumentType());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.DOCUMENT_TYPE);
        }
        if (temp.getDocumentClass() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(temp.getDocumentClass());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.DOCUMENT_CLASS);
        }
        
        contract.setProcessStatus(temp.getProcessStatus());
        contract.setRecordLockStatus(temp.getRecordLockStatus());
        if (contractMF.getField(ContractUtils.START_DATE).getValue() != null && !contractMF.getField(ContractUtils.START_DATE).getValue().equals("")) {
            contract.setStartDate((Date) contractMF.getField(ContractUtils.START_DATE).getValue());
        }
        contract.setEndDate((Date) contractMF.getField(Constants.END_DATE).getValue());
        contract.setDocumentType(contractMF.getField(Constants.DOC_TYPE).getValue() == null ? 0 : getHelperCode(UIUtils.DOCUMENT_TYPE, contractMF.getField(Constants.DOC_TYPE).getValue().toString()));
        contract.setTerm(contractMF.getField(Constants.TERM).getValue() == null ? 0 : Integer.valueOf(contractMF.getField(Constants.TERM).getValue().toString()));
        contract.setContractTradeClass(contractMF.getField(Constants.TRADE_CLASS).getValue() == null ? 0 : getHelperCode(UIUtils.TRADE_CLASS, contractMF.getField(Constants.TRADE_CLASS).getValue().toString()));
        contract.setRenegotiationStartDate((Date) contractMF.getField(Constants.RENEGOTIATION_START_DATE).getValue());
        contract.setRenegotiationEndDate((Date) contractMF.getField(Constants.RENEGOTIATION_END_DATE).getValue());
        contract.setPriceprotectionStartDate((Date) contractMF.getField(Constants.PRICE_PROTECTION_START_DATE).getValue());
        contract.setPriceprotectionEndDate((Date) contractMF.getField(Constants.PRICE_PROTECTION_END_DATE).getValue());
        contract.setDocumentClass(contractMF.getField(Constants.DOC_CLASS).getValue() == null ? 0 : getHelperCode(UIUtils.DOCUMENT_CLASS, contractMF.getField(Constants.DOC_CLASS).getValue().toString()));
        
        if (contractMF.getField(Constants.COMPANY_NAME).getValue() != null && !contractMF.getField(Constants.COMPANY_NAME).getValue().toString().equals("") && !contractMF.getField(Constants.COMPANY_NAME).getValue().toString().equals("0")) {
            contract.setBunitCompanyMasterSid((contractMF.getField(Constants.COMPANY_NAME).getValue().toString().replaceAll(",", "")));
        } 
        
        if (contractMF.getField(ContractUtils.TRADINGPARTNER_SYSTEMID).getValue() != null) {
            contract.setContHoldCompanyMasterSid(Integer.parseInt(contractMF.getField(ContractUtils.TRADINGPARTNER_SYSTEMID).getValue().toString().replaceAll(",", "")) == 0 ? null : contractMF.getField(ContractUtils.TRADINGPARTNER_SYSTEMID).getValue().toString().replaceAll(",", ""));
        } 
        
        contract.setInsideOwner(validation(String.valueOf(contractMF.getField("insideOwner").getValue())) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("insideOwner").getValue()));
        contract.setInsidePhone(validation(String.valueOf(contractMF.getField("insidePhone"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("insidePhone")));
        contract.setInsideAuthor(validation(String.valueOf(contractMF.getField("insideAuthor"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("insideAuthor")));
        contract.setInsideAdditional(validation(String.valueOf(contractMF.getField("insideAdditional"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("insideAdditional")));
        contract.setInsideAdditionalName(validation(String.valueOf(contractMF.getField("insideAdditionalName"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("insideAdditionalName")));
        contract.setInsideAdditionalPhone(validation(String.valueOf(contractMF.getField("insideAdditionalPhone"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("insideAdditionalPhone")));
        contract.setOutsideOwner(validation(String.valueOf(contractMF.getField("outsideOwner"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("outsideOwner")));
        contract.setOutsidePhone(validation(String.valueOf(contractMF.getField("outsidePhone"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("outsidePhone")));
        contract.setOutsideAuthor(validation(String.valueOf(contractMF.getField("outsideAuthor"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("outsideAuthor")));
        contract.setOutsideAdditional(validation(String.valueOf(contractMF.getField("outsideAdditional"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("outsideAdditional")));
        contract.setOutsideAdditionalName(validation(String.valueOf(contractMF.getField("outsideAdditionalName"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("outsideAdditionalName")));
        contract.setOutsideAdditionalPhone(validation(String.valueOf(contractMF.getField("outsideAdditionalPhone"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("outsideAdditionalPhone")));
        String value = String.valueOf(contractMF.getField("advanceNoticeDays").getValue());
        contract.setAdvanceNoticeDays("".equals(value) || "null".equals(value) || "0".equals(value) ? 0.0 : Double.valueOf(value));
        contract.setAffiliatedContractInfo(validation(String.valueOf(contractMF.getField("affiliatedContractInfo"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("affiliatedContractInfo")));
        contract.setShippingTerms(validation(String.valueOf(contractMF.getField("shippingTerms"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("shippingTerms")));
        if (contractMF.getField("proposedStartDate").getValue() != null) {
            contract.setProposalStartDate((Date) contractMF.getField("proposedStartDate").getValue());
        } else {
            contract.setProposalStartDate(null);
        }
        if (contractMF.getField("proposedEndDate").getValue() != null) {
            contract.setProposalEndDate((Date) contractMF.getField("proposedEndDate").getValue());
        } else {
            contract.setProposalEndDate(null);
        }
        if (contractMF.getField("originalStartDate").getValue() != null) {
            contract.setOriginalStartDate((Date) contractMF.getField("originalStartDate").getValue());
        } else {
            contract.setOriginalStartDate(null);
        }
        if (contractMF.getField("originalEndDate").getValue() != null) {
            contract.setOriginalEndDate((Date) contractMF.getField("originalEndDate").getValue());
        } else {
            contract.setOriginalEndDate(null);
        }
        if (contractMF.getField("awardStatus").getValue() != null) {
            contract.setAwardStatus(((com.stpl.ifs.util.HelperDTO) contractMF.getField("awardStatus").getValue()).getId());
        }
        if (contractMF.getField("lastUpdatedDate").getValue() != null) {
            contract.setLastUpdatedDate((Date) contractMF.getField("lastUpdatedDate").getValue());
        } else {
            contract.setLastUpdatedDate(null);
        }
        contract.setPriceEscalationClause(validation(String.valueOf(contractMF.getField("priceEscalationClause"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("priceEscalationClause")));
        contract.setExemptFromLowPrice(validation(String.valueOf(contractMF.getField("exemptFromLowPrice"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("exemptFromLowPrice")));
        if (contractMF.getField("priceResetIndicator").getValue() != null) {
            contract.setPriceResetIndicator(String.valueOf(((com.stpl.ifs.util.HelperDTO) contractMF.getField("priceResetIndicator").getValue()).getId()));
        }
        contract.setCancellationClause(validation(String.valueOf(contractMF.getField("cancellationClause"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("cancellationClause")));
        contract.setMostFavoredNation(validation(String.valueOf(contractMF.getField("mostFavoredNation"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("mostFavoredNation")));
        contract.setCategory(validation(String.valueOf(contractMF.getField("category"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("category")));
        contract.setCurrency(validation(String.valueOf(contractMF.getField("currency"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("currency")));
        contract.setMinimumOrder(validation(String.valueOf(contractMF.getField("minimumOrder"))) ? StringUtils.EMPTY : String.valueOf(contractMF.getField("minimumOrder")));
        if (contractMF.getField("paymentTerms").getValue() != null) {
            contract.setPaymentTerms(((com.stpl.ifs.util.HelperDTO) contractMF.getField("paymentTerms").getValue()).getId());
        }
        contract.setContractMasterSid(contractsystemId);
        contract.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
        contract.setCreatedDate(new Date());
        contract.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
        contract.setModifiedDate(new Date());
        contract.setSource("GTN");
        final ContractMaster result = dao.updateContractMaster(contract);
        if (result.getContractStatus() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(result.getContractStatus());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_STATUS);
        }
        if (result.getContractType() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(result.getContractType());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_TYPE);
        }
        if (result.getContractTradeClass() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(result.getContractTradeClass());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.TRADE_CLASS);
        }
        if (result.getDocumentType() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(result.getDocumentType());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.DOCUMENT_TYPE);
        }
        if (result.getDocumentClass() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(result.getDocumentClass());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.DOCUMENT_CLASS);
        }
        
        final List<ContractAliasMaster> aliasMasterList = dao.findByContractSystemId(result.getContractMasterSid());
        for (int i = 0; i < aliasMasterList.size(); i++) {
            final ContractAliasMaster contractAM = (ContractAliasMaster) aliasMasterList.get(i);
            final ContractAliasMaster check = dao.deleteContractAliasMaster(contractAM);
            if (check.getContractAliasType() != 0) {
                HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(check.getContractAliasType());
                udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_ALIAS_TYPE);
            }
        }
        saveContractAliasMasterList(aliasMasterBeans, result);
        LOGGER.info("End of saveMasterDetails method");
    }
    
    public void savePriceScheduleMaster(final int psSystemId, final CustomFieldGroup pricingBinderEdit) throws PortalException, SystemException {
        PsContract priceSchedule = PsContractLocalServiceUtil.getPsContract(psSystemId);
        priceSchedule.setPsStatus(getHelperCode(Constants.STATUS, pricingBinderEdit.getField("priceScheduleStatus").toString()));
        priceSchedule.setPsStartDate((Date) pricingBinderEdit.getField("priceScheduleStartDate").getValue());
        priceSchedule.setPsEndDate((Date) pricingBinderEdit.getField("priceScheduleEndDate").getValue());
        priceSchedule.setPsDesignation(String.valueOf(getHelperCode(Constants.DB_PS_DESIGNATION, pricingBinderEdit.getField("priceScheduleDesignation").toString())));
        priceSchedule.setParentPsId(String.valueOf(pricingBinderEdit.getField("parentPriceScheduleId")));
        priceSchedule.setParentPsName(String.valueOf(pricingBinderEdit.getField("parentPriceScheduleName")));
        priceSchedule.setPsType(getHelperCode(Constants.DB_PS_TYPE, pricingBinderEdit.getField("priceScheduleType").toString()));
        priceSchedule.setPsCategory(getHelperCode(Constants.DB_PS_CATEGORY, pricingBinderEdit.getField("priceScheduleCategory").toString()));
        priceSchedule.setPsTradeClass(getHelperCode(Constants.DB_TRADE_CLASS, pricingBinderEdit.getField("tradeClass").toString()));
        priceSchedule.setCreatedDate(new Date());
        priceSchedule.setModifiedDate(new Date());
        priceSchedule.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
        priceSchedule.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ContractUtils.USER_ID)));
        final PsContract psResult = PsContractLocalServiceUtil.updatePsContract(priceSchedule);
        
    }
    
    public boolean validation(String value) {
        if ("null".equals(value) || "".equals(value) || StringUtils.isBlank(value)) {
            
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Method used for Save rs details.
     *
     * @param rebateBinder the rebate binder
     * @param rsDetailsRB the rs details rb
     * @param contractSystemId the contract system id
     * @param cfpSystemId the cfp system id
     * @param ifpSystemId the ifp system id
     * @param psSystemId the ps system id
     * @param rsSystemId the rs system id
     * @throws SystemException
     */
    private void saveRSDetails(final CustomFieldGroup rebateBinder, final List<TempRebateDTO> rsDetailsRB, final int contractSystemId, final int cfpSystemId, final int ifpSystemId,
            final int psSystemId, final int rsSystemId) throws SystemException {
        LOGGER.info("Entering saveRSDetails method");
        try {
            final DynamicQuery itemDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
            itemDynamicQuery.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
            if (cfpSystemId == 0) {
                itemDynamicQuery.add(RestrictionsFactoryUtil.isNull("cfpContractSid"));
            } else {
                itemDynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractSid", String.valueOf(cfpSystemId)));
            }
            if (ifpSystemId == 0) {
                itemDynamicQuery.add(RestrictionsFactoryUtil.isNull("ifpContractSid"));
            } else {
                itemDynamicQuery.add(RestrictionsFactoryUtil.eq("ifpContractSid", String.valueOf(ifpSystemId)));
            }
            if (psSystemId == 0) {
                itemDynamicQuery.add(RestrictionsFactoryUtil.isNull("psContractSid"));
            } else {
                itemDynamicQuery.add(RestrictionsFactoryUtil.eq("psContractSid", String.valueOf(psSystemId)));
            }
            itemDynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", rsSystemId));
            
            final List<RsContract> rebateSM = dao.rsMasterDynamicQuery(itemDynamicQuery);
            
            if (rebateSM != null && rebateSM.size() > ContractUtils.ZERO) {
                final RsContract rsMasterInfo = rebateSM.get(0);
                rsMasterInfo.setModifiedDate(new Date());
                BeanItem binder = (BeanItem) rebateBinder.getItemDataSource();
                RsItemDetailsDTO binderDto = (RsItemDetailsDTO) binder.getBean();
                rsMasterInfo.setRsId(String.valueOf(rebateBinder.getField(Constants.RS_ID).getValue()));
                rsMasterInfo.setRsNo(String.valueOf(rebateBinder.getField(Constants.RS_NO).getValue()));
                rsMasterInfo.setRsName(String.valueOf(rebateBinder.getField(Constants.RS_NAME).getValue()));
                rsMasterInfo.setRsStatus(((HelperDTO) (rebateBinder.getField(Constants.RS_STATUS).getValue())).getId());//hard-coded temporarily
                rsMasterInfo.setRsType(((HelperDTO) (rebateBinder.getField(Constants.RS_TYPE).getValue())).getId());//hard-coded temporarily
                rsMasterInfo.setRebateProgramType(((HelperDTO) (rebateBinder.getField(Constants.REBATE_PROGRAM_TYPE).getValue())).getId());//hard-coded temporarily
                rsMasterInfo.setRsValidationProfile(((HelperDTO) (rebateBinder.getField(Constants.VALIDATION_PROFILE).getValue())).getId());//hard-coded temporarily
                rsMasterInfo.setInterestBearingIndicator(rebateBinder.getField("interestBearingIndicator").getValue() == null ? 0 : ((HelperDTO) (rebateBinder.getField("interestBearingIndicator").getValue())).getId());
                rsMasterInfo.setRsAlias(String.valueOf(rebateBinder.getField("rebateScheduleAlias").getValue()).trim());
                
                rsMasterInfo.setRsStartDate((Date) rebateBinder.getField(Constants.ITEM_REBATE_START_DATE).getValue());
                rsMasterInfo.setRsEndDate((Date) rebateBinder.getField(Constants.ITEM_REBATE_END_DATE).getValue());
                rsMasterInfo.setPaymentMethod(((HelperDTO) rebateBinder.getField(Constants.PAYMENT_METHOD).getValue()).getId());//hard-coded temporarily
                rsMasterInfo.setPaymentFrequency(((HelperDTO) rebateBinder.getField(Constants.PAYMENT_FREQUENCY).getValue()).getId());//hard-coded temporarily
                rsMasterInfo.setPaymentTerms(rebateBinder.getField(Constants.PAYMENT_TERMS).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.PAYMENT_TERMS).getValue()).getId() : 0);//hard-coded temporarily
                rsMasterInfo.setRsCalendar(String.valueOf(((HelperDTO) (rebateBinder.getField(Constants.CALENDER).getValue())).getId()));
                rsMasterInfo.setRebateFrequency(rebateBinder.getField(Constants.REBATE_FREQUENCY).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.REBATE_FREQUENCY).getValue()).getId() : 0);//hard-coded temporarily
                if (rebateBinder.getField("deductionInclusion").getValue() == null) {
                    rsMasterInfo.setDeductionInclusion("0");
                } else {
                    rsMasterInfo.setDeductionInclusion(String.valueOf(((HelperDTO) rebateBinder.getField("deductionInclusion").getValue()).getId()));
                }
                rsMasterInfo.setPaymentGracePeriod(String.valueOf(rebateBinder.getField(Constants.PAYMENT_GRACE_PERIOD).getValue()));
                rsMasterInfo.setRebateProcessingType(rebateBinder.getField("rebateProcessingType").getValue() == null ? 0 : ((HelperDTO) rebateBinder.getField("rebateProcessingType").getValue()).getId());
                
                rsMasterInfo.setSource("GTN");
                rsMasterInfo.setRsCategory(rebateBinder.getField(Constants.RS_CATEGORY).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.RS_CATEGORY).getValue()).getId() : 0);
                rsMasterInfo.setRsTradeClass(rebateBinder.getField(Constants.TRADE_CLASS).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.TRADE_CLASS).getValue()).getId() : 0);
                rsMasterInfo.setRsTransRefNo(String.valueOf(rebateBinder.getField(Constants.RS_TRANS_REF_NO).getValue()));
                rsMasterInfo.setRsTransRefName(String.valueOf(rebateBinder.getField(Constants.RS_TRANS_REF_NAME).getValue()));
                
                if (rebateBinder.getField(Constants.RS_DESIGNATION).getValue() != null) {
                    rsMasterInfo.setRsDesignation(String.valueOf(((HelperDTO) rebateBinder.getField(Constants.RS_DESIGNATION).getValue()).getId()));
                } else {
                    rsMasterInfo.setRsDesignation(String.valueOf("0"));
                }
                rsMasterInfo.setParentRsId(String.valueOf(rebateBinder.getField(Constants.PARENT_RS_ID).getValue()));
                rsMasterInfo.setParentRsName(String.valueOf(rebateBinder.getField(Constants.PARENT_RS_NAME).getValue()));
                rsMasterInfo.setInterestBearingBasis((rebateBinder.getField(Constants.INTERSETING_BEARING_BASIS).getValue()) == null
                        ? 0 : (((HelperDTO) rebateBinder.getField(Constants.INTERSETING_BEARING_BASIS).getValue()).getId()));
                rsMasterInfo.setCalculationType(rebateBinder.getField(Constants.CALCULATION_TYPE).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.CALCULATION_TYPE).getValue()).getId() : 0);
                rsMasterInfo.setCalculationLevel(rebateBinder.getField(Constants.CALCULATION_LEVEL).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.CALCULATION_LEVEL).getValue()).getId() : 0);
                if (!"0".equals(String.valueOf(rebateBinder.getField(Constants.CALCULATION_SYSTEM_ID).getValue()).trim()) && !(String.valueOf(rebateBinder.getField(Constants.CALCULATION_SYSTEM_ID).getValue()).trim()).isEmpty()) {
                    rsMasterInfo.setCalculationRule(String.valueOf(rebateBinder.getField(Constants.CALCULATION_SYSTEM_ID).getValue()));
                } else {
                    rsMasterInfo.setCalculationRule(null);
                }
                rsMasterInfo.setCalculationRuleLevel(rebateBinder.getField(Constants.CALCULATION_RULE_LEVEL).getValue() != null ? String.valueOf(((HelperDTO) rebateBinder.getField(Constants.CALCULATION_RULE_LEVEL).getValue()).getId()) : String.valueOf(0));
                rsMasterInfo.setEvaluationRuleType(rebateBinder.getField(Constants.EVALUATION_RULE_TYPE).getValue() != null ? String.valueOf(((HelperDTO) rebateBinder.getField(Constants.EVALUATION_RULE_TYPE).getValue()).getId()) : String.valueOf(0));
                rsMasterInfo.setEvaluationRuleLevel(rebateBinder.getField(Constants.EVALUATION_RULE_LEVEL).getValue() != null ? String.valueOf(((HelperDTO) rebateBinder.getField(Constants.EVALUATION_RULE_LEVEL).getValue()).getId()) : String.valueOf(0));
                if (!"0".equals(String.valueOf(rebateBinder.getField(Constants.EVALUATION_SYSTEM_ID).getValue()).trim()) && !(String.valueOf(rebateBinder.getField(Constants.EVALUATION_SYSTEM_ID).getValue()).trim()).isEmpty()) {
                    rsMasterInfo.setEvaluationRuleOrAssociation(String.valueOf(rebateBinder.getField(Constants.EVALUATION_SYSTEM_ID).getValue()));
                } else {
                    rsMasterInfo.setEvaluationRuleOrAssociation(null);
                }
                dao.updateRsMasterAttached(rsMasterInfo);
                Udcs udc;
                DynamicQuery query = DynamicQueryFactoryUtil.forClass(Udcs.class);
                query.add(RestrictionsFactoryUtil.eq("masterSid", rsSystemId));
                query.add(RestrictionsFactoryUtil.eq("masterType", ContractUtils.RS_CONTRACT));
                final List<Udcs> udcsList = UdcsLocalServiceUtil.dynamicQuery(query);
                if (udcsList.isEmpty() || udcsList.get(0) == null) {
                    udc = UdcsLocalServiceUtil.createUdcs(0);
                } else {
                    udc = UdcsLocalServiceUtil.getUdcs(udcsList.get(0).getUdcsSid());
                }
                udc.setMasterSid(rsSystemId);
                udc.setMasterType(ContractUtils.RS_CONTRACT);
                udc.setUdc1(rebateBinder.getField(Constants.UDC1).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.UDC1).getValue()).getId() : 0);
                udc.setUdc2(rebateBinder.getField(Constants.UDC2).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.UDC2).getValue()).getId() : 0);
                udc.setUdc3(rebateBinder.getField(Constants.UDC3).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.UDC3).getValue()).getId() : 0);
                udc.setUdc4(rebateBinder.getField(Constants.UDC4).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.UDC4).getValue()).getId() : 0);
                udc.setUdc5(rebateBinder.getField(Constants.UDC5).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.UDC5).getValue()).getId() : 0);
                udc.setUdc6(rebateBinder.getField(Constants.UDC6).getValue() != null ? ((HelperDTO) rebateBinder.getField(Constants.UDC6).getValue()).getId() : 0);
                if (udcsList.isEmpty()) {
                    UdcsLocalServiceUtil.addUdcs(udc);
                } else {
                    final Udcs udcsID = udcsList.get(0);
                    udc.setUdcsSid(udcsID.getUdcsSid());
                    UdcsLocalServiceUtil.updateUdcs(udc);
                }
                
            }
            
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
        }
        LOGGER.info("End of saveRSDetails method");
    }
    
    @SuppressWarnings("unchecked")
    private void savePSDetails(final List<VwContractPriceInfoDTO> itemDetailsRB, final int contractSystemId, final int cfpSystemId, final int ifpSystemId, final int psSystemId) throws SystemException {
        LOGGER.info("Entering savePSDetails method");
        /*
         if (itemDetailsRB != null) {
         @SuppressWarnings("rawtypes")
         final Collection idList = new ArrayList();
         for (int i = 0; i < itemDetailsRB.size(); i++) {
         final VwContractPriceInfoDTO vwContractPID = itemDetailsRB.get(i);
         if (!StringUtils.isEmpty(vwContractPID.getItemSystemId())) {
         idList.add(Integer.parseInt(vwContractPID.getItemSystemId()));
         }
         }
         final DynamicQuery deleteCfpQuery = DynamicQueryFactoryUtil.forClass(PsDetailsAttached.class);
         final Criterion criteria = RestrictionsFactoryUtil.in(Constants.ITEM_SYSTEM_ID, idList);
         deleteCfpQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_SYSTEM_ID, contractSystemId));
         deleteCfpQuery.add(RestrictionsFactoryUtil.eq(Constants.IFP_SYSTEM_ID, ifpSystemId));
         deleteCfpQuery.add(RestrictionsFactoryUtil.eq(Constants.PS_ID, psSystemId));
         deleteCfpQuery.add(RestrictionsFactoryUtil.not(criteria));
         final List<PsDetailsAttached> delList = dao.psDetailsDynamicQuery(deleteCfpQuery);
         for (final Iterator<PsDetailsAttached> it = delList.iterator(); it.hasNext();) {
         final PsDetailsAttached psDetailsAttached = it.next();
         dao.deletePsDetailsAttached(psDetailsAttached);
         }
         for (int i = 0; i < itemDetailsRB.size(); i++) {
         final VwContractPriceInfoDTO contractPIDTO = itemDetailsRB.get(i);
         final PsDetailsAttached psDetailsAttached = new PsDetailsAttachedImpl();
         psDetailsAttached.setContractSystemId(contractSystemId);
         psDetailsAttached.setCompanyFamilyplanSystemId(cfpSystemId);
         psDetailsAttached.setItemFamilyplanSystemId(ifpSystemId);
         psDetailsAttached.setPriceScheduleSystemId(psSystemId);
         psDetailsAttached.setItemSystemId(Integer.valueOf(contractPIDTO.getItemSystemId()));
         psDetailsAttached.setAttachedStatus(contractPIDTO.getAttachedStatus());// ---Hardcoded
         // Active
         psDetailsAttached.setAttachedDate(contractPIDTO.getAttachedDate());
         psDetailsAttached.setPriceType(contractPIDTO.getPriceType());
         if (contractPIDTO.getPriceTolerance() == null && StringUtils.EMPTY.equals(contractPIDTO.getPriceTolerance())) {
         psDetailsAttached.setPriceTolerance(0);
         } else {
         psDetailsAttached.setPriceTolerance(Double.valueOf(contractPIDTO.getPriceTolerance()));
         }
         psDetailsAttached.setPriceToleranceType(contractPIDTO.getPriceToleranceType());
         if (contractPIDTO.getPriceToleranceInterval() == null && StringUtils.EMPTY.equals(contractPIDTO.getPriceToleranceInterval())) {
         psDetailsAttached.setPriceToleranceInterval(0);
         } else {
         psDetailsAttached.setPriceToleranceInterval(Integer.valueOf(contractPIDTO.getPriceToleranceInterval()));
         }
         psDetailsAttached.setPriceToleranceFrequency(contractPIDTO.getPriceToleranceFrequency());
         psDetailsAttached.setContractPriceStartDate(contractPIDTO.getCpStartDate());
         psDetailsAttached.setContractPriceEndDate(contractPIDTO.getCpEndDate());
        
         if (contractPIDTO.getContractprice() == null && StringUtils.EMPTY.equals(contractPIDTO.getContractprice())) {
         psDetailsAttached.setContractPrice(0);
         } else {
         psDetailsAttached.setContractPrice(Double.valueOf(contractPIDTO.getContractprice()));
         }
         if (contractPIDTO.getPrice() == null && StringUtils.EMPTY.equals(contractPIDTO.getPrice())) {
         psDetailsAttached.setPrice(0);
         } else {
         psDetailsAttached.setPrice(Double.valueOf(contractPIDTO.getPrice()));
         }
         psDetailsAttached.setPriceProtectionStartDate(contractPIDTO.getPpStartDate());
         psDetailsAttached.setPriceProtectionEndDate(contractPIDTO.getPpEndDate());

         if (contractPIDTO.getBasePrice() == null && StringUtils.EMPTY.equals(contractPIDTO.getBasePrice())) {
         psDetailsAttached.setBasePrice(0);
         } else {
         psDetailsAttached.setBasePrice(Double.valueOf(contractPIDTO.getBasePrice()));
         }

         psDetailsAttached.setCreatedDate(CURRENTDate);
         psDetailsAttached.setRecordLockStatus(Constants.UN_LOCKED);
         if (StringUtils.isEmpty(contractPIDTO.getPsDetailsSystemId())) {
         LOGGER.info(" addPsDetailsAttached()");
         dao.addPsDetailsAttached(psDetailsAttached);
         } else {
         psDetailsAttached.setPsDetailsSystemId(Integer.valueOf(contractPIDTO.getPsDetailsSystemId()));
         LOGGER.info(" updatePsDetailsAttached()");
         dao.updatePsDetailsAttached(psDetailsAttached);
         }
         }
         LOGGER.info("End of savePSDetails method");
         } */
    }

    /**
     * Method used for Save ifp details.
     *
     * @param itemDetailsRB the item details rb
     * @param contractSystemId the contract system id
     * @param cfpSystemId the cfp system id
     * @param ifpSystemId the ifp system id
     * @throws SystemException the system exception
     */
    private void saveIFPDetails(final List<VwContractPriceInfoDTO> itemDetailsRB, final int contractSystemId, final int cfpSystemId, final int ifpSystemId, final int psSystemId, final int rsSystemId) throws SystemException {
        LOGGER.info("Entering saveIFPDetails method");
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        
        if (ifpSystemId != 0) {
            final List<Object> input = new ArrayList<Object>(9);
            input.add(userId);
            input.add(sessionId);
            input.add(userId);
            input.add(sessionId);
            input.add(userId);
            input.add(sessionId);
            input.add(ifpSystemId);
            input.add(ifpSystemId);
            ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, "com.contractDashboard.process.saveIFP");
            
        }
        
        if (psSystemId != 0) {
            final List<Object> psMap = new ArrayList<Object>(9);
            psMap.add(userId);
            psMap.add(sessionId);
            psMap.add(userId);
            psMap.add(sessionId);
            psMap.add(userId);
            psMap.add(sessionId);
            psMap.add(psSystemId);
            psMap.add(psSystemId);
            ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(psMap, "com.contractDashboard.process.savePS");
        }
        if (rsSystemId != 0) {
            final List<Object> input = new ArrayList<Object>(9);
            input.add(userId);
            input.add(sessionId);
            input.add(userId);
            input.add(sessionId);
            input.add(userId);
            input.add(sessionId);
            input.add(rsSystemId);
            input.add(rsSystemId);
            input.add(userId);
            input.add(sessionId);
            ImtdItemPriceRebateDetailsLocalServiceUtil.saveItem(input, "com.contractDashboard.process.saveRS");
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
            query.add(RestrictionsFactoryUtil.eq("rsContractSid", rsSystemId));
            query.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            
            List<RsContractDetails> list = RsContractDetailsLocalServiceUtil.dynamicQuery(query);
            
            for (RsContractDetails bean : list) {
                
                ImtdItemPriceRebateDetailsLocalServiceUtil.mergeImtdRsContractDetailsFormula(bean.getRsContractDetailsSid(), bean.getItemMasterSid(), userId, sessionId);
            }
        }
        LOGGER.info("End of saveIFPDetails method");
    }

    /**
     * To Save CFP Details
     *
     * @param list
     * @param contractSystemId
     * @param cfpContractSId
     * @throws SystemException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public void saveCFPDetails(final List<CFPCompanyDTO> list, final int contractSystemId, final int cfpContractSId) throws SystemException, ParseException, java.text.ParseException {
        LOGGER.info("Entering saveCFPDetails method");
        
        final VaadinSession current = VaadinSession.getCurrent();
        final String userId = String.valueOf(current.getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List<Object> input = new ArrayList<Object>(8);
        input.add(userId);
        input.add(sessionId);
        input.add(userId);
        input.add(sessionId);
        input.add(userId);
        input.add(sessionId);
        input.add(cfpContractSId);
        input.add(cfpContractSId);
        input.add(userId);
        input.add(sessionId);
        
        ImtdCfpDetailsLocalServiceUtil.saveCompany(input, "com.contractDashboard.process.saveCFP");
        LOGGER.info("End of saveCFPDetails method");
    }

    /**
     * Method used for Gets the overlapped company list.
     *
     * @param selectedCL the selected cl
     * @param contractSystemId the contract system id
     * @return the overlapped company list
     * @throws SystemException
     */
    public List getOverlappedCompanyList(final List<CFPCompanyDTO> selectedCL, final int contractSystemId) throws SystemException {
        LOGGER.info("Entering getOverlappedCompanyList method with contractSystemId=" + contractSystemId);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final List overlapList = new ArrayList();
        final List companyList = new ArrayList();
        for (int i = 0; i < selectedCL.size(); i++) {
            final CFPCompanyDTO dto = selectedCL.get(i);
            companyList.add(Integer.valueOf(dto.getCompanySystemId()));
        }
        
        final DynamicQuery cfpQuery = DynamicQueryFactoryUtil.forClass(CfpContractDetails.class);
        cfpQuery.add(RestrictionsFactoryUtil.ne(Constants.CONTRACT_SYSTEM_ID, contractSystemId));
        cfpQuery.add(RestrictionsFactoryUtil.in(Constants.TRADING_PARTNER_SYS_ID, companyList));
        List<Object> input = new ArrayList<Object>();
        input.add(userId);
        input.add(sessionId);
        final List<CfpContractDetails> delList = getCustomizedCFP((List<Object[]>) ImtdCfpDetailsLocalServiceUtil.getOverlapedCompanies(input, null));
        String companyDupId[] = new String[delList.size()];
        long companyDSD[] = new long[delList.size()];
        long companyDED[] = new long[delList.size()];
        for (int j = 0; j < delList.size(); j++) {
            final CfpContractDetails cfpDetailsAtt = delList.get(j);
            companyDupId[j] = String.valueOf(cfpDetailsAtt.getCompanyMasterSid());
            companyDSD[j] = (cfpDetailsAtt.getCompanyStartDate() == null || cfpDetailsAtt.getCompanyStartDate().toString().equals("") ? 0 : ((Date) cfpDetailsAtt.getCompanyStartDate()).getTime());
            companyDED[j] = (cfpDetailsAtt.getCompanyEndDate() == null || cfpDetailsAtt.getCompanyEndDate().toString().equals("") ? 0 : ((Date) cfpDetailsAtt.getCompanyEndDate()).getTime());
        }
        for (int i = 0; i < selectedCL.size(); i++) {
            final CFPCompanyDTO dtoObj = (CFPCompanyDTO) selectedCL.get(i);
            final String companyId = dtoObj.getCompanySystemId();
            final long companyStartDate = dtoObj.getCompanyFamilyPlanStartDate() == null || dtoObj.getCompanyFamilyPlanStartDate().equals("") ? 0 : dtoObj.getCompanyFamilyPlanStartDate().getTime();
            final long companyEndDate = dtoObj.getCompanyFamilyPlanEndDate() == null || dtoObj.getCompanyFamilyPlanEndDate().equals("") ? 0 : dtoObj.getCompanyFamilyPlanEndDate().getTime();
            for (int s = 0; s < companyDupId.length; s++) {
                if (companyId.equals(companyDupId[s])) {
                    if (companyDED[s] == ContractUtils.ZERO) {
                        if (!overlapList.contains(dtoObj.getCompanyNo())) {
                            overlapList.add(dtoObj.getCompanyNo());
                        }
                    } else if (companyDED[s] != ContractUtils.ZERO && companyStartDate == companyDSD[s] || companyStartDate <= companyDSD[s] && companyEndDate != ContractUtils.ZERO
                            && companyEndDate >= companyDSD[s] || companyStartDate >= companyDSD[s] && companyStartDate <= companyDED[s]) {
                        if (!overlapList.contains(dtoObj.getCompanyNo())) {
                            overlapList.add(dtoObj.getCompanyNo());
                        }
                    }
                }
            }
        }
        
        LOGGER.info("End of getOverlappedCompanyList method");
        return overlapList;
    }
    
    public static String loadDescription(int code) {
        String descValue = "";
        try {
            HelperTable description = HelperTableLocalServiceUtil.getHelperTable(code);
            descValue = description.getDescription();
        } catch (PortalException ex) {
            LOGGER.error(ex.getMessage());;
            java.util.logging.Logger.getLogger(DashBoardLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());;
            java.util.logging.Logger.getLogger(DashBoardLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return descValue;
    }
    
    public static int getHelperCode(String listName, String description) {
        int codeId = 0;
        try {
            
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.LIST_NAME, listName));
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.DESCRIPTION, description));
            ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
            projectonList.add(ProjectionFactoryUtil.property(Constants.HELPER_TABLE_SID));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectonList));
            List<Integer> listInt = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (!listInt.isEmpty()) {
                for (int i = 0; i < listInt.size(); i++) {
                    
                    codeId = listInt.get(0);
                }
            }
            
        } catch (SystemException ex) {
            
            LOGGER.error(ex.getMessage());;
        }
        return codeId;
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the list< cfp details attached>
     * @throws SystemException the system exception
     */
    public List<CfpContractDetails> cfpDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        
        return dao.cfpDetailsDynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the list
     * @throws SystemException the system exception
     */
    public List psDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        
        return dao.psDetailsDynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the list
     * @throws SystemException the system exception
     */
    public List rsDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        
        return dao.rsDetailsDynamicQuery(dynamicQuery);
    }

    /**
     * Returns the contract master with the primary key.
     *
     * @param contractId the contract id
     * @return the contract master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public ContractMaster getContractMaster(final int contractId) throws SystemException, PortalException {
        
        return dao.getContractMaster(contractId);
        
    }

    /**
     * Performs a dynamic query on the PriceScheduleDetails table and returns
     * the matching rows.
     *
     * @param dynamicQuery to get the data from PriceScheduleDetails table
     * @return list of data retrieved from the PriceScheduleDetails table
     * @throws SystemException the system exception
     */
    public List priceScheduleDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        
        return dao.priceScheduleDetailsDynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery o get the data from RebateScheduleDetails
     * @return list of data retrieved from the RebateScheduleDetails
     * @throws SystemException the system exception
     */
    public List rebateScheduleDetailsDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
        
        return dao.rebateScheduleDetailsDynamicQuery(dynamicQuery);
    }
    
    private List<CfpContractDetails> getCustomizedCFP(List<Object[]> resultList) {
        List<CfpContractDetails> retList = new ArrayList<CfpContractDetails>();
        for (Object[] temp : resultList) {
            CfpContractDetails tempCfp = CfpContractDetailsLocalServiceUtil.createCfpContractDetails(0);
            tempCfp.setCompanyMasterSid((Integer) temp[0]);
            tempCfp.setCompanyStartDate((Date) temp[1]);
            tempCfp.setCompanyEndDate((Date) temp[2]);
        }
        return retList;
    }
    
    private String getPricingQualifierName(Integer pricingQualifSId) throws PortalException, SystemException {
        final ItemPricingQualifier result = ItemPricingQualifierLocalServiceUtil.getItemPricingQualifier(pricingQualifSId);
        String qualifierName = StringUtils.isBlank(result.getItemPricingQualifierName()) ? StringUtils.EMPTY : result.getItemPricingQualifierName();
        return qualifierName;
    }

    /**
     * Method to Delete the Uploaded File
     *
     * @param docDetailsId
     * @param moduleName
     * @param fileName
     * @return true or false
     */
    public void deleteUploadedFileList(List<AdditionalInfoDTO> tableBeanList) {
        for (AdditionalInfoDTO obj : tableBeanList) {
            if (obj.getDocDetailsId() != 0) {
                try {
                    MasterDataFilesLocalServiceUtil.deleteMasterDataFiles(obj.getDocDetailsId());
                } catch (PortalException ex) {
                    java.util.logging.Logger.getLogger(DashBoardLogic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(DashBoardLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            File file = new File(obj.getDocumentFullPath());
            file.delete();
        }
    }
    
    public static int getLazyTierFormulaIdCount(String filter, HelperDTO helper) {
        List<Object[]> list = null;
        try {
            filter = StringUtils.trimToEmpty(filter);
            LOGGER.info("Entering getLazyTierFormulaIdCount method with filterText :" + filter);
            
            final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
            if (!filter.isEmpty()) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.eq("formulaId", Integer.parseInt(filter)));
            }
            rsDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct("formulaId"));
            rsDynamicQuery.add(RestrictionsFactoryUtil.ne("formulaId", 0));
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNotNull("formulaId"));
            if (helper != null && helper.getId() != 0) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.ne("formulaId", helper.getId()));
            }
            list = RsContractDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
//                DAO.getFormulaIdList(rsDynamicQuery);
            formulaIdCount = Integer.parseInt(String.valueOf(list.get(0)));
            LOGGER.info("Ending getLazyTierFormulaIdCount method : returning count :" + formulaIdCount);
            
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());;
        }
        return Integer.parseInt(String.valueOf(list.get(0)));
    }
    
    public static List<HelperDTO> getLazyTierFormulaIdResults(final int startIndex, final int end, String filter, final HelperDTO helper) {
        List<String> qualifierList;
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        try {
            filter = StringUtils.trimToEmpty(filter);
            LOGGER.info("Entering getLazyTierFormulaIdResults method with filterText" + filter);
            final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
            rsDynamicQuery.setLimit(startIndex, end);
            rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("formulaId")));
            rsDynamicQuery.addOrder(OrderFactoryUtil.asc("formulaId"));
            if (!filter.isEmpty()) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.eq("formulaId", Integer.parseInt(filter)));
            }
            if (helper != null && helper.getId() != 0) {
                rsDynamicQuery.add(RestrictionsFactoryUtil.ne("formulaId", helper.getId()));
            }
            rsDynamicQuery.add(RestrictionsFactoryUtil.ne("formulaId", 0));
            rsDynamicQuery.add(RestrictionsFactoryUtil.isNotNull("formulaId"));
            
            qualifierList = RsContractDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
            
            HelperDTO dto;
            if (startIndex == Constants.ZERO) {
                dto = new HelperDTO();
                dto.setDescription("-Select One-");
                list.add(dto);
            }
            for (final Iterator<String> iterator = qualifierList.iterator(); iterator.hasNext();) {
                final String value = String.valueOf(iterator.next());
                dto = new HelperDTO(value == null ? StringUtils.EMPTY : value);
                if (!StringUtils.EMPTY.equals(dto.getDescription())) {
                    list.add(dto);
                }
            }
            LOGGER.info("Ending getLazyTierFormulaIdResults method : returning CompanyQualifier size :" + list.size());
            
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());;
        }
        return list;
        
    }
    
    public String getInternalNotesHistory(int contractSystemId) throws PortalException, SystemException {
        ContractMaster contractMaster = ContractMasterLocalServiceUtil.getContractMaster(contractSystemId);
        return contractMaster.getInternalNotes();
    }
    
    public boolean validateContractToProcess(ContractMaster contractMaster) {
        boolean validateResult = false;
        String query = "";
        List resultList;
        query = queryUtil.getContractActualsValidationQuery(contractMaster.getContractId());
        resultList = dao.executeSelectQuery(query);
        if ((resultList != null ? Integer.valueOf(String.valueOf(resultList.get(0))) : 0) > 0) {
            return true;
        } else {
            query = queryUtil.getForecastTypeContractValidationQuery(contractMaster.getContractMasterSid());
            resultList = dao.executeSelectQuery(query);
            if (resultList != null) {
                for (Object object : resultList) {
                    String type = (String) object;
                    if ("Non Mandated".equals(type)) {
                        query = queryUtil.getNMSalesContractValidationQuery(contractMaster.getContractMasterSid());
                        resultList = dao.executeSelectQuery(query);
                        if ((resultList != null ? Integer.valueOf(String.valueOf(resultList.get(0))) : 0) > 0) {
                            return true;
                        }
                        query = queryUtil.getNMSalesContractValidationQuery(contractMaster.getContractMasterSid());
                        resultList = dao.executeSelectQuery(query);
                        if ((resultList != null ? Integer.valueOf(String.valueOf(resultList.get(0))) : 0) > 0) {
                            return true;
                        }
                    } else if ("Mandated".equals(type)) {
                        query = queryUtil.getMSalesContractValidationQuery(contractMaster.getContractMasterSid());
                        resultList = dao.executeSelectQuery(query);
                        if ((resultList != null ? Integer.valueOf(String.valueOf(resultList.get(0))) : 0) > 0) {
                            return true;
                        }
                        query = queryUtil.getMDiscountContractValidationQuery(contractMaster.getContractMasterSid());
                        resultList = dao.executeSelectQuery(query);
                        if ((resultList != null ? Integer.valueOf(String.valueOf(resultList.get(0))) : 0) > 0) {
                            return true;
                        }
                    } else if ("Channel".equals(type)) {
                        query = queryUtil.getCHSalesContractValidationQuery(contractMaster.getContractMasterSid());
                        resultList = dao.executeSelectQuery(query);
                        if ((resultList != null ? Integer.valueOf(String.valueOf(resultList.get(0))) : 0) > 0) {
                            return true;
                        }
                        query = queryUtil.getCHDiscountContractValidationQuery(contractMaster.getContractMasterSid());
                        resultList = dao.executeSelectQuery(query);
                        if ((resultList != null ? Integer.valueOf(String.valueOf(resultList.get(0))) : 0) > 0) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return validateResult;
    }
    
    public static int getPriceTypeCount(final String filterText, final HelperDTO priceType) throws PortalException, SystemException {
        final String filter = StringUtils.trimToEmpty(filterText) + "%";
        LOGGER.debug("Entering getLazyPriceTypeCount method with filterText :" + filterText);
        List<Object[]> qualifierList;
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        if (priceType != null && priceType.getId() != 0) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, priceType.getId()));
        }
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_PRICING_QUAL_NAME, filter));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        qualifierList = dao.getItemPricingTypeList(cfpDynamicQuery);
        LOGGER.debug("Ending getLazyPriceTypeCount method with filterText with count :" + qualifierList.get(0));
        return Integer.parseInt(String.valueOf(qualifierList.get(0)));
    }
    
    public static List<HelperDTO> getPriceTypeResults(final int startIndex, final int end, final String filter, final HelperDTO priceType) throws PortalException, SystemException {
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        LOGGER.debug("Entering getLazyPriceTypeResults method with filterText :" + filter);
        final String filterString = StringUtils.trimToEmpty(filter) + "%";
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        cfpDynamicQuery.setLimit(startIndex, end);
        if (priceType != null && priceType.getId() != 0) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID, priceType.getId()));
        }
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
        cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        if (filter != null) {
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.ITEM_PRICING_QUAL_NAME, filterString));
        }
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
        cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
        final List<Object[]> returnList = dao.getItemPricingTypeList(cfpDynamicQuery);
        
        HelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            helperTable = new HelperDTO();
            helperTable.setId(0);
            helperTable.setDescription(ConstantsUtils.SELECT_ONE);
            list.add(helperTable);
            if (priceType != null && priceType.getId() != 0 && filter == null) {
                list.add(priceType);
            }
        }
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new HelperDTO();
            helperTable.setId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        LOGGER.debug("Ending getLazyPriceTypeResults  return list size :" + +list.size());
        return list;
    }

    /**
     *
     * @param rebatePlanDTO
     * @param start
     * @param offset
     * @param isCount
     * @param filterSet
     * @param columns
     * @return
     */
    public Object loadRebatePlan(final RebatePlanDTO rebatePlanDTO, final int start, final int offset, final boolean isCount, final Set<Filter> filterSet, List<SortByColumn> columns) {
        
        String rebatePlanID = rebatePlanDTO.getRebatePlanId();
        String rebatePlanNo = rebatePlanDTO.getRebatePlanNo();
        String rebatePlanName = rebatePlanDTO.getRebatePlanName();
        int rebatePlanStatus = rebatePlanDTO.getRebatePlanStatus() != null ? rebatePlanDTO.getRebatePlanStatus().getId() : 0;
        int rebatePlanType = rebatePlanDTO.getRebatePlanType() != null ? rebatePlanDTO.getRebatePlanType().getId() : 0;
        String netSalesFormula = rebatePlanDTO.getNetSalesFormula();
        String netSalesRule = rebatePlanDTO.getNetSalesRule();
        int createdBy = 0;
        int modifiedBy = 0;
        int rebateBasedOn = rebatePlanDTO.getRebateBasedOn() != null ? rebatePlanDTO.getRebateBasedOn().getId() : 0;
        int rangeBasedOn = rebatePlanDTO.getRangeBasedOn() != null ? rebatePlanDTO.getRangeBasedOn().getId() : 0;
        int rebateStructure = rebatePlanDTO.getRebateStructure() != null ? rebatePlanDTO.getRebateStructure().getId() : 0;
        Map<String, Object> filterCriteria = new HashMap<>();
        
        String column = "RP.REBATE_PLAN_TYPE";
        String orderBy = "ASC";
        
        if (columns != null) {
            for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                column = ContractUtils.getRebatePlanLookupColumnMap(orderByColumn.getName());
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    orderBy = "ASC";
                } else {
                    orderBy = "DESC";
                }
            }
        }
        if (!(filterSet == null || filterSet.isEmpty())) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    Object propertyId = stringFilter.getPropertyId();
                    switch (propertyId.toString()) {
                        
                        case "rebatePlanId":
                            rebatePlanID = ContractUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", ContractUtils.PERCENCTAGE) : filterString) + ContractUtils.PERCENCTAGE;
                            
                            break;
                        case "rebatePlanNo":
                            rebatePlanNo = ContractUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", ContractUtils.PERCENCTAGE) : filterString) + ContractUtils.PERCENCTAGE;
                            break;
                        case "rebatePlanName":
                            rebatePlanName = ContractUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", ContractUtils.PERCENCTAGE) : filterString) + ContractUtils.PERCENCTAGE;
                            break;
                        case "rebatePlanStatus":
                            rebatePlanStatus = Integer.valueOf(filterString);
                            break;
                        case "rebatePlanType":
                            rebatePlanType = Integer.valueOf(filterString);
                            break;
                        case "rangeBasedOn":
                            rangeBasedOn = Integer.valueOf(filterString);
                            break;
                        case "rebateStructure":
                            rebateStructure = Integer.valueOf(filterString);
                            break;
                        case "rebateBasedOn":
                            rebateBasedOn = Integer.valueOf(filterString);
                            break;
                        case "netSalesFormula":
                            netSalesFormula = ContractUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", ContractUtils.PERCENCTAGE) : filterString) + ContractUtils.PERCENCTAGE;
                            break;
                        case "netSalesRule":
                            netSalesRule = ContractUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", ContractUtils.PERCENCTAGE) : filterString) + ContractUtils.PERCENCTAGE;
                            break;
                        case "createdBy":
                            createdBy = Integer.valueOf(filterString);
                            break;
                        case "modifiedBy":
                            modifiedBy = Integer.valueOf(filterString);
                            break;
                        default:
                            break;
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());
                    if (startValue != null) {
                        filterCriteria.put(stringFilter.getPropertyId() + Constants.FROM, (" >= '" + startValue + "'"));
                    }
                    if (endValue != null) {
                        filterCriteria.put(stringFilter.getPropertyId() + Constants.TO, (" <= '" + endValue + "'"));
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            filterCriteria.put(stringFilter.getPropertyId().toString(), (" >= '" + filterString + "'"));
                        } else {
                            filterCriteria.put(stringFilter.getPropertyId().toString(), (" <= '" + filterString + "'"));
                        }
                    }
                }
            }
        }
        String query;
        if (isCount) {
            query = CustomSQLUtil.get("com.rsModel.rebatePlanLookUpCount");
        } else {
            query = CustomSQLUtil.get("com.rsModel.rebatePlanLookUpResults");
        }
        
        query = query.replace("@REBATE_PLAN_ID", StringUtils.isBlank(rebatePlanID) ? ContractUtils.PERCENCTAGE : replaceForWildCardSearch(rebatePlanID));
        query = query.replace("@REBATE_PLAN_NO", StringUtils.isBlank(rebatePlanNo) ? ContractUtils.PERCENCTAGE : replaceForWildCardSearch(rebatePlanNo));
        query = query.replace("@REBATE_PLAN_NAME", StringUtils.isBlank(rebatePlanName) ? ContractUtils.PERCENCTAGE : replaceForWildCardSearch(rebatePlanName));
        
        if (rebatePlanStatus != 0) {
            query += "AND RP.REBATE_PLAN_STATUS = " + rebatePlanStatus + " \n";
        }
        if (rebatePlanType != 0) {
            query += "AND RP.REBATE_PLAN_TYPE = " + rebatePlanType + " \n";
        }
        if (rebateStructure != 0) {
            query += "AND RP.REBATE_STRUCTURE = " + rebateStructure + " \n";
        }
        if (rangeBasedOn != 0) {
            query += "AND RP.REBATE_RANGE_BASED_ON = " + rangeBasedOn + " \n";
        }
        if (rebateBasedOn != 0) {
            query += "AND RP.REBATE_BASED_ON = " + rebateBasedOn + " \n";
        }
        
        if (!netSalesFormula.equals(StringUtils.EMPTY)) {
            query += "AND NSF.NET_SALES_FORMULA_NAME LIKE '" + netSalesFormula + "'\n";
        }
        if (!netSalesRule.equals(StringUtils.EMPTY)) {
            query += "AND CDR.RULE_NAME LIKE '" + netSalesRule + "'\n";
        }
        
        if (createdBy != 0) {
            query += "AND  RP.CREATED_BY LIKE '" + createdBy + "'\n";
        }
        if (modifiedBy != 0) {
            query += "AND  RP.MODIFIED_BY LIKE '" + modifiedBy + "'\n";
        }
        if (filterCriteria.get("createdDate" + Constants.FROM) != null) {
            query += " AND RP.CREATED_DATE ";
            query += String.valueOf(filterCriteria.get("createdDate" + Constants.FROM));
        }
        if (filterCriteria.get("createdDate" + Constants.TO) != null) {
            query += " AND  RP.CREATED_DATE ";
            query += String.valueOf(filterCriteria.get("createdDate" + Constants.TO));
        }
        if (filterCriteria.get("createdDate") != null) {
            query += " AND  RP.CREATED_DATE  ";
            query += String.valueOf(filterCriteria.get("createdDate"));
        }
        if (filterCriteria.get("modifiedDate" + Constants.FROM) != null) {
            query += " AND  RP.MODIFIED_DATE  ";
            query += String.valueOf(filterCriteria.get("modifiedDate" + Constants.FROM));
        }
        if (filterCriteria.get("modifiedDate" + Constants.TO) != null) {
            query += " AND  RP.MODIFIED_DATE  ";
            query += String.valueOf(filterCriteria.get("modifiedDate" + Constants.TO));
        }
        if (filterCriteria.get("modifiedDate") != null) {
            query += " AND  RP.MODIFIED_DATE  ";
            query += String.valueOf(filterCriteria.get("modifiedDate"));
        }
        if (!isCount) {
            query += "ORDER BY " + column + " " + orderBy + " \n"
                    + "OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        }
        
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return convertRebatePlanList(resultList);
        }
        
    }
    
    private String replaceForWildCardSearch(String input) {
        if (StringUtils.isNotBlank(input)) {
            input = input.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        return input;
    }

    /**
     * Converts the list of objects to list of RebatePlanDTO.
     *
     * @param list List<Object[]>
     * @return List<RebatePlanDTO>
     */
    private List<RebatePlanDTO> convertRebatePlanList(final List<Object[]> list) {
        
        List<RebatePlanDTO> resultList = new ArrayList<RebatePlanDTO>();
        for (Object[] object : list) {
            try {
                Map<Integer, String> userMap = StplSecurity.getUserName();
                RebatePlanDTO rebatePlanDTO = new RebatePlanDTO();
                rebatePlanDTO.setRebatePlanSystemId(String.valueOf(object[0]));
                rebatePlanDTO.setRebatePlanId(object[1] == null ? StringUtils.EMPTY : String.valueOf(object[1]));
                rebatePlanDTO.setRebatePlanNo(object[2] == null ? StringUtils.EMPTY : String.valueOf(object[2]));
                rebatePlanDTO.setRebatePlanName(object[3] == null ? StringUtils.EMPTY : String.valueOf(object[3]));
                rebatePlanDTO.setRebatePlanStatus(helperListUtil.getIdHelperDTOMap().get(object[4]));
                rebatePlanDTO.setRebatePlanType(helperListUtil.getIdHelperDTOMap().get(object[5]));
                rebatePlanDTO.setRebateStructure(helperListUtil.getIdHelperDTOMap().get(object[6]));
                rebatePlanDTO.setRangeBasedOn(helperListUtil.getIdHelperDTOMap().get(object[7]));
                rebatePlanDTO.setNetSalesFormula(object[8] == null ? StringUtils.EMPTY : String.valueOf(object[8]));
                rebatePlanDTO.setNetSalesRule(object[9] == null ? StringUtils.EMPTY : String.valueOf(object[9]));
                rebatePlanDTO.setRebateBasedOn(helperListUtil.getIdHelperDTOMap().get(object[10]));
                rebatePlanDTO.setCreatedDateString(formatDate(convertNullToEmpty(String.valueOf(object[11]))));
                rebatePlanDTO.setCreatedDate(parsetDate(convertNullToEmpty(String.valueOf(object[11]))));
                rebatePlanDTO.setModifiedDateString(formatDate(convertNullToEmpty(String.valueOf(object[13]))));
                rebatePlanDTO.setModifiedDate(parsetDate(convertNullToEmpty(String.valueOf(object[13]))));
                rebatePlanDTO.setCreatedBy(userMap.get((int) object[12]) == null ? StringUtils.EMPTY : userMap.get((int) object[12]));
                rebatePlanDTO.setModifiedBy(userMap.get((int) object[14]) == null ? StringUtils.EMPTY : userMap.get((int) object[14]));
                resultList.add(rebatePlanDTO);
            } catch (Exception ex) {
                LOGGER.error("Error in RP look up Customization" + ex.getMessage());
            }
        }
        return resultList;
    }
    
    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || "null".equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }
    
    private static Date parsetDate(String value) throws java.text.ParseException {
        Date date = null;
        String tempDate = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !"null".equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = (format.parse(tempDate));
        }
        return date;
    }
    
    private static String formatDate(String value) throws java.text.ParseException {
        String date = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !"null".equals(value)) {
            date = format.format(parse.parse(value));
        }
        return date;
    }
    
    public List<PriceProtectionFormulaDTO> loadRSDetails(final PriceProtectionFormulaDTO rsFormulaDTO) {
        String query;
        query = CustomSQLUtil.get("loadFormulaDetails");
        
        query = query.replace("@FORECASTING_FORMULA_SID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || "null".equals(rsFormulaDTO.getFormulaID())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaID()));
        query = query.replace("@FORMULA_NO", StringUtils.isBlank(rsFormulaDTO.getFormulaNo()) || "null".equals(rsFormulaDTO.getFormulaNo())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaNo()));
        query = query.replace("@FORMULA_NAME", StringUtils.isBlank(rsFormulaDTO.getFormulaName()) || "null".equals(rsFormulaDTO.getFormulaName())
                ? ConstantsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaName()));
        query = query.replace("@FORMULA_TYPE", rsFormulaDTO.getFormulaType() == null || (com.stpl.app.util.Constants.SELECT_ONE).equals(rsFormulaDTO.getFormulaType()) ? ConstantsUtils.PERCENCTAGE : (String.valueOf(rsFormulaDTO.getFormulaType().getId())));
        query = query.replace("@FORMULA_ID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || "null".equals(rsFormulaDTO.getFormulaID())
                ? ConstantsUtils.PERCENCTAGE : (rsFormulaDTO.getFormulaID()));
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        List<PriceProtectionFormulaDTO> list = getCustomizedRSDetails(resultList);
        return list;
    }
    
    private List<PriceProtectionFormulaDTO> getCustomizedRSDetails(List list) {
        final List<PriceProtectionFormulaDTO> resultList = new ArrayList<>();
        if (list != null) {
            for (Object formulaList : list) {
                PriceProtectionFormulaDTO formulaDto = new PriceProtectionFormulaDTO();
                final String formula = (String) formulaList;
                formulaDto.setFormula(formula);
                resultList.add(formulaDto);
            }
        }
        return resultList;
    }
    
    private String getRuleNameById(int systemId) throws PortalException, SystemException {
        String ruleName = "";
        if (systemId != 0) {
            CdrModel cdrModel = CdrModelLocalServiceUtil.getCdrModel(systemId);
            ruleName = cdrModel.getRuleName();
        }
        return ruleName;
    }
    
    public static String getUserFLName(String userId) throws PortalException, SystemException {
        String name = StringUtils.EMPTY;
        User userInfo = com.stpl.app.contract.contractheader.util.CommonUtils.getUser(userId);
        if (userInfo != null) {
            name = userInfo.getLastName() + ", " + userInfo.getFirstName();
        }
        return name;
    }
    
    public int validateCCPActuals(String companySid) {
        
        final int contractSystemId = (Integer) (sessionDTO.getContractSystemId());
        final int cfpContractSId = (Integer) (sessionDTO.getCfpSystemId());
        final int ifpSystemId = (Integer) (sessionDTO.getIfpSystemId());
        
        if (contractSystemId == 0 || cfpContractSId == 0 || ifpSystemId == 0 || companySid == null || companySid.isEmpty() || Integer.valueOf(companySid) == 0) {
            return 0;
        } else {
            String query = CustomSQLUtil.get("validateCCPActuals");
            query = query.replace("@CONSID", "" + contractSystemId);
            query = query.replace("@CFPSID", "" + cfpContractSId);
            query = query.replace("@IFPSID", "" + ifpSystemId);
            
            query = query.replace("WHERE  ICD.ITEM_MASTER_SID = @ITEMSID", "WHERE  CCD.COMPANY_MASTER_SID = " + companySid + "");
            
            List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            
            return list == null ? 0 : (int) list.get(0);
        }
        
    }
    
    public Boolean saveItem(final List<Object> input, final Object future) {
        Boolean retFlag = false;
        try {
            if ("SaveDetails".equals(future)) {
                List<ImtdItemPriceRebateDetails> saveList = (List<ImtdItemPriceRebateDetails>) input.get(0);
                for (ImtdItemPriceRebateDetails temp : saveList) {
                    ImtdItemPriceRebateDetailsLocalServiceUtil.updateImtdItemPriceRebateDetails(temp);
                }
                retFlag = true;
            } else if ("Validation".equals(future)) {
                
                if (input.contains("Rebate_Amount")) {
                    StringBuilder insertQuery = new StringBuilder();
                    insertQuery.append("SELECT Count(IMTD_ITEM_PRICE_REBATE_SID) FROM dbo.IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID='");
                    insertQuery.append(input.get(0));
                    insertQuery.append("' and SESSION_ID='");
                    insertQuery.append(input.get(1));
                    insertQuery.append("' and ");
                    insertQuery.append("(((Rebate_Amount is not null and Rebate_Amount <> 0) AND (REBATE_PLAN_SYSTEM_ID is not null AND REBATE_PLAN_SYSTEM_ID <> 0)) ");
                    insertQuery.append("OR ((Rebate_Amount is null or Rebate_Amount=0) AND (REBATE_PLAN_SYSTEM_ID is null OR REBATE_PLAN_SYSTEM_ID = 0)))");
                    insertQuery.append("");
                    String sql = insertQuery.toString();
                } else {
                    String sql = CustomSQLUtil.get("com.contractDashboard.process.saveIFPNullValidation");
                    if (input.contains("CONTRACT_PRICE") || input.contains("Price") || input.contains("BASE_PRICE") || input.contains("Rebate_Amount")) {
                        sql = CustomSQLUtil.get("com.contractDashboard.process.saveIFPNnumericValidation");
                    }
                    for (Object temp : input) {
                        sql = sql.replaceFirst("[?]", temp.toString());
                    }
                }
            } else if ("AlphaNumericValidation".equals(future)) {
                String sql = CustomSQLUtil.get("com.contractDashboard.process.saveIFPAlphaNumericValidation");
                for (Object temp : input) {
                    sql = sql.replaceFirst("[?]", temp.toString());
                }
                
                LOGGER.info(" " + sql);
            } else {
                String sql = CustomSQLUtil.get(String.valueOf(future));
                for (Object temp : input) {
                    sql = sql.replaceFirst("[?]", temp.toString());
                }
                
                LOGGER.info("^ " + sql);
                retFlag = true;
            }
            
        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e);
        }
        return retFlag;
    }
    
    private void updateAttachedDate(final List<Object> input) {
        LOGGER.info("Entering Attached Date Update");
        String sql = "SElECT Count(IMTD_ITEM_PRICE_REBATE_SID) FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID = " + input.get(0) + " AND SESSION_ID = '" + input.get(1) + "'";
        int count = (Integer) (HelperTableLocalServiceUtil.executeSelectQuery(sql)).get(0);
        String query = "SElECT ATTACHED_DATE,IMTD_ITEM_PRICE_REBATE_SID FROM IMTD_ITEM_PRICE_REBATE_DETAILS WHERE USERS_SID = " + input.get(0) + " AND SESSION_ID = '" + input.get(1) + "'";
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (Object[] tempObj : list) {
            for (int i = 0; i < tempObj.length; i++) {
                if (tempObj[0] == null) {
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date date = new Date();
                    String updateQuery = "update IMTD_ITEM_PRICE_REBATE_DETAILS set ATTACHED_DATE = getDate()"
                            + " WHERE USERS_SID = \n"
                            + "    " + input.get(0) + " AND SESSION_ID = '" + input.get(1) + "' AND IMTD_ITEM_PRICE_REBATE_SID = " + String.valueOf(tempObj[1]);
                    HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
                }
            }
        }
        LOGGER.info("Ending Attached Date Update");
    }
}
