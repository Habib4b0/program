/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.form;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class VariablesDdlbLookup extends Window {
    
    private static final long serialVersionUID = 1L;
    VerticalLayout layout=new VerticalLayout();
    private OptionGroup variables=new OptionGroup();
    String indicator=StringUtils.EMPTY;
    List<String> selectedVariables =new ArrayList<String>();
    /**
     *
     * @param indicator
     * @param selectedVariables
     */
    public VariablesDdlbLookup(String indicator,List<String> selectedVariables) {
        super("Variable Selection");
        this.indicator=indicator;
        this.selectedVariables=selectedVariables;
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        init();
    }

    public void init() {
        setClosable(true);
        setModal(true);
        Panel panel=new Panel();
        layout.addComponent(variables);
        panel.setContent(layout);
        setContent(panel);
        configureFields();
    }
    /**
     * Configure fields.
     */
    private void configureFields() {
        variables.setImmediate(true);
        variables.setMultiSelect(true);
        if ("BR".equals(indicator)) {
            variables.removeAllItems();
            variables.addItem("Accruals");
            variables.addItem("Accruals - Base Rate");
            variables.addItem("Accruals - Manual Adjustments");
            variables.addItem("Accruals - Reconciliation");
            variables.addItem("Accruals - Other");
            variables.addItem("Projected Deduction Amount");
            variables.addItem("Deductions");

            variables.addItem("Proj. Deduction Rate");
            variables.addItem("Deduction Rate");
            variables.addItem("Proj. Contract Sales Amt");
            variables.addItem("Contract Sales Amt");
            variables.addItem("Proj. Contract Sales units");
            variables.addItem("Contract Sales Units");

            variables.addItem("% Achieved - Accruals to Proj. Deductions");
            variables.addItem("Variance - Accruals to Proj. Deductions");
            variables.addItem("% Achieved - Accruals to Deductions");
            variables.addItem("Variance - Accruals to Deductions");
            variables.addItem("% Achieved - Deductions to Proj. Deductions");
            variables.addItem("Variance - Deductions Amt to Proj. Deductions Amt");
            variables.addItem("Variance - Deductions Rate to Proj. Deduction Rate");
            variables.addItem("% Achieved - Contract Sales to Proj. Contract Sales");
            variables.addItem("Variance - Contract Sales to Proj. Contract Sales");
            variables.addItem("% Achieved - Contract Units to Projec. Contract Units");
            variables.addItem("Variance - Contract Units to Proj. Contract Units");

            variables.select("Accruals");
            variables.select("Deductions");
            variables.select("Variance - Accruals to Deductions");
        }else{
            variables.removeAllItems();
            variables.addItem("Accruals");
            variables.addItem("Accruals - Base Rate");
            variables.addItem("Accruals - Manual Adjustments");
            variables.addItem("Accruals - Reconciliation");
            variables.addItem("Accruals - Other");
            variables.addItem("Deductions");
            variables.addItem("Variance - Accruals to Deductions");
            
            variables.select("Accruals");
            variables.select("Deductions");
            variables.select("Variance - Accruals to Deductions");
        }
        
    }

    public String getSelectedVariables() {
        String selectedValues=String.valueOf(variables.getValue());
        return selectedValues;
    }
    
}
