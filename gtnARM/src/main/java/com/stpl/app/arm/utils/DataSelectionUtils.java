/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.utils;

import com.stpl.app.arm.dataselection.dto.LevelDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.TreeTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author 
 */
public class DataSelectionUtils {

    public static List<Integer> getSelectedRelationshipLevelSids(List<LevelDTO> itemIds) {
        List<Integer> selectedRelationshipLevelSids = null;
        if (itemIds != null && !itemIds.isEmpty()) {
            LevelDTO dto;
            selectedRelationshipLevelSids = new ArrayList<Integer>();
            for (Object item : itemIds) {
                dto = getBeanFromId(item);
                selectedRelationshipLevelSids.add(dto.getRelationshipLevelSid());
            }
        }
        return selectedRelationshipLevelSids;
    }

    public static LevelDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof LevelDTO) {
            targetItem = new BeanItem<LevelDTO>((LevelDTO) obj);
        }

        return (LevelDTO) targetItem.getBean();
    }

    public static String identifyLevel(LevelDTO levelDTO) {
        String level = StringUtils.EMPTY;
        if (!StringUtils.isBlank(levelDTO.getLevel()) && !ARMUtils.NULL.equalsIgnoreCase(levelDTO.getLevel())) {
            if (ARMUtils.COMPANY_MASTER.equalsIgnoreCase(levelDTO.getTableName())
                    && (ARMUtils.CUSTOMER_SMALL.equalsIgnoreCase(levelDTO.getLevel())
                    || ARMUtils.COMPANY_SMALL.equalsIgnoreCase(levelDTO.getLevel())
                    || ARMUtils.TRADING_PARTNER.equalsIgnoreCase(levelDTO.getLevel()))) {
                level = ARMUtils.INDICATOR_LEVEL_CUSTOMER;
            } else if (ARMUtils.CONTRACT_MASTER.equalsIgnoreCase(levelDTO.getTableName())
                    && levelDTO.getLevel().contains(ARMUtils.CONTRACT_SMALL)) {
                level = ARMUtils.INDICATOR_LEVEL_CONTRACT;
            } else if (ARMUtils.ITEM_MASTER.equalsIgnoreCase(levelDTO.getTableName())
                    && (levelDTO.getLevel().contains(ARMUtils.NDC)
                    || "Item".equalsIgnoreCase(levelDTO.getLevel()))) {
                level = ARMUtils.INDICATOR_LEVEL_NDC;
            }
        }
        return level;
    }

    public static List<String> getEndLevelHierNo(List<LevelDTO> levels) {
        List<String> hierNos = new ArrayList<String>();
        for (LevelDTO level : levels) {
            hierNos.add(String.valueOf(level.getHierarchyNo()));
        }
        return hierNos;
    }

    public static List<String> storeUncommonValues(List<String> list1, List<String> list2) {
        // Prepare a union
        List<String> union = new ArrayList<String>(list1);
        union.addAll(list2);
        // Prepare an intersection
        List<String> intersection = new ArrayList<String>(list1);
        intersection.retainAll(list2);
        // Subtract the intersection from the union
        union.removeAll(intersection);
        return union;
    }

    public static void removeItemsRecursively(final Object item, final TreeTable selectedTable, final ExtFilterTable availableTable,
            final ExtTreeContainer<LevelDTO> selectedContainer, final BeanItemContainer<LevelDTO> availableContainer, final int currentLevel) {
        if (selectedTable.hasChildren(item)) {
            Collection<?> children = selectedTable.getChildren(item);
            if (children != null && children.size() > 0) {
                BeanItemContainer<LevelDTO> tempBean = new BeanItemContainer<LevelDTO>(LevelDTO.class);
                LinkedList<Object> children2 = new LinkedList<Object>();
                for (Iterator<?> i = children.iterator(); i.hasNext();) {
                    children2.add((Object) i.next());
                }
                for (Iterator<Object> i = children2.iterator(); i.hasNext();) {
                    Object child = i.next();
                    LevelDTO beanItem = getBeanFromId(child);
                    tempBean.addBean(beanItem);
                    removeItemsRecursively(child, selectedTable, availableTable, selectedContainer, availableContainer, currentLevel);

                    selectedTable.removeItem(child);
                    selectedContainer.removeItemRecursively(child);
                }
            }
        }
    }

    public static List<LevelDTO> getFSValue(final String relationshipLevelValue, final String fieldName) {
        List<LevelDTO> list = new ArrayList<LevelDTO>();
        DataSelectionLogic logic = new DataSelectionLogic();
        List<Object> listValue = logic.getFSValue(relationshipLevelValue, fieldName);
        LevelDTO dto = new LevelDTO();
        for (int i = 0; i < listValue.size(); i++) {
            Object[] obj = (Object[]) listValue.get(i);
            dto.setForm(String.valueOf(obj[0]));
            dto.setStrength(String.valueOf(obj[1]));
            list.add(dto);
        }
        return list;
    }

    public static String getTimePeriod(Date date) throws ParseException {
        String timePeriod = StringUtils.EMPTY;
        SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
        int quarter = getQuarter(date);
        int year = Integer.parseInt(getYear.format(date));
        if (date != null) {
            timePeriod = "Q" + quarter + " - " + year;
        }
        return timePeriod;
    }

    private static int getQuarter(Date end) {
        SimpleDateFormat getMonth = new SimpleDateFormat(ARMUtils.MM);
        return Integer.parseInt(getMonth.format(end)) % NumericConstants.THREE == 0 ? (Integer.parseInt(getMonth.format(end)) / NumericConstants.THREE) : (Integer.parseInt(getMonth.format(end)) / NumericConstants.THREE) + 1;
    }
}
