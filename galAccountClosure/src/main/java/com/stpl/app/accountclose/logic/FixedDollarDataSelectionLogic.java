/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import static com.stpl.app.accountclose.common.CommonLogic.LOGGER;
import com.stpl.app.accountclose.common.CommonQuery;
import com.stpl.app.accountclose.common.FixedDollarQuery;
import com.stpl.app.accountclose.dao.FixedDollarDAO;
import com.stpl.app.accountclose.dao.impl.FixedDollarDAOImpl;
import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class FixedDollarDataSelectionLogic {

    FixedDollarDAO dao = new FixedDollarDAOImpl();
    FixedDollarQuery fixedDollarQuery = new FixedDollarQuery();
    CommonQuery commonQuery = new CommonQuery();

    public List<FixedDollarDTO> getConfigureFixedDollarLoadData(Object parentId, FixedDollarDTO fixedDollarDTO) {
        List<FixedDollarDTO> salesList;
        if (parentId instanceof FixedDollarDTO) {
            FixedDollarDTO dto = (FixedDollarDTO) parentId;
            fixedDollarDTO.setCcpSid(dto.getCcpSid());
            if (fixedDollarDTO.getViewtype().equalsIgnoreCase("0")) {
                fixedDollarDTO.setLevelNo(dto.getLevelNo() + 1);
            } else {
                fixedDollarDTO.setLevelNo(dto.getLevelNo() - 1);
            }
            fixedDollarDTO.setIdMainMap(getFdResultLevelMap(fixedDollarDTO, dto));
        } else {
            if (fixedDollarDTO.getViewtype().equalsIgnoreCase("0")) {
                fixedDollarDTO.setLevelNo(1);
            } else {
                fixedDollarDTO.setLevelNo(5);
            }
        }
        salesList = configureFdResultLevelSales(fixedDollarDTO);

        return salesList;
    }

    private Map<Integer, Map<String, String>> getFdResultLevelMap(FixedDollarDTO fixedDollarDTO, FixedDollarDTO dto) {
        Map<String, String> idMap = new HashMap<String, String>();
        Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>();
        if (fixedDollarDTO.getLevelNo() == 1) {
            idMap.put("Contract", fixedDollarDTO.getContractSid());
        } else if (fixedDollarDTO.getLevelNo() == 2) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getCompanyMasterSid());
            idMainMap.put(fixedDollarDTO.getLevelNo(), idMap);
        } else if (fixedDollarDTO.getLevelNo() == 3) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getCompanyMasterSid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMainMap.put(fixedDollarDTO.getLevelNo(), idMap);
        } else if (fixedDollarDTO.getLevelNo() == 4) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getCompanyMasterSid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMap.put("item", dto.getItemSid());
            idMainMap.put(fixedDollarDTO.getLevelNo(), idMap);
        } else if (fixedDollarDTO.getLevelNo() == 5) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getCompanyMasterSid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMap.put("rebate", dto.getRebateId());
            idMap.put("item", dto.getItemSid());
            idMainMap.put(fixedDollarDTO.getLevelNo(), idMap);
        }
        return idMainMap;
    }

    public List<FixedDollarDTO> configureFdResultLevelSales(FixedDollarDTO fixedDollarDTO) {
        LOGGER.info("Entering configureLevelSales" + fixedDollarDTO.getLevelNo());
        List<FixedDollarDTO> resultList = new ArrayList<FixedDollarDTO>();
        try {
            String query = fixedDollarQuery.getLoadFdResultsDataQuery(fixedDollarDTO);
            query += " OFFSET " + fixedDollarDTO.getStartIndex() + " ROWS FETCH NEXT " + fixedDollarDTO.getOffSet() + "  ROWS ONLY;";
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            FixedDollarDTO dto;
            for (Object object : list) {
                final Object[] obj = (Object[]) object;
                dto = new FixedDollarDTO();
                dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                if (fixedDollarDTO.getLevelNo() == 1) {
                    dto.setCompanyMasterSid(obj[1].toString());
                }
                dto.setParent(1);
                if ((fixedDollarDTO.getLevelNo() == 5 && fixedDollarDTO.getViewtype().equalsIgnoreCase("0"))
                        || fixedDollarDTO.getLevelNo() == 1 && fixedDollarDTO.getViewtype().equalsIgnoreCase("1")) {
                    dto.setParent(0);
                }

                dto.setLevelNo(fixedDollarDTO.getLevelNo());
                if (fixedDollarDTO.getCalculate().equalsIgnoreCase("0")) {
                    dto.setCcpSid(String.valueOf(obj[2]));
                }
                if (fixedDollarDTO.getCalculate().equalsIgnoreCase("1")) {
                    if (obj[1] != null) {

                        dto.setAccrualPeriodSales("$" + String.format("%.2f", new BigDecimal(obj[1].toString())));
                    }
                    if (obj[2] != null) {
                        dto.setAccrualPeriodAccruals("$" + String.format("%.2f", new BigDecimal(obj[2].toString())));
                    }
                    if (obj[3] != null) {
                        dto.setAutoAccruals("$" + String.format("%.2f", new BigDecimal(obj[3].toString())));
                    }
                    if (obj[4] != null) {
                        dto.setManualAdjustment("$" + String.format("%.2f", new BigDecimal(obj[4].toString())));
                    }
                    if (obj[5] != null) {
                        dto.setPaymentTrueUp("$" + String.format("%.2f", new BigDecimal(obj[5].toString())));
                    }
                    if (obj[6] != null) {

                        dto.setOther("$" + String.format("%.2f", new BigDecimal(obj[6].toString())));
                    }
                    if (obj[7] != null) {
                        dto.setProjectedDisc("$" + String.format("%.2f", new BigDecimal(obj[7].toString())));
                    }
                    if (obj[8] != null) {
                        dto.setPercentageAccrualToProj("$" + String.format("%.2f", new BigDecimal(obj[8].toString())));

                    }
                    if (obj[9] != null) {
                        dto.setPercentageActualToProj("$" + String.format("%.2f", new BigDecimal(obj[9].toString())));

                    }
                    if (obj[10] != null) {
                        dto.setAccrualPeriodActuals("$" + String.format("%.2f", new BigDecimal(obj[10].toString())));

                    }

                    if (obj[11] != null) {
                        dto.setVariance("$" + String.format("%.2f", new BigDecimal(obj[11].toString())));

                    }
                    if (obj[12] != null) {
                        dto.setSuggestedAdj("$" + String.format("%.2f", new BigDecimal(obj[12].toString())));

                    }
                    if (obj[13] != null) {
                        dto.setPercentageRelease("$" + String.format("%.2f", new BigDecimal(obj[13].toString())));
                    }
                    if (obj[14] != null) {
                        dto.setManualAmount("$" + String.format("%.2f", new BigDecimal(obj[14].toString())));
                    }
                    if (obj[15] != null) {
                        dto.setTotalAdjustmentDollar("$" + String.format("%.2f", new BigDecimal(obj[15].toString())));

                    }
                    if (obj[16] != null) {

                        dto.setTotalAdjustmentDollarPerDay("$" + String.format("%.2f", new BigDecimal(obj[16].toString())));
                    }
                    if (obj[17] != null) {
                        dto.setReasonCode(obj[17].toString());

                    }
                    if (obj[18] != null) {

                        dto.setNotes(obj[18].toString());
                    }
                    if (obj[19] != null) {
                        dto.setCcpSid(obj[19].toString());
                    }

                }
                resultList.add(dto);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending configureLevelSales" + resultList.size());
        return resultList;
    }

    public int getConfigureFdResultCount(Object parentId, FixedDollarDTO fixedDollarDTO) {
        LOGGER.info("Entering getConfigureCount");
        int count = 0;
        if (parentId instanceof FixedDollarDTO) {
            FixedDollarDTO dto = (FixedDollarDTO) parentId;
            fixedDollarDTO.setCcpSid(dto.getCcpSid());
            if (fixedDollarDTO.getViewtype().equalsIgnoreCase("0")) {
                fixedDollarDTO.setLevelNo(dto.getLevelNo() + 1);
            } else {
                fixedDollarDTO.setLevelNo(dto.getLevelNo() - 1);
            }
            fixedDollarDTO.setIdMainMap(getFdResultLevelMap(fixedDollarDTO, dto));
        } else {
            if (fixedDollarDTO.getViewtype().equalsIgnoreCase("0")) {
                fixedDollarDTO.setLevelNo(1);
            } else {
                fixedDollarDTO.setLevelNo(5);
            }
        }
        /*   if (fixedDollarDTO.getLevelNo() == 1) {
         fixedDollarDTO.setCountFlag("getFixedDollarTreeCustomerCount");
         } else if (fixedDollarDTO.getLevelNo() == 2) {
         fixedDollarDTO.setCountFlag("getFixedDollarTreeContractCount");
         } else if (fixedDollarDTO.getLevelNo() == 3) {
         fixedDollarDTO.setCountFlag("getFixedDollarTreeTheraCount");
         } else if (fixedDollarDTO.getLevelNo() == 4) {
         fixedDollarDTO.setCountFlag("getfixedDollarTreeBrandCount");
         } else if (fixedDollarDTO.getLevelNo() == 5) {
         fixedDollarDTO.setCountFlag("getFixedDollarTreeItemCount");
         }

         int count = configureFdResultLevelCount(fixedDollarDTO);
         LOGGER.info("Ending getConfigureCount" + count); */
        String query = fixedDollarQuery.getLoadFdResultsDataQuery(fixedDollarDTO);
        try {
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            count = list.size();
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    public int configureFdResultLevelCount(FixedDollarDTO fixedDollarDTO) {
        List input = new ArrayList();
        Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>(fixedDollarDTO.getIdMainMap());
        Map<String, String> idMap = idMainMap.get(fixedDollarDTO.getLevelNo());
        if (fixedDollarDTO.getLevelNo() == 1) {
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            input.add(Integer.parseInt(userId));
            if (fixedDollarDTO.getSessionId() != null && fixedDollarDTO.getSessionId() != StringUtils.EMPTY) {
                input.add(Integer.parseInt(fixedDollarDTO.getSessionId()));
            }
        } else if (fixedDollarDTO.getLevelNo() == 2) {
            input.add(fixedDollarDTO.getContractSid());
        } else if (fixedDollarDTO.getLevelNo() == 3) {
            input.add(fixedDollarDTO.getItemSid());
        } else if (fixedDollarDTO.getLevelNo() == 4) {
            input.add(fixedDollarDTO.getItemSid());
        } else if (fixedDollarDTO.getLevelNo() == 5) {
            input.add(fixedDollarDTO.getItemSid());

        }
        List<Object[]> list = commonQuery.getDdlbData(input, fixedDollarDTO.getCountFlag(), null);
        Object obj = list.get(0);
        return obj == null ? 0 : (Integer) obj;
    }

    public int getConfigureFdCount(Object parentId, TreeDTO fixedDollarDTO) {
         int count = 0;
        try {
            LOGGER.info("Entering getConfigureCount");
          
            if (parentId instanceof TreeDTO) {
                TreeDTO dto = (TreeDTO) parentId;
                fixedDollarDTO.setCcpSid(dto.getCcpSid());
                fixedDollarDTO.setLevelNo(dto.getLevelNo() + 1);
                fixedDollarDTO.setIdMainMap(getFdLevelMap(fixedDollarDTO, dto));
            } else {
                fixedDollarDTO.setLevelNo(1);
            }
            String query = fixedDollarQuery.getLoadFdDataQuery(fixedDollarDTO);
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            count = list.size();
            LOGGER.info("Ending getConfigureCount" + count);
          
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
          return count;
    }

    private Map<Integer, Map<String, String>> getFdLevelMap(TreeDTO fixedDollarDTO, TreeDTO dto) {
        Map<String, String> idMap = new HashMap<String, String>();
        Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>();
        if (fixedDollarDTO.getLevelNo() == 1) {
            idMap.put("Contract", fixedDollarDTO.getContractSid());
        } else if (fixedDollarDTO.getLevelNo() == 2) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getComapnyMasterSid());
            idMainMap.put(fixedDollarDTO.getLevelNo(), idMap);
        } else if (fixedDollarDTO.getLevelNo() == 3) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getComapnyMasterSid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMainMap.put(fixedDollarDTO.getLevelNo(), idMap);
        } else if (fixedDollarDTO.getLevelNo() == 4) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getComapnyMasterSid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMap.put("item", dto.getItemSid());
            idMainMap.put(fixedDollarDTO.getLevelNo(), idMap);
        } else if (fixedDollarDTO.getLevelNo() == 5) {
            idMap.put("Contract", dto.getContractSid());
            idMap.put("Company", dto.getComapnyMasterSid());
            idMap.put("brand", dto.getBrandMasterSid());
            idMap.put("rebate", dto.getRebateId());
            idMap.put("item", dto.getItemSid());
            idMainMap.put(fixedDollarDTO.getLevelNo(), idMap);
        }
        return idMainMap;
    }

    public int configureFdLevelCount(TreeDTO fixedDollarDTO) {
        List input = new ArrayList();
        Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>(fixedDollarDTO.getIdMainMap());
        Map<String, String> idMap = idMainMap.get(fixedDollarDTO.getLevelNo());
        if (fixedDollarDTO.getLevelNo() == 1) {
            input.add(fixedDollarDTO.getContractSid());
            input.add(fixedDollarDTO.getItemSid());
        } else if (fixedDollarDTO.getLevelNo() == 2) {
            input.add(fixedDollarDTO.getContractSid());
        } else if (fixedDollarDTO.getLevelNo() == 3) {
            input.add(fixedDollarDTO.getItemSid());
        } else if (fixedDollarDTO.getLevelNo() == 4) {
            input.add(fixedDollarDTO.getItemSid());
        } else if (fixedDollarDTO.getLevelNo() == 5) {
            input.add(fixedDollarDTO.getItemSid());

        }
        List<Object[]> list = commonQuery.getDdlbData(input, fixedDollarDTO.getCountFlag(), null);
        Object obj = list.get(0);
        return obj == null ? 0 : (Integer) obj;
    }

    public List<TreeDTO> getConfigureFdLoadData(Object parentId, TreeDTO fixedDollarDTO) {
        List<TreeDTO> salesList;
        if (parentId instanceof TreeDTO) {
            TreeDTO dto = (TreeDTO) parentId;
            fixedDollarDTO.setCcpSid(dto.getCcpSid());
            fixedDollarDTO.setLevelNo(dto.getLevelNo() + 1);
            fixedDollarDTO.setIdMainMap(getFdLevelMap(fixedDollarDTO, dto));
        } else {
            fixedDollarDTO.setLevelNo(1);
        }
        salesList = configureFdLevelSales(fixedDollarDTO);

        return salesList;
    }

    public List<TreeDTO> configureFdLevelSales(TreeDTO fixedDollarDTO) {
        LOGGER.info("Entering configureLevelSales" + fixedDollarDTO.getLevelNo());
        List<TreeDTO> resultList = new ArrayList<>();
        try {
            String query = fixedDollarQuery.getLoadFdDataQuery(fixedDollarDTO);
            query += " OFFSET " + fixedDollarDTO.getStartIndex() + " ROWS FETCH NEXT " + fixedDollarDTO.getOffSet() + "  ROWS ONLY;";
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            TreeDTO dto;
            for (Object object : list) {
                final Object[] obj = (Object[]) object;
                dto = new TreeDTO();
                if(fixedDollarDTO.getLevelNo() == 3) {
                dto.setId(obj[0] == null || "-Select One-".equals(String.valueOf(obj[0]))? "No Therapy" : obj[0].toString()); 
                } else {
                dto.setId(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                }
                if (fixedDollarDTO.getLevelNo() == 1) {
                    dto.setComapnyMasterSid(obj[1].toString());
                }
                dto.setParent(1);
                if (fixedDollarDTO.getLevelNo() == 5) {
                    dto.setParent(0);
                }
                dto.setLevelNo(fixedDollarDTO.getLevelNo());
                dto.setCcpSid(String.valueOf(obj[2]));
                if (obj[3] == null) {
                    dto.setActiveAdj("N");
                    dto.setPendingAdj("N");
                } else {
                    if (String.valueOf(obj[3]).equals("Pending")) {
                        dto.setActiveAdj("N");
                        dto.setPendingAdj("Y");
                    }
                    if (String.valueOf(obj[3]).equals("Approved")) {
                        dto.setActiveAdj("Y");
                        dto.setPendingAdj("N");
                    }
                }
                resultList.add(dto);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        LOGGER.info("Ending configureLevelSales" + resultList.size());
        return resultList;
    }

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

    public int callFixedDollarUpdate(boolean checkValue, TreeDTO dto, String moduleName, String userId, String sessionId, String contractSid, String itemSid) {
        int count = 0;
        StringBuilder query = new StringBuilder("   ");
        query.append("UPDATE ST_ACC_CLOSURE_DETAILS SET CHECK_RECORD='").append(checkValue ? 1 : 0).append("'");
        query.append("WHERE CCP_DETAILS_SID='").append(!dto.getCcpSid().equals(StringUtils.EMPTY) ? dto.getCcpSid() : 0).append("'  ");
        query.append(" AND CONTRACT_MASTER_SID = '").append(!contractSid.equals(StringUtils.EMPTY) ? contractSid : 0).append("'  ");
        query.append(" AND COMPANY_MASTER_SID = '").append(!dto.getComapnySid().equals(StringUtils.EMPTY) ? dto.getComapnySid() : 0).append("'  ");
        query.append(" AND ITEM_MASTER_SID = '").append(!itemSid.equals(StringUtils.EMPTY) ? itemSid : 0).append("'  ");
        query.append(" AND MODULE_NAME = '").append(moduleName).append("'");
        query.append(" AND USER_ID = '").append(userId).append("'");
        query.append(" AND SESSION_ID = '").append(sessionId).append("'");

        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());
        return count;
    }

    public int callFixedDollarInsert(boolean checkValue, TreeDTO dto, String moduleName, String userId, String sessionId, String contractSid, String itemSid) {
        int count = 0;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        StringBuilder query = new StringBuilder("   ");
        query.append("INSERT INTO ST_ACC_CLOSURE_DETAILS ");
        query.append(" (CHECK_RECORD, CCP_DETAILS_SID, CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, MODULE_NAME, USER_ID, SESSION_ID, LAST_MODIFIED_DATE)  ");
        query.append("VALUES(  ");
        query.append("'").append(checkValue ? 1 : 0).append("'");
        query.append(",").append(!dto.getCcpSid().equals(StringUtils.EMPTY) ? dto.getCcpSid() : 0);
        query.append(",").append(!contractSid.equals(StringUtils.EMPTY) ? contractSid : 0);
        query.append(",").append(!dto.getComapnySid().equals(StringUtils.EMPTY) ? dto.getComapnySid() : 0);
        query.append(",").append(!itemSid.equals(StringUtils.EMPTY) ? itemSid : 0);
        query.append(",'").append(moduleName).append("'");
        query.append(",").append(userId);
        query.append(",").append(sessionId);
        query.append(",").append("'").append(format.format(new Date())).append("'");
        query.append("  )");
        count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query.toString());

        return count;
    }

    public static String createSessionId() {
        final Date tempDate = new Date();
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
        return fmtID.format(tempDate);
    }
}
