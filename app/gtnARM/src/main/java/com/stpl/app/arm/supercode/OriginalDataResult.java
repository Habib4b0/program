/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.stpl.app.arm.common.CommonLogic;
import java.util.List;

/**
 *
 * @author Abhiram.Giri
 * @param <T>
 */
public class OriginalDataResult<T extends InterFaceDTO> implements DataResult<T> {

    private List<T> dataResults;
    private Object message;

    public OriginalDataResult() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public List<T> getDataResults() {
        return CommonLogic.getInstance().getArrayListCloned(dataResults);
    }

    @Override
    public void setDataResults(List<T> dataResults) {
        this.dataResults = CommonLogic.getInstance().getArrayListCloned(dataResults);
    }

    @Override
    public Object getMessage() {
        return message;
    }

    @Override
    public void setMessage(Object message) {
        this.message = message;
    }

}
