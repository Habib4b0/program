package com.stpl.app.contract.contractheader.logic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.contract.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.dto.AdditionalInfoDTO;
import com.stpl.app.contract.contractheader.dto.ContractAliasMasterDTO;
import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dao.impl.ContractHeaderLogicDAOImpl;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.global.logic.NotesTabLogic;
import com.stpl.app.contract.ui.UDCIncrementCheck;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.domain.contract.contractheader.ContractHeaderDAO;
import com.stpl.domain.contract.contractheader.HeaderLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;

/**
 * Holds the logic for ContractHeader.
 */
public class ContractHeaderLogic implements HeaderLogic {

    SessionDTO sessionDTO;

    public ContractHeaderLogic() {
    }

    public ContractHeaderLogic(final SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractHeaderLogic.class);
    /**
     * ContractHeaderLogicDAO object.
     */
    private final ContractHeaderDAO dao = new ContractHeaderLogicDAOImpl();
    /**
     * UDCIncrementCheck object.
     */
    /**
     * Date Formatter
     */
    final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private final UDCIncrementCheck udcCheck = new UDCIncrementCheck();
    private final CFPSearchLogic cfpSearchLogic = new CFPSearchLogic();
    private final NotesTabLogic notesLogic = new NotesTabLogic();
    private static HelperListUtil helperListUtil = HelperListUtil.getInstance();

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public ContractHeaderDAO getDao() {
        return dao;
    }

    /**
     * Gets the udc check.
     *
     * @return the udc check
     */
    public UDCIncrementCheck getUdcCheck() {
        return udcCheck;
    }

