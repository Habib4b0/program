/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.supercode;

import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.vaadin.ui.TabSheet;

/**
 *
 * @author Abhiram.Giri
 */
public abstract class AbstractModule implements Module, GenerateAble, HasTabs {

    private TabSheet tabSheet;
    private String name;
    private int id;
    private DataSelectionDTO dataselectionDTO;
    protected SessionDTO sessionDTO;

    public AbstractModule(TabSheet tabSheet, int id, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) {
        this.tabSheet = tabSheet;
        this.id = id;
        this.name = name;
        this.dataselectionDTO = dataselectionDTO;
        this.sessionDTO = sessionDTO;
    }

    public TabSheet getTabSheet() {
        return tabSheet;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public DataSelectionDTO getDataselectionDTO() {
        return dataselectionDTO;
    }

    public SessionDTO getSessionDTO() {
        return sessionDTO;
    }

}
