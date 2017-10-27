/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.singleliablity.logic;

import com.stpl.app.arm.balancesummaryreport.logic.AbstractBSummaryLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.CommonConstant;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class SingleLiablityLogic <AdjustmentDTO> extends AbstractBSummaryLogic {

    @Override
    protected List getQueryTableinput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getTableInput(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getCommonQueryName() {
        return "BSummaryCommonQuery-Pipeline";

    }

    @Override
    protected String getLoadDataQueryName() {
        return "BSummaryLoadData-Pipeline";
    }

    @Override
    protected String getCountQueryName() {
        return "BSummaryCount-Pipeline";
    }

    @Override
    protected String getTotalQueryName() {
        return "BSummaryLoadTotalData-Pipeline";
    }

    @Override
    protected String getExcelQueryName() {
        return "getBSummaryExcelQuery-Pipeline";
    }

    @Override
    protected DataResult getCustomizedData(SelectionDTO data, List list) {
        OriginalDataResult dataResult = new OriginalDataResult();
        dataResult.setDataResults(Collections.emptyList());
        return dataResult;
    }

    @Override
    public List bsExcelCustomizer(List resultList, Object[] object, List visibleColumns, boolean isFixedColumns, int interval, int discountColumnNeeded, AbstractSelectionDTO selection) throws IllegalAccessException, InvocationTargetException {
        return Collections.emptyList();
    }

    @Override
    public List getQueryTableinputparameter(SessionDTO sessionDTO) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    protected String getExcelTotalQueryName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
