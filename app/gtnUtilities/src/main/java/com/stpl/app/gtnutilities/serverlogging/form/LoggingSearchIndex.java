/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.serverlogging.form;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.gtnutilities.charts.logic.TableLogic;
import com.stpl.app.gtnutilities.serverlogging.dto.LoggingDto;
import com.stpl.app.gtnutilities.serverlogging.logic.SearchLogic;
import com.stpl.app.gtnutilities.util.CommonMethods;
import com.stpl.app.gtnutilities.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.GtnFileUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Karthik.Raja
 */
public class LoggingSearchIndex extends CustomComponent implements View {

	private static final Logger LOGGER = Logger.getLogger(LoggingSearchIndex.class);
	final SearchLogic searchLogic = SearchLogic.getInstance();
	@UiField("saveBtn")
	private Button saveBtn;
	@UiField("resetBtn")
	private Button resetBtn;
	@UiField("viewBtn")
	private Button viewBtn;
	@UiField("deleteBtn")
	private Button deleteBtn;
	private Button downloadBtn = new Button(Constants.DOWNLOAD);
	@UiField("buttonLayout2")
	private HorizontalLayout buttonLayout2;
	public LoggingDto loggingDTO = new LoggingDto();
	@UiField("logDestination")
	private TextField logDestination;
	@UiField("messagesSelected")
	private TextField messagesSelected;
	/**
	 * The results bean.
	 */
	public BeanItemContainer<LoggingDto> resultsBean = new BeanItemContainer<LoggingDto>(LoggingDto.class);
	/**
	 * The Constant LOGGING_TABLE_COLUMNS.
	 */
	public static final Object[] LOGGING_TABLE_COLUMNS = new Object[] { "logDestination", "active",
			"messagesSelected" };
	/**
	 * The Constant LOGGING_TABLE_HEADER.
	 */
	public static final String[] LOGGING_TABLE_HEADER = new String[] { "Log Destination", "Active",
			"Messages Selected" };
	@UiField("tableLayout")
	VerticalLayout tableLayout;
	@UiField("buttonLayout")
	HorizontalLayout buttonLayout;
	private HorizontalLayout controlLayout = new HorizontalLayout();
	TableLogic tableLogic = new TableLogic();
	ExtPagedTable results = new ExtPagedTable(tableLogic);
	public String logDestinationValue;
	private boolean isActive = false;
	/**
	 *
	 * /** The Constant NULLOBJECT.
	 */
	private static final BeanItem<?> NULLOBJECT = null;

	/**
	 * Instantiates a new LoggingSearchIndex
	 */
	public LoggingSearchIndex() {

		super();
		try {
			init();
			configureFields();
			refreshTable();
			addButtonListeners();
		} catch (Exception e) {

			LOGGER.debug(e);
		}
	}

	/**
	 * Inits the.
	 */
	private void init() {
		setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/LoggingSearchIndex.xml"), this));
		configureTable();
		downloadBtn = new Button(Constants.DOWNLOAD);
		buttonLayout2.addComponent(downloadBtn);
	}

	/**
	 * (non-Javadoc).
	 *
	 * @param event
	 *            the event
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	public void enter(final ViewChangeListener.ViewChangeEvent event) {
		return;
	}

	/**
	 * Configure fields.
	 */
	private void configureFields() {
		logDestination.setValue(StringUtils.EMPTY);
		messagesSelected.setValue(StringUtils.EMPTY);
	}

	/**
	 * TO rew the grid
	 */
	private void refreshTable() {
		try {
			tableLogic.configureSearchData(null, Constants.Server_Logging);
		} catch (Exception ex) {
			LOGGER.debug(ex);
		}
	}

