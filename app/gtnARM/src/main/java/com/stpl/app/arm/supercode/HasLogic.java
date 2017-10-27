/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

/**
 *
 * @author Abhiram.Giri
 */
public interface HasLogic<T extends InterFaceDTO> {

    public LogicAble<T> getSummaryLogic();
}
