
package com.stpl.app.global.rebateschedule.logic;

import com.stpl.app.global.abstractsearch.dto.SearchResultsDTO;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.dao.impl.CompanySearchLogicDAOImpl;
import com.stpl.app.global.dao.impl.RebateScheduleLogicDAOImpl;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.rebateschedule.dto.IFPDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.ImtdRsDetailsFrDTO;
import com.stpl.app.global.rebateschedule.dto.ItemDetailsDTO;
import com.stpl.app.global.rebateschedule.dto.NetSalesFormulaDTO;
import com.stpl.app.global.rebateschedule.dto.RSFormulaDTO;
import com.stpl.app.global.rebateschedule.dto.RebatePlanDTO;
import com.stpl.app.global.rebateschedule.dto.RebateScheduleMasterDTO;
import com.stpl.app.global.rebateschedule.dto.RebateScheduleSearchDTO;
import com.stpl.app.global.rebateschedule.dto.RsDeductionLookupDto;
import com.stpl.app.global.rebateschedule.util.RsUtils;
import com.stpl.app.model.CdrModel;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastingFormula;
import com.stpl.app.model.FormulaDetailsMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ImtdRsDetails;
import com.stpl.app.model.ImtdRsDetailsFr;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.model.RebateTierFormula;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.model.Udcs;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.service.CdrModelLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ForecastingFormulaLocalServiceUtil;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdRsDetailsFrLocalServiceUtil;
import com.stpl.app.service.ImtdRsDetailsLocalServiceUtil;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.app.ui.UDCIncrementCheck;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperUtils;
import com.stpl.app.util.xmlparser.SQLUtil;
import com.stpl.domain.global.company.CompanyMasterDAO;
import com.stpl.domain.global.rebateschedule.RebateLogic;
import com.stpl.domain.global.rebateschedule.RebateScheduleDAO;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;
// TODO: Auto-generated Javadoc
/**
 * Class to implement logic for Rebate Schedule .
 *
 */
