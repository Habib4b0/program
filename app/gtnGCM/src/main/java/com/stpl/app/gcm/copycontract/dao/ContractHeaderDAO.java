/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dao;

import com.stpl.app.gcm.copycontract.dto.CFPCompanyDTO;
import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.model.HelperTable;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import java.util.List;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public interface ContractHeaderDAO {

    List<HelperTable> getHelperTableList(DynamicQuery dynamicQuery);
            

    List getColumnNames(String tableName);

    public int getCFPCount(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc);

    public List<CFPCompanyDTO> getCFPdetails(CFPCompanyDTO removeDiscountDto, BeanSearchCriteria bsc,
            int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getCompanyCount(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc);

    public List getCompanydetails(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getIFPCount(IFPItemDTO ifpItemDto, BeanSearchCriteria bsc);

    public List getIFPdetails(IFPItemDTO ifpItemDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getPSCount(PSIFPDTO psIfpDto, BeanSearchCriteria bsc);

    public List getPSdetails(PSIFPDTO psIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getRSCount(RsIfpDto rsIfpDto, BeanSearchCriteria bsc);

    public List getRsdetails(RsIfpDto rsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getAttachedItemCount(IFPItemDTO ifpItemDto, BeanSearchCriteria bsc);

    public List getIFPItemdetails(IFPItemDTO ifpItemDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getPSAttachedItemCount(PSIFPDTO psIfpDto, BeanSearchCriteria bsc);

    public List getPSAttachedItemdetails(PSIFPDTO psIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getRSAttachedItemCount(RsIfpDto rsIfpDto, BeanSearchCriteria bsc);

    public List getRsItemdetails(RsIfpDto rsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public void updateCFP(List<Object> input);

    public void updateIFP(List<Object> input);

    public void updatePS(List<Object> input);

    public void updateRS(List<Object> input);
}
