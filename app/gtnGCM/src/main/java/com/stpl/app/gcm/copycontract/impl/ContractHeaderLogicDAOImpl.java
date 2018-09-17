/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.impl;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.copycontract.dao.ContractHeaderDAO;
import com.stpl.app.gcm.copycontract.dto.CFPCompanyDTO;
import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.app.gcm.impl.CompanyMasterImpl;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public class ContractHeaderLogicDAOImpl implements ContractHeaderDAO {

    private static final QueryUtils QUERYUTILS = new QueryUtils();
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractHeaderLogicDAOImpl.class);

    @Override
    public List<HelperTable> getHelperTableList(final DynamicQuery dynamicQuery) {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public List getColumnNames(String tableName) {
        return CompanyMasterImpl.getColumnNames(tableName);
    }

    @Override
    public int getCFPCount(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getCFPcount(cfpCompanyDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getCFPCount:::list.size() {}" , list.size());
        return list.size();
    }

    @Override
    public List<CFPCompanyDTO> getCFPdetails(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<CFPCompanyDTO> resultLists;
        String query = QUERYUTILS.getCFPcount(cfpCompanyDto);
        query = query + "  order by  cfp.CFP_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS FETCH NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<CFPCompanyDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;

    }

    @Override
    public int getCompanyCount(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getCFPAttachedCompanies(cfpCompanyDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getCompanyCount:::list.size() {} " , list.size());
        return list.size();

    }

    @Override
    public List getCompanydetails(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<CFPCompanyDTO> resultLists;
        String query = QUERYUTILS.getCFPAttachedCompanies(cfpCompanyDto);
        query = query + "  order by  cm.COMPANY_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS FETCH NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<CFPCompanyDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getIFPCount(IFPItemDTO ifpItemDto, BeanSearchCriteria bsc) {

        String query = QUERYUTILS.getIFPcount(ifpItemDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.size();
    }

    @Override
    public List getIFPdetails(IFPItemDTO ifpItemDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<IFPItemDTO> resultLists;
        String query = QUERYUTILS.getIFPcount(ifpItemDto);
        query = query + "  order by  ifp.IFP_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS  FETCH  NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<IFPItemDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getPSCount(PSIFPDTO psIfpDto, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getPScount(psIfpDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getPSCount:::list.size() {} " , list.size());
        return list.size();
    }

    @Override
    public List getPSdetails(PSIFPDTO psifpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<PSIFPDTO> resultLists;
        String query = QUERYUTILS.getPScount(psifpDto);
        query = query + "  order by  ps.PS_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "   ROWS FETCH NEXT  " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<PSIFPDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getRSCount(RsIfpDto rsIfpDto, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getRScount(rsIfpDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.size();
    }

    @Override
    public List getRsdetails(RsIfpDto rsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<RsIfpDto> resultLists;
        String query = QUERYUTILS.getRScount(rsIfpDto);
        query = query + "  order by  rs.RS_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS FETCH NEXT   " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<RsIfpDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getAttachedItemCount(IFPItemDTO ifpItemDTO, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getIfpAttachedItems(ifpItemDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getAttachedItemCount:::list.size() {} " , list.size());
        return list.size();

    }

    @Override
    public List getIFPItemdetails(IFPItemDTO ifpItemDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<IFPItemDTO> resultLists;
        String query = QUERYUTILS.getIfpAttachedItems(ifpItemDTO);
        query = query + "  order by  im.ITEM_NO " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS FETCH  NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<IFPItemDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getPSAttachedItemCount(PSIFPDTO psIfpDto, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getPSItemcount(psIfpDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getPSAttachedItemCount:::list.size() {} " , list.size());
        return list.size();
    }

    @Override
    public List getPSAttachedItemdetails(PSIFPDTO psIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<PSIFPDTO> resultLists;
        String query = QUERYUTILS.getPSItemcount(psIfpDto);
        query = query + "  order by im.ITEM_NO " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS  FETCH NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<PSIFPDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getRSAttachedItemCount(RsIfpDto rsIfpDto, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getRSAttachedItems(rsIfpDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getRSAttachedItemCount:::list.size() {} " , list.size());
        return list.size();
    }

    @Override
    public List getRsItemdetails(RsIfpDto rsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<RsIfpDto> resultLists;
        String query = QUERYUTILS.getRSAttachedItems(rsIfpDto);
        query = query + "  order by im.ITEM_NO " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS  FETCH NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<RsIfpDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public void updateCFP(List<Object> input) {
        String sql = SQlUtil.getQuery("Existing.saveCFP");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);

    }

    @Override
    public void updateIFP(List<Object> input) {
        String sql = SQlUtil.getQuery("Existing.saveIFP");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }

    @Override
    public void updatePS(List<Object> input) {
        String sql = SQlUtil.getQuery("Existing.savePS");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }

    @Override
    public void updateRS(List<Object> input) {
        String sql = SQlUtil.getQuery("Existing.saveRS");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }
}
