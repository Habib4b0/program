/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.impl;

import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.copycontract.dao.ContractHeaderDAO;
import com.stpl.app.gcm.copycontract.dto.CFPCompanyDTO;
import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public class ContractHeaderLogicDAOImpl implements ContractHeaderDAO {

    CommonDao DAO = CommonImpl.getInstance();
    QueryUtils queryUtils = new QueryUtils();
    public static final Logger LOGGER = Logger.getLogger(ContractHeaderLogicDAOImpl.class);

    public List<HelperTable> getHelperTableList(final DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List getColumnNames(String tableName) throws SystemException {
        return CompanyMasterLocalServiceUtil.getColumnNames(tableName);
    }

    public int getCFPCount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc) {
        String query = queryUtils.getCFPcount(CFPCompanyDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getCFPCount:::list.size()" + list.size());
        return list.size();
    }

    public List<CFPCompanyDTO> getCFPdetails(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<CFPCompanyDTO> resultLists = new ArrayList<CFPCompanyDTO>();
        String query = queryUtils.getCFPcount(CFPCompanyDTO);
        query = query + "  order by  cfp.CFP_ID " + "  OFFSET  " + startIndex + "  ROWS FETCH NEXT " + offset + " ROWS ONLY;";
        resultLists = (List<CFPCompanyDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;

    }

    public int getCompanyCount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc) {
        String query = queryUtils.getCFPAttachedCompanies(CFPCompanyDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getCompanyCount:::list.size()" + list.size());
        return list.size();

    }

    public List getCompanydetails(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<CFPCompanyDTO> resultLists = new ArrayList<CFPCompanyDTO>();
        String query = queryUtils.getCFPAttachedCompanies(CFPCompanyDTO);
        query = query + "  order by  cm.COMPANY_ID " + "  OFFSET  " + startIndex + "  ROWS FETCH NEXT " + offset + " ROWS ONLY;";
        resultLists = (List<CFPCompanyDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public int getIFPCount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc) {

        String query = queryUtils.getIFPcount(IFPItemDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.size();
    }

    public List getIFPdetails(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<IFPItemDTO> resultLists = new ArrayList<IFPItemDTO>();
        String query = queryUtils.getIFPcount(IFPItemDTO);
        query = query + "  order by  ifp.IFP_ID " + "  OFFSET  " + startIndex + "  ROWS FETCH NEXT " + offset + " ROWS ONLY;";
        resultLists = (List<IFPItemDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public int getPSCount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc) {
        String query = queryUtils.getPScount(PSIFPDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getPSCount:::list.size()" + list.size());
        return list.size();
    }

    public List getPSdetails(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<PSIFPDTO> resultLists = new ArrayList<PSIFPDTO>();
        String query = queryUtils.getPScount(PSIFPDTO);
        query = query + "  order by  ps.PS_ID " + "  OFFSET  " + startIndex + "  ROWS FETCH NEXT " + offset + " ROWS ONLY;";
        resultLists = (List<PSIFPDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public int getRSCount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc) {
        String query = queryUtils.getRScount(RsIfpDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.size();
    }

    public List getRsdetails(RsIfpDto RsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<RsIfpDto> resultLists = new ArrayList<RsIfpDto>();
        String query = queryUtils.getRScount(RsIfpDto);
        query = query + "  order by  rs.RS_ID " + "  OFFSET  " + startIndex + "  ROWS FETCH NEXT " + offset + " ROWS ONLY;";
        resultLists = (List<RsIfpDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public int getAttachedItemCount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc) {
        String query = queryUtils.GetIFPAttachedItems(IFPItemDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getAttachedItemCount:::list.size()" + list.size());
        return list.size();

    }

    public List getIFPItemdetails(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<IFPItemDTO> resultLists = new ArrayList<IFPItemDTO>();
        String query = queryUtils.GetIFPAttachedItems(IFPItemDTO);
        query = query + "  order by  im.ITEM_NO " + "  OFFSET  " + startIndex + "  ROWS FETCH NEXT " + offset + " ROWS ONLY;";
        resultLists = (List<IFPItemDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public int getPSAttachedItemCount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc) {
        String query = queryUtils.getPSItemcount(PSIFPDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getPSAttachedItemCount:::list.size()" + list.size());
        return list.size();
    }

    public List getPSAttachedItemdetails(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<PSIFPDTO> resultLists = new ArrayList<PSIFPDTO>();
        String query = queryUtils.getPSItemcount(PSIFPDTO);
        query = query + "  order by im.ITEM_NO " + "  OFFSET  " + startIndex + "  ROWS FETCH NEXT " + offset + " ROWS ONLY;";
        resultLists = (List<PSIFPDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public int getRSAttachedItemCount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc) {
        String query = queryUtils.getRSAttachedItems(RsIfpDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getRSAttachedItemCount:::list.size()" + list.size());
        return list.size();
    }

    public List getRsItemdetails(RsIfpDto RsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<RsIfpDto> resultLists = new ArrayList<RsIfpDto>();
        String query = queryUtils.getRSAttachedItems(RsIfpDto);
        query = query + "  order by im.ITEM_NO " + "  OFFSET  " + startIndex + "  ROWS FETCH NEXT " + offset + " ROWS ONLY;";
        resultLists = (List<RsIfpDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public void updateCFP(List<Object> input) {
        String sql = CustomSQLUtil.get("Existing.saveCFP");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);

    }

    public void updateIFP(List<Object> input) {
        String sql = CustomSQLUtil.get("Existing.saveIFP");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }

    public void updatePS(List<Object> input) {
        String sql = CustomSQLUtil.get("Existing.savePS");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }

    public void updateRS(List<Object> input) {
        String sql = CustomSQLUtil.get("Existing.saveRS");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }
}
