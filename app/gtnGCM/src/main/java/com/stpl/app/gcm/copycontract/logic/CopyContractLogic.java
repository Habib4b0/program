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
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gcm.util.DataTypeConverter;
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
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyy");
    public static final String SPACE_COUNT = " COUNT";
    private final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd ");
    private final TradingPartnerDAO ccDao = new TradingPartnerDAOImpl();
    private final SimpleDateFormat sdfSource = new SimpleDateFormat(Constants.DBDATE_FORMAT);

    public List<HelperDTO> getDropDownList(final String listType) {
        final List<HelperDTO> helperList = new ArrayList<>();

        LOGGER.debug("entering getDropDownList method with paramater listType=" + listType);
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

        LOGGER.debug(" getDropDownList method ends with return value strList size =" + helperList.size());

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

    public List loadSearchFieldDDLB(String Tablename) {
        final List items = dao.getColumnNames(Tablename);
        return items;
    }

    public int getContractSearchCount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getCFPCount(CFPCompanyDTO, bsc);
        return count;
    }

    public int getIFPSearchCount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getIFPCount(IFPItemDTO, bsc);
        return count;
    }

    public int getIFPAttachedItemCount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getAttachedItemCount(IFPItemDTO, bsc);
        return count;
    }

    public int getPSSearchCount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getPSCount(PSIFPDTO, bsc);
        return count;
    }

    public int getPSAttachedItemSearchCount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getPSAttachedItemCount(PSIFPDTO, bsc);
        return count;
    }

    public int getRSAttachedItemSearchCount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getRSAttachedItemCount(RsIfpDto, bsc);
        return count;
    }

    public int getRSSearchCount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getRSCount(RsIfpDto, bsc);
        return count;
    }

    public List<ExistingComponentDTO> getRSAttachedItemSearch(RsIfpDto RsIfpDto, int startIndex,
            int offset, BeanSearchCriteria bsc, List<OrderByColumn> list) throws ParseException {
        List resultList;
        String dbColumnName = StringUtils.EMPTY;
        boolean asc = false;
        List<ExistingComponentDTO> searchList;
        resultList = dao.getRsItemdetails(RsIfpDto, bsc, startIndex, offset, list, dbColumnName, asc);
        searchList = setRSmainValues(resultList);
        return searchList;

    }

    public List<ExistingComponentDTO> setCFPValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO CFPCompanyDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            CFPCompanyDto = new ExistingComponentDTO();
            CFPCompanyDto.setCompanyId(String.valueOf(objects[0]));
            CFPCompanyDto.setCompanyNo(String.valueOf(objects[1]));
            CFPCompanyDto.setCompanyName(String.valueOf(objects[NumericConstants.TWO]));
            if (objects[NumericConstants.THREE] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.THREE]));
                String finalString = DATE_FORMAT.format(date);
                CFPCompanyDto.setTradeClassStartDate(finalString);
                CFPCompanyDto.setStartDate(finalString);
            } else {
                CFPCompanyDto.setTradeClassStartDate(StringUtils.EMPTY);
                CFPCompanyDto.setStartDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FOUR] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.FOUR]));
                String finalString = DATE_FORMAT.format(date);
                CFPCompanyDto.setTradeClassEndDate(finalString);
                CFPCompanyDto.setEndDate(finalString);
            } else {
                CFPCompanyDto.setEndDate(StringUtils.EMPTY);
            }
            CFPCompanyDto.setTradeClass(objects[NumericConstants.SIX] != null && !String.valueOf(objects[NumericConstants.SIX]).trim().equalsIgnoreCase(Constants.SELECT_ONE)
                    && !String.valueOf(objects[NumericConstants.SIX]).isEmpty() ? String.valueOf(objects[NumericConstants.SIX]) : StringUtils.EMPTY);
            CFPCompanyDto.setAttachedDate(null);
            CFPCompanyDto.setCompanyStatusValue(Constants.NULL.equals(String.valueOf(objects[NumericConstants.FIVE])) ? StringUtils.EMPTY : objects[NumericConstants.FIVE].toString());
            returnList.add(CFPCompanyDto);
        }

        return returnList;
    }

    public List<ExistingComponentDTO> setCFPMainValues(List list) throws ParseException {

        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO CFPCompanyDto;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            CFPCompanyDto = new ExistingComponentDTO();

            CFPCompanyDto.setCompanyFamilyPlanId(String.valueOf(objects[0]));
            CFPCompanyDto.setCompanyFamilyPlanNo(String.valueOf(objects[1]));
            CFPCompanyDto.setCompanyFamilyPlanName(String.valueOf(objects[NumericConstants.TWO]));
            if (objects[NumericConstants.TWELVE] != null) {
                CFPCompanyDto.setCompanyFamilyPlanName(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.TWELVE])) ? StringUtils.EMPTY : objects[NumericConstants.TWELVE].toString());
            } else {
                CFPCompanyDto.setCompanyFamilyPlanTypeValue(StringUtils.EMPTY);
            }

            CFPCompanyDto.setCompanyFamilyPlanStatusValue(String.valueOf(objects[NumericConstants.THIRTEEN]));
            if (objects[NumericConstants.FIVE] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.FIVE]));
                String finalString = DATE_FORMAT.format(date);
                CFPCompanyDto.setCompanyFamilyPlanStartDate(finalString);
            } else {
                CFPCompanyDto.setCompanyFamilyPlanStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.SIX] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.SIX]));
                String finalString = DATE_FORMAT.format(date);
                CFPCompanyDto.setCompanyFamilyPlanEndDate(finalString);
            } else {
                CFPCompanyDto.setCompanyFamilyPlanEndDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FIFTEEN] != null) {
                CFPCompanyDto.setCompanyFamilyPlanCategoryValue(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.FIFTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.FIFTEEN].toString());
            } else {
                CFPCompanyDto.setCompanyFamilyPlanCategoryValue(StringUtils.EMPTY);
            }
            CFPCompanyDto.setCompanyFamilyTradeClassValue(String.valueOf(objects[NumericConstants.FOURTEEN]));
            CFPCompanyDto.setCompanyFamilyPlanDesignation(Constants.NULL.equals(String.valueOf(objects[NumericConstants.NINE])) ? StringUtils.EMPTY : objects[NumericConstants.NINE].toString());
            CFPCompanyDto.setCompanyFamilyPlanSystemId(Integer.parseInt(String.valueOf(objects[NumericConstants.SIXTEEN])));
            returnList.add(CFPCompanyDto);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setIFPValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO IFPItemDTO;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            IFPItemDTO = new ExistingComponentDTO();

            IFPItemDTO.setItemNo(String.valueOf(objects[0]));
            IFPItemDTO.setItemName(String.valueOf(objects[1]));
            if (objects[NumericConstants.TWO] != null) {
                IFPItemDTO.setTherapeuticClass(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.TWO])) ? StringUtils.EMPTY : objects[NumericConstants.TWO].toString());
            } else {
                IFPItemDTO.setTherapeuticClass(StringUtils.EMPTY);
            }
            IFPItemDTO.setBrand(Constants.NULL.equals(String.valueOf(objects[NumericConstants.THREE])) ? StringUtils.EMPTY : objects[NumericConstants.THREE].toString());
            IFPItemDTO.setItemStatus(Constants.NULL.equals(String.valueOf(objects[NumericConstants.SIX])) ? StringUtils.EMPTY : objects[NumericConstants.SIX].toString());
            if (objects[NumericConstants.FOUR] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.FOUR]));
                String finalString = DATE_FORMAT.format(date);
                IFPItemDTO.setItemStartDate(finalString);
                IFPItemDTO.setStartDate(finalString);
            } else {
                IFPItemDTO.setItemStartDate(StringUtils.EMPTY);
                IFPItemDTO.setStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.FIVE] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.FIVE]));
                String finalString = DATE_FORMAT.format(date);
                IFPItemDTO.setItemEndDate(finalString);
                IFPItemDTO.setEndDate(finalString);
            } else {
                IFPItemDTO.setItemEndDate(StringUtils.EMPTY);
                IFPItemDTO.setEndDate(StringUtils.EMPTY);
            }
            IFPItemDTO.setAttachedDate(null);
            returnList.add(IFPItemDTO);
        }
        return returnList;

    }

    public List<ExistingComponentDTO> setIFPmainValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO IFPItemDTO;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            IFPItemDTO = new ExistingComponentDTO();
            IFPItemDTO.setIfpDetailsSystemId(Integer.parseInt(String.valueOf(objects[0])));
            IFPItemDTO.setItemFamilyplanId(String.valueOf(objects[1]));
            IFPItemDTO.setItemFamilyplanNo(String.valueOf(objects[NumericConstants.TWO]));
            IFPItemDTO.setItemFamilyplanName(String.valueOf(objects[NumericConstants.THREE]));
            if (objects[NumericConstants.SEVEN] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.SEVEN]));
                String finalString = DATE_FORMAT.format(date);
                IFPItemDTO.setIfpStartDate(finalString);
            } else {
                IFPItemDTO.setIfpStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.EIGHT] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.EIGHT]));
                String finalString = DATE_FORMAT.format(date);
                IFPItemDTO.setIfpEndDate(finalString);
            } else {
                IFPItemDTO.setIfpEndDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.EIGHTEEN] != null) {
                IFPItemDTO.setItemFamilyplanType(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.EIGHTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.EIGHTEEN].toString());
            } else {
                IFPItemDTO.setItemFamilyplanType(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FIVE] != null) {
                IFPItemDTO.setIFPtype(Integer.valueOf(String.valueOf(objects[NumericConstants.FIVE])));
            }
            if (objects[NumericConstants.NINETEEN] != null) {
                IFPItemDTO.setDisplayIFPStatus(Constants.NULL.equals(String.valueOf(objects[NumericConstants.NINETEEN])) ? StringUtils.EMPTY : objects[NumericConstants.NINETEEN].toString());
            }
            if (objects[NumericConstants.FOUR] != null) {
                IFPItemDTO.setItemFamilyplanStatus(Integer.valueOf(String.valueOf(objects[NumericConstants.FOUR])));
            }

            if (objects[NumericConstants.TWENTY_ONE] != null) {
                IFPItemDTO.setItemFamilyplanDesignation(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.TWENTY_ONE])) ? StringUtils.EMPTY : objects[NumericConstants.TWENTY_ONE].toString());
            }
            IFPItemDTO.setItemFamilyplanDesignation(StringUtils.EMPTY);

            returnList.add(IFPItemDTO);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setPSValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO PSIFPDTO;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            PSIFPDTO = new ExistingComponentDTO();

            PSIFPDTO.setItemNo(String.valueOf(objects[0]));
            PSIFPDTO.setItemName(String.valueOf(objects[1]));
            PSIFPDTO.setTherapeuticClass(Constants.NULL.equals(String.valueOf(objects[NumericConstants.TWO])) ? StringUtils.EMPTY : objects[NumericConstants.TWO].toString());
            PSIFPDTO.setBrand(Constants.NULL.equals(String.valueOf(objects[NumericConstants.THREE])) ? StringUtils.EMPTY : objects[NumericConstants.THREE].toString());
            PSIFPDTO.setItemStatus(String.valueOf(objects[NumericConstants.SIX]));

            if (objects[NumericConstants.FOUR] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.FOUR]));
                String finalString = DATE_FORMAT.format(date);
                PSIFPDTO.setItemStartDate(finalString);
                PSIFPDTO.setStartDate(finalString);
            } else {
                PSIFPDTO.setItemStartDate(StringUtils.EMPTY);
                PSIFPDTO.setStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.FIVE] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.FIVE]));
                String finalString = DATE_FORMAT.format(date);
                PSIFPDTO.setItemEndDate(finalString);
                PSIFPDTO.setEndDate(finalString);
            } else {
                PSIFPDTO.setItemEndDate(StringUtils.EMPTY);
                PSIFPDTO.setEndDate(StringUtils.EMPTY);
            }

            PSIFPDTO.setPriceType(Constants.NULL.equals(String.valueOf(objects[NumericConstants.SEVEN])) ? StringUtils.EMPTY : objects[NumericConstants.SEVEN].toString());
            PSIFPDTO.setPriceProtectionStartDate(Constants.NULL.equals(String.valueOf(objects[NumericConstants.EIGHT])) ? StringUtils.EMPTY : objects[NumericConstants.EIGHT].toString());
            PSIFPDTO.setPricePlanNo(objects[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.NINE]));
            PSIFPDTO.setPricePlanName(objects[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TEN]));
            PSIFPDTO.setPriceProtectionStatus(objects[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.ELEVEN]));
            PSIFPDTO.setPriceProtectionEndDate(Constants.NULL.equals(String.valueOf(objects[NumericConstants.TWELVE])) ? StringUtils.EMPTY : objects[NumericConstants.TWELVE].toString());
            PSIFPDTO.setPriceProtectionPriceType(objects[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.THIRTEEN]));
            PSIFPDTO.setPriceToleranceInterval(objects[NumericConstants.FOURTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.FOURTEEN]));
            PSIFPDTO.setPriceToleranceFrequency(objects[NumericConstants.FIFTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.FIFTEEN]));
            PSIFPDTO.setMaxIncrementalChange(objects[NumericConstants.SEVENTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.SEVENTEEN]));
            PSIFPDTO.setPriceTolerance(objects[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.EIGHTEEN]));
            PSIFPDTO.setPriceToleranceType(objects[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.SIXTEEN]));
            PSIFPDTO.setEligibility(objects[NumericConstants.TWENTY] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY]));
            PSIFPDTO.setResetType(objects[NumericConstants.TWENTY_ONE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY_ONE]));
            PSIFPDTO.setResetDate(objects[NumericConstants.TWENTY_TWO] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY_TWO]));
            PSIFPDTO.setResetIntervel(objects[NumericConstants.TWENTY_THREE] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY_THREE]));
            PSIFPDTO.setResetFrequency(objects[NumericConstants.TWENTY_FOUR] == null ? StringUtils.EMPTY : String.valueOf(objects[NumericConstants.TWENTY_FOUR]));
            PSIFPDTO.setAttachedDate(null);
            returnList.add(PSIFPDTO);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setPSmainValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO PSIFPDTO;
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            PSIFPDTO = new ExistingComponentDTO();

            PSIFPDTO.setPriceScheduleSystemId(String.valueOf(objects[0]));
            PSIFPDTO.setPriceScheduleIdValue(String.valueOf(objects[1]));
            PSIFPDTO.setPriceScheduleNoValue(String.valueOf(objects[NumericConstants.TWO]));
            PSIFPDTO.setPriceScheduleNameValue(String.valueOf(objects[NumericConstants.THREE]));
            PSIFPDTO.setPriceScheduleStatusValue(Constants.NULL.equals(String.valueOf(objects[NumericConstants.FOURTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.FOURTEEN].toString());
            if (objects[NumericConstants.SEVEN] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.SEVEN]));
                String finalString = DATE_FORMAT.format(date);
                PSIFPDTO.setPriceScheduleStartDate(finalString);

            } else {
                PSIFPDTO.setPriceScheduleStartDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.EIGHT] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.EIGHT]));
                String finalString = DATE_FORMAT.format(date);
                PSIFPDTO.setPriceScheduleEndDate(finalString);
            } else {
                PSIFPDTO.setPriceScheduleEndDate(StringUtils.EMPTY);
            }

            PSIFPDTO.setItemFamilyplanName(String.valueOf(objects[NumericConstants.NINETEEN]));
            if (objects[NumericConstants.THIRTEEN] != null) {
                PSIFPDTO.setPriceScheduleTypeValue(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.THIRTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.THIRTEEN].toString());
            } else {
                PSIFPDTO.setPriceScheduleTypeValue(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.FIFTEEN] != null) {
                PSIFPDTO.setPriceScheduleCategoryValue(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.FIFTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.FIFTEEN].toString());
            } else {
                PSIFPDTO.setPriceScheduleCategoryValue(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.SIXTEEN] != null) {
                PSIFPDTO.setPriceScheduleDesignationValue(Constants.SELECT_ONE.equals(String.valueOf(objects[NumericConstants.SIXTEEN])) ? StringUtils.EMPTY : objects[NumericConstants.SIXTEEN].toString());
            } else {
                PSIFPDTO.setPriceScheduleDesignationValue(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FIVE] != null) {
                PSIFPDTO.setPStatus(Integer.valueOf(String.valueOf(objects[NumericConstants.FIVE])));
            }
            if (objects[NumericConstants.FOUR] != null) {
                PSIFPDTO.setPtype(Integer.valueOf(String.valueOf(objects[NumericConstants.FOUR])));
            }

            returnList.add(PSIFPDTO);
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
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.ELEVEN]));
                String finalString = DATE_FORMAT.format(date);
                rsIfpDto.setItemRebateStartDate(finalString);
            } else {
                rsIfpDto.setItemRebateStartDate(StringUtils.EMPTY);
            }
            if (objects[NumericConstants.TWELVE] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.TWELVE]));
                String finalString = DATE_FORMAT.format(date);
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
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.FOUR]));
                String finalString = DATE_FORMAT.format(date);
                rsIfpDto.setItemStartDate(finalString);
                rsIfpDto.setStartDate(finalString);

            } else {
                rsIfpDto.setItemStartDate(StringUtils.EMPTY);
                rsIfpDto.setStartDate(StringUtils.EMPTY);
            }

            if (objects[NumericConstants.FIVE] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(objects[NumericConstants.FIVE]));
                String finalString = DATE_FORMAT.format(date);
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

    public int getCompanySearchCount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc) {
        int count = 0;
        count = dao.getCompanyCount(CFPCompanyDTO, bsc);
        return count;
    }

    public void SaveCFP(String cfpid, Integer cfpmodelid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(cfpid);
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updateCFP(input);
    }

    public void SaveIFP(String ifpId, Integer cfpmodelid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(ifpId);
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updateIFP(input);
    }

    public void SavePS(String psid, Integer cfpmodelid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(psid);
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updatePS(input);
    }

    public void SaveRS(String rsid, Integer RSmodalid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(rsid);
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(RSmodalid);
        dao.updateRS(input);

    }

    public void SaveCFPForCopyComponent(String cfpid, String cfpModelSId) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(cfpid);
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(cfpModelSId);
        dao.updateCFP(input);

    }

    public void SaveIFPForCopyComponent(String ifpId, String ifpModelSid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(ifpId);
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(ifpModelSid);
        dao.updateIFP(input);
    }

    public void SavePSForCopyComponent(String psid, String psModelSid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(psid);
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(psModelSid);
        dao.updatePS(input);
    }

    public void SaveRSForCopyComponent(String rsid, String rsModelSid) {
        List<Object> input = new ArrayList<>(NumericConstants.FIVE);
        input.add(rsid);
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(DB_DATE_FORMAT.format(new java.util.Date()));
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
        if (itemList != null && itemList.size() > 0) {
            for (int i = 0; i < itemList.size(); i++) {
                NewComponentDTO itemDTO = new NewComponentDTO();
                Object[] obje = (Object[]) itemList.get(i);
                itemDTO.setIfpNo(String.valueOf(obje[0]));
                itemDTO.setIfpName(String.valueOf(obje[1]));
                itemDTO.setBrand(obje[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWO]));
                itemDTO.setItemStatus(obje[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THREE]));
                if (obje[NumericConstants.FOUR] != null) {
                    Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.FOUR]));
                    String finalString = DATE_FORMAT.format(date);
                    itemDTO.setPsStartDate(finalString);
                } else {
                    itemDTO.setPsStartDate(StringUtils.EMPTY);
                }
                if (obje[NumericConstants.FIVE] != null) {
                    Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.FIVE]));
                    String finalString = DATE_FORMAT.format(date);
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
        if (itemList != null && itemList.size() > 0) {
            for (int i = 0; i < itemList.size(); i++) {
                NewComponentDTO itemDTO = new NewComponentDTO();
                Object[] obje = (Object[]) itemList.get(i);
                itemDTO.setItemNo(String.valueOf(obje[0]));
                itemDTO.setItemName(String.valueOf(obje[1]));
                itemDTO.setBrand(checkNullValue(obje[NumericConstants.TWO]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWO]));
                itemDTO.setItemStatus(checkNullValue(obje[NumericConstants.THREE]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THREE]));
                if (obje[NumericConstants.FOUR] != null) {
                    Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.FOUR]));
                    String finalString = DATE_FORMAT.format(date);
                    itemDTO.setPsStartDate(finalString);
                } else {
                    itemDTO.setPsStartDate(StringUtils.EMPTY);
                }
                if (obje[NumericConstants.FIVE] != null) {
                    Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.FIVE]));
                    String finalString = DATE_FORMAT.format(date);
                    itemDTO.setPsEndDate(finalString);
                } else {
                    itemDTO.setPsEndDate(Constants.EMPTY);
                }
                itemDTO.setItemType(obje[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SIX]));
                itemDTO.setPricePlanNo(obje[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SEVEN]));
                itemDTO.setPricePlanName(obje[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.EIGHT]));
                itemDTO.setPriceProtectionStatus(checkNullValue(obje[NumericConstants.NINE]) ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.NINE]));
                if (obje[NumericConstants.TEN] != null) {
                    Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.TEN]));
                    String finalString = DATE_FORMAT.format(date);
                    itemDTO.setCompanyStartDate(finalString);
                } else {
                    itemDTO.setCompanyStartDate(StringUtils.EMPTY);
                }
                if (obje[NumericConstants.ELEVEN] != null) {
                    Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.ELEVEN]));
                    String finalString = DATE_FORMAT.format(date);
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
            NewComponentDTO Company = new NewComponentDTO();
            Object[] obje = (Object[]) list.get(i);
            Company.setModelId(String.valueOf(obje[0]));
            Company.setCompanyId(String.valueOf(obje[1]));
            Company.setCompanyNo(String.valueOf(obje[NumericConstants.TWO]));
            Company.setCompanyName(String.valueOf(obje[NumericConstants.THREE]));
            Company.setCompanyType(String.valueOf(obje[NumericConstants.FOUR]));
            if (obje[NumericConstants.FIVE] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.FIVE]));
                String finalString = DATE_FORMAT.format(date);
                Company.setPsStartDate(finalString);
            } else {
                Company.setPsStartDate(StringUtils.EMPTY);
            }
            if (obje[NumericConstants.SIX] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.SIX]));
                String finalString = DATE_FORMAT.format(date);
                Company.setPsEndDate(finalString);
            } else {
                Company.setPsEndDate(Constants.EMPTY);
            }
            Company.setCompanyStatus(String.valueOf(obje[NumericConstants.SEVEN]));
            Company.setTradeClass(String.valueOf(obje[NumericConstants.EIGHT]));
            Company.setAttachedDate((Date) obje[NumericConstants.NINE]);
            companylist.add(Company);
        }
        return companylist;
    }

    private Object getCustomizedIFPData(List list) throws ParseException {
        List<NewComponentDTO> companylist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            NewComponentDTO Company = new NewComponentDTO();
            Object[] obje = (Object[]) list.get(i);
            Company.setModelId(String.valueOf(obje[NumericConstants.ELEVEN]));
            Company.setItemId(String.valueOf(obje[0]));
            Company.setItemNo(String.valueOf(obje[1]));
            Company.setItemName(obje[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.TWO]));
            Company.setItemStatus(obje[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.THREE]));
            if (obje[NumericConstants.FOUR] != null) {
                if (String.valueOf(obje[NumericConstants.FOUR]).equalsIgnoreCase(Constants.SELECT_ONE)) {
                    Company.setItemType(Constants.EMPTY);
                } else {
                    Company.setItemType(String.valueOf(obje[NumericConstants.FOUR]));
                }
            }
            if (obje[NumericConstants.EIGHT] != null) {
                if (String.valueOf(obje[NumericConstants.EIGHT]).equalsIgnoreCase(Constants.SELECT_ONE)) {
                    Company.setTherapyClass(Constants.EMPTY);
                } else {
                    Company.setTherapyClass(String.valueOf(obje[NumericConstants.EIGHT]));
                }
            }
            Company.setBrand(obje[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.FIVE]));
            Company.setForm(obje[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SIX]));
            Company.setStrength(obje[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(obje[NumericConstants.SEVEN]));

            if (obje[NumericConstants.NINE] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.NINE]));
                String finalString = DATE_FORMAT.format(date);
                Company.setPsStartDate(finalString);
            } else {
                Company.setPsStartDate(StringUtils.EMPTY);
            }
            if (obje[NumericConstants.TEN] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.TEN]));
                String finalString = DATE_FORMAT.format(date);
                Company.setPsEndDate(finalString);
            } else {
                Company.setPsEndDate(Constants.EMPTY);
            }
            Company.setAttachedDate((Date) obje[NumericConstants.TWELVE]);
            companylist.add(Company);
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
        if (itemData != null && itemData.size() > 0) {
            for (int i = 0; i < itemData.size(); i++) {
                CopyComponentDTO companyDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) itemData.get(i);
                companyDTO.setCompanyNo(String.valueOf(obje[0]));
                companyDTO.setCompanyName(String.valueOf(obje[1]));
                companyDTO.setCompanyStatus(String.valueOf(obje[NumericConstants.TWO]));
                if (obje[NumericConstants.THREE] != null) {
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.THREE]);
                    companyDTO.setCompanyStartDate(date);
                } else {
                    companyDTO.setCompanyStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.FOUR] != null) {
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.FOUR]);
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
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.FIVE]));
                String finalString = DATE_FORMAT.format(date);
                itemDTO.setIfpStartDate(finalString);
            } else {
                itemDTO.setIfpStartDate(StringUtils.EMPTY);
            }
            if (obje[NumericConstants.SIX] != null) {
                Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(obje[NumericConstants.SIX]));
                String finalString = DATE_FORMAT.format(date);
                itemDTO.setIfpEndDate(finalString);
            } else {
                itemDTO.setIfpEndDate(StringUtils.EMPTY);
            }

            itemList.add(itemDTO);
        }
        return itemList;
    }

    public int getComponentCount(String Query) {
        List componentList = ccDao.searchList(Query);
        return componentList.size();
    }

    public List<CopyComponentDTO> getComponentinfoResults(List<ContractSelectionDTO> selectedlist) throws ParseException {

        return SelectedContracts(selectedlist);

    }

    public List<CopyComponentDTO> SelectedContracts(List<ContractSelectionDTO> selectedList) throws ParseException {
        List<CopyComponentDTO> copyList = new ArrayList<>();
        if (selectedList != null && selectedList.size() > 0) {

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
                        String s = DATE_FORMAT.format(d);
                        dto.setContractStartDate(s);
                    } catch (ParseException ex) {
                        LOGGER.error("",ex);
                    }
                } else {
                    dto.setContractStartDate(Constants.EMPTY);
                }
                if (!contractDto.getEndDate().equals(Constants.NULL)) {
                    Date date = (Date) DB_DATE_FORMAT.parse(String.valueOf(contractDto.getEndDate()));
                    String finalString = DATE_FORMAT.format(date);
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

    public List<CopyComponentDTO> getComponentResults(String componentType, String Query, int start, int offset) {
        String qry = Query;
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
        if (componentList != null && componentList.size() > 0) {
            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO companyDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                companyDTO.setCompanyNo(String.valueOf(obje[0]));
                companyDTO.setTpNo(String.valueOf(obje[0]));
                companyDTO.setCompanyName(String.valueOf(obje[1]));
                companyDTO.setCompanyStatus(String.valueOf(obje[NumericConstants.TWO]));
                if (obje[NumericConstants.THREE] != null) {
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.THREE]);
                    companyDTO.setCompanyStartDate(date);
                } else {
                    companyDTO.setCompanyStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.FOUR] != null) {
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.FOUR]);
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

        if (componentList != null && componentList.size() > 0) {
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
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.FIVE]);
                    itemDTO.setIfpStartDate(date);
                } else {
                    itemDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.SIX] != null) {
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.SIX]);
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

        if (componentList != null && componentList.size() > 0) {

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
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.FIVE]);
                    itemDTO.setIfpStartDate(date);
                } else {
                    itemDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.SIX] != null) {
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.SIX]);
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
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.SEVEN]);
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
        if (componentList != null && componentList.size() > 0) {

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
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.FIVE]);
                    rebateDTO.setIfpStartDate(date);
                } else {
                    rebateDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[NumericConstants.SIX] != null) {
                    String date = DATE_FORMAT.format((Date) obje[NumericConstants.SIX]);
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
        LOGGER.debug("Rs Detailsssss Queryy::::" + query);
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
