/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.getCustomViewDetails;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import static com.stpl.app.gtnforecasting.utils.CommonUtils.resetDdlb;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.ui.ComboBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nimisha.Rakesh
 */
public class Utility {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    public static void loadHierarchyList(final SessionDTO session) {
        int producthierarchyLevelNo = CommonUtils.isInteger(session.getProductLevelNumber()) ? Integer.parseInt(session.getProductLevelNumber()) : 0;
        session.setProductHierarchyList(CommonLogic.getProductHierarchy(session.getProjectionId(), producthierarchyLevelNo, session.getProdRelationshipBuilderSid()));
        int customerhierarchyLevelNo = CommonUtils.isInteger(session.getCustomerLevelNumber()) ? Integer.parseInt(session.getCustomerLevelNumber()) : 0;
        session.setCustomerHierarchyList(CommonLogic.getCustomerHierarchy(session.getProjectionId(), customerhierarchyLevelNo, session.getCustRelationshipBuilderSid()));
    }

    //Used to just load the level 
    public static void loadLevelValue(ComboBox level, ComboBox levelFilter, ComboBox populateLevel, final List<Leveldto> currentHierarchy, String view) {
        loadDdlb(level, true, currentHierarchy, view);
        loadDdlb(levelFilter, false, currentHierarchy, view);
        loadDdlb(populateLevel, false, currentHierarchy, view);

    }

    public static void loadLevelValueForResult(ComboBox level, ComboBox levelFilter, ComboBox populateLevel, final List<Leveldto> currentHierarchy, String view) {
        if (level != null) {
            level.setId("levelDdlb");
        }
        loadDdlb(level, false, currentHierarchy, view);
        loadDdlb(levelFilter, false, currentHierarchy, view);
        loadDdlb(populateLevel, false, currentHierarchy, view);

    }

    public static void loadCustomHierarchyList(final SessionDTO session) {
        Map<Integer, List<Leveldto>> hierarchy;
        if (session.getCustomHierarchyMap() == null) {
            hierarchy = new HashMap<>();
        } else {
            hierarchy = session.getCustomHierarchyMap();
        }
        int customId = session.getCustomId();
        if (!session.getCustomHierarchyMap().containsKey(customId)) {
            List<CustomViewDetails> customDetailsList = null;
            List<Leveldto> listValue = new ArrayList<>();
            if (customId != 0) {
                if (session.getCustomDetailMap().get(customId) != null) {
                    customDetailsList = session.getCustomDetailMap().get(customId);
                } else {
                    customDetailsList = getCustomViewDetails(customId);
                    session.getCustomDetailMap().put(customId, customDetailsList);
                }
                if (customDetailsList != null && !customDetailsList.isEmpty()) {
                    StringBuilder relationShipLevelQry = new StringBuilder();
                    relationShipLevelQry.append("select DISTINCT LEVEL_NAME,LEVEL_NO,HIERARCHY_LEVEL_DEFINITION_SID from dbo.RELATIONSHIP_LEVEL_DEFINITION where HIERARCHY_LEVEL_DEFINITION_SID in (");
                    for (int i = 0; i < customDetailsList.size(); i++) {
                        relationShipLevelQry.append(customDetailsList.get(i).getHierarchyId());
                        if (i != customDetailsList.size() - 1) {
                            relationShipLevelQry.append(",");
                        }
                    }

                    relationShipLevelQry.append(")");
                    List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(relationShipLevelQry.toString());
                    /**
                     * assign null to Object , To be destroyed By JVM *
                     */
                    for (CustomViewDetails ob : customDetailsList) {
                        for (Object[] obj : list) {
                            if ((String.valueOf(obj[NumericConstants.TWO]).trim().equals(String.valueOf(ob.getHierarchyId()).trim())) && (obj.length > 1)) {
                                    Leveldto dto = new Leveldto();
                                    dto.setHierarchyId(ob.getHierarchyId());
                                    dto.setLevelNo(Integer.valueOf(String.valueOf((obj[1].toString()).trim())));
                                    dto.setLevel(String.valueOf(obj[0]));
                                    dto.setTreeLevelNo(ob.getLevelNo());
                                    dto.setHierarchyIndicator(ob.getHierarchyIndicator());
                                    listValue.add(dto);
                            }
                        }
                    }
                }
            }
            hierarchy.put(customId, listValue);
            session.setCustomHierarchyMap(hierarchy);
        }
    }

