/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.global.dto.CompanyMasterDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author mohamed
 */
public class CompanyAdditionDAO implements BeanDAO<CompanyMasterDTO> {

    /**
     * Logger used for CompanyAdditionDAO.
     */
    public String searchFields;
    public String searchValue;
    private static final Logger LOGGER = Logger.getLogger(CompanyAdditionDAO.class);
    private final CFPSearchLogic logic = new CFPSearchLogic();

    public CompanyAdditionDAO(String searchFields, String searchValue) throws SystemException {
        this.searchFields = searchFields;
        this.searchValue = searchValue;

    }

    /**
     * Method used for count SearchCriteria.
     *
     * @param searchCriteria the search criteria
     * @return the int
     */
    public int count(final BeanSearchCriteria searchCriteria) {

        try {
            LOGGER.debug("enters count()");
            final int count = logic.getCompanyAddtionCount(searchFields, searchValue,searchCriteria);

            LOGGER.debug("method count ends with count = " + count);
            return count;
        } catch (Exception ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            LOGGER.error(ex);

        }
        return 0;
    }

    public List<CompanyMasterDTO> find(final BeanSearchCriteria criteria, final int startIndex, final int offset, final List<OrderByColumn> columns) {
        List<CompanyMasterDTO> companyList = new ArrayList<CompanyMasterDTO>();
        try {
            LOGGER.debug("Enters getCompaniesForCFP find method with parameters startIndex=" + startIndex + ", offset=" + offset + ", columns size=" + columns.size());
          
                companyList = logic.getCompaniesForCFP(searchFields, searchValue, startIndex, offset, columns,criteria);
                LOGGER.debug("End of  getCompaniesForCFP method with contractList size=" + companyList.size());
          
        } catch (Exception ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
           

        }

        return companyList;
    }
}
