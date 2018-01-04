
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.remove.logic;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemLogic;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractSummaryDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import static com.stpl.app.gcm.tp.logic.LoadTabLogic.tpDao;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.HeaderUtils.getCommonColumnHeader;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 */
public class RemoveItemLogic {

    /**
     * The Currency Zero Decimal Places Format.
     */
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    public static final String CONTRACT = "contract";
    public static final String COMPANY = "company";
    
    public static final String UNITS = "Units";
    public static final String SALES = "Sales";
    public static final String EMPTY_STRING = "empty";
    public static final String REBATE = "rebate";
    public static final String RATE = "Rate";
    public static final String AMOUNT = "Amount";
    /**
     * The unit volume.
     */
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0.0");

    public int getLevelCount(Object parentId, TabSelectionDTO tabSelectionDTO, SelectionDTO selection) {
        int count = 0;
        if (parentId instanceof AbstractSummaryDTO) {
            AbstractSummaryDTO pDto = (AbstractSummaryDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setBrandMasterSid(pDto.getBrandMasterSid());
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            }
            tabSelectionDTO.setIsProjectionTotal(Boolean.FALSE);
        } else {
            tabSelectionDTO.setParentLevel("new");
            tabSelectionDTO.setIsProjectionTotal(Boolean.TRUE);
        }
        getIdAndForecastingType(tabSelectionDTO, selection);
        getParentLevels(tabSelectionDTO);
        List<Object> list = getCountQuery(tabSelectionDTO);
        if (list != null && !list.isEmpty()) {
            Object obj = list.get(0);
            count = obj == null ? 0 : (Integer) obj;
        }
        if (tabSelectionDTO.isIsProjectionTotal()) {
        }
        return count;
    }

