package com.stpl.app.global.ifp.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.dto.ItemFamilyplanMasterDTO;
import com.stpl.app.global.ifp.dto.ItemFamilyplanSearchDTO;
import com.stpl.app.global.ifp.ui.lazyload.IFPParentLookupContainer;
import com.stpl.app.global.ifp.ui.lazyload.IFPParentLookupCriteria;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.ui.IFPFilterGenerator;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;


// TODO: Auto-generated Javadoc
/**
 * The Class ParentIFPIdLookup.
 */
public final class ParentIFPIdLookup extends Window {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PricingResults.class);
    
     /**
     * The error msg.
     */
    @UiField("errorLB")
    private ErrorLabel errorMsg;
    /**
     * The company family plan id.
     */

    @UiField("itemFamilyplanIdLB")
    private Label itemFamilyplanIdLB;
    @UiField("itemFamilyplanId")
    private TextField itemFamilyplanId;
    /**
     * The company family plan name.
     */
    @UiField("itemFamilyplanNoLB")
    private Label itemFamilyplanNoLB;
    @UiField("itemFamilyplanNo")
    private TextField itemFamilyplanNo;
    /**
     * The company family plan status.
     */
    @UiField("itemFamilyplanStatusLB")
    private Label itemFamilyplanStatusLB;
    @UiField("itemFamilyplanStatus")
    private ComboBox itemFamilyplanStatus;
    /**
     * The company family plan no.
     */
    @UiField("itemFamilyplanNameLB")
    private Label itemFamilyplanNameLB;
    @UiField("itemFamilyplanName")
    private TextField itemFamilyplanName;
    /**
     * The company family plan type.
     */
    @UiField("itemFamilyplanTypeLB")
    private Label itemFamilyplanTypeLB;
    @UiField("itemFamilyplanType")
    private ComboBox itemFamilyplanType;
    
   
    /**
     * The space.
     */
    private  final Label space = new Label(ConstantsUtils.SPACE, ContentMode.HTML);
    
    /**
     * The parent item familyplan id.
     */
    private  TextField parentItemFamilyplanId = new TextField();
    /**
     * The parent item familyplan name.
     */
    private  TextField parentItemFamilyplanName = new TextField();
    /**
     * The ifp logic.
     */
    private  final ItemSearchLogic ifpLogic = new ItemSearchLogic();

    public IFPParentLookupCriteria ifpCriteria = new IFPParentLookupCriteria();
    /**
     * The binder.
     */
    private  ErrorfulFieldGroup binder;
    /**
     * The result table.
     */
    @UiField("resultTable")
    private  ExtFilterTable resultTable;
    /**
     * The search results.
     */
    private LazyBeanItemContainer searchResults;
    /**
     * The search.
     */
    @UiField("search")
    private Button search;
    /**
     * The reset.
     */
    @UiField("reset")
    private Button reset;
    /**
     * Select Button
     */
    @UiField("selectBtn")
    private Button selectBtn;
    /**
     * Close Button
     */
    @UiField("closeBtn")
    private Button closeBtn;
    /**
     * Flag for item click
     */
    private Boolean itemClicked = false;

    /**  A dummy BeanItemContainer to avoid load issue in empty lazy bean container. */
    private BeanItemContainer<ItemFamilyplanSearchDTO> dummySearchResulbeans = new BeanItemContainer<ItemFamilyplanSearchDTO>(ItemFamilyplanSearchDTO.class);
    
    private CommonUtil commonUtil = CommonUtil.getInstance();

	/**
	 * Gets the parent item familyplan id.
	 *
	 * @return the parentItemFamilyplanId
	 */
	public TextField getParentItemFamilyplanId() {
		return parentItemFamilyplanId;
	}

	/**
	 * Sets the parent item familyplan id.
	 *
	 * @param parentItemFamilyplanId the parentItemFamilyplanId to set
	 */
	public void setParentItemFamilyplanId(final TextField parentItemFamilyplanId) {
		this.parentItemFamilyplanId = parentItemFamilyplanId;
	}

	/**
	 * Gets the parent item familyplan name.
	 *
	 * @return the parentItemFamilyplanName
	 */
	public TextField getParentItemFamilyplanName() {
		return parentItemFamilyplanName;
	}

	/**
	 * Sets the parent item familyplan name.
	 *
	 * @param parentItemFamilyplanName the parentItemFamilyplanName to set
	 */
	public void setParentItemFamilyplanName(final TextField parentItemFamilyplanName) {
		this.parentItemFamilyplanName = parentItemFamilyplanName;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public ErrorLabel getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Gets the space.
	 *
	 * @return the space
	 */
	public Label getSpace() {
		return space;
	}

	/**
	 * Gets the item familyplan id.
	 *
	 * @return the item familyplan id
	 */
	public TextField getItemFamilyplanId() {
		return itemFamilyplanId;
	}

	/**
	 * Gets the item familyplan name.
	 *
	 * @return the item familyplan name
	 */
	public TextField getItemFamilyplanName() {
		return itemFamilyplanName;
	}

	/**
	 * Gets the item familyplan no.
	 *
	 * @return the item familyplan no
	 */
	public TextField getItemFamilyplanNo() {
		return itemFamilyplanNo;
	}

	/**
	 * Gets the item familyplan status.
	 *
	 * @return the item familyplan status
	 */
	public ComboBox getItemFamilyplanStatus() {
		return itemFamilyplanStatus;
	}

	/**
	 * Gets the item familyplan type.
	 *
	 * @return the item familyplan type
	 */
	public ComboBox getItemFamilyplanType() {
		return itemFamilyplanType;
	}

	/**
	 * Gets the ifp logic.
	 *
	 * @return the ifp logic
	 */
	public ItemSearchLogic getIfpLogic() {
		return ifpLogic;
	}

	/**
	 * Gets the result table.
	 *
	 * @return the result table
	 */
	public ExtFilterTable getResultTable() {
		return resultTable;
	}

	/**
	 * Gets the search results.
	 *
	 * @return the search results
	 */
	public LazyBeanItemContainer getSearchResults() {
		return searchResults;
	}

	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public Button getSearch() {
		return search;
	}

	/**
	 * Gets the reset.
	 *
	 * @return the reset
	 */
	public Button getReset() {
		return reset;
	}

	/**
	 * Sets the binder.
	 *
	 * @param binder the new binder
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
	}

        /**
         * Gets the dummy search result bean.
         *
         * @return the dummy search result bean
         */
        public BeanItemContainer<ItemFamilyplanSearchDTO> getDummySearchResulbeans() {
            return dummySearchResulbeans;
        }

        /**
         * Sets the dummy search result bean.
         *
         * @param dummySearchResulbeans - the dummy search result bean to be set
         */
        public void setDummySearchResulbeans(final BeanItemContainer<ItemFamilyplanSearchDTO> dummySearchResulbeans) {
            this.dummySearchResulbeans = dummySearchResulbeans;
        }

        /**
         * Sets the search results.
         *
         * @param searchResults the new search results
         */
        public void setSearchResults(final LazyBeanItemContainer searchResults) {
                this.searchResults = searchResults;
        }

        
    /**
     * Instantiates a new parent ifp id lookup.
     *
     * @param parentItemFamilyplanId the parent item familyplan id
     * @param parentItemFamilyplanName the parent item familyplan name
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public ParentIFPIdLookup(final TextField parentItemFamilyplanId, final TextField parentItemFamilyplanName) throws SystemException, Exception {
        super("Item Family Plan Search");
    	this.parentItemFamilyplanId = parentItemFamilyplanId;
        this.parentItemFamilyplanName = parentItemFamilyplanName;
        init();
        }

    /**
     * Initial method when the Constructor get calls.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void init() throws SystemException, Exception {
        center();
        setClosable(true);
        setModal(true);
        setHeight("600px");
        addToContent();
        configureFields();
        getBinder();
        validateFields();
        
    }
    
         public void validateFields() {
        Collection<Field<?>> collection = binder.getFields();
        CommonUtil commmonUtil = CommonUtil.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
            }
        }
    }

    /**
     * Method to load all the contents in a form.
     *
     * @return the panel
     */
    private void addToContent() throws PortalException, SystemException {
    
        setContent(Clara.create(getClass().getResourceAsStream("/declarativeui/item_family_plan/parentifpidlookup.xml"), this));
        
        addTabel();
        

    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {

        binder = new ErrorfulFieldGroup(
                new BeanItem<ItemFamilyplanMasterDTO>(new ItemFamilyplanMasterDTO()));
        binder.setBuffered(true);
        binder.bindMemberFields(this);
        binder.setErrorDisplay(errorMsg);
         
        return binder;
    }

   
    public HorizontalLayout addToGrid() {

        final HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setStyleName("responsiveTabGrid");
        final CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(new Label("IFP ID"));
        cssLayout.addComponent(itemFamilyplanId);
        cssLayout.addComponent(new Label("IFP No"));
        cssLayout.addComponent(itemFamilyplanNo);
        cssLayout.addComponent(new Label("IFP Name"));
        cssLayout.addComponent(itemFamilyplanName);
        cssLayout.addComponent(new Label("IFP Status"));
        cssLayout.addComponent(itemFamilyplanStatus);
        cssLayout.addComponent(new Label("IFP Type"));
        cssLayout.addComponent(itemFamilyplanType);
        hLayout.addComponent(cssLayout);
        return hLayout;

    }

    /**
     * Adds the tabel.
     *
     * @return the table
     */
    private void addTabel() {
      
        resultTable.setPageLength(10);
        resultTable.setImmediate(true);
        resultTable.setSelectable(true);
        resultTable.setWidth(98, UNITS_PERCENTAGE);

        resultTable.setContainerDataSource(dummySearchResulbeans);
        resultTable.setVisibleColumns(UIUtils.IFP_SEARCH_TABLE);
        resultTable.setColumnHeaders(UIUtils.IFP_COL_HEADERS);
        
        resultTable.setFilterBarVisible(true);
        resultTable.setFilterDecorator(new ExtDemoFilterDecorator());
//        
        resultTable.setValidationVisible(false);
        resultTable.addStyleName(ConstantsUtils.FILTER_BAR);
        resultTable.setFilterFieldVisible("totalDollarCommitment", false);
        resultTable.setFilterFieldVisible("commitmentPeriod", false);
        resultTable.setFilterFieldVisible("totalVolumeCommitment", false);
        resultTable.setFilterFieldVisible("totalMarketshareCommitment", false);
        resultTable.setFilterGenerator(new IFPFilterGenerator());

        resultTable.addItemClickListener(new ItemClickListener() {
            /**
             * Called when a item has been clicked.
             */ @SuppressWarnings("PMD")
            public void itemClick(final ItemClickEvent event) {
                try {
                    itemClicked = true;
                     selectBtn.setEnabled(true);
                    closeBtn.setEnabled(true);
                   } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1014), new MessageBoxListener() {  
                        /**            
                         * The method is triggered when a button of the message box is 
                         * pressed .          
                         *               
                         * @param buttonId The buttonId of the pressed button.     
                         */             
                        @SuppressWarnings("PMD")          
                        public void buttonClicked(final ButtonId buttonId) {      
                            // Do Nothing              
                        }         
                    }, ButtonId.OK);      
                    msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);

        }

            }
        });
    }

    /**
     * Configure all the fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    protected void configureFields() throws SystemException, Exception {
        addStyleName("bootstrap-company");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
         selectBtn.setEnabled(false);
         closeBtn.setEnabled(false);
        setResizable(false);
        itemFamilyplanId.focus();
        final CommonUtils commonUtils = new CommonUtils();
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE);
        
        
        

        itemFamilyplanName.setData("maxlengthvalidationhundred,maxlengthvalidationitemfamilyplanname,alphaNumericChars,alphaNumericCharsMessage");
        itemFamilyplanName.setImmediate(true);
        itemFamilyplanName.setValidationVisible(true);
        itemFamilyplanName.setDescription((String) itemFamilyplanName.getValue());
        itemFamilyplanName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */ @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanName.setDescription((String) itemFamilyplanName.getValue());
            }
        });

        itemFamilyplanNo.setImmediate(true);
        itemFamilyplanNo.setValidationVisible(true);
        itemFamilyplanNo.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanno,alphaNumericChars,alphaNumericCharsMessage");
        itemFamilyplanNo.setDescription((String) itemFamilyplanNo.getValue());
        itemFamilyplanNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanNo.setDescription((String) itemFamilyplanNo.getValue());
            }
        });
        
        
         itemFamilyplanId.setImmediate(true);
         itemFamilyplanId.setValidationVisible(true);
         itemFamilyplanId.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanid,alphaNumericChars,alphaNumericCharsMessage");
                
        itemFamilyplanId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
               itemFamilyplanId.setDescription((String) itemFamilyplanId.getValue());
            }
        });


		itemFamilyplanStatus.setImmediate(true);
                commonUtil.loadComboBox(itemFamilyplanStatus, UIUtils.STATUS, false);
                
                
		itemFamilyplanType.setImmediate(true);
                commonUtil.loadComboBox(itemFamilyplanType, "IFP_TYPE", false);

        space.setHeight("10px");


        search.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering ParentIFPIdLookup search operaion");
                List<Object> collapsedColumns = new ArrayList<Object>();     
                try {
                  
                    for (Object item : resultTable.getVisibleColumns()) {
                        if (resultTable.isColumnCollapsed(item)) {
                            collapsedColumns.add(item);
                        }
                    }
                    binder.commit();
                    if (binder.getField("itemFamilyplanId").getValue().toString().isEmpty() 
                            && binder.getField("itemFamilyplanNo").getValue().toString().isEmpty() 
                            && binder.getField("itemFamilyplanName").getValue().toString().isEmpty()
                            && (((com.stpl.ifs.util.HelperDTO)binder.getField("itemFamilyplanStatus").getValue()).getId() == 0)
                            && (((com.stpl.ifs.util.HelperDTO)binder.getField("itemFamilyplanType").getValue()).getId() == 0))  
                    {
                        MessageBox.showPlain(Icon.ERROR, "Search Error", "Please enter Search Criteria", ButtonId.OK);
                        return;
                    }
                    ifpCriteria.setCustomDirty(true);
                    searchResults = new LazyBeanItemContainer(ItemFamilyplanMasterDTO.class, new IFPParentLookupContainer(binder), ifpCriteria);

                    resultTable.setContainerDataSource(searchResults);
                    if (searchResults.size() > Constants.ZERO) {
                        CommonUIUtils.successNotification(ConstantsUtils.SEARCH_COMPLETED);
                    } else {
                        CommonUIUtils.successNotification(ConstantsUtils.NO_RESULTS_COMPLETED);
                    }

                    resultTable.setVisibleColumns(UIUtils.IFP_SEARCH_TABLE);
                    resultTable.setColumnHeaders(UIUtils.IFP_COL_HEADERS);
                } catch (FieldGroup.CommitException ex) {                
                    LOGGER.error(ex);
                } catch (Exception exception) {
                     final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {  
                        /**            
                         * The method is triggered when a button of the message box is 
                         * pressed .          
                         *               
                         * @param buttonId The buttonId of the pressed button.     
                         */             
                        @SuppressWarnings("PMD")          
                        public void buttonClicked(final ButtonId buttonId) {      
                            // Do Nothing              
                        }         
                    }, ButtonId.OK);      
                    msg.getButton(ButtonId.OK).focus();
                    LOGGER.error(exception);
                }
                ifpCriteria.setCustomDirty(false);
                for (Object propertyId : collapsedColumns) {
                    resultTable.setColumnCollapsed(propertyId, true);
                }
                ifpCriteria.setCustomDirty(true);
                LOGGER.info("Ending ParentIFPIdLookup search operaion");
            }
        });

        reset.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */
            public void buttonClick(final ClickEvent event) {
                MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Are you sure you want to reset the page to default/previous values " + " ?", new MessageBoxListener() {
                    /**
                     * Called when a Button has been clicked .
                     *
                     */
                    @SuppressWarnings("PMD")
                    public void buttonClicked(final ButtonId buttonId) {
                        if (buttonId.name().equals(ConstantsUtils.YES)) {
                            try {
                                List<Object> collapsedColumns = new ArrayList<Object>();
                                for (Object item : resultTable.getVisibleColumns()) {
                                    if (resultTable.isColumnCollapsed(item)) {
                                        collapsedColumns.add(item);
                                    }
                                }
                                LOGGER.info("Entering ParentIFPIdLookup reset operaion");
                                binder.setItemDataSource(new BeanItem<ItemFamilyplanMasterDTO>(new ItemFamilyplanMasterDTO()));
                                if (searchResults != null) {
                                    searchResults.removeAllItems();
                                }
                                binder.getErrorDisplay().clearError();
                                final BeanItemContainer<ItemFamilyplanMasterDTO> searchResultbeans = new BeanItemContainer<ItemFamilyplanMasterDTO>(ItemFamilyplanMasterDTO.class);
                                resultTable.setContainerDataSource(searchResultbeans);
                                resultTable.setVisibleColumns(UIUtils.IFP_SEARCH_TABLE);
                                resultTable.setColumnHeaders(UIUtils.IFP_COL_HEADERS);
                                binder.getErrorDisplay().clearError();
                                ifpCriteria.setCustomDirty(false);
                                for (Object propertyId : collapsedColumns) {
                                    resultTable.setColumnCollapsed(propertyId, true);
                                }
                                ifpCriteria.setCustomDirty(true);
                                LOGGER.info("Ending ParentIFPIdLookup reset operaion");
                            } catch (Exception exception) {
                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1006), new MessageBoxListener() {  
                                    /**            
                                     * The method is triggered when a button of the message box is 
                                     * pressed .          
                                     *               
                                     * @param buttonId The buttonId of the pressed button.     
                                     */             
                                    @SuppressWarnings("PMD")          
                                    public void buttonClicked(final ButtonId buttonId) {      
                                        // Do Nothing              
                                    }         
                                }, ButtonId.OK);      
                                msg.getButton(ButtonId.OK).focus();
                                LOGGER.error(exception);

                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

            }
        });

        }

    /**
     * Handler for selecting a row from the popup
     *
     * @param event
     */
    @UiHandler("selectBtn")
    public void selectBtn(Button.ClickEvent event) {
        if(itemClicked){
        final ItemFamilyplanSearchDTO ifpSearchDto = (ItemFamilyplanSearchDTO) resultTable.getValue();
        parentItemFamilyplanId.setValue(ifpSearchDto.getIfpId());
        parentItemFamilyplanName.setValue(ifpSearchDto.getIfpName());
        close();
        }else{
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, "Error", "Please select a row", new MessageBoxListener() {  
                /**            
                 * The method is triggered when a button of the message box is 
                 * pressed .          
                 *               
                 * @param buttonId The buttonId of the pressed button.     
                 */             
                @SuppressWarnings("PMD")          
                public void buttonClicked(final ButtonId buttonId) {      
                    // Do Nothing              
                }         
            }, ButtonId.OK);      
            msg.getButton(ButtonId.OK).focus();
        }
    }

    /**
     * Close button handler
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void closeBtn(Button.ClickEvent event) {
        close();
    }

}
