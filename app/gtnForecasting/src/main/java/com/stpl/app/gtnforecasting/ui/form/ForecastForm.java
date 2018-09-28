package com.stpl.app.gtnforecasting.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.abstractforecast.AbstractForm;
import com.stpl.app.gtnforecasting.additionalinformation.form.AdditionalInformationForm;
import com.stpl.app.gtnforecasting.bpm.logic.DSCalculationLogic;
import com.stpl.app.gtnforecasting.bpm.logic.VarianceCalculationLogic;
import com.stpl.app.gtnforecasting.bpm.persistance.WorkflowPersistance;
import com.stpl.app.gtnforecasting.bpm.util.MessageUtils;
import com.stpl.app.gtnforecasting.dataassumptions.form.DataAssumptions;
import com.stpl.app.gtnforecasting.dataassumptions.logic.DataAssumptionsLogic;
import com.stpl.app.gtnforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.gtnforecasting.discountprojectionresults.form.NMDiscountProjectionResults;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.ppaprojection.form.PPAProjection;
import com.stpl.app.gtnforecasting.pparesults.form.PPAProjectionResults;
import com.stpl.app.gtnforecasting.projectionresults.form.NMProjectionResults;
import com.stpl.app.gtnforecasting.projectionvariance.form.NMProjectionVariance;
import com.stpl.app.gtnforecasting.salesprojection.form.NMSalesProjection;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.salesprojectionresults.form.NMSalesProjectionResults;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastEditWindow;
import com.stpl.app.gtnforecasting.ui.ForecastMainView;
import com.stpl.app.gtnforecasting.ui.ForecastWindow;
import com.stpl.app.gtnforecasting.ui.NonMandatedViewWindow;
import com.stpl.app.gtnforecasting.ui.form.lookups.WorkFlowNotesLookup;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.DataSelectionUtil;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.gtnforecasting.workflow.dto.WorkflowMasterDTO;
import com.stpl.app.gtnforecasting.workflow.logic.WorkflowLogic;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.util.service.thread.ThreadPool;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_NEXT;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_PREVIOUS;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_REFRESH_UPDATE;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_TIME_PERIOD_CHANGED;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM_CATEGORY;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_ADDITIONAL_INFORMATION;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DATA_ASSUMPTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DATA_SELECTION;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DISCOUNT_PROJECTION;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DISCOUNT_PROJECTION_RESULTS;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_PPA_PROJECTION_RESULTS;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_PROJECTION_RESULTS;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_PROJECTION_VARIANCE;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_SALES_PROJECTION;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_SALES_PROJECTION_RESULTS;
import static com.stpl.app.utils.Constants.WindowMessagesName.CONFIRMATION;
import com.stpl.app.utils.TableHeaderColumnsUtil;
import com.stpl.app.utils.UiUtils;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.MinimizeTray;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Contains and Controls the tabsheet containing all the screen.
 *
 * @author soundarrajan
 */
