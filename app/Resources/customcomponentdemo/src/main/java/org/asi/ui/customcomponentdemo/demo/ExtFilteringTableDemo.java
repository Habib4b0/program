/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.ui.customcomponentdemo.demo;

import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.DefaultFieldFactory;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.ExtCustomTable.RowHeaderMode;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.calendarfield.CalendarField;
import org.asi.calendarfield.WeekDay;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.customcomponentdemo.Custom;
import org.asi.ui.customcomponentdemo.demo.dto.MyTableDTO;
import org.asi.ui.customcomponentdemo.demo.util.CustomTableHeaderDTO;
import org.asi.ui.customcomponentdemo.demo.util.Utils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezeFilterTreeTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedFilterTable;
import org.asi.ui.extfilteringtable.freezetable.listener.TableCollapseListener;
import org.asi.ui.extfilteringtable.freezetable.listener.TableExpandListener;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;

/**
 *
 * @author Abhiram
 */
public class ExtFilteringTableDemo extends VerticalLayout implements View {

    public static final String NAME = "extfilteringtable-demo";
    private final BeanItemContainer<MyTableDTO> phasedProjectionBean = new BeanItemContainer<MyTableDTO>(MyTableDTO.class);
    ExtTreeContainer<MyTableDTO> availableProductsBean = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    ExtTreeContainer<MyTableDTO> availableProductsBean2 = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    ExtTreeContainer<MyTableDTO> availableProductsBean1 = new ExtTreeContainer<MyTableDTO>(MyTableDTO.class);
    private final Map<Object, Object[]> dMapVisibleColumnsMore = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> tMapVisibleColumnsMore = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> dMapVisibleColumnsLess = new HashMap<Object, Object[]>();
    private final Map<Object, Object[]> tMapVisibleColumnsLess = new HashMap<Object, Object[]>();
    public ExtCustomTable periodTableId = new ExtCustomTable();
    NativeSelect frequency = new NativeSelect("frequency");
    NativeSelect history = new NativeSelect("history");
    OptionGroup actualOrProj = new OptionGroup("actualOrProj");
    OptionGroup pivotView = new OptionGroup("pivotView");
    Button generate = new Button("GENERATE");
    TabSheet tabsheet = new TabSheet();
    boolean expAllowed = false;
    

    public ExtFilteringTableDemo() {
        init();
    }

