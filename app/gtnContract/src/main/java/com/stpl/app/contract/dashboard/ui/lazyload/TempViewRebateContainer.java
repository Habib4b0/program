package com.stpl.app.contract.dashboard.ui.lazyload;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.dashboard.dto.TempRebateDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

public class TempViewRebateContainer implements BeanDAO<TempRebateDTO> {

    private static final Logger LOGGER = Logger.getLogger(TempViewRebateContainer.class);
    /**
     * Record check box value
     */
    String record = StringUtils.EMPTY;
    private int count;
    SessionDTO sessionDTO;
    IfpLogic ifpLogic;

    public TempViewRebateContainer(final SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
        ifpLogic = new IfpLogic(this.sessionDTO);
    }

    @Override
    public int count(final BeanSearchCriteria searchCriteria) {
        List list = ifpLogic.getLazyItemRebateDeatils(0, 0, searchCriteria, true, getRecord(), Boolean.FALSE,null);
        count = Integer.valueOf(String.valueOf(list.get(0)));
        return list == null ? 0 : count;
    }

    /**
     * Method used for get the results.
     *
     * @param searchCriteria
     * @param startIndex
     * @param offset
     * @param list
     * @return
     */
    @Override
    public List<TempRebateDTO> find(final BeanSearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            List<Object[]> returnList = ifpLogic.getLazyItemRebateDeatils(startIndex, startIndex + offset, searchCriteria, false, getRecord(), Boolean.FALSE,list);
            return ifpLogic.getCustomizedRebateDTO(returnList, getRecord());
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }
        return new ArrayList<>();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
