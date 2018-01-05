/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.util;

import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class HeaderUtils {

    private static final int[] QUATER_VALUE = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
    private static final int[] SEMI_VALUE = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
    private static final String[] CURRENT_MONTH = {"Jan~1", "Feb~2", "Mar~3", "Apr~4", "May~5", "Jun~6", "Jul~7", "Aug~8", "Sep~9", "Oct~10", "Nov~11", "Dec~12"};
    private static final String[] CURRENT_MONTH_HEADER = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    /**
     * Customer visible columns
     */
    public static final Object[] CUSTOMER_COLS = new Object[]{"organisationKey", "customerId", "customerNo", "customerName", "tradeClass", "tradeClassStartDate", "tradeClassEndDate", "customerType", "customerStatus", "lives", "customerStartDate", "customerEndDate", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "customerGroup", "financialSystem", "address1", "address2", "city", "state", "zipCode", "country", "regionCode", "parentCustomerNo", "parentStartDate", "parentEndDate", "priorParentStartDate", "priorParentCustomerNo"};
    /**
     * Customer column headers
     */
    public static final String[] CUSTOMER_HEADERS = new String[]{"Organization Key", "Customer ID", "Customer No", "Customer Name", "Trade Class", "Trade Class Start Date", "Trade Class End Date", "Customer Type", "Customer Status ", "Lives ", "Customer Start Date", "Customer End Date", "UDC1", "UDC2", "UDC3", "UDC4", "UDC5", "UDC6", "Customer Group", "Financial System", "Address 1", "Address 2", "City", "State", "Zip Code", "Country", "Region Code", "Parent Customer No", "Parent Start Date", "Parent End Date", "Prior Parent Start Date", "Prior Parent Customer No"};
    /**
     * Item visible columns
     */
    public static final Object[] ITEM_COLS = new Object[]{"itemId", "item", "itemCode", "itemName", "itemDesc", "itemStartDate", "itemEndDate", "itemStatus", "therapeuticClass", "brand", "form", "strength", "packageSizeCode", "packageSizeIntroDate", "psUP", "manufacturerID", "manufacturerNO", "manufacturerName", "labelerCode", "productOrganizationKey", "acquisitionDate", "authorizedGeneric", "authorizedGenericStartDate", "authorizedGenericEndDate", "firstSaleDate", "itemTypeIndicator", "itemClass", "itemType", "marketTerminationDate", "newFormulationIndicator", "newFormulation", "newFormulationStartDate", "newFormulationEndDate", "pediatricExclusiveIndicator", "pediatricExclusiveEndDate", "pediatricExclusiveStartDate", "clottingFactorIndicator", "clottingFactorStartDate", "clottingFactorEndDate", "primaryUOM", "secondaryUOM", "shelfLife", "shelfLifeType", "dualPricingIndicator", "itemFamilyID", "psUDC1", "psUDC2", "psUDC3", "psUDC4", "psUDC5", "psUDC6", "acquiredAMP", "acquiredBAMP", "psOBRABAMP", "psDRA", "dosesperUnit", "discontinuationDate", "lastLotExpirationDate", "psNDC9", "psNDC8", "displayBrand", "innovatorCode", "baselineAMP", "baseYearCPI"};
    /**
     * Item column headers
     */
    public static final String[] ITEM_HEADERS = new String[]{"Item ID", "Item No", "Item Code", "Item Name", "Item Desc", "Item Start Date", "Item End Date", "Item Status", "Therapeutic Class", "Brand", "Form", "Strength", "Package Size Code", "Package Size Intro Date", "UPPS", "Manufacturer ID", "Manufacturer NO", "Manufacturer Name", "Labeler Code", "Organization Key", "Acquisition Date", "Authorized Generic", "Authorized Generic Start Date", "Authorized Generic End Date", "First Sale Date", "Item Type Indicator", "Item Class", "Item Type", "Market Termination Date", "New Formulation Indicator", "New Formulation", "New Formulation Start Date", "New Formulation End Date", "Pediatric Exclusive Indicator", "Pediatric Exclusive Start Date", "Pediatric Exclusive End Date", "Clotting Factor Indicator", "Clotting Factor Start Date", "Clotting Factor End Date", "Primary UOM", "Secondary UOM", "Shelf Life", "Shelf Life Type", "Dual Pricing Indicator", "Item Family ID", "UDC1", "UDC2", "UDC3", "UDC4", "UDC5", "UDC6", "Acquired AMP", "Acquired BAMP", "OBRA BAMP", "DRA", "Doses per Unit", "Discontinuation Date", "Last Lot Expiration Date", "NDC9", "NDC8", "Display Brand", "Innovator Code", "Baseline AMP", "Base Year CPI"};

    private static final HashMap<String, String> COL_NAMES = new HashMap<String, String>();

    public static CustomTableHeaderDTO getRightDeductionDetailsHeader(CustomTableHeaderDTO fullHeaderDTO, DeductionDetailsDTO detailsDto) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO = getCustomHeader(tableHeaderDTO, fullHeaderDTO, detailsDto);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getLeftDeductionDetailsHeader(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("check", " ", Boolean.class);
        fullHeaderDTO.addSingleColumn("check", " ", Boolean.class);
        tableHeaderDTO.addSingleColumn("group", "Level Name", String.class);
        fullHeaderDTO.addSingleColumn("group", "Level Name", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getCustomHeader(CustomTableHeaderDTO tableHeaderDTO, CustomTableHeaderDTO fullHeaderDTO, DeductionDetailsDTO detailsDto) {
        String fullFromDateArr[] = detailsDto.getDetailsFromDate() == null || StringUtils.EMPTY.equals(detailsDto.getDetailsFromDate()) || ConstantsUtils.NULL.equals(detailsDto.getDetailsFromDate()) ? detailsDto.getForecastFromDate().split("-") : detailsDto.getDetailsFromDate().split("-");
        String fullToDateArr[] = detailsDto.getDetailsToDate() == null || StringUtils.EMPTY.equals(detailsDto.getDetailsToDate()) || ConstantsUtils.NULL.equals(detailsDto.getDetailsToDate()) ? detailsDto.getForecastToDate().split("-") : detailsDto.getDetailsToDate().split("-");
        int startYear = Integer.valueOf(String.valueOf(fullFromDateArr[0]));
        int endYear = Integer.valueOf(String.valueOf(fullToDateArr[0]));
        int startPeriod = 0;
        int endPeriod = 0;
        int endMonth = 0;
        int startYearLoop = Integer.valueOf(String.valueOf(fullFromDateArr[0]));
        if (ConstantsUtils.QUARTERLY.equalsIgnoreCase(detailsDto.getFrequency())) {
            endPeriod = detailsDto.getDetailsToDate()==null || StringUtils.EMPTY.equals(detailsDto.getDetailsToDate()) || ConstantsUtils.NULL.equals(detailsDto.getDetailsToDate()) ? NumericConstants.FOUR : QUATER_VALUE[Integer.valueOf(String.valueOf(fullToDateArr[1]))-1];
            startPeriod = QUATER_VALUE[Integer.valueOf(String.valueOf(fullFromDateArr[1])) - 1];
        } else if (ConstantsUtils.SEMI_ANNUAL.equalsIgnoreCase(detailsDto.getFrequency())) {
            endPeriod = detailsDto.getDetailsToDate()==null || StringUtils.EMPTY.equals(detailsDto.getDetailsToDate()) || ConstantsUtils.NULL.equals(detailsDto.getDetailsToDate()) ? NumericConstants.TWO : SEMI_VALUE[Integer.valueOf(String.valueOf(fullToDateArr[1]))-1];
            startPeriod = SEMI_VALUE[Integer.valueOf(String.valueOf(fullFromDateArr[1])) - 1];
        } else if ("Monthly".equalsIgnoreCase(detailsDto.getFrequency())) {
            endPeriod = detailsDto.getDetailsToDate() == null || StringUtils.EMPTY.equals(detailsDto.getDetailsToDate()) || ConstantsUtils.NULL.equals(detailsDto.getDetailsToDate()) ? NumericConstants.TWELVE : Integer.valueOf(String.valueOf(fullToDateArr[1]));
            startPeriod = Integer.valueOf(String.valueOf(fullFromDateArr[1]));
        }
        for (int i = startYear; i <= endYear; i++) {
            if ("Annual".equalsIgnoreCase(detailsDto.getFrequency())) {
                tableHeaderDTO.addSingleColumn(StringUtils.EMPTY + startYearLoop, StringUtils.EMPTY + startYearLoop, String.class);
                fullHeaderDTO.addSingleColumn(StringUtils.EMPTY + startYearLoop, StringUtils.EMPTY + startYearLoop, String.class);
            } else {
                if (i == endYear) {
                    endMonth = endPeriod;
                } else {
                    endMonth =ConstantsUtils.QUARTERLY.equalsIgnoreCase(detailsDto.getFrequency()) ? NumericConstants.FOUR : ConstantsUtils.SEMI_ANNUAL.equalsIgnoreCase(detailsDto.getFrequency()) ? NumericConstants.TWO : NumericConstants.TWELVE; 
                }
                for (int j = startPeriod; j <= endMonth; j++) {
                    if (ConstantsUtils.QUARTERLY.equalsIgnoreCase(detailsDto.getFrequency())) {
                        tableHeaderDTO.addSingleColumn("q" + j + StringUtils.EMPTY + startYearLoop, "Q" + j + " " + startYearLoop, String.class);
                        fullHeaderDTO.addSingleColumn("q" + j + StringUtils.EMPTY + startYearLoop, "Q" + j + " " + startYearLoop, String.class);
                    } else if (ConstantsUtils.SEMI_ANNUAL.equalsIgnoreCase(detailsDto.getFrequency())) {
                        tableHeaderDTO.addSingleColumn("s" + j + StringUtils.EMPTY + startYearLoop, "S" + j + " " + startYearLoop, String.class);
                        fullHeaderDTO.addSingleColumn("s" + j + StringUtils.EMPTY + startYearLoop, "S" + j + " " + startYearLoop, String.class);
                    } else if ("Monthly".equalsIgnoreCase(detailsDto.getFrequency())) {
                        tableHeaderDTO.addSingleColumn(StringUtils.EMPTY + CURRENT_MONTH[j - 1] + "~" + startYearLoop, StringUtils.EMPTY + CURRENT_MONTH_HEADER[j - 1] + " " + startYearLoop, String.class);
                        fullHeaderDTO.addSingleColumn(StringUtils.EMPTY + CURRENT_MONTH[j - 1] + "~" + startYearLoop, StringUtils.EMPTY + CURRENT_MONTH_HEADER[j - 1] + " " + startYearLoop, String.class);
                    }
                }
                startPeriod = 1;
            }
            startYearLoop = startYearLoop + 1;
        }

        return tableHeaderDTO;
    }

    public static String getDbColumnNames(String visibleColumnName) {
        return COL_NAMES.get(visibleColumnName);
    }

    public static HashMap<String, String> loadColumnNames() {
        COL_NAMES.put("organisationKey", "ORGANIZATION_KEY");
        COL_NAMES.put("customerId", "COMPANY_ID");
        COL_NAMES.put("customerName", "COMPANY_NAME");
        COL_NAMES.put("tradeClass", "COMPANY_TRADE_CLASS");
        COL_NAMES.put("tradeClassStartDate", "TRADE_CLASS_START_DATE");
        COL_NAMES.put("tradeClassEndDate", "TRADE_CLASS_END_DATE");
        COL_NAMES.put("customerType", "COMPANY_TYPE");
        COL_NAMES.put("customerStatus", "COMPANY_STATUS");
        COL_NAMES.put("lives", "LIVES");
        COL_NAMES.put("customerStartDate", "COMPANY_START_DATE");
        COL_NAMES.put("customerEndDate", "COMPANY_END_DATE");
        COL_NAMES.put("udc1", "UDC1");
        COL_NAMES.put("udc2", "UDC2");
        COL_NAMES.put("udc3", "UDC3");
        COL_NAMES.put("udc4", "UDC4");
        COL_NAMES.put("udc5", "UDC5");
        COL_NAMES.put("udc6", "UDC6");
        COL_NAMES.put("customerGroup", "COMPANY_GROUP");
        COL_NAMES.put("financialSystem", "FINANCIAL_SYSTEM");
        COL_NAMES.put("address1", "ADDRESS1");
        COL_NAMES.put("address2", "ADDRESS2");
        COL_NAMES.put("city", "CITY");
        COL_NAMES.put("state", "STATE");
        COL_NAMES.put("zipCode", "ZIP_CODE");
        COL_NAMES.put("country", "COUNTRY");
        COL_NAMES.put("regionCode", "REGION_CODE");
        COL_NAMES.put("parentStartDate", "PARENT_START_DATE");
        COL_NAMES.put("parentEndDate", "PARENT_END_DATE");
        COL_NAMES.put("priorParentStartDate", "PRIOR_PARENT_START_DATE");
        return COL_NAMES;
    }
}
