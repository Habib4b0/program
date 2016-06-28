package com.stpl.app.forecastdashboard.ui.form;

import com.stpl.app.forecastdashboard.dto.BigFiveDTO;
import com.stpl.app.forecastdashboard.logic.BigFiveLogic;
import static com.stpl.app.forecastdashboard.utils.CommonUtils.formatDecimalPlaces;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class BigFive extends VerticalLayout implements View {

    private static final Logger LOGGER = Logger.getLogger(BigFive.class);
    Table productTable = new Table();
    Table customerTable = new Table();
    VerticalLayout layout = new VerticalLayout();

    Object visibleColumnCompany[] = {"company", "sales"};
    Object visibleColumnProduct[] = {"product", "sales"};
    String headersProduct[] = {"Product", "Sales ($B)"};
    String headersCompany[] = {"Customers", "Sales ($B)"};
//    BeanItemContainer<BigFiveDTO> container = new BeanItemContainer<BigFiveDTO>(BigFiveDTO.class);
    BigFiveLogic logic = new BigFiveLogic();
    
//    DataFormatConverter dataConverter = new DataFormatConverter();

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

//        MarginInfo margininfo = new MarginInfo(false, true, false, true);
//        setMargin(margininfo);
//        setSpacing(true);
        Panel panel = new Panel();
        panel.setContent(layout);
        addComponent(panel);
        addStyleName("mainLayout");

        TabSheet tabSheet = new TabSheet();
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);

        layout.addComponent(tabSheet);
        layout.setSpacing(true);
        layout.setMargin(true);

        productTable.setImmediate(Boolean.TRUE);
        productTable.setContainerDataSource(loadContainer(true));
        productTable.setVisibleColumns(visibleColumnProduct);
        productTable.setColumnHeaders(headersProduct);
        productTable.setColumnFooter("product", "Top 5 Total");
        productTable.setFooterVisible(Boolean.TRUE);
        productTable.setPageLength(5);
        productTable.setWidth("98%");
//        productTable.setConverter("sales", dataConverter);

        customerTable.setImmediate(Boolean.TRUE);
        customerTable.setContainerDataSource(loadContainer(false));
        customerTable.setVisibleColumns(visibleColumnCompany);
        customerTable.setColumnHeaders(headersCompany);
        customerTable.setColumnFooter("company", "Top 5 Total");
        customerTable.setFooterVisible(Boolean.TRUE);
        customerTable.setPageLength(5);
        customerTable.setWidth("98%");
//        customerTable.setConverter("sales", dataConverter);

        VerticalLayout productTableLayout = new VerticalLayout();
        productTableLayout.addComponent(new Label(""));
        productTableLayout.addComponent(productTable);

        VerticalLayout customerTableLayout = new VerticalLayout();
        customerTableLayout.addComponent(new Label(""));
        customerTableLayout.addComponent(customerTable);

        tabSheet.addTab(productTableLayout, "Products");
        tabSheet.addTab(customerTableLayout, "Customers");
    }

    private BeanItemContainer<BigFiveDTO> loadContainer(boolean isProduct) {
        List<BigFiveDTO> list = new ArrayList<BigFiveDTO>();
        BeanItemContainer<BigFiveDTO> container = new BeanItemContainer<BigFiveDTO>(BigFiveDTO.class);
        list = logic.getResult(isProduct);
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
