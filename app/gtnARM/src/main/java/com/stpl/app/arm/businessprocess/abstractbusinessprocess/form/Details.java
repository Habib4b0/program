/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.supercode.GenerateAble;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

/**
 *
 * @author Karthikeyan.Subraman
 */
public interface Details extends View, Component, GenerateAble, DefaultFocusable {

    public boolean saveAssets();
}
