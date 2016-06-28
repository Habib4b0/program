/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomePagedFilterTable;
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
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;

/**
 *
 * @author Soundarrajan
 */
public class ResponsiveUtils {

    private final static Logger LOGGER = Logger.getLogger(com.stpl.app.global.company.util.CommonUtils.class);
   
    public static Label makeLabel(String value, boolean isMandatory) {
        StringBuilder sb = new StringBuilder(StringUtils.EMPTY);
        sb.append(value);
        if (isMandatory) {
            sb.append(" <span style=\"color: #ed473b; padding: 0 0.2em;\">*</span>");
        }
        Label label = new Label(sb.toString(), ContentMode.HTML);
        return label;
    }
    
    public static Label makeLabel(Label label, boolean isMandatory) {
        StringBuilder sb = new StringBuilder(StringUtils.EMPTY);
        sb.append(label.getValue());
        if (isMandatory) {
            sb.append(" <span style=\"color: #ed473b; padding: 0 0.2em;\">*</span>");
        }

        label.setValue(sb.toString());        
        return label;
    }  


    public static Label makeLabel(String value, String styleName,boolean isMandatory) {
        StringBuilder sb = new StringBuilder(StringUtils.EMPTY);
        sb.append(value);
        if (isMandatory) {
            sb.append(" <span style=\"color: #ed473b; padding: 0 0.2em;\">*</span>");
        }
        Label label = new Label(sb.toString(), ContentMode.HTML);
        label.addStyleName("filenamelable");
        return label;
    }
    
