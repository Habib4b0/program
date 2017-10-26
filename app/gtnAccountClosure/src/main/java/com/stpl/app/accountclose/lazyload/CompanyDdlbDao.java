/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.lazyload;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author alok.v
 */
public class CompanyDdlbDao implements DAO<HelperDTO>{
     private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanyDdlbDao.class);
    CommonLogic logic=new CommonLogic();
    HelperDTO companyDdlbDefault;
    HelperDTO selectedCompany;
    
    
      public CompanyDdlbDao(HelperDTO companyDdlbDefault) {
        try {
            this.companyDdlbDefault = companyDdlbDefault;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }  
   
      
       public CompanyDdlbDao(HelperDTO companyDdlbDefault, HelperDTO selectedCompany) {
        try {
            this.companyDdlbDefault = companyDdlbDefault;
            this.selectedCompany = selectedCompany;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public int count(SearchCriteria sc) {
        int count = 0;
        try {
           count = logic.getCompaniesCount(sc.getFilter());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    public List<HelperDTO> find(SearchCriteria sc, int startIndex, int offset, List<OrderByColumn> list) {
        List<HelperDTO> resultList = new ArrayList<HelperDTO>();
        try {
           resultList = logic.getCompanies(startIndex, startIndex + offset, sc.getFilter(), companyDdlbDefault, selectedCompany);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }
}
