package com.stpl.app.global.priceschedule.ui.form;

import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.priceschedule.dto.IFPFilterGenerator;
import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.global.priceschedule.ui.lazyload.AvailableIFPContainer;
import com.stpl.app.global.priceschedule.ui.lazyload.AvailableIFPCriteria;
import com.stpl.app.global.priceschedule.util.PsUtils;
import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.TableResultCustom;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyBeanItemContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

public class PSItemAdditionTabForm extends CustomComponent implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(PSItemAdditionTabForm.class.getName());
	/**
	 * The ps master.
	 */
	final private PSDTO psMaster;
	/**
	 * The binder.
	 */
	final private ErrorfulFieldGroup binder;

	final private ErrorLabel errorMsg;

	@UiField("hlayout2")
	private HorizontalLayout hlayout2;

	@UiField("hlayout")
	private HorizontalLayout hlayout;
	@UiField("root")
	private VerticalLayout root;
	/**
	 * The available table.
	 */
	@UiField("availableTable")
	private ExtFilterTable availableTable;
	/**
	 * The selected table.
	 */
	@UiField("selectedTable")
	private ExtFilterTable selectedTable;

	/**
	 * The available item result bean.
	 */
	private BeanItemContainer<PSIFPDTO> availableItemResultBean;
	/**
	 * The available item result bean.
	 */
	private LazyBeanItemContainer<PSIFPDTO> availableItemResultLazyBean;
	/**
	 * The selected item result bean.
	 */
	private BeanItemContainer<PSIFPDTO> selectedItemResultBean;

	/**
	 * The search fields.
	 */
        @UiField("searchFieldsLB")
        Label searchFieldsLB;
        
        @UiField("searchValueLB")
        Label searchValueLB;
        
	@UiField("searchFields")
	private ComboBox searchFields;
	/**
	 * The search value.
	 */
	@UiField("searchValue")
	private TextField searchValue;
        
	@UiField("searchBtn")
	private Button searchBtn;
        
	@UiField("addBtn")
	private Button addBtn;
        
	@UiField("removeBtn")
	private Button removeBtn;
        
        @UiField("cssLayout")
	private CssLayout cssLayout;
        
        @UiField("searchType")
        private OptionGroup searchType; 
        
	final Map<String, String> searchMap = new ConcurrentHashMap<String, String>();
	private Object searchFieldObject;
	private Object searchValueObject;
	final private Map<String, AppPermission> fieldPsHM;
	final private BeanItemContainer<PSIFPDTO> itemDetailsResultBean;

	/**
	 * the used id
	 */
	final private String userId;
	/**
	 * the session id
	 */
	final private String sessionId;
	/**
	 * the temp table records created date
	 */
	final private String tempCreatedDate;

	/**
	 * The ps logic.
	 */
	final private PSLogic psLogic;
	final private Label selectedId;
	private PSPricingTabForm pricingTab;
        private PriceProtectionTabForm priceProtectionTabForm;
        
    private final IFPLogic ifpLogic = new IFPLogic();
        
    final CommonUtils commonUtils = new CommonUtils();
    
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    String mode;
    
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PSItemAdditionTabForm.class);
    
    final StplSecurity stplSecurity = new StplSecurity();
    
    

	   public PSItemAdditionTabForm(final PSDTO psMaster, final ErrorfulFieldGroup binder, final ErrorLabel errorMsg, final Map<String, AppPermission> fieldPsHM,
            final BeanItemContainer<PSIFPDTO> availableItemResultBean, BeanItemContainer<PSIFPDTO> itemDetailsResultBean, BeanItemContainer<PSIFPDTO> selectedItemResultBean, String userId,
            String sessionId, String tempCreatedDate, final PSLogic psLogic, final Label selectedId, final PSPricingTabForm pricingTab, final String mode) {
        this.psMaster = psMaster;
        this.binder = binder;
        this.errorMsg = errorMsg;
        this.fieldPsHM = fieldPsHM;
        this.availableItemResultBean = availableItemResultBean;
        this.itemDetailsResultBean = itemDetailsResultBean;
        this.selectedItemResultBean = selectedItemResultBean;
        this.userId = userId;
        this.sessionId = sessionId;
        this.tempCreatedDate = tempCreatedDate;
        this.psLogic = psLogic;
        this.selectedId = selectedId;
        this.pricingTab = pricingTab;
        this.mode = mode;

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/priceschedule/psitemadditiontabform.xml"), this));
        configurePermission(fieldPsHM);
        getBinder();
        configureFields();
        try {
            final Map<String, AppPermission> functionRsHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.PRICE_SCHEDULE + "," + "Item Addition");
            if (functionRsHM.get(FunctionNameUtil.SEARCH) != null && ((AppPermission) functionRsHM.get(FunctionNameUtil.SEARCH)).isFunctionFlag()) {
                configureSearchButton(fieldPsHM);
            } else {
                searchBtn.setVisible(false);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

        
        private void configurePermission(final Map<String, AppPermission> fieldPsHM) {
        logger.info("Entering configurePermission");
        try {
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.PRICE_SCHEDULE,"Item Addition");
        commonUiUtil.removeComponentOnPermission(resultList, cssLayout, fieldPsHM, mode,binder);
        }catch(Exception ex){
            logger.error(ex);
        }
        logger.info("Ending configurePermission");
    }

	/**
	 * Gets the biner.
	 *
	 * @return the bdinder
	 */
	private ErrorfulFieldGroup getBinder() {

		binder.bindMemberFields(this);
		binder.setItemDataSource(new BeanItem<PSDTO>(psMaster));
		binder.setBuffered(true);
		binder.setErrorDisplay(errorMsg);
		errorMsg.setId("ErrorMessage");
		return binder;
	}

	private void configureFields() {
		final PsUtils psUtils = new PsUtils();
		try {


			removeBtn.setCaption("<");
			addBtn.setCaption(">");
			addResponsiveButtons();

			getResponsiveTables();

			CommonUtils.getComboBox(searchFields);
			searchFields.setContainerDataSource(psUtils.searchFields());
			searchFields.select(ConstantsUtils.SELECT_ONE);
			searchFields.addValueChangeListener(new Property.ValueChangeListener() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				/**
				 * Method used to search field logic and its listener.
				 */
				public void valueChange(final ValueChangeEvent event) {
				}
			});
                        
                    searchType.setImmediate(true);
                    searchType.addItem("Item");
                    searchType.addItem("IFP");
                    searchType.setValue("IFP"); 
                    
                    searchType.addStyleName("horizontal");
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public void addResponsiveButtons() throws Exception {
		logger.debug("Entering addButtons");
		configreAddButton();
		configureRemoveButton();
                searchFieldsLB.setPrimaryStyleName("labelsize");
                searchValueLB.setPrimaryStyleName("labelsize");
		logger.debug("Ending addButtons");

	}

	/**
	 * Adds the.
	 *
	 * @return the button
	 */
	public void configreAddButton() {

		addBtn.setErrorHandler(new ErrorHandler() {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			/**
			 * Invoked when an error occurs.
			 *
			 * @param event
			 *            the fired event.
			 */
			public void error(final com.vaadin.server.ErrorEvent event) {
				// empty block
			}
		});
		addBtn.addClickListener(new ClickListener() {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			/**
			 * Method used to Called when a Button has been clicked.
			 */
			public void buttonClick(final ClickEvent event) {
				logger.debug("Entering PSTabsheetForm Move Right operation");
				final Object itemId = availableTable.getValue();
				binder.getErrorDisplay().clearError();
				if (availableTable.size() > 0) {
					if (itemId == null) {
						binder.getErrorDisplay().setError("Please select the IFP to Add");
					} else {
						if (selectedItemResultBean.size() < Constants.ONE) {
							final PSIFPDTO item = (PSIFPDTO) itemId;
							selectedItemResultBean.addItem(itemId);
							selectedId.setValue(String.valueOf(item.getItemFamilyplanSystemId()));
							if (item.getItemFamilyplanSystemId() != null && !StringUtils.isEmpty(item.getItemFamilyplanSystemId())) {
                                                          ImtdPsDetailsLocalServiceUtil.insertTempPsDetailsInADD(userId, sessionId, tempCreatedDate, item.getItemFamilyplanSystemId(), "", "", psMaster.getPriceScheduleSystemId(), null);
							}
						}
					}
				} else {
					binder.getErrorDisplay().setError("There are no items to move");
				}
				logger.debug("Ending PSTabsheetForm Move Right operation");
			}
		});

	}

	/**
	 * Removes the.
	 *
	 * @return the button
	 * @throws Exception
	 *             the exception
	 */
	public void configureRemoveButton() throws Exception {

		removeBtn.setErrorHandler(new ErrorHandler() {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			/**
			 * Invoked when an error occurs.
			 *
			 * @param event
			 *            the fired event.
			 */
			public void error(final com.vaadin.server.ErrorEvent event) {
				// empty block
			}
		});
		removeBtn.addClickListener(new ClickListener() {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			/**
			 * Button click listener for remove button
			 */
			public void buttonClick(final ClickEvent event) {
				logger.debug("Entering PSTabsheetForm Move Left operation");
				final Object itemId = selectedTable.getValue();
				binder.getErrorDisplay().clearError();
				if (selectedItemResultBean.size() > 0) {
					if (selectedTable.getContainerDataSource().getItemIds().isEmpty() || itemId == null) {
						binder.getErrorDisplay().setError("Please select the IFP to Remove");
					} else {
						try {
							if (itemDetailsResultBean.size() > 0) {
								MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Removing this IFP " + ((PSIFPDTO) itemId).getItemNo()
										+ " will clear the information entered in Pricing tab. Do you want to proceed ?", new MessageBoxListener() {
									/**
									 * Button Clicked event for Conformation
									 * Message Box
									 */
									@SuppressWarnings("PMD")
									public void buttonClicked(final ButtonId buttonId) {
										try {
											if (buttonId.name().equals("YES")) {
												selectedItemResultBean.removeItem(itemId);
												itemDetailsResultBean.removeAllItems();
												psLogic.removeAllFromTempTable(true);
												selectedId.setValue(StringUtils.EMPTY);
											}
										} catch (SystemException sysException) {
											final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
											logger.error(errorMsg);
                                                                                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {    
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
										} catch (PortalException portException) {
											logger.error(portException);
                                                                                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {    
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
										} catch (Exception exception) {
                                                                                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1012), new MessageBoxListener() {    
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
											logger.error(exception);

										}
									}
								}, ButtonId.YES, ButtonId.NO);
							} else {
								selectedItemResultBean.removeItem(itemId);
								psLogic.removeAllFromTempTable(true);
								selectedId.setValue(StringUtils.EMPTY);
							}
							// refreshing the #3rd tab tables
							pricingTab.clearTable();
                                                        priceProtectionTabForm.clearTable();
						} catch (SystemException ex) {
							java.util.logging.Logger.getLogger(PSTabsheetForm.class.getName()).log(Level.SEVERE, null, ex);
						} catch (PortalException ex) {
							java.util.logging.Logger.getLogger(PSTabsheetForm.class.getName()).log(Level.SEVERE, null, ex);
						} catch (Exception ex) {
                                                java.util.logging.Logger.getLogger(PSItemAdditionTabForm.class.getName()).log(Level.SEVERE, null, ex);
                                                }
					}

					logger.debug("Ending PSTabsheetForm Move Left operation");
				} else {
					binder.getErrorDisplay().setError("There is no items to remove");
				}
			}
		});

	}

	private void getResponsiveTables() throws Exception {
		logger.debug("Entering getTables");
		addAvailableTable();
		addSelectedTable();
		hlayout2.addStyleName("dataTransferGridForPS");
		hlayout2.addStyleName("responsiveTabGrid");
            

		logger.debug("Ending getTables");

	}

	public ExtFilterTable addAvailableTable() throws Exception {
            
            
            
            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> fieldPsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.PRICE_SCHEDULE,false);
            
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.PRICE_SCHEDULE, "Item Addition");
            Object[] obj = PsUtils.AVAILABLE_IFP_COL;
            TableResultCustom tableResultCustom = UISecurityUtil.getTableColumnsPermission(resultList, obj, fieldPsHM, mode);
           if(tableResultCustom.getObjResult().length == 0){
               availableTable.setVisible(false);
           }

		availableTable.markAsDirty();
		availableTable.addStyleName(ConstantsUtils.FILTER_BAR);
		availableItemResultBean = new BeanItemContainer<PSIFPDTO>(PSIFPDTO.class);
		availableTable.setContainerDataSource(availableItemResultBean);
                availableTable.setVisibleColumns(tableResultCustom.getObjResult());
                availableTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
		
		availableTable.setFilterBarVisible(true);
		availableTable.setPageLength(8);
		availableTable.setImmediate(true);
		availableTable.setSelectable(true);
		availableTable.setSizeFull();
		availableTable.setFilterGenerator(new IFPFilterGenerator());
		availableTable.setFilterDecorator(new ExtDemoFilterDecorator());
		availableTable.setWidth("380px");
		availableTable.setErrorHandler(new ErrorHandler() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * Invoked when an error occurs.
			 *
			 * @param event
			 *            the fired event.
			 */
			@SuppressWarnings("PMD")
			public void error(final com.vaadin.server.ErrorEvent event) {
				// Method used to Invoked when an error occurs.
			}
		});

		return availableTable;
	}

	/**
	 * Adds the selected table.
	 *
	 * @return the table
	 * @throws Exception
	 *             the exception
	 */
	public ExtFilterTable addSelectedTable() throws Exception {
            
            final StplSecurity stplSecurity = new StplSecurity();
            final Map<String, AppPermission> fieldPsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.PRICE_SCHEDULE,false);
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.PRICE_SCHEDULE, "Item Addition");
            Object[] obj = PsUtils.AVAILABLE_IFP_COL;
            TableResultCustom tableResultCustom = UISecurityUtil.getTableColumnsPermission(resultList, obj, fieldPsHM, mode);
            if(tableResultCustom.getObjResult().length == 0){
               selectedTable.setVisible(false);
           }
		selectedTable.markAsDirty();
		selectedTable.addStyleName(ConstantsUtils.FILTER_BAR);
		selectedTable.setFilterBarVisible(true);
		selectedTable.setFilterDecorator(new ExtDemoFilterDecorator());

		selectedTable.setContainerDataSource(selectedItemResultBean);  
                selectedTable.setVisibleColumns(tableResultCustom.getObjResult());
                selectedTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
                
		selectedTable.setPageLength(8);
		selectedTable.setImmediate(true);
		selectedTable.setSelectable(true);
		selectedTable.setSizeFull();
		selectedTable.setWidth("380px");
		selectedTable.setErrorHandler(new ErrorHandler() {
			/**
			 * Invoked when an error occurs .
			 *
			 * @param event
			 *            the fired event .
			 */
			@SuppressWarnings("PMD")
			public void error(final com.vaadin.server.ErrorEvent event) {
				// empty block
			}
		});

		return selectedTable;
	}

	public void configureSearchButton(final Map<String, AppPermission> fieldPsHM) throws Exception {

		searchBtn.setErrorHandler(new ErrorHandler() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * Invoked when an error occurs .
			 *
			 * @param event
			 *            the fired event .
			 */
			@SuppressWarnings("PMD")
			public void error(final com.vaadin.server.ErrorEvent event) {
				// empty block
			}
		});
		searchBtn.addClickListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * Button Click listener for Find
			 */
			public void buttonClick(final ClickEvent event) {
				logger.debug("Entering PSTabsheetForm Search operation");
				loadData();
				loadMap();
				if (searchFields.getValue() == null || StringUtils.isBlank(searchValue.getValue())) {

					if (searchFields.getValue() == null) {
						binder.getErrorDisplay().setError("Please Select a Search Field to Search");
					} else {
						binder.getErrorDisplay().setError("Please enter the Value to search");

					}

				} else if (searchFieldObject != null && searchValueObject != null) {
					binder.getErrorDisplay().clearError();
					if (String.valueOf(searchValueObject).length() > 39) {
						binder.getErrorDisplay().setError("Value should be less than 38 characters");
						return;
					}
					loadBean();
					if (availableItemResultLazyBean.size() > Constants.ZERO) {
						CommonUIUtils.successNotification("Search Completed");
					} else {
						CommonUIUtils.successNotification("No results found");
					}
				}
				logger.debug("Ending PSTabsheetForm Search operation");
			}
		});

	}

	private void loadData() {
		searchFieldObject = searchFields.getValue();
		searchValueObject = searchValue.getValue();
	}

	private void loadMap() {
		searchMap.put("IFP No", "ifpNo");
		searchMap.put("IFP Name", "ifpName");

	}

	private void loadBean() {
		availableItemResultLazyBean = new LazyBeanItemContainer<PSIFPDTO>(PSIFPDTO.class, new AvailableIFPContainer(searchMap.get(searchFieldObject.toString().trim()), searchValueObject.toString(),
				null), new AvailableIFPCriteria());
		availableTable.setContainerDataSource(availableItemResultLazyBean);
		availableTable.setVisibleColumns(PsUtils.AVAILABLE_IFP_COL);
		availableTable.setColumnHeaders(PsUtils.AVAILABLE_IFP_COL_HEADER);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public ExtFilterTable getAvailableTable() {
		return availableTable;
	}

	public BeanItemContainer<PSIFPDTO> getAvailableItemResultBean() {
		return availableItemResultBean;
	}

	public LazyBeanItemContainer<PSIFPDTO> getAvailableItemResultLazyBean() {
		return availableItemResultLazyBean;
	}

	public ComboBox getSearchFields() {
		return searchFields;
	}

	public void focusSearchField() {
		searchFields.focus();
	}

	public TextField getSearchValue() {
		return searchValue;
	}

	public void removeAndDisablingComponents() {
		root.removeComponent(hlayout);
		availableTable.setSelectable(false);
		selectedTable.setSelectable(false);
		addBtn.setEnabled(false);
		removeBtn.setEnabled(false);
	}

    /**
     *
     * @param pricingTab
     * @param priceProtectionTabForm
     */
    public void setPricingAndProtectionTab(PSPricingTabForm pricingTab, PriceProtectionTabForm priceProtectionTabForm) {
        this.pricingTab = pricingTab;
        this.priceProtectionTabForm = priceProtectionTabForm;
    }

    }
