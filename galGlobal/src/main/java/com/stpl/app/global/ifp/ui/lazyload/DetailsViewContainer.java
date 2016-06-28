/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.ifp.ui.lazyload;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.dto.IFPItemDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author Harlin
 */
public class DetailsViewContainer implements BeanDAO<IFPItemDTO> {
    private ErrorfulFieldGroup binder;
    private CustomePagedFilterTable table;
    private int count;
    /**
     * Record check box value
     */
    String record = StringUtils.EMPTY;
    /**
     * 
     * @param binder
     * @param table 
     */

    SessionDTO sessionDTO;
    private final IFPLogic ifpLogic;

    public DetailsViewContainer(final ErrorfulFieldGroup binder, final CustomePagedFilterTable table, final SessionDTO sessionDTO) {
        this.binder = binder;
        this.table = table;
        this.sessionDTO = sessionDTO;
        ifpLogic = new IFPLogic(this.sessionDTO);
    }
    public int count(BeanSearchCriteria sc) {
        try {
            count = ifpLogic.ifpViewCount(getRecord(),false);
           
            return count;
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing              
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
        return 0;
    }

    public List<IFPItemDTO> find(BeanSearchCriteria sc, int i, int i1, List<OrderByColumn> list) {
        try {
            final int currentPage = table.getCurrentPage() - 1;
            final int pageLength = table.getPageLength();
            int offset = i1;
            if (count < currentPage * pageLength + offset) {
                offset = (currentPage * pageLength + offset) - count;
            }
            return ifpLogic.getViewTableResult(pageLength*currentPage,(currentPage*pageLength+offset),binder,list,getRecord());
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing              
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
        return new ArrayList<IFPItemDTO>();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
    
}
