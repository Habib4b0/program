/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.ui;

import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * The Class UDCIncrementCheck.
 *
 * @author shrihariharan
 */
public class UDCIncrementCheck {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(UDCIncrementCheck.class);

    /**
     * Increment.
     *
     * @param value the value
     * @param listName the list name
     */
    public void increment(final String value, final String listName) throws SystemException {
        LOGGER.debug("entering increment method with parameter value=" + value + ", listName=" + listName);

        final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, value));
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.LIST_NAME, listName));
        final ContractHeaderLogic contractHeaderLogic = new ContractHeaderLogic();
        final List<HelperTable> list = contractHeaderLogic.getHelperTableList(helperDynamicQuery);

        if (!list.isEmpty()) {
            final HelperTable helper = list.get(0);
            if (helper.getRefCount() != -1) {
                helper.setRefCount(helper.getRefCount() + 1);
            }
            contractHeaderLogic.updateHelperTable(helper);
        }
        LOGGER.debug(" end of increment()");

    }

    /**
     * Decrement.
     *
     * @param value the value
     * @param listName the list name
     */
    public void decrement(final String value, final String listName) throws SystemException {
        LOGGER.debug("Entering decrement method with parameter value=" + value + ",listName=" + listName);
        final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.DESCRIPTION, value));
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(Constants.LIST_NAME, listName));
        final ContractHeaderLogic contractHeaderLogic = new ContractHeaderLogic();
        final List<HelperTable> list = contractHeaderLogic.getHelperTableList(helperDynamicQuery);
        if (!list.isEmpty()) {
            final HelperTable helper = list.get(0);
            if (helper.getRefCount() != -1) {
                if (helper.getRefCount() == Constants.ZERO) {
                    helper.setRefCount(0);
                } else {
                    helper.setRefCount(helper.getRefCount() - 1);
                }
            }
            contractHeaderLogic.updateHelperTable(helper);
        }
        LOGGER.debug(" end of decrement()");

    }
}