public class RebateScheduleLogic extends BeanItemContainer<RsModel> implements RebateLogic{

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebateScheduleLogic.class);
    /**
     * The dao.
     */
    private static final RebateScheduleDAO DAO = new RebateScheduleLogicDAOImpl();
    private static final CompanyMasterDAO companySearchLogicDao = new CompanySearchLogicDAOImpl();
    private static int formulaIdCount;    
    private static int rebateCount;       
    
    //Java date format
    public static String DEFAULT_JAVA_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    //SQL date format
    public static String DEFAULT_SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    
    HelperListUtil helperListUtil = HelperListUtil.getInstance(); 
    SessionDTO sessionDTO;
    public static ResourceBundle constantProperties = ResourceBundle.getBundle("properties.constants");
    static HashMap<String, String> criteria = new HashMap<>();
    public static final SimpleDateFormat DB_DATE = new SimpleDateFormat("yyyy-MM-dd");
    static HashMap<String, String> deductionCriteria = new HashMap<>();
    static HashMap<String, String> deductionFilter = new HashMap<>();

    static HashMap<String, String> rsFormulaDbMap = new HashMap<>();

    
    /**
     * getting count for Rebate Plan Name
     *
     * @param filter
     * @return
     */
    public static int getRebatePlanCount(String filter, final HelperDTO rebatePlan) throws PortalException, SystemException {
        LOGGER.debug("Entering getRebatePlanCount method ");
        filter = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        List<Object[]> rebateList;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.REBATE_PLAN_NAME, filter));
        if (rebatePlan != null && rebatePlan.getId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.REBATE_PLAN_MASTER_SID, rebatePlan.getId()));
        }
        dynamicQuery.setProjection(ProjectionFactoryUtil.count(ConstantsUtils.REBATE_PLAN_NAME));
        rebateList = DAO.getRebatePlanList(dynamicQuery);
        rebateCount = Integer.parseInt(String.valueOf(rebateList.get(0)));
        LOGGER.debug("Ending getRebatePlanCount method : returning count :" + rebateCount);
        return rebateCount;
    }

    /**
     *
     * @param start
     * @param end
     * @param filter
     * @return
     */
    public static List<HelperDTO> getRebatePlanResults(int start, int end, String filter, final HelperDTO rebatePlan) throws PortalException, SystemException {
        LOGGER.debug("Entering getLazyBrandCount method ");
        filter = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        List<Object[]> qualifierList;
        final List<HelperDTO> list = new ArrayList<>();
        int startValue;
        int endValue;
        if (start == Constants.ZERO) {
            startValue = start;
            endValue = end - 1;
        } else {
            startValue = start - 1;
            endValue = end - 1;
        }

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.REBATE_PLAN_NAME, filter));
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        dynamicQuery.setLimit(startValue, endValue);
        if (rebatePlan != null && rebatePlan.getId() != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.REBATE_PLAN_MASTER_SID, rebatePlan.getId()));
        }
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.REBATE_PLAN_MASTER_SID));
        projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.REBATE_PLAN_NAME));
        dynamicQuery.setProjection(projectionList);
        dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.REBATE_PLAN_NAME));

        qualifierList = DAO.getRebatePlanList(dynamicQuery);

        HelperDTO dto;
        if (start == Constants.ZERO) {
            dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
            dto.setDescription(ConstantsUtils.SELECT_ONE);
            list.add(dto);
            if (rebatePlan != null && rebatePlan.getId() != 0) {
                list.add(rebatePlan);
            }

        }
        for (final Iterator<Object[]> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            dto = new HelperDTO(StringUtils.EMPTY);
            dto.setId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
            dto.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            list.add(dto);
        }
        LOGGER.debug("return Brand size -" + list.size());
        return list;
    }
    private final String user = VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString();
    
    /**
     * Constructor.
     */
    public RebateScheduleLogic() {
        super(RsModel.class);
    }
    public RebateScheduleLogic(final SessionDTO sessionDTO) {
        super(RsModel.class);
        this.sessionDTO=sessionDTO;
    }

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public RebateScheduleDAO getDao() {
        return DAO;
    }
    
    public List<SearchResultsDTO> getCustomizedSearchForHelper(final List list) {
        LOGGER.debug("Entering getCustomizedSearchForHelper()");
        final List<SearchResultsDTO> searchItemList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SearchResultsDTO searchRSForm = new SearchResultsDTO();

               final Object[] obj = (Object[]) list.get(i);
                searchRSForm.setRsSystemId(String.valueOf(String.valueOf(obj[0])));
                searchRSForm.setSystemID(searchRSForm.getRsSystemId());
                searchRSForm.setRebateScheduleId(String.valueOf(obj[1]));                
                searchRSForm.setRebateScheduleNo(obj[NumericConstants.TWO]!=null ? String.valueOf(obj[NumericConstants.TWO]) : StringUtils.EMPTY);                                
                searchRSForm.setRebateScheduleName(obj[NumericConstants.THREE]!=null ? String.valueOf(obj[NumericConstants.THREE]) : StringUtils.EMPTY);                
                searchRSForm.setRebateScheduleType(obj[NumericConstants.SEVEN]!=null ? String.valueOf(obj[NumericConstants.SEVEN]) : StringUtils.EMPTY);               
                searchRSForm.setRebateScheduleStatus(obj[NumericConstants.FOUR]!=null ? String.valueOf(obj[NumericConstants.EIGHT]) : StringUtils.EMPTY);               
                searchRSForm.setRsProgramType(obj[NumericConstants.NINE]!=null ? String.valueOf(obj[NumericConstants.NINE]) : StringUtils.EMPTY);               
                searchRSForm.setRsCategory(obj[NumericConstants.TEN]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TEN])) ? String.valueOf(obj[NumericConstants.TEN]) : StringUtils.EMPTY);               
                searchRSForm.setRsDesignation(obj[NumericConstants.THIRTEEN]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTEEN])) ? String.valueOf(obj[NumericConstants.THIRTEEN]) : StringUtils.EMPTY);               
                searchRSForm.setParentId(obj[NumericConstants.FOURTEEN]!=null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : StringUtils.EMPTY);               
                searchRSForm.setParentName(obj[NumericConstants.FIFTEEN]!=null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : StringUtils.EMPTY);                
                searchRSForm.setRecordLockStatus((Boolean)obj[NumericConstants.SIXTEEN]);             
                searchRSForm.setRsTradeClass(obj[NumericConstants.SEVENTEEN]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.SEVENTEEN])) ?  String.valueOf(obj[NumericConstants.SEVENTEEN]) : StringUtils.EMPTY);                
                searchRSForm.setRebateScheduleAliasID(obj[NumericConstants.EIGHTEEN]!=null ? String.valueOf(obj[NumericConstants.EIGHTEEN]) : StringUtils.EMPTY);                
                searchRSForm.setRebateFrequency(obj[NumericConstants.NINETEEN]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.NINETEEN])) ? String.valueOf(obj[NumericConstants.NINETEEN]) : StringUtils.EMPTY);               
                searchRSForm.setCalendar(obj[NumericConstants.TWENTY]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TWENTY])) ? String.valueOf(obj[NumericConstants.TWENTY]) : StringUtils.EMPTY);                
                searchRSForm.setCalculationType(obj[NumericConstants.TWENTY_ONE]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TWENTY_ONE])) ? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : StringUtils.EMPTY);                
                searchRSForm.setCalculationLevel(obj[NumericConstants.TWENTY_TWO]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TWENTY_TWO])) ? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : StringUtils.EMPTY);               
                searchRSForm.setRebateRuleType(obj[NumericConstants.TWENTY_THREE]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TWENTY_THREE])) ? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : StringUtils.EMPTY);                
                searchRSForm.setRebateRuleAssociation(obj[NumericConstants.TWENTY_FOUR]!=null ? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : StringUtils.EMPTY);                
                searchRSForm.setPaymentTerms(obj[NumericConstants.TWENTY_FIVE]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TWENTY_FIVE])) ? String.valueOf(obj[NumericConstants.TWENTY_FIVE]) : StringUtils.EMPTY);                
                searchRSForm.setPaymentMethod(obj[NumericConstants.TWENTY_SIX]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TWENTY_SIX])) ? String.valueOf(obj[NumericConstants.TWENTY_SIX]) : StringUtils.EMPTY);                
                searchRSForm.setPaymentGracePeriod(obj[NumericConstants.TWENTY_SEVEN]!=null ? String.valueOf(obj[NumericConstants.TWENTY_SEVEN]) : StringUtils.EMPTY);                
                searchRSForm.setPaymentFrequency(obj[NumericConstants.TWENTY_EIGHT]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TWENTY_EIGHT])) ? String.valueOf(obj[NumericConstants.TWENTY_EIGHT]) : StringUtils.EMPTY);                
                searchRSForm.setInterestBearingIndicator(obj[NumericConstants.TWENTY_NINE]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TWENTY_NINE])) && !"0".equals(String.valueOf(obj[NumericConstants.TWENTY_NINE]).trim()) ? CommonUtils.getDescription((int)(obj[NumericConstants.TWENTY_NINE])) : StringUtils.EMPTY);              
                searchRSForm.setInterestBearingBasis(obj[NumericConstants.THIRTY]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTY])) && !"0".equals(String.valueOf(obj[NumericConstants.THIRTY]).trim()) ? CommonUtils.getDescription((int)(obj[NumericConstants.THIRTY])) : StringUtils.EMPTY);               
                searchRSForm.setRsTransactionReferenceID(obj[NumericConstants.THIRTY_ONE]!=null ? String.valueOf(obj[NumericConstants.THIRTY_ONE]) : StringUtils.EMPTY);                
                searchRSForm.setRsTransactionReferenceName(obj[NumericConstants.THIRTY_TWO]!=null ? String.valueOf(obj[NumericConstants.THIRTY_TWO]) : StringUtils.EMPTY);                
                searchRSForm.setUdc1(obj[NumericConstants.THIRTY_THREE]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTY_THREE])) ? String.valueOf(obj[NumericConstants.THIRTY_THREE]) : StringUtils.EMPTY);                
                searchRSForm.setUdc2(obj[NumericConstants.THIRTY_FOUR]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTY_FOUR])) ? String.valueOf(obj[NumericConstants.THIRTY_FOUR]) : StringUtils.EMPTY);                
                searchRSForm.setUdc3(obj[NumericConstants.THIRTY_FIVE]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTY_FIVE])) ? String.valueOf(obj[NumericConstants.THIRTY_FIVE]) : StringUtils.EMPTY);                
                searchRSForm.setUdc4(obj[NumericConstants.THIRTY_SIX]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTY_SIX])) ? String.valueOf(obj[NumericConstants.THIRTY_SIX]) : StringUtils.EMPTY);
                searchRSForm.setUdc5(obj[NumericConstants.THIRTY_SEVEN]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTY_SEVEN])) ? String.valueOf(obj[NumericConstants.THIRTY_SEVEN]) : StringUtils.EMPTY);                
                searchRSForm.setUdc6(obj[NumericConstants.THIRTY_EIGHT]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTY_EIGHT])) ? String.valueOf(obj[NumericConstants.THIRTY_EIGHT]) : StringUtils.EMPTY);               
                searchItemList.add(searchRSForm);
            }
        }
        LOGGER.debug("End of getCustomizedSearchForml method");
        return searchItemList;
    }

    /**
     * 
     * @param rebateSchForm
     * @param itemDetails
     * @param availableUploadedInformation
     * @param addedNotes
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception 
     */
    public String saveRS(final ErrorfulFieldGroup rebateSchForm, final List<ItemDetailsDTO> itemDetails, final List<NotesDTO> availableUploadedInformation, final String addedNotes) throws SystemException, PortalException {
       try{
        LOGGER.debug("Entering saveRS P2: -" + ((itemDetails == null) ? itemDetails : itemDetails.size()));
        RsModel rebateSchedule;
         SimpleDateFormat date = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        String systemId = rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_SYSTEM_ID).getValue() == null && rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_SYSTEM_ID).getValue().equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_SYSTEM_ID).getValue());
        String sysId = systemId.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
        String rsID = String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue()).trim();
        //to check RSID in D status to update it with new info
        final DynamicQuery rsQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
        rsQuery.add(RestrictionsFactoryUtil.eq("rsId", rsID));
        rsQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        final List<RsModel> rebateMaster = DAO.getRebateScheduleMasterList(rsQuery);
        
        if ((sysId.equals(ConstantsUtils.NULL) || sysId.equals(ConstantsUtils.ZERO) ||(ConstantsUtils.COPY).equals(sessionDTO.getMode())) && rebateMaster.isEmpty()
                ) {
            rebateSchedule = RsModelLocalServiceUtil.createRsModel(0);
            rebateSchedule.setInboundStatus(ConstantsUtils.INBOUND_STATUS_A);
        } else {
            LOGGER.debug("In saveRS rsSystemId=" + systemId);
            if (!rebateMaster.isEmpty()) {
                sysId = String.valueOf(rebateMaster.get(0).getRsModelSid());
                systemId=sysId;
            }
            rebateSchedule = DAO.getRebateScheduleMasterBySystemId(Integer.parseInt(sysId));
            rebateSchedule.setInboundStatus(!rebateMaster.isEmpty() ? ConstantsUtils.INBOUND_STATUS_A : ConstantsUtils.INBOUND_STATUS_C);
        }
        // Rebate Schedule Information Section
        rebateSchedule.setRsId(String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue()).trim());
        rebateSchedule.setRsNo(String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NO).getValue()).trim());
        rebateSchedule.setRsName(String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue()).trim());
        rebateSchedule.setRsStatus(checkEmptyDataFromFields(ConstantsUtils.REBATE_SCHEDULE_STATUS,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_STATUS).getValue()).getId());
        rebateSchedule.setRsType(checkEmptyDataFromFields(ConstantsUtils.REBATE_SCHEDULE_TYPE,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_TYPE).getValue()).getId());
        rebateSchedule.setRebateProgramType(checkEmptyDataFromFields(ConstantsUtils.REBATE_PROGRAM_TYPE,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.REBATE_PROGRAM_TYPE).getValue()).getId());
        rebateSchedule.setRsCategory(checkEmptyDataFromFields(ConstantsUtils.REBATE_SCHEDULE_CATEGORY,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_CATEGORY).getValue()).getId());
        rebateSchedule.setRsTradeClass(checkEmptyDataFromFields(ConstantsUtils.TRADE_CLASS,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.TRADE_CLASS).getValue()).getId());
        rebateSchedule.setRsDesignation(checkEmptyDataFromFields(ConstantsUtils.REBATE_SCHEDULE_DESIGNATION,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_DESIGNATION).getValue()).getId());
        rebateSchedule.setRsAlias(String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ALIAS).getValue()));
        rebateSchedule.setRsTransRefId(String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_TRANS_REF_NO).getValue()));
        rebateSchedule.setRsTransRefNo(String.valueOf(((CustomTextField)rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_TRANS_REF_NO)).getData()));
        rebateSchedule.setRsTransRefName(String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_TRANS_REF_NAME).getValue()));        
        rebateSchedule.setParentRsId(String.valueOf(rebateSchForm.getField(ConstantsUtils.PARENT_REBATE_SCHEDULE_ID).getValue()));
        rebateSchedule.setParentRsName(String.valueOf(rebateSchForm.getField(ConstantsUtils.PARENT_REBATE_SCHEDULE_NAME).getValue()));
        
        // Rebate Options Section
               
        rebateSchedule.setRsCalendar(checkEmptyDataFromFields(ConstantsUtils.CALENDER,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.CALENDER).getValue()).getId());
        rebateSchedule.setRebateFrequency(checkEmptyDataFromFields(ConstantsUtils.REBATE_FREQUENCY,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.REBATE_FREQUENCY).getValue()).getId());
        rebateSchedule.setPaymentFrequency(checkEmptyDataFromFields(ConstantsUtils.PAYMENT_FREQUENCY,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.PAYMENT_FREQUENCY).getValue()).getId());
        rebateSchedule.setPaymentGracePeriod(String.valueOf(rebateSchForm.getField(ConstantsUtils.PAYMENT_GRACE_PEERIOD).getValue()));        
        rebateSchedule.setPaymentLevel(checkEmptyDataFromFields(ConstantsUtils.PAYMENT_LEVEL,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.PAYMENT_LEVEL).getValue()).getId());
        rebateSchedule.setPaymentTerms(checkEmptyDataFromFields(ConstantsUtils.PAYMENT_TERMS,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.PAYMENT_TERMS).getValue()).getId());
        rebateSchedule.setPaymentMethod(checkEmptyDataFromFields(ConstantsUtils.PAYMENT_METHOD,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.PAYMENT_METHOD).getValue()).getId());
        rebateSchedule.setRebateRuleType(checkEmptyDataFromFields(ConstantsUtils.REBATE_RULE_TYPE,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.REBATE_RULE_TYPE).getValue()).getId());
        rebateSchedule.setCalculationType(checkEmptyDataFromFields(ConstantsUtils.CALCULATION_TYPE,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.CALCULATION_TYPE).getValue()).getId());
        rebateSchedule.setCalculationLevel(checkEmptyDataFromFields(ConstantsUtils.CALCULATION_LEVEL,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.CALCULATION_LEVEL).getValue()).getId());   
        if (rebateSchForm.getField(ConstantsUtils.REBATE_PLAN_LEVEL).getValue() != null && !ConstantsUtils.NULL.equals(rebateSchForm.getField(ConstantsUtils.REBATE_PLAN_LEVEL).getValue().toString())) {
            rebateSchedule.setRebatePlanLevel(String.valueOf(rebateSchForm.getField(ConstantsUtils.REBATE_PLAN_LEVEL).getValue()));
        }else{
            rebateSchedule.setRebatePlanLevel(StringUtils.EMPTY);
        }
        rebateSchedule.setRsValidationProfile(checkEmptyDataFromFields(ConstantsUtils.VALIDATION_PROFILE,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.VALIDATION_PROFILE).getValue()).getId());
        rebateSchedule.setInterestBearingIndicator(checkEmptyDataFromFields(ConstantsUtils.INTEREST_BEARING_INDICATOR,rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField(ConstantsUtils.INTEREST_BEARING_INDICATOR).getValue()).getId());
        rebateSchedule.setInterestBearingBasis(checkEmptyDataFromFields("interestBearingBasisInfo",rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField("interestBearingBasisInfo").getValue()).getId());
        rebateSchedule.setRebateProcessingType(checkEmptyDataFromFields("rebateProcessingTypeDDLB",rebateSchForm) ? Constants.ZERO : ((HelperDTO)rebateSchForm.getField("rebateProcessingTypeDDLB").getValue()).getId());                
        int tempInterestBearingBasis = checkEmptyDataFromFields("interestBearingBasisDDLB", rebateSchForm) ? Constants.ZERO : ((HelperDTO) rebateSchForm.getField("interestBearingBasisDDLB").getValue()).getId();
        int tempInterestBearingIndicator = checkEmptyDataFromFields("interestBearingIndicatorDDLB", rebateSchForm) ? Constants.ZERO : ((HelperDTO) rebateSchForm.getField("interestBearingIndicatorDDLB").getValue()).getId();
        if (rebateSchedule.getInterestBearingBasis() == 0 && tempInterestBearingBasis != 0) {
            rebateSchedule.setInterestBearingBasis(tempInterestBearingBasis);
        }
        if (rebateSchedule.getInterestBearingIndicator() == 0 && tempInterestBearingIndicator != 0) {
            rebateSchedule.setInterestBearingIndicator(tempInterestBearingIndicator);
        }
        
        rebateSchedule.setRsStartDate(new Date());

        rebateSchedule.setRecordLockStatus(false);
        rebateSchedule.setInternalNotes(addedNotes);
         
        rebateSchedule.setDeductionInclusion(checkEmptyDataFromFields("deductionInclusion",rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((HelperDTO)rebateSchForm.getField("deductionInclusion").getValue()).getId()));
       
        rebateSchedule.setEvaluationRuleLevel(checkEmptyDataFromFields(ConstantsUtils.EVALUATION_RULE_LEVEL,rebateSchForm) ? ConstantsUtils.ZERO :  String.valueOf(((HelperDTO)rebateSchForm.getField(ConstantsUtils.EVALUATION_RULE_LEVEL).getValue()).getId()));
        rebateSchedule.setEvaluationRuleType(checkEmptyDataFromFields(ConstantsUtils.EVALUATION_RULE_TYPE,rebateSchForm) ? ConstantsUtils.ZERO :  String.valueOf(((HelperDTO)rebateSchForm.getField(ConstantsUtils.EVALUATION_RULE_TYPE).getValue()).getId()));
      
        rebateSchedule.setCalculationRuleLevel(checkEmptyDataFromFields(ConstantsUtils.CALCULATION_RULE_LEVEL,rebateSchForm) ? ConstantsUtils.ZERO :  String.valueOf(((HelperDTO)rebateSchForm.getField(ConstantsUtils.CALCULATION_RULE_LEVEL).getValue()).getId()));
         
        
        if(!ConstantsUtils.ZERO.equals(String.valueOf(rebateSchForm.getField(ConstantsUtils.EVALUATION_SYSTEM_ID).getValue()).trim())&&!(String.valueOf(rebateSchForm.getField(ConstantsUtils.EVALUATION_SYSTEM_ID).getValue()).trim()).isEmpty()){
        rebateSchedule.setEvaluationRuleOrAssociation(String.valueOf(rebateSchForm.getField(ConstantsUtils.EVALUATION_SYSTEM_ID).getValue()).trim());
        }
        if(!ConstantsUtils.ZERO.equals(String.valueOf(rebateSchForm.getField(ConstantsUtils.CALCULATION_SID).getValue()).trim())&&!(String.valueOf(rebateSchForm.getField(ConstantsUtils.CALCULATION_SID).getValue()).trim()).isEmpty()){
        rebateSchedule.setCalculationRule(String.valueOf(rebateSchForm.getField(ConstantsUtils.CALCULATION_SID).getValue()).trim());
         }
        
       
           if ((Date) rebateSchForm.getField(ConstantsUtils.START_DATE).getValue() != null) {
               rebateSchedule.setRsStartDate(new Date(date.format((Date) rebateSchForm.getField(ConstantsUtils.START_DATE).getValue()).trim()));
           }
           if ((Date) rebateSchForm.getField(ConstantsUtils.END_DATE).getValue() != null) {
               rebateSchedule.setRsEndDate(new Date(date.format((Date) rebateSchForm.getField(ConstantsUtils.END_DATE).getValue()).trim()));
           }
        final String user = VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString();
        if (systemId.equals(ConstantsUtils.NULL) || systemId.equals(ConstantsUtils.ZERO) ||(ConstantsUtils.COPY).equals(sessionDTO.getMode())) {
            rebateSchedule.setCreatedDate(new Date());
            rebateSchedule.setModifiedDate(new Date());
            rebateSchedule.setCreatedBy(Integer.valueOf(user));
            rebateSchedule.setModifiedBy(Integer.valueOf(user));
            LOGGER.debug("In saveRS  rebateScheduleDynamicQuery");
            
            final DynamicQuery rebateSchQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
            rebateSchQuery.add(RestrictionsFactoryUtil.eq("rsId", rebateSchedule.getRsId()));
            rebateSchQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RsModel> rebateSchMaster = DAO.getRebateScheduleMasterList(rebateSchQuery);
            
            final DynamicQuery rebateSchNoQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
            rebateSchNoQuery.add(RestrictionsFactoryUtil.eq("rsNo", rebateSchedule.getRsNo()));
            rebateSchNoQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RsModel> rebateSchMasterNo = DAO.getRebateScheduleMasterList(rebateSchNoQuery);
            
            final DynamicQuery rebateSchNameQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
            rebateSchNameQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_NAME, rebateSchedule.getRsName()));
            rebateSchNameQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            final List<RsModel> rebateSchMasterName = DAO.getRebateScheduleMasterList(rebateSchNameQuery);
            
            LOGGER.debug("In  rebateScheduleMasterSize=" + ((rebateSchMaster == null) ? rebateSchMaster : rebateSchMaster.size()));                                             
            LOGGER.debug("In saveRS  rebateSchMasterNo" + ((rebateSchMasterNo == null) ? rebateSchMasterNo : rebateSchMasterNo.size()));                                             
            LOGGER.debug("In saveRS  rebateSchMasterName=" + ((rebateSchMasterNo == null) ? rebateSchMasterName : rebateSchMasterName.size()));                                             
          
             
                  if (rebateSchMaster.isEmpty() && rebateSchMasterNo.isEmpty() && rebateSchMasterName.isEmpty()) {
             
                rebateSchedule = DAO.saveRebateScheduleMaster(rebateSchedule);
               
                sessionDTO.setSystemId(rebateSchedule.getRsModelSid());
                
                      if (rebateSchedule.getRsModelSid() != 0) {

                          DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
                          dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_SID, rebateSchedule.getRsModelSid()));
                          dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_TYPE, ConstantsUtils.RS_MODEL));
                          List<Udcs> list = UdcsLocalServiceUtil.dynamicQuery(dynamicQuery);
                          Udcs udc;
                          if (list.size() > 0) {
                              udc = list.get(0);
                          } else {
                              udc = UdcsLocalServiceUtil.createUdcs(0);
                              udc.setMasterSid(rebateSchedule.getRsModelSid());
                              udc.setMasterType(ConstantsUtils.RS_MODEL);
                          }

                          if (rebateSchForm.getField(ConstantsUtils.UDC1).getValue()!=null && ((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC1).getValue()).getId() != 0) {
                              udc.setUdc1(((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC1).getValue()).getId());
                          }
                          if (rebateSchForm.getField(ConstantsUtils.UDC2).getValue()!=null && ((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC2).getValue()).getId() != 0) {
                              udc.setUdc2(((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC2).getValue()).getId());
                          }
                          if (rebateSchForm.getField(ConstantsUtils.UDC3).getValue()!=null && ((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC3).getValue()).getId() != 0) {
                              udc.setUdc3(((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC3).getValue()).getId());
                          }
                          if (rebateSchForm.getField(ConstantsUtils.UDC4).getValue()!=null && ((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC4).getValue()).getId() != 0) {
                              udc.setUdc4(((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC4).getValue()).getId());
                          }
                          if (rebateSchForm.getField(ConstantsUtils.UDC5).getValue()!=null && ((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC5).getValue()).getId() != 0) {
                              udc.setUdc5(((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC5).getValue()).getId());
                          }
                          if (rebateSchForm.getField(ConstantsUtils.UDC6).getValue()!=null && ((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC6).getValue()).getId() != 0) {
                              udc.setUdc6(((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC6).getValue()).getId());
                          }
                          if (list.size() > 0) {
                              companySearchLogicDao.updateUdcs(udc);
                          } else {
                              companySearchLogicDao.saveUdcs(udc);
                          }

                      }            
                
                LOGGER.debug("In saveRS itemDetailsSize=" + ((itemDetails == null) ? itemDetails : itemDetails.size()));
                saveRebateScheduleDetails(itemDetails, rebateSchedule);
                saveUploadedInformation(availableUploadedInformation,ConstantsUtils.RS_MODEL, rebateSchedule.getRsModelSid(),ConstantsUtils.COPY.equals(sessionDTO.getMode()));
            } else if(!rebateSchMaster.isEmpty()) {
                LOGGER.debug(ConstantsUtils.DUPLICATE);
                return ConstantsUtils.DUPLICATE;
            } else if(!rebateSchMasterNo.isEmpty()) {
                LOGGER.debug(ConstantsUtils.DUPLICATENO);
                return ConstantsUtils.DUPLICATENO;
            } else if(!rebateSchMasterName.isEmpty()) {
                LOGGER.debug(ConstantsUtils.DUPLICATENAME);
                return ConstantsUtils.DUPLICATENAME;
            }

        } else {
            final DynamicQuery rebateSchQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
            rebateSchQuery.add(RestrictionsFactoryUtil.eq("rsId", rebateSchedule.getRsId()));
            rebateSchQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            LOGGER.debug("In saveRS");
            final List<RsModel> rebateSchList = DAO.getRebateScheduleMasterList(rebateSchQuery);
            int count = 0;
            for (int i = 0; i < rebateSchList.size(); i++) {
                if (Integer.valueOf(systemId.replaceAll("\\,", StringUtils.EMPTY)) == rebateSchList.get(i).getRsModelSid()) {
                } else {
                    count++;
                }
            }
            
            final DynamicQuery rebateSchNoQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
            rebateSchNoQuery.add(RestrictionsFactoryUtil.eq("rsNo", rebateSchedule.getRsNo()));
            rebateSchNoQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            LOGGER.debug("In saveRS Entering rebateSchNoQuery");
            final List<RsModel> rebateSchNoList = DAO.getRebateScheduleMasterList(rebateSchNoQuery);
            int countNo = 0;
            for (int i = 0; i < rebateSchNoList.size(); i++) {
                if (Integer.valueOf(systemId.replaceAll("\\,", StringUtils.EMPTY)) == rebateSchNoList.get(i).getRsModelSid()) {
                } else {
                    countNo++;
                }
            }
            
            final DynamicQuery rebateSchNameQuery = DynamicQueryFactoryUtil.forClass(RsModel.class);
            rebateSchNameQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_NAME, rebateSchedule.getRsName()));
            rebateSchNameQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            LOGGER.debug("In saveRS Entering getRebateScheduleMasterList");
            final List<RsModel> rebateSchNameList = DAO.getRebateScheduleMasterList(rebateSchNameQuery);
            int countName = 0;
            for (int i = 0; i < rebateSchNameList.size(); i++) {
                if (Integer.valueOf(systemId.replaceAll("\\,", StringUtils.EMPTY)) == rebateSchNameList.get(i).getRsModelSid()) {
                } else {
                    countName++;
                }
            }
            if (count < (Constants.ONE) && countNo < Constants.ONE && countName < Constants.ONE) {
                rebateSchedule.setModifiedDate(new Date());
                rebateSchedule.setModifiedBy(Integer.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID).toString()));
                LOGGER.debug("In saveRS -RebateScheduleSystemId -" + rebateSchedule.getRsModelSid());                                
                
                final RsModel result = DAO.updateRebateScheduleMaster(rebateSchedule);

                if (rebateSchedule.getRsModelSid() != 0) {

                    DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
                    dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_SID, result.getRsModelSid()));
                    dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_TYPE, ConstantsUtils.RS_MODEL));
                    List<Udcs> list = UdcsLocalServiceUtil.dynamicQuery(dynamicQuery);                    
                    if (list.size() > 0) {
                        Udcs udc = list.get(0);
                        if (rebateSchForm.getField(ConstantsUtils.UDC1).getValue() != null) {

                            if (((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC1).getValue()).getId() != 0) {
                                udc.setUdc1(((HelperDTO) rebateSchForm.getField(ConstantsUtils.UDC1).getValue()).getId());
                            }
                        }else{
                            udc.setUdc1(0);
                        }
                        if (rebateSchForm.getField(ConstantsUtils.UDC2).getValue() != null) {
                        if (((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC2).getValue()).getId() != 0) {
                            udc.setUdc2(((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC2).getValue()).getId());
                        }
                        }else{
                            udc.setUdc2(0);
                        }
                        if (rebateSchForm.getField(ConstantsUtils.UDC3).getValue() != null) {
                        if (((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC3).getValue()).getId() != 0) {
                            udc.setUdc3(((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC3).getValue()).getId());
                        }
                        }else{
                            udc.setUdc3(0);
                        }
                        if (rebateSchForm.getField(ConstantsUtils.UDC4).getValue() != null) {
                        if (((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC4).getValue()).getId() != 0) {
                            udc.setUdc4(((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC4).getValue()).getId());
                        }
                        }else{
                            udc.setUdc4(0);
                        }
                        if (rebateSchForm.getField(ConstantsUtils.UDC5).getValue() != null) {
                        if (((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC5).getValue()).getId() != 0) {
                            udc.setUdc5(((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC5).getValue()).getId());
                        }
                        }else{
                            udc.setUdc5(0);
                        }
                        if (rebateSchForm.getField(ConstantsUtils.UDC6).getValue() != null) {
                        if (((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC6).getValue()).getId() != 0) {
                            udc.setUdc6(((HelperDTO)rebateSchForm.getField(ConstantsUtils.UDC6).getValue()).getId());
                        }
                        }else{
                            udc.setUdc6(0);
                        }
                        companySearchLogicDao.updateUdcs(udc);
                    }
                }                                            
                
                rebateSchedule.setRsModelSid(Integer.valueOf(systemId.replaceAll("\\,", StringUtils.EMPTY)));
                saveRebateScheduleDetails(itemDetails, result);
                saveUploadedInformation(availableUploadedInformation, ConstantsUtils.RS_MODEL, rebateSchedule.getRsModelSid(),ConstantsUtils.COPY.equals(sessionDTO.getMode()));

            } else if(count>0) {
                LOGGER.debug("saveRS  Return duplicate");
                return ConstantsUtils.DUPLICATE;
            } else if(countNo>0) {
                LOGGER.debug("saveRS Return countNo>0");
                return ConstantsUtils.DUPLICATENO;
            } else if(countName>0) {
                LOGGER.debug("saveRS Return duplicate");
                return ConstantsUtils.DUPLICATENAME;
            }
        }
        LOGGER.debug("saveRS Return success");
       }catch(Exception ex){
       LOGGER.error(ex);
       
       }
        return ConstantsUtils.SUCCESS;

    }
        
    /**
     * Method to save item details in RebateScheduleDetails table .
     * 
     * @param itemDetails
     * @param rebateSchedule
     * @return
     * @throws SystemException
     * @throws Exception 
     */
    @Override
    public String saveRebateScheduleDetails(final List<ItemDetailsDTO> itemDetails,
            final RsModel rebateSchedule) throws SystemException {

        LOGGER.debug("Entering saveRebateScheduleDetails P1: " + ((itemDetails == null) ? itemDetails : itemDetails.size()));       
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        final String flag = String.valueOf(sessionDTO.getMode().equalsIgnoreCase("COPY")  ? "Add" : sessionDTO.getMode());
        ImtdRsDetailsLocalServiceUtil.updateToRsDetails(rebateSchedule.getRsModelSid(), userId, sessionId, createdDate, flag, user, user, user, user);
        

        ImtdRsDetailsLocalServiceUtil.deleteTempTableRecords(rebateSchedule.getRsModelSid(),0,userId,sessionId);        
        LOGGER.debug("Ending saveRebateScheduleDetails Return success");
        return ConstantsUtils.SUCCESS;
    }

    /**
     * Method to retrieve the rebateSchedule based on the passed SystemId from
     * rebateScheduleMaster table .
     *
     * @param idValue the id value
     * @return the rebate schedule master by id
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    @Override
    public RebateScheduleMasterDTO getRebateScheduleMasterById(final int idValue) throws SystemException, PortalException {

        LOGGER.debug("In getRebateScheduleMasterById P1:id=" + idValue);
         RebateScheduleMasterDTO rsMasterDTO = new RebateScheduleMasterDTO();
try{
        final RsModel rebateSchMaster = DAO.fetchRebateScheduleMaster(idValue);
       
        if(rebateSchMaster!=null){            
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_SID, idValue));
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.MASTER_TYPE, ConstantsUtils.RS_MODEL));
            List<Udcs> list = UdcsLocalServiceUtil.dynamicQuery(dynamicQuery);
            
            Udcs udcs = UdcsLocalServiceUtil.createUdcs(0);
            if(!list.isEmpty()){
                udcs = list.get(0);
            }
            
            rsMasterDTO = convertRSModelToDTO(rebateSchMaster,udcs);
        }
}catch(Exception ex){
LOGGER.error(ex);
}
        LOGGER.debug("getRebateScheduleMasterById Return rebateScheduleMaster");
        return rsMasterDTO;

    }

    /**
     * Method to delete the rebateSchedule based on the passed SystemId from
     * rebateScheduleMaster table .
     *
     * @param rebateSchSysId the rebate schedule system id
     * @return the rebate schedule master
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     */
    @Override
    public RsModel deleteRebateScheduleById(
            final int rebateSchSysId) throws SystemException, PortalException {

        LOGGER.debug("Entering deleteRebateScheduleById P1: -" + rebateSchSysId);

        final RsModel rebateSchedule = DAO.getRebateScheduleMasterBySystemId(rebateSchSysId);
        rebateSchedule.setInboundStatus(ConstantsUtils.INBOUND_STATUS_D);
        DAO.updateRebateScheduleMaster(rebateSchedule);
        if (rebateSchedule.getRsType() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getRsType()), com.stpl.app.util.GeneralCommonUtils.REBATE_SCHEDULE_TYPE);
        }
        if (rebateSchedule.getRebateProgramType() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getRebateProgramType()), com.stpl.app.util.GeneralCommonUtils.REBATE_PROGRAM_TYPE);
        }
        if (rebateSchedule.getPaymentMethod() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getPaymentMethod()), com.stpl.app.util.GeneralCommonUtils.PAYMENT_METHOD);
        }
        if (rebateSchedule.getRebateFrequency() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getRebateFrequency()), com.stpl.app.util.GeneralCommonUtils.REBATE_FREQUENCY);
        }
        if (rebateSchedule.getPaymentFrequency() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getPaymentFrequency()), com.stpl.app.util.GeneralCommonUtils.PAYMENT_FREQUENCY);
        }
        if (rebateSchedule.getPaymentTerms() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getPaymentTerms()), com.stpl.app.util.GeneralCommonUtils.PAYMENT_TERMS);
        }
        if (rebateSchedule.getRsCalendar() != 0) {
            UDCIncrementCheck.decrement(rebateSchedule.getRsCalendar(), com.stpl.app.util.GeneralCommonUtils.CALENDAR);
        }
        if (rebateSchedule.getRsStatus() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getRsStatus()), com.stpl.app.util.GeneralCommonUtils.RS_STATUS);
        }
        if (rebateSchedule.getRsValidationProfile() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getRsValidationProfile()), com.stpl.app.util.GeneralCommonUtils.REBATE_VALIDATION_PROFILE);
        }
        if (rebateSchedule.getRebatePlanLevel() != null) {
            UDCIncrementCheck.decrement(rebateSchedule.getRebatePlanLevel(), com.stpl.app.util.GeneralCommonUtils.REBATE_PLAN_LEVEL);
        }

        if (rebateSchedule.getRsDesignation() != 0) {
            UDCIncrementCheck.decrement(rebateSchedule.getRsDesignation(), com.stpl.app.util.GeneralCommonUtils.REBATE_SCHEDULE_DESIGNATION);
        }
        if (rebateSchedule.getRsCategory() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getRsCategory()), com.stpl.app.util.GeneralCommonUtils.REBATE_SCHEDULE_CATEGORY);
        }
        if (rebateSchedule.getRsTradeClass() != 0) {
            UDCIncrementCheck.decrement(CommonUtils.getHelperDescription(rebateSchedule.getRsTradeClass()), com.stpl.app.util.GeneralCommonUtils.TRADE_CLASS);
        }

        deleteRebateScheduleDetails(rebateSchSysId);
        LOGGER.debug("deleteRebateScheduleById Return rebateSchedule");
        LOGGER.debug("return RebateSchedule");
        return rebateSchedule;

    }

    /**
     * Method to retrieve and delete the list of rebate Schedule Details based
     * on the SystemId from RebateScheduleDetails table .
     *
     * @param rebateSchSysId the rebate schedule system id
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public void deleteRebateScheduleDetails(final int rebateSchSysId) throws SystemException, PortalException {
        LOGGER.debug("Entering deleteRebateScheduleDetails P1: " + rebateSchSysId);
        
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(RsDetails.class);
        query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_MODEL_SID, rebateSchSysId));
        query.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS,ConstantsUtils.INBOUND_STATUS_D));
        List<RsDetails> details = RsDetailsLocalServiceUtil.dynamicQuery(query);
        
        if(details!=null && !details.isEmpty()){
            for(RsDetails bean:details){
                        ImtdRsDetailsLocalServiceUtil.deleteRsdFr(bean.getRsDetailsSid());
            }
        }
        
        ImtdRsDetailsLocalServiceUtil.deleteAll(null, null, "RSDetails", null, rebateSchSysId, null, null, null);
        LOGGER.debug("Ending deleteRebateScheduleDetails");

    }

    /**
     * Method to add description and details in a list from HelperTable .
     *
     * @param listType the list type
     * @return the helper details
     */
    public List<String> getHelperDetails(final String listType) throws SystemException {

        final List<String> returnList = new ArrayList<>();
        final List<HelperDTO> helperList = new ArrayList<>();

        LOGGER.debug("In getHelperDetails" + listType);
        List<HelperTable> list = DAO.getHelperTableDetailsByListName(listType);
        returnList.add(ConstantsUtils.SELECT_ONE);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
                returnList.add(helperTable.getDescription());
            }
        }
        LOGGER.debug("getHelperDetails Return listSize=" + returnList.size());
        Collections.sort(returnList);
        return returnList;
    }
    
    /**
     * Method to add description and details in a list from HelperTable .
     *
     * @param listType the list type
     * @return the helper details
     */
    public List<HelperDTO> getHelperIdDetails(final String listType) throws SystemException {
        List<HelperTable> list = null;
        final List<HelperDTO> helperList = new ArrayList<>();

        LOGGER.debug("In getHelperDetails P1:listType=" + listType);
        list = DAO.getHelperTableDetailsByListName(listType);
        if (list != null) {
            helperList.add(new HelperDTO(0, Constants.SELECT_ONE));
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
            }
        }
        return helperList;
    }

    /**
     * Method to search from IfpModel table.
     *
     * @param ifpNumber the ifp number
     * @param ifpName the ifp name
     * @param ifpType the ifp type
     * @return the ifp masters
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public List<IFPDetailsDTO> getIfpMasters(final String ifpNumber,
            final String ifpName, final Object ifpType, final Date ifpStartDate, final Date ifpEndStart) throws SystemException {
        LOGGER.debug("Entering getIfpMasters");
        List<IFPDetailsDTO> tempList = new ArrayList<>();

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(IfpModel.class);

        if (ifpNumber != null && !StringUtils.EMPTY.equals(ifpNumber)) {

            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.IFP_NO, ifpNumber.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT)));
        }
        if (ifpName != null && !StringUtils.EMPTY.equals(ifpName)) {

            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.IFP_NAME, ifpName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT)));
        }
        if (ifpType != null && !StringUtils.EMPTY.equals(ifpType.toString())) {
            String ifpTypeString;
            ifpTypeString = ifpType.toString().replace(
                    CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.IFP_TYPE, ifpTypeString));
        }
        if (ifpStartDate != null && !StringUtils.EMPTY.equals(ifpStartDate.toString())) {
            ifpStartDate.setHours(00);
            ifpStartDate.setMinutes(00);
            ifpStartDate.setSeconds(00);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.IFP_START_DATE, ifpStartDate));
        }
        if (ifpEndStart != null && !StringUtils.EMPTY.equals(ifpEndStart.toString())) {
            ifpEndStart.setHours(00);
            ifpEndStart.setMinutes(00);
            ifpEndStart.setSeconds(00);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.IFP_END_DATE, ifpEndStart));
        }

        final List<IfpModel> ifpList = DAO.getItemFamilyplanMasterList(ifpDynamicQuery);

        LOGGER.debug("return getIfpMasters ifpListSize=" + ((ifpList == null) ? ifpList : ifpList.size()));
        for (IfpModel object : ifpList) {
            IFPDetailsDTO tempDTO = new IFPDetailsDTO();
            tempDTO.setIfpNo(object.getIfpNo());
            tempDTO.setIfpName(object.getIfpName());
            tempDTO.setIfpType(CommonUtils.getDescription(object.getIfpType()));
            tempDTO.setIfpStartDate(object.getIfpStartDate());
            tempDTO.setIfpEndDate(object.getIfpEndDate());
            tempDTO.setItemFamilyplanSystemId(object.getIfpModelSid());
            tempList.add(tempDTO);
        }
        return tempList;
        

    }

    /**
     * Method to add the item master details that has been attached to the IFP.
     *
     * @param selectedIfpId the selected ifp id
     * @return the list
     */

    /**
     * Method to fetch and append the item family plan from
     * RebateScheduleDetails table.
     *
     * @param systemId the system id
     * @return the item family plan from rsid
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public List<IFPDetailsDTO> getItemFamilyPlanFromRSID(final int systemId) throws SystemException {

        LOGGER.debug("In getItemFamilyPlanFromRSID P1:systemId=" + systemId);
        final List<IfpModel> returnList = new ArrayList<>();
        List<IFPDetailsDTO> tempList = new ArrayList<>();
        final DynamicQuery rebateSchQuery = DynamicQueryFactoryUtil
                .forClass(RsDetails.class);
        rebateSchQuery.add(RestrictionsFactoryUtil.eq(
                ConstantsUtils.RS_MODEL_SID, systemId));
        rebateSchQuery.setLimit(0, 1);
        rebateSchQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        final List<RsDetails> rebateDetails = DAO.getRebateScheduleDetailsList(rebateSchQuery);
        if (!rebateDetails.isEmpty()) {
            // this result shd be one ifp 
            // this result contains more than one ifp means it will make some pbm
            returnList.add(DAO.fetchItemFamilyplanMaster(rebateDetails.get(0)
                    .getIfpModelSid()));
        }
        LOGGER.debug("getItemFamilyPlanFromRSID return returnList=" + returnList.size());
        for (IfpModel object : returnList) {
            IFPDetailsDTO tempDTO = new IFPDetailsDTO();
            tempDTO.setIfpId(object.getIfpId());
            tempDTO.setIfpNo(object.getIfpNo());
            tempDTO.setIfpName(object.getIfpName());
            tempDTO.setIfpType(CommonUtils.getDescription(object.getIfpType()));
            tempDTO.setIfpStatus(CommonUtils.getDescription(object.getIfpStatus()));
            tempDTO.setIfpCategory(CommonUtils.getDescription(object.getIfpCategory()));
            tempDTO.setIfpStartDate(object.getIfpStartDate());
            tempDTO.setIfpEndDate(object.getIfpEndDate());

            tempDTO.setItemFamilyplanSystemId(object.getIfpModelSid());
            tempList.add(tempDTO);
        }
        return tempList;

    }
    
    /**
     * Method to collect the item details based on system ID.
     *
     * @param systemId the system id
     * @return the item details
     */
    public List<ItemDetailsDTO> getItemDetails(final int systemId,String record) throws SystemException, PortalException {

        LOGGER.debug("In getItemDetails P1:systemId=" + systemId);
        
        Map<String,Object> parameters = new HashMap<>();
        
        if(!StringUtils.isBlank(record)){
            if(record.contains(ConstantsUtils.CURRENT)){
                    parameters.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.HISTORY)){
                    parameters.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.FUTURE)){
                    parameters.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
        }
        
        final List collectedList = DAO.getRebateScheduleDetails(systemId,parameters,null);

        final List<ItemDetailsDTO> itemDetails = getcustomizedListfromRebateDetails(collectedList);

        LOGGER.debug("Ends getItemDetails return  itemDetails=" + itemDetails.size());
        return itemDetails;

    }

    /**
     * Method to get the customized list from rebate details.
     *
     * @param collectedList the collected list
     * @return the customized listfrom rebate details
     */
    private List<ItemDetailsDTO> getcustomizedListfromRebateDetails(
            @SuppressWarnings(ConstantsUtils.RAWTYPES) final List collectedList) throws SystemException, PortalException {
        LOGGER.debug("In getcustomizedListfromRebateDetails P1:collectedList=" + collectedList.size());
        final List<ItemDetailsDTO> itemDetails = new ArrayList<>();
        if (collectedList != null && collectedList.size() != 0) {

            for (int i = 0; i < collectedList.size(); i++) {
                final Object[] obj = (Object[]) collectedList.get(i);
                final ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
                itemDetailsDTO.setIfpSystemId(obj[0].toString());
                itemDetailsDTO.setItemNo(obj[1].toString());
                itemDetailsDTO.setItemName(obj[NumericConstants.TWO].toString());
                itemDetailsDTO.setItemType(obj[NumericConstants.THREE] == null ? StringUtils.EMPTY : obj[NumericConstants.THREE].toString());
                itemDetailsDTO.setItemSystemId(obj[NumericConstants.FOUR].toString());      
                itemDetailsDTO.setRebatePlanSystemId(String.valueOf((obj[NumericConstants.FOUR]!=null)?(Integer) obj[NumericConstants.FOUR]:0));
                itemDetailsDTO.setRebateAmount(obj[NumericConstants.SIX].toString());
                itemDetailsDTO.setStartDate((Date) obj[NumericConstants.SEVEN]);
                if (obj[NumericConstants.EIGHT] != null && !obj[NumericConstants.EIGHT].equals(StringUtils.EMPTY)) {
                    itemDetailsDTO.setEndDate((Date) obj[NumericConstants.EIGHT]);
                }
                itemDetailsDTO.setCheckbox(true);
                int attachedStatus = (obj[NumericConstants.NINE]!=null)?(Integer) obj[NumericConstants.NINE]:0;
                itemDetailsDTO.setAttachedStatus(helperListUtil.getIdHelperDTOMap().get(attachedStatus));
                itemDetailsDTO.setAttachedDate((Date) obj[NumericConstants.TEN]);
                itemDetailsDTO.setRevisionDate((Date) obj[NumericConstants.ELEVEN]);
                itemDetailsDTO.setBundleNo(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : StringUtils.EMPTY);
                itemDetailsDTO.setRebatePlanNo(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : StringUtils.EMPTY);
                itemDetailsDTO.setRebatePlanName(obj[NumericConstants.FIFTEEN] != null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : StringUtils.EMPTY);
                itemDetailsDTO.setDeductionSystemId(obj[NumericConstants.SIXTEEN] != null ? String.valueOf(obj[NumericConstants.SIXTEEN]) : StringUtils.EMPTY);
                itemDetailsDTO.setDeductionCalendarNo(obj[NumericConstants.SEVENTEEN] != null ? String.valueOf(obj[NumericConstants.SEVENTEEN]) : StringUtils.EMPTY);
                itemDetailsDTO.setDeductionCalendarName(obj[NumericConstants.EIGHTEEN] != null ? String.valueOf(obj[NumericConstants.EIGHTEEN]) : StringUtils.EMPTY);
                itemDetailsDTO.setSystemID(obj[NumericConstants.NINETEEN] != null ? String.valueOf(obj[NumericConstants.NINETEEN]) : StringUtils.EMPTY);
                itemDetailsDTO.setNetSalesFormulaNo(obj[NumericConstants.TWENTY] != null ? String.valueOf(obj[NumericConstants.TWENTY]) : StringUtils.EMPTY);
                int netSalesrule = (obj[NumericConstants.TWENTY_ONE]!=null)?(Integer) obj[NumericConstants.TWENTY_ONE]:0;
                itemDetailsDTO.setNetSalesRule(getRuleNameById(netSalesrule));
                int evaluationRule = (obj[NumericConstants.TWENTY_TWO]!=null)?(Integer) obj[NumericConstants.TWENTY_TWO]:0;
                itemDetailsDTO.setEvaluationRule(getRuleNameById(evaluationRule));
                int calculationRule = (obj[NumericConstants.TWENTY_THREE]!=null)?(Integer) obj[NumericConstants.TWENTY_THREE]:0;
                itemDetailsDTO.setCalculationRule(getRuleNameById(calculationRule));
                itemDetailsDTO.setEvaluationRuleBundle(obj[NumericConstants.TWENTY_FOUR] != null ? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : StringUtils.EMPTY);
                itemDetailsDTO.setCalculationRuleBundle(obj[NumericConstants.TWENTY_FIVE] != null ? String.valueOf(obj[NumericConstants.TWENTY_FIVE]) : StringUtils.EMPTY);
                itemDetailsDTO.setFormulaId(obj[NumericConstants.TWENTY_SIX]==null?StringUtils.EMPTY:String.valueOf(obj[NumericConstants.TWENTY_SIX]));
                itemDetailsDTO.setFormulaNo(obj[NumericConstants.TWENTY_SEVEN]==null?StringUtils.EMPTY:String.valueOf(obj[NumericConstants.TWENTY_SEVEN]));
                itemDetailsDTO.setFormulaName(obj[NumericConstants.TWENTY_EIGHT]==null?StringUtils.EMPTY:String.valueOf(obj[NumericConstants.TWENTY_EIGHT]));
                itemDetails.add(itemDetailsDTO);
            }

        }
        LOGGER.debug("Ends getcustomizedListfromRebateDetails return  itemDetails=" + itemDetails.size());
        return itemDetails;

    }

    /**
     * Method to obtain the rebate plan names within the particular limit .
     *
     * @return the rebate plan names
     */
    public List<RebatePlanMaster> getRebatePlanNames() throws SystemException {
        LOGGER.debug("In getRebatePlanNames");

        final int end = DAO.getRebatePlanMasterTotalCount();
        final List<RebatePlanMaster> results = DAO.getRebatePlanMasterByLimit(0, end);
        LOGGER.debug("getRebatePlanNames return resultsList" + results.size());
        return results;

    }
     
    /**
     * Method to get the count of the rebate schedule search.
     *
     * @param rebateSchForm
     * @param start
     * @param offset
     * @param columns
     * @param fieldFlag
     * @param criteria
     * @param isCount
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public Object getRSResults(final ErrorfulFieldGroup rebateSchForm, int start, int offset, final List<OrderByColumn> columns, final boolean fieldFlag, final BeanSearchCriteria criteria, boolean isCount)
            throws SystemException {
        LOGGER.debug("In getSearchCount");
        Map<String, Object> filterCriteria = new HashMap<>();
        Map<String, String> searchCriteria = new HashMap<>();

        searchCriteria.put(ConstantsUtils.REBATE_ID, checkEmptyDataFromFields(ConstantsUtils.TEXT1, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT1).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.REBATE_NO, checkEmptyDataFromFields(ConstantsUtils.TEXT2, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT2).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.REBATE_NAME, checkEmptyDataFromFields(ConstantsUtils.TEXT3, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT3).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.REBATE_TYPE, checkEmptyDataFromFields(ConstantsUtils.COMBO1, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO1).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.REBATE_STATUS, checkEmptyDataFromFields(ConstantsUtils.COMBO2, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO2).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.REBATE_PRO_TYPE, checkEmptyDataFromFields(ConstantsUtils.COMBO3, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO3).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.REBATE_CATEGORY, checkEmptyDataFromFields(ConstantsUtils.COMBO4, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO4).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.REBATE_FREQ, checkEmptyDataFromFields(ConstantsUtils.COMBO6, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO6).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.CALCULATION_TYPE, checkEmptyDataFromFields(ConstantsUtils.COMBO7, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO7).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.RS_ALAIS_ID, checkEmptyDataFromFields(ConstantsUtils.TEXT7, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT7).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.ITEM_NO, checkEmptyDataFromFields(ConstantsUtils.TEXT8, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT8).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.ITEM_NAME, checkEmptyDataFromFields(ConstantsUtils.TEXT_9, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT_9).getValue().toString()).trim());

        if (criteria != null && criteria.getFilters() != null) {
            for (Container.Filter filter : criteria.getFilters()) {
                
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                    if (ConstantsUtils.REBATE_SCHEDULE_STATUS.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_SCHEDULE_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_PROGRAM_TYPE.equals(stringFilter.getPropertyId()) || ConstantsUtils.RS_PROGRAM_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.RS_CATEGORY.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.RS_DESIGNATION.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.RS_TRADE_CLASS.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_FREQ.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_PROGRAM_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.CALENDER.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.CALCULATION_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_RULE_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.PAYMENT_LEVEL.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.PAYMENT_TERMS.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.PAYMENT_METHOD.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.PAYMENT_FREQUENCY.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.INTEREST_BEARING_INDICATOR.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.INTEREST_BEARING_BASIS.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC1.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC2.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC3.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC4.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC5.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC6.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), filterString);
                    }
                }
            }
        }
        String column = ConstantsUtils.RS_ID_COLUMN;
        String orderBy = "ASC";
        if (columns != null) {
            for (final Iterator<OrderByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
                String columnName = orderByColumn.getName();
                
                if (ConstantsUtils.REBATE_SCHEDULE_NO.equals(columnName)) {
                    column = ConstantsUtils.RS_NO_COLUMN;
                } else if (ConstantsUtils.REBATE_SCHEDULE_ID.equals(columnName)) {
                    column =ConstantsUtils.RS_ID_COLUMN;
                } else if (ConstantsUtils.REBATE_SCHEDULE_NAME.equals(columnName)) {
                    column = ConstantsUtils.RS_NAME_LIST;
                } else if (ConstantsUtils.REBATE_SCHEDULE_STATUS.equals(columnName)) {
                    column = ConstantsUtils.RS_STATUS1;
                } else if (ConstantsUtils.REBATE_SCHEDULE_TYPE.equals(columnName)) {
                    column = ConstantsUtils.R_TYPE;
                } else if (ConstantsUtils.REBATE_PROGRAM_TYPE.equals(columnName) || ConstantsUtils.RS_PROGRAM_TYPE.equals(columnName)) {
                    column = ConstantsUtils.RP_TYPE;
                } else if (columnName != null && columnName.contains("udc")) {
                    column = columnName.toUpperCase();
                } else if (ConstantsUtils.RS_CATEGORY.equals(columnName)) {
                    column = ConstantsUtils.RS_CATEGORY1;
                } else if (ConstantsUtils.RS_DESIGNATION.equals(columnName)) {
                    column = ConstantsUtils.RS_DESIGNATION1;
                } else if (ConstantsUtils.RS_TRADE_CLASS.equals(columnName)) {
                    column = "RS_TRADE_CLASS";
                } else if (ConstantsUtils.REBATE_FREQ.equals(columnName)) {
                    column = "REBATE_FREQUENCY";
                } else if (ConstantsUtils.CALENDER.equals(columnName)) {
                    column = "CALENDAR";
                } else if (ConstantsUtils.CALCULATION_TYPE.equals(columnName)) {
                    column = "CALCULATION_TYPE";
                } else if (ConstantsUtils.REBATE_RULE_TYPE.equals(columnName)) {
                    column = "REBATE_RULE_TYPE";
                } else if (ConstantsUtils.PAYMENT_LEVEL.equals(columnName)) {
                } else if (ConstantsUtils.PAYMENT_TERMS.equals(columnName)) {
                    column = "PAYMENT_TERMS";
                } else if (ConstantsUtils.PAYMENT_METHOD.equals(columnName)) {
                    column = "PAYMENT_METHOD";
                } else if (ConstantsUtils.PAYMENT_FREQUENCY.equals(columnName)) {
                    column = "PAYMENT_FREQUENCY";
                } else if (ConstantsUtils.INTEREST_BEARING_INDICATOR.equals(columnName)) {
                    column = "INTEREST_BEARING_INDICATOR";
                } else if (ConstantsUtils.INTEREST_BEARING_BASIS.equals(columnName)) {
                    column = "INTEREST_BEARING_BASIS";
                } else if (ConstantsUtils.RS_PARENT_ID.equals(columnName)) {
                    column =ConstantsUtils.PARENT_RS_SID;
                } else if (ConstantsUtils.RS_PARENT_NAME.equals(columnName)) {
                    column = ConstantsUtils.PARENT_RS_NAME;
                } else if (ConstantsUtils.RS_REFERENCE_ID.equals(columnName)) {
                    column = "RS_TRANS_REF_ID";
                } else if (ConstantsUtils.RS_REFERENCE_NAME.equals(columnName)) {
                    column = "RS_TRANS_REF_NAME";
                } 

                if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                    orderBy = "ASC";
                } else {
                    orderBy = "DESC";
                }
            }
        }
        String query = getRSQuery(searchCriteria, start, offset, column, orderBy, filterCriteria, isCount);
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);

        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return resultList;
        }

    }

    /**
     * Method to get the total count 
     *
     * @return the total count
     */
    public int getTotalCount() throws SystemException {
        LOGGER.debug("getTotalCount method");

        LOGGER.debug("Ends getTotalCount method with count  --- ");
        return DAO.getRebateScheduleMasterTotalCount();

    }

    /**
     * Method to get the rebate schedule id list from RebateScheduleDetails
     * table.
     *
     * @return the formula id list
     */
    public List<HelperDTO> getFormulaIdList() throws SystemException {
        List<RsDetails> list = null;
        final List<HelperDTO> helperList = new ArrayList<>();
        HelperDTO helperTable;

        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);

        list = DAO.getRebateScheduleDetailsList(cfpDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                helperTable = new HelperDTO(String.valueOf(list.get(i).getFormulaId()));
                helperList.add(helperTable);
            }
        }

        return helperList;
    }

    /**
     * Method to get the tier formula name having the passed formulaId from
     * RebateTierFormula table .
     *
     * @param formulaId the formula id
     * @return the formula name
     */
    public String getFormulaName(final String formulaId) throws SystemException {

        final DynamicQuery rebateTierFormula = DynamicQueryFactoryUtil.forClass(RebateTierFormula.class);
        rebateTierFormula.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.REBATE_TIER_FORMULA_ID, formulaId));

        final List<RebateTierFormula> list = DAO.getTierFormulaList(rebateTierFormula);

        return list.get(0).getRebateTierFormulaName();

    }

    public static int getLazyTierFormulaIdCount(String filter, final HelperDTO helper) throws SystemException {

        filter = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyTierFormulaIdCount method with filterText :" + filter);
        List<Object[]> list;
        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);
        if(!ConstantsUtils.PERCENCTAGE.equals(filter)){
            rsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORMULA_ID, filter));
        }
        rsDynamicQuery.setProjection(ProjectionFactoryUtil.countDistinct(ConstantsUtils.FORMULA_ID));
        rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.FORMULA_ID, ConstantsUtils.ZERO_INT));
        rsDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.FORMULA_ID));
        if (helper != null && helper.getId() != 0) {
            rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.FORMULA_ID, helper.getId()));
        }
        list = DAO.getFormulaIdList(rsDynamicQuery);
        formulaIdCount = Integer.parseInt(String.valueOf(list.get(0)));
        LOGGER.debug("Ending getLazyTierFormulaIdCount method : returning count :" + formulaIdCount);
        return Integer.parseInt(String.valueOf(list.get(0)));
    }

    public static List<HelperDTO> getLazyTierFormulaIdResults(final int startIndex, final int end, String filter, final HelperDTO helper) throws SystemException {
        filter = StringUtils.trimToEmpty(filter) + ConstantsUtils.PERCENCTAGE;
        LOGGER.debug("Entering getLazyTierFormulaIdResults method with filterText" + filter);
        List<String> qualifierList;
        final List<HelperDTO> list = new ArrayList<>();

        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);
        rsDynamicQuery.setLimit(startIndex, end);
        rsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(ConstantsUtils.FORMULA_ID)));
        rsDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.FORMULA_ID));
        if(!ConstantsUtils.PERCENCTAGE.equals(filter)){
            rsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORMULA_ID, filter));
        }
        if (helper != null && helper.getId() != 0) {
            rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.FORMULA_ID, helper.getId()));
        }
        rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.FORMULA_ID, ConstantsUtils.ZERO_INT));
        rsDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.FORMULA_ID));

        qualifierList = DAO.getFormulaIdList(rsDynamicQuery);

        HelperDTO dto;
        if (startIndex == Constants.ZERO) {
            dto = new HelperDTO();
            dto.setDescription(ConstantsUtils.SELECT_ONE);
            list.add(dto);
        }
        for (final Iterator<String> iterator = qualifierList.iterator(); iterator.hasNext();) {
            final String value = iterator.next();
            dto = new HelperDTO(value == null ? StringUtils.EMPTY : value);
            if (!StringUtils.EMPTY.equals(dto.getDescription())) {
                list.add(dto);
            }
        }
        LOGGER.debug("Ending getLazyTierFormulaIdResults method : returning CompanyQualifier size :" + list.size());
        return list;
    }

    /**
     * Method to search from IfpModel table.
     *
     * @param start
     * @param end
     * @param ifpNumber the ifp number
     * @param ifpName the ifp name
     * @param ifpType the ifp type
     * @param ifpStartDate
     * @param ifpEndStart
     * @param itemName
     * @param itemNo
     * @return the ifp masters
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public List<IFPDetailsDTO> getIfpMastersResult(final int start, final int end, String ifpNumber,
            String ifpName, final Object ifpType, final Date ifpStartDate, final Date ifpEndStart, String itemName,
            String itemNo,final List<OrderByColumn> columns,final BeanSearchCriteria criteria,String count,String ifpCategory,String ifpStatus) {
        LOGGER.debug("Entering getIfpMastersResult");
       
        Map<String, Object> parameters = new HashMap<>();
        List<IFPDetailsDTO> searchList=new ArrayList<>();
        int flag=0;     
         try{
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(IfpModel.class);
        if (ifpNumber != null && !StringUtils.EMPTY.equals(ifpNumber)) {
            ifpNumber=CommonUtil.buildSearchCriteria(ifpNumber);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.IFP_MODEL_NO, ifpNumber));
        }
        if (ifpName != null && !StringUtils.EMPTY.equals(ifpName)) {
            ifpName=CommonUtil.buildSearchCriteria(ifpName);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.IFP_MODEL_NAME, ifpName));
        }
        if (ifpType != null && !StringUtils.EMPTY.equals(ifpType.toString())) {


        }
        if (ifpStartDate != null && !StringUtils.EMPTY.equals(ifpStartDate.toString())) {
            ifpStartDate.setHours(00);
            ifpStartDate.setMinutes(00);
            ifpStartDate.setSeconds(00);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.IFP_MODEL_START_DATE, ifpStartDate));
        }
        if (ifpEndStart != null && !StringUtils.EMPTY.equals(ifpEndStart.toString())) {
            ifpEndStart.setHours(00);
            ifpEndStart.setMinutes(00);
            ifpEndStart.setSeconds(00);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.IFP_MODEL_END_DATE, ifpEndStart));
        }
        if (ifpType != null && !StringUtils.EMPTY.equals(ifpType.toString())) {
            List<HelperDTO> list = helperListUtil.getListNameMap().get("IFP_TYPE");
            if (list!= null && !list.isEmpty()) {
                for (HelperDTO list1 : list) {
                    if (String.valueOf(list1.getId()).equals(ifpType.toString())) {
                        int ik = list1.getId();
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.IFP_MODEL_TYPE, ik));
                    }
                }
            }
        }
             if (itemNo != null && !StringUtils.EMPTY.equals(itemNo)) {
                 itemNo = CommonUtil.buildSearchCriteria(itemNo);
             }
             if (itemName != null && !StringUtils.EMPTY.equals(itemName)) {
                 itemName = CommonUtil.buildSearchCriteria(itemName);
             }

        if(ifpCategory != null || StringUtils.isBlank(ifpCategory)){
            parameters.put("ifpCategorySearch", String.valueOf(ifpCategory));
        }
        
        if(ifpStatus != null || StringUtils.isNotBlank(ifpStatus) || ConstantsUtils.ZERO.equals(ifpStatus)){
            parameters.put("ifpStatusSearch", String.valueOf(ifpStatus));
        }
        
        if (StringUtils.isNotEmpty(itemNo) || StringUtils.isNotEmpty(itemName)) {
            flag=1;
        }

        
        String column = ConstantsUtils.IFP_NO_COLUMN;
        String orderBy = "ASC";
       if(columns!=null){ 
        for (final Iterator<OrderByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            
            if(orderByColumn.getName()=="ifpNo"){
                column = ConstantsUtils.IFP_NO_COLUMN;
               }else if(orderByColumn.getName()=="ifpId"){
                column = "IFP_ID";
                }else if(orderByColumn.getName()==ConstantsUtils.IFP_MODEL_STATUS){
                column = "istatus";
                }else if(orderByColumn.getName()==ConstantsUtils.IFP_CATEGORY1){
                column = "icategory";
                }else if(orderByColumn.getName()=="ifpType"){
                    column = "itype";
                }else if(orderByColumn.getName()=="ifpStartDate"){
                    column = "IFP_START_DATE";
                }else if(orderByColumn.getName()=="ifpEndDate"){
                    column = "IFP_END_DATE";
                }
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
               orderBy="ASC";
            } else {
               orderBy="DESC";
            }
        }
       }
        if (criteria != null && criteria.getFilters() != null) {
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                    if (ConstantsUtils.IFP_MODEL_TYPE.equals(stringFilter.getPropertyId())) {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.IFP_MODEL_TYPE, Integer.valueOf(stringFilter.getFilterString())));
                        parameters.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.IFP_MODEL_STATUS.equals(stringFilter.getPropertyId())) {
                        parameters.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    }else if (ConstantsUtils.IFP_CATEGORY1.equals(stringFilter.getPropertyId())) {
                        parameters.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(stringFilter.getPropertyId().toString(), filterString));
                        parameters.put(stringFilter.getPropertyId().toString(), filterString);
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM_FILTER, String.valueOf(startValue));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO_FILTER, String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(compare.getPropertyId() + ConstantsUtils.FROM_FILTER, String.valueOf(value));
                    } else {
                        parameters.put(compare.getPropertyId() + ConstantsUtils.TO_FILTER, String.valueOf(value));
                    }
                }

            }
        }
        
       if (flag == Constants.ZERO || flag == Constants.ONE) {

            List resultList=RsModelLocalServiceUtil.getIfpList(ifpNumber, ifpName, ifpType, ifpStartDate!=null?ifpStartDate.toString():StringUtils.EMPTY, ifpEndStart!=null?ifpEndStart.toString():StringUtils.EMPTY, itemNo, itemName, start, end, column, orderBy,parameters);
            searchList = getCustomizedSearchForHelperTable(resultList);
        }}catch(Exception e){
                LOGGER.error(e);
                }

        LOGGER.debug("return getIfpMasters ifpListSize=" + ((searchList == null) ? searchList : searchList.size()));
        return searchList;

    }
    
    /**
     * Method to search from IfpModel table.
     *
     * @param start
     * @param end
     * @param ifpNumber the ifp number
     * @param ifpName the ifp name
     * @param ifpType the ifp type
     * @param ifpStartDate
     * @param ifpEndStart
     * @param itemName
     * @param itemNo
     * @return the ifp masters
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    @SuppressWarnings(ConstantsUtils.UNCHECK)
    public int getIfpMastersCount(final int start, final int end, String ifpNumber,
            String ifpName, final Object ifpType, final Date ifpStartDate, final Date ifpEndStart, String itemName,
            String itemNo,final List<OrderByColumn> columns,final BeanSearchCriteria criteria,String count,String ifpCategory,String ifpStatus) {
        LOGGER.debug("Entering getIfpMasters");
      
        Map<String, Object> parameters = new HashMap<>();
         try{

            if (ifpNumber != null && !StringUtils.EMPTY.equals(ifpNumber)) {
                ifpNumber = CommonUtil.buildSearchCriteria(ifpNumber);
            }
            if (ifpName != null && !StringUtils.EMPTY.equals(ifpName)) {
                ifpName = CommonUtil.buildSearchCriteria(ifpName);
            }
        if (ifpType != null && !StringUtils.EMPTY.equals(ifpType.toString())) {


        }
        if (ifpStartDate != null && !StringUtils.EMPTY.equals(ifpStartDate.toString())) {
            ifpStartDate.setHours(00);
            ifpStartDate.setMinutes(00);
            ifpStartDate.setSeconds(00);
            
        }
        if (ifpEndStart != null && !StringUtils.EMPTY.equals(ifpEndStart.toString())) {
            ifpEndStart.setHours(00);
            ifpEndStart.setMinutes(00);
            ifpEndStart.setSeconds(00);
           
        }
        if (ifpType != null && !StringUtils.EMPTY.equals(ifpType.toString())) {
            List<HelperDTO> list = helperListUtil.getListNameMap().get("IFP_TYPE");     
            if (list!=null && !list.isEmpty()) {
                for (HelperDTO list1 : list) {
                if (String.valueOf(list1.getId()).equals(ifpType.toString())) {
                  
                }
            }
            }
            
        }
        if (itemNo != null && !StringUtils.EMPTY.equals(itemNo)) {
            itemNo = CommonUtil.buildSearchCriteria(itemNo);
        }
        if (itemName != null && !StringUtils.EMPTY.equals(itemName)) {
            itemName = CommonUtil.buildSearchCriteria(itemName);
        }
        if (StringUtils.isNotEmpty(itemNo) || StringUtils.isNotEmpty(itemName)) {
        }
        if(ifpCategory != null || StringUtils.isNotBlank(ifpCategory) || ConstantsUtils.ZERO.equals(ifpCategory)){
            parameters.put("ifpCategorySearch", String.valueOf(ifpCategory));
        }
        if(ifpStatus != null || StringUtils.isNotBlank(ifpStatus) || ConstantsUtils.ZERO.equals(ifpStatus)){
            parameters.put("ifpStatusSearch", String.valueOf(ifpStatus));
        }

        
        String column = ConstantsUtils.IFP_NO_COLUMN;
        String orderBy = "ASC";
       if(columns!=null){ 
        for (final Iterator<OrderByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            
            if(orderByColumn.getName()=="ifpNo"){
                column = ConstantsUtils.IFP_NO_COLUMN;
               }else if(orderByColumn.getName()=="ifpId"){
                column = "IFP_ID";
                }else if(orderByColumn.getName()==ConstantsUtils.IFP_MODEL_STATUS){
                column = "istatus";
                }else if(orderByColumn.getName()==ConstantsUtils.IFP_CATEGORY1){
                column = "icategory";
                }else if(orderByColumn.getName()=="ifpName"){
                column = "IFP_NAME";
                }else if(orderByColumn.getName()=="ifpType"){
                    column = "itype";
                }else if(orderByColumn.getName()=="ifpStartDate"){
                    column = "IFP_START_DATE";
                }else if(orderByColumn.getName()=="ifpEndDate"){
                    column = "IFP_END_DATE";
                }
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
               orderBy="ASC";
            } else {
               orderBy="DESC";
            }
        }
       }
        if (criteria != null && criteria.getFilters() != null) {
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                    if (ConstantsUtils.IFP_MODEL_TYPE.equals(stringFilter.getPropertyId())) {
                        
                        parameters.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.IFP_MODEL_STATUS.equals(stringFilter.getPropertyId())) {
                        parameters.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    }else if (ConstantsUtils.IFP_CATEGORY1.equals(stringFilter.getPropertyId())) {
                        parameters.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    }else {
                       
                        parameters.put(stringFilter.getPropertyId().toString(), filterString);
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM_FILTER, String.valueOf(startValue));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO_FILTER, String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(compare.getPropertyId() + ConstantsUtils.FROM_FILTER, String.valueOf(value));
                    } else {
                        parameters.put(compare.getPropertyId() + ConstantsUtils.TO_FILTER, String.valueOf(value));
                    }
                }

            }
        }
        
       

            List resultList=RsModelLocalServiceUtil.getIfpList(ifpNumber, ifpName, ifpType, ifpStartDate!=null?ifpStartDate.toString():StringUtils.EMPTY, ifpEndStart!=null?ifpEndStart.toString():StringUtils.EMPTY, itemNo, itemName, start, end, column, orderBy,parameters);
            
                if(resultList!=null || !resultList.isEmpty()){
                   return resultList.size();
               }else{
                    return 0;
                }
         
         
         }catch(Exception e){
                LOGGER.error(e);
                }
        return 0;

 
    }
    

    public static List<IFPDetailsDTO> getCustomizedSearchForHelperTable(final List list) {
        LOGGER.debug("Entering getCustomizedSearchFormFromModel()");
        final List<IFPDetailsDTO> searchItemList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final IFPDetailsDTO searchCompanyForm = new IFPDetailsDTO();

               final Object[] obj = (Object[]) list.get(i);
                if(obj[0]!=null){
                searchCompanyForm.setIfpNo((String)obj[0]);
                }
                if(obj[1]!=null){
                searchCompanyForm.setIfpName(String.valueOf(obj[1]));
                }
                if(obj[NumericConstants.SIX]!=null && !String.valueOf(obj[NumericConstants.SIX]).equals(ConstantsUtils.SELECT_ONE)) {
                searchCompanyForm.setIfpType(String.valueOf(obj[NumericConstants.SIX]));
                }
                if(obj[NumericConstants.THREE]!=null){
                searchCompanyForm.setIfpStartDate((Date)obj[NumericConstants.THREE]);
                }
                if(obj[NumericConstants.FOUR]!=null){
                searchCompanyForm.setIfpEndDate((Date)obj[NumericConstants.FOUR]);
                }               
                searchCompanyForm.setItemFamilyplanSystemId((Integer)obj[NumericConstants.FIVE]);            
               
                if(obj[NumericConstants.SEVEN]!=null){
                    
                  searchCompanyForm.setIfpStatus(String.valueOf(obj[NumericConstants.SEVEN]));
                }
                
                if(obj[NumericConstants.EIGHT]!=null){
                    searchCompanyForm.setIfpId(String.valueOf(obj[NumericConstants.EIGHT]));
                }
                
                if(obj[NumericConstants.NINE]!=null && !String.valueOf(obj[NumericConstants.NINE]).equals(ConstantsUtils.SELECT_ONE)){
                     searchCompanyForm.setIfpCategory(String.valueOf(obj[NumericConstants.NINE]));
                }
                searchItemList.add(searchCompanyForm);

            }
        }
        LOGGER.debug("End of getCustomizedSearchFormFromModel method");
        return searchItemList;
    }
       
    public static List<IFPDetailsDTO> ifpDTOConfig(List<IfpModel> reault) {
        List<IFPDetailsDTO> tempList = new ArrayList<>();
        for (IfpModel object : reault) {
            IFPDetailsDTO tempDTO = new IFPDetailsDTO();
            tempDTO.setIfpNo(object.getIfpNo().trim());
            tempDTO.setIfpName(object.getIfpName().trim());
            tempDTO.setIfpType(CommonUtils.getDescription(object.getIfpType()));
            tempDTO.setIfpStartDate(object.getIfpStartDate());
            tempDTO.setIfpEndDate(object.getIfpEndDate());

            tempDTO.setItemFamilyplanSystemId(object.getIfpModelSid());
            tempList.add(tempDTO);
        }
        return tempList;
    }
    
    public static List<IFPDetailsDTO> getCustomizedIFP(List list) {
        List<IFPDetailsDTO> tempList = new ArrayList<>();
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                int j = 0;
                final Object[] obj = (Object[]) list.get(i);
                IFPDetailsDTO tempDTO = new IFPDetailsDTO();
                tempDTO.setIfpNo(obj[j++].toString().trim());
                tempDTO.setIfpName(obj[j++].toString().trim());
                tempDTO.setIfpType(CommonUtils.getDescription(Integer.valueOf(obj[j++].toString())));
                tempDTO.setIfpStartDate((Date) obj[j++]);
                tempDTO.setIfpEndDate((Date) obj[j++]);

                tempDTO.setItemFamilyplanSystemId(Integer.valueOf(obj[j++].toString()));
                tempList.add(tempDTO);
            }
        }
        return tempList;
    }

    public int getLazyAvailableItemsCount(final Set<Filter> filterSet) throws SystemException {

        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetails.class);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());

        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(
                ConstantsUtils.USER_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(
                ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.INBOUND_STATUS_D));
            for (Container.Filter filter : filterSet) {

                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                    if (ConstantsUtils.ITEM_NO.equals(stringFilter.getPropertyId())) {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_NO, filterString));
                    }
                    if (ConstantsUtils.ITEM_NAME.equals(stringFilter.getPropertyId())) {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_NAME, filterString));
                    }
                    if (ConstantsUtils.ATTACHED_STATUS.equals(stringFilter.getPropertyId())) {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("rsDetailsAttachedStatus", Integer.valueOf(stringFilter.getFilterString())));
                    }
                    if (ConstantsUtils.REBATE_PLAN_NAME.equals(stringFilter.getPropertyId())) {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.like("rebatePlanMasterSid",stringFilter.getFilterString()));
                    }
                    if (ConstantsUtils.REBATE_BUNDLE_NO.equals(stringFilter.getPropertyId())) {
                            ifpDynamicQuery.add(RestrictionsFactoryUtil.like("rsDetailsBundleNo", filterString));
                    }
                    

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                   
                    if (stringFilter.getValue() instanceof Date) {

                        Date filterString = (Date) stringFilter.getValue();
                        if (ConstantsUtils.START_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.ITEM_REBATE_START_DATE, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.ITEM_REBATE_START_DATE, filterString));
                            }
                        }
                        if (ConstantsUtils.END_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.ITEM_REBATE_END_DATE, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.ITEM_REBATE_END_DATE, filterString));
                            }
                        }
                        if ("attachedDate".equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.RS_DETAILS_ATTACHED_DATE, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.RS_DETAILS_ATTACHED_DATE, filterString));
                            }
                        }
                        if ("revisionDate".equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.RS_MODIFIED_DATE, filterString));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.le(ConstantsUtils.RS_MODIFIED_DATE, filterString));
                            }
                        }
                    } else {
                        Integer filterValue = (Integer) stringFilter.getValue();
                        if (ConstantsUtils.ATTACHED_STATUS.equals(stringFilter.getPropertyId()) && filterValue != 0) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("rsDetailsAttachedStatus", filterValue));
                        }
                        if (ConstantsUtils.REBATE_PLAN_NAME.equals(stringFilter.getPropertyId()) && filterValue != 0) {
                                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanMasterSid", filterValue));
                        }
                    }
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;                    
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();

                    if (ConstantsUtils.START_DATE.equals(stringFilter.getPropertyId())) {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.ITEM_REBATE_START_DATE, filterString));
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.ITEM_REBATE_START_DATE, filterString1));
                    }

                    if (ConstantsUtils.END_DATE.equals(stringFilter.getPropertyId())) {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.ITEM_REBATE_END_DATE, filterString));
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.ITEM_REBATE_END_DATE, filterString1));
                    }
                    
                    if ("attachedDate".equals(stringFilter.getPropertyId())) {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.RS_DETAILS_ATTACHED_DATE, filterString));
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.RS_DETAILS_ATTACHED_DATE, filterString1));
                    }

                    if ("revisionDate".equals(stringFilter.getPropertyId())) {
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.ge(ConstantsUtils.RS_MODIFIED_DATE, filterString));
                        ifpDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantsUtils.RS_MODIFIED_DATE, filterString1));
                    }
                }

            }
        long ifpId = ImtdRsDetailsLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery);
        return Integer.parseInt(String.valueOf(ifpId));

    }
    
    public Object getLazyItemDetailsResults(final int startIndex, final int end, List<SortByColumn> columns, final Set<Filter> filterSet,boolean isCount,String record) throws PortalException, SystemException {
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetails.class);
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(
                ConstantsUtils.USER_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(
                ConstantsUtils.SESSION_ID, sessionId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.INBOUND_STATUS_D));
        ifpDynamicQuery.setLimit(startIndex, end);
        RsUtils.loadColumnName();
        String column = "ITEM_NO";
        String orderBy = "ASC";
        if (columns != null) {
            for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                column = RsUtils.getColumnName(orderByColumn.getName());
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    orderBy = "ASC";
                } else {
                    orderBy = "DESC";
                }
            }
        }
        Map<String, Object> parameters = new HashMap<>();
        
        if(!StringUtils.isBlank(record)){
            if(record.contains(ConstantsUtils.CURRENT)){
                    parameters.put(ConstantsUtils.CURRENT, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.HISTORY)){
                    parameters.put(ConstantsUtils.HISTORY, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
                if(record.contains(ConstantsUtils.FUTURE)){
                    parameters.put(ConstantsUtils.FUTURE, CommonUIUtils.convertStringToDate(new Date().toString(), DEFAULT_JAVA_DATE_FORMAT, DEFAULT_SQL_DATE_FORMAT));
                }
        }
        
        DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.YMD_FORMAT);
        if(filterSet != null){
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();                    
                    Object propertyId = stringFilter.getPropertyId();
                    if (ConstantsUtils.ATTACHED_STATUS.equals(propertyId)) {                        
                        parameters.put(String.valueOf(stringFilter.getPropertyId()), stringFilter.getFilterString());
                    } else {
                        filterString = (filterString != null) ? CommonUtil.buildFilterCriteria(filterString) : filterString;
                        parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                    }                    
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue)));

                } else if (filter instanceof Compare) {

                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    
                        Date value = (Date) compare.getValue();
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(value)));
                        } else {
                            parameters.put(compare.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(value)));
                        }

                } else if (filter instanceof And) {

                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {                        
                        if (filter1 instanceof Compare.Less) {
                            Compare.Less less = (Compare.Less) filter1;                            
                            parameters.put(less.getPropertyId() + ConstantsUtils.TO, String.valueOf(less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;                            
                            parameters.put(greater.getPropertyId() + ConstantsUtils.FROM, String.valueOf(greater.getValue()));
                        }
                    }
                }
            }
        }
                     
        String query = getRebateSetupQuery(startIndex, end, userId, sessionId, column, orderBy,parameters,isCount);
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return getLazyResults(resultList);
        }        
    }

    public List<ItemDetailsDTO> getLazyResults(List list) throws SystemException, PortalException {
        
        final List<ItemDetailsDTO> returnList = new ArrayList<>();      
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final Object[] obj = (Object[]) list.get(i);
                int j=0;
                
                Object value;
                ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
               
                value=obj[j++];                 
                itemDetailsDTO.setTempRSDetailsSystemId(Integer.valueOf(value.toString()));
                value=obj[j++];
                itemDetailsDTO.setUserID(value==null?ConstantsUtils.ZERO:value.toString());
                value=obj[j++];
                itemDetailsDTO.setSessionID(value==null?ConstantsUtils.ZERO:value.toString());
                value=obj[j++];
                itemDetailsDTO.setIfpSystemId(value==null?StringUtils.EMPTY:value.toString());
                value=obj[j++];
                itemDetailsDTO.setItemNo(value==null?StringUtils.EMPTY:value.toString());
                value=obj[j++];
                itemDetailsDTO.setItemName(value==null?StringUtils.EMPTY:value.toString());
                value=obj[j++];
                itemDetailsDTO.setItemSystemId(value==null?StringUtils.EMPTY:value.toString());
                value=obj[j++];
                itemDetailsDTO.setRebateAmount(value==null?StringUtils.EMPTY:value.toString());
                value=obj[j++];
                itemDetailsDTO.setStartDate((Date) value);
                value=obj[j++];
                itemDetailsDTO.setEndDate((Date) value);
                value=obj[j++];
                int formulaId = (value==null || StringUtils.isBlank(value.toString()))?0:Integer.parseInt(value.toString());                
                itemDetailsDTO.setFormulaId(String.valueOf(formulaId));
                value=obj[j++];
                itemDetailsDTO.setFormulaName(value==null?StringUtils.EMPTY:value.toString());
                value=obj[j++];
                int tempValue = value==null || StringUtils.isBlank(value.toString()) ? 0 : Integer.valueOf(value.toString());
                itemDetailsDTO.setAttachedStatus(helperListUtil.getIdHelperDTOMap().get(tempValue));
                value=obj[j++];
                itemDetailsDTO.setAttachedDate((Date) value);
                value=obj[j++];
                itemDetailsDTO.setRevisionDate((Date) value);
                j++;
                value=obj[j++];
                itemDetailsDTO.setCheckbox((Boolean) value);
                
                value=obj[j++];                
                itemDetailsDTO.setBundleNo(value==null?StringUtils.EMPTY:value.toString());
                
                HelperDTO helper = null;
                try {
                    value =obj[j++];
                    int rsMasterSid = (value!=null && StringUtils.isNotBlank(value.toString()))? Integer.parseInt(value.toString()):0;
                    helper = new HelperDTO(rsMasterSid, getRebatePlanNameByID(rsMasterSid));
                } catch (SystemException ex) {
                    java.util.logging.Logger.getLogger(RebateScheduleLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                itemDetailsDTO.setRebatePlanName(helper.getDescription());
                j++;
                j++;
                value =obj[j++];
                itemDetailsDTO.setItemId(value==null?StringUtils.EMPTY:value.toString());
                String tempRebatePlanNo = String.valueOf(obj[j++]);
                itemDetailsDTO.setRebatePlanNo(StringUtils.isBlank(tempRebatePlanNo) || ConstantsUtils.NULL.equals(tempRebatePlanNo) ? StringUtils.EMPTY : tempRebatePlanNo);

                itemDetailsDTO.setFormulaNo(obj[NumericConstants.TWENTY_THREE]==null?StringUtils.EMPTY:String.valueOf(obj[NumericConstants.TWENTY_THREE]));
                
                value = obj[NumericConstants.TWENTY_FIVE];
                itemDetailsDTO.setDeductionSystemId(value == null ? StringUtils.EMPTY : value.toString());
                value = obj[NumericConstants.TWENTY_SIX];
                itemDetailsDTO.setDeductionCalendarNo(value == null ? StringUtils.EMPTY : value.toString());
                value = obj[NumericConstants.TWENTY_SEVEN];
                itemDetailsDTO.setDeductionCalendarName(value==null?StringUtils.EMPTY:value.toString());
                
                value = obj[NumericConstants.TWENTY_EIGHT];
                itemDetailsDTO.setSystemID(value == null ? StringUtils.EMPTY : value.toString());
                value = obj[NumericConstants.TWENTY_NINE];
                itemDetailsDTO.setNetSalesFormulaNo(value == null ? StringUtils.EMPTY : value.toString());
                value = obj[NumericConstants.THIRTY];
                itemDetailsDTO.setNetSalesFormulaName(value==null?StringUtils.EMPTY:value.toString());
                
               
                itemDetailsDTO.setNetSalesSystemId(String.valueOf(obj[NumericConstants.THIRTY_ONE]==null?StringUtils.EMPTY:obj[NumericConstants.THIRTY_ONE]));
        
                itemDetailsDTO.setEvaluationSystemId(String.valueOf(obj[NumericConstants.THIRTY_TWO]==null?StringUtils.EMPTY:obj[NumericConstants.THIRTY_TWO]));

                itemDetailsDTO.setCalculationSystemId(String.valueOf(obj[NumericConstants.THIRTY_THREE]==null?StringUtils.EMPTY:obj[NumericConstants.THIRTY_THREE]));
         
                itemDetailsDTO.setEvaluationRuleBundle(String.valueOf(obj[NumericConstants.THIRTY_FOUR]==null?StringUtils.EMPTY:obj[NumericConstants.THIRTY_FOUR]));
                
                itemDetailsDTO.setCalculationRuleBundle(String.valueOf(obj[NumericConstants.THIRTY_FIVE]==null?StringUtils.EMPTY:obj[NumericConstants.THIRTY_FIVE]));
                if(!itemDetailsDTO.getNetSalesSystemId().isEmpty()){
                itemDetailsDTO.setNetSalesRule(getRuleNameById(Integer.parseInt(itemDetailsDTO.getNetSalesSystemId())));
                }
                if(!itemDetailsDTO.getCalculationSystemId().isEmpty()){
                itemDetailsDTO.setCalculationRule(getRuleNameById(Integer.parseInt(itemDetailsDTO.getCalculationSystemId())));
                }
                if(!itemDetailsDTO.getEvaluationSystemId().isEmpty()){
                itemDetailsDTO.setEvaluationRule(getRuleNameById(Integer.parseInt(itemDetailsDTO.getEvaluationSystemId())));
                }
               
                
                 returnList.add(itemDetailsDTO);

            }
        }
        return returnList;
    }

    /**
     * Method to add the item master details that has been attached to the IFP.
     *
     * @param selectedIfpId the selected ifp id
     * @return the list
     */
    public void addItemDetailsFromIfp(String ifpSystemId,String idValue) {

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdRsDetailsLocalServiceUtil.deleteAll(userId, sessionId, null, null, null, null, null, null);
        ImtdRsDetailsLocalServiceUtil.insertTempRsDetailsInADD(userId, sessionId, createdDate, ifpSystemId, idValue, null, null, null);


    }

    public void removeRsItems(){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        ImtdRsDetailsLocalServiceUtil.deleteAll(userId, sessionId, null, null, null, null, null, null);
    }
    
    
    /**
     * Method to add the item master details that has been attached to the IFP.
     *
     * @param selectedIfpId the selected ifp id
     * @return the list
     */
    public void loadItemDetailsFromIfp(String rsSystemId) throws SystemException, PortalException {

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        RebateScheduleMasterDTO rsMasterDto = getRebateScheduleMasterById(Integer.valueOf(rsSystemId));
        
        ImtdRsDetailsLocalServiceUtil.insertTempRsDetailsInEdit(userId, sessionId, createdDate, rsSystemId, rsMasterDto.getRebateScheduleId(), null, null, sessionDTO.getMode());


    }

    public void deleteOperation() {

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        ImtdRsDetailsLocalServiceUtil.updateOperation(userId, sessionId, createdDate, StringUtils.EMPTY, null, null, null, null);
    }

    /**
     * Remove all the records from the Temp Table.
     *
     * @param itemIds
     * @param removeAllFlag
     * @throws SystemException
     */
    public void removeAllFromTempTable(Boolean removeAllFlag) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
        if (removeAllFlag) {
            ImtdRsDetailsLocalServiceUtil.deleteAll(userId, sessionId, tempCreatedDate, "All", null, null, null, null);
        } else {
            ImtdRsDetailsLocalServiceUtil.deleteAll(userId, sessionId, tempCreatedDate, null, null, null, null, null);
        }

    }

    public void saveToTemp(List<ItemDetailsDTO> list, Boolean rebateLevelReset) throws PortalException, SystemException {
        List<ItemDetailsDTO> tempList = list;
        for (Iterator<ItemDetailsDTO> temp = tempList.iterator(); temp.hasNext();) {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            ItemDetailsDTO item = temp.next();
            ImtdRsDetails tempModel = ImtdRsDetailsLocalServiceUtil.getImtdRsDetails(item.getTempRSDetailsSystemId());
            tempModel.setCheckRecord(item.getCheckbox());
            tempModel.setRsDetailsAttachedStatus(item.getAttachedStatus().getId());
            tempModel.setRsDetailsModifiedBy(userId);
            tempModel.setRsDetailsModifiedDate(new Date());
            tempModel.setRsDetailsCreatedBy(userId);
            tempModel.setItemRebateStartDate(item.getStartDate());
            tempModel.setItemRebateEndDate(item.getEndDate());
            tempModel.setRsDetailsBundleNo(item.getBundleNo());
            if(StringUtils.isNotBlank(item.getRebateAmount())) {
                tempModel.setRsDetailsRebateAmount(Double.parseDouble(item.getRebateAmount()));
            } else {
                tempModel.setRsDetailsRebateAmount(0);
            }
            if(StringUtils.isNotBlank(item.getRebatePlanSystemId())){
                tempModel.setRebatePlanMasterSid(item.getRebatePlanSystemId());
            }
            if(StringUtils.isNotBlank(item.getRebatePlanName())){
                tempModel.setRsDetailsRebatePlanName(item.getRebatePlanName());
            }   
            if (StringUtils.isNotBlank(item.getDeductionSystemId())) {
                tempModel.setDeductionCalendarMasterSid(Integer.valueOf(item.getDeductionSystemId()));
            }
            if (StringUtils.isNotBlank(item.getDeductionCalendarNo())) {
                tempModel.setRsDetailsDeductionCalendarNo(item.getDeductionCalendarNo());
            }
            if (StringUtils.isNotBlank(item.getDeductionCalendarName())) {
                tempModel.setRsDetailsDeductionCalendarName(item.getDeductionCalendarName());
            }
            if (StringUtils.isNotBlank(item.getSystemID())) {
                tempModel.setNetSalesFormulaMasterSid(Integer.valueOf(item.getSystemID()));
            }
            if (StringUtils.isNotBlank(item.getNetSalesFormulaNo())) {
                tempModel.setRsDetailsNetSalesFormulaNo(item.getNetSalesFormulaNo());
            }
            if (StringUtils.isNotBlank(item.getNetSalesFormulaName())) {
                tempModel.setRsDetailsNetSalesFormulaName(item.getNetSalesFormulaName());
            }
            tempModel.setRsDetailsRebatePlanName(StringUtils.isBlank(item.getRebatePlanName()) || ConstantsUtils.NULL.equals(item.getRebatePlanName()) ? StringUtils.EMPTY : item.getRebatePlanName());
            tempModel.setRsDetailsFormulaNo(StringUtils.isBlank(item.getFormulaNo()) || ConstantsUtils.NULL.equals(item.getFormulaNo()) ? StringUtils.EMPTY : item.getFormulaNo());
            tempModel.setRsDetailsFormulaName(StringUtils.isBlank(item.getFormulaName()) || ConstantsUtils.NULL.equals(item.getFormulaName()) ? StringUtils.EMPTY : item.getFormulaName());
            tempModel.setRsDetailsFormulaId(StringUtils.isBlank(item.getFormulaId()) || ConstantsUtils.NULL.equals(item.getFormulaId()) ? StringUtils.EMPTY : item.getFormulaId());
            if(!(StringUtils.isBlank(item.getRebatePlanSystemId()) || ConstantsUtils.NULL.equals(item.getRebatePlanSystemId()))){
                tempModel.setRebatePlanMasterSid(item.getRebatePlanSystemId());            
            }
            if (!(StringUtils.isBlank(item.getCalculationSystemId()) || ConstantsUtils.NULL.equals(item.getCalculationSystemId()) || ConstantsUtils.ZERO.equals(item.getCalculationSystemId())    )) {
                tempModel.setCalculationRule(item.getCalculationSystemId());
            }
            if (!(StringUtils.isBlank(item.getNetSalesSystemId()) || ConstantsUtils.NULL.equals(item.getNetSalesSystemId())|| ConstantsUtils.ZERO.equals(item.getCalculationSystemId()))) {
                tempModel.setNetSalesRule(item.getNetSalesSystemId());
            }
            if (!(StringUtils.isBlank(item.getEvaluationSystemId()) || ConstantsUtils.NULL.equals(item.getEvaluationSystemId())|| ConstantsUtils.ZERO.equals(item.getCalculationSystemId()))) {
                tempModel.setEvaluationRule(item.getEvaluationSystemId());
            }
            
            if (StringUtils.isNotBlank(item.getCalculationRuleBundle())) {
                tempModel.setCalculationRuleBundle(item.getCalculationRuleBundle());
            }
             if (StringUtils.isNotBlank(item.getEvaluationRuleBundle())) {
                tempModel.setEvaluationRuleBundle(item.getEvaluationRuleBundle());
            }
            
            
            ImtdRsDetailsLocalServiceUtil.updateImtdRsDetails(tempModel);            
        }
    }

    public void populateLogic(String populateField, String PopulateValue, Boolean populateAll, String populate, String rebatePlanLevel) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final String createdDate = String.valueOf(sessionDTO.getSessionDate());
        
        if (populateAll) {
            ImtdRsDetailsLocalServiceUtil.updateForPopulateAll(userId, sessionId, createdDate, populate, populateField, PopulateValue, rebatePlanLevel, null);
        } else {
            ImtdRsDetailsLocalServiceUtil.updateForPopulate(userId, sessionId, createdDate, null, populateField, PopulateValue, rebatePlanLevel, null);
        }
    }

    public Boolean saveValidation(String type) {
        Boolean retFlag;
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        Integer result = (Integer) ImtdRsDetailsLocalServiceUtil.validateTempRSDeatils(userId, sessionId, null, type, null, null, null, null);
       
        if ("AtleastOne".equals(type)) {
            retFlag = result > 0;
        } else {
            retFlag = result <= 0;
        }
        return retFlag;
    }
    
    public Boolean rebateSaveValidation(String type) {
        Boolean retFlag;
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        Integer result = (Integer) ImtdRsDetailsLocalServiceUtil.validateTempRSDeatils(userId, sessionId, null, type, null, null, null, null);
        LOGGER.debug("resultin rebateSaveValidation > " + result);
        retFlag = result > 0;
        return retFlag;
    }
    
    public List saveProductBundleValidation(String type) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        List result = (List) ImtdRsDetailsLocalServiceUtil.validateTempRSDeatils(userId, sessionId, null, type, null, null, null, null);
        return result;
    }

    public int rsViewCount() throws SystemException {
        final String rsSystemId = String.valueOf(sessionDTO.getSystemId());
        final DynamicQuery rsDynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);
        rsDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_MODEL_SID, Integer.parseInt(rsSystemId)));
        rsDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        rsDynamicQuery.setProjection(ProjectionFactoryUtil.count("ifpModelSid"));
        return Integer.parseInt(String.valueOf(RsDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery).get(0)));
    }

    public List<ItemDetailsDTO> getViewTableResult(int start, int end, List<OrderByColumn> columns) throws SystemException, PortalException {
        final String rsSystemId = String.valueOf(sessionDTO.getSystemId());
        RsUtils.loadViewColumnName();
        String column = "ITEM_NO";
        String orderBy = "ASC";
        for (final Iterator<OrderByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();
            
            column =RsUtils.getViewColumnName(orderByColumn.getName());
            
            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderBy = "ASC";
            } else {
                orderBy = "DESC";
            }
        }   
        List<Object[]> result = (List<Object[]>) ImtdRsDetailsLocalServiceUtil.getTempRSLazyList(rsSystemId, null, null, StringUtils.EMPTY, start, end, column, orderBy);
        return getCoustomizedViewDTO(result);
    }

    public  List<ItemDetailsDTO> getCoustomizedViewDTO(List<Object[]> list) throws SystemException, PortalException {
        List<ItemDetailsDTO> resultList = new ArrayList<>();
        Map<Integer, String> hm = com.stpl.app.util.GeneralCommonUtils.getCodeDescription();
        for (Iterator<Object[]> temp = list.iterator(); temp.hasNext();) {
            Object[] item = temp.next();
            ItemDetailsDTO resultDTO = new ItemDetailsDTO();
            int i = 0;
            resultDTO.setItemNo(HelperUtils.getString(item[i++]));
            resultDTO.setItemName(HelperUtils.getString(item[i++]));
            
            resultDTO.setFormulaName(HelperUtils.getString(item[i++]));
            resultDTO.setRebateAmount(HelperUtils.getString(item[i++]));
            resultDTO.setStartDate((Date) item[i++]);
            resultDTO.setEndDate((Date) item[i++]);
            String attachedStatus = hm.get(item[i]!=null?(Integer) item[i]:0);
            resultDTO.setAttachedStatusValue(attachedStatus);
            i++;
            resultDTO.setFormulaType(HelperUtils.getString(item[i++]));
            resultDTO.setFormulaMethodId(HelperUtils.getString(item[i++]));
            int end = i++;
            resultDTO.setFormulaNo(HelperUtils.getString(item[end]));
            HelperDTO helper = null;
            int sysId = item[NumericConstants.TWELVE]!=null?(Integer) item[NumericConstants.TWELVE]:0;
            helper = new HelperDTO(sysId, getRebatePlanNameByID(sysId));
            resultDTO.setRebatePlanName(helper.getDescription());
            resultDTO.setAttachedDate((Date) item[NumericConstants.THIRTEEN]);
            resultDTO.setRevisionDate((Date) item[NumericConstants.FOURTEEN]);
            Object value = item[NumericConstants.SEVENTEEN];
            resultDTO.setItemId(String.valueOf(value));
            value=item[NumericConstants.EIGHTEEN];
            resultDTO.setItemSystemId(String.valueOf(value));
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
                List<Integer> formulaIdList = getImtdFormulaDescList(Integer.parseInt(resultDTO.getItemSystemId()));
                if(formulaIdList!=null && !formulaIdList.isEmpty()){
                    query.add(RestrictionsFactoryUtil.in(ConstantsUtils.FORMULA_ID, formulaIdList));
                    query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEMID, resultDTO.getItemId()));
                    query.add(RestrictionsFactoryUtil.le(ConstantsUtils.START_DATE, new Date()));
                    query.addOrder(OrderFactoryUtil.desc(ConstantsUtils.START_DATE));
                    List<FormulaDetailsMaster> companyTradeClass=FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
                 
                if(companyTradeClass!=null && !companyTradeClass.isEmpty()){
                   
                    String formulaNo = companyTradeClass.get(0).getFormulaNo();
                    resultDTO.setFormulaNo(formulaNo);
                    resultDTO.setFormulaName(companyTradeClass.get(0).getFormulaDesc());
                    
                }
                }
            
            resultList.add(resultDTO);
        }
        return resultList;
    }

    public static String getRebatePlanNameByID(final int id) throws SystemException, PortalException {
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.REBATE_PLAN_MASTER_SID, id));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.REBATE_PLAN_NAME));
        final List<String> returnList = DAO.getRebatePlanNameList(dynamicQuery);
        if (returnList.isEmpty()) {
            return StringUtils.EMPTY;
        } else {
            return String.valueOf(returnList.get(0));
        }

    }    
    
    /**
     * Converts the RsModel class to Rebate Schedule Master DTO.
     * @param rsModel
     * @param udcs
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public RebateScheduleMasterDTO convertRSModelToDTO(RsModel rsModel, Udcs udcs) throws PortalException, SystemException{
        RebateScheduleMasterDTO rsMasterDto = new RebateScheduleMasterDTO();
        
        if(rsModel!=null){
            rsMasterDto.setRebateScheduleSystemId(rsModel.getRsModelSid());
            rsMasterDto.setRebateScheduleId(rsModel.getRsId());
            rsMasterDto.setRebateScheduleNo(rsModel.getRsNo());
            rsMasterDto.setRebateScheduleName(rsModel.getRsName());
            rsMasterDto.setRebateScheduleStatus(getHelperDTOById(rsModel.getRsStatus()));
            rsMasterDto.setRebateScheduleType(getHelperDTOById(rsModel.getRsType()));
            rsMasterDto.setRebateProgramType(getHelperDTOById(rsModel.getRebateProgramType()));
            rsMasterDto.setPaymentMethod(getHelperDTOById(rsModel.getPaymentMethod()));        
            rsMasterDto.setRebateFrequency(getHelperDTOById(rsModel.getRebateFrequency()));
            rsMasterDto.setCalculationLevel(getHelperDTOById(rsModel.getCalculationLevel()));
            rsMasterDto.setCalculationType(getHelperDTOById(rsModel.getCalculationType()));
            rsMasterDto.setPaymentLevel(getHelperDTOById(rsModel.getPaymentLevel()));
            rsMasterDto.setPaymentFrequency(getHelperDTOById(rsModel.getPaymentFrequency()));
            rsMasterDto.setInterestBearingIndicator(getHelperDTOById(rsModel.getInterestBearingIndicator()));
            rsMasterDto.setInterestBearingBasisInfo(getHelperDTOById(rsModel.getInterestBearingBasis()));
            rsMasterDto.setRebateRuleType(getHelperDTOById(rsModel.getRebateRuleType()));
            rsMasterDto.setPaymentFrequency(getHelperDTOById(rsModel.getPaymentFrequency()));
            rsMasterDto.setPaymentTerms(getHelperDTOById(rsModel.getPaymentTerms()));
            rsMasterDto.setCalendar(getHelperDTOById(rsModel.getRsCalendar()));            
            rsMasterDto.setPaymentGracePeriod(rsModel.getPaymentGracePeriod());
            if(StringUtils.isNotBlank(rsModel.getRebatePlanLevel())) {
                rsMasterDto.setRebatePlanLevel(Integer.parseInt(rsModel.getRebatePlanLevel()));
            }
            
            if (rsModel.getDeductionInclusion() != null && !ConstantsUtils.NULL.equals(rsModel.getDeductionInclusion())) {
            rsMasterDto.setDeductionInclusion(getHelperDTOById(Integer.parseInt(rsModel.getDeductionInclusion())));
            } else {
                rsMasterDto.setDeductionInclusion(new HelperDTO(0, Constants.SELECT_ONE));
            }
            if (rsModel.getEvaluationRuleLevel() != null && !ConstantsUtils.NULL.equals(rsModel.getEvaluationRuleLevel())) {
            rsMasterDto.setEvaluationRuleLevel(getHelperDTOById(Integer.parseInt(rsModel.getEvaluationRuleLevel())));
            } else {
                rsMasterDto.setEvaluationRuleLevel(new HelperDTO(0, Constants.SELECT_ONE));
            }      
            if (rsModel.getEvaluationRuleType() != null && !ConstantsUtils.NULL.equals(rsModel.getEvaluationRuleType())) {
            rsMasterDto.setEvaluationRuleType(getHelperDTOById(Integer.parseInt(rsModel.getEvaluationRuleType())));
            } else {
                rsMasterDto.setEvaluationRuleType(new HelperDTO(0, Constants.SELECT_ONE));
            } 
             if (rsModel.getCalculationRuleLevel() != null && !ConstantsUtils.NULL.equals(rsModel.getCalculationRuleLevel())) {
            rsMasterDto.setCalculationRuleLevel(getHelperDTOById(Integer.parseInt(rsModel.getCalculationRuleLevel())));
            } else {
                rsMasterDto.setCalculationRuleLevel(new HelperDTO(0, Constants.SELECT_ONE));
            } 

            
           

            rsMasterDto.setEvaluationSystemId((rsModel.getEvaluationRuleOrAssociation() != null && !ConstantsUtils.NULL.equals(rsModel.getEvaluationRuleOrAssociation()))?rsModel.getEvaluationRuleOrAssociation():ConstantsUtils.ZERO);
            rsMasterDto.setCalculationSystemId((rsModel.getCalculationRule() != null && !ConstantsUtils.NULL.equals(rsModel.getCalculationRule()))?rsModel.getCalculationRule():ConstantsUtils.ZERO);
         
            rsMasterDto.setEvaluationRuleAssociation( !rsMasterDto.getEvaluationSystemId().equals(ConstantsUtils.ZERO)?getRuleNameById(Integer.parseInt(rsMasterDto.getEvaluationSystemId())):StringUtils.EMPTY);
            rsMasterDto.setCalculationRule(!rsMasterDto.getCalculationSystemId().equals(ConstantsUtils.ZERO)?getRuleNameById(Integer.parseInt(rsMasterDto.getCalculationSystemId())):StringUtils.EMPTY);
            if(rsModel.getRsStartDate()!=null){
            rsMasterDto.setStartDate(rsModel.getRsStartDate());
            }
             if(rsModel.getRsEndDate()!=null){
            rsMasterDto.setEndDate(rsModel.getRsEndDate());
             }
            rsMasterDto.setValidationProfile(getHelperDTOById(rsModel.getRsValidationProfile()));
            rsMasterDto.setRebateProcessingTypeDDLB(getHelperDTOById(rsModel.getRebateProcessingType()));
            rsMasterDto.setInterestBearingIndicatorDDLB(getHelperDTOById(rsModel.getInterestBearingIndicator()));
            rsMasterDto.setInterestBearingBasisDDLB(getHelperDTOById(rsModel.getInterestBearingBasis()));
            rsMasterDto.setItemRebateStartDate(rsModel.getRsStartDate());
            rsMasterDto.setItemRebateEndDate(rsModel.getRsEndDate());
            rsMasterDto.setRebateScheduleCategory(getHelperDTOById(rsModel.getRsCategory()));
            rsMasterDto.setTradeClass(getHelperDTOById(rsModel.getRsTradeClass()));
            rsMasterDto.setRebateScheduleTransRefId(rsModel.getRsTransRefId());
            rsMasterDto.setRebateScheduleTransRefNo(rsModel.getRsTransRefId());            
            rsMasterDto.setRebateScheduleTransRefName(rsModel.getRsTransRefName());
            rsMasterDto.setRebateScheduleAlias(rsModel.getRsAlias());            
            rsMasterDto.setRebateScheduleDesignation(getHelperDTOById(rsModel.getRsDesignation()));            
            rsMasterDto.setParentRebateScheduleId(rsModel.getParentRsId());
            rsMasterDto.setParentRebateScheduleName(rsModel.getParentRsName());
            rsMasterDto.setInternalNotes(rsModel.getInternalNotes()); 
            rsMasterDto.setManufacturer(rsModel.getManfCompanyMasterSid());
            if (udcs != null) {
                rsMasterDto.setUdc1(getHelperDTOById(udcs.getUdc1()));
                rsMasterDto.setUdc2(getHelperDTOById(udcs.getUdc2()));
                rsMasterDto.setUdc3(getHelperDTOById(udcs.getUdc3()));
                rsMasterDto.setUdc4(getHelperDTOById(udcs.getUdc4()));
                rsMasterDto.setUdc5(getHelperDTOById(udcs.getUdc5()));
                rsMasterDto.setUdc6(getHelperDTOById(udcs.getUdc6()));
            }else{
                rsMasterDto.setUdc1(new HelperDTO(0, Constants.SELECT_ONE));
                rsMasterDto.setUdc2(new HelperDTO(0, Constants.SELECT_ONE));
                rsMasterDto.setUdc3(new HelperDTO(0, Constants.SELECT_ONE));
                rsMasterDto.setUdc4(new HelperDTO(0, Constants.SELECT_ONE));
                rsMasterDto.setUdc5(new HelperDTO(0, Constants.SELECT_ONE));
                rsMasterDto.setUdc6(new HelperDTO(0, Constants.SELECT_ONE));
            } 
        }        
        return rsMasterDto;                
    }	
        /**
        * Method to get attached doc List
        *
        * @param masterTableSid
        * @param moduleName
        * @param filepath
        * @param id
        * @return attachmentDTOList
        */
	@SuppressWarnings("unchecked")
	public List<ItemDetailsDTO> getAttachmentDTOList(int masterTableSid,
			String moduleName,String filepath,String id) {
		List<ItemDetailsDTO> attachmentDTOList = new ArrayList<>();
		DynamicQuery docDetailsDynamicQuery = DynamicQueryFactoryUtil
				.forClass(MasterDataFiles.class);
		docDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableSid",
				masterTableSid));
		docDetailsDynamicQuery.add(RestrictionsFactoryUtil.ilike(
				"masterTableName", moduleName));
		List<MasterDataFiles> docDetailsList = null;
		ItemDetailsDTO attachmentDTO;
		try {
			docDetailsList = MasterDataFilesLocalServiceUtil
					.dynamicQuery(docDetailsDynamicQuery);
		
		if (docDetailsList != null && docDetailsList.size() > 0) {
			for (MasterDataFiles docDetails : docDetailsList) {
				attachmentDTO = new ItemDetailsDTO();
				attachmentDTO.setDocDetailsId(docDetails.getMasterDataFilesSid());
                                String filePath = docDetails.getFilePath();
				attachmentDTO.setDocumentFullPath(filePath);  
                                attachmentDTO.setDocumentName(filePath.substring(filePath.lastIndexOf("/")+1, filePath.lastIndexOf("_"))+filePath.substring(filePath.lastIndexOf("."))); 
                                String tempfilePath = docDetails.getFilePath();
				attachmentDTO.setDocumentFullPath(tempfilePath);   
                                String fileNameWithId = tempfilePath.replace(filepath, StringUtils.EMPTY);
                                StringBuilder fileName = new StringBuilder(fileNameWithId);
                                fileName.replace(fileName.lastIndexOf("_"), fileName.lastIndexOf("."), StringUtils.EMPTY);
                                attachmentDTO.setDocumentName(fileName.toString());   
                                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                TimeZone central = TimeZone.getTimeZone("CST");
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
         * Method to add Notes in Additional Information Screen
         * 
         * @param projectionId
         * @param moduleName
         * @return notes as String
         */
	@SuppressWarnings("unchecked")
	public String getNotes(int projectionId, String moduleName) {
		StringBuilder notes = new StringBuilder();
		notes.append(StringUtils.EMPTY);
		DynamicQuery additionalNotesDynamicQuery = DynamicQueryFactoryUtil
				.forClass(RsModel.class);
		additionalNotesDynamicQuery.add(RestrictionsFactoryUtil.eq(
				ConstantsUtils.RS_MODEL_SID, projectionId));
		List<RsModel> additionalNotesList = null;
		try {
			additionalNotesList = RsModelLocalServiceUtil
					.dynamicQuery(additionalNotesDynamicQuery);
			if (additionalNotesList != null && additionalNotesList.size() > 0) {
				for (RsModel additionalNotes : additionalNotesList) {
					notes.append("<");
					notes.append(additionalNotes.getCreatedDate());
					notes.append("> ");
					notes.append(additionalNotes.getCreatedBy());
					notes.append(" : ");
					notes.append(additionalNotes.getInternalNotes());
					notes.append("\n");
				}
			}
		} catch (SystemException e) {
			LOGGER.error(e);
		}
		return notes.toString();
	}
        /**
         * Method to Save the Added Notes
         * 
         * @param projectionId
         * @param createdBy
         * @param notes
         * @param moduleName
         * @return true or false
         */
	public Boolean saveNotes(int projectionId, String createdBy, String notes,
			String moduleName) {
		RsModel additionalNotesList = RsModelLocalServiceUtil.createRsModel(0);
		additionalNotesList.setCreatedDate(new Date());
		additionalNotesList.setCreatedBy(0);
		additionalNotesList.setParentRsId(String.valueOf(projectionId));
		additionalNotesList.setInternalNotes(notes);
		try {
			RsModelLocalServiceUtil
					.addRsModel(additionalNotesList);
		} catch (SystemException e) {
			LOGGER.error(e);
		}
		return true;
	}
 
    
    public void saveUploadedInformation(List<NotesDTO> availableUploadedInformation, String moduleName, int moduleSystemId,boolean isCopy) throws SystemException, PortalException {

        if (availableUploadedInformation != null && availableUploadedInformation.size() > 0) {

            List<Integer> uploadedDetailsId = new ArrayList<>();
            for (NotesDTO uploadDetails : availableUploadedInformation) {
            
                if (uploadDetails.getDocDetailsId() == 0 || isCopy) {
                    MasterDataFiles masterDataFiles = MasterDataFilesLocalServiceUtil.createMasterDataFiles(0);
                    masterDataFiles.setMasterTableName(moduleName);
                    masterDataFiles.setMasterTableSid(moduleSystemId);
                    masterDataFiles.setFilePath(uploadDetails.getDocumentFullPath());
                    masterDataFiles.setCreatedBy(uploadDetails.getUserId());
                    masterDataFiles.setCreatedDate(new Date());
                    masterDataFiles = MasterDataFilesLocalServiceUtil.addMasterDataFiles(masterDataFiles);
                    uploadedDetailsId.add(masterDataFiles.getMasterDataFilesSid());
                } else {
                    uploadedDetailsId.add(uploadDetails.getDocDetailsId());
}
            }
            
            DynamicQuery masterDataFilesDynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class);            
            masterDataFilesDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.in("masterDataFilesSid", uploadedDetailsId)));
            masterDataFilesDynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableName", moduleName));
            masterDataFilesDynamicQuery.add(RestrictionsFactoryUtil.eq("masterTableSid", moduleSystemId));
            
            List<MasterDataFiles> masterDataFilesList = MasterDataFilesLocalServiceUtil.dynamicQuery(masterDataFilesDynamicQuery);
            
            
            if (!masterDataFilesList.isEmpty()) {
                for (MasterDataFiles uploadDetails : masterDataFilesList) {
                    MasterDataFilesLocalServiceUtil.deleteMasterDataFiles(uploadDetails.getMasterDataFilesSid());
                }
            }
        }
    }
    public static List<HelperDTO> getManufacturer() {
        List list;

        final List<HelperDTO> helperList = new ArrayList<>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

        dynamicQuery.add(PropertyFactoryUtil.forName("companyType").in(
                DynamicQueryFactoryUtil.forClass(HelperTable.class)
                .add(RestrictionsFactoryUtil.eq("listName", "COMPANY_TYPE"))
                .add(RestrictionsFactoryUtil.eq("description", "MANUFACTURER"))
                .setProjection(ProjectionFactoryUtil.property("helperTableSid"))));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.COMPANY_MASTER_ID));
        productProjectionList.add(ProjectionFactoryUtil.property("companyName"));
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
        dynamicQuery.setProjection(productProjectionList);
        dynamicQuery.addOrder(OrderFactoryUtil.asc("companyName"));
        try {
            list = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
            
            if (list != null) {
                HelperDTO helperTable;
                for (int i = 0; i < list.size(); i++) {
                    Object[] companyName=(Object[])list.get(i);
                    helperTable = new HelperDTO(Integer.valueOf(String.valueOf(companyName[0])), String.valueOf(companyName[1]));
                    helperList.add(helperTable);
                }

            }

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        
          return helperList;
    }
    
    public void sortBy(final String columnName, final boolean asc, final List<RebateScheduleSearchDTO> searchList) {
        if (asc) {                                     
            if(ConstantsUtils.REBATE_SCHEDULE_ID.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsIdAsc);
            }else if(ConstantsUtils.REBATE_SCHEDULE_NO.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsNoAsc);
            }else if(ConstantsUtils.REBATE_SCHEDULE_NAME.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsNameAsc);
            }else if(ConstantsUtils.REBATE_SCHEDULE_STATUS.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsStatusAsc);                
            }else if(ConstantsUtils.REBATE_SCHEDULE_TYPE.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsTypeAsc);                
            }else if(ConstantsUtils.REBATE_PROGRAM_TYPE.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rebatePgmTypeAsc);                 
            }
        } else {
            if(ConstantsUtils.REBATE_SCHEDULE_ID.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsIdDsc);
            }else if(ConstantsUtils.REBATE_SCHEDULE_NO.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsNoDsc);
            }else if(ConstantsUtils.REBATE_SCHEDULE_NAME.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsNameDsc);
            }else if(ConstantsUtils.REBATE_SCHEDULE_STATUS.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsStatusDsc);                
            }else if(ConstantsUtils.REBATE_SCHEDULE_TYPE.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rsTypeDsc);                
            }else if(ConstantsUtils.REBATE_PROGRAM_TYPE.equals(columnName)){
                Collections.sort(searchList, RebateScheduleSearchDTO.rebatePgmTypeDsc);                 
            }
        }
    }   
    /**
     *
     * @param userId
     * @param sessionId
     * @param tempCreatedDate
     * @param process
     * @return
     * @throws SystemException
     */
    public List<Object> validateNull(String userId, String sessionId, String tempCreatedDate, String process) {
        return (List<Object>) ImtdRsDetailsLocalServiceUtil.validateTempRSDeatils(userId, sessionId, tempCreatedDate, process, null, null, null,null);
}
    
        /**
     * Method to add description and details in a list from HelperTable .
     *
     * @param listType the list type
     * @return the helper details
     */
    public List<String> getHelperDetailsForFilter(final String listType) throws SystemException {
        List<HelperTable> list = null;
        final List<String> returnList = new ArrayList<>();
        final List<HelperDTO> helperList = new ArrayList<>();

        LOGGER.debug("In getHelperDetails P1:listType=" + listType);
        list = DAO.getHelperTableDetailsByListName(listType);
        returnList.add(ConstantsUtils.SHOW_ALL);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));
                returnList.add(helperTable.getDescription());
            }
        }
        LOGGER.debug("getHelperDetails Return listSize=" + returnList.size());
        Collections.sort(returnList);
        return returnList;
    }
    
    
    public int getForecastingFormulaCount(){
        
        
        try {
            return ForecastingFormulaLocalServiceUtil.getForecastingFormulasCount();
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(RebateScheduleLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0; 
    }
    
    
     public List<RSFormulaDTO> getForecastingFormula(int start, int end){
        
         List<RSFormulaDTO> resultList = new ArrayList<>();
         
         final DynamicQuery forecastingFormula = DynamicQueryFactoryUtil.forClass(ForecastingFormula.class);
         forecastingFormula.setLimit(start, end);
        List<ForecastingFormula> list;
        try {
            list = ForecastingFormulaLocalServiceUtil.dynamicQuery(forecastingFormula);
            resultList =  getCustomisedForecastingFormula(list);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(RebateScheduleLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           return resultList;
    }
    
    public List<RSFormulaDTO> getLoadForecastingFormula(){
        
         List<RSFormulaDTO> resultList = new ArrayList<>();
         
         final DynamicQuery forecastingFormula = DynamicQueryFactoryUtil.forClass(ForecastingFormula.class);
         
        List<ForecastingFormula> list;
        try {
            list = ForecastingFormulaLocalServiceUtil.dynamicQuery(forecastingFormula);
            resultList =  getCustomisedForecastingFormula(list);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(RebateScheduleLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           return resultList;
    } 
     
    public List<RSFormulaDTO> getCustomisedForecastingFormula(List<ForecastingFormula> list){
        List<RSFormulaDTO> resultList = new ArrayList<>();
            RSFormulaDTO dto;
            try{
                if(list!=null){
                    for(ForecastingFormula formula:list){
                        dto=new RSFormulaDTO();
                        dto.setForectastingFormulaSid(formula.getForecastingFormulaSid());
                        dto.setFormulaNo(formula.getFormulaNo());
                        dto.setFormulaName(formula.getFormulaName());
                        dto.setFormula(formula.getFormula());
                        dto.setFormulaTypeId(formula.getFormulaType());
                        dto.setCreatedDate(formula.getCreatedDate());
                        dto.setModifiedDate(formula.getModifiedDate());
                        resultList.add(dto);
                    }
                }
            }catch(Exception e){
                LOGGER.error(e);
            }
        return resultList;
    }
    
    public void saveToImtdFormulaRsDetails(ImtdRsDetailsFrDTO formula,int imtdRsdSid,int rsdFrSid,int rsdSid,int itemSid) throws PortalException{
        
        
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        try {
            if(tempTableFormulaValidation(itemSid,formula.getFormulaId()) == ConstantsUtils.ZERO_INT){
                ImtdRsDetailsFr bean = ImtdRsDetailsFrLocalServiceUtil.createImtdRsDetailsFr(0);
                bean.setImtdRsDetailsSid(formula.getImtdRsDetailsSid());
                bean.setRsDetailsFrSid(formula.getRsDetailsFrSid());
                bean.setRsDetailsSid(formula.getRsDetailsSid());
                bean.setItemMasterSid(itemSid);
                bean.setOperation("A");
                bean.setFormulaMethodId(formula.getFormulaMethodId());
                bean.setFormulaId(formula.getFormulaId());
                bean.setUsersId(userId);
                bean.setSessionId(sessionId);
                bean.setImtdCreatedDate(new Date());
                ImtdRsDetailsFrLocalServiceUtil.addImtdRsDetailsFr(bean);
             }else{
                 final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                        .forClass(ImtdRsDetailsFr.class);
                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID,itemSid));
                ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORMULA_ID,formula.getFormulaId()));
                ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.USERS_SID, userId));
                ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SESSION_ID, sessionId));
                List<ImtdRsDetailsFr> list = ImtdRsDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
                ImtdRsDetailsFr bean = ImtdRsDetailsFrLocalServiceUtil.getImtdRsDetailsFr(list.get(0).getImtdRsDetailsFrSid());
                bean.setImtdRsDetailsFrSid(list.get(0).getImtdRsDetailsFrSid());
                bean.setImtdRsDetailsSid(list.get(0).getImtdRsDetailsSid());
                bean.setRsDetailsFrSid(list.get(0).getRsDetailsFrSid());
                bean.setRsDetailsSid(list.get(0).getRsDetailsSid());
                bean.setItemMasterSid(itemSid);
                bean.setFormulaId(formula.getFormulaId());
                bean.setOperation("U");
                bean.setFormulaMethodId(formula.getFormulaMethodId());
                bean.setUsersId(userId);
                bean.setSessionId(sessionId);
                bean.setImtdCreatedDate(new Date());
                ImtdRsDetailsFrLocalServiceUtil.updateImtdRsDetailsFr(bean);
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }
    
    
    
    public ImtdRsDetailsFrDTO addToSelectedFormulaList(RSFormulaDTO formula,int imtdRsdSid,int rsdFrSid,int rsdSid,int itemSid,String itemId){
        ImtdRsDetailsFrDTO selectedDto = new ImtdRsDetailsFrDTO();
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
            query.add(RestrictionsFactoryUtil.ilike("formulaDesc",formula.getFormulaName()));
            List<FormulaDetailsMaster> list= FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
            if(list!=null && !list.isEmpty()){
                selectedDto.setFormulaId(Integer.valueOf(list.get(0).getFormulaId()));
                selectedDto.setFormulaName(list.get(0).getFormulaDesc());
            }
            selectedDto.setImtdRsDetailsSid(imtdRsdSid);
            selectedDto.setRsDetailsFrSid(rsdFrSid);
            selectedDto.setRsDetailsSid(rsdSid);
            selectedDto.setItemMasterSid(itemSid);
            
           
        } catch (SystemException ex) {
            
           LOGGER.error(ex);
        }
        return selectedDto;
    }
    
    /**
     * To get the result for lazy load
     * @param itemId
     * @return
     * @throws SystemException
     */
    public int tempTableFormulaValidation(final int itemSid,int formulaId) throws SystemException{
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
       
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(ImtdRsDetailsFr.class);
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID,itemSid));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORMULA_ID,formulaId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.USERS_SID, userId));
        ifpDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.SESSION_ID, sessionId));
        int value = Integer.parseInt(String.valueOf(ImtdRsDetailsFrLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery)));
      
        return value;
    }
    
    
    public int getImtdRsDetailsFrCount(int itemMasterId){
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID,itemMasterId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.INBOUND_STATUS_D));
                     
            
            int count =(int)ImtdRsDetailsFrLocalServiceUtil.dynamicQueryCount(ifpDynamicQuery);
            return count;
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(RebateScheduleLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
     public List<ImtdRsDetailsFrDTO> getImtdRsDetailsFr(int itemMasterId,int start,int offset){
          List<ImtdRsDetailsFrDTO> resultList = new ArrayList<>();
        try {
           
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID,itemMasterId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.INBOUND_STATUS_D));
            ifpDynamicQuery.setLimit(start, offset);
            List<ImtdRsDetailsFr> list= ImtdRsDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
            
            resultList =getCustomisedImtdRsDetailsFr(list);
            
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        
        return resultList;
    }
     
    public List<ImtdRsDetailsFrDTO> getLoadImtdRsDetailsFr(int itemMasterId){
          List<ImtdRsDetailsFrDTO> resultList = new ArrayList<>();
        try {
           
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID,itemMasterId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.INBOUND_STATUS_D));
          List<ImtdRsDetailsFr> list= ImtdRsDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
            
            resultList =getCustomisedImtdRsDetailsFr(list);
            
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        
        return resultList;
    } 
     
     public List<Integer> getImtdFormulaDescList(int itemMasterId){
        List<Integer> list = new ArrayList<>();
        try {
           
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            
            final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
            
            final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID,itemMasterId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.INBOUND_STATUS_D));
            final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
            projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.FORMULA_ID));
            ifpDynamicQuery.setProjection(projectionList);
            
            list= ImtdRsDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
            
            
            
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        
        return list;
    } 
     
     public List<ImtdRsDetailsFrDTO> getCustomisedImtdRsDetailsFr(List<ImtdRsDetailsFr> list){
         List<ImtdRsDetailsFrDTO> resultList = new ArrayList<>();
         ImtdRsDetailsFrDTO dto;
         for(ImtdRsDetailsFr bean : list){
             try {
                 dto=new ImtdRsDetailsFrDTO();
                 dto.setImtdRsDetailsFrSid(bean.getImtdRsDetailsFrSid());
                 dto.setImtdRsDetailsSid(bean.getImtdRsDetailsSid());
                 dto.setItemMasterSid(bean.getItemMasterSid());
                 dto.setFormulaMethodId(bean.getFormulaMethodId());
                 dto.setRsDetailsSid(bean.getRsDetailsSid());
                 dto.setRsDetailsFrSid(bean.getRsDetailsFrSid());
                 dto.setFormulaId(bean.getFormulaId());
                 
                                
                  DynamicQuery query = DynamicQueryFactoryUtil.forClass(FormulaDetailsMaster.class);
                  query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.FORMULA_ID,bean.getFormulaId()));
                  
                  List<FormulaDetailsMaster> formulaList= FormulaDetailsMasterLocalServiceUtil.dynamicQuery(query);
                  if(formulaList!=null && !formulaList.isEmpty()){
                        dto.setFormulaName(formulaList.get(0).getFormulaDesc());
                  }
                 resultList.add(dto);
             } catch (SystemException ex) {
                 LOGGER.error(ex);
             }
         }
         return resultList;
     }
     
     public void removeImtdRsDetailsFr(ImtdRsDetailsFrDTO dto){
        try {
            ImtdRsDetailsFr bean =ImtdRsDetailsFrLocalServiceUtil.getImtdRsDetailsFr(dto.getImtdRsDetailsFrSid());
            
            bean.setOperation(ConstantsUtils.INBOUND_STATUS_D);
            
            ImtdRsDetailsFrLocalServiceUtil.updateImtdRsDetailsFr(bean);
        
        } catch (PortalException ex) {
            LOGGER.error(ex);
        
        } catch (SystemException ex) {
            LOGGER.error(ex);
        
        }
         
     }
     
     public void removeAllItemsFormula(int itemSid){
           try {
         final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
         final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
         final DynamicQuery ifpDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ImtdRsDetailsFr.class);
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.ITEM_MASTER_SID,itemSid));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USERS_SID, userId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
            ifpDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.OPERATION, ConstantsUtils.INBOUND_STATUS_D));
      
            List<ImtdRsDetailsFr> list =ImtdRsDetailsFrLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
                        for(ImtdRsDetailsFr bean:list){
                            bean.setOperation(ConstantsUtils.INBOUND_STATUS_D);
                            ImtdRsDetailsFrLocalServiceUtil.updateImtdRsDetailsFr(bean);
                        } 
           } catch (SystemException ex) {
           LOGGER.error(ex);
        }
     }
     
     public void loadFormulaToImtdRsdFr(int rsSysId){
        try {
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
                final String createdDate = String.valueOf(sessionDTO.getSessionDate());
                DynamicQuery query = DynamicQueryFactoryUtil.forClass(RsDetails.class);
                query.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_MODEL_SID, rsSysId));
                query.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
                List<RsDetails> list = RsDetailsLocalServiceUtil.dynamicQuery(query);
        
                for(RsDetails bean :list){
                    ImtdRsDetailsLocalServiceUtil.insertFormulaToRsdFrImtd(bean.getRsDetailsSid(), userId, sessionId, createdDate);
                }
                
                
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        
     }
     
     public void addAllFormulas(int itemSid){
                final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
                final String createdDate = String.valueOf(sessionDTO.getSessionDate());
                ImtdRsDetailsLocalServiceUtil.addAllFormulaToRsdFrImtd(itemSid, userId, sessionId, createdDate);
        }
     
     public void removeAllFormulas(int itemSid){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
                final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
                final String createdDate = String.valueOf(sessionDTO.getSessionDate());        
        ImtdRsDetailsLocalServiceUtil.remaoveAllFormulaToRsdFrImtd(itemSid, userId, sessionId, createdDate);
     }
     
     /**
      * Count method for rs parent lookup
      * @param rebateSchForm
      * @param fieldFlag
      * @param criteria
      * @return
      * @throws SystemException
      * @throws Exception 
      */
      public int RSLookUpSearchCount(final ErrorfulFieldGroup rebateSchForm,final boolean fieldFlag,final Set<Filter> filterSet)
            throws SystemException {
        LOGGER.debug("In getSearchCount P1:rebateScheduleForm");
        Map<String, Object> parameters = new HashMap<>();
        String rebateSchId;
        String rebateSchNo;
        String rebateSchName;
        String rebateSchStatus;
        String rebateSchType;
        String rebateProType=ConstantsUtils.ZERO;
        String itemId;
        String itemNo;
        String itemName;
        int count =0;
        final DynamicQuery rebateSchQuery = DynamicQueryFactoryUtil
                .forClass(RsModel.class);

        rebateSchQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue() == null || StringUtils.isEmpty(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue().toString())) {
            rebateSchId = StringUtils.EMPTY;
           
        } else {
            rebateSchId = rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ID)
                    .getValue().toString().trim();

        }
        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NO).getValue() == null || StringUtils.isEmpty(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NO).getValue().toString())) {

            rebateSchNo = StringUtils.EMPTY;
           
        } else {
            rebateSchNo = rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NO)
                    .getValue().toString().trim();

        }
        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue() == null || StringUtils.isEmpty(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue().toString())) {
            rebateSchName = StringUtils.EMPTY;
            
        } else {
            rebateSchName = rebateSchForm
                    .getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue().toString().trim();

        }
        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_STATUS).getValue() == null) {

            rebateSchStatus = ConstantsUtils.ZERO;
            
        } else {

            rebateSchStatus = rebateSchForm
                    .getField(ConstantsUtils.REBATE_SCHEDULE_STATUS).getValue().toString();
        }
        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_TYPE).getValue() == null) {
            rebateSchType = ConstantsUtils.ZERO;
        } else {
            rebateSchType = rebateSchForm
                    .getField(ConstantsUtils.REBATE_SCHEDULE_TYPE).getValue().toString();

        }
        if (StringUtils.isNotBlank(rebateSchId)) {
            rebateSchId = CommonUtil.buildSearchCriteria(rebateSchId);
            rebateSchQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.RS_ID, rebateSchId));
        }
        if (StringUtils.isNotBlank(rebateSchNo)) {
            rebateSchNo = CommonUtil.buildSearchCriteria(rebateSchNo);
            rebateSchQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.RS_NO, rebateSchNo));
        }
        if (StringUtils.isNotBlank(rebateSchName)) {
            rebateSchName = CommonUtil.buildSearchCriteria(rebateSchName);
            rebateSchQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.RS_NAME, rebateSchName));
        }

        if (StringUtils.isNotBlank(rebateSchStatus)&&!rebateSchStatus.equals(ConstantsUtils.ZERO)) {       
                    rebateSchStatus=String.valueOf(CommonUtils.getHelperSID(rebateSchStatus));
                    rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_STATUS, Integer.valueOf(rebateSchStatus)));
        }
        if (StringUtils.isNotBlank(rebateSchType)&&!rebateSchType.equals(ConstantsUtils.ZERO)) {
            
            List<HelperDTO> list = helperListUtil.getListNameMap().get("RS_TYPE");
            if (list!=null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDescription().equals(rebateSchType)) {
                        int ik = list.get(i).getId();
                        rebateSchType = String.valueOf(ik);
                        rebateSchQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.RS_TYPE, ik));
                    }
                }
            }
        }
        if(fieldFlag){
        if (rebateSchForm.getField(ConstantsUtils.ITEMID).getValue() == null) {
        
            itemId = StringUtils.EMPTY;
        } else {
            itemId = rebateSchForm.getField(ConstantsUtils.ITEMID)
                    .getValue().toString();

        }
        if (rebateSchForm.getField(ConstantsUtils.ITEM_NO).getValue() == null) {
        
            itemNo = StringUtils.EMPTY;
        } else {
            itemNo = rebateSchForm.getField(ConstantsUtils.ITEM_NO)
                    .getValue().toString();

        }
        if (rebateSchForm.getField(ConstantsUtils.ITEM_NAME).getValue() == null) {
            itemName = StringUtils.EMPTY;
        } else {
            itemName = rebateSchForm
                    .getField(ConstantsUtils.ITEM_NAME).getValue().toString();
        }
        } else{
             itemId = StringUtils.EMPTY;
             itemNo = StringUtils.EMPTY;
             itemName = StringUtils.EMPTY;
        }
        if (StringUtils.isNotEmpty(itemId)||StringUtils.isNotEmpty(itemNo) || StringUtils.isNotEmpty(itemName)) {
            
            if (StringUtils.isNotBlank(itemId)) {
                itemId = CommonUtil.buildSearchCriteria(itemId);
            }

            if (StringUtils.isNotBlank(itemNo)) {
                itemNo = CommonUtil.buildSearchCriteria(itemNo);
            }
            
            if (StringUtils.isNotBlank(itemName)) {
                itemName = CommonUtil.buildSearchCriteria(itemName);
            }
        }
      
         if (filterSet != null ) {
             RsUtils.loadRsColumnName();
             for (Container.Filter filter : filterSet) {
              if(filter instanceof SimpleStringFilter){   
                 SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                 String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                 String dbColumnName ;
                  dbColumnName = RsUtils.getRsDBColumnName(stringFilter.getPropertyId().toString());
                 if (ConstantsUtils.REBATE_SCHEDULE_STATUS.equals(stringFilter.getPropertyId())) {
                     rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_STATUS, Integer.valueOf(stringFilter.getFilterString())));
                     parameters.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                 } else if (ConstantsUtils.REBATE_SCHEDULE_TYPE.equals(stringFilter.getPropertyId())) {
                     rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_TYPE, Integer.valueOf(stringFilter.getFilterString())));
                     parameters.put(stringFilter.getPropertyId().toString(),  stringFilter.getFilterString());
                 } else if (ConstantsUtils.REBATE_PROGRAM_TYPE.equals(stringFilter.getPropertyId())) {
                     rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.REBATE_PROGRAM_TYPE,Integer.valueOf(stringFilter.getFilterString())));
                     parameters.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                 }else if(ConstantsUtils.RS_CATEGORY.equals(stringFilter.getPropertyId())){
                         parameters.put(stringFilter.getPropertyId().toString(),stringFilter.getFilterString());
                 }else if(ConstantsUtils.RS_DESIGNATION.equals(stringFilter.getPropertyId())){
                        parameters.put(stringFilter.getPropertyId().toString(),stringFilter.getFilterString());
                 } else {
                     rebateSchQuery.add(RestrictionsFactoryUtil.like(dbColumnName, filterString));
                     parameters.put(stringFilter.getPropertyId().toString(), filterString);
                 }
             
              }else if (filter instanceof Between) { 
                
                 SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                    Between betweenFilter = (Between) filter; 
                    Date startValue = (Date) betweenFilter.getStartValue(); 
                    Date endValue = (Date) betweenFilter.getEndValue(); 
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, String.valueOf(dateFormat.format(startValue)));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, String.valueOf(dateFormat.format(endValue))); 
                }
             
             }
         }
        
        if (StringUtils.isBlank(rebateSchId) && StringUtils.isBlank(rebateSchNo) && StringUtils.isBlank(rebateSchName)
                && StringUtils.isBlank(rebateSchStatus) && StringUtils.isBlank(rebateSchType) 
                && StringUtils.isBlank(itemId) &&StringUtils.isBlank(itemNo) && StringUtils.isBlank(itemName)) {
            
            return count;
        }
        
      
            List resultList;
            
            resultList=RsModelLocalServiceUtil.getParentRsList(rebateSchId, rebateSchNo, rebateSchName, rebateSchStatus, rebateSchType, rebateProType,itemId,itemNo, itemName, 0, 0, null, null,parameters,true);
            if(resultList!=null){
            count=resultList.size();
            }else{
            count=0;
            }
         
        return count;
    }
      
 /**
  * returns the results of parent rs lookup search
  * @param rebateSchForm
  * @param start
  * @param end
  * @param columns
  * @param fieldFlag
  * @param criteria
  * @return
  * @throws SystemException
  * @throws Exception 
  */     
 public List<RebateScheduleSearchDTO> searchRSLookup(
            final ErrorfulFieldGroup rebateSchForm, final int start, final int end, final List<SortByColumn> columns, final boolean fieldFlag,final Set<Filter> filterSet) throws SystemException {
Map<String, Object> parameters = new HashMap<>();
        String rebateSchId;
        String rebateSchNo;
        String rebateSchName;
        String rebateSchStatus;
        String rebateSchType;
        String rebateProgType= ConstantsUtils.ZERO;
        String itemId;
        String itemNo;
        String itemName;
        String columnName;
        String dbColumnName;        
        int flag=0;
        List<RebateScheduleSearchDTO> searchList = new ArrayList<>();
        RsUtils.loadRsColumnName();

        LOGGER.debug("In searchRebateScheduleMaster P1:rebateScheduleForm P2:start=" + start + " P3:end=" + end );
        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue() == null || StringUtils.isEmpty(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ID).getValue().toString())) {
            rebateSchId = StringUtils.EMPTY;

        } else {
            rebateSchId = rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_ID)
                    .getValue().toString().trim();
        }
        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NO).getValue() == null || StringUtils.isEmpty(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NO).getValue().toString())) {
            rebateSchNo = StringUtils.EMPTY;

        } else {
            rebateSchNo = rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NO)
                    .getValue().toString().trim();
        }

        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue() == null || StringUtils.isEmpty(rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue().toString())) {
            rebateSchName = StringUtils.EMPTY;

        } else {
            rebateSchName = rebateSchForm
                    .getField(ConstantsUtils.REBATE_SCHEDULE_NAME).getValue().toString().trim();
        }
        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_STATUS).getValue() == null) {
            rebateSchStatus = ConstantsUtils.ZERO;
        } else {
            rebateSchStatus = rebateSchForm
                    .getField(ConstantsUtils.REBATE_SCHEDULE_STATUS).getValue().toString();

        }
        if (rebateSchForm.getField(ConstantsUtils.REBATE_SCHEDULE_TYPE).getValue() == null) {
            rebateSchType = ConstantsUtils.ZERO;

        } else {
            rebateSchType = rebateSchForm
                    .getField(ConstantsUtils.REBATE_SCHEDULE_TYPE).getValue().toString();
        }
       if (fieldFlag) {
           
        if (rebateSchForm.getField(ConstantsUtils.ITEMID).getValue() == null) {

            itemId = StringUtils.EMPTY;
            
        }  else {
            itemId = rebateSchForm.getField(ConstantsUtils.ITEMID)
                    .getValue().toString();

        }  
        if (rebateSchForm.getField(ConstantsUtils.ITEM_NO).getValue() == null) {

            itemNo = StringUtils.EMPTY;
            
        } else {
            itemNo = rebateSchForm.getField(ConstantsUtils.ITEM_NO)
                    .getValue().toString();

        }
        if (rebateSchForm.getField(ConstantsUtils.ITEM_NAME).getValue() == null) {
            itemName = StringUtils.EMPTY;
            
        } else {
            itemName = rebateSchForm
                    .getField(ConstantsUtils.ITEM_NAME).getValue().toString();

        }
    } else {
           itemId = StringUtils.EMPTY;
        itemNo = StringUtils.EMPTY;
        itemName = StringUtils.EMPTY;
    }
        
        final DynamicQuery rebateSchQuery = DynamicQueryFactoryUtil
                .forClass(RsModel.class);

        rebateSchQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.eq(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D)));

        if (StringUtils.isNotBlank(rebateSchId)) {
            rebateSchId = CommonUtil.buildSearchCriteria(rebateSchId);
            rebateSchQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.RS_ID, rebateSchId));
        }
        if (StringUtils.isNotBlank(rebateSchNo)) {
            rebateSchNo = CommonUtil.buildSearchCriteria(rebateSchNo);
            rebateSchQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.RS_NO, rebateSchNo));
        }
        if (StringUtils.isNotBlank(rebateSchName)) {
            rebateSchName = CommonUtil.buildSearchCriteria(rebateSchName);
            rebateSchQuery.add(RestrictionsFactoryUtil.like(
                    ConstantsUtils.RS_NAME, rebateSchName));
        }
        if (StringUtils.isNotBlank(rebateSchStatus)) {
            rebateSchStatus=String.valueOf(CommonUtils.getHelperSID(rebateSchStatus));
            rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_STATUS, CommonUtils.getHelperSID(rebateSchStatus)));
        }
        if (StringUtils.isNotBlank(rebateSchType)) {
               List<HelperDTO> list = helperListUtil.getListNameMap().get("RS_TYPE");
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDescription().equals(rebateSchType)) {
                        int ik = list.get(i).getId();
                        rebateSchType = String.valueOf(ik);
                        rebateSchQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.RS_TYPE, ik));
                    }
                }
            }
        }
        if (StringUtils.isNotEmpty(itemId)||StringUtils.isNotEmpty(itemNo) || StringUtils.isNotEmpty(itemName)) {
            
             if (StringUtils.isNotBlank(itemId)) {
                itemId = CommonUtil.buildSearchCriteria(itemId);
            }

            if (StringUtils.isNotBlank(itemNo)) {
                itemNo = CommonUtil.buildSearchCriteria(itemNo);
            }
            
            if (StringUtils.isNotBlank(itemName)) {
                itemName = CommonUtil.buildSearchCriteria(itemName);
            }
            flag = 1;
        }
        
        String column = ConstantsUtils.RS_ID_COLUMN;
        String orderBy = "ASC";
        
        for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
            final SortByColumn orderByColumn = (SortByColumn) iterator.next();
            columnName = orderByColumn.getName();            
            dbColumnName = RsUtils.getRsDBColumnName(columnName);              
            if(columnName == "systemId"){                
                column = "RS_MODEL_SID";
                }else if(dbColumnName=="rsNo"){
                column = ConstantsUtils.RS_NO_COLUMN;
                }else if(dbColumnName=="rsId"){
                column = ConstantsUtils.RS_ID_COLUMN;
                }else if(dbColumnName==ConstantsUtils.RS_NAME){
                    column = ConstantsUtils.RS_NAME_LIST;
                }else if(dbColumnName=="rsStatus"){
                    column = ConstantsUtils.RS_STATUS1;
                }else if(dbColumnName=="rsType"){
                    column = ConstantsUtils.R_TYPE;
                }else if(dbColumnName==ConstantsUtils.REBATE_PROGRAM_TYPE){
                    column = ConstantsUtils.RP_TYPE;
                }else if(columnName == ConstantsUtils.START_DATE){                
                    column = "RS_START_DATE";
                }else if(columnName == ConstantsUtils.END_DATE){                
                    column = "RS_END_DATE";
                }else if(columnName == ConstantsUtils.RS_DESIGNATION){                
                    column = ConstantsUtils.RS_DESIGNATION1;
                }else if(columnName == ConstantsUtils.PARENT_ID){                
                    column = ConstantsUtils.PARENT_RS_SID;
                }else if(columnName == ConstantsUtils.PARENT_NAME){                
                    column = ConstantsUtils.PARENT_RS_NAME;
                }else if(columnName == ConstantsUtils.RS_CATEGORY){
                    column = ConstantsUtils.RS_CATEGORY1;
                }
            
            if (orderByColumn.getType() == SortByColumn.Type.ASC) {
               orderBy="ASC";
            } else {
               orderBy="DESC";
            }
        }
    
           if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                 if (filter instanceof SimpleStringFilter) { 
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                  
                    if (ConstantsUtils.REBATE_SCHEDULE_STATUS.equals(stringFilter.getPropertyId())) {
                        parameters.put(stringFilter.getPropertyId().toString(),stringFilter.getFilterString());
                     }  else if (ConstantsUtils.REBATE_SCHEDULE_TYPE.equals(stringFilter.getPropertyId())) {
                         parameters.put(stringFilter.getPropertyId().toString(),stringFilter.getFilterString());
                     } else if (ConstantsUtils.REBATE_PROGRAM_TYPE.equals(stringFilter.getPropertyId())) {
                         parameters.put(stringFilter.getPropertyId().toString(),stringFilter.getFilterString());
                     }else if(ConstantsUtils.RS_CATEGORY.equals(stringFilter.getPropertyId())){
                         parameters.put(stringFilter.getPropertyId().toString(),stringFilter.getFilterString());
                     }else if(ConstantsUtils.RS_DESIGNATION.equals(stringFilter.getPropertyId())){
                         parameters.put(stringFilter.getPropertyId().toString(),stringFilter.getFilterString());
                     }else {
                         parameters.put(stringFilter.getPropertyId().toString(), filterString);                      
                     }
                    
                   }else if (filter instanceof Between) { 
                 SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                 
                    
                    Between betweenFilter = (Between) filter; 
                    Date startValue = (Date) betweenFilter.getStartValue(); 
                    Date endValue = (Date) betweenFilter.getEndValue(); 
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.FROM, dateFormat.format(startValue));
                    parameters.put(betweenFilter.getPropertyId() + ConstantsUtils.TO, dateFormat.format(endValue)); 
                }
            }
        }
        if (flag == Constants.ZERO) {
            List resultList=RsModelLocalServiceUtil.getParentRsList(rebateSchId, rebateSchNo, rebateSchName, rebateSchStatus, rebateSchType, rebateProgType,itemId,itemNo, itemName, start, end, column, orderBy,parameters,false);
            searchList = getCustomizedSearchForLookUp(resultList);
         }
        if (flag == Constants.ONE) {
            List resultList;
            resultList=RsModelLocalServiceUtil.getParentRsList(rebateSchId, rebateSchNo, rebateSchName, rebateSchStatus, rebateSchType, rebateProgType,itemId,itemNo, itemName, start, end, column, orderBy,parameters,false);
            searchList = getCustomizedSearchForLookUp(resultList);
        }
        
        
        LOGGER.debug("End of searchRebateScheduleMaster Return searchList Size is" + searchList.size());
        return searchList;

    }
  public List<RebateScheduleSearchDTO> getCustomizedSearchForLookUp(final List list) {
        LOGGER.debug("Entering getCustomizedSearchFormFromModel()");
        final List<RebateScheduleSearchDTO> searchItemList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final RebateScheduleSearchDTO searchCompanyForm = new RebateScheduleSearchDTO();

               final Object[] obj = (Object[]) list.get(i);
                searchCompanyForm.setRebateScheduleSystemId((Integer)obj[0]);
                searchCompanyForm.setSystemId(String.valueOf(String.valueOf(obj[0])));
                searchCompanyForm.setRebateScheduleId(String.valueOf(obj[1]));
                if(obj[NumericConstants.TWO]!=null){
                searchCompanyForm.setRebateScheduleNo(String.valueOf(obj[NumericConstants.TWO]));
                }
                if(obj[NumericConstants.THREE]!=null){
                searchCompanyForm.setRebateScheduleName(String.valueOf(obj[NumericConstants.THREE]));
                }
                if(obj[NumericConstants.SEVEN]!=null){
                searchCompanyForm.setRebateScheduleType(String.valueOf(obj[NumericConstants.SEVEN]));
                }
               if(obj[NumericConstants.FOUR]!=null){
                searchCompanyForm.setRebateScheduleStatus(String.valueOf(obj[NumericConstants.EIGHT]));
               }
               if(obj[NumericConstants.NINE]!=null){
                searchCompanyForm.setRebateProgramType(String.valueOf(obj[NumericConstants.NINE]));
               }
               
               if(obj[NumericConstants.TEN]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.TEN]))){
                searchCompanyForm.setRsCategory(String.valueOf(obj[NumericConstants.TEN]));
               }
               
               if(obj[NumericConstants.ELEVEN]!=null){
                   
                searchCompanyForm.setStartDate((Date)(obj[NumericConstants.ELEVEN]));
               }
               
                if(obj[NumericConstants.TWELVE]!=null){
                   
                searchCompanyForm.setEndDate((Date)(obj[NumericConstants.TWELVE]));
               }
                
                if(obj[NumericConstants.THIRTEEN]!=null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(obj[NumericConstants.THIRTEEN]))){
                searchCompanyForm.setRsDesignation( String.valueOf(obj[NumericConstants.THIRTEEN]));
                }
                
                if(obj[NumericConstants.FOURTEEN]!=null){
                searchCompanyForm.setParentId(String.valueOf(obj[NumericConstants.FOURTEEN]));
                }
                
                if(obj[NumericConstants.FIFTEEN]!=null){
                searchCompanyForm.setParentName(String.valueOf(obj[NumericConstants.FIFTEEN]));
                }
                if(obj[NumericConstants.SIXTEEN]!=null){
                searchCompanyForm.setRecordLockStatus((Boolean)obj[NumericConstants.SIXTEEN]);
                } 
               
                searchItemList.add(searchCompanyForm);

            }
        }
        LOGGER.debug("End of getCustomizedSearchFormFromModel method");
        return searchItemList;
    }
  
    public String getRSQuery(Map<String,String> searchCriteria, int start, int offset, String column, String orderBy, Map<String, Object> parameters, boolean isCount) {
        
        if (orderBy == null) {
            orderBy = "ASC";
        }
        String sql = CustomSQLUtil.get("com.rsModel.searchRSModel.tempDeclareRsModel");
        sql = sql.replace("?ITEM_NO", StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.ITEM_NO)) && !ConstantsUtils.NULL.equals(searchCriteria.get(ConstantsUtils.ITEM_NO)) ? searchCriteria.get(ConstantsUtils.ITEM_NO).contains(GlobalConstants.getSlashPercent()) ?  searchCriteria.get(ConstantsUtils.ITEM_NO) + ConstantsUtils.PERCENCTAGE: searchCriteria.get(ConstantsUtils.ITEM_NO) : ConstantsUtils.PERCENCTAGE);
        sql = sql.replace("?ITEM_NAME", StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.ITEM_NAME)) && !ConstantsUtils.NULL.equals(searchCriteria.get(ConstantsUtils.ITEM_NAME)) ? searchCriteria.get(ConstantsUtils.ITEM_NAME).contains(GlobalConstants.getSlashPercent()) ? searchCriteria.get(ConstantsUtils.ITEM_NAME) + ConstantsUtils.PERCENCTAGE : searchCriteria.get(ConstantsUtils.ITEM_NAME)  : ConstantsUtils.PERCENCTAGE);
        if (isCount) {
            sql += "SELECT COUNT(DISTINCT rs.RS_MODEL_SID) ";
        } else {
            sql +=  CustomSQLUtil.get("com.rsModel.searchRSModel.selectQuery");
        } 
            sql +=  CustomSQLUtil.get("com.rsModel.searchRSModel.joinQuery");
                        
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.REBATE_ID)) && StringUtils.isNotEmpty(searchCriteria.get(ConstantsUtils.REBATE_ID))) {
            if (searchCriteria.get(ConstantsUtils.REBATE_ID).contains(GlobalConstants.getEscapeSlash())) {
                sql += " AND rs.RS_ID LIKE '" + searchCriteria.get(ConstantsUtils.REBATE_ID) + ConstantsUtils.SINGLE_QUOTE + " " + GlobalConstants.getEscapeSlash();
            } else {
                sql += " AND rs.RS_ID LIKE '" + searchCriteria.get(ConstantsUtils.REBATE_ID) + ConstantsUtils.SINGLE_QUOTE;
            }
        }
        
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.REBATE_NO)) && StringUtils.isNotEmpty(searchCriteria.get(ConstantsUtils.REBATE_NO))) {
            if (searchCriteria.get(ConstantsUtils.REBATE_NO).contains(GlobalConstants.getEscapeSlash())) {
                sql += " AND rs.RS_NO LIKE '" + searchCriteria.get(ConstantsUtils.REBATE_NO) + ConstantsUtils.SINGLE_QUOTE + " " + GlobalConstants.getEscapeSlash();
            } else {
                sql += " AND rs.RS_NO LIKE '" + searchCriteria.get(ConstantsUtils.REBATE_NO) + ConstantsUtils.SINGLE_QUOTE;
            }
        }
        if (StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.REBATE_NAME)) && StringUtils.isNotEmpty(searchCriteria.get(ConstantsUtils.REBATE_NAME))) {
            if (searchCriteria.get(ConstantsUtils.REBATE_NAME).contains(GlobalConstants.getEscapeSlash())) {
                sql += " AND rs.RS_NAME LIKE '" + searchCriteria.get(ConstantsUtils.REBATE_NAME) + ConstantsUtils.SINGLE_QUOTE + " " + GlobalConstants.getEscapeSlash();
            } else {
                sql += " AND rs.RS_NAME LIKE '" + searchCriteria.get(ConstantsUtils.REBATE_NAME) + ConstantsUtils.SINGLE_QUOTE;
            }
        }
        if (searchCriteria.get(ConstantsUtils.REBATE_STATUS) != null && Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_STATUS)) != 0) {
            sql += " AND rs.RS_STATUS = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_STATUS));

        }
        if (searchCriteria.get(ConstantsUtils.REBATE_TYPE) != null && !searchCriteria.get(ConstantsUtils.REBATE_TYPE).equals(ConstantsUtils.ZERO)) {
            sql += " AND rs.RS_TYPE = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_TYPE));

        }
        if (searchCriteria.get(ConstantsUtils.REBATE_PRO_TYPE) != null && ! searchCriteria.get(ConstantsUtils.REBATE_PRO_TYPE).equals(ConstantsUtils.ZERO)) {
            sql += " AND rs.REBATE_PROGRAM_TYPE = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.REBATE_PRO_TYPE));
        }
        
        if (searchCriteria.get(ConstantsUtils.RS_PROGRAM_TYPE) != null && !searchCriteria.get(ConstantsUtils.RS_PROGRAM_TYPE).equals(ConstantsUtils.ZERO)) {
            sql += " AND rs.REBATE_PROGRAM_TYPE = " + Integer.valueOf(searchCriteria.get(ConstantsUtils.RS_PROGRAM_TYPE));
        }
                
        if (searchCriteria.get(ConstantsUtils.REBATE_CATEGORY) != null && !searchCriteria.get(ConstantsUtils.REBATE_CATEGORY).equals(ConstantsUtils.ZERO)) {
            sql += " AND rs.RS_CATEGORY LIKE '" + searchCriteria.get(ConstantsUtils.REBATE_CATEGORY) + ConstantsUtils.SINGLE_QUOTE;
        }

        if (searchCriteria.get(ConstantsUtils.RS_ALAIS_ID) != null && StringUtils.isNotBlank(searchCriteria.get(ConstantsUtils.RS_ALAIS_ID))) {
            if (searchCriteria.get(ConstantsUtils.RS_ALAIS_ID).contains(GlobalConstants.getEscapeSlash())) {
                sql += " AND rs.RS_ALIAS LIKE '" + searchCriteria.get(ConstantsUtils.RS_ALAIS_ID) + ConstantsUtils.SINGLE_QUOTE + " " + GlobalConstants.getEscapeSlash();
            } else {
                sql += " AND rs.RS_ALIAS LIKE '" + searchCriteria.get(ConstantsUtils.RS_ALAIS_ID) + ConstantsUtils.SINGLE_QUOTE;
            }
        } else {
            sql += " AND (rs.RS_ALIAS LIKE '%' OR rs.RS_ALIAS IS NULL) ";
        }
        
         if (searchCriteria.get(ConstantsUtils.REBATE_FREQ) != null && !searchCriteria.get(ConstantsUtils.REBATE_FREQ).equals(ConstantsUtils.ZERO)) {
            sql += " AND rs.REBATE_FREQUENCY LIKE '" + searchCriteria.get(ConstantsUtils.REBATE_FREQ) + ConstantsUtils.SINGLE_QUOTE;
        }

        if (searchCriteria.get(ConstantsUtils.CALCULATION_TYPE) != null && !searchCriteria.get(ConstantsUtils.CALCULATION_TYPE).equals(ConstantsUtils.ZERO)) {
            sql += " AND rs.CALCULATION_TYPE LIKE '" + searchCriteria.get(ConstantsUtils.CALCULATION_TYPE) + ConstantsUtils.SINGLE_QUOTE;
        }                        
        
        if (parameters.get(ConstantsUtils.REBATE_SCHEDULE_ID) != null) {
            sql += " AND rs.RS_ID like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.REBATE_SCHEDULE_ID)) + "' ";
        }

        if (parameters.get(ConstantsUtils.REBATE_SCHEDULE_NO) != null) {
            sql += " AND rs.RS_NO like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.REBATE_SCHEDULE_NO)) + "' ";
        }

        if (parameters.get(ConstantsUtils.REBATE_SCHEDULE_NAME) != null) {
            sql += " AND rs.RS_NAME like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.REBATE_SCHEDULE_NAME)) + "' ";
        }

        if (parameters.get(ConstantsUtils.REBATE_SCHEDULE_STATUS) != null) {
            sql += " AND rs.RS_STATUS like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.REBATE_SCHEDULE_STATUS)) + "' ";
        }

        if (parameters.get(ConstantsUtils.REBATE_SCHEDULE_TYPE) != null) {
            sql += " AND rs.RS_TYPE like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.REBATE_SCHEDULE_TYPE)) + "' ";
        }

        if (parameters.get(ConstantsUtils.PARENT_ID) != null) {
            sql += " AND rs.PARENT_RS_ID like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.PARENT_ID)) + "' ";
        }

        if (parameters.get(ConstantsUtils.PARENT_NAME) != null) {
            sql += " AND rs.PARENT_RS_NAME like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.PARENT_NAME)) + "' ";
        }

        if (parameters.get(ConstantsUtils.RS_CATEGORY) != null) {
            sql += " AND rs.RS_CATEGORY like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.RS_CATEGORY)) + "' ";
        }

        if (parameters.get(ConstantsUtils.RS_DESIGNATION) != null) {
            sql += " AND rs.RS_DESIGNATION like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.RS_DESIGNATION)) + "' ";
        }

        if (parameters.get(ConstantsUtils.RS_PROGRAM_TYPE) != null) {
            sql += " AND rs.REBATE_PROGRAM_TYPE like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.RS_PROGRAM_TYPE)) + "' ";
        }

        
        if (parameters.get(ConstantsUtils.RS_TRADE_CLASS) != null) {
            sql += " AND rs.RS_TRADE_CLASS like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.RS_TRADE_CLASS)) + "' ";
        }

        if (parameters.get("rebateScheduleAliasID") != null) {
            sql += " AND rs.RS_ALIAS like '";
            sql += String.valueOf(parameters.get("rebateScheduleAliasID")) + "' ";
        }

        if (parameters.get(ConstantsUtils.REBATE_FREQ) != null) {
            sql += " AND rs.REBATE_FREQUENCY like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.REBATE_FREQ)) + "' ";
        }

        if (parameters.get(ConstantsUtils.CALENDER) != null) {
            sql += " AND rs.RS_CALENDAR like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.CALENDER)) + "' ";
        }

        if (parameters.get(ConstantsUtils.CALCULATION_TYPE) != null) {
            sql += " AND rs.CALCULATION_TYPE like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.CALCULATION_TYPE)) + "' ";
        }

        if (parameters.get("calculationLevel") != null) {
            sql += " AND rs.CALCULATION_LEVEL like '";
            sql += String.valueOf(parameters.get("calculationLevel")) + "' ";
        }

        if (parameters.get(ConstantsUtils.REBATE_RULE_TYPE) != null) {
            sql += " AND rs.REBATE_RULE_TYPE like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.REBATE_RULE_TYPE)) + "' ";
        }

        if (parameters.get("rebateRuleAssociation") != null) {
            sql += " AND rs.REBATE_RULE_ASSOCIATION like '";
            sql += String.valueOf(parameters.get("rebateRuleAssociation")) + "' ";
        }

        if (parameters.get(ConstantsUtils.PAYMENT_TERMS) != null) {
            sql += " AND rs.PAYMENT_TERMS like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.PAYMENT_TERMS)) + "' ";
        }

        if (parameters.get(ConstantsUtils.PAYMENT_METHOD) != null) {
            sql += " AND rs.PAYMENT_METHOD like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.PAYMENT_METHOD)) + "' ";
        }
        
        
        if (parameters.get("paymentGracePeriod") != null) {
            sql += " AND rs.PAYMENT_GRACE_PERIOD like '";
            sql += String.valueOf(parameters.get("paymentGracePeriod")) + "' ";
        }

        if (parameters.get(ConstantsUtils.PAYMENT_FREQUENCY) != null) {
            sql += " AND rs.PAYMENT_FREQUENCY like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.PAYMENT_FREQUENCY)) + "' ";
        }

        if (parameters.get(ConstantsUtils.INTEREST_BEARING_INDICATOR) != null) {
            sql += " AND rs.INTEREST_BEARING_INDICATOR like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.INTEREST_BEARING_INDICATOR)) + "' ";
        }

        if (parameters.get(ConstantsUtils.INTEREST_BEARING_BASIS) != null) {
            sql += " AND rs.INTEREST_BEARING_BASIS like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.INTEREST_BEARING_BASIS)) + "' ";
        }

        if (parameters.get(ConstantsUtils.RS_REFERENCE_ID) != null) {
            sql += " AND rs.RS_TRANS_REF_ID like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.RS_REFERENCE_ID)) + "' ";
        }

        if (parameters.get(ConstantsUtils.RS_REFERENCE_NAME) != null) {
            sql += " AND rs.RS_TRANS_REF_NAME like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.RS_REFERENCE_NAME)) + "' ";
        }

        if (parameters.get(ConstantsUtils.UDC1) != null) {
            sql += " AND udcs.UDC1 like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.UDC1)) + "' ";
        }

        if (parameters.get(ConstantsUtils.UDC2) != null) {
            sql += " AND udcs.UDC2 like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.UDC2)) + "' ";
        }

        if (parameters.get(ConstantsUtils.UDC3) != null) {
            sql += " AND udcs.UDC3 like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.UDC3)) + "' ";
        }

        if (parameters.get(ConstantsUtils.UDC4) != null) {
            sql += " AND udcs.UDC4 like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.UDC4)) + "' ";
        }
        
        if (parameters.get(ConstantsUtils.UDC5) != null) {
            sql += " AND udcs.UDC5 like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.UDC5)) + "' ";
        }

        if (parameters.get(ConstantsUtils.UDC6) != null) {
            sql += " AND udcs.UDC6 like '";
            sql += String.valueOf(parameters.get(ConstantsUtils.UDC6)) + "' ";
        }

        
        if (parameters.get(ConstantsUtils.START_DATE_FROM) != null && parameters.get(ConstantsUtils.STATUS_DATE_TO) != null) {
            String from = String.valueOf(parameters.get(ConstantsUtils.START_DATE_FROM));
            String to = String.valueOf(parameters.get(ConstantsUtils.STATUS_DATE_TO));
            sql += " AND RS_START_DATE BETWEEN '" + from + "' AND '" + to + ConstantsUtils.SINGLE_QUOTE;
        }

        if (parameters.get(ConstantsUtils.END_DATE_FROM) != null && parameters.get(ConstantsUtils.END_DATE_TO) != null) {
            String from = String.valueOf(parameters.get(ConstantsUtils.END_DATE_FROM));
            String to = String.valueOf(parameters.get(ConstantsUtils.END_DATE_TO));
            sql += " AND RS_END_DATE BETWEEN '" + from + "' AND '" + to + ConstantsUtils.SINGLE_QUOTE;
        }

        if (!isCount) {
            sql += ConstantsUtils.ORDER_BY + column + " " + orderBy + ConstantsUtils.OFFSET_SPACE + start + ConstantsUtils.ROW_FETCH_NEXT + offset + " ROWS ONLY ";
        }
       
       
        
        return sql;
    }
  
    /**
     * 
     * @param fieldName
     * @param binder
     * @return 
     */
    private boolean checkEmptyDataFromFields(String fieldName, ErrorfulFieldGroup binder) {
        boolean isEmpty = false;        
        
        if (binder.getField(fieldName) instanceof TextField) {
            TextField textField = (TextField)binder.getField(fieldName);
            isEmpty = StringUtils.isBlank(textField.getValue()) || ConstantsUtils.NULL.equals(textField.getValue());
        }
        if (binder.getField(fieldName) instanceof ComboBox) {
            
            ComboBox comboBox = (ComboBox) binder.getField(fieldName);
            if (comboBox.getValue() instanceof com.stpl.app.util.HelperDTO) {
                isEmpty = comboBox.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((com.stpl.app.util.HelperDTO) comboBox.getValue()).getDescription());
            } else if ( comboBox.getValue() instanceof HelperDTO) {
                isEmpty = comboBox.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((HelperDTO) comboBox.getValue()).getDescription());
            } else if( comboBox.getValue() == null){
                isEmpty = true;
            }
            
        }      
        return isEmpty;
    }
    
    private String replaceForWildCardSearch(String input){
        if (StringUtils.isNotBlank(input)) {
            input = input.replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
            input = input.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        return input;
    }
    
    
    private HelperDTO getHelperDTOById(int systemId) throws PortalException, SystemException{
        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(systemId);
        return new HelperDTO(systemId,helperTable.getDescription());
    }
     private String getRuleNameById(int systemId) throws PortalException, SystemException{
         String ruleName=StringUtils.EMPTY;
         
         if(systemId!=0){
          CdrModel cdrModel = CdrModelLocalServiceUtil.getCdrModel(systemId);
         ruleName=cdrModel.getRuleName();
         }
         
       return ruleName;
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
            String rebatePlanIDFilter = StringUtils.EMPTY;
            String rebatePlanNoFilter = StringUtils.EMPTY;
            String rebatePlanNameFilter = StringUtils.EMPTY;
            int rebatePlanStatus = rebatePlanDTO.getRebatePlanStatus() != null ? rebatePlanDTO.getRebatePlanStatus().getId() : 0;
             int rebatePlanType = rebatePlanDTO.getRebatePlanType() != null ? rebatePlanDTO.getRebatePlanType().getId() : 0;
            String netSalesFormula = rebatePlanDTO.getNetSalesFormula();
            String netSalesRule = rebatePlanDTO.getNetSalesRule();
            int createdBy =0;
            int modifiedBy=0 ;
            int rebateBasedOn = rebatePlanDTO.getRebateBasedOn()!= null ? rebatePlanDTO.getRebateBasedOn().getId() : 0;
            int rangeBasedOn = rebatePlanDTO.getRangeBasedOn()!= null ? rebatePlanDTO.getRangeBasedOn().getId() : 0;
            int rebateStructure = rebatePlanDTO.getRebateStructure()!= null ? rebatePlanDTO.getRebateStructure().getId() : 0;
             Map<String, Object> filterCriteria = new HashMap<>();
             

            String column = "RP.REBATE_PLAN_TYPE";
            String orderBy = "ASC";
         
            if (columns != null) {
                for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                    final SortByColumn orderByColumn = (SortByColumn) iterator.next();                
                    column = RsUtils.getRebatePlanLookupColumnMap(orderByColumn.getName());
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
                    String filterString = stringFilter.getFilterString().replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
                    Object propertyId = stringFilter.getPropertyId();
                    switch (propertyId.toString()) {

                        case "rebatePlanId":
                            rebatePlanIDFilter = RsUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", RsUtils.PERCENCTAGE) : filterString) + RsUtils.PERCENCTAGE;

                            break;
                        case ConstantsUtils.REBATE_PLAN_NO:
                            rebatePlanNoFilter = RsUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", RsUtils.PERCENCTAGE) : filterString) + RsUtils.PERCENCTAGE;
                            break;
                        case ConstantsUtils.REBATE_PLAN_NAME:
                            rebatePlanNameFilter = RsUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", RsUtils.PERCENCTAGE) : filterString) + RsUtils.PERCENCTAGE;
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
                            netSalesFormula = RsUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", RsUtils.PERCENCTAGE) : filterString) + RsUtils.PERCENCTAGE;
                            break;
                        case "netSalesRule":
                            netSalesRule = RsUtils.PERCENCTAGE + ((filterString != null && filterString.contains("*")) ? filterString.replace("*", RsUtils.PERCENCTAGE) : filterString) + RsUtils.PERCENCTAGE;
                            break;
                        case ConstantsUtils.CREATEDBY:
                            createdBy = Integer.valueOf(filterString);
                            break;
                        case ConstantsUtils.MODIFIEDBY:
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
                        filterCriteria.put(stringFilter.getPropertyId() + ConstantsUtils.FROM, ConstantsUtils.GREATER_THAN_EQUAL + startValue + ConstantsUtils.SINGLE_QUOTE);
                    }
                    if (endValue != null) {
                        filterCriteria.put(stringFilter.getPropertyId() + ConstantsUtils.TO, ConstantsUtils.LESS_THAN_EQUAL + endValue + ConstantsUtils.SINGLE_QUOTE);
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            filterCriteria.put(stringFilter.getPropertyId().toString(), ConstantsUtils.GREATER_THAN_EQUAL  + filterString + ConstantsUtils.SINGLE_QUOTE);
                        } else {
                            filterCriteria.put(stringFilter.getPropertyId().toString(), ConstantsUtils.LESS_THAN_EQUAL + filterString + ConstantsUtils.SINGLE_QUOTE);
                        }
                    }
                }
            }
        }
            String query;
            if (isCount) {
                query = SQLUtil.getQuery("com.rsModel.rebatePlanLookUpCount");
            } else {
                query = SQLUtil.getQuery("com.rsModel.rebatePlanLookUpResults");
            }

            query = query.replace("@REBATE_PLAN_ID", StringUtils.isBlank(rebatePlanID) ? RsUtils.PERCENCTAGE : CommonUtil.buildSearchCriteria(rebatePlanID));
            query = query.replace("@REBATE_PLAN_NO", StringUtils.isBlank(rebatePlanNo) ? RsUtils.PERCENCTAGE : CommonUtil.buildSearchCriteria(rebatePlanNo));
            query = query.replace("@REBATE_PLAN_NAME", StringUtils.isBlank(rebatePlanName) ? RsUtils.PERCENCTAGE : CommonUtil.buildSearchCriteria(rebatePlanName));
            
            
            if (!rebatePlanIDFilter.equals(StringUtils.EMPTY)) {
                query += "AND RP.REBATE_PLAN_ID LIKE '" + rebatePlanIDFilter + "'\n";
            }
             if (!rebatePlanNoFilter.equals(StringUtils.EMPTY)) {
                query += "AND RP.REBATE_PLAN_NO LIKE '" + rebatePlanNoFilter + "'\n";
            }
             if (!rebatePlanNameFilter.equals(StringUtils.EMPTY)) {
                query += "AND RP.REBATE_PLAN_NAME LIKE '" + rebatePlanNameFilter + "'\n";
            }
      
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

            if (createdBy!= 0) {
                query += "AND  RP.CREATED_BY LIKE '" + createdBy + "'\n";
            }
            if (modifiedBy!= 0) {
                query += "AND  RP.MODIFIED_BY LIKE '" + modifiedBy + "'\n";
            }
            if (filterCriteria.get(ConstantsUtils.CREATEDDATE + ConstantsUtils.FROM) != null) {
                query += " AND RP.CREATED_DATE ";
                query += String.valueOf(filterCriteria.get(ConstantsUtils.CREATEDDATE + ConstantsUtils.FROM));
            }
            if (filterCriteria.get(ConstantsUtils.CREATEDDATE + ConstantsUtils.TO) != null) {
                query += " AND  RP.CREATED_DATE ";
                query += String.valueOf(filterCriteria.get(ConstantsUtils.CREATEDDATE + ConstantsUtils.TO));
            }
            if (filterCriteria.get(ConstantsUtils.CREATEDDATE) != null) {
                query += " AND  RP.CREATED_DATE  ";
                query += String.valueOf(filterCriteria.get(ConstantsUtils.CREATEDDATE));
            }
            if (filterCriteria.get(ConstantsUtils.MODIFIEDDATE + ConstantsUtils.FROM) != null) {
                query += " AND  RP.MODIFIED_DATE   ";
                query += String.valueOf(filterCriteria.get(ConstantsUtils.MODIFIEDDATE + ConstantsUtils.FROM));
            }
            if (filterCriteria.get(ConstantsUtils.MODIFIEDDATE + ConstantsUtils.TO) != null) {
                query += " AND  RP.MODIFIED_DATE  ";
                query += String.valueOf(filterCriteria.get(ConstantsUtils.MODIFIEDDATE + ConstantsUtils.TO));
            }
            if (filterCriteria.get(ConstantsUtils.MODIFIEDDATE) != null) {
                query += " AND  RP.MODIFIED_DATE  ";
                query += String.valueOf(filterCriteria.get(ConstantsUtils.MODIFIEDDATE));
            }
            if (!isCount) {
            query += "ORDER BY " + column + " " + orderBy + " \n"
                        + "OFFSET " + start + ConstantsUtils.ROW_FETCH_NEXT + offset + " ROWS ONLY";
            }

            LOGGER.debug("Sec REbate  Query------>"+query);
            List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
            if (isCount) {
                int count = (Integer) resultList.get(0);
                return count;
            } else {
                return convertRebatePlanList(resultList);
            }
        
    }    
    
    /**
     * Converts the list of objects to list of RebatePlanDTO.
     * @param list List<Object[]>
     * @return List<RebatePlanDTO>
     */
    private List<RebatePlanDTO> convertRebatePlanList(final List<Object[]> list){

        List<RebatePlanDTO> resultList = new ArrayList<>();
        for(Object[] object : list) {
            try {
                Map<Integer, String> userMap= StplSecurity.getUserName();
                RebatePlanDTO rebatePlanDTO = new RebatePlanDTO();
                rebatePlanDTO.setRebatePlanSystemId(String.valueOf(object[0]));
                rebatePlanDTO.setRebatePlanId(object[1]==null ? StringUtils.EMPTY:String.valueOf(object[1]));
                rebatePlanDTO.setRebatePlanNo(object[NumericConstants.TWO]==null ? StringUtils.EMPTY:String.valueOf(object[NumericConstants.TWO]));
                rebatePlanDTO.setRebatePlanName(object[NumericConstants.THREE]==null ? StringUtils.EMPTY:String.valueOf(object[NumericConstants.THREE]));
                rebatePlanDTO.setRebatePlanStatus(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(object[NumericConstants.FOUR]))));
                rebatePlanDTO.setRebatePlanType(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(object[NumericConstants.FIVE]))));
                rebatePlanDTO.setRebateStructure(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(object[NumericConstants.SIX]))));
                rebatePlanDTO.setRangeBasedOn(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(object[NumericConstants.SEVEN]))));
                rebatePlanDTO.setNetSalesFormula(object[NumericConstants.EIGHT]==null ? StringUtils.EMPTY:String.valueOf(object[NumericConstants.EIGHT]));
                rebatePlanDTO.setNetSalesRule(object[NumericConstants.NINE]==null ? StringUtils.EMPTY:String.valueOf(object[NumericConstants.NINE]));
                rebatePlanDTO.setRebateBasedOn(helperListUtil.getIdHelperDTOMap().get(Integer.valueOf(String.valueOf(object[NumericConstants.TEN]))));
                rebatePlanDTO.setCreatedDateString(formatDate(convertNullToEmpty(String.valueOf(object[NumericConstants.ELEVEN]))));
                rebatePlanDTO.setCreatedDate(parsetDate(convertNullToEmpty(String.valueOf(object[NumericConstants.ELEVEN]))));
                rebatePlanDTO.setModifiedDateString(formatDate(convertNullToEmpty(String.valueOf(object[NumericConstants.THIRTEEN]))));
                rebatePlanDTO.setModifiedDate(parsetDate(convertNullToEmpty(String.valueOf(object[NumericConstants.THIRTEEN]))));
                rebatePlanDTO.setCreatedBy(userMap.get((int)object[NumericConstants.TWELVE])==null ? StringUtils.EMPTY : userMap.get((int)object[NumericConstants.TWELVE]));
                rebatePlanDTO.setModifiedBy(userMap.get((int)object[NumericConstants.FOURTEEN])==null ? StringUtils.EMPTY : userMap.get((int)object[NumericConstants.FOURTEEN]));
                resultList.add(rebatePlanDTO);
            } catch (Exception ex) {
                LOGGER.error(ex);
               LOGGER.error("Error in RP look up Customization"+ex);
            }
        }
        return resultList;
    }
    
    /**
     *
     * @param rsFormulaDTO
     * @param start
     * @param offset
     * @param isCount
     * @return
     */
    public Object loadRSFormula(final RSFormulaDTO rsFormulaDTO, final int start, final int offset, final boolean isCount, final Set<Container.Filter> filterSet, final List<SortByColumn> columns) {
        String query;
        
        if (isCount) {
            query = CustomSQLUtil.get("com.rsModel.getFormulaCountForRS");
        } else {
            query = CustomSQLUtil.get("com.rsModel.getFormulaForRS");
        }

        query = query.replace("@FORMULA_ID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || ConstantsUtils.NULL.equals(rsFormulaDTO.getFormulaID())
                ? RsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaID()));
        query = query.replace("@FORMULA_NO", StringUtils.isBlank(rsFormulaDTO.getFormulaNo()) || ConstantsUtils.NULL.equals(rsFormulaDTO.getFormulaNo())
                ? RsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaNo()));
        query = query.replace("@FORMULA_NAME", StringUtils.isBlank(rsFormulaDTO.getFormulaName()) || ConstantsUtils.NULL.equals(rsFormulaDTO.getFormulaName())
                ? RsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaName()));
        query = query.replace("@FORMULA_TYPE", rsFormulaDTO.getFormulaType()== null || (Constants.SELECT_ONE).equals(rsFormulaDTO.getFormulaType()) ? RsUtils.PERCENCTAGE : String.valueOf(rsFormulaDTO.getFormulaType().getId()));
        
        query = getRsFormulaFilterQuery(filterSet, query);

        if (!isCount) {
        query = getRsFormulaOrderQuery(query, columns, start, offset);
        }

        query = new String(query.toString().replaceAll("WHERE AND", " WHERE "));
        query = new String(query.toString().endsWith(ConstantsUtils.WHERE) ? query.toString().replace(ConstantsUtils.WHERE, " ") : query);
        
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return convertFormulaList(resultList);
        }

    }
    
    private String getRsFormulaFilterQuery(final Set<Container.Filter> filterSet, String string) {
        if (rsFormulaDbMap.isEmpty()) {
        loadRsformulaMap();
        }
              
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    string += (ConstantsUtils.AND) + (rsFormulaDbMap.get(stringFilter.getPropertyId().toString())) + (ConstantsUtils.LIKE_QUOTE) + (CommonUtil.buildFilterCriteria(stringFilter.getFilterString())) + ("'");
                } 
                    else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        string+=(ConstantsUtils.AND)+(rsFormulaDbMap.get(stringFilter.getPropertyId().toString()))+(ConstantsUtils.GREATER_THAN_EQUAL )+(startValue)+("' ");
                    }
                    if (endValue != null) {
                        string+=(ConstantsUtils.AND)+(rsFormulaDbMap.get(stringFilter.getPropertyId().toString()))+(ConstantsUtils.LESS_THAN_EQUAL)+(endValue)+("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            string+=(ConstantsUtils.AND)+(rsFormulaDbMap.get(String.valueOf(stringFilter.getPropertyId())))+(ConstantsUtils.GREATER_THAN_EQUAL )+(filterString)+("' ");
                        } else {
                            string+=(ConstantsUtils.AND)+(rsFormulaDbMap.get(String.valueOf(stringFilter.getPropertyId())))+(ConstantsUtils.LESS_THAN_EQUAL)+(filterString)+("' ");
                        }
    }
    }
            } 
        } 

        return string;
    }
    
    private void loadRsformulaMap() {
        rsFormulaDbMap.clear();
        rsFormulaDbMap.put("formulaType", "FORMULA_TYPE");
        rsFormulaDbMap.put("formulaID", "FORECASTING_FORMULA_SID");
        rsFormulaDbMap.put(ConstantsUtils.FORMULA_NO, "FORMULA_NO");
        rsFormulaDbMap.put(ConstantsUtils.FORMULA_NAME, "FORMULA_NAME");
    }
    
    private String getRsFormulaOrderQuery(String string, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = rsFormulaDbMap.get(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            string+=(" ORDER BY FORMULA_ID ");
        } else {
            string+=(ConstantsUtils.ORDER_BY)+(orderByColumn)+((!sortOrder) ? ConstantsUtils.ASC_SPACE : ConstantsUtils.DESC_SPACE);
        }

        string+=(ConstantsUtils.OFFSET_SPACE)+(startIndex);
        string+=(ConstantsUtils.ROW_FETCH_NEXT)+(endIndex)+(" ROWS ONLY ;");

        return string;
    }
    
    /**
     * Converts the list of objects to list of RSFormulaDTO.
     * @param list List<Object[]>
     * @return List<RebatePlanDTO>
     */
    private List<RSFormulaDTO> convertFormulaList(final List<Object[]> list){
        List<RSFormulaDTO> resultList = new ArrayList<>();
        for(Object[] object : list) {
            HelperDTO dto = new HelperDTO();
            RSFormulaDTO rSFormulaDTO = new RSFormulaDTO();
            rSFormulaDTO.setFormulaID(String.valueOf(object[0]));
            if(!ConstantsUtils.NULL.equals(String.valueOf(object[1]))) {
            rSFormulaDTO.setFormulaNo(String.valueOf(object[1]));
            }
            rSFormulaDTO.setFormulaName(String.valueOf(object[NumericConstants.TWO]));
            if((Integer) (object[NumericConstants.THREE]) != null && (Integer) (object[NumericConstants.THREE]) != 0 && (String) (object[NumericConstants.FOUR]) != null) {
            dto.setId((Integer) (object[NumericConstants.THREE]));
            dto.setDescription((String) (object[NumericConstants.FOUR]));
            rSFormulaDTO.setFormulaType(dto);
            }
            resultList.add(rSFormulaDTO);
        }
        return resultList;
    }
    
    /**
     * 
     * @param start
     * @param offset
     * @param userID
     * @param sessionID
     * @param column
     * @param orderBy
     * @param value1
     * @param value2
     * @param parameters
     * @param isCount
     * @return 
     */
    private String getRebateSetupQuery(int start, int offset, Object userID, Object sessionID, Object column, Object orderBy,Map<String, Object> parameters, boolean isCount) {
        
        String sql;
        if(isCount){
            sql = "SELECT COUNT(DISTINCT cm.IMTD_RS_DETAILS_SID) from IMTD_RS_DETAILS cm LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  cm.RS_DETAILS_ATTACHED_STATUS"
                + " LEFT JOIN dbo.REBATE_PLAN_MASTER rp on rp.REBATE_PLAN_MASTER_SID =  cm.REBATE_PLAN_MASTER_SID "
                 + "LEFT JOIN dbo.DEDUCTION_CALENDAR_MASTER dc on dc.DEDUCTION_CALENDAR_MASTER_SID =  cm.DEDUCTION_CALENDAR_MASTER_SID  "
                + " WHERE cm.USERS_SID='" + userID + "' AND SESSION_ID='" + sessionID
                + "' AND cm.\"OPERATION\" <> 'D'"; 
        }else{
          sql = "SELECT cm.IMTD_RS_DETAILS_SID, cm.USERS_SID, cm.SESSION_ID, cm.IFP_MODEL_SID, cm.ITEM_NO, cm.ITEM_NAME, cm.ITEM_MASTER_SID, cm.RS_DETAILS_REBATE_AMOUNT, cm.ITEM_REBATE_START_DATE, cm.ITEM_REBATE_END_DATE, cm.RS_DETAILS_FORMULA_ID,"
                + "cm.RS_DETAILS_FORMULA_NAME, cm.RS_DETAILS_ATTACHED_STATUS, cm.RS_DETAILS_ATTACHED_DATE, cm.RS_DETAILS_MODIFIED_DATE, cm.RS_DETAILS_FORMULA_TYPE, cm.CHECK_RECORD, cm.RS_DETAILS_BUNDLE_NO AS BUNDLE_NO, cm.REBATE_PLAN_MASTER_SID, htype.DESCRIPTION as status, cm.RS_DETAILS_REBATE_PLAN_NAME as rpName,"
                + "cm.ITEM_ID,rp.REBATE_PLAN_NO AS REBATE_PLAN_NO,cm.RS_DETAILS_FORMULA_NO AS FORMULA_NO,cm.RS_DETAILS_FORMULA_NAME AS FORMULA_NAME,dc.DEDUCTION_CALENDAR_MASTER_SID,dc.DEDUCTION_CALENDAR_NO,dc.DEDUCTION_CALENDAR_NAME,ns.NET_SALES_FORMULA_MASTER_SID,ns.NET_SALES_FORMULA_NO,"
                  + "ns.NET_SALES_FORMULA_NAME,cm.NET_SALES_RULE,cm.EVALUATION_RULE,cm.CALCULATION_RULE,cm.EVALUATION_RULE_BUNDLE,cm.CALCULATION_RULE_BUNDLE from IMTD_RS_DETAILS cm LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  cm.RS_DETAILS_ATTACHED_STATUS"
                + " LEFT JOIN dbo.REBATE_PLAN_MASTER rp on rp.REBATE_PLAN_MASTER_SID =  cm.REBATE_PLAN_MASTER_SID "
                  + "LEFT JOIN dbo.DEDUCTION_CALENDAR_MASTER dc on dc.DEDUCTION_CALENDAR_MASTER_SID =  cm.DEDUCTION_CALENDAR_MASTER_SID  "
                   + "LEFT JOIN dbo.NET_SALES_FORMULA_MASTER ns on ns.NET_SALES_FORMULA_MASTER_SID =  cm.NET_SALES_FORMULA_MASTER_SID  "
                + " WHERE cm.USERS_SID='" + userID + "' AND SESSION_ID='" + sessionID
                + "' AND cm.\"OPERATION\" <> 'D'";  
        }         

        if (parameters.get(ConstantsUtils.REBATE_BUNDLE_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.REBATE_BUNDLE_NO)))) {
            sql += " AND cm.RS_DETAILS_BUNDLE_NO LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.REBATE_BUNDLE_NO)) + "%'";
        }
        if (parameters.get(ConstantsUtils.ITEM_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NO)))) {
            sql += " AND cm.ITEM_NO LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.ITEM_NO)) + "%'";
        }
        if (parameters.get(ConstantsUtils.ITEM_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME)))) {
            sql += " AND cm.ITEM_NAME LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.ITEM_NAME)) + "%'";
        }
        if (parameters.get(ConstantsUtils.ATTACHED_STATUS) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_STATUS))) && !ConstantsUtils.ZERO.equalsIgnoreCase(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_STATUS)))) {

            sql += " AND cm.RS_DETAILS_ATTACHED_STATUS = " + String.valueOf(parameters.get(ConstantsUtils.ATTACHED_STATUS));
        }        
        if (parameters.get(ConstantsUtils.START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.START_DATE_FROM)))) {
            sql += " AND cm.ITEM_REBATE_START_DATE >='" + String.valueOf(parameters.get(ConstantsUtils.START_DATE_FROM) + ConstantsUtils.SINGLE_QUOTE);
        }
        if (parameters.get(ConstantsUtils.STATUS_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.STATUS_DATE_TO)))) {
            sql += " AND cm.ITEM_REBATE_START_DATE <='" + String.valueOf(parameters.get(ConstantsUtils.STATUS_DATE_TO) + ConstantsUtils.SINGLE_QUOTE);
        }
        if (parameters.get(ConstantsUtils.END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.END_DATE_FROM)))) {
            sql += " AND cm.ITEM_REBATE_END_DATE >='" + String.valueOf(parameters.get(ConstantsUtils.END_DATE_FROM) + ConstantsUtils.SINGLE_QUOTE);
        }
        if (parameters.get(ConstantsUtils.END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.END_DATE_TO)))) {
            sql += " AND cm.ITEM_REBATE_END_DATE <='" + String.valueOf(parameters.get(ConstantsUtils.END_DATE_TO) + ConstantsUtils.SINGLE_QUOTE);
        }
        if (parameters.get(ConstantsUtils.ATTACHED_DATE_FORM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_FORM)))) {
            sql += " AND cm.RS_DETAILS_ATTACHED_DATE >='" + String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_FORM) + ConstantsUtils.SINGLE_QUOTE);
        }
        if (parameters.get(ConstantsUtils.ATTACHED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_TO)))) {
            sql += " AND cm.RS_DETAILS_ATTACHED_DATE <='" + String.valueOf(parameters.get(ConstantsUtils.ATTACHED_DATE_TO) + ConstantsUtils.SINGLE_QUOTE);
        }
        if (parameters.get(ConstantsUtils.REVISION_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.REVISION_DATE_FROM)))) {
            sql += " AND cm.RS_DETAILS_MODIFIED_DATE >='" + String.valueOf(parameters.get(ConstantsUtils.REVISION_DATE_FROM) + ConstantsUtils.SINGLE_QUOTE);
        }
        if (parameters.get(ConstantsUtils.REVISION_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.REVISION_DATE_TO)))) {
            sql += " AND cm.RS_DETAILS_MODIFIED_DATE <='" + String.valueOf(parameters.get(ConstantsUtils.REVISION_DATE_TO) + ConstantsUtils.SINGLE_QUOTE);
        }
        if(StringUtils.isNotBlank(String.valueOf(parameters.get(ConstantsUtils.REBATE_PLAN_NO)))&& ! ConstantsUtils.NULL.equals(String.valueOf(parameters.get(ConstantsUtils.REBATE_PLAN_NO)))){            
            sql += " AND rp.REBATE_PLAN_NO LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.REBATE_PLAN_NO) + "%'");
        }
        if(StringUtils.isNotBlank(String.valueOf(parameters.get(ConstantsUtils.REBATE_PLAN_NAME)))&& ! ConstantsUtils.NULL.equals(String.valueOf(parameters.get(ConstantsUtils.REBATE_PLAN_NAME)))){
            sql += " AND cm.RS_DETAILS_REBATE_PLAN_NAME LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.REBATE_PLAN_NAME) + "%'");
        }
        if(StringUtils.isNotBlank(String.valueOf(parameters.get(ConstantsUtils.FORMULA_NO)))&& ! ConstantsUtils.NULL.equals(String.valueOf(parameters.get(ConstantsUtils.FORMULA_NO)))){
            sql += " AND cm.RS_DETAILS_FORMULA_NO LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.FORMULA_NO) + "%'");
        }
        if(StringUtils.isNotBlank(String.valueOf(parameters.get(ConstantsUtils.FORMULA_NAME)))&& ! ConstantsUtils.NULL.equals(String.valueOf(parameters.get(ConstantsUtils.FORMULA_NAME)))){
            sql += " AND cm.RS_DETAILS_FORMULA_NAME LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.FORMULA_NAME) + "%'");
        }   
         if(StringUtils.isNotBlank(String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CALENDAR_NAME)))&& ! ConstantsUtils.NULL.equals(String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CALENDAR_NAME)))){
            sql += " AND dc.DEDUCTION_CALENDAR_NAME LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CALENDAR_NAME) + "%'");
        }   
          if(StringUtils.isNotBlank(String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CALENDAR_NO)))&& ! ConstantsUtils.NULL.equals(String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CALENDAR_NO)))){
            sql += " AND dc.DEDUCTION_CALENDAR_NO LIKE '%" + String.valueOf(parameters.get(ConstantsUtils.DEDUCTION_CALENDAR_NO) + "%'");
        }   
          
        
        if (parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.FUTURE) != null) {
                sql+=" AND (  '"+String.valueOf(parameters.get(ConstantsUtils.CURRENT))+"' BETWEEN cm.ITEM_REBATE_START_DATE AND ISNULL(cm.ITEM_REBATE_END_DATE ,'"+String.valueOf(parameters.get(ConstantsUtils.CURRENT))+"') ";
                sql+=" OR "+" cm.ITEM_REBATE_END_DATE <  '"+parameters.get(ConstantsUtils.HISTORY)+"' ";
                sql+=" OR "+" cm.ITEM_REBATE_START_DATE > '"+parameters.get(ConstantsUtils.FUTURE)+"' )";
            } else if ((parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.HISTORY) != null) || (parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.CURRENT) != null)) {
                sql+=" AND ( '"+String.valueOf(parameters.get(ConstantsUtils.CURRENT))+"' BETWEEN cm.ITEM_REBATE_START_DATE AND ISNULL(cm.ITEM_REBATE_END_DATE,'"+String.valueOf(parameters.get(ConstantsUtils.CURRENT))+"') OR cm.ITEM_REBATE_END_DATE < '"+String.valueOf(parameters.get(ConstantsUtils.HISTORY))+"') ";
            } else if ((parameters.get(ConstantsUtils.HISTORY) != null && parameters.get(ConstantsUtils.FUTURE) != null) || (parameters.get(ConstantsUtils.FUTURE) != null && parameters.get(ConstantsUtils.HISTORY) != null)) {
                sql+=" AND ("+" cm.ITEM_REBATE_END_DATE < '"+String.valueOf(parameters.get(ConstantsUtils.HISTORY))+"' OR cm.ITEM_REBATE_START_DATE > '"+String.valueOf(parameters.get(ConstantsUtils.FUTURE))+"') ";
            } else if ((parameters.get(ConstantsUtils.FUTURE) != null && parameters.get(ConstantsUtils.CURRENT) != null) || (parameters.get(ConstantsUtils.CURRENT) != null && parameters.get(ConstantsUtils.FUTURE) != null)) {
                sql+=" AND ( '"+String.valueOf(parameters.get(ConstantsUtils.CURRENT))+"' BETWEEN cm.ITEM_REBATE_START_DATE AND ISNULL(cm.ITEM_REBATE_END_DATE,'"+String.valueOf(parameters.get(ConstantsUtils.CURRENT))+"') OR cm.ITEM_REBATE_START_DATE > '"+String.valueOf(parameters.get(ConstantsUtils.FUTURE))+"') ";
            } else if (parameters.get(ConstantsUtils.CURRENT) != null) {
                sql+=" AND '"+String.valueOf(parameters.get(ConstantsUtils.CURRENT))+"' BETWEEN cm.ITEM_REBATE_START_DATE AND ISNULL(cm.ITEM_REBATE_END_DATE ,'"+String.valueOf(parameters.get(ConstantsUtils.CURRENT))+"') ";
            } else if (parameters.get(ConstantsUtils.HISTORY) != null) {
                sql+=ConstantsUtils.AND+" cm.ITEM_REBATE_END_DATE < '"+String.valueOf(parameters.get(ConstantsUtils.HISTORY))+"' ";
            } else if (parameters.get(ConstantsUtils.FUTURE) != null) {
                sql+=ConstantsUtils.AND+" cm.ITEM_REBATE_START_DATE > '"+String.valueOf(parameters.get(ConstantsUtils.FUTURE))+"' ";
            }
        
        if(!isCount){
        sql += ConstantsUtils.ORDER_BY + column + " " + orderBy + ConstantsUtils.OFFSET_SPACE + start + ConstantsUtils.ROW_FETCH_NEXT + offset + " ROWS ONLY";
        }
        return sql;
    }

    /**
     * Mass Update for Formula and Rebate Plan
     * 
     * @param populateField
     * @param data 
     */
    public void massPopulateForLookUp(String populateField, Object data, boolean isPopulate) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        Map<String, String> map = (Map) data;
        String sql;
        if ("Rebate Plan No".equals(populateField)) {
            if (isPopulate) {
                  sql = CustomSQLUtil.get("com.rsModel.massPopulateRebate");
            } else {
                sql = CustomSQLUtil.get("com.rsModel.massPopulateRebateForAll");
            }
            sql = sql.replace(ConstantsUtils.AT_USER_SID, userId);
            sql = sql.replace(ConstantsUtils.AT_SESSION_ID, sessionId);
            sql = sql.replace("@REBATE_PLAN_MASTER_SID", map.get("rebatePlanSystemId"));
            sql = sql.replace("@RS_DETAILS_REBATE_PLAN_NAME", map.get(ConstantsUtils.REBATE_PLAN_NAME));
        } else {
            sql = CustomSQLUtil.get("com.rsModel.massPopulateFormula");
            sql = sql.replace(ConstantsUtils.AT_USER_SID, userId);
            sql = sql.replace(ConstantsUtils.AT_SESSION_ID, sessionId);
            sql = sql.replace("@RS_DETAILS_FORMULA_NO", map.get(ConstantsUtils.FORMULA_NO));
            sql = sql.replace("@RS_DETAILS_FORMULA_NAME", map.get("formulaName"));
            sql = sql.replace("@RS_DETAILS_FORMULA_ID", map.get("formulaID"));
        }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }
        
    /**
     * Converts the date to the given format.
     *
     * @param date
     * @param format
     * @return String - Date with converted format.
     */
    public String convertDateToString(Date date, String format) {
        SimpleDateFormat outputDateFormatter = new SimpleDateFormat(format);
        return outputDateFormatter.format(date);
    }

    @Override
    public List<SearchResultsDTO> searchRebateScheduleMaster(ErrorfulFieldGroup rebateSchForm, int start, int end, List<OrderByColumn> columns, boolean fieldFlag, BeanSearchCriteria search) throws SystemException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /**
     * Method to get the count of the rebate schedule search.
     *
     * @param rebateSchForm
     * @param start
     * @param offset
     * @param columns
     * @param fieldFlag
     * @param criteria
     * @param isCount
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public Object getCountAndResultsForRS(final ErrorfulFieldGroup rebateSchForm, int start, int offset, final List<SortByColumn> columns, final boolean fieldFlag, final Set<Container.Filter> filterSet, boolean isCount)
            throws SystemException {
        LOGGER.debug("In getSearchCount P1:rebateScheduleForm");
        Map<String, Object> filterCriteria = new HashMap<>();
        Map<String, String> searchCriteria = new HashMap<>();

        searchCriteria.put(ConstantsUtils.REBATE_ID, checkEmptyDataFromFields(ConstantsUtils.TEXT1, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT1).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.REBATE_NO, checkEmptyDataFromFields(ConstantsUtils.TEXT2, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT2).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.REBATE_NAME, checkEmptyDataFromFields(ConstantsUtils.TEXT3, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT3).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.REBATE_TYPE, checkEmptyDataFromFields(ConstantsUtils.COMBO1, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO1).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.REBATE_STATUS, checkEmptyDataFromFields(ConstantsUtils.COMBO2, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO2).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.REBATE_PRO_TYPE, checkEmptyDataFromFields(ConstantsUtils.COMBO3, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO3).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.REBATE_CATEGORY, checkEmptyDataFromFields(ConstantsUtils.COMBO4, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO4).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.REBATE_FREQ, checkEmptyDataFromFields(ConstantsUtils.COMBO6, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO6).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.CALCULATION_TYPE, checkEmptyDataFromFields(ConstantsUtils.COMBO7, rebateSchForm) ? ConstantsUtils.ZERO : String.valueOf(((com.stpl.app.util.HelperDTO) rebateSchForm.getField(ConstantsUtils.COMBO7).getValue()).getId()));
        searchCriteria.put(ConstantsUtils.RS_ALAIS_ID, checkEmptyDataFromFields(ConstantsUtils.TEXT7, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT7).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.ITEM_NO, checkEmptyDataFromFields(ConstantsUtils.TEXT8, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT8).getValue().toString()).trim());
        searchCriteria.put(ConstantsUtils.ITEM_NAME, checkEmptyDataFromFields(ConstantsUtils.TEXT_9, rebateSchForm) ? StringUtils.EMPTY : replaceForWildCardSearch(rebateSchForm.getField(ConstantsUtils.TEXT_9).getValue().toString()).trim());

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = com.stpl.ifs.util.CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                    if (ConstantsUtils.REBATE_SCHEDULE_STATUS.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_SCHEDULE_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_PROGRAM_TYPE.equals(stringFilter.getPropertyId()) || ConstantsUtils.RS_PROGRAM_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.RS_CATEGORY.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.RS_DESIGNATION.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.RS_TRADE_CLASS.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_FREQ.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_PROGRAM_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.CALENDER.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.CALCULATION_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.REBATE_RULE_TYPE.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.PAYMENT_LEVEL.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.PAYMENT_TERMS.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.PAYMENT_METHOD.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.PAYMENT_FREQUENCY.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.INTEREST_BEARING_INDICATOR.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.INTEREST_BEARING_BASIS.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC1.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC2.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC3.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC4.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC5.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else if (ConstantsUtils.UDC6.equals(stringFilter.getPropertyId())) {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), stringFilter.getFilterString());
                    } else {
                        filterCriteria.put(stringFilter.getPropertyId().toString(), filterString);
                    }
                }
            }
        }
        String column = ConstantsUtils.RS_ID_COLUMN;
        String orderBy = "ASC";
        if (columns != null) {
            for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                String columnName = sortByColumn.getName();
                
                if (ConstantsUtils.REBATE_SCHEDULE_NO.equals(columnName)) {
                    column = ConstantsUtils.RS_NO_COLUMN;
                } else if (ConstantsUtils.REBATE_SCHEDULE_ID.equals(columnName)) {
                    column = ConstantsUtils.RS_ID_COLUMN;
                } else if (ConstantsUtils.REBATE_SCHEDULE_NAME.equals(columnName)) {
                    column = ConstantsUtils.RS_NAME_LIST;
                } else if (ConstantsUtils.REBATE_SCHEDULE_STATUS.equals(columnName)) {
                    column = ConstantsUtils.RS_STATUS1;
                } else if (ConstantsUtils.REBATE_SCHEDULE_TYPE.equals(columnName)) {
                    column = ConstantsUtils.R_TYPE;
                } else if (ConstantsUtils.REBATE_PROGRAM_TYPE.equals(columnName) || ConstantsUtils.RS_PROGRAM_TYPE.equals(columnName)) {
                    column = ConstantsUtils.RP_TYPE;
                } else if (columnName != null && columnName.contains("udc")) {
                    column = columnName.toUpperCase();
                } else if (ConstantsUtils.RS_CATEGORY.equals(columnName)) {
                    column = ConstantsUtils.RS_CATEGORY1;
                } else if (ConstantsUtils.RS_DESIGNATION.equals(columnName)) {
                    column = ConstantsUtils.RS_DESIGNATION1;
                } else if (ConstantsUtils.RS_TRADE_CLASS.equals(columnName)) {
                    column = "TRADE_CLASS";
                } else if (ConstantsUtils.REBATE_FREQ.equals(columnName)) {
                    column = "REBATE_FREQUENCY";
                } else if (ConstantsUtils.CALENDER.equals(columnName)) {
                    column = "CALENDAR";
                } else if (ConstantsUtils.CALCULATION_TYPE.equals(columnName)) {
                    column = "CALCULATION_TYPE";
                } else if (ConstantsUtils.REBATE_RULE_TYPE.equals(columnName)) {
                    column = "REBATE_RULE_TYPE";
                } else if (ConstantsUtils.PAYMENT_LEVEL.equals(columnName)) {
                } else if (ConstantsUtils.PAYMENT_TERMS.equals(columnName)) {
                    column = "PAYMENT_TERMS";
                } else if (ConstantsUtils.PAYMENT_METHOD.equals(columnName)) {
                    column = "PAYMENT_METHOD";
                } else if (ConstantsUtils.PAYMENT_FREQUENCY.equals(columnName)) {
                    column = "PAYMENT_FREQUENCY";
                } else if (ConstantsUtils.INTEREST_BEARING_INDICATOR.equals(columnName)) {
                    column = "INTEREST_BEARING_INDICATOR";
                } else if (ConstantsUtils.INTEREST_BEARING_BASIS.equals(columnName)) {
                    column = "INTEREST_BEARING_BASIS";
                } else if (ConstantsUtils.RS_PARENT_ID.equals(columnName)) {
                    column = ConstantsUtils.PARENT_RS_SID;
                } else if (ConstantsUtils.RS_PARENT_NAME.equals(columnName)) {
                    column = ConstantsUtils.PARENT_RS_NAME;
                } else if (ConstantsUtils.RS_REFERENCE_ID.equals(columnName)) {
                    column = "RS_TRANS_REF_ID";
                } else if (ConstantsUtils.RS_REFERENCE_NAME.equals(columnName)) {
                    column = "RS_TRANS_REF_NAME";
                } 

                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    orderBy = "ASC";
                } else {
                    orderBy = "DESC";
                }
            }
        }
        String query = getRSQuery(searchCriteria, start, offset, column, orderBy, filterCriteria, isCount);
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);

        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return resultList;
        }

    }

    /**
     * To Update the RS_DETAILS to default value on reset in add mode
     * 
     * @param calculationType
     * @param userID
     * @param sessionID 
     */
    public void updateDetailsOnReset(String calculationType, String userID, String sessionID) {
        String query;    
        calculationType = calculationType == null || ConstantsUtils.NULL.equals(calculationType) ? "default":calculationType;
        switch (calculationType) {
            case RsUtils.CALC_FORMULA:
                query = CustomSQLUtil.get("com.rsModel.updateRebateSetupOnFormulaReset");
                break;
            case RsUtils.CALC_REBATE_PLAN:
                query = CustomSQLUtil.get("com.rsModel.updateRebateSetupOnRebatePlanReset");
                break;
            case RsUtils.CALC_DEDUCTION_CALENDAR:
                query = CustomSQLUtil.get("com.rsModel.updateRebateSetupOnDefault");
                break;
            default:
                query = CustomSQLUtil.get("com.rsModel.updateRebateSetupOnDefault");
                break;
        }
        query = query.replace(ConstantsUtils.AT_USER_SID,userID);
        query = query.replace(ConstantsUtils.AT_SESSION_ID,sessionID);        
        
        RsModelLocalServiceUtil.executeUpdateQuery(query, null, null);
    }
    
        public long startDateValidation(String userId,String sessionId) throws SystemException {
        DynamicQuery rebateSchQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetails.class);
        rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.USER_SID, userId));
        rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.SESSION_ID, sessionId));
        rebateSchQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.CHECK_RECORD, new Boolean(true)));
        rebateSchQuery.add(RestrictionsFactoryUtil.gtProperty(ConstantsUtils.ITEM_REBATE_START_DATE,ConstantsUtils.ITEM_REBATE_END_DATE));        
        long var = ImtdRsDetailsLocalServiceUtil.dynamicQueryCount(rebateSchQuery);        
        return var;
    }
        public List getRsContractList(int systemId) {
        List rsContractList = new ArrayList<>();
        try {
            final DynamicQuery contractDynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class);
            contractDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.RS_MODEL_SID, systemId));
            contractDynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantsUtils.INBOUND_STATUS, ConstantsUtils.INBOUND_STATUS_D));
            rsContractList = RsContractLocalServiceUtil.dynamicQuery(contractDynamicQuery);
          } catch (SystemException e) {
            LOGGER.error(e);
        }
        return rsContractList;
    }
       
    public int getNsfCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet) {
        int count = 0;
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchFields, true);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);

        LOGGER.debug("getNsfCount " + queryBuilder.toString());
        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.debug(" nsfCount count=" + count);
        return count;
    }

    private void loadNetSalesLookupColumnMap() {
        criteria.clear();
        criteria.put("netSalesFormulaId", "NET_SALES_FORMULA_ID");
        criteria.put(ConstantsUtils.NET_SALES_FORMULA_NO, "NET_SALES_FORMULA_NO");
        criteria.put(ConstantsUtils.NET_SALES_FORMULA_NAME, "NET_SALES_FORMULA_NAME");
        criteria.put(ConstantsUtils.NET_SALES_FORMULA_TYPE, "NET_SALES_FORMULA_TYPE");
    }
    
    private StringBuilder buildSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("netSalesFormula");
        queryBuilder.append(" SELECT ").append(query).append(" FROM NET_SALES_FORMULA_MASTER WHERE INBOUND_STATUS <> 'D' ");
        if (criteria.isEmpty()) {
            loadNetSalesLookupColumnMap();
        }
        Set<String> keys = criteria.keySet();
        for (String fields : keys) {

            if (searchFields.getField(fields).getValue() != null && searchFields.getField(fields).getValue() != null && !ConstantsUtils.SELECT_ONE.equals(String.valueOf(searchFields.getField(fields).getValue())) && !String.valueOf(searchFields.getField(fields).getValue()).trim().isEmpty()) {
                if (ConstantsUtils.NET_SALES_FORMULA_TYPE.equalsIgnoreCase(fields)) {
                    queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" = '").append(checkEmptyDataFromFields(fields, searchFields) ? Constants.ZERO : ((HelperDTO) searchFields.getField(fields).getValue()).getId()).append(ConstantsUtils.SINGLE_QUOTE);
                } else {
                    queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(ConstantsUtils.LIKE_QUOTE).append(CommonUtil.buildSearchCriteria(searchFields.getField(fields).getValue().toString().trim())).append(ConstantsUtils.SINGLE_QUOTE);
                }
            }
        }
        return queryBuilder;
    }
    
    private StringBuilder getFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) {
          HashMap<String, String> filterCriteria = new HashMap<>();
        filterCriteria.put("netSalesFormulaId", "NET_SALES_FORMULA_ID");
        filterCriteria.put(ConstantsUtils.NET_SALES_FORMULA_NO, "NET_SALES_FORMULA_NO");
        filterCriteria.put(ConstantsUtils.NET_SALES_FORMULA_NAME, "NET_SALES_FORMULA_NAME");
        filterCriteria.put(ConstantsUtils.NET_SALES_FORMULA_TYPE, "NET_SALES_FORMULA_TYPE");
        filterCriteria.put("nsfcreatedDate", "CREATED_DATE");
        filterCriteria.put("nsfcreatedBy", "CREATED_BY");
        filterCriteria.put("nsfmodifiedDate", "MODIFIED_DATE");
        filterCriteria.put("nsfmodifiedBy", "MODIFIED_BY");
       
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(ConstantsUtils.AND).append(filterCriteria.get(stringFilter.getPropertyId().toString())).append(ConstantsUtils.LIKE_QUOTE).append(CommonUtil.buildFilterCriteria(stringFilter.getFilterString())).append("'");
              
                
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    String startValue = DB_DATE.format(stringFilter.getStartValue());
                    String endValue = DB_DATE.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        stringBuilder.append(ConstantsUtils.AND).append(filterCriteria.get(stringFilter.getPropertyId().toString())).append(ConstantsUtils.GREATER_THAN_EQUAL ).append(startValue).append("' ");
                    }
                    if (endValue != null) {
                        stringBuilder.append(ConstantsUtils.AND).append(filterCriteria.get(stringFilter.getPropertyId().toString())).append(ConstantsUtils.LESS_THAN_EQUAL).append(endValue).append("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = DB_DATE.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            stringBuilder.append(ConstantsUtils.AND).append(filterCriteria.get(String.valueOf(stringFilter.getPropertyId()))).append(ConstantsUtils.GREATER_THAN_EQUAL ).append(filterString).append("' ");
                        } else {
                            stringBuilder.append(ConstantsUtils.AND).append(filterCriteria.get(String.valueOf(stringFilter.getPropertyId()))).append(ConstantsUtils.LESS_THAN_EQUAL).append(filterString).append("' ");
                        }
    }
    }
    }
        }
          
        return stringBuilder;
    }

    public List<NetSalesFormulaDTO> loadNsfResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        List<NetSalesFormulaDTO> searchList;
        LOGGER.debug("Entering loadNsfResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchFields, false);
        queryBuilder = getFilterQuery(filterSet, queryBuilder);
        queryBuilder = getOrderQuery(queryBuilder, columns, start, end);

        queryBuilder = new StringBuilder(queryBuilder.toString().replaceAll("WHERE AND", " WHERE "));
        queryBuilder = new StringBuilder(queryBuilder.toString().endsWith(ConstantsUtils.WHERE) ? queryBuilder.toString().replace(ConstantsUtils.WHERE, " ") : queryBuilder);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedSearchFormToDTO(list);
        LOGGER.debug("queryBuilderqueryBuilder"+queryBuilder);
        return searchList;
    }
    
    private StringBuilder getOrderQuery(final StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = constantProperties.getString(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY CREATED_DATE ");
        } else {
            stringBuilder.append(ConstantsUtils.ORDER_BY).append(orderByColumn).append((!sortOrder) ? ConstantsUtils.ASC_SPACE : ConstantsUtils.DESC_SPACE);
        }

        stringBuilder.append(ConstantsUtils.OFFSET_SPACE).append(startIndex);
        stringBuilder.append(ConstantsUtils.ROW_FETCH_NEXT).append(endIndex).append(" ROWS ONLY;");

        return stringBuilder;
    }
    
    private List<NetSalesFormulaDTO> getCustomizedSearchFormToDTO(List list) {
        final List<NetSalesFormulaDTO> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
                Map<Integer, String> userMap = StplSecurity.userMap;
                if (userMap.isEmpty()) {
                    userMap = StplSecurity.getUserName();
                }
                for (int i = 0; i < list.size(); i++) {
                    final NetSalesFormulaDTO searchDTO = new NetSalesFormulaDTO();
                    final Object[] object = (Object[]) list.get(i);
                    searchDTO.setNetSalesFormulaType(helperListUtil.getIdHelperDTOMap().get(object[0] != null ? Integer.valueOf(String.valueOf(object[0])) : 0));
                    searchDTO.setNetSalesFormulaId(!ConstantsUtils.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    searchDTO.setNetSalesFormulaNo(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.TWO])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.TWO])) ? String.valueOf(object[NumericConstants.TWO]) : StringUtils.EMPTY);
                    searchDTO.setNetSalesFormulaName(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.THREE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.THREE])) ? String.valueOf(object[NumericConstants.THREE]) : StringUtils.EMPTY);
                    if (object[NumericConstants.FOUR] != null) {
                        Date createdDate = (Date) object[NumericConstants.FOUR];
                        searchDTO.setNsfcreateDate(CommonUtils.convertDateToString(createdDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        createdDate = df.parse(searchDTO.getNsfcreateDate());
                        searchDTO.setNsfcreatedDate(createdDate);
                    }
                    searchDTO.setNsfcreatedBy(object[NumericConstants.FIVE] != null ? userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.FIVE]))) : StringUtils.EMPTY);
                    if (object[NumericConstants.SIX] != null) {
                        Date modifyDate = (Date) object[NumericConstants.SIX];
                        searchDTO.setNsfmodifyDate(CommonUtils.convertDateToString(modifyDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        modifyDate = df.parse(searchDTO.getNsfmodifyDate());
                        searchDTO.setNsfmodifiedDate(modifyDate);
                    }
                    searchDTO.setNsfmodifiedBy(object[NumericConstants.SEVEN] != null ? userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.SEVEN]))) : StringUtils.EMPTY);
                    searchDTO.setSystemID(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.EIGHT])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.EIGHT])) ? String.valueOf(object[NumericConstants.EIGHT]) : ConstantsUtils.ZERO);
                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }
        public int getDeductionCount(final ErrorfulFieldGroup searchFields, final Set<Container.Filter> filterSet)  {
        int count = 0;
        StringBuilder queryBuilder;
        queryBuilder = buildDeductionSearchQuery(searchFields, true);
        queryBuilder = getDeductionFilterQuery(filterSet, queryBuilder);

        List<Object> masterData = (List<Object>) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (masterData != null && !masterData.isEmpty()) {
            Object ob = masterData.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        LOGGER.debug(" getDeductionCount returns count=" + count);
        return count;
    }

    public List<RsDeductionLookupDto> loadDeductionResults(
            final ErrorfulFieldGroup searchFields, final int start, final int end, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        List<RsDeductionLookupDto> searchList;
        LOGGER.debug("Entering loadDeductionResults with start of=" + start + "and endIndex of= " + end + "  Column Size +" + ((columns == null) ? columns : columns.size()));
        StringBuilder queryBuilder;
        queryBuilder = buildDeductionSearchQuery(searchFields, false);
        queryBuilder = getDeductionFilterQuery(filterSet, queryBuilder);
        queryBuilder = getDeductionOrderQuery(queryBuilder, columns, start, end);

        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        searchList = getCustomizedDeductionDTO(list);
        LOGGER.debug("End of loadDeductionResults");
        return searchList;
    }

    private void loadDeductionCriteriaMap() {
        deductionCriteria.clear();
        deductionCriteria.put(ConstantsUtils.DEDUCTION_NO, "DEDUCTION_CALENDAR_NO");
        deductionCriteria.put(ConstantsUtils.DEDUCTION_NAME, "DEDUCTION_CALENDAR_NAME");
        deductionCriteria.put(ConstantsUtils.DEDUCTION_DESC, "DEDUCTION_CALENDAR_DESC");
        deductionCriteria.put("category", "CATEGORY");
    }

    private StringBuilder buildDeductionSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount) {
        StringBuilder queryBuilder = new StringBuilder();
        String query = isCount ? "COUNT( * )" : constantProperties.getString("rsdeductionLookup");
        queryBuilder.append(" SELECT ").append(query).append(" FROM DEDUCTION_CALENDAR_MASTER where INBOUND_STATUS <> 'D' ");
        if (deductionCriteria.isEmpty()) {
            loadDeductionCriteriaMap();
        }
        Set<String> keys = deductionCriteria.keySet();
        for (String fields : keys) {
          
            if (searchFields.getField(fields) != null && searchFields.getField(fields).getValue() != null && !ConstantUtil.SELECT_ONE.equals(String.valueOf(searchFields.getField(fields).getValue())) && !String.valueOf(searchFields.getField(fields).getValue()).trim().isEmpty()) {
                    if ("category".equalsIgnoreCase(fields)) {
                        queryBuilder.append(ConstantsUtils.AND).append(deductionCriteria.get(fields)).append(" = '").append(checkEmptyDataFromFields(fields, searchFields) ? Constants.ZERO : ((HelperDTO) searchFields.getField(fields).getValue()).getId()).append(ConstantsUtils.SINGLE_QUOTE);
                    } else {
                        queryBuilder.append(ConstantsUtils.AND).append(deductionCriteria.get(fields)).append(ConstantsUtils.LIKE_QUOTE).append(CommonUtil.buildSearchCriteria(String.valueOf(searchFields.getField(fields).getValue()).trim())).append(ConstantsUtils.SINGLE_QUOTE);
                    }
                }
        }

        return queryBuilder;
    }

    private List<RsDeductionLookupDto> getCustomizedDeductionDTO(List list) {
        final List<RsDeductionLookupDto> searchResultsList = new ArrayList<>();
        try {
            if (list != null) {
                Map<Integer, String> userMap = StplSecurity.userMap;
                if (userMap.isEmpty()) {
                    userMap = StplSecurity.getUserName();
                }
                for (Object deductionList : list) {
                    final RsDeductionLookupDto searchDTO = new RsDeductionLookupDto();
                    final Object[] object = (Object[]) deductionList;
                    searchDTO.setDeductionSystemId(!ConstantsUtils.NULL.equals(String.valueOf(object[0])) && StringUtils.isNotBlank(String.valueOf(object[0])) ? String.valueOf(object[0]) : ConstantsUtils.ZERO);
                    searchDTO.setDeductionNo(!ConstantsUtils.NULL.equals(String.valueOf(object[1])) && StringUtils.isNotBlank(String.valueOf(object[1])) ? String.valueOf(object[1]) : StringUtils.EMPTY);
                    searchDTO.setDeductionName(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.TWO])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.TWO])) ? String.valueOf(object[NumericConstants.TWO]) : StringUtils.EMPTY);
                    searchDTO.setDeductionDesc(!ConstantsUtils.NULL.equals(String.valueOf(object[NumericConstants.THREE])) && StringUtils.isNotBlank(String.valueOf(object[NumericConstants.THREE])) ? String.valueOf(object[NumericConstants.THREE]) : StringUtils.EMPTY);
                    if (object[NumericConstants.FOUR] != null) {
                        searchDTO.setCategory(helperListUtil.getIdHelperDTOMap().get(object[NumericConstants.FOUR] != null ? Integer.valueOf(String.valueOf(object[NumericConstants.FOUR])) : 0));
                        searchDTO.setDeductionCategory(ConstantsUtils.SELECT_ONE.equals(searchDTO.getCategory().getDescription()) ? StringUtils.EMPTY : searchDTO.getCategory().getDescription());
                    }
                    searchDTO.setCreatedBy(object[NumericConstants.FIVE] != null ? userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.FIVE]))) : StringUtils.EMPTY);
                    if (object[NumericConstants.SIX] != null) {
                        Date createdDate = (Date) object[NumericConstants.SIX];
                        searchDTO.setCreatedDate(com.stpl.app.global.company.util.CommonUtils.convertDateToString(createdDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        createdDate = df.parse(searchDTO.getCreatedDate());
                        searchDTO.setCreationDate(createdDate);
                    }
                    searchDTO.setCreatedBy(object[NumericConstants.SEVEN] != null ? userMap.get(Integer.valueOf(String.valueOf(object[NumericConstants.SEVEN]))) : StringUtils.EMPTY);
                    if (object[NumericConstants.EIGHT] != null) {
                        Date modifiedDate = (Date) object[NumericConstants.EIGHT];
                        searchDTO.setModifyDate(com.stpl.app.global.company.util.CommonUtils.convertDateToString(modifiedDate));
                        DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
                        modifiedDate = df.parse(searchDTO.getModifyDate());
                        searchDTO.setModifiedDate(modifiedDate);
                    }

                    searchResultsList.add(searchDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return searchResultsList;
    }

    private StringBuilder getDeductionFilterQuery(final Set<Container.Filter> filterSet, final StringBuilder stringBuilder) {
        if (deductionFilter.isEmpty()) {
            deductionFilter.clear();
            deductionFilter.put(ConstantsUtils.DEDUCTION_NO, "DEDUCTION_CALENDAR_NO");
            deductionFilter.put(ConstantsUtils.DEDUCTION_NAME, "DEDUCTION_CALENDAR_NAME");
            deductionFilter.put(ConstantsUtils.DEDUCTION_DESC, "DEDUCTION_CALENDAR_DESC");
            deductionFilter.put(ConstantsUtils.DEDUCTION_CATEGORY, "CATEGORY");
            deductionFilter.put(ConstantsUtils.CREATION_DATE, "CREATED_DATE");
            deductionFilter.put(ConstantsUtils.CREATEDBY, "CREATED_BY");
            deductionFilter.put(ConstantsUtils.MODIFIEDDATE, "MODIFIED_DATE");
            deductionFilter.put(ConstantsUtils.MODIFIEDBY, "MODIFIED_BY");
        }
        final SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (filterSet != null) {

            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                   
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    stringBuilder.append(ConstantsUtils.AND).append(deductionFilter.get(String.valueOf(stringFilter.getPropertyId()))).append(ConstantsUtils.LIKE_QUOTE).append(CommonUtil.buildFilterCriteria(stringFilter.getFilterString())).append("'");
               
                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;
                    String startValue = dbDateFormat.format(stringFilter.getStartValue());
                    String endValue = dbDateFormat.format(stringFilter.getEndValue());

                    if (startValue != null) {
                        stringBuilder.append(ConstantsUtils.AND).append(deductionFilter.get(stringFilter.getPropertyId().toString())).append(ConstantsUtils.GREATER_THAN_EQUAL).append(startValue).append("' ");
                    }
                    if (endValue != null) {
                        stringBuilder.append(ConstantsUtils.AND).append(deductionFilter.get(stringFilter.getPropertyId().toString())).append(ConstantsUtils.LESS_THAN_EQUAL).append(endValue).append("' ");
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = dbDateFormat.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            stringBuilder.append(ConstantsUtils.AND).append(deductionFilter.get(String.valueOf(stringFilter.getPropertyId()))).append(ConstantsUtils.GREATER_THAN_EQUAL ).append(filterString).append("' ");
                        } else {
                            stringBuilder.append(ConstantsUtils.AND).append(deductionFilter.get(String.valueOf(stringFilter.getPropertyId()))).append(ConstantsUtils.LESS_THAN_EQUAL).append(filterString).append("' ");
                        }
                    }
                }
            }
        }

        return stringBuilder;
    }

    private StringBuilder getDeductionOrderQuery(final StringBuilder stringBuilder, final List<SortByColumn> sortByColumns, final int startIndex, final int endIndex) {
        boolean sortOrder = false;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                orderByColumn = deductionFilter.get(sortByColumn.getName());
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            stringBuilder.append(" ORDER BY CREATED_DATE ");
        } else {
            stringBuilder.append(ConstantsUtils.ORDER_BY).append(orderByColumn).append((!sortOrder) ? ConstantsUtils.ASC_SPACE : ConstantsUtils.DESC_SPACE);
        }

        stringBuilder.append(ConstantsUtils.OFFSET_SPACE).append(startIndex);
        stringBuilder.append(ConstantsUtils.ROW_FETCH_NEXT).append(endIndex).append(" ROWS ONLY;");

        return stringBuilder;
    }
    /**
     * Mass Update for Formula and Rebate Plan
     * 
     * @param populateField
     * @param data 
     */
    public void massPopulateDeductionLookUp(String populateField, Map<String,String> map,boolean populate){
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql;
        
           sql = CustomSQLUtil.get("com.rsModel.massPopulateDeduction");            
           sql = sql.replace(ConstantsUtils.AT_USER_SID,userId);
           sql = sql.replace(ConstantsUtils.AT_SESSION_ID,sessionId);
           sql = sql.replace("@DEDUCTION_CALENDAR_MASTER_SID",map.get("deductionSystemId"));
           sql = sql.replace("@RS_DETAILS_DEDUCTION_CALENDAR_NO",map.get(ConstantsUtils.DEDUCTION_CALENDAR_NO));  
           sql = sql.replace("@RS_DETAILS_DEDUCTION_CALENDAR_NAME",map.get(ConstantsUtils.DEDUCTION_CALENDAR_NAME));            
          if(populate){
           sql =sql+" AND CHECK_RECORD = 1 ;";
          }
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);        
    }
    
    /**
     * Mass Update for Formula and Rebate Plan
     * 
     * @param populateField
     * @param data 
     */
    public void massPopulateNetSalesLookUp(String populateField, Map<String,String> map, boolean populate) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql;
        sql = CustomSQLUtil.get("com.rsModel.massPopulateNetSales");
        sql = sql.replace(ConstantsUtils.AT_USER_SID, userId);
        sql = sql.replace(ConstantsUtils.AT_SESSION_ID, sessionId);
        sql = sql.replace("@NET_SALES_FORMULA_MASTER_SID", map.get(ConstantsUtils.SYS_ID));
        sql = sql.replace("@RS_DETAILS_NET_SALES_FORMULA_NO", map.get(ConstantsUtils.NET_SALES_FORMULA_NO));
        sql = sql.replace("@RS_DETAILS_NET_SALES_FORMULA_NAME", map.get(ConstantsUtils.NET_SALES_FORMULA_NAME));
        if(populate){
           sql =sql+" AND CHECK_RECORD = 1;";
          }
        LOGGER.debug("\n massPopulateNetSalesLookUp sql===>" + sql);
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }

    /**
     * Mass Update for Formula and Rebate Plan
     * 
     * @param populateField
     * @param data 
     */
    public void massPopulateRuleLookUps(String populateField, Map<String,String> map, boolean populate) {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final String sessionId = String.valueOf(sessionDTO.getUiSessionId());
        String sql=StringUtils.EMPTY;
        if (RsUtils.NET_SALES_RULE.equals(populateField)) {
          sql = CustomSQLUtil.get("com.rsModel.massPopulateNetSalesRule");
        sql = sql.replace(ConstantsUtils.AT_USER_SID, userId);
        sql = sql.replace(ConstantsUtils.AT_SESSION_ID, sessionId);
            LOGGER.debug("sql___________________________________________________________________"+sql);
        
        sql = sql.replace("@NET_SALES_RULE", map.get(ConstantsUtils.SYS_ID));
       

        } else if (RsUtils.CALCULATION_RULE.equals(populateField)) {
            sql = CustomSQLUtil.get("com.rsModel.massPopulateCalculationRule");
        sql = sql.replace(ConstantsUtils.AT_USER_SID, userId);
        sql = sql.replace(ConstantsUtils.AT_SESSION_ID, sessionId);
        sql = sql.replace("@CALCULATION_RULE", map.get(ConstantsUtils.SYS_ID));
       
            
        } else if (RsUtils.EVALUATION_RULE.equals(populateField)) {
            sql = CustomSQLUtil.get("com.rsModel.massPopulateEvaluationRule");
        sql = sql.replace(ConstantsUtils.AT_USER_SID, userId);
        sql = sql.replace(ConstantsUtils.AT_SESSION_ID, sessionId);
        sql = sql.replace("@EVALUATION_RULE", map.get(ConstantsUtils.SYS_ID));
        
        }
        
        if(populate){
           sql =sql+" AND CHECK_RECORD = 1;";
          }
        LOGGER.debug("\n massPopulateNetSalesLookUp sql===>" + sql);
        RsModelLocalServiceUtil.executeUpdateQuery(sql, null, null);
    }

  
    public List<RSFormulaDTO> loadRSDetails(final RSFormulaDTO rsFormulaDTO) {
        String query;
        query = CustomSQLUtil.get("loadFormulaDetails");
        
        query = query.replace("@FORECASTING_FORMULA_SID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || ConstantsUtils.NULL.equals(rsFormulaDTO.getFormulaID())
                ? RsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaID()));
        query = query.replace("@FORMULA_NO", StringUtils.isBlank(rsFormulaDTO.getFormulaNo()) || ConstantsUtils.NULL.equals(rsFormulaDTO.getFormulaNo())
                ? RsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaNo()));
        query = query.replace("@FORMULA_NAME", StringUtils.isBlank(rsFormulaDTO.getFormulaName()) || ConstantsUtils.NULL.equals(rsFormulaDTO.getFormulaName())
                ? RsUtils.PERCENCTAGE : replaceForWildCardSearch(rsFormulaDTO.getFormulaName()));
        query = query.replace("@FORMULA_TYPE", rsFormulaDTO.getFormulaType()== null || (Constants.SELECT_ONE).equals(rsFormulaDTO.getFormulaType()) ? RsUtils.PERCENCTAGE : String.valueOf(rsFormulaDTO.getFormulaType().getId()));
        query = query.replace("@FORMULA_ID", StringUtils.isBlank(rsFormulaDTO.getFormulaID()) || ConstantsUtils.NULL.equals(rsFormulaDTO.getFormulaID())
                ? RsUtils.PERCENCTAGE : rsFormulaDTO.getFormulaID());
        List resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        List<RSFormulaDTO> list = getCustomizedRSDetails(resultList);
        return list;
    }

    private List<RSFormulaDTO> getCustomizedRSDetails(List list) {
        final List<RSFormulaDTO> resultList = new ArrayList<>();
        if (list != null) {
            for (Object formulaList : list) {
                RSFormulaDTO formulaDto = new RSFormulaDTO();
                final String formula = (String) formulaList;
                formulaDto.setFormula(formula);
                resultList.add(formulaDto);
            }
        }
        return resultList;
    }
      private static Date parsetDate(String value) throws ParseException {
        Date date = null;
        String tempDate;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !ConstantsUtils.NULL.equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = format.parse(tempDate);
        }

        return date;
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || ConstantsUtils.NULL.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    private static String formatDate(String value) throws ParseException {
        String date = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !ConstantsUtils.NULL.equals(value)) {
            date = format.format(parse.parse(value));
        }
        return date;
    }


}