    private void init() {
        removeAllComponents();
        setMargin(true);
        setSpacing(true);
        loadData();
        getMapVisibleCols();
        Button menu = new Button("Main Menu");
        addComponent(menu);
        menu.setDescription("Go to Main Menu");
        setComponentAlignment(menu, Alignment.MIDDLE_CENTER);
        menu.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo(Custom.NAME);
            }
        });
        loadTables();

    }

    private void loadData() {
        for (int i = 0; i < 48; i++) {
            MyTableDTO ob = new MyTableDTO();
            MyTableDTO om = new MyTableDTO();
            om.setProjectionType(i);
            om.setProjCol1Sales("" + i);
            phasedProjectionBean.addBean(om);

            if (i == 1) {
                ob.setProjectionType(500);
                ob.setProjCol1Sales("I m parent");
                availableProductsBean.addBean(ob);

                availableProductsBean.setChildrenAllowed(ob, true);
                MyTableDTO oc = new MyTableDTO();
                oc.setProjCol1Sales("I m 1st child");
                oc.setProjectionType(600);
                availableProductsBean.addBean(oc);
                availableProductsBean.setParent(oc, ob);
                MyTableDTO oe = new MyTableDTO();
                oe.setProjCol1Sales("I m 2nd child");
                oe.setProjectionType(700);

                availableProductsBean.addBean(oe);
                availableProductsBean.setParent(oe, ob);
                availableProductsBean.setChildrenAllowed(oe, false);
                availableProductsBean.setChildrenAllowed(oc, true);
                MyTableDTO od = new MyTableDTO();
                od.setProjCol1Sales("I m child of 1st child");

                availableProductsBean.addBean(od);
                availableProductsBean.setParent(od, oc);
            } else {
                ob.setProjectionType(i);
                availableProductsBean.addBean(ob);
                availableProductsBean.setChildrenAllowed(ob, false);
            }
        }

    }

    private void getMapVisibleCols() {
        dMapVisibleColumnsLess.put(Utils.dviscolumnless[0], new Object[]{Utils.viscolumnless[0]});
        dMapVisibleColumnsLess.put(Utils.dviscolumnless[1], new Object[]{Utils.viscolumnless[1], Utils.viscolumnless[2]});

        tMapVisibleColumnsLess.put(Utils.tviscolumnless[0], new Object[]{Utils.dviscolumnless[0], Utils.dviscolumnless[1]});

        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[0], new Object[]{Utils.viscolumnmore[0], Utils.viscolumnmore[1]});
        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[1], new Object[]{Utils.viscolumnmore[2]});
        dMapVisibleColumnsMore.put(Utils.dviscolumnmore[2], new Object[]{Utils.viscolumnmore[3], Utils.viscolumnmore[4], Utils.viscolumnmore[5]});
        int j = 6;
        for (int i = 3; i < Utils.dviscolumnmore.length; i++) {
            dMapVisibleColumnsMore.put(Utils.dviscolumnmore[i], new Object[]{Utils.viscolumnmore[j], Utils.viscolumnmore[j + 1], Utils.viscolumnmore[j + 2], Utils.viscolumnmore[j + 3], Utils.viscolumnmore[j + 4], Utils.viscolumnmore[j + 5]});
            j = j + 6;
        }
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[0], new Object[]{Utils.dviscolumnmore[0], Utils.dviscolumnmore[1]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[1], new Object[]{Utils.dviscolumnmore[2]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[2], new Object[]{Utils.dviscolumnmore[3], Utils.dviscolumnmore[4], Utils.dviscolumnmore[5]});
        tMapVisibleColumnsMore.put(Utils.tviscolumnmore[3], new Object[]{Utils.dviscolumnmore[6], Utils.dviscolumnmore[7], Utils.dviscolumnmore[8], Utils.dviscolumnmore[9]});
    }

    private void loadTables() {

        addComponent(tabsheet);
        tabsheet.setImmediate(true);
        tabsheet.addTab(loadCalender(), "Calender");
        tabsheet.addTab(loadExtCustomTable(), "Ext Custom Table");
        tabsheet.addTab(loadExtFilterTable(), "Ext Filter Table");
        tabsheet.addTab(loadExtFilterTreeTable(), "Ext Filter Tree Table");
        tabsheet.addTab(loadFreezeFilterTreeTable(), "Freeze Filter Tree Table");
        tabsheet.addTab(loadPagedFilterTable(), "Paged Filter Table");
        tabsheet.addTab(loadFreezePagedFilterTable(), "Freeze Paged Filter Table");
//        tabsheet.addTab(loadExtFilterTestingTable(), "Testing Table");
//        tabsheet.addTab(loadTestingTab(),"Testing Table");

    }
    private VerticalLayout loadCalender() {        
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
//        DateField fld1=new DateField();
//        fld1.setResolution(Resolution.DAY);
//        fld1.setImmediate(true);
//        fld1.setRangeStart(new Date());
//        layout.addComponent(fld1);
        
//        fld.getValue()
        final CalendarField cal=new CalendarField();
//        cal.setResolution(Resolution.DAY);
//        cal.setRangeStart(new Date());
        cal.setYearResolution(true);
        cal.setMatrix(CalendarField.Matrix.ROW_4xCOL_3);
        
        cal.setRangeStart(new Date(2016-1900, 0, 1));
        cal.setRangeEnd(new Date(2016-1900, 11, 31));
        cal.setImmediate(true);
        layout.addComponent(cal);
        final TextField fld=new TextField();
        fld.setImmediate(true);
        layout.addComponent(fld);
        layout.addComponent(new Label("Disable Week Days:"));
        final CheckBox cbx=new CheckBox();
        cbx.setImmediate(true);
        cbx.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(cbx.getValue()){
                cal.setSelectedWeekDays(WeekDay.SUNDAY,WeekDay.SATURDAY);
                }else{
                    cal.setSelectedWeekDays(WeekDay.SATURDAY);
                }
                System.out.println("size val="+cal.getValue().size());
            }
        });
        
        layout.addComponent(cbx);
        layout.addComponent(new Label("Disable Monthly Days:"));
        final CheckBox cbx1=new CheckBox();
        cbx1.setImmediate(true);
        cbx1.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(cbx1.getValue()){
                cal.setDisableMonthlyDays(Integer.parseInt(fld.getValue()));
                }else{
                    cal.setDisableMonthlyDays();
                }
            }
        });
        
        layout.addComponent(cbx1);
        final Button btn=new Button("Add");
        btn.setImmediate(true);
        btn.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                cal.setDisableDates(new Date(116,2,Integer.parseInt(fld.getValue())));
            }
        });
        layout.addComponent(btn);
        final Button btn1=new Button("remove");
        btn1.setImmediate(true);
        btn1.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                cal.setDisableDates();
            }
        });
        layout.addComponent(btn1);
        
        
        
        
        
        return layout;
    }
    private VerticalLayout loadExtFilterTestingTable() {
        Object visCol[] = new Object[]{"projCol1Sales", "projCol2Sales", "projCol3Sales"};
        String visHead[] = new String[]{"projCol1Sales", "projCol2Sales", "projCol3Sales"};
        Map<Object, Object[]> map = new HashMap<Object, Object[]>();
        map.put("projCol1Sales", new Object[]{"projCol1Sales"});
        map.put("projCol2Sales", new Object[]{"projCol2Sales"});
        map.put("projCol3Sales", new Object[]{"projCol3Sales"});




        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtFilterTable extFilterTable = new ExtFilterTable();        
        extFilterTable.setSizeFull();
        extFilterTable.setCaption("ExtFilterTable");
        extFilterTable.setContainerDataSource(phasedProjectionBean);
        extFilterTable.setVisibleColumns(visCol);
        extFilterTable.setColumnHeaders(visHead);
        extFilterTable.setFilterBarVisible(true);
        extFilterTable.setDoubleHeaderVisible(true);
        extFilterTable.setDoubleHeaderVisibleColumns(visCol);
        extFilterTable.setDoubleHeaderColumnHeaders(visHead);
        extFilterTable.setDoubleHeaderMap(map);

        extFilterTable.setColumnCollapsingAllowed(true);
        extFilterTable.setTripleHeaderVisible(true);

        extFilterTable.setTripleHeaderVisibleColumns(visCol);
        extFilterTable.setTripleHeaderColumnHeaders(visHead);
        extFilterTable.setTripleHeaderMap(map);
        extFilterTable.setSelectable(true);
        extFilterTable.setEditable(true);
        extFilterTable.setPageLength(10);
        extFilterTable.setRowHeaderMode(ExtCustomTable.RowHeaderMode.ICON_ONLY);

        for (int i = 0; i < visCol.length; i++) {
            extFilterTable.setColumnCheckBox(visCol[i], true);
            extFilterTable.setDoubleHeaderColumnRadioButton(visCol[i], visHead[0]);
            extFilterTable.setTripleHeaderColumnRadioButton(visCol[i], visHead[0]);
        }

        extFilterTable.setImmediate(true);
        extFilterTable.addDoubleHeaderColumnRadioCheckListener(new ExtCustomTable.DoubleHeaderColumnRadioCheckListener() {
            @Override
            public void doubleHeaderColumnRadioCheck(ExtCustomTable.DoubleHeaderColumnRadioCheckEvent event) {
                Notification.show("Double Radio RadioButtonName: " + event.getRadioButtonName() + " Cur: " + event.getCurrentValue() + " Pre: " + event.getPreviousValue());
            }
        });
        extFilterTable.addTripleHeaderColumnRadioCheckListener(new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {
            @Override
            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {
                Notification.show("Triple Radio RadioButtonName: " + event.getRadioButtonName() + " Cur: " + event.getCurrentValue() + " Pre: " + event.getPreviousValue());
            }
        });

        extFilterTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                extFilterTable.setTripleHeaderColumnRadioButtonDisable(event.getPropertyId(), !event.isChecked());
                extFilterTable.setDoubleHeaderColumnRadioButtonDisable(event.getPropertyId(), !event.isChecked());
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(extFilterTable);

        return layout;
    }

    private VerticalLayout loadExtCustomTable() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtCustomTable extCustomTable = new ExtCustomTable();
        extCustomTable.setSizeFull();
        extCustomTable.setCaption("ExtFilterTable");
        extCustomTable.setContainerDataSource(phasedProjectionBean);
        extCustomTable.setVisibleColumns(Utils.viscolumnmore);
        extCustomTable.setColumnHeaders(Utils.visheadermore);
