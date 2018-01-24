/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.abstractsearch.dto;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.HelperUtils;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Manasa
 */
public class SearchCriteriaDTO implements Serializable {
    
    private static final long serialVersionUID = 3584109928269363711L;
    
    public SearchCriteriaDTO(){
    	super();
    }
    
    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public String getText5() {
        return text5;
    }

    public void setText5(String text5) {
        this.text5 = text5;
    }

    public String getText6() {
        return text6;
    }

    public void setText6(String text6) {
        this.text6 = text6;
    }

    public String getText7() {
        return text7;
    }

    public void setText7(String text7) {
        this.text7 = text7;
    }

    public String getText8() {
        return text8;
    }

    public void setText8(String text8) {
        this.text8 = text8;
    }

    public HelperDTO getCombo1() {
        return combo1;
    }

    public void setCombo1(HelperDTO combo1) {
        this.combo1 = combo1;
    }

    public HelperDTO getCombo2() {
        return combo2;
    }

    public void setCombo2(HelperDTO combo2) {
        this.combo2 = combo2;
    }

    public HelperDTO getCombo3() {
        return combo3;
    }

    public void setCombo3(HelperDTO combo3) {
        this.combo3 = combo3;
    }

    public Date getDate1() {
        return date1 == null ? null : (Date) date1.clone();
    }

    public void setDate1(final Date date1) {
        this.date1 = date1 == null ? null : (Date) date1.clone();
    }

    public Date getDate2() {
        return date2 == null ? null : (Date) date2.clone();
    }

    public void setDate2(final Date date2) {
        this.date2 = date2 == null ? null : (Date) date2.clone();
    }

    public Date getDate3() {
        return date3 == null ? null : (Date) date3.clone();
    }

    public void setDate3(final Date date3) {
        this.date3 = date3 == null ? null : (Date) date3.clone();
    }

    public Date getDate4() {
        return date4 == null ? null : (Date) date4.clone();
    }

    public void setDate4(final Date date4) {
        this.date4 = date4 == null ? null : (Date) date4.clone();
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }
    
    
    private String text1 = HelperUtils.EMPTY;
    private String text2 = HelperUtils.EMPTY;
    private String text3 = HelperUtils.EMPTY;
    private String text4 = HelperUtils.EMPTY;
    private String text5 = HelperUtils.EMPTY;
    private String text6 = HelperUtils.EMPTY;
    private String text7 = HelperUtils.EMPTY;
    private String text8 = HelperUtils.EMPTY;
    
    private String option1 = HelperUtils.EMPTY;
    private String option2 = HelperUtils.EMPTY;
    
    private HelperDTO combo1 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private HelperDTO combo2 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private HelperDTO combo3 = new HelperDTO(0, com.stpl.app.adminconsole.util.ConstantsUtils.SELECT_ONE); 
    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;
    
}
