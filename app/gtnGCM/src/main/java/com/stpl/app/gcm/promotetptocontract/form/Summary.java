/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.UiUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author alok.v
 */
public class Summary extends VerticalLayout implements View {

	/**
	 * View name for navigation.
	 */
	public static final String NAME = StringUtils.EMPTY;
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(Summary.class);
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private SessionDTO session;
	private SummaryTPDetails summaryTPDetails;
	private SummarySalesProjectionTransfer summarySalesProjectionTransfer;
	private TabSheet tabSheet;
	private final Map<Integer, Boolean> tabLazyLoadMap = new HashMap<>();
	private int tabPosition;
	@UiField("companyNo")
	public TextField companyNo;
	@UiField("companyName")
	public TextField companyName;
	@UiField("companyType")
	public TextField companyType;
	@UiField("companyCategory")
	public TextField companyCategory;
	@UiField("tradeClass")
	public TextField tradeClass;
	ExtFilterTable resultTable;

	public Summary(SessionDTO session, ExtFilterTable resultTable) {
		try {
			this.session = session;
			this.resultTable = resultTable;
			addComponent(Clara.create(getClass().getResourceAsStream("/summary.xml"), this));
			init();
			addContent();
			setHeaderValues();

		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	public void setHeaderValues() {
		PromoteTpToChDto dto = (PromoteTpToChDto) resultTable.getValue();
		companyNo.setValue(dto.getCompanyNo());
		companyName.setValue(dto.getCompanyName());
		companyType.setValue(dto.getCompanyType());
		companyCategory.setValue(dto.getCompanyCategory());
		tradeClass.setValue(dto.getTradeClass());
		companyNo.setEnabled(false);
		companyName.setEnabled(false);
		companyType.setEnabled(false);
		companyCategory.setEnabled(false);
		tradeClass.setEnabled(false);
	}

	private void init() {
		this.summaryTPDetails = new SummaryTPDetails(session, resultTable);
		this.summarySalesProjectionTransfer = new SummarySalesProjectionTransfer(resultTable, session);
	}

	private void addContent() {
		initializeTabs();
		initializeLazyTabLoad(tabLazyLoadMap, tabSheet.getComponentCount());
		buildScreen();

	}

	protected void initializeTabs() {
		tabSheet = new TabSheet();
		tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		tabSheet.setImmediate(true);
		try {
			tabSheet.addTab(summaryTPDetails, Constants.IndicatorConstants.TAB_SUMMARY_TP_DETAILS.getConstant(), null,
					0);
			tabSheet.addTab(summarySalesProjectionTransfer,
					Constants.IndicatorConstants.TAB_SALES_PROJECTION_TRANSFER.getConstant(), null, 1);

		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		attachTabChangeListener();

	}

	protected void initializeLazyTabLoad(final Map<Integer, Boolean> tabLazyLoadMap, final int componentCount) {
		tabLazyLoadMap.clear();
		for (int i = 0, tabCount = componentCount; i < tabCount; i++) {
			if (i == 1 || i == 0) {
				tabLazyLoadMap.put(i, Boolean.TRUE);
			} else {
				tabLazyLoadMap.put(i, Boolean.FALSE);
			}
		}

	}

	protected void buildScreen() {
		final VerticalLayout vLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		vLayout.addComponent(tabSheet);
		addComponent(vLayout);

	}

	private void attachTabChangeListener() {
		tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			@Override
			public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
				final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet()
						.getTab(event.getTabSheet().getSelectedTab());
				tabPosition = event.getTabSheet().getTabPosition(tab);
				String tabName = tabSheet.getTab(tabPosition).getCaption();
				try {
					if ("Sales Projection Transfer".equals(tabName)) {

						summarySalesProjectionTransfer.loadResultTable(false);
					}
				} catch (Exception ex) {
					LOGGER.error(ex);
				}
			}
		});
	}

        @Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		// empty
	}

	public void loadCurrentTPDetails() {
		summaryTPDetails.loadCurrentTradingPartnerDetails();
	}

	public void transferTPDetails() {
		summaryTPDetails.loadSelectedTradingPartner(session.getContractMasterSid(), session.getCompanyMasterSid());
	}

	public void loadSalesTransferData() {
		summarySalesProjectionTransfer.loadResultTable(false);
	}
}
