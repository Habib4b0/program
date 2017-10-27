package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.contract.dao.impl.DashboardLogicDAOImpl;
import com.stpl.app.contract.dashboard.SearchPriceScheduleDTO;
import com.stpl.app.contract.dashboard.dto.ContractMember;
import com.stpl.app.contract.dashboard.dto.DetailSearchDTO;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.dashboard.util.ConverterUtil;
import com.stpl.app.contract.dashboard.util.QueryUtil;
import com.stpl.app.contract.global.util.CommonUtils;
import com.stpl.app.contract.ui.ErrorfulFieldGroup;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.domain.contract.contractdashboard.DashboardDAO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 * Landing page all component search logics implemented here
 * @author shrihariharan
 *
 */
public class DashboardComponentSearchLogic {

	
	 /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DashboardComponentSearchLogic.class);
	
	DashboardDAO dao = new DashboardLogicDAOImpl();
	
	QueryUtil queryUtil= new QueryUtil();
	
	ConverterUtil converter = new ConverterUtil();
	
	/**
	 * To get the searched result Count for the left pane
	 * @param searchComponentDTO - search parameters
	 * @return count in integer type
	 */
	public int getComponentSearchCount(final CustomFieldGroup leftSearchBinder,String component,final BeanSearchCriteria searchCriteria){
		LOGGER.debug("Enters Component Count Search Method");
		int count =0;
		String query="";
                 Map<String,Object> filterMap=getFilterMap(searchCriteria);
		if(ContractUtils.CONTRACT_DETAILS_COMPONENT.equalsIgnoreCase(component)){
			
			query=queryUtil.frameContractComponentSearchQuery(leftSearchBinder, 0, 0, filterMap, null, null, true);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
			
		}else if(ContractUtils.CFP_COMPONENT.equalsIgnoreCase(component)){
			
			query=queryUtil.frameCFPComponentSearchQuery(leftSearchBinder, 0, 0, filterMap, null, null, true);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
		}else if(ContractUtils.IFP_COMPONENT.equalsIgnoreCase(component)){
			
			query=queryUtil.frameIFPComponentSearchQuery(leftSearchBinder, 0, 0, filterMap, null, null, true);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
			
		}else if(ContractUtils.PS_COMPONENT.equalsIgnoreCase(component)){

			query=queryUtil.framePSComponentSearchQuery(leftSearchBinder, 0, 0, filterMap, null, null, true);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
			
		}else if(ContractUtils.RS_COMPONENT.equalsIgnoreCase(component)){

			query=queryUtil.frameRSComponentSearchQuery(leftSearchBinder, 0, 0, filterMap, null, null, true);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
			
		}
		LOGGER.debug("Exits Component Search Count Method returns Empty List");
		return count;
	}
	
	
	/**
	 * To get the searched result for the left pane
	 * @param searchComponentDTO - search parameters
	 * @return result list in table bean format
	 */
	public List<ContractMember> getComponentSearch(final CustomFieldGroup leftSearchBinder,String component,final BeanSearchCriteria searchCriteria,final int start,final int end){
		LOGGER.debug("Enters Component Search Method");
		String query="";
                Map<String,Object> filterMap=getFilterMap(searchCriteria);
		if(ContractUtils.CONTRACT_DETAILS_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.frameContractComponentSearchQuery(leftSearchBinder, start, end, filterMap, "con.CONTRACT_ID", "ASC", false);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTOFromModel(resultList,ContractUtils.CONTRACT_DETAILS_COMPONENT);
		}else if(ContractUtils.CFP_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.frameCFPComponentSearchQuery(leftSearchBinder, start, end, filterMap, "cf.CFP_ID", "ASC", false);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTOFromModel(resultList,ContractUtils.CFP_COMPONENT);
		}else if(ContractUtils.IFP_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.frameIFPComponentSearchQuery(leftSearchBinder, start, end, filterMap, "ifm.IFP_ID", "ASC", false);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTOFromModel(resultList,ContractUtils.IFP_COMPONENT);
		}else if(ContractUtils.PS_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.framePSComponentSearchQuery(leftSearchBinder, start, end, filterMap, "ps.PS_ID", "ASC", false);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTOFromModel(resultList,ContractUtils.PS_COMPONENT);
		}else if(ContractUtils.RS_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.frameRSComponentSearchQuery(leftSearchBinder, start, end, filterMap, "rsm.RS_ID", "ASC", false);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTOFromModel(resultList,ContractUtils.RS_COMPONENT);
		}
		LOGGER.debug("Exits Component Search Method returns Empty List");
		return new ArrayList<>();
	}
        
        private Map<String,Object> getFilterMap(final BeanSearchCriteria searchCriteria){
            Map<String,Object> filterMap = new HashMap<>();
            if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {

                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter filterValue = (SimpleStringFilter)filter;
                            if("memberType".equals(String.valueOf(filterValue.getPropertyId()))){
                                filterMap.put(String.valueOf(filterValue.getPropertyId()), filterValue.getFilterString());
                            }else{
                                String filterString = "%" + filterValue.getFilterString() + "%";
                                filterMap.put(String.valueOf(filterValue.getPropertyId()), filterString);
                            }

                    }
                } 
            }
            return filterMap;
        }
        
        
        /**
	 * To get the searched result Count for the left pane
	 * @param searchComponentDTO - search parameters
	 * @return count in integer type
	 */
	public int getComponentDetailsSearchCount(final int modelSid,String component,final BeanSearchCriteria searchCriteria){
		LOGGER.debug("Enters Component Count Search Method");
		int count =0;
		String query="";
                Map<String,Object> filterMap = getDetailFilterMap(searchCriteria);
		if(ContractUtils.CFP_COMPONENT.equalsIgnoreCase(component)){
			
			query=queryUtil.getCFPDetails(modelSid, 0, 0, true,filterMap);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
		}else if(ContractUtils.IFP_COMPONENT.equalsIgnoreCase(component)){
			
			query=queryUtil.getIFPDetails(modelSid,filterMap, 0, 0, true);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
			
		}else if(ContractUtils.PS_COMPONENT.equalsIgnoreCase(component)){

			query=queryUtil.getPSDetails(modelSid,filterMap, 0, 0, true);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
			
		}else if(ContractUtils.RS_COMPONENT.equalsIgnoreCase(component)){

			query=queryUtil.getRSDetails(modelSid,filterMap, 0, 0, true);
			
			List resultCount = dao.executeSelectQuery(query);
			
			if(resultCount!=null && !resultCount.isEmpty()){
				count = Integer.valueOf(String.valueOf(resultCount.get(0)));
			}
			
		}
		LOGGER.debug("Exits Component Search Count Method returns Empty List");
		return count;
	}
        
        /**
	 * To get the searched result for the left pane
	 * @param searchComponentDTO - search parameters
	 * @return result list in table bean format
	 */
	public List<DetailSearchDTO> getComponentDetailsSearch(final int modelSid,String component,final BeanSearchCriteria searchCriteria,final int start,final int end){
		LOGGER.debug("Enters Component Search Method");
		String query="";
                Map<String,Object> filterMap = getDetailFilterMap(searchCriteria);
		if(ContractUtils.CFP_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.getCFPDetails(modelSid, start, end, false,filterMap);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTODetaildFromModel(resultList,ContractUtils.CFP_COMPONENT);
		}else if(ContractUtils.IFP_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.getIFPDetails(modelSid,filterMap, start, end, false);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTODetaildFromModel(resultList,ContractUtils.IFP_COMPONENT);
		}else if(ContractUtils.PS_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.getPSDetails(modelSid,filterMap, start, end, false);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTODetaildFromModel(resultList,ContractUtils.PS_COMPONENT);
		}else if(ContractUtils.RS_COMPONENT.equalsIgnoreCase(component)){
			query=queryUtil.getRSDetails(modelSid,filterMap, start, end, false);
			List resultList = dao.executeSelectQuery(query);
			return converter.getCustomizedDTODetaildFromModel(resultList,ContractUtils.RS_COMPONENT);
		}
		LOGGER.debug("Exits Component Search Method returns Empty List");
		return new ArrayList<>();
	}
        
        private Map<String,Object> getDetailFilterMap(final BeanSearchCriteria searchCriteria){
            Map<String,Object> filterMap = new HashMap<>();
            final SimpleDateFormat dateFormat = new SimpleDateFormat(ContractUtils.MMDDYYYY);
             if (searchCriteria != null && searchCriteria.getFilters() != null) {
                for (Container.Filter filter : searchCriteria.getFilters()) {

                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter filterValue = (SimpleStringFilter)filter;
                            if("type".equals(String.valueOf(filterValue.getPropertyId())) || "status".equals(String.valueOf(filterValue.getPropertyId())) || "category".equals(String.valueOf(filterValue.getPropertyId()))){
                                filterMap.put(String.valueOf(filterValue.getPropertyId()), filterValue.getFilterString());
                            }else{
                                String filterString = "%" + filterValue.getFilterString() + "%";
                                filterMap.put(String.valueOf(filterValue.getPropertyId()), filterString);
                            }

                    }else if (filter instanceof Compare) {
                    
                        Compare stringFilter = (Compare) filter;
                        
                    
                    if (stringFilter.getValue() instanceof Date) {

                        
                        Date filterString = (Date) stringFilter.getValue();
                        if ("startDate".equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                filterMap.put("startDatefrom", String.valueOf(dateFormat.format(filterString)));

                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                filterMap.put("startDateto", String.valueOf(dateFormat.format(filterString)));

                            }
                        } else if ("endDate".equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                filterMap.put("endDatefrom", String.valueOf(dateFormat.format(filterString)));

                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                filterMap.put("endDateto", String.valueOf(dateFormat.format(filterString)));

                            }
                        }
                    }

                } else if (filter instanceof Between) {

                    Between stringFilter = (Between) filter;                    
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();

                    if ("startDate".equals(stringFilter.getPropertyId())) {
                        filterMap.put("startDatefrom", String.valueOf(dateFormat.format(filterString)));
                        filterMap.put("startDateto", String.valueOf(dateFormat.format(filterString1)));

                    }

                    if ("endDate".equals(stringFilter.getPropertyId())) {
                        filterMap.put("endDatefrom", String.valueOf(dateFormat.format(filterString)));
                        filterMap.put("endDateto", String.valueOf(dateFormat.format(filterString1)));

                    }
                    
                    
                    }
                } 
            }
            return filterMap;
        }
        
    public int getSearchPsCountParentLookup(final ErrorfulFieldGroup searchItemForm, final BeanSearchCriteria search) {

        LOGGER.debug(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
        SimpleDateFormat format = new SimpleDateFormat(ContractUtils.MMDDYYYY);
        String psId;
        String psNo;
        String psName;
        String itemId;
        String itemNo;
        String itemName;
        int psTypeID = 0;

        int psStatusID = 0;

        LOGGER.debug("priceScheduleId=" + searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_ID).getValue());
        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_ID).getValue() == null || searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_ID).getValue().toString() == "") {
            psId = "";
        } else {
            psId = searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_ID).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_NO).getValue() == null) {
            psNo = "";
        } else {
            psNo = searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_NAME).getValue() == null) {
            psName = "";
        } else {
            psName = searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantUtil.ITEM_ID).getValue() == null) {
            itemId = "";
        } else {
            itemId = searchItemForm.getField(ConstantUtil.ITEM_ID).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantUtil.ITEM_NO).getValue() == null) {
            itemNo = "";
        } else {
            itemNo = searchItemForm.getField(ConstantUtil.ITEM_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantUtil.ITEM_NAME).getValue() == null) {
            itemName = "";
        } else {
            itemName = searchItemForm.getField(ConstantUtil.ITEM_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_TYPE).getValue() == null) {
            psTypeID = 0;
        } else {
            psTypeID = ((HelperDTO) searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_TYPE).getValue()).getId();
        }
        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_STATUS).getValue() == null) {
            psStatusID = 0;
        } else {
            psStatusID = ((HelperDTO) searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_STATUS).getValue()).getId();
        }

        if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemId)) {
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemNo)) {
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemName)) {
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        Map<String, Object> filterMap = new HashMap<>();

        if (search != null && search.getFilters() != null) {
            for (Container.Filter filter : search.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if ("priceScheduleType".equals(stringFilter.getPropertyId()) || "priceScheduleStatus".equals(stringFilter.getPropertyId()) || "priceScheduleCategory".equals(stringFilter.getPropertyId()) || "priceScheduleDesignation".equals(stringFilter.getPropertyId())
                            || "tradeClass".equals(stringFilter.getPropertyId())) {
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()), stringFilter.getFilterString());
                    } else {
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                    }

                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()) + "start", format.format(filterString));
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()) + "end", format.format(filterString1));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Integer && stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {

                            if (((Integer) stringFilter.getValue()) == 0) {

                            } else {
                                int value = (Integer) stringFilter.getValue();
                                filterMap.put(String.valueOf(stringFilter.getPropertyId()), value);

                            }
                        }else if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                filterMap.put(String.valueOf(stringFilter.getPropertyId()) + "start", format.format((Date)stringFilter.getValue()));
                        }else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                filterMap.put(String.valueOf(stringFilter.getPropertyId()) + "end", format.format((Date)stringFilter.getValue()));
                        }
                }
            }
        }
        List psSearchList = PsDetailsLocalServiceUtil.getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, 0, 0, null, null, true);
        if (psSearchList != null) {
            return psSearchList.size();
        } else {
            return 0;
        }

    }

    public List<SearchPriceScheduleDTO> getSearchPsListParentLookup(final ErrorfulFieldGroup searchItemForm, int start, int end, final List<OrderByColumn> orderByColumns, final BeanSearchCriteria search) {

        LOGGER.debug(" getCustomizedIfpSearchFormFromModel(ErrorfulFieldGroup searchItemForm)");
        SimpleDateFormat format = new SimpleDateFormat(ContractUtils.MMDDYYYY);
        String psId;
        String psNo;
        String psName;
        String itemId;
        String itemNo;
        String itemName;
        int psTypeID = 0;
        int psStatusID = 0;

        LOGGER.debug("priceScheduleId=" + searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_ID).getValue());
        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_ID).getValue() == null || searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_ID).getValue().toString() == "") {
            psId = "";
        } else {
            psId = searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_ID).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_NO).getValue() == null) {
            psNo = "";
        } else {
            psNo = searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_NAME).getValue() == null) {
            psName = "";
        } else {
            psName = searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantUtil.ITEM_ID).getValue() == null) {
            itemId = "";
        } else {
            itemId = searchItemForm.getField(ConstantUtil.ITEM_ID).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantUtil.ITEM_NO).getValue() == null) {
            itemNo = "";
        } else {
            itemNo = searchItemForm.getField(ConstantUtil.ITEM_NO).getValue()
                    .toString().trim();
        }
        if (searchItemForm.getField(ConstantUtil.ITEM_NAME).getValue() == null) {
            itemName = "";
        } else {
            itemName = searchItemForm.getField(ConstantUtil.ITEM_NAME).getValue()
                    .toString().trim();
        }

        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_TYPE).getValue() == null) {
            psTypeID = 0;
        } else {
            psTypeID = ((HelperDTO) searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_TYPE).getValue()).getId();
        }
        if (searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_STATUS).getValue() == null) {
            psStatusID = 0;
        } else {
            psStatusID = ((HelperDTO) searchItemForm.getField(ConstantUtil.PRICE_SCHEDULE_STATUS).getValue()).getId();
        }

        if (StringUtils.isNotBlank(psId)) {
            psId = psId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psNo)) {
            psNo = psNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }
        if (StringUtils.isNotBlank(psName)) {
            psName = psName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemId)) {
            itemId = itemId.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemNo)) {
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        if (StringUtils.isNotBlank(itemName)) {
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK,
                    CommonUtils.CHAR_PERCENT);
        }

        Map<String, Object> filterMap = new HashMap<>();

        if (search != null && search.getFilters() != null) {
            for (Container.Filter filter : search.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                      if ("priceScheduleType".equals(stringFilter.getPropertyId()) || "priceScheduleStatus".equals(stringFilter.getPropertyId()) || "priceScheduleCategory".equals(stringFilter.getPropertyId()) || "priceScheduleDesignation".equals(stringFilter.getPropertyId())
                              || "tradeClass".equals(stringFilter.getPropertyId())) {
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()), stringFilter.getFilterString());
                    } else {
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()), filterString);
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()) + "start", format.format(filterString));
                    filterMap.put(String.valueOf(stringFilter.getPropertyId()) + "end", format.format(filterString1));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Integer && stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {

                        if (((Integer) stringFilter.getValue()) == 0) {

                        } else {
                            int value = (Integer) stringFilter.getValue();
                            filterMap.put(String.valueOf(stringFilter.getPropertyId()), value);

                        }
                    } else if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()) + "start", format.format((Date) stringFilter.getValue()));
                    } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                        filterMap.put(String.valueOf(stringFilter.getPropertyId()) + "end", format.format((Date) stringFilter.getValue()));
                    }
                }
            }
        }

        String columnName = "ps.PS_MODEL_SID";
        String orderBy = "ASC";
        for (final Iterator<OrderByColumn> iterator = orderByColumns.iterator(); iterator
                .hasNext();) {
            final OrderByColumn orderByColumn = (OrderByColumn) iterator.next();

            if ("psSystemId".equals(orderByColumn.getName())) {
                columnName = "ps.PS_MODEL_SID";
            } else if (ConstantUtil.PRICE_SCHEDULE_ID.equals(orderByColumn.getName())) {
                columnName = "ps.PS_ID";
            } else if (ConstantUtil.PRICE_SCHEDULE_NO.equals(orderByColumn.getName())) {
                columnName = "ps.PS_NO";
            } else if (ConstantUtil.PRICE_SCHEDULE_NAME.equals(orderByColumn.getName())) {
                columnName = "ps.PS_NAME";
            } else if (ConstantUtil.PRICE_SCHEDULE_STATUS.equals(orderByColumn.getName())) {
                columnName = "status";
            } else if (ConstantUtil.PRICE_SCHEDULE_TYPE.equals(orderByColumn.getName())) {
                columnName = "type";
            } else if ("priceScheduleCategory".equals(orderByColumn.getName())) {
                columnName = "category";
            } else if ("priceScheduleStartDate".equals(orderByColumn.getName())) {
                columnName = "ps.PS_START_DATE";
            } else if ("priceScheduleEndDate".equals(orderByColumn.getName())) {
                columnName = "ps.PS_END_DATE";
            } else if ("priceScheduleDesignation".equals(orderByColumn.getName())) {
                columnName = "designation";
            } else if ("parentId".equals(orderByColumn.getName())) {
                columnName = "ps.PARENT_PS_ID";
            } else if ("parentName".equals(orderByColumn.getName())) {
                columnName = "ps.PARENT_PS_NAME";
            } else if ("tradeClass".equals(orderByColumn.getName())) {
                columnName = "trade";
            }

            if (orderByColumn.getType() == OrderByColumn.Type.ASC) {
                orderBy = "ASC";
            } else {
                orderBy = "DESC";
            }
        }

        List psSearchList = PsDetailsLocalServiceUtil.getPsSearchList(psId, psNo, psName, psStatusID, psTypeID, itemId, itemNo, itemName, filterMap, start, end, columnName, orderBy, false);

        return getCustomizedSearchPsListParentLookup(psSearchList);
    }

    List<SearchPriceScheduleDTO> getCustomizedSearchPsListParentLookup(List list) {
        List<SearchPriceScheduleDTO> result = new ArrayList<>();
        SearchPriceScheduleDTO dto;
        for (int i = 0; i < list.size(); i++) {
            dto = new SearchPriceScheduleDTO();
            Object[] obj = (Object[]) list.get(i);
            if (obj[Constants.ZERO] != null) {
                dto.setPriceScheduleSystemId(Integer.valueOf(String.valueOf(obj[0])));
                dto.setPsSystemId(String.valueOf(obj[0]));
            }

            if (obj[NumericConstants.ONE] != null) {
                dto.setPriceScheduleId(String.valueOf(obj[NumericConstants.ONE]));
            }

            if (obj[NumericConstants.TWO] != null) {
                dto.setPriceScheduleNo(String.valueOf(obj[NumericConstants.TWO]));
            }
            if (obj[NumericConstants.THREE] != null) {
                dto.setPriceScheduleName(String.valueOf(obj[NumericConstants.THREE]));
            }

            if (obj[NumericConstants.SEVEN] != null) {
                dto.setPriceScheduleStartDate((Date) obj[NumericConstants.SEVEN]);
            }

            if (obj[NumericConstants.EIGHT] != null) {
                dto.setPriceScheduleEndDate((Date) obj[NumericConstants.EIGHT]);
            }

            if (obj[NumericConstants.TEN] != null && StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.TEN]))) {

                String psId = String.valueOf(obj[NumericConstants.TEN]);
                dto.setParentId(psId);

            }

            if (obj[NumericConstants.ELEVEN] != null) {
                dto.setParentName(String.valueOf(obj[NumericConstants.ELEVEN]));
            }

            if (obj[NumericConstants.THIRTEEN] != null && !String.valueOf(obj[NumericConstants.THIRTEEN]).equals(ConstantUtil.SELECT_ONE)) {
                dto.setPriceScheduleType(String.valueOf(obj[NumericConstants.THIRTEEN]));
            }
            if (obj[NumericConstants.FOURTEEN] != null && !String.valueOf(obj[NumericConstants.FOURTEEN]).equals(ConstantUtil.SELECT_ONE)) {
                dto.setPriceScheduleStatus(String.valueOf(obj[NumericConstants.FOURTEEN]));
            }

            if (obj[NumericConstants.FIFTEEN] != null && !String.valueOf(obj[NumericConstants.FIFTEEN]).equals(ConstantUtil.SELECT_ONE)) {
                dto.setPriceScheduleCategory(String.valueOf(obj[NumericConstants.FIFTEEN]));
            }
            if (obj[NumericConstants.SIXTEEN] != null && !String.valueOf(obj[NumericConstants.SIXTEEN]).equals(ConstantUtil.SELECT_ONE)) {
                dto.setPriceScheduleDesignation(String.valueOf(obj[NumericConstants.SIXTEEN]));
            }
            if (obj[NumericConstants.SEVENTEEN] != null && !String.valueOf(obj[NumericConstants.SEVENTEEN]).equals(ConstantUtil.SELECT_ONE)) {
                dto.setTradeClass(String.valueOf(obj[NumericConstants.SEVENTEEN]));
            }

            if (obj[NumericConstants.EIGHTEEN] != null) {
                dto.setRecordLockStatus(String.valueOf(obj[NumericConstants.EIGHTEEN]));
            }

            result.add(dto);

        }

        return result;
    }

}
