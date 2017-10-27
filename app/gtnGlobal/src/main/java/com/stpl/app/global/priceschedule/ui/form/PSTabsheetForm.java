package com.stpl.app.global.priceschedule.ui.form;

import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.ui.InformationLayout;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.ui.lazyload.TempItemCriteria;
import com.stpl.app.model.PsModel;
import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.global.priceschedule.ui.view.PSView;
import com.stpl.app.global.priceschedule.util.FieldNameUtils;
import com.stpl.app.global.priceschedule.util.LabelUtils;
import com.stpl.app.global.priceschedule.util.PsUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.NotesTabForm;
import com.stpl.app.ui.StplCustomComponent;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.domain.global.base.AddBaseForm;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

// TODO: Auto-generated Javadoc
/**
 * The Class PSTabsheetForm.
 */
public final class PSTabsheetForm extends StplCustomComponent implements AddBaseForm {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The Constant NAME.
	 */
	public static final String NAME = "add";
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(PSTabsheetForm.class.getName());
	/**
	 * The error message.
	 */
	@UiField("errorMsg")
	private ErrorLabel errorMsg;
	/**
	 * The ps logic.
	 */
	final private PSLogic psLogic;
	/**
	 * The space.
	 */
	final private Label space = new Label("&nbsp;", ContentMode.HTML);
	/**
	 * The available item result bean.
	 */
	private final BeanItemContainer<PSIFPDTO> availableItemResultBean;
	/**
	 * The selected item result bean.
	 */
	private final BeanItemContainer<PSIFPDTO> selectedItemResultBean;
	/**
	 * The item details results bean.
	 */
	private final BeanItemContainer<PSIFPDTO> resultBeans;
	TempItemCriteria tempItemCriteria = new TempItemCriteria();
	// TABBAR 1
	/**
	 * The table.
	 */
	private Table table;
	/**
	 * The available table.
	 */
	private ExtFilterTable availableTable;
	/**
	 * The selected table.
	 */
	private ExtFilterTable selectedTable;
	/**
	 * The item details table.
	 */
	private ExtPagedTable<?> itemDetailsTable;
        
	/**
	 * The selected id.
	 */
	final private Label selectedId = new Label();

	/**
	 * The map.
	 */
	private final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

	/**
	 * The ps master.
	 */
	private PSDTO psMaster;
	/**
	 * The binder.
	 */
	private final ErrorfulFieldGroup binder;

	/**
	 * The dateFormat.
	 */
	private static DecimalFormat dateFormat = new DecimalFormat("#.##");
	/**
	 * The priceScheduleSystemId.
	 */
	private BeanItemContainer<PSIFPDTO> itemDetailsResultBean = new BeanItemContainer<>(PSIFPDTO.class);


	/**
	 * the used id
	 */
	private String userId;
	/**
	 * the session id
	 */
	private String sessionId;
	/**
	 * the temp table records created date
	 */
	private String tempCreatedDate;
        CommonUtil commonutil=CommonUtil.getInstance();
        
	/**
	 * the
	 */
	private final String mode;

	private final boolean isAddMode ;
	private final boolean isEditMode ;

	/**
	 * The tabsheet.
	 */
	@UiField("tabSheet")
	TabSheet tabsheet;

	NotesTabForm notesTab;

	PSInfoTabForm infoTab;
	PSItemAdditionTabForm itemAdditionTab;
	PSPricingTabForm pricingTab;
        PriceProtectionTabForm priceProtectionTabForm;
	@UiField("buttonLayout")
	private HorizontalLayout buttonLayout;
	@UiField("saveBtn")
	private Button saveBtn;
	@UiField("backBtn")
	private Button backBtn;
	@UiField("resetBtn")
	private Button resetBtn;
	@UiField("deleteBtn")
	private Button deleteBtn;
        @UiField("informationLayout")
        private VerticalLayout informationLayout;

	private String success = StringUtils.EMPTY;
	private int selectedTabIndex = 0;
        SessionDTO sessionDTO;
        private final BeanItemContainer<PSIFPDTO> emptyAvailableContainer = new BeanItemContainer<>(PSIFPDTO.class);

