/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;

/**
 *
 * @author vinodhini.palanisamy
 */
public class ChangeCustomMenuBarValueUtil {
     private ChangeCustomMenuBarValueUtil() {
        // ChangeCustomMenuBarValueUtil
    }

    public static String getMenuItemToDisplay(CustomMenuBar.CustomMenuItem customerFilterValue) {
        String defaultValueMenuDisplay = "-Select Level-";
        List<Object> captionListDisplay = new ArrayList<>();
        if (customerFilterValue != null && customerFilterValue.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> menuItems = customerFilterValue.getChildren();

            for (Iterator<CustomMenuBar.CustomMenuItem> itvalues = menuItems.iterator(); itvalues.hasNext();) {

                CustomMenuBar.CustomMenuItem customItem = itvalues.next();
                if (customItem.isChecked() && !customItem.getMenuItem().getCaption().equals("Select All")) {
                    captionListDisplay.add(customItem.getMenuItem().getCaption());
                }
            }
            defaultValueMenuDisplay = getCheckedRecordResult(defaultValueMenuDisplay, captionListDisplay);
        }
        return defaultValueMenuDisplay;
    }

    private static String getCheckedRecordResult(String defaultValue, List<Object> recordList) {
        String checkDefaultValue =defaultValue; 
        if (recordList.size() == 1) {
            checkDefaultValue = (String) recordList.get(0);
        } else if (recordList.size() > 1) {
            checkDefaultValue = "Multiple";
        }
        return checkDefaultValue;
    }

    public static String getInclusionMenuItemToDisplay(CustomMenuBar.CustomMenuItem salesInclusionValue) {
        String inclusionValueDisplay = "-Select Values-";
        List<Object> captionListInclusionDisplay = new ArrayList<>();
        if (salesInclusionValue != null && salesInclusionValue.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> itemsInclusionDisplay = salesInclusionValue.getChildren();

            for (Iterator<CustomMenuBar.CustomMenuItem> itInclusionValues = itemsInclusionDisplay.iterator(); itInclusionValues.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItemInclusion = itInclusionValues.next();
                if (customMenuItemInclusion.isChecked()) {
                    captionListInclusionDisplay.add(customMenuItemInclusion.getMenuItem().getCaption());
                }
            }
            inclusionValueDisplay = getInclusionCheckedResult(inclusionValueDisplay, captionListInclusionDisplay);
        }
        return inclusionValueDisplay;
    }

    private static String getInclusionCheckedResult(String inclusionValue, List<Object> captionList) {
       String value = inclusionValue;
        if (captionList.size() == 1) {
            value = (String) captionList.get(0);
        } else if (captionList.size() > 1) {
            value = "Both";
        }
        return value;
    }

    public static void setMenuItemToDisplay(CustomMenuBar menuBar, String caption) {
        if (!menuBar.getItems().isEmpty()) {
            MenuItemDTO customerMenuItemDto = menuBar.getItems().get(0).getMenuItem();
            customerMenuItemDto.setCaption(caption);
            menuBar.markAsDirty();
        }
    }
}
