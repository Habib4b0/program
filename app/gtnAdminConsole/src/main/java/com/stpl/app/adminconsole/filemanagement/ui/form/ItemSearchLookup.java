/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.form;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.filemanagement.dto.FileManagementFilterGenerator;
import com.stpl.app.adminconsole.filemanagement.dto.ItemSearchDTO;
import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.stpl.app.adminconsole.filemanagement.logic.tablelogic.ItemSearchTableLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import static com.stpl.app.adminconsole.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.app.adminconsole.util.ValidationUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import static com.vaadin.server.Sizeable.UNITS_PERCENTAGE;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import static com.stpl.app.adminconsole.util.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Class FileManagementLookup.
 *
 * @author Elangovan
 */
public class ItemSearchLookup extends Window {

    @UiField("itemName")
    private TextField itemName;
    @UiField("systemId")
    private TextField systemId;
    @UiField("itemNo")
    private TextField itemNo;
    @UiField("itemDesc")
    private TextField itemDesc;
    @UiField("itemType")
    private ComboBox itemType;
    @UiField("therapyClass")
    private ComboBox therapyClass;
    @UiField("identifierType")
    private ComboBox identifierType;
    @UiField("itemStatus")
    private ComboBox itemStatus;
    @UiField("brand")
    private ComboBox brand;
    @UiField("identifier")
    private TextField identifier;
    @UiField("ndc9")
    private TextField ndc9;
    @UiField("ndc8")
    private TextField ndc8;
    @UiField("search")
    Button search;
    @UiField("reset")
    private Button reset;
    @UiField("select")
    Button select;
    @UiField("close")
    private Button close;
    @UiField("cssLayout")
    CssLayout cssLayout;
    FileManagementLogic logic = new FileManagementLogic();
    TextField itemNumber;
    TextField itemLookupName;
    ItemSearchDTO itemSearchDTO = new ItemSearchDTO();
    private ErrorfulFieldGroup itemSearchBinder = new ErrorfulFieldGroup(new BeanItem<ItemSearchDTO>(itemSearchDTO));
    private final ErrorLabel errorMsg = new ErrorLabel();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ItemSearchLookup.class);
    private final BeanItemContainer<ItemSearchDTO> itemBean = new BeanItemContainer<ItemSearchDTO>(ItemSearchDTO.class);
    @UiField("tableLayout")
    VerticalLayout tableLayout;
    private HorizontalLayout controlLayout = new HorizontalLayout();
    ItemSearchTableLogic tableLogic = new ItemSearchTableLogic();
    ExtPagedTable resultsTable = new ExtPagedTable(tableLogic);
    CommonUtil commonUtil = new CommonUtil();
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
    SessionDTO sessionDTO;
    int masterSid = 0;
    String itemId = StringUtils.EMPTY;
   public boolean isSelected=false;
   String currentItemNumber="";
   String currentItemLookUpName="";

    public ItemSearchLookup(TextField itemNo, TextField itemLookupName, final SessionDTO sessionDTO) {
        super("Item Search");
        this.itemNumber = itemNo;
        this.itemLookupName = itemLookupName;
        this.sessionDTO = sessionDTO;
        this.currentItemNumber = itemNo.getValue();
        this.currentItemLookUpName = itemLookupName.getValue();
    }

    public int getMasterSid() {
        return masterSid;
    }

    public String getItemId() {
        return itemId;
    }

    public void init() throws PortalException, SystemException {
        setId("COMPANY");
        setStyleName("bootstrap-ui");
        addStyleName("bootstrap-company");
        addStyleName("bootstrap");
        center();
        setClosable(true);
        setModal(true);
        center();
        setWidth("99%");
        setHeight("99%");
        setContent(Clara.create(getClass().getResourceAsStream("/clara/itemSearchLookup.xml"), this));
        getBinder();

        configureTable();
        configureFields();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {
        LOGGER.debug("getBinder method started");
        itemSearchBinder.bindMemberFields(this);
        itemSearchBinder.setItemDataSource(new BeanItem<ItemSearchDTO>(itemSearchDTO));
        itemSearchBinder.setBuffered(true);
        itemSearchBinder.setErrorDisplay(errorMsg);
        LOGGER.debug("getBinder method returns getBinder");
        return itemSearchBinder;
    }

    
    protected void addDoubleClick(final ItemClickEvent event) {
        LOGGER.debug("Entering addItemsButtonClick method ");

        try {
            Object obj = resultsTable.getItem(event.getItemId());
            ItemSearchDTO dto = getBeanFromId(obj);
            itemNumber.setValue(dto.getItemNo());
            itemLookupName.setValue(dto.getItemName());
            itemId = dto.getItemId();
            masterSid = Integer.valueOf(dto.getSystemId());
            isSelected=true;
            close();
        } catch (Exception e) {
            LOGGER.error(e);

        }
        LOGGER.debug(" addItemsButtonClick method Ended");
    }

    private void configureFields() throws PortalException, SystemException{
        
        CommonUtils commonUtil = CommonUtils.getInstance();
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        helperListUtil.loadValuesWithListName("filemanagement");
        FileManagementLogic fileLogic = new FileManagementLogic();

        commonUtil.loadComboBox(itemStatus, "STATUS", false);
        commonUtil.loadComboBox(therapyClass, "THERAPEUTIC_CLASS", false);
        commonUtil.loadComboBox(itemType, "ITEM_TYPE", false);

        ndc8.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR, ValidationUtils.SPECIAL_CHAR_MSG));
        ndc8.setImmediate(true);
        ndc8.setValidationVisible(true);
        ndc8.setRequired(true);
        ndc8.addValidator(new StringLengthValidator(" NDC8 Should be  8 characters", NumericConstants.EIGHT, NumericConstants.EIGHT, true));
        ndc8.setDescription(ndc8.getValue());
        ndc8.addValueChangeListener(new Property.ValueChangeListener() {
           
            public void valueChange(final Property.ValueChangeEvent event) {
                ndc8.setDescription(ndc8.getValue());
            }
        });

        ndc9.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR, ValidationUtils.SPECIAL_CHAR_MSG));
        ndc9.setImmediate(true);
        ndc9.setValidationVisible(true);
        ndc9.setRequired(true);
        ndc9.addValidator(new StringLengthValidator(" NDC9 Should be 9 characters", NumericConstants.NINE, NumericConstants.NINE, true));
        ndc9.setDescription(ndc9.getValue());
        ndc9.addValueChangeListener(new Property.ValueChangeListener() {
            
            public void valueChange(final Property.ValueChangeEvent event) {
                ndc9.setDescription(ndc9.getValue());
            }
        });

        new FileManagementLogic().getNativeSelect(identifierType, fileLogic.getItemQualifierNameResults());
        identifierType.setNullSelectionAllowed(true);
        identifierType.setInputPrompt(ConstantsUtils.SELECT_ONE);
        identifierType.setImmediate(true);
        identifierType.setDescription(String.valueOf((Integer) identifierType.getValue()));
        identifierType.addValueChangeListener(new Property.ValueChangeListener() {
            
            public void valueChange(final Property.ValueChangeEvent event) {
                identifierType.setDescription(String.valueOf((Integer) identifierType.getValue()));
            }
        });
        new FileManagementLogic().getNativeSelect(brand, fileLogic.getBrandResults());
        brand.setNullSelectionAllowed(true);
        brand.setInputPrompt(ConstantsUtils.SELECT_ONE);
        brand.setImmediate(true);
        brand.setDescription(String.valueOf((Integer) brand.getValue()));
        brand.addValueChangeListener(new Property.ValueChangeListener() {
            
            public void valueChange(final Property.ValueChangeEvent event) {
                brand.setDescription(String.valueOf((Integer) brand.getValue()));
            }
        });
        search.addClickListener(new Button.ClickListener() {
          
            public void buttonClick(final Button.ClickEvent event) {
                try {
                    ItemSearchDTO itemDto = new ItemSearchDTO();
                    itemDto.setSystemId(systemId.getValue().trim());
                    itemDto.setItemName(itemName.getValue().trim());
                    itemDto.setItemNo(itemNo.getValue().trim());
                    itemDto.setHelperTherapyClass(therapyClass.getValue() != null ? (HelperDTO) therapyClass.getValue() : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                    itemDto.setNdc9(ndc9.getValue().trim());
                    itemDto.setNdc8(ndc8.getValue().trim());
                    itemDto.setIdentifierType(String.valueOf(identifierType.getValue()) != null ? String.valueOf(identifierType.getValue()) : "0");
                    itemDto.setIdentifier(identifier.getValue().trim());
                    itemDto.setBrand(String.valueOf(brand.getValue()) != null ? String.valueOf(brand.getValue()) : "0");
                    itemDto.setHelperStatus(itemStatus.getValue() != null ? (HelperDTO) itemStatus.getValue() : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                    itemDto.setHelperItemType(itemType.getValue() != null ? (HelperDTO) itemType.getValue() : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                    itemDto.setItemDesc(itemDesc.getValue().trim());
                    tableLogic.configureSearchData(itemDto);
                    resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    resultsTable.setFilterGenerator(new FileManagementFilterGenerator());
                    resultsTable.setImmediate(true);
                    resultsTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
                    resultsTable.addStyleName("TableCheckBox");
                    resultsTable.setSelectable(true);
                    resultsTable.markAsDirtyRecursive();

                    if (!tableLogic.isResultsEmpty()) {
                        Notification.show("Search Completed");
                    } else {
                        MessageBox.showPlain(Icon.ERROR, "No Matching Records", "There were no records matching the search Criteria. Please try Again", ButtonId.OK);
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            }
        });

        close.addClickListener(
                new Button.ClickListener() {
                   
                    public void buttonClick(final Button.ClickEvent event) {
                        if (!isSelected) {
                            itemNumber.setValue(StringUtils.EMPTY);
                            itemLookupName.setValue(StringUtils.EMPTY);
                        }
                        close();
                        
                    }
                });

        select.addClickListener(new Button.ClickListener() {
           
            public void buttonClick(final Button.ClickEvent event) {
                if (resultsTable.getValue() == null) {
                    AbstractNotificationUtils.getErrorNotification("Select Error", "Please click on a record within the results list view");
                } else {
                    isSelected=true;
                    close();
                }
            }
        });
        reset.addClickListener(new Button.ClickListener() {
            
            public void buttonClick(final Button.ClickEvent event) {
                LOGGER.debug("In configureFields resetButtonClickLogic started");
                MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION, "Are you sure you want to reset the page to default/previous values"
                        + ConstantsUtils.QUESTION_MARK, new MessageBoxListener() {
                          
                            @SuppressWarnings("PMD")
                            public void buttonClicked(final ButtonId buttonId) {
                                if (buttonId.name().equals(ConstantsUtils.YES)) {
                                    resetButtonClickLogic();
                                }
                            }
                        }, ButtonId.YES, ButtonId.NO);
                LOGGER.debug("In configureFields resetButtonClickLogic Ended");
            }
        });

    }
    /* Iterate the object and set value in DTO */

    public ItemSearchDTO getBeanFromId(final Object obj) {
        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof ItemSearchDTO) {
            targetItem = new BeanItem<ItemSearchDTO>((ItemSearchDTO) obj);
        }
        return (ItemSearchDTO) targetItem.getBean();
    }

    /**
     * reset button logic
     */
    private void resetButtonClickLogic() {
        systemId.setValue(StringUtils.EMPTY);
        itemName.setValue(StringUtils.EMPTY);
        itemNo.setValue(StringUtils.EMPTY);
        itemDesc.setValue(StringUtils.EMPTY);
        ndc8.setValue(StringUtils.EMPTY);
        ndc9.setValue(StringUtils.EMPTY);
        identifier.setValue(StringUtils.EMPTY);
        itemType.setValue(null);
        itemStatus.setValue(null);
        therapyClass.setValue(null);
        brand.setValue(null);
        identifierType.setValue(null);
    }

    private void configureTable() {

        tableLayout.addComponent(resultsTable);
        getResponsiveControls(tableLogic.createControls(), controlLayout);
        tableLayout.addComponent(controlLayout);

        tableLogic.setContainerDataSource(itemBean);
        tableLogic.setPageLength(NumericConstants.FIFTEEN);
        tableLogic.sinkItemPerPageWithPageLength(false);
        setTableDefaultConfig();
        resultsTable.setSelectable(true);
        resultsTable.markAsDirty();
        resultsTable.setComponentError(null);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new FileManagementFilterGenerator());
        resultsTable.setValidationVisible(false);
        resultsTable.addStyleName("filterbar");

        resultsTable.setSelectable(true);
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @SuppressWarnings("PMD")
            /**
             * called when item has clicked
             */
            public void itemClick(final ItemClickEvent event) {
                try {
                    Object obj = resultsTable.getItem(event.getItemId());
                    ItemSearchDTO dto = getBeanFromId(obj);
                    itemNumber.setValue(dto.getItemNo());
                    itemLookupName.setValue(dto.getItemName());
                    itemId = dto.getItemId();
                    masterSid = Integer.valueOf(dto.getSystemId());
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            /**
             * method to listen the action
             *
             */
            public void itemClick(final ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    addDoubleClick(event);
                }
            }
        });

    }

    public void setTableDefaultConfig() {

        resultsTable.setVisibleColumns("systemId", "itemNo", "itemName", "itemDesc", "itemType", "itemStatus", "therapyClass", "brand", "ndc9", "ndc8", "identifierType", "identifier");
        resultsTable.setColumnHeaders("System ID", "Item #", "Item Name", "Item Desc", "Item Type", "Status", "Theraphy Class", "Brand", "NDC 9", "NDC 8", "Identifier Type", "Identifier");
        resultsTable.markAsDirtyRecursive();
        resultsTable.setImmediate(true);
        resultsTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
        resultsTable.setHeight("250px");

    }
}