    /**
     * Returns the contract master based on search Item form.
     *
     * @param searchItemForm the search item form
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the list< contract master dto>
     * @throws PortalException
     */
    @SuppressWarnings("unchecked")
    public List<SearchResultsDTO> searchContractMaster(final CustomFieldGroup searchItemForm, final int start, final int end, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria criteria) throws SystemException,
            PortalException {
        String contractId;
        String contractNo;
        String contractName;
        int contractType;
        int contractStatus;
        String tradeClass;
        int tradingPartner;
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;
        UIUtils.loadColumnName();
        Map<String, Object> filterMap = new HashMap<String, Object>();
        LOGGER.info("Enters searchContractMaster with parameters start= " + start + " ,end=" + end + ", orderByColumns size =" + orderByColumns.size());
        if (searchItemForm.getField(Constants.TEXT_ONE) == null || searchItemForm.getField(Constants.TEXT_ONE).getValue().toString() == null) {
            contractId = Constants.PERCENT;
        } else {
            contractId = searchItemForm.getField(Constants.TEXT_ONE).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TEXT_TWO) == null || searchItemForm.getField(Constants.TEXT_TWO).getValue().toString() == null) {
            contractNo = Constants.PERCENT;
        } else {
            contractNo = searchItemForm.getField(Constants.TEXT_TWO).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TEXT_THREE) == null || searchItemForm.getField(Constants.TEXT_THREE).getValue().toString() == null) {
            contractName = Constants.PERCENT;
        } else {
            contractName = searchItemForm.getField(Constants.TEXT_THREE).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.COMBO_TWO).getValue() == null || searchItemForm.getField(Constants.COMBO_TWO).getValue().toString() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(Constants.COMBO_TWO).getValue().toString())) {
            contractType = 0;
        } else {
            contractType = ((HelperDTO) searchItemForm.getField(Constants.COMBO_TWO).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.COMBO_ONE).getValue() == null || searchItemForm.getField(Constants.COMBO_ONE).getValue().toString() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(Constants.COMBO_ONE).getValue().toString())) {
            contractStatus = 0;
        } else {
            contractStatus = ((HelperDTO) searchItemForm.getField(Constants.COMBO_ONE).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.TEXT_FOUR) == null || StringUtils.isBlank(searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString()) || searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString() == null) {
            tradeClass = Constants.PERCENT;
        } else {
            tradeClass = searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID) == null || !StringUtils.isNotBlank(searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString())) {
            tradingPartner = 0;
        } else {
            tradingPartner = Integer.valueOf(searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString().replaceAll(Constants.COMMA, StringUtils.EMPTY).trim());
        }

        if (StringUtils.isNotBlank(contractId)) {
            contractId = contractId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(contractNo)) {
            contractNo = contractNo.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(contractName)) {
            contractName = contractName.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(tradeClass)) {
            tradeClass = tradeClass.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);

        }

        filterMap.put(Constants.CONTRACT_ID, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_NO, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_NAME, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_STATUS, 0);
        filterMap.put(Constants.CONTRACT_TYPE, 0);
        filterMap.put(Constants.TRADING_PARTNER_NAME, StringUtils.EMPTY);

        if (criteria != null && criteria.getFilters() != null) {
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (stringFilter.getPropertyId().equals(Constants.CONTRACT_STATUS)) {

                        filterMap.put(Constants.CONTRACT_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(Constants.CONTRACT_TYPE)) {

                        filterMap.put(Constants.CONTRACT_TYPE, Integer.valueOf(stringFilter.getFilterString()));

                    } else if (stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)) {
                        String trade = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(Constants.TRADE_CLASS, trade);
                        tradeClass = trade;

                    } else if (stringFilter.getPropertyId().equals(Constants.TRADING_PARTNER_NAME)) {
                        String tradingPartnerName = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(Constants.TRADING_PARTNER_NAME, tradingPartnerName);

                    } else {
                        String filterString = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                    }
                }

            }
        }

        String column = "CONTRACT_MASTER_SID";
        String orderBy = Constants.ASC;

        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            if (Constants.CONTRACT_ID.equals(orderByColumn.getName())) {
                column = Constants.CONTRACT_ID_CAPS;
            } else if (Constants.CONTRACT_NO.equals(orderByColumn.getName())) {
                column = Constants.CONTRACT_NO_CAPS;
            } else if (Constants.CONTRACT_NAME.equals(orderByColumn.getName())) {
                column = Constants.CONTRACT_NAME_CAPS;
            } else if (Constants.CONTRACT_STATUS.equals(orderByColumn.getName())) {
                column = Constants.CON_STATUS;
            } else if (Constants.CONTRACT_TYPE.equals(orderByColumn.getName())) {
                column = Constants.CON_TYPE;
            } else if (Constants.TRADE_CLASS.equals(orderByColumn.getName())) {
                column = Constants.CON_TRADE_CLASS;
            } else if (Constants.TRADING_PARTNER_NAME.equals(orderByColumn.getName())) {
                column = Constants.COMP_NAME;
            }
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderBy = Constants.ASC;
            } else {
                orderBy = "DESC";
            }
        }

        List result = ContractMasterLocalServiceUtil.getContractList(contractId, contractNo, contractName, contractStatus, contractType, tradeClass, tradingPartner, filterMap, orderBy, column, start, end, true);

        List<SearchResultsDTO> searchList = getCustomizedSearchFilter(result);

        LOGGER.info("Returns searchList size =" + result.size() + "  and  searchContractMaster method ends");
        return searchList;
    }

    /**
     * returns the List containing customized ContractMasterDTO.
     *
     * @param list the list
     * @return the customized search form from model
     * @throws PortalException
     * @throws SystemException
     */
    public List<ContractMasterDTO> getCustomizedSearchFormFromModel(final List<ContractMaster> list) throws SystemException, PortalException {
        LOGGER.info("Enters getCustomizedSearchFormFromModel method");
        final List<ContractMasterDTO> searchItemList = new ArrayList<ContractMasterDTO>();
        if (list != null) {
            ContractMasterDTO searchItemForm;
            Map<Integer, String> statusMap = cfpSearchLogic.getCodeDescription(UIUtils.STATUS);
            statusMap.put(0, StringUtils.EMPTY);
            Map<Integer, String> contractTypeMap = cfpSearchLogic.getCodeDescription(UIUtils.CONTRACT_TYPE);
            contractTypeMap.put(0, StringUtils.EMPTY);
            Map<Integer, String> tradeClassMap = cfpSearchLogic.getCodeDescription(UIUtils.TRADE_CLASS);
            tradeClassMap.put(0, StringUtils.EMPTY);
            for (int i = 0; i < list.size(); i++) {
                searchItemForm = new ContractMasterDTO();
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
                if (obj.getContHoldCompanyMasterSid() != null) {
                    final CompanyMaster tradingPartner = dao.getCompanyMaster(Integer.valueOf(obj.getContHoldCompanyMasterSid()));
                    searchItemForm.setTradingPartnerName(tradingPartner.getCompanyName());
                }
                searchItemList.add(searchItemForm);
            }
        }
        LOGGER.info(" getCustomizedSearchFormFromModel method ends  by returning searchItemList size=" + searchItemList.size());
        return searchItemList;
    }

    public List<SearchResultsDTO> getCustomizedSearchFilter(final List list) throws SystemException, PortalException {
        LOGGER.info("Enters getCustomizedSearchFormFromModel method");
        final List<SearchResultsDTO> searchItemList = new ArrayList<SearchResultsDTO>();
        SearchResultsDTO searchItemForm;
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            searchItemForm = new SearchResultsDTO();
            searchItemForm.setContractSystemId(Integer.valueOf(String.valueOf(obj[0])));
            searchItemForm.setContractId(String.valueOf(obj[1]));
            searchItemForm.setContractNo(String.valueOf(obj[2]));
            if (obj[3] != null) {
                searchItemForm.setContractName(String.valueOf(obj[3]));
            }
            searchItemForm.setContractStatus(String.valueOf(obj[8] != null && !Constants.SELECT_ONE.equals(String.valueOf(obj[8])) ? obj[8] : StringUtils.EMPTY));
            searchItemForm.setContractType(String.valueOf(obj[9] != null && !Constants.SELECT_ONE.equals(String.valueOf(obj[9])) ? obj[9] : StringUtils.EMPTY));
            if (obj[10] != null && !String.valueOf(obj[10]).equals(Constants.SELECT_ONE)) {
                searchItemForm.setTradeClass(String.valueOf(obj[10]));
            }
            if (obj[11] != null) {
                searchItemForm.setTradingPartnerName(String.valueOf(obj[11]));
            }
            searchItemForm.setRecordLockStatus(String.valueOf(Boolean.valueOf(String.valueOf(obj[12]))));
            searchItemList.add(searchItemForm);
        }

        LOGGER.info(" getCustomizedSearchFormFromModel method ends  by returning searchItemList size=" + searchItemList.size());
        return searchItemList;
    }

    /**
     * Gets the Contract Master DTO by contractSystemId.
     *
     * @param contractSystemId the id
     * @return the contract master by contractSystemId
     */
    public ContractMasterDTO getContractMasterById(final int contractSystemId) throws SystemException, PortalException {
        ContractMaster contractMaster;
        final ContractMasterDTO contractMasterDTO = new ContractMasterDTO();
        LOGGER.info("entering getContractMasterById method with contractSystemId:" + contractSystemId);
        contractMaster = dao.getContractMasterById(contractSystemId);
        contractMasterDTO.setContractSystemId(contractMaster.getContractMasterSid());
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
            contractMasterDTO.setCompanyName(Integer.valueOf(contractMaster.getBunitCompanyMasterSid()));
            final CompanyMaster compName = dao.getCompanyMaster(Integer.valueOf(contractMaster.getBunitCompanyMasterSid()));
            if (compName != null) {
                contractMasterDTO.setCompanyLabel(compName.getCompanyName());
            }
        }
        if (contractMaster.getContHoldCompanyMasterSid() != null && !StringUtils.isBlank(contractMaster.getContHoldCompanyMasterSid())) {
            contractMasterDTO.setTradingPartnerSystemId(Integer.valueOf(contractMaster.getContHoldCompanyMasterSid()));
            final CompanyMaster forName = dao.getCompanyMaster(Integer.valueOf(contractMaster.getContHoldCompanyMasterSid()));
            if (forName != null) {
                contractMasterDTO.setTradingPartnerName(forName.getCompanyName());
            }
        }
        if (!"null".equals(String.valueOf(contractMaster.getAdvanceNoticeDays())) && !"0.0".equals(String.valueOf(contractMaster.getAdvanceNoticeDays()))) {
            // contractMasterDTO.setAdvanceNoticeDays(Double.isNaN(contractMaster.getAdvanceNoticeDays()) ? 0.0 : contractMaster.getAdvanceNoticeDays());
            contractMasterDTO.setAdvanceNoticeDays(String.valueOf(contractMaster.getAdvanceNoticeDays()));
        } else {
            contractMasterDTO.setAdvanceNoticeDays("0");
        }

        //  contractMasterDTO.setAdvanceNoticeDays(contractMaster.getAdvanceNoticeDays());
        contractMasterDTO.setInsideOwner(contractMaster.getInsideOwner());
        contractMasterDTO.setInsidePhone(contractMaster.getInsidePhone());
        contractMasterDTO.setInsideAuthor(contractMaster.getInsideAuthor());
        contractMasterDTO.setInsideAdditional(contractMaster.getInsideAdditional());
        contractMasterDTO.setInsideAdditionalName(contractMaster.getInsideAdditionalName());
        contractMasterDTO.setInsideAdditionalPhone(contractMaster.getInsideAdditionalPhone());
        contractMasterDTO.setOutsideOwner(contractMaster.getOutsideOwner());
        contractMasterDTO.setOutsidePhone(contractMaster.getOutsidePhone());
        contractMasterDTO.setOutsideAuthor(contractMaster.getOutsideAuthor());
        contractMasterDTO.setOutsideAdditional(contractMaster.getOutsideAdditional());
        contractMasterDTO.setOutsideAdditionalName(contractMaster.getOutsideAdditionalName());
        contractMasterDTO.setOutsideAdditionalPhone(contractMaster.getOutsideAdditionalPhone());
        contractMasterDTO.setAffiliatedContractInfo(contractMaster.getAffiliatedContractInfo());
        contractMasterDTO.setShippingTerms(contractMaster.getShippingTerms());
        contractMasterDTO.setProposedStartDate(contractMaster.getProposalStartDate());
        contractMasterDTO.setProposedEndDate(contractMaster.getProposalEndDate());
        contractMasterDTO.setOriginalStartDate(contractMaster.getOriginalStartDate());
        contractMasterDTO.setOriginalEndDate(contractMaster.getOriginalEndDate());
        contractMasterDTO.setAwardStatus(helperListUtil.getIdHelperDTOMap().get(contractMaster.getAwardStatus()));
        contractMasterDTO.setLastUpdatedDate(contractMaster.getLastUpdatedDate());
        contractMasterDTO.setPriceEscalationClause(contractMaster.getPriceEscalationClause());
        contractMasterDTO.setExemptFromLowPrice(StringUtils.EMPTY.equals(contractMaster.getExemptFromLowPrice()) ? null : contractMaster.getExemptFromLowPrice());
        int priceResetIndicator = (contractMaster.getPriceResetIndicator() == null || StringUtils.isBlank(contractMaster.getPriceResetIndicator())) ? 0 : Integer.valueOf(contractMaster.getPriceResetIndicator());
        contractMasterDTO.setPriceResetIndicator(helperListUtil.getIdHelperDTOMap().get(priceResetIndicator));
        contractMasterDTO.setCancellationClause(contractMaster.getCancellationClause());
        contractMasterDTO.setMostFavoredNation(contractMaster.getMostFavoredNation());
        contractMasterDTO.setCategory(String.valueOf(contractMaster.getCategory()));
        contractMasterDTO.setCurrency(contractMaster.getCurrency());
        contractMasterDTO.setMinimumOrder(contractMaster.getMinimumOrder());
        contractMasterDTO.setPaymentTerms(helperListUtil.getIdHelperDTOMap().get(contractMaster.getPaymentTerms()));
        contractMasterDTO.setRecordLockStatus(String.valueOf(contractMaster.getRecordLockStatus()));
        contractMasterDTO.setCreatedBy(String.valueOf(contractMaster.getCreatedBy()));
        contractMasterDTO.setCreatedDate(contractMaster.getCreatedDate());
        if (contractMaster.getManfCompanyMasterSid() == null || StringUtils.isBlank(contractMaster.getManfCompanyMasterSid()) || StringUtils.EMPTY.equals(contractMaster.getManfCompanyMasterSid())) {
            contractMasterDTO.setCompanySystemId(new HelperDTO(Constants.SELECT_ONE));
        } else {
            contractMasterDTO.setCompanySystemId(new CFPSearchLogic().getCompanyId(Integer.valueOf(contractMaster.getManfCompanyMasterSid())));
        }

        final List<ContractAliasMaster> aliasList = dao.findByContractSystemId(contractSystemId);
        final List<ContractAliasMasterDTO> contractAliasMasterDTO = new ArrayList<ContractAliasMasterDTO>();
        ContractAliasMasterDTO aliasMasterDTO;
        Map<Integer, String> aliasTypeMap = cfpSearchLogic.getCodeDescription(UIUtils.CONTRACT_ALIAS_TYPE);
        aliasTypeMap.put(0, StringUtils.EMPTY);
        for (int i = 0; i < aliasList.size(); i++) {
            aliasMasterDTO = new ContractAliasMasterDTO();
            final ContractAliasMaster contractAliasMaster = aliasList.get(i);
            aliasMasterDTO.setContractSystemId(contractAliasMaster.getContractMasterSid());
            aliasMasterDTO.setContractAliasSystemId(contractAliasMaster.getContractAliasMasterSid());
            aliasMasterDTO.setContractAliasName(contractAliasMaster.getContractAliasName());
            aliasMasterDTO.setContractAliasNo(contractAliasMaster.getContractAliasNo());
            aliasMasterDTO.setTradingPartner(contractAliasMaster.getTpCompanyMasterSid());
            if (aliasMasterDTO.getTradingPartner() != Constants.ZERO) {
                final CompanyMaster forId = dao.getCompanyMaster(aliasMasterDTO.getTradingPartner());
                aliasMasterDTO.setTpCompanyMasterSid(forId.getCompanyName());
            }
            aliasMasterDTO.setContractAliasType(helperListUtil.getIdHelperDTOMap().get(contractAliasMaster.getContractAliasType()));
            aliasMasterDTO.setContractAliasDesc(String.valueOf(aliasTypeMap.get(contractAliasMaster.getContractAliasType())));
            if (contractAliasMaster.getStartDate() != null) {
                aliasMasterDTO.setAliasStartDate(dateFormat.format(contractAliasMaster.getStartDate()));
                aliasMasterDTO.setStartDate(dateFormat.format(contractAliasMaster.getStartDate()));
            }
            if (contractAliasMaster.getEndDate() != null) {
                aliasMasterDTO.setAliasEndDate(dateFormat.format((contractAliasMaster.getEndDate())));
                aliasMasterDTO.setEndDate(dateFormat.format((contractAliasMaster.getEndDate())));
            }
            aliasMasterDTO.setRecordLockStatus(String.valueOf(contractAliasMaster.getRecordLockStatus()));
            aliasMasterDTO.setCreatedBy(String.valueOf(contractAliasMaster.getCreatedBy()));
            aliasMasterDTO.setCreatedDate(contractAliasMaster.getCreatedDate());
            contractAliasMasterDTO.add(aliasMasterDTO);
        }
        contractMasterDTO.setContracAliasMasterList(contractAliasMasterDTO);
        LOGGER.info("ends getContractMasterById method after returning contractMasterDTO ");
        return contractMasterDTO;
    }

    /**
     * Saves the ContractMaster to the DataBase.
     *
     * @param contractMasterForm the contract master form
     * @param contractAliasList the contract alias list
     * @return the string - save status.
     */
    public String saveContractMaster(final CustomFieldGroup contractMasterForm, final List<ContractAliasMasterDTO> contractAliasList, final List<NotesDTO> availableUploadedInformation, final String addedNotes) throws SystemException, PortalException {

        LOGGER.info("entering saveContractMaster method");
        ContractMaster contract;
        /* to update the deleted record with new details */
        boolean flag = false;
        String systemId = contractMasterForm.getField(Constants.CONTRACT_SYSTEM_ID) == null || contractMasterForm.getField(Constants.CONTRACT_SYSTEM_ID).getValue() == null
                || contractMasterForm.getField(Constants.CONTRACT_SYSTEM_ID).getValue().equals(Constants.NULL) ? StringUtils.EMPTY : String.valueOf(contractMasterForm.getField(Constants.CONTRACT_SYSTEM_ID)
                                .getValue());

        final String contractID = contractMasterForm.getField(Constants.CONTRACT_ID).getValue().toString().trim();

        if (StringUtils.isNotEmpty(contractID)) {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_ID, contractID));
            dynamicQuery.add(RestrictionsFactoryUtil.like(Constants.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D));
            final List<ContractMaster> contractMasterList = dao.getContractMasterList(dynamicQuery);
            if (!contractMasterList.isEmpty()) {
                systemId = String.valueOf(contractMasterList.get(0).getContractMasterSid());
                flag = true;
            }
        }
        final String systemId1 = systemId.replace(Constants.COMMA, StringUtils.EMPTY);

        if (systemId1.equals(Constants.NULL) || Constants.ZEROSTRING.equals(systemId1) || StringUtils.EMPTY.equals(systemId1)) {
            contract = ContractMasterLocalServiceUtil.createContractMaster(0);
            LOGGER.info("saving new record ");
        } else {
            LOGGER.info("updating old record ");
            contract = dao.getContractMasterById(Integer.parseInt(systemId1));
        }
        if (contractMasterForm.getField(Constants.CONTRACT_ID).getValue() != null && !contractMasterForm.getField(Constants.CONTRACT_ID).getValue().equals(StringUtils.EMPTY)) {
            contract.setContractId(contractMasterForm.getField(Constants.CONTRACT_ID).getValue().toString().trim());
        }
        if (contractMasterForm.getField(Constants.CONTRACT_NO).getValue() != null && !contractMasterForm.getField(Constants.CONTRACT_NO).getValue().equals(StringUtils.EMPTY)) {
            contract.setContractNo(contractMasterForm.getField(Constants.CONTRACT_NO).getValue().toString().trim());
        }
        if (contractMasterForm.getField(Constants.CONTRACT_NAME).getValue() != null && !contractMasterForm.getField(Constants.CONTRACT_NAME).getValue().equals(StringUtils.EMPTY)) {
            contract.setContractName(contractMasterForm.getField(Constants.CONTRACT_NAME).getValue().toString().trim());
        } else {
            contract.setContractName(StringUtils.EMPTY);
        }
        if (contractMasterForm.getField(Constants.CONTRACT_TYPE).getValue() != null && ((com.stpl.ifs.util.HelperDTO) contractMasterForm.getField(Constants.CONTRACT_TYPE).getValue()).getId() != 0) {
            contract.setContractType(((com.stpl.ifs.util.HelperDTO) contractMasterForm.getField(Constants.CONTRACT_TYPE).getValue()).getId());
        }
        if (contractMasterForm.getField(Constants.CONTRACT_STATUS).getValue() != null || ((com.stpl.ifs.util.HelperDTO) contractMasterForm.getField(Constants.CONTRACT_STATUS).getValue()).getId() != 0) {

            contract.setContractStatus(((com.stpl.ifs.util.HelperDTO) contractMasterForm.getField(Constants.CONTRACT_STATUS).getValue()).getId());
        }
        if (contractMasterForm.getField(Constants.START_DATE).getValue() != null && !contractMasterForm.getField(Constants.START_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setStartDate((Date) contractMasterForm.getField(Constants.START_DATE).getValue());
        }
        if (contractMasterForm.getField(ContractUtils.END_DATE).getValue() != null && !contractMasterForm.getField(Constants.START_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setEndDate((Date) contractMasterForm.getField(ContractUtils.END_DATE).getValue());
        } else {
            contract.setEndDate(null);
        }
        contract.setDocumentType(contractMasterForm.getField(Constants.DOC_TYPE).getValue() == null ? 0 : ((com.stpl.ifs.util.HelperDTO) contractMasterForm.getField(Constants.DOC_TYPE).getValue()).getId());
        contract.setTerm(contractMasterForm.getField(Constants.TERM).getValue() == null || StringUtils.isEmpty(String.valueOf(contractMasterForm.getField(Constants.TERM).getValue())) ? 0 : Integer.valueOf(contractMasterForm.getField(Constants.TERM).getValue().toString()));
        contract.setContractTradeClass(contractMasterForm.getField(Constants.TRADE_CLASS).getValue() == null ? 0 : ((com.stpl.ifs.util.HelperDTO) contractMasterForm.getField(Constants.TRADE_CLASS).getValue()).getId());
        contract.setInternalNotes(addedNotes);
        if (contractMasterForm.getField(Constants.RENEGOTIATION_START_DATE).getValue() != null && !contractMasterForm.getField(Constants.RENEGOTIATION_START_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setRenegotiationStartDate((Date) contractMasterForm.getField(Constants.RENEGOTIATION_START_DATE).getValue());

        } else {

            contract.setRenegotiationStartDate(null);
        }

        if (contractMasterForm.getField(Constants.RENEGOTIATION_END_DATE).getValue() != null && !contractMasterForm.getField(Constants.RENEGOTIATION_END_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setRenegotiationEndDate((Date) contractMasterForm.getField(Constants.RENEGOTIATION_END_DATE).getValue());

        } else {
            contract.setRenegotiationEndDate(null);
        }

        if (contractMasterForm.getField(Constants.PRICE_PROTECTION_START_DATE).getValue() != null && !contractMasterForm.getField(Constants.PRICE_PROTECTION_START_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setPriceprotectionStartDate((Date) contractMasterForm.getField(Constants.PRICE_PROTECTION_START_DATE).getValue());

        } else {

            contract.setPriceprotectionStartDate(null);
        }
        if (contractMasterForm.getField(Constants.PRICE_PROTECTION_END_DATE).getValue() != null && !contractMasterForm.getField(Constants.PRICE_PROTECTION_END_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setPriceprotectionEndDate((Date) contractMasterForm.getField(Constants.PRICE_PROTECTION_END_DATE).getValue());

        } else {
            contract.setPriceprotectionEndDate(null);
        }
        if (contractMasterForm.getField(Constants.COMPANY_LABEL).getValue() != null && StringUtils.isNotBlank(String.valueOf(contractMasterForm.getField(Constants.COMPANY_LABEL).getValue()))) {
            if (contractMasterForm.getField(Constants.COMPANY_NAME).getValue() != null && StringUtils.isNotBlank(String.valueOf(contractMasterForm.getField(Constants.COMPANY_NAME).getValue()))) {
                int companyId = Integer.parseInt(contractMasterForm.getField(Constants.COMPANY_NAME).getValue().toString().replaceAll(Constants.COMMA, StringUtils.EMPTY));
                contract.setBunitCompanyMasterSid(companyId == 0 || StringUtils.isEmpty(String.valueOf(contractMasterForm.getField(Constants.COMPANY_NAME).getValue())) ? null : String.valueOf(companyId));
            } else {
                contract.setBunitCompanyMasterSid(null);
            }
        } else {
            contract.setBunitCompanyMasterSid(null);
        }
        if (contractMasterForm.getField(Constants.COMPANY_SYSTEM_ID).getValue() != null) {
            contract.setManfCompanyMasterSid((((HelperDTO) contractMasterForm.getField(Constants.COMPANY_SYSTEM_ID).getValue()).getId()) == 0 ? null : String.valueOf(((HelperDTO) contractMasterForm.getField(Constants.COMPANY_SYSTEM_ID).getValue()).getId()));
        } else {
            contract.setManfCompanyMasterSid(null);
        }
        LOGGER.info("contract.getManfCompanyMasterSid()" + contract.getManfCompanyMasterSid());
        if (contractMasterForm.getField(Constants.DOC_CLASS).getValue() != null) {
            contract.setDocumentClass(((com.stpl.ifs.util.HelperDTO) contractMasterForm.getField(Constants.DOC_CLASS).getValue()).getId());
        } else {
            contract.setDocumentClass(0);
        }
        if (contractMasterForm.getField(Constants.TRADING_PARTNER_NAME).getValue() != null && StringUtils.isNotBlank(String.valueOf(contractMasterForm.getField(Constants.TRADING_PARTNER_NAME).getValue()))) {
            if (contractMasterForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue() != null && StringUtils.isNotBlank(String.valueOf(contractMasterForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue()))) {
                contract.setContHoldCompanyMasterSid(Integer.parseInt(contractMasterForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString().replaceAll(Constants.COMMA, StringUtils.EMPTY)) == 0 ? null : contractMasterForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString().replaceAll(Constants.COMMA, StringUtils.EMPTY));
            } else {
                contract.setContHoldCompanyMasterSid(null);
            }
        } else {
            contract.setContHoldCompanyMasterSid(null);
        }
        LOGGER.info("contract.setContHoldCompanyMasterSid(0)" + contract.getContHoldCompanyMasterSid());
        contract.setAdvanceNoticeDays(StringUtils.isBlank(String.valueOf(contractMasterForm.getField(Constants.ADVANCE_NOTICE_DAYS).getValue())) || "null".equals(String.valueOf(contractMasterForm.getField(Constants.ADVANCE_NOTICE_DAYS).getValue())) ? 0 : Double.valueOf(String.valueOf(contractMasterForm.getField(Constants.ADVANCE_NOTICE_DAYS).getValue()).trim().replace(Constants.COMMA, StringUtils.EMPTY)));
        contract.setInsideOwner(contractMasterForm.getField(Constants.INSIDE_OWNER).getValue().toString().trim());
        contract.setInsidePhone(contractMasterForm.getField(Constants.INSIDE_PHONE).getValue().toString().trim());
        contract.setInsideAuthor(contractMasterForm.getField(Constants.INSIDE_AUTHOR).getValue().toString().trim());
        contract.setInsideAdditional(contractMasterForm.getField(Constants.INSIDE_ADDITIONAL).getValue().toString().trim());
        contract.setInsideAdditionalName(contractMasterForm.getField(Constants.INSIDE_ADDITIONAL_NAME).getValue().toString().trim());
        contract.setInsideAdditionalPhone(contractMasterForm.getField(Constants.INSIDE_ADDITIONAL_PHONE).getValue().toString().trim());
        contract.setOutsideOwner(contractMasterForm.getField(Constants.OUTSIDE_OWNER).getValue().toString().trim());
        contract.setOutsidePhone(contractMasterForm.getField(Constants.OUTSIDE_PHONE).getValue().toString().trim());
        contract.setOutsideAuthor(contractMasterForm.getField(Constants.OUTSIDE_AUTHOR).getValue().toString().trim());
        contract.setOutsideAdditional(contractMasterForm.getField(Constants.OUTSIDE_ADDITIONAL).getValue().toString().trim());
        contract.setOutsideAdditionalName(contractMasterForm.getField(Constants.OUTSIDE_ADDITIONAL_NAME).getValue().toString().trim());
        contract.setOutsideAdditionalPhone(contractMasterForm.getField(Constants.OUTSIDE_ADDITIONAL_PHONE).getValue().toString().trim());
        contract.setAffiliatedContractInfo(contractMasterForm.getField(Constants.AFFILIATED_CONTRACT_INFO).getValue().toString().trim());
        contract.setShippingTerms(contractMasterForm.getField(Constants.SHIPPING_TERMS).getValue().toString().trim());
        contract.setSource("GTN");
        if (contractMasterForm.getField(Constants.PROPOSED_START_DATE).getValue() != null && !contractMasterForm.getField(Constants.PROPOSED_START_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setProposalStartDate((Date) contractMasterForm.getField(Constants.PROPOSED_START_DATE).getValue());
        } else {
            contract.setProposalStartDate(null);
        }
        if (contractMasterForm.getField(Constants.PROPOSED_END_DATE).getValue() != null && !contractMasterForm.getField(Constants.PROPOSED_END_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setProposalEndDate((Date) contractMasterForm.getField(Constants.PROPOSED_END_DATE).getValue());
        } else {
            contract.setProposalEndDate(null);
        }
        if (contractMasterForm.getField(Constants.ORIGINAL_START_DATE).getValue() != null && !contractMasterForm.getField(Constants.ORIGINAL_START_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setOriginalStartDate((Date) contractMasterForm.getField(Constants.ORIGINAL_START_DATE).getValue());
        } else {
            contract.setOriginalStartDate(null);
        }
        if (contractMasterForm.getField(Constants.ORIGINAL_END_DATE).getValue() != null && !contractMasterForm.getField(Constants.ORIGINAL_END_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setOriginalEndDate((Date) contractMasterForm.getField(Constants.ORIGINAL_END_DATE).getValue());
        } else {
            contract.setOriginalEndDate(null);
        }
        if (contractMasterForm.getField(Constants.AWARD_STATUS).getValue() != null) {
            contract.setAwardStatus(((HelperDTO) (contractMasterForm.getField(Constants.AWARD_STATUS).getValue())).getId());
        } else {
            contract.setAwardStatus(0);
        }
        if (contractMasterForm.getField(Constants.LAST_UPDATED_DATE).getValue() != null && !contractMasterForm.getField(Constants.LAST_UPDATED_DATE).getValue().equals(StringUtils.EMPTY)) {
            contract.setLastUpdatedDate((Date) contractMasterForm.getField(Constants.LAST_UPDATED_DATE).getValue());
        } else {
            contract.setLastUpdatedDate(null);
        }
        contract.setPriceEscalationClause(contractMasterForm.getField(Constants.PRICE_ESCALATION_CLAUSE).getValue().toString().trim());
        contract.setExemptFromLowPrice(contractMasterForm.getField(Constants.EXEMPT_FROM_LOW_PRICE).getValue() == null ? null : contractMasterForm.getField(Constants.EXEMPT_FROM_LOW_PRICE).getValue().toString().trim());
        if (contractMasterForm.getField(Constants.PRICE_RESET_INDICATOR).getValue() != null) {
            contract.setPriceResetIndicator(String.valueOf(((HelperDTO) contractMasterForm.getField(Constants.PRICE_RESET_INDICATOR).getValue()).getId()));
        } else {
            contract.setPriceResetIndicator("0");
        }
        contract.setCancellationClause(contractMasterForm.getField(Constants.CANCELLATION_CLAUSE).getValue().toString().trim());
        contract.setMostFavoredNation(contractMasterForm.getField(Constants.MOST_FAVORED_NATION).getValue().toString().trim());
        contract.setCategory(contractMasterForm.getField(Constants.CATEGORY).getValue().toString().trim());
        contract.setCurrency(contractMasterForm.getField(Constants.CURRENCY).getValue().toString().trim());
        contract.setMinimumOrder(contractMasterForm.getField(Constants.MINIMUM_ORDER).getValue().toString().trim());
        if (contractMasterForm.getField(Constants.PAYMENT_TERMS).getValue() != null) {
            contract.setPaymentTerms(((HelperDTO) (contractMasterForm.getField(Constants.PAYMENT_TERMS).getValue())).getId());
        } else {
            contract.setPaymentTerms(0);
        }
        contract.setRecordLockStatus(false);
        if (systemId1.equals(Constants.NULL) || Constants.ZEROSTRING.equals(systemId1) || StringUtils.EMPTY.equals(systemId1)) {
            final String user = VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString();
            contract.setCreatedDate(new Date());
            contract.setModifiedDate(new Date());
            contract.setCreatedBy(Integer.valueOf(user));
            contract.setModifiedBy(Integer.valueOf(user));

            contract.setInboundStatus(ContractUtils.INBOUND_STATUS_A);

            final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_ID, contract.getContractId()));
            cfpDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D));
            final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
            contractDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_NO, contract.getContractNo()));
            contractDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D));
            final List<ContractMaster> contractMaster = dao.getContractMasterList(cfpDynamicQuery);

            if (StringUtils.isBlank(contract.getContractNo()) || contract.getContractNo().equals(Constants.NULL)) {
                if (contractMaster.size() < Constants.ONE) {

                    final ContractMaster result = dao.addContractMaster(contract);
                    sessionDTO.setSystemId(result.getContractMasterSid());
                    if (contract.getContractStatus() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getContractStatus());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_STATUS);
                    }
                    if (contract.getContractType() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getContractType());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_TYPE);
                    }
                    if (contract.getContractTradeClass() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getContractTradeClass());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.TRADE_CLASS);
                    }
                    if (contract.getDocumentType() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getDocumentType());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.DOCUMENT_TYPE);
                    }
                    if (contract.getDocumentClass() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getDocumentClass());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.DOCUMENT_CLASS);
                    }
                    if (contract.getAwardStatus() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getAwardStatus());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.AWARD_STATUS);
                    }
                    if (contract.getPriceResetIndicator() != null) {
                        udcCheck.increment(contract.getPriceResetIndicator(), UIUtils.PRICE_RESET_INDICATOR);
                    }
                    if (contract.getPaymentTerms() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getPaymentTerms());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.PAYMENT_TERMS);
                    }
                    saveContractAliasMasterList(contractAliasList, result);
                    notesLogic.saveUploadedInformation(availableUploadedInformation, Constants.CONTRACT_MASTER, result.getContractMasterSid());
                } else {
                    return Constants.DUPLICATE;
                }

            } else {
                final List<ContractMaster> contractNoMaster = dao.getContractMasterList(contractDynamicQuery);
                if (contractMaster.size() < 1 && contractNoMaster.size() < 1) {

                    final ContractMaster result = dao.addContractMaster(contract);
                    sessionDTO.setSystemId(result.getContractMasterSid());

                    if (result.getContractStatus() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getContractStatus());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_STATUS);
                    }
                    if (result.getContractType() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getContractType());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_TYPE);
                    }
                    if (result.getContractTradeClass() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getContractTradeClass());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.TRADE_CLASS);
                    }
                    if (result.getDocumentType() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getDocumentType());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.DOCUMENT_TYPE);
                    }
                    if (result.getDocumentClass() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getDocumentClass());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.DOCUMENT_CLASS);
                    }
                    if (result.getAwardStatus() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getAwardStatus());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.AWARD_STATUS);
                    }
                    if (result.getPriceResetIndicator() != null) {
                        udcCheck.increment(result.getPriceResetIndicator(), UIUtils.PRICE_RESET_INDICATOR);
                    }
                    if (result.getPaymentTerms() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getAwardStatus());
                        udcCheck.increment(helperTable.getDescription(), UIUtils.PAYMENT_TERMS);
                    }
                    saveContractAliasMasterList(contractAliasList, result);
                    notesLogic.saveUploadedInformation(availableUploadedInformation, Constants.CONTRACT_MASTER, result.getContractMasterSid());
                } else if (contractNoMaster.size() > Constants.ONE) {
                    return Constants.DUPLICATE_NO;

                } else {
                    return Constants.DUPLICATE;
                }
            }
        } else {
            contract.setModifiedDate(new Date());
            contract.setModifiedBy(Integer.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString()));

            if (flag) {
                contract.setProcessStatus(false);
                contract.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
            } else {
                contract.setProcessStatus(dao.getContractMasterById((Integer) sessionDTO.getSystemId()).getProcessStatus());
                contract.setInboundStatus(ContractUtils.INBOUND_STATUS_C);
                contract.setContractMasterSid((Integer) sessionDTO.getSystemId());
            }

            final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
            contractDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_ID, contract.getContractId()));
            contractDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D));
            final List<ContractMaster> conMaster = dao.getContractMasterList(contractDynamicQuery);

            final DynamicQuery contractNoDynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);
            contractNoDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.CONTRACT_NO, contract.getContractNo()));
            contractNoDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.INBOUND_STATUS, ContractUtils.INBOUND_STATUS_D));
            final List<ContractMaster> conNoMaster = dao.getContractMasterList(contractNoDynamicQuery);

            int count = 0;
            for (int i = 0; i < conMaster.size(); i++) {
                if (Integer.parseInt(systemId.replace(Constants.COMMA, StringUtils.EMPTY)) != conMaster.get(i).getContractMasterSid()) {
                    count++;
                }
            }
            int countNo = 0;

            if (StringUtils.isNotBlank(contract.getContractNo()) && !contract.getContractNo().equals(Constants.NULL)) {

                for (int i = 0; i < conNoMaster.size(); i++) {
                    if (Integer.parseInt(systemId.replace(Constants.COMMA, StringUtils.EMPTY)) != conNoMaster.get(i).getContractMasterSid()) {
                        countNo++;
                    }
                }
            }

            if (count < 1 && countNo < 1) {
                final ContractMaster checkBean = dao.getContractMasterById(contract.getContractMasterSid());
                if (checkBean.getContractStatus() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(checkBean.getContractStatus());
                    udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_STATUS);
                }
                if (checkBean.getContractType() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(checkBean.getContractType());
                    udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_TYPE);
                }
                if (checkBean.getContractTradeClass() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(checkBean.getContractTradeClass());
                    udcCheck.decrement(helperTable.getDescription(), UIUtils.TRADE_CLASS);
                }
                if (checkBean.getDocumentType() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(checkBean.getDocumentType());
                    udcCheck.decrement(helperTable.getDescription(), UIUtils.DOCUMENT_TYPE);
                }
                if (checkBean.getDocumentClass() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(checkBean.getDocumentClass());
                    udcCheck.decrement(helperTable.getDescription(), UIUtils.DOCUMENT_CLASS);
                }
                if (checkBean.getAwardStatus() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(checkBean.getAwardStatus());
                    udcCheck.decrement(helperTable.getDescription(), UIUtils.AWARD_STATUS);
                }
                if (checkBean.getPriceResetIndicator() != null) {
                    udcCheck.decrement(checkBean.getPriceResetIndicator(), UIUtils.PRICE_RESET_INDICATOR);
                }
                if (checkBean.getPaymentTerms() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(checkBean.getPaymentTerms());
                    udcCheck.decrement(helperTable.getDescription(), UIUtils.PAYMENT_TERMS);
                }

                final ContractMaster result = dao.updateContractMaster(contract);
                sessionDTO.setSystemId(result.getContractMasterSid());
                if (result.getContractStatus() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getContractStatus());
                    udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_STATUS);
                }
                if (result.getContractType() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getContractType());
                    udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_TYPE);
                }
                if (result.getContractTradeClass() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getContractTradeClass());
                    udcCheck.increment(helperTable.getDescription(), UIUtils.TRADE_CLASS);
                }
                if (result.getDocumentType() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getDocumentType());
                    udcCheck.increment(helperTable.getDescription(), UIUtils.DOCUMENT_TYPE);
                }
                if (result.getDocumentClass() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getDocumentClass());
                    udcCheck.increment(helperTable.getDescription(), UIUtils.DOCUMENT_CLASS);
                }
                if (result.getAwardStatus() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getAwardStatus());
                    udcCheck.increment(helperTable.getDescription(), UIUtils.AWARD_STATUS);
                }
                if (result.getPriceResetIndicator() != null) {
                    udcCheck.increment(result.getPriceResetIndicator(), UIUtils.PRICE_RESET_INDICATOR);
                }
                if (result.getPaymentTerms() != 0) {
                    HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(result.getPaymentTerms());
                    udcCheck.increment(helperTable.getDescription(), UIUtils.PAYMENT_TERMS);
                }
                final List<ContractAliasMaster> aliasMasterList = dao.findByContractSystemId(result.getContractMasterSid());
                for (int i = 0; i < aliasMasterList.size(); i++) {
                    final ContractAliasMaster contractAliasMaster = (ContractAliasMaster) aliasMasterList.get(i);

                    final ContractAliasMaster udcDeleteCheck = dao.deleteContractAliasMaster(contractAliasMaster);
                    if (udcDeleteCheck.getContractAliasType() != 0) {
                        HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(udcDeleteCheck.getContractAliasType());
                        udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_ALIAS_TYPE);
                    }
                }
                saveContractAliasMasterList(contractAliasList, result);
                notesLogic.saveUploadedInformation(availableUploadedInformation, Constants.CONTRACT_MASTER, result.getContractMasterSid());
                return Constants.SUCCESS;

            } else {
                return Constants.DUPLICATE;
            }
        }
        LOGGER.info(" ending saveContractMaster method ");

        return Constants.SUCCESS;

    }

    /**
     * Saves the Contract Alias Master to Database.
     *
     * @param aliasList the alias list
     * @param result the result
     */
    public void saveContractAliasMasterList(final List<ContractAliasMasterDTO> aliasList, final ContractMaster result) throws SystemException {

        LOGGER.info("enters saveContractAliasMasterList method ");

        if (aliasList != null) {
            try {

                for (int i = 0; i < aliasList.size(); i++) {
                    final ContractAliasMasterDTO aliasForm = (ContractAliasMasterDTO) aliasList.get(i);

                    final ContractAliasMaster alias = ContractAliasMasterLocalServiceUtil.createContractAliasMaster(0);

                    alias.setContractMasterSid(result.getContractMasterSid());

                    alias.setTpCompanyMasterSid(aliasForm.getTradingPartner());

                    alias.setContractAliasNo(aliasForm.getContractAliasNo());

                    alias.setContractAliasName(aliasForm.getContractAliasName());

                    alias.setContractAliasType(aliasForm.getContractAliasType().getId());
                    if (StringUtils.isNotBlank(aliasForm.getAliasStartDate())) {
                        alias.setStartDate(CommonUtils.convertStringToDate(aliasForm.getAliasStartDate()));
                    }
                    if (StringUtils.isNotBlank(aliasForm.getAliasEndDate())) {
                        alias.setEndDate(CommonUtils.convertStringToDate(aliasForm.getAliasEndDate()));
                    }
                    alias.setRecordLockStatus(false);
                    alias.setSource("GTN");
                    final String user = VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString();
                    if (aliasForm.getContractAliasSystemId() == Constants.ZERO) {
                        final Date date = new Date();
                        alias.setCreatedBy(Integer.valueOf(user));
                        alias.setModifiedBy(Integer.valueOf(user));
                        alias.setCreatedDate(date);
                        alias.setModifiedDate(date);
                        alias.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
                        final ContractAliasMaster udcIncCheck = dao.addContractAliasMaster(alias);
                        if (udcIncCheck.getContractAliasType() != 0) {
                            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(udcIncCheck.getContractAliasType());
                            udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_ALIAS_TYPE);
                        }
                    } else {
                        final Date date = new Date();
                        alias.setCreatedBy(Integer.valueOf(aliasForm.getCreatedBy()));
                        alias.setCreatedDate(aliasForm.getCreatedDate());
                        alias.setModifiedDate(date);
                        alias.setModifiedBy(Integer.valueOf(user));
                        alias.setInboundStatus(ContractUtils.INBOUND_STATUS_A);
                        final ContractAliasMaster udcIncCheck = dao.addContractAliasMaster(alias);
                        if (udcIncCheck.getContractAliasType() != 0) {
                            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(udcIncCheck.getContractAliasType());
                            udcCheck.increment(helperTable.getDescription(), UIUtils.CONTRACT_ALIAS_TYPE);
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info(" saveContractAliasMasterList method ends ");

    }

    /**
     * Delete contract master by id.
     *
     * @param contractSystemId the contract system id
     * @return the contract master
     */
    public ContractMaster deleteContractMasterById(final int contractSystemId) throws SystemException, PortalException {

        LOGGER.info("enters deleteContractMasterById with parameter contractSystemId=" + contractSystemId);
        final ContractMaster contract = dao.getContractMasterById(contractSystemId);
        contract.setInboundStatus(ContractUtils.INBOUND_STATUS_D);
        dao.updateContractMaster(contract);

        if (contract.getContractStatus() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getContractStatus());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_STATUS);
        }
        if (contract.getContractType() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getContractType());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_TYPE);
        }
        if (contract.getContractTradeClass() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getContractTradeClass());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.TRADE_CLASS);
        }
        if (contract.getDocumentType() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getDocumentType());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.DOCUMENT_TYPE);
        }
        if (contract.getDocumentClass() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getDocumentClass());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.DOCUMENT_CLASS);
        }
        if (contract.getAwardStatus() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getAwardStatus());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.AWARD_STATUS);
        }
        if (contract.getPriceResetIndicator() != null) {
            udcCheck.decrement(contract.getPriceResetIndicator(), UIUtils.PRICE_RESET_INDICATOR);
        }
        if (contract.getPaymentTerms() != 0) {
            HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(contract.getPaymentTerms());
            udcCheck.decrement(helperTable.getDescription(), UIUtils.PAYMENT_TERMS);
        }

        final List<ContractAliasMaster> aliasList = dao.findByContractSystemId(contractSystemId);
        for (int i = 0; i < aliasList.size(); i++) {
            final ContractAliasMaster alias1 = aliasList.get(i);
            final ContractAliasMaster check = dao.deleteContractAliasMaster(alias1.getContractAliasMasterSid());
            if (check.getContractAliasType() != 0) {
                HelperTable helperTable = HelperTableLocalServiceUtil.fetchHelperTable(check.getContractAliasType());
                udcCheck.decrement(helperTable.getDescription(), UIUtils.CONTRACT_ALIAS_TYPE);
            }
        }
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.MASTER_TABLE_SID, contractSystemId));
        dynamicQuery.add(RestrictionsFactoryUtil.like(Constants.MASTER_TABLE_NAME, Constants.CONTRACT_MASTER));
        final List<MasterDataFiles> masterDataFiles = MasterDataFilesLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (!masterDataFiles.isEmpty()) {
            for (MasterDataFiles masterDataFile : masterDataFiles) {
                MasterDataFilesLocalServiceUtil.deleteMasterDataFiles(masterDataFile.getMasterDataFilesSid());
            }
        }
        LOGGER.info(" deleteContractMasterById method ends with return value contract ");

        return contract;

    }

    /**
     * Gets the drop down list based on given type.
     *
     * @param listType the list type
     * @return the drop down list
     */
    public List<HelperDTO> getDropDownList(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType), RestrictionsFactoryUtil.like(Constants.LIST_NAME, Constants.ALL)));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
        helperList.add(new HelperDTO(0, Constants.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }
        }

        LOGGER.info(" getDropDownList method ends with return value strList size =" + helperList.size());

        return helperList;
    }

    /**
     * Gets the drop down list based on given type.
     *
     * @param listType the list type
     * @return the drop down list
     */
    public List<HelperDTO> getDropDownListWithoutNull(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType), RestrictionsFactoryUtil.like(Constants.LIST_NAME, Constants.ALL)));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }
        }

        LOGGER.info(" getDropDownList method ends with return value strList size =" + helperList.size());

        return helperList;
    }

    /**
     * Gets the drop down list based on given type.
     *
     * @param listType the list type
     * @return the drop down list
     */
    public List<HelperDTO> getCompanyDropDownListWithoutNull(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }
        }

        LOGGER.info(" getDropDownList method ends with return value strList size =" + helperList.size());

        return helperList;
    }

    /**
     * Gets the drop down list based on given type.
     *
     * @param listType the list type
     * @return the drop down list
     */
    public List<String> getDropDownListDesc(final String listType) throws SystemException {
        final List<String> helperList = new ArrayList<String>();

        LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType), RestrictionsFactoryUtil.like(Constants.LIST_NAME, Constants.ALL)));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(helperTable.getDescription());

            }
        }
        LOGGER.info(" getDropDownList method ends with return value strList size =" + helperList.size());

        return helperList;
    }

    /**
     * Gets the trading partner name list.
     *
     * @return the list of Helper DTO
     */
    public List<HelperDTO> getTradingPartnerNameList() throws SystemException {
        LOGGER.info("Entering  getTradingPartnerNameList method ");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        HelperDTO helperTable;
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        companyDynamicQuery.add(RestrictionsFactoryUtil.ne(Constants.COMPANY_TYPE, Constants.MANUFACTURER));
        final List<CompanyMaster> resultList = dao.getCompanyMasterList(companyDynamicQuery, 0, 10);

        if (resultList != null) {
            for (int i = 0; i < resultList.size(); i++) {
                helperTable = new HelperDTO(resultList.get(i).getCompanyMasterSid(), resultList.get(i).getCompanyId());
                helperList.add(helperTable);
            }
        }
        LOGGER.info("returns helperList size =" + helperList.size() + " and the getTradingPartnerNameList method ends ");
        return helperList;
    }

    /**
     * Gets the company name list.
     *
     * @return the list of Helper DTO.
     */
    public List<HelperDTO> getCompanyNameList() throws SystemException {
        LOGGER.info("entering getCompanyNameList method ");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        HelperDTO helperTable;
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.COMPANY_TYPE, "BUNIT"));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.COMPANY_TYPE));
        final List<CompanyMaster> list = dao.getCompanyMasterList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                helperTable = new HelperDTO(list.get(i).getCompanyMasterSid(), list.get(i).getCompanyId());
                helperList.add(helperTable);
            }
        }
        LOGGER.info("returns helperList size =" + helperList.size() + " and getCompanyNameList method ends");
        return helperList;
    }

    /**
     * Gets the search count.
     *
     * @param searchItemForm the search item form
     * @return the search count
     * @throws SystemException the system exception
     */
    public int getSearchCount(final CustomFieldGroup searchItemForm, final BeanSearchCriteria criteria) throws SystemException {
        LOGGER.info("Entering getCompanyNameList method  ");
        String contractId;
        String contractNo;
        String contractName;
        int contractType;
        int contractStatus;
        String tradeClass = Constants.PERCENT;
        int tradingPartner = 0;
        Map<String, Object> filterMap = new HashMap<String, Object>();

        if (searchItemForm.getField(Constants.TEXT_ONE) == null || searchItemForm.getField(Constants.TEXT_ONE).getValue().toString() == null) {
            contractId = Constants.PERCENT;
        } else {
            contractId = searchItemForm.getField(Constants.TEXT_ONE).getValue().toString().trim();
        }
        LOGGER.info("Value of Contract ID  ---" + contractId);
        if (searchItemForm.getField(Constants.TEXT_TWO) == null || searchItemForm.getField(Constants.TEXT_TWO).getValue().toString() == null) {
            contractNo = Constants.PERCENT;
        } else {
            contractNo = searchItemForm.getField(Constants.TEXT_TWO).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TEXT_THREE) == null || searchItemForm.getField(Constants.TEXT_THREE).getValue().toString() == null) {
            contractName = Constants.PERCENT;
        } else {
            contractName = searchItemForm.getField(Constants.TEXT_THREE).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.COMBO_TWO).getValue() == null || searchItemForm.getField(Constants.COMBO_TWO).getValue().toString() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(Constants.COMBO_TWO).getValue().toString())) {
            contractType = 0;
        } else {
            contractType = ((HelperDTO) searchItemForm.getField(Constants.COMBO_TWO).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.COMBO_ONE).getValue() == null || searchItemForm.getField(Constants.COMBO_ONE).getValue().toString() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(Constants.COMBO_ONE).getValue().toString())) {
            contractStatus = 0;
        } else {
            contractStatus = ((HelperDTO) searchItemForm.getField(Constants.COMBO_ONE).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.TEXT_FOUR) == null || StringUtils.isBlank(searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString()) || searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString() == null) {
            tradeClass = Constants.PERCENT;
        } else {
            tradeClass = searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID) == null || !StringUtils.isNotBlank(searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString())) {
            tradingPartner = 0;
        } else {
            tradingPartner = Integer.valueOf(searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString().replaceAll(Constants.COMMA, StringUtils.EMPTY).trim());
        }
        if (StringUtils.isNotBlank(contractId)) {
            contractId = contractId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(contractNo)) {
            contractNo = contractNo.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(contractName)) {
            contractName = contractName.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (contractType != 0) {
        }
        if (contractStatus != 0) {
        }
        if (StringUtils.isNotBlank(tradeClass)) {
            tradeClass = tradeClass.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }

        if (tradingPartner != Constants.ZERO) {
        }

        filterMap.put(Constants.CONTRACT_ID, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_NO, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_NAME, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_STATUS, 0);
        filterMap.put(Constants.CONTRACT_TYPE, 0);
        filterMap.put(Constants.TRADING_PARTNER_NAME, StringUtils.EMPTY);

        if (criteria != null && criteria.getFilters() != null) {
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (stringFilter.getPropertyId().equals(Constants.CONTRACT_STATUS)) {

                        filterMap.put(Constants.CONTRACT_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(Constants.CONTRACT_TYPE)) {

                        filterMap.put(Constants.CONTRACT_TYPE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)) {
                        String trade = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(Constants.TRADE_CLASS, trade);
                        tradeClass = trade;

                    } else if (stringFilter.getPropertyId().equals(Constants.TRADING_PARTNER_NAME)) {
                        String tradingPartnerName = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(Constants.TRADING_PARTNER_NAME, tradingPartnerName);

                    } else {
                        String filterString = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                    }
                }

            }
        }

        List count = ContractMasterLocalServiceUtil.getContractList(contractId, contractNo, contractName, contractStatus, contractType, tradeClass, tradingPartner, filterMap, null, null, 0, 0, false);

        if (count.isEmpty()) {
            return 0;
        } else {
            return count.size();
        }

    }

    /**
     * Gets the total count.
     *
     * @return the total count
     */
    public int getTotalCount() throws SystemException {
        LOGGER.info("Entering getTotalCount method");

        final int count = dao.getContractMasterCount();
        LOGGER.info("Ends getTotalCount method ");
        return count;
    }

    /**
     * Retrieves the results from the Helper Table based on description or
     * listName and returns as list.
     *
     * @param dynamicQuery - returns the HelperTable model Object based on
     * description or listName
     * @return List of HelperTable model object.
     * @throws SystemException the system exception
     */
    public List<HelperTable> getHelperTableList(final DynamicQuery dynamicQuery) throws SystemException {
        LOGGER.info("Enters getHelperTableList method");
        final List<HelperTable> helperTableList = dao.getHelperTableList(dynamicQuery);
        LOGGER.info(" getHelperTableList method ends after returning helperTableList size=" + helperTableList.size());
        return helperTableList;
    }

    /**
     * Updates the HelperTable and returns the updated HelperTable model object.
     *
     * @param helperTable - HelperTable
     * @return updated HelperTable model object.
     * @throws SystemException the system exception
     */
    public HelperTable updateHelperTable(final HelperTable helperTable) throws SystemException {
        LOGGER.info("Enters updateHelperTable method");
        final HelperTable updatehelperTable = dao.updateHelperTable(helperTable);
        LOGGER.info(" updateHelperTable method ends after returning updatehelperTable ");
        return updatehelperTable;
    }

    public void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        for (int i = 0; dropDownList.size() > i; i++) {
            comboBox.addItem(String.valueOf(dropDownList.get(i)
                    .getId()));
            comboBox.setItemCaption(String.valueOf(dropDownList.get(i)
                    .getId()), dropDownList.get(i).getDescription());

        }
    }

    public boolean getProcessStatus(int systemId) {
        boolean flag = false;
        try {
            ContractMaster contractMaster = dao.getContractMaster(systemId);

            flag = contractMaster.getProcessStatus();

        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(ContractHeaderLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(ContractHeaderLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    /**
     * Method to get attached doc List
     *
     * @param masterTableSid
     * @param moduleName
     * @return attachmentDTOList
     */
    @SuppressWarnings("unchecked")
    public List<AdditionalInfoDTO> getAttachmentDTOList(int masterTableSid,
            String moduleName, String filepath, String id) {
        List<AdditionalInfoDTO> attachmentDTOList = new ArrayList<AdditionalInfoDTO>();
        DynamicQuery docDetailsDynamicQuery = DynamicQueryFactoryUtil
                .forClass(MasterDataFiles.class);
        docDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constants.MASTER_TABLE_SID,
                masterTableSid));
        docDetailsDynamicQuery.add(RestrictionsFactoryUtil.ilike(
                Constants.MASTER_TABLE_NAME, moduleName));
        List<MasterDataFiles> docDetailsList = null;
        AdditionalInfoDTO attachmentDTO;
        try {
            docDetailsList = MasterDataFilesLocalServiceUtil
                    .dynamicQuery(docDetailsDynamicQuery);

            if (docDetailsList != null && docDetailsList.size() > 0) {
                for (MasterDataFiles docDetails : docDetailsList) {
                    attachmentDTO = new AdditionalInfoDTO();
                    attachmentDTO.setDocDetailsId(docDetails.getMasterDataFilesSid());
                    String filePath = docDetails.getFilePath();
                    attachmentDTO.setDocumentFullPath(filePath);
                    attachmentDTO.setDocumentName(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("_")) + filePath.substring(filePath.lastIndexOf(".")));
                    String tempfilePath = docDetails.getFilePath();
                    attachmentDTO.setDocumentFullPath(tempfilePath);
                    String fileNameWithId = tempfilePath.replace(filepath, StringUtils.EMPTY);
                    StringBuilder fileName = new StringBuilder(fileNameWithId);
                    fileName.replace(fileName.lastIndexOf("_"), fileName.lastIndexOf("."), StringUtils.EMPTY);
                    attachmentDTO.setDocumentName(fileName.toString());
                    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    TimeZone central = TimeZone.getTimeZone("EST");
                    format.setTimeZone(central);
                    attachmentDTO.setDateAdded(format.format(docDetails.getCreatedDate()));
                    attachmentDTO.setUserName(StplSecurity.userMap.get(docDetails.getCreatedBy()));
                    attachmentDTOList.add(attachmentDTO);
                }
            }
        } catch (SystemException e) {
            LOGGER.error(e);
        }

        return attachmentDTOList;
    }

    /**
     * Method o Delete the Uploaded File
     *
     * @param docDetailsId
     * @param moduleName
     * @param fileName
     * @return true or false
     */
    public Boolean deleteUploadedFile(int docDetailsId, String moduleName,
            String fileName) {
        if (docDetailsId != 0) {
            try {
                MasterDataFilesLocalServiceUtil.deleteMasterDataFiles(docDetailsId);
            } catch (PortalException ex) {
                java.util.logging.Logger.getLogger(ContractHeaderLogic.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                java.util.logging.Logger.getLogger(ContractHeaderLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        File file = new File(fileName);
        file.delete();
        return true;
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
                    java.util.logging.Logger.getLogger(ContractHeaderLogic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(ContractHeaderLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            File file = new File(obj.getDocumentFullPath());
            file.delete();
        }
    }


    /*
     *Security Implementation for  Fields
     */
    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<Object>();
        try {
            resultList = ContractMasterLocalServiceUtil.fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    /**
     * Returns the contract master based on search Item form.
     *
     * @param searchItemForm the search item form
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the list< contract master dto>
     * @throws PortalException
     */
    @SuppressWarnings("unchecked")
    public List<SearchResultsDTO> searchContractResults(final CustomFieldGroup searchItemForm, final int start, final int end, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) throws SystemException,
            PortalException {
        String contractId;
        String contractNo;
        String contractName;
        int contractType;
        int contractStatus;
        String tradeClass;
        int tradingPartner;

        UIUtils.loadColumnName();
        Map<String, Object> filterMap = new HashMap<String, Object>();
        LOGGER.info("Enters searchContractResults with parameters start= " + start + " ,end=" + end + ", sortByColumns size =" + sortByColumns.size());
        if (searchItemForm.getField(Constants.TEXT_ONE) == null || searchItemForm.getField(Constants.TEXT_ONE).getValue().toString() == null) {
            contractId = Constants.PERCENT;
        } else {
            contractId = searchItemForm.getField(Constants.TEXT_ONE).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TEXT_TWO) == null || searchItemForm.getField(Constants.TEXT_TWO).getValue().toString() == null) {
            contractNo = Constants.PERCENT;
        } else {
            contractNo = searchItemForm.getField(Constants.TEXT_TWO).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TEXT_THREE) == null || searchItemForm.getField(Constants.TEXT_THREE).getValue().toString() == null) {
            contractName = Constants.PERCENT;
        } else {
            contractName = searchItemForm.getField(Constants.TEXT_THREE).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.COMBO_TWO).getValue() == null || searchItemForm.getField(Constants.COMBO_TWO).getValue().toString() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(Constants.COMBO_TWO).getValue().toString())) {
            contractType = 0;
        } else {
            contractType = ((HelperDTO) searchItemForm.getField(Constants.COMBO_TWO).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.COMBO_ONE).getValue() == null || searchItemForm.getField(Constants.COMBO_ONE).getValue().toString() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(Constants.COMBO_ONE).getValue().toString())) {
            contractStatus = 0;
        } else {
            contractStatus = ((HelperDTO) searchItemForm.getField(Constants.COMBO_ONE).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.TEXT_FOUR) == null || StringUtils.isBlank(searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString()) || searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString() == null) {
            tradeClass = Constants.PERCENT;
        } else {
            tradeClass = searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID) == null || !StringUtils.isNotBlank(searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString())) {
            tradingPartner = 0;
        } else {
            tradingPartner = Integer.valueOf(searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString().replaceAll(Constants.COMMA, StringUtils.EMPTY).trim());
        }

        if (StringUtils.isNotBlank(contractId)) {
            contractId = contractId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(contractNo)) {
            contractNo = contractNo.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(contractName)) {
            contractName = contractName.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(tradeClass)) {
            tradeClass = tradeClass.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);

        }

        if (tradingPartner != Constants.ZERO) {

        }

        filterMap.put(Constants.CONTRACT_ID, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_NO, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_NAME, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_STATUS, 0);
        filterMap.put(Constants.CONTRACT_TYPE, 0);
        filterMap.put(Constants.TRADING_PARTNER_NAME, StringUtils.EMPTY);

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (stringFilter.getPropertyId().equals(Constants.CONTRACT_STATUS)) {

                        filterMap.put(Constants.CONTRACT_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(Constants.CONTRACT_TYPE)) {

                        filterMap.put(Constants.CONTRACT_TYPE, Integer.valueOf(stringFilter.getFilterString()));

                    } else if (stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)) {
                        String trade = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(Constants.TRADE_CLASS, trade);
                        tradeClass = trade;

                    } else if (stringFilter.getPropertyId().equals(Constants.TRADING_PARTNER_NAME)) {
                        String tradingPartnerName = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(Constants.TRADING_PARTNER_NAME, tradingPartnerName);

                    } else {
                        String filterString = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                    }
                }

            }
        }

        String column = "CONTRACT_MASTER_SID";
        String orderBy = Constants.ASC;

        for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator
                .hasNext();) {
            final SortByColumn sortByColumn = (SortByColumn) iterator.next();
            String columnName = sortByColumn.getName();

            if (Constants.CONTRACT_ID.equals(columnName)) {
                column = Constants.CONTRACT_ID_CAPS;
            } else if (Constants.CONTRACT_NO.equals(columnName)) {
                column = Constants.CONTRACT_NO_CAPS;
            } else if (Constants.CONTRACT_NAME.equals(columnName)) {
                column = Constants.CONTRACT_NAME_CAPS;
            } else if (Constants.CONTRACT_STATUS.equals(columnName)) {
                column = Constants.CON_STATUS;
            } else if (Constants.CONTRACT_TYPE.equals(columnName)) {
                column = Constants.CON_TYPE;
            } else if (Constants.TRADE_CLASS.equals(columnName)) {
                column = Constants.CON_TRADE_CLASS;
            } else if (Constants.TRADING_PARTNER_NAME.equals(columnName)) {
                column = Constants.COMP_NAME;
            }
            if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                orderBy = Constants.ASC;
            } else {
                orderBy = "DESC";
            }
        }

        List result = ContractMasterLocalServiceUtil.getContractList(contractId, contractNo, contractName, contractStatus, contractType, tradeClass, tradingPartner, filterMap, orderBy, column, start, end, true);

        List<SearchResultsDTO> searchList = getCustomizedSearchFilter(result);

        LOGGER.info("searchContractResults Returns searchList size =" + result.size() + "  and  searchContractMaster method ends");
        return searchList;
    }

    /**
     * Gets the search count.
     *
     * @param searchItemForm the search item form
     * @param filterSet
     * @return the search count
     * @throws SystemException the system exception
     */
    public int getContractCount(final CustomFieldGroup searchItemForm, final Set<Container.Filter> filterSet) throws SystemException {
        LOGGER.info("Entering getContractCount method  ");
        String contractId;
        String contractNo;
        String contractName;
        int contractType;
        int contractStatus;
        String tradeClass = Constants.PERCENT;
        int tradingPartner = 0;
        Map<String, Object> filterMap = new HashMap<String, Object>();

        if (searchItemForm.getField(Constants.TEXT_ONE) == null || searchItemForm.getField(Constants.TEXT_ONE).getValue().toString() == null) {
            contractId = Constants.PERCENT;
        } else {
            contractId = searchItemForm.getField(Constants.TEXT_ONE).getValue().toString().trim();
        }
        LOGGER.info("Value of Contract ID  ---" + contractId);
        if (searchItemForm.getField(Constants.TEXT_TWO) == null || searchItemForm.getField(Constants.TEXT_TWO).getValue().toString() == null) {
            contractNo = Constants.PERCENT;
        } else {
            contractNo = searchItemForm.getField(Constants.TEXT_TWO).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TEXT_THREE) == null || searchItemForm.getField(Constants.TEXT_THREE).getValue().toString() == null) {
            contractName = Constants.PERCENT;
        } else {
            contractName = searchItemForm.getField(Constants.TEXT_THREE).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.COMBO_TWO).getValue() == null || searchItemForm.getField(Constants.COMBO_TWO).getValue().toString() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(Constants.COMBO_TWO).getValue().toString())) {
            contractType = 0;
        } else {
            contractType = ((HelperDTO) searchItemForm.getField(Constants.COMBO_TWO).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.COMBO_ONE).getValue() == null || searchItemForm.getField(Constants.COMBO_ONE).getValue().toString() == null || Constants.SELECT_ONE.equals(searchItemForm.getField(Constants.COMBO_ONE).getValue().toString())) {
            contractStatus = 0;
        } else {
            contractStatus = ((HelperDTO) searchItemForm.getField(Constants.COMBO_ONE).getValue()).getId();
        }
        if (searchItemForm.getField(Constants.TEXT_FOUR) == null || StringUtils.isBlank(searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString()) || searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString() == null) {
            tradeClass = Constants.PERCENT;
        } else {
            tradeClass = searchItemForm.getField(Constants.TEXT_FOUR).getValue().toString().trim();
        }
        if (searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID) == null || !StringUtils.isNotBlank(searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString())) {
            tradingPartner = 0;
        } else {
            tradingPartner = Integer.valueOf(searchItemForm.getField(Constants.TRADING_PARTNER_SYS_ID).getValue().toString().replaceAll(Constants.COMMA, StringUtils.EMPTY).trim());
        }
        if (StringUtils.isNotBlank(contractId)) {
            contractId = contractId.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(contractNo)) {
            contractNo = contractNo.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(contractName)) {
            contractName = contractName.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(tradeClass)) {
            tradeClass = tradeClass.replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
        }
        filterMap.put(Constants.CONTRACT_ID, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_NO, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_NAME, Constants.PERCENT);
        filterMap.put(Constants.CONTRACT_STATUS, 0);
        filterMap.put(Constants.CONTRACT_TYPE, 0);
        filterMap.put(Constants.TRADING_PARTNER_NAME, StringUtils.EMPTY);

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (stringFilter.getPropertyId().equals(Constants.CONTRACT_STATUS)) {

                        filterMap.put(Constants.CONTRACT_STATUS, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(Constants.CONTRACT_TYPE)) {

                        filterMap.put(Constants.CONTRACT_TYPE, Integer.valueOf(stringFilter.getFilterString()));
                    } else if (stringFilter.getPropertyId().equals(Constants.TRADE_CLASS)) {
                        String trade = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(Constants.TRADE_CLASS, trade);
                        tradeClass = trade;

                    } else if (stringFilter.getPropertyId().equals(Constants.TRADING_PARTNER_NAME)) {
                        String tradingPartnerName = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(Constants.TRADING_PARTNER_NAME, tradingPartnerName);

                    } else {
                        String filterString = Constants.PERCENT + stringFilter.getFilterString() + Constants.PERCENT;
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                    }
                }

            }
        }

        List count = ContractMasterLocalServiceUtil.getContractList(contractId, contractNo, contractName, contractStatus, contractType, tradeClass, tradingPartner, filterMap, null, null, 0, 0, false);

        if (count != null && !count.isEmpty()) {
            return count.size();
        } else {
            return 0;
        }

    }

}
