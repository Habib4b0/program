/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import java.util.List;

/**
 *
 * @author Abhiram.Giri
 * @param <T>
 */
public class OriginalDataResult<T extends InterFaceDTO> implements DataResult<T> {

    List<T> dataResults;
    Object message;

    @Override
    public List<T> getDataResults() {
        return dataResults;
    }

    @Override
    public void setDataResults(List<T> dataResults) {
        this.dataResults = dataResults;
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
