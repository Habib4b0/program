/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.ui.layout;

import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.copycontract.ui.form.CopyContractform;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author kasiammal.m
 */
public class CopyContractWindow extends CustomWindow {

    ExtFilterTable resultTable;
    SessionDTO session;
    List<ContractSelectionDTO> selectedList;
    String Count = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */

    public CopyContractWindow(final SessionDTO session, List<ContractSelectionDTO> selectedList, String Count) throws SystemException {
        super("Copy Contract");
        this.session = session;
        this.selectedList = selectedList;
        this.Count = Count;
        init();

        setClosable(false);
        addStyleName("valo-theme-customwindow");
        setMinimizeToTray();
    }

    private void init() throws SystemException {

        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(Constants.ZERO);
        setPositionY(Constants.ZERO);
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        loadSessionDTO();
        setContent(new CopyContractform(this, selectedList, Count));
    }

    private void loadSessionDTO() {
    }
}
