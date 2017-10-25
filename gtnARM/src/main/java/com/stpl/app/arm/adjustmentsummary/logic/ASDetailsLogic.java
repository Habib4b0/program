/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentsummary.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic;
import com.stpl.app.arm.supercode.SelectionDTO;
import static com.stpl.app.arm.utils.ARMUtils.COMMA;
import com.stpl.app.arm.utils.QueryUtils;
import static com.stpl.app.utils.VariableConstants.DASH;
import static com.stpl.app.utils.VariableConstants.SINGLE_QUOTE;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Jayaram.LeelaRam 
 */
public class ASDetailsLogic<T extends AdjustmentDTO> extends AbstractAdjustmentDetailsLogic<T> {
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String getQuery(SelectionDTO selection, Boolean isCount, int start, int offset) {
        LOGGER.debug(" Inside GetQuery method ");
        String sb = StringUtils.EMPTY;
        String category = StringUtils.EMPTY;
        String type = StringUtils.EMPTY;
        String account = StringUtils.EMPTY;
        String accountDesc = StringUtils.EMPTY;
        String[] adjustmentLevel = null;
        String[] gtnAdjustmentLevel = null;
        if (selection.getDetail_reserveAcount() != null) {
            for (int i = 0; i < selection.getDetail_reserveAcount().size(); i++) {
                String[] str = selection.getDetail_reserveAcount().get(i).split(" - ");
                if (i == 0) {
                    category = str[0].trim();
                    type = str[1].trim();
                    account = SINGLE_QUOTE + str[NumericConstants.TWO].trim() + SINGLE_QUOTE;
                    if (str.length ==  NumericConstants.FOUR) {
                        accountDesc = SINGLE_QUOTE + str[NumericConstants.THREE].trim() + SINGLE_QUOTE;
                    }

                } else {
                    category += COMMA + str[0].trim();
                    type += COMMA + str[1].trim();
                    account += COMMA + SINGLE_QUOTE + str[NumericConstants.TWO].trim() + SINGLE_QUOTE;
                    if (str.length ==  NumericConstants.FOUR) {
                        accountDesc += COMMA + SINGLE_QUOTE + str[NumericConstants.THREE].trim() + SINGLE_QUOTE;
                    }
                }
            }
        }
        List<HelperDTO> list = helperId.getListNameMap().get("ARM_RES_ADJUSTMENT_LEVEL");
        if (list != null) {
            adjustmentLevel = new String[NumericConstants.TWO];
            for (HelperDTO dto : list) {
                if (dto.getDescription().equalsIgnoreCase("BRAND")) {
                    adjustmentLevel[0] = String.valueOf(dto.getId());
                } else {
                    adjustmentLevel[1] = String.valueOf(dto.getId());
                }
            }
        }
        int configLevelid = helperId.getIdByDesc("ARM_CONFIGURATION_TYPE", selection.getDetail_Level());
        String adjustmentDetailSelection = StringUtils.EMPTY;
        List adjDetail = null;
        try {
            if (GlobalConstants.getReserveDetail().equals(selection.getDetail_Level())) {
                adjDetail = selection.getSelectedAdjustmentType();
                if (adjDetail != null && !adjDetail.isEmpty()) {
                    for (int i = 0; i < adjDetail.size(); i++) {
                        if (i == 0) {
                            adjustmentDetailSelection = adjDetail.get(i).toString();
                        } else {
                            adjustmentDetailSelection += "," + adjDetail.get(i).toString();
                        }
                    }
                }

                sb = SQlUtil.getQuery("ASAdjustmentDetailReserveCountQuery");
                sb = sb.replace("@SELECT", isCount ? " SELECT COUNT (*) FROM ( " + SQlUtil.getQuery("ASAdjustmentDetailReserveLoadDataQuery") + " )A " : SQlUtil.getQuery("ASAdjustmentDetailReserveLoadDataQuery"));
                sb = sb.replace("@CATEGORY", category);
                sb = sb.replace("@TYPE", type);
                sb = sb.replace("@ACCOUNT", account);
                sb = sb.replaceAll("@ADJUSTMENT_TYPE", adjustmentDetailSelection);
                sb = sb.replace("@PROJECTIONMASTER", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
                sb = sb.replace("@ADJUSTMENTLEVELBRAND", adjustmentLevel != null ? adjustmentLevel[0] : StringUtils.EMPTY);
                sb = sb.replace("@ADJUSTMENTLEVELTOTAL", adjustmentLevel != null ? adjustmentLevel[1] : StringUtils.EMPTY);
                sb = sb.replace("@FROMDATE", /*"2016-07-30"*/ dateFormat.format(sdf.parse(selection.getDataSelectionDTO().getFromPeriod())));
                sb = sb.replace("@TODATE", /*"2016-10-30"*/ dateFormat.format(sdf.parse(selection.getDataSelectionDTO().getToPeriod())));
                sb = sb.replace("@CONFIGURATIONTYPE", String.valueOf(configLevelid));
                String status = "0".equals(selection.getStatus()) ? StringUtils.EMPTY : selection.getStatus();
                status = status.replace("[", "").replace("]", "");
                sb = sb.replace("@WORKFLOWSTATUS", status);
                sb = sb.replace("@PAGINATION", isCount ? StringUtils.EMPTY : " ORDER BY AAD.LINE_DESCRIPTION,UDC1.DESCRIPTION,HT.DESCRIPTION,A.BRAND_ID OFFSET " + String.valueOf(start) + " ROWS FETCH NEXT " + String.valueOf(offset) + " ROWS ONLY; ");
            } else {
                List<HelperDTO> gtnList = helperId.getListNameMap().get("ARM_GTN_ADJUSTMENT_LEVEL");
                if (gtnList != null) {
                    gtnAdjustmentLevel = new String[NumericConstants.TWO];
                    for (HelperDTO dto : gtnList) {
                        if ("ITEM".equalsIgnoreCase(dto.getDescription())) {
                            gtnAdjustmentLevel[0] = String.valueOf(dto.getId());
                        }
                    }
                }
                adjDetail = selection.getSelectedAdjustmentTypeValues();
                if (adjDetail != null && !adjDetail.isEmpty()) {
                    adjustmentDetailSelection = StringUtils.join(adjDetail.toArray(),"','");
                }
                sb = SQlUtil.getQuery("ASAdjustmentDetailGTNCountQuery");
                sb = sb.replace("@SELECT", isCount ? " SELECT COUNT (*) FROM ( " + SQlUtil.getQuery("ASAdjustmentDetailGTNLoadDataQuery") + " )A " : SQlUtil.getQuery("ASAdjustmentDetailGTNLoadDataQuery"));
                sb = sb.replace("@PROJECTIONMASTER", /*String.valueOf(1131)*/ String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
                sb = sb.replace("@GLCOMP", /*String.valueOf(519461)*/ String.valueOf(selection.getDataSelectionDTO().getCompanyMasterSid()));
                sb = sb.replace("@FROMDATE", /*"2016-07-30"*/ dateFormat.format(sdf.parse(selection.getDataSelectionDTO().getFromPeriod())));
                sb = sb.replace("@TODATE", /*"2016-10-30"*/ dateFormat.format(sdf.parse(selection.getDataSelectionDTO().getToPeriod())));
                sb = sb.replace("@CATEGORY", category);
                sb = sb.replace("@TYPE", type);
                sb = sb.replace("@ACCOUNT", account);
                sb = sb.replaceAll("@ADJUSTMENT_TYPE", "'"+adjustmentDetailSelection+"'");
                sb = sb.replace("@ACCDESC", accountDesc);
                sb = sb.replace("@CONFIGURATIONTYPE", String.valueOf(configLevelid));
                String status = "0".equals(selection.getStatus()) ? StringUtils.EMPTY : selection.getStatus();
                status = status.replace("[", "").replace("]", "");
                sb = sb.replace("@WORKFLOWSTATUS", status);
                sb = sb.replace("@ADJUSTMENT_LEVEL", gtnAdjustmentLevel != null ? gtnAdjustmentLevel[0] : StringUtils.EMPTY);
                sb = sb.replace("@PAGINATION", isCount ? StringUtils.EMPTY : " ORDER BY UDC1.DESCRIPTION,RTYPE.DESCRIPTION,TP.APPRVD_TRANSACTION_NAME,A.BRAND_ID OFFSET " + String.valueOf(start) + " ROWS FETCH NEXT " + String.valueOf(offset) + " ROWS ONLY; ");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        if (category.isEmpty() || type.isEmpty() || account.isEmpty()) {
            return StringUtils.EMPTY;
        }
        return sb;
    }

    @Override
    public List<List> getReserveAccountDetails(AbstractSelectionDTO selection, Boolean isReserve) {
        List replaceList = new ArrayList();
        List<String> reserveHeader = new ArrayList<>();
        List<String> reserveProperty = new ArrayList<>();
        List<List> finalList = new ArrayList<>();
        String value = StringUtils.EMPTY;
        String property = StringUtils.EMPTY;
        String isReserveValue = isReserve ? "0" : "1";
        String adjustmentDetailSelection = StringUtils.EMPTY;
        List adjDetail = selection.getSelectedAdjustmentType();
        if (adjDetail != null && !adjDetail.isEmpty()) {
            for (int i = 0; i < adjDetail.size(); i++) {
                if (i == 0) {
                    adjustmentDetailSelection = adjDetail.get(i).toString();
                } else {
                    adjustmentDetailSelection += "," + adjDetail.get(i).toString();
                }
            }
            replaceList.add(isReserveValue);
            replaceList.add(adjustmentDetailSelection);
            replaceList.add(selection.getDataSelectionDTO().getProjectionId());
            replaceList.add(selection.getDataSelectionDTO().getProjectionId());
            replaceList.add(selection.getDataSelectionDTO().getCompanyMasterSid());
            replaceList.add(selection.getDataSelectionDTO().getBu_companyMasterSid());
            StringBuilder query = new StringBuilder(SQlUtil.getQuery("getReserveAccountSummary"));
            for (Object temp : replaceList) {
                query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(temp));
            }
            List list = QueryUtils.executeSelect(query.toString());
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    value = StringUtils.EMPTY;
                    property = StringUtils.EMPTY;
                    if (isValid(obj[0])) {
                        value = helperId.getDescriptionByID(Integer.valueOf(String.valueOf(obj[0])));
                        property = String.valueOf(obj[0]);
                    }
                    if (isValid(obj[1])) {
                        value += DASH + helperId.getDescriptionByID(Integer.valueOf(String.valueOf(obj[1])));
                        property += DASH + String.valueOf(obj[1]);
                    }
                    if (isValid(obj[NumericConstants.TWO])) {
                        value += DASH + String.valueOf(obj[NumericConstants.TWO]);
                        property += DASH + String.valueOf(obj[NumericConstants.TWO]);
                    }
                    if (isValid(obj[NumericConstants.THREE])) {
                        value += DASH + String.valueOf(obj[NumericConstants.THREE]);
                        property += DASH + String.valueOf(obj[NumericConstants.THREE]);
                    }
                    reserveHeader.add(value);
                    reserveProperty.add(property);
                }
                finalList.add(reserveHeader);
                finalList.add(reserveProperty);
            }
        }
        return finalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForEdit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected CharSequence getRateColumn() {
        return "B.RATE";
    }

    @Override
    protected String getAmountFilterCondition(List<String> condition, String tableAliasName) {
        return "";
    }

}
