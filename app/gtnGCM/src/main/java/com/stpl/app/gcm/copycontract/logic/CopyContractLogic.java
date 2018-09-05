/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic;

import com.stpl.app.gcm.copycontract.dao.ContractHeaderDAO;
import com.stpl.app.gcm.copycontract.dto.CFPCompanyDTO;
import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.copycontract.dto.CopyComponentDTO;
import com.stpl.app.gcm.copycontract.dto.ExistingComponentDTO;
import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
import com.stpl.app.gcm.copycontract.dto.NewComponentDTO;
import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.gcm.copycontract.impl.ContractHeaderLogicDAOImpl;
import static com.stpl.app.gcm.copycontract.logic.CopyContractLogic.LOGGER;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.tp.dao.impl.TradingPartnerDAOImpl;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.model.HelperTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.ui.ComboBox;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.asi.ui.addons.lazycontainer.BeanSearchCriteria;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import com.stpl.app.service.HelperTableLocalServiceUtil;

/**
 *
 * @author kasiammal.m
 */
public class CopyContractLogic {

    public static final Logger LOGGER = LoggerFactory.getLogger(CopyContractLogic.class);
    private final ContractHeaderDAO dao = new ContractHeaderLogicDAOImpl();
    private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
    public static final String SPACE_COUNT = " COUNT";
    private final SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
    private final TradingPartnerDAO ccDao = new TradingPartnerDAOImpl();
    private final SimpleDateFormat sdfSource = new SimpleDateFormat(Constants.DBDATE_FORMAT);

