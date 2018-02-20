/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.impl;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
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

    private static final CommonDao DAO = CommonImpl.getInstance();
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
    public int getCFPCount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getCFPcount(CFPCompanyDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getCFPCount:::list.size()" + list.size());
        return list.size();
    }

    @Override
    public List<CFPCompanyDTO> getCFPdetails(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<CFPCompanyDTO> resultLists;
        String query = QUERYUTILS.getCFPcount(CFPCompanyDTO);
        query = query + "  order by  cfp.CFP_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS FETCH NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<CFPCompanyDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;

    }

    @Override
    public int getCompanyCount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getCFPAttachedCompanies(CFPCompanyDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getCompanyCount:::list.size()" + list.size());
        return list.size();

    }

    @Override
    public List getCompanydetails(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<CFPCompanyDTO> resultLists;
        String query = QUERYUTILS.getCFPAttachedCompanies(CFPCompanyDTO);
        query = query + "  order by  cm.COMPANY_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS FETCH NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<CFPCompanyDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getIFPCount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc) {

        String query = QUERYUTILS.getIFPcount(IFPItemDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.size();
    }

    @Override
    public List getIFPdetails(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<IFPItemDTO> resultLists;
        String query = QUERYUTILS.getIFPcount(IFPItemDTO);
        query = query + "  order by  ifp.IFP_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS  FETCH  NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<IFPItemDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getPSCount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getPScount(PSIFPDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getPSCount:::list.size()" + list.size());
        return list.size();
    }

    @Override
    public List getPSdetails(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<PSIFPDTO> resultLists;
        String query = QUERYUTILS.getPScount(PSIFPDTO);
        query = query + "  order by  ps.PS_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "   ROWS FETCH NEXT  " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<PSIFPDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getRSCount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getRScount(RsIfpDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.size();
    }

    @Override
    public List getRsdetails(RsIfpDto RsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<RsIfpDto> resultLists;
        String query = QUERYUTILS.getRScount(RsIfpDto);
        query = query + "  order by  rs.RS_ID " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS FETCH NEXT   " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<RsIfpDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getAttachedItemCount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.GetIFPAttachedItems(IFPItemDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getAttachedItemCount:::list.size()" + list.size());
        return list.size();

    }

    @Override
    public List getIFPItemdetails(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<IFPItemDTO> resultLists;
        String query = QUERYUTILS.GetIFPAttachedItems(IFPItemDTO);
        query = query + "  order by  im.ITEM_NO " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS FETCH  NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<IFPItemDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getPSAttachedItemCount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getPSItemcount(PSIFPDTO);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getPSAttachedItemCount:::list.size()" + list.size());
        return list.size();
    }

    @Override
    public List getPSAttachedItemdetails(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<PSIFPDTO> resultLists;
        String query = QUERYUTILS.getPSItemcount(PSIFPDTO);
        query = query + "  order by im.ITEM_NO " + StringConstantsUtil.SPACE_OFFSET_SPACE + startIndex + "  ROWS  FETCH NEXT " + offset + StringConstantsUtil.ROWS_ONLY;
        resultLists = (List<PSIFPDTO>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getRSAttachedItemCount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc) {
        String query = QUERYUTILS.getRSAttachedItems(RsIfpDto);
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("getRSAttachedItemCount:::list.size()" + list.size());
        return list.size();
    }

    @Override
    public List getRsItemdetails(RsIfpDto RsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc) {
        List<RsIfpDto> resultLists;
        String query = QUERYUTILS.getRSAttachedItems(RsIfpDto);
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
