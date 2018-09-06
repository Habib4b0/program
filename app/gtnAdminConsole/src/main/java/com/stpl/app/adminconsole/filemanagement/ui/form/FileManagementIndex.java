/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.form;

import static com.stpl.app.adminconsole.util.ResponsiveUtils.getResponsiveControls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.filemanagement.dto.FileManagementDTO;
import com.stpl.app.adminconsole.filemanagement.dto.FileManagementFilterGenerator;
import com.stpl.app.adminconsole.filemanagement.dto.FileMananagementResultDTO;
import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.stpl.app.adminconsole.filemanagement.logic.tablelogic.FileManagementTableLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 * The Class FileManagementIndex.
 *
 * @author Elangovan
 */
public class FileManagementIndex extends CustomComponent implements View {

	@UiField("errorMsg")
	private ErrorLabel errorMsg;

	private final Label space = new Label("&nbsp;", ContentMode.HTML);

	@UiField("fileTypeCombo")
	private ComboBox fileType;

	@UiField("country")
	private ComboBox country;

	@UiField("currentFile")
	private TextField currentFile;

	private final TextField currentFileHidden = new TextField();

	@UiField("effectiveDateStr")
	private TextField effectiveDateStr;

	@UiField("selectFile")
	private CustomTextField selectFile;

	@UiField("saveBtn")
	private Button saveBtn;

	@UiField("resetBtn")
	private Button resetBtn;

	private FileManagementTableLogic tableLogic = new FileManagementTableLogic();

	private ExtPagedTable fileHistoryTable = new ExtPagedTable(tableLogic);

	@UiField("excelExportDetail")
	private Button excelExportDetail;

	@UiField("autoUpdate")
	private Button autoUpdate;

	@UiField("processTracking")
	private Button processTracking;

	@UiField("tableLayout")
	private VerticalLayout tableLayout;

	@UiField("cssLayout")
	private CssLayout cssLayout;

	@UiField("selectFileCssLayout")
	private CssLayout selectFileCssLayout;

	@UiField("fileTypeLb")
	private Label fileTypeLb;

	@UiField("countryLb")
	private Label countryLb;

	@UiField("businessUnit")
	private ComboBox businessUnit;

	@UiField("company")
	private ComboBox company;

	private HorizontalLayout controlLayout = new HorizontalLayout();

	private FileManagementLogic logic = new FileManagementLogic();

	private String[] fileString = new String[NumericConstants.TWO];

	private final BeanItemContainer<FileMananagementResultDTO> resultsBean = new BeanItemContainer<>(
			FileMananagementResultDTO.class);

	private final FileMananagementResultDTO fileMgtDTO = new FileMananagementResultDTO();
	private ExtFilterTable excelTable;
	private BeanItemContainer<FileMananagementResultDTO> excelTableBean;

	private CommonUtil commonUtil = new CommonUtil();

	private static final Logger LOGGER = LoggerFactory.getLogger(FileManagementIndex.class);
	private CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
	private SessionDTO sessionDTO;

	private static ResourceBundle configProperties = ResourceBundle.getBundle("properties.FileManagementConfiguration");

	private FileMananagementResultDTO resultDTO = new FileMananagementResultDTO();

	public ErrorLabel getErrorMsg() {
		return errorMsg;
	}

	public Label getSpace() {
		return space;
	}

	public ComboBox getFileType() {
		return fileType;
	}

	public ComboBox getCountry() {
		return country;
	}

	public TextField getCurrentFile() {
		return currentFile;
	}

	public TextField getCurrentFileHidden() {
		return currentFileHidden;
	}

	public TextField getSelectFile() {
		return selectFile;
	}

	public Button getSaveBtn() {
		return saveBtn;
	}

	public Button getResetBtn() {
		return resetBtn;
	}

	public ExtFilterTable getFileHistoryTable() {
		return fileHistoryTable;
	}

	public Button getExcelExport() {
		return excelExportDetail;
	}

