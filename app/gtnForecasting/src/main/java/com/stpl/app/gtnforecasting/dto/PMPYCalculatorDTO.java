/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class PMPYCalculatorDTO.
 *
 * @author lokeshwari
 */
public class PMPYCalculatorDTO implements Serializable {

    
    
    protected transient Map<String,String> properties=new HashMap<>();

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
    
    
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -1040418954290545878L;

    /**
     * The Constant COMMA.
     */
    public static final String COMMA = ",";

    /**
     * The quaterThree2012.
     */
    private String quaterThree2012=StringUtils.EMPTY;

    /**
     * The quaterTwo2012.
     */
    private String quaterTwo2012=StringUtils.EMPTY;

    /**
     * The quaterOne2012.
     */
    private String quaterOne2012=StringUtils.EMPTY;

    /**
     * The quaterFour2011.
     */
    private String quaterFour2011=StringUtils.EMPTY;

    /**
     * The quaterThree2011.
     */
    private String quaterThree2011=StringUtils.EMPTY;

    /**
     * The quaterTwo2011.
     */
    private String quaterTwo2011=StringUtils.EMPTY;

    /**
     * The type.
     */
    private String type=StringUtils.EMPTY;

    /**
     * The q1y1 ch.
     */
    private Double q1y1Ch=0.0;

    /**
     * The q2y1 ch.
     */
    private Double q2y1Ch=0.0;

    /**
     * The q3y1 ch.
     */
    private Double q3y1Ch=0.0;

    /**
     * The q4y1 ch.
     */
    private Double q4y1Ch=0.0;

    /**
     * The q1y2 ch.
     */
    private Double q1y2Ch=0.0;

    /**
     * The q2y2 ch.
     */
    private Double q2y2Ch=0.0;

    /**
     * The q3y2 ch.
     */
    private Double q3y2Ch=0.0;

    /**
     * The q4y2 ch.
     */
    private Double q4y2Ch=0.0;

    /**
     * The q1y3 ch.
     */
    private Double q1y3Ch=0.0;

    /**
     * The q2y3 ch.
     */
    private Double q2y3Ch=0.0;

    /**
     * The q3y3 ch.
     */
    private Double q3y3Ch=0.0;
    /**
     * The q4y3 ch.
     */
    private Double q4y3Ch=0.0;

    /**
     * The y1 ch.
     */
    private Double y1Ch=0.0;

    /**
     * The y2 ch.
     */
    private Double y2Ch=0.0;

    /**
     * The y3 ch.
     */
    private Double y3Ch=0.0;

    /**
     * The s1y1 ch.
     */
    private Double s1y1Ch=0.0;

    /**
     * The s2y1 ch.
     */
    private Double s2y1Ch=0.0;

    /**
     * The s1y2 ch.
     */
    private Double s1y2Ch=0.0;

    /**
     * The s2y2 ch.
     */
    private Double s2y2Ch=0.0;

    /**
     * The s1y3 ch.
     */
    private Double s1y3Ch=0.0;

    /**
     * The s2y3 ch.
     */
    private Double s2y3Ch=0.0;

    /**
     * The q1y1 tp.
     */
    private Double q1y1Tp=0.0;

    /**
     * The q2y1 tp.
     */
    private Double q2y1Tp=0.0;

    /**
     * The q3y1 tp.
     */
    private Double q3y1Tp=0.0;
    /**
     * The q4y1 tp.
     */
    private Double q4y1Tp=0.0;

    /**
     * The q1y2 tp.
     */
    private Double q1y2Tp=0.0;

    /**
     * The q2y2 tp.
     */
    private Double q2y2Tp=0.0;

    /**
     * The q3y2 tp.
     */
    private Double q3y2Tp=0.0;

    /**
     * The q4y2 tp.
     */
    private Double q4y2Tp=0.0;

    /**
     * The q1y3 tp.
     */
    private Double q1y3Tp=0.0;

