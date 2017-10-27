package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.dto.BigFiveDTO;
import com.stpl.app.forecastdashboard.logic.BigFiveLogic;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import static com.stpl.app.forecastdashboard.utils.CommonUtils.formatDecimalPlaces;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.asi.ui.extfilteringtable.paged.ExtPagedFilterTable;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class BigFive extends VerticalLayout implements View {

    ExtPagedFilterTable productTable = new ExtPagedFilterTable();
    ExtPagedFilterTable customerTable = new ExtPagedFilterTable();
    VerticalLayout layout = new VerticalLayout();

    Object visibleColumnCompany[] = {"company", "sales"};
    Object visibleColumnProduct[] = {"product", "sales"};
    String headersProduct[] = {"Product", "Sales ($)"};
    String headersCompany[] = {"Customers", "Sales ($)"};
    ComboBox valueDDLB = new ComboBox();
    BigFiveLogic logic = new BigFiveLogic();

    public BigFive() {
        init();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void init() {
        configureFields();
    }

    private void configureFields() {
        HorizontalLayout glayout = new HorizontalLayout();
        glayout.addStyleName("forecat-actual-top");
        valueDDLB.addItems("5", "10", "15");
        valueDDLB.setNullSelectionAllowed(false);
        valueDDLB.select("5");   
        valueDDLB.addStyleName("top-actual-combo");
        Panel panel = new Panel();
        panel.setContent(layout);
        addComponent(panel);
        addStyleName("mainLayout");

        TabSheet tabSheet = new TabSheet();
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        VerticalLayout vlLayout = new VerticalLayout();
        vlLayout.setSpacing(true);
        HorizontalLayout hlLayout = new HorizontalLayout();
        Label frequencyLabel = new Label("Top : ");
        CommonUtils.formatLabel(frequencyLabel);
        glayout.addComponent(frequencyLabel);
        glayout.addComponent(valueDDLB);
        glayout.setSpacing(true);
        glayout.setMargin(true);
        vlLayout.addComponent(glayout);
        layout.addComponent(vlLayout);
        layout.addComponent(tabSheet);
        layout.setSpacing(true);
        layout.setMargin(true);

        loadTable(String.valueOf(valueDDLB.getValue()));
        VerticalLayout productTableLayout = new VerticalLayout();
        productTableLayout.addComponent(new Label(""));
        productTableLayout.addComponent(productTable);
        HorizontalLayout prodLayout = new HorizontalLayout();
        prodLayout.setSpacing(true);
        prodLayout.setMargin(true);
        getResponsiveControls(productTable.createControls(), prodLayout);
        productTableLayout.addComponent(prodLayout);
        VerticalLayout customerTableLayout = new VerticalLayout();
        customerTableLayout.addComponent(new Label(""));
        HorizontalLayout customerLayout = new HorizontalLayout();
        customerLayout.setSpacing(true);
        customerLayout.setMargin(true);
        
        getResponsiveControls(customerTable.createControls(), customerLayout);
        customerTableLayout.addComponent(customerTable);
        customerTableLayout.addComponent(customerLayout);

        tabSheet.addTab(productTableLayout, "Products");
        tabSheet.addTab(customerTableLayout, "Customers");
        valueDDLB.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                loadTable(String.valueOf(valueDDLB.getValue()));
            }
        });
    }

    public void getResponsiveControls(HorizontalLayout tempLayout, HorizontalLayout controlBar) {
        controlBar.setStyleName("responsivePagedTable");
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);
        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        for (int index = 0; index < 8; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
    }

    public void loadTable(String valueDDLB) {
        productTable.setImmediate(Boolean.TRUE);
        productTable.setContainerDataSource(loadContainer(true, valueDDLB));
        productTable.setVisibleColumns(visibleColumnProduct);
        productTable.setColumnHeaders(headersProduct);
        productTable.setColumnFooter("product", "Top " + valueDDLB + " Total");
        productTable.setFooterVisible(Boolean.TRUE);
        productTable.setPageLength(10);
        productTable.setItemsPerPage(10);
        productTable.setWidth("98%");
        productTable.setColumnWidth("product", 300);

        customerTable.setImmediate(Boolean.TRUE);
        customerTable.setContainerDataSource(loadContainer(false, valueDDLB));
        customerTable.setVisibleColumns(visibleColumnCompany);
        customerTable.setColumnHeaders(headersCompany);
        customerTable.setColumnFooter("company", "Top " + valueDDLB + " Total");
        customerTable.setFooterVisible(Boolean.TRUE);
        customerTable.setPageLength(10);
        customerTable.setItemsPerPage(10);
        customerTable.setWidth("98%");
        customerTable.setColumnWidth("company", 300);

    }

    private BeanItemContainer<BigFiveDTO> loadContainer(boolean isProduct, String valueDDLB) {
        List<BigFiveDTO> list = new ArrayList<BigFiveDTO>();
        BeanItemContainer<BigFiveDTO> container = new BeanItemContainer<BigFiveDTO>(BigFiveDTO.class);
        list = logic.getResult(isProduct, valueDDLB);
        container.removeAllItems();
        BigFiveDTO totaldto = list.get(list.size() - 1);
        list.remove(totaldto);
        container.addAll(list);
        if (isProduct) {
            productTable.setColumnFooter("sales", totaldto.getSales());
        } else {
            customerTable.setColumnFooter("sales", totaldto.getSales());
        }
        return container;
    }

    private class DataFormatConverter implements Converter<String, String> {

        @Override
        public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws ConversionException {
            return value;
        }

        @Override
        public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws ConversionException {
            return formatDecimalPlaces(value);
        }

        @Override
        public Class<String> getModelType() {
            return String.class;
        }

        @Override
        public Class<String> getPresentationType() {
            return String.class;
        }

    }

}
