/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.NULL;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHS;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERS;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.YEAR;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_CUSTOMER_HIERARCHY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LEVEL_CUSTOMER;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LEVEL_NDC;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_GROUP;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_PRODUCT_HIERARCHY;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_ADD;
import static com.stpl.app.utils.Constants.LabelConstants.MODE_SEARCH;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_CUSTOMER_GROUP_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_CUSTOMER_HIERARCHY_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_PRODUCT_GROUP_LOOKUP;
import static com.stpl.app.utils.Constants.LabelConstants.WINDOW_PRODUCT_HIERARCHY_LOOKUP;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.addons.lazycontainer.LazyContainer;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dto.CompanyDdlbDto;
import com.stpl.app.gtnforecasting.dto.RelationshipDdlbDto;
import com.stpl.app.gtnforecasting.lazyload.CompanyDdlbCriteria;
import com.stpl.app.gtnforecasting.lazyload.CompanyDdlbDao;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import static com.stpl.app.gtnforecasting.ui.form.DataSelectionForm.customerHierarchyLookupWindow;
import static com.stpl.app.gtnforecasting.ui.form.DataSelectionForm.getCustomerHierarchyLookupWindow;
import com.stpl.app.gtnforecasting.ui.form.lookups.CustomerProductGroupLookup;
import com.stpl.app.gtnforecasting.ui.form.lookups.HierarchyLookup;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.DataSelectionUtil;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.app.utils.Constants;
import com.stpl.app.utils.UiUtils;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.forecastds.form.ForecastDataSelection;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.ui.ComboBox;

/**
 *
 * @author sooriya.lakshmanan
 */
