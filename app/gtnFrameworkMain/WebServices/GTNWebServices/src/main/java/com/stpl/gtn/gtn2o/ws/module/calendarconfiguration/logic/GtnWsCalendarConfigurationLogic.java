/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.calendarconfiguration.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.entity.calendarconfig.CalendarConfigDetails;
import com.stpl.gtn.gtn2o.ws.entity.calendarconfig.CalendarConfigMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.calendarconfiguration.controller.GtnWsCalendarConfigurationController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.calendarconfiguration.GtnWsCalendarConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.calendarconfiguration.GtnWsCalendarConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsCalendarConfigurationLogic {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCalendarConfigurationLogic.class);
	private final GtnWsCalendarConfigurationController controller;

	public GtnWsCalendarConfigurationLogic(GtnWsCalendarConfigurationController controller) {
		this.controller = controller;
	}

	public GtnWsCalendarConfigurationController getController() {
		return controller;
	}

	public String getQuery(String queryName) {
		return getController().getQuery(queryName);
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCalendarConfigurationTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCalendarConfigurationCount"
					: "getCalendarConfigurationResults";
			List<Object> inputlist = getCalendarConfigurationSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			inputlist.add(0, catalog);
			inputlist.add(0, catalog);
			List<Object[]> result = getController()
					.executeQuery(controller.getGtnWsSqlService().getQuery(inputlist, queryName));
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(result);
				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (GtnFrameworkGeneralException | NumberFormatException ex) {
			logger.error("Exception in getCalendarConfigurationTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCalendarConfigurationTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getCalendarConfigurationSearchInput(GtnWsSearchRequest searchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		String where = " WHERE ";
		String and = "";
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = searchCriteria.getFilterValue1();
				if (searchCriteria.isFilter() && "LIKE".equalsIgnoreCase(searchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				inputWhereConditions.append(where).append(and)
						.append(GtnCommonUtil.getWhereClauseForAColumn(searchCriteria.getExpression(),
								getCalendarConfigurationColumns(searchCriteria.getFieldId()), value,
								searchCriteria.getFilterValue2()));
				where = "";
				and = " AND ";
			}
			list.add(inputWhereConditions.toString());
			if (!searchRequest.isCount()) {
				list.add(getCalendarConfigurationSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getCalendarConfigurationSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getCalendarConfigurationSearchInput: ", ex);

		}
		return list;
	}

	private String getCalendarConfigurationSortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = "calendarName";
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getCalendarConfigurationColumns(property) + order;
	}

	private String getCalendarConfigurationColumns(String property) {
		String propertyValue = property.trim();
		String calendarConfigurationColumnName;
		switch (propertyValue) {
		case "calendarName":
		case "CCMainView_CalendarName":
		case "CCCrudView_CalendarName":
			calendarConfigurationColumnName = "CL.CALENDAR_NAME";
			break;
		case "calendarDescription":
		case "CCMainView_CalendarDescription":
		case "CCCrudView_CalendarDescription":
			calendarConfigurationColumnName = "CL.CALENDAR_DESCRIPTION";
			break;
		case "calendarYear":
		case "CCMainView_CalendarYear":
		case "CCCrudView_CalendarYear":
			calendarConfigurationColumnName = "CL.CALENDAR_YEAR";
			break;
		default:
			calendarConfigurationColumnName = getMoreCalendarConfigurationColumns(propertyValue);
		}
		return calendarConfigurationColumnName;
	}

	private String getMoreCalendarConfigurationColumns(String property) {
		String columnName;
		switch (property) {
		case "CCMainView_Country":
		case "CCCrudView_Country":
			columnName = "HC.HELPER_TABLE_SID";
			break;
		case "createdBy":
			columnName = "usr.lastName + ' ' + usr.middleName + ' ' + usr.firstName";
			break;
		case "createdDate":
			columnName = "CL.CREATED_DATE";
			break;
		case "modifiedBy":
			columnName = "usr1.lastName + ' ' + usr1.middleName + ' ' + usr1.firstName";
			break;
		case "modifiedDate":
			columnName = "CL.MODIFIED_DATE";
			break;
		case "CCCrudView_DefaultHolidays":
			columnName = "CL.DEFAULT_HOLIDAYS";
			break;
		default:
			columnName = "CL.CALENDAR_NAME";
		}
		return columnName;
	}

	public void deleteCalendarConfiguration(GtnWsCalendarConfigurationRequest ccRequest,
			GtnWsCalendarConfigurationResponse ccResponse) throws GtnFrameworkGeneralException {
		ccResponse.setSuccess(true);
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			if (validateBeforeDelete(ccRequest.getCalendarId(), session)) {
				ccResponse.setSuccess(false);
				ccResponse.setMessageHeader(GtnFrameworkCommonStringConstants.ERROR);
				ccResponse.setMessage("Calendar cannot be deleted as it is associated to Company");
			} else {
				CalendarConfigMaster calendarConfigMaster = session.load(CalendarConfigMaster.class,
						ccRequest.getCalendarId());
				deletCalendarConfigDetails(calendarConfigMaster, session);
				session.delete(calendarConfigMaster);
				tx.commit();
				ccResponse.setMessage(ccRequest.getCalendarName() + " has been deleted Successfully.");
			}
		} catch (Exception e) {
			tx.rollback();
			ccResponse.setSuccess(false);
			ccResponse.setMessageHeader(GtnFrameworkCommonStringConstants.ERROR);
			ccResponse.setMessage("Calendar cannot be deleted as it is associated to Company");
			logger.error("Exception in deleteCalendarConfiguration", e);
			throw new GtnFrameworkGeneralException("Exception in deleteCalendarConfiguration ", e);
		} finally {
			session.close();
		}
	}

	public boolean validateBeforeDelete(int contractId, Session session) {
		return isPresentinAutoCLOSE(contractId, session) || isPresentinCLOSE(contractId, session);
	}

	public boolean isPresentinAutoCLOSE(int contractId, Session session) {
		String checkIncompanyMaster = "select count(*) from COMPANY_FINANCIAL_CLOSE_AUTO where  CALENDAR  ="
				+ contractId;
		@SuppressWarnings("unchecked")
		List<Object> count = session.createSQLQuery(checkIncompanyMaster).list();
		return ((Integer) count.get(0)) > 0;
	}

	public boolean isPresentinCLOSE(int contractId, Session session) {
		String checkIncompanyMaster = "select count(*) from COMPANY_FINANCIAL_CLOSE where CALENDAR  = " + contractId;
		@SuppressWarnings("unchecked")
		List<Object> count = session.createSQLQuery(checkIncompanyMaster).list();
		return ((Integer) count.get(0)) > 0;
	}

	public void saveCalendarConfiguration(GtnWsCalendarConfigurationRequest ccRequest,
			GtnWsCalendarConfigurationResponse ccResponse) throws GtnFrameworkGeneralException {
		ccResponse.setSuccess(true);
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CalendarConfigMaster calendarConfigMaster = new CalendarConfigMaster();
			calendarConfigMaster.setCreatedDate(new Date());
			calendarConfigMaster.setCreatedBy(ccRequest.getUserId());
			if (ccRequest.getCalendarId() != 0) {
				calendarConfigMaster = session.load(CalendarConfigMaster.class, ccRequest.getCalendarId());
				deletCalendarConfigDetails(calendarConfigMaster, session);
				calendarConfigMaster.setModifiedDate(new Date());
				calendarConfigMaster.setModifiedBy(ccRequest.getUserId());
			}
			calendarConfigMaster.setCalendarName(ccRequest.getCalendarName());
			calendarConfigMaster.setCalendarDescription(ccRequest.getCalendarDescription());
			calendarConfigMaster.setCalendarYear(ccRequest.getCalendarYear());
			calendarConfigMaster.setCountry(ccRequest.getCountry());
			calendarConfigMaster.setDefaultHolidays(ccRequest.isDefaultHolidays());
			session.saveOrUpdate(calendarConfigMaster);
			saveCalendarConfigDetails(session, ccRequest, calendarConfigMaster);
			tx.commit();
			GtnWsRecordBean bean = new GtnWsRecordBean();
			ccResponse.setCalendarBean(bean);
			bean.setProperties(new ArrayList<>());
			bean.getProperties().add(ccRequest.getCalendarName());
			bean.getProperties().add(ccRequest.getCalendarDescription());
			bean.getProperties().add(ccRequest.getCalendarYear());
			bean.getProperties().add("");
			bean.getProperties().add(calendarConfigMaster.getCreatedDate());
			bean.getProperties().add("");
			bean.getProperties().add(calendarConfigMaster.getModifiedDate());
			bean.getProperties().add(calendarConfigMaster.getCalendarConfigMasterSid());
			bean.getProperties().add(ccRequest.isDefaultHolidays());
			bean.getProperties().add(ccRequest.getCountryCode());
			ccResponse.setMessage("Saved Successfully");
		} catch (Exception e) {
			tx.rollback();
			ccResponse.setSuccess(false);
			ccResponse.setMessage(ccRequest.getCalendarName() + " has not been saved.");
			logger.error("Exception in saveCalendarConfiguration", e);
			throw new GtnFrameworkGeneralException("Exception in save CalendarConfiguration ", e);
		} finally {
			session.close();
		}
	}

	private void saveCalendarConfigDetails(Session session, GtnWsCalendarConfigurationRequest cdRequest,
			CalendarConfigMaster calendarConfigMaster) throws GtnFrameworkGeneralException {
		try {
			if (cdRequest.getHolidays() != null) {
				for (Date date : cdRequest.getHolidays()) {
					CalendarConfigDetails calendarConfigDetails = new CalendarConfigDetails();
					calendarConfigDetails.setCalendarConfigMaster(calendarConfigMaster);
					calendarConfigDetails.setHolidaysPeriodDate(date);
					session.saveOrUpdate(calendarConfigDetails);
				}
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save CalendarConfigDetails", e);
		}
	}

	@SuppressWarnings("unchecked")
	public void deletCalendarConfigDetails(CalendarConfigMaster calendarConfigMaster, Session session)
			throws GtnFrameworkGeneralException {
		try {
			Criteria cr = session.createCriteria(CalendarConfigDetails.class)
					.add(Restrictions.eq("calendarConfigMaster", calendarConfigMaster));
			List<CalendarConfigDetails> results = cr.list();
			if (results != null && !results.isEmpty()) {
				for (CalendarConfigDetails calendarConfigDetails : results) {
					session.delete(calendarConfigDetails);
				}
				session.flush();
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in deleting CalendarConfigDetails", e);
		}
	}

	@SuppressWarnings("unchecked")
	public void getCalendarConfigurationHolidays(GtnWsCalendarConfigurationRequest ccRequest,
			GtnWsCalendarConfigurationResponse ccResponse) throws GtnFrameworkGeneralException {
		ccResponse.setSuccess(true);
		List<Date> holidayList = new ArrayList<>();

		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(CalendarConfigDetails.class).add(Restrictions
					.eq("calendarConfigMaster", session.load(CalendarConfigMaster.class, ccRequest.getCalendarId())));
			List<CalendarConfigDetails> results = cr.list();
			if (results != null && !results.isEmpty()) {
				for (CalendarConfigDetails calendarConfigDetails : results) {
					holidayList.add(calendarConfigDetails.getHolidaysPeriodDate());
				}
			}
			ccResponse.setHolidays(holidayList);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getCalendarConfigurationHolidays", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getCalendarConfigurationCalendarName(GtnWsCalendarConfigurationRequest ccRequest,
			GtnWsCalendarConfigurationResponse ccResponse) throws GtnFrameworkGeneralException {
		ccResponse.setSuccess(true);
		String calendarName=ccRequest.getCalendarName();
		int calendarId=ccRequest.getCalendarId();
		boolean calendarNameAlreadyExists=false;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(CalendarConfigMaster.class);
			List<CalendarConfigMaster> resultList = cr.list();
			calendarNameAlreadyExists = checkCalendarNameAlreadyExists(calendarName, calendarId,resultList);
			ccResponse.setCalendarNameExists(calendarNameAlreadyExists);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getCalendarConfigurationHolidays", e);
		}
	}

	private boolean checkCalendarNameAlreadyExists(String calendarName, int calendarId,List<CalendarConfigMaster> resultList) {
		boolean calendarNameInModeCheck;
		if((calendarId==0)){
			calendarNameInModeCheck = checkForCalendarNameInAddMode(calendarName,resultList);
		}
		else{
			calendarNameInModeCheck = checkForCalendarNameNonAddMode(calendarName, calendarId,resultList);
		}
		return calendarNameInModeCheck;
	}

	private boolean checkForCalendarNameNonAddMode(String calendarName, int calendarId,List<CalendarConfigMaster> resultList) {
		boolean calendarNameInNonAddModeCheck = false;
		String tempCalendarName ="";
		for (CalendarConfigMaster calendarConfigMaster : resultList) {
			if(calendarId==calendarConfigMaster.getCalendarConfigMasterSid()){
				tempCalendarName=calendarConfigMaster.getCalendarName();
			}
		}
			if (!tempCalendarName.equals(calendarName)) {
				calendarNameInNonAddModeCheck = checkForCalendarNameInAddMode(calendarName, resultList);
			}
		return calendarNameInNonAddModeCheck;
	}

	private boolean checkForCalendarNameInAddMode(String calendarName,List<CalendarConfigMaster> resultList) {
		boolean calendarNameInAddModeCheck = false;
		for (CalendarConfigMaster calendarConfigMaster : resultList) {
			if(calendarName.equalsIgnoreCase(calendarConfigMaster.getCalendarName())){
				calendarNameInAddModeCheck=true;
				break;
				}
			}
		return calendarNameInAddModeCheck;
	}
}
