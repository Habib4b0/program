/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.ui.lazyload;


import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author Harlin
 */
public class SelectedCompaniesContainer implements BeanDAO<CFPCompanyDTO> {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SelectedCompaniesContainer.class);
    SessionDTO sessionDTO;
    CFPSearchLogic cfpSearchLogic=new CFPSearchLogic();
    public SelectedCompaniesContainer(final SessionDTO sessionDTO){
        this.sessionDTO=sessionDTO;
        cfpSearchLogic=new CFPSearchLogic(this.sessionDTO);
    }

    @Override
    public int count(final BeanSearchCriteria searchCriteria) {
        int count = 0;
        count = cfpSearchLogic.getLazySelectedCompaniesDetailsCount(0, 0, false, null, searchCriteria, true);
        return count;
    }

    /**
     * Method used for get the results.
     * @param searchCriteria
     * @param startIndex
     * @param offset
     * @param list
     * @return 
     */
    @Override
    public List<CFPCompanyDTO> find(final BeanSearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        LOGGER.debug("Entering AvailableCompanyAddition find method :");
        return cfpSearchLogic.getLazySelectedCompaniesDeatils(startIndex, offset, false, list,searchCriteria,false,StringUtils.EMPTY, Boolean.FALSE);
}
}