	private void configureTable() {

		tableLayout.addComponent(results);
		CommonMethods.getResponsiveControls(tableLogic.createControls(), controlLayout);
		tableLayout.addComponent(controlLayout);
		tableLogic.setContainerDataSource(resultsBean);
		tableLogic.setPageLength(NumericConstants.FIFTEEN);
		tableLogic.sinkItemPerPageWithPageLength(false);
		results.setVisibleColumns(LOGGING_TABLE_COLUMNS);
		results.setColumnHeaders(LOGGING_TABLE_HEADER);
		CommonMethods.commonTableConfig(results);
		results.addValueChangeListener(new Property.ValueChangeListener() {
			/**
			 * Notifies this listener that the Property's value has changed.
			 *
			 *
			 */
			@SuppressWarnings("PMD")
			public void valueChange(final Property.ValueChangeEvent event) {
				LOGGER.debug("In results valueChange");
				resultsItemClick(event.getProperty().getValue());
			}
		});

	}

	private void addButtonListeners() {
		saveBtn.addClickListener(new Button.ClickListener() {
			public void buttonClick(final Button.ClickEvent event) {
				if (logDestination.getValue() != null && !logDestination.getValue().isEmpty()) {
					searchLogic.addLogFile(logDestination.getValue(),
							messagesSelected.getValue() == null || messagesSelected.getValue().isEmpty() ? "---"
									: messagesSelected.getValue(),
							false);
					refreshTable();
					CommonMethods.getInfoNotification("Success", "Log File Added Scucessfully");
				} else {
					CommonMethods.getErrorNotification("Mandatory Field", "Please Enter Log Destination");
				}
			}
		});
		viewBtn.addClickListener(new Button.ClickListener() {
			public void buttonClick(final Button.ClickEvent event) {
				if (logDestinationValue != null) {
					if (isActive) {
						VaadinSession.getCurrent().setAttribute("LOG_DESTINATION", logDestinationValue);
						UI.getCurrent().getNavigator().navigateTo(ViewLog.NAME);
					} else {
						CommonMethods.getErrorNotification(Constants.ERROR, "Selected Record is inactive");
					}
				} else {
					CommonMethods.getErrorNotification(Constants.ERROR, "Please Select any record");
				}
			}
		});
		deleteBtn.addClickListener(new Button.ClickListener() {
			public void buttonClick(final Button.ClickEvent event) {
				if (logDestinationValue != null) {
					searchLogic.addLogFile(logDestinationValue, StringUtils.EMPTY, true);
					refreshTable();
					CommonMethods.getInfoNotification(Constants.SUCCESS, "Log File Removed Scucessfully");
				} else {
					CommonMethods.getErrorNotification(Constants.ERROR, "Please Select any record");
				}
			}
		});
		resetBtn.addClickListener(new Button.ClickListener() {
			public void buttonClick(final Button.ClickEvent event) {
				messagesSelected.setValue(Constants.EMPTY);
				logDestination.setValue(Constants.EMPTY);
			}
		});
	}

	protected void resultsItemClick(final Object obj) {
		if (obj == null) {
			logDestinationValue = null;
			isActive = false;
		} else {
			BeanItem<?> targetItem;
			if (obj instanceof BeanItem<?>) {
				targetItem = (BeanItem<?>) obj;
			} else if (obj instanceof LoggingDto) {
				targetItem = new BeanItem<LoggingDto>((LoggingDto) obj);
			} else {
				targetItem = NULLOBJECT;
			}
			isActive = ((LoggingDto) targetItem.getBean()).getActive().equalsIgnoreCase("Yes");
			logDestinationValue = ((LoggingDto) targetItem.getBean()).getLogDestination();
			if (isActive) {
				buttonLayout2.removeComponent(downloadBtn);
				downloadBtn = new Button(Constants.DOWNLOAD);
				Resource res = new FileResource(GtnFileUtil.getFile(logDestinationValue));
				FileDownloader fd = new FileDownloader(res);
				fd.extend(downloadBtn);
				buttonLayout2.addComponent(downloadBtn);
			}
		}
	}
}