     public static Label makeLabel(String value, boolean isMandatory, String width) {
        StringBuilder sb = new StringBuilder(StringUtils.EMPTY);
        sb.append(value);
        if (isMandatory) {
            sb.append(" <span style=\"color: #ed473b; padding: 0 0.2em;\">*</span>");
        }
        Label label = new Label(sb.toString(), ContentMode.HTML);
        label.addStyleName("qualifierlable");
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
    
    public static void removeComponentFromCssLayout(CssLayout layout, final Component labelComponent, final Component fieldComponent, final boolean appPermission) {
         try {
        	// Clara UI implementation
            if (!appPermission) {
                layout.removeComponent(labelComponent);
                layout.removeComponent(fieldComponent);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public static void addComponentInCssLayout(CssLayout layout, final Component labelComponent, final Component fieldComponent, final boolean appPermission) {
        try {
        	// Clara UI implementation
            if (!appPermission) {
                layout.removeComponent(labelComponent);
                layout.removeComponent(fieldComponent);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    
    public static void addComponentInCsssLayoutForAdd(CssLayout layout, final Component labelComponent, final Component fieldComponent, final boolean appPermission) {
        try {
            if (!appPermission) {
                layout.removeComponent(labelComponent);
                layout.removeComponent(fieldComponent);
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

    public static void addResponsiveTableCollapse(final Table table) {
        table.setColumnCollapsingAllowed(true);
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {
                        
                        

                                if (defaultColumnsVisible(table)) {
                                    if (Page.getCurrent().getBrowserWindowWidth() < 380) {
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
    
    public static void addResponsiveTableCollapse(final ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {
                        
                        

                                if (defaultColumnsVisible(table)) {
                                    if (Page.getCurrent().getBrowserWindowWidth() < 380) {
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

     public static void addResponsiveTableCollapseForExtTable(final ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {
                        
                        

                                if (defaultColumnsVisible(table)) {
                                    if (Page.getCurrent().getBrowserWindowWidth() < 380) {
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
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }
    private static boolean defaultColumnsVisible(ExtFilterTable table) {
        boolean result = true;
        for (String propertyId : getCollapsibleColumns(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    private static String[] getCollapsibleColumns(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        if(list.size()>2)
        {
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
    
    private static String[] getCollapsibleColumns(Table table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        if(list.size()>2)
        {
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleOneColumn(Table table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
    private static String[] getCollapsibleOneColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
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
                        if (browserWidth > 1516) {
                            tabSheet.setWidth(100, Sizeable.Unit.PERCENTAGE);
                        } else if (browserWidth < 1516 && browserWidth > 978) {
                            tabSheet.setWidth("1100px");
                        } else if (browserWidth < 978 && browserWidth > 600) {
                            tabSheet.setWidth("860px");
                        } else if (browserWidth < 600 && browserWidth > 480) {
                            tabSheet.setWidth("500px");
                        } else if (browserWidth < 480 && browserWidth > 320) {
                            tabSheet.setWidth("400px");
                        } else if (browserWidth < 320) {
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
                                if (browserWidth > 1516) {
                                    gridLayout.setWidth(100, Sizeable.Unit.PERCENTAGE);
                                } else if (browserWidth < 1516 && browserWidth > 978) {
                                    gridLayout.setWidth("1200px");
                                } else if (browserWidth < 978 && browserWidth > 600) {
                                    gridLayout.setWidth("920px");
                                } else if (browserWidth < 600 && browserWidth > 480) {
                                    gridLayout.setWidth("550px");
                                } else if (browserWidth < 480 && browserWidth > 320) {
                                    gridLayout.setWidth("450px");
                                } else if (browserWidth < 320) {
                                    gridLayout.setWidth("300px");
                                }

                            }
                });
    }

    public static HorizontalLayout addNaviButton(Table table) throws Exception {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("prev-next-layout");
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName("prev-column-button");
        nextColumn.setStyleName("next-column-button");
        addButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }
    
    
    public static void addNaviButtonForDeclarativeUI(Table table,Button prevColumn,Button nextColumn) throws Exception {
        
       
        addButtonListeners(table, prevColumn, nextColumn);
        
    }
    
    public static HorizontalLayout addNaviButton(ExtFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("prev-next-layout");
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName("prev-column-button");
        nextColumn.setStyleName("next-column-button");
        addButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }
    
    public static HorizontalLayout addFilterNaviButton(ExtFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("prev-next-layout");
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName("prev-column-button");
        nextColumn.setStyleName("next-column-button");
        addFilterButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }
    
    public static void addFilterButtonListeners(final ExtFilterTable table, final Button prevColumn, final Button nextColumn) {
       
        prevColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
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

               
                if (collapsedColumns.size() <= 2) {
                    prevColumn.setEnabled(false);
                }

            }
        });

        nextColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
                int lastIndex = 0;
                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));
                if ((lastIndex+1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex+1), false);
                    if((lastIndex+2) == visibleColumnsList.size())
                    {
                    nextColumn.setEnabled(false);
                    }
                } 

                if ((lastIndex) >= visibleColumnsList.size()) {
                    nextColumn.setEnabled(false);
                }

                List<Object> collapsedColumns1 = new ArrayList<Object>();

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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=2)
        {
            prevColumn.setEnabled(false);
        }
        if(count>=table.getVisibleColumns().length)
        {
         nextColumn.setEnabled(false);   
        }
            
                    }
                    });
                    }

    
public static void addButtonListeners(final Table table, final Button prevColumn, final Button nextColumn) throws Exception {
       
        prevColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
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

               
                if (collapsedColumns.size() <= 2) {
                    prevColumn.setEnabled(false);
                }

            }
        });

        nextColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
                int lastIndex = 0;
                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));
                if ((lastIndex+1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex+1), false);
                    if((lastIndex+2) == visibleColumnsList.size())
                    {
                    nextColumn.setEnabled(false);
                    }
                } 

                if ((lastIndex) >= visibleColumnsList.size()) {
                    nextColumn.setEnabled(false);
                }

                List<Object> collapsedColumns1 = new ArrayList<Object>();

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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=2)
        {
            prevColumn.setEnabled(false);
        }
        if(count>=table.getVisibleColumns().length)
        {
         nextColumn.setEnabled(false);   
        }
            
                    }
                    });
                    }

public static void addButtonListeners(final ExtFilterTable table, final Button prevColumn, final Button nextColumn) {
       
        prevColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
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

               
                if (collapsedColumns.size() <= 2) {
                    prevColumn.setEnabled(false);
                }

            }
        });

        nextColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
                int lastIndex = 0;
                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));
                if ((lastIndex+1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex+1), false);
                    if((lastIndex+2) == visibleColumnsList.size())
                    {
                    nextColumn.setEnabled(false);
                    }
                } 

                if ((lastIndex) >= visibleColumnsList.size()) {
                    nextColumn.setEnabled(false);
                }

                List<Object> collapsedColumns1 = new ArrayList<Object>();

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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=2)
        {
            prevColumn.setEnabled(false);
        }
        if(count>=table.getVisibleColumns().length)
        {
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
                                    if (Page.getCurrent().getBrowserWindowWidth() < 380) {
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
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static boolean defaultPagedColumnsVisible(CustomePagedFilterTable table) {
        boolean result = true;
        for (String propertyId : getPagedCollapsibleColumns(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    private static String[] getPagedCollapsibleColumns(CustomePagedFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        list.remove(propertyIds[1]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }
public static HorizontalLayout addNaviButton(CustomePagedFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("prev-next-layout");
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName("prev-column-button");
        nextColumn.setStyleName("next-column-button");
        addButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }

public static void addNaviButtonWithDeclarativeUI(CustomePagedFilterTable table,Button prevColumn,Button nextColumn) {

        addButtonListeners(table, prevColumn, nextColumn);
        
    }

public static HorizontalLayout addNaviButtonForLandingSearch(ExtFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("prev-next-layout");
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName("prev-column-button");
        nextColumn.setStyleName("next-column-button");
        addButtonListenersForLandingSearch(table, prevColumn, nextColumn);
        return layout;
    }

public static void addNaviButtonForLandingSearchWithDeclarativeUI(ExtFilterTable table,Button prevColumn,Button nextColumn) {

        addButtonListenersForLandingSearch(table, prevColumn, nextColumn);
 }

public static void addButtonListenersForLandingSearch(final ExtFilterTable table, final Button prevColumn, final Button nextColumn) {
        prevColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
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

                
                if (collapsedColumns.size() <= 2) {
                    prevColumn.setEnabled(false);
                }

            }
        });

        nextColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
                int lastIndex = 0;
                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));

                if ((lastIndex + 1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex + 1), false);
                      if((lastIndex+2) == visibleColumnsList.size())
                    {
                    nextColumn.setEnabled(false);
                    }
                } 

                if ((lastIndex) >= visibleColumnsList.size()) {
                    nextColumn.setEnabled(false);
                }

                List<Object> collapsedColumns1 = new ArrayList<Object>();

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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=2)
        {
            prevColumn.setEnabled(false);
        }
        if(count>=table.getVisibleColumns().length)
        {
         nextColumn.setEnabled(false);   
        }
            
                    }
                    });
    }