	public BeanItemContainer<FileMananagementResultDTO> getResultsBean() {
		return resultsBean;
	}

	public FileMananagementResultDTO getFileMgtDTO() {
		return fileMgtDTO;
	}

	public void setFileHistoryTable(final ExtPagedTable fileHistoryTable) {
		this.fileHistoryTable = fileHistoryTable;
	}

	private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(fileMgtDTO));

	/**
	 * Instantiates a new file management index.
	 *
	 * @throws Exception
	 * @throws PortalException
	 * @throws SystemException
	 */
	public FileManagementIndex(final SessionDTO sessionDTO) {
		super();
		this.sessionDTO = sessionDTO;
		LOGGER.debug("FileManagementIndex Constructor Entered ");
		try {
			init();
		} catch (PortalException ex) {
			LOGGER.error(ex.getMessage());
		} catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}

	}

	/**
	 * Inits the.
	 *
	 * @throws Exception
	 * @throws PortalException
	 * @throws SystemException
	 */
	private void init() throws PortalException {
		LOGGER.debug("init Method Started ");
		setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/fileManagement.xml"), this));
		getBinder();
		final StplSecurity stplSecurity = new StplSecurity();
		final String userId = String.valueOf(sessionDTO.getUserId());
		final Map<String, AppPermission> fieldItemHM;
		fieldItemHM = stplSecurity.getFieldOrColumnPermission(userId,
				CommonUtil.FILE_MANAGEMENT + "," + CommonUtil.LANDING_SCREEN, false);
		final Map<String, AppPermission> functionCompanyHM = stplSecurity.getBusinessFunctionPermission(userId,
				CommonUtil.FILE_MANAGEMENT + "," + CommonUtil.LANDING_SCREEN);

		getResponsiveFirstTab(fieldItemHM);
		getButtonPermission(functionCompanyHM);
		configureTable();
		configureFields();
		LOGGER.debug("init Method Ended ");
	}

	private void getResponsiveFirstTab(final Map<String, AppPermission> fieldItemHM) {
		LOGGER.debug("Entering getFirstTab1");
		try {
			List<Object> resultList = commonUtil.getFieldsForSecurity(CommonUtil.FILE_MANAGEMENT,
					CommonUtil.LANDING_SCREEN);

			commonSecurity.removeSearchComponentOnPermission(resultList, selectFileCssLayout, fieldItemHM);

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.debug("Ending getFirstTab1");
	}

	/**
	 * Gets the customer group binder.
	 *
	 * @return the customer group binder
	 */
	private ErrorfulFieldGroup getBinder() {
		LOGGER.debug("Entering getCustomerGroupBinder method");
		binder.bindMemberFields(this);
		binder.setItemDataSource(new BeanItem<>(fileMgtDTO));
		binder.setBuffered(true);

		LOGGER.debug("getCustomerGroupBinder method RETURNS customerGroupBinder ");
		return binder;
	}

	private void getButtonPermission(Map<String, AppPermission> functionCompanyHM) {

		if (functionCompanyHM.get(ConstantsUtils.PROCESS_TRACKING_BUTTON) != null
				&& !((AppPermission) functionCompanyHM.get(ConstantsUtils.PROCESS_TRACKING_BUTTON)).isFunctionFlag()) {
			processTracking.setVisible(false);
		}
		if (functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON) != null
				&& !((AppPermission) functionCompanyHM.get(ConstantsUtils.SAVE_BUTTON)).isFunctionFlag()) {
			saveBtn.setVisible(false);
		}
		if (functionCompanyHM.get(ConstantsUtils.AUTO_UPDATE_BUTTON) != null
				&& !((AppPermission) functionCompanyHM.get(ConstantsUtils.AUTO_UPDATE_BUTTON)).isFunctionFlag()) {
			autoUpdate.setVisible(false);
		}

		if (functionCompanyHM.get(ConstantsUtils.RESET_BUTTON) != null
				&& !((AppPermission) functionCompanyHM.get(ConstantsUtils.RESET_BUTTON)).isFunctionFlag()) {
			resetBtn.setVisible(false);
		}

	}

	private void saveButtonLogic() {

		saveBtn.addClickListener(new Button.ClickListener() {
			/**
			 * Called when a {@link Button} has been clicked. A reference to the button is
			 * given by {@link ClickEvent#getButton()}.
			 *
			 * @param event
			 *            An event containing information about the click.
			 */
			public void buttonClick(final Button.ClickEvent event) {
				try {
					LOGGER.debug("In configureFields saveOnClick started");
					if (ConstantsUtils.EMPTY.equals(selectFile.getValue())) {
						AbstractNotificationUtils.getErrorNotification("Save Error",
								"Please select a file using the Lookup icon feature");
					} else {
						resultDTO.setFileName(fileMgtDTO.getFileName());
						resultDTO.setVersion(fileMgtDTO.getVersion());
						resultDTO.setType(fileMgtDTO.getType());
						if (logic.checkBusinessUnits(resultDTO)) {
							/**
							 * To change alert Type to ERROR change MessageType=ERROR in
							 * FileManagementConfiguration.properties file *
							 */
							if (configProperties.getString("MessageType").equalsIgnoreCase("WARNING")) {
								MessageBox.showPlain(Icon.WARN, "Warning", configProperties.getString("WARNING_MSG"),
										new MessageBoxListener() {
											/**
											 * The method is triggered when a button of the message box is pressed.
											 *
											 */
											@SuppressWarnings("PMD")
											public void buttonClicked(final ButtonId buttonId) {
												if (buttonId.name().equals("YES")) {
													try {
														saveOnClick();
													} catch (Exception ex) {
														LOGGER.error(ex.getMessage());
													}
												} 
											}
										}, ButtonId.YES, ButtonId.NO);

							} else {
								AbstractNotificationUtils.getErrorNotification(CommonUtil.ERROR,
										configProperties.getString("ERROR_MSG"));
							}

						} else {
							saveOnClick();
						}
					}
					LOGGER.debug("In configureFields saveOnClick Ended");
				} catch (SystemException ex) {
					LOGGER.error(ex.getMessage());
				} catch (PortalException ex) {
					LOGGER.error(ex.getMessage());
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage());
				}
			}
		});

	}

	private void resetButtonLogic() {
		resetBtn.addClickListener(new Button.ClickListener() {
			/**
			 * Called when a {@link Button} has been clicked. A reference to the button is
			 * given by {@link ClickEvent#getButton()}.
			 *
			 * @param event
			 *            An event containing information about the click.
			 */
			public void buttonClick(final Button.ClickEvent event) {
				LOGGER.debug("In configureFields resetBtn.addClickListener started");
				final AbstractNotificationUtils notificationUtils = new AbstractNotificationUtils() {
					/**
					 * Executed by clicking yes .
					 */
					@SuppressWarnings("PMD")
					public void yesMethod() {
						try {
							company.setValue(null);
							fileType.select(0);
							country.setValue(null);
							currentFile.setValue(ConstantsUtils.EMPTY);
							effectiveDateStr.setValue(ConstantsUtils.EMPTY);
							selectFile.setValue(ConstantsUtils.EMPTY);
							businessUnit.setValue(null);
						} catch (Exception e) {
							LOGGER.error(e.getMessage());
						}

					}

					/**
					 * Executed by clicking No
					 */
					public void noMethod() {
						return;
					}
				};
				notificationUtils.getConfirmationMessage(ConstantsUtils.CONFORMATION,
						"Are you sure you want to reset the page to default/previous values?");
				LOGGER.debug("In configureFields resetBtn.addClickListener Ended");
			}
		});
	}

	private void excelExportBtnLogic() {
		excelExportDetail.addClickListener(new ClickListener() {

			public void buttonClick(final ClickEvent event) {
				LOGGER.debug("In configureFields selectedResultsExcelExport.addClickListener started");
				try {
					configureExcelResultTable();
					VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, "true");
					loadExcelTable(CommonUtil.getSelectedFileType(fileType), String.valueOf(country.getValue()),
							String.valueOf(businessUnit.getValue()));
					ExcelExport excel = new ExcelExport(new ExtCustomTableHolder(fileHistoryTable),
							"File Management History", "File Management History", "FileManagementHistory.xls", false);
					excel.export();

				} catch (Exception e) {
					LOGGER.error(e.getMessage());
				}
				LOGGER.debug("In configureFields selectedResultsExcelExport.addClickListener Ended");
			}
		});
	}

	private void autoUpdateBtnLogic() {

		autoUpdate.addClickListener(new Button.ClickListener() {

			public void buttonClick(final Button.ClickEvent event) {
				final Emailer email = new Emailer();

				final FileManagementDTO fileManagementDto;
				try {
					if (fileHistoryTable.getValue() == null) {
						AbstractNotificationUtils.getErrorNotification(CommonUtil.ERROR,
								"Please select a File to Auto-Update the Projection");
					} else {

						fileManagementDto = logic.getCurrentFileInfo(CommonUtil.getSelectedFileType(fileType),
								String.valueOf(businessUnit.getValue()), company.getValue());
						String file = fileManagementDto.getCurrentFile();
						String version = fileManagementDto.getForecastVersion();
						FileMananagementResultDTO dto = (FileMananagementResultDTO) fileHistoryTable.getValue();
						if (dto.getFile().equals(file) && dto.getVersion().equals(version)) {
							MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION,
									"Do you want to Activate the file and automatically recalculate Forecasting Projections in Pending or Approved?",
									new MessageBoxListener() {

										public void buttonClicked(ButtonId buttonId) {
											String msg = email.sendMail("support@bpitechnologies.com", "TestMail",
													"You clicked", false, StringUtils.EMPTY);
											if (msg.equals(ConstantsUtils.SUCCESS)) {
                                                                                    LOGGER.debug("SUCCESS");
											}
										}

									}, ButtonId.YES, ButtonId.NO);

						} else {
							AbstractNotificationUtils.getErrorNotification(CommonUtil.ERROR,
									"Please select current GTS file to auto-update the projection");
						}
					}

				} catch (SystemException ex) {
					LOGGER.error(ex.getMessage());
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage());
				}

			}
		});
	}

	private void processTrackingBtnLogic() {
		processTracking.addClickListener(new Button.ClickListener() {

			public void buttonClick(final Button.ClickEvent event) {
				final TrackingProgress lookUp = new TrackingProgress("Tracking Process Lookup", sessionDTO);
				lookUp.init();
				UI.getCurrent().addWindow(lookUp);
			}
		});
	}

	private void configureFields() throws SystemException {

		LOGGER.debug("configureFields Method Started ");

		cssLayout.setSizeFull();

		excelExportDetail.setDescription(ConstantsUtils.EXCEL_EXPORT);
		fileType.focus();

		fileType();
		effectiveDateStr.setEnabled(true);
		effectiveDateStr.setValue(ConstantsUtils.EMPTY);
		currentFile.setEnabled(false);
		effectiveDateStr.setEnabled(false);
		country.addItem(ConstantsUtils.SELECT_ONE);
		country.addItem(ConstantsUtils.COUNTRY_US);
		country.addItem(ConstantsUtils.COUNTRY_PR);
		country.setNullSelectionAllowed(true);
		country.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
		country.select(ConstantsUtils.SELECT_ONE);
		country.setImmediate(true);

		logic.loadBusinessUnitDdlb(businessUnit, false);

		company.setPageLength(NumericConstants.SEVEN);
		company.setImmediate(true);
		company.addItem(0);
		company.setItemCaption(0, ConstantsUtils.SELECT_ONE);
		company.setNullSelectionAllowed(true);
		company.setNullSelectionItemId(0);
		company.setInputPrompt(ConstantsUtils.SELECT_ONE);
		company.markAsDirty();

		List<Object[]> companyList = logic.getCompanies(0);
		if (companyList != null && !companyList.isEmpty()) {
			Map map = new HashMap();
			for (Object[] object : companyList) {
				company.addItem(object[0]);
				company.setItemCaption(object[0],
						object[NumericConstants.TWO] + " - " + object[NumericConstants.THREE]);
				map.put(object[0], object[NumericConstants.THREE]);
			}
			company.setData(map);
		}
		company.setValue(null);

		company.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {

				if (company == null) {
					tableLogic.clearAll();
				} else {
					loadCurrentFile();
					tableLogic.configureSearchData(CommonUtil.getSelectedFileType(fileType),
							String.valueOf(country.getValue()), String.valueOf(businessUnit.getValue()),
							company.getValue());
				}

			}
		});

		excelExportDetail.setIcon(new ThemeResource("img/excel.png"));
		excelExportDetail.setStyleName("link");

		selectFile.addClickListener(new CustomTextField.ClickListener() {

			@SuppressWarnings("PMD")
			public void click(CustomTextField.ClickEvent event) {
				if (fileType.getValue() == null || StringUtils.isBlank(String.valueOf(fileType.getValue()))
						|| String.valueOf(fileType.getValue()).equals(ConstantsUtils.SELECT_ONE)
						|| String.valueOf(fileType.getValue()).equals("0")) {
					AbstractNotificationUtils.getErrorNotification(CommonUtil.ERROR,
							"Please select a File Type/Country");
				} else if (country.getValue() == null || country.getValue() == ConstantsUtils.EMPTY
						|| country.getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
					AbstractNotificationUtils.getErrorNotification(CommonUtil.ERROR,
							"Please select a File Type/Country");
				} else if (businessUnit.getValue() == null || businessUnit.getValue() == ConstantsUtils.EMPTY
						|| businessUnit.getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
					AbstractNotificationUtils.getErrorNotification(CommonUtil.ERROR, "Please select a Business Unit");
				} else if (company.getValue() == null || company.getValue() == ConstantsUtils.EMPTY
						|| company.getValue().toString().equals(ConstantsUtils.SELECT_ONE)) {
					AbstractNotificationUtils.getErrorNotification(CommonUtil.ERROR, "Please select a Company");
				} else {

					fileMgtDTO.setCompanyMasterSystemId((Integer) company.getValue());
					Map map = (Map) company.getData();
					fileMgtDTO.setCompanyName(String.valueOf(map.get(company.getValue())));

					final FileManagementLookup lookUp = new FileManagementLookup(fileMgtDTO, selectFile, fileType,
							String.valueOf(country.getValue()), sessionDTO,
							ConstantsUtils.INVENTORY_WITHDRAWAL_DETAIL
									.equals(CommonUtil.getSelectedFileType(fileType).getDescription()),
							String.valueOf(businessUnit.getValue()));
					lookUp.init();

					UI.getCurrent().addWindow(lookUp);

					lookUp.addCloseListener(new Window.CloseListener() {

						public void windowClose(final Window.CloseEvent e) {
							resultDTO = lookUp.getResultDTO();
							saveBtn.focus();
						}
					});
				}
			}
		});

		country.addValueChangeListener(new Property.ValueChangeListener() {

			public void valueChange(final Property.ValueChangeEvent event) {
				LOGGER.debug("In configureFields country.addValueChangeListener started");
				if (country.getValue() == null || StringUtils.EMPTY.equals(country.getValue())) {
					tableLogic.clearAll();
					tableLogic.setReset(true);
					tableLogic.setRequiredCount(true);
					tableLogic.setCurrentPage(1);
					fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
					setTableDefaultConfig();
				}
				if (ConstantsUtils.PR.equals(country.getValue())) {
					currentFile.setValue(ConstantsUtils.EMPTY);
					effectiveDateStr.setValue(ConstantsUtils.EMPTY);
				} else {
					loadCurrentFile();
				}
					tableLogic.configureSearchData(CommonUtil.getSelectedFileType(fileType),
							String.valueOf(country.getValue()), String.valueOf(businessUnit.getValue()),
							company.getValue());
					fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
					fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
					fileHistoryTable.setImmediate(true);
					fileHistoryTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
					fileHistoryTable.addStyleName(CommonUtil.TABLECHECKBOX);
					fileHistoryTable.setSelectable(true);
					fileHistoryTable.markAsDirtyRecursive();

				LOGGER.debug("In configureFields country.addValueChangeListener Ended");
			}
		});
		businessUnit.addValueChangeListener(new Property.ValueChangeListener() {

			public void valueChange(final Property.ValueChangeEvent event) {
				LOGGER.debug("In configureFields country.addValueChangeListener started");
				if (businessUnit.getValue() == null || StringUtils.EMPTY.equals(businessUnit.getValue())) {
					tableLogic.clearAll();
					tableLogic.setReset(true);
					tableLogic.setRequiredCount(true);
					tableLogic.setCurrentPage(1);
					fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
					setTableDefaultConfig();
				}
				
					loadCurrentFile();
					tableLogic.configureSearchData(CommonUtil.getSelectedFileType(fileType),
							String.valueOf(country.getValue()), String.valueOf(businessUnit.getValue()),
							company.getValue());
					fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
					fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
					fileHistoryTable.setImmediate(true);
					fileHistoryTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
					fileHistoryTable.addStyleName(CommonUtil.TABLECHECKBOX);
					fileHistoryTable.setSelectable(true);
					fileHistoryTable.markAsDirtyRecursive();

				
				LOGGER.debug("In configureFields country.addValueChangeListener Ended");
			}
		});
		fileType.addValueChangeListener(new Property.ValueChangeListener() {

			public void valueChange(final Property.ValueChangeEvent event) {
				LOGGER.debug("In configureFields fileType.addValueChangeListener started= {}", fileType.getValue());
				if (ConstantsUtils.PR.equals(country.getValue())) {
					currentFile.setValue(ConstantsUtils.EMPTY);
					effectiveDateStr.setValue(ConstantsUtils.EMPTY);
				} else {
					loadCurrentFile();
				}
				if (fileType.getValue() == null || ConstantsUtils.SELECT_ONE.equals(fileType.getValue())
						|| "null".equals(fileType.getValue().toString().trim())
						|| "0".equals(fileType.getValue().toString().trim())) {
					resultsBean.removeAllItems();
					currentFile.setValue(ConstantsUtils.EMPTY);
					currentFileHidden.setValue(ConstantsUtils.EMPTY);
					effectiveDateStr.setValue(ConstantsUtils.EMPTY);
					selectFile.setValue(ConstantsUtils.EMPTY);
				} else {

					try {
						fileString[1] = String.valueOf(country.getValue());
						tableLogic.configureSearchData(CommonUtil.getSelectedFileType(fileType), fileString[1],
								String.valueOf(businessUnit.getValue()), company.getValue());
						fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
						fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
						fileHistoryTable.setImmediate(true);
						fileHistoryTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
						fileHistoryTable.addStyleName(CommonUtil.TABLECHECKBOX);
						fileHistoryTable.setSelectable(true);
						fileHistoryTable.markAsDirtyRecursive();
						if (!ConstantsUtils.PR.equals(country.getValue())) {
							loadCurrentFile();
						}

					} catch (SystemException e) {

						final String errorMessage = ErrorCodeUtil.getErrorMessage(e);
						LOGGER.error(e.getMessage());
						AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
								errorMessage);
					} catch (Exception e) {

						LOGGER.error(e.getMessage());
						AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
								ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4007));
					}
				}
				LOGGER.debug("In configureFields fileType.addValueChangeListener Ended");
			}
		});

		LOGGER.debug("configureFields Method Ended ");
		excelExportBtnLogic();
		processTrackingBtnLogic();
		saveButtonLogic();
		autoUpdateBtnLogic();
		resetButtonLogic();

	}

	private void fileType() throws SystemException {
		List<HelperDTO> fileTypeList = new ArrayList<>();
		CommonUtil.getFileTypeComboBox(fileType, "FILE_TYPE", fileTypeList);

		fileType.setNullSelectionAllowed(true);
		fileType.setNullSelectionItemId(0);
		fileType.select(0);
		fileType.setImmediate(true);

	}

	/**
	 * Save on click.
	 *
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 * @throws Exception
	 *             the exception
	 */
	protected void saveOnClick() throws PortalException {
		LOGGER.debug("saveOnClick method started");
		final FileManagementLogic fileMgtLogic = new FileManagementLogic();
		final FileManagementDTO fileMgtDTO1 = fileMgtLogic.getCurrentFileInfo(CommonUtil.getSelectedFileType(fileType),
				String.valueOf(businessUnit.getValue()), company.getValue());
		if (fileMgtDTO1.getCurrentFile().equals(selectFile.getValue())
				&& fileMgtDTO1.getForecastVersion().equals(fileMgtDTO.getVersion())) {
			AbstractNotificationUtils.getErrorNotification("Save Error",
					"The selected file is the current active file.Please select a different file");
		} else {
			MessageBox.showPlain(Icon.QUESTION, ConstantsUtils.CONFORMATION,
					"Save record " + selectFile.getValue() + "?", new MessageBoxListener() {

						@SuppressWarnings("PMD")
						public void buttonClicked(final ButtonId buttonId) {
							if (buttonId.name().equals(ConstantsUtils.YES)) {
								final FileManagementLogic fileMgtLogic = new FileManagementLogic();
								try {
									fileMgtDTO.setBusinessUnitSysId(
											Integer.parseInt(String.valueOf(businessUnit.getValue())));
									final String msg = fileMgtLogic.saveFileMgtHist(fileMgtDTO,
											CommonUtil.getSelectedFileType(fileType), sessionDTO);
									if (ConstantsUtils.SUCCESS.equals(msg)) {
										CommonUIUtils.getMessageNotification("Saved succesfully");
									} else {
										CommonUIUtils.getMessageNotification("Save failed");
									}
									fileString[0] = String.valueOf(CommonUtil.getSelectedFileType(fileType).getId());

									fileString[1] = ConstantsUtils.EMPTY;

									tableLogic.configureSearchData(CommonUtil.getSelectedFileType(fileType),
											fileString[1], String.valueOf(businessUnit.getValue()), company.getValue());
									fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
									fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
									fileHistoryTable.setImmediate(true);
									fileHistoryTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
									fileHistoryTable.addStyleName(CommonUtil.TABLECHECKBOX);
									fileHistoryTable.setSelectable(true);
									fileHistoryTable.markAsDirtyRecursive();

									currentFile.setValue(selectFile.getValue());
									selectFile.setValue(StringUtils.EMPTY);
									// Removed Alert message based on CEL-281 online CR
									Date gtsDate = new com.stpl.app.adminconsole.util.CommonUtils()
											.getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
									fileMgtLogic.updateAutoModeProcess(gtsDate);
									sendMailOnFileActivation();
								} catch (SystemException e) {
									final String errorMessage = ErrorCodeUtil.getErrorMessage(e);
									LOGGER.error(e.getMessage());
									AbstractNotificationUtils.getErrorNotification(
											ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMessage);
								} catch (Exception e) {
									LOGGER.error(e.getMessage());
									AbstractNotificationUtils.getErrorNotification(
											ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),
											ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4002));
								}
							}
						}
					}, ButtonId.YES, ButtonId.NO);
		}
		LOGGER.debug("saveOnClick method Ended");
	}

	/**
	 *
	 * This method is always called before the view is shown on screen.
	 *
	 * @param event
	 *            the event
	 */
	public void enter(final ViewChangeListener.ViewChangeEvent event) {
		return;
	}

	private void configureTable() {

		tableLayout.addComponent(fileHistoryTable);
		getResponsiveControls(tableLogic.createControls(), controlLayout);
		tableLayout.addComponent(controlLayout);

		tableLogic.setContainerDataSource(resultsBean);
		tableLogic.setPageLength(10);
		tableLogic.sinkItemPerPageWithPageLength(false);
		setTableDefaultConfig();
		fileHistoryTable.setSelectable(true);
		fileHistoryTable.markAsDirty();
		fileHistoryTable.setComponentError(null);
		fileHistoryTable.setFilterBarVisible(true);
		fileHistoryTable.setFilterDecorator(new ExtDemoFilterDecorator());
		fileHistoryTable.setFilterGenerator(new FileManagementFilterGenerator());
		fileHistoryTable.setValidationVisible(false);
		fileHistoryTable.addStyleName("filterbar");

	}

	public void setTableDefaultConfig() {

		fileHistoryTable.setVisibleColumns(CommonUIUtil.getfileMgmtHistoryResultColumns());
		fileHistoryTable.setColumnHeaders(CommonUIUtil.getfileMgmtHistoryResultHeader());
		fileHistoryTable.markAsDirtyRecursive();
		fileHistoryTable.setImmediate(true);
		fileHistoryTable.setWidth(NumericConstants.NINTY_NINE, UNITS_PERCENTAGE);
		fileHistoryTable.setHeight("250px");

	}

	private void configureExcelResultTable() {
		excelTableBean = new BeanItemContainer<>(FileMananagementResultDTO.class);
		excelTable = new ExtFilterTable();
		tableLayout.addComponent(excelTable);
		excelTable.setVisible(false);
		excelTable.setContainerDataSource(excelTableBean);
		excelTable.setVisibleColumns(CommonUIUtil.getfileMgmtHistoryResultColumns());
		excelTable.setColumnHeaders(CommonUIUtil.getfileMgmtHistoryResultHeader());
		excelTable.markAsDirtyRecursive();

	}

	private void loadExcelTable(HelperDTO fileType, String country, String businessUnit) throws SystemException {
		excelTableBean.removeAllItems();
		if (fileHistoryTable.size() != 0 && fileType != null) {
			int recordCount = (Integer) logic.getFileHistoryResults(fileType, country, businessUnit,
					(Object) company.getValue(), 0, 0, null, null, true);
			List<FileMananagementResultDTO> resultList = (List<FileMananagementResultDTO>) logic.getFileHistoryResults(
					fileType, country, businessUnit, (Object) company.getValue(), 0, recordCount, null, null, false);
			excelTableBean.addAll(resultList);
		}
	}

	public void sendMailOnFileActivation() {
		final Emailer email = new Emailer();
		try {
			List<FileManagementDTO> processList = logic.getSearchResult(fileType);
		    email.sendMailonFileActivation(false, processList);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	private void loadCurrentFile() {
		final FileManagementLogic fileMgtLogic = new FileManagementLogic();
		currentFile.setValue(ConstantsUtils.EMPTY);
		currentFileHidden.setValue(ConstantsUtils.EMPTY);
		effectiveDateStr.setValue(ConstantsUtils.EMPTY);
		selectFile.setValue(ConstantsUtils.EMPTY);
		final FileManagementDTO fileDTO = fileMgtLogic.getCurrentFileInfo(CommonUtil.getSelectedFileType(fileType),
				String.valueOf(businessUnit.getValue()), company.getValue());
		if(fileDTO==null) {
			return;
		}
		currentFile.setValue(fileDTO.getCurrentFile());
		currentFileHidden.setValue(fileDTO.getCurrentFile());
		effectiveDateStr.setValue(fileDTO.getEffectiveDate());
	}

}
