/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

/**
 *
 * @author Abhiram.Giri
 * @param <T>
 */
public interface LogicAble<T extends InterFaceDTO> {

    int getCount(Criteria criteria);

    DataResult<T> getData(Criteria criteria);
}
