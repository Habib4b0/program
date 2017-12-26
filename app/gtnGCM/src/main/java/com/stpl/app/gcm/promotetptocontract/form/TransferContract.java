/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.gcm.promotetptocontract.dto.PromoteTpToChDto;
import com.stpl.app.gcm.promotetptocontract.logic.PromoteTPLogic;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.util.UiUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TreeTable;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author alok.v
 */
public class TransferContract extends VerticalLayout implements View {

	/**
	 * View name for navigation.
	 */
	public static final String NAME = StringUtils.EMPTY;
	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(TransferContract.class);
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	SessionDTO session;
	private TransferComponents transferComponents;
	private NewComponents newComponents;
	private ExistingComponents existingComponents;
	private TabSheet tabSheet;
	private final Map<Integer, Boolean> tabLazyLoadMap = new HashMap<>();
	private int tabPosition;
	@UiField("marketType")
	public ComboBox marketType;
	@UiField("type")
	public ComboBox type;
	@UiField("populateBtn1")
	public Button populateBtn1;
	@UiField(Constants.CONTRACT_HOLDER)
	public TextField contractHolder;
	@UiField("contractNo")
	public TextField contractNo;
	@UiField("contractName")
	public TextField contractName;
	@UiField("startDate")
	public PopupDateField startDate;
	@UiField("endDate")
	public PopupDateField endDate;
	@UiField("cfpName")
	public TextField cfpName;
	@UiField("cfpSDate")
	public PopupDateField cfpSDate;
	@UiField("cfpEDate")
	public PopupDateField cfpEDate;
	@UiField("number")
	public TextField number;
	@UiField("aliasStartDate")
	public PopupDateField aliasStartDate;
	@UiField("aliasEndDate")
	public PopupDateField aliasEndDate;
	@UiField("cfpNo")
	public TextField cfpNo;
	@UiField("cfpId")
	public TextField cfpId;
	@UiField("cfpStatus")
	public ComboBox cfpStatus;
	ExtFilterTable resultTable;
	PromoteTPLogic logic = new PromoteTPLogic();
	TreeTable contractDashBoardTable = new TreeTable();
	TreeTable contractDashBoardTable1 = new TreeTable();
	TreeTable contractDashBoardTable2 = new TreeTable();
	CommonUtil commonUtil = CommonUtil.getInstance();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DBDATE_FORMAT);
	PromoteTPLogic tpLogic = new PromoteTPLogic();
	public static final String ALREADY_EXIST_IN_THE_SYSTEM = " Already exist in the system.";

	public PopupDateField getStartDate() {
		return startDate;
	}

	public void setStartDate(PopupDateField startDate) {
		this.startDate = startDate;
	}

	public TransferContract() {

	}

	public TransferContract(SessionDTO session, ExtFilterTable resultTable) {
		try {
			this.session = session;
			this.resultTable = resultTable;
			addComponent(Clara.create(getClass().getResourceAsStream("/transferContract.xml"), this));
			init();
			addContent();
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	/**
	 * The init method
	 *
	 * @throws SystemException
	 * @throws Exception
	 */
	private void init() {
		this.transferComponents = new TransferComponents(session, contractDashBoardTable2);
		this.newComponents = new NewComponents(session, contractDashBoardTable1);
		this.existingComponents = new ExistingComponents(session, contractDashBoardTable);
	}

	public void enter(ViewChangeListener.ViewChangeEvent event) {
		// empty
	}

	private void addContent() {
		initializeTabs();
		initializeLazyTabLoad(tabLazyLoadMap, tabSheet.getComponentCount());
		buildScreen();
		configureFields();
		setContractHolder();

	}

	protected void initializeTabs() {
		tabSheet = new TabSheet();
		tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		try {
			tabSheet.addTab(transferComponents,
					Constants.IndicatorConstants.TAB_PROMOTE_TP_TRANSFER_COMPONENTS.getConstant(), null, 0);
			tabSheet.addTab(newComponents, Constants.IndicatorConstants.TAB_PROMOTE_TP_NEW_COMPONENTS.getConstant(),
					null, 1);
			tabSheet.addTab(existingComponents,
					Constants.IndicatorConstants.TAB_PROMOTE_TP_EXISTING_COMPONENTS.getConstant(), null,
					NumericConstants.TWO);

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
				try {
				} catch (Exception ex) {
					LOGGER.error(ex);
				}
			}
		});
	}

	protected void configureFields() {
		try {
			contractNo.setData(
					"maxlengthvalidationhundred,maxlengthvalidationcontractno,specialchar,specialcharcontractno");
			contractNo.setValidationVisible(true);

			contractName.setData(
					"maxlengthvalidationhundred,maxlengthvalidationcontractname,specialchar,specialcharcontractname");
			contractName.setValidationVisible(true);

			cfpName.setData("maxlengthvalidationhundred,maxlengthvalidationcfpname,specialchar,specialcharcfpname");
			cfpName.setValidationVisible(true);

			number.setData("maxlengthvalidationhundred,maxlengthvalidationnumber,specialchar,specialcharnumber");
			number.setValidationVisible(true);

			startDate.setDateFormat(Constants.DATE_FORMAT);
			endDate.setDateFormat(Constants.DATE_FORMAT);
			cfpSDate.setDateFormat(Constants.DATE_FORMAT);
			cfpEDate.setDateFormat(Constants.DATE_FORMAT);
			aliasStartDate.setDateFormat(Constants.DATE_FORMAT);
			aliasEndDate.setDateFormat(Constants.DATE_FORMAT);

			contractHolder.setEnabled(false);
			commonUtil.loadComboBox(marketType, UiUtils.CONTRACT_TYPE, false);
			marketType.setValidationVisible(true);

			commonUtil.loadComboBox(type, UiUtils.CONTRACT_ALIAS_TYPE, false);
			type.setValidationVisible(true);

			cfpStatus.setNullSelectionAllowed(true);
			cfpStatus.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
			cfpStatus.addItems(CommonLogic.getDropDownList("STATUS"));

		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	protected void setContractHolder() {
		PromoteTpToChDto dto = (PromoteTpToChDto) resultTable.getValue();
		contractHolder.setValue(dto.getCompanyName());
	}

	/**
	 * Populate button functionality
	 *
	 * @param event
	 * @throws ParseException
	 * @throws SystemException
	 */
	@UiHandler("populateBtn1")
	public void populateBtn1Logic(Button.ClickEvent event) {

		String cName = contractName.getValue();
		String cNo = contractNo.getValue();
		String sDate = startDate.getValue() != null ? simpleDateFormat.format(startDate.getValue()) : null;
		String eDate = startDate.getValue() != null ? simpleDateFormat.format(endDate.getValue()) : null;
		String mType = String.valueOf(marketType.getValue());
		String cHolder = String.valueOf(contractHolder.getValue());
		String compFamilyPlanName = String.valueOf(cfpName.getValue());
		String cfpStartDate = String.valueOf(cfpSDate.getValue());
		String cfpEndDate = String.valueOf(cfpEDate.getValue());
		String aliasType = String.valueOf(type.getValue());
		String aliasSDATE = String.valueOf(aliasStartDate.getValue());
		String aliasNumber = number.getValue();
		String aliasEDATE = String.valueOf(aliasEndDate.getValue());
		String cfpIdvalue = String.valueOf(cfpId.getValue());
		String cfpNovalue = String.valueOf(cfpNo.getValue());
		String cfpStatusValue = String.valueOf(cfpStatus.getValue());

		if (StringUtils.EMPTY.equals(cName) || StringUtils.EMPTY.equals(cNo) || Constants.NULL.equals(sDate)
				|| Constants.NULL.equals(eDate) || mType.equals(Constants.NULL) || StringUtils.EMPTY.equals(cHolder)
				|| StringUtils.EMPTY.equals(compFamilyPlanName) || Constants.NULL.equals(cfpStartDate)
				|| Constants.NULL.equals(cfpEndDate) || StringUtils.EMPTY.equals(cfpIdvalue)
				|| StringUtils.EMPTY.equals(cfpNovalue) || cfpStatusValue.equals(Constants.NULL)) {

			AbstractNotificationUtils.getErrorNotification(Constants.ERROR,
					"Please Enter All the Fields in Contract Header Section");

		} else {
			String cfpStDate = simpleDateFormat.format(cfpSDate.getValue());
			String cfpEnDate = simpleDateFormat.format(cfpEDate.getValue());

			Boolean contractNoFlag = tpLogic.duplicateCheck("CONTRACT_NO", cNo, "Contract");
			if (contractNoFlag) {
				AbstractNotificationUtils.getErrorNotification(Constants.ERROR,
						"Entered Contract No " + cNo + ALREADY_EXIST_IN_THE_SYSTEM);
				return;
			}
			Boolean contractNameFlag = tpLogic.duplicateCheck("CONTRACT_NAME", cName, "Contract");
			if (contractNameFlag) {
				AbstractNotificationUtils.getErrorNotification(Constants.ERROR,
						"Entered Contract Name " + cName + ALREADY_EXIST_IN_THE_SYSTEM);
				return;
			}
			Boolean cfpNameFlag = tpLogic.duplicateCheck("CFP_NAME", compFamilyPlanName, "cfp");
			if (cfpNameFlag) {
				AbstractNotificationUtils.getErrorNotification(Constants.ERROR,
						"Entered CFP Name " + compFamilyPlanName + ALREADY_EXIST_IN_THE_SYSTEM);
				return;
			}
			Boolean cfpIdFlag = tpLogic.duplicateCheck("CFP_ID", cfpIdvalue, "cfp");
			if (cfpIdFlag) {
				AbstractNotificationUtils.getErrorNotification(Constants.ERROR,
						"Entered CFP ID " + cfpIdvalue + ALREADY_EXIST_IN_THE_SYSTEM);
				return;
			}
			Boolean cfpNoFlag = tpLogic.duplicateCheck("CFP_NO", cfpNovalue, "cfp");
			if (cfpNoFlag) {
				AbstractNotificationUtils.getErrorNotification(Constants.ERROR,
						"Entered CFP NO " + cfpNovalue + ALREADY_EXIST_IN_THE_SYSTEM);
				return;
			}

			if (tabPosition == NumericConstants.TWO && contractDashBoardTable.getItemIds().isEmpty()) {
				final Object rootId = contractDashBoardTable.addItem();
				contractDashBoardTable.getContainerProperty(rootId, StringConstantsUtil.CATEGORY)
						.setValue(StringConstantsUtil.CONTRACT_HEADER);
				contractDashBoardTable.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_ID).setValue(cNo);
				contractDashBoardTable.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_NUMBER).setValue(cNo);
				contractDashBoardTable.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_NAME).setValue(cName);
				contractDashBoardTable.getContainerProperty(rootId, StringConstantsUtil.LEVEL_NO)
						.setValue(Constants.ZEROSTRING);
				contractDashBoardTable.getContainerProperty(rootId, Constants.MARKET_TYPE).setValue(mType);
				contractDashBoardTable.getContainerProperty(rootId, Constants.CONTRACT_HOLDER).setValue(cHolder);
				contractDashBoardTable.getContainerProperty(rootId, Constants.START_DATE).setValue(sDate);
				contractDashBoardTable.getContainerProperty(rootId, Constants.END_DATE).setValue(eDate);
				contractDashBoardTable.getContainerProperty(rootId, Constants.CFP_NAME).setValue(compFamilyPlanName);
				contractDashBoardTable.getContainerProperty(rootId, Constants.CFP_START_DATE).setValue(cfpStartDate);
				contractDashBoardTable.getContainerProperty(rootId, Constants.CFP_END_DATE).setValue(cfpEndDate);
				contractDashBoardTable.getContainerProperty(rootId, "type").setValue(aliasType);
				contractDashBoardTable.getContainerProperty(rootId, StringConstantsUtil.ALIAS_START_DATE)
						.setValue(aliasSDATE);
				contractDashBoardTable.getContainerProperty(rootId, StringConstantsUtil.NUMBER).setValue(aliasNumber);
				contractDashBoardTable.getContainerProperty(rootId, StringConstantsUtil.ALIAS_END_DATE)
						.setValue(aliasEDATE);
				contractDashBoardTable.addItem(rootId);

				contractDashBoardTable.addItem(rootId);
				contractDashBoardTable.setChildrenAllowed(rootId, true);

				Collection<?> list = contractDashBoardTable.getItemIds();
				for (Object obj : list) {
					Object ob = obj;
					final Object rootId1 = contractDashBoardTable.addItem();
					contractDashBoardTable.getContainerProperty(rootId1, StringConstantsUtil.CATEGORY)
							.setValue(Constants.CFP);
					contractDashBoardTable.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_ID)
							.setValue(cfpIdvalue);
					contractDashBoardTable.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_NUMBER)
							.setValue(cfpNovalue);
					contractDashBoardTable.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_NAME)
							.setValue(compFamilyPlanName);
					contractDashBoardTable.getContainerProperty(rootId1, StringConstantsUtil.LEVEL_NO).setValue("1");
					contractDashBoardTable.getContainerProperty(rootId1, Constants.CFP_START_DATE).setValue(cfpStDate);
					contractDashBoardTable.getContainerProperty(rootId1, Constants.CFP_END_DATE).setValue(cfpEnDate);
					contractDashBoardTable.getContainerProperty(rootId1, Constants.CFP_NAME)
							.setValue(compFamilyPlanName);
					contractDashBoardTable.getContainerProperty(rootId1, "id").setValue(cfpIdvalue);
					contractDashBoardTable.getContainerProperty(rootId1, StringConstantsUtil.CFP_NO)
							.setValue(cfpNovalue);
					HelperDTO statusDTO = (HelperDTO) cfpStatus.getValue();
					String cStatus = String.valueOf(statusDTO.getId());
					contractDashBoardTable.getContainerProperty(rootId1, StringConstantsUtil.CFP_STATUS)
							.setValue(cStatus);
					contractDashBoardTable.addItem(rootId1);
					contractDashBoardTable.addItem(rootId1);
					contractDashBoardTable.setParent(rootId1, ob);
					contractDashBoardTable.setChildrenAllowed(rootId1, true);
					contractDashBoardTable.setCollapsed(ob, false);

				}

			}
			if (tabPosition == 1 && contractDashBoardTable1.getItemIds().isEmpty()) {
				final Object rootId = contractDashBoardTable1.addItem();
				contractDashBoardTable1.getContainerProperty(rootId, StringConstantsUtil.CATEGORY)
						.setValue(StringConstantsUtil.CONTRACT_HEADER);
				contractDashBoardTable1.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_ID).setValue(cNo);
				contractDashBoardTable1.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_NUMBER)
						.setValue(cNo);
				contractDashBoardTable1.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_NAME)
						.setValue(cName);
				contractDashBoardTable1.getContainerProperty(rootId, StringConstantsUtil.LEVEL_NO)
						.setValue(Constants.ZEROSTRING);
				contractDashBoardTable1.getContainerProperty(rootId, Constants.MARKET_TYPE).setValue(mType);
				contractDashBoardTable1.getContainerProperty(rootId, Constants.CONTRACT_HOLDER).setValue(cHolder);
				contractDashBoardTable1.getContainerProperty(rootId, Constants.START_DATE).setValue(sDate);
				contractDashBoardTable1.getContainerProperty(rootId, Constants.END_DATE).setValue(eDate);

				contractDashBoardTable1.getContainerProperty(rootId, Constants.CFP_NAME).setValue(compFamilyPlanName);
				contractDashBoardTable1.getContainerProperty(rootId, Constants.CFP_START_DATE).setValue(cfpStartDate);
				contractDashBoardTable1.getContainerProperty(rootId, Constants.CFP_END_DATE).setValue(cfpEndDate);
				contractDashBoardTable1.getContainerProperty(rootId, "type").setValue(aliasType);
				contractDashBoardTable1.getContainerProperty(rootId, StringConstantsUtil.ALIAS_START_DATE)
						.setValue(aliasSDATE);
				contractDashBoardTable1.getContainerProperty(rootId, StringConstantsUtil.NUMBER).setValue(aliasNumber);
				contractDashBoardTable1.getContainerProperty(rootId, StringConstantsUtil.ALIAS_END_DATE)
						.setValue(aliasEDATE);
				contractDashBoardTable1.addItem(rootId);
				contractDashBoardTable1.setChildrenAllowed(rootId, true);

				Collection<?> list = contractDashBoardTable1.getItemIds();
				for (Object obj : list) {
					Object ob = obj;
					final Object rootId1 = contractDashBoardTable1.addItem();
					contractDashBoardTable1.getContainerProperty(rootId1, StringConstantsUtil.CATEGORY)
							.setValue(Constants.CFP);
					contractDashBoardTable1.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_ID)
							.setValue(cfpIdvalue);
					contractDashBoardTable1.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_NUMBER)
							.setValue(cfpNovalue);
					contractDashBoardTable1.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_NAME)
							.setValue(compFamilyPlanName);
					contractDashBoardTable1.getContainerProperty(rootId1, Constants.CFP_NAME)
							.setValue(compFamilyPlanName);
					contractDashBoardTable1.getContainerProperty(rootId1, StringConstantsUtil.LEVEL_NO).setValue("1");
					contractDashBoardTable1.getContainerProperty(rootId1, Constants.CFP_START_DATE).setValue(cfpStDate);
					contractDashBoardTable1.getContainerProperty(rootId1, Constants.CFP_END_DATE).setValue(cfpEnDate);
					contractDashBoardTable1.getContainerProperty(rootId1, "id").setValue(cfpIdvalue);
					contractDashBoardTable1.getContainerProperty(rootId1, StringConstantsUtil.CFP_NO)
							.setValue(cfpNovalue);
					HelperDTO statusDTO = (HelperDTO) cfpStatus.getValue();
					String cStatus = String.valueOf(statusDTO.getId());
					contractDashBoardTable1.getContainerProperty(rootId1, StringConstantsUtil.CFP_STATUS)
							.setValue(cStatus);
					contractDashBoardTable1.addItem(rootId1);
					contractDashBoardTable1.setParent(rootId1, ob);
					contractDashBoardTable1.setChildrenAllowed(rootId1, true);
					contractDashBoardTable1.setCollapsed(ob, false);

				}
			}

			if (tabPosition == 0 && contractDashBoardTable2.getItemIds().isEmpty()) {
				final Object rootId = contractDashBoardTable2.addItem();
				contractDashBoardTable2.getContainerProperty(rootId, StringConstantsUtil.CATEGORY)
						.setValue(StringConstantsUtil.CONTRACT_HEADER);
				contractDashBoardTable2.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_ID).setValue(cNo);
				contractDashBoardTable2.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_NUMBER)
						.setValue(cNo);
				contractDashBoardTable2.getContainerProperty(rootId, StringConstantsUtil.DASHBOARD_NAME)
						.setValue(cName);
				contractDashBoardTable2.getContainerProperty(rootId, StringConstantsUtil.LEVEL_NO)
						.setValue(Constants.ZEROSTRING);
				contractDashBoardTable2.getContainerProperty(rootId, Constants.MARKET_TYPE).setValue(mType);
				contractDashBoardTable2.getContainerProperty(rootId, Constants.CONTRACT_HOLDER).setValue(cHolder);
				contractDashBoardTable2.getContainerProperty(rootId, Constants.START_DATE).setValue(sDate);
				contractDashBoardTable2.getContainerProperty(rootId, Constants.END_DATE).setValue(eDate);

				contractDashBoardTable2.getContainerProperty(rootId, Constants.CFP_NAME).setValue(compFamilyPlanName);
				contractDashBoardTable2.getContainerProperty(rootId, Constants.CFP_START_DATE).setValue(cfpStartDate);
				contractDashBoardTable2.getContainerProperty(rootId, Constants.CFP_END_DATE).setValue(cfpEndDate);
				contractDashBoardTable2.getContainerProperty(rootId, "type").setValue(aliasType);
				contractDashBoardTable2.getContainerProperty(rootId, StringConstantsUtil.ALIAS_START_DATE)
						.setValue(aliasSDATE);
				contractDashBoardTable2.getContainerProperty(rootId, StringConstantsUtil.NUMBER).setValue(aliasNumber);
				contractDashBoardTable2.getContainerProperty(rootId, StringConstantsUtil.ALIAS_END_DATE)
						.setValue(aliasEDATE);
				contractDashBoardTable2.addItem(rootId);
				contractDashBoardTable2.setChildrenAllowed(rootId, true);

				Collection<?> list = contractDashBoardTable2.getItemIds();
				for (Object obj : list) {
					Object ob = obj;
					final Object rootId1 = contractDashBoardTable2.addItem();
					contractDashBoardTable2.getContainerProperty(rootId1, StringConstantsUtil.CATEGORY)
							.setValue(Constants.CFP);
					contractDashBoardTable2.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_ID)
							.setValue(cfpIdvalue);
					contractDashBoardTable2.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_NUMBER)
							.setValue(cfpNovalue);
					contractDashBoardTable2.getContainerProperty(rootId1, StringConstantsUtil.DASHBOARD_NAME)
							.setValue(compFamilyPlanName);
					contractDashBoardTable2.getContainerProperty(rootId1, StringConstantsUtil.LEVEL_NO).setValue("1");
					contractDashBoardTable2.getContainerProperty(rootId1, Constants.CFP_START_DATE).setValue(cfpStDate);
					contractDashBoardTable2.getContainerProperty(rootId1, Constants.CFP_END_DATE).setValue(cfpEnDate);
					contractDashBoardTable2.getContainerProperty(rootId1, Constants.CFP_NAME)
							.setValue(compFamilyPlanName);
					contractDashBoardTable2.getContainerProperty(rootId1, "id").setValue(cfpIdvalue);
					contractDashBoardTable2.getContainerProperty(rootId1, StringConstantsUtil.CFP_NO)
							.setValue(cfpNovalue);
					HelperDTO statusDTO = (HelperDTO) cfpStatus.getValue();
					String cStatus = String.valueOf(statusDTO.getId());
					contractDashBoardTable2.getContainerProperty(rootId1, StringConstantsUtil.CFP_STATUS)
							.setValue(cStatus);
					contractDashBoardTable2.addItem(rootId1);
					contractDashBoardTable2.setParent(rootId1, ob);
					contractDashBoardTable2.setChildrenAllowed(rootId1, true);
					contractDashBoardTable2.setCollapsed(ob, false);

				}
			}

		}

	}

	public void loadContractCompResultsTable() {
		transferComponents.loadCurrentComponentResults();
	}

	public void loadTransferComponenttable(ExtPagedTable table) {
		BeanItemContainer<CurrentContractDTO> transferCompContainer1 = transferComponents.getTransferCompContainer1();
		List<CurrentContractDTO> selectedList = new ArrayList<>();
		Collection list = table.getItemIds();
		for (final Iterator<Object> iterator = list.iterator(); iterator.hasNext();) {
			final Object item = iterator.next();
			Boolean checked = (Boolean) table.getContainerProperty(item, Constants.CHECK_RECORD).getValue();
			if (checked) {
				CurrentContractDTO selectedDto = (CurrentContractDTO) item;
				CurrentContractDTO dto = new CurrentContractDTO();
				dto.setContractName(selectedDto.getContractId());
				dto.setContractNo(selectedDto.getContractNo());
				dto.setMarketType(selectedDto.getMarketType());
				dto.setStartDate(selectedDto.getStartDate());
				if (!selectedDto.getIfpContSid().equals(StringUtils.EMPTY)) {
					dto.setIFPId(Integer.valueOf(selectedDto.getIfpContSid()));
				} else {
					dto.setIFPId(0);
				}
				if (!selectedDto.getPsContSid().equals(StringUtils.EMPTY)) {
					dto.setPSId(Integer.valueOf(selectedDto.getPsContSid()));
				} else {
					dto.setPSId(0);
				}

				if (!selectedDto.getRsContSid().equals(StringUtils.EMPTY)) {
					dto.setRSId(Integer.valueOf(selectedDto.getRsContSid()));
				} else {
					dto.setRSId(0);
				}
				selectedList.add(dto);
			}
		}
		transferCompContainer1.addAll(selectedList);
	}

	public void saveContract() {
		CommonLogic logic = new CommonLogic();
		if (tabPosition == 0) {
			try {
				List<Integer> list = transferComponents.saveTransferContract();
				if (list != null && list.size() > 0) {
					String.valueOf(list.get(0));
				}
				logic.callCcpInsertProcedure();
				logic.callActualsDetailsInsertProcedure();
			} catch (SystemException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			} catch (PortalException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ParseException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (tabPosition == NumericConstants.TWO) {
			try {
				existingComponents.saveExistingContract();
			} catch (SystemException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			} catch (PortalException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ParseException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (tabPosition == 1) {
			try {
				newComponents.saveNewContract();
			} catch (SystemException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			} catch (PortalException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ParseException ex) {
				java.util.logging.Logger.getLogger(TransferContract.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