        private BeanItemContainer<PSIFPDTO> itemDetailsContainer = new BeanItemContainer<>(PSIFPDTO.class);

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public static String getName() {
		return NAME;
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the errorMsg
	 */
	public ErrorLabel getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Gets the ps logic.
	 *
	 * @return the psLogic
	 */
	public PSLogic getPsLogic() {
		return psLogic;
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
	 * Gets the available item result bean.
	 *
	 * @return the availableItemResultBean
	 */
	public BeanItemContainer<PSIFPDTO> getAvailableItemResultBean() {
		return availableItemResultBean;
	}

	/**
	 * Gets the selected item result bean.
	 *
	 * @return the selectedItemResultBean
	 */
	public BeanItemContainer<PSIFPDTO> getSelectedItemResultBean() {
		return selectedItemResultBean;
	}

	/**
	 * Gets the result beans.
	 *
	 * @return the resultBeans
	 */
	public BeanItemContainer<PSIFPDTO> getResultBeans() {
		return resultBeans;
	}

	/**
	 * Gets the available table.
	 *
	 * @return the availableTable
	 */
	public ExtFilterTable getAvailableTable() {
		return availableTable;
	}

	/**
	 * Gets the selected table.
	 *
	 * @return the selectedTable
	 */
	public ExtFilterTable getSelectedTable() {
		return selectedTable;
	}

	/**
	 * Gets the item details table.
	 *
	 * @return the itemDetailsTable
	 */
	public ExtPagedTable getItemDetailsTable() {
		return itemDetailsTable;
	}

	/**
	 * Gets the selected id.
	 *
	 * @return the selectedId
	 */
	public Label getSelectedId() {
		return selectedId;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map<String, String> getMap() {
		return map;
	}

	/**
	 * Gets the ps master.
	 *
	 * @return the psMaster
	 */
	public PSDTO getPSDTO() {
		return psMaster;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * The Constructor.
	 *
	 * @param psMaster
	 *            the ps master
	 * @param binder
	 *            the binder
	 * @param availableItemResultBean
	 *            the available item result bean
	 * @param selectedBeans
	 *            the selected item result bean
	 * @param itemDetailsResultsBean
	 *            the item details results bean
	 * @throws PortalException
	 *             the portal exception
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */
	public PSTabsheetForm(final PSDTO psMaster, final ErrorfulFieldGroup binder, final BeanItemContainer<PSIFPDTO> availableItemResultBean, final BeanItemContainer<PSIFPDTO> selectedBeans,
			final BeanItemContainer<PSIFPDTO> itemDetailsResultsBean,final String mode,final SessionDTO sessionDTO) throws PortalException, SystemException {
		super();
		this.availableItemResultBean = availableItemResultBean;
		this.selectedItemResultBean = selectedBeans;
		this.resultBeans = itemDetailsResultsBean;
		this.psMaster = psMaster;
		this.binder = binder;
                this.sessionDTO=sessionDTO;
                psLogic = new PSLogic(this.sessionDTO);
		this.mode=mode;
		this.isAddMode = ConstantsUtils.ADD.equals(mode);
		this.isEditMode = ConstantsUtils.EDIT.equals(mode)|| ConstantsUtils.COPY.equals(mode);
		setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/common/tabsheetform.xml"), this));
		init();

	}

	/**
	 * inits the content.
	 *
	 * @throws PortalException
	 *             the portal exception
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */
	public void init() throws PortalException, SystemException {
		space.setHeight(String.valueOf(NumericConstants.TWENTY));
		addToContent();
		configureFields();
	}

	/**
	 * Adds the to content.
	 *
	 * @throws SystemException
	 *             the system exception-
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public void addToContent() throws SystemException, PortalException {
            if (ConstantsUtils.COPY.equals(mode)) {
            configInfoLayoutCopy();
        }
        informationLayout.addComponent(new InformationLayout("price_Schedule", psMaster.getPriceScheduleId(), psMaster.getPriceScheduleNo(), psMaster.getPriceScheduleName()));
        configureTabSheet();
    }

	/**
	 * Gets the binder.
	 *
	 * @return the binder
	 */

	/**
	 * Adds the tab sheet.
	 *
	 * @return the tab sheet
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	public void configureTabSheet() throws SystemException, PortalException {

		tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		tabsheet.setReadOnly(true);
		tabsheet.setId("responsive-tab");
		final StplSecurity stplSecurity = new StplSecurity();
		userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
		sessionId = String.valueOf(sessionDTO.getUiSessionId());
		tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());		
                final Map<String, AppPermission> fieldPsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.PRICE_SCHEDULE,false);
                LOGGER.debug("fieldPsHM price schedule"+fieldPsHM);
			LOGGER.debug("Adding Price Schedule Information Tab ");
			infoTab = new PSInfoTabForm(binder, psLogic, errorMsg, psMaster, fieldPsHM, mode);
			tabsheet.addTab(infoTab, TabNameUtil.PRICE_SCHEDULE_INFO, null, 0);
                        if (ConstantsUtils.COPY.equals(mode)) {
                psMaster.setPriceScheduleId(StringUtils.EMPTY);
                psMaster.setPriceScheduleName(StringUtils.EMPTY);
                psMaster.setPriceScheduleNo(StringUtils.EMPTY);
                psMaster.setModifiedBy(StringUtils.EMPTY);
                deleteBtn.setVisible(false);
            }
			LOGGER.debug("Adding Price Schedule Item Addition Tab ");
			itemAdditionTab = new PSItemAdditionTabForm(psMaster, binder, errorMsg, fieldPsHM, availableItemResultBean, itemDetailsResultBean, selectedItemResultBean, userId, sessionId,
					tempCreatedDate, psLogic, selectedId, pricingTab, mode,sessionDTO.getSystemId());
			tabsheet.addTab(itemAdditionTab, TabNameUtil.ITEM_ADDITION, null, 1);
			availableTable = itemAdditionTab.getAvailableTable();
			selectedTable = itemAdditionTab.addSelectedTable();
			
                        LOGGER.debug("Adding Price Schedule Pricing Tab ");
			pricingTab = new PSPricingTabForm(errorMsg, binder, psMaster, itemDetailsResultBean, userId, sessionId, tempCreatedDate, psLogic, resultBeans,mode, itemDetailsContainer, sessionDTO);
			tabsheet.addTab(pricingTab, TabNameUtil.PS_PRICING, null, NumericConstants.TWO);
			itemDetailsTable = pricingTab.getItemDetailsTable();
                LOGGER.debug("Adding Price Schedule price Protection Tab ");        
                priceProtectionTabForm=new PriceProtectionTabForm(binder, itemDetailsResultBean, mode, userId, sessionId, tempCreatedDate, psMaster, errorMsg, psLogic, itemDetailsContainer, psMaster, sessionDTO);
                tabsheet.addTab(priceProtectionTabForm, TabNameUtil.PRICE_PROTECTION, null, NumericConstants.THREE);
		itemAdditionTab.setPricingAndProtectionTab(pricingTab,priceProtectionTabForm);
                LOGGER.debug("Adding Price Schedule Notes Tab ");
                    notesTab = new NotesTabForm(binder, "Price Schedule", "PS_MODEL","priceScheduleSystemId",mode);
                    tabsheet.addTab(notesTab, TabNameUtil.NOTES, null, NumericConstants.FOUR);

		tabsheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
			/**
			 * Listener for Tab Change Event
			 */
			public void selectedTabChange(final SelectedTabChangeEvent event) {
				pricingTab.commitItemDetailsTable();
                                priceProtectionTabForm.commitPriceProtectionTable();
				binder.getErrorDisplay().clearError();
				final String tabname = event.getTabSheet().getSelectedTab().getCaption();
				try {
					Component component = event.getTabSheet().getSelectedTab();
					selectedTabIndex = event.getTabSheet().getTabPosition(event.getTabSheet().getTab(component));
					LOGGER.debug("Selected Tab Name :" + tabname);
					LOGGER.debug("Selected Tab Index :" + selectedTabIndex);
                                        switch(selectedTabIndex){
                                            case 0:
                                                infoTab.focusPriceScheduleId();
                                                break;
                                                
                                            case 1:
                                                itemAdditionTab.focusSearchField(); 
                                                break;
                                                
                                            case NumericConstants.TWO:   
                                                pricingTab.setRecordValue(psMaster.getRecord());
                                                pricingTab.enableOrDisableMassCheck(ConstantsUtils.DISABLE);
						if (selectedItemResultBean.size() > 0) {
                                                    if (itemDetailsResultBean.size() > 0) {
                                                        psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
                                                        itemDetailsResultBean.removeAllItems();
                                                    }
							if (isAddMode) {
								final String value = selectedId.getValue();
								if (!StringUtils.EMPTY.equals(value)) {
									pricingTab.refreshTable();
								}
							} else if (isEditMode) {
								pricingTab.refreshTable();
							}
						}
                                            break;
                                            case NumericConstants.THREE:
                                                priceProtectionTabForm.setRecordValue(psMaster.getRecord());
                                                priceProtectionTabForm.enableOrDisableMassCheck(ConstantsUtils.DISABLE);
                                                if (selectedItemResultBean.size() > 0) {
                                                    if (itemDetailsResultBean.size() > 0) {
                                                        psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
                                                        itemDetailsResultBean.removeAllItems();
                                                    }
							if (isAddMode) {
								final String value = selectedId.getValue();
								if (!StringUtils.EMPTY.equals(value)) {
									priceProtectionTabForm.refreshTable();
								}
							} else if (isEditMode) {
								priceProtectionTabForm.refreshTable();
							}
						}
                                            break;
                                            case NumericConstants.FOUR:    
                                                notesTab.callJavaScriptForButton();
						notesTab.focusNewNote();
						if (isEditMode) {
							notesTab.documentExporter();
						}
                                            break;
                                        }
                                        
				} catch (Exception exception) {
					final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {      
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
	 * Configure fields.
	 *
	 * @throws SystemException
	 *             the system exception
	 * @throws Exception
	 *             the exception
	 */
	public void configureFields() throws SystemException, PortalException {
		backButton();
                
                
                final StplSecurity stplSecurity = new StplSecurity();
		final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
		final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.PRICE_SCHEDULE);
		
            if (functionPsHM.get(FunctionNameUtil.SAVE) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.SAVE)).isFunctionFlag()) {
                saveButton();
            } else {
                saveBtn.setVisible(false);
            }
            if (functionPsHM.get(FunctionNameUtil.RESET) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
                resetButton();
            } else {
                resetBtn.setVisible(false);
            }

		map.put("Price Protection Start Date", "priceProtectionStartDate");
		map.put("Price Protection End Date", "priceProtectionEndDate");
		map.put("Base Price", "basePrice");
		map.put("Contract Price", "contractPrice");
		map.put("CP Start Date", "contractPriceStartDate");
		map.put("CP End Date", "contractPriceEndDate");
		map.put("Price Type", "priceType");
		map.put("Price Tolerance", "priceTolerance");
		map.put("Price Tolerance Type", "priceToleranceType");
		map.put("Price Tolerance Interval", "priceToleranceInterval");
		map.put("Price Tolerance Frequency", "priceToleranceFrequency");
		map.put(ConstantsUtils.PRIC, "price");

                if (functionPsHM.get(FunctionNameUtil.EDIT) != null && ((AppPermission) functionPsHM.get(FunctionNameUtil.EDIT)).isFunctionFlag()) {
			deleteButton();
		}
		
	}
        
        
        public void backButton(){
            backBtn.addClickListener(new ClickListener() {
                /**
                 * Button Click event for Back Button
                 */
                @SuppressWarnings("PMD")
                public void buttonClick(final ClickEvent event) {
                    if (mode.equals(ConstantsUtils.VIEW)) {
                         AbstractSearchView.flag=false;
                        getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                    } else {
                        LOGGER.debug("Entering Price Schedule back button operation from Add");

                        try {
                            psLogic.removeAllFromTempTable();
                            MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(),commonutil.getBackMessage(), new MessageBoxListener() {
                                /**
                                 * Called when a Button has been clicked .
                                 *
                                 */
                                public void buttonClicked(final ButtonId buttonId) {
                                    if (buttonId.name().equals(ConstantsUtils.YES)) {
                                        LOGGER.debug("NavigateTo PriceScheduleSearchView page");
                                          AbstractSearchView.flag=false;
                                        getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                                    }
                                }
                            }, ButtonId.YES, ButtonId.NO);

                        } catch (Exception exception) {
                            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1009), new MessageBoxListener() {    
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
                        LOGGER.debug("Ending Price Schedule back button operation from Add");
                    }
                }
            });
        }
        
        public void saveButton(){
            saveBtn.addClickListener(new ClickListener() {
			/**
			 * Button Click for Add Button
			 */
			@SuppressWarnings("PMD")
			public void buttonClick(final ClickEvent event) {
				LOGGER.debug("Entering Price Schedule Save button operation from Add");
				try {
					if (isAddMode || ConstantsUtils.COPY.equals(mode)) {
						saveLogicForAddMode();
					} else if (isEditMode && !(ConstantsUtils.COPY.equals(mode)) ) {
						updateLogicForEditMode();
					}

					LOGGER.debug("Ending Price Schedule back button operation from Add");
				} catch (FieldGroup.CommitException comException) {
					LOGGER.error(comException);
				} catch (SystemException sysException) {
					LOGGER.error(sysException);
					final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
					LOGGER.error(errorMsg);
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
					LOGGER.error(portException);
                                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {    
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
					LOGGER.error(exception);
                                        final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {    
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
        
        public void resetButton(){
            resetBtn.addClickListener(new ClickListener() {
			@SuppressWarnings("PMD")
			/**
			 * Constant SerialID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * Logic for back button.
			 *
			 * @param event
			 */
			@SuppressWarnings("PMD")
			public void buttonClick(final ClickEvent event) {
				MessageBox.showPlain(Icon.QUESTION,commonutil.getHeaderMessage(),commonutil.getResetMessage(), new MessageBoxListener() {
					/**
					 * Called when a Button has been clicked .
					 *
					 */
					@SuppressWarnings("PMD")
					public void buttonClicked(final ButtonId buttonId) {
						if (buttonId.name().equals(ConstantsUtils.YES)) {
							try {
								LOGGER.debug("Entering inside  reset  method from ADD ");
								LOGGER.debug("tabsheet caption " + selectedTabIndex);
								if (isAddMode) {
									resetLogicForAddMode();
								} else if (isEditMode) {
									resetLogicForEditMode();
								}

							} catch (Exception e) {
								LOGGER.error(e);
							}
						}
					}
				}, ButtonId.YES, ButtonId.NO);

			}

		});
        }
        
        public void deleteButton(){
            deleteBtn.setErrorHandler(new ErrorHandler() {
			/**
			 * Method used to Invoked when an error occurs.
			 */
			@SuppressWarnings("PMD")
			public void error(final com.vaadin.server.ErrorEvent event) {
				// empty block
			}
		});
		deleteBtn.addClickListener(new ClickListener() {
			/**
			 * Button click event for Delete Button
			 */
			public void buttonClick(final ClickEvent event) {
				LOGGER.debug("Entering Price Schedule delete operation from Edit");
				binder.getFields();
			                         final int systemId = Integer.valueOf(binder.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID).getValue().toString().replace(ConstantsUtils.COMMA, StringUtils.EMPTY));

                            List psContractList = psLogic.getPsContractList(systemId);

                            if (psContractList != null && !psContractList.isEmpty() && psContractList.size() > 0) {
                                final MessageBox msg = MessageBox.showPlain(Icon.WARN, ValidationUtils.CANNOT_DELETE, "Price Schedule cannot be deleted as it is associated with Contract", new MessageBoxListener() {

                                    @Override
                                    public void buttonClicked(final ButtonId buttonId) {
                                        // Do Nothing
                                    }
                                }, ButtonId.OK);
                                msg.getButton(ButtonId.OK).focus();

                            }else{
                                
                                MessageBox.showPlain(Icon.QUESTION,commonutil.getHeaderMessage(),commonutil.getDeleteMessage(binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME).getValue().toString()) ,
						new MessageBoxListener() {
							/**
							 * Button Clicked event for Conformation Message Box
							 */
							@SuppressWarnings("PMD")
							public void buttonClicked(final ButtonId buttonId) {
								try {
									if (buttonId.name().equals(ConstantsUtils.YES)) {
										final int systemId = Integer.valueOf(binder.getField(ConstantsUtils.PRICE_SCHEDULE_SYS_ID).getValue().toString().replace(ConstantsUtils.COMMA, StringUtils.EMPTY));
										final PsModel master = psLogic.deletPSById(systemId);
										psLogic.softDeleteAllFromTempTable();
										psLogic.deleteNotesTabAttachment(systemId);
										final Notification notif = new Notification(commonutil.getDeletedSuccessfulMessage(master.getPsId(),master.getPsName()),Notification.Type.HUMANIZED_MESSAGE);
										notif.setPosition(Position.MIDDLE_CENTER);
										notif.setStyleName(ConstantsUtils.MY_STYLE);
										notif.show(Page.getCurrent());
                                                                                  AbstractSearchView.flag=true;
										getUI().getNavigator().navigateTo(AbstractSearchView.NAME);
                        }
								} catch (SystemException sysException) {
									final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
									LOGGER.error(errorMsg);
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
                                                                        LOGGER.error(sysException);
								} catch (PortalException portException) {
									LOGGER.error(portException);
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
                                                                        LOGGER.error(exception);
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
									LOGGER.error(exception);

								}
							}
						}, ButtonId.YES, ButtonId.NO);
                            }
				LOGGER.debug("Ending Price Schedule delete operation from Edit");
			}
		});
        }

	
	@Override
	public void addLogic() {
            return;
	}

	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Sets the table.
	 *
	 * @param table
	 *            the new table
	 */
	public void setTable(final Table table) {
		this.table = table;
	}

	/**
	 * Gets the date format.
	 *
	 * @return the date format
	 */
	public static DecimalFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * Sets the date format.
	 *
	 * @param dateFormat
	 *            the new date format
	 */
	public static void setDateFormat(final DecimalFormat dateFormat) {
		PSTabsheetForm.dateFormat = dateFormat;
	}


	public PSInfoTabForm getInfoTab() {
		return infoTab;
	}

	public PSItemAdditionTabForm getItemAdditionTab() {
		return itemAdditionTab;
	}

	public PSPricingTabForm getPricingTab() {
		return pricingTab;
	}

	public String getMode() {
		return mode;
	}


		
	

	public void addModeConfig() {
		buttonLayout.removeComponent(deleteBtn);
		saveBtn.setCaption(ConstantsUtils.SAVE);
                
	}

	public void editModeConfig() {
		deleteBtn.setVisible(true);
		saveBtn.setCaption(ConstantsUtils.UPDATE);
		notesTab.refreshTable();
	}
        public void copyModeConfig() {
        deleteBtn.setVisible(false);
        saveBtn.setCaption(ConstantsUtils.SAVE);
        notesTab.refreshTable();
    }
	public void viewModeConfig() {
		buttonLayout.removeComponent(deleteBtn);
		buttonLayout.removeComponent(saveBtn);
		buttonLayout.removeComponent(resetBtn);
		itemAdditionTab.removeAndDisablingComponents();
		pricingTab.removeAndDisablingComponents();
                priceProtectionTabForm.removeAndDisablingComponents();
		notesTab.refreshTable();
		notesTab.removeAndDisablingComponents();
	}

	private void resetLogicForAddMode() {
		if (selectedTabIndex == 0) { // Information tab
			TextField id = (TextField) binder.getField(FieldNameUtils.PRICE_SCHEDULE_ID);
			id.setValue(StringUtils.EMPTY);
			TextField no = (TextField) binder.getField(FieldNameUtils.PRICE_SCHEDULE_NO);
			no.setValue(StringUtils.EMPTY);
			TextField name = (TextField) binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME);
			name.setValue(StringUtils.EMPTY);
			ComboBox status = (ComboBox) binder.getField(FieldNameUtils.PS_STATUS);
			status.setValue(null);
			PopupDateField startDate = (PopupDateField) binder.getField(FieldNameUtils.PS_START_DATE);
			startDate.setValue(null);
			PopupDateField endDate = (PopupDateField) binder.getField(FieldNameUtils.PS_END_DATE);
			endDate.setValue(null);
			ComboBox type = (ComboBox) binder.getField(FieldNameUtils.PRICE_SCHEDULE_TYPE);
			type.setValue(null);
			ComboBox category = (ComboBox) binder.getField(FieldNameUtils.PRICE_SCHEDULE_CAT);
			category.setValue(0);
			ComboBox designation = (ComboBox) binder.getField(FieldNameUtils.PS_DESIGNATION);
			designation.setValue(null);
			ComboBox tradeClass = (ComboBox) binder.getField(FieldNameUtils.TRADE_CLASS);
			tradeClass.setValue(0);
			TextField parentCompany = (TextField) binder.getField(FieldNameUtils.PARENT_PS_NAME);
			parentCompany.setValue(null);
                        TextField parentId = (TextField) binder.getField(FieldNameUtils.PARENT_PS_ID);
                        parentId.setValue(null);  
		}
		if (selectedTabIndex == 1) { try {
                    // Item addition
                    // tab
                    ComboBox field = (ComboBox) binder.getField("searchFields");
                    field.setValue(ConstantsUtils.SELECT_ONE);
                    TextField value = (TextField) binder.getField("searchValue");
                    value.setValue(StringUtils.EMPTY);
                    itemDetailsResultBean.removeAllItems();
                    availableTable.setContainerDataSource(emptyAvailableContainer);
                    availableTable.setVisibleColumns(PsUtils.getInstance().availableIfpCol);
                    availableTable.setColumnHeaders(PsUtils.getInstance().availableIfpColHeader);
                    selectedItemResultBean.removeAllItems();
                    selectedTable.setVisibleColumns(PsUtils.getInstance().availableIfpCol);
                    selectedTable.setColumnHeaders(PsUtils.getInstance().availableIfpColHeader);
                    psLogic.removeAllFromTempTable();
                    pricingTab.clearTable();
                    priceProtectionTabForm.clearTable();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }

		}
		if (selectedTabIndex == NumericConstants.TWO ) { // Pricing Tab
                    if(itemDetailsResultBean.size() > 0) {
                        try {
                            psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
                            itemDetailsResultBean.removeAllItems();
                        } catch (PortalException ex) {
                            LOGGER.error(ex);
                        } catch (SystemException ex) {
                            LOGGER.error(ex);
                        }
                    }
                    pricingTab.enableOrDisableMassCheck(ConstantsUtils.DISABLE);
                    psLogic.resetPricingTab(false, null);
                    pricingTab.loadItemDetailsTable();
		}
                if (selectedTabIndex == NumericConstants.THREE) {
                if(itemDetailsResultBean.size() > 0) {
                        try {
                            psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
                            itemDetailsResultBean.removeAllItems();
                        } catch (PortalException ex) {
                            LOGGER.error(ex);
                        } catch (SystemException ex) {
                            LOGGER.error(ex);
                        }
                    }
                priceProtectionTabForm.enableOrDisableMassCheck(ConstantsUtils.DISABLE);
                psLogic.resetPriceProtectionTab(false, null);
                priceProtectionTabForm.refreshTable();
            }
                
            if (selectedTabIndex == NumericConstants.FOUR) {
                notesTab.resetAddMode();
            }
		
	}

	public void resetLogicForEditMode() throws SystemException, PortalException {
		final int systemId = (Integer) sessionDTO.getSystemId();
		psMaster = psLogic.getPriceschedulesById(Integer.valueOf(systemId));
		if (selectedTabIndex == 0) {
			TextField id = (TextField) binder.getField(FieldNameUtils.PRICE_SCHEDULE_ID);
			id.setValue(psMaster.getPriceScheduleId());
			TextField no = (TextField) binder.getField(FieldNameUtils.PRICE_SCHEDULE_NO);
			no.setValue(psMaster.getPriceScheduleNo());
			TextField name = (TextField) binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME);
			name.setValue(psMaster.getPriceScheduleName());
			ComboBox status = (ComboBox) binder.getField(FieldNameUtils.PS_STATUS);
			status.setValue(psMaster.getPriceScheduleStatus());
			PopupDateField startDate = (PopupDateField) binder.getField(FieldNameUtils.PS_START_DATE);
			startDate.setValue(psMaster.getPriceScheduleStartDate());
			PopupDateField endDate = (PopupDateField) binder.getField(FieldNameUtils.PS_END_DATE);
			endDate.setValue(psMaster.getPriceScheduleEndDate());
			ComboBox type = (ComboBox) binder.getField(FieldNameUtils.PRICE_SCHEDULE_TYPE);
			type.setValue(psMaster.getPriceScheduleType());
			ComboBox category = (ComboBox) binder.getField(FieldNameUtils.PRICE_SCHEDULE_CAT);
			category.setValue(psMaster.getPriceScheduleCategory());
			ComboBox designation = (ComboBox) binder.getField(FieldNameUtils.PS_DESIGNATION);
			designation.setValue(psMaster.getPriceScheduleDesignation());
			ComboBox tradeClass = (ComboBox) binder.getField(FieldNameUtils.TRADE_CLASS);
			tradeClass.setValue(psMaster.getTradeClass());
			TextField parentCompany = (TextField) binder.getField(FieldNameUtils.PARENT_PS_NAME);
                        parentCompany.setReadOnly(false);
                        parentCompany.setValue(psMaster.getParentPriceScheduleName());
                        parentCompany.setReadOnly(true);
                        TextField parentId = (TextField) binder.getField(FieldNameUtils.PARENT_PS_ID);
                        parentId.setReadOnly(false);
                        parentId.setValue(psMaster.getParentPriceScheduleId());
                        parentId.setReadOnly(true);
                       if (ConstantsUtils.COPY.equals(mode)) {
                    psMaster.setPriceScheduleId(StringUtils.EMPTY);
                    psMaster.setPriceScheduleName(StringUtils.EMPTY);
                    psMaster.setPriceScheduleNo(StringUtils.EMPTY);
                }

            }
		if (selectedTabIndex == 1 || selectedTabIndex == NumericConstants.TWO || selectedTabIndex == NumericConstants.THREE) {
			ComboBox field = (ComboBox) binder.getField("searchFields");
			field.setValue(ConstantsUtils.SELECT_ONE);
			TextField value = (TextField) binder.getField("searchValue");
			value.setValue(StringUtils.EMPTY);
                        itemDetailsResultBean.removeAllItems();
                        psLogic.removeAllFromTempTable();
			psLogic.addToTempPSDetailsEdit(psMaster.getPriceScheduleSystemId());
			selectedItemResultBean.removeAllItems();
                        selectedItemResultBean.addAll((List<PSIFPDTO>)psLogic.getSelectedItemListTable(sessionDTO.getSystemId(), false));
			selectedTable.setVisibleColumns(PsUtils.getInstance().availableIfpCol);
			selectedTable.setColumnHeaders(PsUtils.getInstance().availableIfpColHeader);
			availableTable.setContainerDataSource(emptyAvailableContainer);
			availableTable.setVisibleColumns(PsUtils.getInstance().availableIfpCol);
			availableTable.setColumnHeaders(PsUtils.getInstance().availableIfpColHeader);
                        pricingTab.clearTable();
                        priceProtectionTabForm.clearTable();
                        pricingTab.loadItemDetailsTable();
                        priceProtectionTabForm.refreshTable();
		}
                
            if (selectedTabIndex == NumericConstants.FOUR) {
                notesTab.resetAddMode();
            }
	}

	private void saveLogicForAddMode() throws SystemException, CommitException, PortalException {
		binder.getErrorDisplay().clearError();

		if (!binder.getFields().isEmpty()) {
			binder.commit();
		}

		boolean flag = false;

		StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + ConstantsUtils.BREAK);
		if (StringUtils.isBlank(String.valueOf(binder.getField(FieldNameUtils.PRICE_SCHEDULE_ID).getValue()))) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PRICE_SCHEDULE_ID);
			flag = true;
		}
                if (StringUtils.isBlank(String.valueOf(binder.getField(FieldNameUtils.PRICE_SCHEDULE_NO).getValue()))) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PRICE_SCHEDULE_NO);
			flag = true;
		}
                
                  if (StringUtils.isBlank(String.valueOf(binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME).getValue()))) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PRICE_SCHEDULE_NAME);
			flag = true;
		}
		if (binder.getField(FieldNameUtils.PS_STATUS)==null || ((HelperDTO)binder.getField(FieldNameUtils.PS_STATUS).getValue()).getId()==0 ) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PS_STATUS);
			flag = true;
		}
		if (binder.getField(FieldNameUtils.PS_START_DATE).getValue() == null) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PS_START_DATE);
			flag = true;
		}
		if (binder.getField(FieldNameUtils.PS_START_DATE).getValue() != null && binder.getField(FieldNameUtils.PS_END_DATE).getValue() != null) {

			Date ob = (Date) binder.getField(FieldNameUtils.PS_START_DATE).getValue();
			Date ob1 = (Date) binder.getField(FieldNameUtils.PS_END_DATE).getValue();
			if (ob.after(ob1)) {
				if (flag) {
					errorMessage.append(ConstantsUtils.COMMA);
				}
				errorMessage.append("Price Schedule End Date should be after Price Schedule Start Date");
				flag = true;
			} else if (ob.equals(ob1)) {
				if (flag) {
					errorMessage.append(ConstantsUtils.COMMA);
				}
				errorMessage.append("Price Schedule Start Date and Price Schedule End Date are equal");
				flag = true;
			}
		}
                
            if (binder.getField(FieldNameUtils.PS_DESIGNATION).getValue() != null
                    && "Child".equalsIgnoreCase(((HelperDTO) binder.getField(FieldNameUtils.PS_DESIGNATION).getValue()).getDescription())
                    && (binder.getField(FieldNameUtils.PARENT_PS_ID).getValue() == null || StringUtils.isBlank(String.valueOf(binder.getField(FieldNameUtils.PARENT_PS_ID).getValue())))) {

                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.PARENT_PS_ID);
                flag = true;

            }
		if (flag) {
			errorMessage.append(ConstantsUtils.BREAK);
		}
		if (selectedItemResultBean != null && selectedItemResultBean.size() == 0) {
			errorMessage.append("Add atleast One Item in Item Addition tab for PS");
			flag = true;
		}
		if (flag) {

			binder.getErrorDisplay().setError(errorMessage.toString());
			return;
		}
		if (itemDetailsResultBean.size() > 0) {
			psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
			itemDetailsResultBean.removeAllItems();

		}
                
		String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
		String sessionId = String.valueOf(sessionDTO.getUiSessionId());
		String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());
		List<Object> itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "tempCount");
		if (itemList.isEmpty()) {
			binder.getErrorDisplay().setError("Add atleast one Item in Pricing tab for PS");
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "tempCheckedCount");
		if (itemList.isEmpty()) {
			binder.getErrorDisplay().setError("Select atleast one Item in Pricing tab for PS");
			return;
		}
                itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "Status");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("Status required for Item No " + itemNo);
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "PriceType");
                List<Object> itemList1 = psLogic.validateNull(userId, sessionId, tempCreatedDate, ConstantsUtils.PRIC);
		if (itemList.size() > 0 ) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("Price Type is required for Item No " + itemNo);
			return;
                }
                if(itemList1.size() > 0){
                Object itemNo = itemList1.get(0);
		binder.getErrorDisplay().setError("Price is required for Item No " + itemNo);
		return;
                }
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "CPStartDateNull");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("CP Start Date required for Item No " + itemNo);
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "CPStartDateEqual");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("CP Start Date is equal to CP End date for item No" + itemNo);
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "CPStartDateLess");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("CP Start Date is less than CP End date for item No" + itemNo);
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "PPStartDateEqual");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("Price Protection Start Date and Price Protection End date should not be equal for item No" + itemNo);
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "PPStartDateLess");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("Price Protection Start Date is less than Price Protection End date for item No " + itemNo);
			return;
		}
		LOGGER.debug("All validation succesfull :");
		MessageBox.showPlain(Icon.QUESTION, commonutil.getHeaderMessage(),commonutil.getSaveMessage(binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME).getValue().toString()), new MessageBoxListener() {
			/**
			 * Called when a Button has been clicked .
			 *
			 */
			@SuppressWarnings("PMD")
			public void buttonClicked(final ButtonId buttonId) {
				if (buttonId.name().equals(ConstantsUtils.YES)) {
					try {
						success = psLogic.savePS(binder, notesTab.getUploadedData(), notesTab.getAddedNotes(), notesTab.removeDetailsList());
					} catch (Exception exception) {
                                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1003), new MessageBoxListener() {    
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
					if (ConstantsUtils.SUCCESS.equals(success)) {
                                                sessionDTO.setMode(ConstantsUtils.EDIT);
						getUI().getNavigator().navigateTo(PSView.NAME);
						final Notification notification = new Notification(commonutil.getSavedSuccessfulMessage(binder.getField(FieldNameUtils.PRICE_SCHEDULE_ID).getValue().toString(),binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME).getValue().toString()), Notification.Type.HUMANIZED_MESSAGE);
						notification.setPosition(Position.MIDDLE_CENTER);
						notification.setStyleName(ConstantsUtils.MY_STYLE);
						notification.show(Page.getCurrent());
						itemDetailsResultBean.removeAllItems();
					} else if ("duplicate".equals(success)) {
						binder.getErrorDisplay().setError("Price Schedule ID Already Exists");
						return;
					}
				}
			}
		}, ButtonId.YES, ButtonId.NO);
	}

	private void updateLogicForEditMode() throws PortalException, SystemException, CommitException {
		binder.getErrorDisplay().clearError();

		boolean flag = false;
		if (!binder.getFields().isEmpty()) {
			binder.getFields();
			binder.commit();
		}
		StringBuilder errorMessage = new StringBuilder("Information for the following Mandatory fields need to be provided:" + ConstantsUtils.BREAK);
		if (StringUtils.isBlank(String.valueOf(binder.getField(FieldNameUtils.PRICE_SCHEDULE_ID).getValue()))) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PRICE_SCHEDULE_ID);
			flag = true;
		}
                if (StringUtils.isBlank(String.valueOf(binder.getField(FieldNameUtils.PRICE_SCHEDULE_NO).getValue()))) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PRICE_SCHEDULE_NO);
			flag = true;
		}
                 if (StringUtils.isBlank(String.valueOf(binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME).getValue()))) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PRICE_SCHEDULE_NAME);
			flag = true;
		}
		if (binder.getField(FieldNameUtils.PS_STATUS).getValue()==null) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PS_STATUS);
			flag = true;
		}
		if (binder.getField(FieldNameUtils.PS_START_DATE).getValue() == null) {
			if (flag) {
				errorMessage.append(ConstantsUtils.COMMA);
			}
			errorMessage.append(LabelUtils.PS_START_DATE);
			flag = true;
		}
		if (binder.getField(FieldNameUtils.PS_START_DATE).getValue() != null && binder.getField(FieldNameUtils.PS_END_DATE).getValue() != null) {

			Date ob = (Date) binder.getField(FieldNameUtils.PS_START_DATE).getValue();
			Date ob1 = (Date) binder.getField(FieldNameUtils.PS_END_DATE).getValue();
			if (ob.after(ob1)) {
				if (flag) {
					errorMessage.append(ConstantsUtils.COMMA);
				}
				errorMessage.append("Price Schedule End Date should be after Price Schedule Start Date");
				flag = true;
			} else if (ob.equals(ob1)) {
				if (flag) {
					errorMessage.append(ConstantsUtils.COMMA);
				}
				errorMessage.append("Price Schedule Start Date and Price Schedule End Date are equal");
				flag = true;
			}
		}
                
            if (binder.getField(FieldNameUtils.PS_DESIGNATION).getValue() != null
                    && "Child".equalsIgnoreCase(((HelperDTO) binder.getField(FieldNameUtils.PS_DESIGNATION).getValue()).getDescription())
                    && (binder.getField(FieldNameUtils.PARENT_PS_ID).getValue() == null || StringUtils.isBlank(String.valueOf(binder.getField(FieldNameUtils.PARENT_PS_ID).getValue())))) {

                if (flag) {
                    errorMessage.append(ConstantsUtils.COMMA);
                }
                errorMessage.append(LabelUtils.PARENT_PS_ID);
                flag = true;

            }
		if (flag) {
			errorMessage.append(ConstantsUtils.BREAK);
		}
		if (selectedItemResultBean != null && selectedItemResultBean.size() == 0) {
			errorMessage.append("Add atleast One Item in Item Addition tab for PS");
			flag = true;
		}
		if (flag) {
			binder.getErrorDisplay().setError(errorMessage.toString());
			return;
		}

		if (this.itemDetailsResultBean.size() > 0) {
			psLogic.saveToTemp(itemDetailsResultBean.getItemIds(), binder);
			itemDetailsResultBean.removeAllItems();

		}
		String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
		String sessionId = String.valueOf(sessionDTO.getUiSessionId());
		String tempCreatedDate = String.valueOf(sessionDTO.getSessionDate());

		List<Object> itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "tempCount");
		if (itemList.isEmpty()) {
			binder.getErrorDisplay().setError("Add atleast one Item in Pricing tab for PS");
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "tempCheckedCount");
		if (itemList.isEmpty()) {
			binder.getErrorDisplay().setError("Select atleast one Item in Pricing tab for PS");
			return;
		}
                itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "Status");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("Status required for Item No " + itemNo);
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "PriceType");
		List<Object> itemList1 = psLogic.validateNull(userId, sessionId, tempCreatedDate, ConstantsUtils.PRIC);
		if (itemList.size() > 0 ) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("Price Type is required for Item No " + itemNo);
			return;
                }
                if(itemList1.size() > 0){
                Object itemNo = itemList1.get(0);
		binder.getErrorDisplay().setError("Price is required for Item No " + itemNo);
		return;
                }
               
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "CPStartDateNull");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("CP Start Date required for Item No " + itemNo);
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "CPStartDateEqual");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("CP Start Date is equal to CP End date for item No" + itemNo);
			return;
		}

		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "CPStartDateLess");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("CP Start Date is less than CP End date for item No" + itemNo);
			return;
		}
		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "PPStartDateEqual");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("Price Protection Start Date and Price Protection End date should not be equal for item No" + itemNo);
			return;
		}

		itemList = psLogic.validateNull(userId, sessionId, tempCreatedDate, "PPStartDateLess");
		if (itemList.size() > 0) {
			Object itemNo = itemList.get(0);
			binder.getErrorDisplay().setError("Price Protection Start Date is less than Price Protection End date for item No " + itemNo);
			return;
		}

		MessageBox.showPlain(Icon.QUESTION, "Confirmation", "Save record " + binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME).getValue() + " ?", new MessageBoxListener() {
			/**
			 * Called when a Button has been clicked .
			 *
			 */
			@SuppressWarnings("PMD")
			public void buttonClicked(final ButtonId buttonId) {
				String success = StringUtils.EMPTY;
				if (buttonId.name().equals(ConstantsUtils.YES)) {
					try {
						success = psLogic.savePS(binder, notesTab.getUploadedData(), notesTab.getAddedNotes(), notesTab.removeDetailsList());
					} catch (Exception exception) {
                                                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1008), new MessageBoxListener() {    
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
					if (ConstantsUtils.SUCCESS.equals(success)) {
                                            sessionDTO.setMode(ConstantsUtils.EDIT);
                                            getUI().getCurrent().getNavigator().navigateTo(PSView.NAME);
						final Notification notif = new Notification(commonutil.getSavedSuccessfulMessage(binder.getField(FieldNameUtils.PRICE_SCHEDULE_ID).getValue().toString(),binder.getField(FieldNameUtils.RICE_SCHEDULE_NAME).getValue().toString()));
						notif.setPosition(Position.MIDDLE_CENTER);
						notif.setStyleName(ConstantsUtils.MY_STYLE);
						notif.show(Page.getCurrent());
					} else {
						binder.getErrorDisplay().setError("Please enter different PS ID, Since the entered PS ID already exists.");
					}
				}
			}
		}, ButtonId.YES, ButtonId.NO);

	}

	protected void finalize() throws Throwable {
		LOGGER.debug("calling finalize method in :" + this.getClass());
		super.finalize();

	}
        public void configInfoLayoutCopy() {
        psMaster.setPriceScheduleId(StringUtils.EMPTY);
        psMaster.setPriceScheduleName(StringUtils.EMPTY);
        psMaster.setPriceScheduleNo(StringUtils.EMPTY);
    }
}