//        extCustomTable.setFilterBarVisible(true);
        extCustomTable.setDoubleHeaderVisible(true);
        extCustomTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        extCustomTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        extCustomTable.setDoubleHeaderMap(dMapVisibleColumnsMore);

        extCustomTable.setColumnCollapsingAllowed(true);
//        extCustomTable.setTripleHeaderVisible(true);

//        extCustomTable.setTripleHeaderVisibleColumns(Utils.tviscolumnmore);
//        extCustomTable.setTripleHeaderColumnHeaders(Utils.tvisheadermore);
//        extCustomTable.setTripleHeaderMap(tMapVisibleColumnsMore);
        extCustomTable.setSelectable(true);
        extCustomTable.setEditable(true);
        extCustomTable.setPageLength(10);
        extCustomTable.setRowHeaderMode(ExtCustomTable.RowHeaderMode.ICON_ONLY);
//        extCustomTable.setTripleHeaderColumnCheckBox(Utils.tviscolumnmore[0], true, true);
//        extCustomTable.setTripleHeaderColumnIcon(Utils.tviscolumnmore[1], Utils.logoimg);
//        extCustomTable.setTripleHeaderColumnExpandIcon(Utils.tviscolumnmore[0], true);
        extCustomTable.setColumnExpandIcon(Utils.viscolumnmore[1], true);
//        extCustomTable.setDoubleHeaderColumnExpandIcon(Utils.dviscolumnmore[1], true);
//        extCustomTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[2], "asit");
//        extCustomTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[0], "asit");

//        extCustomTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[2], "asit1");
//        extCustomTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[0], "asit1");
//        extCustomTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
//        extCustomTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        extCustomTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        extCustomTable.setColumnRadioButton(Utils.viscolumnmore[0], "asit111");
        extCustomTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

//        extCustomTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
//        extCustomTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        extCustomTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        extCustomTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        extCustomTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        extCustomTable.setImmediate(true);
        extCustomTable.setColumnExpandIconStyle(ExtCustomTable.ColumnExpandIconStyle.ARROW_CIRCLE);
//        extCustomTable.addTripleHeaderColumnCheckListener(new ExtCustomTable.TripleHeaderColumnCheckListener() {
//            @Override
//            public void tripleHeaderColumnCheck(ExtCustomTable.TripleHeaderColumnCheckEvent event) {
//                Notification.show("Triple Check PrropertyId: " + event.getPropertyId() + " Pre: " + event.isChecked());
//                if (expAllowed) {
//                    System.out.println("expAllowed="+expAllowed);
//                    extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extCustomTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
//                }
//                System.out.println("addTripleHeaderColumnCheckListener expAllowed="+expAllowed);
//            }
//        });
//        
//        extCustomTable.addTripleHeaderColumnRadioCheckListener(
//                new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {
//            @Override
//            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {
//                Notification.show("Triple Radio RadioButtonName: " + event.getRadioButtonName() + " Cur: " + event.getCurrentValue() + " Pre: " + event.getPreviousValue());
//            }
//        });
//        extCustomTable.addTripleHeaderColumnResizeListener(
//                new ExtCustomTable.TripleHeaderColumnResizeListener() {
//            @Override
//            public void tripleHeaderColumnResize(ExtCustomTable.TripleHeaderColumnResizeEvent event) {
//                Notification.show("Triple PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
//            }
//        });
//        extCustomTable.addDoubleHeaderColumnResizeListener(
//                new ExtCustomTable.DoubleHeaderColumnResizeListener() {
//            @Override
//            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
//                Notification.show("Double PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
//            }
//        });
        extCustomTable.addColumnResizeListener(
                new ExtCustomTable.ColumnResizeListener() {
            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
            }
        });
//        extCustomTable.addTripleHeaderClickListener(
//                new ExtCustomTable.TripleHeaderClickListener() {
//            @Override
//            public void tripleHeaderClick(ExtCustomTable.TripleHeaderClickEvent event) {
//                Notification.show("Triple PrropertyId: " + event.getPropertyId());
//            }
//        });
//        extCustomTable.addDoubleHeaderClickListener(
//                new ExtCustomTable.DoubleHeaderClickListener() {
//            @Override
//            public void doubleHeaderClick(ExtCustomTable.DoubleHeaderClickEvent event) {
//                Notification.show("Double PrropertyId: " + event.getPropertyId());
//                if (expAllowed) {
//                    System.out.println("expAllowed="+expAllowed);
//                    extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extCustomTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
//                }
//                System.out.println("addDoubleHeaderClickListener expAllowed="+expAllowed);
//            }
//        });
        extCustomTable.addHeaderClickListener(
                new ExtCustomTable.HeaderClickListener() {
            @Override
            public void headerClick(ExtCustomTable.HeaderClickEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId());
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
//                    extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extCustomTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
                }
                System.out.println("addHeaderClickListener expAllowed="+expAllowed);
            }
        });
//        extCustomTable.addDoubleHeaderColumnCheckListener(
//                new ExtCustomTable.DoubleHeaderColumnCheckListener() {
//            @Override
//            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
////                extCustomTable.setDoubleHeaderColumnRadioButtonDisable(Utils.dviscolumnmore[2], event.isChecked());
////                extCustomTable.setDoubleHeaderColumnRadioButtonDisable(Utils.dviscolumnmore[0], event.isChecked());
//                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
//                if (expAllowed) {
//                    System.out.println("expAllowed="+expAllowed);
//                    extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extCustomTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
//                }
//                System.out.println("addDoubleHeaderColumnCheckListener expAllowed="+expAllowed);
//            }
//        });
        extCustomTable.addColumnCheckListener(
                new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {

                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
//                    extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extCustomTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
                }
                System.out.println("addColumnCheckListener expAllowed="+expAllowed);
            }
        });
        extCustomTable.addTripleHeaderColumnExpandIconListener(new ExtCustomTable.TripleHeaderColumnExpandIconListener() {
            @Override
            public void tripleHeaderColumnExpandIcon(ExtCustomTable.TripleHeaderColumnExpandIconEvent event) {
                
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
//                    extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
                }
                System.out.println("addTripleHeaderColumnExpandIconListener expAllowed="+expAllowed);
            }
        });
