/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic;

import static com.stpl.app.gcm.common.QueryUtils.CHAR_ASTERISK;
import static com.stpl.app.gcm.common.QueryUtils.CHAR_PERCENT;
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
import com.stpl.app.gcm.transfercontract.util.CommonUtil;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.ComboBox;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author kasiammal.m
 */
public class CopyContractLogic {

    public static final Logger LOGGER = Logger.getLogger(CopyContractLogic.class);
    private final ContractHeaderDAO dao = new ContractHeaderLogicDAOImpl();
    DateFormat format = new SimpleDateFormat("MM/dd/yyy");
    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd ");
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    TradingPartnerDAO ccDao = new TradingPartnerDAOImpl();
    SimpleDateFormat sdfSource = new SimpleDateFormat(Constants.DBDATE_FORMAT);

    public List<HelperDTO> getDropDownList(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
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

        LOGGER.info(" getDropDownList method ends with return value strList size =" + helperList.size());

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
        select.setPageLength(4);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(Constants.ZERO);
        return select;
    }

    public List loadSearchFieldDDLB(String Tablename) throws SystemException {
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
        // return null;
        List resultList = new ArrayList();
        String dbColumnName = StringUtils.EMPTY;
        boolean asc = false;
        List<ExistingComponentDTO> searchList = new ArrayList<ExistingComponentDTO>();
        resultList = dao.getRsItemdetails(RsIfpDto, bsc, startIndex, offset, list, dbColumnName, asc);
        searchList = setRSmainValues(resultList);
        return searchList;

    }

