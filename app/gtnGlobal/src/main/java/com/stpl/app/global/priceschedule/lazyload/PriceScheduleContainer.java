/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.priceschedule.lazyload;

import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.global.rebateschedule.dto.IFPDetailsDTO;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author Manikanda.Prabu
 */
public class PriceScheduleContainer implements BeanDAO<PSIFPDTO> {

    String searchField = "";
    String value = "";

    public PriceScheduleContainer(String searchField, String value) {
        this.searchField = searchField;
        this.value = value;
    }

    @Override
    public int count(BeanSearchCriteria bsc) {
        try {
            return PSLogic.getLazyAvailableIFPCount(searchField, value, bsc);
        } catch (Exception ex) {
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

    @Override
    public List<PSIFPDTO> find(BeanSearchCriteria bsc, int i, int i1, List<OrderByColumn> list) {
        try {
            return PSLogic.getLazyAvailableIFPResults(searchField, value, list, bsc,i,i1);
        } catch (Exception ex) {
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
        return new ArrayList<PSIFPDTO>();
    }

}