    public void getParentLevels(TabSelectionDTO tabSelectionDTO) {
        if ("new".equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(CONTRACT);
        } else if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(COMPANY);
        } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(Constants.BRAND_PROPERTY);
        } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel("item");
        }
    }

    public static List getProjectionQuery(TabSelectionDTO SelectionDTO, SelectionDTO selection) {
        String queryName = "Projection Query";
        List input = new ArrayList();
        input.add(selection.getSessionId());
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            input.add(SelectionDTO.getOperation());
        } else {
            input.add(ConstantsUtil.SUMMARY);
        }
        if(ConstantsUtil.TRANSFER_SUMMARY.equals(SelectionDTO.getOperation()))
        {
            input.add(AbstractLogic.getItemIds(selection.getTransferItemList()));
        }
        else
        {
         input.add(AbstractLogic.getItemIds(selection.getItemList()));
        }
        if (selection.getButtonMode().equals(ConstantsUtil.ADD)) {
            queryName = "Projection Query-Add";
        }
        return ItemQueries.getItemData(input, queryName, null);
    }

    public static Object executeSelectQuery(String query) {
        return tpDao.executeSelectQuery(query);
    }

    public static List<Object> getCountQuery(TabSelectionDTO tabSelectionDTO) {
        String query = new String();
        List input = new ArrayList();
        if (CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "contractCountForItem";
            input.add(AbstractLogic.getItemIds(tabSelectionDTO.getItemList()));
            input.add(tabSelectionDTO.getSummaryProjectionId());
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "companyCountForItem";
            input.add(AbstractLogic.getItemIds(tabSelectionDTO.getItemList()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getSummaryProjectionId());
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "brandCountForItem";
            input.add(AbstractLogic.getItemIds(tabSelectionDTO.getItemList()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getSummaryProjectionId());
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "itemCountForItem";
            input.add(tabSelectionDTO.getBrandMasterSid());
        }
        return ItemQueries.getItemData(input, query, null);
    }

    public List<AbstractSummaryDTO> getConfiguredSalesTabResults(Object parentId, TabSelectionDTO tabSelectionDTO, boolean excelExport) {
        List<AbstractSummaryDTO> salesRowList = new ArrayList<>();

        if (parentId instanceof AbstractSummaryDTO) {
            AbstractSummaryDTO pDto = (AbstractSummaryDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setBrandMasterSid(pDto.getBrandMasterSid());
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            }
            tabSelectionDTO.setIsProjectionTotal(Boolean.FALSE);
        } else {
            tabSelectionDTO.setIsProjectionTotal(Boolean.TRUE);
            tabSelectionDTO.setParentLevel("new");
        }
        List<Object> list;
        getParentLevels(tabSelectionDTO);
        List<String> tableAndColumn = CommonLogic.getApprovedProjectionResults(tabSelectionDTO.getForeCastingType().trim(), true);
        if (!tableAndColumn.isEmpty()) {
            tabSelectionDTO.setTableName(tableAndColumn.get(0));
            tabSelectionDTO.setSalesField(tableAndColumn.get(1));
            tabSelectionDTO.setUnitField(tableAndColumn.get(NumericConstants.TWO));
        }
        list = getLevelListQuery(tabSelectionDTO);
        String tempCcpid = EMPTY_STRING;
        String levelNo = StringUtils.EMPTY;
        AbstractSummaryDTO salesTabDTO = null;
        int frequencyDivision = 0;
        if ("quarter".equalsIgnoreCase(tabSelectionDTO.getFrequency())) {
            frequencyDivision = NumericConstants.FOUR;
        } else if ("month".equalsIgnoreCase(tabSelectionDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWELVE;
        } else if ("semi_annual".equalsIgnoreCase(tabSelectionDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWO;
        } else {
            frequencyDivision = 1;
        }
        if (tabSelectionDTO.isIsProjectionTotal()) {
            AbstractSummaryDTO totalCurrentContract = new AbstractSummaryDTO();
            totalCurrentContract.setLevelValue("Total Current Contract");
            totalCurrentContract.setParent(0);
            AbstractSummaryDTO totalAdjustedContract = new AbstractSummaryDTO();
            totalAdjustedContract.setLevelValue("Total Adjusted Contract");
            totalAdjustedContract.setParent(0);
            AbstractSummaryDTO totalVariance = new AbstractSummaryDTO();
            totalVariance.setLevelValue("Total Variance");
            totalVariance.setParent(0);
            AbstractSummaryDTO totalPerChange = new AbstractSummaryDTO();
            totalPerChange.setLevelValue("Total % Change");
            totalPerChange.setParent(0);
        }

        for (int i = 0; i < list.size(); i++) {

            Object[] obj = (Object[]) list.get(i);
            int year = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
            int period = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String commonColumn = common.get(0);
            if (frequencyDivision == NumericConstants.TWELVE) {
                commonColumn = commonColumn.toLowerCase();
            }
            if (tempCcpid.equalsIgnoreCase(EMPTY_STRING)) {
                tempCcpid = String.valueOf(obj[1]);
                salesTabDTO = new AbstractSummaryDTO();
            }

            if (tempCcpid.equalsIgnoreCase(String.valueOf(obj[1]))) {

                if (CONTRACT.equals(tabSelectionDTO.getParentLevel()) || "item".equals(tabSelectionDTO.getParentLevel())) {
                    if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                        salesTabDTO.setContractMasterSid((Integer) obj[0]);
                    }
                    levelNo = String.valueOf(String.valueOf(obj[NumericConstants.SIX]));
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    salesTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNo = String.valueOf(String.valueOf(obj[NumericConstants.SEVEN]));
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setBrandMasterSid((Integer) obj[0]);
                    salesTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    salesTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNo = String.valueOf(String.valueOf(obj[NumericConstants.EIGHT]));
                }
                salesTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                salesTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + SALES, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                salesTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + UNITS, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExport));
                salesTabDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setParent(0);
                } else {
                    salesTabDTO.setParent(1);
                }
            } else {

                salesTabDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setParent(0);
                } else {
                    salesTabDTO.setParent(1);
                }
                salesRowList.add(salesTabDTO);
                tempCcpid = String.valueOf(obj[1]);

                salesTabDTO = new AbstractSummaryDTO();
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setContractMasterSid((Integer) obj[0]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    salesTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setBrandMasterSid((Integer) obj[0]);
                    salesTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    salesTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                }
                salesTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                salesTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + SALES, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                salesTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + UNITS, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExport));
            }
            if (i == (list.size() - 1)) {
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setContractMasterSid((Integer) obj[0]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    salesTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setBrandMasterSid((Integer) obj[0]);
                    salesTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    salesTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                }
                salesTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                salesTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + SALES, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                salesTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + UNITS, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExport));

                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setParent(0);
                } else {
                    salesTabDTO.setParent(1);
                }
                salesTabDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                salesRowList.add(salesTabDTO);
            }
        }
        return salesRowList;
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value, boolean excelExport) {
        if (value.contains(Constants.NULL)) {
            value = "---";
        } else {
            if (!excelExport) {
                value = FORMAT.format(Double.valueOf(value));
            } else {
                return value;
            }
        }
        return value;
    }

    public static List<Object> getLevelListQuery(TabSelectionDTO tabSelectionDTO) {

        String query = new String();
        List input = new ArrayList();
        if (CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getContractForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getFrequency());
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getCompanyForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getFrequency());
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getBrandForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getFrequency());
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getItemForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getFrequency());
        }
        return ItemQueries.getItemData(input, query, null);
    }

    public int getLevelCountForRebate(Object parentId, TabSelectionDTO tabSelectionDTO, SelectionDTO selection) {
        int count = 0;
        if (parentId instanceof AbstractSummaryDTO) {
            AbstractSummaryDTO pDto = (AbstractSummaryDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setBrandMasterSid(pDto.getBrandMasterSid());
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setBrandMasterSid(pDto.getBrandMasterSid());
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
                tabSelectionDTO.setRebateProgramType(pDto.getRebateProgramType());
            }
            tabSelectionDTO.setIsProjectionTotal(Boolean.FALSE);
        } else {
            tabSelectionDTO.setParentLevel("new");
            tabSelectionDTO.setIsProjectionTotal(Boolean.TRUE);
        }
        getIdAndForecastingType(tabSelectionDTO, selection);
        getParentLevelsForRebate(tabSelectionDTO);
        List<Object> list = getCountQueryForRebate(tabSelectionDTO);
        if (list != null && !list.isEmpty()) {
            Object obj = list.get(0);
            count = obj == null ? 0 : (Integer) obj;
        }
        if (tabSelectionDTO.isIsProjectionTotal()) {
        }
        return count;
    }

    public void getParentLevelsForRebate(TabSelectionDTO tabSelectionDTO) {
        if ("new".equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(CONTRACT);
        } else if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(COMPANY);
        } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(Constants.BRAND_PROPERTY);
        } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(REBATE);
        } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel("item");
        }
    }

    public static List<Object> getCountQueryForRebate(TabSelectionDTO tabSelectionDTO) {
        String query = new String();
        List input = new ArrayList();
        if (CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "contractCountForItem";
            input.add(AbstractLogic.getItemIds(tabSelectionDTO.getItemList()));
            input.add(tabSelectionDTO.getSummaryProjectionId());
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "companyCountForItem";
            input.add(AbstractLogic.getItemIds(tabSelectionDTO.getItemList()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getSummaryProjectionId());
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "brandCountForItem";
            input.add(AbstractLogic.getItemIds(tabSelectionDTO.getItemList()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getSummaryProjectionId());
        } else if (REBATE.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "rebateCountForItem";
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getSummaryProjectionId());
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "itemCountForRebateForItem";
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getRebateProgramType());
            input.add(tabSelectionDTO.getSummaryProjectionId());
        }
        return ItemQueries.getItemData(input, query, null);
    }

    public List<AbstractSummaryDTO> getConfiguredRebateTabResults(Object parentId, TabSelectionDTO tabSelectionDTO, boolean excelExport) {
        List<AbstractSummaryDTO> rebateRowList = new ArrayList<>();
        if (parentId instanceof AbstractSummaryDTO) {
            AbstractSummaryDTO pDto = (AbstractSummaryDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setBrandMasterSid(pDto.getBrandMasterSid());
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
            } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                tabSelectionDTO.setBrandMasterSid(pDto.getBrandMasterSid());
                tabSelectionDTO.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTO.setContractMasterSid(pDto.getContractMasterSid());
                tabSelectionDTO.setRebateProgramType(pDto.getRebateProgramType());
            }
            tabSelectionDTO.setIsProjectionTotal(Boolean.FALSE);
        } else {
            tabSelectionDTO.setParentLevel("new");
            tabSelectionDTO.setIsProjectionTotal(Boolean.TRUE);
        }
        List<Object> list;
        getParentLevelsForRebate(tabSelectionDTO);
        List<String> tableAndColumn = CommonLogic.getApprovedProjectionResults(tabSelectionDTO.getForeCastingType(), false);
        if (!tableAndColumn.isEmpty()) {
            tabSelectionDTO.setTableName(tableAndColumn.get(0));
            tabSelectionDTO.setRebateField(tableAndColumn.get(1));
            tabSelectionDTO.setAmountField(tableAndColumn.get(NumericConstants.TWO));
        }
        list = getLevelListQueryForRebate(tabSelectionDTO);
        String tempCcpid = EMPTY_STRING;
        String levelNo = StringUtils.EMPTY;
        AbstractSummaryDTO rebateTabDTO = null;
        int frequencyDivision = 0;
        if ("quarter".equalsIgnoreCase(tabSelectionDTO.getFrequency())) {
            frequencyDivision = NumericConstants.FOUR;
        } else if ("month".equalsIgnoreCase(tabSelectionDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWELVE;
        } else if ("semi_annual".equalsIgnoreCase(tabSelectionDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWO;
        } else {
            frequencyDivision = 1;
        }
        if (tabSelectionDTO.isIsProjectionTotal()) {
            AbstractSummaryDTO totalCurrentContract = new AbstractSummaryDTO();
            totalCurrentContract.setLevelValue("Total Current Contract");
            totalCurrentContract.setParent(0);
            AbstractSummaryDTO totalAdjustedContract = new AbstractSummaryDTO();
            totalAdjustedContract.setLevelValue("Total Adjusted Contract");
            totalAdjustedContract.setParent(0);
            AbstractSummaryDTO totalVariance = new AbstractSummaryDTO();
            totalVariance.setLevelValue("Total Variance");
            totalVariance.setParent(0);
            AbstractSummaryDTO totalPerChange = new AbstractSummaryDTO();
            totalPerChange.setLevelValue("Total % Change");
            totalPerChange.setParent(0);
        }
        for (int i = 0; i < list.size(); i++) {

            Object[] obj = (Object[]) list.get(i);
            int year = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
            int period = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String commonColumn = common.get(0);
            if (frequencyDivision == NumericConstants.TWELVE) {
                commonColumn = commonColumn.toLowerCase();
            }

            if (tempCcpid.equalsIgnoreCase(EMPTY_STRING)) {
                tempCcpid = String.valueOf(obj[1]);
                rebateTabDTO = new AbstractSummaryDTO();
            }

            if (tempCcpid.equalsIgnoreCase(String.valueOf(obj[1]))) {

                if (CONTRACT.equals(tabSelectionDTO.getParentLevel()) || "item".equals(tabSelectionDTO.getParentLevel())) {
                    if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                        rebateTabDTO.setContractMasterSid((Integer) obj[0]);
                    }
                    levelNo = String.valueOf(String.valueOf(obj[NumericConstants.SIX]));
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNo = String.valueOf(String.valueOf(obj[NumericConstants.SEVEN]));
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNo = String.valueOf(String.valueOf(obj[NumericConstants.EIGHT]));
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNo = String.valueOf(obj[NumericConstants.NINE]);
                }
                rebateTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + RATE, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExport));
                rebateTabDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setParent(0);
                } else {
                    rebateTabDTO.setParent(1);
                }
            } else {

                rebateTabDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setParent(0);
                } else {
                    rebateTabDTO.setParent(1);
                }
                rebateRowList.add(rebateTabDTO);
                tempCcpid = String.valueOf(obj[1]);

                rebateTabDTO = new AbstractSummaryDTO();
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setContractMasterSid((Integer) obj[0]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNo = String.valueOf(obj[NumericConstants.NINE]);
                }
                rebateTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + RATE, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExport));
            }
            if (i == (list.size() - 1)) {
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setContractMasterSid((Integer) obj[0]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNo = String.valueOf(obj[NumericConstants.NINE]);
                }
                rebateTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + RATE, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExport));

                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setParent(0);
                } else {
                    rebateTabDTO.setParent(1);
                }
                rebateTabDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                rebateRowList.add(rebateTabDTO);
            }
        }
        return rebateRowList;
    }

    public static List<Object> getLevelListQueryForRebate(TabSelectionDTO tabSelectionDTO) {

        String query = new String();
        List input = new ArrayList();
        if (CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getContractForRebateForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getFrequency());
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getCompanyForRebateForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getFrequency());
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getBrandForRebateForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getFrequency());
        } else if (REBATE.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getRebateForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getFrequency());
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getItemForRebateForItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getCompanyMasterSid());
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getOperation());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getRebateProgramType());
            input.add(tabSelectionDTO.getTableName());
            input.add(tabSelectionDTO.getSummaryProjectionId());
            input.add(tabSelectionDTO.getFrequency());
        }
        return ItemQueries.getItemData(input, query, null);
    }

    public static void getIdAndForecastingType(TabSelectionDTO selectionDTO, SelectionDTO selection) {
        if (selectionDTO.getSummaryProjectionId() == 0 || selectionDTO.getForeCastingType().equals(StringUtils.EMPTY)) {
            ItemLogic.getIdAndForecastingType(selectionDTO, selection);
        }
    }
}
