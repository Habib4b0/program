/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author shrihariharan
 */
public class DetailSearchDTO {
    String no=StringUtils.EMPTY;
    String name=StringUtils.EMPTY;
    String type = StringUtils.EMPTY;
    String category = StringUtils.EMPTY;
    String status = StringUtils.EMPTY;
    Date startDate;
    Date endDate;

    
    public DetailSearchDTO(){
        
    }
    
    public DetailSearchDTO(String no,String name,String type,String category,String status,Date startDate,Date endDate){
        this.no=no;
        this.name=name;
        this.type=type;
        this.category=category;
        this.status=status;
        this.startDate=startDate;
        this.endDate=endDate;
    }
    
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