//        extCustomTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
//            @Override
//            public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
//                
//                if (expAllowed) {
//                    System.out.println("expAllowed="+expAllowed);
//                    extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
//                }
//                System.out.println("addDoubleHeaderColumnExpandIconListener expAllowed="+expAllowed);
//            }
//        });
        extCustomTable.addColumnExpandIconListener(new ExtCustomTable.ColumnExpandIconListener() {
            @Override
            public void columnExpandIcon(ExtCustomTable.ColumnExpandIconEvent event) {
                
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
//                    extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
                }
                System.out.println("addColumnExpandIconListener expAllowed="+expAllowed);
            }
        });
        layout.addComponent(extCustomTable);
        
        final CheckBox ch = new CheckBox("Expand/Collapse");

        ch.setImmediate(
                true);
        ch.setEnabled(
                true);
        layout.addComponent(ch);

        ch.addValueChangeListener(
                new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                int wid=extCustomTable.getColumnWidth(Utils.viscolumnmore[1]);
                System.out.println("wid="+wid);
                extCustomTable.setColumnWidth(Utils.viscolumnmore[1],wid-50);
//                extCustomTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !ch.getValue());
            }
        });
        final CheckBox ch1 = new CheckBox("Expand/Collapse allowed");

        ch1.setImmediate(
                true);
        ch1.setEnabled(
                true);
        layout.addComponent(ch1);

        ch1.addValueChangeListener(
                new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                expAllowed=ch1.getValue().booleanValue();
            }
        });
        return layout;
    }
    private VerticalLayout loadExtFilterTable() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtFilterTable extFilterTable = new ExtFilterTable();
        extFilterTable.setSizeFull();
        extFilterTable.setCaption("ExtFilterTable");
        extFilterTable.setContainerDataSource(phasedProjectionBean);
        extFilterTable.setVisibleColumns(Utils.viscolumnmore);
        extFilterTable.setColumnHeaders(Utils.visheadermore);
        extFilterTable.setFilterBarVisible(true);
        extFilterTable.setDoubleHeaderVisible(true);
        extFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        extFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        extFilterTable.setDoubleHeaderMap(dMapVisibleColumnsMore);

        extFilterTable.setColumnCollapsingAllowed(true);
        extFilterTable.setTripleHeaderVisible(true);

        extFilterTable.setTripleHeaderVisibleColumns(Utils.tviscolumnmore);
        extFilterTable.setTripleHeaderColumnHeaders(Utils.tvisheadermore);
        extFilterTable.setTripleHeaderMap(tMapVisibleColumnsMore);
        extFilterTable.setSelectable(true);
        extFilterTable.setEditable(true);
        extFilterTable.setPageLength(10);
        extFilterTable.setRowHeaderMode(ExtCustomTable.RowHeaderMode.ICON_ONLY);
        extFilterTable.setTripleHeaderColumnCheckBox(Utils.tviscolumnmore[0], true, true);
        extFilterTable.setTripleHeaderColumnIcon(Utils.tviscolumnmore[1], Utils.logoimg);
        extFilterTable.setTripleHeaderColumnExpandIcon(Utils.tviscolumnmore[0], true);
        extFilterTable.setColumnExpandIcon(Utils.viscolumnmore[1], true);
        extFilterTable.setDoubleHeaderColumnExpandIcon(Utils.dviscolumnmore[1], true);
        extFilterTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[2], "asit");
        extFilterTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[0], "asit");

        extFilterTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[2], "asit1");
        extFilterTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[0], "asit1");
        extFilterTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        extFilterTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        extFilterTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        extFilterTable.setColumnRadioButton(Utils.viscolumnmore[0], "asit111");
        extFilterTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

        extFilterTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        extFilterTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        extFilterTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        extFilterTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        extFilterTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        extFilterTable.setImmediate(true);
        extFilterTable.addTripleHeaderColumnCheckListener(new ExtCustomTable.TripleHeaderColumnCheckListener() {
            @Override
            public void tripleHeaderColumnCheck(ExtCustomTable.TripleHeaderColumnCheckEvent event) {
                Notification.show("Triple Check PrropertyId: " + event.getPropertyId() + " Pre: " + event.isChecked());
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
                    extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extFilterTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
                }
                System.out.println("addTripleHeaderColumnCheckListener expAllowed="+expAllowed);
            }
        });
        
        extFilterTable.addTripleHeaderColumnRadioCheckListener(
                new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {
            @Override
            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {
                Notification.show("Triple Radio RadioButtonName: " + event.getRadioButtonName() + " Cur: " + event.getCurrentValue() + " Pre: " + event.getPreviousValue());
            }
        });
        extFilterTable.addTripleHeaderColumnResizeListener(
                new ExtCustomTable.TripleHeaderColumnResizeListener() {
            @Override
            public void tripleHeaderColumnResize(ExtCustomTable.TripleHeaderColumnResizeEvent event) {
                Notification.show("Triple PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTable.addDoubleHeaderColumnResizeListener(
                new ExtCustomTable.DoubleHeaderColumnResizeListener() {
            @Override
            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTable.addColumnResizeListener(
                new ExtCustomTable.ColumnResizeListener() {
            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTable.addTripleHeaderClickListener(
                new ExtCustomTable.TripleHeaderClickListener() {
            @Override
            public void tripleHeaderClick(ExtCustomTable.TripleHeaderClickEvent event) {
                Notification.show("Triple PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTable.addDoubleHeaderClickListener(
                new ExtCustomTable.DoubleHeaderClickListener() {
            @Override
            public void doubleHeaderClick(ExtCustomTable.DoubleHeaderClickEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId());
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
                    extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extFilterTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
                }
                System.out.println("addDoubleHeaderClickListener expAllowed="+expAllowed);
            }
        });
        extFilterTable.addHeaderClickListener(
                new ExtCustomTable.HeaderClickListener() {
            @Override
            public void headerClick(ExtCustomTable.HeaderClickEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId());
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
                    extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extFilterTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
                }
                System.out.println("addHeaderClickListener expAllowed="+expAllowed);
            }
        });
        extFilterTable.addDoubleHeaderColumnCheckListener(
                new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
//                extCustomTable.setDoubleHeaderColumnRadioButtonDisable(Utils.dviscolumnmore[2], event.isChecked());
//                extCustomTable.setDoubleHeaderColumnRadioButtonDisable(Utils.dviscolumnmore[0], event.isChecked());
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
                    extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extFilterTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
                }
                System.out.println("addDoubleHeaderColumnCheckListener expAllowed="+expAllowed);
            }
        });
        extFilterTable.addColumnCheckListener(
                new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {

                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
                    extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !extFilterTable.isTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1]));
                }
                System.out.println("addColumnCheckListener expAllowed="+expAllowed);
            }
        });
        extFilterTable.addTripleHeaderColumnExpandIconListener(new ExtCustomTable.TripleHeaderColumnExpandIconListener() {
            @Override
            public void tripleHeaderColumnExpandIcon(ExtCustomTable.TripleHeaderColumnExpandIconEvent event) {
                
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
                    extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
                }
                System.out.println("addTripleHeaderColumnExpandIconListener expAllowed="+expAllowed);
            }
        });
        extFilterTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {
            @Override
            public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
                    extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
                }
                System.out.println("addDoubleHeaderColumnExpandIconListener expAllowed="+expAllowed);
            }
        });
        extFilterTable.addColumnExpandIconListener(new ExtCustomTable.ColumnExpandIconListener() {
            @Override
            public void columnExpandIcon(ExtCustomTable.ColumnExpandIconEvent event) {
                
                if (expAllowed) {
                    System.out.println("expAllowed="+expAllowed);
                    extFilterTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
                }
                System.out.println("addColumnExpandIconListener expAllowed="+expAllowed);
            }
        });
        layout.addComponent(extFilterTable);
        
        final CheckBox ch = new CheckBox("Expand/Collapse");

        ch.setImmediate(
                true);
        ch.setEnabled(
                true);
        layout.addComponent(ch);

        ch.addValueChangeListener(
                new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                int wid=extFilterTable.getColumnWidth(Utils.viscolumnmore[1]);
                System.out.println("wid="+wid);
                extFilterTable.setColumnWidth(Utils.viscolumnmore[1],wid-50);
            }
        });
        final CheckBox ch1 = new CheckBox("Expand/Collapse allowed");

        ch1.setImmediate(
                true);
        ch1.setEnabled(
                true);
        layout.addComponent(ch1);

        ch1.addValueChangeListener(
                new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                expAllowed=ch1.getValue().booleanValue();
            }
        });
        return layout;
    }

    private VerticalLayout loadExtFilterTreeTable() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtFilterTreeTable extFilterTreeTable = new ExtFilterTreeTable();
        extFilterTreeTable.setEditable(true);
        extFilterTreeTable.setSizeFull();
        extFilterTreeTable.setCaption("ExtFilterTreeTable");
        extFilterTreeTable.setContainerDataSource(availableProductsBean);
        extFilterTreeTable.setVisibleColumns(Utils.viscolumnmore);
        extFilterTreeTable.setColumnHeaders(Utils.visheadermore);
        extFilterTreeTable.setFilterBarVisible(true);
        extFilterTreeTable.setDoubleHeaderVisible(true);
        extFilterTreeTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        extFilterTreeTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        extFilterTreeTable.setDoubleHeaderMap(dMapVisibleColumnsMore);

        extFilterTreeTable.setColumnCollapsingAllowed(true);
        extFilterTreeTable.setTripleHeaderVisible(true);

        extFilterTreeTable.setTripleHeaderVisibleColumns(Utils.tviscolumnmore);
        extFilterTreeTable.setTripleHeaderColumnHeaders(Utils.tvisheadermore);
        extFilterTreeTable.setTripleHeaderMap(tMapVisibleColumnsMore);
        extFilterTreeTable.setSelectable(true);
        extFilterTreeTable.setPageLength(10);
        extFilterTreeTable.setRowHeaderMode(ExtCustomTable.RowHeaderMode.ICON_ONLY);
        extFilterTreeTable.setTripleHeaderColumnCheckBox(Utils.tviscolumnmore[0], true, true);
        extFilterTreeTable.setTripleHeaderColumnIcon(Utils.tviscolumnmore[1], Utils.logoimg);
        extFilterTreeTable.setTripleHeaderColumnExpandIcon(Utils.tviscolumnmore[0], true);
        extFilterTreeTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[2], "asit");
        extFilterTreeTable.setTripleHeaderColumnRadioButton(Utils.tviscolumnmore[0], "asit");

        extFilterTreeTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[2], "asit1");
        extFilterTreeTable.setDoubleHeaderColumnRadioButton(Utils.dviscolumnmore[0], "asit1");
        extFilterTreeTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        extFilterTreeTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        extFilterTreeTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        extFilterTreeTable.setColumnRadioButton(Utils.viscolumnmore[0], "asit111");
        extFilterTreeTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

        extFilterTreeTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        extFilterTreeTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        extFilterTreeTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        extFilterTreeTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        extFilterTreeTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        extFilterTreeTable.setImmediate(true);
