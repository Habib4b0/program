/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.ui.lazyload;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.global.dto.CFPCompanyDTO;
import com.stpl.app.contract.global.logic.CFPSearchLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author Harlin
 */
public class TempCompanyContainer implements BeanDAO<CFPCompanyDTO> {
    private CustomePagedFilterTable table;
    private BeanItemContainer<CFPCompanyDTO> saveContainer;
    private int count;
    SessionDTO sessionDTO;
    CFPSearchLogic cfpSearchLogic;
    String record = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(TempCompanyContainer.class);
    
    public TempCompanyContainer(final CustomePagedFilterTable table, final BeanItemContainer<CFPCompanyDTO> saveContainer, final SessionDTO sessionDTO){
        this.table=table;
        this.saveContainer=saveContainer;
        this.sessionDTO=sessionDTO;
        cfpSearchLogic=new CFPSearchLogic(this.sessionDTO);
    }
    public int count(BeanSearchCriteria sc) {
        count = cfpSearchLogic.getLazySelectedCompaniesCount(sc,getRecord());
        return count;
    }

    public List<CFPCompanyDTO> find(BeanSearchCriteria sc, int i, int i1, List<OrderByColumn> list) {
        try {
            if(saveContainer.size()>0){
                cfpSearchLogic.saveToTempCFP(saveContainer.getItemIds());
                saveContainer.removeAllItems();
            }
            return cfpSearchLogic.getLazySelectedCompaniesDeatils(i,i1,true, list,sc,false,getRecord(),Boolean.FALSE);
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004));
        }
         return new ArrayList<>(1);
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
    
}