public class ForecastForm extends AbstractForm {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForecastForm.class);
        
        

	/**
	 * The data.
	 */
	private DataSelection data;
	/**
	 * Object for Sales Projection Tab.
	 */
	private NMSalesProjection nmSalesProjection;
	/**
	 * Object for Sales Projection Tab.
	 */
	/**
	 * Object for Sales Projection Tab.
	 */
	private NMSalesProjectionResults salesProjectionResults;
	/**
	 * Object for Sales Projection Tab.
	 */
	/**
	 * Object for Discount Projection Tab.
	 */
	private NMDiscountProjection discountProjection;
	/**
	 * Object for Discount Projection Results Tab.
	 */
	private NMDiscountProjectionResults discountProjectionResults;
	/**
	 * Object for Discount Projection Results Tab.
	 */
	/**
	 * Object for PPA Projection Tab.
	 */
	private PPAProjection ppaProjection;
	/**
	 * Object for PPA Projection Results Tab.
	 */
	private PPAProjectionResults ppaProjectionResults;
	/**
	 * Object for Projection Results Tab.
	 */
	private NMProjectionResults nonmandatedprojectionResults;
	/**
	 * Object for Projection Variance Tab.
	 */
	private NMProjectionVariance projectionVariance;
	/**
	 * Object for Projection Variance Tab.
	 */
	/**
	 * Object for Additional Information Tab.
	 */
	private AdditionalInformationForm additionalInformation;
	/**
	 * Object for Additional Information Tab.
	 */
	/**
	 * Object for Additional Information Tab.
	 */
	private DataAssumptions dataAssumptions;
	/**
	 * Tabsheet containing all the screens.
	 */
	private TabSheet tabSheet;
	/**
	 * Map for lazy loading.
	 */
	private final Map<Integer, Boolean> tabLazyLoadMap = new HashMap<>();
	private final Map<String, Boolean> pushMap = new HashMap<>();
	private final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
	/**
	 * Position of the tab.
	 */
	private int tabPosition;
	private CustomFieldGroup dataSelectionBinder;
	private DataSelectionDTO dataSelectionDTO;
	private SessionDTO session;
	private ForecastEditWindow editWindow;
	private ForecastWindow forecastWindow;
	private int lastPosition;
	private Button btnNextInForm = new Button(BTN_NEXT.getConstant());
	private Button btnPrevInForm = new Button(BTN_PREVIOUS.getConstant());
	private Button btnRefresh = new Button("REFRESH");
	private ExtFilterTable resultTable;
	private NonMandatedViewWindow viewWindow;
	private int tempTabPosition = 0;
	private boolean dsFlag = true;
	private String screenName;
	private final DataSelectionForm dataSelectionForm;
	private DataSelectionLogic dsLogic = new DataSelectionLogic();
	private NonMandatedLogic logic = new NonMandatedLogic();
	private DataAssumptionsLogic dataAssumption = new DataAssumptionsLogic();
	private static final ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
	private boolean discountFlag = true;
	private Thread dsThread;
	private CountDownLatch latch;
	private boolean isCommercialGovernment = BooleanConstant.getFalseFlag();
	private ExecutorService service = ThreadPool.getInstance().getService();
        private boolean discountLoadFlag = true;
        private boolean discountUpsFlag = false;
        private static final CommonUtil commUtil = CommonUtil.getInstance();
      
	public ForecastForm(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, SessionDTO session,
			ForecastEditWindow editWindow, final ExtFilterTable resultTable, final String screenName,
			final DataSelectionForm dataSelectionForm, ForecastWindow forecastWindow) {

		this.dataSelectionBinder = dataSelectionBinder;
		this.dataSelectionDTO = dataSelectionDTO;
		this.session = session;
		this.editWindow = editWindow;
		if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
			isCommercialGovernment = BooleanConstant.getTrueFlag();
			this.forecastWindow = forecastWindow;
		}
		this.resultTable = resultTable;
		this.screenName = screenName;
		this.dataSelectionForm = dataSelectionForm;
		try {
			if (Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
				checkForActualSales();
			} else {
				init();
				addContent();
				if (Constant.EDIT.equalsIgnoreCase(session.getAction())) {
					getBtnSave().setCaption("UPDATE");
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
        
	private void forecastDTOConfiguration() {
		DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
	}

	private void init() throws PortalException {

		session.setPpaIndicator(true);
		forecastDTOConfiguration();
		this.data = new DataSelection(dataSelectionBinder, dataSelectionDTO, session, this, screenName,
				dataSelectionForm);

		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			session.setMaximumCustomerLevel(dsLogic.getMaximumLevelNo(session.getHierarchyLevelDetails(),
					Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY));
			session.setMaximumProductLevel(dsLogic.getMaximumLevelNo(session.getHierarchyLevelDetails(),
					Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY));
                    if (Constant.VIEW.equalsIgnoreCase(session.getAction())) {
                        dataSelectionDTO.setForecastEligibleDate(dsLogic.getWorkflowEligibleDateFromProjMaster(dataSelectionDTO));
                    }
			commercialConfiguration();
			dataAssumption.setSession(this.session);
			session.setDataAssumptionLogic(dataAssumption);
			dataAssumption.getLatestFilesList();
			dataAssumption.isSalesCalculatedAlready();
				if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction())) {
					discountFlag = false;
				}
			this.salesProjectionResults = new NMSalesProjectionResults(session, screenName);
			this.nmSalesProjection = new NMSalesProjection(session, screenName);
			this.dataAssumptions = new DataAssumptions(session);
                        this.discountProjection = new NMDiscountProjection(session, screenName);
			this.discountProjectionResults = new NMDiscountProjectionResults(session, screenName, this);
			this.ppaProjectionResults = new PPAProjectionResults(session);
			this.projectionVariance = new NMProjectionVariance(this, session, screenName);
			this.nonmandatedprojectionResults = new NMProjectionResults(session, screenName, this);
			this.additionalInformation = new AdditionalInformationForm(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED,
					session.getProjectionId(), session.getAction());

		}
	}
        
	/**
	 * Adds the content to the screen.
	 *
	 * @throws Exception
	 * @throws SystemException
	 */
	   private void addContent() throws  PortalException {
        initializeTabs();
        initializeLazyTabLoad(tabLazyLoadMap, tabSheet.getComponentCount());
        buildScreen();

        if (session != null && (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction())
                || Constant.VIEW.equalsIgnoreCase(session.getAction())) && session.getWorkflowId() != 0) {

            if (WorkflowConstants.getApproverConstant().equals(session.getWorkflowUserType())) {

                getBtnWithdraw().setVisible(false);
                getBtnSubmit().setVisible(false);

                boolean buttonVisiblity = WorkflowConstants.getPendingStatus()
                        .equalsIgnoreCase(session.getWorkflowStatus());
                getBtnApprove().setVisible(buttonVisiblity);
                getBtnReject().setVisible(buttonVisiblity);
                getBtnCancel().setVisible(buttonVisiblity);

            } else if (WorkflowConstants.getCreatorConstant().equals(session.getWorkflowUserType())) {

                getBtnApprove().setVisible(false);
                getBtnReject().setVisible(false);
                getBtnCancel().setVisible(false);

                if (WorkflowConstants.getPendingStatus().equalsIgnoreCase(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(true);
                    getBtnSubmit().setVisible(false);
                    getBtnCancel().setVisible(false);
                } else if (WorkflowConstants.getWithdrawnStatus().equalsIgnoreCase(session.getWorkflowStatus())
                        || WorkflowConstants.getRejectedStatus().equalsIgnoreCase(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(false);
                    getBtnSubmit().setVisible(true);
                    getBtnCancel().setVisible(false);
                } else if (WorkflowConstants.getApprovedStatus().equals(session.getWorkflowStatus())
                        || WorkflowConstants.getCancelledStatus().equals(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(false);
                    getBtnSubmit().setVisible(false);
                       }
                   }
               } else {
                   getBtnApprove().setVisible(false);
                   getBtnReject().setVisible(false);
                   getBtnWithdraw().setVisible(false);
                   getBtnCancel().setVisible(false);
               }

               getBtnSubmit().setEnabled(true);
               configureForView();
               setTabSecurity();
               setButtonSecurity();
               if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                   tabSheet.setSelectedTab(2);
                   
               } else {
                   tabSheet.setSelectedTab(1);
               }
    }

	protected void initializeLazyTabLoad(final Map<Integer, Boolean> tabLazyLoadMap, final int componentCount) {
		tabLazyLoadMap.clear();
		for (int i = 0, tabCount = componentCount; i < tabCount; i++) {
			if (i == 1 || i == 0) {
				tabLazyLoadMap.put(i, Boolean.TRUE);
			} else {
				tabLazyLoadMap.put(i, BooleanConstant.getFalseFlag());
			}
		}
		pushMap.put(INDICATOR_REFRESH_UPDATE.getConstant(), BooleanConstant.getFalseFlag());
		pushMap.put(INDICATOR_TIME_PERIOD_CHANGED.getConstant(), BooleanConstant.getFalseFlag());

	}

	/**
	 * To initialize and configure the tabLazyLoadMap for lazy tab loading.
	 */
	@Override
	protected void initializeTabs() {
		tabSheet = new TabSheet();
		tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		
		try {
			tabSheet.addTab(data, TAB_DATA_SELECTION.getConstant(), null, 0);
			if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				tabSheet.addTab(dataAssumptions, TAB_DATA_ASSUMPTIONS.getConstant(), null, 1);
				tabSheet.addTab(nmSalesProjection, TAB_SALES_PROJECTION.getConstant(), null, NumericConstants.TWO);
				tabSheet.addTab(salesProjectionResults, TAB_SALES_PROJECTION_RESULTS.getConstant(), null,
						NumericConstants.THREE);
				tabSheet.addTab(discountProjection, TAB_DISCOUNT_PROJECTION.getConstant(), null, NumericConstants.FOUR);
				tabSheet.addTab(discountProjectionResults, TAB_DISCOUNT_PROJECTION_RESULTS.getConstant(), null,
						NumericConstants.FIVE);
				tabSheet.addTab(ppaProjectionResults, TAB_PPA_PROJECTION_RESULTS.getConstant(), null,
						NumericConstants.SIX);
				tabSheet.addTab(nonmandatedprojectionResults, TAB_PROJECTION_RESULTS.getConstant(), null,
						NumericConstants.SEVEN);
				tabSheet.addTab(projectionVariance, TAB_PROJECTION_VARIANCE.getConstant(), null,
						NumericConstants.EIGHT);
				tabSheet.addTab(additionalInformation, TAB_ADDITIONAL_INFORMATION.getConstant(), null,
						NumericConstants.NINE);
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		attachTabChangeListener();
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)
				&& tabSheet.getTab(tabPosition).isVisible()) {
			ppaProjectionResults.setVisible(session.isCidtIndicator());
		}
	}

	private void attachTabChangeListener() {
		tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			@Override
			public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
				boolean checkSalesFlag = false;
				final Tab tab = (Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
				tabPosition = event.getTabSheet().getTabPosition(tab);
				buttonEnableLogic(tabPosition, tabSheet.getComponentCount() - 1);
				                    try {
                            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                                session.setTabNameCaption(tabSheet.getTab(tabPosition).getCaption());
                                if (tabPosition == 0) {
                                    session.getFutureValue(Constant.DATA_SELECTION_TAB_LOAD, 0).get();
                                    data.setFrequency(session);
                                }
                                checkSalesFlag = checkLastPositionTab(tabPosition);
                                if (checkSalesFlag) {
                                    commUtil
                                            .waitsForOtherThreadsToComplete(session.getFutureValue(Constant.FILE_INSERT)[0]);
                                }
                                if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == NumericConstants.THREE) {
                                    salesProjectionResults.defaultFocus();
                                } else if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == NumericConstants.FIVE) {
                                    discountProjectionResults.defaultFocus();
                                }
                                if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == NumericConstants.SEVEN) {
                                    nonmandatedprojectionResults.defaultFocus();
                                }
                                if (tabPosition == NumericConstants.TWO) {
                                    session.setIsDeductionCustom(false);
                                    if (nmSalesProjection.isValueChange() && !nmSalesProjection.isRefresh()) {
                                        nmSalesProjection.generateBtnLogic(null);
                                    }
                                }
                                if (discountLoadFlag && (tabPosition == NumericConstants.FOUR || tabPosition == NumericConstants.EIGHT)){
                                    commUtil.isProcedureCompleted("Discount", "PRC_NM_MASTER_INSERT", session);
                                    session.addFutureMap(Constant.CUST_VIEW_MAP_QUERY,
				new Future[] {service.submit(commUtil.createRunnable(Constant.CUST_VIEW_MAP_QUERY,session))});
                                    discountLoadFlag = false;
                                }
                                
                                if (!discountUpsFlag && tabPosition == NumericConstants.FOUR){
                                      CommonLogic.viewProceduresCompletionCheckDiscount(session);
                                    if (!session.getAction().equalsIgnoreCase(Constant.VIEW)) {
                                    callContractDetailsPrcForDiscount();
                                    } 
                                    session.setFunctionMode("UPS");
                                    nmDiscountViewsPopulationProcedure();
                                     discountProjection.getContent();                                     
                                     discountUpsFlag=true;
                                }
                                if(tabPosition == NumericConstants.TWO){
                                   nmSalesProjection.checkSpFrequency();
                                }
                                if(tabPosition == NumericConstants.FOUR){
                                   discountProjection.checkFrequencyChange(); 
                                }
                            
                                    if (nmSalesProjection.isSalesValueChange()) {
                                    CommonLogic.viewProceduresCompletionCheckDiscount(session);
                                    session.setFunctionMode("UPS");
                                    nmDiscountViewsPopulationProcedure();  
                                    }
                                if (tabPosition == NumericConstants.FOUR || tabPosition == NumericConstants.FIVE
                                        || tabPosition == NumericConstants.EIGHT) {
                                    session.setIsDeductionCustom(true);
                                    discountProjection.setFrequency(session);
                                }
                                onTabChange(tabPosition);
                                   
                            }
                            if (checkSalesFlag) {
                                nmSalesProjection.setFrequency(session);
                            }
                        } catch (InterruptedException | ExecutionException ex) {
                            LOGGER.error(ex.getMessage());
                        }
                    }
		});
	}

	protected void buttonEnableLogic(int tabPosition, int i) {
		if (tabPosition != 0) {
			btnPrevInForm.setVisible(true);
		} else {
			btnPrevInForm.setVisible(false);
		}
		if (tabPosition == i) {
			btnNextInForm.setVisible(false);
			btnRefresh.setVisible(true);

		} else {
			btnNextInForm.setVisible(true);
			btnRefresh.setVisible(false);
		}
		if (tabPosition <= i) {
			btnPrevInForm.setEnabled(true);
		}
		if (tabPosition == NumericConstants.NINE && screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                        btnRefresh.setVisible(false);
		}
	}

	/**
	 * Adds the tabsheet and footer buttons to the screen
	 */
	@Override
	protected void buildScreen() {

		final VerticalLayout vLayout = (VerticalLayout) UiUtils.getLayout(new VerticalLayout());
		vLayout.addComponent(tabSheet);
		vLayout.addComponent(addFooterButtons(btnNextInForm, btnPrevInForm, btnRefresh));
		configureFields();
		addComponent(vLayout);
		session.setSaveFlag(false);
		session.setSubmitFlag(false);

	}

	/**
	 * Configure fields.
	 */
	private void configureFields() {
		
		btnPrevInForm.addClickListener(new Button.ClickListener() {
                        @Override
			public void buttonClick(Button.ClickEvent event) {
				btnPreviousLogic();
			}
		});
		btnNextInForm.addClickListener(new Button.ClickListener() {
                        @Override
			public void buttonClick(Button.ClickEvent event) {

				if ((data.isUpdateOnTabChange() || data.isCustomChange())) {
					if (!data.isDataSelectionValid()) {
						data.setUpdateOnTabChange(true);
						dsFlag = true;
						AbstractNotificationUtils.getErrorNotification(Constant.SELECTION_CRITERIA_HEADER,
								Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
						return;
					} else {
						new AbstractNotificationUtils() {
							@Override
							public void noMethod() {
								data.configureOnTabLoad(session.getProjectionId(), dataSelectionDTO);
							}

							@Override
							public void yesMethod() {
								try {
									data.updateDataSelection();
										session.setTradingPartner(
												CommonLogic.getTradingPartnerLevelNo(false, session.getProjectionId()));
									pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
									if (session.isFromDateChanged()) {
										DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
										pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
										session.setFromDateChanged(false);
									}
									btnNextLogic();
									if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                                                                           
										 nmSalesProjection.init();
                                                                                 nmSalesProjection.getViewDdlb().select(session.getCustomRelationShipSid());
                                                                                 discountProjection.getViewDdlb().select(session.getCustomDeductionRelationShipSid());
									}
								} catch (PortalException | SystemException | IOException | ClassNotFoundException ex) {
									LOGGER.error(StringUtils.EMPTY,ex);
								}

							}
						}.getConfirmationMessage(Constant.UPDATE_CONFIRMATION_ALERT,
								Constant.DATA_SELECTION_VALUES_HAVE_CHANGED);
                                                data.setCustomChange(BooleanConstant.getFalseFlag());
                                                data.setDedCustomChange(BooleanConstant.getFalseFlag());
                                                data.setUpdateOnTabChange(BooleanConstant.getFalseFlag());
					}

					data.setUpdateOnTabChange(BooleanConstant.getFalseFlag());
				} else {
					btnNextLogic();
				}
			}
		});
	}

	/**
	 * Called on tab change.
	 *
	 * @param tabPosition
	 *            the tab position
	 */
	@Override
	protected void onTabChange(int tabPosition) {
		LOGGER.debug("onTabChange starts");
		try {

			if ((lastPosition == data.getTabNumber()) && ((data.isUpdateOnTabChange() || data.isCustomChange()) && dsFlag)) {
				dsFlag = false;
				tempTabPosition = tabPosition;
				tabSheet.setSelectedTab(0);
				new AbstractNotificationUtils() {
					@Override
					public void yesMethod() {
						try {
							if (data.isDataSelectionValid()) {
								data.updateDataSelection();
								session.setTradingPartner(
										CommonLogic.getTradingPartnerLevelNo(false, session.getProjectionId()));
								pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
								if (session.isFromDateChanged()) {
									DataSelectionUtil.getForecastDTO(dataSelectionDTO, session);
									pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
									session.setFromDateChanged(false);
								}
                                                                data.setCustomChange(BooleanConstant.getFalseFlag());
                                                                data.setDedCustomChange(BooleanConstant.getFalseFlag());
								tabSheet.setSelectedTab(tempTabPosition);
								dsFlag = true;
								discountFlag = true;
								nmSalesProjection.init();
                                                                nmSalesProjection.getViewDdlb().select(session.getCustomRelationShipSid());
								discountProjection.getContent();
                                                                discountProjection.getViewDdlb().select(session.getCustomDeductionRelationShipSid());
							} else {
								Tab tabToReset = tabSheet.getTab(2);
								tabSheet.removeTab(tabToReset);
								tabSheet.addTab(nmSalesProjection, Constant.SALES_PROJECTION, null, 2);
								dsFlag = true;
								data.setUpdateOnTabChange(BooleanConstant.getTrueFlag());
								tabSheet.setSelectedTab(0);
								lastPosition = 0;
								AbstractNotificationUtils.getErrorNotification(Constant.SELECTION_CRITERIA_HEADER,
										Constant.NOT_ALL_REQUIRED_FIELDS_POPULATED);
							}
						} catch (PortalException | SystemException | IOException | ClassNotFoundException ex) {
							LOGGER.error(ex.getMessage());
						}
					}

					@Override
					public void noMethod() {

						Tab tabToReset = tabSheet.getTab(2);
						tabSheet.removeTab(tabToReset);
						tabSheet.addTab(nmSalesProjection, Constant.SALES_PROJECTION, null, 2);
						dsFlag = true;
						try {

							data.configureOnTabLoad(session.getProjectionId(), dataSelectionDTO);
						} catch (Exception ex) {
							LOGGER.error(ex.getMessage());
						}
						tabSheet.setSelectedTab(0);
						lastPosition = 0;
                                                data.setCustomChange(BooleanConstant.getFalseFlag());
                                                data.setDedCustomChange(BooleanConstant.getFalseFlag());
					}
				}.getConfirmationMessage(Constant.UPDATE_CONFIRMATION_ALERT,
						Constant.DATA_SELECTION_VALUES_HAVE_CHANGED);
				data.setReloadAfterUpdate(BooleanConstant.getTrueFlag());
				data.setUpdateOnTabChange(BooleanConstant.getFalseFlag());
			}

			/**
			 * Saving Projection selection for projection variance data load
			 *
			 */
			if (lastPosition == discountProjection.getTabNumber()) {
				discountProjection.saveSelections();
			}
			session.setIsProgramCategory(PROGRAM_CATEGORY.getConstant().equals(discountProjection.getDiscountType()));
			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == NumericConstants.THREE) {
				salesProjectionResults.configure();
			}
			if (tabPosition == discountProjection.getTabNumber() && isListViewWithDiscountFlag()) {
                            discountProjection.configure();
                            discountProjection.saveDiscountProjectionScreen(false);
                            discountFlag = false;
                            discountProjection.setFrequency(session);
			}
			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == nonmandatedprojectionResults.getTabNumber()
					&& tabLazyLoadMap.get(nonmandatedprojectionResults.getTabNumber())) {
				session.setPpaIndicator(true);
				nonmandatedprojectionResults.loadGroupFilterOntabChange();
			}
			if (tabSheet.getTab(tabPosition).isVisible()
					&& tabPosition == nonmandatedprojectionResults.getTabNumber()) {
				nonmandatedprojectionResults.configure();
			}
			if (!tabLazyLoadMap.get(tabPosition)) {
				detachListeners(tabSheet);
				lazyLoadTab(tabPosition);
				tabLazyLoadMap.put(tabPosition, Boolean.TRUE);
				attachTabChangeListener();
			}
			if (tabPosition == projectionVariance.getTabNumber()
					&& tabLazyLoadMap.get(projectionVariance.getTabNumber())) {
				projectionVariance.configure();
				projectionVariance.loadGroupFilterOntabChange();
                                projectionVariance.checkPvFrequency(); 
                                projectionVariance.setFrequency(session);
			}
                         if(tabPosition == NumericConstants.EIGHT){
                                   projectionVariance.checkPvFrequency(); 
                                }
			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == discountProjectionResults.getTabNumber()
					&& tabLazyLoadMap.get(discountProjectionResults.getTabNumber())) {
				discountProjectionResults.configure();
				// To make the discount projection insert procedure to wait
				commUtil
						.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.DISCOUNT_PROCEDURE_CALL, 0));
				discountProjectionResults.loadGroupFilter();
			}
			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == ppaProjectionResults.getTabNumber()
					&& session.isIsSalesCalculated()) {
				nmPPAInitProcedure();
			}
			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == salesProjectionResults.getTabNumber()) {
				session.setSprRefreshReqd(true);
			}
			push(tabPosition);

			lastPosition = tabPosition;
		} catch (PortalException | SystemException ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.debug("onTabChange ends");
	}

    private boolean isListViewWithDiscountFlag() {
        return discountProjection.isListviewGenerated() && discountFlag;
    }

	/**
	 * To implement tabsheet lazy loading.
	 *
	 * @param tabPosition
	 *            current position of the tab
	 */
	@Override
	protected void lazyLoadTab(final int tabPosition) {
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == salesProjectionResults.getTabNumber()) {
				tabSheet.replaceComponent(salesProjectionResults, salesProjectionResults);
				salesProjectionResults.setProjectionSelection();
			} else if (tabPosition == discountProjection.getTabNumber()) {
				discountProjection.getContent();
				tabSheet.replaceComponent(discountProjection, discountProjection);
			} else if (tabSheet.getTab(tabPosition).isVisible()
					&& tabPosition == discountProjectionResults.getTabNumber()) {
				tabSheet.replaceComponent(discountProjectionResults, discountProjectionResults);
				discountProjectionResults.setProjectionSelection();
			} else if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == ppaProjectionResults.getTabNumber()) {
				tabSheet.replaceComponent(ppaProjectionResults, ppaProjectionResults.getContent());
			} else if (tabSheet.getTab(tabPosition).isVisible()
					&& tabPosition == nonmandatedprojectionResults.getTabNumber()) {
				tabSheet.replaceComponent(nonmandatedprojectionResults, nonmandatedprojectionResults);

			} else if (tabPosition == projectionVariance.getTabNumber()) {
				tabSheet.replaceComponent(projectionVariance, projectionVariance);
				projectionVariance.setProjectionSelection(false);
			} else if (tabPosition == NumericConstants.NINE) {
				tabSheet.replaceComponent(additionalInformation, additionalInformation);
				try {

					additionalInformation.documentExporter();
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
				}
			}
		}

	}

	public void detachListeners(TabSheet tabsheet) {
		List<TabSheet.SelectedTabChangeListener> listeners;

		listeners = (List<TabSheet.SelectedTabChangeListener>) tabsheet
				.getListeners(TabSheet.SelectedTabChangeEvent.class);

		for (TabSheet.SelectedTabChangeListener object : listeners) {
			tabsheet.removeSelectedTabChangeListener(object);

		}

	}

	/**
	 * Logic to save the projection.
	 */
	@Override
	protected void btnSaveLogic() {
             if (NonMandatedLogic.isProjectionSavedSuccessFully(session)){
                 return;
             }
		MessageBox.showPlain(Icon.QUESTION, "Save Confirmation", "Are you sure you want to save the projection?",
				new MessageBoxListener() {
                                        @Override
					public void buttonClicked(ButtonId buttonId) {
						if (buttonId.name().equals(Constant.YES)) {
							try {
                                                                session.setProjectionName(data.getProjectionName());
                                                                session.setDescription(data.getProjectionDescription());
                                                                dsLogic.updateProjectionNameAndProjectionDescription(session);
								saveProjection(true);
								final Notification notif = new Notification("For Projection "+
										session.getProjectionName() + " ,Save has been successfully Intiated",
 										Notification.Type.HUMANIZED_MESSAGE);
								notif.setPosition(Position.TOP_CENTER);
								notif.setStyleName(ConstantsUtils.MY_STYLE);
								notif.show(Page.getCurrent());
								btnSave.setCaption("UPDATE");
								session.setAction(Constant.EDIT_SMALL);
							} catch (Exception ex) {
								LOGGER.error(ex.getMessage());
							}
						}
					}
				}, ButtonId.YES, ButtonId.NO);
	}

	/**
	 * Moves to previous tab.
	 */
	protected void btnPreviousLogic() {
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			int i = 1;
			while ((lastPosition - i) > 0 && !tabSheet.getTab(lastPosition - i).isVisible()) {
				i++;
			}
			if (lastPosition - i == 0) {
				waitForThread(dsThread);
			}
			if (isCommercialGovernment && lastPosition - i == 0) {
				commUtil
						.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.DATA_SELECTION_TAB_LOAD, 0));
			}
			tabSheet.setSelectedTab(lastPosition - i);
		} else {
			if (tabPosition != Constant.ZERO) {
				tabSheet.setSelectedTab(tabPosition - 1);
			}
		}
	}

	/**
	 * Moves to next tab.
	 */
	protected void btnNextLogic() {

		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			int i = 1;
			while ((lastPosition + i) < tabSheet.getComponentCount()
					&& !tabSheet.getTab(lastPosition + i).isVisible()) {
				i++;
			}
			tabSheet.setSelectedTab(lastPosition + i);
		} else {
			tabSheet.setSelectedTab(tabPosition + 1);
		}

		UI.getCurrent().setFocusedComponent(UI.getCurrent());
	}

	/**
	 * Closes the projection.
	 */
	@Override
	protected void btnCloseLogic() {
		String msgTitle;
		String msgContent;
		if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction())
				|| Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
			msgTitle = "Save?";
				msgContent = "Changes have been made to the projection. Would you like to save the changes before closing?";
		} else {
			msgTitle = Constant.CLOSE_CONFIRMATION;
			msgContent = "Are you sure you want to close this Projection?";
		}

		new AbstractNotificationUtils() {
			@Override
			public void yesMethod() {
				if (session.getWorkflowId() != 0) {
					Page.getCurrent().getJavaScript().execute("window.open('','_parent','');window.close(); ");
				} else {
					if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)
                                              || screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION)) {
						checkSaveFlag(true);

					}
				}
				CommonLogic.dropDynamicTables(session.getUserId(), session.getSessionId());
			}

			@Override
			public void noMethod() {
				if (!screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION) && 
                                        (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction())
							|| Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()))) {
						checkCloseFlag(true);
					}
			}
		}.getConfirmationMessage(msgTitle, msgContent);

	}

	private void checkSaveFlag(boolean flag) {
		if (!session.isProjectionSaveFlag()) {
			if (dataSelectionDTO != null && Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
				new AbstractNotificationUtils() {
					@Override
					public void yesMethod() {
						try {

							if (resultTable != null) {
								resultTable.removeAllItems();
								BeanItemContainer<DataSelectionDTO> tempContainer = new BeanItemContainer<>(
										DataSelectionDTO.class);
								resultTable.setContainerDataSource(tempContainer);
								resultTable
										.setVisibleColumns(TableHeaderColumnsUtil.getDataSelectionColumns());
								resultTable.setColumnHeaders(TableHeaderColumnsUtil.getDataSelectionHeaders());
							}
							saveProjection(false);
						} catch (IllegalArgumentException ex) {
							LOGGER.error(ex.getMessage());
						}
						if (editWindow != null) {
							closeEditTray(editWindow);
							editWindow.close();
						} else if (isCommercialGovernment && forecastWindow != null) {
							closeEditTray(forecastWindow);
							forecastWindow.close();
						}
						if (viewWindow != null) {
							closeViewTray(viewWindow);
							viewWindow.close();
						}

					}

					@Override
					public void noMethod() {
						if (editWindow != null) {
							closeEditTray(editWindow);
							editWindow.close();
						} else if (isCommercialGovernment && forecastWindow != null) {
							closeEditTray(forecastWindow);
						}
						if (viewWindow != null) {
							closeViewTray(viewWindow);
							viewWindow.close();
						}
					}
				}.getConfirmationMessage(Constant.CLOSE_CONFIRMATION,
						"Changes have been saved. Do you want to close the projection now?");
			} else if (flag) {
				if (editWindow != null) {
					closeEditTray(editWindow);
					editWindow.close();
				} else if (isCommercialGovernment && forecastWindow != null) {
					closeEditTray(forecastWindow);
					forecastWindow.close();
				}
				if (viewWindow != null) {
					closeViewTray(viewWindow);
					viewWindow.close();
				}
			}
		} else {

			if (flag) {
				if (editWindow != null) {
					closeEditTray(editWindow);
					editWindow.close();
				} else if (isCommercialGovernment && forecastWindow != null) {
					closeEditTray(forecastWindow);
					forecastWindow.close();
				}
				if (viewWindow != null) {
					closeViewTray(viewWindow);
					viewWindow.close();
				}
			}

		}
	}

	/**
	 * Logic to save the projection.
	 */
	@Override
	protected void btnSubmitLogic() {
		if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction())
				|| Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
			if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				if (submitCheck()) {
					if (dataAssumption.isIsAllowedToSubmit() && (session.isIsDiscountCalculated()
							|| Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()))) {
						submitLogic(true);
					} else {
						AbstractNotificationUtils.getInfoNotification("New File Activated", session.getFileStatus() == 0
								? "There is a new file " + session.getFileName()
										+ " that has been activated. Please recalculate the Discount Projection, since it is currently based off of outdated data"
								: "Please recalculate the Discount Projection, since it is currently based off of outdated data");
					}
				} else {
					AbstractNotificationUtils.getErrorNotification("Missing Values",
							"The workflow cannot be submitted for approval.\n  Not all required fields have been completed.");
				}
			} else {
				AbstractNotificationUtils.getErrorNotification("Missing Values",
						"The workflow cannot be submitted for approval.\n  Not all required fields have been completed.");
			}
		}
	}

	private boolean submitCheck() {
		return (Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()) && nmSalesProjection.getSubmitFlag()
				&& discountProjection.getSubmitFlag() && dataAssumption.isDiscountLoaded())
				|| (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction()) && dataAssumption.isDiscountLoaded());
	}

	private void submitLogic(final boolean isCommercial) {
		new AbstractNotificationUtils() {
			@Override
			public void yesMethod() {
				final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
				UI.getCurrent().addWindow(popup);
				popup.addCloseListener(new Window.CloseListener() {
					@Override
					public void windowClose(Window.CloseEvent e) {
						try {
							if (ppaProjectionResults.isIsTabVisible()) {
                                                                nmPPAInitProcedure();
								ppaProjectionResults.getContent();
								ppaProjectionResults.ppaProcedure();
							}
							if (WorkFlowNotesLookup.getSUBMITFLAG().equals("Success")) {
								submitProjection(popup.getNotes().getValue(), screenName, popup.getUploadedData());
								if ((Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction())
										|| Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()))
										&& editWindow != null) {
									closeEditTray(editWindow);
									editWindow.close();
								} else if (isCommercialGovernment && forecastWindow != null) {
									closeEditTray(forecastWindow);
									forecastWindow.close();
								} else if (viewWindow != null) {
									closeViewTray(viewWindow);
									viewWindow.close();
								}
								WorkFlowNotesLookup.setSUBMITFLAG("Failed");
								CommonLogic.dropDynamicTables(session.getUserId(), session.getSessionId());
							}
						} catch (PortalException ex) {
							LOGGER.error(ex.getMessage());
						}
					}
				});
			}

			@Override
			public void noMethod() {
				if (isCommercial) {
					dataAssumption.isSalesCalculatedAlready();
				}
			}
		}.getConfirmationMessage("Confirm Submit", "Are you sure you want to submit the projection for approval?");
	}


	/**
	 * Saves the projection.
	 */
	private void saveProjection(boolean isSave) {
		LOGGER.debug("Entering SaveProjection method---->>= {} " , session.getProjectionId());
		try {
			if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                                logic.updateFlagForSaveCount(session);
				Future discountListView = service.submit(
						commUtil.createRunnable(Constant.DISCOUNT_LIST_VIEW_SAVE, discountProjection));
				checkRunningThreads();
				List<Future> saveFutureList = new ArrayList<>();
				// To save data from temp to main. threads used
				logic.saveTempToMain(session, service, saveFutureList, discountListView);
                                
				projectionVariance.savePvSelections();
				salesProjectionResults.saveSPResults();
				nmSalesProjection.saveSPSave();
				discountProjection.saveSelections();
				discountProjectionResults.saveButtonLogic();
				nonmandatedprojectionResults.saveProjectionResultsSelection();
				additionalInformation.saveNotesInformation(session.getProjectionId(),
						CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
				dsLogic.saveCurrenctActiveFile(session);
				// Below code will wait for the temp to main insertion to get
				// complete
                                if(!isSave){
                                commUtil.isProcedureCompletedForSubmit("FORECASTING", "SAVE_VIEW", session);
                                }
				
			}
			updateSaveFlag(session.getProjectionId());
		} catch (SystemException | PortalException | InterruptedException | ExecutionException ex) {
			LOGGER.error(ex.getMessage());
		}
		LOGGER.debug("Exiting SaveProjection method");
	}

	public void updateSaveFlag(final int projectionId) {
		if (!StringUtils.isEmpty(String.valueOf(projectionId))
				&& !Constant.NULL.equalsIgnoreCase(String.valueOf(projectionId))) {
			String query = "UPDATE PROJECTION_MASTER SET SAVE_FLAG=1 ,MODIFIED_BY= "
					+ UiUtils.parseStringToInteger(userId) + ",MODIFIED_DATE=GETDATE() WHERE PROJECTION_MASTER_SID = "
					+ projectionId;
			HelperTableLocalServiceUtil.executeUpdateQuery(query);
		}
	}

	/**
	 * Submits the projection. Saves and calls the workflow
	 */
	private void submitProjection(final String notes, final String screenName, final List<NotesDTO> getUploadedData)
			throws PortalException {

		if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction())
				|| Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction()) || session.getWorkflowId() != 0) {
			NonMandatedLogic nmLogic = new NonMandatedLogic();
			saveProjection(false);
			if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
			}
			String workflowStatus = nmLogic.getWorkflowStatus(session.getProjectionId(), screenName);
			if (!workflowStatus.equals("R") && !workflowStatus.equals("W")) {
				 List<String> roleList = new ArrayList<>();
                                 GtnWsCommonWorkflowResponse response = DSCalculationLogic.startWorkflow(session,userId);
				if (response.isHasPermission()) {
                                      DSCalculationLogic.startAndCompleteTask(session, userId);
				      submitProjToWorkflow( notes, screenName, getUploadedData);         
				} else {
					StringBuilder notiMsg = new StringBuilder("You dont have permission to submit a projection.");
					if (!roleList.isEmpty()) {
						notiMsg.append("\n Only " ).append( roleList ).append( " can submit a projection.");
					}
					NotificationUtils.getAlertNotification("Permission Denied", notiMsg.toString());

				}
			} else {
				submitProjToWorkflow( notes, screenName, getUploadedData);
			}
		} else {
			NotificationUtils.getErrorNotification("Error", MessageUtils.WFP_SUBMIT_ERROR);
		}
	}

	private void submitProjToWorkflow(final String notes, final String screenName,
			final List<NotesDTO> getUploadedData) {	
		try {
			Long processId = 0L;
			List processList = WorkflowPersistance.selectWFInstanceInfo(session.getProjectionId());
			if (processList != null && !(processList.isEmpty())) {
				processId = Long.valueOf(processList.get(0).toString());
			}

			VarianceCalculationLogic.submitWorkflow( processId, session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
			String autoApproval = DSCalculationLogic.getProcessVariableLog(processId,"Auto_Approval");
			String noOfUsers = DSCalculationLogic.getProcessVariableLog(processId,"NoOfUsers");
			if (!autoApproval.isEmpty() && !noOfUsers.isEmpty()) {

				LOGGER.debug("autoApproval  = {} " , autoApproval);
				LOGGER.debug("no of users = {} " , noOfUsers);
                                
                            session.setProjectionName(data.getProjectionName());
                            session.setDescription(data.getProjectionDescription());
                            dsLogic.updateProjectionNameAndProjectionDescription(session);
				String workflowId = submitToWorkflow(notes, Integer.parseInt(noOfUsers), screenName, getUploadedData);
				String approvedFlag;
				logic.deleteTempBySession();
				if (Constant.TRUE.equals(autoApproval)) {
					WorkflowLogic wfLogic = new WorkflowLogic();
					WorkflowMaster wm = wfLogic.getWorkflowMasterByProjectionId(session.getProjectionId());
					WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(session.getProjectionId(),
							wm.getWorkflowMasterSid(), Integer.parseInt(session.getUserId()),
							WorkflowConstants.getApprovedStatus(), notes, session.getApprovalLevel());
					workflowId = wfLogic.updateWorkflow(wfMasterDto);
					approvedFlag = "Submitted and Approved";
				} else {
					approvedFlag = Constant.SUBMITTED;
				}

				if (workflowId != null && !workflowId.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
					callWorkflowInboxRefresh();
					MessageBox.showPlain(Icon.INFO, approvedFlag + " Successfully ",
							" Workflow Id: " + workflowId + "  ", new MessageBoxListener() {
								/**
								 * 
								 * The method is triggered when a button of the
								 * message box is pressed .
								 *
								 * @param buttonId
								 *            The buttonId of the pressed
								 *            button.
								 */
								@SuppressWarnings("PMD")
                                                                @Override
								public void buttonClicked(final ButtonId buttonId) {
									if (session.getWorkflowId() != 0) {
										getBtnSave().setEnabled(false);
										getBtnSubmit().setEnabled(false);
									} else {
										UI.getCurrent().getNavigator().navigateTo(ForecastMainView.NAME);
									}
								}
							}, ButtonId.OK);

				} else {
					NotificationUtils.getErrorNotification("Error", "The Data not saved properly");
				}

			} else {
				submitProjection(notes, screenName, getUploadedData);
			}
		} catch (PortalException | SystemException | NumberFormatException ex) {
			LoggerFactory.getLogger(ForecastForm.class.getName()).error( StringUtils.EMPTY, ex);
		}
	}

	/**
	 * Called on submitting. Starts the workflow
	 */
	private String submitToWorkflow(String notes, int noOfApprovals, String screenName,
			List<NotesDTO> getUploadedData) {
		NonMandatedLogic nmLogic = new NonMandatedLogic();
		return nmLogic.submitProjection(session.getProjectionId(), session.getUserId(), notes, noOfApprovals, screenName,
				getUploadedData, session.getDescription());
	}

	/**
	 * Called on submitting. Starts the workflow
	 */
	public void configureOnEnter() {
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			tabSheet.setSelectedTab(2);
		} else {
			tabSheet.setSelectedTab(1);
		}
	}

	public NMSalesProjection getSalesProjection() {
		return nmSalesProjection;
	}

	public SessionDTO getSessions() {
		return session;
	}

	private void configureForView() {

		if (Constant.VIEW.equalsIgnoreCase(session.getAction())) {
			getBtnSave().setEnabled(false);
			getBtnSubmit().setEnabled(false);
		}

	}

    @Override
    protected void btnRefreshLogic() {

        saveProjection(false);
    }

    public void pushUpdate(String indicator) {
        if (INDICATOR_REFRESH_UPDATE.getConstant().equals(indicator)) {
            try {
                session.setFunctionMode("UPA");
                // Call sales Discount Master Insert Procedure
                nmSalesInsertDiscMasterProcedure();
                // Call sales View Procedures
                nmSalesViewsPopulationProcedureOnDataSelectionTabChange();
                commUtil.isProcedureCompleted("SALES", "PRC_NM_MASTER_INSERT", session);
                commUtil.isProcedureCompleted("SALES", "CUSTOMER", session);
                pushMap.put(INDICATOR_REFRESH_UPDATE.getConstant(), Boolean.TRUE);
                if (data.isDedCustomChange()) {
                    LOGGER.info("Deduction Custom Change {}");
                    session.setFunctionMode(session.getAction().equalsIgnoreCase(Constant.ADD_FULL_SMALL) ? "G" : "E");
                    commUtil.updateStatusTable(Constant.DISCOUNT3, session, Constants.CUSTOM);
                    String query = SQlUtil.getQuery("ViewTableTruncationDiscountCustom");
                    HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
                    service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                            Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.DISCOUNT3, "U", "", "", session));
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } 
        if (INDICATOR_TIME_PERIOD_CHANGED.getConstant().equals(indicator)) {
            try {

                pushMap.put(INDICATOR_TIME_PERIOD_CHANGED.getConstant(), Boolean.TRUE);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    private void push(int tabPosition) {

        if (pushMap.get(INDICATOR_REFRESH_UPDATE.getConstant()) != null
                && pushMap.get(INDICATOR_REFRESH_UPDATE.getConstant())) {

			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == discountProjectionResults.getTabNumber()
					&& tabLazyLoadMap.get(discountProjectionResults.getTabNumber())) {
				discountProjectionResults.pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
			}
			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == ppaProjectionResults.getTabNumber()
					&& tabLazyLoadMap.get(ppaProjectionResults.getTabNumber())) {
				ppaProjectionResults.pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
			}

			if (tabPosition == projectionVariance.getTabNumber()
					&& tabLazyLoadMap.get(projectionVariance.getTabNumber())) {
				projectionVariance.pushUpdate(INDICATOR_REFRESH_UPDATE.getConstant());
			}
			pushMap.put(INDICATOR_REFRESH_UPDATE.getConstant(), Boolean.FALSE);
		}

		if (pushMap.get(INDICATOR_TIME_PERIOD_CHANGED.getConstant()) != null
				&& pushMap.get(INDICATOR_TIME_PERIOD_CHANGED.getConstant())) {

			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == discountProjectionResults.getTabNumber()
					&& tabLazyLoadMap.get(discountProjectionResults.getTabNumber())) {
				discountProjectionResults.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
			}
			if (tabPosition == ppaProjection.getTabNumber() && tabLazyLoadMap.get(ppaProjection.getTabNumber())) {
				ppaProjection.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
			}
			if (tabSheet.getTab(tabPosition).isVisible() && tabPosition == ppaProjectionResults.getTabNumber()
					&& tabLazyLoadMap.get(ppaProjectionResults.getTabNumber())) {
				ppaProjectionResults.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
			}

			if (tabPosition == projectionVariance.getTabNumber()
					&& tabLazyLoadMap.get(projectionVariance.getTabNumber())) {
				projectionVariance.pushUpdate(INDICATOR_TIME_PERIOD_CHANGED.getConstant());
			}
			pushMap.put(INDICATOR_TIME_PERIOD_CHANGED.getConstant(), Boolean.FALSE);
		}

	}

	public NMDiscountProjection getDiscountProjection() {
		return discountProjection;
	}

	/**
	 * Tab Security
	 *
	 * @throws Exception
	 */
	private void setTabSecurity() {
		final StplSecurity stplSecurity = new StplSecurity();
                    if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessTabPermissionForNm(
					String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.BUSINESS_ROLE_IDS)),
					getCommercialConstant());
			if (functionPsHM.get(FunctionNameUtil.NM_DATA_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_DATA_TAB)).isTabFlag()) {
				tabSheet.getTab(0).setVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_DATA_ASSUMPTIONS_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_DATA_ASSUMPTIONS_TAB)).isTabFlag()) {
				tabSheet.getTab(1).setVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_SP_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_SP_TAB)).isTabFlag()) {
				tabSheet.getTab(NumericConstants.TWO).setVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_SPR_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_SPR_TAB)).isTabFlag()) {
				tabSheet.getTab(NumericConstants.THREE).setVisible(BooleanConstant.getFalseFlag());
				salesProjectionResults.setIsTabVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_SDP_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_SDP_TAB)).isTabFlag()) {
				tabSheet.getTab(NumericConstants.FOUR).setVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_SDPR_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_SDPR_TAB)).isTabFlag()) {
				tabSheet.getTab(NumericConstants.FIVE).setVisible(BooleanConstant.getFalseFlag());
				discountProjectionResults.setIsTabVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_PPA_RESULTS_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_PPA_RESULTS_TAB)).isTabFlag()) {
				tabSheet.getTab(NumericConstants.SIX).setVisible(BooleanConstant.getFalseFlag());
				ppaProjectionResults.setIsTabVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_PR_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_PR_TAB)).isTabFlag()) {
				tabSheet.getTab(NumericConstants.SEVEN).setVisible(BooleanConstant.getFalseFlag());
				nonmandatedprojectionResults.setIsTabVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_PV_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_PV_TAB)).isTabFlag()) {
				tabSheet.getTab(NumericConstants.EIGHT).setVisible(BooleanConstant.getFalseFlag());
			}
			if (functionPsHM.get(FunctionNameUtil.NM_ADDITIONAL_TAB) != null
					&& !((AppPermission) functionPsHM.get(FunctionNameUtil.NM_ADDITIONAL_TAB)).isTabFlag()) {
				tabSheet.getTab(NumericConstants.NINE).setVisible(BooleanConstant.getFalseFlag());
			}
		}
	}

	/**
	 * Tab Security
	 *
	 * @throws Exception
	 */
	private void setButtonSecurity() throws PortalException {
		final StplSecurity stplSecurity = new StplSecurity();
		Map<String, AppPermission> functionPsHM = stplSecurity
				.getBusinessFunctionPermissionForNm(
						String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.BUSINESS_ROLE_IDS)),
						(getCommercialConstant() + "," + "Common"));
		if (functionPsHM.get("btnSave") != null && !((AppPermission) functionPsHM.get("btnSave")).isFunctionFlag()) {
			getBtnSave().setVisible(BooleanConstant.getFalseFlag());
		}
		if (functionPsHM.get("btnSubmit") != null
				&& !((AppPermission) functionPsHM.get("btnSubmit")).isFunctionFlag()) {
			getBtnSubmit().setVisible(BooleanConstant.getFalseFlag());
		}
	}

	/**
	 * Used to close the Edit tray
	 *
	 * @param editWindow
	 */
	public void closeEditTray(ForecastEditWindow editWindow) {
		MinimizeTray tray = editWindow.getMinimizeTray();
		if (tray.getWindowItems().size() == 1) {
			tray.close();
		}
	}

	/**
	 * Used to close the Edit tray
	 *
	 * @param forecastWindow
	 */
	public void closeEditTray(ForecastWindow forecastWindow) {
		MinimizeTray tray = forecastWindow.getMinimizeTray();
		if (tray.getWindowItems().size() == 1) {
			tray.close();
		}
	}

	/**
	 * Used to close the View tray
	 *
	 * @param viewWindow
	 */
	private void closeViewTray(NonMandatedViewWindow viewWindow) {
		MinimizeTray tray = viewWindow.getMinimizeTray();
		if (tray.getWindowItems().size() == 1) {
			tray.close();
		}
	}

	/**
	 * Method checks for the actual sales and displays the alert message. If Yes
	 * selected, All the Trading Partners / Customers will be available in the
	 * projection. If No selected, Trading Partners / Customers with no actuals
	 * will be removed from the projection.
	 *
	 * @throws PortalException
	 * @throws Exception
	 */

	private void checkForActualSales() throws PortalException {

	if (logic.checkForZeroActuals(session)) {
			new AbstractNotificationUtils() {
				@Override
				public void yesMethod() {
					try {
						if (!screenName.equals(Constants.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
							callInsertProcedureOnGenerate(session);
						}
						init();
						addContent();
					} catch (Exception ex) {
						LOGGER.error(ex.getMessage());
					}
				}

				@Override
				public void noMethod() {
					try {                                                                                     
						logic.removeTPOrCustomerFromProjection(session,dataSelectionDTO);
						if (!screenName.equals(Constants.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
							callInsertProcedureOnGenerate(session);
						}
						init();
						addContent();
					} catch (Exception ex) {
						LOGGER.error(ex.getMessage());
					}
				}
			}.getConfirmationMessage(CONFIRMATION.getConstant(), alertMsg.getString(
					"F_" + screenName.replaceAll("\\s", StringUtils.EMPTY).toUpperCase(Locale.ENGLISH) + "_ACT_CHECK_MSG"));
		} else {
			if (!screenName.equals(Constants.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
				callInsertProcedureOnGenerate(session);
			}
			init();
			addContent();
		}
	}

	/**
	 * Method calls the insert procedure while generating a projection based on
	 * the forecast module.
	 */
	private void callInsertProcedureOnGenerate(final SessionDTO session) {
		dsLogic.callInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId(),SalesUtils.MANDATED_PRO_NAME);
	}

	@Override
	protected void btnApproveLogic() {

		MessageBox.showPlain(Icon.QUESTION, "Confirm Approve",
				"Are you sure you want to approve the projection " + " ?", new MessageBoxListener() {
                                        @Override
					public void buttonClicked(ButtonId buttonId) {
						if (buttonId.name().equals(Constant.YES)) {

							final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
							UI.getCurrent().addWindow(popup);
							popup.addCloseListener(new Window.CloseListener() {
								@Override
								public void windowClose(Window.CloseEvent e) {
									try {
										int projectionId = session.getProjectionId();
										String localUserId = session.getUserId();
										int userIdInt = Integer.parseInt(localUserId);
										int workflowId = session.getWorkflowId();
										WorkflowLogic wfLogic = new WorkflowLogic();
										String workflowIdUpdate;
										WorkflowMasterDTO wfMasterDto;
										wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt,
												WorkflowConstants.getApprovedStatus(), popup.getNotes().getValue(),
												session.getApprovalLevel());
										workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
										if (session.getNoOfApproval() > session.getApprovalLevel()) {
											wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId,
													userIdInt, WorkflowConstants.getPendingStatus(), StringUtils.EMPTY,
													session.getApprovalLevel() + 1);
											workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
										}
										if (workflowIdUpdate != null
												&& !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {

											VarianceCalculationLogic.approveWorkflow(
													session.getProcessId(), session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
											callWorkflowInboxRefresh();
											AbstractNotificationUtils.getInfoNotification("Approved Information",
													Constant.WORKFLOW_ID + workflowIdUpdate + " approved successfully");
											getBtnApprove().setEnabled(false);
											getBtnWithdraw().setEnabled(false);
											getBtnCancel().setEnabled(false);
											getBtnReject().setEnabled(false);
										} else {
											CommonUIUtils
													.getMessageNotification("The projection not approved properly");
										}
									} catch (NumberFormatException ex) {
										LOGGER.error(ex.getMessage());
									}
								}
							});

						}
					}
				}, ButtonId.YES, ButtonId.NO);

	}

	@Override
	protected void btnRejectLogic() {
		MessageBox.showPlain(Icon.QUESTION, "Confirm Reject", "Are you sure you want to reject the projection " + " ?",
				new MessageBoxListener() {
                                        @Override
					public void buttonClicked(ButtonId buttonId) {
						if (buttonId.name().equals(Constant.YES)) {
							final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
							UI.getCurrent().addWindow(popup);
							popup.addCloseListener(new Window.CloseListener() {
								@Override
								public void windowClose(Window.CloseEvent e) {
									try {
										int projectionId = session.getProjectionId();
										String localUserId = session.getUserId();
										int userIdInt = Integer.parseInt(localUserId);
										int workflowId = session.getWorkflowId();
										WorkflowLogic wfLogic = new WorkflowLogic();
										WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId,
												workflowId, userIdInt, WorkflowConstants.getRejectedStatus(),
												popup.getNotes().getValue(), session.getApprovalLevel());
										String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
										if (workflowIdUpdate != null
												&& !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
											VarianceCalculationLogic.rejectWorkFlow(
													session.getProcessId(), session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
											callWorkflowInboxRefresh();
											AbstractNotificationUtils.getInfoNotification("Rejected Information ",
													Constant.WORKFLOW_ID + workflowIdUpdate + " rejected successfully");
											getBtnApprove().setEnabled(false);
											getBtnWithdraw().setEnabled(false);
											getBtnCancel().setEnabled(false);
											getBtnReject().setEnabled(false);
										} else {
											CommonUIUtils
													.getMessageNotification("The projection not rejected properly");
										}
									} catch (NumberFormatException ex) {
										LOGGER.error(ex.getMessage());
									}
								}
							});
						}
					}
				}, ButtonId.YES, ButtonId.NO);

	}

	@Override
	protected void btnWithdrawLogic() {

		MessageBox.showPlain(Icon.QUESTION, "Confirm Withdraw",
				"Are you sure you want to withdraw the projection " + " ?", new MessageBoxListener() {
                                        @Override
					public void buttonClicked(ButtonId buttonId) {
						if (buttonId.name().equals(Constant.YES)) {
							final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
							UI.getCurrent().addWindow(popup);
							popup.addCloseListener(new Window.CloseListener() {
								@Override
								public void windowClose(Window.CloseEvent e) {
									try {
										int projectionId = session.getProjectionId();
										String localUserId = session.getUserId();
										int userIdInt = Integer.parseInt(localUserId);
										int workflowId = session.getWorkflowId();
										WorkflowLogic wfLogic = new WorkflowLogic();
										WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId,
												workflowId, userIdInt, WorkflowConstants.getWithdrawnStatus(),
												popup.getNotes().getValue(), session.getApprovalLevel());
										String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
										if (workflowIdUpdate != null
												&& !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
											VarianceCalculationLogic.withDrawWorkflow(
													session.getProcessId(), session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);

											callWorkflowInboxRefresh();
											AbstractNotificationUtils.getInfoNotification("Workflow withdrawn ",
													Constant.WORKFLOW_ID + workflowIdUpdate
															+ " withdrawn successfully");

											
											getBtnApprove().setEnabled(false);
											getBtnWithdraw().setEnabled(false);
											getBtnCancel().setEnabled(false);
											getBtnReject().setEnabled(false);
										} else {
											CommonUIUtils
													.getMessageNotification("The projection not withdrawn properly");
										}
									} catch (NumberFormatException ex) {
										LOGGER.error(ex.getMessage());
									}
								}
							});
						}
					}
				}, ButtonId.YES, ButtonId.NO);

	}

	@Override
	protected void btnCancelLogic() {
		MessageBox.showPlain(Icon.QUESTION, "Confirm Cancel", "Are you sure you want to cancel the projection " + " ?",
				new MessageBoxListener() {
                                        @Override
					public void buttonClicked(ButtonId buttonId) {
						if (buttonId.name().equals(Constant.YES)) {
							final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
							UI.getCurrent().addWindow(popup);
							popup.addCloseListener(new Window.CloseListener() {
								@Override
								public void windowClose(Window.CloseEvent e) {
									try {
										int projectionId = session.getProjectionId();
										String localUserId = session.getUserId();
										int userIdInt = Integer.parseInt(localUserId);
										int workflowId = session.getWorkflowId();
										WorkflowLogic wfLogic = new WorkflowLogic();
										WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId,
												workflowId, userIdInt, WorkflowConstants.getCancelledStatus(),
												popup.getNotes().getValue(), session.getApprovalLevel());
										String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
										if (workflowIdUpdate != null
												&& !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {

											VarianceCalculationLogic.cancelWorkFlow(
													session.getProcessId(), session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
											callWorkflowInboxRefresh();
											AbstractNotificationUtils.getInfoNotification("Cancel Information",
													Constant.WORKFLOW_ID + workflowIdUpdate
															+ " cancelled successfully");

											getBtnApprove().setEnabled(false);
											getBtnWithdraw().setEnabled(false);
											getBtnCancel().setEnabled(false);
											getBtnReject().setEnabled(false);
										} else {
											CommonUIUtils
													.getMessageNotification("The projection not cancelled properly");
										}
									} catch (NumberFormatException ex) {
										LOGGER.error(ex.getMessage());
									}
								}
							});
						}
					}
				}, ButtonId.YES, ButtonId.NO);
	}

	private void callWorkflowInboxRefresh() {
		JavaScript.getCurrent()
				.execute("localStorage.setItem('" + WorkflowConstants.getBusinessProcessNameForecast() + "', 'true');");
	}

	/**
	 * Checks for the available records in the actual and projection (Government
	 * Discount) tables.
	 *
	 * @param projectionId
	 * @param userId
	 * @param sessionId
	 * @return true - if records available in both tables. false - if records
	 *         not available in anyone table.
	 */
	public boolean checkMandatedDiscountAvailablity(SessionDTO session) {
		final DiscountProjectionLogic dpLogic = new DiscountProjectionLogic();

		final boolean isActualDiscountAvailable = dpLogic.checkForActualGovtDiscount(session);
		final boolean isDiscountProjectionAvailable = dpLogic.checkForGovtDiscountProjection(session);

		return isActualDiscountAvailable && isDiscountProjectionAvailable;
	}

	/**
	 * To run the dataselection tab in separate thread to reduce the time in DS
	 * generate,edit and view It will be same for Government, Commercial and
	 * Returns.
	 *
	 * @param form
	 * @return
	 */
	private Runnable createRunnable() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					data.init();
					latch.countDown();
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage());
				}
			}
		};
		return runnable;
	}

	/**
	 * Used to wait for the Data Selection thread(Once the dataselection tab is
	 * loaded screen will be visible to the user).
	 */
	private void waitForThread(Thread th) {
		if (th != null && th.isAlive()) {

			synchronized (th) {
				try {
					th.wait();
				} catch (InterruptedException ex) {
					LoggerFactory.getLogger(ForecastForm.class.getName()).error( StringUtils.EMPTY, ex);
                                        Thread.currentThread().interrupt();
				}
			}

		}
	}

	/**
	 * Commercial configuration for all the mode... Add,Edit and View. All the
	 * thread should be added in session.addFutureMap(); So that we can check
	 * all the threads are completed when we save or submit.
	 *
	 * In Add mode
	 *
	 * ProjHierarchyInsert-->> will be triggered to insert the values in
	 * Projection_Cust and Projection_Prod tables. It will be checked when we
	 * save or submit and also in data selection tab load, since the data
	 * selection tab will be loaded based on the Projection_cust and
	 * Projection_Prod tables
	 *
	 * Data Selection tab -->> will be triggered to load the data selection tab
	 * and it will be checked if the user goes to Data selection tab.
	 * ProjHierarchyInsert thread will be sent as input to this thread. Data
	 * selection tab will be loaded based on the ProjHierarchyInsert, so once
	 * the ProjHierarchyInsert is complete, data selection tab load will be
	 * started
	 *
	 * PROJECTION_DETAILS_INSERT-->> will be triggered to insert the data to the
	 * projection_details table. Projection details query will be differ for
	 * generate and data selection tab change(If the user change the customer
	 * and product selection inside the data selection tab).
	 *
	 * DISCOUNT_PROCEDURE_CALL -->> it will trigger the discount insert
	 * procedure call and it will be checked in save and submit. And also in
	 * Discount projection tab load.
	 *
	 *
	 * In Edit Mode
	 *
	 * Main to temp insert--> Main to temp copy is called. Sales projection
	 * insert alone will be triggered and wait for its get complete since sales
	 * is the initial screen. all other screen(Discount and PPA) insert will be
	 * triggered after sales.
	 *
	 * Data Selection tab -->> will be triggered to load the data selection tab
	 * load and it will be checked if the user goes to Data selection tab.
	 *
	 * DISCOUNT_PROCEDURE_CALL -->> it will trigger the discount insert
	 * procedure call. Inside this thread, it will wait for the discount main to
	 * temp copy get complete and it will be checked in save and submit. And
	 * also in Discount projection tab load.
	 */
	   private void commercialConfiguration() {
        switch (session.getAction().toLowerCase(Locale.ENGLISH)) {
            case Constant.ADD_FULL_SMALL:

                session.addFutureMap(Constant.FILE_INSERT, new Future[]{service.submit(
                    commUtil.createRunnable(Constant.MERGE_QUERY, dataInsertProcedureCall()))});

                session.addFutureMap(Constant.PROJ_HIERARCHY_INSERT,
                        new Future[]{
                            // PROJECTION_CUST_HIERARCHY INSERT CALL
                            service.submit(commUtil.createRunnable(Constant.CUST_HIERARCHY_INSERT,
                                    dataSelectionDTO.getProjectionId(),
                                    dataSelectionDTO.getSelectedCustomerRelationSid(), Boolean.FALSE)),
                            // PROJECTION_PROD_HIERARCHY INSERT CALL
                            service.submit(commUtil.createRunnable(Constant.PROD_HIERARCHY_INSERT,
                                    dataSelectionDTO.getProjectionId(),
                                    dataSelectionDTO.getSelectedProductRelationSid(), Boolean.FALSE))});
                // To load the data selection tab once the PROJECTION_CUST AND
                // PROJECTION_PROD GET EXCUTE
                session.addFutureMap(Constant.DATA_SELECTION_TAB_LOAD,
                        new Future[]{
                            service.submit(commUtil.createRunnable(Constant.DATA_SELECTION_TAB_LOAD,
                                    data, session.getFutureValue(Constant.PROJ_HIERARCHY_INSERT)))});
                // To insert the Projection details table
                session.addFutureMap(Constant.PROJECTION_DETAILS_INSERT, new Future[]{
                    service.submit(commUtil.createRunnable(Constant.PROJECTION_DETAILS_INSERT,
                    dataSelectionDTO.getProjectionId(), session.getCurrentTableNames(), Boolean.FALSE))});
                // Call sales Insert Procedure
                nmSalesInsertDiscMasterProcedure();
                // sales threads need to be completed before calling discound thread
                nmSalesViewsPopulationProcedure();
                commUtil.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.SALES_PROCEDURE_CALL));
                commUtil.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL));
                break;
            case Constant.EDIT_SMALL:
                // Main to temp insert
                session.addFutureMap(Constant.FILE_INSERT, new Future[]{service.submit(
                    commUtil.createRunnable(Constant.MERGE_QUERY, dataInsertProcedureCall()))});
                logic.mainToTempTableInsert(session, service);

                session.addFutureMap(Constant.DATA_SELECTION_TAB_LOAD, new Future[]{
                    service.submit(commUtil.createRunnable(Constant.DATA_SELECTION_TAB_LOAD, data))});
                // Call sales Insert Procedure
                nmSalesInsertDiscMasterProcedure();
                // sales threads need to be completed before calling discound thread
                nmSalesViewsPopulationProcedure();
                commUtil.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.SALES_PROCEDURE_CALL));
                commUtil.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL));
                break;

            case Constant.VIEW:

                session.addFutureMap(Constant.FILE_INSERT, new Future[]{service.submit(
                    commUtil.createRunnable(Constant.MERGE_QUERY, dataInsertProcedureCallForView()))});
                // Main to temp insert
                logic.mainToTempTableInsert(session, service);
                session.addFutureMap(Constant.DATA_SELECTION_TAB_LOAD, new Future[]{
                service.submit(commUtil.createRunnable(Constant.DATA_SELECTION_TAB_LOAD, data))});
                nmSalesInsertDiscMasterProcedure();
                nmSalesViewsPopulationProcedure();
                commUtil.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.SALES_PROCEDURE_CALL));
                commUtil.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL));
                break;
            default:
                break;

        }
    }
        
	/**
	 * This method is to check whether any thread is running or not If it its
	 * running we will make it wait untill the thread get executed
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private void checkRunningThreads() throws InterruptedException, ExecutionException {
		for (Map.Entry<String, Future[]> entry : session.returnFutureMap().entrySet()) {
			for (Future future : entry.getValue()) {
				future.get();
			}
		}
	}

	private String dataInsertProcedureCall() {
		String sessionId = "'" + session.getSessionId() + "'";
                String queryProc = StringUtils.EMPTY;
		queryProc = queryProc + SQlUtil.getQuery("Product_customer_files_insert")
				.replace("?PROJECTION_ID", String.valueOf(session.getProjectionId()))
				.replace("?USER_ID", String.valueOf(session.getUserId())).replace("?SESSION_ID", sessionId);
		return queryProc;
	}

	private String dataInsertProcedureCallForView() {
		String sessionId = "'" + session.getSessionId() + "'";
                String query = StringUtils.EMPTY;
		query =  query +  SQlUtil.getQuery("Product_customer_files_insert_view")
				.replace("?PROJECTION_ID", String.valueOf(session.getProjectionId()))
				.replace("?USER_ID", String.valueOf(session.getUserId())).replace("?SESSION_ID", sessionId);
		return query;
	}

	private Boolean checkLastPositionTab(int tabPosition) {
		boolean salesFlag = false;
		if (isNonMandatedTab2(tabPosition) || isNonMandatedTab1(tabPosition)) {
			salesFlag = true;
		}
		return salesFlag;
	}

    private boolean isNonMandatedTab1(int tabPosition1) {
        return tabPosition1 == 1 && (!screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED));
    }

    private boolean isNonMandatedTab2(int tabPosition1) {
        return tabPosition1 == 2 && screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED);
    }

    private void nmSalesInsertDiscMasterProcedure() {
        CommonUtil salesCommonUtil = commUtil;
        if((data.isUpdateOnTabChange() && data.isCustomChange()) || (!data.isUpdateOnTabChange() && !data.isCustomChange())){
		session.addFutureMap(Constant.SALES_PROCEDURE_CALL,
				new Future[] {
						service.submit(salesCommonUtil.createRunnable(Constant.PROCEDURE_CALL,
								SalesUtils.PRC_NM_MASTER_INSERT, dataSelectionDTO.getProjectionId(),
								session.getUserId(), session.getSessionId(), Constant.SALES1,session))});
                
						service.submit(salesCommonUtil.createRunnable(Constant.PROCEDURE_CALL,
								SalesUtils.PRC_NM_PROJECTION_INSERT, dataSelectionDTO.getProjectionId(),
								session.getUserId(), session.getSessionId(), Constant.SALES1,session)) ;
                                                
                session.addFutureMap(Constant.DISCOUNT_MASTER_PROCEDURE_CALL,
				new Future[] {
						service.submit(salesCommonUtil.createRunnable(Constant.DISCOUNT_MASTER,
				SalesUtils.PRC_NM_MASTER_INSERT, dataSelectionDTO.getProjectionId(), session.getUserId(),
				session.getSessionId(), Constant.DISCOUNT3,session)) });
        }
                                                         
    }
        
       private void nmSalesViewsPopulationProcedure() {
            if(!data.isCustomChange()){
            session.addFutureMap(Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL,
				new Future[] {service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                                Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "C", "", "", session))});

            service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                    Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "P", "", "", session));

            service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                    Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "U", "", "", session));
            } else{
            service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                    Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "U", "", "", session));
        }
    }
       private void nmSalesViewsPopulationProcedureOnDataSelectionTabChange() {
        if (!data.isCustomChange() && data.isUpdateOnTabChange()) {
            commUtil.updateStatusTable(Constant.SALES1, session, Constants.CUSTOMER);
            commUtil.updateStatusTable(Constant.SALES1, session, Constants.PRODUCT);
            commUtil.updateStatusTable(Constant.SALES1, session, Constants.CUSTOM);
            session.addFutureMap(Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL,
                    new Future[]{service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                                Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "C", "", "", session))});

            service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                    Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "P", "", "", session));

            service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                    Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "U", "", "", session));
        } else {
            session.setFunctionMode(session.getAction().equalsIgnoreCase(Constant.ADD_FULL_SMALL) ? "G" : "E");
            commUtil.updateStatusTable(Constant.SALES1, session, Constants.CUSTOM);
            String query = SQlUtil.getQuery("ViewTableTruncationSalesCustom");
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
            service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                    Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "U", "", "", session));
        }
    }

    private void nmDiscountViewsPopulationProcedure() {
        if(!data.isCustomChange()){
        session.addFutureMap(Constant.CUSTOMER_VIEW_DISCOUNT_POPULATION_CALL,
				new Future[] {service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                Constant.CUSTOMER_VIEW_DISCOUNT_POPULATION_CALL, session.getFunctionMode(), Constant.DISCOUNT3, "C", "null", "null", session))});
        service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                Constant.PRODUCT_VIEW_DISCOUNT_POPULATION_CALL,session.getFunctionMode(), Constant.DISCOUNT3, "P", "null", "null", session));
        service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                Constant.CUSTOM_VIEW_DISCOUNT_POPULATION_CALL,session.getFunctionMode(), Constant.DISCOUNT3, "U", "null", "null", session));
        }
        else{
        service.submit(commUtil.createRunnable(Constant.PRC_VIEWS_CALL,
                Constant.CUSTOM_VIEW_DISCOUNT_POPULATION_CALL,session.getFunctionMode(), Constant.DISCOUNT3, "U", "null", "null", session));  
        }
    }
	private void nmPPAInitProcedure() {
		Future ppaInit = service.submit(
				commUtil.createRunnableForPPAInitProcedure(SalesUtils.PRC_NM_PPA_PROJ_INIT, session));
		session.addFutureMap(SalesUtils.PRC_NM_PPA_PROJ_INIT, new Future[] { ppaInit });
		session.setIsSalesCalculated(false);
	}

	private void callContractDetailsPrcForDiscount() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName(Constant.PRC_CONTRACT_DETAILS_REBATE);
				// It will wait until the discount insert procedure complete
                        commUtil
						.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.DISCOUNT_PROCEDURE_CALL));
				StringBuilder query = new StringBuilder("EXEC ");
				query.append(Constant.PRC_CONTRACT_DETAILS_REBATE);
				query.append(' ').append(session.getProjectionId()).append(',');
				query.append(session.getUserId()).append(",'").append(session.getSessionId()).append('\'');
				HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
	}
		};
		session.addFutureMap(Constant.CALL_PRC_CONTRACT_DETAILS_REBATE, new Future[] { service.submit(runnable) });
	}

	private void checkCloseFlag(boolean flag) {

		if (!session.isProjectionSaveFlag()) {
			if (dataSelectionDTO != null && Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())) {
				new AbstractNotificationUtils() {
                                    @Override
					public void yesMethod() {
						try {

							if (resultTable != null) {
								resultTable.removeAllItems();
								BeanItemContainer<DataSelectionDTO> tempContainer = new BeanItemContainer<>(
										DataSelectionDTO.class);
								resultTable.setContainerDataSource(tempContainer);
								resultTable
										.setVisibleColumns(TableHeaderColumnsUtil.getDataSelectionColumns());
								resultTable.setColumnHeaders(TableHeaderColumnsUtil.getDataSelectionHeaders());
							}
							dsLogic.deleteProjection(session.getProjectionId(), session.getUserId(), screenName);
						} catch (IllegalArgumentException ex) {
							LOGGER.error(ex.getMessage());
						}
						if (editWindow != null) {
							closeEditTray(editWindow);
							editWindow.close();
						} else if (isCommercialGovernment && forecastWindow != null) {
							closeEditTray(forecastWindow);
							forecastWindow.close();
						}
						if (viewWindow != null) {
							closeViewTray(viewWindow);
							viewWindow.close();
						}

					}

					@Override
					public void noMethod() {
						if (editWindow != null) {
							closeEditTray(editWindow);
							editWindow.close();
						} else if (isCommercialGovernment && forecastWindow != null) {
							closeEditTray(forecastWindow);
						}
						if (viewWindow != null) {
							closeViewTray(viewWindow);
							viewWindow.close();
						}
					}
				}.getConfirmationMessage(Constant.CLOSE_CONFIRMATION,
						"Changes have not been saved.Do you want to close the projection now?");
			} else if (flag) {
				if (editWindow != null) {
					closeEditTray(editWindow);
					editWindow.close();
				} else if (isCommercialGovernment && forecastWindow != null) {
					closeEditTray(forecastWindow);
					forecastWindow.close();
				}
				if (viewWindow != null) {
					closeViewTray(viewWindow);
					viewWindow.close();
				}
			}
		} else if (flag) {
			if (editWindow != null) {
				closeEditTray(editWindow);
				editWindow.close();
			} else if (isCommercialGovernment && forecastWindow != null) {
				closeEditTray(forecastWindow);
				forecastWindow.close();
			}
			if (viewWindow != null) {
				closeViewTray(viewWindow);
				viewWindow.close();
			}
		}
	}

    }
