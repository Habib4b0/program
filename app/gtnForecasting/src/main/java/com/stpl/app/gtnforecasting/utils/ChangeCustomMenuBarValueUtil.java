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

    public static String getMenuItemToDisplay(CustomMenuBar.CustomMenuItem customerFilterValues) {
        String defaultValue = "-Select Level-";
        List<Object> captionList = new ArrayList<>();
        if (customerFilterValues != null && customerFilterValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = customerFilterValues.getChildren();

            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {

                CustomMenuBar.CustomMenuItem customItem1 = it.next();
                if (customItem1.isChecked() && !customItem1.getMenuItem().getCaption().equals("Select All")) {
                    captionList.add(customItem1.getMenuItem().getCaption());
                }
            }
            defaultValue = getCheckedRecordResult(defaultValue, captionList);
        }
        return defaultValue;
    }

    private static String getCheckedRecordResult(String defaultValue, List<Object> recordList) {
        if (recordList.size() == 1) {
            defaultValue = (String) recordList.get(0);
        } else if (recordList.size() > 1) {
            defaultValue = "Multiple";
        }
        return defaultValue;
    }

    public static String getInclusionMenuItemToDisplay(CustomMenuBar.CustomMenuItem salesInclusionValues) {
        String inclusionValue = "-Select Values-";
        List<Object> captionList = new ArrayList<>();
        if (salesInclusionValues != null && salesInclusionValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = salesInclusionValues.getChildren();

            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem = it.next();
                if (customMenuItem.isChecked()) {
                    captionList.add(customMenuItem.getMenuItem().getCaption());
                }
            }
            inclusionValue = getInclusionCheckedResult(inclusionValue, captionList);
        }
        return inclusionValue;
    }

    private static String getInclusionCheckedResult(String inclusionValue, List<Object> captionList) {
        if (captionList.size() == 1) {
            inclusionValue = (String) captionList.get(0);
        } else if (captionList.size() > 1) {
            inclusionValue = "Both";
        }
        return inclusionValue;
    }

    public static void setMenuItemToDisplay(CustomMenuBar menuBar, String caption) {
        if (!menuBar.getItems().isEmpty()) {
            MenuItemDTO customerMenuItemDto = menuBar.getItems().get(0).getMenuItem();
            customerMenuItemDto.setCaption(caption);
            menuBar.markAsDirty();
        }
    }
}
