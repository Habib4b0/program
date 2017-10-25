/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.dto;

import com.vaadin.ui.Button;

/**
 *
 * @author Karthik.Raja
 */
public class ChartsDTO {
    private String queryName;
    private String procedure_Name;
    private String value;
    private String inserted_Date;
    private String index_Name;
    private String table_Name;
    private String fragmentation_Percentage;
    private String space_Used_Percentage;
    private String total_Rows;
    private String distributed_Rows;
    private Button text_Query;
    private String dataBase_Name;


    public String getProcedure_Name() {
        return procedure_Name;
    }

    public void setProcedure_Name(String procedure_Name) {
        this.procedure_Name = procedure_Name;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInserted_Date() {
        return inserted_Date;
    }

    public void setInserted_Date(String inserted_Date) {
        this.inserted_Date = inserted_Date;
    }

    public String getIndex_Name() {
        return index_Name;
    }

    public void setIndex_Name(String index_Name) {
        this.index_Name = index_Name;
    }

    public String getTable_Name() {
        return table_Name;
    }

    public void setTable_Name(String table_Name) {
        this.table_Name = table_Name;
    }

    public String getFragmentation_Percentage() {
        return fragmentation_Percentage;
    }

    public void setFragmentation_Percentage(String fragmentation_Percentage) {
        this.fragmentation_Percentage = fragmentation_Percentage;
    }

    public String getSpace_Used_Percentage() {
        return space_Used_Percentage;
    }

    public void setSpace_Used_Percentage(String space_Used_Percentage) {
        this.space_Used_Percentage = space_Used_Percentage;
    }

    public String getTotal_Rows() {
        return total_Rows;
    }

    public void setTotal_Rows(String total_Rows) {
        this.total_Rows = total_Rows;
    }

    public String getDistributed_Rows() {
        return distributed_Rows;
    }

    public void setDistributed_Rows(String distributed_Rows) {
        this.distributed_Rows = distributed_Rows;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public Button getText_Query() {
        return text_Query;
    }

    public void setText_Query(Button text_Query) {
        this.text_Query = text_Query;
    }

    public String getDataBase_Name() {
        return dataBase_Name;
    }

    public void setDataBase_Name(String dataBase_Name) {
        this.dataBase_Name = dataBase_Name;
    }

   
   
    
}
