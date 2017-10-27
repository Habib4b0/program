/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author mohamed
 */
public class ResponsiveUtils {

    public static HorizontalLayout addNaviButton(final ExtFilterTable table) {
        final Button prevColumn = new Button("<<");
        final Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("prev-next-layout");
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName("prev-column-button");
        nextColumn.setStyleName("next-column-button");
        addButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }

    public static void addButtonListeners(final ExtFilterTable table, final Button prevColumn, final Button nextColumn) {
        List<Object> collapsedColumns1 = new ArrayList<>();

        for (Object item : table.getVisibleColumns()) {
            if (!table.isColumnCollapsed(item)) {
                collapsedColumns1.add(item);
            }
        }
        prevColumn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<>();
                int lastIndex = 0;
                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }

                visibleColumnsList.indexOf(collapsedColumns.get(0));
                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));

                if ((lastIndex) > 0) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex), true);
                }

                if ((lastIndex) < visibleColumnsList.size()) {
                    nextColumn.setEnabled(true);
                }

                List<Object> collapsedColumns1 = new ArrayList<>();

                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns1.add(item);
                    }
                }

                if (collapsedColumns.size() <= NumericConstants.TWO) {
                    prevColumn.setEnabled(false);
                }

            }
        });

        nextColumn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<>();
                int lastIndex = 0;
                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }

                visibleColumnsList.indexOf(collapsedColumns.get(0));
                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));

                if ((lastIndex + 1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex + 1), false);
                    if ((lastIndex + NumericConstants.TWO) == visibleColumnsList.size()) {
                        nextColumn.setEnabled(false);
                    }
                }

                if ((lastIndex) >= visibleColumnsList.size()) {
                    nextColumn.setEnabled(false);
                }

                List<Object> collapsedColumns1 = new ArrayList<>();

                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns1.add(item);
                    }
                }
                if (collapsedColumns1.size() > 1) {
                    prevColumn.setEnabled(true);
                }
                if (collapsedColumns1.size() >= visibleColumnsList.size()) {
                    prevColumn.setEnabled(true);
                }
            }
        });
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(
                    final Page.BrowserWindowResizeEvent event) {

                int count = 0;
                prevColumn.setEnabled(true);
                nextColumn.setEnabled(true);
                for (Object obj : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(obj)) {
                        count++;
                    }
                }
                if (count <= NumericConstants.TWO) {
                    prevColumn.setEnabled(false);
                }
                if (count >= table.getVisibleColumns().length) {
                    nextColumn.setEnabled(false);
                }

            }
        });

    }
    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout){
        HorizontalLayout controlBar = new HorizontalLayout();
                        controlBar.setStyleName(RESPONSIVE_PAGED_TABLE);
                        
                        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
                        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);
                        
                        CssLayout cssLayout = new CssLayout();
                        cssLayout.setSizeFull();
                        cssLayout.addComponent(pageSize.getComponent(0));
                        cssLayout.addComponent(pageSize.getComponent(0));
                        for(int index=0;index<NumericConstants.EIGHT;index++){
                            cssLayout.addComponent(pageManagement.getComponent(0));
                        }
                        controlBar.addComponent(cssLayout);
                        return controlBar;
    }
    public static final String RESPONSIVE_PAGED_TABLE = "responsivePagedTable";
   
 public static Label makeLabel(String value, boolean isMandatory) {
        StringBuilder sb = new StringBuilder("");
        sb.append(value);
        if (isMandatory) {
            sb.append(" <span style=\"color: #ed473b; padding: 0 0.2em;\">*</span>");
        }
        Label label = new Label(sb.toString(), ContentMode.HTML);
        return label;
    }
    public static Label makeLabel(Label label, boolean isMandatory) {
            StringBuilder sb = new StringBuilder("");
            sb.append(label.getValue());
            if (isMandatory) {
                sb.append(" <span style=\"color: #ed473b; padding: 0 0.2em;\">*</span>");
            }

            label.setValue(sb.toString());        
            return label;
        }
    public static void getResponsiveControls(HorizontalLayout tempLayout, HorizontalLayout controlBar) {

		controlBar.setStyleName(RESPONSIVE_PAGED_TABLE);
		HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
		HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

		CssLayout cssLayout = new CssLayout();
		cssLayout.setSizeFull();
		cssLayout.addComponent(pageSize.getComponent(0));
		cssLayout.addComponent(pageSize.getComponent(0));
		for (int index = 0; index < NumericConstants.EIGHT; index++) {
			cssLayout.addComponent(pageManagement.getComponent(0));
		}
		controlBar.addComponent(cssLayout);

	}
    
    public static HorizontalLayout getResponsiveControl(HorizontalLayout tempLayout) {
        HorizontalLayout controlBar = new HorizontalLayout();
        controlBar.setStyleName(RESPONSIVE_PAGED_TABLE);
        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
        return controlBar;
    }

}