public class DataSelection extends ForecastDataSelection {

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSelection.class);
	private DataSelectionDTO selectionDTO;
	private final SessionDTO session;
	private boolean firstTimeLoad = true;
	private boolean dismantelCustomerSelection = true;
	private boolean dismantelProductSelection = true;
	private final DataSelectionForm dataSelectionForm;
	private boolean updateOnTabChange = false;
	private boolean customChange = false;
	private boolean dedCustomChange = false;
	private boolean reloadAfterUpdate = false;
	private boolean valid = true;
	private LazyContainer discountDdlbLazyContainer;
	private final CompanyDdlbDto discountDdlbDefault = new CompanyDdlbDto(0, SELECT_ONE, true);
	private CompanyDdlbDto discountDTO = null;
	private final List<Integer> customerBeanList = new ArrayList<>();
	private final List<Integer> productBeanList = new ArrayList<>();
	private static List<Integer> productBeanLisTemp = new ArrayList<>();
	private final List<String> productHierarchyNos = new ArrayList<>();
	private final DataSelectionLogic dsLogic = new DataSelectionLogic();
	private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();
	private Map<String, String> customViewInput = new HashMap<>();

	public static final String CONFIRM_CHANGE = "Confirm Change";

	// Used for CCP_HIERARCHY_INSERT query formation
	private String topLevelName = StringUtils.EMPTY;

	public DataSelection(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, SessionDTO session,
			ForecastForm form, String screenName, final DataSelectionForm dataSelectionForm) {
		super(dataSelectionBinder, screenName, false);
		this.session = session;
		this.selectionDTO = dataSelectionDTO;
		this.screenName = screenName;
		this.dataSelectionForm = dataSelectionForm;
		customerDescriptionMap = session.getCustomerDescription();
		productDescriptionMap = session.getProductDescription();
		customerGroup.setValue(dataSelectionDTO.getCustomerGroup());
		productGroup.setValue(dataSelectionDTO.getProductGroup());
		frequency.select(session.getDsFrequency());
		customRelationDdlb.setValue(session.getCustomRelationShipSid());
		customRelationDdlbDeduction.setValue(session.getCustomDeductionRelationShipSid());
		LOGGER.debug("Inside Constructor= {}", form);
	}

	/**
	 * Used to load the data selection tab It will be loaded in thread
	 *
	 * @throws Exception
	 */
	public void init() {
		try {
			configureDdlb();
			configureOnLoading(selectionDTO.getProjectionId(), selectionDTO);
			configureFields();
			configureForView();
			if (ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
				configureOnViewMode();
			}
			configureDataSelectionDeductionLevel();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
	}

	/**
	 * For accural rate Projection
	 *
	 * @param dataSelectionBinder
	 * @param dataSelectionDTO
	 * @param session
	 * @param screenName
	 * @param dataSelectionForm
	 */
	public DataSelection(CustomFieldGroup dataSelectionBinder, DataSelectionDTO dataSelectionDTO, SessionDTO session,
			String screenName, final DataSelectionForm dataSelectionForm) {
		super(dataSelectionBinder, screenName, false);
		this.session = session;
		this.selectionDTO = dataSelectionDTO;
		this.screenName = screenName;
		this.dataSelectionForm = dataSelectionForm;
		customerDescriptionMap = session.getCustomerDescription();
		productDescriptionMap = session.getProductDescription();
		deductionLevel.addItem(session.getDeductionLevel());
		deductionLevel.select(session.getDeductionLevel());
		deductionLevel.setImmediate(true);
		deductionValue.addItem(session.getDeductionValue());
		deductionValue.select(session.getDeductionValue());
		deductionValue.setImmediate(true);
		configureDdlb();
		configureOnLoading(dataSelectionDTO.getProjectionId(), dataSelectionDTO);
		configureFields();
		configureForView();
		configureOnViewMode();

	}

	private void configureDdlb() {
		//String from = DataSelectionUtil.getTimePeriod(selectionDTO.getFromDate());
		//String to = DataSelectionUtil.getTimePeriod(selectionDTO.getToDate());
                String from = selectionDTO.getFromPeriod();
		String to = selectionDTO.getToPeriod();
                String fromId = (String) fromPeriod.getValue();
                System.out.println("fromId"+fromId);
		fromPeriod.select(from);
		toPeriod.select(to);
		toPeriod.setReadOnly(true);
//		fromPeriod.addValueChangeListener(new Property.ValueChangeListener() {
//			@Override
//			public void valueChange(Property.ValueChangeEvent event) {
//				setUpdateOnTabChange(BooleanConstant.getTrueFlag());
//				session.setFromPeriod(String.valueOf(fromPeriod.getValue()));
//				session.setFromDate(selectionDTO.getFromDate());
//				session.setFromDateChanged(BooleanConstant.getTrueFlag());
//				productBeanLisTemp.clear();
//				productHierarchyNos.clear();
//				for (Leveldto dto : selectedProductContainer.getItemIds()) {
//
//					productBeanLisTemp.add(dto.getRelationshipLevelSid());
//					productHierarchyNos.add(dto.getHierarchyNo());
//
//				}
//				setProductBeanLisTemp(productBeanLisTemp);
//			}
//		});
//		toPeriod.addValueChangeListener(new Property.ValueChangeListener() {
//
//			@Override
//			public void valueChange(Property.ValueChangeEvent event) {
//				session.setSaveFlag(true);
//				session.setToPeriod(selectionDTO.getToPeriod());
//				session.setToDate(selectionDTO.getToDate());
//			}
//		});
//
//		businessUnit.setPageLength(NumericConstants.SEVEN);
//		businessUnit.setImmediate(true);
//		businessUnit.addItem(0);
//		businessUnit.setItemCaption(0, Constants.CommonConstants.SELECT_ONE.getConstant());
//		businessUnit.setNullSelectionAllowed(true);
//		businessUnit.setNullSelectionItemId(0);
//		businessUnit.setInputPrompt(Constants.CommonConstants.SELECT_ONE.getConstant());
//		businessUnit.markAsDirty();
//		List<Object[]> list = dsLogic.getBusinessUnits(0);
//		if (list != null && !list.isEmpty()) {
//			for (Object[] object : list) {
//				businessUnit.addItem(object[0]);
//				businessUnit.setItemCaption(object[0], object[NumericConstants.TWO] + Constant.SPACE
//						+ Constant.DASH_NO_DATA + Constant.SPACE + object[NumericConstants.THREE]);
//			}
//		}
//
//		businessUnit.select(selectionDTO.getBusinessUnitSystemId());
//		businessUnit.addValueChangeListener(new Property.ValueChangeListener() {
//
//			@Override
//			public void valueChange(Property.ValueChangeEvent event) {
//
//				availableProductContainer.removeAllItems();
//				selectedProductContainer.removeAllItems();
//				productBeanList.clear();
//			}
//		});
//		customerGroup.setValue(selectionDTO.getCustomerGroup());
//		productGroup.setValue(selectionDTO.getProductGroup());
//		company.setPageLength(NumericConstants.SEVEN);
//		company.setImmediate(true);
//		company.addItem(0);
//		company.setItemCaption(0, Constants.CommonConstants.SELECT_ONE.getConstant());
//		company.setNullSelectionAllowed(true);
//		company.setNullSelectionItemId(0);
//		company.setInputPrompt(Constants.CommonConstants.SELECT_ONE.getConstant());
//		company.markAsDirty();
//
//		List<Object[]> companyList = dsLogic.getCompanies();
//		if (companyList != null && !companyList.isEmpty()) {
//			for (Object[] object : companyList) {
//				company.addItem(object[0]);
//				company.setItemCaption(object[0], object[NumericConstants.TWO] + Constant.SPACE + Constant.DASH_NO_DATA
//						+ Constant.SPACE + object[NumericConstants.THREE]);
//			}
//		}
//
//		company.select(Integer.valueOf(selectionDTO.getCompanySid()));
//		company.addValueChangeListener(new Property.ValueChangeListener() {
//
//			@Override
//			public void valueChange(Property.ValueChangeEvent event) {
//
//				availableProductContainer.removeAllItems();
//				selectedProductContainer.removeAllItems();
//				productBeanList.clear();
//			}
//		});

	}

	private void configureForView() {
//		resultsTablePanel.setVisible(false);
//		buttonLay.setVisible(false);
//		finalBtn.setVisible(false);
//		modeOption.setEnabled(false);
//		discount.setEnabled(false);
//		if (dataSelectionForm != null) {
//			publicView.setValue(dataSelectionForm.getPublicViewName() != null ? dataSelectionForm.getPublicViewName()
//					: StringUtils.EMPTY);
//			privateView.setValue(dataSelectionForm.getPrivateViewName() != null ? dataSelectionForm.getPrivateViewName()
//					: StringUtils.EMPTY);
//		}
//		privateView.setEnabled(false);
//		publicView.setEnabled(false);
//		discount.addValueChangeListener(new Property.ValueChangeListener() {
//			@Override
//			public void valueChange(Property.ValueChangeEvent event) {
//				LOGGER.debug("Discount Value Change");
//				level.select(Constant.SELECT_ONE);
//				availableCustomerContainer.removeAllItems();
//				availableCustomer.removeAllItems();
//				selectedCustomerContainer.removeAllItems();
//				selectedCustomer.removeAllItems();
//			}
//		});

		customerLevel.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				if (!firstTimeLoad) {
					if (!selectedCustomerContainer.getItemIds().isEmpty() && customerLevelListenerFlag) {
						final String customerLevelValue = getSelectedCustomerLevel();
						new AbstractNotificationUtils() {

							@Override
							public void noMethod() {
								// do nothing
								if (!StringUtils.isBlank(customerLevelValue)) {
									customerLevelListenerFlag = false;
									customerLevel.select(customerLevelValue);
									customerLevelListenerFlag = true;
								}
							}

							@Override
							/**
							 * The method is triggered when Yes button of themessage box is pressed .
							 *
							 *
							 * @param buttonId
							 *            The buttonId of the pressed button.
							 */

							public void yesMethod() {
								customerLevelValueChange(event);
								setUpdateOnTabChange(true);
							}
						}.getConfirmationMessage(CONFIRM_CHANGE,
								"You have selected a new Forecast Level. Are you sure you want to proceed? You will lose the current Customer/Product hierarchies if you continue.");
					} else if (customerLevelListenerFlag) {
						customerLevelValueChange(event);
					}
				} else {
					customerLevelValueChange(event);
				}
			}
		});

		productLevel.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				if (!firstTimeLoad) {
					if (!selectedProductContainer.getItemIds().isEmpty() && productLevelListenerFlag) {
						final String productLevelValue = getSelectedProductLevel();
						new AbstractNotificationUtils() {
							@Override
							public void noMethod() {
								// do nothing
								if (!StringUtils.isBlank(productLevelValue)) {
									productLevelListenerFlag = false;
									productLevel.select(productLevelValue);
									productLevelListenerFlag = true;
								}
							}

							@Override
							/**
							 * The method is triggered when Yes button of the message box is pressed .
							 *
							 * @param buttonId
							 *            The buttonId of the pressed button.
							 */
							public void yesMethod() {
								productLevelValueChange(event);
								setUpdateOnTabChange(true);
							}
						}.getConfirmationMessage(CONFIRM_CHANGE,
								"You have selected a new Forecast Level. Are you sure you want to proceed? You will lose the current Customer/Product hierarchies if you continue.");
					} else if (productLevelListenerFlag) {
						productLevelValueChange(event);
					}
				} else {
					productLevelValueChange(event);
				}
			}
		});

		dataSelectionDeductionLevel.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				if (!firstTimeLoad) {
					if (dataSelectionDeductionLevelListenerFlag) {
						final String dataSelectionDedLevelValue = getSelectedDataSelectionDeductionLevel();
						new AbstractNotificationUtils() {

							@Override
							public void noMethod() {
								// do nothing
								if (!StringUtils.isBlank(dataSelectionDedLevelValue)) {
									dataSelectionDeductionLevelListenerFlag = false;
									dataSelectionDeductionLevel.select(Integer.valueOf(dataSelectionDedLevelValue));
									dataSelectionDeductionLevelListenerFlag = true;
								}
							}

							@Override
							/**
							 * The method is triggered when Yes button of the message box is pressed .
							 *
							 *
							 * @param buttonId
							 *            The buttonId of the pressed button.
							 */

							public void yesMethod() {
								dataSelectionDedLevelValueChange(event);
								setUpdateOnTabChange(true);
							}
						}.getConfirmationMessage(CONFIRM_CHANGE,
								"You have selected a new Deduction Level. Are you sure you want to proceed? You will lose the current Deduction Level if you continue.");
					}
				} else {
					dataSelectionDedLevelValueChange(event);
				}
			}
		});
		frequency.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				setUpdateOnTabChange(true);
			}
		});

		customRelationDdlb.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				if (customRelationDdlb.getValue() != null) {
					int custRelationValue = Integer.parseInt(customRelationDdlb.getValue().toString());
					dataSelectionDTO.setCustomRelationShipSid(custRelationValue);
					setCustomChange(true);
					setUpdateOnTabChange(false);
					customDdlb();
				} else {
					dataSelectionDTO.setCustomRelationShipSid(0);
					setUpdateOnTabChange(false);
					setCustomChange(false);
				}
			}
		});
		customRelationDdlbDeduction.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				if (customRelationDdlbDeduction.getValue() != null) {
					int custDeductionRelationValue = Integer
							.parseInt(customRelationDdlbDeduction.getValue().toString());
					dataSelectionDTO.setCustomDeductionRelationShipSid(custDeductionRelationValue);
					setUpdateOnTabChange(false);
					setCustomChange(true);
					setDedCustomChange(true);
					customDdlb();
				} else {
					dataSelectionDTO.setCustomDeductionRelationShipSid(0);
					setUpdateOnTabChange(false);
					setCustomChange(true);
					setDedCustomChange(false);
				}
			}

		});
		projectionName.setValue(selectionDTO.getProjectionName());
		description.setValue(selectionDTO.getDescription());
	}

	public void customDdlb() {
		if (customRelationDdlb.getValue() != null) {
			int custRelationValue = Integer.parseInt(customRelationDdlb.getValue().toString());
			dataSelectionDTO.setCustomRelationShipSid(custRelationValue);
		}
		if (customRelationDdlbDeduction.getValue() != null) {
			int custDeductionRelationValue = Integer.parseInt(customRelationDdlbDeduction.getValue().toString());
			dataSelectionDTO.setCustomDeductionRelationShipSid(custDeductionRelationValue);
		}
	}

	private void customerLevelValueChange(Property.ValueChangeEvent event) {
            
             Map<Integer, String> hierarchyLevelMapper = new HashMap<>();
               
                     hierarchyLevelMapper = DataSelectionForm.getCustomerHierarchyLookupWindow().getHierarchyDto().getHierarchyMap();
                
               
                ObjectMapper mapper = new ObjectMapper();

                Map<Integer, String> hierarchyLevelMap = mapper.convertValue(
                        hierarchyLevelMapper, new TypeReference<Map<Integer, String>>() {
                        });
                Object[] keySet = hierarchyLevelMap.keySet().toArray();
                int selectedLevelValue = Integer.valueOf(customerLevel.getValue().toString());
                for (int i = 0; i < selectedLevelValue; i++) {
                    level.addItem((int) keySet[i]);
                    level.setItemCaption((int) keySet[i], formHierarchyInnerLevelValues((int) keySet[i], hierarchyLevelMap.get(keySet[i])));
                }
//		customerInnerLevelContainer.removeAllItems();
//		if (event.getProperty().getValue() != null
//				&& !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
//			String selectedLevel = String.valueOf(event.getProperty().getValue());
//			String[] val = selectedLevel.split(" ");
//			int forecastLevel = Integer.parseInt(val[1]);
//			setSelectedCustomerLevel(selectedLevel);
//			session.setCustomerLevelNumber(String.valueOf(forecastLevel));
//			customerInnerLevelContainer.removeAllItems();
//			if (!firstTimeLoad) {
//				selectedCustomer.removeAllItems();
//				selectedCustomerContainer.removeAllItems();
//			}
//			customerBeanList.clear();
//			for (int i = 1; i <= forecastLevel; i++) {
//				String levelName = innerCustLevels.get(i - 1).getLevel();
//				customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
//			}
//			level.setContainerDataSource(customerInnerLevelContainer);
//			setCustomerLevelNullSelection();
//		} else if (event.getProperty().getValue() == null || (event.getProperty().getValue() != null
//				&& SELECT_ONE.equals(String.valueOf(event.getProperty().getValue())))) {
//			customerInnerLevelContainer.removeAllItems();
//			customerInnerLevelContainer.addItem(SELECT_ONE);
//			availableCustomer.removeAllItems();
//			availableCustomerContainer.removeAllItems();
//			selectedCustomer.removeAllItems();
//			selectedCustomerContainer.removeAllItems();
//			customerBeanList.clear();
//			setCustomerLevelNullSelection();
//		}
	}

         private String formHierarchyInnerLevelValues(int levelNo, String levelValue) {
        String levelName = "Level " + levelNo + " - " + levelValue;
       return levelName;
    }
         
	public void setFrequency(SessionDTO session) {
		frequency.setValue(session.getDsFrequency());
	}

	private void productLevelValueChange(Property.ValueChangeEvent event) {
		productInnerLevelContainer.removeAllItems();
		if (event.getProperty().getValue() != null
				&& !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
			String selectedLevel = String.valueOf(event.getProperty().getValue());
			setSelectedProductLevel(selectedLevel);
			String[] val = selectedLevel.split(" ");
			int forecastLevel = Integer.parseInt(val[1]);
			session.setProductLevelNumber(String.valueOf(forecastLevel));
			productInnerLevelContainer.removeAllItems();
			productBeanList.clear();
			if (!firstTimeLoad) {
				selectedProduct.removeAllItems();
				selectedProductContainer.removeAllItems();
			}
			for (int i = 1; i <= forecastLevel; i++) {
				String levelName = innerProdLevels.get(i - 1).getLevel();
				productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			}
			productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
			setProductLevelNullSelection();
		} else if (event.getProperty().getValue() == null || (event.getProperty().getValue() != null
				&& SELECT_ONE.equals(String.valueOf(event.getProperty().getValue())))) {
			productInnerLevelContainer.removeAllItems();
			productInnerLevelContainer.addItem(SELECT_ONE);
			availableProduct.removeAllItems();
			availableProductContainer.removeAllItems();
			selectedProduct.removeAllItems();
			selectedProductContainer.removeAllItems();
			productBeanList.clear();
			setProductLevelNullSelection();
		}
	}

	private void dataSelectionDedLevelValueChange(Property.ValueChangeEvent event) {
		if (event.getProperty().getValue() != null
				&& !SELECT_ONE.equals(String.valueOf(event.getProperty().getValue()))) {
			String selectedLevel = String.valueOf(event.getProperty().getValue());
			setSelectedDataSelectionDeductionLevel(selectedLevel);
		}
	}

	public final void configureOnLoading(int projectionId, DataSelectionDTO dataSelectionDTO) {
		session.setFromPeriod(dataSelectionDTO.getFromPeriod());
		session.setToPeriod(dataSelectionDTO.getToPeriod());
		session.setFromDate(dataSelectionDTO.getFromDate());
		session.setToDate(dataSelectionDTO.getToDate());
//		session.setProductHierarchyId(Integer.parseInt(dataSelectionDTO.getProdHierSid()));
//		if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
//			configureStartAndEndPeriods();
//		} else {
//			productLevel.setVisible(false);
//			customerLevel.setVisible(false);
//			productForecastLevelLabel.setVisible(false);
//			customerForecastLevelLabel.setVisible(false);
//			forecastEligibleDateLB.setVisible(false);
//			forecastEligibleDate.setVisible(false);
//			customRelation.setVisible(false);
//			customRelationDdlb.setVisible(false);
//			customRelationDiscount.setVisible(false);
//			customRelationDdlbDeduction.setVisible(false);
//		}
//		initializeProductHierarchy(projectionId, String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
		setFirstTimeLoad(true);
		initializeFromDto();
		setFirstTimeLoad(false);
		if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
			session.setCustomerHierarchyId(Integer.parseInt(dataSelectionDTO.getCustomerHierSid()!=null ? "0" : dataSelectionDTO.getCustomerHierSid()));
			initializeCustomerHierarchy(projectionId, String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
		}
	}

	public void configureOnTabLoad(int projectionId, DataSelectionDTO dataSelectionDTO) {
		try {
			customerDescriptionMap = session.getCustomerDescription();
			productDescriptionMap = session.getProductDescription();
			setFirstTimeLoad(true);
			initializeFromDto();
			setFirstTimeLoad(false);
			if (!CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(screenName)) {
				initializeCustomerHierarchy(projectionId, String.valueOf(dataSelectionDTO.getCustomerHierarchyLevel()));
			}
			initializeProductHierarchy(projectionId, String.valueOf(dataSelectionDTO.getProductHierarchyLevel()));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	private void configureStartAndEndPeriods() {
		CommonUtils.loadHistory(MONTHLY.getConstant(), MONTHS.getConstant(), session);
		CommonUtils.loadHistory(QUARTERLY.getConstant(), QUARTERS.getConstant(), session);
		CommonUtils.loadHistory(SEMI_ANNUALLY.getConstant(), SEMI_ANNUAL.getConstant(), session);
		CommonUtils.loadHistory(ANNUALLY.getConstant(), YEAR.getConstant(), session);
		CommonUtils.getProjectionNumber(MONTHLY.getConstant(), session);
		CommonUtils.getProjectionNumber(QUARTERLY.getConstant(), session);
		CommonUtils.getProjectionNumber(SEMI_ANNUALLY.getConstant(), session);
		CommonUtils.getProjectionNumber(ANNUALLY.getConstant(), session);
	}

	public void setFirstTimeLoad(boolean firstTimeLoad) {
		this.firstTimeLoad = firstTimeLoad;
	}

	public List<Leveldto> getCompanySidFromHierarchy(final boolean loadFromSelected, final boolean dsTabflag) {
		List<Leveldto> innerLevelValues = null;
		try {
			DataSelectionLogic logic = new DataSelectionLogic();

			if (dsTabflag) {
				int hierarchyId = 0;
				if (productHierarchyDto == null) {
					hierarchyId = UiUtils.parseStringToInteger(selectionDTO.getProdHierSid());
				} else {
					hierarchyId = productHierarchyDto.getHierarchyId();
				}
				if (innerProdLevels == null || innerProdLevels.isEmpty() || productHierarchyDto == null) {
					innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY,
							selectionDTO.getCustomerHierVersionNo());
				}

			}
			if ((innerProdLevels != null)
					&& (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue()))) {
				String relationshipSid = String.valueOf(productRelation.getValue());
				Leveldto companyLevel = null;
				for (Leveldto dto : innerProdLevels) {
					if (Constant.COMPANY_SMALL.equalsIgnoreCase(dto.getLevel()) || "GL Comp".contains(dto.getLevel())
							|| "GL Company".contains(dto.getLevel())) {
						companyLevel = dto;
						break;
					}
				}
				List<Integer> selectedLevelSids = null;
				if (loadFromSelected) {
					selectedLevelSids = DataSelectionUtil
							.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds());
				}

				if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
					discountDTO = (CompanyDdlbDto) discount.getValue();
				}

				if (companyLevel != null) {

					innerLevelValues = logic.loadInnerLevel(companyLevel.getLevel(),
							productHierarchyDto == null ? 0 : productHierarchyDto.getHierarchyId(), selectedLevelSids,
							false, companyLevel.getFieldName(), relationshipSid, productDescriptionMap,
							StringUtils.EMPTY, screenName, discountDTO != null ? discountDTO.getRsModelSid() : 0,
							companyLevel.getLevelNo(), StringUtils.EMPTY, StringUtils.EMPTY, company.getValue(),
							businessUnit.getValue());
				}
			}
		} catch (SystemException ex) {

			LOGGER.error("in getCompanySidFromHierarchy= {}", ex);
		}
		return innerLevelValues;
	}

	/**
	 * Load customer hierarchy.
	 *
	 * @param projectionId
	 * @throws java.lang.Exception
	 */
	private void initializeCustomerHierarchy(final int projectionId, final String customerLevel) {
		LOGGER.debug("Initializing Customer Hierarchy...");
		List<Leveldto> initialCustomerHierarchy = getInitialHierarchy(projectionId, Boolean.TRUE, customerLevel,
				session.getCustomerDescription());
		int forecastLevel = 0;
		forecastLevel = UiUtils.parseStringToInteger(customerLevel);
		customerBeanList.clear();
		for (Leveldto dto : initialCustomerHierarchy) {

			customerBeanList.add(dto.getRelationshipLevelSid());
		}

		for (Leveldto dto : initialCustomerHierarchy) {
			if (dto.getLevelNo() == 1) {
				selectedCustomerContainer.removeAllItems();
				selectedCustomerContainer.addItem(dto);
				selectedCustomerContainer.setChildrenAllowed(dto, true);
			} else {
				for (Object tempdto : selectedCustomerContainer.getItemIds()) {
					if (dto.getParentNode().contains("~")) {
						String[] parentarr = dto.getParentNode().split("~");
						String parentName = parentarr[1];
						int parentLevel = 0;
						try {
							parentLevel = Integer.parseInt(parentarr[0]);
						} catch (NumberFormatException ne) {
							LOGGER.info("Error While loading the Customer level is not a valid number.= {}",
									parentarr[0]);
						}
						Leveldto levelDto = DataSelectionUtil.getBeanFromId(tempdto);
						if (levelDto.getLevelNo() == parentLevel
								&& levelDto.getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
							selectedCustomerContainer.addBean(dto);
							if (forecastLevel != dto.getLevelNo()) {
								selectedCustomerContainer.setChildrenAllowed(dto, true);
							} else {
								selectedCustomerContainer.setChildrenAllowed(dto, false);
							}
							selectedCustomerContainer.setParent(dto, tempdto);
							break;
						}
					} else {
						selectedCustomerContainer.addBean(dto);
						if (forecastLevel != dto.getLevelNo()) {
							selectedCustomerContainer.setChildrenAllowed(dto, true);
						} else {
							selectedCustomerContainer.setChildrenAllowed(dto, false);
						}
						selectedCustomerContainer.setParent(dto, tempdto);
						break;
					}
				}
			}
		}
		selectedCustomer.setContainerDataSource(selectedCustomerContainer);
		selectedCustomer.setVisibleColumns(new Object[] { Constant.DISPLAY_VALUE_PROPERTY });
		selectedCustomer.setColumnHeaders(new String[] { "Customer Hierarchy Group Builder" });
		for (Leveldto ddo : selectedCustomerContainer.getItemIds()) {
			selectedCustomer.setCollapsed(ddo, false);
		}
	}

	/**
	 * Load customer hierarchy.
	 *
	 * @param projectionId
	 * @throws java.lang.Exception
	 */
	private void initializeProductHierarchy(final int projectionId, final String productLevel) {
		int forecastLevel = 0;
		forecastLevel = UiUtils.parseStringToInteger(productLevel);
		List<Leveldto> reslistOne;
		reslistOne = relationLogic.getRelationShipValues(projectionId, BooleanConstant.getFalseFlag(), productLevel,
				productDescriptionMap);
		productBeanList.clear();
		for (Leveldto dto : reslistOne) {

			productBeanList.add(dto.getRelationshipLevelSid());
		}

		for (Leveldto dto : reslistOne) {
			if (dto.getLevelNo() == 1) {
				selectedProductContainer.removeAllItems();
				selectedProductContainer.addItem(dto);
				selectedProductContainer.setChildrenAllowed(dto, true);
			} else {
				for (Object tempdto : selectedProductContainer.getItemIds()) {
					if (dto.getParentNode().contains("~")) {
						String[] parentarr = dto.getParentNode().split("~");
						String parentName = parentarr[1];
						int parentLevel = 0;
						try {
							parentLevel = Integer.parseInt(parentarr[0]);
						} catch (NumberFormatException nfe) {
							LOGGER.error("Error While loading the Product level is not a valid number.= {}",
									parentarr[0]);
						}
						Leveldto levelDto = DataSelectionUtil.getBeanFromId(tempdto);
						if (levelDto.getLevelNo() == parentLevel
								&& levelDto.getRelationshipLevelValue().equalsIgnoreCase(parentName)) {
							selectedProductContainer.addBean(dto);
							if (forecastLevel != dto.getLevelNo()) {
								selectedProductContainer.setChildrenAllowed(dto, true);
							} else {
								selectedProductContainer.setChildrenAllowed(dto, false);
							}
							selectedProductContainer.setParent(dto, tempdto);
							break;
						}
					} else {
						selectedProductContainer.addBean(dto);
						if (forecastLevel != dto.getLevelNo()) {
							selectedProductContainer.setChildrenAllowed(dto, true);
						} else {
							selectedProductContainer.setChildrenAllowed(dto, false);
						}
						selectedProductContainer.setParent(dto, tempdto);
						break;
					}
				}
			}
		}
		selectedProduct.setContainerDataSource(selectedProductContainer);
		selectedProduct.setVisibleColumns(new Object[] { Constant.DISPLAY_VALUE_PROPERTY });
		selectedProduct.setColumnHeaders(new String[] { "Product Hierarchy Group Builder" });
		for (Leveldto ddo : selectedProductContainer.getItemIds()) {
			selectedProduct.setCollapsed(ddo, false);
		}
	}

	private void loadCustomerLevel(final String hierarchyId, final int hierarchyVersion) {
		DataSelectionLogic logic = new DataSelectionLogic();
		innerCustLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY,
				hierarchyVersion);
		int levelNo = UiUtils.parseStringToInteger(selectionDTO.getCustomerHierarchyLevel());
		String selectedLevelName = innerCustLevels.get(levelNo - 1).getLevel();
		customerForecastLevelContainer.removeAllItems();
		for (int i = 1; i <= innerCustLevels.size(); i++) {
			String levelName = innerCustLevels.get(i - 1).getLevel();
			levelCheck(levelName);
			customerForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
		}
		session.setLowerMostCustomerLevelNo(innerCustLevels.size());
		customerLevel.setContainerDataSource(customerForecastLevelContainer);
		customerLevel.setNullSelectionAllowed(true);
		customerLevel.select(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
		setSelectedCustomerLevel(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
	}

	private void initializeFromDto() {
		try {
			if (selectionDTO != null) {
				description.setValue(selectionDTO.getDescription());
				projectionName.setValue(selectionDTO.getProjectionName());
				privateView.setValue(selectionDTO.getPrivateView());
				publicView.setValue(selectionDTO.getPublicView());
				session.setProductLevelNumber(String.valueOf(selectionDTO.getProductHierarchyLevel()));
				session.setCustomerLevelNumber(String.valueOf(selectionDTO.getCustomerHierarchyLevel()));
				customerRelationship();
				customerLevel();
				productRelationship();
				productLevel();

				productGroup();
				customerGroup();

				companySid();

				businessUnit.setValue(selectionDTO.getBusinessUnitSystemId());

				if (!String.valueOf(selectionDTO.getDiscount()).equals(Constant.NULL)) {
					CompanyDdlbDto selectedDiscountDTO = new CompanyDdlbDto(selectionDTO.getDiscountSid(),
							selectionDTO.getDiscount(), true);
					loadDiscountDdlb(selectionDTO.getDiscountSid(), selectedDiscountDTO);
				}

				forecastEligibleDate.setValue(selectionDTO.getForecastEligibleDate());
				dataSelectionDeductionLevel.setValue(selectionDTO.getDataSelectionDeductionLevelSid());

			}

		} catch (Property.ReadOnlyException ex) {
			LOGGER.error(" in initializeFromDto= {} ", ex);
		}
	}

	private void companySid() {
		if (selectionDTO.getCompanySid() == null
				|| Constants.CommonConstants.NULL.getConstant().equalsIgnoreCase(selectionDTO.getSelectedCompanyName())
				|| "0".equals(selectionDTO.getCompanySid())) {
			company.setValue(0);
		} else {
			company.setValue(selectionDTO.getCompanySid());
		}
	}

	private void customerGroup() {
		if (selectionDTO.getCustomerGrpSid() != null
				&& !StringUtils.EMPTY.equals(String.valueOf(selectionDTO.getCustomerGrpSid()))
				&& !Constants.CommonConstants.NULL.getConstant()
						.equals(String.valueOf(selectionDTO.getCustomerGrpSid()))) {
			selectedCustomerGroupDTO = new GroupDTO();
			selectedCustomerGroupDTO.setCustomerGroupSid(String.valueOf(selectionDTO.getCustomerGrpSid()));
			selectedCustomerGroupDTO.setCustomerGroupName(selectionDTO.getCustomerGroup());
			triggerCustGrpOnView(selectionDTO.getCustomerGrpSid(), true);
		}
	}

	private void productGroup() {
		if (selectionDTO.getProdGrpSid() != null
				&& !StringUtils.EMPTY.equals(String.valueOf(selectionDTO.getProdGrpSid()))
				&& !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(selectionDTO.getProdGrpSid()))) {
			selectedProductGroupDTO = new GroupDTO();
			selectedProductGroupDTO.setProductGroupSid(String.valueOf(selectionDTO.getProdGrpSid()));
			selectedProductGroupDTO.setProductGroupName(selectionDTO.getProductGroup());
			triggerProdGrpOnView(selectionDTO.getProdGrpSid(), true);
		}
	}

	private void productLevel() {
		if (selectionDTO.getProdHierSid() != null
				&& !StringUtils.EMPTY.equals(String.valueOf(selectionDTO.getProdHierSid()))
				&& !Constants.CommonConstants.NULL.getConstant()
						.equals(String.valueOf(selectionDTO.getProdHierSid()))) {
			productHierarchyDto = new HierarchyLookupDTO();
			productHierarchyDto.setHierarchyId(UiUtils.parseStringToInteger(selectionDTO.getProdHierSid()));
			productHierarchyDto.setHierarchyName(selectionDTO.getProductHierarchy());
			productHierarchy.setValue(productHierarchyDto.getHierarchyName());
			loadProductLevel();
			if (!StringUtils.isBlank(selectionDTO.getProductHierarchyInnerLevel()) && !Constants.CommonConstants.NULL
					.getConstant().equals(selectionDTO.getProductHierarchyInnerLevel())) {
				loadInnerProductLevel();
			}
		}
	}

	private void customerLevel() {
		if (selectionDTO.getCustomerHierSid() != null
				&& !StringUtils.EMPTY.equals(String.valueOf(selectionDTO.getCustomerHierSid()))
				&& !Constants.CommonConstants.NULL.getConstant()
						.equals(String.valueOf(selectionDTO.getCustomerHierSid()))) {
			customerHierarchyDto = new HierarchyLookupDTO();
			customerHierarchyDto.setHierarchyId(UiUtils.parseStringToInteger(selectionDTO.getCustomerHierSid()));
			customerHierarchyDto.setHierarchyName(selectionDTO.getCustomerHierarchy());
			customerHierarchy.setValue(customerHierarchyDto.getHierarchyName());
			loadCustomerLevel();
			if (!StringUtils.isBlank(selectionDTO.getCustomerHierarchyInnerLevel()) && !Constants.CommonConstants.NULL
					.getConstant().equals(selectionDTO.getCustomerHierarchyInnerLevel())) {
				loadInnerCustomerLevel();
			}
		}
	}

	private void loadProductLevel() {
		try {
			loadProductLevel(String.valueOf(selectionDTO.getProdHierSid()), selectionDTO.getProductHierVersionNo());
		} catch (Exception ex) {
			LOGGER.error("in load product level= {} ", ex);
		}
	}

	private void loadCustomerLevel() {
		try {
			loadCustomerLevel(String.valueOf(selectionDTO.getCustomerHierSid()),
					selectionDTO.getCustomerHierVersionNo());
		} catch (Exception ex) {
			LOGGER.error("in load customer level= {} ", ex);
		}
	}

	private void loadInnerCustomerLevel() {
		try {
			loadInnerCustomerLevel(UiUtils.parseStringToInteger(selectionDTO.getCustomerHierarchyLevel()),
					UiUtils.parseStringToInteger(selectionDTO.getCustomerHierarchyInnerLevel()));
		} catch (Exception ex) {
			LOGGER.error(" in load inner customer level= {} ", ex);
		}
	}

	private void loadInnerProductLevel() {
		try {
			loadInnerProductLevel(UiUtils.parseStringToInteger(selectionDTO.getProductHierarchyLevel()),
					UiUtils.parseStringToInteger(selectionDTO.getProductHierarchyInnerLevel()));
		} catch (Exception ex) {
			LOGGER.error(" in load inner product level= {} ", ex);
		}
	}

	private void productRelationship() {
		try {
			if (selectionDTO.getProdHierSid() != null
					&& !StringUtils.EMPTY.equals(String.valueOf(selectionDTO.getProdHierSid()))
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(selectionDTO.getProdHierSid()))) {
				RelationshipDdlbDto selectedRelationshipDdlbDto = null;
				if (!StringUtils.isBlank(selectionDTO.getProdRelationshipBuilderSid())
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(selectionDTO.getProdRelationshipBuilderSid())) {
					selectedRelationshipDdlbDto = new RelationshipDdlbDto();
					selectedRelationshipDdlbDto.setRelationshipBuilderSid(selectionDTO.getProdRelationshipBuilderSid());
					session.setProdRelationshipBuilderSid(selectionDTO.getProdRelationshipBuilderSid());
				}
				loadSelectedProductLevel(selectedRelationshipDdlbDto);
			}
		} catch (Exception ex) {
			LOGGER.error(" in Product Relationship= {} ", ex);
		}
	}

	private void loadSelectedProductLevel(RelationshipDdlbDto selectedRelationshipDdlbDto) {
		try {
			DataSelectionForm.loadRelationDdlb(UiUtils.parseStringToInteger(selectionDTO.getProdHierSid()),
					selectedRelationshipDdlbDto, productRelation);
		} catch (Exception ex) {
			LOGGER.error(" in product relationship selected level load= {} ", ex);

		}
	}

	private void customerRelationship() {
		try {
			if (selectionDTO.getCustomerHierSid() != null
					&& !StringUtils.EMPTY.equals(String.valueOf(selectionDTO.getCustomerHierSid()))
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(selectionDTO.getCustomerHierSid()))) {

				RelationshipDdlbDto selectedRelationshipDdlbDto = null;
				if (!StringUtils.isBlank(selectionDTO.getCustRelationshipBuilderSid())
						&& !Constants.CommonConstants.NULL.getConstant()
								.equals(selectionDTO.getCustRelationshipBuilderSid())) {
					selectedRelationshipDdlbDto = new RelationshipDdlbDto();
					selectedRelationshipDdlbDto.setRelationshipBuilderSid(selectionDTO.getCustRelationshipBuilderSid());
					session.setCustRelationshipBuilderSid(selectionDTO.getCustRelationshipBuilderSid());
				}
				loadSelectedCustomersLevel(selectedRelationshipDdlbDto);
			}
		} catch (Exception ex) {
			LOGGER.error(" in customer relationship= {} ", ex);
		}
	}

	private void loadSelectedCustomersLevel(RelationshipDdlbDto selectedRelationshipDdlbDto) {
		try {
			DataSelectionForm.loadRelationDdlb(UiUtils.parseStringToInteger(selectionDTO.getCustomerHierSid()),
					selectedRelationshipDdlbDto, customerRelationComboBox);
		} catch (Exception ex) {
			LOGGER.error(" in customer relationship selected level load= {} ", ex);

		}
	}

	/**
	 * Manual trigger and processing of customer group select button logic Code
	 * based on customer group select button logic Any change made there, should be
	 * made here accordingly
	 *
	 * @param customerGrpSid
	 */
	private void triggerCustGrpOnView(String customerGrpSid, final boolean filter) {

		List<String> companySids = null;
		List<String> customerSidsFromDetails = null;
		DataSelectionLogic logic = new DataSelectionLogic();
		try {
			if (!StringUtils.isBlank(customerGrpSid) && !NULL.equalsIgnoreCase(customerGrpSid)) {
				companySids = DataSelectionUtil.getCustomerSidFromHierarchy(getCustomersFromHierarchy());
				if (companySids != null) {
					List<String> finalCompanySids = new ArrayList<>(companySids);
					customerSidsFromDetails = logic.getCustomerGroupDetails(Integer.parseInt(customerGrpSid));
					finalCompanySids.retainAll(customerSidsFromDetails);
					groupFilteredCompanies = new ArrayList<>(finalCompanySids);
				}
				if (filter) {
					filterCustomerOnGroupSelect();
				}
			}
		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error("at triggerCustGrpOnView= {}", ex);
		}
	}

	private void loadProductLevel(final String hierarchyId, final int hierarchyVersion) {
		DataSelectionLogic logic = new DataSelectionLogic();
		innerProdLevels = logic.loadCustomerForecastLevel(Integer.parseInt(hierarchyId), StringUtils.EMPTY,
				hierarchyVersion);
		int levelNo = UiUtils.parseStringToInteger(selectionDTO.getProductHierarchyLevel());
		String selectedLevelName = innerProdLevels.get(levelNo - 1).getLevel();
		productForecastLevelContainer.removeAllItems();
		for (int i = 1; i <= innerProdLevels.size(); i++) {
			String levelName = innerProdLevels.get(i - 1).getLevel();
			productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
		}
		session.setLowerMostProductLevelNo(innerProdLevels.size());
		productLevel.setContainerDataSource(productForecastLevelContainer);
		productLevel.setNullSelectionAllowed(true);
		productLevel.select(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
		setSelectedProductLevel(Constant.LEVEL + (levelNo) + " - " + selectedLevelName);
	}

	/**
	 * Manual trigger and processing of product group select button logic Code based
	 * on product group select button logic Any change made there, should be made
	 * here accordingly
	 *
	 * @param productGrpSid
	 */
	private void triggerProdGrpOnView(String productGrpSid, final boolean filter) {
		try {
			if (!StringUtils.isBlank(productGrpSid) && !NULL.equalsIgnoreCase(productGrpSid)) {
				List<String> itemSids = null;
				List<String> itemSidsFromDetails = null;
				DataSelectionLogic logic = new DataSelectionLogic();
				itemSids = DataSelectionUtil.getItemSidFromHierarchy(getItemSidFromHierarchy());
				if (itemSids != null) {
					List<String> finalItemSids = new ArrayList<>(itemSids);
					itemSidsFromDetails = logic.getItemGroupDetails(Integer.parseInt(productGrpSid));
					finalItemSids.retainAll(itemSidsFromDetails);
					groupFilteredItems = new ArrayList<>(finalItemSids);
				}
				if (filter && productlevelDdlb.getValue() != null
						&& !String.valueOf(SELECT_ONE).equalsIgnoreCase(String.valueOf(productlevelDdlb.getValue()))) {
					loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()));
				}
			}
		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error("at triggerProdGrpOnView= {}", ex);
		}
	}

	private void loadInnerCustomerLevel(int forecastLevel, int innerLevel) {
		String selectedLevelName = StringUtils.EMPTY;
		customerInnerLevelContainer.removeAllItems();
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerCustLevels.get(i - 1).getLevel();
			customerInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
				break;
			}
		}
		level.setContainerDataSource(customerInnerLevelContainer);
		level.select(Constant.LEVEL + innerLevel + " - " + selectedLevelName);
	}

	private void loadInnerProductLevel(int forecastLevel, int innerLevel) {
		String selectedLevelName = StringUtils.EMPTY;
		productInnerLevelContainer.removeAllItems();
		for (int i = 1; i <= forecastLevel; i++) {
			String levelName = innerProdLevels.get(i - 1).getLevel();
			productInnerLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
			if (i == innerLevel) {
				selectedLevelName = levelName;
				break;
			}
		}
		productlevelDdlb.setContainerDataSource(productInnerLevelContainer);
		productlevelDdlb.select(Constant.LEVEL + innerLevel + " - " + selectedLevelName);
	}

	public boolean isDataSelectionValid() {

		if (!screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {

			if (getSelectedCustomers() != null && !getSelectedCustomers().isEmpty() && getSelectedProducts() != null
					&& !getSelectedProducts().isEmpty()
					&& (company.getValue() != null && !Constant.SELECT_ONE.equals(company.getValue()))
					&& (businessUnit.getValue() != null && !Constant.SELECT_ONE.equals(businessUnit.getValue()))
					&& dataSelectionDeductionLevel.getValue() != null) {
				setValid(BooleanConstant.getTrueFlag());
			} else {
				setValid(BooleanConstant.getFalseFlag());
			}
		} else {
			isReturnsDataSelectionValid();
		}
		return isValid();
	}

	public boolean isReturnsDataSelectionValid() {

		if (getSelectedProducts() != null && !getSelectedProducts().isEmpty()
				&& (company.getValue() != null && !Constant.SELECT_ONE.equals(company.getValue()))
				&& (businessUnit.getValue() != null && !Constant.SELECT_ONE.equals(businessUnit.getValue()))) {
			setValid(BooleanConstant.getTrueFlag());
		} else {
			setValid(BooleanConstant.getFalseFlag());
		}
		return isValid();
	}

	public boolean isUpdateOnTabChange() {
		return updateOnTabChange;
	}

	public void setUpdateOnTabChange(boolean updateOnTabChange) {
		session.setSaveFlag(updateOnTabChange);
		this.updateOnTabChange = updateOnTabChange;
	}

	public boolean isReloadAfterUpdate() {
		return reloadAfterUpdate;
	}

	public void setReloadAfterUpdate(boolean reloadAfterUpdate) {
		this.reloadAfterUpdate = reloadAfterUpdate;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<Leveldto> getSelectedCustomers() {
		return selectedCustomerContainer.getItemIds();
	}

	public List<Leveldto> getSelectedProducts() {
		return selectedProductContainer.getItemIds();
	}

	public int getTabNumber() {
		return Constant.ZERO;
	}

	public boolean isCustomChange() {
		return customChange;
	}

	public void setCustomChange(boolean customChange) {
		this.customChange = customChange;
	}

	public boolean isDedCustomChange() {
		return dedCustomChange;
	}

	public void setDedCustomChange(boolean dedCustomChange) {
		this.dedCustomChange = dedCustomChange;
	}

	public void updateBasicsProjectionMaster() throws PortalException {
		NonMandatedLogic logic = new NonMandatedLogic();
		selectionDTO = bindDataselectionDtoToSave();
		logic.updateBasicsProjectionMaster(selectionDTO, session.getProjectionId(), true);
	}

	public void updateDataSelection() throws PortalException, ClassNotFoundException, IOException {
		LOGGER.debug("updateDataSelection starts");
		selectionDTO = bindDataselectionDtoToSave();
		NonMandatedLogic logic = new NonMandatedLogic();
		logic.saveProjection(selectionDTO, screenName, true);
		session.setCustRelationshipBuilderSid(selectionDTO.getCustRelationshipBuilderSid());
		session.setProdRelationshipBuilderSid(selectionDTO.getProdRelationshipBuilderSid());
		session.setCustomerDescription(customerDescriptionMap);
		session.setProductDescription(productDescriptionMap);
		session.setCustomerHierarchyVersion(selectionDTO.getCustomerHierVersionNo());
		session.setProductHierarchyVersion(selectionDTO.getProductHierVersionNo());
		session.setCustomerRelationVersion(selectionDTO.getCustomerRelationShipVersionNo());
		session.setProductRelationVersion(selectionDTO.getProductRelationShipVersionNo());
		session.setCustomRelationShipSid(selectionDTO.getCustomRelationShipSid());
		session.setCustomDeductionRelationShipSid(selectionDTO.getCustomDeductionRelationShipSid());
		session.setDsFrequency(String.valueOf(frequency.getValue()));
		session.setDataSelectionDeductionLevel(selectedDataSelectionDeductionLevel);
		selectionDTO.setProjectionId(session.getProjectionId());
		selectionDTO.setSelectedCustomerRelationSid(getRelationshipSid(selectedCustomerContainer.getItemIds()));
		selectionDTO.setSelectedProductRelationSid(getRelationshipSid(selectedProductContainer.getItemIds()));
		if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)
				|| CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName)) {
			updateDataSelectionChanges();
		}
		LOGGER.debug("updateDataSelection ends");
	}

	public DataSelectionDTO bindDataselectionDtoToSave() {
		LOGGER.debug("bindDataselectionDtoToSave starts");
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
			SimpleDateFormat format = new SimpleDateFormat(Constants.CommonConstants.DATE_FORMAT.getConstant());
			if (company.getValue() != null && !SELECT_ONE.equals(company.getValue())) {

				selectionDTO.setCompanySid(String.valueOf(company.getValue()));
			} else {
				selectionDTO.setCompanySid(String.valueOf(0));
			}

			if (businessUnit.getValue() != null && !SELECT_ONE.equals(businessUnit.getValue())) {
				selectionDTO.setBusinessUnitSystemId((Integer) businessUnit.getValue());
			} else {
				selectionDTO.setBusinessUnitSystemId(0);
			}

			if (customerRelationComboBox.getValue() != null
					&& !SELECT_ONE.equals(customerRelationComboBox.getValue())) {

				selectionDTO.setCustRelationshipBuilderSid(String.valueOf(customerRelationComboBox.getValue()));
				loadCustomerVersionNo(customerRelationComboBox.getValue());
			} else {
				selectionDTO.setCustRelationshipBuilderSid(String.valueOf(0));
			}
			if (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue())) {

				selectionDTO.setProdRelationshipBuilderSid(String.valueOf(productRelation.getValue()));
				loadProductVersionNo(productRelation.getValue());
			} else {
				selectionDTO.setProdRelationshipBuilderSid(String.valueOf(0));
			}
			if (customerHierarchyDto != null
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(customerHierarchyDto.getHierarchyId()))
					&& !StringUtils.isEmpty(String.valueOf(customerHierarchyDto.getHierarchyId()))
					&& !StringUtils.isBlank(String.valueOf(customerHierarchyDto.getHierarchyId()))) {
				selectionDTO.setCustomerHierSid(String.valueOf(customerHierarchyDto.getHierarchyId()));
			} else {
				selectionDTO.setCustomerHierSid(String.valueOf(0));
			}
			if (productHierarchyDto != null
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(productHierarchyDto.getHierarchyId()))
					&& !StringUtils.isEmpty(String.valueOf(productHierarchyDto.getHierarchyId()))
					&& !StringUtils.isBlank(String.valueOf(productHierarchyDto.getHierarchyId()))) {
				selectionDTO.setProdHierSid(String.valueOf(productHierarchyDto.getHierarchyId()));
			} else {
				selectionDTO.setProdHierSid(String.valueOf(0));
			}

			if (selectedCustomerGroupDTO != null
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
					&& !StringUtils.isEmpty(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))
					&& !StringUtils.isBlank(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()))) {
				selectionDTO.setCustomerGrpSid(String.valueOf(selectedCustomerGroupDTO.getCustomerGroupSid()));
			} else {
				selectionDTO.setCustomerGrpSid(String.valueOf(0));
			}
			if (selectedProductGroupDTO != null
					&& !Constants.CommonConstants.NULL.getConstant()
							.equals(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
					&& !StringUtils.isEmpty(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))
					&& !StringUtils.isBlank(String.valueOf(selectedProductGroupDTO.getProductGroupSid()))) {
				selectionDTO.setProdGrpSid(String.valueOf(selectedProductGroupDTO.getProductGroupSid()));
			} else {
				selectionDTO.setProdGrpSid(String.valueOf(0));
			}

			int customerForecastLevel = 0;
			int productForecastLevel = 0;
			int customerForecastInnerLevel = 0;
			int productForecastInnerLevel = 0;
			if (customerLevel.getValue() != null && !SELECT_ONE.equals(customerLevel.getValue())) {
				customerForecastLevel = UiUtils
						.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
			}
			if (productLevel.getValue() != null && !SELECT_ONE.equals(productLevel.getValue())) {
				productForecastLevel = UiUtils
						.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
			}
			if (level.getValue() != null && !SELECT_ONE.equals(level.getValue())) {
				customerForecastInnerLevel = UiUtils
						.parseStringToInteger(String.valueOf(level.getValue()).split("-")[0]);
			}
			if (productlevelDdlb.getValue() != null && !SELECT_ONE.equals(productlevelDdlb.getValue())) {
				productForecastInnerLevel = UiUtils
						.parseStringToInteger(String.valueOf(productlevelDdlb.getValue()).split("-")[0]);
			}
			if (customerHierarchyDto != null && customerRelationVersionComboBox.getValue() != null) {
				int custHierarchyVersionNo = Integer
						.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				selectionDTO.setCustomerHierVersionNo(custHierarchyVersionNo);
			} else {
				selectionDTO.setCustomerHierVersionNo(0);
			}
			if (productHierarchyDto != null && productRelationVersionComboBox.getValue() != null) {
				int prodHierarchyVersionNo = Integer
						.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				selectionDTO.setProductHierVersionNo(prodHierarchyVersionNo);
			} else {
				selectionDTO.setProductHierVersionNo(0);
			}
			if (fromPeriod.getData() == null) {
				if (fromPeriod.getValue() != null) {
					if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
						selectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));
						if (!String.valueOf(fromPeriod.getValue()).isEmpty()
								&& !Constant.SELECT_ONE.equals(String.valueOf(fromPeriod.getValue()))) {
							selectionDTO.setFromDate(format.parse(
									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
						}
					} else {
						selectionDTO.setFromPeriod(
								DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
						selectionDTO.setFromDate(format
								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
					}

				}
			} else if (fromPeriod.getValue() != null) {
				if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
					selectionDTO.setFromPeriod(String.valueOf(fromPeriod.getValue()));
					if (!String.valueOf(fromPeriod.getValue()).isEmpty()
							&& !Constant.SELECT_ONE.equals(String.valueOf(fromPeriod.getValue()))) {
						selectionDTO.setFromDate(format
								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
					}
				} else {
					// Getting Current Year & Period Value
					Date dateFromValue = format
							.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
					int quarterFromValue = DataSelectionUtil.getQuarter(dateFromValue);
					int yearFromValue = DataSelectionUtil.getYearFromDate(dateFromValue);
					// Getting Existing Year & Period Value
					Date date = inputFormat.parse(String.valueOf(fromPeriod.getData()));
					int quarterFromFc = DataSelectionUtil.getQuarter(date);
					int yearFromFc = DataSelectionUtil.getYearFromDate(date);
					// Comparing Existing year Value with New Value
					if (yearFromValue == yearFromFc) {
						// Comparing Existing year Period with New Year Period
						if (quarterFromValue == quarterFromFc) {
							String outputString = format.format(date);
							selectionDTO.setFromPeriod(outputString);
							selectionDTO.setFromDate(format.parse(outputString));
						} else {
							selectionDTO.setFromPeriod(
									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
							selectionDTO.setFromDate(format.parse(
									DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
						}
					} else {
						selectionDTO.setFromPeriod(
								DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue())));
						selectionDTO.setFromDate(format
								.parse(DataSelectionUtil.getDateFromQuarter(String.valueOf(fromPeriod.getValue()))));
					}
				}

			}
			if (toPeriod.getData() == null) {
				if (toPeriod.getValue() != null) {
					if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
						selectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
						if (!String.valueOf(toPeriod.getValue()).isEmpty()
								&& !Constant.SELECT_ONE.equals(String.valueOf(toPeriod.getValue()))) {
							selectionDTO.setToDate(format.parse(
									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
						}
					} else {
						selectionDTO.setToPeriod(
								DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
						selectionDTO.setToDate(format
								.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
					}
				}
			} else if (toPeriod.getValue() != null) {
				if (MODE_SEARCH.getConstant().equalsIgnoreCase(String.valueOf(modeOption.getValue()))) {
					selectionDTO.setToPeriod(String.valueOf(toPeriod.getValue()));
					if (!String.valueOf(toPeriod.getValue()).isEmpty()
							&& !Constant.SELECT_ONE.equals(String.valueOf(toPeriod.getValue()))) {
						Date date = inputFormat.parse(String.valueOf(toPeriod.getData()));
						String outputString = format.format(date);
						selectionDTO.setToDate(format.parse(outputString));
					}
				} else {
					Date date = inputFormat.parse(String.valueOf(toPeriod.getData()));
					Date dateToValue = format
							.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
					int quarterToValue = DataSelectionUtil.getQuarter(dateToValue);
					int yearToValue = DataSelectionUtil.getYearFromDate(dateToValue);
					int quarterToFc = DataSelectionUtil.getQuarter(date);
					int yearToFc = DataSelectionUtil.getYearFromDate(dateToValue);
					if (yearToValue == yearToFc) {
						if (quarterToValue == quarterToFc) {
							String outputString = format.format(date);
							selectionDTO.setToPeriod(outputString);
							selectionDTO.setToDate(format.parse(outputString));
						} else {
							selectionDTO.setToPeriod(
									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
							selectionDTO.setToDate(format.parse(
									DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
						}
					} else {
						selectionDTO.setToPeriod(
								DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue())));
						selectionDTO.setToDate(format
								.parse(DataSelectionUtil.getLastDateFromQuarter(String.valueOf(toPeriod.getValue()))));
					}
				}
			}

			if (discount.getValue() != null && !SELECT_ONE.equals(discount.getValue())
					&& StringUtils.isNotBlank(discount.getValue().toString())) {
				CompanyDdlbDto dto = (CompanyDdlbDto) discount.getValue();
				selectionDTO.setDiscount(dto.getRsNo());
				selectionDTO.setDiscountSid(dto.getRsModelSid());
			} else {
				selectionDTO.setDiscount(SELECT_ONE);
				selectionDTO.setDiscountSid(0);
			}

			selectionDTO.setCustomerHierarchyLevel(String.valueOf(customerForecastLevel));
			selectionDTO.setProductHierarchyLevel(String.valueOf(productForecastLevel));
			selectionDTO.setCustomerHierarchyInnerLevel(String.valueOf(customerForecastInnerLevel));
			selectionDTO.setProductHierarchyInnerLevel(String.valueOf(productForecastInnerLevel));
			selectionDTO.setProjectionName(projectionName.getValue());
			selectionDTO.setDescription(description.getValue());
			selectionDTO.setForecastEligibleDate(forecastEligibleDate.getValue());
			selectionDTO.setCustomRelationShipSid(customRelationDdlb.getValue() != null
					? Integer.parseInt(String.valueOf(customRelationDdlb.getValue()))
					: 0);
			selectionDTO.setCustomDeductionRelationShipSid(customRelationDdlbDeduction.getValue() != null
					? Integer.parseInt(String.valueOf(customRelationDdlbDeduction.getValue()))
					: 0);

		} catch (ParseException ex) {
			LOGGER.error(" in binding for save, can't parse dates= {}", ex);
		}
		LOGGER.debug("bindDataselectionDtoToSave ends");
		return selectionDTO;
	}

	public void updateDataSelectionProjectionTables(final String propertyName) throws SystemException {
		DataSelectionLogic dsLogicProj = new DataSelectionLogic();
		List<Leveldto> initialCustomerHierarchy = getInitialHierarchy(session.getProjectionId(), Boolean.TRUE,
				selectionDTO.getCustomerHierarchyLevel(), customerDescriptionMap);
		List<Leveldto> initialProductHierarchy = getInitialHierarchy(session.getProjectionId(), Boolean.FALSE,
				selectionDTO.getProductHierarchyLevel(), productDescriptionMap);
		List<String> customerRemovedLevels = DataSelectionUtil.getRemovedLevelsHno(initialCustomerHierarchy,
				getSelectedCustomers());
		List<String> productRemovedLevels = DataSelectionUtil.getRemovedLevelsHno(initialProductHierarchy,
				getSelectedProducts());
		List<Integer> projectionDetailsSid = new ArrayList<>();
		if (!customerRemovedLevels.isEmpty()) {
			projectionDetailsSid.addAll((List<Integer>) HelperTableLocalServiceUtil.executeSelectQuery(SQlUtil
					.getQuery("getProjectionDetailsQuery").replace("@HIERARCHY_TABLE", "PROJECTION_CUST_HIERARCHY")
					.replace("@PROJECTION_SID", String.valueOf(session.getProjectionId()))
					.replace("@HIERARCHYNO", CommonUtils.CollectionToString(customerRemovedLevels, true))));
		}
		if (!productRemovedLevels.isEmpty()) {
			projectionDetailsSid.addAll((List<Integer>) HelperTableLocalServiceUtil.executeSelectQuery(SQlUtil
					.getQuery("getProjectionDetailsQuery").replace("@HIERARCHY_TABLE", "PROJECTION_PROD_HIERARCHY")
					.replace("@PROJECTION_SID", String.valueOf(session.getProjectionId()))
					.replace("@HIERARCHYNO", CommonUtils.CollectionToString(productRemovedLevels, true))));
		}
		if (!customerRemovedLevels.isEmpty()) {
			dsLogicProj.deleteTempOnUpdate("PROJECTION_CUST_HIERARCHY", session.getProjectionId(),
					UiUtils.stringListToString(customerRemovedLevels));
		}
		if (!productRemovedLevels.isEmpty()) {
			dsLogicProj.deleteTempOnUpdate("PROJECTION_PROD_HIERARCHY", session.getProjectionId(),
					UiUtils.stringListToString(productRemovedLevels));
		}

		setRelationshipBuilderSids(String.valueOf(customerRelationComboBox.getValue()));
		setRelationshipBuilderSids(String.valueOf(productRelation.getValue()));
		dsLogicProj.updateCustomerHierarchyLogic(getSelectedCustomers(),
				DataSelectionUtil
						.getEndLevelHierNo(dataSelectionForm.getCustomerHierarchyEndLevels(selectedCustomerContainer)),
				session.getProjectionId(),
				DataSelectionUtil.getRemovedLevelsRsid(initialCustomerHierarchy, getSelectedCustomers()),
				DataSelectionUtil.getNewLevelRsid(initialCustomerHierarchy, getSelectedCustomers()));
		dsLogicProj.updateProductHierarchyLogic(getSelectedProducts(),
				DataSelectionUtil
						.getEndLevelHierNo(dataSelectionForm.getProductHierarchyEndLevels(selectedProductContainer)),
				session.getProjectionId(),
				DataSelectionUtil.getRemovedLevelsRsid(initialProductHierarchy, getSelectedProducts()),
				DataSelectionUtil.getNewLevelRsid(initialProductHierarchy, getSelectedProducts()), selectionDTO);

		dsLogicProj.updateCcpLogic(dataSelectionForm.getCustomerHierarchyEndLevels(selectedCustomerContainer),
				dataSelectionForm.getProductHierarchyEndLevelsHierNo(selectedProductContainer),
				Constant.CUSTOMER1_SMALL, session.getProjectionId());

		if (!projectionDetailsSid.isEmpty()) {
			deleteProjectionDetailstable(CommonUtils.CollectionToString(projectionDetailsSid, true), propertyName);
		}
	}

	private List<Leveldto> getInitialHierarchy(final int projectionId, Boolean indicator, final String level,
			final Map<String, String> descriptionMap) {
		List<Leveldto> initialHierarchy = relationLogic.getRelationShipValues(projectionId, indicator, level,
				descriptionMap);
		return initialHierarchy;
	}

	private void setRelationshipBuilderSids(String rbSid) {

		relationshipBuilderSids.add(rbSid);
	}

	@Override
	protected void productLevelDdlbValueChange(String selectedLevel, boolean flag) {
		loadFilteredProductSelection(selectedLevel);
	}

	private List<Leveldto> getItemSidFromHierarchy() {
		List<Leveldto> innerLevelValues = null;
		try {
			int hierarchyId = 0;
			DataSelectionLogic logic = new DataSelectionLogic();
			if (productHierarchyDto == null) {
				hierarchyId = UiUtils.parseStringToInteger(selectionDTO.getProdHierSid());
			} else {
				hierarchyId = productHierarchyDto.getHierarchyId();
			}
			if (innerProdLevels == null || innerProdLevels.isEmpty() || productHierarchyDto == null) {
				innerProdLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY,
						selectionDTO.getCustomerHierVersionNo());
			}
			if ((innerProdLevels != null)
					&& (productRelation.getValue() != null && !SELECT_ONE.equals(productRelation.getValue()))) {

				String relationshipSid = String.valueOf(productRelation.getValue());
				Leveldto ndcLevel = null;
				for (Leveldto dto : innerProdLevels) {
					if (Constant.NDC.equalsIgnoreCase(dto.getLevel()) || "Item".equalsIgnoreCase(dto.getLevel())) {
						ndcLevel = dto;
						break;
					}
				}
				if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
					discountDTO = (CompanyDdlbDto) discount.getValue();
				}
				if (ndcLevel != null) {

					String dedLevel = StringUtils.EMPTY;
					String dedValue = StringUtils.EMPTY;
					if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
						dedLevel = Constant.DEDUCTION_PROGRAM_TYPE1
								.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))
										? Constant.REBATE_PROGRAM_TYPE1
										: Constant.DEDUCTION_CATEGORY1
												.equalsIgnoreCase(String.valueOf(deductionLevel.getValue()))
														? Constant.RS_CATEGORY1
														: Constant.DEDUCTION_SCHEDULE_TYPE1.equalsIgnoreCase(
																String.valueOf(deductionLevel.getValue()))
																		? Constant.RS_TYPE
																		: StringUtils.EMPTY;
						dedValue = String.valueOf(deductionValue.getValue());
					}
					innerLevelValues = logic.loadInnerLevel(ndcLevel.getLevel(),
							productHierarchyDto == null ? 0 : productHierarchyDto.getHierarchyId(),
							DataSelectionUtil.getSelectedRelationshipLevelSids(selectedProductContainer.getItemIds()),
							true, ndcLevel.getFieldName(), relationshipSid, productDescriptionMap,
							INDICATOR_LEVEL_NDC.getConstant(), screenName,
							discountDTO != null ? discountDTO.getRsModelSid() : 0, ndcLevel.getLevelNo(), dedValue,
							dedLevel, company.getValue(), businessUnit.getValue());
				}
			}
		} catch (SystemException ex) {
			LOGGER.error("in getItemSidFromHierarchy= {}", ex);
		}
		return innerLevelValues;
	}

	@Override
	protected void customerHierarchyLookUp() {
		final HierarchyLookup customerHierarchyLookupWindow = new HierarchyLookup(
				INDICATOR_CUSTOMER_HIERARCHY.getConstant(), WINDOW_CUSTOMER_HIERARCHY_LOOKUP.getConstant(),
				customerHierarchy, customerHierarchyDto);
		UI.getCurrent().addWindow(customerHierarchyLookupWindow);
                
                DataSelectionForm.setCustomerHierarchyLookupWindow(customerHierarchyLookupWindow);
		customerHierarchyLookupWindow.addCloseListener(new Window.CloseListener() {
			@Override
			public void windowClose(Window.CloseEvent e) {
				if (customerHierarchyLookupWindow.getHierarchyDto() != null) {
					final HierarchyLookupDTO lookupDto = customerHierarchyLookupWindow.getHierarchyDto();
					customerHierarchyDto = lookupDto;
                                        customerHierarchyDto.setHierarchySelection("customerSelection");
					DataSelectionForm.loadRelationDdlb(customerHierarchyDto.getHierarchyId(), null,
							customerRelationComboBox);
					resetCustomerLevel();
					resetSecondCustomerLevel();
					setCustomerRelationNullSelection();
					availableCustomer.removeAllItems();
					availableCustomerContainer.removeAllItems();
					selectedCustomer.removeAllItems();
					selectedCustomerContainer.removeAllItems();
					customerGroup.setValue(StringUtils.EMPTY);
					selectionDTO.setCustomerGrpSid(null);

					groupFilteredCompanies = null;
					setCustomerForecastLevelNullSelection();
					setUpdateOnTabChange(BooleanConstant.getTrueFlag());
				}
			}
		});
	}

	@Override
	protected void productHierarchyLookUp() {
		final HierarchyLookup productHierarchyLookupWindow = new HierarchyLookup(
				INDICATOR_PRODUCT_HIERARCHY.getConstant(), WINDOW_PRODUCT_HIERARCHY_LOOKUP.getConstant(),
				productHierarchy, productHierarchyDto);
		UI.getCurrent().addWindow(productHierarchyLookupWindow);
		productHierarchyLookupWindow.addCloseListener(new Window.CloseListener() {

			@Override
			public void windowClose(Window.CloseEvent e) {
				if (productHierarchyLookupWindow.getHierarchyDto() != null) {
					final HierarchyLookupDTO lookupDto = productHierarchyLookupWindow.getHierarchyDto();
					productHierarchyDto = lookupDto;
					DataSelectionForm.loadRelationDdlb(productHierarchyDto.getHierarchyId(), null, productRelation);
					resetProductLevel();
					resetSecondProductLevel();
					setProductRelationNullSelection();
					availableProduct.removeAllItems();
					availableProductContainer.removeAllItems();
					selectedProduct.removeAllItems();
					selectedProductContainer.removeAllItems();
					selectionDTO.setProdGrpSid(null);
					selectionDTO.setCompanySid(null);
					groupFilteredItems = null;
					setProductForecastLevelNullSelection();
					setUpdateOnTabChange(BooleanConstant.getTrueFlag());
				}
			}
		});
	}

	@Override
	protected void modeOptionChange(boolean isAddMode) {
		try {
			if (isAddMode) {
				DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(),
						screenName);
			} else {
				DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_SEARCH.getConstant(),
						screenName);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}

	}

	@Override
	protected void customerLevelValueChange(Property.ValueChangeEvent event, boolean flag) {
		return;
	}

	@Override
	protected void levelValueChangeListener(Object value)
			throws ClassNotFoundException, CloneNotSupportedException, IOException {

		String dedLevel = StringUtils.EMPTY;
		String dedValue = StringUtils.EMPTY;

		LOGGER.debug("customer inner Level - ValueChangeListener= {}  ", value);
		availableCustomerContainer.removeAllItems();
		String levelName = Constant.LEVEL_LABEL;
		int relationVersionNo = 0;
		int hierarchyVersionNo = 0;
		try {

			if (customerRelationVersionComboBox.getValue() != null) {

				if (value != null && customerRelationComboBox.getValue() != null
						&& !SELECT_ONE.equals(customerRelationComboBox.getValue())) {
                                    
                                    
                                    List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanList = getRelationshipBuilderBeanList();

                                    for (GtnWsRelationshipBuilderBean relationshipBuiderBean : relationshipBuilderBeanList) {
                                        if (relationshipBuiderBean.getRelationshipBuilderSid() == (int) customerRelationComboBox.getValue()) {
                                            relationVersionNo = relationshipBuiderBean.getVersionNo();
                                            break;
                                        }
                                    }
                                     hierarchyVersionNo = customerHierarchyLookupWindow.getHierarchyDto().getVersionNo();
                                 
                                 
//					relationVersionNo = Integer.parseInt(
//							customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
//					hierarchyVersionNo = Integer.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
					customerDescriptionMap = relationLogic.getLevelValueMap(
							String.valueOf(customerRelationComboBox.getValue()), customerHierarchyDto.getHierarchyId(),
							hierarchyVersionNo, relationVersionNo);
				}
			} else {
				if (selectionDTO != null) {
					LOGGER.info("customer relation version = {}", selectionDTO.getCustomerRelationShipVersionNo());
					LOGGER.info("customer hierarchy version no= {}", selectionDTO.getCustomerHierVersionNo());
					relationVersionNo = selectionDTO.getCustomerRelationShipVersionNo();
					hierarchyVersionNo = selectionDTO.getCustomerHierVersionNo();
					customerDescriptionMap = relationLogic.getLevelValueMap(
							String.valueOf(customerRelationComboBox.getValue()), customerHierarchyDto.getHierarchyId(),
							hierarchyVersionNo, relationVersionNo);
				}
			}
		}

		catch (NumberFormatException ex) {
			LOGGER.error(" level  ValueChangeListener1= {} ", ex);
		}
		setAvailableCustomer(value, dedLevel, dedValue, levelName, relationVersionNo, hierarchyVersionNo);
	}

        private List<GtnWsRelationshipBuilderBean> getRelationshipBuilderBeanList() throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMapper = customerHierarchyLookupWindow.getHierarchyDto().getRelationshipMap();
        Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap = mapper.convertValue(
                relationshipMapper, new TypeReference<Map<Integer, List<GtnWsRelationshipBuilderBean>>>() {});
        List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanList = relationshipMap.get(customerHierarchyLookupWindow.getHierarchyDto().getHierarchyId());
        return relationshipBuilderBeanList;
    }
        
	private void setAvailableCustomer(Object value, String dedLevel, String dedValue, String levelName,
			int relationVersionNo, int hierarchyVersionNo) {
		int forecastLevel;
                String dedLevelCustomer = dedLevel;
                String dedValueCustomer = dedValue;
                String levelNameCustomer = levelName;
		try {

			if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
					dedLevelCustomer = getDedutionLevel();
					dedValueCustomer = String.valueOf(deductionValue.getValue());
			}
		} catch (NumberFormatException ex) {
			LOGGER.error(" level  ValueChangeListener2= {} ", ex);
		}
		try {
			String selectedLevel = String.valueOf(value);
			String relationshipSid = String.valueOf(customerRelationComboBox.getValue());
			//String[] val = selectedLevel.split(" ");
			if (selectedLevel.length() > 0) {
				forecastLevel = (int)value;
				dataSelectionDTO.setSelectedCustomerLevelNo(selectedLevel);
				List<Leveldto> customerHierarchyLevelDefinitionList = relationLogic
						.getHierarchyLevelDefinition(customerHierarchyDto.getHierarchyId(), hierarchyVersionNo);
				Leveldto selectedHierarchyLevelDto = customerHierarchyLevelDefinitionList.get(forecastLevel - 1);
				List<String> tempGroupFileter = groupFilteredCompanies == null ? Collections.<String>emptyList()
						: groupFilteredCompanies;

                                List<Object> availableCustomersList = new ArrayList<>();
				

				availableCustomersList = relationLogic.loadAvailableCustomerlevel(selectedHierarchyLevelDto,
						Integer.parseInt(relationshipSid), tempGroupFileter, dedLevelCustomer, dedValueCustomer, relationVersionNo,
						forecastEligibleDate.getValue(), customerDescriptionMap);
                                List<Leveldto> resultedLevelsList = new ArrayList<>((List<Leveldto>)availableCustomersList.get(0));
				if (selectedHierarchyLevelDto.getLevel() != null) {
					levelNameCustomer = selectedHierarchyLevelDto.getLevel();
				}

				availableCustomerContainer.addAll(resultedLevelsList);
				availableCustomer.setContainerDataSource(availableCustomerContainer);
				availableCustomer.setVisibleColumns(Constant.DISPLAY_VALUE);
				availableCustomer.setColumnHeaders(levelNameCustomer);
				availableCustomer.setFilterBarVisible(true);
				availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
				availableCustomer.setStyleName(Constant.FILTER_TABLE);
			}

		} catch (Exception ex) {
			LOGGER.error(" level  ValueChangeListener3= {} ", ex);
		}
	}

	private String getDedutionLevel() {
		switch (String.valueOf(deductionLevel.getValue())) {
		case Constant.DEDUCTION_PROGRAM_TYPE1:
			return Constant.REBATE_PROGRAM_TYPE1;
		case Constant.DEDUCTION_CATEGORY1:
			return Constant.RS_CATEGORY1;
		case Constant.DEDUCTION_SCHEDULE_TYPE1:
			return Constant.RS_TYPE;
		default:
			return StringUtils.EMPTY;
		}
	}

	@Override
	protected void productLevelValueChange(Object value, boolean flag) {
		return;
	}

	@Override
	protected void customerRelationValueChange(Object value) {
		LOGGER.debug("data selection customer value change");
		if (value == null || (SELECT_ONE.equals(String.valueOf(value)))) {
			customerInnerLevelContainer.removeAllItems();
			availableCustomer.removeAllItems();
			availableCustomerContainer.removeAllItems();
			selectedCustomer.removeAllItems();
			selectedCustomerContainer.removeAllItems();
			setCustomerForecastLevelNullSelection();
			setCustomerLevelNullSelection();
			setNullSelectionCustomDdlb(customRelationDdlb);
			setNullSelectionCustomDdlb(customRelationDdlbDeduction);
			if (!isFirstTimeLoad()) {
				customerDescriptionMap = null;
			}
		} else {
			//relationLogic.waitForAutomaticRelation();
			try {
                              Map<Integer, String> hierarchyMap = new HashMap<>();
                           
                            hierarchyMap = DataSelectionForm.getCustomerHierarchyLookupWindow().getHierarchyDto().getHierarchyMap();
                            
                            for (Map.Entry<Integer, String> hierarchyLevelEntry : hierarchyMap.entrySet()) {
				customerLevel.addItem(hierarchyLevelEntry.getKey());
                                customerLevel.setItemCaption(hierarchyLevelEntry.getKey(),formHierarchyLevelValues(hierarchyLevelEntry));
			}
//				if (!firstTimeLoad) {
//					selectedCustomer.removeAllItems();
//					selectedCustomerContainer.removeAllItems();
//				}
//				availableCustomer.removeAllItems();
//				availableCustomerContainer.removeAllItems();
//				setCustomerForecastLevelNullSelection();
//				setCustomerLevelNullSelection();
//				loadCustomerVersionNo(customerRelationComboBox.getValue());
//				customViewInput.put("custVer",
//						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
//				customViewInput.put("custSid", String.valueOf(customerRelationComboBox.getValue()));
//				loadCustomViewDropDown(customRelationDdlb, customViewInput);
//				loadCustomViewDeductionDropDown(customRelationDdlbDeduction, customViewInput);
			} catch (Exception ex) {

				LOGGER.error(" in customerRelation value change= {}", ex);
			}
		}
		if (!isFirstTimeLoad()) {
			customerGroup.setValue(StringUtils.EMPTY);
			selectedCustomerGroupDTO = new GroupDTO();
			selectionDTO.setCustomerGrpSid(null);
		}
		selectionDTO.setCustomerDescription(customerDescriptionMap);
	}

         public String formHierarchyLevelValues(Map.Entry<Integer, String> hierarchyLevelEntry) {
		String levelValue = "Level " + hierarchyLevelEntry.getKey() + " - " + hierarchyLevelEntry.getValue();
                return levelValue;
        }
         
	public boolean isFirstTimeLoad() {
		return firstTimeLoad;
	}

	@Override
	protected void productRelationValueChange(Object value) {
		if (value == null || (SELECT_ONE.equals(String.valueOf(value)))) {
			availableProduct.removeAllItems();
			availableProductContainer.removeAllItems();
			selectedProduct.removeAllItems();
			selectedProductContainer.removeAllItems();
			productInnerLevelContainer.removeAllItems();
			setProductForecastLevelNullSelection();
			setProductLevelNullSelection();
			setNullSelectionCustomDdlb(customRelationDdlb);
			setNullSelectionCustomDdlb(customRelationDdlbDeduction);
			if (!isFirstTimeLoad()) {
				productDescriptionMap = null;
			}

		} else {
			try {
				relationLogic.waitForAutomaticRelation();
				if (!firstTimeLoad) {
					selectedProduct.removeAllItems();
					selectedProductContainer.removeAllItems();
				}
				availableProduct.removeAllItems();
				availableProductContainer.removeAllItems();
				setProductForecastLevelNullSelection();
				setProductLevelNullSelection();
				if (!firstTimeLoad) {
					int relationVersionNo = 0;
					int hierarchyVersionNo = 0;
					if (customerRelationVersionComboBox.getValue() != null) {
						relationVersionNo = Integer.parseInt(customerRelationVersionComboBox
								.getItemCaption(customerRelationVersionComboBox.getValue()));
						hierarchyVersionNo = Integer
								.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
						productDescriptionMap = relationLogic.getLevelValueMap(
								String.valueOf(productRelation.getValue()), productHierarchyDto.getHierarchyId(),
								hierarchyVersionNo, relationVersionNo);
					} else {
						if (selectionDTO != null) {
							relationVersionNo = selectionDTO.getCustomerRelationShipVersionNo();
							hierarchyVersionNo = selectionDTO.getCustomerHierVersionNo();
							productDescriptionMap = relationLogic.getLevelValueMap(
									String.valueOf(productRelation.getValue()), productHierarchyDto.getHierarchyId(),
									hierarchyVersionNo, relationVersionNo);
						}
					}
				}
				loadProductVersionNo(productRelation.getValue());
				customViewInput.put("prodVer",
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
				customViewInput.put("prodSid", String.valueOf(productRelation.getValue()));

				if (!CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
					loadCustomViewDropDown(customRelationDdlb, customViewInput);
					loadCustomViewDeductionDropDown(customRelationDdlbDeduction, customViewInput);
				}
			} catch (NumberFormatException ex) {
				LOGGER.error(" in productRelation value change= {}", ex);
			}
		}
		if (!isFirstTimeLoad()) {
			selectionDTO.setProdGrpSid(null);
			productGroup.setValue(StringUtils.EMPTY);
			selectedProductGroupDTO = new GroupDTO();
		}
		selectionDTO.setProductDescription(productDescriptionMap);

	}

	@Override
	protected void generateButtonLogic() {
		return;
	}

	@Override
	protected void searchButtonLogic() {
		return;
	}

	@Override
	protected void editButtonLogic() {
		return;
	}

	@Override
	protected void viewButtonLogic() {
		return;
	}

	@Override
	protected void deleteButtonLogic() {
		return;
	}

	@Override
	protected void configureTimeDdlb(ComboBox fromPeriod, ComboBox toPeriod, Object object, Object object0,
			String constant, String screenName) {
		try {
			DataSelectionUtil.configureTimeDdlb(fromPeriod, toPeriod, null, null, MODE_ADD.getConstant(), screenName);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	@Override
	protected void loadPublicView() {
		return;
	}

	@Override
	protected void loadPrivateView() {
		return;
	}

	@Override
	protected void loadProductGroup() {
		final CustomerProductGroupLookup productGroupLookupWindow = new CustomerProductGroupLookup(
				INDICATOR_PRODUCT_GROUP.getConstant(), WINDOW_PRODUCT_GROUP_LOOKUP.getConstant(), productGroup,
				Collections.<String>emptyList(), screenName);
		productGroupLookupWindow.init();
		UI.getCurrent().addWindow(productGroupLookupWindow);
		productGroupLookupWindow.addCloseListener(new Window.CloseListener() {

			@Override
			public void windowClose(Window.CloseEvent e) {
				if (productGroupLookupWindow.getSelectedProdHierarchy() != null) {
					selectedProductGroupDTO = productGroupLookupWindow.getSelectedProdHierarchy();
					groupFilteredItems = productGroupLookupWindow.getFilteredSids();
					if (productlevelDdlb.getValue() != null && !String.valueOf(SELECT_ONE)
							.equalsIgnoreCase(String.valueOf(productlevelDdlb.getValue()))) {
						loadFilteredProductSelection(String.valueOf(productlevelDdlb.getValue()));
					}
				}
			}
		});
	}

	public void loadFilteredProductSelection(final String selectedLevel) {
		try {
			LOGGER.info("inside filtered product selection");
			availableProductContainer.removeAllItems();
			int forecastLevel = 0;
			boolean isNdc = false;
			List<Leveldto> selectedCustomerContractList;
			String levelName = Constant.LEVEL_LABEL;
			List<Leveldto> resultedLevelsList;
			if (selectedLevel != null && !Constants.CommonConstants.NULL.getConstant().equals(selectedLevel)
					&& !SELECT_ONE.equals(selectedLevel)) {
				LOGGER.info("inside if");
				int relationVersionNo = 0;
				int hierarchyVersionNo = 0;
				int customerRelationVersionNo = 0;
				if (productRelationVersionComboBox.getValue() != null) {
					relationLogic.waitForAutomaticRelation();
					relationVersionNo = Integer.parseInt(
							productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
					hierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
					customerRelationVersionNo = Integer.parseInt(
							customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
					productDescriptionMap = relationLogic.getLevelValueMap(String.valueOf(productRelation.getValue()),
							productHierarchyDto.getHierarchyId(), hierarchyVersionNo, relationVersionNo);
				}

				else {
					if (selectionDTO != null) {
						relationVersionNo = selectionDTO.getProductRelationShipVersionNo();
						hierarchyVersionNo = selectionDTO.getProductHierVersionNo();
						customerRelationVersionNo = selectionDTO.getCustomerRelationShipVersionNo();
						productDescriptionMap = relationLogic.getLevelValueMap(
								String.valueOf(productRelation.getValue()), productHierarchyDto.getHierarchyId(),
								hierarchyVersionNo, relationVersionNo);

					}
				}
				String dedLevel = StringUtils.EMPTY;
				String dedValue = StringUtils.EMPTY;
				if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equals(screenName)) {
						dedLevel = getDedutionLevel();
						dedValue = String.valueOf(deductionValue.getValue());
				}
				String relationshipSid = String.valueOf(productRelation.getValue());

				String[] val = selectedLevel.split(" ");
				forecastLevel = Integer.parseInt(val[1]);
				List<Leveldto> productHierarchyLevelDefinitionList = relationLogic
						.getHierarchyLevelDefinition(productHierarchyDto.getHierarchyId(), hierarchyVersionNo);
				Leveldto selectedHierarchyLevelDto = productHierarchyLevelDefinitionList.get(forecastLevel - 1);
				isNdc = (selectedHierarchyLevelDto.getLevel().equalsIgnoreCase("Package")
						|| selectedHierarchyLevelDto.getLevel().equalsIgnoreCase("NDC-11"));
				selectedCustomerContractList = getSelectedCustomerContractList();

				List<String> tempGroupFileter = groupFilteredItems == null ? Collections.<String>emptyList()
						: groupFilteredItems;
				resultedLevelsList = relationLogic.loadAvailableProductlevel(selectedHierarchyLevelDto,
						Integer.parseInt(relationshipSid), tempGroupFileter, selectedCustomerContractList, isNdc,
						dedLevel, dedValue, relationVersionNo, customerRelationVersionNo, businessUnit.getValue(),
						productDescriptionMap);
				if (selectedHierarchyLevelDto.getLevel() != null) {
					levelName = selectedHierarchyLevelDto.getLevel();
				}
				availableProduct.removeAllItems();
				availableProductContainer.removeAllItems();
				availableProductContainer.addAll(resultedLevelsList);
			}
			availableProduct.setContainerDataSource(availableProductContainer);
			if (isNdc) {
				availableProduct.setVisibleColumns(Constant.DISPLAY_VALUE, "form", "strength");
				availableProduct.setColumnHeaders(Constant.NDC, "Form", "Strength");
			} else {
				availableProduct.setVisibleColumns(Constant.DISPLAY_VALUE);
				availableProduct.setColumnHeaders(levelName);
			}
			availableProduct.setFilterBarVisible(true);
			availableProduct.setFilterDecorator(new ExtDemoFilterDecorator());
			availableProduct.setStyleName(Constant.FILTER_TABLE);
		} catch (CloneNotSupportedException | NumberFormatException ex) {
			LOGGER.error(" - in loadFilteredProductSelection= {}", ex);
		}
	}

	private List<Leveldto> getSelectedCustomerContractList() {
		List<Leveldto> ccList = Collections.emptyList();
		if (selectedCustomerContainer != null && !selectedCustomerContainer.getItemIds().isEmpty()) {
			Leveldto currentDto;
			ccList = new ArrayList<>();
			for (Object item : selectedCustomerContainer.getItemIds()) {
				currentDto = DataSelectionUtil.getBeanFromId(item);
				if (currentDto != null && !StringUtils.isBlank(currentDto.getTableName())) {
					ccList.add(currentDto);
				}
			}
		}
		return ccList;
	}

	@Override
	protected void loadCustomerGroup() {
		final CustomerProductGroupLookup customerGroupLookupWindow = new CustomerProductGroupLookup(
				INDICATOR_CUSTOMER_GROUP.getConstant(), WINDOW_CUSTOMER_GROUP_LOOKUP.getConstant(), customerGroup,
				Collections.<String>emptyList(), screenName);
		customerGroupLookupWindow.init();
		UI.getCurrent().addWindow(customerGroupLookupWindow);
		customerGroupLookupWindow.addCloseListener(new Window.CloseListener() {

			@Override
			public void windowClose(Window.CloseEvent e) {
				if (customerGroupLookupWindow.getSelectedCustHierarchy() != null) {
					selectedCustomerGroupDTO = customerGroupLookupWindow.getSelectedCustHierarchy();
					groupFilteredCompanies = customerGroupLookupWindow.getFilteredSids();
					try {
						levelValueChangeListener(level.getValue());
					} catch (ClassNotFoundException | CloneNotSupportedException | IOException ex) {

						LOGGER.error(" - in customerGroupLookupWindow= {}", e);
					}
				}
			}
		});
	}

	private List<Leveldto> getCustomersFromHierarchy() {
		List<Leveldto> innerLevelValues = null;
		try {
			int hierarchyId = 0;
			if (customerHierarchyDto == null) {
				hierarchyId = UiUtils.parseStringToInteger(selectionDTO.getCustomerHierSid());
			} else {
				hierarchyId = customerHierarchyDto.getHierarchyId();
			}
			DataSelectionLogic logic = new DataSelectionLogic();
			if (innerCustLevels == null || innerCustLevels.isEmpty() || customerHierarchyDto == null) {
				innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY,
						selectionDTO.getCustomerHierVersionNo());
			}
			if ((innerCustLevels != null) && (customerRelationComboBox.getValue() != null
					&& !SELECT_ONE.equals(customerRelationComboBox.getValue()))) {

				String relationshipSid = String.valueOf(customerRelationComboBox.getValue());
				Leveldto customerLevelDto = null;
				for (Leveldto dto : innerCustLevels) {
					if (Constant.CUSTOMER_SMALL.equalsIgnoreCase(dto.getLevel())
							|| Constant.COMPANY_SMALL.equalsIgnoreCase(dto.getLevel())
							|| Constant.TRADING_PARTNER.equalsIgnoreCase(dto.getLevel())) {
						customerLevelDto = dto;
						break;
					}
				}
				if (customerLevelDto != null) {
					if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
						discountDTO = (CompanyDdlbDto) discount.getValue();
					}
					innerLevelValues = logic.loadInnerLevel(customerLevelDto.getLevel(),
							customerHierarchyDto == null ? 0 : customerHierarchyDto.getHierarchyId(),
							DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()),
							false, customerLevelDto.getFieldName(), relationshipSid, customerDescriptionMap,
							INDICATOR_LEVEL_CUSTOMER.getConstant(), screenName,
							discountDTO != null ? discountDTO.getRsModelSid() : 0, customerLevelDto.getLevelNo(),
							StringUtils.EMPTY, StringUtils.EMPTY, company.getValue(), businessUnit.getValue());
				}
			}
		} catch (SystemException ex) {
			LOGGER.error(" in getCustomersFromHierarchy= {}", ex);
		}
		return innerLevelValues;
	}

	private void filterCustomerOnGroupSelect() {
		availableCustomerContainer.removeAllItems();
		String levelName = Constant.LEVEL_LABEL;
		try {
			int forecastLevel = 0;
			List<Leveldto> custVlues = null;
			boolean isNdc = false;
			if (level.getValue() != null && !Constants.CommonConstants.NULL.getConstant().equals(level.getValue())
					&& !SELECT_ONE.equals(level.getValue()) && customerRelationComboBox.getValue() != null) {
				String selectedLevel = String.valueOf(level.getValue());

				if (customerRelationComboBox.getValue() != null
						&& !SELECT_ONE.equals(customerRelationComboBox.getValue())) {
					String relationshipSid = String.valueOf(customerRelationComboBox.getValue());
					int hierarchyId = 0;
					if (customerHierarchyDto == null) {
						hierarchyId = UiUtils.parseStringToInteger(selectionDTO.getCustomerHierSid());
					} else {
						hierarchyId = customerHierarchyDto.getHierarchyId();
					}
					DataSelectionLogic logic = new DataSelectionLogic();
					String[] val = selectedLevel.split(" ");
					forecastLevel = Integer.parseInt(val[1]);
					if (innerCustLevels.isEmpty() || customerHierarchyDto == null) {
						innerCustLevels = logic.loadCustomerForecastLevel(hierarchyId, StringUtils.EMPTY,
								selectionDTO.getCustomerHierVersionNo());
					}
					Leveldto tempDto = (Leveldto) innerCustLevels.get(forecastLevel - 1);
					if (tempDto.getLevel() != null) {
						levelName = tempDto.getLevel();
					}
					if (tempDto.getLevel() != null
							&& (Constant.NDC.equalsIgnoreCase(tempDto.getLevel())
									|| "Item".equalsIgnoreCase(tempDto.getLevel()))
							&& Constant.ITEM_MASTER.equals(tempDto.getTableName())) {
						isNdc = true;
					} else {
						isNdc = false;
					}
					if (CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName)) {
						discountDTO = (CompanyDdlbDto) discount.getValue();
					}
					custVlues = logic.loadInnerLevel(tempDto.getLevel(), hierarchyId,
							DataSelectionUtil.getSelectedRelationshipLevelSids(selectedCustomerContainer.getItemIds()),
							isNdc, tempDto.getFieldName(), relationshipSid, customerDescriptionMap,
							DataSelectionUtil.identifyLevel(tempDto), screenName,
							discountDTO != null ? discountDTO.getRsModelSid() : 0, tempDto.getLevelNo(),
							StringUtils.EMPTY, StringUtils.EMPTY, company.getValue(), businessUnit.getValue());

					if (groupFilteredCompanies != null
							&& Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(tempDto.getTableName()))
							&& (Constant.TRADING_PARTNER.equals(levelName)
									|| Constant.COMPANY_SMALL.equals(levelName))) {
						List<Leveldto> filteredValues = new ArrayList<>();
						if (!groupFilteredCompanies.isEmpty()) {
								for (Leveldto leveldto : custVlues) {
									if (groupFilteredCompanies.contains(leveldto.getRelationshipLevelValue().trim())) {
										filteredValues.add(leveldto);
									}
								}
						}
						availableCustomerContainer.addAll(filteredValues);
					} else {
						availableCustomerContainer.addAll(custVlues);
					}
				}
			}
			availableCustomer.setContainerDataSource(availableCustomerContainer);
			availableCustomer.setVisibleColumns(new Object[] { Constant.DISPLAY_VALUE_PROPERTY });
			availableCustomer.setColumnHeaders(new String[] { levelName });
			availableCustomer.setFilterBarVisible(true);
			availableCustomer.setFilterDecorator(new ExtDemoFilterDecorator());
			availableCustomer.setStyleName(Constant.FILTER_TABLE);
		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error(" filterCustomerOnGroupSelect= {} ", ex);
		}

	}

	@Override
	protected void saveViewButtonlogic() {
		return;
	}

	@Override
	protected void moveLeftButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int customerHierarchyVersionNo = 0;
			int customerRelationVersionNo = 0;
			if (customerRelationVersionComboBox.getValue() == null && selectionDTO != null) {
				customerHierarchyVersionNo = selectionDTO.getCustomerHierVersionNo();
				customerRelationVersionNo = selectionDTO.getCustomerRelationShipVersionNo();
			} else {
				customerHierarchyVersionNo = Integer
						.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				customerRelationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
			}
			if (availableCustomer.getValue() != null) {
				int forecastLevel = 0;
				if (customerLevel.getValue() != null) {
					forecastLevel = UiUtils
							.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
				}
				Object item = availableCustomer.getValue();
				if (selectedCustomerContainer.size() > 0) {

					if (selectedCustomer.getValue() != null) {

						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), false);

						if (!newChildLevels.isEmpty()) {
							int pos3 = 0;
							String childHierarchyNo;
							Leveldto childsParent = null;
							for (Leveldto newLevel : newChildLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos3 != -1) {
									childHierarchyNo = tempHNo.substring(0, pos3) + ".";
								} else {
									childHierarchyNo = tempHNo + ".";
								}
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
								}

								if (!StringUtils.isBlank(childHierarchyNo)) {
									for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
										if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											childsParent = selectedLevel;
											break;
										}
									}
								}
								selectedCustomerContainer.setParent(newLevel, childsParent);
							}
						}

					} else {

						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						String selectedParentHierarchyNo = StringUtils.EMPTY;
						Leveldto selectedParent = null;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}
						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
								selectedHierarchyNos);
						List<String> removeValues = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValues.add(uncommonHierValue);
							}
						}
						if (!removeValues.isEmpty()) {
							uncommonValues.removeAll(removeValues);
						}
						if (!uncommonValues.isEmpty()) {
							String tempHNo = uncommonValues.get(0);
							if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
								tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
							}
							int pos2;
							pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos2 != -1) {
								selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
							} else {
								selectedParentHierarchyNo = tempHNo + ".";
							}
						}
						if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
							for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									selectedParent = selectedLevel;
									break;
								}
							}
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevels = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
									customerHierarchyVersionNo, customerRelationVersionNo);
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), false);
						if (newParentLevels != null) {
							for (Leveldto newLevel : newParentLevels) {
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
									selectedCustomerContainer.setParent(newLevel, selectedParent);
									selectedParent = newLevel;
								}
							}
							if (!newChildLevels.isEmpty()) {
								int pos3;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}

									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
									}

									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}

									selectedCustomerContainer.setParent(newLevel, childsParent);
								}
							}

						}

					}
				} else if (availableCustomer.getValue() != null
						&& (DataSelectionUtil.getBeanFromId(availableCustomer.getValue()).getLevelNo() > 1)) {

					String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
					if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
						hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
					}
					List<String> hierarchyNos = new ArrayList<>();
					List<Leveldto> newParentLevels;
					List<Leveldto> newChildLevels;
					hierarchyNos.add(hierarchyNo + ".");
					int pos;
					String selectedParentHierarchyNo = StringUtils.EMPTY;
					Leveldto selectedParent2 = null;
					while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
						pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
						if (pos != -1) {
							hierarchyNo = hierarchyNo.substring(0, pos);
						}
						hierarchyNos.add(hierarchyNo + ".");
					}
					Collections.reverse(hierarchyNos);
					if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
						for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
							if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
								selectedParent2 = selectedLevel;
								break;
							}
						}
					}
					newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(hierarchyNos),
							customerDescriptionMap, customerHierarchyVersionNo, customerRelationVersionNo);
					newChildLevels = logic.getChildLevelsWithHierarchyNo(
							UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
							customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
							customerHierarchyVersionNo, customerRelationVersionNo,
							UiUtils.getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]),
							forecastEligibleDate.getValue(), false);
					if (newParentLevels != null) {
						for (Leveldto newLevel : newParentLevels) {
							if (customerBeanList.isEmpty()
									|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {

								customerBeanList.add(newLevel.getRelationshipLevelSid());
								selectedCustomerContainer.addBean(newLevel);
								if (forecastLevel != newLevel.getLevelNo()) {
									selectedCustomerContainer.setChildrenAllowed(newLevel, true);
								} else {
									selectedCustomerContainer.setChildrenAllowed(newLevel, false);
								}
								selectedCustomerContainer.setParent(newLevel, selectedParent2);
								selectedParent2 = newLevel;
							}
						}
						if (!newChildLevels.isEmpty()) {
							int pos3;
							String childHierarchyNo;
							Leveldto childsParent = null;
							for (Leveldto newLevel : newChildLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos3 != -1) {
									childHierarchyNo = tempHNo.substring(0, pos3) + ".";
								} else {
									childHierarchyNo = tempHNo + ".";
								}
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
								}
								if (!StringUtils.isBlank(childHierarchyNo)) {
									for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
										if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											childsParent = selectedLevel;
											break;
										}
									}
								}
								selectedCustomerContainer.setParent(newLevel, childsParent);
							}
						}

					}
				} else {
					Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);
					String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
					if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
						hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
					}
					List<Leveldto> newChildLevels = null;
					int pos = 0;
					if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos) + ".";
							}
						}
					}
					if (customerBeanList.isEmpty() || !customerBeanList
							.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
						customerBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
						selectedCustomerContainer.addBean(selectedParent);
						if (forecastLevel != selectedParent.getLevelNo()) {
							selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
						} else {
							selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
						}
					}
					newChildLevels = logic.getChildLevelsWithHierarchyNo(
							UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
							customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
							customerHierarchyVersionNo, customerRelationVersionNo,
							UiUtils.getDataSelectionFormattedLevelNo(String.valueOf(level.getValue()).split("-")[0]),
							forecastEligibleDate.getValue(), false);
					if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
						int pos3;
						String childHierarchyNo;
						Leveldto childsParent = null;
						for (Leveldto newLevel : newChildLevels) {
							String tempHNo = newLevel.getHierarchyNo();
							if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
								tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
							}
							pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos3 != -1) {
								childHierarchyNo = tempHNo.substring(0, pos3) + ".";
							} else {
								childHierarchyNo = tempHNo + ".";
							}
							if (customerBeanList.isEmpty() || !customerBeanList
									.contains(Integer.valueOf(newLevel.getRelationShipBuilderId()))) {
								customerBeanList.add(newLevel.getRelationshipLevelSid());
								selectedCustomerContainer.addBean(newLevel);
								if (forecastLevel != newLevel.getLevelNo()) {
									selectedCustomerContainer.setChildrenAllowed(newLevel, true);
								} else {
									selectedCustomerContainer.setChildrenAllowed(newLevel, false);
								}
							}

							if (!StringUtils.isBlank(childHierarchyNo)) {
								for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
									if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
										childsParent = selectedLevel;
										break;
									}
								}
							}
							selectedCustomerContainer.setParent(newLevel, childsParent);
						}
					}

				}
				setUpdateOnTabChange(BooleanConstant.getTrueFlag());
			} else {
				AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
						"No Level was selected to move. Please try again.");
			}
			productBeanLisTemp.clear();
			productHierarchyNos.clear();
			for (Leveldto dto : selectedProductContainer.getItemIds()) {
				productHierarchyNos.add(dto.getHierarchyNo());
				productBeanLisTemp.add(dto.getRelationshipLevelSid());

			}
			setProductBeanLisTemp(productBeanLisTemp);
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void moveAllButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int customerHierarchyVersionNo;
			int customerRelationVersionNo;
			if (customerRelationVersionComboBox.getValue() == null && selectionDTO != null) {
				customerHierarchyVersionNo = selectionDTO.getCustomerHierVersionNo();
				customerRelationVersionNo = selectionDTO.getCustomerRelationShipVersionNo();
			} else {
				customerHierarchyVersionNo = Integer
						.parseInt(String.valueOf(customerRelationVersionComboBox.getValue()));
				customerRelationVersionNo = Integer.parseInt(
						customerRelationVersionComboBox.getItemCaption(customerRelationVersionComboBox.getValue()));
			}
			if (availableCustomerContainer.size() > 0) {
				int forecastLevel = 0;
				if (customerLevel.getValue() != null) {
					forecastLevel = UiUtils
							.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]);
				}
				List<Leveldto> iteams = new ArrayList<>(availableCustomerContainer.getItemIds());
				Object selectedItem;
				if (selectedCustomerContainer.size() > 0) {
					if (selectedCustomer.getValue() != null) {
						selectedItem = selectedCustomer.getValue();
						for (Leveldto item : iteams) {
							if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil
									.getBeanFromId(selectedItem).getLevelNo()) {

								String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
								if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
									hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
								}
								List<String> hierarchyNos = new ArrayList<>();
								List<Leveldto> newParentLevels = null;
								List<Leveldto> newChildLevels;
								hierarchyNos.add(hierarchyNo + ".");
								int pos;
								while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
									pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos != -1) {
										hierarchyNo = hierarchyNo.substring(0, pos);
									}
									hierarchyNos.add(hierarchyNo + ".");
								}
								Collections.reverse(hierarchyNos);
								List<String> selectedHierarchyNos = new ArrayList<>();
								for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
									if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
										selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
									}
								}
								List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
										selectedHierarchyNos);
								List<String> removeValues = new ArrayList<>();
								for (String uncommonHierValue : uncommonValues) {
									if (selectedHierarchyNos.contains(uncommonHierValue)) {
										removeValues.add(uncommonHierValue);
									}
								}
								if (!removeValues.isEmpty()) {
									uncommonValues.removeAll(removeValues); // At
																			// this
																			// point,
																			// uncommonValues
																			// should
																			// contain
																			// only
																			// 1
																			// value
																			// since
																			// only
																			// one
																			// value
																			// is
																			// selected
																			// to
																			// be
																			// moved
																			// in
																			// available
																			// table.
								}
								if (!uncommonValues.isEmpty()) {
									newParentLevels = logic.getParentLevelsWithHierarchyNo(
											UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
											customerHierarchyVersionNo, customerRelationVersionNo);
								}
								newChildLevels = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(customerLevel.getValue()).split("-")[0]),
										customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
										customerHierarchyVersionNo, customerRelationVersionNo,
										UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(level.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), false);
								if (newParentLevels != null) {
									int pos2;
									String parentHierarchyNo;
									Leveldto parent = null;
									for (Leveldto newLevel : newParentLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());

										if (pos2 != -1) {
											parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
										} else {
											parentHierarchyNo = tempHNo + ".";
										}
										if (customerBeanList.isEmpty()
												|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
											customerBeanList.add(newLevel.getRelationshipLevelSid());
											selectedCustomerContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedCustomerContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedCustomerContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(parentHierarchyNo)) {
											for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
												if (parentHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													parent = selectedLevel;
													break;
												}
											}
										}
										selectedCustomerContainer.setParent(newLevel, parent);
										parent = newLevel;
									}
									if (!newChildLevels.isEmpty()) {
										int pos3;
										String childHierarchyNo;
										Leveldto childsParent = null;
										for (Leveldto newLevel : newChildLevels) {
											String tempHNo = newLevel.getHierarchyNo();
											if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
												tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
											}
											pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
											if (pos3 != -1) {
												childHierarchyNo = tempHNo.substring(0, pos3) + ".";
											} else {
												childHierarchyNo = tempHNo + ".";
											}
											if (customerBeanList.isEmpty()
													|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
												customerBeanList.add(newLevel.getRelationshipLevelSid());
												selectedCustomerContainer.addBean(newLevel);
												if (forecastLevel != newLevel.getLevelNo()) {
													selectedCustomerContainer.setChildrenAllowed(newLevel, true);
												} else {
													selectedCustomerContainer.setChildrenAllowed(newLevel, false);
												}
											}
											if (!StringUtils.isBlank(childHierarchyNo)) {
												for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
													if (childHierarchyNo
															.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
														childsParent = selectedLevel;
														break;
													}
												}
											}
											selectedCustomerContainer.setParent(newLevel, childsParent);
										}
									}

								}
							}
						}
					} else {

						for (Leveldto item : iteams) {
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							List<String> hierarchyNos = new ArrayList<>();
							List<Leveldto> newParentLevels = null;
							List<Leveldto> newChildLevels;
							hierarchyNos.add(hierarchyNo + ".");
							int pos;
							String selectedParentHierarchyNo = StringUtils.EMPTY;
							Leveldto selectedParent = null;
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos);
								}
								hierarchyNos.add(hierarchyNo + ".");
							}
							Collections.reverse(hierarchyNos);
							List<String> selectedHierarchyNos = new ArrayList<>();
							for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
								if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
									selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
								}
							}
							List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
									selectedHierarchyNos);
							List<String> removeValues = new ArrayList<>();
							for (String uncommonHierValue : uncommonValues) {
								if (selectedHierarchyNos.contains(uncommonHierValue)) {
									removeValues.add(uncommonHierValue);
								}
							}
							if (!removeValues.isEmpty()) {
								uncommonValues.removeAll(removeValues); // At
																		// this
																		// point,
																		// uncommonValues
																		// should
																		// contain
																		// only
																		// 1
																		// value
																		// since
																		// only
																		// one
																		// value
																		// is
																		// selected
																		// to be
																		// moved
																		// in
																		// available
																		// table.
							}
							if (!uncommonValues.isEmpty()) {
								String tempHNo = uncommonValues.get(0);
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								int pos2 = 0;
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									selectedParentHierarchyNo = tempHNo + ".";
								}

							}
							if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
								for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
									if (selectedParentHierarchyNo
											.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
										selectedParent = selectedLevel;
										break;
									}
								}
							}
							if (!uncommonValues.isEmpty()) {
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
										customerHierarchyVersionNo, customerRelationVersionNo);
							}
							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(
											String.valueOf(customerLevel.getValue()).split("-")[0]),
									customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
									customerHierarchyVersionNo, customerRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(level.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), false);
							if (newParentLevels != null) {
								for (Leveldto newLevel : newParentLevels) {
									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
										selectedCustomerContainer.setParent(newLevel, selectedParent);
										selectedParent = newLevel;
									}
								}
								if (!newChildLevels.isEmpty()) {
									int pos3 = 0;
									String childHierarchyNo;
									Leveldto childsParent = null;
									for (Leveldto newLevel : newChildLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
										if (pos3 != -1) {
											childHierarchyNo = tempHNo.substring(0, pos3) + ".";
										} else {
											childHierarchyNo = tempHNo + ".";
										}
										if (customerBeanList.isEmpty()
												|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
											customerBeanList.add(newLevel.getRelationshipLevelSid());
											selectedCustomerContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedCustomerContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedCustomerContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(childHierarchyNo)) {
											for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
												if (childHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													childsParent = selectedLevel;
													break;
												}
											}
										}
										selectedCustomerContainer.setParent(newLevel, childsParent);
									}
								}

							}

						}
					}
				} else if (level.getValue() != null
						&& UiUtils.parseStringToInteger(String.valueOf(level.getValue())) == 1) {

					for (Leveldto item : iteams) {
						selectedCustomerContainer.removeAllItems();
						selectedCustomer.removeAllItems();
						Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						List<Leveldto> newChildLevels = null;
						int pos = 0;
						if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos) + ".";
								}
							}
						}
						if (customerBeanList.isEmpty() || !customerBeanList
								.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
							customerBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
							selectedCustomerContainer.addBean(selectedParent);
							if (forecastLevel != selectedParent.getLevelNo()) {
								selectedCustomerContainer.setChildrenAllowed(selectedParent, true);
							} else {
								selectedCustomerContainer.setChildrenAllowed(selectedParent, false);
							}
						}

						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), false);
						if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
							int pos3 = 0;
							String childHierarchyNo;
							Leveldto childsParent = null;
							for (Leveldto newLevel : newChildLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos3 != -1) {
									childHierarchyNo = tempHNo.substring(0, pos3) + ".";
								} else {
									childHierarchyNo = tempHNo + ".";
								}
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
								}
								if (!StringUtils.isBlank(childHierarchyNo)) {
									for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
										if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											childsParent = selectedLevel;
											break;
										}
									}
								}
								selectedCustomerContainer.setParent(newLevel, childsParent);
							}
						}
					}
				} else {

					for (Leveldto item : iteams) {
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels;
						List<Leveldto> newChildLevels;
						hierarchyNos.add(hierarchyNo + ".");
						int pos;
						String selectedParentHierarchyNo = StringUtils.EMPTY;
						Leveldto selectedParent = null;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}

						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
								selectedHierarchyNos);
						List<String> removeValues = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValues.add(uncommonHierValue);
							}
						}
						if (!removeValues.isEmpty()) {
							uncommonValues.removeAll(removeValues);
						}
						if (!uncommonValues.isEmpty()) {
							String tempHNo = uncommonValues.get(0);
							if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
								tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
							}
							int pos2 = 0;
							pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos2 != -1) {
								selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
							} else {
								selectedParentHierarchyNo = tempHNo + ".";
							}

						}
						if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
							for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									selectedParent = selectedLevel;
									break;
								}
							}
						}
						newParentLevels = logic.getParentLevelsWithHierarchyNo(
								UiUtils.stringListToString(uncommonValues), customerDescriptionMap,
								customerHierarchyVersionNo, customerRelationVersionNo);
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(customerLevel.getValue()).split("-")[0]),
								customerDescriptionMap, 0, DataSelectionUtil.getBeanFromId(item),
								customerHierarchyVersionNo, customerRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(level.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), false);
						if (newParentLevels != null) {
							for (Leveldto newLevel : newParentLevels) {
								if (customerBeanList.isEmpty()
										|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
									customerBeanList.add(newLevel.getRelationshipLevelSid());
									selectedCustomerContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedCustomerContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedCustomerContainer.setChildrenAllowed(newLevel, false);
									}
									selectedCustomerContainer.setParent(newLevel, selectedParent);
									selectedParent = newLevel;
								}
							}
							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (customerBeanList.isEmpty()
											|| !customerBeanList.contains(newLevel.getRelationshipLevelSid())) {
										customerBeanList.add(newLevel.getRelationshipLevelSid());
										selectedCustomerContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedCustomerContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedCustomerContainer.setChildrenAllowed(newLevel, false);
										}
									}
									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedCustomerContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedCustomerContainer.setParent(newLevel, childsParent);
								}
							}

						}
					}
				}
				productBeanLisTemp.clear();
				productHierarchyNos.clear();
				for (Leveldto dto : selectedProductContainer.getItemIds()) {
					productHierarchyNos.add(dto.getHierarchyNo());
					productBeanLisTemp.add(dto.getRelationshipLevelSid());

				}
				setProductBeanLisTemp(productBeanLisTemp);
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void moveAllProductButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int forecastLevel = 0;
			int productHierarchyVersionNo;
			int productRelationVersionNo;
			if (productLevel.getValue() != null) {
				forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
			}
			if (productRelationVersionComboBox.getValue() == null && selectionDTO != null) {
				productHierarchyVersionNo = selectionDTO.getProductHierVersionNo();
				productRelationVersionNo = selectionDTO.getProductRelationShipVersionNo();
			} else {
				productHierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				productRelationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
			}

			if (availableProductContainer.size() > 0) {
				List<Leveldto> iteams = new ArrayList<>(availableProductContainer.getItemIds());
				Object selectedItem;
				if (selectedProductContainer.size() > 0) {
					if (selectedProduct.getValue() != null) {
						selectedItem = selectedProduct.getValue();
						for (Leveldto item : iteams) {
							if (DataSelectionUtil.getBeanFromId(item).getLevelNo() > DataSelectionUtil
									.getBeanFromId(selectedItem).getLevelNo()) {

								String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
								if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
									hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
								}
								List<String> hierarchyNos = new ArrayList<>();
								List<Leveldto> newParentLevels = null;
								List<Leveldto> newChildLevels = null;
								hierarchyNos.add(hierarchyNo + ".");
								int pos = 0;
								while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
									pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos != -1) {
										hierarchyNo = hierarchyNo.substring(0, pos);
									}
									hierarchyNos.add(hierarchyNo + ".");
								}
								Collections.reverse(hierarchyNos);
								List<String> selectedHierarchyNos = new ArrayList<>();
								for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
									if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
										selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
									}
								}
								List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
										selectedHierarchyNos);
								List<String> removeValues = new ArrayList<>();
								for (String uncommonHierValue : uncommonValues) {
									if (selectedHierarchyNos.contains(uncommonHierValue)) {
										removeValues.add(uncommonHierValue);
									}
								}
								if (!removeValues.isEmpty()) {
									uncommonValues.removeAll(removeValues);
								}
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), productDescriptionMap,
										productHierarchyVersionNo, productRelationVersionNo);
								newChildLevels = logic.getChildLevelsWithHierarchyNo(
										UiUtils.parseStringToInteger(
												String.valueOf(productLevel.getValue()).split("-")[0]),
										productDescriptionMap, businessUnit.getValue(),
										DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
										productRelationVersionNo,
										UiUtils.getDataSelectionFormattedLevelNo(
												String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
										forecastEligibleDate.getValue(), true);
								if (newParentLevels != null) {
									int pos2 = 0;
									String parentHierarchyNo;
									Leveldto parent = null;
									for (Leveldto newLevel : newParentLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());

										if (pos2 != -1) {
											parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
										} else {
											parentHierarchyNo = tempHNo + ".";
										}
										if (productBeanList.isEmpty()
												|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
											productBeanList.add(newLevel.getRelationshipLevelSid());
											selectedProductContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedProductContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedProductContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(parentHierarchyNo)) {
											for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
												if (parentHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													parent = selectedLevel;
													break;
												}
											}
										}
										selectedProductContainer.setParent(newLevel, parent);
										parent = newLevel;
									}
									if (!newChildLevels.isEmpty()) {
										int pos3 = 0;
										String childHierarchyNo;
										Leveldto childsParent = null;
										for (Leveldto newLevel : newChildLevels) {
											String tempHNo = newLevel.getHierarchyNo();
											if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
												tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
											}
											pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
											if (pos3 != -1) {
												childHierarchyNo = tempHNo.substring(0, pos3) + ".";
											} else {
												childHierarchyNo = tempHNo + ".";
											}
											if (productBeanList.isEmpty()
													|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
												productBeanList.add(newLevel.getRelationshipLevelSid());
												selectedProductContainer.addBean(newLevel);
												if (forecastLevel != newLevel.getLevelNo()) {
													selectedProductContainer.setChildrenAllowed(newLevel, true);
												} else {
													selectedProductContainer.setChildrenAllowed(newLevel, false);
												}
											}
											if (!StringUtils.isBlank(childHierarchyNo)) {
												for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
													if (childHierarchyNo
															.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
														childsParent = selectedLevel;
														break;
													}
												}
											}
											selectedProductContainer.setParent(newLevel, childsParent);
										}
									}

								}
							}
						}
					} else {

						for (Leveldto item : iteams) {
							String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
							if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
								hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
							}
							List<String> hierarchyNos = new ArrayList<>();
							List<Leveldto> newParentLevels = null;
							List<Leveldto> newChildLevels = null;
							hierarchyNos.add(hierarchyNo + ".");
							int pos = 0;
							String selectedParentHierarchyNo = StringUtils.EMPTY;
							Leveldto selectedParent = null;
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos);
								}
								hierarchyNos.add(hierarchyNo + ".");
							}
							Collections.reverse(hierarchyNos);
							List<String> selectedHierarchyNos = new ArrayList<>();
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
									selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
								}
							}
							List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
									selectedHierarchyNos);
							List<String> removeValues = new ArrayList<>();
							for (String uncommonHierValue : uncommonValues) {
								if (selectedHierarchyNos.contains(uncommonHierValue)) {
									removeValues.add(uncommonHierValue);
								}
							}
							if (!removeValues.isEmpty()) {
								uncommonValues.removeAll(removeValues);
							}
							if (!uncommonValues.isEmpty()) {
								String tempHNo = uncommonValues.get(0);
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								int pos2 = 0;
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									selectedParentHierarchyNo = tempHNo + ".";
								}

							}
							if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
								for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
									if (selectedParentHierarchyNo
											.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
										selectedParent = selectedLevel;
										break;
									}
								}
							}
							if (!uncommonValues.isEmpty()) {
								newParentLevels = logic.getParentLevelsWithHierarchyNo(
										UiUtils.stringListToString(uncommonValues), productDescriptionMap,
										productHierarchyVersionNo, productRelationVersionNo);
							}
							newChildLevels = logic.getChildLevelsWithHierarchyNo(
									UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
									productDescriptionMap, businessUnit.getValue(),
									DataSelectionUtil.getBeanFromId(item), productHierarchyVersionNo,
									productRelationVersionNo,
									UiUtils.getDataSelectionFormattedLevelNo(
											String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
									forecastEligibleDate.getValue(), true);
							if (newParentLevels != null) {
								for (Leveldto newLevel : newParentLevels) {
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
										selectedProductContainer.setParent(newLevel, selectedParent);
										selectedParent = newLevel;
									}
								}
								if (!newChildLevels.isEmpty()) {
									int pos3 = 0;
									String childHierarchyNo;
									Leveldto childsParent = null;
									for (Leveldto newLevel : newChildLevels) {
										String tempHNo = newLevel.getHierarchyNo();
										if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
											tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
										}
										pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
										if (pos3 != -1) {
											childHierarchyNo = tempHNo.substring(0, pos3) + ".";
										} else {
											childHierarchyNo = tempHNo + ".";
										}
										if (productBeanList.isEmpty()
												|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
											productBeanList.add(newLevel.getRelationshipLevelSid());
											selectedProductContainer.addBean(newLevel);
											if (forecastLevel != newLevel.getLevelNo()) {
												selectedProductContainer.setChildrenAllowed(newLevel, true);
											} else {
												selectedProductContainer.setChildrenAllowed(newLevel, false);
											}
										}

										if (!StringUtils.isBlank(childHierarchyNo)) {
											for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
												if (childHierarchyNo
														.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
													childsParent = selectedLevel;
													break;
												}
											}
										}
										selectedProductContainer.setParent(newLevel, childsParent);
									}
								}

							}

						}
					}
				} else if (productLevel.getValue() != null
						&& UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue())) == 1) {

					for (Leveldto item : iteams) {
						selectedProductContainer.removeAllItems();
						selectedProduct.removeAllItems();
						Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						List<Leveldto> newChildLevels = null;
						int pos = 0;
						if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
								pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos != -1) {
									hierarchyNo = hierarchyNo.substring(0, pos) + ".";
								}
							}
						}
						if (productBeanList.isEmpty() || !productBeanList
								.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
							productBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
							selectedProductContainer.addBean(selectedParent);
							if (forecastLevel != selectedParent.getLevelNo()) {
								selectedProductContainer.setChildrenAllowed(selectedParent, true);
							} else {
								selectedProductContainer.setChildrenAllowed(selectedParent, false);
							}
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), true);
						if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
							int pos3 = 0;
							String childHierarchyNo;
							Leveldto childsParent = null;
							for (Leveldto newLevel : newChildLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos3 != -1) {
									childHierarchyNo = tempHNo.substring(0, pos3) + ".";
								} else {
									childHierarchyNo = tempHNo + ".";
								}
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
									productBeanList.add(newLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newLevel, false);
									}
								}
								if (!StringUtils.isBlank(childHierarchyNo)) {
									for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
										if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											childsParent = selectedLevel;
											break;
										}
									}
								}
								selectedProductContainer.setParent(newLevel, childsParent);
							}
						}
					}
				} else {

					for (Leveldto item : iteams) {
						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						String selectedParentHierarchyNo = StringUtils.EMPTY;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}
						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
								selectedHierarchyNos);
						List<String> removeValues = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValues.add(uncommonHierValue);
							}
						}
						if (!removeValues.isEmpty()) {
							uncommonValues.removeAll(removeValues);
						}
						if (!uncommonValues.isEmpty()) {
							String tempHNo = uncommonValues.get(0);
							if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
								tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
							}
							int pos2 = 0;
							pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos2 != -1) {
								selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
							} else {
								selectedParentHierarchyNo = tempHNo + ".";
							}

						}
						if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									break;
								}
							}
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevels = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), productDescriptionMap,
									productHierarchyVersionNo, productRelationVersionNo);
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), true);
						if (newParentLevels != null) {
							int pos2 = 0;
							String parentHierarchyNo;
							Leveldto parent = null;
							for (Leveldto newLevel : newParentLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());

								if (pos2 != -1) {
									parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									parentHierarchyNo = tempHNo + ".";
								}
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
									productBeanList.add(newLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newLevel, false);
									}
								}
								if (!StringUtils.isBlank(parentHierarchyNo)) {
									for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
										if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											parent = selectedLevel;
											break;
										}
									}
								}
								selectedProductContainer.setParent(newLevel, parent);
								parent = newLevel;
							}

							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
									}
									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedProductContainer.setParent(newLevel, childsParent);
								}
							}

						}
					}
				}
				setUpdateOnTabChange(BooleanConstant.getTrueFlag());
				DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
				productBeanLisTemp.clear();
				productHierarchyNos.clear();
				for (Leveldto dto : selectedProductContainer.getItemIds()) {
					productHierarchyNos.add(dto.getHierarchyNo());
					productBeanLisTemp.add(dto.getRelationshipLevelSid());

				}
				setProductBeanLisTemp(productBeanLisTemp);

			}

		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}

	}

	public static List<Integer> getProductBeanLisTemp() {
		return productBeanLisTemp;

	}

	public static void setProductBeanLisTemp(List<Integer> productBeanLisTemp) {
		DataSelection.productBeanLisTemp = productBeanLisTemp;
	}

	@Override
	protected void moveRigthButtonLogic() {

		if (selectedCustomer.getValue() != null) {
			Object selectedItem = selectedCustomer.getValue();

			DataSelectionUtil.removeItemsRecursively(selectedItem, selectedCustomer, selectedCustomerContainer);
			selectedCustomerContainer.removeItem(DataSelectionUtil.getBeanFromId(selectedItem));
			selectedCustomer.removeItem(selectedItem);
			customerBeanList.remove(DataSelectionUtil.getBeanFromId(selectedItem).getRelationshipLevelSid());
			customerBeanList.clear();
			List<Leveldto> selectedValueItem = selectedCustomerContainer.getItemIds();
			for (Leveldto dto : selectedValueItem) {
				customerBeanList.add(dto.getRelationshipLevelSid());
			}
			setUpdateOnTabChange(true);
			if (dismantelCustomerSelection) {

				triggerCustGrpOnView(selectionDTO.getCustomerGrpSid(), false);
				dismantelCustomerSelection = false;
			}
		} else {
			AbstractNotificationUtils.getErrorNotification("No Customer hierarchy level Selected",
					"No Level was selected to move. Please try again. ");
		}
		productBeanLisTemp.clear();
		productHierarchyNos.clear();
		for (Leveldto dto : selectedProductContainer.getItemIds()) {
			productHierarchyNos.add(dto.getHierarchyNo());
			productBeanLisTemp.add(dto.getRelationshipLevelSid());

		}
		setProductBeanLisTemp(productBeanLisTemp);
	}

	@Override
	protected void moveRigthProductButtonLogic() {
		try {
			if (selectedProduct.getValue() != null) {
				List<Leveldto> listValue;
				Object selectedItem = selectedProduct.getValue();
				Leveldto selectedLevel = (Leveldto) DataSelectionUtil.getBeanFromId(selectedItem);
				String levelInString = DASH;
				if (!String.valueOf(productlevelDdlb.getValue()).equalsIgnoreCase(String.valueOf(SELECT_ONE))) {
					levelInString = String.valueOf(productlevelDdlb.getValue());
				}
				int currentLevel = UiUtils.parseStringToInteger(levelInString);
				if ((currentLevel != 0 && selectedLevel.getLevelNo() == currentLevel)
						&& (Constant.NDC.equalsIgnoreCase(selectedLevel.getLevel()))) {
					listValue = DataSelectionUtil.getFSValue(selectedLevel.getRelationshipLevelValue(),
							selectedLevel.getFieldName());
					selectedLevel.setForm(StringUtils.EMPTY + listValue.get(0).getForm());
					selectedLevel.setStrength(StringUtils.EMPTY + listValue.get(0).getStrength());

				}
				DataSelectionUtil.removeItemsRecursively(selectedItem, selectedProduct, selectedProductContainer);
				selectedProductContainer.removeItem(selectedLevel);
				selectedProduct.removeItem(selectedItem);

				if (dismantleProductSelection) {
					triggerProdGrpOnView(selectionDTO.getProdGrpSid(), false);
					dismantleProductSelection = false;
				}
				setUpdateOnTabChange(true);
				if (dismantelProductSelection) {
					triggerProdGrpOnView(selectionDTO.getProdGrpSid(), false);
					dismantelProductSelection = false;
				}
				productBeanList.clear();
				productHierarchyNos.clear();
				List<Leveldto> productListValue = selectedProductContainer.getItemIds();
				for (Leveldto dto : productListValue) {
					productHierarchyNos.add(dto.getHierarchyNo());
					productBeanList.add(dto.getRelationshipLevelSid());
				}
			} else {
				AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
						"No Level was selected to move. Please try again. ");
			}
		} catch (Exception ex) {
			LOGGER.error(" in moveRightProduct= {}", ex);
		}

	}

	@Override
	protected void moveLeftProductButtonLogic() {
		try {
			DataSelectionLogic logic = new DataSelectionLogic();
			int forecastLevel = 0;
			int productHierarchyVersionNo = 0;
			int productRelationVersionNo = 0;
			if (productLevel.getValue() != null) {
				forecastLevel = UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]);
			}
			if (productRelationVersionComboBox.getValue() == null && selectionDTO != null) {
				productHierarchyVersionNo = selectionDTO.getProductHierVersionNo();
				productRelationVersionNo = selectionDTO.getProductRelationShipVersionNo();
			} else {
				productHierarchyVersionNo = Integer.parseInt(String.valueOf(productRelationVersionComboBox.getValue()));
				productRelationVersionNo = Integer.parseInt(
						productRelationVersionComboBox.getItemCaption(productRelationVersionComboBox.getValue()));
			}

			if (availableProduct.getValue() != null) {

				Object item = availableProduct.getValue();
				if (selectedProductContainer.size() > 0) {
					if (selectedProduct.getValue() != null) {

						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}
						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
								selectedHierarchyNos);
						List<String> removeValues = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValues.add(uncommonHierValue);
							}
						}
						if (!removeValues.isEmpty()) {
							uncommonValues.removeAll(removeValues);
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevels = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), productDescriptionMap,
									productHierarchyVersionNo, productRelationVersionNo);
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), true);
						if (newParentLevels != null) {
							int pos2 = 0;
							String parentHierarchyNo;
							Leveldto parent = null;
							for (Leveldto newLevel : newParentLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos2 != -1) {
									parentHierarchyNo = tempHNo.substring(0, pos2) + ".";
								} else {
									parentHierarchyNo = tempHNo + ".";
								}
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
									productBeanList.add(newLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newLevel, false);
									}
								}

								if (!StringUtils.isBlank(parentHierarchyNo)) {
									for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
										if (parentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											parent = selectedLevel;
											break;
										}
									}
								}
								selectedProductContainer.setParent(newLevel, parent);
								parent = newLevel;
							}
							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
									}

									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}
									selectedProductContainer.setParent(newLevel, childsParent);
								}
							}

						}

					} else {

						String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
						if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
							hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
						}
						List<String> hierarchyNos = new ArrayList<>();
						List<Leveldto> newParentLevels = null;
						List<Leveldto> newChildLevels = null;
						hierarchyNos.add(hierarchyNo + ".");
						int pos = 0;
						String selectedParentHierarchyNo = StringUtils.EMPTY;
						Leveldto selectedParent = null;
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos);
							}
							hierarchyNos.add(hierarchyNo + ".");
						}
						Collections.reverse(hierarchyNos);
						List<String> selectedHierarchyNos = new ArrayList<>();
						for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
							if (!StringUtils.isBlank(selectedLevel.getHierarchyNo())) {
								selectedHierarchyNos.add(selectedLevel.getHierarchyNo());
							}
						}
						List<String> uncommonValues = DataSelectionUtil.storeUncommonValues(hierarchyNos,
								selectedHierarchyNos);
						List<String> removeValues = new ArrayList<>();
						for (String uncommonHierValue : uncommonValues) {
							if (selectedHierarchyNos.contains(uncommonHierValue)) {
								removeValues.add(uncommonHierValue);
							}
						}
						if (!removeValues.isEmpty()) {
							uncommonValues.removeAll(removeValues);
						}
						if (!uncommonValues.isEmpty()) {
							String tempHNo = uncommonValues.get(0);
							if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
								tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
							}
							int pos2 = 0;
							pos2 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos2 != -1) {
								selectedParentHierarchyNo = tempHNo.substring(0, pos2) + ".";
							} else {
								selectedParentHierarchyNo = tempHNo + ".";
							}
						}
						if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
							for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
								if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
									selectedParent = selectedLevel;
									break;
								}
							}
						}
						if (!uncommonValues.isEmpty()) {
							newParentLevels = logic.getParentLevelsWithHierarchyNo(
									UiUtils.stringListToString(uncommonValues), productDescriptionMap,
									productHierarchyVersionNo, productRelationVersionNo);
						}
						newChildLevels = logic.getChildLevelsWithHierarchyNo(
								UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
								productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
								productHierarchyVersionNo, productRelationVersionNo,
								UiUtils.getDataSelectionFormattedLevelNo(
										String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
								forecastEligibleDate.getValue(), true);
						if (newParentLevels != null) {
							for (Leveldto newLevel : newParentLevels) {
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
									productBeanList.add(newLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newLevel, false);
									}
									selectedProductContainer.setParent(newLevel, selectedParent);
									selectedParent = newLevel;
								}
							}
							if (!newChildLevels.isEmpty()) {
								int pos3 = 0;
								String childHierarchyNo;
								Leveldto childsParent = null;
								for (Leveldto newLevel : newChildLevels) {
									String tempHNo = newLevel.getHierarchyNo();
									if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
										tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
									}
									pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
									if (pos3 != -1) {
										childHierarchyNo = tempHNo.substring(0, pos3) + ".";
									} else {
										childHierarchyNo = tempHNo + ".";
									}
									if (productBeanList.isEmpty()
											|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
										productBeanList.add(newLevel.getRelationshipLevelSid());
										selectedProductContainer.addBean(newLevel);
										if (forecastLevel != newLevel.getLevelNo()) {
											selectedProductContainer.setChildrenAllowed(newLevel, true);
										} else {
											selectedProductContainer.setChildrenAllowed(newLevel, false);
										}
									}

									if (!StringUtils.isBlank(childHierarchyNo)) {
										for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
											if (childHierarchyNo
													.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
												childsParent = selectedLevel;
												break;
											}
										}
									}

									selectedProductContainer.setParent(newLevel, childsParent);
								}
							}

						}

					}
				} else if (availableProduct.getValue() != null
						&& (DataSelectionUtil.getBeanFromId(availableProduct.getValue()).getLevelNo() > 1)) {

					String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
					if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
						hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
					}
					List<String> hierarchyNos = new ArrayList<>();
					List<Leveldto> newParentLevels = null;
					List<Leveldto> newChildLevels = null;
					hierarchyNos.add(hierarchyNo + ".");
					int pos = 0;
					String selectedParentHierarchyNo = StringUtils.EMPTY;
					Leveldto selectedParent2 = null;
					while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
						pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
						if (pos != -1) {
							hierarchyNo = hierarchyNo.substring(0, pos);
						}
						hierarchyNos.add(hierarchyNo + ".");
					}
					Collections.reverse(hierarchyNos);
					if (!StringUtils.isBlank(selectedParentHierarchyNo)) {
						for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
							if (selectedParentHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
								selectedParent2 = selectedLevel;
								break;
							}
						}
					}
					newParentLevels = logic.getParentLevelsWithHierarchyNo(UiUtils.stringListToString(hierarchyNos),
							productDescriptionMap, productHierarchyVersionNo, productRelationVersionNo);
					newChildLevels = logic.getChildLevelsWithHierarchyNo(
							UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
							productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
							productHierarchyVersionNo, productRelationVersionNo,
							UiUtils.getDataSelectionFormattedLevelNo(
									String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
							forecastEligibleDate.getValue(), true);
					if (newParentLevels != null) {
						for (Leveldto newLevel : newParentLevels) {
							if (productBeanList.isEmpty()
									|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
								productBeanList.add(newLevel.getRelationshipLevelSid());
								selectedProductContainer.addBean(newLevel);
								if (forecastLevel != newLevel.getLevelNo()) {
									selectedProductContainer.setChildrenAllowed(newLevel, true);
								} else {
									selectedProductContainer.setChildrenAllowed(newLevel, false);
								}
								selectedProductContainer.setParent(newLevel, selectedParent2);
								selectedParent2 = newLevel;
							}
						}
						if (!newChildLevels.isEmpty()) {
							int pos3 = 0;
							String childHierarchyNo;
							Leveldto childsParent = null;
							for (Leveldto newLevel : newChildLevels) {
								String tempHNo = newLevel.getHierarchyNo();
								if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
									tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
								}
								pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
								if (pos3 != -1) {
									childHierarchyNo = tempHNo.substring(0, pos3) + ".";
								} else {
									childHierarchyNo = tempHNo + ".";
								}
								if (productBeanList.isEmpty()
										|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
									productBeanList.add(newLevel.getRelationshipLevelSid());
									selectedProductContainer.addBean(newLevel);
									if (forecastLevel != newLevel.getLevelNo()) {
										selectedProductContainer.setChildrenAllowed(newLevel, true);
									} else {
										selectedProductContainer.setChildrenAllowed(newLevel, false);
									}
								}

								if (!StringUtils.isBlank(childHierarchyNo)) {
									for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
										if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
											childsParent = selectedLevel;
											break;
										}
									}
								}
								selectedProductContainer.setParent(newLevel, childsParent);
							}
						}

					}
				} else {
					Leveldto selectedParent = DataSelectionUtil.getBeanFromId(item);

					String hierarchyNo = DataSelectionUtil.getBeanFromId(item).getHierarchyNo();
					if (hierarchyNo.length() > 0 && hierarchyNo.charAt(hierarchyNo.length() - 1) == '.') {
						hierarchyNo = hierarchyNo.substring(0, hierarchyNo.length() - 1);
					}
					List<Leveldto> newChildLevels = null;
					int pos = 0;
					if (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
						while (hierarchyNo.contains(Constants.StringConstants.DOT.getConstant())) {
							pos = hierarchyNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos != -1) {
								hierarchyNo = hierarchyNo.substring(0, pos) + ".";
							}
						}
					}
					if (productBeanList.isEmpty() || !productBeanList
							.contains(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid())) {
						productBeanList.add(DataSelectionUtil.getBeanFromId(item).getRelationshipLevelSid());
						selectedProductContainer.addBean(selectedParent);
						if (forecastLevel != selectedParent.getLevelNo()) {
							selectedProductContainer.setChildrenAllowed(selectedParent, true);
						} else {
							selectedProductContainer.setChildrenAllowed(selectedParent, false);
						}
					}

					newChildLevels = logic.getChildLevelsWithHierarchyNo(
							UiUtils.parseStringToInteger(String.valueOf(productLevel.getValue()).split("-")[0]),
							productDescriptionMap, businessUnit.getValue(), DataSelectionUtil.getBeanFromId(item),
							productHierarchyVersionNo, productRelationVersionNo,
							UiUtils.getDataSelectionFormattedLevelNo(
									String.valueOf(productlevelDdlb.getValue()).split("-")[0]),
							forecastEligibleDate.getValue(), true);
					if ((newChildLevels != null) && (!newChildLevels.isEmpty())) {
						int pos3 = 0;
						String childHierarchyNo;
						Leveldto childsParent = null;
						for (Leveldto newLevel : newChildLevels) {
							String tempHNo = newLevel.getHierarchyNo();
							if (tempHNo.length() > 0 && tempHNo.charAt(tempHNo.length() - 1) == '.') {
								tempHNo = tempHNo.substring(0, tempHNo.length() - 1);
							}
							pos3 = tempHNo.lastIndexOf(Constants.StringConstants.DOT.getConstant());
							if (pos3 != -1) {
								childHierarchyNo = tempHNo.substring(0, pos3) + ".";
							} else {
								childHierarchyNo = tempHNo + ".";
							}
							if (productBeanList.isEmpty()
									|| !productBeanList.contains(newLevel.getRelationshipLevelSid())) {
								productBeanList.add(newLevel.getRelationshipLevelSid());
								selectedProductContainer.addBean(newLevel);
								if (forecastLevel != newLevel.getLevelNo()) {
									selectedProductContainer.setChildrenAllowed(newLevel, true);
								} else {
									selectedProductContainer.setChildrenAllowed(newLevel, false);
								}
							}
							if (!StringUtils.isBlank(childHierarchyNo)) {
								for (Leveldto selectedLevel : selectedProductContainer.getItemIds()) {
									if (childHierarchyNo.equals(String.valueOf(selectedLevel.getHierarchyNo()))) {
										childsParent = selectedLevel;
										break;
									}
								}
							}
							selectedProductContainer.setParent(newLevel, childsParent);
						}
					}
				}
				setUpdateOnTabChange(BooleanConstant.getTrueFlag());
				DataSelectionLogic.selectedProductTableAlignmentChange(selectedProduct, selectedProductContainer);
				productBeanLisTemp.clear();
				productHierarchyNos.clear();
				for (Leveldto dto : selectedProductContainer.getItemIds()) {
					productHierarchyNos.add(dto.getHierarchyNo());
					productBeanLisTemp.add(dto.getRelationshipLevelSid());

				}
				setProductBeanLisTemp(productBeanLisTemp);
			} else {
				AbstractNotificationUtils.getErrorNotification("No Product hierarchy level Selected",
						"No Level was selected to move. Please try again.");
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	protected void deleteViewButtonLogic() {
		return;
	}

	@Override
	protected void resetButtonLogic() {
		return;
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		return;
	}

	private void configureFields() {

		productlevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				String selectedLevel = String.valueOf(event.getProperty().getValue());
				loadFilteredProductSelection(selectedLevel);
			}

		});

	}

	final void configureOnViewMode() {
		horizontalLayout.setEnabled(false);
		selectedCustomer.setEditable(false);
		selectedCustomer.setSelectable(false);
		all.setEnabled(false);
		moveRightBtn.setEnabled(false);
		moveLeftBtn.setEnabled(false);
		level.setEnabled(false);
		customerGroup.setEnabled(false);
		customerLevel.setEnabled(false);
		customerRelationComboBox.setEnabled(false);
		customerHierarchy.setEnabled(false);
		company.setEnabled(false);
		businessUnit.setEnabled(false);
		productHierarchy.setEnabled(false);
		productRelation.setEnabled(false);
		productGroup.setEnabled(false);
		productlevelDdlb.setEnabled(false);
		availableProduct.setEnabled(false);
		allProductBtn.setEnabled(false);
		moveLeftProduct.setEnabled(false);
		moveRightProduct.setEnabled(false);
		selectedProduct.setSelectable(false);
		forecastEligibleDate.setEnabled(false);
	}

	private void loadDiscountDdlb(int discountSid, CompanyDdlbDto selectedDiscountDdlbDto) {
		LOGGER.debug("Entering loadDiscountDDLB method");
		discountDdlbLazyContainer = new LazyContainer(CompanyDdlbDto.class,
				new CompanyDdlbDao(Constant.DISCOUNT_SMALL, discountDdlbDefault, selectedDiscountDdlbDto),
				new CompanyDdlbCriteria());
		discount.setPageLength(NumericConstants.SEVEN);
		discount.setContainerDataSource(discountDdlbLazyContainer);
		if (discountSid != 0) {
			discount.select(selectedDiscountDdlbDto);
		} else {
			discount.select(discountDdlbDefault);
		}
		discount.setNullSelectionItemId(discountDdlbDefault);
		discount.setNullSelectionAllowed(true);
		discount.setImmediate(true);
		discount.setItemCaptionPropertyId("rsNo");
		discountDdlbLazyContainer.setMinFilterLength(0);
		LOGGER.debug("End of loadDiscountDDLB method");
	}

	@Override
	protected void resetTwo() {
		return;
	}

	public String getProjectionName() {
		return projectionName.getValue();
	}

	public String getProjectionDescription() {
		return description.getValue();
	}

	public void updateDataSelectionSelectedProducts() {
		LOGGER.debug("updateDataSelectionSelectedProducts starts");
		StringBuilder prodUpdateQuery = new StringBuilder();
		prodUpdateQuery.append(" Delete FROM PROJECTION_PROD_HIERARCHY where PROJECTION_MASTER_SID=")
				.append(session.getProjectionId()).append("; ");
		HelperTableLocalServiceUtil.executeUpdateQuery(prodUpdateQuery.toString());
		LOGGER.debug("updateDataSelectionSelectedProducts ends");
	}

	public void updateProjectionProdHierarchy() {

		List<Leveldto> productList = selectedProductContainer.getItemIds();
		List<String> productListEndSids = DataSelectionUtil
				.getEndLevelHierNo(dataSelectionForm.getProductHierarchyEndLevels(selectedProductContainer));
		dsLogic.saveProductHierarchyLogic(productList, productListEndSids, session.getProjectionId(), null, "save",
				selectionDTO);
	}

	/**
	 * Used to delete the projection Details table for removed hierarchy
	 *
	 * @param CollectionToString
	 */
	private void deleteProjectionDetailstable(final String CollectionToString, final String propertyName) {
		ResourceBundle tableName = ResourceBundle.getBundle("properties.Constants");
		StringBuilder queryBuilder = new StringBuilder();
		String[] tables = tableName.getString(propertyName).split(",");
		for (String tableNames : tables) {
			queryBuilder.append(SQlUtil.getQuery("deleteTemplate").replace(Constant.AT_TABLE_NAME, tableNames)
					.replace("@PD_SID", CollectionToString));
		}
		queryBuilder.append("DELETE FROM PROJECTION_DETAILS where PROJECTION_DETAILS_SID in (")
				.append(CollectionToString).append(')');

		HelperTableLocalServiceUtil.executeUpdateQuery(queryBuilder.toString());
	}

	/**
	 * Used to convert the RelationshipLevelSid in to the List of string
	 *
	 * @param leveldtos
	 * @return
	 */
	private List<String> getRelationshipSid(List<Leveldto> leveldtos) {
		List<String> relationshipSids = new ArrayList<>();
		for (Leveldto dto : leveldtos) {
			relationshipSids.add(String.valueOf(dto.getRelationshipLevelSid()));
		}
		return relationshipSids;
	}

	/**
	 * TO update the CCP Hierarchy insert , Projection_Cust, Projection_Prod and
	 * Projection_Details
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void updateDataSelectionChanges() throws ClassNotFoundException, IOException {
		final ThreadPool service = ThreadPool.getInstance();
		dsLogic.deleteFromTempCCPTable(session);
		relationLogic.ccpHierarchyInsert(session.getCurrentTableNames(), selectedCustomerContainer.getItemIds(),
				selectedProductContainer.getItemIds(), selectionDTO);
		session.addFutureMap(Constant.FILE_INSERT, new Future[] { service.submitRunnable(
				CommonUtil.getInstance().createRunnable(Constant.MERGE_QUERY, dataInsertProcedureCall())) });
		// PROJECTION_CUST_HIERARCHY INSERT CALL
		session.addFutureMap(Constant.CUST_HIERARCHY_INSERT, new Future[] { service.submitRunnable(
				CommonUtil.getInstance().createRunnable(Constant.CUST_HIERARCHY_INSERT, selectionDTO.getProjectionId(),
						selectionDTO.getSelectedCustomerRelationSid(), Boolean.TRUE)) });
		// PROJECTION_PROD_HIERARCHY INSERT CALL
		session.addFutureMap(Constant.PROD_HIERARCHY_INSERT, new Future[] {
				service.submitRunnable(CommonUtil.getInstance().createRunnable(Constant.PROD_HIERARCHY_INSERT,
						selectionDTO.getProjectionId(), selectionDTO.getSelectedProductRelationSid(), Boolean.TRUE)) });
		// PROJECTION_DETAILS_INSERT
		// TO check already projection details insert is running or not
		CommonUtil.getInstance()
				.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.PROJECTION_DETAILS_INSERT, 0));
		session.addFutureMap(Constant.PROJECTION_DETAILS_INSERT,
				new Future[] { service
						.submitRunnable(CommonUtil.getInstance().createRunnable(Constant.PROJECTION_DETAILS_INSERT,
								selectionDTO.getProjectionId(), session.getCurrentTableNames(), Boolean.TRUE)) });
		session.setHierarchyLevelDetails(new LinkedHashMap<>());
		session.setCustomerLevelDetails(
				dsLogic.getLevelValueDetails(session, selectionDTO.getCustRelationshipBuilderSid(), true));
		session.setProductLevelDetails(
				dsLogic.getLevelValueDetails(session, selectionDTO.getProdRelationshipBuilderSid(), false));
                session.setSalesHierarchyLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomRelationShipSid())));
                session.setDiscountCustomerProductLevelDetails(
                                        dsLogic.getRelationshipDetailsCustom(session, String.valueOf(session.getCustomDeductionRelationShipSid())));

	}

	/**
	 * Used to check which level is top in selected customer hierarchy either
	 * customer or contract It is used for CCP_HIERARCHY_INSERT query formation
	 *
	 * @param levelName
	 */
	private void levelCheck(String levelName) {
		if (StringUtils.isBlank(topLevelName) && ("Customer".equals(levelName) || "Trading Partner".equals(levelName)
				|| "TradingPartner".equals(levelName) || "Contract".equals(levelName))) {
			topLevelName = levelName;
		}
	}

	private String dataInsertProcedureCall() {
		String sessionId = "'" + session.getSessionId() + "'";
		String query = SQlUtil.getQuery("Product_customer_files_insert")
				.replace("?PROJECTION_ID", String.valueOf(session.getProjectionId()))
				.replace("?USER_ID", String.valueOf(session.getUserId())).replace("?SESSION_ID", sessionId);
		return query;
	}

	@Override
	public void loadProductVersionNo(Object selectedProductRelation) {
		if (selectedProductRelation != null && !SELECT_ONE.equals(String.valueOf(selectedProductRelation))) {
			List<Object[]> versionNoList = relationLogic.getVersionNoList(selectedProductRelation);
			Object value = loadComboBoxBasedOnRelationshipVersion(productRelationVersionComboBox, versionNoList);
			LOGGER.info("value= {}", value);
			productRelationVersionComboBox.select(value);
		}
	}

	@Override
	public void loadCustomerVersionNo(Object selectedCustomerRelation) {
		if (selectedCustomerRelation != null && !SELECT_ONE.equals(String.valueOf(selectedCustomerRelation))) {
			List<Object[]> versionNoList = relationLogic.getVersionNoList(selectedCustomerRelation);
			Object value = loadComboBoxBasedOnRelationshipVersion(customerRelationVersionComboBox, versionNoList);
			customerRelationVersionComboBox.select(value);
		}
	}

	@Override
	protected void loadForecastLevels(List<Leveldto> innerLevels, IndexedContainer productForecastLevelContainer,
			ComboBox level, int hierarchySid, int hierarchyVersion) {
		innerLevels.clear();
		innerLevels.addAll(
				new DataSelectionLogic().loadCustomerForecastLevel(hierarchySid, StringUtils.EMPTY, hierarchyVersion));
		productForecastLevelContainer.removeAllItems();
		for (int i = 1; i <= innerLevels.size(); i++) {
			String levelName = innerLevels.get(i - 1).getLevel();
			productForecastLevelContainer.addItem(Constant.LEVEL + i + " - " + levelName);
		}
		level.setContainerDataSource(productForecastLevelContainer);
	}

	private void configureDataSelectionDeductionLevel() {

		List<String[]> newDeductionLevelList = CommonLogic.getDataselectionDeductionLevel();
		setFirstTimeLoad(true);
		Utility.loadDdlbForDeduction(dataSelectionDeductionLevel, newDeductionLevelList, session);
		setFirstTimeLoad(false);
		setSelectedDataSelectionDeductionLevel(String.valueOf(dataSelectionDeductionLevel.getValue()));
	}

	private void loadCustomViewDropDown(ComboBox customRelationDdlb, Map<String, String> inputData) {
		setNullSelectionCustomDdlb(customRelationDdlb);
		dsLogic.loadCustomViewValues(customRelationDdlb, inputData, true);
		if (session.getCustomRelationShipSid() == 0) {
			customRelationDdlb.select(null);
		}
		customRelationDdlb.select(session.getCustomRelationShipSid());
	}

	private void loadCustomViewDeductionDropDown(ComboBox customRelationDdlb, Map<String, String> inputData) {
		setNullSelectionCustomDdlb(customRelationDdlb);
		dsLogic.loadCustomViewDeductionValues(customRelationDdlb, inputData, true);
		if (session.getCustomDeductionRelationShipSid() == 0) {
			customRelationDdlb.select(null);
		}
		customRelationDdlb.select(session.getCustomDeductionRelationShipSid());

	}

}
