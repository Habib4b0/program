/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Map;
import java.util.Set;
import org.asi.ui.customwindow.CustomWindow;

/**
 *
 * @author Harlin
 */
public class TransferContractWindow extends CustomWindow {

    final Map<String, Set<String>> resultList;

    public TransferContractWindow(final Map<String, Set<String>> resultList) {
        super("Transfer Contract");
        this.resultList = resultList;
        init();
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init() {
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(Constants.ZERO);
        setPositionY(Constants.ZERO);
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setClosable(false);
        setContent(new TransferSelectionForm(resultList, this));
    }
}
