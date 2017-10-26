/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.util;

import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;

/**
 *
 * @author harlin
 */
public class Constant {

    public static final String CONTRACT_HOLDER = "contractHolder";
    public static final String CONTRACT_NO = "contractNo";
    public static final String CONTRACT_NAME = "contractName";
    public static final String CUSTOMER_NO = "customerNo";
    public static final String CUSTOMER_NAME = "customerName";
    public static final String MARKET_TYPE = "marketType";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = Constants.END_DATE;
    public static final String ITEM_NO = "itemNo";
    public static final String ITEM_NAME = "itemName";
    public static final String COMPANY_FAMILY_PLAN = "companyFamilyPlan";
    public static final String ITEM_FAMILY_PLAN = "itemFamilyPlan";
    public static final String PRICE_SCHEDULE = "priceSchedule";
    public static final String REBATE_SCHEDULE = "rebateSchedule";
    public static final String ALIAS_TYPE = "aliasType";
    public static final String ALIAS_NO = "aliasNumber";
    public static final String ALIAS_START_DATE = "aliasStartDate";
    public static final String ALIAS_END_DATE = "aliasEndDate";
    public static final String MARKET_TYPE_HT = "CONTRACT_TYPE";
    public static final String CONTRACT_ALIAS_TYPE = "CONTRACT_ALIAS_TYPE";
    public static final String CONTRACT_CATEGORY = "Contract Header";
    public static final String CFP_CATEGORY = "Company Family Plan";
    public static final String IFP_CATEGORY = "Item Family Plan";
    public static final String PS_CATEGORY = "Price Schedule";
    public static final String RS_CATEGORY = "Rebate Schedule";
    public static final String[] CONTRACT_SEARCH = {"Contract No", "Contract Name", "Contract ID"};
    public static final String[] CFP_SEARCH = {"CFP No", "CFP Name", "CFP ID"};
    public static final String[] IFP_SEARCH = {Constants.IFP_NO, Constants.IfpNAME, Constants.IFP_ID};
    public static final String[] PS_SEARCH = {"PS No", "PS Name", "PS ID"};
    public static final String[] RS_SEARCH = {"RS No", "RS Name", "RS ID"};
    public static final HelperDTO HELPER_DTO = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
}
