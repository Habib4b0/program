
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.utils.CommonUtils;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.portal.kernel.exception.PortalException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Maheshj
 */
public class SuggestedAdjLookUpLogic {

    BaseRateDAO dao = new BaseRateDAOImpl();
    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("#,##00.00000%");
    final DecimalFormat GTS = new DecimalFormat("$0.00");
    String symbol = StringUtils.EMPTY;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SuggestedAdjLookUpLogic.class);

    public Object getCompanyLevel(boolean isCount, int offset, int count, String accMasSid, String ccpDetId) {
        List<Object[]> list = new ArrayList<Object[]>();
        StringBuilder query = new StringBuilder();
        if (isCount) {
            query.append(" SELECT COUNT(*) FROM ( ");
        }
        query.append("SELECT CM.COMPANY_ID,CM.COMPANY_MASTER_SID, AVG(ISNULL(BRS.CURRENT_RATE,0)) AS CURRENT_RATE,\n"
                + "ISNULL(ACM.WORKFLOW_STATUS,0) AS STATUS from COMPANY_MASTER CM \n"
                + "JOIN CCP_DETAILS CCP ON CCP.COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID \n"
                + "JOIN ACC_CLOSURE_DETAILS ACD ON CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID\n"
                + "LEFT JOIN AC_BASE_RATE_SUMMARY BRS ON ACD.ACC_CLOSURE_DETAILS_SID = BRS.ACC_CLOSURE_DETAILS_SID\n"
                + "LEFT JOIN ACC_CLOSURE_MASTER ACM ON ACD.ACC_CLOSURE_MASTER_SID = ACM.ACC_CLOSURE_MASTER_SID\n"
                + " WHERE CCP.CCP_DETAILS_SID  IN (" + ccpDetId + ") AND ACM.ACC_CLOSURE_MASTER_SID=" + accMasSid + "    \n"
                + " GROUP BY CM.COMPANY_ID,CM.COMPANY_MASTER_SID,ISNULL(ACM.WORKFLOW_STATUS,0) \n");

        if (isCount) {
            query.append(" ) TEMP ");
        } else {
            query.append(" ORDER BY CM.COMPANY_ID,CM.COMPANY_MASTER_SID   OFFSET  " + offset + "  ROWS FETCH NEXT  " + count + "  ROWS ONLY ");
        }

        try {
            list = (List<Object[]>) dao.executeSelectQuery(query.toString());
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public Object getContractLevel(boolean isCount, int offset, int count, String accMasSid, String ccpDetId, String compSid) {
        List<Object[]> list = new ArrayList<Object[]>();
        StringBuilder query = new StringBuilder();
        if (isCount) {
            query.append(" SELECT COUNT(*) FROM ( ");
        }
        query.append("\n"
                + "SELECT CON.CONTRACT_ID,CON.CONTRACT_MASTER_SID, AVG(ISNULL(BRS.CURRENT_RATE,0)) AS CURRENT_RATE,\n"
                + "ISNULL(ACM.WORKFLOW_STATUS,0) AS STATUS from CONTRACT_MASTER CON \n"
                + "JOIN CCP_DETAILS CCP ON CCP.CONTRACT_MASTER_SID=CON.CONTRACT_MASTER_SID \n"
                + "JOIN ACC_CLOSURE_DETAILS ACD ON CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID\n"
                + "LEFT JOIN AC_BASE_RATE_SUMMARY BRS ON ACD.ACC_CLOSURE_DETAILS_SID = BRS.ACC_CLOSURE_DETAILS_SID\n"
                + "LEFT JOIN ACC_CLOSURE_MASTER ACM ON ACD.ACC_CLOSURE_MASTER_SID = ACM.ACC_CLOSURE_MASTER_SID\n"
                + " WHERE CCP.CCP_DETAILS_SID  IN (" + ccpDetId + ") AND CCP.COMPANY_MASTER_SID=" + compSid + "  AND ACM.ACC_CLOSURE_MASTER_SID=" + accMasSid + "  \n"
                + "  GROUP BY CON.CONTRACT_ID,CON.CONTRACT_MASTER_SID,ISNULL(ACM.WORKFLOW_STATUS,0)  \n");

        if (isCount) {
            query.append(" ) TEMP ");
        } else {
            query.append("  ORDER BY CON.CONTRACT_ID ,CON.CONTRACT_MASTER_SID   OFFSET  " + offset + "  ROWS FETCH NEXT  " + count + "  ROWS ONLY; ");
        }
        try {
            list = (List<Object[]>) dao.executeSelectQuery(query.toString());
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public Object getTheraupicLevel(boolean isCount, int offset, int count, String accMasSid, String ccpDetId, String compSid, String contrSid) {
        List<Object[]> list = new ArrayList<Object[]>();
        StringBuilder query = new StringBuilder();
        if (isCount) {
            query.append(" SELECT COUNT(*) FROM ( ");
        }
        query.append("SELECT HT.DESCRIPTION,HT.HELPER_TABLE_SID,  AVG(ISNULL(BRS.CURRENT_RATE,0)) AS CURRENT_RATE,\n"
                + "ISNULL(ACM.WORKFLOW_STATUS,0) AS STATUS from ITEM_MASTER IM \n"
                + "JOIN CCP_DETAILS CCP ON CCP.ITEM_MASTER_SID=IM.ITEM_MASTER_SID \n"
                + "JOIN ACC_CLOSURE_DETAILS ACD ON CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID\n"
                + "JOIN HELPER_TABLE HT ON IM.THERAPEUTIC_CLASS = HT.HELPER_TABLE_SID \n"
                + "LEFT JOIN AC_BASE_RATE_SUMMARY BRS ON ACD.ACC_CLOSURE_DETAILS_SID = BRS.ACC_CLOSURE_DETAILS_SID\n"
                + "LEFT JOIN ACC_CLOSURE_MASTER ACM ON ACD.ACC_CLOSURE_MASTER_SID = ACM.ACC_CLOSURE_MASTER_SID\n"
                + " WHERE CCP.CCP_DETAILS_SID  IN (" + ccpDetId + ") AND CCP.COMPANY_MASTER_SID=" + compSid + " AND CCP.CONTRACT_MASTER_SID=" + contrSid + "  AND ACM.ACC_CLOSURE_MASTER_SID=" + accMasSid + "  \n"
                + " GROUP BY HT.DESCRIPTION,HT.HELPER_TABLE_SID,ISNULL(ACM.WORKFLOW_STATUS,0) \n");
        if (isCount) {
            query.append(" ) TEMP ");
        } else {
            query.append("  ORDER BY HT.DESCRIPTION,HT.HELPER_TABLE_SID  OFFSET  " + offset + "  ROWS FETCH NEXT  " + count + "  ROWS ONLY; ");
        }
        try {
            list = (List<Object[]>) dao.executeSelectQuery(query.toString());
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public Object getBrandLevel(boolean isCount, int offset, int count, String accMasSid, String ccpDetId, String compSid, String contrSid, String theraupticId) {
        List<Object[]> list = new ArrayList<Object[]>();
        StringBuilder query = new StringBuilder();
        if (isCount) {
            query.append(" SELECT COUNT(*) FROM ( ");
        }
        query.append("\n"
                + "SELECT BR_M.BRAND_NAME,BR_M.BRAND_MASTER_SID, AVG(ISNULL(BRS.CURRENT_RATE,0)) AS CURRENT_RATE,\n"
                + "ISNULL(ACM.WORKFLOW_STATUS,0) AS STATUS from ITEM_MASTER IM \n"
                + "JOIN CCP_DETAILS CCP ON CCP.ITEM_MASTER_SID=IM.ITEM_MASTER_SID \n"
                + "JOIN BRAND_MASTER BR_M ON BR_M.BRAND_MASTER_SID=IM.BRAND_MASTER_SID\n"
                + "JOIN ACC_CLOSURE_DETAILS ACD ON CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID\n"
                + "LEFT JOIN AC_BASE_RATE_SUMMARY BRS ON ACD.ACC_CLOSURE_DETAILS_SID = BRS.ACC_CLOSURE_DETAILS_SID\n"
                + "LEFT JOIN ACC_CLOSURE_MASTER ACM ON ACD.ACC_CLOSURE_MASTER_SID = ACM.ACC_CLOSURE_MASTER_SID\n"
                + " WHERE CCP.CCP_DETAILS_SID  IN (" + ccpDetId + ") AND CCP.COMPANY_MASTER_SID=" + compSid + " AND CCP.CONTRACT_MASTER_SID=" + contrSid + " AND IM.THERAPEUTIC_CLASS=" + theraupticId + "  AND ACM.ACC_CLOSURE_MASTER_SID=" + accMasSid + "  \n"
                + "  GROUP BY BR_M.BRAND_NAME,BR_M.BRAND_MASTER_SID,ISNULL(ACM.WORKFLOW_STATUS,0)  \n");
        if (isCount) {
            query.append(" ) TEMP ");
        } else {
            query.append("  ORDER BY  BR_M.BRAND_NAME,BR_M.BRAND_MASTER_SID   OFFSET  " + offset + "  ROWS FETCH NEXT  " + count + "  ROWS ONLY; ");
        }
        try {
            list = (List<Object[]>) dao.executeSelectQuery(query.toString());
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public Object getItemLevel(boolean isCount, int offset, int count, String accMasSid, String ccpDetId, String compSid, String contrSid, String theraupticId, String brandSid) {
        List<Object[]> list = new ArrayList<Object[]>();
        StringBuilder query = new StringBuilder();
        if (isCount) {
            query.append(" SELECT COUNT(*) FROM ( ");
        }
        query.append("\n"
                + "SELECT IM.ITEM_ID,IM.ITEM_MASTER_SID, AVG(ISNULL(BRS.CURRENT_RATE,0)) AS CURRENT_RATE,\n"
                + "ISNULL(ACM.WORKFLOW_STATUS,0) AS STATUS from ITEM_MASTER IM \n"
                + "JOIN CCP_DETAILS CCP ON CCP.ITEM_MASTER_SID=IM.ITEM_MASTER_SID \n"
                + "JOIN ACC_CLOSURE_DETAILS ACD ON CCP.CCP_DETAILS_SID = ACD.CCP_DETAILS_SID\n"
                + "LEFT JOIN AC_BASE_RATE_SUMMARY BRS ON ACD.ACC_CLOSURE_DETAILS_SID = BRS.ACC_CLOSURE_DETAILS_SID\n"
                + "LEFT JOIN ACC_CLOSURE_MASTER ACM ON ACD.ACC_CLOSURE_MASTER_SID = ACM.ACC_CLOSURE_MASTER_SID\n"
                + " WHERE CCP.CCP_DETAILS_SID  IN (" + ccpDetId + ")  AND CCP.COMPANY_MASTER_SID=" + compSid + " AND CCP.CONTRACT_MASTER_SID=" + contrSid + " AND IM.THERAPEUTIC_CLASS=" + theraupticId + " AND IM.BRAND_MASTER_SID=" + brandSid + "  AND ACM.ACC_CLOSURE_MASTER_SID=" + accMasSid + " \n"
                + "  GROUP BY IM.ITEM_ID,IM.ITEM_MASTER_SID,ISNULL(ACM.WORKFLOW_STATUS,0)  \n");
        if (isCount) {
            query.append(" ) TEMP ");
        } else {
            query.append("  ORDER BY  IM.ITEM_ID,IM.ITEM_MASTER_SID   OFFSET  " + offset + "  ROWS FETCH NEXT  " + count + "  ROWS ONLY; ");
        }
        try {
            list = (List<Object[]>) dao.executeSelectQuery(query.toString());
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public List<TreeDTO> configureLevelSales(Object list, int levelNo, String compSid, String contrSid, String theraupticId, String brandSid) {

        List<TreeDTO> resultList = new ArrayList<TreeDTO>();
        try {
            List result = (List<Object[]>) list;

            TreeDTO dto;
            for (Object object : result) {
                final Object[] obj = (Object[]) object;
                dto = new TreeDTO();
                dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                if (levelNo == 1) {
                    dto.setComapnySid(obj[1].toString());
                    dto.setLevelNo(1);
                }
                if (levelNo == 2) {
                    dto.setComapnySid(String.valueOf(compSid));
                    dto.setContractSid(obj[1].toString());
                    dto.setLevelNo(2);
                }
                if (levelNo == 3) {
                    dto.setComapnySid(String.valueOf(compSid));
                    dto.setContractSid(String.valueOf(contrSid));
                    dto.setTherupticClassId(obj[1].toString());
                    dto.setLevelNo(3);
                }
                if (levelNo == 4) {
                    dto.setComapnySid(String.valueOf(compSid));
                    dto.setContractSid(String.valueOf(contrSid));
                    dto.setTherupticClassId(String.valueOf(theraupticId));
                    dto.setBrandMasterSid(obj[1].toString());
                    dto.setLevelNo(4);
                }
                dto.setParent(1);
                if (levelNo == 5) {
                    dto.setComapnySid(String.valueOf(compSid));
                    dto.setContractSid(String.valueOf(contrSid));
                    dto.setTherupticClassId(String.valueOf(theraupticId));
                    dto.setBrandMasterSid(String.valueOf(brandSid));
                    dto.setItemSid(obj[1].toString());
                    dto.setParent(0);
                    dto.setLevelNo(5);
                }

                dto.addStringProperties("currRate", "N");
                dto.addStringProperties("penRate", "N");
                dto.addStringProperties("conAdj", "N");

                resultList.add(dto);

            }

        } catch (Exception e) {
            LOGGER.error(e);

        }

        return resultList;
    }

    public int getCount(Object parentId, List<String> ccpList, String accMasSid) {
        List<Object[]> list = new ArrayList<Object[]>();
        int count = 0;
        TreeDTO dto = null;
        String ccpDetId = StringUtils.EMPTY;
        for (String id : ccpList) {
            if (ccpDetId.equals(StringUtils.EMPTY)) {
                ccpDetId = ccpDetId + id;
            } else {
                ccpDetId = ccpDetId + "," + id;
            }

        }
        int levelNo = 1;
        if (parentId instanceof TreeDTO) {
            dto = (TreeDTO) parentId;
            levelNo = dto.getLevelNo() + 1;

        }
        if (levelNo == 1) {
            list = (List<Object[]>) getCompanyLevel(true, 0, 0, accMasSid, ccpDetId);
        } else if (levelNo == 2) {
            list = (List<Object[]>) getContractLevel(true, 0, 0, accMasSid, ccpDetId, dto.getComapnySid());
        } else if (levelNo == 3) {
            list = (List<Object[]>) getTheraupicLevel(true, 0, 0, accMasSid, ccpDetId, dto.getComapnySid(), dto.getContractSid());
        } else if (levelNo == 4) {
            list = (List<Object[]>) getBrandLevel(true, 0, 0, accMasSid, ccpDetId, dto.getComapnySid(), dto.getContractSid(), dto.getTherupticClassId());
        } else if (levelNo == 5) {
            list = (List<Object[]>) getItemLevel(true, 0, 0, accMasSid, ccpDetId, dto.getComapnySid(), dto.getContractSid(), dto.getTherupticClassId(), dto.getBrandMasterSid());
        }
        if (list != null && !list.isEmpty()) {
            count = Integer.parseInt(String.valueOf(list.get(0)));

        }

        return count;
    }

    public List<TreeDTO> getData(Object parentId, List<String> ccpList, int count, int offset, String accMasSid) {
        List<TreeDTO> resultList = new ArrayList<TreeDTO>();
        List<Object[]> list = new ArrayList<Object[]>();
        TreeDTO dto = null;
        int levelNo = 1;
        if (parentId instanceof TreeDTO) {
            dto = (TreeDTO) parentId;
            levelNo = dto.getLevelNo() + 1;

        } else {
            dto = new TreeDTO();
        }
        String ccpDetId = StringUtils.EMPTY;
        for (String id : ccpList) {
            if (ccpDetId.equals(StringUtils.EMPTY)) {
                ccpDetId = ccpDetId + id;
            } else {
                ccpDetId = ccpDetId + "," + id;
            }
        }
        if (levelNo == 1) {
            list = (List<Object[]>) getCompanyLevel(false, count, offset, accMasSid, ccpDetId);
        } else if (levelNo == 2) {
            list = (List<Object[]>) getContractLevel(false, count, offset, accMasSid, ccpDetId, dto.getComapnySid());
        } else if (levelNo == 3) {
            list = (List<Object[]>) getTheraupicLevel(false, count, offset, accMasSid, ccpDetId, dto.getComapnySid(), dto.getContractSid());
        } else if (levelNo == 4) {
            list = (List<Object[]>) getBrandLevel(false, count, offset, accMasSid, ccpDetId, dto.getComapnySid(), dto.getContractSid(), dto.getTherupticClassId());
        } else if (levelNo == 5) {
            list = (List<Object[]>) getItemLevel(false, count, offset, accMasSid, ccpDetId, dto.getComapnySid(), dto.getContractSid(), dto.getTherupticClassId(), dto.getBrandMasterSid());
        }

        resultList = configureLevelSales(list, levelNo, dto.getComapnySid(), dto.getContractSid(), dto.getTherupticClassId(), dto.getBrandMasterSid());

        return resultList;
    }

    public List<BaseRateDTO> getGenerateResult(List<String> ccpDetIds, String fre, String accMasSid) {

        callGenerateProcedure(Integer.parseInt(accMasSid));
        CommonUtils utils = new CommonUtils();
        List<BaseRateDTO> brList = new ArrayList<BaseRateDTO>();
        fre = QUARTERLY.getConstant();
        List<Object[]> list = new ArrayList<Object[]>();
        StringBuilder query = new StringBuilder();
        String ccpIds = StringUtils.EMPTY;
        for (String ccp : ccpDetIds) {
            if (ccpIds.equals(StringUtils.EMPTY)) {
                ccpIds = ccp;
            } else {
                ccpIds = ccpIds + "," + ccp;
            }

        }
        query.append("\n"
                + "SELECT I.\"YEAR\" ,I.QUARTER,\n"
                + "                        SUM(BRD.ACTUAL_GTS) as ACTUAL_GTS,\n"
                + "                        SUM(BRD.PROJECTED_GTS) as PROJECTED_GTS,\n"
                + "                        SUM(BRD.ACTUAL_PAYMENT) AS ACTUAL_PAYMENT,\n"
                + "                        AVG(BRD.ACTUAL_RATE) AS ACTUAL_RATE,\n"
                + "                        SUM(BRD.PROJECTED_PAYMENTS) AS PROJECTED_PAYMENTS,\n"
                + "                        AVG(BRD.PROJECTED_RATE) AS PROJECTED_RATE,\n"
                + "                        SUM(BRD.ACCRUALS) AS ACCRUALS,\n"
                + "                        SUM(BRD.AUTO_ACCRUALS) AS AUTO_ACCRUALS,\n"
                + "                        SUM(BRD.MANUAL_ADJUSTMENTS) AS MANUAL_ADJUSTMENTS,\n"
                + "                        SUM(BRD.PAYMENT_TRUE_UP) AS PAYMENT_TRUE_UP,\n"
                + "                        SUM(BRD.ACCRUAL_OTHERS) AS OTHERS,\n"
                + "                        AVG(BRD.ACCRUAL_RATE) AS ACCRUAL_RATE                       \n"
                + "                        FROM   ACC_CLOSURE_DETAILS ACD\n"
                + "                        JOIN dbo.AC_BASE_RATE_DETAILS BRD\n"
                + "                        ON BRD.ACC_CLOSURE_DETAILS_SID = ACD.ACC_CLOSURE_DETAILS_SID\n"
                + "                        JOIN \"PERIOD\" I\n"
                + "                        ON BRD.PERIOD_SID = I.PERIOD_SID\n"
                + "                        WHERE  ACD.ACC_CLOSURE_MASTER_SID =" + accMasSid + " \n"
                + "                        GROUP BY I.\"YEAR\" ,I.QUARTER ORDER BY I.\"YEAR\" ,I.QUARTER \n");

        try {

            BaseRateDTO dto = new BaseRateDTO();
            Map<String, String> inputMap = new HashMap<String, String>();
            String commonColumn = StringUtils.EMPTY;
            Calendar ob = Calendar.getInstance();
            int curMonth = ob.get(Calendar.MONTH);
            int curYear = ob.get(Calendar.YEAR);
            int current = 1;
            int division = 1;
            String dateQuery = StringUtils.EMPTY;
            if (fre.equals(QUARTERLY.getConstant())) {
                current = (curMonth / 3);
                division = 4;
                dateQuery = ",I.QUARTER";
            } else if (fre.equals(SEMI_ANNUALLY.getConstant())) {
                current = (curMonth / 6);
                division = 2;
                dateQuery = ",I.SEMI_ANNUAL";
            } else if (fre.equals(MONTHLY.getConstant())) {
                current = curMonth;
                division = 12;
                dateQuery = ",I.MONTH";
            } else if (fre.equals(ANNUALLY.getConstant())) {
                current = curYear;
                division = 1;
            }
            int pastYear = curYear;

            int startFreq = current + 1;
            int frequencyRange = utils.getFrequencyRange(fre);
            int tempFreq = frequencyRange - current;

            if (tempFreq > 0) {
                pastYear = pastYear - tempFreq / division;
                startFreq = 1;
                if (tempFreq % division > 0) {

                    pastYear = pastYear - 1;

                    startFreq = division - (tempFreq % division) + 1;
                    if (!fre.equals(MONTHLY.getConstant())) {
                        startFreq = startFreq + 1;
                    }

                    if (fre.equals(QUARTERLY.getConstant())) {
                        if (startFreq < 3) {
                            startFreq = 1;
                        } else if (startFreq >= 3 && startFreq < 6) {
                            startFreq = 2;
                        } else if (startFreq >= 6 && startFreq < 9) {
                            startFreq = 3;
                        } else if (startFreq >= 9 && startFreq < 12) {
                            startFreq = 4;
                        }
                    }

                }
            } else {
                startFreq = startFreq - frequencyRange;
            }

            int squr = startFreq;
            int syear = pastYear;
            if (fre.contains(ANNUALLY.getConstant()) && !fre.contains(SEMI_ANNUALLY.getConstant())) {
                syear = current - frequencyRange;
            }

            list = (List<Object[]>) dao.executeSelectQuery(query.toString());

            for (int j = 0; j < 12; j++) {
                dto = new BaseRateDTO();
                for (int i = 0; i < list.size(); i++) {

                    Object[] obj = (Object[]) list.get(i);
                    DecimalFormat FORMAT = setFormat(j);
                    List<Object> doubleHeaderMap = new ArrayList<Object>();
                    List<Object> tripleHeaderMap = new ArrayList<Object>();
                    List<Object> actualDoubleHeaderMap = new ArrayList<Object>();
                    List<Object> prjDoubleHeaderMap = new ArrayList<Object>();
                    String actualColumn = "Actuals";
                    String projectionColumn = "Projections";
                    String commonHeader = StringUtils.EMPTY;
                    syear = Integer.parseInt(String.valueOf(obj[0]));
                    if (!utils.getNull(String.valueOf(obj[1]))) {
                        squr = Integer.parseInt(String.valueOf(obj[1]));
                    }
                    if (fre.contains(QUARTERLY.getConstant())) {
                        commonColumn = "Q" + squr + StringUtils.EMPTY + syear;
                        commonHeader = "Q" + squr + StringUtils.EMPTY + syear + "    ";
                    } else if (fre.contains(SEMI_ANNUALLY.getConstant())) {
                        commonColumn = "S" + squr + StringUtils.EMPTY + syear;
                        commonHeader = "S" + squr + " " + syear + "    ";
                    } else if (fre.contains(ANNUALLY.getConstant())) {
                        commonColumn = StringUtils.EMPTY + syear;
                        commonHeader = StringUtils.EMPTY + syear + "    ";
                    } else if (fre.contains(MONTHLY.getConstant())) {
                        String monthName = getMonthForInt(squr - 1);
                        commonColumn = (monthName + syear);
                        commonHeader = monthName + " " + syear + "    ";
                    }

                    Object singleColumn = commonColumn;
                    actualColumn = commonColumn + "Actuals";
                    projectionColumn = commonColumn + "Actuals";
                    dto.addStringProperties(commonColumn, utils.getNull(String.valueOf(obj[2 + j])) ? setZeroValue(j) : getFormattedValue(FORMAT, String.valueOf(obj[2 + j])));
                    squr++;
                    if (squr > division) {
                        squr = 1;
                        syear++;
                    }

                }
                brList.add(dto);
            }

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return brList;
    }

    public String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num == 12) {
            num = 0;
        }
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    /**
     * The Base Rate procedure for Generate
     *
     * @param session
     * @return
     */
    public boolean callGenerateProcedure(int accMasSid) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        boolean result = false;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_AC_BASE_RATE_INSERT (?)}");

                statement.setInt(1, accMasSid);
                result = statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return false;
    }

    public String setZeroValue(final int i) {
        /*  String symbol = StringUtils.EMPTY;
         if (i == 0 || i == 1 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8) {
         symbol = "$0.00";
         } else {
         symbol = "00.00%";
         }
         return symbol; */
        String symbol = StringUtils.EMPTY;
        if (i == 3 || i == 5 || i == 11) {
            symbol = "00.00000%";
        } else {
            symbol = "$0.00";
        }
        return symbol;
    }

    private DecimalFormat setFormat(int i) {
        /*  DecimalFormat FORMAT = new DecimalFormat();
         if (i == 0 || i == 1 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8) {
         FORMAT = GTS;
         symbol = "$";
         } else {
         FORMAT = PERCENTAGE_FORMAT;
         symbol = "%";
         }
         return FORMAT; */
        DecimalFormat FORMAT = new DecimalFormat();
        if (i == 3 || i == 5 || i == 11) {
            FORMAT = PERCENTAGE_FORMAT;
            symbol = "%";
        } else {
            FORMAT = GTS;
            symbol = "$";
        }
        return FORMAT;
    }

    /**
     * To get the formatted value for Excel
     *
     * @param FORMAT
     * @param value
     * @return
     */
    public String getFormattedValue(DecimalFormat FORMAT, String value) {

        if (value.contains("null") || value.equals("-")) {
            value = "-";
        } else {
            Double newValue = Double.valueOf(value);
            if (FORMAT.toPattern().contains("%")) {
                newValue = newValue / 100;
            }
            value = FORMAT.format(newValue);
        }
        return value;
    }

}
