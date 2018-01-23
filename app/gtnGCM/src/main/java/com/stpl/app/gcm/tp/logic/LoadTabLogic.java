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
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
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
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author lokeshwari
 */
public class LoadTabLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(LoadTabLogic.class);
    private static int projectionId = 0;
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
    private final CommonDao DAO = CommonImpl.getInstance();

    public int getLevelCount(Object parentId, TabSelectionDTO tabSelectionDTO, int projectionId, SessionDTO session) {
        LOGGER.debug("Inside getLevelCount");
        int count = 0;
        if (parentId instanceof SalesTabDTO) {
            SalesTabDTO pDto = (SalesTabDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            tabSelectionDTO.setIsProjectionTotal(Boolean.FALSE);
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

        } else {
            tabSelectionDTO.setParentLevel("new");
            tabSelectionDTO.setContractMasterSid(session.getContMasteSid());
        }
        LoadTabLogic.projectionId = projectionId;
        setParentLevels(tabSelectionDTO);
        setForecastingType(LoadTabLogic.projectionId);

        List<Object> list = getCountQuery(tabSelectionDTO);
        if (list != null && !list.isEmpty()) {
            Object obj = list.get(0);
            count = obj == null ? 0 : (Integer) obj;
        }
        if (tabSelectionDTO.isIsProjectionTotal()) {
            count = count + NumericConstants.FOUR;
        }
        LOGGER.debug("Exiting getLevelCount");
        return count;
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

    public static List<Object> getLevelListQuery(TabSelectionDTO tabSelectionDTO) {
        LOGGER.debug("Entering getLevelListQuery");
        String query = new String();
        List input = new ArrayList();
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

    public List<SalesTabDTO> getConfiguredSalesTabResults(Object parentId, TabSelectionDTO tabSelectionDTO, boolean excelExport) {
        LOGGER.debug("Inside getConfiguredSalesTabResults");
        List<SalesTabDTO> salesRowList = new ArrayList<>();
        if (parentId instanceof SalesTabDTO) {
            SalesTabDTO pDto = (SalesTabDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            tabSelectionDTO.setIsProjectionTotal(Boolean.FALSE);
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
        } else {
            tabSelectionDTO.setParentLevel("new");
        }
        List<Object> list;
        setParentLevels(tabSelectionDTO);
        List<String> tableAndColumn = CommonLogic.getApprovedProjectionResults(getForecatingType(), true);
        if (!tableAndColumn.isEmpty()) {
            tabSelectionDTO.setTableName(tableAndColumn.get(0));
            tabSelectionDTO.setTempTableName("ST_" + tableAndColumn.get(0));
            tabSelectionDTO.setSalesField(tableAndColumn.get(1));
            tabSelectionDTO.setUnitField(tableAndColumn.get(NumericConstants.TWO));
        }
        list = getLevelListQuery(tabSelectionDTO);
        String tempCcpid = EMPTY_STRING;
        String levelNo = StringUtils.EMPTY;
        SalesTabDTO salesTabDTO = null;
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

            Object obj[] = (Object[]) list.get(i);
            int year = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
            int period = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String commonColumn = common.get(0);

            if (tempCcpid.equalsIgnoreCase(EMPTY_STRING)) {
                tempCcpid = String.valueOf(obj[1]);
                salesTabDTO = new SalesTabDTO();
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

                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    salesTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                }

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

                salesTabDTO = new SalesTabDTO();
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
                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    salesTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                }

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
                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    salesTabDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    salesTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                }

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
        LOGGER.debug("Exiting getConfiguredSalesTabResults");
        return salesRowList;
    }

    public static List<Object> getCountQuery(TabSelectionDTO tabSelectionDTO) {
        LOGGER.debug("getCountQuery");
        String query = new String();
        List input = new ArrayList();
        if (VARIANCE.equals(tabSelectionDTO.getParentLevel()) || CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "contractCount";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(projectionId);
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "companyCount";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "brandCount";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(projectionId);
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "itemCount";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(projectionId);
            input.add(tabSelectionDTO.getBrandMasterSid());
        }
        LOGGER.debug("Exiting getCountQuery");
        return ItemQueries.getItemData(input, query, null);
    }

    public static List<Object> getRebateCountQuery(TabSelectionDTO tabSelectionDTO) {
        String query = StringUtils.EMPTY;
        List input = new ArrayList();
        if (VARIANCE.equals(tabSelectionDTO.getParentLevel()) || CONTRACT.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "contractCount";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(projectionId);
        } else if (COMPANY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "companyCount";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
        } else if (Constants.BRAND_PROPERTY.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "brandCount";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(projectionId);
        } else if (REBATE.equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "rebateCount";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(projectionId);
        } else if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
            query = "itemCountForRebate";
            input.add(getCompanyIds(tabSelectionDTO.getCompanyMasterSids()));
            input.add(tabSelectionDTO.getContractMasterSid());
            input.add(tabSelectionDTO.getBrandMasterSid());
            input.add(tabSelectionDTO.getRebateProgramType());
            input.add(projectionId);
        }
        return ItemQueries.getItemData(input, query, null);
    }

    public int getRebateLevelCount(Object parentId, TabSelectionDTO tabSelectionDTO, int projectionId,SessionDTO session) {
        int count = 0;
        if (parentId instanceof RebateTabDTO) {
            RebateTabDTO pDto = (RebateTabDTO) parentId;
            tabSelectionDTO.setParentLevel(pDto.getParentLevel());
            tabSelectionDTO.setIsProjectionTotal(Boolean.FALSE);
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
        LoadTabLogic.projectionId = projectionId;
        getRebateParentLevels(tabSelectionDTO);
        setForecastingType(projectionId);
        List<Object> list = getRebateCountQuery(tabSelectionDTO);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = Integer.valueOf(String.valueOf(ob));
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
            tabSelectionDTO.setIsProjectionTotal(Boolean.FALSE);
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
        String tempCcpid = EMPTY_STRING;
        RebateTabDTO rebateTabDTO = null;
        String levelNo = StringUtils.EMPTY;
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

            Object obj[] = (Object[]) list.get(i);

            int year = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
            int period = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String commonColumn = common.get(0);
            if (tempCcpid.equalsIgnoreCase(EMPTY_STRING)) {
                tempCcpid = String.valueOf(obj[1]);
                levelNo = String.valueOf(obj[NumericConstants.SIX]);
                rebateTabDTO = new RebateTabDTO();
            }

            if (tempCcpid.equalsIgnoreCase(String.valueOf(obj[1]))) {
                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    rebateTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                }
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setContractMasterSid((Integer) obj[0]);
                    levelNo = String.valueOf(obj[NumericConstants.SIX]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNo = String.valueOf(obj[NumericConstants.SEVEN]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNo = String.valueOf(obj[NumericConstants.EIGHT]);
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNo = String.valueOf(obj[NumericConstants.NINE]);
                }

                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + "Rate", getFormattedValue(PER2DECIMAL, String.valueOf(obj[NumericConstants.FIVE]), excelExport));

                rebateTabDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    levelNo = String.valueOf(obj[NumericConstants.SIX]);
                    rebateTabDTO.setParent(0);
                } else {
                    rebateTabDTO.setParent(1);
                }
            } else {

                rebateTabDTO.setParentLevel(tabSelectionDTO.getParentLevel());
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setContractMasterSid((Integer) obj[0]);
                    levelNo = String.valueOf(obj[NumericConstants.SIX]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNo = String.valueOf(obj[NumericConstants.SEVEN]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNo = String.valueOf(obj[NumericConstants.EIGHT]);
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNo = String.valueOf(obj[NumericConstants.NINE]);
                }
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    levelNo = String.valueOf(obj[NumericConstants.SIX]);
                    rebateTabDTO.setParent(0);
                } else {
                    rebateTabDTO.setParent(1);
                }
                rebateRowList.add(rebateTabDTO);
                tempCcpid = String.valueOf(obj[1]);

                rebateTabDTO = new RebateTabDTO();
                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    rebateTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                }

                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + "Rate", getFormattedValue(PER2DECIMAL, String.valueOf(obj[NumericConstants.FIVE]), excelExport));
            }
            if (i == (list.size() - 1)) {
                if (VARIANCE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setLevelValue(TOTAL_VARIANCE);
                } else {
                    rebateTabDTO.setLevelValue(levelNo + "-" + String.valueOf(obj[1]));
                }
                if (CONTRACT.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setContractMasterSid((Integer) obj[0]);
                    levelNo = String.valueOf(obj[NumericConstants.SIX]);
                } else if (COMPANY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    levelNo = String.valueOf(obj[NumericConstants.SEVEN]);
                } else if (Constants.BRAND_PROPERTY.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setBrandMasterSid((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    levelNo = String.valueOf(obj[NumericConstants.EIGHT]);
                } else if (REBATE.equals(tabSelectionDTO.getParentLevel())) {
                    rebateTabDTO.setRebateProgramType((Integer) obj[0]);
                    rebateTabDTO.setContractMasterSid((Integer) obj[NumericConstants.SIX]);
                    rebateTabDTO.setCompanyMasterSid((Integer) obj[NumericConstants.SEVEN]);
                    rebateTabDTO.setBrandMasterSid((Integer) obj[NumericConstants.EIGHT]);
                    levelNo = String.valueOf(obj[NumericConstants.NINE]);
                }

                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + AMOUNT, getFormattedValue(CUR_ZERO, String.valueOf(obj[NumericConstants.FOUR]), excelExport));
                rebateTabDTO.addStringProperties(StringUtils.EMPTY + commonColumn + "Rate", getFormattedValue(PER2DECIMAL, String.valueOf(obj[NumericConstants.FIVE]), excelExport));
                if ("item".equalsIgnoreCase(tabSelectionDTO.getParentLevel())) {
                    levelNo = String.valueOf(obj[NumericConstants.SIX]);
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

        String queryString = StringUtils.EMPTY;
        List input = new ArrayList();
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
            setForecastingType(LoadTabLogic.projectionId);
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

        list = (List) DAO.executeSelect(query);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object obj[] = (Object[]) list.get(i);
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

        CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

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
        CompanyMasterLocalServiceUtil.executeUpdateQuery(tempQuery);
    }

    public void updateSalesAndDiscount(SessionDTO session) {
        LoadTabLogic.projectionId = session.getProjectionId();

        setForecastingType(LoadTabLogic.projectionId);
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