//        rightTable.setSortEnabled(false);
        extFilterTreeTable.addTripleHeaderColumnCheckListener(new ExtCustomTable.TripleHeaderColumnCheckListener() {
            @Override
            public void tripleHeaderColumnCheck(ExtCustomTable.TripleHeaderColumnCheckEvent event) {
                Notification.show("Triple Check PrropertyId: " + event.getPropertyId() + " Pre: " + event.isChecked());
            }
        });
        extFilterTreeTable.addTripleHeaderColumnExpandIconListener(new ExtCustomTable.TripleHeaderColumnExpandIconListener() {
            @Override
            public void tripleHeaderColumnExpandIcon(ExtCustomTable.TripleHeaderColumnExpandIconEvent event) {
                if (event.getPropertyId().equals(Utils.tviscolumnmore[0])) {
                    extFilterTreeTable.setTripleHeaderColumnCollapsed(Utils.tviscolumnmore[1], !event.isExpanded());
                }
            }
        });
        extFilterTreeTable.addTripleHeaderColumnRadioCheckListener(new ExtCustomTable.TripleHeaderColumnRadioCheckListener() {
            @Override
            public void tripleHeaderColumnRadioCheck(ExtCustomTable.TripleHeaderColumnRadioCheckEvent event) {
                Notification.show("Triple Radio RadioButtonName: " + event.getRadioButtonName() + " Cur: " + event.getCurrentValue() + " Pre: " + event.getPreviousValue());
            }
        });
        extFilterTreeTable.addTripleHeaderColumnResizeListener(new ExtCustomTable.TripleHeaderColumnResizeListener() {
            @Override
            public void tripleHeaderColumnResize(ExtCustomTable.TripleHeaderColumnResizeEvent event) {
                Notification.show("Triple PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTreeTable.addDoubleHeaderColumnResizeListener(new ExtCustomTable.DoubleHeaderColumnResizeListener() {
            @Override
            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTreeTable.addColumnResizeListener(new ExtCustomTable.ColumnResizeListener() {
            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId() + " Pre: " + event.getPreviousWidth() + " Cur: " + event.getCurrentWidth());
            }
        });
        extFilterTreeTable.addTripleHeaderClickListener(new ExtCustomTable.TripleHeaderClickListener() {
            @Override
            public void tripleHeaderClick(ExtCustomTable.TripleHeaderClickEvent event) {
                Notification.show("Triple PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTreeTable.addDoubleHeaderClickListener(new ExtCustomTable.DoubleHeaderClickListener() {
            @Override
            public void doubleHeaderClick(ExtCustomTable.DoubleHeaderClickEvent event) {
                Notification.show("Double PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTreeTable.addHeaderClickListener(new ExtCustomTable.HeaderClickListener() {
            @Override
            public void headerClick(ExtCustomTable.HeaderClickEvent event) {
                Notification.show("PrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTreeTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        extFilterTreeTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(extFilterTreeTable);
        return layout;
    }

    private VerticalLayout loadFreezeFilterTreeTable() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        FreezeFilterTreeTable freezeFilterTreeTable = new FreezeFilterTreeTable();
        TableExpandListener filtertableExpandListener = new TableExpandListener(freezeFilterTreeTable.getRightFreezeAsTable());
        TableCollapseListener filtertableCollapseListener = new TableCollapseListener(freezeFilterTreeTable.getRightFreezeAsTable());
        TableExpandListener filterfreezeTableExpandListener = new TableExpandListener(filtertableExpandListener);
        TableCollapseListener filterfreezeTableCollapseListener = new TableCollapseListener(filtertableCollapseListener);
        freezeFilterTreeTable.setCaption("FreezeFilterTreeTable");
        freezeFilterTreeTable.setSplitPosition(Utils.splitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setMinSplitPosition(Utils.minSplitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setMaxSplitPosition(Utils.maxSplitPosition, Unit.PIXELS);
        freezeFilterTreeTable.setContainerDataSource(availableProductsBean);
        freezeFilterTreeTable.setPageLength(6);
        freezeFilterTreeTable.setSelectable(true);
        ExtFilterTreeTable leftTable = freezeFilterTreeTable.getLeftFreezeAsTable();
        final ExtFilterTreeTable rightTable = freezeFilterTreeTable.getRightFreezeAsTable();
        leftTable.setVisibleColumns(Utils.viscolumnless);
        leftTable.setColumnHeaders(Utils.visheaderless);
        rightTable.setVisibleColumns(Utils.viscolumnmore);
        rightTable.setColumnHeaders(Utils.visheadermore);
        freezeFilterTreeTable.setDoubleHeaderVisible(true);
        freezeFilterTreeTable.setFilterBarVisible(true);

        freezeFilterTreeTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnless, Utils.dviscolumnmore);
        freezeFilterTreeTable.setDoubleHeaderColumnHeaders(Utils.dvisheaderless, Utils.dvisheadermore);
        freezeFilterTreeTable.setDoubleHeaderMap(dMapVisibleColumnsLess, dMapVisibleColumnsMore);
        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        rightTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        rightTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        rightTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        leftTable.addExpandListener(filterfreezeTableExpandListener);
        leftTable.addCollapseListener(filterfreezeTableCollapseListener);
        rightTable.addExpandListener(filtertableExpandListener);
        rightTable.addCollapseListener(filtertableCollapseListener);
        layout.addComponent(freezeFilterTreeTable);
        freezeFilterTreeTable.setImmediate(true);
        return layout;
    }

    private VerticalLayout loadPagedFilterTable() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        final ExtPagedFilterTable<IndexedContainer> pagedFilterTable = new ExtPagedFilterTable<IndexedContainer>();
        pagedFilterTable.setCaption("ExtPagedFilterTable");
        pagedFilterTable.setWidth("100%");
        pagedFilterTable.setFilterBarVisible(true);

        pagedFilterTable.setSelectable(true);
        pagedFilterTable.setImmediate(true);
        pagedFilterTable.setMultiSelect(true);
        pagedFilterTable.setPageLength(5);
        pagedFilterTable.setRowHeaderMode(RowHeaderMode.INDEX);

        pagedFilterTable.setColumnReorderingAllowed(true);

        pagedFilterTable.setContainerDataSource(phasedProjectionBean);

        pagedFilterTable.setVisibleColumns(Utils.viscolumnmore);
        pagedFilterTable.setColumnHeaders(Utils.visheadermore);
        pagedFilterTable.setDoubleHeaderVisible(true);
        pagedFilterTable.sinkItemPerPageWithPageLength(false);
        pagedFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnmore);
        pagedFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheadermore);
        pagedFilterTable.setDoubleHeaderMap(dMapVisibleColumnsMore);
        pagedFilterTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
        pagedFilterTable.setColumnCheckBox(Utils.viscolumnmore[2], true, true);
        pagedFilterTable.addColumnResizeListener(new ExtCustomTable.ColumnResizeListener() {
            @Override
            public void columnResize(ExtCustomTable.ColumnResizeEvent event) {
                Notification.show("Pro:" + event.getPropertyId() + "\n Pre:" + event.getPreviousWidth() + "\n Cur: " + event.getCurrentWidth());
            }
        });
        pagedFilterTable.addDoubleHeaderColumnResizeListener(new ExtCustomTable.DoubleHeaderColumnResizeListener() {
            @Override
            public void doubleHeaderColumnResize(ExtCustomTable.DoubleHeaderColumnResizeEvent event) {
                Notification.show("Pro:" + event.getPropertyId() + "\n Pre:" + event.getPreviousWidth() + "\n Cur: " + event.getCurrentWidth());
            }
        });
        pagedFilterTable.setEditable(true);
        pagedFilterTable.setImmediate(true);
        pagedFilterTable.setTableFieldFactory(new DefaultFieldFactory() {
            @Override
            public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
                if (propertyId.equals(Utils.viscolumnmore[2])) {
                    TextField ob = new TextField();
                    ob.setImmediate(true);
                    return ob;
                }
                return null;
            }
        });
        layout.addComponent(pagedFilterTable);
        layout.addComponent(pagedFilterTable.createControls());
        final CheckBox sink = new CheckBox("Sink ItemPerPage With PageLength");
        sink.setImmediate(true);
        layout.addComponent(sink);
        sink.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                pagedFilterTable.sinkItemPerPageWithPageLength(sink.getValue());
            }
        });
        return layout;
    }

    private VerticalLayout loadFreezePagedFilterTable() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        FreezePagedFilterTable<IndexedContainer> pagedFreezeFilterTable = new FreezePagedFilterTable<IndexedContainer>();

        pagedFreezeFilterTable.setCaption("FreezePagedFilterTable");
        pagedFreezeFilterTable.setSplitPosition(Utils.splitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setMinSplitPosition(Utils.minSplitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setMaxSplitPosition(Utils.maxSplitPosition, Unit.PIXELS);
        pagedFreezeFilterTable.setContainerDataSource(phasedProjectionBean);
        pagedFreezeFilterTable.setPageLength(6);
//        pagedFilterTable.setHeight("210px");
        pagedFreezeFilterTable.setSelectable(true);
        final ExtPagedFilterTable leftTable = pagedFreezeFilterTable.getLeftFreezeAsTable();
        final ExtPagedFilterTable rightTable = pagedFreezeFilterTable.getRightFreezeAsTable();
        leftTable.setVisibleColumns(Utils.viscolumnless);
        leftTable.setColumnHeaders(Utils.visheaderless);
        rightTable.setVisibleColumns(Utils.viscolumnmore);
        rightTable.setColumnHeaders(Utils.visheadermore);
        pagedFreezeFilterTable.setDoubleHeaderVisible(true);
        pagedFreezeFilterTable.setFilterBarVisible(true);
        pagedFreezeFilterTable.setDoubleHeaderVisibleColumns(Utils.dviscolumnless, Utils.dviscolumnmore);
        pagedFreezeFilterTable.setDoubleHeaderColumnHeaders(Utils.dvisheaderless, Utils.dvisheadermore);
        pagedFreezeFilterTable.setDoubleHeaderMap(dMapVisibleColumnsLess, dMapVisibleColumnsMore);
        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[0], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[1], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[0], true, true);
        rightTable.setColumnIcon(Utils.viscolumnmore[2], Utils.logoimg);

        rightTable.setDoubleHeaderColumnCheckBox(Utils.dviscolumnmore[2], true, true);
        rightTable.setDoubleHeaderColumnIcon(Utils.dviscolumnmore[2], Utils.logoimg);
        rightTable.setColumnCheckBox(Utils.viscolumnmore[1], true, false);
        rightTable.setColumnIcon(Utils.viscolumnmore[1], Utils.logoimg);
        rightTable.setColumnIcon(Utils.viscolumnmore[3], Utils.logoimg);
//        rightTable.setSortEnabled(false);
        rightTable.addDoubleHeaderColumnCheckListener(new ExtCustomTable.DoubleHeaderColumnCheckListener() {
            @Override
            public void doubleHeaderColumnCheck(ExtCustomTable.DoubleHeaderColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        rightTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Notification.show("Current Value: " + event.isChecked() + "\nPrropertyId: " + event.getPropertyId());
            }
        });
        layout.addComponent(pagedFreezeFilterTable);
        layout.addComponent(pagedFreezeFilterTable.createControls());
        final CheckBox sink = new CheckBox("Sink ItemPerPage With PageLength");
        sink.setImmediate(true);
        layout.addComponent(sink);
        sink.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                leftTable.sinkItemPerPageWithPageLength(sink.getValue());
                rightTable.sinkItemPerPageWithPageLength(sink.getValue());
            }
        });
        return layout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (getUI().getTheme().equalsIgnoreCase("customValoTheme")) {
            tabsheet.removeStyleName("reinder-theme-extfiltertable");
            tabsheet.addStyleName("valo-theme-extfiltertable");
        } else {

            tabsheet.removeStyleName("valo-theme-extfiltertable");
            tabsheet.addStyleName("reinder-theme-extfiltertable");
        }
    }