    /**
     * The q2y3 tp.
     */
    private Double q2y3Tp=0.0;

    /**
     * The q3y3 tp.
     */
    private Double q3y3Tp=0.0;

    /**
     * The q4y3 tp.
     */
    private Double q4y3Tp=0.0;
    /**
     * The y1 tp.
     */
    private Double y1Tp=0.0;

    /**
     * The y2 tp.
     */
    private Double y2Tp=0.0;

    /**
     * The y3 tp.
     */
    private Double y3Tp=0.0;

    /**
     * The s1y1 tp.
     */
    private Double s1y1Tp=0.0;
    /**
     * The s2y1 tp.
     */
    private Double s2y1Tp=0.0;

    /**
     * The s1y2 tp.
     */
    private Double s1y2Tp=0.0;

    /**
     * The s2y2 tp.
     */
    private Double s2y2Tp=0.0;

    /**
     * The s1y3 tp.
     */
    private Double s1y3Tp=0.0;

    /**
     * The s2y3 tp.
     */
    private Double s2y3Tp=0.0;

    /**
     * The pmpy contract holder.
     */
    private String pmpyContractHolder = StringUtils.EMPTY;

    /**
     * The trading partner no.
     */
    private String tradingPartnerNo = StringUtils.EMPTY;

    /**
     * The trading partner name.
     */
    private String tradingPartnerName = StringUtils.EMPTY;

    /**
     * The frequency.
     */
    private String frequency = StringUtils.EMPTY;

    /**
     * The history.
     */
    private String history = StringUtils.EMPTY;

    /**
     * The contract.
     */
    private String contract;

    /**
     * The trading partner.
     */
    private String tradingPartner = StringUtils.EMPTY;

    /**
     * The contract holder list.
     */
    private List<PMPYCalculatorDTO> contrachHolderList;

    /**
     * The trading partner list.
     */
    private List<PMPYCalculatorDTO> tradingPartnerList;

    /**
     * The payment term.
     */
    private String paymentTerm = StringUtils.EMPTY;

    /**
     * Gets the quaterThree2012.
     *
     * @return the quaterThree2012
     */
    public String getQ32012() {
        return quaterThree2012;
    }

    /**
     * Sets the quaterThree2012.
     *
     * @param quarterThree2012 the quaterThree2012 to set
     */
    public void setQ32012(final String quarterThree2012) {
        this.quaterThree2012 = quarterThree2012;
    }

    /**
     * Gets the quaterTwo2012.
     *
     * @return the quaterTwo2012
     */
    public String getQ22012() {
        return quaterTwo2012;
    }

    /**
     * Sets the quaterTwo2012.
     *
     * @param quarterTwo2012 the quaterTwo2012 to set
     */
    public void setQ22012(final String quarterTwo2012) {
        this.quaterTwo2012 = quarterTwo2012;
    }

    /**
     * Gets the quaterOne2012.
     *
     * @return the quaterOne2012
     */
    public String getQ12012() {
        return quaterOne2012;
    }

    /**
     * Sets the quaterOne2012.
     *
     * @param quarterOne2012 the quaterOne2012 to set
     */
    public void setQ12012(final String quarterOne2012) {
        this.quaterOne2012 = quarterOne2012;
    }

    /**
     * Gets the quaterFour2011.
     *
     * @return the quaterFour2011
     */
    public String getQ42011() {
        return quaterFour2011;
    }

    /**
     * Sets the quaterFour2011.
     *
     * @param quarterFour2011 the quaterFour2011 to set
     */
    public void setQ42011(final String quarterFour2011) {
        this.quaterFour2011 = quarterFour2011;
    }

    /**
     * Gets the quaterThree2011.
     *
     * @return the quaterThree2011
     */
    public String getQ32011() {
        return quaterThree2011;
    }

    /**
     * Sets the quaterThree2011.
     *
     * @param quarterThree2011 the quaterThree2011 to set
     */
    public void setQ32011(final String quarterThree2011) {
        this.quaterThree2011 = quarterThree2011;
    }

