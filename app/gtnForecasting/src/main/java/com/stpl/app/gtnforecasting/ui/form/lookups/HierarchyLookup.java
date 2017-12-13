/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.forecastabstract.lookups.AbstractHierarchyLookup;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_RESET;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SEARCH;
import static com.stpl.app.utils.Constants.LabelConstants.PRIMARY;
import com.stpl.app.utils.DateToStringConverter;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyLookup.
 *
 * @author soundarrajan
 */
public class HierarchyLookup extends AbstractHierarchyLookup {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(HierarchyLookup.class);
    /**
     * Indicates whether it is customer lookup or product lookup.
     */
    private String indicator;

    /**
     * The hierarchyName TextField for searching.
     */
    private TextField hierarchyName;

    /**
     * hierarchyLookup TextField.
     */
    private final TextField hrchyLookup;

    /**
     * The search result table.
     */
    private ExtFilterTable results;

    private final Button selectBtn = new Button(Constants.ButtonConstants.BTN_SELECT.getConstant());
    /**
     * Container for results table.
     */
    private OptionGroup hierarchyType;

    private final Button searchBtn = new Button(BTN_SEARCH.getConstant());
    private final Button resetBtn = new Button(BTN_RESET.getConstant());
    /**
     * Container for results table.
     */
    private final BeanItemContainer<HierarchyLookupDTO> resultBean = new BeanItemContainer<>(HierarchyLookupDTO.class);

    /**
     * Hierarchy DTO.
     */
    private HierarchyLookupDTO hierarchyDto;

    /**
     * Constructor for Hierarchy Lookup window.
     *
     * @param indicator Indicates whether it is customer lookup or product
     * @param windowName window name for lookup lookup
     * @param hierarchyLookup the textfield which opens this popup
     * @param hierarchyDto to return the selected hierarchy information
     */
    public HierarchyLookup(final String indicator, final String windowName, final TextField hierarchyLookup, HierarchyLookupDTO hierarchyDto) {
        super(windowName);
        this.indicator = indicator;
        this.hrchyLookup = hierarchyLookup;
        this.hierarchyDto = hierarchyDto;
        initializeComponents();
        setContent(buildHierarchyLookup(hierarchyName, hierarchyType, results, searchBtn, resetBtn, selectBtn));
        configureFields();
    }

    /**
     * Initializes the components.
     */
    private void initializeComponents() {
        setHierarchyName(UiUtils.addTextField());
        hierarchyName.setImmediate(true);
        hierarchyName.focus();
        hierarchyType = new OptionGroup();

        results = new ExtFilterTable();
        results.setContainerDataSource(resultBean);
        results.setSelectable(true);
        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setStyleName(Constant.FILTER_TABLE);
        results.setPageLength(NumericConstants.TEN);
        selectBtn.setEnabled(false);

        results.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(results.getValue() != null) {
                    selectBtn.setEnabled(true);
                } else {
                    selectBtn.setEnabled(false);
                }
            }
        });
    }
    /**
     * Configures all the components.
     */
    private void configureFields() {
        // Fields to be configured over here
        results.addStyleName("custom-table-header-alignment");
        results.setColumnAlignments(ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,
                ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT);
        hierarchyName.setMaxLength(Constants.LengthConstants.LENGTH_200.getConstant());
        hierarchyName.setImmediate(true);
        hierarchyName.setConverter(new TextFieldConverter());
        results.setConverter("createdDateSearch", new DateToStringConverter());
        results.setConverter("modifiedDateSearch", new DateToStringConverter());
        results.refreshRowCache();
    }
    @Override
    protected void btnSearchLogic() {
        try {
            DataSelectionLogic logic = new DataSelectionLogic();
            List<HierarchyLookupDTO> list = logic.getHierarchyGroup(String.valueOf(hierarchyName.getValue()), String.valueOf(getHierarchyType().getValue()), indicator, BTN_SEARCH.getConstant());
            if (list != null && !list.isEmpty()) {
                resultBean.removeAllItems();
                resultBean.addAll(list);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Results Found", "There are no Hierarchies that match the search criteria.");
                resultBean.removeAllItems();
            }
        } catch (ParseException ex) {
            LOGGER.error(ex + " Hierarchy search logic");
        }
    }

    @Override
    protected void btnResetLogic() {
        NotificationUtils notificationUtils = new NotificationUtils() {
            @Override
            public void yesMethod() {
        hierarchyName.setValue(StringUtils.EMPTY);
        hierarchyType.select(PRIMARY.getConstant());
        selectBtn.setEnabled(false);
            }

            @Override
            public void noMethod() {
                LOGGER.debug("Inside overriden method: Do nothing");
            }
        };
        notificationUtils.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

    }

    /**
     * Overload and customize select logic.
     */
    @Override
    protected void btnLookupSelectLogic() {
        if (results.getValue() != null) {
            hierarchyDto = (HierarchyLookupDTO) results.getValue();
            hrchyLookup.setValue(String.valueOf(hierarchyDto.getHierarchyName()).trim());
            try {
            } catch (Exception ex) {
                Logger.getLogger(HierarchyLookup.class.getName()).log(Level.SEVERE, null, ex);
            }

            close();
        }
    }

    /**
     * Gets the indicator.
     *
     * @return the indicator
     */
    public String getIndicator() {
        return indicator;
    }

    /**
     * Sets the indicator.
     *
     * @param indicator the new indicator
     */
    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    /**
     * Gets the hierarchy name.
     *
     * @return the hierarchy name
     */
    public TextField getHierarchyName() {
        return hierarchyName;
    }

    /**
     * Sets the hierarchy name.
     *
     * @param hierarchyName the new hierarchy name
     */
    public void setHierarchyName(TextField hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    /**
     * Gets the results.
     *
     * @return the results
     */
    public ExtFilterTable getResults() {
        return results;
    }

    /**
     * Sets the results.
     *
     * @param results the new results
     */
    public void setResults(ExtFilterTable results) {
        this.results = results;
    }

    public HierarchyLookupDTO getHierarchyDto() {
        return hierarchyDto;
    }

    public void setHierarchyDto(HierarchyLookupDTO hierarchyDto) {
        this.hierarchyDto = hierarchyDto;
    }


    @Override
    protected void btnCloseLogic() {
        close();
    }

    @Override
    protected void configureResultTable(ExtPagedTable results, String indicator) {
        LOGGER.debug("Inside Overriden method: do nothing");
    }
}
