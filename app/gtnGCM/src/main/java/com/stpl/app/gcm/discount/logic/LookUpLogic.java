
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.discount.dao.DiscountDAO;
import com.stpl.app.gcm.discount.dao.impl.DiscountDaoImpl;
import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vigneshkanna
 */
public class LookUpLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountLogic.class);
    public static final SimpleDateFormat DBDate = new SimpleDateFormat("MM/dd/yyyy");
    private final QueryUtils queryUtils = new QueryUtils();
    public static final char CHAR_PERCENT = '%';
    public static final char CHAR_ASTERISK = '*';
    private final DiscountDAO discountDAO = new DiscountDaoImpl();
    private final DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

    public int getSearchCount(ErrorfulFieldGroup discountChBinder, String moduleName, Set<Container.Filter> filters) {
        int count = 0;

        count = discountDAO.getSearchCount(discountChBinder, moduleName, filters);

        return count;
    }

    public List<LookupDTO> getSearch(ErrorfulFieldGroup discountChBinder, int start, int offset, String moduleName, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        List<LookupDTO> searchList = new ArrayList<>();
        try {
            List resultList = discountDAO.getSearchValues(discountChBinder, start, offset, moduleName, filters, sortByColumns);
            if (Constants.CFP.equals(moduleName)) {
                searchList = setCfpValues(resultList);
            }
            if (Constants.IFP.equals(moduleName)) {
                searchList = setIfpValues(resultList);
            }
            if (Constants.PS.equals(moduleName)) {
                searchList = setPSValues(resultList);
            }
            if (Constants.RS.equals(moduleName)) {
                searchList = setRSValues(resultList);
            }
        } catch (Exception e) {
           LOGGER.error("",e);
        }
        return searchList;
    }

    public List<LookupDTO> setCfpValues(List list) {
        List<LookupDTO> returnList = new ArrayList<>();
        LookupDTO lookupDTO;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object[] objects = (Object[]) list.get(i);
            lookupDTO = new LookupDTO();
            lookupDTO.setCfpId(CommonUtils.getPureValue(String.valueOf(objects[0])));
            lookupDTO.setCfpNo(CommonUtils.getPureValue(String.valueOf(objects[1])));
            lookupDTO.setCfpName(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.TWO])));
            lookupDTO.setCfpType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.THREE]))));
            lookupDTO.setCfpCategory(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.FOUR]))));
            lookupDTO.setCfpDesignation(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.FIVE])));
            lookupDTO.setCfpStatus(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.SIX]))));
            lookupDTO.setCfpTradeClass(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.SEVEN])));
            lookupDTO.setCfpStartDate(setFilterFormatted(objects[NumericConstants.EIGHT]));
            if (queryUtils.getNull(String.valueOf(objects[NumericConstants.NINE]))) {
                lookupDTO.setCfpEndDate(setFilterFormatted(objects[NumericConstants.NINE]));
            }
            lookupDTO.setCfpSystemId(Integer.parseInt(String.valueOf(objects[NumericConstants.TEN])));
            returnList.add(lookupDTO);
        }

        return returnList;
    }

    public List<LookupDTO> setIfpValues(List list) {
        List<LookupDTO> returnList = new ArrayList<>();
        LookupDTO lookupDTO;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object[] objects = (Object[]) list.get(i);
            lookupDTO = new LookupDTO();
            lookupDTO.setIfpSystemId(Integer.parseInt(String.valueOf(objects[0])));
            lookupDTO.setIfpId(CommonUtils.getPureValue(String.valueOf(objects[1])));
            lookupDTO.setIfpNo(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.TWO])));
            lookupDTO.setIfpName(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.THREE])));
            lookupDTO.setIfpType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.FOUR]))));
            lookupDTO.setIfpCategory(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.FIVE]))));
            lookupDTO.setIfpDesignation(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.SIX])));
            lookupDTO.setIfpStatus(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.SEVEN]))));
            lookupDTO.setIfpStartDate(setFilterFormatted(objects[NumericConstants.EIGHT]));
            if (queryUtils.getNull(String.valueOf(objects[NumericConstants.NINE]))) {
                lookupDTO.setIfpEndDate(setFilterFormatted(objects[NumericConstants.NINE]));
            }
            lookupDTO.setIfpPlanId(objects[NumericConstants.TEN] != null ? String.valueOf(objects[NumericConstants.TEN]) : StringUtils.EMPTY);
            lookupDTO.setIfpPlanName(objects[NumericConstants.ELEVEN] != null ? String.valueOf(objects[NumericConstants.ELEVEN]) : StringUtils.EMPTY);
            returnList.add(lookupDTO);
        }

        return returnList;
    }

    public List<LookupDTO> setPSValues(List list) {
        List<LookupDTO> returnList = new ArrayList<>();
        LookupDTO lookupDTO;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object[] objects = (Object[]) list.get(i);
            lookupDTO = new LookupDTO();
            lookupDTO.setPsSystemId(Integer.parseInt(String.valueOf(objects[0])));
            lookupDTO.setPsId(CommonUtils.getPureValue(String.valueOf(objects[1])));
            lookupDTO.setPsNo(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.TWO])));
            lookupDTO.setPsName(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.THREE])));
            lookupDTO.setPsType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.FOUR]))));
            lookupDTO.setPsCategory(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.FIVE]))));
            lookupDTO.setPsTradeClass(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.SIX])));
            lookupDTO.setPsDesignation(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.SEVEN])));
            lookupDTO.setPsStatus(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.EIGHT]))));
            lookupDTO.setPsStartDate(setFilterFormatted(objects[NumericConstants.NINE]));
            if (queryUtils.getNull(String.valueOf(objects[NumericConstants.TEN]))) {
                lookupDTO.setPsEndDate(setFilterFormatted(objects[NumericConstants.TEN]));
            }
            returnList.add(lookupDTO);
        }

        return returnList;
    }

   public List<LookupDTO> setRSValues(List list) {
        List<LookupDTO> returnList = new ArrayList<>();
        LookupDTO lookupDTO;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object[] objects = (Object[]) list.get(i);
            lookupDTO = new LookupDTO();
            lookupDTO.setRsSystemId(Integer.parseInt(String.valueOf(objects[0])));
            lookupDTO.setRsId(CommonUtils.getPureValue(String.valueOf(objects[1])));
            lookupDTO.setRsNo(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.TWO])));
            lookupDTO.setRsName(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.THREE])));
            lookupDTO.setRsType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.FOUR]))));
            lookupDTO.setRebateProgramType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.FIVE]))));
            lookupDTO.setRsCategory(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.SIX]))));
            lookupDTO.setRsTradeClass(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.SEVEN])));
            lookupDTO.setRsDesignation(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.EIGHT])));
            lookupDTO.setRsStatus(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[NumericConstants.NINE]))));
            lookupDTO.setRsStartDate(setFilterFormatted(objects[NumericConstants.TEN]));
            if (queryUtils.getNull(String.valueOf(objects[NumericConstants.ELEVEN]))) {
                lookupDTO.setRsEndDate(setFilterFormatted(objects[NumericConstants.ELEVEN]));
            }
            returnList.add(lookupDTO);
        }

        return returnList;
    }


    public Date setFilterFormatted(final Object date) {

        Date resultDate = date != null ? (Date) date : new Date();
            try {
                String stringDate = CommonUtils.convertDateToString(resultDate);
                resultDate = df.parse(stringDate);
                return resultDate;
            } catch (ParseException ex) {
                LoggerFactory.getLogger(LookUpLogic.class.getName()).error("", ex);
            }
        return new Date();
    }
}