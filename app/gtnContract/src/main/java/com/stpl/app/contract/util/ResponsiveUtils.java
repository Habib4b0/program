/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.util;

import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

/**
 *
 * @author pvinoth
 */
public class ResponsiveUtils {

    private final static Logger LOGGER = Logger.getLogger(ResponsiveUtils.class);
    public static final String SPAN_STYLECOLOR_PADDING = " <span style=\"color: #ed473b; padding: 0 0.2em;\">*</span>";
    public static final String PREVNEXTLAYOUT = "prev-next-layout";
    public static final String PREVCOLUMNBUTTON = "prev-column-button";
    public static final String NEXTCOLUMNBUTTON = "next-column-button";
    
    public static Label makeLabel(String value, boolean isMandatory) {
        StringBuilder sb = new StringBuilder("");
        sb.append(value);
        if (isMandatory) {
            sb.append(SPAN_STYLECOLOR_PADDING);
        }
        Label label = new Label(sb.toString(), ContentMode.HTML);
        return label;
    }
    
    
    public static Label makeLabel(Label label, boolean isMandatory) {
        StringBuilder sb = new StringBuilder("");
        sb.append(label.getValue());
        if (isMandatory) {
            sb.append(SPAN_STYLECOLOR_PADDING);
        }
        label.setValue(sb.toString());        
        return label;
    }
    
    public static HorizontalLayout addComponentsInHorizontalLayout(Component c1, Component c2) {
        HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.addComponent(c1);
        hlayout.addComponent(c2);
        return hlayout;
    }

    public static HorizontalLayout addComponentsInHorizontalLayoutSecured(Component c1, Component c2, final boolean appPermission) {
        HorizontalLayout hlayout = new HorizontalLayout();
        if (appPermission) {
            hlayout.addComponent(c1);
            hlayout.addComponent(c2);
        }
        return hlayout;
    }

