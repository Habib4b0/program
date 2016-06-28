
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
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
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
import org.jboss.logging.Logger;

/**
 *
 * @author vigneshkanna
 */
public class LookUpLogic {

    private static final Logger LOGGER = Logger.getLogger(DiscountLogic.class);
    public static final SimpleDateFormat DBDate = new SimpleDateFormat("MM/dd/yyyy");
    QueryUtils queryUtils = new QueryUtils();
    public static final char CHAR_PERCENT = '%';
    public static final char CHAR_ASTERISK = '*';
    DiscountDAO discountDAO = new DiscountDaoImpl();
    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

    public int getSearchCount(CustomFieldGroup discountChBinder, String moduleName, Set<Container.Filter> filters) {
        int count = 0;

        count = discountDAO.getSearchCount(discountChBinder, moduleName, filters);

        return count;
    }

    public List<LookupDTO> getSearch(CustomFieldGroup discountChBinder, int start, int offset, String moduleName, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        List<LookupDTO> searchList = new ArrayList<LookupDTO>();
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
            e.getMessage();
        }
        return searchList;
    }

    public List<LookupDTO> setCfpValues(List list) throws Exception {
        List<LookupDTO> returnList = new ArrayList<LookupDTO>();
        LookupDTO lookupDTO;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object objects[] = (Object[]) list.get(i);
            lookupDTO = new LookupDTO();
            lookupDTO.setCfpId(CommonUtils.getPureValue(String.valueOf(objects[0])));
            lookupDTO.setCfpNo(CommonUtils.getPureValue(String.valueOf(objects[1])));
            lookupDTO.setCfpName(CommonUtils.getPureValue(String.valueOf(objects[2])));
            lookupDTO.setCfpType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[3]))));
            lookupDTO.setCfpCategory(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[4]))));
            lookupDTO.setCfpDesignation(CommonUtils.getPureValue(String.valueOf(objects[5])));
            lookupDTO.setCfpStatus(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[6]))));
            lookupDTO.setCfpTradeClass(CommonUtils.getPureValue(String.valueOf(objects[7])));
            lookupDTO.setCfpStartDate(setFilterFormatted(objects[8]));
            if (queryUtils.getNull(String.valueOf(objects[9]))) {
                lookupDTO.setCfpEndDate(setFilterFormatted(objects[9]));
            }
            lookupDTO.setCfpSystemId(Integer.parseInt(String.valueOf(objects[10])));
            returnList.add(lookupDTO);
        }

        return returnList;
    }

    public List<LookupDTO> setIfpValues(List list) throws Exception {
        List<LookupDTO> returnList = new ArrayList<LookupDTO>();
        LookupDTO lookupDTO;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object objects[] = (Object[]) list.get(i);
            lookupDTO = new LookupDTO();
            lookupDTO.setIfpSystemId(Integer.parseInt(String.valueOf(objects[0])));
            lookupDTO.setIfpId(CommonUtils.getPureValue(String.valueOf(objects[1])));
            lookupDTO.setIfpNo(CommonUtils.getPureValue(String.valueOf(objects[2])));
            lookupDTO.setIfpName(CommonUtils.getPureValue(String.valueOf(objects[3])));
            lookupDTO.setIfpType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[4]))));
            lookupDTO.setIfpCategory(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[5]))));
            lookupDTO.setIfpDesignation(CommonUtils.getPureValue(String.valueOf(objects[6])));
            lookupDTO.setIfpStatus(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[7]))));
            lookupDTO.setIfpStartDate(setFilterFormatted(objects[8]));
            if (queryUtils.getNull(String.valueOf(objects[9]))) {
                lookupDTO.setIfpEndDate(setFilterFormatted(objects[9]));
            }
            lookupDTO.setIfpPlanId(objects[10] != null ? String.valueOf(objects[10]) : StringUtils.EMPTY);
            lookupDTO.setIfpPlanName(objects[11] != null ? String.valueOf(objects[11]) : StringUtils.EMPTY);
            returnList.add(lookupDTO);
        }

        return returnList;
    }

    public List<LookupDTO> setPSValues(List list) throws Exception {
        List<LookupDTO> returnList = new ArrayList<LookupDTO>();
        LookupDTO lookupDTO;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object objects[] = (Object[]) list.get(i);
            lookupDTO = new LookupDTO();
            lookupDTO.setPsSystemId(Integer.parseInt(String.valueOf(objects[0])));
            lookupDTO.setPsId(CommonUtils.getPureValue(String.valueOf(objects[1])));
            lookupDTO.setPsNo(CommonUtils.getPureValue(String.valueOf(objects[2])));
            lookupDTO.setPsName(CommonUtils.getPureValue(String.valueOf(objects[3])));
            lookupDTO.setPsType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[4]))));
            lookupDTO.setPsCategory(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[5]))));
            lookupDTO.setPsTradeClass(CommonUtils.getPureValue(String.valueOf(objects[6])));
            lookupDTO.setPsDesignation(CommonUtils.getPureValue(String.valueOf(objects[7])));
            lookupDTO.setPsStatus(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[8]))));
            lookupDTO.setPsStartDate(setFilterFormatted(objects[9]));
            if (queryUtils.getNull(String.valueOf(objects[10]))) {
                lookupDTO.setPsEndDate(setFilterFormatted(objects[10]));
            }
            returnList.add(lookupDTO);
        }

        return returnList;
    }

   public List<LookupDTO> setRSValues(List list) throws Exception {
        List<LookupDTO> returnList = new ArrayList<LookupDTO>();
        LookupDTO lookupDTO;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object objects[] = (Object[]) list.get(i);
            lookupDTO = new LookupDTO();
            lookupDTO.setRsSystemId(Integer.parseInt(String.valueOf(objects[0])));
            lookupDTO.setRsId(CommonUtils.getPureValue(String.valueOf(objects[1])));
            lookupDTO.setRsNo(CommonUtils.getPureValue(String.valueOf(objects[2])));
            lookupDTO.setRsName(CommonUtils.getPureValue(String.valueOf(objects[3])));
            lookupDTO.setRsType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[4]))));
            lookupDTO.setRebateProgramType(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[5]))));
            lookupDTO.setRsCategory(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[6]))));
            lookupDTO.setRsTradeClass(CommonUtils.getPureValue(String.valueOf(objects[7])));
            lookupDTO.setRsDesignation(CommonUtils.getPureValue(String.valueOf(objects[8])));
            lookupDTO.setRsStatus(new HelperDTO(CommonUtils.getPureValue(String.valueOf(objects[9]))));
            lookupDTO.setRsStartDate(setFilterFormatted(objects[10]));
            if (queryUtils.getNull(String.valueOf(objects[11]))) {
                lookupDTO.setRsEndDate(setFilterFormatted(objects[11]));
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
                java.util.logging.Logger.getLogger(LookUpLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        return new Date();
    }
}