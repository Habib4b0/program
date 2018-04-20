/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.supercode.GenerateAble;
import com.stpl.app.arm.supercode.HasSave;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import org.asi.container.ExtTreeContainer;

/**
 *
 * @author Karthikeyan.Subraman
 */
public interface Summary extends View, Component, GenerateAble, DefaultFocusable, HasSave {

    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainer();
}
