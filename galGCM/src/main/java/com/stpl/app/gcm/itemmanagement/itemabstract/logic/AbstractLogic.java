/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic;

import com.stpl.app.gcm.common.HelperListUtil;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.AddItemTableDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.SummaryDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import static com.stpl.app.gcm.itemmanagement.index.form.ItemManagementLookup.LOGGER;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractFilter;
import com.stpl.app.gcm.itemmanagement.itemabstract.lazyload.DdlbCriteria;
import com.stpl.app.gcm.itemmanagement.itemabstract.lazyload.LoadDdlbDAO;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.ANNUALLY;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.QUARTERS;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.gcm.util.Constants.FrequencyConstants.YEARS;
import com.stpl.app.gcm.util.Constants.IndicatorConstants;
import static com.stpl.app.gcm.util.Constants.QUARTERLY;
import static com.stpl.app.gcm.util.Constants.SPACE;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 *
 * @author mohamed.hameed
 */
public class AbstractLogic {

    static HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    static HelperDTO ddlbShowAllValue = new HelperDTO(0, Constants.SHOW_ALL);
    public static final Map<String, List> ddlbMap = new HashMap<String, List>();
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    private static final AbstractLogic logic = new AbstractLogic();
    /**
     * The helper list util.
     */
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    private AbstractLogic() {

    }

    public static final AbstractLogic getInstance() {
        return logic;
    }

    public void LazyLoadDdlb(final ComboBox comboBox, String countFlag, String findFlag, Boolean isFilter) {
        final List inputList = new ArrayList();
        inputList.add(countFlag);
        inputList.add(findFlag);
        LazyContainer containerData = new LazyContainer(HelperDTO.class, new LoadDdlbDAO(inputList, isFilter), new DdlbCriteria());
        comboBox.setPageLength(7);
        comboBox.setContainerDataSource(containerData);
        if (isFilter) {
            comboBox.setNullSelectionItemId(ddlbShowAllValue);
        } else {
            comboBox.setNullSelectionItemId(ddlbDefaultValue);
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        containerData.setMinFilterLength(0);
    }

    public int getDdlbCount(String QueryName, final List<String> input) {
        List<Object[]> list = ItemQueries.getItemData(input, QueryName, null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    public List<HelperDTO> getDdlbList(String QueryName, final List<String> input, final Boolean isFilter) {
        List<Object[]> list = ItemQueries.getItemData(input, QueryName, null);
        List<HelperDTO> resultList = new ArrayList<HelperDTO>();
        if (Integer.valueOf(String.valueOf(input.get(1))) == 0) {
            if (isFilter) {
                HelperDTO defaultValue = new HelperDTO(0, Constants.SHOW_ALL);
                resultList.add(defaultValue);
            } else {
                HelperDTO defaultValue = new HelperDTO(0, IndicatorConstants.SELECT_ONE.getConstant());
                resultList.add(defaultValue);
            }
        }
        for (Object[] str : list) {
            if (!str[1].equals(String.valueOf(IndicatorConstants.SELECT_ONE.getConstant()))) {
                HelperDTO dto = new HelperDTO();
                dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                dto.setDescription(str[1] == null ? Constants.ZEROSTRING: String.valueOf(str[1]));
                resultList.add(dto);
            }
        }
        return resultList;
    }

    public int getContractCount(SelectionDTO selection, List<ItemIndexDto> selectedItemList, AddItemTableDTO binderDto) {
        List input = getInput(selection, binderDto);
        List<Object[]> list = ItemQueries.getItemData(input, selection.getCountQueryName(), null);
        Object obj = list.get(0);
        int count = obj == null ? 0 : (Integer) obj;
        return count;
    }

    public List getContractResults(SelectionDTO selection, List<ItemIndexDto> selectedItemList, int start, int offset, AddItemTableDTO binderDto) {

        List input = getInput(selection, binderDto);
        input.add(start);
        input.add(offset);
        List<Object[]> list = ItemQueries.getItemData(input, selection.getDataQueryName(), null);
        return setContractDetailsData(list, selection, input.get(1).toString());
    }

    private List<AbstractContractSearchDTO> setContractDetailsData(List<Object[]> list, SelectionDTO selection, String screenName) {
        List<AbstractContractSearchDTO> resultList = new ArrayList<AbstractContractSearchDTO>();

        for (Object[] str : list) {
            AbstractContractSearchDTO dto = new AbstractContractSearchDTO();
            dto.setProjectionId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            if (!String.valueOf(str[1]).equals("Approved")) {
                dto.setWorkFlowStatus(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            }
            dto.setContractHolder(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setContractNo(str[3] == null ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setContractName(str[4] == null ? StringUtils.EMPTY : String.valueOf(str[4]));
            dto.setMarketType(str[5] == null ? StringUtils.EMPTY : String.valueOf(str[5]));
            dto.setStartDate(str[6] == null ? null : (Date) str[6]);
            dto.setEndDate(str[7] == null ? null : (Date) str[7]);
            HelperDTO status = new HelperDTO();
            status.setId(str[8] == null ? 0 : Integer.valueOf(String.valueOf(str[8])));
            status.setDescription(str[43] == null ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : String.valueOf(String.valueOf(str[43])));
            dto.setStatus(status);
            dto.setItemStartDate(str[9] == null ? null : (Date) str[9]);
            dto.setItemEndDate(str[10] == null ? null : (Date) str[10]);

            dto.setCpStartDate(str[11] == null ? null : (Date) str[11]);
            dto.setCpEndDate(str[12] == null ? null : (Date) str[12]);
            dto.setContractPrice(str[13] == null ? StringUtils.EMPTY : String.valueOf(str[13]));
            dto.setPrice(str[14] == null ? StringUtils.EMPTY : String.valueOf(str[14]));
            dto.setPriceProtectionStartDate(str[15] == null ? null : (Date) str[15]);
            dto.setPriceProtectionEndDate(str[16] == null ? null : (Date) str[16]);
            HelperDTO priceToleranceType = new HelperDTO();
            priceToleranceType.setId(str[17] == null ? 0 : Integer.valueOf(String.valueOf(str[17])));
            priceToleranceType.setDescription(str[44] == null ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : String.valueOf(String.valueOf(str[44])));
            dto.setPriceToleranceType(priceToleranceType);
            dto.setPriceTolerance(str[18] == null ? StringUtils.EMPTY : String.valueOf(str[18]));
            HelperDTO priceTolerance = new HelperDTO();
            HelperDTO priceToleranceFrequency = new HelperDTO();
            priceToleranceFrequency.setId(str[19] == null ? 0 : Integer.valueOf(String.valueOf(str[19])));
            priceToleranceFrequency.setDescription(str[45] == null ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : String.valueOf(String.valueOf(str[45])));
            dto.setPriceToleranceFrequency(priceToleranceFrequency);
            priceTolerance.setId(str[20] == null ? 0 : Integer.valueOf(String.valueOf(str[20])));
            priceTolerance.setDescription(str[46] == null ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : String.valueOf(String.valueOf(str[46])));
            dto.setPriceToleranceInterval(priceTolerance);
            dto.setBasePrice(str[21] == null ? StringUtils.EMPTY : String.valueOf(str[21]));
            dto.setRSStartDate(str[22] == null ? null : (Date) str[22]);
            dto.setRSEndDate(str[23] == null ? null : (Date) str[23]);
            dto.setFormulaId(str[24] == null ? StringUtils.EMPTY : String.valueOf(str[24]));
            dto.setFormulaMethodId(str[25] == null ? StringUtils.EMPTY : String.valueOf(str[25]));
            dto.setRebateAmount(str[26] == null ? StringUtils.EMPTY : String.valueOf(str[26]));
            dto.setCfpNO(str[27] == null ? StringUtils.EMPTY : String.valueOf(str[27]));
            dto.setCfpName(str[28] == null ? StringUtils.EMPTY : String.valueOf(str[28]));
            dto.setIfpNo(str[29] == null ? StringUtils.EMPTY : String.valueOf(str[29]));
            dto.setIfpName(str[30] == null ? StringUtils.EMPTY : String.valueOf(str[30]));
            dto.setPsNo(str[31] == null ? StringUtils.EMPTY : String.valueOf(str[31]));
            dto.setPsName(str[32] == null ? StringUtils.EMPTY : String.valueOf(str[32]));
            dto.setRsNo(str[33] == null ? StringUtils.EMPTY : String.valueOf(str[33]));
            dto.setRsName(str[34] == null ? StringUtils.EMPTY : String.valueOf(str[34]));
            dto.setRarCategory(str[35] == null ? StringUtils.EMPTY : String.valueOf(str[35]));
            dto.setCheckRecord(str[36] == null ? Boolean.FALSE : (Boolean) str[36]);
            dto.setContractMasterSid(str[37] == null ? 0 : Integer.valueOf(str[37].toString()));
            dto.setCfpContractSid(str[38] == null ? 0 : Integer.valueOf(str[38].toString()));
            dto.setIfpConteractSid(str[39] == null ? 0 : Integer.valueOf(str[39].toString()));
            dto.setPsContractSid(str[40] == null ? 0 : Integer.valueOf(str[40].toString()));
            dto.setRsContractSid(str[41] == null ? 0 : Integer.valueOf(str[41].toString()));
            dto.setItemMasterSid(str[42] == null ? 0 : Integer.valueOf(str[42].toString()));
            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                dto.setTransferScreenName(screenName);
                selection.setTransferScreenName(screenName);
            }

            resultList.add(dto);
        }
        return resultList;
    }

    public static List getResultsInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        return input;
    }

    public static List getCurrentInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_COONTRACT);
        return input;
    }

    public static List getTransferInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.TRANSFER_CONTRACT);
        return input;
    }

