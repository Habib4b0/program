package com.stpl.app.cff.dao.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.CommonServiceImpl;
import com.stpl.app.cff.dao.FileManagementLogicDAO;
import com.stpl.app.model.DemandForecast;
import com.stpl.app.model.FileManagement;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.DemandForecastLocalServiceUtil;
import com.stpl.app.service.FileManagementLocalServiceUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO:  
/**
 * The Class FileManagementLogicDAOImpl.
 *
 * @author sriram
 */
public class FileManagementLogicDAOImpl implements FileManagementLogicDAO {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FileManagementLogicDAOImpl.class);

    /**
     * To get the list of forecasts from ForecastingMaster table.
     *
     * @param query the query
     * @return the forecast list
     * @throws SystemException
     * @throws Exception the exception
     */
    @Override
    public List getForecastList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getForecastList started with P1:DynamicQuery query");
        return ForecastingMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get the forecast details based on the passed parameters from
     * ForecastingMaster table.
     *
     * @param fileName the file name
     * @param version the version
     * @param fileType the file type
     * @param country the country
     * @return the forecast details
     */
    @Override
    public List getForecastDetails(final String fileName, final String version, final String fileType, final String country, int year) throws SystemException {
        LOGGER.debug("In query-getForecastDetails started with P1:String fileName= {} and P2:String version= {} and P3:String fileType={} and P4:String country={}", fileName, version, fileType, country);
        return CommonServiceImpl.getInstance().getDetailsResults(fileName, version, fileType, country, year);
    }

    /**
     * To get the list of files from FileManagement table based on query.
     *
     * @param query the query
     * @return the files list
     * @throws SystemException
     * @throws Exception the exception
     */
    @Override
    public List getFilesList(final DynamicQuery query) throws SystemException {
        LOGGER.debug("In query-getFilesList started with P1:DynamicQuery query");
        return FileManagementLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To update data in FileManagement table.
     *
     * @param file the file
     * @throws SystemException
     * @throws Exception the exception
     */
    @Override
    public void updateFiles(final FileManagement file) throws SystemException {
        LOGGER.debug("In query-updateFiles started with P1:FileManagement file");
        FileManagementLocalServiceUtil.updateFileManagement(file);
    }

    /**
     * To add Data in FileManagement table.
     *
     * @param file the file
     * @throws SystemException
     * @throws Exception the exception
     */
    @Override
    public void addFiles(final FileManagement file) throws SystemException {
        LOGGER.debug("In query-addFiles started with P1:FileManagement file");
        FileManagementLocalServiceUtil.addFileManagement(file);
    }

    @Override
    public void addForecastDetails(ForecastingMaster master) throws SystemException {
        ForecastingMasterLocalServiceUtil.addForecastingMaster(master);
    }
    @Override
    public void addDemandDetails(DemandForecast master) throws SystemException {
        DemandForecastLocalServiceUtil.addDemandForecast(master);
    }

    @Override
    public Object executeSelectQuery(String query, Object udc1, Object udc2) throws SystemException {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }
    
    @Override
       public List itemIrtQualifierNameList(final DynamicQuery ifpDynamicQuery) throws PortalException,SystemException{
        return ItemQualifierLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }
      

    @Override
    public List getBrandList(final DynamicQuery ifpDynamicQuery) throws PortalException, SystemException {
        return BrandMasterLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }
    
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of HelperTable
     * @return list of HelperTable
     * @throws SystemException 
     */
    @Override
    public List<HelperTable> getHelperTableList(final DynamicQuery query) throws PortalException,SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }
    /**
     * To get a list of companies from the companyMaster Table.
     *
     * @param query the query
     * @return List<CompanyMaster>
     * @throws SystemException
     * @throws Exception the exception
     * @table CompanyMaster
     */
    @Override
    public List getCompanyMasterList(final DynamicQuery query) throws SystemException {
        return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }
  
    @Override
    public int getFileManagementCount(DynamicQuery query) throws SystemException {
        return (int) FileManagementLocalServiceUtil.dynamicQueryCount(query);
    }
    
}
