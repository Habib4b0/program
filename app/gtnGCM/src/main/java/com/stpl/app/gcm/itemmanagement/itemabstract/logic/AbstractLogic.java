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
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil.FrequencyConstants;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractFilter;
import com.stpl.app.gcm.itemmanagement.itemabstract.lazyload.DdlbCriteria;
import com.stpl.app.gcm.itemmanagement.itemabstract.lazyload.LoadDdlbDAO;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.tp.logic.GcmtFilterLogic;
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
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.asi.ui.addons.lazycontainer.LazyContainer;

/**
 *
 * @author mohamed.hameed
 */
public class AbstractLogic {

    private static final HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    private static final HelperDTO ddlbShowAllValue = new HelperDTO(0, Constants.SHOW_ALL);
    private static final Map<String, List> ddlbMap = new HashMap<>();
    public static final String DESCRIPTION = "description";
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    private static final AbstractLogic logic = new AbstractLogic();
    private static final String COMMA = ",";
    public final SimpleDateFormat formatter = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    /**
     * The helper list util.
     */
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();

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
        comboBox.setPageLength(NumericConstants.SEVEN);
        comboBox.setContainerDataSource(containerData);
        if (isFilter) {
            comboBox.setNullSelectionItemId(ddlbShowAllValue);
        } else {
            comboBox.setNullSelectionItemId(ddlbDefaultValue);
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setItemCaptionPropertyId(DESCRIPTION);
        containerData.setMinFilterLength(0);
    }

    public int getDdlbCount(String queryName, final List<String> input) {
        List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    public List<HelperDTO> getDdlbList(String queryName, final List<String> input, final Boolean isFilter) {
        List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
        List<HelperDTO> resultList = new ArrayList<>();
        if (Integer.parseInt(String.valueOf(input.get(1))) == 0) {
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
                dto.setId(str[0] == null ? 0 : Integer.parseInt(str[0].toString()));
                dto.setDescription(str[1] == null ? Constants.ZEROSTRING: String.valueOf(str[1]));
                resultList.add(dto);
            }
        }
        return resultList;
    }

    public int getContractCount(SelectionDTO selection, AddItemTableDTO binderDto) {
        List input = getInput(selection, binderDto);
        List<Object[]> list = ItemQueries.getItemData(input, selection.getCountQueryName(), null);
        Object obj = list.get(0);
        int count = obj == null ? 0 : (Integer) obj;
        return count;
    }

    public List getContractResults(SelectionDTO selection, int start, int offset, AddItemTableDTO binderDto) {

        List input = getInput(selection, binderDto);
        input.add(start);
        input.add(offset);
        List<Object[]> list = ItemQueries.getItemData(input, selection.getDataQueryName(), null);
        return setContractDetailsData(list, selection, input.get(1).toString());
    }
    
