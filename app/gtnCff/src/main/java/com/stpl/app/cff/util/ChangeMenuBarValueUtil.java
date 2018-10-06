/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinodhini.palanisamy
 */
public class ChangeMenuBarValueUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeMenuBarValueUtil.class);

    private ChangeMenuBarValueUtil() {
        LOGGER.debug("Entering ChangeMenuBarValueUtil ");
    }


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
        String defaultVal = defaultValue;
        if (recordList.size() == 1) {
            defaultVal = (String) recordList.get(0);
        } else if (recordList.size() > 1) {
            defaultVal = "Multiple";
        }
        return defaultVal;
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

    private static String getInclusionCheckedResult(String inclusionValueParam, List<Object> captionList) {
        String inclusionValue = inclusionValueParam;
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
