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
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public interface ContractHeaderDAO {

    List<HelperTable> getHelperTableList(DynamicQuery dynamicQuery)
            throws SystemException;

    List getColumnNames(String tableName)
            throws SystemException;

    public int getCFPCount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc);

    public List<CFPCompanyDTO> getCFPdetails(CFPCompanyDTO removeDiscountDto, BeanSearchCriteria bsc,
            int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getCompanyCount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc);

    public List getCompanydetails(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getIFPCount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc);

    public List getIFPdetails(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getPSCount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc);

    public List getPSdetails(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getRSCount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc);

    public List getRsdetails(RsIfpDto RsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getAttachedItemCount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc);

    public List getIFPItemdetails(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getPSAttachedItemCount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc);

    public List getPSAttachedItemdetails(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public int getRSAttachedItemCount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc);

    public List getRsItemdetails(RsIfpDto RsIfpDto, BeanSearchCriteria bsc, int startIndex, int offset, List<OrderByColumn> list, String dbColumnName, boolean asc);

    public void updateCFP(List<Object> input);

    public void updateIFP(List<Object> input);

    public void updatePS(List<Object> input);

    public void updateRS(List<Object> input);
}