    public List<HelperDTO> getDropDownList(final String listType) {
        final List<HelperDTO> helperList = new ArrayList<>();

        LOGGER.debug("entering getDropDownList method with paramater listType= {} " , listType);
        final DynamicQuery cfpDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType), RestrictionsFactoryUtil.like(Constants.LIST_NAME, Constants.ALL)));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
        helperList.add(new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant()));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }

        }

        LOGGER.debug(" getDropDownList method ends with return value strList size = {} " , helperList.size());

        return helperList;
    }

    public void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        for (int i = 0; dropDownList.size() > i; i++) {
            comboBox.addItem(String.valueOf(dropDownList.get(i)
                    .getId()));
            comboBox.setItemCaption(String.valueOf(dropDownList.get(i)
                    .getId()), dropDownList.get(i).getDescription());

        }
    }

    public ComboBox getSelectNull(final ComboBox select) {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(NumericConstants.FOUR);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(Constants.ZERO);
        return select;
    }

    public List loadSearchFieldDDLB(String tableName) {
        final List items = dao.getColumnNames(tableName);
        return items;
    }

    public int getContractSearchCount(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getCFPCount(cfpCompanyDto, bsc);
        return count;
    }

    public int getIFPSearchCount(IFPItemDTO ifpItemDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getIFPCount(ifpItemDto, bsc);
        return count;
    }

    public int getIFPAttachedItemCount(IFPItemDTO ifpItemDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getAttachedItemCount(ifpItemDto, bsc);
        return count;
    }

    public int getPSSearchCount(PSIFPDTO psIfpDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getPSCount(psIfpDto, bsc);
        return count;
    }

     public int getPSAttachedItemSearchCount(PSIFPDTO psIfpDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getPSAttachedItemCount(psIfpDto, bsc);
        return count;
    }

    public int getRSAttachedItemSearchCount(RsIfpDto rsIfpDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getRSAttachedItemCount(rsIfpDto, bsc);
        return count;
    }

    public int getRSSearchCount(RsIfpDto rsIfpDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getRSCount(rsIfpDto, bsc);
        return count;
    }

    public List<ExistingComponentDTO> getRSAttachedItemSearch(RsIfpDto rsIfpDto, int startIndex,
            int offset, BeanSearchCriteria bsc, List<OrderByColumn> list) throws ParseException {
        List resultList;
        String dbColumnName = StringUtils.EMPTY;
        boolean asc = false;
        List<ExistingComponentDTO> searchList;
        resultList = dao.getRsItemdetails(rsIfpDto, bsc, startIndex, offset, list, dbColumnName, asc);
        searchList = setRSmainValues(resultList);
        return searchList;

    }

    public List<ExistingComponentDTO> setCFPValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO cfpCompanyDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            cfpCompanyDto = new ExistingComponentDTO();
            cfpCompanyDto.setCompanyId(String.valueOf(objects[0]));
            cfpCompanyDto.setCompanyNo(String.valueOf(objects[1]));
            cfpCompanyDto.setCompanyName(String.valueOf(objects[NumericConstants.TWO]));
            if (objects[NumericConstants.THREE] != null) {
                Date date = dbDateFormat.parse(String.valueOf(objects[NumericConstants.THREE]));
                String finalString = dateFormat.format(date);
                cfpCompanyDto.setTradeClassStartDate(finalString);
                cfpCompanyDto.setStartDate(finalString);
            } else {
                cfpCompanyDto.setTradeClassStartDate(StringUtils.EMPTY);
                cfpCompanyDto.setStartDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FOUR] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.FOUR]));
                String finalString = dateFormat.format(date);
                cfpCompanyDto.setTradeClassEndDate(finalString);
                cfpCompanyDto.setEndDate(finalString);
            } else {
                cfpCompanyDto.setEndDate(StringUtils.EMPTY);
            }
            cfpCompanyDto.setTradeClass(objects[NumericConstants.SIX] != null && !String.valueOf(objects[NumericConstants.SIX]).trim().equalsIgnoreCase(Constants.SELECT_ONE)
                    && !String.valueOf(objects[NumericConstants.SIX]).isEmpty() ? String.valueOf(objects[NumericConstants.SIX]) : StringUtils.EMPTY);
            cfpCompanyDto.setAttachedDate(null);
            cfpCompanyDto.setCompanyStatusValue(Constants.NULL.equals(String.valueOf(objects[NumericConstants.FIVE])) ? StringUtils.EMPTY : objects[NumericConstants.FIVE].toString());
            returnList.add(cfpCompanyDto);
        }

        return returnList;
    }

    public List<ExistingComponentDTO> setCFPMainValues(List list) throws ParseException {

        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO cfpCompanyDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            cfpCompanyDto = new ExistingComponentDTO();

            cfpCompanyDto.setCompanyFamilyPlanId(String.valueOf(objects[0]));
            cfpCompanyDto.setCompanyFamilyPlanNo(String.valueOf(objects[1]));
            cfpCompanyDto.setCompanyFamilyPlanName(String.valueOf(objects[NumericConstants.TWO]));
            if (objects[NumericConstants.TWELVE] != null) {
                cfpCompanyDto.setCompanyFamilyPlanName(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.TWELVE])) ? StringUtils.EMPTY : objects[NumericConstants.TWELVE].toString());
            } else {
                cfpCompanyDto.setCompanyFamilyPlanTypeValue(StringUtils.EMPTY);
            }

            cfpCompanyDto.setCompanyFamilyPlanStatusValue(String.valueOf(objects[NumericConstants.THIRTEEN]));
            if (objects[NumericConstants.FIVE] != null) {
                Date date = dbDateFormat.parse(String.valueOf(objects[NumericConstants.FIVE]));
                String finalString = dateFormat.format(date);
                cfpCompanyDto.setCompanyFamilyPlanStartDate(finalString);
            } else {
                cfpCompanyDto.setCompanyFamilyPlanStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.SIX] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.SIX]));
                String finalString = dateFormat.format(date);
                cfpCompanyDto.setCompanyFamilyPlanEndDate(finalString);
            } else {
                cfpCompanyDto.setCompanyFamilyPlanEndDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FIFTEEN] != null) {
                cfpCompanyDto.setCompanyFamilyPlanCategoryValue(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.FIFTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.FIFTEEN].toString());
            } else {
                cfpCompanyDto.setCompanyFamilyPlanCategoryValue(StringUtils.EMPTY);
            }
            cfpCompanyDto.setCompanyFamilyTradeClassValue(String.valueOf(objects[NumericConstants.FOURTEEN]));
            cfpCompanyDto.setCompanyFamilyPlanDesignation(Constants.NULL.equals(String.valueOf(objects[NumericConstants.NINE])) ? StringUtils.EMPTY : objects[NumericConstants.NINE].toString());
            cfpCompanyDto.setCompanyFamilyPlanSystemId(Integer.parseInt(String.valueOf(objects[NumericConstants.SIXTEEN])));
            returnList.add(cfpCompanyDto);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setIFPValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO ifpItemDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            ifpItemDto = new ExistingComponentDTO();

            ifpItemDto.setItemNo(String.valueOf(objects[0]));
            ifpItemDto.setItemName(String.valueOf(objects[1]));
            if (objects[NumericConstants.TWO] != null) {
                ifpItemDto.setTherapeuticClass(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.TWO])) ? StringUtils.EMPTY : objects[NumericConstants.TWO].toString());
            } else {
                ifpItemDto.setTherapeuticClass(StringUtils.EMPTY);
            }
            ifpItemDto.setBrand(Constants.NULL.equals(String.valueOf(objects[NumericConstants.THREE])) ? StringUtils.EMPTY : objects[NumericConstants.THREE].toString());
            ifpItemDto.setItemStatus(Constants.NULL.equals(String.valueOf(objects[NumericConstants.SIX])) ? StringUtils.EMPTY : objects[NumericConstants.SIX].toString());
            if (objects[NumericConstants.FOUR] != null) {
                Date date = (Date) dbDateFormat.parse(String.valueOf(objects[NumericConstants.FOUR]));
                String finalString = dateFormat.format(date);
                ifpItemDto.setItemStartDate(finalString);
                ifpItemDto.setStartDate(finalString);
            } else {
                ifpItemDto.setItemStartDate(StringUtils.EMPTY);
                ifpItemDto.setStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.FIVE] != null) {
                Date date = dbDateFormat.parse(String.valueOf(objects[NumericConstants.FIVE]));
                String finalString = dateFormat.format(date);
                ifpItemDto.setItemEndDate(finalString);
                ifpItemDto.setEndDate(finalString);
            } else {
                ifpItemDto.setItemEndDate(StringUtils.EMPTY);
                ifpItemDto.setEndDate(StringUtils.EMPTY);
            }
            ifpItemDto.setAttachedDate(null);
            returnList.add(ifpItemDto);
        }
        return returnList;

    }

    public List<ExistingComponentDTO> setIFPmainValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO ifpItemDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            ifpItemDto = new ExistingComponentDTO();
            ifpItemDto.setIfpDetailsSystemId(Integer.parseInt(String.valueOf(objects[0])));
            ifpItemDto.setItemFamilyplanId(String.valueOf(objects[1]));
            ifpItemDto.setItemFamilyplanNo(String.valueOf(objects[NumericConstants.TWO]));
            ifpItemDto.setItemFamilyplanName(String.valueOf(objects[NumericConstants.THREE]));
            if (objects[NumericConstants.SEVEN] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.SEVEN]));
                String finalString = dateFormat.format(date);
                ifpItemDto.setIfpStartDate(finalString);
            } else {
                ifpItemDto.setIfpStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.EIGHT] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.EIGHT]));
                String finalString = dateFormat.format(date);
                ifpItemDto.setIfpEndDate(finalString);
            } else {
                ifpItemDto.setIfpEndDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.EIGHTEEN] != null) {
                ifpItemDto.setItemFamilyplanType(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.EIGHTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.EIGHTEEN].toString());
            } else {
                ifpItemDto.setItemFamilyplanType(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FIVE] != null) {
                ifpItemDto.setIFPtype(Integer.valueOf(String.valueOf(objects[NumericConstants.FIVE])));
            }
            if (objects[NumericConstants.NINETEEN] != null) {
                ifpItemDto.setDisplayIFPStatus(Constants.NULL.equals(String.valueOf(objects[NumericConstants.NINETEEN])) ? StringUtils.EMPTY : objects[NumericConstants.NINETEEN].toString());
            }
            if (objects[NumericConstants.FOUR] != null) {
                ifpItemDto.setItemFamilyplanStatus(Integer.valueOf(String.valueOf(objects[NumericConstants.FOUR])));
            }

            if (objects[NumericConstants.TWENTY_ONE] != null) {
                ifpItemDto.setItemFamilyplanDesignation(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.TWENTY_ONE])) ? StringUtils.EMPTY : objects[NumericConstants.TWENTY_ONE].toString());
            }
            ifpItemDto.setItemFamilyplanDesignation(StringUtils.EMPTY);

            returnList.add(ifpItemDto);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setPSValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO psIfpDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            psIfpDto = new ExistingComponentDTO();

            psIfpDto.setItemNo(String.valueOf(objects[0]));
            psIfpDto.setItemName(String.valueOf(objects[1]));
            psIfpDto.setTherapeuticClass(Constants.NULL.equals(String.valueOf(objects[NumericConstants.TWO])) ? StringUtils.EMPTY : objects[NumericConstants.TWO].toString());
            psIfpDto.setBrand(Constants.NULL.equals(String.valueOf(objects[NumericConstants.THREE])) ? StringUtils.EMPTY : objects[NumericConstants.THREE].toString());
            psIfpDto.setItemStatus(String.valueOf(objects[NumericConstants.SIX]));

            if (objects[NumericConstants.FOUR] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.FOUR]));
                String finalString = dateFormat.format(date);
                psIfpDto.setItemStartDate(finalString);
                psIfpDto.setStartDate(finalString);
            } else {
                psIfpDto.setItemStartDate(StringUtils.EMPTY);
                psIfpDto.setStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.FIVE] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.FIVE]));
                String finalString = dateFormat.format(date);
                psIfpDto.setItemEndDate(finalString);
                psIfpDto.setEndDate(finalString);
            } else {
                psIfpDto.setItemEndDate(StringUtils.EMPTY);
                psIfpDto.setEndDate(StringUtils.EMPTY);
            }

            psIfpDto.setPriceType(Constants.NULL.equals(String.valueOf(objects[NumericConstants.SEVEN])) ? StringUtils.EMPTY : objects[NumericConstants.SEVEN].toString());
            psIfpDto.setPriceProtectionStartDate(Constants.NULL.equals(String.valueOf(objects[NumericConstants.EIGHT])) ? StringUtils.EMPTY : objects[NumericConstants.EIGHT].toString());
            psIfpDto.setPricePlanNo(objects[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.NINE]));
            psIfpDto.setPricePlanName(objects[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TEN]));
            psIfpDto.setPriceProtectionStatus(objects[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.ELEVEN]));
            psIfpDto.setPriceProtectionEndDate(Constants.NULL.equals(String.valueOf(objects[NumericConstants.TWELVE])) ? StringUtils.EMPTY : objects[NumericConstants.TWELVE].toString());
            psIfpDto.setPriceProtectionPriceType(objects[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.THIRTEEN]));
            psIfpDto.setPriceToleranceInterval(objects[NumericConstants.FOURTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.FOURTEEN]));
            psIfpDto.setPriceToleranceFrequency(objects[NumericConstants.FIFTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.FIFTEEN]));
            psIfpDto.setMaxIncrementalChange(objects[NumericConstants.SEVENTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.SEVENTEEN]));
            psIfpDto.setPriceTolerance(objects[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.EIGHTEEN]));
            psIfpDto.setPriceToleranceType(objects[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.SIXTEEN]));
            psIfpDto.setEligibility(objects[NumericConstants.TWENTY] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY]));
            psIfpDto.setResetType(objects[NumericConstants.TWENTY_ONE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY_ONE]));
            psIfpDto.setResetDate(objects[NumericConstants.TWENTY_TWO] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY_TWO]));
            psIfpDto.setResetIntervel(objects[NumericConstants.TWENTY_THREE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY_THREE]));
            psIfpDto.setResetFrequency(objects[NumericConstants.TWENTY_FOUR] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY_FOUR]));
            psIfpDto.setAttachedDate(null);
            returnList.add(psIfpDto);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setPSmainValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO psIfpDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            psIfpDto = new ExistingComponentDTO();

            psIfpDto.setPriceScheduleSystemId(String.valueOf(objects[0]));
            psIfpDto.setPriceScheduleIdValue(String.valueOf(objects[1]));
            psIfpDto.setPriceScheduleNoValue(String.valueOf(objects[NumericConstants.TWO]));
            psIfpDto.setPriceScheduleNameValue(String.valueOf(objects[NumericConstants.THREE]));
            psIfpDto.setPriceScheduleStatusValue(Constants.NULL.equals(String.valueOf(objects[NumericConstants.FOURTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.FOURTEEN].toString());
            if (objects[NumericConstants.SEVEN] != null) {
                Date date = dbDateFormat.parse(String.valueOf(objects[NumericConstants.SEVEN]));
                String finalString = dateFormat.format(date);
                psIfpDto.setPriceScheduleStartDate(finalString);

            } else {
                psIfpDto.setPriceScheduleStartDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.EIGHT] != null) {
                Date date = dbDateFormat.parse(String.valueOf(objects[NumericConstants.EIGHT]));
                String finalString = dateFormat.format(date);
                psIfpDto.setPriceScheduleEndDate(finalString);
            } else {
                psIfpDto.setPriceScheduleEndDate(StringUtils.EMPTY);
            }

            psIfpDto.setItemFamilyplanName(String.valueOf(objects[NumericConstants.NINETEEN]));
            if (objects[NumericConstants.THIRTEEN] != null) {
                psIfpDto.setPriceScheduleTypeValue(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.THIRTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.THIRTEEN].toString());
            } else {
                psIfpDto.setPriceScheduleTypeValue(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.FIFTEEN] != null) {
                psIfpDto.setPriceScheduleCategoryValue(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.FIFTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.FIFTEEN].toString());
            } else {
                psIfpDto.setPriceScheduleCategoryValue(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.SIXTEEN] != null) {
                psIfpDto.setPriceScheduleDesignationValue(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.SIXTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.SIXTEEN].toString());
            } else {
                psIfpDto.setPriceScheduleDesignationValue(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FIVE] != null) {
                psIfpDto.setPStatus(Integer.valueOf(String.valueOf(objects[NumericConstants.FIVE])));
            }
            if (objects[NumericConstants.FOUR] != null) {
                psIfpDto.setPtype(Integer.valueOf(String.valueOf(objects[NumericConstants.FOUR])));
            }

            returnList.add(psIfpDto);
        }
        return returnList;

    }

    public List<ExistingComponentDTO> setRSmainValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO rsIfpDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            rsIfpDto = new ExistingComponentDTO();
            rsIfpDto.setRebateScheduleSystemId(DataTypeConverter.convertObjectToInt(objects[0]));
            rsIfpDto.setRebateScheduleId(String.valueOf(objects[1]));
            rsIfpDto.setRebateScheduleNo(String.valueOf(objects[NumericConstants.TWO]));
            rsIfpDto.setRebateScheduleName(String.valueOf(objects[NumericConstants.THREE]));
            rsIfpDto.setStatusRebate(String.valueOf(objects[NumericConstants.EIGHT]));
            if (objects[NumericConstants.ELEVEN] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.ELEVEN]));
                String finalString = dateFormat.format(date);
                rsIfpDto.setItemRebateStartDate(finalString);
            } else {
                rsIfpDto.setItemRebateStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.TWELVE] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.TWELVE]));
                String finalString = dateFormat.format(date);
                rsIfpDto.setItemRebateEndDate(finalString);
            } else {
                rsIfpDto.setItemRebateEndDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FOUR] != null) {
                rsIfpDto.setRebateScheduleStatus(DataTypeConverter.convertObjectToInt(objects[NumericConstants.FOUR]));
            }
            rsIfpDto.setRebatetype(String.valueOf(objects[NumericConstants.FIVE]));
            rsIfpDto.setIfpName(String.valueOf(objects[NumericConstants.SEVENTEEN]));

            returnList.add(rsIfpDto);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setRSValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO rsIfpDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            rsIfpDto = new ExistingComponentDTO();

            rsIfpDto.setItemNo(String.valueOf(objects[0]));
            rsIfpDto.setItemName(String.valueOf(objects[1]));
            rsIfpDto.setTherapeuticClass(String.valueOf(objects[NumericConstants.TWO]));
            rsIfpDto.setBrand(Constants.NULL.equals(String.valueOf(objects[NumericConstants.THREE])) ? StringUtils.EMPTY : objects[NumericConstants.THREE].toString());
            rsIfpDto.setItemStatus(String.valueOf(objects[NumericConstants.SIX]));
            if (objects[NumericConstants.FOUR] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.FOUR]));
                String finalString = dateFormat.format(date);
                rsIfpDto.setItemStartDate(finalString);
                rsIfpDto.setStartDate(finalString);

            } else {
                rsIfpDto.setItemStartDate(StringUtils.EMPTY);
                rsIfpDto.setStartDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FIVE] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(objects[NumericConstants.FIVE]));
                String finalString = dateFormat.format(date);
                rsIfpDto.setItemEndDate(finalString);
                rsIfpDto.setEndDate(finalString);
            } else {
                rsIfpDto.setItemEndDate(StringUtils.EMPTY);
                rsIfpDto.setEndDate(StringUtils.EMPTY);
            }
            rsIfpDto.setRebatePlanName(Constants.NULL.equals(String.valueOf(objects[NumericConstants.EIGHT])) ? StringUtils.EMPTY : objects[NumericConstants.EIGHT].toString());
            rsIfpDto.setRebatePlan(Constants.NULL.equals(String.valueOf(objects[NumericConstants.EIGHT])) ? StringUtils.EMPTY : objects[NumericConstants.EIGHT].toString());
            rsIfpDto.setFormulaId(Constants.NULL.equals(String.valueOf(objects[NumericConstants.SEVEN])) ? StringUtils.EMPTY : objects[NumericConstants.SEVEN].toString());
            rsIfpDto.setFormulaType(objects[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.NINE]));
            rsIfpDto.setFormulaName(objects[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TEN]));
            rsIfpDto.setRebatePlanId(objects[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.ELEVEN]));
            rsIfpDto.setRebateAmount(objects[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWELVE]));
            rsIfpDto.setBundleNo(objects[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.THIRTEEN]));
            rsIfpDto.setAttachedDate(null);
            returnList.add(rsIfpDto);
        }
        return returnList;

    }

    public int getCompanySearchCount(CFPCompanyDTO cfpCompanyDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getCompanyCount(cfpCompanyDto, bsc);
        return count;
    }

    public void saveCfp(String cfpid, Integer cfpmodelid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(cfpid);
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updateCFP(input);
    }

    public void saveIfp(String ifpId, Integer cfpmodelid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(ifpId);
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updateIFP(input);
    }

    public void savePs(String psid, Integer cfpmodelid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(psid);
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updatePS(input);
    }

    public void saveRs(String rsid, Integer rsModalId) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(rsid);
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(rsModalId);
        dao.updateRS(input);

    }

    public void saveCfpForCopyComponent(String cfpid, String cfpModelSId) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(cfpid);
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(cfpModelSId);
        dao.updateCFP(input);

    }

    public void saveIfpForCopyComponent(String ifpId, String ifpModelSid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(ifpId);
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(ifpModelSid);
        dao.updateIFP(input);
    }

    public void savePsForCopyComponent(String psid, String psModelSid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(psid);
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(psModelSid);
        dao.updatePS(input);
    }

    public void saveRsForCopyComponent(String rsid, String rsModelSid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(rsid);
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(1);
        input.add(dbDateFormat.format(new java.util.Date()));
        input.add(rsModelSid);
        dao.updateRS(input);

    }

    public Object getComponentInfoSelection(NewComponentDTO selection, String componentType, String componentInnerType, String searchValue, boolean isCount) throws ParseException {

        String queryName = Constants.ZEROSTRING;

        if (componentType.equals(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            queryName = "Copy Contract-Components Details PS Search";
            getDTOForRSandPS(selection, componentInnerType, searchValue);
            List input = new ArrayList<>();
            input.add(selection.getIfpId());
            input.add(selection.getIfpNo());
            input.add(selection.getIfpName());
            input.add(selection.getIfpStatus());
            input.add(selection.getIfpType());
            if (isCount) {
                queryName = queryName + SPACE_COUNT;
                List list = ItemQueries.getItemData(input, queryName, null);
                return CommonUtils.getDdlbCountThroughList(list);
            }
            input.add(selection.getStart());
            input.add(selection.getOffset());
            List list = ItemQueries.getItemData(input, queryName, null);
            return getCustomizedPSData(list);
        } else if (componentType.equals(Constants.REBATE_SCHEDULE)) {
            queryName = "Copy Contract-Components Details RS Search";
            if (isCount) {
                queryName = queryName + SPACE_COUNT;
            }
            getDTOForRSandPS(selection, componentInnerType, searchValue);
            List list = ItemQueries.getDataByDTO(queryName, selection);
            if (isCount) {
                return CommonUtils.getDdlbCountThroughList(list);
            }
            return getCustomizedRSData(list);
        } else if (componentType.equals(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            queryName = "Copy Contract-Components Details CFP Search";
            if (isCount) {
                queryName = queryName + SPACE_COUNT;
            }
            selection.setCompanyStatus(Constants.PERCENT);
            selection.setTradeClass(Constants.PERCENT);
            selection.setCompanyType(Constants.PERCENT);
            selection.setCompanyId(Constants.PERCENT);
            selection.setCompanyName(Constants.PERCENT);
            selection.setCompanyNo(Constants.PERCENT);
            selection.setCompanyCategory(Constants.PERCENT);

            switch (componentInnerType) {
                case "Company Status":
                    selection.setCompanyStatus(searchValue.replace('*', '%'));
                    break;
                case "Trade Class":
                    selection.setTradeClass(searchValue);
                    break;
                case "Company Type":
                    selection.setCompanyType(searchValue);
                    break;
                case "Company ID":
                    selection.setCompanyId(searchValue.replace('*', '%'));
                    break;
                case "Company Name":
                    selection.setCompanyName(searchValue.replace('*', '%'));
                    break;
                case "Company No":
                    selection.setCompanyNo(searchValue.replace('*', '%'));
                    break;
                default:
                    break;

            }
            List list = ItemQueries.getDataByDTO(queryName, selection);
            if (isCount) {
                return CommonUtils.getDdlbCountThroughList(list);
            }
            return getCustomizedCFPData(list);

        } else if (componentType.equals(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            queryName = "Copy Contract-Components Details IFP Search";
            if (isCount) {
                queryName = queryName + SPACE_COUNT;
            }
            selection.setItemStatus(Constants.PERCENT);
            selection.setItemType(Constants.PERCENT);
            selection.setForm(Constants.PERCENT);
            selection.setStrength(Constants.PERCENT);
            selection.setTherapyClass(Constants.PERCENT);
            selection.setItemId(Constants.PERCENT);
            selection.setItemName(Constants.PERCENT);
            selection.setItemNo(Constants.PERCENT);
            selection.setBrand(Constants.PERCENT);
            switch (componentInnerType) {
                case "Item Status":
                    selection.setItemStatus(searchValue);
                    break;
                case "Item Type":
                    selection.setItemType(searchValue);
                    break;
                case "Form":
                    selection.setForm(searchValue);
                    break;
                case "Strength":
                    selection.setStrength(searchValue);
                    break;
                case "Therapy Class":
                    selection.setTherapyClass(searchValue);
                    break;
                case "Item ID":
                    selection.setItemId(searchValue.replace('*', '%'));
                    break;
                case "Item Name":
                    selection.setItemName(searchValue.replace('*', '%'));
                    break;
                case "Item No":
                    selection.setItemNo(searchValue.replace('*', '%'));
                    break;
                case "Brand":
                    selection.setBrand(searchValue);
                    break;
                default:
                    break;
            }

            List list = ItemQueries.getDataByDTO(queryName, selection);
            if (isCount) {
                return CommonUtils.getDdlbCountThroughList(list);
            }
            return getCustomizedIFPData(list);
        }
        return queryName;

    }

    private void getDTOForRSandPS(NewComponentDTO selection, String componentInnerType, String searchValue) {

        selection.setIfpId(Constants.PERCENT);
        selection.setIfpNo(Constants.PERCENT);
        selection.setIfpName(Constants.PERCENT);
        selection.setIfpStatus(Constants.PERCENT);
        selection.setIfpType(Constants.PERCENT);
        switch (componentInnerType) {
            case "IFP ID":
                selection.setIfpId(searchValue.replace('*', '%'));
                break;
            case "IFP No":
                selection.setIfpNo(searchValue.replace('*', '%'));
                break;
            case "IFP Name":
                selection.setIfpName(searchValue.replace('*', '%'));
                break;
            case "IFP Status":
                selection.setIfpStatus(searchValue);
                break;
            case "IFP Type":
                selection.setIfpType(searchValue);
                break;
            default:
                break;
        }
    }

    private List getCustomizedRSData(List itemList) throws ParseException {
        List finalList = new ArrayList();
        if (itemList != null && !itemList.isEmpty()) {
            for (int i = 0; i < itemList.size(); i++) {
                NewComponentDTO itemDTO = new NewComponentDTO();
                Object[] obje = (Object[]) itemList.get(i);
                itemDTO.setIfpNo(String.valueOf(obje[0]));
                itemDTO.setIfpName(String.valueOf(obje[1]));
                itemDTO.setBrand(obje[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWO]));
                itemDTO.setItemStatus(obje[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THREE]));
                if (obje[NumericConstants.FOUR] != null) {
                    Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.FOUR]));
                    String finalString = dateFormat.format(date);
                    itemDTO.setPsStartDate(finalString);
                } else {
                    itemDTO.setPsStartDate(StringUtils.EMPTY);
                }
                if (obje[NumericConstants.FIVE] != null) {
                    Date date = dbDateFormat.parse(String.valueOf(obje[NumericConstants.FIVE]));
                    String finalString = dateFormat.format(date);
                    itemDTO.setPsEndDate(finalString);
                } else {
                    itemDTO.setPsEndDate(Constants.EMPTY);
                }
                itemDTO.setFormulaType(obje[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SIX]));
                itemDTO.setFormulaId(obje[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SEVEN]));
                itemDTO.setFormulaName(obje[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.EIGHT]));
                itemDTO.setRebatePlanId(obje[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.NINE]));
                itemDTO.setRebatePlanName(obje[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TEN]));
                itemDTO.setRebateAmount(obje[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.ELEVEN]));
                itemDTO.setBundleNo(obje[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWELVE]));
                itemDTO.setModelId(obje[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THIRTEEN]));
                finalList.add(itemDTO);
            }
        }
        return finalList;
    }

    private List getCustomizedPSData(List itemList) throws ParseException {
        List finalList = new ArrayList();
        if (itemList != null && !itemList.isEmpty()) {
            for (int i = 0; i < itemList.size(); i++) {
                NewComponentDTO itemDTO = new NewComponentDTO();
                Object[] obje = (Object[]) itemList.get(i);
                itemDTO.setItemNo(String.valueOf(obje[0]));
                itemDTO.setItemName(String.valueOf(obje[1]));
                itemDTO.setBrand(checkNullValue(obje[NumericConstants.TWO]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWO]));
                itemDTO.setItemStatus(checkNullValue(obje[NumericConstants.THREE]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THREE]));
                if (obje[NumericConstants.FOUR] != null) {
                    Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.FOUR]));
                    String finalString = dateFormat.format(date);
                    itemDTO.setPsStartDate(finalString);
                } else {
                    itemDTO.setPsStartDate(StringUtils.EMPTY);
                }
                if (obje[NumericConstants.FIVE] != null) {
                    Date date = dbDateFormat.parse(String.valueOf(obje[NumericConstants.FIVE]));
                    String finalString = dateFormat.format(date);
                    itemDTO.setPsEndDate(finalString);
                } else {
                    itemDTO.setPsEndDate(Constants.EMPTY);
                }
                itemDTO.setItemType(obje[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SIX]));
                itemDTO.setPricePlanNo(obje[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SEVEN]));
                itemDTO.setPricePlanName(obje[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.EIGHT]));
                itemDTO.setPriceProtectionStatus(checkNullValue(obje[NumericConstants.NINE]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.NINE]));
                if (obje[NumericConstants.TEN] != null) {
                    Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.TEN]));
                    String finalString = dateFormat.format(date);
                    itemDTO.setCompanyStartDate(finalString);
                } else {
                    itemDTO.setCompanyStartDate(StringUtils.EMPTY);
                }
                if (obje[NumericConstants.ELEVEN] != null) {
                    Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.ELEVEN]));
                    String finalString = dateFormat.format(date);
                    itemDTO.setCompanyEndDate(finalString);
                } else {
                    itemDTO.setCompanyEndDate(Constants.EMPTY);
                }
                itemDTO.setPriceProtectionPriceType(checkNullValue(obje[NumericConstants.TWELVE]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWELVE]));
                itemDTO.setPriceToleranceInterval(checkNullValue(obje[NumericConstants.THIRTEEN]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.THIRTEEN].toString())));
                itemDTO.setPriceToleranceFrequency(checkNullValue(obje[NumericConstants.FOURTEEN]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.FOURTEEN].toString())));
                itemDTO.setMaxIncrementalChange(checkNullValue(obje[NumericConstants.FIFTEEN]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.FIFTEEN].toString())));
                itemDTO.setPriceTolerance(checkNullValue(obje[NumericConstants.SIXTEEN]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SIXTEEN]));
                itemDTO.setPriceToleranceType(checkNullValue(obje[NumericConstants.SEVENTEEN]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SEVENTEEN]));
                itemDTO.setEligibility(checkNullValue(obje[NumericConstants.EIGHTEEN]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.EIGHTEEN]));
                itemDTO.setResetType(checkNullValue(obje[NumericConstants.NINETEEN]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.NINETEEN].toString())));
                itemDTO.setResetDate(checkNullValue(obje[NumericConstants.TWENTY_ONE]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWENTY_ONE]));
                itemDTO.setResetIntervel(checkNullValue(obje[NumericConstants.TWENTY])  ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWENTY]));
                itemDTO.setResetFrequency(checkNullValue(obje[NumericConstants.TWENTY_TWO]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.TWENTY_TWO].toString())));
                itemDTO.setAttachedDate(null);
                itemDTO.setModelId(String.valueOf(obje[NumericConstants.TWENTY_FIVE]));
                finalList.add(itemDTO);
            }
        }
        return finalList;
    }

    private Object getCustomizedCFPData(List list) throws ParseException {
        List<NewComponentDTO> companylist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            NewComponentDTO company = new NewComponentDTO();
            Object[] obje = (Object[]) list.get(i);
            company.setModelId(String.valueOf(obje[0]));
            company.setCompanyId(String.valueOf(obje[1]));
            company.setCompanyNo(String.valueOf(obje[NumericConstants.TWO]));
            company.setCompanyName(String.valueOf(obje[NumericConstants.THREE]));
            company.setCompanyType(String.valueOf(obje[NumericConstants.FOUR]));
            if (obje[NumericConstants.FIVE] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.FIVE]));
                String finalString = dateFormat.format(date);
                company.setPsStartDate(finalString);
            } else {
                company.setPsStartDate(StringUtils.EMPTY);
            }
            if (obje[NumericConstants.SIX] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.SIX]));
                String finalString = dateFormat.format(date);
                company.setPsEndDate(finalString);
            } else {
                company.setPsEndDate(Constants.EMPTY);
            }
            company.setCompanyStatus(String.valueOf(obje[NumericConstants.SEVEN]));
            company.setTradeClass(String.valueOf(obje[NumericConstants.EIGHT]));
            company.setAttachedDate((Date) obje[NumericConstants.NINE]);
            companylist.add(company);
        }
        return companylist;
    }

    private Object getCustomizedIFPData(List list) throws ParseException {
        List<NewComponentDTO> companylist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            NewComponentDTO company = new NewComponentDTO();
            Object[] obje = (Object[]) list.get(i);
            company.setModelId(String.valueOf(obje[NumericConstants.ELEVEN]));
            company.setItemId(String.valueOf(obje[0]));
            company.setItemNo(String.valueOf(obje[1]));
            company.setItemName(obje[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWO]));
            company.setItemStatus(obje[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THREE]));
            if (obje[NumericConstants.FOUR] != null) {
                if (String.valueOf(obje[NumericConstants.FOUR]).equalsIgnoreCase(Constants.SELECT_ONE)) {
                    company.setItemType(Constants.EMPTY);
                } else {
                    company.setItemType(String.valueOf(obje[NumericConstants.FOUR]));
                }
            }
            if (obje[NumericConstants.EIGHT] != null) {
                if (String.valueOf(obje[NumericConstants.EIGHT]).equalsIgnoreCase(Constants.SELECT_ONE)) {
                    company.setTherapyClass(Constants.EMPTY);
                } else {
                    company.setTherapyClass(String.valueOf(obje[NumericConstants.EIGHT]));
                }
            }
            company.setBrand(obje[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.FIVE]));
            company.setForm(obje[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SIX]));
            company.setStrength(obje[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SEVEN]));

            if (obje[NumericConstants.NINE] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.NINE]));
                String finalString = dateFormat.format(date);
                company.setPsStartDate(finalString);
            } else {
                company.setPsStartDate(StringUtils.EMPTY);
            }
            if (obje[NumericConstants.TEN] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.TEN]));
                String finalString = dateFormat.format(date);
                company.setPsEndDate(finalString);
            } else {
                company.setPsEndDate(Constants.EMPTY);
            }
            company.setAttachedDate((Date) obje[NumericConstants.TWELVE]);
            companylist.add(company);
        }
        return companylist;
    }

    public Object getExistingDetailsCount(String componentType, List input, boolean isCount, int start, int offset) throws ParseException {
        String queryName;
        switch (componentType) {
            case "Company Family Plan":
                queryName = "Copy Contract- Existing Components Details CFP Search";
                if (isCount) {
                    queryName = queryName + SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setCFPMainValues(ItemQueries.getItemData(input, queryName, null));
                }

            case "Item Family Plan":
                queryName = "Copy Contract- Existing Components Details IFP Search";
                if (isCount) {
                    queryName = queryName + SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setIFPmainValues(ItemQueries.getItemData(input, queryName, null));
                }

            case Constants.PRICE_SCHEDULE:
                queryName = "Copy Contract- Existing Components Details PS Search";
                if (isCount) {
                    queryName = queryName + SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setPSmainValues(ItemQueries.getItemData(input, queryName, null));
                }
            case Constants.REBATE_SCHEDULE:
                queryName = "Copy Contract- Existing Components Details RS Search";
                if (isCount) {
                    queryName = queryName + SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setRSmainValues(ItemQueries.getItemData(input, queryName, null));
                }
            default:
                break;
        }
        return 0;
    }

    public Object getExistingDetailsData(String componentType, ExistingComponentDTO dto, boolean isCount, int start, int offset) throws ParseException {
        String queryName;
        List input = new ArrayList();
        switch (componentType) {
            case "Company Family Plan":
                queryName = "Copy Contract- Existing Components Data Company Search";
                input.add(dto.getCompanyFamilyPlanSystemId());
                if (isCount) {
                    queryName += SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setCFPValues(ItemQueries.getItemData(input, queryName, null));
                }

            case "Item Family Plan":
                queryName = "Copy Contract- Existing Components Data item Search";
                input.add(dto.getIfpDetailsSystemId());
                if (isCount) {
                    queryName += SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setIFPValues(ItemQueries.getItemData(input, queryName, null));
                }

            case Constants.PRICE_SCHEDULE:
                queryName = "Copy Contract- Existing Components Data PS Search";
                input.add(dto.getPriceScheduleSystemId());
                if (isCount) {
                    queryName += SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setPSValues(ItemQueries.getItemData(input, queryName, null));
                }
            case Constants.REBATE_SCHEDULE:
                queryName = "Copy Contract- Existing Components Data RS Search";
                input.add(dto.getRebateScheduleSystemId());
                if (isCount) {
                    queryName += SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setRSValues(ItemQueries.getItemData(input, queryName, null));
                }
            default:
                break;
        }
        return 0;
    }

    public Object getComponentLevelData(Integer levelNo, Integer id, boolean isCount, int start, int offset) throws ParseException {
        String queryName = null;
        List input = new ArrayList();
        input.add(id);
        switch (levelNo) {
            case 1:
                queryName = "Copy Contract- Existing Leve Data CFP Search";
                if (isCount) {
                    queryName = queryName + SPACE_COUNT;
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return getCustomizedCFP(ItemQueries.getItemData(input, queryName, null));
                }

            case NumericConstants.TWO:
                queryName = "Copy Contract- Existing Leve Data IFP Search";
                break;
            case NumericConstants.THREE:
                queryName = "Copy Contract- Existing Leve Data PS Search";
                break;
            case NumericConstants.FOUR:
                queryName = "Copy Contract- Existing Leve Data RS Search";
                break;
            default:
                break;
        }
        if (isCount) {
            queryName = queryName + SPACE_COUNT;
            return CommonUtils.getDataCount(queryName, input);
        } else {
            input.add(start);
            input.add(offset);
            return getCustomizedPSandRS(ItemQueries.getItemData(input, queryName, null));
        }

    }

    private List getCustomizedCFP(List itemData) {
        List<CopyComponentDTO> companyList = new ArrayList<>();
        if (itemData != null && !itemData.isEmpty()) {
            for (int i = 0; i < itemData.size(); i++) {
                CopyComponentDTO companyDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) itemData.get(i);
                companyDTO.setCompanyNo(String.valueOf(obje[0]));
                companyDTO.setCompanyName(String.valueOf(obje[1]));
                companyDTO.setCompanyStatus(String.valueOf(obje[NumericConstants.TWO]));
                if (obje[NumericConstants.THREE] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.THREE]);
                    companyDTO.setCompanyStartDate(date);
                } else {
                    companyDTO.setCompanyStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.FOUR] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.FOUR]);
                    companyDTO.setCompanyEndDate(date);
                } else {
                    companyDTO.setCompanyEndDate(Constants.EMPTY);
                }
                companyList.add(companyDTO);
            }
        }
        return companyList;
    }

    private Object getCustomizedPSandRS(List itemData) throws ParseException {
        List<CopyComponentDTO> itemList = new ArrayList<>();
        for (int i = 0; i < itemData.size(); i++) {
            CopyComponentDTO itemDTO = new CopyComponentDTO();
            Object[] obje = (Object[]) itemData.get(i);
            itemDTO.setItemNo(String.valueOf(obje[0]));
            itemDTO.setItemName(String.valueOf(obje[1]));
            itemDTO.setTherapyClass(Constants.NULL.equals(String.valueOf(obje[NumericConstants.TWO])) || Constants.SELECT_ONE.equals(String.valueOf(obje[NumericConstants.TWO])) ? StringUtils.EMPTY : obje[NumericConstants.TWO].toString());
            itemDTO.setBrand(Constants.NULL.equals(String.valueOf(obje[NumericConstants.THREE])) ? StringUtils.EMPTY : obje[NumericConstants.THREE].toString());
            itemDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
            if (obje[NumericConstants.FIVE] != null) {
                Date date =  dbDateFormat.parse(String.valueOf(obje[NumericConstants.FIVE]));
                String finalString = dateFormat.format(date);
                itemDTO.setIfpStartDate(finalString);
            } else {
                itemDTO.setIfpStartDate(StringUtils.EMPTY);
            }
            if (obje[NumericConstants.SIX] != null) {
                Date date = dbDateFormat.parse(String.valueOf(obje[NumericConstants.SIX]));
                String finalString = dateFormat.format(date);
                itemDTO.setIfpEndDate(finalString);
            } else {
                itemDTO.setIfpEndDate(StringUtils.EMPTY);
            }

            itemList.add(itemDTO);
        }
        return itemList;
    }

    public int getComponentCount(String query) {
        List componentList = ccDao.searchList(query);
        return componentList.size();
    }

    public List<CopyComponentDTO> getComponentinfoResults(List<ContractSelectionDTO> selectedlist) throws ParseException {

        return selectedContracts(selectedlist);

    }

    public List<CopyComponentDTO> selectedContracts(List<ContractSelectionDTO> selectedList) throws ParseException {
        List<CopyComponentDTO> copyList = new ArrayList<>();
        if (selectedList != null && !selectedList.isEmpty()) {

            for (int i = 0; i < selectedList.size(); i++) {
                LOGGER.debug("inside for");
                CopyComponentDTO dto = new CopyComponentDTO();
                ContractSelectionDTO contractDto = selectedList.get(i);
                dto.setContractHolder(contractDto.getContractHolder());
                dto.setContractName(contractDto.getContractName());
                dto.setContractNo(contractDto.getContractNo());
                HelperDTO marketType=new HelperDTO();
                marketType.setId(0);
                marketType.setDescription(contractDto.getMarketType());
                dto.setMarketType(marketType);
                if (!contractDto.getStartDate().equals(Constants.NULL)) {
                    try {
                        String date = String.valueOf(contractDto.getStartDate());
                        Date d = sdfSource.parse(date);
                        String s = dateFormat.format(d);
                        dto.setContractStartDate(s);
                    } catch (ParseException ex) {
                        LOGGER.error("",ex);
                    }
                } else {
                    dto.setContractStartDate(Constants.EMPTY);
                }
                if (!contractDto.getEndDate().equals(Constants.NULL)) {
                    Date date = dbDateFormat.parse(String.valueOf(contractDto.getEndDate()));
                    String finalString = dateFormat.format(date);
                    dto.setContractEndDate(finalString);
                } else {
                    dto.setContractEndDate(Constants.EMPTY);
                }
                dto.setCFPname(contractDto.getCFPname());
                dto.setIFPname(contractDto.getIFPname());
                dto.setPSname(contractDto.getPSname());
                dto.setRSname(contractDto.getRSname());
                dto.setCFPId(contractDto.getCFPId());
                dto.setIFPId(contractDto.getIFPId());
                dto.setPSId(contractDto.getPSId());
                dto.setRSId(contractDto.getRSId());
                copyList.add(dto);
            }
        }
        return copyList;
    }

    public List<CopyComponentDTO> getComponentResults(String componentType, String query, int start, int offset) {
        String qry = query;
        qry += " ORDER BY 1 OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY";
        List<CopyComponentDTO> result = new ArrayList<>();
        List componentList = ccDao.searchList(qry);
        if (componentType.equalsIgnoreCase(Constants.CFP)) {
            result = setCompanyValues(componentList);
        } else if (componentType.equalsIgnoreCase(Constants.IFP)) {
            result = setIFPAttatchedItems(componentList);
        } else if (componentType.equalsIgnoreCase(Constants.PS)) {
            result = setPSAttatchedItems(componentList);
        } else if (componentType.equalsIgnoreCase(Constants.RS)) {
            result = setRSAttatchedItems(componentList);
        }
        return result;
    }

    public List<CopyComponentDTO> setCompanyValues(List componentList) {
        List<CopyComponentDTO> companyList = new ArrayList<>();
        if (componentList != null && !componentList.isEmpty()) {
            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO companyDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                companyDTO.setCompanyNo(String.valueOf(obje[0]));
                companyDTO.setTpNo(String.valueOf(obje[0]));
                companyDTO.setCompanyName(String.valueOf(obje[1]));
                companyDTO.setCompanyStatus(String.valueOf(obje[NumericConstants.TWO]));
                if (obje[NumericConstants.THREE] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.THREE]);
                    companyDTO.setCompanyStartDate(date);
                } else {
                    companyDTO.setCompanyStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.FOUR] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.FOUR]);
                    companyDTO.setCompanyEndDate(date);
                } else {
                    companyDTO.setCompanyEndDate(Constants.EMPTY);
                }
                companyList.add(companyDTO);
            }
        }
        return companyList;
    }

    public List<CopyComponentDTO> setIFPAttatchedItems(List componentList) {
        List<CopyComponentDTO> itemList = new ArrayList<>();

        if (componentList != null && !componentList.isEmpty()) {
            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO itemDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                itemDTO.setItemNo(String.valueOf(obje[0]));
                itemDTO.setItemName(String.valueOf(obje[1]));
                itemDTO.setTherapyClass(((obje[NumericConstants.TWO] != null) && !Constants.SELECT_ONE.equals(obje[NumericConstants.TWO]) && !Constants.NULL.equals(obje[NumericConstants.TWO]))
                        ? String.valueOf(obje[NumericConstants.TWO]) : Constants.EMPTY);
                itemDTO.setBrand((obje[NumericConstants.THREE] != null) ? String.valueOf(obje[NumericConstants.THREE]) : Constants.EMPTY);
                itemDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
                if (obje[NumericConstants.FIVE] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.FIVE]);
                    itemDTO.setIfpStartDate(date);
                } else {
                    itemDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.SIX] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.SIX]);
                    itemDTO.setIfpEndDate(date);
                } else {
                    itemDTO.setIfpEndDate(Constants.EMPTY);
                }
                itemList.add(itemDTO);
            }
        }
        return itemList;
    }

    public List<CopyComponentDTO> setPSAttatchedItems(List componentList) {
        List<CopyComponentDTO> priceList = new ArrayList<>();

        if (componentList != null && !componentList.isEmpty()) {

            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO itemDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                itemDTO.setItemNo(String.valueOf(obje[0]));
                itemDTO.setItemName(String.valueOf(obje[1])); 
                itemDTO.setTherapyClass(((obje[NumericConstants.TWO] != null) && !Constants.SELECT_ONE.equals(obje[NumericConstants.TWO]) && !Constants.NULL.equals(obje[NumericConstants.TWO]))
                        ? String.valueOf(obje[NumericConstants.TWO]) : Constants.EMPTY);
                itemDTO.setBrand((obje[NumericConstants.THREE] != null) ? String.valueOf(obje[NumericConstants.THREE]) : Constants.EMPTY);
                itemDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
                if (obje[NumericConstants.FIVE] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.FIVE]);
                    itemDTO.setIfpStartDate(date);
                } else {
                    itemDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.SIX] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.SIX]);
                    itemDTO.setIfpEndDate(date);
                } else {
                    itemDTO.setIfpEndDate(Constants.EMPTY);
                }
                if ((obje[NumericConstants.EIGHT] != null) && !obje[NumericConstants.EIGHT].equals(Constants.SELECT_ONE) && !obje[NumericConstants.EIGHT].equals(Constants.NULL)) {
                    itemDTO.setPriceType(String.valueOf(obje[NumericConstants.EIGHT]));
                } else {
                    itemDTO.setPriceType(Constants.EMPTY);
                }
                if (obje[NumericConstants.SEVEN] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.SEVEN]);
                    itemDTO.setPpStartDate(date);
                } else {
                    itemDTO.setPpStartDate(Constants.EMPTY);
                }
                itemDTO.setPricePlanNo(obje[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.NINE]));
                itemDTO.setPricePlanName(obje[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TEN]));
                itemDTO.setPriceProtectionStatus(obje[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.ELEVEN]));
                itemDTO.setPriceProtectionEndDate(Constants.NULL.equals(String.valueOf(obje[NumericConstants.TWELVE])) ? StringUtils.EMPTY : obje[NumericConstants.TWELVE].toString());
                itemDTO.setPriceProtectionPriceType(obje[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THIRTEEN]));
                itemDTO.setPriceToleranceInterval(checkNullValue(obje[NumericConstants.FOURTEEN]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.FOURTEEN].toString())));
                itemDTO.setPriceToleranceFrequency(checkNullValue(obje[NumericConstants.FIFTEEN]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.FIFTEEN].toString())));
                itemDTO.setMaxIncrementalChange(checkNullValue(obje[NumericConstants.SEVENTEEN]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SEVENTEEN]));
                itemDTO.setPriceTolerance(checkNullValue(obje[NumericConstants.EIGHTEEN]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.EIGHTEEN]));
                itemDTO.setPriceToleranceType(checkNullValue(obje[NumericConstants.SIXTEEN]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SIXTEEN]));
                itemDTO.setEligibility(checkNullValue(obje[NumericConstants.TWENTY]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.TWENTY].toString())));
                itemDTO.setResetType(checkNullValue(obje[NumericConstants.TWENTY_ONE]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWENTY_ONE]));
                itemDTO.setResetDate(checkNullValue(obje[NumericConstants.TWENTY_TWO]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWENTY_TWO]));
                itemDTO.setResetIntervel(checkNullValue(obje[NumericConstants.TWENTY_THREE]) ? StringUtils.EMPTY : getDescription(Integer.parseInt(obje[NumericConstants.TWENTY_THREE].toString())));
                itemDTO.setResetFrequency(checkNullValue(obje[NumericConstants.TWENTY_FOUR]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWENTY_FOUR]));
                itemDTO.setAttachedDate(checkNullValue(obje[NumericConstants.TWENTY_FIVE]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWENTY_FIVE]));
                priceList.add(itemDTO);
            }
        }
        return priceList;
    }

    public List<CopyComponentDTO> setRSAttatchedItems(List componentList) {
        List<CopyComponentDTO> rebateList = new ArrayList<>();
        if (componentList != null && !componentList.isEmpty()) {

            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO rebateDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                rebateDTO.setItemNo(String.valueOf(obje[0]));
                rebateDTO.setItemName(String.valueOf(obje[1]));
                rebateDTO.setTherapyClass(((obje[NumericConstants.TWO] != null) && !Constants.SELECT_ONE.equals(obje[NumericConstants.TWO]) && !Constants.NULL.equals(obje[NumericConstants.TWO]))
                        ? String.valueOf(obje[NumericConstants.TWO]) : Constants.EMPTY);
                rebateDTO.setBrand((obje[NumericConstants.THREE] != null) ? String.valueOf(obje[NumericConstants.THREE]) : Constants.EMPTY);
                rebateDTO.setIfpStatus(String.valueOf(obje[NumericConstants.FOUR]));
                if (obje[NumericConstants.FIVE] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.FIVE]);
                    rebateDTO.setIfpStartDate(date);
                } else {
                    rebateDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.SIX] != null) {
                    String date = dateFormat.format((Date) obje[NumericConstants.SIX]);
                    rebateDTO.setIfpEndDate(date);
                } else {
                    rebateDTO.setIfpEndDate(Constants.EMPTY);
                }
                rebateDTO.setFormulaId((obje[NumericConstants.SEVEN] != null) ? String.valueOf(obje[NumericConstants.SEVEN]) : Constants.EMPTY);
                rebateDTO.setRebatePlan((obje[NumericConstants.EIGHT] != null) ? String.valueOf(obje[NumericConstants.EIGHT]) : Constants.EMPTY);
                rebateDTO.setRebatePlanName((obje[NumericConstants.EIGHT] != null) ? String.valueOf(obje[NumericConstants.EIGHT]) : Constants.EMPTY);
                rebateDTO.setFormulaType(obje[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.NINE]));
                rebateDTO.setFormulaName(obje[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TEN]));
                rebateDTO.setRebatePlanId(obje[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.ELEVEN]));
                rebateDTO.setRebateAmount(obje[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWELVE]));
                rebateDTO.setBundleNo(obje[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THIRTEEN]));
                rebateDTO.setAttachedDate(null);
                rebateList.add(rebateDTO);
            }

        }
        return rebateList;
    }

    public void insertIntoRsDetails(String temptableSId, String userId, int rsModelSid) {
        String query = "INSERT INTO RS_DETAILS (RS_MODEL_SID,IFP_MODEL_SID,ITEM_MASTER_SID,ITEM_REBATE_START_DATE,INBOUND_STATUS,RECORD_LOCK_STATUS,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE)\n"
                + "select " + rsModelSid + ",IFP_MODEL_SID,ITEM_MASTER_SID,PS_DTLS_CONT_PRICE_STARTDATE,'A','0'," + userId + ",getDate()," + userId + ",getDate() from IMTD_PS_DETAILS where PS_MODEL_SID='" + temptableSId + "'";
        LOGGER.debug("Rs Detailsssss Queryy:::: {} " , query);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void updatePsAndRsids(int ifpModelSid, String temptableSId, String queryName) {
        List input=new ArrayList<>();
        input.add(ifpModelSid);
        input.add(temptableSId);
        ItemQueries.itemUpdate(input, queryName);
    }

    public void insertPsDetails(int psModelSid, String userId, String temptableSId) {
        List input = new ArrayList<>();
        input.add(psModelSid);
        input.add(userId);
        input.add(userId);
        input.add(temptableSId);
        ItemQueries.itemUpdate(input, "Copy Contract- Insert Rs details");
    }
        public static Boolean checkNullValue(Object obj) {
        if (obj == null || Constants.ZEROSTRING.equals(String.valueOf(obj)) ||Constants.NULL.equals(obj) || Constants.SELECT_ONE.contains(String.valueOf(obj))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
        
     public static String getDescription(int code) {
        try {
            HelperTable table = HelperTableLocalServiceUtil.getHelperTable(code);
            return table.getDescription();
        } catch (PortalException | SystemException ex) {
            LOGGER.error("", ex);
            return null;
        }
    }
        

}
