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
public interface DataResult<T extends InterFaceDTO> {
    List<T> getDataResults();
    void setDataResults(List<T> dataResults);
    
    Object getMessage();

    void setMessage(Object message);
}
