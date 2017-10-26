/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Elangovan
 */
public class ForecastingMasterFinderImpl extends BasePersistenceImpl<ForecastingMaster> implements ForecastingMasterFinder {

	private static final Logger LOGGER = Logger.getLogger(ForecastingMasterFinderImpl.class);// Logger
																								// Declaration
	private final String EMPTY_STRING = Constants.EMPTY;

	public List getResults(String fileType, String country, String fileName, String type, String version, String forecastYear, String fromDate, String toDate) {
		LOGGER.debug("Entering getResults with P1:String fileType=" + fileType + " P2:String country=" + country + " P3:String fileName=" + fileName + " P4:String type" + type + " P5:String version"
				+ version + " P6:String forecastYear" + forecastYear + " P7:Date fromDate" + fromDate + " Date toDate" + toDate);
		List list = new ArrayList();
		Session session = null;
                String sqlString = StringUtils.EMPTY;
		try {
			session = openSession();
			
			sqlString = CustomSQLUtil.get("getResults");
			if ("Gross Trade Sales".equals(fileType)) {
				sqlString = sqlString.concat(" WHERE (FM.SOURCE LIKE 'FORESIGHT' OR FM.SOURCE LIKE 'FF_SALES')");
			} else if ("Vaccine Segmentation".equals(fileType)) {
				sqlString = sqlString.concat(" WHERE (FM.SOURCE LIKE 'FF_VACCINE')");
			}
			if (!"null".equals(country)) {
				sqlString = sqlString.concat(" AND FM.COUNTRY LIKE '").concat(country).concat("'");
			}
			if (!EMPTY_STRING.equals(fileName)) {
				sqlString = sqlString.concat(" AND FM.FORECAST_NAME LIKE '").concat(fileName).concat("'");
			}
			if (!EMPTY_STRING.equals(type)) {
				sqlString = sqlString.concat(" AND FM.SOURCE LIKE '").concat(type).concat("'");
			}
			if (!EMPTY_STRING.equals(version)) {
				sqlString = sqlString.concat(" AND FM.FORECAST_VER LIKE '").concat(version).concat("'");
			}
			if (!"null".equals(forecastYear)) {
				sqlString = sqlString.concat(" AND FM.FORECAST_YEAR LIKE '").concat(forecastYear).concat("'");
			}
			if (!"null".equals(fromDate)) {
				sqlString = sqlString.concat(" AND FM.FORECAST_DATE >= '").concat(fromDate).concat("'");
			}
			if (!"null".equals(toDate)) {
				sqlString = sqlString.concat(" AND FM.FORECAST_DATE <= '").concat(toDate).concat("'");
			}
			// LOGGER.debug("--------------->sqlString" + sqlString);
			SQLQuery sqlQuery = session.createSQLQuery(sqlString);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
                        LOGGER.error(sqlString);
		} finally {
			closeSession(session);
		}
		LOGGER.debug("getResults return List list=" + list.size());
		return list;
	}

	public List getDetailsResults(String fileName, String version, String fileType, String country,int year) {
		LOGGER.debug("Entering getDetailsResults with P1:String fileName=" + fileName + " P2:String version=" + version + " P3:String fileType=" + fileType + " P4:String country" + country);
		List list = new ArrayList();
		Session session = null;
                String sqlString = StringUtils.EMPTY;
		try {
			session = openSession();
			
			sqlString = CustomSQLUtil.get("getDetailsResults");
			sqlString = sqlString.concat("'").concat(fileName).concat("'");
			if ("Gross Trade Sales".equals(fileType) && "US".equals(country)) {
				sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FORESIGHT' OR FM.SOURCE LIKE 'LE_FORESIGHT')");
			} else if ("Gross Trade Sales".equals(fileType) && "PR".equals(country)) {
				sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_SALES')");
			} else if ("Vaccine Segmentation".equals(fileType)) {
				sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_VACCINE')");
			}
			sqlString = sqlString.concat(" AND FM.COUNTRY='").concat(country).concat("'");
                        
//                        sqlString = sqlString.concat(" AND FM.FORECAST_YEAR='").concat(String.valueOf(year)).concat("'");
                        if(version.contains("~"))
                        {
                           String[] versionArray=version.split("~");
                           sqlString=sqlString.concat(" AND ").concat(" (FM.FORECAST_VER='").concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
                        }else{
                          sqlString=sqlString.concat(" AND ").concat(" FM.FORECAST_VER='").concat(version).concat("'");  
                        }
                         sqlString=sqlString.concat(" ORDER BY FM.FORECAST_YEAR");
 			//LOGGER.debug("--- details query--------query------------>sqlString" + sqlString);
			SQLQuery sqlQuery = session.createSQLQuery(sqlString);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
                        LOGGER.error(sqlString);
		} finally {
			closeSession(session);
		}
		LOGGER.debug("getDetailsResults return List list=" + list.size());
		return list;
	}
        public Object executeSelectQuery(String query, Object udc1, Object udc2) {
		Session session = null;
		List<Object[]> returnList = new ArrayList<Object[]>();
		try {
			session = openSession();

			Query sqlQuery = session.createSQLQuery(query);
			LOGGER.debug("SIZE--------->" + sqlQuery.list().size());
			returnList = sqlQuery.list();

		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
                         LOGGER.error(query);
		} finally {
			closeSession(session);
		}
		return returnList;
	}
        public Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {
        Session session = null;

        try {
            session = openSession();

            Query sqlQuery = session.createSQLQuery(query);
            sqlQuery.executeUpdate();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
        }
        return true;
    }

    public Object executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {
        Session session = null;
        try {
            session = openSession();
            for (int i = 0; i < nmSalesList.size(); i++) {
                Object nmSalesProjection = nmSalesList.get(i);
                session.saveOrUpdate(nmSalesProjection);
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            closeSession(session);
        }
        return true;
    }
    
    public List getFileSearchResults(String query) {
		
		List list = new ArrayList();
		Session session = null;
		try {
			session = openSession();
			SQLQuery sqlQuery = session.createSQLQuery(query);
			list.addAll(sqlQuery.list());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
                        LOGGER.error(query);
		} finally {
			closeSession(session);
		}
		LOGGER.debug("getDetailsResults return List list=" + list.size());
		return list;
	}
    
}