//    private VerticalLayout loadTestingTab(){
//        frequency.addItem("Annually");
//        frequency.addItem("Semi-Annually");
//        frequency.addItem("Monthly");
//        frequency.addItem("Quarterly");
//        frequency.setValue("Quarterly");
//        frequency.setImmediate(true);
//        
//        history.addItem("1 Quarters");
//        history.addItem("2 Quarters");
//        history.addItem("3 Quarters");
//        history.addItem("4 Quarters");
//        history.addItem("5 Quarters");
//        history.addItem("6 Quarters");
//        history.addItem("7 Quarters");
//        history.addItem("8 Quarters");
//        history.addItem("9 Quarters");
//        history.setValue("4 Quarters");
//        history.setImmediate(true);
//        
//
//        actualOrProj.addItem("Actuals");
//        actualOrProj.addItem("Projections");
//        actualOrProj.addItem("Both");
//        actualOrProj.setValue("Both");
//        actualOrProj.setImmediate(true);
//        
//        pivotView.addItem("Period");
//        pivotView.addItem("Variable");
//        pivotView.setValue("Period");
//        pivotView.setImmediate(true);
//        
//        VerticalLayout layout=new VerticalLayout();
//        layout.setMargin(true);
//        layout.setSpacing(true);
//       
//        
//        layout.addComponent(frequency);
//        layout.addComponent(history);
//        layout.addComponent(actualOrProj);
//        layout.addComponent(pivotView);
//        layout.addComponent(pivotView);
//        layout.addComponent(generate);
//        initResult();
//        layout.addComponent(periodTableId);
//        generate.addClickListener(new Button.ClickListener() {
//
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//               periodTableId.reConstruct(true);
//               initResult1();
//            }
//        });
//        return layout;
//    }
//    private void initResult() {
//        periodTableId.setSizeFull();
//        periodTableId.markAsDirty();
//        
//        periodTableId.setSelectable(true);
//        periodTableId.setEditable(true);
//        periodTableId.setImmediate(true);
//        periodTableId.setPageLength(7);
//        Map<String, String> selection = new HashMap<String, String>();
//            
//        selection.put("frequency", frequency.getValue().toString());
//        selection.put("history", history.getValue().toString());
//        selection.put("projectFrequency", history.getValue().toString());
//        selection.put("projection", actualOrProj.getValue().toString());
//        selection.put("year", "ALL");
//        selection.put("no", "0");
////         selection.put("frequency", "Quarterly");
////        selection.put("history", "4 Quarters");
////        selection.put("projectFrequency", "4 Quarters");
////        selection.put("projection", "Both");
////        selection.put("pivotView", "Variable");
//        
//        CustomTableHeaderDTO rightHeader = HeaderUtils.getProjectionResultsRightTableColumns(selection);
//        
//        CustomTreeContainer<ProjectionResultsDTO> resultBeamContainer = new CustomTreeContainer<ProjectionResultsDTO>(ProjectionResultsDTO.class);
//        resultBeamContainer.setColumnProperties(rightHeader.getProperties());
//        periodTableId.setContainerDataSource(resultBeamContainer);
//        periodTableId.setVisibleColumns(rightHeader.getSingleColumns().toArray());
//        periodTableId.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
//       
//        periodTableId.setDoubleHeaderVisible(true);
//        periodTableId
//                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
//        periodTableId
//                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
//
//        System.out.println(Arrays.toString(rightHeader.getSingleColumns().toArray(new String[rightHeader.getSingleColumns().size()])));
//        System.out.println(Arrays.toString(rightHeader.getDoubleColumns().toArray(new String[rightHeader.getDoubleColumns().size()])));
//        periodTableId.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
//        periodTableId.setTripleHeaderVisible(true);
//       periodTableId.setTripleHeaderVisibleColumns(rightHeader.getTripleColumns().toArray());
//       periodTableId.setTripleHeaderMap(rightHeader.getTripleHeaderMaps());
//        
//    }

    public void configureTripleHeader(CustomTableHeaderDTO rightHeader) {
        List<Object> hed = rightHeader.getDoubleColumns();
        List<Object> hed1 = new ArrayList<Object>();
        String triplecol = "";
        int i = 0;
        for (int j = 0; j < hed.size() - 2; j++) {
            if (j % 3 == 0) {
                if (j != 0) {
                }
                triplecol = hed.toString();
                rightHeader.addTripleColumn(triplecol);
            }
        }
    }