    public static void addComponentInCsssLayout(CssLayout layout, final Component labelComponent, final Component fieldComponent, final boolean appPermission) {
        try {
            if (appPermission) {
                layout.addComponent(labelComponent);
                layout.addComponent(fieldComponent);
            }
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }

    public static void addComponentInCssLayout(final CssLayout cssLayout, final Component labelComponent, final Component fieldComponent,
            final String fieldName, final Map<String, AppPermission> fieldCompanyHM) {

        if ((((AppPermission) fieldCompanyHM.get(fieldName)) == null) ? false : ((AppPermission) fieldCompanyHM.get(fieldName)).isAddFlag()) {
            cssLayout.addComponent(labelComponent);
            cssLayout.addComponent(fieldComponent);
        }
    }
    
    public static void addComponentInCssLayout(CssLayout layout, final Component labelComponent, final Component fieldComponent, final boolean appPermission) {
        try {
        	
            if (!appPermission) {
                layout.removeComponent(labelComponent);
                layout.removeComponent(fieldComponent);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public static void addResponsiveTableCollapse(final Table table) {
        table.setColumnCollapsingAllowed(true);
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {

                                if (defaultColumnsVisible(table)) {
                                    if (Page.getCurrent().getBrowserWindowWidth() < NumericConstants.THREE_EIGHT_ZERO) {
                                        for (Object propertyId : getCollapsibleOneColumn(table)) {
                                            table.setColumnCollapsed(propertyId, true);
                                        }
                                    } else {
                                        for (Object propertyId : getCollapsibleColumns(table)) {
                                            table.setColumnCollapsed(propertyId, true);
                                        }
                                    }
                                }
                            }
                });
    }

    
    private static boolean defaultColumnsVisible(Table table) {
        boolean result = true;
        for (String propertyId : getCollapsibleColumns(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < NumericConstants.EIGHT_HUNDRED) {
                result = false;
            }
        }
        return result;
    }
  
    private static String[] getCollapsibleColumns(Table table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
    

    private static String[] getCollapsibleOneColumn(Table table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

  
    public static void addResponsiveTabSheet(final TabSheet tabSheet) {
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(final Page.BrowserWindowResizeEvent event) {
                        int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                        if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX) {
                            tabSheet.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                        } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
                            tabSheet.setWidth("1100px");
                        } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED) {
                            tabSheet.setWidth("860px");
                        } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
                            tabSheet.setWidth("500px");
                        } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO) {
                            tabSheet.setWidth("400px");
                        } else if (browserWidth < NumericConstants.THREE_TWO_ZERO) {
                            tabSheet.setWidth("300px");
                        }

                    }
                });
    }

    public static void addResponsiveVerticalLayout(final VerticalLayout verticalLayout) {
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {
                                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                                verticalLayout.setWidth(browserWidth, Sizeable.Unit.PIXELS);

                            }
                });
    }

    public static void addResponsiveGridLayout(final GridLayout gridLayout) {
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {
                                int browserWidth = Page.getCurrent().getBrowserWindowWidth();
                                if (browserWidth > NumericConstants.ONE_FIVE_ONE_SIX) {
                                    gridLayout.setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
                                } else if (browserWidth < NumericConstants.ONE_FIVE_ONE_SIX && browserWidth > NumericConstants.NINE_SEVEN_EIGHT) {
                                    gridLayout.setWidth("1200px");
                                } else if (browserWidth < NumericConstants.NINE_SEVEN_EIGHT && browserWidth > NumericConstants.SIX_HUNDRED) {
                                    gridLayout.setWidth("920px");
                                } else if (browserWidth < NumericConstants.SIX_HUNDRED && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
                                    gridLayout.setWidth("550px");
                                } else if (browserWidth < NumericConstants.FOUR_EIGHT_ZERO && browserWidth > NumericConstants.THREE_TWO_ZERO) {
                                    gridLayout.setWidth("450px");
                                } else if (browserWidth < NumericConstants.THREE_TWO_ZERO) {
                                    gridLayout.setWidth("300px");
                                }

                            }
                });
    }

    public static HorizontalLayout addNaviButton(Table table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName(PREVNEXTLAYOUT);
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName(PREVCOLUMNBUTTON);
        nextColumn.setStyleName(NEXTCOLUMNBUTTON);
        addButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }
    
    public static HorizontalLayout addNaviButton(ExtFilterTable table) {
            Button prevColumn = new Button("<<");
            Button nextColumn = new Button(">>");
            HorizontalLayout layout = new HorizontalLayout();
            layout.setStyleName(PREVNEXTLAYOUT);
            layout.addComponent(prevColumn);
            layout.addComponent(nextColumn);
            prevColumn.setStyleName(PREVCOLUMNBUTTON);
            nextColumn.setStyleName(NEXTCOLUMNBUTTON);
            addButtonListeners(table, prevColumn, nextColumn);
            return layout;
        }
    public static HorizontalLayout addFilterNaviButtonPaged(CustomePagedFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName(PREVNEXTLAYOUT);
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName(PREVCOLUMNBUTTON);
        nextColumn.setStyleName(NEXTCOLUMNBUTTON);
        addFilterButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }

    public static void addFilterButtonListeners(final CustomePagedFilterTable table, final Button prevColumn, final Button nextColumn) {
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

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));

                if ((lastIndex + 1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex + 1), false);
                     if((lastIndex+NumericConstants.TWO) == visibleColumnsList.size()){
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
                      
                int count=0;
                prevColumn.setEnabled(true);
                nextColumn.setEnabled(true); 
        for(Object obj:table.getVisibleColumns()){
            if(!table.isColumnCollapsed(obj)){
                count++;
            }
        }
        if(count<=NumericConstants.TWO){
            prevColumn.setEnabled(false);
        }
        if(count>=table.getVisibleColumns().length){
         nextColumn.setEnabled(false);   
        }
            
                    }
                    });
    }

    public static void addButtonListeners(final Table table, final Button prevColumn, final Button nextColumn) {
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

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));

                if ((lastIndex + 1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex + 1), false);
                    if((lastIndex+NumericConstants.TWO) == visibleColumnsList.size()) {
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
                      
                int count=0;
                prevColumn.setEnabled(true);
                nextColumn.setEnabled(true); 
        for(Object obj:table.getVisibleColumns()){
            if(!table.isColumnCollapsed(obj))  {
                count++;
            }
        }
        if(count<=NumericConstants.TWO){
            prevColumn.setEnabled(false);
        }
        if(count>=table.getVisibleColumns().length){
         nextColumn.setEnabled(false);   
        }
            
                    }
                    });
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

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));

                if ((lastIndex + 1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex + 1), false);
                    if((lastIndex+NumericConstants.TWO) == visibleColumnsList.size()){
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
                      
                int count=0;
                prevColumn.setEnabled(true);
                nextColumn.setEnabled(true); 
        for(Object obj:table.getVisibleColumns()){
            if(!table.isColumnCollapsed(obj)){
                count++;
            }
        }
        if(count<=NumericConstants.TWO){
            prevColumn.setEnabled(false);
        }
        if(count>=table.getVisibleColumns().length){
         nextColumn.setEnabled(false);   
        }
            
                    }
                    });
    }
    public static void addResponsivePagedTableCollapse(final CustomePagedFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {

                                if (defaultPagedColumnsVisible(table)) {
                                    if (Page.getCurrent().getBrowserWindowWidth() < NumericConstants.THREE_EIGHT_ZERO) {
                                        for (Object propertyId : getPagedCollapsibleOneColumn(table)) {
                                            table.setColumnCollapsed(propertyId, true);
                                        }
                                    } else {
                                        for (Object propertyId : getPagedCollapsibleColumns(table)) {
                                            table.setColumnCollapsed(propertyId, true);
                                        }
                                    }
                                }
                            }
                });
    }

    private static String[] getPagedCollapsibleOneColumn(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static boolean defaultPagedColumnsVisible(CustomePagedFilterTable table) {
        boolean result = true;
        for (String propertyId : getPagedCollapsibleColumns(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < NumericConstants.EIGHT_HUNDRED) {
                result = false;
            }
        }
        return result;
    }

    private static String[] getPagedCollapsibleColumns(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout) {
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
    public static final String RESPONSIVE_PAGED_TABLE = "responsivePagedTable";

    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout,boolean flag){
                        LOGGER.debug(flag);
                        HorizontalLayout controlBar = new HorizontalLayout();
                        controlBar.setStyleName(RESPONSIVE_PAGED_TABLE);
                        
                        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
                        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);
                        
                        VerticalLayout layout = new VerticalLayout();
                        layout.addComponent(pageSize);
                        CssLayout cssLayout = new CssLayout();
                        cssLayout.setSizeFull();
                        for(int index=0;index<NumericConstants.EIGHT;index++){
                            cssLayout.addComponent(pageManagement.getComponent(0));
                        }
                        layout.addComponent(cssLayout);
                        controlBar.addComponent(layout);
                        return controlBar;
    }

    public static void removeComponentFromCssLayout(CssLayout layout, final Component labelComponent, final Component fieldComponent, final boolean appPermission) {
        try {
            if (!appPermission) {
                layout.removeComponent(labelComponent);
                layout.removeComponent(fieldComponent);
            }
        } catch (Exception e) {

            LOGGER.error(e);
        }
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
    
    public static void addNaviButtonForLandingSearchWithDeclarativeUI(ExtFilterTable table, Button prevColumn, Button nextColumn) {

        addButtonListenersForLandingSearch(table, prevColumn, nextColumn);
    }

    public static void addButtonListenersForLandingSearch(final ExtFilterTable table, final Button prevColumn, final Button nextColumn) {
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

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));

                if ((lastIndex) > 0) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex), true);
                }

                if ((lastIndex) < visibleColumnsList.size()) {
                    nextColumn.setEnabled(true);
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
}
