/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.ui.lazyload;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
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
public class TempRebateContainer implements BeanDAO<TempRebateDTO> {
    private CustomePagedFilterTable table;
    private BeanItemContainer<TempRebateDTO> saveContainer;
    private int count;
    private boolean rpLevelChangeFlag;
    SessionDTO sessionDTO;
    IfpLogic ifpLogic;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(TempRebateContainer.class);
    /**
     * Record check box value
     */
    String record = StringUtils.EMPTY;
    
    public TempRebateContainer(final CustomePagedFilterTable table, final BeanItemContainer<TempRebateDTO> saveContainer, final boolean rpLevelChangeFlag, final SessionDTO sessionDTO){
        this.table=table;
        this.saveContainer=saveContainer;
        this.rpLevelChangeFlag = rpLevelChangeFlag;
        this.sessionDTO = sessionDTO;
        ifpLogic = new IfpLogic(this.sessionDTO);
    }
    public int count(BeanSearchCriteria sc) {
        List list = ifpLogic.getLazyItemRebateDeatils(0,0,sc,true,getRecord(), Boolean.FALSE,null);
        if(list!=null && !list.isEmpty()){
            count = Integer.valueOf(String.valueOf(list.get(0)));
        }
        return list==null ? 0 : count;
    }

    public List<TempRebateDTO> find(BeanSearchCriteria sc, int i, int i1, List<OrderByColumn> list) {
        try {
            if(saveContainer.size()>0){
                ifpLogic.saveToTempRebate(saveContainer.getItemIds(), rpLevelChangeFlag);
                saveContainer.removeAllItems();
            }
            List<Object[]> returnList =  ifpLogic.getLazyItemRebateDeatils(i,i1,sc,false,getRecord(), Boolean.FALSE,list);
            return ifpLogic.getCustomizedRebateDTO(returnList,getRecord());
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