public static void addButtonListeners(final CustomePagedFilterTable table, final Button prevColumn, final Button nextColumn) {
        prevColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
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

                
                if (collapsedColumns.size() <= 2) {
                    prevColumn.setEnabled(false);
                }

            }
        });

        nextColumn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                List<Object> visibleColumnsList = new ArrayList<Object>(Arrays.asList(table.getVisibleColumns()));
                List<Object> collapsedColumns = new ArrayList<Object>();
                int lastIndex = 0;
                for (Object item : table.getVisibleColumns()) {
                    if (!table.isColumnCollapsed(item)) {
                        collapsedColumns.add(item);
                    }
                }

                lastIndex = visibleColumnsList.indexOf(collapsedColumns.get(collapsedColumns.size() - 1));

                if ((lastIndex + 1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex + 1), false);
                      if((lastIndex+2) == visibleColumnsList.size())
                    {
                    nextColumn.setEnabled(false);
                    }
                } 

                if ((lastIndex) >= visibleColumnsList.size()) {
                    nextColumn.setEnabled(false);
                }

                List<Object> collapsedColumns1 = new ArrayList<Object>();

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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=2)
        {
            prevColumn.setEnabled(false);
        }
        if(count>=table.getVisibleColumns().length)
        {
         nextColumn.setEnabled(false);   
        }
            
                    }
                    });
    }
public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout){
        HorizontalLayout controlBar = new HorizontalLayout();
                        controlBar.setStyleName("responsivePagedTable");
                        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
                        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);
                        
                        CssLayout cssLayout = new CssLayout();
                        cssLayout.setSizeFull();
                        cssLayout.addComponent(pageSize.getComponent(0));
                        cssLayout.addComponent(pageSize.getComponent(0));
                        for(int index=0;index<8;index++){
                            cssLayout.addComponent(pageManagement.getComponent(0));
                        }
                        controlBar.addComponent(cssLayout);
                        return controlBar;
    }

	public static void getResponsiveControls(HorizontalLayout tempLayout, HorizontalLayout controlBar) {

		controlBar.setStyleName("responsivePagedTable");
		HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
		HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

		CssLayout cssLayout = new CssLayout();
		cssLayout.setSizeFull();
		cssLayout.addComponent(pageSize.getComponent(0));
		cssLayout.addComponent(pageSize.getComponent(0));
		for (int index = 0; index < 8; index++) {
			cssLayout.addComponent(pageManagement.getComponent(0));
		}
		controlBar.addComponent(cssLayout);

	}

    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout,boolean flag){
        HorizontalLayout controlBar = new HorizontalLayout();
                        controlBar.setStyleName("responsivePagedTable");
                        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
                        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);
                        VerticalLayout layout = new VerticalLayout();
                        layout.addComponent(pageSize);
                        CssLayout cssLayout = new CssLayout();
                        cssLayout.setSizeFull();
                        for(int index=0;index<8;index++){
                            cssLayout.addComponent(pageManagement.getComponent(0));
                        }
                        layout.addComponent(cssLayout);
                        controlBar.addComponent(layout);
                        return controlBar;
    }


    public static void addResponsiveExtFilterTableCollapse(final ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {

                                if (defaultColumnsExtFilterTableVisible(table)) {
                                    if (Page.getCurrent().getBrowserWindowWidth() < 380) {
                                        for (Object propertyId : getCollapsibleOneExtFilterTableColumn(table)) {
                                            table.setColumnCollapsed(propertyId, true);
                                        }
                                    } else {
                                        for (Object propertyId : getCollapsibleExtFilterTableColumns(table)) {
                                            table.setColumnCollapsed(propertyId, true);
                                        }
                                    }
                                }
                            }
                });
    }

    private static boolean defaultColumnsExtFilterTableVisible(ExtFilterTable table) {
        boolean result = true;
        for (String propertyId : getCollapsibleExtFilterTableColumns(table)) {
            if (table.isColumnCollapsed(propertyId) == Page.getCurrent()
                    .getBrowserWindowWidth() < 800) {
                result = false;
            }
        }
        return result;
    }

    private static String[] getCollapsibleExtFilterTableColumns(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        if (list.size() > 2) {
            list.remove(propertyIds[0]);
            list.remove(propertyIds[1]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleOneExtFilterTableColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<String>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

}
