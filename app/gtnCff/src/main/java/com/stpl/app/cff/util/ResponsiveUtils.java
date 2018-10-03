/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.cff.util;

import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author shrihariharan
 */
public class ResponsiveUtils {
       private static final Logger LOGGER = LoggerFactory.getLogger(ResponsiveUtils.class);

       
       private ResponsiveUtils()
       {
           LOGGER.debug("ResponsiveUtils");
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

            LOGGER.error(e.getMessage());
        }
    }

    public static void addComponentInCssLayout(final CssLayout cssLayout, final Component labelComponent, final Component fieldComponent,
            final String fieldName, final Map<String, AppPermission> fieldCompanyHM) {

        if ((((AppPermission) fieldCompanyHM.get(fieldName)) == null) ? false : ((AppPermission) fieldCompanyHM.get(fieldName)).isAddFlag()) {
            cssLayout.addComponent(labelComponent);
            cssLayout.addComponent(fieldComponent);
        }
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
                        } else if (browserWidth < NumericConstants.SIX_THREE_ZERO && browserWidth > NumericConstants.FOUR_EIGHT_ZERO) {
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
        layout.setStyleName(StringConstantsUtil.PREV_NEXT_LAYOUT);
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName(StringConstantsUtil.PREV_COLUMN_BUTTON);
        nextColumn.setStyleName(StringConstantsUtil.NEXT_COLUMN_BUTTON);
        addButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }
    public static HorizontalLayout addNaviButton(ExtFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName(StringConstantsUtil.PREV_NEXT_LAYOUT);
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName(StringConstantsUtil.PREV_COLUMN_BUTTON);
        nextColumn.setStyleName(StringConstantsUtil.NEXT_COLUMN_BUTTON);
        addButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }
    
    public static HorizontalLayout addFilterNaviButton(ExtFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName(StringConstantsUtil.PREV_NEXT_LAYOUT);
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName(StringConstantsUtil.PREV_COLUMN_BUTTON);
        nextColumn.setStyleName(StringConstantsUtil.NEXT_COLUMN_BUTTON);
        addFilterButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }
    
    public static void addFilterButtonListeners(final ExtFilterTable table, final Button prevColumn, final Button nextColumn) {
       
        prevColumn.addClickListener(new Button.ClickListener() {

            @Override
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

            @Override
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
                if ((lastIndex+1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex+1), false);
                    if((lastIndex+NumericConstants.TWO) == visibleColumnsList.size())
                    {
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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=NumericConstants.TWO)
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

    
public static void addButtonListeners(final Table table, final Button prevColumn, final Button nextColumn) {
       
        prevColumn.addClickListener(new Button.ClickListener() {

            @Override
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

            @Override
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
                if ((lastIndex+1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex+1), false);
                    if((lastIndex+NumericConstants.TWO) == visibleColumnsList.size())
                    {
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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=NumericConstants.TWO)
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

            @Override
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

            @Override
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
                if ((lastIndex+1) < visibleColumnsList.size()) {
                    table.setColumnCollapsed(visibleColumnsList.get(lastIndex+1), false);
                    if((lastIndex+NumericConstants.TWO) == visibleColumnsList.size())
                    {
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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=NumericConstants.TWO)
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
public static HorizontalLayout addNaviButton(CustomePagedFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName(StringConstantsUtil.PREV_NEXT_LAYOUT);
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName(StringConstantsUtil.PREV_COLUMN_BUTTON);
        nextColumn.setStyleName(StringConstantsUtil.NEXT_COLUMN_BUTTON);
        addButtonListeners(table, prevColumn, nextColumn);
        return layout;
    }

public static HorizontalLayout addNaviButtonForLandingSearch(ExtFilterTable table) {
        Button prevColumn = new Button("<<");
        Button nextColumn = new Button(">>");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName(StringConstantsUtil.PREV_NEXT_LAYOUT);
        layout.addComponent(prevColumn);
        layout.addComponent(nextColumn);
        prevColumn.setStyleName(StringConstantsUtil.PREV_COLUMN_BUTTON);
        nextColumn.setStyleName(StringConstantsUtil.NEXT_COLUMN_BUTTON);
        addButtonListenersForLandingSearch(table, prevColumn, nextColumn);
        return layout;
    }

public static void addButtonListenersForLandingSearch(final ExtFilterTable table, final Button prevColumn, final Button nextColumn) {
        prevColumn.addClickListener(new Button.ClickListener() {

            @Override
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

            @Override
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
                      if((lastIndex+NumericConstants.TWO) == visibleColumnsList.size())
                    {
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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=NumericConstants.TWO)
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

            @Override
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

            @Override
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
                      if((lastIndex+NumericConstants.TWO) == visibleColumnsList.size())
                    {
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
        for(Object obj:table.getVisibleColumns())
        {
            if(!table.isColumnCollapsed(obj))
            {
                count++;
            }
        }
        if(count<=NumericConstants.TWO)
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
                        controlBar.setStyleName(Constants.RESPONSIVE_PAGED_TABLE);
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

    public static void addResponsiveExtFilterTableCollapse(final ExtFilterTable table) {
        table.setColumnCollapsingAllowed(true);
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final Page.BrowserWindowResizeEvent event) {

                                if (defaultColumnsExtFilterTableVisible(table)) {
                                    if (Page.getCurrent().getBrowserWindowWidth() < NumericConstants.THREE_EIGHT_ZERO) {
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
                    .getBrowserWindowWidth() < NumericConstants.EIGHT_HUNDRED) {
                result = false;
            }
        }
        return result;
    }

    private static String[] getCollapsibleExtFilterTableColumns(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        if (list.size() > NumericConstants.TWO) {
            list.remove(propertyIds[0]);
            list.remove(propertyIds[1]);
        }
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

    private static String[] getCollapsibleOneExtFilterTableColumn(ExtFilterTable table) {
        Object[] visibleColumns = table.getVisibleColumns();
        String[] propertyIds = Arrays.copyOf(visibleColumns, visibleColumns.length, String[].class);
        List<String> list = new ArrayList<>(Arrays.asList(propertyIds));
        list.remove(propertyIds[0]);
        propertyIds = list.toArray(new String[list.size()]);
        return propertyIds;
    }

}
