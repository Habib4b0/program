/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.logic;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.tp.dao.impl.TradingPartnerDAOImpl;
import com.stpl.app.gcm.tp.dto.RebateTabDTO;
import com.stpl.app.gcm.tp.dto.SalesTabDTO;
import com.stpl.app.gcm.tp.dto.SummaryTemDTO;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.HeaderUtils.getCommonColumnHeader;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lokeshwari
 */
public class LoadTabLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadTabLogic.class);
    
    private int projectionId;
    private static String forecatingType = StringUtils.EMPTY;
    private static TradingPartnerDAO tpDao = new TradingPartnerDAOImpl();
    public static final String VARIANCE = "Variance";
    public static final String CONTRACT = "contract";
    public static final String COMPANY = "company";
    public static final String UNITS = "Units";
    public static final String TOTAL_VARIANCE = "Total Variance";
    public static final String AMOUNT = "Amount";
    public static final String SALES = "Sales";
    public static final String REBATE = "rebate";
    public static final String EMPTY_STRING = "empty";
    /**
     * The Currency Zero Decimal Places Format.
     */
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    /**
     * The unit volume.
     */
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0.0");
    /**
     * The unit volume.
     */
    private static final DecimalFormat PER2DECIMAL = new DecimalFormat("#,##0.00%");
    private final CommonDao dao = CommonImpl.getInstance();

    public LoadTabLogic() {
        super();
    }

    public int getLevelCount(Object parentIdLoad, TabSelectionDTO tabSelectionDTOLoad, int projectionIdLoad, SessionDTO sessionLoad) {
        LOGGER.debug("Inside getLevelCount");
        int countLevelCount = 0;
        if (parentIdLoad instanceof SalesTabDTO) {
            SalesTabDTO pDto = (SalesTabDTO) parentIdLoad;
            tabSelectionDTOLoad.setParentLevel(pDto.getParentLevel());
            tabSelectionDTOLoad.setIsProjectionTotal(BooleanConstant.getFalseFlag());
            if (CONTRACT.equals(tabSelectionDTOLoad.getParentLevel())) {
                tabSelectionDTOLoad.setContractMasterSid(pDto.getContractMasterSid());
            } else if (COMPANY.equals(tabSelectionDTOLoad.getParentLevel())) {
                tabSelectionDTOLoad.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTOLoad.setContractMasterSid(pDto.getContractMasterSid());
            } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTOLoad.getParentLevel())) {
                tabSelectionDTOLoad.setBrandMasterSid(pDto.getBrandMasterSid());
                tabSelectionDTOLoad.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTOLoad.setContractMasterSid(pDto.getContractMasterSid());
            }

        } else {
            tabSelectionDTOLoad.setParentLevel("new");
            tabSelectionDTOLoad.setContractMasterSid(sessionLoad.getContMasteSid());
        }
        this.projectionId = projectionIdLoad;
        setParentLevels(tabSelectionDTOLoad);
        setForecastingType(this.projectionId);

        List<Object> listLoad = getCountQuery(tabSelectionDTOLoad);
        if (listLoad != null && !listLoad.isEmpty()) {
            Object obj = listLoad.get(0);
            countLevelCount = obj == null ? 0 : (Integer) obj;
        }
        if (tabSelectionDTOLoad.isIsProjectionTotal()) {
            countLevelCount = countLevelCount + NumericConstants.FOUR;
        }
        LOGGER.debug("Exiting getLevelCount");
        return countLevelCount;
    }

    public void setParentLevels(TabSelectionDTO tabSelectionDTO) {
        if ("new".equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(VARIANCE);
        } else if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(CONTRACT);
        } else if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(COMPANY);
        } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(Constants.BRAND_PROPERTY);
        } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel("item");
        }
    }


    public void setForecastingType(int projectionId) {
        List<Object> list = (List<Object>) executeSelectQuery("select FORECASTING_TYPE from PROJECTION_MASTER where PROJECTION_MASTER_SID = " + projectionId);
        if (list != null && !list.isEmpty()) {
            setForecatingType(String.valueOf(list.get(0)));
        }
    }

    public List<Object> getLevelListQuery(TabSelectionDTO tabSelectionDTO) {
        LOGGER.debug("Entering getLevelListQuery");
        String query = " ";
        List input = new ArrayList(NumericConstants.TWENTY_FIVE);
        if (VARIANCE.equalsIgnoreCase(tabSelectionDTO.getParentLevel()) || CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getContract";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(tabSelectionDTO.getTableName());
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getCompany";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getBrandForSales";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "getItem";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getSalesField());
            input.add(tabSelectionDTO.getUnitField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        }
        LOGGER.debug("Exiting getLevelListQuery");
        return ItemQueries.getItemData(input, query, null);
    }

    public static Object executeSelectQuery(String query) {

        return getTpDao().executeSelectQuery(query);

    }

    public String getFormattedValue(DecimalFormat format, String value, boolean excelExport) {
        if (value.contains(Constants.NULL)) {
            value = "---";
        } else {
            if (!excelExport) {
                value = format.format(Double.valueOf(value));
            } else {
                return value;
            }
        }
        return value;
    }

    public List<SalesTabDTO> getConfiguredSalesTabResults(Object parentIdSales, TabSelectionDTO tabSelectionDTOSales, boolean excelExportSales) {
        LOGGER.debug("Inside getConfiguredSalesTabResults");
        List<SalesTabDTO> salesRowListResults = new ArrayList<>();
        if (parentIdSales instanceof SalesTabDTO) {
            SalesTabDTO pDto = (SalesTabDTO) parentIdSales;
            tabSelectionDTOSales.setParentLevel(pDto.getParentLevel());
            tabSelectionDTOSales.setIsProjectionTotal(BooleanConstant.getFalseFlag());
            if (CONTRACT.equals(tabSelectionDTOSales.getParentLevel())) {
                tabSelectionDTOSales.setContractMasterSid(pDto.getContractMasterSid());
            } else if (COMPANY.equals(tabSelectionDTOSales.getParentLevel())) {
                tabSelectionDTOSales.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTOSales.setContractMasterSid(pDto.getContractMasterSid());
            } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTOSales.getParentLevel())) {
                tabSelectionDTOSales.setBrandMasterSid(pDto.getBrandMasterSid());
                tabSelectionDTOSales.setCompanyMasterSid(pDto.getCompanyMasterSid());
                tabSelectionDTOSales.setContractMasterSid(pDto.getContractMasterSid());
            }
        } else {
            tabSelectionDTOSales.setParentLevel("new");
        }
        List<Object> listSalesResults;
        setParentLevels(tabSelectionDTOSales);
        List<String> tableAndColumnSales = CommonLogic.getApprovedProjectionResults(getForecatingType(), true);
        if (!tableAndColumnSales.isEmpty()) {
            tabSelectionDTOSales.setTableName(tableAndColumnSales.get(0));
            tabSelectionDTOSales.setTempTableName("ST_" + tableAndColumnSales.get(0));
            tabSelectionDTOSales.setSalesField(tableAndColumnSales.get(1));
            tabSelectionDTOSales.setUnitField(tableAndColumnSales.get(NumericConstants.TWO));
        }
        listSalesResults = getLevelListQuery(tabSelectionDTOSales);
        String tempCcpidSales = EMPTY_STRING;
        String levelNoSales = StringUtils.EMPTY;
        SalesTabDTO salesTabDTOSales = null;
        int frequencyDivisionSales = 0;
        if ("quarter".equalsIgnoreCase(tabSelectionDTOSales.getFrequency())) {
            frequencyDivisionSales = NumericConstants.FOUR;
        } else if ("month".equalsIgnoreCase(tabSelectionDTOSales.getFrequency())) {
            frequencyDivisionSales = NumericConstants.TWELVE;
        } else if ("semi_annual".equalsIgnoreCase(tabSelectionDTOSales.getFrequency())) {
            frequencyDivisionSales = NumericConstants.TWO;
        } else {
            frequencyDivisionSales = 1;
        }

        for (int i = 0; i < listSalesResults.size(); i++) {

            Object[] obj = (Object[]) listSalesResults.get(i);
            int year = Integer.parseInt(String.valueOf(obj[NumericConstants.TWO]));
            int period = Integer.parseInt(String.valueOf(obj[NumericConstants.THREE]));
            List<String> common = getCommonColumnHeader(frequencyDivisionSales, year, period);
            String commonColumn = common.get(0);

            if (tempCcpidSales.equalsIgnoreCase(EMPTY_STRING)) {
                tempCcpidSales = String.valueOf(obj[1]);
                salesTabDTOSales = new SalesTabDTO();
            }

            if (tempCcpidSales.equalsIgnoreCase(String.valueOf(obj[1]))) {

                if (CONTRACT.equals(tabSelectionDTOSales.getParentLevel()) || "item".equals(tabSelectionDTOSales.getParentLevel())) {
                    if (CONTRACT.equals(tabSelectionDTOSales.getParentLevel())) {
                        salesTabDTOSales.setContractMasterSid((Integer) obj[0]);
                    }
                    levelNoSales = String.valueOf(String.valueOf(obj[NumericConstants.SIX]));
                } else if (COMPANY.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setCompanyMasterSid(DataTypeConverter.convertObjectToInt(obj[0]));
                    salesTabDTOSales.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNoSales = String.valueOf(String.valueOf(obj[NumericConstants.SEVEN]));
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setBrandMasterSid((Integer) obj[0]);
                    salesTabDTOSales.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    salesTabDTOSales.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNoSales = String.valueOf(String.valueOf(obj[NumericConstants.EIGHT]));
                }

                if (VARIANCE.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setLevelValue(TOTAL_VARIANCE);
                } else {
                    salesTabDTOSales.setLevelValue(levelNoSales + "-" +(obj[1]));
                }

                salesTabDTOSales.addStringProperties(StringUtils.EMPTY + commonColumn + SALES, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExportSales));
                salesTabDTOSales.addStringProperties(StringUtils.EMPTY + commonColumn + UNITS, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExportSales));
                salesTabDTOSales.setParentLevel(tabSelectionDTOSales.getParentLevel());
                if ("item".equalsIgnoreCase(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setParent(0);
                } else {
                    salesTabDTOSales.setParent(1);
                }
            } else {

                salesTabDTOSales.setParentLevel(tabSelectionDTOSales.getParentLevel());
                if ("item".equalsIgnoreCase(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setParent(0);
                } else {
                    salesTabDTOSales.setParent(1);
                }
                salesRowListResults.add(salesTabDTOSales);
                tempCcpidSales = String.valueOf(obj[1]);

                salesTabDTOSales = new SalesTabDTO();
                if (CONTRACT.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setContractMasterSid((Integer) obj[0]);
                } else if (COMPANY.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setCompanyMasterSid(DataTypeConverter.convertObjectToInt(obj[0]));
                    salesTabDTOSales.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setBrandMasterSid((Integer) obj[0]);
                    salesTabDTOSales.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    salesTabDTOSales.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                }
                if (VARIANCE.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setLevelValue(TOTAL_VARIANCE);
                } else {
                    salesTabDTOSales.setLevelValue(levelNoSales + "-" +(obj[1]));
                }

                salesTabDTOSales.addStringProperties(StringUtils.EMPTY + commonColumn + SALES, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExportSales));
                salesTabDTOSales.addStringProperties(StringUtils.EMPTY + commonColumn + UNITS, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExportSales));

            }
            if (i == (listSalesResults.size() - 1)) {
                if (CONTRACT.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setContractMasterSid((Integer) obj[0]);
                } else if (COMPANY.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setCompanyMasterSid(DataTypeConverter.convertObjectToInt(obj[0]));
                    salesTabDTOSales.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setBrandMasterSid((Integer) obj[0]);
                    salesTabDTOSales.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    salesTabDTOSales.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                }
                if (VARIANCE.equals(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setLevelValue(TOTAL_VARIANCE);
                } else {
                    salesTabDTOSales.setLevelValue(levelNoSales + "-" +(obj[1]));
                }

                salesTabDTOSales.addStringProperties(StringUtils.EMPTY + commonColumn + SALES, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExportSales));
                salesTabDTOSales.addStringProperties(StringUtils.EMPTY + commonColumn + UNITS, getFormattedValue(UNITVOLUME, String.valueOf(obj[NumericConstants.FIVE]), excelExportSales));

                if ("item".equalsIgnoreCase(tabSelectionDTOSales.getParentLevel())) {
                    salesTabDTOSales.setParent(0);
                } else {
                    salesTabDTOSales.setParent(1);
                }
                salesTabDTOSales.setParentLevel(tabSelectionDTOSales.getParentLevel());
                salesRowListResults.add(salesTabDTOSales);
            }
        }
        LOGGER.debug("Exiting getConfiguredSalesTabResults");
        return salesRowListResults;
    }

    public List<Object> getCountQuery(TabSelectionDTO tabSelectionDTO) {
        LOGGER.debug("getCountQuery");
        String queryCountQuery =" ";
        List inputCountQuery = new ArrayList();
        if (VARIANCE.equals(tabSelectionDTO.getParentLevel()) || CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryCountQuery = "contractCount";
            inputCountQuery.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            inputCountQuery.add(tabSelectionDTO.getContractMasterSid());
            inputCountQuery.add(projectionId);
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryCountQuery = "companyCount";
            inputCountQuery.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryCountQuery = "brandCount";
            inputCountQuery.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            inputCountQuery.add(tabSelectionDTO.getContractMasterSid());
            inputCountQuery.add(projectionId);
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryCountQuery = "itemCount";
            inputCountQuery.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            inputCountQuery.add(tabSelectionDTO.getContractMasterSid());
            inputCountQuery.add(projectionId);
            inputCountQuery.add(tabSelectionDTO.getBrandMasterSid());
        }
        LOGGER.debug("Exiting getCountQuery");
        return ItemQueries.getItemData(inputCountQuery, queryCountQuery, null);
    }

    public List<Object> getRebateCountQuery(TabSelectionDTO tabSelectionDTO) {
        String queryRebate = StringUtils.EMPTY;
        List inputRebate = new ArrayList();
        if (VARIANCE.equals(tabSelectionDTO.getParentLevel()) || CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryRebate = "contractCount";
            inputRebate.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            inputRebate.add(tabSelectionDTO.getContractMasterSid());
            inputRebate.add(projectionId);
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryRebate = "companyCount";
            inputRebate.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryRebate = "brandCount";
            inputRebate.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            inputRebate.add(tabSelectionDTO.getContractMasterSid());
            inputRebate.add(projectionId);
        } else if (REBATE.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryRebate = "rebateCount";
            inputRebate.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            inputRebate.add(tabSelectionDTO.getContractMasterSid());
            inputRebate.add(tabSelectionDTO.getBrandMasterSid());
            inputRebate.add(projectionId);
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryRebate = "itemCountForRebate";
            inputRebate.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            inputRebate.add(tabSelectionDTO.getContractMasterSid());
            inputRebate.add(tabSelectionDTO.getBrandMasterSid());
            inputRebate.add(tabSelectionDTO.getRebateProgramType());
            inputRebate.add(projectionId);
        }
        return ItemQueries.getItemData(inputRebate, queryRebate, null);
    }

    public int getRebateLevelCount(Object parentId, TabSelectionDTO tabSelectionDTO, int projectionId,SessionDTO session) {
        int count = 0;
        if (parentId instanceof RebateTabDTO) {
            RebateTabDTO pDto = (RebateTabDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            tabSelectionDTO.setIsProjectionTotal(BooleanConstant.getFalseFlag());
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
        } else {
            tabSelectionDTO.setContractMasterSid(session.getContMasteSid());
            tabSelectionDTO.setParentLevel("new");
        }
        this.projectionId = projectionId;
        getRebateParentLevels(tabSelectionDTO);
        setForecastingType(this.projectionId);
        List<Object> list = getRebateCountQuery(tabSelectionDTO);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = Integer.parseInt(String.valueOf(ob));
        }
        if (tabSelectionDTO.isIsProjectionTotal()) {
            count += NumericConstants.FOUR;
        }
        return count;
    }

    public void getRebateParentLevels(TabSelectionDTO tabSelectionDTO) {
        if ("new".equals(tabSelectionDTO.getParentLevel())) {
            tabSelectionDTO.setParentLevel(VARIANCE);
        } else if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
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

    public List<RebateTabDTO> getConfiguredRebateTabResults(Object parentId, TabSelectionDTO tabSelectionDTO, boolean excelExport) {
        List<RebateTabDTO> rebateRowList = new ArrayList<>();

        if (parentId instanceof RebateTabDTO) {
            RebateTabDTO pDto = (RebateTabDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            tabSelectionDTO.setIsProjectionTotal(BooleanConstant.getFalseFlag());
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
        } else {
            tabSelectionDTO.setParentLevel("new");
        }
        getRebateParentLevels(tabSelectionDTO);
        List<String> tableAndColumn = CommonLogic.getApprovedProjectionResults(getForecatingType(), false);
        if (!tableAndColumn.isEmpty()) {
            tabSelectionDTO.setTableName(tableAndColumn.get(0));
            tabSelectionDTO.setTempTableName("ST_" + tableAndColumn.get(0));
            tabSelectionDTO.setRebateField(tableAndColumn.get(1));
            tabSelectionDTO.setAmountField(tableAndColumn.get(NumericConstants.TWO));
        }

        List<Object> list = getLevelListQueryForRebate(tabSelectionDTO);
        String tempCcpidRebate = EMPTY_STRING;
        RebateTabDTO rebateTabResultsDTO = null;
        String levelNoRebateResults = StringUtils.EMPTY;
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

        for (int i = 0; i < list.size(); i++) {

            Object[] obj = (Object[]) list.get(i);

            int year = Integer.parseInt(String.valueOf(obj[NumericConstants.TWO]));
            int period = Integer.parseInt(String.valueOf(obj[NumericConstants.THREE]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String commonColumn = common.get(0);
            if (tempCcpidRebate.equalsIgnoreCase(EMPTY_STRING)) {
                tempCcpidRebate = String.valueOf(obj[1]);
                levelNoRebateResults = String.valueOf(obj[NumericConstants.SIX]);
                rebateTabResultsDTO = new RebateTabDTO();
            }

            if (tempCcpidRebate.equalsIgnoreCase(String.valueOf(obj[1]))) {
                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    rebateTabResultsDTO.setLevelValue(levelNoRebateResults + "-" +(obj[1]));
                }
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[0]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SIX]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setCompanyMasterSid(DataTypeConverter.convertObjectToInt(obj[0]));
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SEVEN]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.EIGHT]);
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabResultsDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.NINE]);
                }

                rebateTabResultsDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabResultsDTO.addStringProperties(StringUtils.EMPTY + commonColumn + "Rate", getFormattedValue(PER2DECIMAL, String.valueOf(obj[NumericConstants.FIVE]), excelExport));

                rebateTabResultsDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setParent(0);
                } else {
                    rebateTabResultsDTO.setParent(1);
                }
            } else {

                rebateTabResultsDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[0]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SIX]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setCompanyMasterSid(DataTypeConverter.convertObjectToInt(obj[0]));
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SEVEN]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.EIGHT]);
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabResultsDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.NINE]);
                }
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setParent(0);
                } else {
                    rebateTabResultsDTO.setParent(1);
                }
                rebateRowList.add(rebateTabResultsDTO);
                tempCcpidRebate = String.valueOf(obj[1]);

                rebateTabResultsDTO = new RebateTabDTO();
                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    rebateTabResultsDTO.setLevelValue(levelNoRebateResults + "-" +(obj[1]));
                }

                rebateTabResultsDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabResultsDTO.addStringProperties(StringUtils.EMPTY + commonColumn + "Rate", getFormattedValue(PER2DECIMAL, String.valueOf(obj[NumericConstants.FIVE]), excelExport));
            }
            if (i == (list.size() - 1)) {
                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    rebateTabResultsDTO.setLevelValue(levelNoRebateResults + "-" +(obj[1]));
                }
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[0]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SIX]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setCompanyMasterSid(DataTypeConverter.convertObjectToInt(obj[0]));
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SEVEN]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.EIGHT]);
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabResultsDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabResultsDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabResultsDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.NINE]);
                }

                rebateTabResultsDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabResultsDTO.addStringProperties(StringUtils.EMPTY + commonColumn + "Rate", getFormattedValue(PER2DECIMAL, String.valueOf(obj[NumericConstants.FIVE]), excelExport));
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    levelNoRebateResults = String.valueOf(obj[NumericConstants.SIX]);
                    rebateTabResultsDTO.setParent(0);
                } else {
                    rebateTabResultsDTO.setParent(1);
                }
                rebateTabResultsDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                rebateRowList.add(rebateTabResultsDTO);
            }

        }

        return rebateRowList;
    }

    public List<Object> getLevelListQueryForRebate(TabSelectionDTO tabSelectionDTO) {

        String queryString = StringUtils.EMPTY;
        List input = new ArrayList(NumericConstants.TWENTY_FIVE);
        if (VARIANCE.equalsIgnoreCase(tabSelectionDTO.getParentLevel()) || CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {

            queryString = "getContractForRebate";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(tabSelectionDTO.getTableName());
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryString = "getCompanyForRebate";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryString = "getBrandForRebate";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        } else if (REBATE.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryString = "getRebate";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            queryString = "getItemForRebate";
            input.add(tabSelectionDTO.getFrequency());
            input.add(tabSelectionDTO.getRebateField());
            input.add(tabSelectionDTO.getAmountField());
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getRebateProgramType());
            input.add(tabSelectionDTO.getTempTableName());
            input.add(projectionId);
            input.add(tabSelectionDTO.getSessionID());
            input.add(tabSelectionDTO.getFrequency());
        }
        return ItemQueries.getItemData(input, queryString, null);
    }

    public static Object getCompanyIds(List<String> itemList) {
        return CommonUtils.getListToString(itemList);
    }

    public void getProjectionList(SessionDTO session) {
        projectionId = session.getProjectionId();
        if (projectionId != 0) {
            setForecastingType(this.projectionId);
            List<String> salesTableAndColumn = CommonLogic.getApprovedProjectionResults(getForecatingType(), true);
            List<String> discountTableAndColumn = CommonLogic.getApprovedProjectionResults(getForecatingType(), false);

            List<SummaryTemDTO> list = getCompanyResults(buildContractCompDetails(session));
            callSalesInsert(session, salesTableAndColumn, discountTableAndColumn, getForecatingType());
            callSalesUpdate(session, list, salesTableAndColumn, discountTableAndColumn);
        }
    }

    public List<SummaryTemDTO> getCompanyResults(String query) {
        List list;
        List<SummaryTemDTO> resultList = new ArrayList<>();
        SummaryTemDTO dto = null;

        list =  dao.executeSelect(query);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                dto = new SummaryTemDTO();

                dto.setContractSid(Integer.parseInt(String.valueOf(obj[0])));
                dto.setCompanySid(Integer.parseInt(String.valueOf(obj[1])));
                dto.setCompanyStartDate(!String.valueOf(obj[NumericConstants.TWO]).equals(Constants.NULL) ? (Date) obj[NumericConstants.TWO] : null);
                dto.setCompanyEndDate(!String.valueOf(obj[NumericConstants.THREE]).equals(Constants.NULL) ? (Date) obj[NumericConstants.THREE] : null);
                resultList.add(dto);

            }
        }

        return resultList;

    }

    public String buildContractCompDetails(SessionDTO session) {
        List<String> compSids = session.getCompanyMasterSids();
        String compSid = StringUtils.EMPTY;
        for (String sid : compSids) {
            if (compSid.equals(StringUtils.EMPTY)) {
                compSid = sid;
            } else {
                compSid = compSid + "," + sid;
            }
        }
        List input = new ArrayList();
        input.add(session.getSessionId());
        input.add(compSid);
        return ItemQueries.getQuery(input, "Build Contract CompDetails");

    }

    public void callSalesInsert(SessionDTO session, List<String> salesColumn, List<String> discountColumn, String forecatingType) {
       
        clearTemp(session);
        List<String> compSids = session.getCompanyMasterSids();
        String compSid = StringUtils.EMPTY;
        for (String sid : compSids) {
            if (compSid.equals(StringUtils.EMPTY)) {
                compSid = sid;
            } else {
                compSid = compSid + "," + sid;
            }
        }

        String query = " INSERT INTO ST_" + salesColumn.get(0) + "(\n"
                + "			PROJECTION_DETAILS_SID,\n"
                + "			" + salesColumn.get(1) + ",\n"
                + "			" + salesColumn.get(NumericConstants.TWO) + ",\n"
                + "			PERIOD_SID,\n"
                + "			USER_ID,\n"
                + "			SESSION_ID,\n"
                + "			LAST_MODIFIED_DATE )\n"
                + "		SELECT A.PROJECTION_DETAILS_SID,\n"
                + "			 A." + salesColumn.get(1) + ",\n"
                + "			 A." + salesColumn.get(NumericConstants.TWO) + ",\n"
                + "			A.PERIOD_SID,\n"
                + "			" + session.getUserId() + " USER_ID,\n"
                + "			" + session.getSessionId() + " SESSION_ID,\n"
                + "			GetDate()  LAST_MODIFIED_DATE \n"
                + "           FROM " + salesColumn.get(0) + " A,\n"
                + "			PROJECTION_DETAILS B,CCP_DETAILS C\n"
                + "		WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
                + "                        AND B.CCP_DETAILS_SID=C.CCP_DETAILS_SID\n"
                + "			AND B.PROJECTION_MASTER_SID=" + session.getProjectionId() + "\n"
                + "                        AND C.CONTRACT_MASTER_SID=" + session.getContMasteSid() + "\n"
                + "                        AND C.COMPANY_MASTER_SID in(" + compSid + ");\n"
                + " INSERT INTO ST_" + discountColumn.get(0) + "(\n"
                + "			PROJECTION_DETAILS_SID,\n"
                + "			" + discountColumn.get(1) + ",\n"
                + "			" + discountColumn.get(NumericConstants.TWO) + ",\n";
        if (forecatingType.equals(Constants.IndicatorConstants.NON_MANDATED.getConstant())) {
            query = query + " RS_MODEL_SID,\n  ";
        }
        query = query + "			PERIOD_SID,\n"
                + "			USER_ID,\n"
                + "			SESSION_ID,\n"
                + "			LAST_MODIFIED_DATE )\n"
                + "		SELECT A.PROJECTION_DETAILS_SID,\n"
                + "			A." + discountColumn.get(1) + ",\n"
                + "			A." + discountColumn.get(NumericConstants.TWO) + ",\n";
        if (forecatingType.equals(Constants.IndicatorConstants.NON_MANDATED.getConstant())) {
            query = query + " RS_MODEL_SID,\n  ";
        }
        query = query + "  A.PERIOD_SID,\n"
                + "			" + session.getUserId() + " USER_ID,\n"
                + "			" + session.getSessionId() + " SESSION_ID,\n"
                + "			GetDate()  LAST_MODIFIED_DATE \n"
                + "           FROM " + discountColumn.get(0) + " A,\n"
                + "			PROJECTION_DETAILS B,CCP_DETAILS C\n"
                + "		WHERE A.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID\n"
                + "                        AND B.CCP_DETAILS_SID=C.CCP_DETAILS_SID\n"
                + "			AND B.PROJECTION_MASTER_SID=" + session.getProjectionId() + "\n"
                + "                        AND C.CONTRACT_MASTER_SID=" + session.getContMasteSid() + "\n"
                + "                        AND C.COMPANY_MASTER_SID in(" + compSid + ");\n";

        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    public void callSalesUpdate(SessionDTO session, List<SummaryTemDTO> list, List<String> salesColumn, List<String> discountColumn) {
        List salesInput = getUpdateDataInput(salesColumn);
        List discountInput = getUpdateDataInput(discountColumn);
        for (SummaryTemDTO dto : list) {
            getUpdatedDataInputForDTO(salesInput, dto, session);
            getUpdatedDataInputForDTO(discountInput, dto, session);
            addUserAndSessionId(discountInput, session);
            addUserAndSessionId(salesInput, session);
            ItemQueries.itemUpdate(salesInput, "call Sales Update");
            ItemQueries.itemUpdate(discountInput, "call Sales Update");
        }
    }

    public void clearTemp(SessionDTO session) {
        List<String> salesTableAndColumn = CommonLogic.getApprovedProjectionResults(getForecatingType(), true);
        List<String> discountTableAndColumn = CommonLogic.getApprovedProjectionResults(getForecatingType(), false);

        String tempQuery = " DELETE FROM dbo.ST_" + salesTableAndColumn.get(0) + " WHERE  USER_ID=" + session.getUserId() + "  AND SESSION_ID=" + session.getSessionId() + ";"
                + "  DELETE FROM dbo.ST_" + discountTableAndColumn.get(0) + " WHERE  USER_ID=" + session.getUserId() + " AND SESSION_ID=" + session.getSessionId() + "; ";
        HelperTableLocalServiceUtil.executeUpdateQuery(tempQuery);
    }

    public void updateSalesAndDiscount(SessionDTO session) {
        this.projectionId = session.getProjectionId();

        setForecastingType(this.projectionId);
        List<String> salesColumn = CommonLogic.getApprovedProjectionResults(getForecatingType(), true);
        List<String> discountColumn = CommonLogic.getApprovedProjectionResults(getForecatingType(), false);

        List<SummaryTemDTO> list = getCompanyResults(buildContractCompDetails(session));
        List salesInput = getUpdateDataInput(salesColumn);
        List discountInput = getUpdateDataInput(discountColumn);
        for (SummaryTemDTO dto : list) {
            getUpdatedDataInputForDTO(salesInput, dto, session);
            getUpdatedDataInputForDTO(discountInput, dto, session);
            ItemQueries.itemUpdate(salesInput, "Update Sales And Discount");
            ItemQueries.itemUpdate(discountInput, "Update Sales And Discount");
        }
    }

    public String getProjectionName(int projectionId) {
        String projName = " ";
        List<Object> list = (List<Object>) executeSelectQuery("select PROJECTION_NAME from PROJECTION_MASTER where PROJECTION_MASTER_SID = " + projectionId);
        if (list != null && !list.isEmpty()) {
            projName = String.valueOf(list.get(0));
        }
        return projName;
    }

    private List getUpdateDataInput(List<String> salesColumn) {
        List input = new ArrayList();
        input.add(salesColumn.get(0));
        input.add(salesColumn.get(1));
        input.add(salesColumn.get(NumericConstants.TWO));
        return input;

    }

    private void getUpdatedDataInputForDTO(List salesInput, SummaryTemDTO dto, SessionDTO session) {
        salesInput.add(dto.getCompanyStartDate().getMonth() + NumericConstants.ONE);
        salesInput.add(dto.getCompanyStartDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
        if(dto.getCompanyEndDate() != null) {
        salesInput.add(dto.getCompanyEndDate().getMonth() + 1);
        salesInput.add(dto.getCompanyEndDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
        }else {
        salesInput.add(null);
        salesInput.add(null);
        }
        salesInput.add(dto.getContractSid());
        salesInput.add(dto.getCompanySid());
        salesInput.add(session.getProjectionId());

    }

    private void addUserAndSessionId(List discountInput, SessionDTO session) {
        discountInput.add(session.getUserId());
        discountInput.add(session.getSessionId());
    }

	public static String getForecatingType() {
		return forecatingType;
	}

	public static void setForecatingType(String forecatingType) {
		LoadTabLogic.forecatingType = forecatingType;
	}

	public static TradingPartnerDAO getTpDao() {
		return tpDao;
	}

	public static void setTpDao(TradingPartnerDAO tpDao) {
		LoadTabLogic.tpDao = tpDao;
	}

}