    public static void loadDdlb(ComboBox ddlb, final boolean isExpCol, List<Leveldto> currentHierarchy, String view) {
        if (ddlb != null) {
            resetDdlb(ddlb);
            if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
                int maxLevel = currentHierarchy.size() - 1;
                Collections.sort(currentHierarchy,new Comparator<Leveldto>(){
                	@Override
    				public int compare(Leveldto o1, Leveldto o2) {
    					return o2.getTreeLevelNo()-o1.getTreeLevelNo();
                	}
                });
                Collections.reverse(currentHierarchy);
                for (int i = 0; i < currentHierarchy.size(); i++) {
                    Leveldto levelDto = currentHierarchy.get(i);
                    int level = view.equals(Constant.CUSTOM_LABEL) ? levelDto.getTreeLevelNo() : levelDto.getCount();
                    if (!isExpCol || level <= maxLevel) {
                        Object itemId = null;
                        itemId = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                        ddlb.addItem(itemId);
                        ddlb.setItemCaption(itemId, Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel());
                    }
                }
            }
        }
    }
    public static void loadDdlbForLevelFilterOption(ComboBox ddlb, List<Leveldto> currentHierarchy, String view) {
        if (ddlb != null) {
            resetDdlb(ddlb);
            if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
                int maxLevel = currentHierarchy.size();
                Collections.sort(currentHierarchy,new Comparator<Leveldto>(){
                	@Override
    				public int compare(Leveldto o1, Leveldto o2) {
    					return o2.getTreeLevelNo()-o1.getTreeLevelNo();
                	}
                });
                Collections.reverse(currentHierarchy);
                for (int i = 0; i < currentHierarchy.size(); i++) {
                    Leveldto levelDto = currentHierarchy.get(i);
                    int level = view.equals(Constant.CUSTOM_LABEL) ? levelDto.getTreeLevelNo() : levelDto.getCount();
                    if (level <= maxLevel) {
                        Object itemId = null;
                        itemId = levelDto.getTreeLevelNo();
                        ddlb.addItem(itemId);
                        ddlb.setItemCaption(itemId,levelDto.getLevel());
                    }
                }
            }
        }
    }
    public static void loadDdlbForDeduction(ComboBox ddlb, List<String[]> currentHierarchy) {
        if (ddlb != null) {
            resetDdlb(ddlb);
            if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
                for (int i = 0; i < currentHierarchy.size(); i++) {
                    Object[] levelValues = currentHierarchy.get(i);
                    ddlb.addItem(Integer.parseInt(String.valueOf(levelValues[0])));
                    ddlb.setItemCaption(Integer.parseInt(String.valueOf(levelValues[0])), String.valueOf(levelValues[1]));
                }
            }
        }
    }

    public static void loadLevelDDlbValue(ComboBox level, ComboBox levelFilter, final List<Leveldto> currentHierarchy) {
        loadLevelAndFilterDdlb(level, currentHierarchy);
        loadLevelAndFilterDdlb(levelFilter, currentHierarchy);
    }

    public static void loadLevelAndFilterDdlb(ComboBox ddlb, List<Leveldto> currentHierarchy) {
        if (ddlb != null) {
            resetDdlb(ddlb);
            if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
            	Collections.sort(currentHierarchy,new Comparator<Leveldto>(){
                	@Override
    				public int compare(Leveldto o1, Leveldto o2) {
    					return o2.getTreeLevelNo()-o1.getTreeLevelNo();
                	}
                });
            	Collections.reverse(currentHierarchy);
                for (int i = 0; i < currentHierarchy.size(); i++) {
                    Leveldto levelDto = currentHierarchy.get(i);
                    Object itemId = null;
                    itemId = levelDto.getTreeLevelNo() + "~" + levelDto.getHierarchyIndicator();
                    ddlb.addItem(itemId);
                    ddlb.setItemCaption(itemId, Constant.LEVEL + levelDto.getTreeLevelNo() + " - " + levelDto.getLevel());
                }
            }
        }
    }


    /**
     * Method used to return the Trading Partner level no.
     *
     * @param projectionIdOrCustomId
     * @param sessionDTO
     * @return
     */
    public static int getTradingPartnerLevelNo(int projectionIdOrCustomId, SessionDTO sessionDTO) {
        int levelNo = 0;
        if (sessionDTO.getTradingPartner() == 0) {
            Map<String, List> hierarchyLevelDetailsMap = sessionDTO.getHierarchyLevelDetails();
            for (Map.Entry<String, List> entry : hierarchyLevelDetailsMap.entrySet()) {
                if ("Trading Partner".equals(entry.getValue().get(1).toString())) {
                    levelNo = Integer.parseInt(entry.getValue().get(NumericConstants.TWO).toString());
                    sessionDTO.setTradingPartner(levelNo);
                    return levelNo;
                }
            }
        } else {
            levelNo = sessionDTO.getTradingPartner();
        }
        LOGGER.debug("projectionIdOrCustomId= {}",projectionIdOrCustomId);
        return levelNo;
    }

}