//    private void initResult1() {
//        
//        Map<String, String> selection = new HashMap<String, String>();
//        selection.put("frequency", frequency.getValue().toString());
//        selection.put("history", history.getValue().toString());
//        selection.put("projectFrequency", history.getValue().toString());
//        selection.put("projection", actualOrProj.getValue().toString());
//        selection.put("year", "ALL");
//        selection.put("no", "5");
//        CustomTableHeaderDTO leftHeader = HeaderUtils.getProjectionResultsLeftTableColumns(selection);
//        
//        CustomTableHeaderDTO rightHeader = HeaderUtils.getProjectionResultsRightTableColumns(selection);
//        CustomTreeContainer<ProjectionResultsDTO> resultBeamContainer = (CustomTreeContainer<ProjectionResultsDTO>)periodTableId.getContainerDataSource();
//        resultBeamContainer.setColumnProperties(rightHeader.getProperties());
//        resultBeamContainer.addBean(new ProjectionResultsDTO());
//        periodTableId.setContainerDataSource(resultBeamContainer);
//        
//        periodTableId.setVisibleColumns(rightHeader.getSingleColumns().toArray());
//        periodTableId.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
//        
//        periodTableId
//                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
//        periodTableId
//                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));
//
//        System.out.println(Arrays.toString(rightHeader.getSingleColumns().toArray(new String[rightHeader.getSingleColumns().size()])));
//        System.out.println(Arrays.toString(rightHeader.getDoubleColumns().toArray(new String[rightHeader.getDoubleColumns().size()])));
//        periodTableId.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
//       periodTableId.setTripleHeaderVisibleColumns(rightHeader.getTripleColumns().toArray());
//       periodTableId.setTripleHeaderMap(rightHeader.getTripleHeaderMaps());
//       periodTableId.markAsDirty();
//        periodTableId.addHeaderClickListener(new ExtCustomTable.HeaderClickListener() {
//
//            @Override
//            public void headerClick(ExtCustomTable.HeaderClickEvent event) {
//                System.out.println("periodTableId.reConstruct= "+periodTableId.isReConstruct());
//            }
//        });

//    }
    public void setExpandIconAction(Object propertuId, boolean collapsed, List<String> discountNames) {
        for (int i = 0; i < discountNames.size(); i++) {
            String commonColumn = propertuId + discountNames.get(i).replace(" ", "");
            periodTableId.setDoubleHeaderColumnCollapsed(commonColumn, collapsed);
            System.out.println("collapsing column= " + commonColumn + "  iscollapse" + periodTableId.isColumnCollapsed(commonColumn));
        }
    }
}