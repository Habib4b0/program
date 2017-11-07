/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.ui.form.AdjustmentDetails;

import com.stpl.app.transactional.common.dto.AdjustmentDetailsDTO;
import com.stpl.app.transactional.common.logic.ViewLookuptablelogic;
import com.stpl.app.util.AbstractNotificationUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Rohit.Vignesh
 */
public class PrivateOrPublicView extends Window {

    private static final Logger LOGGER = Logger.getLogger(PrivateOrPublicView.class);
    AdjustmentDetailsDTO adjustmentDetailsDTO = new AdjustmentDetailsDTO();
    ViewLookuptablelogic tableLogic = new ViewLookuptablelogic();
    private final ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    public BeanItemContainer<AdjustmentDetailsDTO> resultsContainer = new BeanItemContainer<>(AdjustmentDetailsDTO.class);

    @UiField("viewName")
    public TextField viewName;

    @UiField("searchBtn")
    public Button searchBtn;

    @UiField("resetBtn")
    public Button resetBtn;

    @UiField("selectBtn")
    public Button selectBtn;

    @UiField("closeBtn")
    public Button closeBtn;

    @UiField("tableLayout")
    VerticalLayout tableLayout;

    @UiField("panelid")
    Panel panelid;

    @UiField("controlLayout")
    HorizontalLayout controlLayout;

    private String indicator = StringUtils.EMPTY;
    public boolean ispublicView = true;
    String viewTextField;
    String transactionLevel;
    boolean selected;
    private CustomTextField privateViewTextField;
    private CustomTextField publicViewTextField;
    public PrivateOrPublicView(String indicator) {
        this.indicator = indicator;
        init();
    }
    public PrivateOrPublicView(String indicator, CustomTextField privateViewTextField, CustomTextField publicViewTextField) {
        this.privateViewTextField = privateViewTextField;
        this.publicViewTextField = publicViewTextField;
        this.indicator = indicator;
        init();
    }

    private void init() {
        addToContent();
        configureFields();
    }

    private void addToContent() {
        setContent(Clara.create(getClass().getResourceAsStream("/ui/adjustment_details/ViewLookup.xml"), this));
        setDraggable(true);
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        center();
        setClosable(true);
        setModal(true);
        setHeight(NumericConstants.FLOAT_EIGHTY, Unit.PERCENTAGE);
        setWidth(NumericConstants.FLOAT_EIGHTY, Unit.PERCENTAGE);
        if (indicator.equalsIgnoreCase(ConstantUtil.PRIVATE)) {
            setCaption(ConstantUtil.PRIVATE_VIEW_LOOK_UP);
        } else if (indicator.equalsIgnoreCase(ConstantUtil.PUBLIC)) {
            setCaption(ConstantUtil.PUBLIC_VIEW_LOOK_UP);
        }

    }

    private void configureFields() {
        configureTable();
        searchButton();
        resetButton();
        selectButton();
        closeButton();
    }

    private void configureTable() {
        viewName.focus();
        selectBtn.setEnabled(false);
        tableLayout.addComponent(resultTable);
        HorizontalLayout tempLayout = CommonUIUtils.getResponsiveControls(tableLogic.createControls());
        CommonUIUtils.getCustomizedComboBox(
                (ComboBox) ((CssLayout) tempLayout.getComponent(0)).getComponent(1));
        tableLayout.addComponent(tempLayout);

        tableLogic.setContainerDataSource(resultsContainer);
        resultTable.setVisibleColumns(CommonUIUtils.getInstance().viewSearchLookupColumns);
        resultTable.setColumnHeaders(CommonUIUtils.getInstance().viewSearchLookupHeader);
        resultTable.setPageLength(NumericConstants.TEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        resultTable.setComponentError(null);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setFilterBarVisible(true);
        resultTable.setImmediate(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultTable.setValidationVisible(false);
        resultTable.addStyleName("filterbar");
        resultTable.setFilterBarVisible(true);

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItem() != null) {
                    selectBtn.setEnabled(true);
                }

            }
        });
    }

    public Button searchButton() {

        searchBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                adjustmentDetailsDTO.setViewName(viewName.getValue());
                adjustmentDetailsDTO.setViewType(indicator);
                adjustmentDetailsDTO.setGenerate(Boolean.TRUE);
                if ((viewName.getValue() == null || String.valueOf(viewName.getValue()).equals("null") || String.valueOf(viewName.getValue()).trim().isEmpty()) || !tableLogic.configureSearchData(adjustmentDetailsDTO)) {
                    AbstractNotificationUtils.getErrorNotification("Invalid Search",
                            "There are no Views that match the search criteria.  Please try again.");
                } else {
                    CommonUIUtils.successNotification("Search Completed");
                }

            }
        });
        return searchBtn;
    }

    public Button resetButton() {

        resetBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the search criteria to its default state", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equalsIgnoreCase(ConstantUtil.YES)) {
                            viewName.setValue(StringUtils.EMPTY);
                            adjustmentDetailsDTO.setGenerate(Boolean.FALSE);
                            tableLogic.configureSearchData(adjustmentDetailsDTO);
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
            }
        });
        return resetBtn;

    }

    private Button closeButton() {
        closeBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                setSelected(Boolean.FALSE);
                close();
            }
        });
        return closeBtn;

    }

    private Button selectButton() {
        selectBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(resultTable.getValue()!=null){
                selectButtonOnClick();
                }else{
                     AbstractNotificationUtils.getErrorNotification("No View Selected",
                            "There is no view selected. Please select a saved view and try again.");
                }
            }

        });
        return selectBtn;
    }

    private void selectButtonOnClick() {
        try {
            if (resultTable.getValue() != null) {
                adjustmentDetailsDTO = (AdjustmentDetailsDTO) resultTable.getValue();
                adjustmentDetailsDTO.setMode(Boolean.TRUE);
                setSelected(Boolean.TRUE);
                if(ConstantUtil.PRIVATE.equals(indicator) && privateViewTextField != null) {
                    if(publicViewTextField != null) {
                        publicViewTextField.setValue(StringUtils.EMPTY);
                    }
                    privateViewTextField.setValue(adjustmentDetailsDTO.getViewName());
                } else if(ConstantUtil.PUBLIC.equals(indicator) && publicViewTextField != null) {
                    if(privateViewTextField != null) {
                        privateViewTextField.setValue(StringUtils.EMPTY);
                    }
                    publicViewTextField.setValue(adjustmentDetailsDTO.getViewName());
                } 
                close();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public AdjustmentDetailsDTO getAdjustmentDetailsDTO() {
        return adjustmentDetailsDTO;
    }

    public void setAdjustmentDetailsDTO(AdjustmentDetailsDTO adjustmentDetailsDTO) {
        this.adjustmentDetailsDTO = adjustmentDetailsDTO;
    }

}
