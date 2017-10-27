package com.stpl.app.contract.dashboard.ui.lazyload;

import com.stpl.app.contract.common.dto.SessionDTO;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.OrderByColumn;

import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

public class TempViewCompanyContainer implements BeanDAO<CFPCompanyDTO> {
	 private static final Logger LOGGER = Logger.getLogger(TempViewCompanyContainer.class);
        String record = StringUtils.EMPTY;
        SessionDTO sessionDTO;
        CFPSearchLogic cfpSearchLogic;
         public TempViewCompanyContainer(final SessionDTO sessionDTO){
         this.sessionDTO=sessionDTO;
         cfpSearchLogic=new CFPSearchLogic(this.sessionDTO);
         }

	    @Override
	    public int count(final BeanSearchCriteria searchCriteria) {
                return cfpSearchLogic.getLazySelectedCompaniesCount(searchCriteria,getRecord()); 
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
                return cfpSearchLogic.getLazySelectedCompaniesDeatils(startIndex, offset, true, list,searchCriteria,false,getRecord(), Boolean.FALSE);
	    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
            
}
