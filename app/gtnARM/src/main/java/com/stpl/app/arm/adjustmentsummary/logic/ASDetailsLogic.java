/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentsummary.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic;
import static com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractAdjustmentDetailsLogic.LOGGER;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import static com.stpl.app.arm.utils.ARMUtils.COMMA;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import static com.stpl.app.utils.VariableConstants.DASH;
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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getQuery(SelectionDTO selection, Boolean isCount, int start, int offset) {
        LOGGER.debug(" Inside GetQuery method ");
        String sb = StringUtils.EMPTY;
        StringBuilder category = new StringBuilder();
        StringBuilder type = new StringBuilder();
        StringBuilder account = new StringBuilder();
        StringBuilder accountDesc = new StringBuilder();
        String[] adjustmentLevel = null;
        String[] gtnAdjustmentLevel = null;
        if (selection.getDetailreserveAcount() != null) {
            for (int i = 0; i < selection.getDetailreserveAcount().size(); i++) {
                String[] str = selection.getDetailreserveAcount().get(i).split(" - ");
                if (i == 0) {
                    category = new StringBuilder(str[0].trim());
                    type = new StringBuilder(str[1].trim());
                    account = new StringBuilder(ARMUtils.SINGLE_QUOTES);
                    account.append(str[NumericConstants.TWO].trim()).append(ARMUtils.SINGLE_QUOTES);
                    if (str.length == NumericConstants.FOUR) {
                        accountDesc = new StringBuilder(ARMUtils.SINGLE_QUOTES);
                        accountDesc.append(str[NumericConstants.THREE].trim()).append(ARMUtils.SINGLE_QUOTES);
                    }

                } else {
                    category.append(ARMUtils.COMMA_CHAR).append(str[0].trim());
                    type.append(ARMUtils.COMMA_CHAR).append(str[1].trim());
                    account.append(ARMUtils.COMMA_CHAR).append(ARMUtils.SINGLE_QUOTES).append(str[NumericConstants.TWO].trim()).append(ARMUtils.SINGLE_QUOTES);
                    if (str.length == NumericConstants.FOUR) {
                        accountDesc.append(ARMUtils.COMMA_CHAR).append(ARMUtils.SINGLE_QUOTES).append(str[NumericConstants.THREE].trim()).append(ARMUtils.SINGLE_QUOTES);
                    }
                }
            }
        }
        List<HelperDTO> list = helperId.getListNameMap().get("ARM_RES_ADJUSTMENT_LEVEL");
        if (list != null) {
            adjustmentLevel = new String[NumericConstants.TWO];
            for (HelperDTO dto : list) {
                if ("BRAND".equalsIgnoreCase(dto.getDescription())) {
                    adjustmentLevel[0] = String.valueOf(dto.getId());
                } else {
                    adjustmentLevel[1] = String.valueOf(dto.getId());
                }
            }
        }
        int configLevelid = helperId.getIdByDesc("ARM_CONFIGURATION_TYPE", selection.getDetailLevel());
        StringBuilder adjustmentDetailSelection = new StringBuilder();
        List adjDetail = null;
        try {
            if (GlobalConstants.getReserveDetail().equals(selection.getDetailLevel())) {
                adjDetail = selection.getSelectedAdjustmentType();
                if (adjDetail != null && !adjDetail.isEmpty()) {
                    for (int i = 0; i < adjDetail.size(); i++) {
                        if (i == 0) {
                            adjustmentDetailSelection = new StringBuilder(adjDetail.get(i).toString());
                        } else {
                            adjustmentDetailSelection.append(ARMUtils.COMMA_CHAR).append(adjDetail.get(i).toString());
                        }
                    }
                }

                sb = SQlUtil.getQuery("ASAdjustmentDetailReserveCountQuery");
                sb = sb.replace("@SELECT", isCount ? " SELECT COUNT (*) FROM ( " + SQlUtil.getQuery("ASAdjustmentDetailReserveLoadDataQuery") + " )A " : SQlUtil.getQuery("ASAdjustmentDetailReserveLoadDataQuery"));
                sb = sb.replace("@CATEGORY", category.toString());
                sb = sb.replace("@TYPE", type.toString());
                sb = sb.replace("@ACCOUNT", account.toString());
                sb = sb.replaceAll("@ADJUSTMENT_TYPE", adjustmentDetailSelection.toString());
                sb = sb.replace("@PROJECTIONMASTER", String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
                sb = sb.replace("@ADJUSTMENTLEVELBRAND", adjustmentLevel != null ? adjustmentLevel[0] : StringUtils.EMPTY);
                sb = sb.replace("@ADJUSTMENTLEVELTOTAL", adjustmentLevel != null ? adjustmentLevel[1] : StringUtils.EMPTY);
                sb = sb.replace("@FROMDATE", /*"2016-07-30"*/ dateFormat.format(sdf.parse(selection.getDataSelectionDTO().getFromPeriod())));
                sb = sb.replace("@TODATE", /*"2016-10-30"*/ dateFormat.format(sdf.parse(selection.getDataSelectionDTO().getToPeriod())));
                sb = sb.replace("@CONFIGURATIONTYPE", String.valueOf(configLevelid));
                String status = "0".equals(selection.getStatus()) ? StringUtils.EMPTY : selection.getStatus();
                status = status.replace("[", "").replace("]", "");
                sb = sb.replace("@WORKFLOWSTATUS", status);
                sb = sb.replace("@PAGINATION", isCount ? StringUtils.EMPTY : " ORDER BY AAD.LINE_DESCRIPTION,UDC1.DESCRIPTION,HT.DESCRIPTION,A.BRAND_ID OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY; ");
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
                    adjustmentDetailSelection = new StringBuilder(StringUtils.join(adjDetail.toArray(), "','"));
                }
                sb = SQlUtil.getQuery("ASAdjustmentDetailGTNCountQuery");
                sb = sb.replace("@SELECT", isCount ? " SELECT COUNT (*) FROM ( " + SQlUtil.getQuery("ASAdjustmentDetailGTNLoadDataQuery") + " )A " : SQlUtil.getQuery("ASAdjustmentDetailGTNLoadDataQuery"));
                sb = sb.replace("@PROJECTIONMASTER", /*String.valueOf(1131)*/ String.valueOf(selection.getDataSelectionDTO().getProjectionId()));
                sb = sb.replace("@GLCOMP", /*String.valueOf(519461)*/ String.valueOf(selection.getDataSelectionDTO().getCompanyMasterSid()));
                sb = sb.replace("@FROMDATE", /*"2016-07-30"*/ dateFormat.format(sdf.parse(selection.getDataSelectionDTO().getFromPeriod())));
                sb = sb.replace("@TODATE", /*"2016-10-30"*/ dateFormat.format(sdf.parse(selection.getDataSelectionDTO().getToPeriod())));
                sb = sb.replace("@CATEGORY", category.toString());
                sb = sb.replace("@TYPE", type.toString());
                sb = sb.replace("@ACCOUNT", account.toString());
                sb = sb.replaceAll("@ADJUSTMENT_TYPE", StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + adjustmentDetailSelection.toString() + ARMUtils.SINGLE_QUOTES);
                sb = sb.replace("@ACCDESC", accountDesc.toString());
                sb = sb.replace("@CONFIGURATIONTYPE", String.valueOf(configLevelid));
                String status = "0".equals(selection.getStatus()) ? StringUtils.EMPTY : selection.getStatus();
                status = status.replace("[", "").replace("]", "");
                sb = sb.replace("@WORKFLOWSTATUS", status);
                sb = sb.replace("@ADJUSTMENT_LEVEL", gtnAdjustmentLevel != null ? gtnAdjustmentLevel[0] : StringUtils.EMPTY);
                sb = sb.replace("@PAGINATION", isCount ? StringUtils.EMPTY : " ORDER BY UDC1.DESCRIPTION,RTYPE.DESCRIPTION,TP.APPRVD_TRANSACTION_NAME,A.BRAND_ID OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY; ");
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getQuery :", ex);
        }
        if (category.length() == 0 || type.length() == 0 || account.length() == 0) {
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
        String isReserveValue = isReserve ? "0" : "1";
        StringBuilder adjustmentDetailSelection = new StringBuilder();
        List adjDetail = selection.getSelectedAdjustmentType();
        if (adjDetail != null && !adjDetail.isEmpty()) {
            for (int i = 0; i < adjDetail.size(); i++) {
                if (i == 0) {
                    adjustmentDetailSelection = new StringBuilder(adjDetail.get(i).toString());
                } else {
                    adjustmentDetailSelection.append(ARMUtils.COMMA_CHAR).append(adjDetail.get(i).toString());
                }
            }
            replaceList.add(isReserveValue);
            replaceList.add(adjustmentDetailSelection);
            replaceList.add(selection.getDataSelectionDTO().getProjectionId());
            replaceList.add(selection.getDataSelectionDTO().getProjectionId());
            replaceList.add(selection.getDataSelectionDTO().getCompanyMasterSid());
            replaceList.add(selection.getDataSelectionDTO().getBucompanyMasterSid());
            StringBuilder query = new StringBuilder(SQlUtil.getQuery("getReserveAccountSummary"));
            for (Object temp : replaceList) {
                query.replace(query.indexOf("?"), query.indexOf("?") + 1, String.valueOf(temp));
            }
            List list = QueryUtils.executeSelect(query.toString());
            finalList = getFinallist(list, reserveHeader, reserveProperty);
        }
        return finalList;
    }

    private List<List> getFinallist(List list, List<String> reserveHeader, List<String> reserveProperty) {
        List<List> finalList = new ArrayList<>();
        StringBuilder value;
        StringBuilder property;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                value = new StringBuilder("");
                property = new StringBuilder("");

                if (isValid(obj[0])) {
                    value = new StringBuilder(helperId.getDescriptionByID((Integer) (obj[0])));
                    property = new StringBuilder(String.valueOf(obj[0]));
                }
                if (isValid(obj[1])) {
                    value.append(DASH).append(helperId.getDescriptionByID((Integer) (obj[1])));
                    property.append(DASH).append(String.valueOf(obj[1]));
                }
                if (isValid(obj[NumericConstants.TWO])) {
                    value.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                    property.append(DASH).append(String.valueOf(obj[NumericConstants.TWO]));
                }
                if (isValid(obj[NumericConstants.THREE])) {
                    value.append(DASH).append(String.valueOf(obj[NumericConstants.THREE]));
                    property.append(DASH).append(String.valueOf(obj[NumericConstants.THREE]));
                }
                reserveHeader.add(value.toString());
                reserveProperty.add(property.toString());
            }
            finalList.add(reserveHeader);
            finalList.add(reserveProperty);
        }
        return finalList;
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForView() {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getTableNameForEdit() {
        throw new UnsupportedOperationException(CommonConstant.NOT_SUPPORTED_YET); //To change body of generated methods, choose Tools | Templates.
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