    private List<AbstractContractSearchDTO> setContractDetailsData(List<Object[]> list, SelectionDTO selection, String screenName) {
        List<AbstractContractSearchDTO> resultList = new ArrayList<>();

        for (Object[] str : list) {
            AbstractContractSearchDTO dto = new AbstractContractSearchDTO();
            dto.setProjectionId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            if (!String.valueOf(str[1]).equals("Approved")) {
                dto.setWorkFlowStatus(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            }
            dto.setContractHolder(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setContractNo(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setContractName(str[NumericConstants.FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
            dto.setMarketType(str[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
            dto.setStartDate(str[NumericConstants.SIX] == null ? null : (Date) str[NumericConstants.SIX]);
            dto.setEndDate(str[NumericConstants.SEVEN] == null ? null : (Date) str[NumericConstants.SEVEN]);
            HelperDTO status = new HelperDTO();
            status.setId(str[NumericConstants.EIGHT] == null ? 0 : Integer.parseInt(String.valueOf(str[NumericConstants.EIGHT])));
            status.setDescription(str[NumericConstants.FORTY_THREE] == null ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : String.valueOf(String.valueOf(str[NumericConstants.FORTY_THREE])));
            dto.setStatus(status);
            dto.setItemStartDate(str[NumericConstants.NINE] == null ? null : (Date) str[NumericConstants.NINE]);
            dto.setItemEndDate(str[NumericConstants.TEN] == null ? null : (Date) str[NumericConstants.TEN]);

            dto.setCpStartDate(str[NumericConstants.ELEVEN] == null ? null : (Date) str[NumericConstants.ELEVEN]);
            dto.setCpEndDate(str[NumericConstants.TWELVE] == null ? null : (Date) str[NumericConstants.TWELVE]);
            dto.setContractPrice(str[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTEEN]));
            dto.setPrice(str[NumericConstants.FOURTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOURTEEN]));
            dto.setPriceProtectionStartDate(str[NumericConstants.FIFTEEN] == null ? null : (Date) str[NumericConstants.FIFTEEN]);
            dto.setPriceProtectionEndDate(str[NumericConstants.SIXTEEN] == null ? null : (Date) str[NumericConstants.SIXTEEN]);
            HelperDTO priceToleranceType = new HelperDTO();
            priceToleranceType.setId(str[NumericConstants.SEVENTEEN] == null ? 0 : Integer.parseInt(String.valueOf(str[NumericConstants.SEVENTEEN])));
            priceToleranceType.setDescription(str[NumericConstants.FORTY_FOUR] == null ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : String.valueOf(String.valueOf(str[NumericConstants.FORTY_FOUR])));
            dto.setPriceToleranceType(priceToleranceType);
            dto.setPriceTolerance(str[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHTEEN]));
            HelperDTO priceTolerance = new HelperDTO();
            HelperDTO priceToleranceFrequency = new HelperDTO();
            priceToleranceFrequency.setId(str[NumericConstants.NINETEEN] == null ? 0 : Integer.parseInt(String.valueOf(str[NumericConstants.NINETEEN])));
            priceToleranceFrequency.setDescription(str[NumericConstants.FORTY_FIVE] == null ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : String.valueOf(String.valueOf(str[NumericConstants.FORTY_FIVE])));
            dto.setPriceToleranceFrequency(priceToleranceFrequency);
            priceTolerance.setId(str[NumericConstants.TWENTY] == null ? 0 : Integer.parseInt(String.valueOf(str[NumericConstants.TWENTY])));
            priceTolerance.setDescription(str[NumericConstants.FORTY_SIX] == null ? Constants.IndicatorConstants.SELECT_ONE.getConstant() : String.valueOf(String.valueOf(str[NumericConstants.FORTY_SIX])));
            dto.setPriceToleranceInterval(priceTolerance);
            dto.setBasePrice(str[NumericConstants.TWENTY_ONE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_ONE]));
            dto.setRSStartDate(str[NumericConstants.TWENTY_TWO] == null ? null : (Date) str[NumericConstants.TWENTY_TWO]);
            dto.setRSEndDate(str[NumericConstants.TWENTY_THREE] == null ? null : (Date) str[NumericConstants.TWENTY_THREE]);
            dto.setFormulaId(str[NumericConstants.TWENTY_FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_FOUR]));
            dto.setFormulaMethodId(str[NumericConstants.TWENTY_FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_FIVE]));
            dto.setRebateAmount(str[NumericConstants.TWENTY_SIX] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_SIX]));
            dto.setCfpNO(str[NumericConstants.TWENTY_SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_SEVEN]));
            dto.setCfpName(str[NumericConstants.TWENTY_EIGHT] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_EIGHT]));
            dto.setIfpNo(str[NumericConstants.TWENTY_NINE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_NINE]));
            dto.setIfpName(str[NumericConstants.THIRTY] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY]));
            dto.setPsNo(str[NumericConstants.THIRTY_ONE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_ONE]));
            dto.setPsName(str[NumericConstants.THIRTY_TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_TWO]));
            dto.setRsNo(str[NumericConstants.THIRTY_THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_THREE]));
            dto.setRsName(str[NumericConstants.THIRTY_FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_FOUR]));
            dto.setRarCategory(str[NumericConstants.THIRTY_FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_FIVE]));
            dto.setCheckRecord(str[NumericConstants.THIRTY_SIX] == null ? Boolean.FALSE : (Boolean) str[NumericConstants.THIRTY_SIX]);
            dto.setContractMasterSid(str[NumericConstants.THIRTY_SEVEN] == null ? 0 : Integer.parseInt(str[NumericConstants.THIRTY_SEVEN].toString()));
            dto.setCfpContractSid(str[NumericConstants.THIRTY_EIGHT] == null ? 0 : Integer.parseInt(str[NumericConstants.THIRTY_EIGHT].toString()));
            dto.setIfpConteractSid(str[NumericConstants.THIRTY_NINE] == null ? 0 : Integer.parseInt(str[NumericConstants.THIRTY_NINE].toString()));
            dto.setPsContractSid(str[NumericConstants.FORTY] == null ? 0 : Integer.parseInt(str[NumericConstants.FORTY].toString()));
            dto.setRsContractSid(str[NumericConstants.FORTY_ONE] == null ? 0 : Integer.parseInt(str[NumericConstants.FORTY_ONE].toString()));
            dto.setItemMasterSid(str[NumericConstants.FORTY_TWO] == null ? 0 : Integer.parseInt(str[NumericConstants.FORTY_TWO].toString()));
            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
                dto.setTransferScreenName(screenName);
                selection.setTransferScreenName(screenName);
            }
            
            dto.setNep(str[NumericConstants.FORTY_SEVEN] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_SEVEN]) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FORTY_SEVEN])); // TEXT FIELD
            dto.setPriceProtectionStatus(str[NumericConstants.FORTY_EIGHT] == null || Constants.NULL.equals(str[NumericConstants.FORTY_EIGHT]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.FORTY_EIGHT]))));
            dto.setNepFormula(str[NumericConstants.FORTY_NINE] == null || Constants.NULL.equals(str[NumericConstants.FORTY_NINE]) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FORTY_NINE])); // Lookup
            dto.setMaxIncrementalChange(str[NumericConstants.FIFTY] == null || Constants.NULL.equals(str[NumericConstants.FIFTY]) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIFTY])); // Lookup
            dto.setResetEligible(str[NumericConstants.FIFTY_ONE] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_ONE]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.FIFTY_ONE]))));
            dto.setResetType(str[NumericConstants.FIFTY_TWO] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_TWO]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.FIFTY_TWO]))));
            dto.setResetDate(str[NumericConstants.FIFTY_THREE] == null ? null : (Date) str[NumericConstants.FIFTY_THREE]);
            dto.setResetInterval(str[NumericConstants.FIFTY_FOUR] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_FOUR]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.FIFTY_FOUR]))));
            dto.setResetFrequency(str[NumericConstants.FIFTY_FIVE] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_FIVE]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.FIFTY_FIVE]))));
            dto.setNetPriceType(str[NumericConstants.FIFTY_SIX] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_SIX]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.FIFTY_SIX]))));
            dto.setNetPriceTypeFormula(str[NumericConstants.FIFTY_SEVEN] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_SEVEN]) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIFTY_SEVEN])); // Lookup
            dto.setResetPriceType(str[NumericConstants.FIFTY_EIGHT] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_EIGHT]) ? 0 : Integer.parseInt(String.valueOf(str[NumericConstants.FIFTY_EIGHT])));
            dto.setNetResetPriceType(str[NumericConstants.FIFTY_NINE] == null || Constants.NULL.equals(str[NumericConstants.FIFTY_NINE]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.FIFTY_NINE]))));
            dto.setNetResetPriceFormula(str[NumericConstants.SIXTY] == null || Constants.NULL.equals(str[NumericConstants.SIXTY]) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIXTY])); // Lookup
            dto.setBasePriceType(str[NumericConstants.SIXTY_ONE] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_ONE]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.SIXTY_ONE]))));
            dto.setSubsequentPeriodPriceType(str[NumericConstants.SIXTY_TWO] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_TWO]) ? 0 : Integer.parseInt(String.valueOf(str[NumericConstants.SIXTY_TWO])));
            dto.setNetSubsequentPeriodPrice(str[NumericConstants.SIXTY_THREE] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_THREE]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.SIXTY_THREE]))));
            dto.setNetSubsequentPeriodPriceFormula(str[NumericConstants.SIXTY_FOUR] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_FOUR]) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIXTY_FOUR])); // Lookup
            dto.setNetBaselineWACFormula(str[NumericConstants.SIXTY_FIVE] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_FIVE]) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIXTY_FIVE])); // Lookup
            dto.setBaselineNetWAC(str[NumericConstants.SIXTY_SIX] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_SIX]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.SIXTY_SIX]))));
            dto.setPriceType(str[NumericConstants.SIXTY_SEVEN] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_SEVEN]) ? 0 : Integer.parseInt(String.valueOf(str[NumericConstants.SIXTY_SEVEN])));
            dto.setMeasurementPrice(str[NumericConstants.SIXTY_EIGHT] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_EIGHT]) ? 0 : Integer.parseInt(String.valueOf(str[NumericConstants.SIXTY_EIGHT])));
            dto.setBaseLineWacManual(str[NumericConstants.SIXTY_NINE] == null || Constants.NULL.equals(str[NumericConstants.SIXTY_NINE]) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIXTY_NINE])); // TEXT FIELD
            dto.setBaseLineWacDate(str[NumericConstants.SEVENTY] == null ? null : (Date) str[NumericConstants.SEVENTY]);
            dto.setBaseLineWacPriceType(str[71] == null || Constants.NULL.equals(str[71]) ? 0 : Integer.parseInt(String.valueOf(str[71])));
            
            String basePriceType = dto.getBasePriceType().getDescription();
            if (!Constants.EMPTY.equals(dto.getBaseLineWacManual()) && Constants.MANUAL_LABLE_NAME.equals(basePriceType)) {
                dto.setBaselineWAC(dto.getBaseLineWacManual());
            } else if (Constants.DATE_LABLE_NAME.equals(basePriceType)) {
                dto.setBaselineWAC(dto.getBaseLineWacDate());
            } else if (Constants.PRICE_TYPE_LABEL.equals(basePriceType)) {
                dto.setBaselineWAC(dto.getBaseLineWacPriceType());
            } else {
                dto.setBaselineWAC(Constants.EMPTY);
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
            result.append(dto.getSystemId() ).append( ',');
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
        List<ComponentInfoDTO> finalResult = new ArrayList<>();
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
        List<ComponentInfoDTO> finalResult = new ArrayList<>();
        for (Object[] str : list) {
            ComponentInfoDTO dto = new ComponentInfoDTO();
            dto.setItemNo(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setItemName(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setItemStatus(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setStartDate(str[NumericConstants.THREE] == null ? null : (Date) (str[NumericConstants.THREE]));
            dto.setEndDate(str[NumericConstants.FOUR] == null ? null : (Date) (str[NumericConstants.FOUR]));
            dto.setStatus(str[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
            dto.setTradeClass(str[NumericConstants.SIX] == null || Constants.SELECT_ONE.equalsIgnoreCase(String.valueOf(str[NumericConstants.SIX])) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
            dto.setAttachedDate(str[NumericConstants.SEVEN] == null ? null : (Date) (str[NumericConstants.SEVEN]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    public List<ComponentInfoDTO> getCustomizedIFPComponentInfo(final List<Object[]> list) {
        List<ComponentInfoDTO> finalResult = new ArrayList<>();
        for (Object[] str : list) {
            ComponentInfoDTO dto = new ComponentInfoDTO();
            dto.setItemNo(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setItemName(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setBrand(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setStatus(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setStartDate(str[NumericConstants.FOUR] == null ? null : (Date) (str[NumericConstants.FOUR]));
            dto.setEndDate(str[NumericConstants.FIVE] == null ? null : (Date) (str[NumericConstants.FIVE]));
            dto.setAttachedDate(str[NumericConstants.SIX] == null ? null : (Date) (str[NumericConstants.SIX]));

            finalResult.add(dto);
        }
        return finalResult;
    }

    public List<ComponentInfoDTO> getCustomizedPSComponentInfo(final List<Object[]> list) {
        List<ComponentInfoDTO> finalResult = new ArrayList<>();
        try {
            for (Object[] str : list) {
                ComponentInfoDTO dto = new ComponentInfoDTO();
                dto.setItemNo(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
                dto.setItemName(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
                dto.setBrand(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
                dto.setPriceProtectionStatus(str[NumericConstants.THREE] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.THREE])) && !Constants.NULL.equals(str[NumericConstants.THREE]) ? getDescription(Integer.parseInt(str[NumericConstants.THREE].toString())) : StringUtils.EMPTY);
                dto.setPriceProtectionStartDate(str[NumericConstants.FOUR] == null ? null : (Date) (str[NumericConstants.FOUR]));
                dto.setPriceProtectionEndDate(str[NumericConstants.FIVE] == null ? null : (Date) (str[NumericConstants.FIVE]));
                dto.setMeasurementPrice(str[NumericConstants.THIRTY_ONE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_ONE]));
                dto.setNep(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
                dto.setNepFormula(str[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
                dto.setBasePriceType(str[NumericConstants.NINE] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.NINE])) && !Constants.NULL.equals(str[NumericConstants.NINE]) ? getDescription(Integer.parseInt(str[NumericConstants.NINE].toString())) : StringUtils.EMPTY);
                dto.setBaselineNetWAC(str[NumericConstants.TEN] != null && !Constants.ZEROSTRING.equals(str[NumericConstants.TEN]) && !Constants.NULL.equals(str[NumericConstants.TEN]) ? getDescription(Integer.parseInt(str[NumericConstants.TEN].toString())) : StringUtils.EMPTY);
                dto.setNetBaselineWACFormula(str[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ELEVEN]));
                dto.setSubsequentPeriodPriceType(str[NumericConstants.THIRTY_TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_TWO]));
                dto.setNetSubsequentPeriodPrice(str[NumericConstants.THIRTEEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.THIRTEEN])) && !Constants.NULL.equals(str[NumericConstants.THIRTEEN]) ? getDescription(Integer.parseInt(str[NumericConstants.THIRTEEN].toString())) : StringUtils.EMPTY);
                dto.setNetSubsequentPeriodPriceFormula(str[NumericConstants.FOURTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOURTEEN]));
                dto.setPriceToleranceInterval(str[NumericConstants.FIFTEEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.FIFTEEN])) && !Constants.NULL.equals(str[NumericConstants.FIFTEEN]) ? getDescription(Integer.parseInt(str[NumericConstants.FIFTEEN].toString())) : StringUtils.EMPTY);
                dto.setPriceToleranceFrequency(str[NumericConstants.SIXTEEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.SIXTEEN])) && !Constants.NULL.equals(str[NumericConstants.SIXTEEN]) ? getDescription(Integer.parseInt(str[NumericConstants.SIXTEEN].toString())) : StringUtils.EMPTY);
                dto.setPriceToleranceType(str[NumericConstants.SEVENTEEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.SEVENTEEN])) && !Constants.NULL.equals(str[NumericConstants.SEVENTEEN]) ? getDescription(Integer.parseInt(str[NumericConstants.SEVENTEEN].toString())) : StringUtils.EMPTY);
                dto.setPriceTolerance(str[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHTEEN]));
                dto.setMaxIncrementalChange(str[NumericConstants.NINETEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.NINETEEN]));
                dto.setResetEligible(str[NumericConstants.TWENTY] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY])) && !Constants.NULL.equals(str[NumericConstants.TWENTY]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY].toString())) : StringUtils.EMPTY);
                dto.setResetType(str[NumericConstants.TWENTY_ONE] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_ONE])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_ONE]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_ONE].toString())) : StringUtils.EMPTY);
                dto.setResetDate(str[NumericConstants.TWENTY_TWO] == null ? null : (Date) (str[NumericConstants.TWENTY_TWO]));
                dto.setResetInterval(str[NumericConstants.TWENTY_THREE] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_THREE])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_THREE]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_THREE].toString())) : StringUtils.EMPTY);
                dto.setResetFrequency(str[NumericConstants.TWENTY_FOUR] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_FOUR])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_FOUR]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_FOUR].toString())) : StringUtils.EMPTY);
                dto.setResetPriceType(str[NumericConstants.THIRTY_THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_THREE]));
                dto.setNetResetPriceType(str[NumericConstants.TWENTY_SIX] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_SIX])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_SIX]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_SIX].toString())) : StringUtils.EMPTY);
                dto.setNetResetPriceFormula(str[NumericConstants.TWENTY_SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_SEVEN]));
                dto.setNetPriceType(str[NumericConstants.TWENTY_EIGHT] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_EIGHT])) &&!Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.THREE])) && !Constants.NULL.equals(str[NumericConstants.THREE]) ? getDescription(Integer.parseInt(str[NumericConstants.THREE].toString())) : StringUtils.EMPTY);
                dto.setPriceProtectionStartDate(str[NumericConstants.FOUR] == null ? null : (Date) (str[NumericConstants.FOUR]));
                dto.setPriceProtectionEndDate(str[NumericConstants.FIVE] == null ? null : (Date) (str[NumericConstants.FIVE]));
                dto.setMeasurementPrice(str[NumericConstants.THIRTY_ONE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_ONE]));
                dto.setNep(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
                dto.setNepFormula(str[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
                dto.setBasePriceType(str[NumericConstants.NINE] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.NINE])) && !Constants.NULL.equals(str[NumericConstants.NINE]) ? getDescription(Integer.parseInt(str[NumericConstants.NINE].toString())) : StringUtils.EMPTY);
                dto.setBaselineNetWAC(str[NumericConstants.TEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TEN])) && !Constants.NULL.equals(str[NumericConstants.TEN]) ? getDescription(Integer.parseInt(str[NumericConstants.TEN].toString())) : StringUtils.EMPTY);
                dto.setNetBaselineWACFormula(str[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ELEVEN]));
                dto.setSubsequentPeriodPriceType(str[NumericConstants.THIRTY_TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_TWO]));
                dto.setNetSubsequentPeriodPrice(str[NumericConstants.THIRTEEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.THIRTEEN])) && !Constants.NULL.equals(str[NumericConstants.THIRTEEN]) ? getDescription(Integer.parseInt(str[NumericConstants.THIRTEEN].toString())) : StringUtils.EMPTY);
                dto.setNetSubsequentPeriodPriceFormula(str[NumericConstants.FOURTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOURTEEN]));
                dto.setPriceToleranceInterval(str[NumericConstants.FIFTEEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.FIFTEEN])) && !Constants.NULL.equals(str[NumericConstants.FIFTEEN]) ? getDescription(Integer.parseInt(str[NumericConstants.FIFTEEN].toString())) : StringUtils.EMPTY);
                dto.setPriceToleranceFrequency(str[NumericConstants.SIXTEEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.SIXTEEN])) && !Constants.NULL.equals(str[NumericConstants.SIXTEEN]) ? getDescription(Integer.parseInt(str[NumericConstants.SIXTEEN].toString())) : StringUtils.EMPTY);
                dto.setPriceToleranceType(str[NumericConstants.SEVENTEEN] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.SEVENTEEN])) && !Constants.NULL.equals(str[NumericConstants.SEVENTEEN]) ? getDescription(Integer.parseInt(str[NumericConstants.SEVENTEEN].toString())) : StringUtils.EMPTY);
                dto.setPriceTolerance(str[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHTEEN]));
                dto.setMaxIncrementalChange(str[NumericConstants.NINETEEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.NINETEEN]));
                dto.setResetEligible(str[NumericConstants.TWENTY] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY])) && !Constants.NULL.equals(str[NumericConstants.TWENTY]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY].toString())) : StringUtils.EMPTY);
                dto.setResetType(str[NumericConstants.TWENTY_ONE] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_ONE])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_ONE]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_ONE].toString())) : StringUtils.EMPTY);
                dto.setResetDate(str[NumericConstants.TWENTY_TWO] == null ? null : (Date) (str[NumericConstants.TWENTY_TWO]));
                dto.setResetInterval(str[NumericConstants.TWENTY_THREE] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_THREE])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_THREE]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_THREE].toString())) : StringUtils.EMPTY);
                dto.setResetFrequency(str[NumericConstants.TWENTY_FOUR] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_FOUR])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_FOUR]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_FOUR].toString())) : StringUtils.EMPTY);
                dto.setResetPriceType(str[NumericConstants.THIRTY_THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_THREE]));
                dto.setNetResetPriceType(str[NumericConstants.TWENTY_SIX] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_SIX])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_SIX]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_SIX].toString())) : StringUtils.EMPTY);
                dto.setNetResetPriceFormula(str[NumericConstants.TWENTY_SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_SEVEN]));
                dto.setNetPriceType(str[NumericConstants.TWENTY_EIGHT] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.TWENTY_EIGHT])) && !Constants.NULL.equals(str[NumericConstants.TWENTY_EIGHT]) ? getDescription(Integer.parseInt(str[NumericConstants.TWENTY_EIGHT].toString())) : StringUtils.EMPTY);
                dto.setNetPriceTypeFormula(str[NumericConstants.TWENTY_NINE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWENTY_NINE]));
                dto.setAttachedDate(str[NumericConstants.THIRTY] == null ? null : (Date) (str[NumericConstants.THIRTY]));
                String basePriceType = dto.getBasePriceType();
                if (basePriceType != null && !Constants.SELECT_ONE.equals(basePriceType) && !Constants.NULL.equals(basePriceType) && !Constants.ZEROSTRING.equals(basePriceType) && !StringUtils.EMPTY.equals(basePriceType)) {
                        switch (basePriceType) {
                        case Constants.MANUAL_LABLE_NAME:
                            dto.setBaselineWAC(str[NumericConstants.THIRTY_FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_FOUR]));
                            break;
                        case Constants.DATE_LABLE_NAME:
                            dto.setBaselineWAC(str[NumericConstants.THIRTY_FIVE] == null ? StringUtils.EMPTY : (Date) (str[NumericConstants.THIRTY_FIVE]));
                            dto.setBaselineWAC((Date) (str[NumericConstants.THIRTY_FIVE]));
                            break;
                        case Constants.PRICE_TYPE_LABEL:
                            dto.setBaselineWAC(str[NumericConstants.THIRTY_SIX] != null && !Constants.ZEROSTRING.equals(String.valueOf(str[NumericConstants.THIRTY_SIX])) && !Constants.NULL.equals(str[NumericConstants.THIRTY_SIX]) ? getDescription(Integer.parseInt(str[NumericConstants.THIRTY_SIX].toString())) : StringUtils.EMPTY);
                            break;
                        default:
                            break;
                    }
                }
                 
                finalResult.add(dto);
            }

        } catch (SystemException e) {
             LOGGER.error("",e);
        }
        return finalResult;

    }
    
      public static String getDescription(int code) {
        try {
            HelperTable table = HelperTableLocalServiceUtil.getHelperTable(code);
            return table.getDescription();
        } catch (PortalException | SystemException ex) {
            LOGGER.error("",ex);
            return null;
        }
    }

    public List<ComponentInfoDTO> getCustomizedRSComponentInfo(final List<Object[]> list) {
        List<ComponentInfoDTO> finalResult = new ArrayList<>();
        for (Object[] str : list) {
            ComponentInfoDTO dto = new ComponentInfoDTO();
            dto.setItemNo(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setItemName(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setBrand(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setStatus(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setStartDate(str[NumericConstants.FOUR] == null ? null : (Date) (str[NumericConstants.FOUR]));
            dto.setEndDate(str[NumericConstants.FIVE] == null ? null : (Date) (str[NumericConstants.FIVE]));
            dto.setFormulaType(str[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
            dto.setFormulaId(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
            dto.setFormulaName(str[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
            dto.setRebatePlanId(str[NumericConstants.NINE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.NINE]));
            dto.setRebatePlanName(str[NumericConstants.TEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TEN]));
            dto.setRebateAmount(str[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ELEVEN]));
            dto.setBundleNo(str[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWELVE]));
            dto.setAttachedDate(str[NumericConstants.THIRTEEN] == null ? null : (Date) (str[NumericConstants.THIRTEEN]));
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
                input.add(Constants.PERCENT);
            }
            if (binderDto.getRsProgramType_DTO() != null) {
                input.add(binderDto.getRsProgramType_DTO().getId());
            } else {
                input.add(Constants.PERCENT);
            }
            if (binderDto.getRsCategory_DTO() != null) {
                input.add(binderDto.getRsCategory_DTO().getId());
            } else {
                input.add(Constants.PERCENT);
            }
            if (binderDto.getPaymentFrequency_DTO() != null) {
                input.add(binderDto.getPaymentFrequency_DTO().getId());
            } else {
                input.add(Constants.PERCENT);
            }
            if (binderDto.getRebatePlanLevel_DTO() != null) {
                input.add(binderDto.getRebatePlanLevel_DTO().getId());
            } else {
                input.add(Constants.PERCENT);
            }
        }
        StringBuilder sql = AbstractFilter.getInstance().getComponentfilterQueryGenerator(selection.getComponent(), selection.getFilters());
        if (sql != null) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDto.getIsCount()) {
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
        input.add(systemid);
        List<Object[]> list = ItemQueries.getItemData(input, componentFlag, null);
        for (Object[] str : list) {
            dto.setComponenId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setComponenNumber(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setComponenName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setComponentStatus(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setComponentType(str[NumericConstants.FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
            dto.setComponentStartDate(str[NumericConstants.FIVE] == null ? null : (Date) str[NumericConstants.FIVE]);
            dto.setComponentEndDate(str[NumericConstants.SIX] == null ? null : (Date) str[NumericConstants.SIX]);
            if (componentFlag.equals("RS text")) {
                dto.setRsType_Value((str[NumericConstants.FOUR] == null || String.valueOf(str[NumericConstants.ELEVEN]).equals(FrequencyConstants.SELECT_ONE.getConstant())) 
                        ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
                dto.setRebateFrequency_Value((str[NumericConstants.SEVEN] == null || String.valueOf(str[NumericConstants.ELEVEN]).equals(FrequencyConstants.SELECT_ONE.getConstant())) 
                        ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
                dto.setRsProgramType_Value((str[NumericConstants.EIGHT] == null || String.valueOf(str[NumericConstants.ELEVEN]).equals(FrequencyConstants.SELECT_ONE.getConstant())) ? 
                        StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
                dto.setRsCategory_Value((str[NumericConstants.NINE] == null || String.valueOf(str[NumericConstants.ELEVEN]).equals(FrequencyConstants.SELECT_ONE.getConstant())) 
                        ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.NINE]));
                dto.setPaymentFrequency_Value((str[NumericConstants.TEN] == null || String.valueOf(str[NumericConstants.ELEVEN]).equals(FrequencyConstants.SELECT_ONE.getConstant())) ? 
                        StringUtils.EMPTY : String.valueOf(str[NumericConstants.TEN]));
                dto.setRebatePlanLevel_Value((str[NumericConstants.ELEVEN] == null || String.valueOf(str[NumericConstants.ELEVEN]).equals(FrequencyConstants.SELECT_ONE.getConstant())) ? 
                        StringUtils.EMPTY : String.valueOf(str[NumericConstants.ELEVEN]));
            }
        }
        return dto;
    }

    public int getFormulaIdCount(FormulaDTO binderDto, SelectionDTO selection) {
        List<Object[]> list = ItemQueries.getItemData(getFormulaIdInput(binderDto), selection.getCountQueryName(), null);
        Object obj = list.get(0);
        int count = obj == null ? 0 : (Integer) obj;
        return count;
    }

    public List<FormulaDTO> getFormulaIdRecords(FormulaDTO binderDto, SelectionDTO selection) {
        List<FormulaDTO> finalResult = new ArrayList<>();
        List<Object[]> list = ItemQueries.getItemData(getFormulaIdInput(binderDto), selection.getDataQueryName(), null);
        for (Object[] str : list) {
            FormulaDTO dto = new FormulaDTO();
            dto.setFormulaId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setFormulaNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setFormulaName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setFormulaSid(str[NumericConstants.THREE] == null ? (Integer) 0 : (Integer) (str[NumericConstants.THREE]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    private List getFormulaIdInput(FormulaDTO binderDto) {
        List<Object> input = new ArrayList();
        if (binderDto.getFormulaId() != null && !binderDto.getFormulaId().isEmpty()) {
            input.add(binderDto.getFormulaId().replace('*', '%'));
        } else {
            input.add("%");
        }
        if (binderDto.getFormulaNo() != null && !binderDto.getFormulaNo().isEmpty()) {
            input.add(binderDto.getFormulaNo().replace('*', '%'));
        } else {
            input.add("%");
        }
        if (binderDto.getFormulaName() != null && !binderDto.getFormulaName().isEmpty()) {
            input.add(binderDto.getFormulaName().replace('*', '%'));
        } else {
            input.add("%");
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    public void LazyTableLoadDdlb(ComboBox comboBox, String loadItemStatusCount, String loadItemStatus) {
        final List inputList = new ArrayList();
        inputList.add(loadItemStatusCount);
        inputList.add(loadItemStatus);
        LazyContainer containerData = new LazyContainer(HelperDTO.class, new LoadDdlbDAO(inputList, true), new DdlbCriteria());
        comboBox.setPageLength(NumericConstants.SEVEN);
        comboBox.setContainerDataSource(containerData);
        comboBox.setNullSelectionItemId(ddlbDefaultValue);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setItemCaptionPropertyId(DESCRIPTION);
    }

    public Boolean getEditedItemDetails(final AbstractContractSearchDTO compDTO, final SelectionDTO selection) {
        selection.setIsContractUpdate(false);
        List input = getEditedItemInput(compDTO, selection);
        String queryname = null;
        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {

            queryname = "Abstract Data update For Add";
            if (compDTO.getCaseNo().equals(NumericConstants.SEVENTEEN)) {
                queryname = "Abstract Data update CheckRecord For Add";
            }
        } else {
            queryname = "Abstract Data update";
            if (compDTO.getCaseNo().equals(NumericConstants.SEVENTEEN)) {
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
            if (compDTO.getCaseNo().equals(NumericConstants.SEVENTEEN)) {
                selection.setIsContractUpdate(false);
                queryname = "Abstract Data update CheckRecord For Add";
            }
            input = getEditedItemInput(compDTO, selection);
        } else {
            queryname = "Abstract FieldFactory Values Update";
            selection.setIsContractUpdate(true);
            if (compDTO.getCaseNo().equals(NumericConstants.SEVENTEEN)) {
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
            case NumericConstants.ONE:
                input.add(compDTO.getItemStartDate() != null ? setQuotes(formatter.format(compDTO.getItemStartDate())) : null);
                break;
            case NumericConstants.TWO:
                input.add(compDTO.getItemEndDate() != null ? setQuotes(formatter.format(compDTO.getItemEndDate())) : null);
                break;
            case NumericConstants.THREE:
                input.add(compDTO.getPriceToleranceType() != null ? compDTO.getPriceToleranceType().getId() : null);
                break;
            case NumericConstants.FOUR:
                input.add(compDTO.getCpStartDate() != null ? setQuotes(formatter.format(compDTO.getCpStartDate())) : null);
                break;
            case NumericConstants.FIVE:
                input.add(compDTO.getCpEndDate() != null ? setQuotes(formatter.format(compDTO.getCpEndDate())) : null);
                break;
            case NumericConstants.SIX:
                input.add(compDTO.getStatus() != null ? compDTO.getStatus().getId() : null);
                break;
            case NumericConstants.SEVEN:
                input.add(compDTO.getPriceTolerance() != null && !compDTO.getPriceTolerance().isEmpty() ? compDTO.getPriceTolerance() : null);
                break;
            case NumericConstants.EIGHT:
                input.add(compDTO.getPriceProtectionStartDate() != null ? setQuotes(formatter.format(compDTO.getPriceProtectionStartDate())) : null);
                break;
            case NumericConstants.NINE:
                input.add(compDTO.getPriceProtectionEndDate() != null ? setQuotes(formatter.format(compDTO.getPriceProtectionEndDate())) : null);
                break;
            case NumericConstants.TEN:
                input.add(compDTO.getPriceToleranceType() != null ? compDTO.getPriceToleranceType().getId() : null);
                break;
            case NumericConstants.ELEVEN:
                input.add(compDTO.getPriceToleranceInterval() != null ? compDTO.getPriceToleranceInterval().getId() : null);
                break;
            case NumericConstants.TWELVE:
                input.add(compDTO.getPriceToleranceFrequency() != null ? compDTO.getPriceToleranceFrequency().getId() : null);
                break;
            case NumericConstants.THIRTEEN:
                input.add(compDTO.getBasePrice() != null && !compDTO.getBasePrice().isEmpty() ? compDTO.getBasePrice() : null);
                break;
            case NumericConstants.FOURTEEN:
                input.add(compDTO.getStartDate() != null ? setQuotes(formatter.format(compDTO.getStartDate())) : null);
                break;
            case NumericConstants.FIFTEEN:
                input.add(compDTO.getPrice() != null && !compDTO.getPrice().isEmpty() ? compDTO.getPrice() : null);
                break;
            case NumericConstants.SIXTEEN:
                input.add(compDTO.getEndDate() != null ? setQuotes(formatter.format(compDTO.getEndDate())) : null);
                break;
            case NumericConstants.SEVENTEEN:
                input.add(compDTO.getCheckRecord() ? 1 : 0);
                break;
            case NumericConstants.EIGHTEEN:
                input.add(compDTO.getContractPrice() != null && !compDTO.getContractPrice().isEmpty() ? compDTO.getContractPrice() : null);
                break;
            case NumericConstants.NINETEEN:
                input.add(compDTO.getRSStartDate() != null ? setQuotes(formatter.format(compDTO.getRSStartDate())) : null);
                break;
            case NumericConstants.TWENTY:
                input.add(compDTO.getRSEndDate() != null ? setQuotes(formatter.format(compDTO.getRSEndDate())) : null);
                break;
            case NumericConstants.TWENTY_ONE:
                input.add(compDTO.getRebateAmount() != null && !compDTO.getRebateAmount().isEmpty() ? compDTO.getRebateAmount() : null);
                break;
            case NumericConstants.TWENTY_TWO:
                input.add(compDTO.getFormulaMethodId() != null && !compDTO.getFormulaMethodId().isEmpty() ? compDTO.getFormulaMethodId() : null);
                break;
            case NumericConstants.TWENTY_THREE:
                input.add(compDTO.getTempSid() != null && compDTO.getTempSid() != 0 ? compDTO.getTempSid() : null);
                break;
            case NumericConstants.TWENTY_FOUR:
                input.add(compDTO.getTempSid() != null && compDTO.getTempSid() != 0 ? compDTO.getTempSid() : null);
                break;
            case NumericConstants.TWENTY_FIVE:
                input.add(compDTO.getNep() != null && !compDTO.getNep().isEmpty() ? compDTO.getNep() : null);
                break;
            case NumericConstants.TWENTY_SIX:
                input.add(compDTO.getPriceProtectionStatus() != null ? compDTO.getPriceProtectionStatus().getId() : null);
                break;
            case NumericConstants.TWENTY_SEVEN://NEP_FORMULA
                 input.add(compDTO.getNepFormula()!= null && !compDTO.getNepFormula().isEmpty() ? Constants.SINGLE_QUOTES + compDTO.getNepFormula() + Constants.SINGLE_QUOTES : null); 
                break;
            case NumericConstants.TWENTY_EIGHT:
                input.add(compDTO.getMaxIncrementalChange() != null && !compDTO.getMaxIncrementalChange().isEmpty() ? compDTO.getMaxIncrementalChange() : null);
                break;
            case NumericConstants.TWENTY_NINE:
                input.add(compDTO.getResetEligible() != null ? compDTO.getResetEligible().getId() : null);
                break;
            case NumericConstants.THIRTY:
                input.add(compDTO.getResetType() != null ? compDTO.getResetType().getId() : null);
                break;
            case NumericConstants.THIRTY_ONE:
                input.add(compDTO.getResetDate() != null ? setQuotes(formatter.format(compDTO.getResetDate())) : null);
                break;
            case NumericConstants.THIRTY_TWO:
                input.add(compDTO.getResetInterval() != null ? compDTO.getResetInterval().getId() : null);
                break;
            case NumericConstants.THIRTY_THREE:
                input.add(compDTO.getResetFrequency() != null ? compDTO.getResetFrequency().getId() : null);
                break;
            case NumericConstants.THIRTY_FOUR:
                input.add(compDTO.getNetPriceType() != null ? compDTO.getNetPriceType().getId() : null);
                break;
            case NumericConstants.THIRTY_FIVE: // NET_PRICE_TYPE_FORMULA 
                 input.add(compDTO.getNetPriceTypeFormula()!= null && !compDTO.getNetPriceTypeFormula().isEmpty() ? Constants.SINGLE_QUOTES + compDTO.getNetPriceTypeFormula() + Constants.SINGLE_QUOTES : null);  
                break;
            case NumericConstants.THIRTY_SIX:
                input.add(compDTO.getResetPriceType() != 0 ? compDTO.getResetPriceType() : null);
                break;
            case NumericConstants.THIRTY_SEVEN:
                input.add(compDTO.getNetResetPriceType() != null ? compDTO.getNetResetPriceType().getId() : null);
                break;
            case NumericConstants.THIRTY_EIGHT: // NET_RESET_PRICE_FORMULA_ID
                input.add(compDTO.getNetResetPriceFormula() != null && !compDTO.getNetResetPriceFormula().isEmpty() ? Constants.SINGLE_QUOTES + compDTO.getNetResetPriceFormula() + Constants.SINGLE_QUOTES : null); 
                break;
            case NumericConstants.THIRTY_NINE:
                input.add(compDTO.getBasePriceType() != null ? compDTO.getBasePriceType().getId() : null);
                break;
            case NumericConstants.FORTY:
                  input.add(compDTO.getSubsequentPeriodPriceType() != 0 ? compDTO.getSubsequentPeriodPriceType() : null);
                break;
            case NumericConstants.FORTY_ONE:
                input.add(compDTO.getNetSubsequentPeriodPrice() != null ? compDTO.getNetSubsequentPeriodPrice().getId() : null);
                break;
            case NumericConstants.FORTY_TWO: // NET_SUBSEQUENT_PRICE_FORMULA_ID
                 input.add(compDTO.getNetSubsequentPeriodPriceFormula()!= null && !compDTO.getNetSubsequentPeriodPriceFormula().isEmpty() ? Constants.SINGLE_QUOTES + compDTO.getNetSubsequentPeriodPriceFormula() + Constants.SINGLE_QUOTES : null); 
                break;
            case NumericConstants.FORTY_THREE: // NET_BASELINE_WAC_FORMULA_ID
                 input.add(compDTO.getNetBaselineWACFormula()!= null && !compDTO.getNetBaselineWACFormula().isEmpty() ? Constants.SINGLE_QUOTES + compDTO.getNetBaselineWACFormula() + Constants.SINGLE_QUOTES : null); 
                break;
            case NumericConstants.FORTY_FOUR:
                input.add(compDTO.getBaselineNetWAC() != null ? compDTO.getBaselineNetWAC().getId() : null);
                break;
            case NumericConstants.FORTY_FIVE:
                input.add(compDTO.getPriceType() != 0 ? compDTO.getPriceType() : null);
                break;
            case NumericConstants.FORTY_SIX:
                 input.add(compDTO.getMeasurementPrice() != 0 ? compDTO.getMeasurementPrice() : null);
                break;
            case NumericConstants.FORTY_SEVEN:
                input.add(compDTO.getBaseLineWacManual()!= null && !compDTO.getBaseLineWacManual().isEmpty() ? compDTO.getBaseLineWacManual() : null);
                break;

            case NumericConstants.FORTY_EIGHT:
                input.add(compDTO.getBaseLineWacDate()!= null ? setQuotes(formatter.format(compDTO.getBaseLineWacDate())) : null);
                break;

            case NumericConstants.FORTY_NINE:
                input.add(compDTO.getBaseLineWacPriceType()!= 0 ? compDTO.getBaseLineWacPriceType() : null);
                break;
            default:
                break;
        }

        if (!selection.isIsContractUpdate()) { // Condition check for identification of - For check record =1 update values
            input.add(compDTO.getContractMasterSid());
            input.add(compDTO.getCfpContractSid());
            input.add(compDTO.getIfpConteractSid());
            input.add(compDTO.getPsContractSid());
            input.add(compDTO.getRsContractSid());
            input.add(selection.getSessionId());
            input.add(compDTO.getProjectionId());
            input.add(compDTO.getItemMasterSid());

            if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
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
        List<Object> input = new ArrayList(NumericConstants.FIFTEEN);
        input.add(dto.getColumnName());
        switch (dto.getCaseNo()) {
            case NumericConstants.ONE:
                input.add(dto.getItemStartDate() != null ? formatter.format(dto.getItemStartDate()) : null);
                break;
            case NumericConstants.TWO:
                input.add(dto.getItemEndDate() != null ? formatter.format(dto.getItemEndDate()) : null);
                break;
            case NumericConstants.THREE:
                input.add(dto.getPriceToleranceType() != null ? dto.getPriceToleranceType() : null);
                break;
            case NumericConstants.FOUR:
                input.add(dto.getCpStartDate() != null ? formatter.format(dto.getCpStartDate()) : null);
                break;
            case NumericConstants.FIVE:
                input.add(dto.getCpEndDate() != null ? formatter.format(dto.getCpEndDate()) : null);
                break;
            case NumericConstants.SIX:
                input.add(dto.getStatus() != null ? dto.getStatus().getId() : null);
                break;
            case NumericConstants.SEVEN:
                input.add(dto.getPriceTolerance() != null && !dto.getPriceTolerance().isEmpty() ? dto.getPriceTolerance() : null);
                break;
            case NumericConstants.EIGHT:
                input.add(dto.getPriceProtectionStartDate() != null ? formatter.format(dto.getPriceProtectionStartDate()) : null);
                break;
            case NumericConstants.NINE:
                input.add(dto.getPriceProtectionEndDate() != null ? formatter.format(dto.getPriceProtectionEndDate()) : null);
                break;
            case NumericConstants.TEN:
                input.add(dto.getPriceToleranceType() != null ? dto.getPriceToleranceType().getId() : null);
                break;
            case NumericConstants.ELEVEN:
                input.add(dto.getPriceToleranceInterval() != null ? dto.getPriceToleranceInterval() : null);
                break;
            case NumericConstants.TWELVE:
                input.add(dto.getPriceToleranceFrequency() != null ? dto.getPriceToleranceFrequency() : null);
                break;
            case NumericConstants.THIRTEEN:
                input.add(dto.getBasePrice() != null && !dto.getBasePrice().isEmpty() ? dto.getBasePrice() : null);
                break;
            case NumericConstants.FOURTEEN:
                input.add(dto.getStartDate() != null ? formatter.format(dto.getStartDate()) : null);
                break;
            case NumericConstants.FIFTEEN:
                input.add(dto.getPrice() != null ? dto.getPrice() : null);
                break;
            case NumericConstants.SIXTEEN:
                input.add(dto.getEndDate() != null ? formatter.format(dto.getEndDate()) : null);
                break;
            case NumericConstants.SEVENTEEN:
                input.add(dto.getCheckRecord() != null ? dto.getCheckRecord() : null);
                break;
            case NumericConstants.EIGHTEEN:
                input.add(dto.getRSStartDate() != null ? formatter.format(dto.getRSStartDate()) : null);
                break;
            case NumericConstants.NINETEEN:
                input.add(dto.getRSEndDate() != null ? formatter.format(dto.getRSEndDate()) : null);
                break;
            case NumericConstants.TWENTY:
                input.add(dto.getRebateAmount() != null ? dto.getRebateAmount() : null);
                break;
            case NumericConstants.TWENTY_ONE:
                input.add(dto.getFormulaMethodId() != null ? dto.getFormulaMethodId() : null);
                break;
            case NumericConstants.TWENTY_FIVE:
                input.add(dto.getNep() != null && !dto.getNep().isEmpty() ?  dto.getNep() : null);
                break;
            case NumericConstants.TWENTY_SIX:
                input.add(dto.getPriceProtectionStatus() != null ? dto.getPriceProtectionStatus().getId() : null);
                break;
            case NumericConstants.TWENTY_SEVEN://NEP_FORMULA
                input.add(dto.getNepFormula() != null && !dto.getNepFormula().isEmpty() ?  Constants.SINGLE_QUOTES + dto.getNepFormula()+ Constants.SINGLE_QUOTES : null);
                break;
            case NumericConstants.TWENTY_EIGHT:
                input.add(dto.getMaxIncrementalChange() != null && !dto.getMaxIncrementalChange().isEmpty() ? dto.getMaxIncrementalChange() : null);
                break;
            case NumericConstants.TWENTY_NINE:
                input.add(dto.getResetEligible() != null ? dto.getResetEligible().getId() : null);
                break;
            case NumericConstants.THIRTY:
                input.add(dto.getResetType() != null ? dto.getResetType().getId() : null);
                break;
            case NumericConstants.THIRTY_ONE:
                input.add(dto.getResetDate() != null ? setQuotes(formatter.format(dto.getResetDate())) : null);
                break;
            case NumericConstants.THIRTY_TWO:
                input.add(dto.getResetInterval() != null ? dto.getResetInterval().getId() : null);
                break;
            case NumericConstants.THIRTY_THREE:
                input.add(dto.getResetFrequency() != null ? dto.getResetFrequency().getId() : null);
                break;
            case NumericConstants.THIRTY_FOUR:
                input.add(dto.getNetPriceType() != null ? dto.getNetPriceType().getId() : null);
                break;
            case NumericConstants.THIRTY_FIVE: // NET_PRICE_TYPE_FORMULA
                input.add(dto.getNetPriceTypeFormula() != null && !dto.getNetPriceTypeFormula().isEmpty() ? Constants.SINGLE_QUOTES + dto.getNetPriceTypeFormula() + Constants.SINGLE_QUOTES : null); 
                break;
            case NumericConstants.THIRTY_SIX:
                input.add(dto.getResetPriceType() != null ? dto.getResetPriceType().getId() : null);
                break;
            case NumericConstants.THIRTY_SEVEN:
                input.add(dto.getNetResetPriceType() != null ? dto.getNetResetPriceType().getId() : null);
                break;
            case NumericConstants.THIRTY_EIGHT: // NET_RESET_PRICE_FORMULA_ID
                input.add(dto.getNetResetPriceFormula() != null && !dto.getNetResetPriceFormula().isEmpty() ? Constants.SINGLE_QUOTES + dto.getNetResetPriceFormula() + Constants.SINGLE_QUOTES : null); 
                break;
            case NumericConstants.THIRTY_NINE:
                input.add(dto.getBasePriceType() != null ? dto.getBasePriceType().getId() : null);
                break;
            case NumericConstants.FORTY:
                input.add(dto.getSubsequentPeriodPriceType() != null ? dto.getSubsequentPeriodPriceType().getId() : null);
                break;
            case NumericConstants.FORTY_ONE:
                input.add(dto.getNetSubsequentPeriodPrice() != null ? dto.getNetSubsequentPeriodPrice().getId() : null);
                break;
            case NumericConstants.FORTY_TWO: // NET_SUBSEQUENT_PRICE_FORMULA_ID
                input.add(dto.getNetSubsequentPeriodPriceFormula() != null && !dto.getNetSubsequentPeriodPriceFormula().isEmpty() ? Constants.SINGLE_QUOTES + dto.getNetSubsequentPeriodPriceFormula() + Constants.SINGLE_QUOTES : null); 
                break;
            case NumericConstants.FORTY_THREE: // NET_BASELINE_WAC_FORMULA_ID
                input.add(dto.getNetBaselineWACFormula() != null && !dto.getNetBaselineWACFormula().isEmpty() ? Constants.SINGLE_QUOTES + dto.getNetBaselineWACFormula() + Constants.SINGLE_QUOTES : null);  
                break;
            case NumericConstants.FORTY_FOUR:
                input.add(dto.getBaselineNetWAC() != null ? dto.getBaselineNetWAC().getId() : null);
                break;
            case NumericConstants.FORTY_FIVE:
                input.add(dto.getPriceType() != null ? dto.getPriceType().getId() : null);
                break;
            case NumericConstants.FORTY_SIX:
                input.add(dto.getMeasurementPrice() != null ? dto.getMeasurementPrice().getId() : null);
                break;
            case NumericConstants.FORTY_SEVEN:
                input.add(dto.getBaseLineWacManual() != null && !dto.getBaseLineWacManual().isEmpty() ? dto.getBaseLineWacManual() : null);
                break;

            case NumericConstants.FORTY_EIGHT:
                input.add(dto.getBaseLineWacDate() != null ? setQuotes(formatter.format(dto.getBaseLineWacDate())) : null);
                break;
            case NumericConstants.FORTY_NINE:
                input.add(dto.getBaseLineWacPriceType() != 0 ? dto.getBaseLineWacPriceType() : null);
                break;
            default:
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

    public Boolean massUpdateItemDetails(final List input) {
        Boolean isUpdated = ItemQueries.itemUpdate(input, "Abstract Mass update");
        return isUpdated;
    }

    private List getInput(SelectionDTO selection, AddItemTableDTO binderDto) {
        List input = new ArrayList(NumericConstants.FIFTEEN);
        input.add(selection.getSessionId());
        input.add(selection.getButtonMode());
        if (binderDto.getContractNoSid() != null && !binderDto.getContractNoSid().isEmpty()) {
            input.add(binderDto.getContractNoSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getContractNameSid() != null && !binderDto.getContractNameSid().isEmpty()) {
            input.add(binderDto.getContractNameSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getContractHolderSid() != null && !binderDto.getContractHolderSid().isEmpty()) {
            input.add(binderDto.getContractHolderSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( cm.START_DATE >= '" + formatter.format(binderDto.getItemStartDate()) + "')");
        } else {
            input.add(" ");
        }

        if (binderDto.getEndDate() != null) {
            input.add(" AND ( CM.END_DATE <= '" + formatter.format(binderDto.getEndDate()) + "')");
        } else {
            input.add(" ");
        }

        if (binderDto.getMarketTypeDto() != null) {
            input.add(binderDto.getMarketTypeDto().getId());
        } else {
            input.add(Constants.PERCENT);
        }

        if (binderDto.getCfpSid() != null && !binderDto.getCfpSid().isEmpty()) {
            input.add(binderDto.getCfpSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getCustomerSid() != null && !binderDto.getCustomerSid().isEmpty()) {
            input.add(binderDto.getCustomerSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getCustomerSid() != null && !binderDto.getCustomerSid().isEmpty()) {
            input.add(binderDto.getCustomerSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }

        if (binderDto.getIfpSid() != null && !binderDto.getIfpSid().isEmpty()) {
            input.add(binderDto.getIfpSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getPsSid() != null && !binderDto.getPsSid().isEmpty()) {
            input.add(binderDto.getPsSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getRsSid() != null && !binderDto.getRsSid().isEmpty()) {
            input.add(binderDto.getRsSid().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        StringBuilder sql = AbstractFilter.getInstance().contractfilterQueryGenerator(selection.getFilters());
        if (!(sql == null)) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        return input;
    }

    public List<AbstractContractSearchDTO> getContractResults(SelectionDTO selection,int start, int offset, List input) {
        input.add(start);
        input.add(offset);
        List<Object[]> list = ItemQueries.getItemData(input, selection.getDataQueryName(), null);
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            return setContractDetailsData(list, selection, input.get(NumericConstants.TWO).toString());
        } else {
            return setContractDetailsData(list, selection, input.get(1).toString());
        }
    }

    public int getContractCount(SelectionDTO selection, List input) {
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
    public int getLookUpSearchCount(ComponentLookUpDTO binderDto, SelectionDTO selection) {
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
        final List input = new ArrayList(NumericConstants.FIFTEEN);
        if (binderDto.getComponentId() != null && !binderDto.getComponentId().isEmpty()) {
            input.add(binderDto.getComponentId().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentName() != null && !binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }

        if (binderDto.getCategory() != null && !binderDto.getCategory().isEmpty()) {
            input.add(binderDto.getCategory().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( CC.CFP_START_DATE >= '" + formatter.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }

        if (binderDto.getComponentNo() != null && !binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentType() != null && !binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }

        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( CC.CFP_END_DATE <= '" + formatter.format(binderDto.getEndDate()) + "')");
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
        final List input = new ArrayList(NumericConstants.FIFTEEN);
        if (binderDto.getComponentName() != null && !binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentNo() != null && !binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentType() != null && !binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getCategory() != null && !binderDto.getCategory().isEmpty()) {
            input.add(binderDto.getCategory().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( IFP_C.IFP_START_DATE >= '" + formatter.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( IFP_C.IFP_END_DATE <= '" + formatter.format(binderDto.getEndDate()) + "')");
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
        final List input = new ArrayList(NumericConstants.FIFTEEN);
        if (binderDto.getComponentName() != null && !binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentNo() != null && !binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentType() != null && !binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getCategory() != null && !binderDto.getCategory().isEmpty()) {
            input.add(binderDto.getCategory().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( PS_C.PS_START_DATE >= '" + formatter.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( PS_C.PS_END_DATE <= '" + formatter.format(binderDto.getEndDate()) + "')");
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
        final List input = new ArrayList(NumericConstants.FIFTEEN);
        if (binderDto.getComponentId() != null && !binderDto.getComponentId().isEmpty()) {
            input.add(binderDto.getComponentId().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentNo() != null && !binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentName() != null && !binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getRsProgramType_DTO() != null) {
            input.add(binderDto.getRsProgramType_DTO().getId());
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentType() != null && !binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace('*', '%'));
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getComponentCategory_DTO() != null) {
            input.add(binderDto.getComponentCategory_DTO().getId());
        } else {
            input.add(Constants.PERCENT);
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( RS_C.RS_START_DATE  >= '" + formatter.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( RS_C.RS_END_DATE <= '" + formatter.format(binderDto.getEndDate()) + "')");
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
        List<ComponentLookUpDTO> resultList = new ArrayList<>();
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
            dto.setComponentName(ObjNullCheck(str[NumericConstants.TWO]) ? StringUtils.EMPTY : (String) str[NumericConstants.TWO]);
            dto.setComponentType(ObjNullCheck(str[NumericConstants.THREE]) ? StringUtils.EMPTY : (String) str[NumericConstants.THREE]);
            dto.setCategory(ObjNullCheck(str[NumericConstants.FOUR]) ? StringUtils.EMPTY : (String) str[NumericConstants.FOUR]);
            dto.setDesignation(ObjNullCheck(str[NumericConstants.FIVE]) ? StringUtils.EMPTY : (String) str[NumericConstants.FIVE]);
            dto.setPlanId(ObjNullCheck(str[NumericConstants.SIX]) ? StringUtils.EMPTY : (String) str[NumericConstants.SIX]);
            dto.setPlanName(ObjNullCheck(str[NumericConstants.SEVEN]) ? StringUtils.EMPTY : (String) str[NumericConstants.SEVEN]);
            dto.setComponentStatus(ObjNullCheck(str[NumericConstants.EIGHT]) ? StringUtils.EMPTY : (String) str[NumericConstants.EIGHT]);
            dto.setTradeClass(ObjNullCheck(str[NumericConstants.NINE]) ? StringUtils.EMPTY : (String) str[NumericConstants.NINE]);
            dto.setStartDate(ObjNullCheck(str[NumericConstants.TEN]) ? null : (Date) str[NumericConstants.TEN]);
            dto.setEndDate(ObjNullCheck(str[NumericConstants.ELEVEN]) ? null : (Date) str[NumericConstants.ELEVEN]);
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
            dto.setComponentType(ObjNullCheck(str[NumericConstants.TWO]) ? StringUtils.EMPTY : (String) str[NumericConstants.TWO]);
            dto.setCategory(ObjNullCheck(str[NumericConstants.THREE]) ? StringUtils.EMPTY : (String) str[NumericConstants.THREE]);
            dto.setDesignation(ObjNullCheck(str[NumericConstants.FOUR]) ? StringUtils.EMPTY : (String) str[NumericConstants.FOUR]);
            dto.setPlanId(ObjNullCheck(str[NumericConstants.FIVE]) ? StringUtils.EMPTY : (String) str[NumericConstants.FIVE]);
            dto.setPlanName(ObjNullCheck(str[NumericConstants.SIX]) ? StringUtils.EMPTY : (String) str[NumericConstants.SIX]);
            dto.setComponentStatus(ObjNullCheck(str[NumericConstants.SEVEN]) ? StringUtils.EMPTY : (String) str[NumericConstants.SEVEN]);
            dto.setStartDate(ObjNullCheck(str[NumericConstants.EIGHT]) ? null : (Date) str[NumericConstants.EIGHT]);
            dto.setEndDate(ObjNullCheck(str[NumericConstants.NINE]) ? null : (Date) str[NumericConstants.NINE]);
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
            dto.setComponentType(ObjNullCheck(str[NumericConstants.TWO]) ? StringUtils.EMPTY : (String) str[NumericConstants.TWO]);
            dto.setCategory(ObjNullCheck(str[NumericConstants.THREE]) ? StringUtils.EMPTY : (String) str[NumericConstants.THREE]);
            dto.setTradeClass(ObjNullCheck(str[NumericConstants.FOUR]) ? StringUtils.EMPTY : (String) str[NumericConstants.FOUR]);
            dto.setDesignation(ObjNullCheck(str[NumericConstants.FIVE]) ? StringUtils.EMPTY : (String) str[NumericConstants.FIVE]);
            dto.setPlanId(ObjNullCheck(str[NumericConstants.SIX]) ? StringUtils.EMPTY : (String) str[NumericConstants.SIX]);
            dto.setPlanName(ObjNullCheck(str[NumericConstants.SEVEN]) ? StringUtils.EMPTY : (String) str[NumericConstants.SEVEN]);
            dto.setComponentStatus(ObjNullCheck(str[NumericConstants.EIGHT]) ? StringUtils.EMPTY : (String) str[NumericConstants.EIGHT]);
            dto.setStartDate(ObjNullCheck(str[NumericConstants.NINE]) ? null : (Date) str[NumericConstants.NINE]);
            dto.setEndDate(ObjNullCheck(str[NumericConstants.TEN]) ? null : (Date) str[NumericConstants.TEN]);

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
            dto.setComponentName(ObjNullCheck(str[NumericConstants.TWO]) ? StringUtils.EMPTY : (String) str[NumericConstants.TWO]);
            dto.setComponentType(ObjNullCheck(str[NumericConstants.THREE]) ? StringUtils.EMPTY : (String) str[NumericConstants.THREE]);
            dto.setRsProgramType(ObjNullCheck(str[NumericConstants.FOUR]) ? StringUtils.EMPTY : (String) str[NumericConstants.FOUR]);
            dto.setCategory(ObjNullCheck(str[NumericConstants.FIVE]) ? StringUtils.EMPTY : (String) str[NumericConstants.FIVE]);
            dto.setTradeClass(ObjNullCheck(str[NumericConstants.SIX]) ? StringUtils.EMPTY : (String) str[NumericConstants.SIX]);
            dto.setDesignation(ObjNullCheck(str[NumericConstants.SEVEN]) ? StringUtils.EMPTY : (String) str[NumericConstants.SEVEN]);
            dto.setPlanId(ObjNullCheck(str[NumericConstants.EIGHT]) ? StringUtils.EMPTY : (String) str[NumericConstants.EIGHT]);
            dto.setPlanName(ObjNullCheck(str[NumericConstants.NINE]) ? StringUtils.EMPTY : (String) str[NumericConstants.NINE]);
            dto.setComponentStatus(ObjNullCheck(str[NumericConstants.TEN]) ? StringUtils.EMPTY : (String) str[NumericConstants.TEN]);
            dto.setStartDate(ObjNullCheck(str[NumericConstants.ELEVEN]) ? null : (Date) str[NumericConstants.ELEVEN]);
            dto.setEndDate(ObjNullCheck(str[NumericConstants.TWELVE]) ? null : (Date) str[NumericConstants.TWELVE]);
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
        comboBox.setPageLength(NumericConstants.SEVEN);
        BeanItemContainer<HelperDTO> container = new BeanItemContainer<>(HelperDTO.class);
        comboBox.setContainerDataSource(container);
        if (isFilter) {
            comboBox.setNullSelectionItemId(ddlbShowAllValue);
        } else {
            comboBox.setNullSelectionItemId(ddlbDefaultValue);
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setItemCaptionPropertyId(DESCRIPTION);
        comboBox.addItems(loadDdlbList(columnName, tableName, isFilter, queryName));
    }

    public static List loadDdlbList(String columnName, String tableName, Boolean isFilter, String queryName) {
        String comboboxName = tableName + "-" + columnName;
        if (getDdlbmap().get(comboboxName) == null) {
            List input = new ArrayList();
            input.add(tableName);
            input.add(columnName);
            List<Object[]> list = ItemQueries.getItemData(input, queryName, null);
            List<HelperDTO> resultList = new ArrayList<>();
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
                    dto.setId(str[0] == null ? 0 : Integer.parseInt(str[0].toString()));
                    dto.setDescription(str[1] == null ? Constants.ZEROSTRING : String.valueOf(str[1]));
                    resultList.add(dto);
                }
            }

            getDdlbmap().put(comboboxName, resultList);
            return resultList;
        }

        return getDdlbmap().get(comboboxName);
    }

    public static Object getItemName(List<ItemIndexDto> itemList) {
        List itemIdList = new ArrayList();
        for (ItemIndexDto dto : itemList) {
            itemIdList.add(dto.getItemName());
        }
        return CommonUtils.getListToString(itemIdList);
    }

    public static List<Object[]> callProcedure(String procedureName, Object[] orderedArgs) {
        DataSource datasource = null;
        CallableStatement statement = null;
        ResultSet rs = null;
        List<Object[]> objectList = new ArrayList<>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException ex) {
            LOGGER.error(ex.getMessage());
        }
        if (datasource != null) {
            try (Connection connection = datasource.getConnection()){
            if (connection != null) {
                StringBuilder procedureToCall = new StringBuilder("{call ");
                procedureToCall.append(procedureName);
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall.append('(');
                    }
                    procedureToCall.append("?,");
                    if (i == noOfArgs - 1) {
                        procedureToCall.append(')');
                    }
                }
                procedureToCall.replace(procedureToCall.lastIndexOf(COMMA), procedureToCall.lastIndexOf(COMMA) + 1, StringUtils.EMPTY);
                procedureToCall.append('}');
                statement = connection.prepareCall(procedureToCall.toString());
                for (int i = 0; i < noOfArgs; i++) {
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);

            }
        } catch (Exception ex) {
            LOGGER.error("",ex);
           
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                 LOGGER.error("",e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
           
        }
        }
        return objectList;
    }
        
    private static List<Object[]> convertResultSetToList(ResultSet rs) {
        List<Object[]> objList = new ArrayList<>();

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
             LOGGER.error("",ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                 LOGGER.error("",ex);
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
        List<String> history = new ArrayList<>();
        int endValue = 0;
        String freq = StringUtils.EMPTY;
        if (ANNUALLY.equals(frequency)) {
            endValue = NumericConstants.THREE;
            freq = YEARS.getConstant();
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {

            endValue = NumericConstants.SIX;
            freq = SEMI_ANNUAL.getConstant();
        } else if (QUARTERLY.equals(frequency)) {

            endValue = NumericConstants.TWELVE;
            freq = QUARTERS.getConstant();
        } else if (MONTHLY.getConstant().equals(frequency)) {

            endValue = NumericConstants.THIRTY_SIX;
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
            String listName, boolean isFilter) {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : Constants.SELECT_ONE);
        select.setValidationVisible(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? Constants.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? Constants.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }
    
    public int lookupCountQuery(final FormulaDTO dto, int start, int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount) {
        List<Object[]> count = ItemQueries.getAppData(getViewInput(dto, start, offset, columns, filterSet, isCount), "formulaIdCount", null);
        return getCount(count);
    }

    public List lookupResultsQuery(final FormulaDTO dto, int start, int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount) {
        List<Object[]> searchResultsList = ItemQueries.getAppData(getViewInput(dto, start, offset, columns, filterSet, isCount), "formulaIdRecord", null);
        List<FormulaDTO> resultsList = getCustomizedViewData(searchResultsList);
        return resultsList;
    }

    private List getViewInput(FormulaDTO binderDto, int start, int offset, final List<SortByColumn> columns, final Set<Container.Filter> filterSet, boolean isCount) {
        List input = new ArrayList();
        StringBuilder filterQString = GcmtFilterLogic.getInstance().filterQueryGenerator(filterSet, getQueryMap());

        input.add(binderDto.getFormulaId() != null && !binderDto.getFormulaId().isEmpty() ? binderDto.getFormulaId().replace('*', '%') : Constants.PERCENT);
        input.add(binderDto.getFormulaNo() != null && !binderDto.getFormulaNo().isEmpty() ? binderDto.getFormulaNo().replace('*', '%') : Constants.PERCENT);
        input.add(binderDto.getFormulaName() != null && !binderDto.getFormulaName().isEmpty() ? binderDto.getFormulaName().replace('*', '%') : Constants.PERCENT);
        input.add(binderDto.getNetSalesformulaType() != null ? binderDto.getNetSalesformulaType().getId() : Constants.PERCENT);
        input.add(filterQString != null ? filterQString.toString().replace("where", "AND") : Constants.SPACE);

        if (!isCount) {
            StringBuilder orderBy = GcmtFilterLogic.getInstance().orderByQueryGenerator(columns, getQueryMap());
            input.add(orderBy != null ? orderBy.toString() : Constants.SPACE);
            input.add(start);
            input.add(offset);
        }
        return input;
    }

    public List getCustomizedViewData(List<Object[]> searchResultsList) {
        List<FormulaDTO> finalList = new ArrayList<>();
        for (int i = 0; i < searchResultsList.size(); i++) {
            final FormulaDTO dto = new FormulaDTO();
            final Object[] str = searchResultsList.get(i);
            dto.setFormulaId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setFormulaNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setFormulaName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setFormulaSid(str[NumericConstants.THREE] == null ? (Integer) 0 : (Integer) (str[NumericConstants.THREE]));
            dto.setNetSalesformulaType(str[NumericConstants.FOUR] == null || Constants.NULL.equals(str[NumericConstants.FOUR]) ? new HelperDTO(0, StringUtils.EMPTY) : HelperListUtil.getInstance().getHelperDTObyID(Integer.parseInt(String.valueOf(str[NumericConstants.FOUR]))));
            finalList.add(dto);
        }
        return finalList;
    }

    private Map<String, String> getQueryMap() {
        Map<String, String> searchColumn = new HashMap<>();
        searchColumn.put(StringUtils.EMPTY, "NET_SALES_FORMULA_MASTER_SID");
        searchColumn.put("formulaId", "NET_SALES_FORMULA_ID");
        searchColumn.put("formulaNo", "NET_SALES_FORMULA_NO");
        searchColumn.put("formulaName", "NET_SALES_FORMULA_NAME");
        searchColumn.put("netSalesformulaType", "NET_SALES_FORMULA_TYPE");
        return searchColumn;
    }
    
    public String updateBaseLineWacColumn(String baseLineColumnName, Object baseLineValue, AbstractContractSearchDTO dto, SelectionDTO selection) {
        String operation = ConstantsUtil.TRANSFER.equals(selection.getButtonMode()) ? ConstantsUtil.TRANSFER_CONTRACT : selection.getButtonMode();
        String updateQuery = "UPDATE GCM_GLOBAL_DETAILS SET " + baseLineColumnName + " ='" + baseLineValue + "' WHERE SESSION_ID ='" + selection.getSessionId() + "' "
                + " AND OPERATION ='" + operation + "' " + Constants.AND_CHECK_RECORD;
        HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
        return updateQuery;
    }

	public static Map<String, List> getDdlbmap() {
		return ddlbMap;
	}

}