    public static List getResultsSummary(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.SUMMARY);
        return input;
    }

    public static List getCurrentSummary(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.CURRENT_SUMMARY);
        return input;
    }

    public static List getTransferSummary(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(ConstantsUtil.TRANSFER_SUMMARY);
        return input;
    }

    public static Integer getContractSid(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        List<Object[]> list = ItemQueries.getItemData(input, "getContractMasterSid", null);
        return getCount(list);
    }

    public static Object getItemIds(List<ItemIndexDto> itemList) {
        List itemIdList = new ArrayList();
        for (ItemIndexDto dto : itemList) {
            itemIdList.add(dto.getSystemId());
        }
        return CommonUtils.getListToString(itemIdList);
    }

    public static Object getItemIdsAsString(List<ItemIndexDto> itemList) {
        StringBuilder result = new StringBuilder();
        for (ItemIndexDto dto : itemList) {
            result.append(dto.getSystemId() + ",");
        }
          result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public int getComponentInfoCount(final ComponentInfoDTO binderDto, final SelectionDTO selection) {
        List inputList = getComponentInfoSelection(binderDto, selection);
        List<Object[]> list = ItemQueries.getItemData(inputList, selection.getComponentCount(), null);
        return getCount(list);
    }

    public List<ComponentInfoDTO> getComponentInfoResults(final ComponentInfoDTO binderDto, final SelectionDTO selection) {
        List<Object[]> list = ItemQueries.getItemData(getComponentInfoSelection(binderDto, selection), selection.getComponentLoad(), null);
        List<ComponentInfoDTO> finalResult = new ArrayList<ComponentInfoDTO>();
        if (selection.getComponent().equalsIgnoreCase(Constants.CFP)) {
            finalResult = getCustomizedCFPComponentInfo(list);
        } else if (selection.getComponent().equals(Constants.IFP)) {
            finalResult = getCustomizedIFPComponentInfo(list);
        } else if (selection.getComponent().equals(Constants.PS)) {
            finalResult = getCustomizedPSComponentInfo(list);
        } else if (selection.getComponent().equals(Constants.RS)) {
            finalResult = getCustomizedRSComponentInfo(list);
        }
        return finalResult;
    }

    public List<ComponentInfoDTO> getCustomizedCFPComponentInfo(final List<Object[]> list) {
        List<ComponentInfoDTO> finalResult = new ArrayList<ComponentInfoDTO>();
        for (Object[] str : list) {
            ComponentInfoDTO dto = new ComponentInfoDTO();
            dto.setItemNo(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setItemName(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setItemStatus(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setStartDate(str[3] == null ? null : (Date) (str[3]));
            dto.setEndDate(str[4] == null ? null : (Date) (str[4]));
            dto.setStatus(str[5] == null ? StringUtils.EMPTY : String.valueOf(str[5]));
            dto.setTradeClass(str[6] == null || Constants.SELECT_ONE.equalsIgnoreCase(String.valueOf(str[6])) ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setAttachedDate(str[7] == null ? null : (Date) (str[7]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    public List<ComponentInfoDTO> getCustomizedIFPComponentInfo(final List<Object[]> list) {
        List<ComponentInfoDTO> finalResult = new ArrayList<ComponentInfoDTO>();
        for (Object[] str : list) {
            ComponentInfoDTO dto = new ComponentInfoDTO();
            dto.setItemNo(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setItemName(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setBrand(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setStatus(str[3] == null ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setStartDate(str[4] == null ? null : (Date) (str[4]));
            dto.setEndDate(str[5] == null ? null : (Date) (str[5]));
            dto.setAttachedDate(str[6] == null ? null : (Date) (str[6]));

            finalResult.add(dto);
        }
        return finalResult;
    }

    public List<ComponentInfoDTO> getCustomizedPSComponentInfo(final List<Object[]> list) {
        List<ComponentInfoDTO> finalResult = new ArrayList<ComponentInfoDTO>();
        for (Object[] str : list) {
            ComponentInfoDTO dto = new ComponentInfoDTO();
            dto.setItemNo(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setItemName(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setBrand(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setStatus(str[3] == null ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setStartDate(str[4] == null ? null : (Date) (str[4]));
            dto.setEndDate(str[5] == null ? null : (Date) (str[5]));
            dto.setPriceType(str[6] == null ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setPricePlanNo(str[7] == null ? StringUtils.EMPTY : String.valueOf(str[7]));
            dto.setPricePlanName(str[8] == null ? StringUtils.EMPTY : String.valueOf(str[8]));
            dto.setPriceProtectionStatus(str[9] == null ? StringUtils.EMPTY : String.valueOf(str[9]));
            dto.setPriceProtectionStartDate(str[10] == null ? null : (Date) str[10]);
            dto.setPriceProtectionEndDate(str[11] == null ? null : (Date) str[11]);
            dto.setPriceProtectionPriceType(str[12] == null ? StringUtils.EMPTY : String.valueOf(str[12]));
            dto.setPriceToleranceInterval(str[13] == null || str[13].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[13]));
            dto.setPriceToleranceFrequency(str[14] == null || str[14].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[14]));
            dto.setPriceToleranceType(str[15] == null || str[15].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[15]));
            dto.setMaxIncrementalChange(str[16] == null ? StringUtils.EMPTY : String.valueOf(str[16]));
            dto.setPriceTolerance(str[17] == null ? StringUtils.EMPTY : String.valueOf(str[17]));
            dto.setResetEligible(str[18] == null ? StringUtils.EMPTY : String.valueOf(str[18]));
            dto.setResetType(str[19] == null ? StringUtils.EMPTY : String.valueOf(str[19]));
            dto.setResetDate(str[20] == null ? null : (Date) str[20]);
            dto.setResetInterval(str[21] == null ? StringUtils.EMPTY : String.valueOf(str[21]));
            dto.setResetFrequency(str[22] == null ? StringUtils.EMPTY : String.valueOf(str[22]));
            dto.setAttachedDate(str[23] == null ? null : (Date) (str[23]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    public List<ComponentInfoDTO> getCustomizedRSComponentInfo(final List<Object[]> list) {
        List<ComponentInfoDTO> finalResult = new ArrayList<ComponentInfoDTO>();
        for (Object[] str : list) {
            ComponentInfoDTO dto = new ComponentInfoDTO();
            dto.setItemNo(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setItemName(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setBrand(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setStatus(str[3] == null ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setStartDate(str[4] == null ? null : (Date) (str[4]));
            dto.setEndDate(str[5] == null ? null : (Date) (str[5]));
            dto.setFormulaType(str[6] == null ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setFormulaId(str[7] == null ? StringUtils.EMPTY : String.valueOf(str[7]));
            dto.setFormulaName(str[8] == null ? StringUtils.EMPTY : String.valueOf(str[8]));
            dto.setRebatePlanId(str[9] == null ? StringUtils.EMPTY : String.valueOf(str[9]));
            dto.setRebatePlanName(str[10] == null ? StringUtils.EMPTY : String.valueOf(str[10]));
            dto.setRebateAmount(str[11] == null ? StringUtils.EMPTY : String.valueOf(str[11]));
            dto.setBundleNo(str[12] == null ? StringUtils.EMPTY : String.valueOf(str[12]));
            dto.setAttachedDate(str[13] == null ? null : (Date) (str[13]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    private List getComponentInfoSelection(ComponentInfoDTO binderDto, SelectionDTO selection) {
        List<Object> input = new ArrayList();
        input.add(binderDto.getSystemId());
        if (selection.getComponent().equals(Constants.RS)) {
            if (binderDto.getRsType_DTO() != null) {
                input.add(binderDto.getRsType_DTO().getId());
            } else {
                input.add("%");
            }
            if (binderDto.getRsProgramType_DTO() != null) {
                input.add(binderDto.getRsProgramType_DTO().getId());
            } else {
                input.add("%");
            }
            if (binderDto.getRsCategory_DTO() != null) {
                input.add(binderDto.getRsCategory_DTO().getId());
            } else {
                input.add("%");
            }
            if (binderDto.getPaymentFrequency_DTO() != null) {
                input.add(binderDto.getPaymentFrequency_DTO().getId());
            } else {
                input.add("%");
            }
            if (binderDto.getRebatePlanLevel_DTO() != null) {
                input.add(binderDto.getRebatePlanLevel_DTO().getId());
            } else {
                input.add("%");
            }
        }
        StringBuilder sql = AbstractFilter.getInstance().getComponentfilterQueryGenerator(selection.getComponent(), selection.getFilters());
        if (sql != null) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;

    }

    public static int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    public static int getDataCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object[] obj = list.get(0);
            int count = obj[0] == null ? 0 : 1;
            return count;
        }
        return 0;
    }

    public ComponentInfoDTO getComponentTextFields(final String componentFlag,Integer systemid,boolean isItemAddTab) {
        List input = new ArrayList();
        ComponentInfoDTO dto = new ComponentInfoDTO();
        if (isItemAddTab) {
            input.add(systemid);
        } else {
            input.add(1);
        }
        List<Object[]> list = ItemQueries.getItemData(input, componentFlag, null);
        for (Object[] str : list) {
            dto.setComponenId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setComponenNumber(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setComponenName(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setComponentStatus(str[3] == null ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setComponentType(str[4] == null ? StringUtils.EMPTY : String.valueOf(str[4]));
            dto.setComponentStartDate(str[5] == null ? null : (Date) str[5]);
            dto.setComponentEndDate(str[6] == null ? null : (Date) str[6]);
            if (componentFlag.equals("RS text")) {
                dto.setRsType_Value(str[4] == null ? StringUtils.EMPTY : String.valueOf(str[4]));
                dto.setRebateFrequency_Value(str[7] == null ? StringUtils.EMPTY : String.valueOf(str[7]));
                dto.setRsProgramType_Value(str[8] == null ? StringUtils.EMPTY : String.valueOf(str[8]));
                dto.setRsCategory_Value(str[9] == null ? StringUtils.EMPTY : String.valueOf(str[9]));
                dto.setPaymentFrequency_Value(str[10] == null ? StringUtils.EMPTY : String.valueOf(str[10]));
                dto.setRebatePlanLevel_Value(str[11] == null ? StringUtils.EMPTY : String.valueOf(str[11]));
            }
        }
        return dto;
    }

    public int getFormulaIdCount(FormulaDTO binderDto, SelectionDTO selection) {
        List<Object[]> list = ItemQueries.getItemData(getFormulaIdInput(binderDto, selection), selection.getCountQueryName(), null);
        Object obj = list.get(0);
        int count = obj == null ? 0 : (Integer) obj;
        return count;
    }

    public List<FormulaDTO> getFormulaIdRecords(FormulaDTO binderDto, SelectionDTO selection) {
        List<FormulaDTO> finalResult = new ArrayList<FormulaDTO>();
        List<Object[]> list = ItemQueries.getItemData(getFormulaIdInput(binderDto, selection), selection.getDataQueryName(), null);
        for (Object[] str : list) {
            FormulaDTO dto = new FormulaDTO();
            dto.setFormulaId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setFormulaNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setFormulaName(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setFormulaSid(str[3] == null ? 0 : (Integer) (str[3]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    private List getFormulaIdInput(FormulaDTO binderDto, SelectionDTO selection) {
        List<Object> input = new ArrayList();
        if (binderDto.getFormulaId() != null && !binderDto.getFormulaId().isEmpty()) {
            input.add(binderDto.getFormulaId().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getFormulaNo() != null && !binderDto.getFormulaNo().isEmpty()) {
            input.add(binderDto.getFormulaNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getFormulaName() != null && !binderDto.getFormulaName().isEmpty()) {
            input.add(binderDto.getFormulaName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    public void LazyTableLoadDdlb(ComboBox comboBox, String load_Item_Status_Count, String load_Item_Status, String key) {
        final List inputList = new ArrayList();
        inputList.add(load_Item_Status_Count);
        inputList.add(load_Item_Status);
        LazyContainer containerData = new LazyContainer(HelperDTO.class, new LoadDdlbDAO(inputList, true), new DdlbCriteria());
        comboBox.setPageLength(7);
        comboBox.setContainerDataSource(containerData);
        comboBox.setNullSelectionItemId(ddlbDefaultValue);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        containerData.setMinFilterLength(0);
    }

    public Boolean getEditedItemDetails(final AbstractContractSearchDTO compDTO, final SelectionDTO selection) {
        selection.setIsContractUpdate(false);
        List input = getEditedItemInput(compDTO, selection);
        String queryname = null;
        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {

            queryname = "Abstract Data update For Add";
            if (compDTO.getCaseNo().equals(17)) {
                queryname = "Abstract Data update CheckRecord For Add";
            }
        } else {
            queryname = "Abstract Data update";
            if (compDTO.getCaseNo().equals(17)) {
                queryname = "Abstract Data update CheckRecord";
            }
        }
        Boolean isUpdated = ItemQueries.itemUpdate(input, queryname);
        return isUpdated;
    }

    public Boolean getEditedPopulateItemDetails(final AbstractContractSearchDTO compDTO, final SelectionDTO selection) {
        List input;
        String queryname = null;
        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
            selection.setIsContractUpdate(true);
            queryname = "Abstract FieldFactory Values Update";
            if (compDTO.getCaseNo().equals(17)) {
                selection.setIsContractUpdate(false);
                queryname = "Abstract Data update CheckRecord For Add";
            }
            input = getEditedItemInput(compDTO, selection);
        } else {
            queryname = "Abstract FieldFactory Values Update";
            selection.setIsContractUpdate(true);
            if (compDTO.getCaseNo().equals(17)) {
                selection.setIsContractUpdate(false);
                queryname = "Abstract Data update CheckRecord";
            }
            input = getEditedItemInput(compDTO, selection);
        }
        Boolean isUpdated = ItemQueries.itemUpdate(input, queryname);

        return isUpdated;
    }

    public List getEditedItemInput(AbstractContractSearchDTO compDTO, SelectionDTO selection) {
        List<Object> input = new ArrayList();
        input.add(compDTO.getColumnName());
        switch (compDTO.getCaseNo()) {
            case 1:
                input.add(compDTO.getItemStartDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getItemStartDate())) : null);
                break;
            case 2:
                input.add(compDTO.getItemEndDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getItemEndDate())) : null);
                break;
            case 3:
                input.add(compDTO.getPriceToleranceType() != null ? compDTO.getPriceToleranceType().getId() : null);
                break;
            case 4:
                input.add(compDTO.getCpStartDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getCpStartDate())) : null);
                break;
            case 5:
                input.add(compDTO.getCpEndDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getCpEndDate())) : null);
                break;
            case 6:
                input.add(compDTO.getStatus() != null ? compDTO.getStatus().getId() : null);
                break;
            case 7:
                input.add(compDTO.getPriceTolerance() != null && !compDTO.getPriceTolerance().isEmpty() ? compDTO.getPriceTolerance() : null);
                break;
            case 8:
                input.add(compDTO.getPriceProtectionStartDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getPriceProtectionStartDate())) : null);
                break;
            case 9:
                input.add(compDTO.getPriceProtectionEndDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getPriceProtectionEndDate())) : null);
                break;
            case 10:
                input.add(compDTO.getPriceToleranceType() != null ? compDTO.getPriceToleranceType().getId() : null);
                break;
            case 11:
                input.add(compDTO.getPriceToleranceInterval() != null ? compDTO.getPriceToleranceInterval().getId() : null);
                break;
            case 12:
                input.add(compDTO.getPriceToleranceFrequency() != null ? compDTO.getPriceToleranceFrequency().getId() : null);
                break;
            case 13:
                input.add(compDTO.getBasePrice() != null && !compDTO.getBasePrice().isEmpty() ? compDTO.getBasePrice() : null);
                break;
            case 14:
                input.add(compDTO.getStartDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getStartDate())) : null);
                break;
            case 15:
                input.add(compDTO.getPrice() != null && !compDTO.getPrice().isEmpty() ? compDTO.getPrice() : null);
                break;
            case 16:
                input.add(compDTO.getEndDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getEndDate())) : null);
                break;
            case 17:
                input.add(compDTO.getCheckRecord() ? 1 : 0);
                break;
            case 18:
                input.add(compDTO.getContractPrice() != null && !compDTO.getContractPrice().isEmpty() ? compDTO.getContractPrice() : null);
                break;
            case 19:
                input.add(compDTO.getRSStartDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getRSStartDate())) : null);
                break;
            case 20:
                input.add(compDTO.getRSEndDate() != null ? setQuotes(CommonUtils.DBDate.format(compDTO.getRSEndDate())) : null);
                break;
            case 21:
                input.add(compDTO.getRebateAmount() != null && !compDTO.getRebateAmount().isEmpty() ? compDTO.getRebateAmount() : null);
                break;
            case 22:
                input.add(compDTO.getFormulaMethodId() != null && !compDTO.getFormulaMethodId().isEmpty() ? compDTO.getFormulaMethodId() : null);
                break;
            case 23:
                input.add(compDTO.getTempSid() != null && compDTO.getTempSid() != 0 ? compDTO.getTempSid() : null);
                break;
            case 24:
                input.add(compDTO.getTempSid() != null && compDTO.getTempSid() != 0 ? compDTO.getTempSid() : null);
                break;
        }

        if (!selection.isIsContractUpdate()) { // Condition check for identification of - For check record =1 update values
            input.add(compDTO.getContractMasterSid());
            input.add(compDTO.getCfpContractSid());
            input.add(compDTO.getIfpConteractSid());
            input.add(compDTO.getPsContractSid());
            input.add(compDTO.getRsContractSid());
            input.add(selection.getSessionId());
            if (!selection.getButtonMode().equals(ConstantsUtil.ADD)) {
                input.add(compDTO.getProjectionId());
                input.add(compDTO.getItemMasterSid());
            }

            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) //        input.add(selection.getInternalSessionid());
            {
                if (compDTO.getTransferScreenName().equals(ConstantsUtil.CURRENT_COONTRACT)) {
                    input.add(ConstantsUtil.CURRENT_COONTRACT);
                } else {
                    input.add(ConstantsUtil.TRANSFER_CONTRACT);
                }
            } else if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                if (compDTO.getTransferScreenName().equals(ConstantsUtil.CURRENT_COONTRACT)) {
                    input.add(ConstantsUtil.CURRENT_COONTRACT);
                } else {
                    input.add(ConstantsUtil.TRANSFER_CONTRACT);
                }
            } else {
                input.add(selection.getButtonMode());
            }
        } else {
            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                if (compDTO.getTransferScreenName().equals(ConstantsUtil.CURRENT_COONTRACT)) {
                    input.addAll(getCurrentInput(selection));
                } else {
                    input.addAll(getTransferInput(selection));
                }
            } else {
                input.addAll(getResultsInput(selection));
            }
        }

        return input;
    }

    public boolean getItemDetails(AddItemTableDTO dto, SelectionDTO selection) {
        Boolean isUpdated = ItemQueries.itemUpdate(getEditedInput(dto, selection), "Abstract Data update");
        return isUpdated;
    }

    private List getEditedInput(AddItemTableDTO dto, SelectionDTO selection) {
        List<Object> input = new ArrayList();
        input.add(dto.getColumnName());
        switch (dto.getCaseNo()) {
            case 1:
                input.add(dto.getItemStartDate() != null ? CommonUtils.DBDate.format(dto.getItemStartDate()) : null);
                break;
            case 2:
                input.add(dto.getItemEndDate() != null ? CommonUtils.DBDate.format(dto.getItemEndDate()) : null);
                break;
            case 3:
                input.add(dto.getPriceToleranceType() != null ? dto.getPriceToleranceType() : null);
                break;
            case 4:
                input.add(dto.getCpStartDate() != null ? CommonUtils.DBDate.format(dto.getCpStartDate()) : null);
                break;
            case 5:
                input.add(dto.getCpEndDate() != null ? CommonUtils.DBDate.format(dto.getCpEndDate()) : null);
                break;
            case 6:
                input.add(dto.getStatus() != null ? dto.getStatus().getId() : null);
                break;
            case 7:
                input.add(dto.getPriceTolerance() != null && !dto.getPriceTolerance().isEmpty() ? dto.getPriceTolerance() : null);
                break;
            case 8:
                input.add(dto.getPriceProtectionStartDate() != null ? CommonUtils.DBDate.format(dto.getPriceProtectionStartDate()) : null);
                break;
            case 9:
                input.add(dto.getPriceProtectionEndDate() != null ? CommonUtils.DBDate.format(dto.getPriceProtectionEndDate()) : null);
                break;
            case 10:
                input.add(dto.getPriceToleranceType() != null ? dto.getPriceToleranceType().getId() : null);
                break;
            case 11:
                input.add(dto.getPriceToleranceInterval() != null ? dto.getPriceToleranceInterval() : null);
                break;
            case 12:
                input.add(dto.getPriceToleranceFrequency() != null ? dto.getPriceToleranceFrequency() : null);
                break;
            case 13:
                input.add(dto.getBasePrice() != null && !dto.getBasePrice().isEmpty() ? dto.getBasePrice() : null);
                break;
            case 14:
                input.add(dto.getStartDate() != null ? CommonUtils.DBDate.format(dto.getStartDate()) : null);
                break;
            case 15:
                input.add(dto.getPrice() != null ? dto.getPrice() : null);
                break;
            case 16:
                input.add(dto.getEndDate() != null ? CommonUtils.DBDate.format(dto.getEndDate()) : null);
                break;
            case 17:
                input.add(dto.getCheckRecord() != null ? dto.getCheckRecord() : null);
                break;
            case 18:
                input.add(dto.getRSStartDate() != null ? CommonUtils.DBDate.format(dto.getRSStartDate()) : null);
                break;
            case 19:
                input.add(dto.getRSEndDate() != null ? CommonUtils.DBDate.format(dto.getRSEndDate()) : null);
                break;
            case 20:
                input.add(dto.getRebateAmount() != null ? dto.getRebateAmount() : null);
                break;
            case 21:
                input.add(dto.getFormulaMethodId() != null ? dto.getFormulaMethodId() : null);
                break;

        }

        input.add(dto.getContractSid());
        input.add(dto.getCfpContractSid());
        input.add(dto.getIfpConteractSid());
        input.add(dto.getPsContractSid());
        input.add(dto.getRsContractSid());
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        return input;
    }

    public Boolean massUpdateItemDetails(final List input, final SelectionDTO selection) {
        Boolean isUpdated = ItemQueries.itemUpdate(input, "Abstract Mass update");
        return isUpdated;
    }

    private List getInput(SelectionDTO selection, AddItemTableDTO binderDto) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        if (binderDto.getContractNo_SID() != null && !binderDto.getContractNo_SID().isEmpty()) {
            input.add(binderDto.getContractNo_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getContractName_SID() != null && !binderDto.getContractName_SID().isEmpty()) {
            input.add(binderDto.getContractName_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getContractHolder_SID() != null && !binderDto.getContractHolder_SID().isEmpty()) {
            input.add(binderDto.getContractHolder_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( cm.START_DATE >= '" + CommonUtils.DBDate.format(binderDto.getItemStartDate()) + "')");
        } else {
            input.add(" ");
        }

        if (binderDto.getEndDate() != null) {
            input.add(" AND ( CM.END_DATE <= '" + CommonUtils.DBDate.format(binderDto.getEndDate()) + "')");
        } else {
            input.add(" ");
        }

        if (binderDto.getMarketType_DTO() != null) {
            input.add(binderDto.getMarketType_DTO().getId());
        } else {
            input.add("%");
        }

        if (binderDto.getCfp_SID() != null && !binderDto.getCfp_SID().isEmpty()) {
            input.add(binderDto.getCfp_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getCustomer_SID() != null && !binderDto.getCustomer_SID().isEmpty()) {
            input.add(binderDto.getCustomer_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getCustomer_SID() != null && !binderDto.getCustomer_SID().isEmpty()) {
            input.add(binderDto.getCustomer_SID().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getIfp_SID() != null && !binderDto.getIfp_SID().isEmpty()) {
            input.add(binderDto.getIfp_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getPs_SID() != null && !binderDto.getPs_SID().isEmpty()) {
            input.add(binderDto.getPs_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getRs_SID() != null && !binderDto.getRs_SID().isEmpty()) {
            input.add(binderDto.getRs_SID().replace("*", "%"));
        } else {
            input.add("%");
        }
        StringBuilder sql = AbstractFilter.getInstance().contractfilterQueryGenerator(selection.getFilters());
        if (!(sql == null)) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        return input;
    }

    public List<AbstractContractSearchDTO> getContractResults(SelectionDTO selection, List<ItemIndexDto> selectedItemList, int start, int offset, List input) {
        input.add(start);
        input.add(offset);
        List<Object[]> list = ItemQueries.getItemData(input, selection.getDataQueryName(), null);
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            return setContractDetailsData(list, selection, input.get(2).toString());
        } else {
            return setContractDetailsData(list, selection, input.get(1).toString());
        }
    }

    public int getContractCount(SelectionDTO selection, List<ItemIndexDto> selectedItemList, List input) {
        List<Object[]> list = ItemQueries.getItemData(input, selection.getCountQueryName(), null);
        Object obj = list.isEmpty() ? 0 : list.get(0);
        int count = obj == null ? 0 : (Integer) obj;
        return count;
    }

    public static List getIfpIdsWithAllItems(List input, String queryName) {
        List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
        List l = new ArrayList();
        for (Object[] str : list) {
            l.add(String.valueOf(str[1]));
        }
        return l;
    }

    public static List getcancelRemoveInput(SelectionDTO selection, SummaryDTO tableValue) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(tableValue.getContractSid());
        input.add(tableValue.getIfpSid());
        input.add(tableValue.getCfpSid());
        input.add(tableValue.getPsSid());
        input.add(tableValue.getRsSid());
        return input;
    }

    public void checkAllInsert(boolean checked, SelectionDTO selection) {
        List input = new ArrayList();
        if (checked) {
            input.add(1);
        } else {
            input.add(0);
        }
        input.addAll(AbstractLogic.getResultsInput(selection));
        ItemQueries.itemUpdate(input, "Check All contract");
    }

    /**
     * Abtsract lookup count
     *
     * @param binderDto
     * @param selection
     * @param filters
     * @return int
     */
    public int getLookUpSearchCount(ComponentLookUpDTO binderDto, SelectionDTO selection, Set<Container.Filter> filters) {
        List<Object[]> list = getLookUpRecords(binderDto, selection, selection.getCountQueryName());
        Object obj = list.get(0);
        int count = obj == null ? 0 : (Integer) obj;
        return count;
    }

    /**
     * Get Input
     *
     * @param binderDto
     * @param selection
     * @return
     */
    private List getCFPLookUpInput(ComponentLookUpDTO binderDto, SelectionDTO selection) {
        final List input = new ArrayList();
        if (binderDto.getComponentId() != null && !binderDto.getComponentId().isEmpty()) {
            input.add(binderDto.getComponentId().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentName() != null && !binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getCategory() != null && !binderDto.getCategory().isEmpty()) {
            input.add(binderDto.getCategory().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( CC.CFP_START_DATE >= '" + CommonUtils.DBDate.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }

        if (binderDto.getComponentNo() != null && !binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentType() != null && !binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( CC.CFP_END_DATE <= '" + CommonUtils.DBDate.format(binderDto.getEndDate()) + "')");
        } else {
            input.add(" ");
        }
        StringBuilder sql = AbstractFilter.getInstance().lookUpfilterQueryGenerator(Constants.CFP, selection.getFilters());
        if (sql != null) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    /**
     * Get Input
     *
     * @param binderDto
     * @param selection
     * @return
     */
    private List getIFPLookUpInput(ComponentLookUpDTO binderDto, SelectionDTO selection) {
        final List input = new ArrayList();
        if (binderDto.getComponentName() != null && !binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentNo() != null && !binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentType() != null && !binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getCategory() != null && !binderDto.getCategory().isEmpty()) {
            input.add(binderDto.getCategory().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( IFP_C.IFP_START_DATE >= '" + CommonUtils.DBDate.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( IFP_C.IFP_END_DATE <= '" + CommonUtils.DBDate.format(binderDto.getEndDate()) + "')");
        } else {
            input.add(" ");
        }
        StringBuilder sql = AbstractFilter.getInstance().lookUpfilterQueryGenerator(Constants.IFP, selection.getFilters());
        if (sql != null) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    /**
     * Get Input
     *
     * @param binderDto
     * @param selection
     * @return
     */
    private List getPSLookUpInput(ComponentLookUpDTO binderDto, SelectionDTO selection) {
        final List input = new ArrayList();
        if (binderDto.getComponentName() != null && !binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentNo() != null && !binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentType() != null && !binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getCategory() != null && !binderDto.getCategory().isEmpty()) {
            input.add(binderDto.getCategory().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( PS_C.PS_START_DATE >= '" + CommonUtils.DBDate.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( PS_C.PS_END_DATE <= '" + CommonUtils.DBDate.format(binderDto.getEndDate()) + "')");
        } else {
            input.add(" ");
        }
        StringBuilder sql = AbstractFilter.getInstance().lookUpfilterQueryGenerator(Constants.PS, selection.getFilters());
        if (sql != null) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    /**
     * Get Input
     *
     * @param binderDto
     * @param selection
     * @return
     */
    private List getRSLookUpInput(ComponentLookUpDTO binderDto, SelectionDTO selection) {
        final List input = new ArrayList();
        if (binderDto.getComponentId() != null && !binderDto.getComponentId().isEmpty()) {
            input.add(binderDto.getComponentId().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentNo() != null && !binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentName() != null && !binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getRsProgramType_DTO() != null) {
            input.add(binderDto.getRsProgramType_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getComponentType() != null && !binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getComponentCategory_DTO() != null) {
            input.add(binderDto.getComponentCategory_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( RS_C.RS_START_DATE  >= '" + CommonUtils.DBDate.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( RS_C.RS_END_DATE <= '" + CommonUtils.DBDate.format(binderDto.getEndDate()) + "')");
        } else {
            input.add(" ");
        }
        StringBuilder sql = AbstractFilter.getInstance().lookUpfilterQueryGenerator(Constants.RS, selection.getFilters());
        if (sql != null) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    /**
     * Results method for Lookup abstract
     *
     * @param binderDto
     * @param selection
     * @return
     */
    public List getLookUpSearchResults(ComponentLookUpDTO binderDto, SelectionDTO selection) {
        List<Object[]> list = getLookUpRecords(binderDto, selection, selection.getDataQueryName());
        List<ComponentLookUpDTO> resultList = new ArrayList<ComponentLookUpDTO>();
        if (selection.getOperation().equals(Constants.CFP)) {
            resultList = setCFPLookUpData(list);
        } else if (selection.getOperation().equals(Constants.IFP)) {
            resultList = setIFPLookUpData(list);
        } else if (selection.getOperation().equals(Constants.PS)) {
            resultList = setPSLookUpData(list);
        } else if (selection.getOperation().equals(Constants.RS)) {
            resultList = setRSLookUpData(list);
        }
        return resultList;
    }

    /**
     * Common customization
     *
     * @param binderDto
     * @param selection
     * @param queryName
     * @return list of object
     */
    private List<Object[]> getLookUpRecords(ComponentLookUpDTO binderDto, SelectionDTO selection, String queryName) {
        List input = new ArrayList();
        if (selection.getOperation().equals(Constants.CFP)) {
            input = getCFPLookUpInput(binderDto, selection);
        } else if (selection.getOperation().equals(Constants.IFP)) {
            input = getIFPLookUpInput(binderDto, selection);
        } else if (selection.getOperation().equals(Constants.PS)) {
            input = getPSLookUpInput(binderDto, selection);
        } else if (selection.getOperation().equals(Constants.RS)) {
            input = getRSLookUpInput(binderDto, selection);
        }
        List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
        return list;
    }

    /**
     * Set Data
     *
     * @param list
     * @return
     */
    private List setCFPLookUpData(List<Object[]> list) {
        List finalResult = new ArrayList();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentId(ObjNullCheck(str[0]) ? StringUtils.EMPTY : (String) str[0]);
            dto.setComponentNo(ObjNullCheck(str[1]) ? StringUtils.EMPTY : (String) str[1]);
            dto.setComponentName(ObjNullCheck(str[2]) ? StringUtils.EMPTY : (String) str[2]);
            dto.setComponentType(ObjNullCheck(str[3]) ? StringUtils.EMPTY : (String) str[3]);
            dto.setCategory(ObjNullCheck(str[4]) ? StringUtils.EMPTY : (String) str[4]);
            dto.setDesignation(ObjNullCheck(str[5]) ? StringUtils.EMPTY : (String) str[5]);
            dto.setPlanId(ObjNullCheck(str[06]) ? StringUtils.EMPTY : (String) str[6]);
            dto.setPlanName(ObjNullCheck(str[7]) ? StringUtils.EMPTY : (String) str[7]);
            dto.setComponentStatus(ObjNullCheck(str[8]) ? StringUtils.EMPTY : (String) str[8]);
            dto.setTradeClass(ObjNullCheck(str[9]) ? StringUtils.EMPTY : (String) str[9]);
            dto.setStartDate(ObjNullCheck(str[10]) ? null : (Date) str[10]);
            dto.setEndDate(ObjNullCheck(str[11]) ? null : (Date) str[11]);
            finalResult.add(dto);
        }
        return finalResult;
    }

    /**
     * Set Data
     *
     * @param list
     * @return
     */
    private List setIFPLookUpData(List<Object[]> list) {
        List finalResult = new ArrayList();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentNo(ObjNullCheck(str[0]) ? StringUtils.EMPTY : (String) str[0]);
            dto.setComponentName(ObjNullCheck(str[1]) ? StringUtils.EMPTY : (String) str[1]);
            dto.setComponentType(ObjNullCheck(str[2]) ? StringUtils.EMPTY : (String) str[2]);
            dto.setCategory(ObjNullCheck(str[3]) ? StringUtils.EMPTY : (String) str[3]);
            dto.setDesignation(ObjNullCheck(str[4]) ? StringUtils.EMPTY : (String) str[4]);
            dto.setPlanId(ObjNullCheck(str[5]) ? StringUtils.EMPTY : (String) str[5]);
            dto.setPlanName(ObjNullCheck(str[6]) ? StringUtils.EMPTY : (String) str[6]);
            dto.setComponentStatus(ObjNullCheck(str[7]) ? StringUtils.EMPTY : (String) str[7]);
            dto.setStartDate(ObjNullCheck(str[8]) ? null : (Date) str[8]);
            dto.setEndDate(ObjNullCheck(str[9]) ? null : (Date) str[9]);
            finalResult.add(dto);
        }
        return finalResult;
    }

    /**
     * Set Data
     *
     * @param list
     * @return
     */
    private List setPSLookUpData(List<Object[]> list) {
        List finalResult = new ArrayList();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentNo(ObjNullCheck(str[0]) ? StringUtils.EMPTY : (String) str[0]);
            dto.setComponentName(ObjNullCheck(str[1]) ? StringUtils.EMPTY : (String) str[1]);
            dto.setComponentType(ObjNullCheck(str[2]) ? StringUtils.EMPTY : (String) str[2]);
            dto.setCategory(ObjNullCheck(str[3]) ? StringUtils.EMPTY : (String) str[3]);
            dto.setTradeClass(ObjNullCheck(str[4]) ? StringUtils.EMPTY : (String) str[4]);
            dto.setDesignation(ObjNullCheck(str[5]) ? StringUtils.EMPTY : (String) str[5]);
            dto.setPlanId(ObjNullCheck(str[6]) ? StringUtils.EMPTY : (String) str[6]);
            dto.setPlanName(ObjNullCheck(str[7]) ? StringUtils.EMPTY : (String) str[7]);
            dto.setComponentStatus(ObjNullCheck(str[8]) ? StringUtils.EMPTY : (String) str[8]);
            dto.setStartDate(ObjNullCheck(str[9]) ? null : (Date) str[9]);
            dto.setEndDate(ObjNullCheck(str[10]) ? null : (Date) str[10]);

            finalResult.add(dto);
        }
        return finalResult;
    }

    /**
     * Set Data
     *
     * @param list
     * @return
     */
    private List setRSLookUpData(List<Object[]> list) {
        List finalResult = new ArrayList();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentId(ObjNullCheck(str[0]) ? StringUtils.EMPTY : (String) str[0]);
            dto.setComponentNo(ObjNullCheck(str[1]) ? StringUtils.EMPTY : (String) str[1]);
            dto.setComponentName(ObjNullCheck(str[2]) ? StringUtils.EMPTY : (String) str[2]);
            dto.setComponentType(ObjNullCheck(str[3]) ? StringUtils.EMPTY : (String) str[3]);
            dto.setRsProgramType(ObjNullCheck(str[4]) ? StringUtils.EMPTY : (String) str[4]);
            dto.setCategory(ObjNullCheck(str[5]) ? StringUtils.EMPTY : (String) str[5]);
            dto.setTradeClass(ObjNullCheck(str[6]) ? StringUtils.EMPTY : (String) str[6]);
            dto.setDesignation(ObjNullCheck(str[7]) ? StringUtils.EMPTY : (String) str[7]);
            dto.setPlanId(ObjNullCheck(str[8]) ? StringUtils.EMPTY : (String) str[8]);
            dto.setPlanName(ObjNullCheck(str[9]) ? StringUtils.EMPTY : (String) str[9]);
            dto.setComponentStatus(ObjNullCheck(str[10]) ? StringUtils.EMPTY : (String) str[10]);
            dto.setStartDate(ObjNullCheck(str[11]) ? null : (Date) str[11]);
            dto.setEndDate(ObjNullCheck(str[12]) ? null : (Date) str[12]);
            finalResult.add(dto);
        }
        return finalResult;
    }

    public static Boolean ObjNullCheck(Object obj) {
        if (obj == null || Constants.NULL.equals(obj) || Constants.SELECT_ONE.contains(String.valueOf(obj))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static void loaDDLB(final ComboBox comboBox, String columnName, String tableName, Boolean isFilter, String queryName) {
        comboBox.setPageLength(7);
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        comboBox.setContainerDataSource(container);
        if (isFilter) {
            comboBox.setNullSelectionItemId(ddlbShowAllValue);
        } else {
            comboBox.setNullSelectionItemId(ddlbDefaultValue);
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        comboBox.addItems(getDDLBList(columnName, tableName, isFilter, queryName));
    }

    public static List getDDLBList(String columnName, String tableName, Boolean isFilter, String queryName) {
        String comboboxName = tableName + "-" + columnName;
        if (ddlbMap.get(comboboxName) == null) {
            List input = new ArrayList();
            input.add(tableName);
            input.add(columnName);
            List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
            List<HelperDTO> resultList = new ArrayList<HelperDTO>();
            if (isFilter) {
                HelperDTO defaultValue = new HelperDTO(0, Constants.SHOW_ALL);
                resultList.add(defaultValue);
            } else {
                HelperDTO defaultValue = new HelperDTO(0, IndicatorConstants.SELECT_ONE.getConstant());
                resultList.add(defaultValue);
            }

            for (Object[] str : list) {
                if (!str[1].equals(String.valueOf(IndicatorConstants.SELECT_ONE.getConstant()))) {
                    HelperDTO dto = new HelperDTO();
                    dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                    dto.setDescription(str[1] == null ? Constants.ZEROSTRING : String.valueOf(str[1]));
                    resultList.add(dto);
                }
            }

            ddlbMap.put(comboboxName, resultList);
            return resultList;
        }

        return ddlbMap.get(comboboxName);
    }

    public static Object getItemName(List<ItemIndexDto> itemList) {
        List itemIdList = new ArrayList();
        for (ItemIndexDto dto : itemList) {
            itemIdList.add(dto.getItemName());
        }
        return CommonUtils.getListToString(itemIdList);
    }

    public static List<Object[]> callProcedure(String procedureName, Object[] orderedArgs) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        ResultSet rs = null;
        List<Object[]> objectList = new ArrayList<Object[]>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                String procedureToCall = "{call " + procedureName;
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall += "(";
                    }
                    procedureToCall += "?,";
                    if (i == noOfArgs - 1) {
                        procedureToCall += ")";
                    }
                }
                procedureToCall = procedureToCall.replace(",)", ")");
                procedureToCall += "}";
                statement = connection.prepareCall(procedureToCall);
                for (int i = 0; i < noOfArgs; i++) {
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);

            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
           
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                LOGGER.error(new Date() + e.getMessage());
            }
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(new Date() + e.getMessage());
            }
            try {
                connection.close();

            } catch (Exception ex) {
                LOGGER.error(new Date() + ex.getMessage());
            }
            try {
                System.gc();
            } catch (Exception ex) {
                LOGGER.error(new Date() + ex.getMessage());
            }
        }
        return objectList;
    }

    private static List<Object[]> convertResultSetToList(ResultSet rs) {
        List<Object[]> objList = new ArrayList<Object[]>();

        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            Object[] header = new Object[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                Object label = rsMetaData.getColumnLabel(i);
                header[i - 1] = label;
            }
            while (rs.next()) {
                Object[] str = new Object[columnCount];
                for (int i = 1; i <= columnCount; ++i) {
                    Object obj = rs.getObject(i);
                    str[i - 1] = obj;
                }
                objList.add(str);
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.info(ex.getCause());
            }
        }
        return objList;
    }

    /**
     * Date check
     *
     * @param dto
     * @param selection
     * @param columnName
     * @return Date
     */
    public Date getStartDateCheck(final AbstractContractSearchDTO dto, final SelectionDTO selection, final String columnName) {
        selection.setIsContractUpdate(false);
        List input = getEditedItemInput(dto, selection);
        input.remove(0);
        input.add(0, columnName);
        String query = "endDateBeforeStartDate";
        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
            query = "endDateBeforeStartDateAdd";
        }
        List<Object[]> list = ItemQueries.getItemData(input, query, null);
        Date startDate = null;
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            startDate = obj == null ? null : (Date) obj;
        }
        return startDate;
    }

    private String setQuotes(String date) {
        return CommonUtils.QUOTES + date + CommonUtils.QUOTES;
    }

    /**
     * Load history.
     *
     * @param frequency the frequency
     * @return the list
     */
    public static final List<String> loadHistory(String frequency, String period) {
        List<String> history = new ArrayList<String>();
        int endValue = 0;
        String freq = StringUtils.EMPTY;
        if (ANNUALLY.equals(frequency)) {
            endValue = 3;
            freq = YEARS.getConstant();
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {

            endValue = 6;
            freq = SEMI_ANNUAL.getConstant();
        } else if (QUARTERLY.equals(frequency)) {

            endValue = 12;
            freq = QUARTERS.getConstant();
        } else if (MONTHLY.getConstant().equals(frequency)) {

            endValue = 36;
            freq = MONTHS.getConstant();
        }

        for (int i = 1; i <= endValue; i++) {
            if ((i == 1) && (QUARTERS.getConstant().equals(freq) || MONTHS.getConstant().equals(freq) || YEARS.getConstant().equals(freq))) {
                period = freq.replace("s", StringUtils.EMPTY);
                history.add(String.valueOf(i) + SPACE + period);
            } else {
                history.add(String.valueOf(i) + SPACE + freq);
            }
        }

        return history;
    }

    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter) throws Exception {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }
}