    /**
     * Gets the q2_2011.
     *
     * @return the q2_2011
     */
    public String getQ22011() {
        return quaterTwo2011;
    }

    /**
     * Sets the q2_2011.
     *
     * @param quarterTwo2011 the q2_2011 to set
     */
    public void setQ22011(final String quarterTwo2011) {
        this.quaterTwo2011 = quarterTwo2011;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the q1y1 ch.
     *
     * @return the q1y1Ch
     */
    public Double getQ1y1Ch() {
        return q1y1Ch;
    }

    /**
     * Sets the q1y1 ch.
     *
     * @param q1y1Ch the q1y1Ch to set
     */
    public void setQ1y1Ch(final Double q1y1Ch) {
        this.q1y1Ch = q1y1Ch;
    }

    /**
     * Gets the q2y1 ch.
     *
     * @return the q2y1Ch
     */
    public Double getQ2y1Ch() {
        return q2y1Ch;
    }

    /**
     * Sets the q2y1 ch.
     *
     * @param q2y1Ch the q2y1Ch to set
     */
    public void setQ2y1Ch(final Double q2y1Ch) {
        this.q2y1Ch = q2y1Ch;
    }

    /**
     * Gets the q3y1 ch.
     *
     * @return the q3y1Ch
     */
    public Double getQ3y1Ch() {
        return q3y1Ch;
    }

    /**
     * Sets the q3y1 ch.
     *
     * @param q3y1Ch the q3y1Ch to set
     */
    public void setQ3y1Ch(final Double q3y1Ch) {
        this.q3y1Ch = q3y1Ch;
    }

    /**
     * Gets the q4y1 ch.
     *
     * @return the q4y1Ch
     */
    public Double getQ4y1Ch() {
        return q4y1Ch;
    }

    /**
     * Sets the q4y1 ch.
     *
     * @param q4y1Ch the q4y1Ch to set
     */
    public void setQ4y1Ch(final Double q4y1Ch) {
        this.q4y1Ch = q4y1Ch;
    }

    /**
     * Gets the q1y2 ch.
     *
     * @return the q1y2Ch
     */
    public Double getQ1y2Ch() {
        return q1y2Ch;
    }

    /**
     * Sets the q1y2 ch.
     *
     * @param q1y2Ch the q1y2Ch to set
     */
    public void setQ1y2Ch(final Double q1y2Ch) {
        this.q1y2Ch = q1y2Ch;
    }

    /**
     * Gets the q2y2 ch.
     *
     * @return the q2y2Ch
     */
    public Double getQ2y2Ch() {
        return q2y2Ch;
    }

    /**
     * Sets the q2y2 ch.
     *
     * @param q2y2Ch the q2y2Ch to set
     */
    public void setQ2y2Ch(final Double q2y2Ch) {
        this.q2y2Ch = q2y2Ch;
    }

    /**
     * Gets the q3y2 ch.
     *
     * @return the q3y2Ch
     */
    public Double getQ3y2Ch() {
        return q3y2Ch;
    }

    /**
     * Sets the q3y2 ch.
     *
     * @param q3y2Ch the q3y2Ch to set
     */
    public void setQ3y2Ch(final Double q3y2Ch) {
        this.q3y2Ch = q3y2Ch;
    }

    /**
     * Gets the q4y2 ch.
     *
     * @return the q4y2Ch
     */
    public Double getQ4y2Ch() {
        return q4y2Ch;
    }

    /**
     * Sets the q4y2 ch.
     *
     * @param q4y2Ch the q4y2Ch to set
     */
    public void setQ4y2Ch(final Double q4y2Ch) {
        this.q4y2Ch = q4y2Ch;
    }

    /**
     * Gets the q1y3 ch.
     *
     * @return the q1y3Ch
     */
    public Double getQ1y3Ch() {
        return q1y3Ch;
    }

    /**
     * Sets the q1y3 ch.
     *
     * @param q1y3Ch the q1y3Ch to set
     */
    public void setQ1y3Ch(final Double q1y3Ch) {
        this.q1y3Ch = q1y3Ch;
    }

    /**
     * Gets the q2y3 ch.
     *
     * @return the q2y3Ch
     */
    public Double getQ2y3Ch() {
        return q2y3Ch;
    }

    /**
     * Sets the q2y3 ch.
     *
     * @param q2y3Ch the q2y3Ch to set
     */
    public void setQ2y3Ch(final Double q2y3Ch) {
        this.q2y3Ch = q2y3Ch;
    }

    /**
     * Gets the q3y3 ch.
     *
     * @return the q3y3Ch
     */
    public Double getQ3y3Ch() {
        return q3y3Ch;
    }

    /**
     * Sets the q3y3 ch.
     *
     * @param q3y3Ch the q3y3Ch to set
     */
    public void setQ3y3Ch(final Double q3y3Ch) {
        this.q3y3Ch = q3y3Ch;
    }

    /**
     * Gets the q4y3 ch.
     *
     * @return the q4y3Ch
     */
    public Double getQ4y3Ch() {
        return q4y3Ch;
    }

    /**
     * Sets the q4y3 ch.
     *
     * @param q4y3Ch the q4y3Ch to set
     */
    public void setQ4y3Ch(final Double q4y3Ch) {
        this.q4y3Ch = q4y3Ch;
    }

    /**
     * Gets the y1 ch.
     *
     * @return the y1Ch
     */
    public Double getY1Ch() {
        return y1Ch;
    }

    /**
     * Sets the y1 ch.
     *
     * @param y1Ch the y1Ch to set
     */
    public void setY1Ch(final Double y1Ch) {
        this.y1Ch = y1Ch;
    }

    /**
     * Gets the y2 ch.
     *
     * @return the y2Ch
     */
    public Double getY2Ch() {
        return y2Ch;
    }

    /**
     * Sets the y2 ch.
     *
     * @param y2Ch the y2Ch to set
     */
    public void setY2Ch(final Double y2Ch) {
        this.y2Ch = y2Ch;
    }

    /**
     * Gets the y3 ch.
     *
     * @return the y3Ch
     */
    public Double getY3Ch() {
        return y3Ch;
    }

    /**
     * Sets the y3 ch.
     *
     * @param y3Ch the y3Ch to set
     */
    public void setY3Ch(final Double y3Ch) {
        this.y3Ch = y3Ch;
    }

    /**
     * Gets the s1y1 ch.
     *
     * @return the s1y1Ch
     */
    public Double getS1y1Ch() {
        return s1y1Ch;
    }

    /**
     * Sets the s1y1 ch.
     *
     * @param s1y1Ch the s1y1Ch to set
     */
    public void setS1y1Ch(final Double s1y1Ch) {
        this.s1y1Ch = s1y1Ch;
    }

    /**
     * Gets the s2y1 ch.
     *
     * @return the s2y1Ch
     */
    public Double getS2y1Ch() {
        return s2y1Ch;
    }

    /**
     * Sets the s2y1 ch.
     *
     * @param s2y1Ch the s2y1Ch to set
     */
    public void setS2y1Ch(final Double s2y1Ch) {
        this.s2y1Ch = s2y1Ch;
    }

    /**
     * Gets the s1y2 ch.
     *
     * @return the s1y2Ch
     */
    public Double getS1y2Ch() {
        return s1y2Ch;
    }

    /**
     * Sets the s1y2 ch.
     *
     * @param s1y2Ch the s1y2Ch to set
     */
    public void setS1y2Ch(final Double s1y2Ch) {
        this.s1y2Ch = s1y2Ch;
    }

    /**
     * Gets the s2y2 ch.
     *
     * @return the s2y2Ch
     */
    public Double getS2y2Ch() {
        return s2y2Ch;
    }

    /**
     * Sets the s2y2 ch.
     *
     * @param s2y2Ch the s2y2Ch to set
     */
    public void setS2y2Ch(final Double s2y2Ch) {
        this.s2y2Ch = s2y2Ch;
    }

    /**
     * Gets the s1y3 ch.
     *
     * @return the s1y3Ch
     */
    public Double getS1y3Ch() {
        return s1y3Ch;
    }

    /**
     * Sets the s1y3 ch.
     *
     * @param s1y3Ch the s1y3Ch to set
     */
    public void setS1y3Ch(final Double s1y3Ch) {
        this.s1y3Ch = s1y3Ch;
    }

    /**
     * Gets the s2y3 ch.
     *
     * @return the s2y3Ch
     */
    public Double getS2y3Ch() {
        return s2y3Ch;
    }

    /**
     * Sets the s2y3 ch.
     *
     * @param s2y3Ch the s2y3Ch to set
     */
    public void setS2y3Ch(final Double s2y3Ch) {
        this.s2y3Ch = s2y3Ch;
    }

    /**
     * Gets the q1y1 tp.
     *
     * @return the q1y1Tp
     */
    public Double getQ1y1Tp() {
        return q1y1Tp;
    }

    /**
     * Sets the q1y1 tp.
     *
     * @param q1y1Tp the q1y1Tp to set
     */
    public void setQ1y1Tp(final Double q1y1Tp) {
        this.q1y1Tp = q1y1Tp;
    }

    /**
     * Gets the q2y1 tp.
     *
     * @return the q2y1Tp
     */
    public Double getQ2y1Tp() {
        return q2y1Tp;
    }

    /**
     * Sets the q2y1 tp.
     *
     * @param q2y1Tp the q2y1Tp to set
     */
    public void setQ2y1Tp(final Double q2y1Tp) {
        this.q2y1Tp = q2y1Tp;
    }

    /**
     * Gets the q3y1 tp.
     *
     * @return the q3y1Tp
     */
    public Double getQ3y1Tp() {
        return q3y1Tp;
    }

    /**
     * Sets the q3y1 tp.
     *
     * @param q3y1Tp the q3y1Tp to set
     */
    public void setQ3y1Tp(final Double q3y1Tp) {
        this.q3y1Tp = q3y1Tp;
    }

    /**
     * Gets the q4y1 tp.
     *
     * @return the q4y1Tp
     */
    public Double getQ4y1Tp() {
        return q4y1Tp;
    }

    /**
     * Sets the q4y1 tp.
     *
     * @param q4y1Tp the q4y1Tp to set
     */
    public void setQ4y1Tp(final Double q4y1Tp) {
        this.q4y1Tp = q4y1Tp;
    }

    /**
     * Gets the q1y2 tp.
     *
     * @return the q1y2Tp
     */
    public Double getQ1y2Tp() {
        return q1y2Tp;
    }

    /**
     * Sets the q1y2 tp.
     *
     * @param q1y2Tp the q1y2Tp to set
     */
    public void setQ1y2Tp(final Double q1y2Tp) {
        this.q1y2Tp = q1y2Tp;
    }

    /**
     * Gets the q2y2 tp.
     *
     * @return the q2y2Tp
     */
    public Double getQ2y2Tp() {
        return q2y2Tp;
    }

    /**
     * Sets the q2y2 tp.
     *
     * @param q2y2Tp the q2y2Tp to set
     */
    public void setQ2y2Tp(final Double q2y2Tp) {
        this.q2y2Tp = q2y2Tp;
    }

    /**
     * Gets the q3y2 tp.
     *
     * @return the q3y2Tp
     */
    public Double getQ3y2Tp() {
        return q3y2Tp;
    }

    /**
     * Sets the q3y2 tp.
     *
     * @param q3y2Tp the q3y2Tp to set
     */
    public void setQ3y2Tp(final Double q3y2Tp) {
        this.q3y2Tp = q3y2Tp;
    }

    /**
     * Gets the q4y2 tp.
     *
     * @return the q4y2Tp
     */
    public Double getQ4y2Tp() {
        return q4y2Tp;
    }

    /**
     * Sets the q4y2 tp.
     *
     * @param q4y2Tp the q4y2Tp to set
     */
    public void setQ4y2Tp(final Double q4y2Tp) {
        this.q4y2Tp = q4y2Tp;
    }

    /**
     * Gets the q1y3 tp.
     *
     * @return the q1y3Tp
     */
    public Double getQ1y3Tp() {
        return q1y3Tp;
    }

    /**
     * Sets the q1y3 tp.
     *
     * @param q1y3Tp the q1y3Tp to set
     */
    public void setQ1y3Tp(final Double q1y3Tp) {
        this.q1y3Tp = q1y3Tp;
    }

    /**
     * Gets the q2y3 tp.
     *
     * @return the q2y3Tp
     */
    public Double getQ2y3Tp() {
        return q2y3Tp;
    }

    /**
     * Sets the q2y3 tp.
     *
     * @param q2y3Tp the q2y3Tp to set
     */
    public void setQ2y3Tp(final Double q2y3Tp) {
        this.q2y3Tp = q2y3Tp;
    }

    /**
     * Gets the q3y3 tp.
     *
     * @return the q3y3Tp
     */
    public Double getQ3y3Tp() {
        return q3y3Tp;
    }

    /**
     * Sets the q3y3 tp.
     *
     * @param q3y3Tp the q3y3Tp to set
     */
    public void setQ3y3Tp(final Double q3y3Tp) {
        this.q3y3Tp = q3y3Tp;
    }

    /**
     * Gets the q4y3 tp.
     *
     * @return the q4y3Tp
     */
    public Double getQ4y3Tp() {
        return q4y3Tp;
    }

    /**
     * Sets the q4y3 tp.
     *
     * @param q4y3Tp the q4y3Tp to set
     */
    public void setQ4y3Tp(final Double q4y3Tp) {
        this.q4y3Tp = q4y3Tp;
    }

    /**
     * Gets the y1 tp.
     *
     * @return the y1Tp
     */
    public Double getY1Tp() {
        return y1Tp;
    }

    /**
     * Sets the y1 tp.
     *
     * @param y1Tp the y1Tp to set
     */
    public void setY1Tp(final Double y1Tp) {
        this.y1Tp = y1Tp;
    }

    /**
     * Gets the y2 tp.
     *
     * @return the y2Tp
     */
    public Double getY2Tp() {
        return y2Tp;
    }

    /**
     * Sets the y2 tp.
     *
     * @param y2Tp the y2Tp to set
     */
    public void setY2Tp(final Double y2Tp) {
        this.y2Tp = y2Tp;
    }

    /**
     * Gets the y3 tp.
     *
     * @return the y3Tp
     */
    public Double getY3Tp() {
        return y3Tp;
    }

    /**
     * Sets the y3 tp.
     *
     * @param y3Tp the y3Tp to set
     */
    public void setY3Tp(final Double y3Tp) {
        this.y3Tp = y3Tp;
    }

    /**
     * Gets the s1y1 tp.
     *
     * @return the s1y1Tp
     */
    public Double getS1y1Tp() {
        return s1y1Tp;
    }

    /**
     * Sets the s1y1 tp.
     *
     * @param s1y1Tp the s1y1Tp to set
     */
    public void setS1y1Tp(final Double s1y1Tp) {
        this.s1y1Tp = s1y1Tp;
    }

    /**
     * Gets the s2y1 tp.
     *
     * @return the s2y1Tp
     */
    public Double getS2y1Tp() {
        return s2y1Tp;
    }

    /**
     * Sets the s2y1 tp.
     *
     * @param s2y1Tp the s2y1Tp to set
     */
    public void setS2y1Tp(final Double s2y1Tp) {
        this.s2y1Tp = s2y1Tp;
    }

    /**
     * Gets the s1y2 tp.
     *
     * @return the s1y2Tp
     */
    public Double getS1y2Tp() {
        return s1y2Tp;
    }

    /**
     * Sets the s1y2 tp.
     *
     * @param s1y2Tp the s1y2Tp to set
     */
    public void setS1y2Tp(final Double s1y2Tp) {
        this.s1y2Tp = s1y2Tp;
    }

    /**
     * Gets the s2y2 tp.
     *
     * @return the s2y2Tp
     */
    public Double getS2y2Tp() {
        return s2y2Tp;
    }

    /**
     * Sets the s2y2 tp.
     *
     * @param s2y2Tp the s2y2Tp to set
     */
    public void setS2y2Tp(final Double s2y2Tp) {
        this.s2y2Tp = s2y2Tp;
    }

    /**
     * Gets the s1y3 tp.
     *
     * @return the s1y3Tp
     */
    public Double getS1y3Tp() {
        return s1y3Tp;
    }

    /**
     * Sets the s1y3 tp.
     *
     * @param s1y3Tp the s1y3Tp to set
     */
    public void setS1y3Tp(final Double s1y3Tp) {
        this.s1y3Tp = s1y3Tp;
    }

    /**
     * Gets the s2y3 tp.
     *
     * @return the s2y3Tp
     */
    public Double getS2y3Tp() {
        return s2y3Tp;
    }

    /**
     * Sets the s2y3 tp.
     *
     * @param s2y3Tp the s2y3Tp to set
     */
    public void setS2y3Tp(final Double s2y3Tp) {
        this.s2y3Tp = s2y3Tp;
    }

    /**
     * Gets the pmpy contract holder.
     *
     * @return the pmpyContractHolder
     */
    public String getPmpyContractHolder() {
        return pmpyContractHolder;
    }

    /**
     * Sets the pmpy contract holder.
     *
     * @param pmpyContractHolder the pmpyContractHolder to set
     */
    public void setPmpyContractHolder(final String pmpyContractHolder) {
        this.pmpyContractHolder = pmpyContractHolder;
    }

    /**
     * Gets the trading partner no.
     *
     * @return the tradingPartnerNo
     */
    public String getTradingPartnerNo() {
        return tradingPartnerNo;
    }

    /**
     * Sets the trading partner no.
     *
     * @param tradingPartnerNo the tradingPartnerNo to set
     */
    public void setTradingPartnerNo(final String tradingPartnerNo) {
        this.tradingPartnerNo = tradingPartnerNo;
    }

    /**
     * Gets the trading partner name.
     *
     * @return the tradingPartnerName
     */
    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    /**
     * Sets the trading partner name.
     *
     * @param tradingPartnerName the tradingPartnerName to set
     */
    public void setTradingPartnerName(final String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    /**
     * Gets the frequency.
     *
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the frequency.
     *
     * @param frequency the frequency to set
     */
    public void setFrequency(final String frequency) {
        this.frequency = frequency;
    }

    /**
     * Gets the history.
     *
     * @return the history
     */
    public String getHistory() {
        return history;
    }

    /**
     * Sets the history.
     *
     * @param history the history to set
     */
    public void setHistory(final String history) {
        this.history = history;
    }

    /**
     * Gets the contract.
     *
     * @return the contract
     */
    public String getContract() {
        return contract;
    }

    /**
     * Sets the contract.
     *
     * @param contract the contract to set
     */
    public void setContract(final String contract) {
        this.contract = contract;
    }

    /**
     * Gets the trading partner.
     *
     * @return the tradingPartner
     */
    public String getTradingPartner() {
        return tradingPartner;
    }

    /**
     * Sets the trading partner.
     *
     * @param tradingPartner the tradingPartner to set
     */
    public void setTradingPartner(final String tradingPartner) {
        this.tradingPartner = tradingPartner;
    }

    /**
     * Gets the contrach holder list.
     *
     * @return the contrachHolderList
     */
    public List<PMPYCalculatorDTO> getContrachHolderList() {
        return contrachHolderList == null ? contrachHolderList : new ArrayList<>(contrachHolderList);
    }

    /**
     * Sets the contrach holder list.
     *
     * @param contrachHolderList the contrachHolderList to set
     */
    public void setContrachHolderList(final List<PMPYCalculatorDTO> contrachHolderList) {
        this.contrachHolderList = contrachHolderList == null ? contrachHolderList : new ArrayList<>(contrachHolderList);
    }

    /**
     * Gets the trading partner list.
     *
     * @return the tradingPartnerList
     */
    public List<PMPYCalculatorDTO> getTradingPartnerList() {
        return tradingPartnerList == null ? tradingPartnerList : new ArrayList<>(tradingPartnerList);
    }

    /**
     * Sets the trading partner list.
     *
     * @param tradingPartnerList the tradingPartnerList to set
     */
    public void setTradingPartnerList(final List<PMPYCalculatorDTO> tradingPartnerList) {
        this.tradingPartnerList = tradingPartnerList == null ? tradingPartnerList : new ArrayList<>(tradingPartnerList);
    }

    /**
     * Gets the payment term.
     *
     * @return the paymentTerm
     */
    public String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * Sets the payment term.
     *
     * @param paymentTerm the paymentTerm to set
     */
    public void setPaymentTerm(final String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    /**
     * Gets the quater three2012.
     *
     * @return the quaterThree2012
     */
    public String getQuaterThree2012() {
        return quaterThree2012;
    }

    /**
     * Sets the quater three2012.
     *
     * @param quaterThree2012 the quaterThree2012 to set
     */
    public void setQuaterThree2012(final String quaterThree2012) {
        this.quaterThree2012 = quaterThree2012;
    }

    /**
     * Gets the quater two2012.
     *
     * @return the quaterTwo2012
     */
    public String getQuaterTwo2012() {
        return quaterTwo2012;
    }

    /**
     * Sets the quater two2012.
     *
     * @param quaterTwo2012 the quaterTwo2012 to set
     */
    public void setQuaterTwo2012(final String quaterTwo2012) {
        this.quaterTwo2012 = quaterTwo2012;
    }

    /**
     * Gets the quater one2012.
     *
     * @return the quaterOne2012
     */
    public String getQuaterOne2012() {
        return quaterOne2012;
    }

    /**
     * Sets the quater one2012.
     *
     * @param quaterOne2012 the quaterOne2012 to set
     */
    public void setQuaterOne2012(final String quaterOne2012) {
        this.quaterOne2012 = quaterOne2012;
    }

    /**
     * Gets the quater four2011.
     *
     * @return the quaterFour2011
     */
    public String getQuaterFour2011() {
        return quaterFour2011;
    }

    /**
     * Sets the quater four2011.
     *
     * @param quaterFour2011 the quaterFour2011 to set
     */
    public void setQuaterFour2011(final String quaterFour2011) {
        this.quaterFour2011 = quaterFour2011;
    }

    /**
     * Gets the quater three2011.
     *
     * @return the quaterThree2011
     */
    public String getQuaterThree2011() {
        return quaterThree2011;
    }

    /**
     * Sets the quater three2011.
     *
     * @param quaterThree2011 the quaterThree2011 to set
     */
    public void setQuaterThree2011(final String quaterThree2011) {
        this.quaterThree2011 = quaterThree2011;
    }

    /**
     * Gets the quater two2011.
     *
     * @return the quaterTwo2011
     */
    public String getQuaterTwo2011() {
        return quaterTwo2011;
    }

    /**
     * Sets the quater two2011.
     *
     * @param quaterTwo2011 the quaterTwo2011 to set
     */
    public void setQuaterTwo2011(final String quaterTwo2011) {
        this.quaterTwo2011 = quaterTwo2011;
    }
}