    public List<ExistingComponentDTO> setCFPValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<>();
        ExistingComponentDTO CFPCompanyDto;
        for (int i = 0; i < list.size(); i++) {
            Object objects[] = (Object[]) list.get(i);
            CFPCompanyDto = new ExistingComponentDTO();
            CFPCompanyDto.setCompanyId(String.valueOf(objects[0]));
            CFPCompanyDto.setCompanyNo(String.valueOf(objects[1]));
            CFPCompanyDto.setCompanyName(String.valueOf(objects[2]));
            if (objects[3] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[3]));
                String finalString = format.format(date);
                CFPCompanyDto.setTradeClassStartDate(finalString);
                CFPCompanyDto.setStartDate(finalString);
            } else {
                CFPCompanyDto.setTradeClassStartDate(StringUtils.EMPTY);
                CFPCompanyDto.setStartDate(StringUtils.EMPTY);
            }

            if (objects[4] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[4]));
                String finalString = format.format(date);
                CFPCompanyDto.setTradeClassEndDate(finalString);
                CFPCompanyDto.setEndDate(finalString);
            } else {
                CFPCompanyDto.setEndDate(StringUtils.EMPTY);
            }
            CFPCompanyDto.setTradeClass(objects[6] != null && !String.valueOf(objects[6]).trim().equalsIgnoreCase(Constants.SELECT_ONE)
                    && !String.valueOf(objects[6]).isEmpty() ? String.valueOf(objects[6]) : StringUtils.EMPTY);
            CFPCompanyDto.setAttachedDate(null);
            CFPCompanyDto.setCompanyStatusValue(Constants.NULL.equals(String.valueOf(objects[5])) ? StringUtils.EMPTY : objects[5].toString());
            returnList.add(CFPCompanyDto);
        }

        return returnList;
    }

    public List<ExistingComponentDTO> setCFPMainValues(List list) throws ParseException {

        List<ExistingComponentDTO> returnList = new ArrayList<ExistingComponentDTO>();
        ExistingComponentDTO CFPCompanyDto;
        for (int i = 0; i < list.size(); i++) {
            Object objects[] = (Object[]) list.get(i);
            CFPCompanyDto = new ExistingComponentDTO();

            CFPCompanyDto.setCompanyFamilyPlanId(String.valueOf(objects[0]));
            CFPCompanyDto.setCompanyFamilyPlanNo(String.valueOf(objects[1]));
            CFPCompanyDto.setCompanyFamilyPlanName(String.valueOf(objects[2]));
            if (objects[12] != null) {
                CFPCompanyDto.setCompanyFamilyPlanName(Constants.SELECT_ONE.equals(String.valueOf(objects[12])) ? StringUtils.EMPTY : objects[12].toString());
            } else {
                CFPCompanyDto.setCompanyFamilyPlanTypeValue(StringUtils.EMPTY);
            }

            CFPCompanyDto.setCompanyFamilyPlanStatusValue(String.valueOf(objects[13]));
            if (objects[5] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[5]));
                String finalString = format.format(date);
                CFPCompanyDto.setCompanyFamilyPlanStartDate(finalString);
            } else {
                CFPCompanyDto.setCompanyFamilyPlanStartDate(StringUtils.EMPTY);
            }
            if (objects[6] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[6]));
                String finalString = format.format(date);
                CFPCompanyDto.setCompanyFamilyPlanEndDate(finalString);
            } else {
                CFPCompanyDto.setCompanyFamilyPlanEndDate(StringUtils.EMPTY);
            }

            if (objects[15] != null) {
                CFPCompanyDto.setCompanyFamilyPlanCategoryValue(Constants.SELECT_ONE.equals(String.valueOf(objects[15])) ? StringUtils.EMPTY : objects[15].toString());
            } else {
                CFPCompanyDto.setCompanyFamilyPlanCategoryValue(StringUtils.EMPTY);
            }
            CFPCompanyDto.setCompanyFamilyTradeClassValue(String.valueOf(objects[14]));
            CFPCompanyDto.setCompanyFamilyPlanDesignation(Constants.NULL.equals(String.valueOf(objects[9])) ? StringUtils.EMPTY : objects[9].toString());
            CFPCompanyDto.setCompanyFamilyPlanSystemId(Integer.valueOf(String.valueOf(objects[16])));
            returnList.add(CFPCompanyDto);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setIFPValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<ExistingComponentDTO>();
        ExistingComponentDTO IFPItemDTO;
        for (int i = 0; i < list.size(); i++) {
            Object objects[] = (Object[]) list.get(i);
            IFPItemDTO = new ExistingComponentDTO();

            IFPItemDTO.setItemNo(String.valueOf(objects[0]));
            IFPItemDTO.setItemName(String.valueOf(objects[1]));
            if (objects[2] != null) {
                IFPItemDTO.setTherapeuticClass(Constants.SELECT_ONE.equals(String.valueOf(objects[2])) ? StringUtils.EMPTY : objects[2].toString());
            } else {
                IFPItemDTO.setTherapeuticClass(StringUtils.EMPTY);
            }
            IFPItemDTO.setBrand(Constants.NULL.equals(String.valueOf(objects[3])) ? StringUtils.EMPTY : objects[3].toString());
            IFPItemDTO.setItemStatus(Constants.NULL.equals(String.valueOf(objects[6])) ? StringUtils.EMPTY : objects[6].toString());
            if (objects[4] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[4]));
                String finalString = format.format(date);
                IFPItemDTO.setItemStartDate(finalString);
                IFPItemDTO.setStartDate(finalString);
            } else {
                IFPItemDTO.setItemStartDate(StringUtils.EMPTY);
                IFPItemDTO.setStartDate(StringUtils.EMPTY);
            }
            if (objects[5] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[5]));
                String finalString = format.format(date);
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
        List<ExistingComponentDTO> returnList = new ArrayList<ExistingComponentDTO>();
        ExistingComponentDTO IFPItemDTO;
        for (int i = 0; i < list.size(); i++) {
            Object objects[] = (Object[]) list.get(i);
            IFPItemDTO = new ExistingComponentDTO();
            IFPItemDTO.setIfpDetailsSystemId(Integer.valueOf(String.valueOf(objects[0])));
            IFPItemDTO.setItemFamilyplanId(String.valueOf(objects[1]));
            IFPItemDTO.setItemFamilyplanNo(String.valueOf(objects[2]));
            IFPItemDTO.setItemFamilyplanName(String.valueOf(objects[3]));
            if (objects[7] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[7]));
                String finalString = format.format(date);
                IFPItemDTO.setIfpStartDate(finalString);
            } else {
                IFPItemDTO.setIfpStartDate(StringUtils.EMPTY);
            }
            if (objects[8] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[8]));
                String finalString = format.format(date);
                IFPItemDTO.setIfpEndDate(finalString);
            } else {
                IFPItemDTO.setIfpEndDate(StringUtils.EMPTY);
            }
            if (objects[18] != null) {
                IFPItemDTO.setItemFamilyplanType(Constants.SELECT_ONE.equals(String.valueOf(objects[18])) ? StringUtils.EMPTY : objects[18].toString());
            } else {
                IFPItemDTO.setItemFamilyplanType(StringUtils.EMPTY);
            }

            if (objects[5] != null) {
                IFPItemDTO.setIFPtype(Integer.valueOf(String.valueOf(objects[5])));
            }
            if (objects[19] != null) {
                IFPItemDTO.setDisplayIFPStatus(Constants.NULL.equals(String.valueOf(objects[19])) ? StringUtils.EMPTY : objects[19].toString());
            }
            if (objects[4] != null) {
                IFPItemDTO.setItemFamilyplanStatus(Integer.valueOf(String.valueOf(objects[4])));
            }

            if (objects[21] != null) {
                IFPItemDTO.setItemFamilyplanDesignation(Constants.SELECT_ONE.equals(String.valueOf(objects[21])) ? StringUtils.EMPTY : objects[21].toString());
            }
            IFPItemDTO.setItemFamilyplanDesignation(StringUtils.EMPTY);

            returnList.add(IFPItemDTO);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setPSValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<ExistingComponentDTO>();
        ExistingComponentDTO PSIFPDTO;
        for (int i = 0; i < list.size(); i++) {
            Object objects[] = (Object[]) list.get(i);
            PSIFPDTO = new ExistingComponentDTO();

            PSIFPDTO.setItemNo(String.valueOf(objects[0]));
            PSIFPDTO.setItemName(String.valueOf(objects[1]));
            PSIFPDTO.setTherapeuticClass(Constants.NULL.equals(String.valueOf(objects[2])) ? StringUtils.EMPTY : objects[2].toString());
            PSIFPDTO.setBrand(Constants.NULL.equals(String.valueOf(objects[3])) ? StringUtils.EMPTY : objects[3].toString());
            PSIFPDTO.setItemStatus(String.valueOf(objects[6]));

            if (objects[4] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[4]));
                String finalString = format.format(date);
                PSIFPDTO.setItemStartDate(finalString);
                PSIFPDTO.setStartDate(finalString);
            } else {
                PSIFPDTO.setItemStartDate(StringUtils.EMPTY);
                PSIFPDTO.setStartDate(StringUtils.EMPTY);
            }
            if (objects[5] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[5]));
                String finalString = format.format(date);
                PSIFPDTO.setItemEndDate(finalString);
                PSIFPDTO.setEndDate(finalString);
            } else {
                PSIFPDTO.setItemEndDate(StringUtils.EMPTY);
                PSIFPDTO.setEndDate(StringUtils.EMPTY);
            }

            PSIFPDTO.setPriceType(Constants.NULL.equals(String.valueOf(objects[7])) ? StringUtils.EMPTY : objects[7].toString());
            PSIFPDTO.setPriceProtectionStartDate(Constants.NULL.equals(String.valueOf(objects[8])) ? StringUtils.EMPTY : objects[8].toString());
            PSIFPDTO.setPricePlanNo(objects[9] == null ? StringUtils.EMPTY : String.valueOf(objects[9]));
            PSIFPDTO.setPricePlanName(objects[10] == null ? StringUtils.EMPTY : String.valueOf(objects[10]));
            PSIFPDTO.setPriceProtectionStatus(objects[11] == null ? StringUtils.EMPTY : String.valueOf(objects[11]));
            PSIFPDTO.setPriceProtectionEndDate(Constants.NULL.equals(String.valueOf(objects[12])) ? StringUtils.EMPTY : objects[12].toString());
            PSIFPDTO.setPriceProtectionPriceType(objects[13] == null ? StringUtils.EMPTY : String.valueOf(objects[13]));
            PSIFPDTO.setPriceToleranceInterval(objects[14] == null ? StringUtils.EMPTY : String.valueOf(objects[14]));
            PSIFPDTO.setPriceToleranceFrequency(objects[15] == null ? StringUtils.EMPTY : String.valueOf(objects[15]));
            PSIFPDTO.setMaxIncrementalChange(objects[17] == null ? StringUtils.EMPTY : String.valueOf(objects[17]));
            PSIFPDTO.setPriceTolerance(objects[18] == null ? StringUtils.EMPTY : String.valueOf(objects[18]));
            PSIFPDTO.setPriceToleranceType(objects[16] == null ? StringUtils.EMPTY : String.valueOf(objects[16]));
            PSIFPDTO.setEligibility(objects[20] == null ? StringUtils.EMPTY : String.valueOf(objects[20]));
            PSIFPDTO.setResetType(objects[21] == null ? StringUtils.EMPTY : String.valueOf(objects[21]));
            PSIFPDTO.setResetDate(objects[22] == null ? StringUtils.EMPTY : String.valueOf(objects[22]));
            PSIFPDTO.setResetIntervel(objects[23] == null ? StringUtils.EMPTY : String.valueOf(objects[23]));
            PSIFPDTO.setResetFrequency(objects[24] == null ? StringUtils.EMPTY : String.valueOf(objects[24]));
            PSIFPDTO.setAttachedDate(null);
            returnList.add(PSIFPDTO);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setPSmainValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<ExistingComponentDTO>();
        ExistingComponentDTO PSIFPDTO;
        for (int i = 0; i < list.size(); i++) {
            Object objects[] = (Object[]) list.get(i);
            PSIFPDTO = new ExistingComponentDTO();

            PSIFPDTO.setPriceScheduleSystemId(String.valueOf(objects[0]));
            PSIFPDTO.setPriceScheduleIdValue(String.valueOf(objects[1]));
            PSIFPDTO.setPriceScheduleNoValue(String.valueOf(objects[2]));
            PSIFPDTO.setPriceScheduleNameValue(String.valueOf(objects[3]));
            PSIFPDTO.setPriceScheduleStatusValue(Constants.NULL.equals(String.valueOf(objects[14])) ? StringUtils.EMPTY : objects[14].toString());
            if (objects[7] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[7]));
                String finalString = format.format(date);
                PSIFPDTO.setPriceScheduleStartDate(finalString);

            } else {
                PSIFPDTO.setPriceScheduleStartDate(StringUtils.EMPTY);
            }

            if (objects[8] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[8]));
                String finalString = format.format(date);
                PSIFPDTO.setPriceScheduleEndDate(finalString);
            } else {
                PSIFPDTO.setPriceScheduleEndDate(StringUtils.EMPTY);
            }

            PSIFPDTO.setItemFamilyplanName(String.valueOf(objects[19]));
            if (objects[13] != null) {
                PSIFPDTO.setPriceScheduleTypeValue(Constants.SELECT_ONE.equals(String.valueOf(objects[13])) ? StringUtils.EMPTY : objects[13].toString());
            } else {
                PSIFPDTO.setPriceScheduleTypeValue(StringUtils.EMPTY);
            }
            if (objects[15] != null) {
                PSIFPDTO.setPriceScheduleCategoryValue(Constants.SELECT_ONE.equals(String.valueOf(objects[15])) ? StringUtils.EMPTY : objects[15].toString());
            } else {
                PSIFPDTO.setPriceScheduleCategoryValue(StringUtils.EMPTY);
            }
            if (objects[16] != null) {
                PSIFPDTO.setPriceScheduleDesignationValue(Constants.SELECT_ONE.equals(String.valueOf(objects[16])) ? StringUtils.EMPTY : objects[16].toString());
            } else {
                PSIFPDTO.setPriceScheduleDesignationValue(StringUtils.EMPTY);
            }

            if (objects[5] != null) {
                PSIFPDTO.setPStatus(Integer.valueOf(String.valueOf(objects[5])));
            }
            if (objects[4] != null) {
                PSIFPDTO.setPtype(Integer.valueOf(String.valueOf(objects[4])));
            }

            returnList.add(PSIFPDTO);
        }
        return returnList;

    }

    public List<ExistingComponentDTO> setRSmainValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<ExistingComponentDTO>();
        ExistingComponentDTO rsIfpDto;
        for (int i = 0; i < list.size(); i++) {
            Object objects[] = (Object[]) list.get(i);
            rsIfpDto = new ExistingComponentDTO();
            rsIfpDto.setRebateScheduleSystemId(Integer.parseInt(String.valueOf(objects[0])));
            rsIfpDto.setRebateScheduleId(String.valueOf(objects[1]));
            rsIfpDto.setRebateScheduleNo(String.valueOf(objects[2]));
            rsIfpDto.setRebateScheduleName(String.valueOf(objects[3]));
            rsIfpDto.setStatusRebate(String.valueOf(objects[8]));
            if (objects[11] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[11]));
                String finalString = format.format(date);
                rsIfpDto.setItemRebateStartDate(finalString);
            } else {
                rsIfpDto.setItemRebateStartDate(StringUtils.EMPTY);
            }
            if (objects[12] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[12]));
                String finalString = format.format(date);
                rsIfpDto.setItemRebateEndDate(finalString);
            } else {
                rsIfpDto.setItemRebateEndDate(StringUtils.EMPTY);
            }

            if (objects[4] != null) {
                rsIfpDto.setRebateScheduleStatus(Integer.parseInt(String.valueOf(objects[4])));
            }
            rsIfpDto.setRebatetype((String.valueOf(objects[5])));
            rsIfpDto.setIfpName(String.valueOf(objects[17]));

            returnList.add(rsIfpDto);
        }
        return returnList;
    }

    public List<ExistingComponentDTO> setRSValues(List list) throws ParseException {
        List<ExistingComponentDTO> returnList = new ArrayList<ExistingComponentDTO>();
        ExistingComponentDTO rsIfpDto;
        for (int i = 0; i < list.size(); i++) {
            Object objects[] = (Object[]) list.get(i);
            rsIfpDto = new ExistingComponentDTO();

            rsIfpDto.setItemNo(String.valueOf(objects[0]));
            rsIfpDto.setItemName(String.valueOf(objects[1]));
            rsIfpDto.setTherapeuticClass(String.valueOf(objects[2]));
            rsIfpDto.setBrand(Constants.NULL.equals(String.valueOf(objects[3])) ? StringUtils.EMPTY : objects[3].toString());
            rsIfpDto.setItemStatus(String.valueOf(objects[6]));
            if (objects[4] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[4]));
                String finalString = format.format(date);
                rsIfpDto.setItemStartDate(finalString);
                rsIfpDto.setStartDate(finalString);

            } else {
                rsIfpDto.setItemStartDate(StringUtils.EMPTY);
                rsIfpDto.setStartDate(StringUtils.EMPTY);
            }

            if (objects[5] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(objects[5]));
                String finalString = format.format(date);
                rsIfpDto.setItemEndDate(finalString);
                rsIfpDto.setEndDate(finalString);
            } else {
                rsIfpDto.setItemEndDate(StringUtils.EMPTY);
                rsIfpDto.setEndDate(StringUtils.EMPTY);
            }
            rsIfpDto.setRebatePlanName(Constants.NULL.equals(String.valueOf(objects[8])) ? StringUtils.EMPTY : objects[8].toString());
            rsIfpDto.setRebatePlan(Constants.NULL.equals(String.valueOf(objects[8])) ? StringUtils.EMPTY : objects[8].toString());
            rsIfpDto.setFormulaId(Constants.NULL.equals(String.valueOf(objects[7])) ? StringUtils.EMPTY : objects[7].toString());
            rsIfpDto.setFormulaType(objects[9] == null ? StringUtils.EMPTY : String.valueOf(objects[9]));
            rsIfpDto.setFormulaName(objects[10] == null ? StringUtils.EMPTY : String.valueOf(objects[10]));
            rsIfpDto.setRebatePlanId(objects[11] == null ? StringUtils.EMPTY : String.valueOf(objects[11]));
            rsIfpDto.setRebateAmount(objects[12] == null ? StringUtils.EMPTY : String.valueOf(objects[12]));
            rsIfpDto.setBundleNo(objects[13] == null ? StringUtils.EMPTY : String.valueOf(objects[13]));
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
        List<Object> input = new ArrayList<Object>(5);
        input.add(cfpid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updateCFP(input);
    }

    public void SaveIFP(String ifpId, Integer cfpmodelid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(ifpId);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updateIFP(input);
    }

    public void SavePS(String psid, Integer cfpmodelid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(psid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(cfpmodelid);
        dao.updatePS(input);
    }

    public void SaveRS(String rsid, Integer RSmodalid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(rsid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(RSmodalid);
        dao.updateRS(input);

    }

    public void SaveCFPForCopyComponent(String cfpid, String cfpModelSId) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(cfpid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(cfpModelSId);
        dao.updateCFP(input);

    }

    public void SaveIFPForCopyComponent(String ifpId, String ifpModelSid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(ifpId);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(ifpModelSid);
        dao.updateIFP(input);
    }

    public void SavePSForCopyComponent(String psid, String psModelSid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(psid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(psModelSid);
        dao.updatePS(input);
    }

    public void SaveRSForCopyComponent(String rsid, String rsModelSid) {
        List<Object> input = new ArrayList<Object>(5);
        input.add(rsid);
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(1);
        input.add(FORMAT.format(new java.util.Date()));
        input.add(rsModelSid);
        dao.updateRS(input);

    }

    public Object getComponentInfoSelection(NewComponentDTO selection, String componentType, String componentInnerType, String searchValue, boolean isCount) throws ParseException {

        String queryName = Constants.ZEROSTRING;

        if (componentType.equals(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            queryName = "Copy Contract-Components Details PS Search";
            getDTOForRSandPS(selection, componentInnerType, searchValue);
            List input = new ArrayList<Object>();
            input.add(selection.getIfpId());
            input.add(selection.getIfpNo());
            input.add(selection.getIfpName());
            input.add(selection.getIfpStatus());
            input.add(selection.getIfpType());
            if (isCount) {
                queryName = queryName + " COUNT";
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
                queryName = queryName + " COUNT";
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
                queryName = queryName + " COUNT";
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
                    selection.setCompanyStatus(searchValue.replace("*", Constants.PERCENT));
                    break;
                case "Trade Class":
                    selection.setTradeClass(searchValue);
                    break;
                case "Company Type":
                    selection.setCompanyType(searchValue);
                    break;
                case "Company ID":
                    selection.setCompanyId(searchValue.replace("*", Constants.PERCENT));
                    break;
                case "Company Name":
                    selection.setCompanyName(searchValue.replace("*", Constants.PERCENT));
                    break;
                case "Company No":
                    selection.setCompanyNo(searchValue.replace("*", Constants.PERCENT));
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
                queryName = queryName + " COUNT";
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
                    selection.setItemId(searchValue.replace("*", Constants.PERCENT));
                    break;
                case "Item Name":
                    selection.setItemName(searchValue.replace("*", Constants.PERCENT));
                    break;
                case "Item No":
                    selection.setItemNo(searchValue.replace("*", Constants.PERCENT));
                    break;
                case "Brand":
                    selection.setBrand(searchValue);
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
                selection.setIfpId(searchValue.replace("*", Constants.PERCENT));
                break;
            case "IFP No":
                selection.setIfpNo(searchValue.replace("*", Constants.PERCENT));
                break;
            case "IFP Name":
                selection.setIfpName(searchValue.replace("*", Constants.PERCENT));
                break;
            case "IFP Status":
                selection.setIfpStatus(searchValue);
                break;
            case "IFP Type":
                selection.setIfpType(searchValue);
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
                itemDTO.setBrand(obje[2] == null ? StringUtils.EMPTY : String.valueOf(obje[2]));
                itemDTO.setItemStatus(obje[3] == null ? StringUtils.EMPTY : String.valueOf(obje[3]));
                if (obje[4] != null) {
                    Date date = (Date) FORMAT.parse(String.valueOf(obje[4]));
                    String finalString = df.format(date);
                    itemDTO.setPsStartDate(finalString);
                } else {
                    itemDTO.setPsStartDate(StringUtils.EMPTY);
                }
                if (obje[5] != null) {
                    Date date = (Date) FORMAT.parse(String.valueOf(obje[5]));
                    String finalString = df.format(date);
                    itemDTO.setPsEndDate(finalString);
                } else {
                    itemDTO.setPsEndDate(Constants.EMPTY);
                }
                itemDTO.setFormulaType(obje[6] == null ? StringUtils.EMPTY : String.valueOf(obje[6]));
                itemDTO.setFormulaId(obje[7] == null ? StringUtils.EMPTY : String.valueOf(obje[7]));
                itemDTO.setFormulaName(obje[8] == null ? StringUtils.EMPTY : String.valueOf(obje[8]));
                itemDTO.setRebatePlanId(obje[9] == null ? StringUtils.EMPTY : String.valueOf(obje[9]));
                itemDTO.setRebatePlanName(obje[10] == null ? StringUtils.EMPTY : String.valueOf(obje[10]));
                itemDTO.setRebateAmount(obje[11] == null ? StringUtils.EMPTY : String.valueOf(obje[11]));
                itemDTO.setBundleNo(obje[12] == null ? StringUtils.EMPTY : String.valueOf(obje[12]));
                itemDTO.setModelId(obje[13] == null ? StringUtils.EMPTY : String.valueOf(obje[13]));
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
                itemDTO.setBrand(obje[2] == null ? StringUtils.EMPTY : String.valueOf(obje[2]));
                itemDTO.setItemStatus(obje[3] == null ? StringUtils.EMPTY : String.valueOf(obje[3]));
                if (obje[4] != null) {
                    Date date = (Date) FORMAT.parse(String.valueOf(obje[4]));
                    String finalString = df.format(date);
                    itemDTO.setPsStartDate(finalString);
                } else {
                    itemDTO.setPsStartDate(StringUtils.EMPTY);
                }
                if (obje[5] != null) {
                    Date date = (Date) FORMAT.parse(String.valueOf(obje[5]));
                    String finalString = df.format(date);
                    itemDTO.setPsEndDate(finalString);
                } else {
                    itemDTO.setPsEndDate(Constants.EMPTY);
                }
                itemDTO.setItemType(obje[6] == null ? StringUtils.EMPTY : String.valueOf(obje[6]));
                itemDTO.setPricePlanNo(obje[7] == null ? StringUtils.EMPTY : String.valueOf(obje[7]));
                itemDTO.setPricePlanName(obje[8] == null ? StringUtils.EMPTY : String.valueOf(obje[8]));
                itemDTO.setPriceProtectionStatus(obje[9] == null ? StringUtils.EMPTY : String.valueOf(obje[9]));
                if (obje[10] != null) {
                    Date date = (Date) FORMAT.parse(String.valueOf(obje[10]));
                    String finalString = df.format(date);
                    itemDTO.setCompanyStartDate(finalString);
                } else {
                    itemDTO.setCompanyStartDate(StringUtils.EMPTY);
                }
                if (obje[11] != null) {
                    Date date = (Date) FORMAT.parse(String.valueOf(obje[11]));
                    String finalString = df.format(date);
                    itemDTO.setCompanyEndDate(finalString);
                } else {
                    itemDTO.setCompanyEndDate(Constants.EMPTY);
                }
                itemDTO.setPriceProtectionPriceType(obje[12] == null ? StringUtils.EMPTY : String.valueOf(obje[12]));
                itemDTO.setPriceToleranceInterval(obje[13] == null ? StringUtils.EMPTY : String.valueOf(obje[13]));
                itemDTO.setPriceToleranceFrequency(obje[14] == null ? StringUtils.EMPTY : String.valueOf(obje[14]));
                itemDTO.setMaxIncrementalChange(obje[15] == null ? StringUtils.EMPTY : String.valueOf(obje[15]));
                itemDTO.setPriceTolerance(obje[16] == null ? StringUtils.EMPTY : String.valueOf(obje[16]));
                itemDTO.setPriceToleranceType(obje[17] == null ? StringUtils.EMPTY : String.valueOf(obje[17]));
                itemDTO.setEligibility(obje[18] == null ? StringUtils.EMPTY : String.valueOf(obje[18]));
                itemDTO.setResetType(obje[19] == null ? StringUtils.EMPTY : String.valueOf(obje[19]));
                itemDTO.setResetDate(obje[20] == null ? StringUtils.EMPTY : String.valueOf(obje[20]));
                itemDTO.setResetIntervel(obje[21] == null ? StringUtils.EMPTY : String.valueOf(obje[21]));
                itemDTO.setResetFrequency(obje[22] == null ? StringUtils.EMPTY : String.valueOf(obje[22]));
                itemDTO.setAttachedDate(null);
                itemDTO.setModelId(String.valueOf(obje[25]));
                finalList.add(itemDTO);
            }
        }
        return finalList;
    }

    private Object getCustomizedCFPData(List list) throws ParseException {
        List<NewComponentDTO> companylist = new ArrayList<NewComponentDTO>();
        for (int i = 0; i < list.size(); i++) {
            NewComponentDTO Company = new NewComponentDTO();
            Object[] obje = (Object[]) list.get(i);
            Company.setModelId(String.valueOf(obje[0]));
            Company.setCompanyId(String.valueOf(obje[1]));
            Company.setCompanyNo(String.valueOf(obje[2]));
            Company.setCompanyName(String.valueOf(obje[3]));
            Company.setCompanyType(String.valueOf(obje[4]));
            if (obje[5] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(obje[5]));
                String finalString = df.format(date);
                Company.setPsStartDate(finalString);
            } else {
                Company.setPsStartDate(StringUtils.EMPTY);
            }
            if (obje[6] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(obje[6]));
                String finalString = df.format(date);
                Company.setPsEndDate(finalString);
            } else {
                Company.setPsEndDate(Constants.EMPTY);
            }
            Company.setCompanyStatus(String.valueOf(obje[7]));
            Company.setTradeClass(String.valueOf(obje[8]));
            Company.setAttachedDate((Date) obje[9]);
            companylist.add(Company);
        }
        return companylist;
    }

    private Object getCustomizedIFPData(List list) throws ParseException {
        List<NewComponentDTO> companylist = new ArrayList<NewComponentDTO>();
        for (int i = 0; i < list.size(); i++) {
            NewComponentDTO Company = new NewComponentDTO();
            Object[] obje = (Object[]) list.get(i);
            Company.setModelId(String.valueOf(obje[11]));
            Company.setItemId(String.valueOf(obje[0]));
            Company.setItemNo(String.valueOf(obje[1]));
            Company.setItemName(obje[2] == null ? StringUtils.EMPTY : String.valueOf(obje[2]));
            Company.setItemStatus(obje[3] == null ? StringUtils.EMPTY : String.valueOf(obje[3]));
            if (obje[4] != null) {
                if (String.valueOf(obje[4]).equalsIgnoreCase(Constants.SELECT_ONE)) {
                    Company.setItemType(Constants.EMPTY);
                } else {
                    Company.setItemType(String.valueOf(obje[4]));
                }
            }
            if (obje[8] != null) {
                if (String.valueOf(obje[8]).equalsIgnoreCase(Constants.SELECT_ONE)) {
                    Company.setTherapyClass(Constants.EMPTY);
                } else {
                    Company.setTherapyClass(String.valueOf(obje[8]));
                }
            }
            Company.setBrand(obje[5] == null ? StringUtils.EMPTY : String.valueOf(obje[5]));
            Company.setForm(obje[6] == null ? StringUtils.EMPTY : String.valueOf(obje[6]));
            Company.setStrength(obje[7] == null ? StringUtils.EMPTY : String.valueOf(obje[7]));

            if (obje[9] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(obje[9]));
                String finalString = df.format(date);
                Company.setPsStartDate(finalString);
            } else {
                Company.setPsStartDate(StringUtils.EMPTY);
            }
            if (obje[10] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(obje[10]));
                String finalString = df.format(date);
                Company.setPsEndDate(finalString);
            } else {
                Company.setPsEndDate(Constants.EMPTY);
            }
            Company.setAttachedDate((Date) obje[12]);
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
                    queryName = queryName + " COUNT";
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setCFPMainValues(ItemQueries.getItemData(input, queryName, null));
                }

            case "Item Family Plan":
                queryName = "Copy Contract- Existing Components Details IFP Search";
                if (isCount) {
                    queryName = queryName + " COUNT";
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setIFPmainValues(ItemQueries.getItemData(input, queryName, null));
                }

            case Constants.PRICE_SCHEDULE:
                queryName = "Copy Contract- Existing Components Details PS Search";
                if (isCount) {
                    queryName = queryName + " COUNT";
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setPSmainValues(ItemQueries.getItemData(input, queryName, null));
                }
            case Constants.REBATE_SCHEDULE:
                queryName = "Copy Contract- Existing Components Details RS Search";
                if (isCount) {
                    queryName = queryName + " COUNT";
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setRSmainValues(ItemQueries.getItemData(input, queryName, null));
                }
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
                    queryName += " COUNT";
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
                    queryName += " COUNT";
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
                    queryName += " COUNT";
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
                    queryName += " COUNT";
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return setRSValues(ItemQueries.getItemData(input, queryName, null));
                }
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
                    queryName = queryName + " COUNT";
                    return CommonUtils.getDataCount(queryName, input);
                } else {
                    input.add(start);
                    input.add(offset);
                    return getCustomizedCFP(ItemQueries.getItemData(input, queryName, null));
                }

            case 2:
                queryName = "Copy Contract- Existing Leve Data IFP Search";
                break;
            case 3:
                queryName = "Copy Contract- Existing Leve Data PS Search";
                break;
            case 4:
                queryName = "Copy Contract- Existing Leve Data RS Search";
                break;
        }
        if (isCount) {
            queryName = queryName + " COUNT";
            return CommonUtils.getDataCount(queryName, input);
        } else {
            input.add(start);
            input.add(offset);
            return getCustomizedPSandRS(ItemQueries.getItemData(input, queryName, null));
        }

    }

    private List getCustomizedCFP(List itemData) throws ParseException {
        List<CopyComponentDTO> companyList = new ArrayList<CopyComponentDTO>();
        if (itemData != null && itemData.size() > 0) {
            for (int i = 0; i < itemData.size(); i++) {
                CopyComponentDTO companyDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) itemData.get(i);
                companyDTO.setCompanyNo(String.valueOf(obje[0]));
                companyDTO.setCompanyName(String.valueOf(obje[1]));
                companyDTO.setCompanyStatus(String.valueOf(obje[2]));
                if (obje[3] != null) {
                    String date = df.format((Date) obje[3]);
                    companyDTO.setCompanyStartDate(date);
                } else {
                    companyDTO.setCompanyStartDate(Constants.EMPTY);
                }
                if (obje[4] != null) {
                    String date = df.format((Date) obje[4]);
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
        List<CopyComponentDTO> itemList = new ArrayList<CopyComponentDTO>();
        for (int i = 0; i < itemData.size(); i++) {
            CopyComponentDTO itemDTO = new CopyComponentDTO();
            Object[] obje = (Object[]) itemData.get(i);
            itemDTO.setItemNo(String.valueOf(obje[0]));
            itemDTO.setItemName(String.valueOf(obje[1]));
            itemDTO.setTherapyClass(Constants.NULL.equals(String.valueOf(obje[2])) ? StringUtils.EMPTY : obje[2].toString());
            itemDTO.setBrand(Constants.NULL.equals(String.valueOf(obje[3])) ? StringUtils.EMPTY : obje[3].toString());
            itemDTO.setIfpStatus(String.valueOf(obje[4]));
            if (obje[5] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(obje[5]));
                String finalString = format.format(date);
                itemDTO.setIfpStartDate(finalString);
            } else {
                itemDTO.setIfpStartDate(StringUtils.EMPTY);
            }
            if (obje[6] != null) {
                Date date = (Date) FORMAT.parse(String.valueOf(obje[6]));
                String finalString = format.format(date);
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

    public List<CopyComponentDTO> getComponentinfoResults(List<ContractSelectionDTO> selectedlist, int start, int end) throws ParseException {

        return SelectedContracts(selectedlist, start, end);

    }

    public List<CopyComponentDTO> SelectedContracts(List<ContractSelectionDTO> selectedList, int start, int end) throws ParseException {
        List<CopyComponentDTO> copyList = new ArrayList<CopyComponentDTO>();
        if (selectedList != null && selectedList.size() > 0) {

            for (int i = 0; i < selectedList.size(); i++) {
                LOGGER.info("inside for");
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
                        String s = df.format(d);
                        dto.setContractStartDate(s);
                    } catch (ParseException ex) {
                        LOGGER.error(ex.getMessage());
                    }
                } else {
                    dto.setContractStartDate(Constants.EMPTY);
                }
                if (!contractDto.getEndDate().equals(Constants.NULL)) {
                    Date date = (Date) FORMAT.parse(String.valueOf(contractDto.getEndDate()));
                    String finalString = df.format(date);
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

    public List<CopyComponentDTO> getComponentResults(String componentType, String Query, int start, int offset) throws ParseException {
        String qry = Query;
        qry += "ORDER BY 1 OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY";
        List<CopyComponentDTO> result = new ArrayList<CopyComponentDTO>();
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

    public List<CopyComponentDTO> setCompanyValues(List componentList) throws ParseException {
        List<CopyComponentDTO> companyList = new ArrayList<CopyComponentDTO>();
        if (componentList != null && componentList.size() > 0) {
            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO companyDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                companyDTO.setCompanyNo(String.valueOf(obje[0]));
                companyDTO.setTpNo(String.valueOf(obje[0]));
                companyDTO.setCompanyName(String.valueOf(obje[1]));
                companyDTO.setCompanyStatus(String.valueOf(obje[2]));
                if (obje[3] != null) {
                    String date = df.format((Date) obje[3]);
                    companyDTO.setCompanyStartDate(date);
                } else {
                    companyDTO.setCompanyStartDate(Constants.EMPTY);
                }
                if (obje[4] != null) {
                    String date = df.format((Date) obje[4]);
                    companyDTO.setCompanyEndDate(date);
                } else {
                    companyDTO.setCompanyEndDate(Constants.EMPTY);
                }
                companyDTO.setTradeClass(obje[5] != null ? String.valueOf(obje[5]) : StringUtils.EMPTY);
                companyList.add(companyDTO);
            }
        }
        return companyList;
    }

    public List<CopyComponentDTO> setIFPAttatchedItems(List componentList) {
        List<CopyComponentDTO> itemList = new ArrayList<CopyComponentDTO>();

        if (componentList != null && componentList.size() > 0) {
            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO itemDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                itemDTO.setItemNo(String.valueOf(obje[0]));
                itemDTO.setItemName(String.valueOf(obje[1]));
                itemDTO.setTherapyClass(String.valueOf(obje[2]));
                itemDTO.setBrand((obje[3] != null) ? String.valueOf(obje[3]) : Constants.EMPTY);
                itemDTO.setIfpStatus(String.valueOf(obje[4]));
                if (obje[5] != null) {
                    String date = df.format((Date) obje[5]);
                    itemDTO.setIfpStartDate(date);
                } else {
                    itemDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[6] != null) {
                    String date = df.format((Date) obje[6]);
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
        List<CopyComponentDTO> priceList = new ArrayList<CopyComponentDTO>();

        if (componentList != null && componentList.size() > 0) {

            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO itemDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                itemDTO.setItemNo(String.valueOf(obje[0]));
                itemDTO.setItemName(String.valueOf(obje[1]));
                itemDTO.setTherapyClass(String.valueOf(obje[2]));
                itemDTO.setBrand((obje[3] != null) ? String.valueOf(obje[3]) : Constants.EMPTY);
                itemDTO.setIfpStatus(String.valueOf(obje[4]));
                if (obje[5] != null) {
                    String date = df.format((Date) obje[5]);
                    itemDTO.setIfpStartDate(date);
                } else {
                    itemDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[6] != null) {
                    String date = df.format((Date) obje[6]);
                    itemDTO.setIfpEndDate(date);
                } else {
                    itemDTO.setIfpEndDate(Constants.EMPTY);
                }
                if ((obje[8] != null) && !obje[8].equals(Constants.SELECT_ONE) && !obje[8].equals(Constants.NULL)) {
                    itemDTO.setPriceType(String.valueOf(obje[8]));
                } else {
                    itemDTO.setPriceType(Constants.EMPTY);
                }
                if (obje[7] != null) {
                    String date = df.format((Date) obje[7]);
                    itemDTO.setPpStartDate(date);
                } else {
                    itemDTO.setPpStartDate(Constants.EMPTY);
                }
                itemDTO.setPricePlanNo(obje[9] == null ? StringUtils.EMPTY : String.valueOf(obje[9]));
                itemDTO.setPricePlanName(obje[10] == null ? StringUtils.EMPTY : String.valueOf(obje[10]));
                itemDTO.setPriceProtectionStatus(obje[11] == null ? StringUtils.EMPTY : String.valueOf(obje[11]));
                itemDTO.setPriceProtectionEndDate(Constants.NULL.equals(String.valueOf(obje[12])) ? StringUtils.EMPTY : obje[12].toString());
                itemDTO.setPriceProtectionPriceType(obje[13] == null ? StringUtils.EMPTY : String.valueOf(obje[13]));
                itemDTO.setPriceToleranceInterval(obje[14] == null ? StringUtils.EMPTY : String.valueOf(obje[14]));
                itemDTO.setPriceToleranceFrequency(obje[15] == null ? StringUtils.EMPTY : String.valueOf(obje[15]));
                itemDTO.setMaxIncrementalChange(obje[17] == null ? StringUtils.EMPTY : String.valueOf(obje[17]));
                itemDTO.setPriceTolerance(obje[18] == null ? StringUtils.EMPTY : String.valueOf(obje[18]));
                itemDTO.setPriceToleranceType(obje[16] == null ? StringUtils.EMPTY : String.valueOf(obje[16]));
                itemDTO.setEligibility(obje[20] == null ? StringUtils.EMPTY : String.valueOf(obje[20]));
                itemDTO.setResetType(obje[21] == null ? StringUtils.EMPTY : String.valueOf(obje[21]));
                itemDTO.setResetDate(obje[22] == null ? StringUtils.EMPTY : String.valueOf(obje[22]));
                itemDTO.setResetIntervel(obje[23] == null ? StringUtils.EMPTY : String.valueOf(obje[23]));
                itemDTO.setResetFrequency(obje[24] == null ? StringUtils.EMPTY : String.valueOf(obje[24]));
                itemDTO.setAttachedDate(null);
                priceList.add(itemDTO);
            }
        }
        return priceList;
    }

    public List<CopyComponentDTO> setRSAttatchedItems(List componentList) {
        List<CopyComponentDTO> rebateList = new ArrayList<CopyComponentDTO>();
        if (componentList != null && componentList.size() > 0) {

            for (int i = 0; i < componentList.size(); i++) {
                CopyComponentDTO rebateDTO = new CopyComponentDTO();
                Object[] obje = (Object[]) componentList.get(i);
                rebateDTO.setItemNo(String.valueOf(obje[0]));
                rebateDTO.setItemName(String.valueOf(obje[1]));
                rebateDTO.setTherapyClass(String.valueOf(obje[2]));
                rebateDTO.setBrand((obje[3] != null) ? String.valueOf(obje[3]) : Constants.EMPTY);
                rebateDTO.setIfpStatus(String.valueOf(obje[4]));
                if (obje[5] != null) {
                    String date = df.format((Date) obje[5]);
                    rebateDTO.setIfpStartDate(date);
                } else {
                    rebateDTO.setIfpStartDate(Constants.EMPTY);
                }
                if (obje[6] != null) {
                    String date = df.format((Date) obje[6]);
                    rebateDTO.setIfpEndDate(date);
                } else {
                    rebateDTO.setIfpEndDate(Constants.EMPTY);
                }
                rebateDTO.setFormulaId((obje[7] != null) ? String.valueOf(obje[7]) : Constants.EMPTY);
                rebateDTO.setRebatePlan((obje[8] != null) ? String.valueOf(obje[8]) : Constants.EMPTY);
                rebateDTO.setRebatePlanName((obje[8] != null) ? String.valueOf(obje[8]) : Constants.EMPTY);
                rebateDTO.setFormulaType(obje[9] == null ? StringUtils.EMPTY : String.valueOf(obje[9]));
                rebateDTO.setFormulaName(obje[10] == null ? StringUtils.EMPTY : String.valueOf(obje[10]));
                rebateDTO.setRebatePlanId(obje[11] == null ? StringUtils.EMPTY : String.valueOf(obje[11]));
                rebateDTO.setRebateAmount(obje[12] == null ? StringUtils.EMPTY : String.valueOf(obje[12]));
                rebateDTO.setBundleNo(obje[13] == null ? StringUtils.EMPTY : String.valueOf(obje[13]));
                rebateDTO.setAttachedDate(null);
                rebateList.add(rebateDTO);
            }

        }
        return rebateList;
    }

    public void insertIntoRsDetails(String temptableSId, String userId, int rsModelSid) {
        String query = "INSERT INTO RS_DETAILS (RS_MODEL_SID,IFP_MODEL_SID,ITEM_MASTER_SID,ITEM_REBATE_START_DATE,INBOUND_STATUS,RECORD_LOCK_STATUS,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE)\n"
                + "select " + rsModelSid + ",IFP_MODEL_SID,ITEM_MASTER_SID,PS_DTLS_CONT_PRICE_STARTDATE,'A','0'," + userId + ",getDate()," + userId + ",getDate() from IMTD_PS_DETAILS where PS_MODEL_SID='" + temptableSId + "'";
        LOGGER.info("Rs Detailsssss Queryy::::" + query);
        int count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
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

   

}
