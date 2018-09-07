/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.forecastabstract.lookups.AbstractGroupLookup;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.GroupSearchLogic;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import static com.stpl.app.utils.Constants.ButtonConstants.*;
import static com.stpl.app.utils.Constants.IndicatorConstants.*;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.Button;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.TextField;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CustomerProductGroupLookup.
 *
 * @author soundarrajan
 */
public class CustomerProductGroupLookup extends AbstractGroupLookup {

	/**
	 * Indicates whether it is customer lookup or product lookup.
	 */
	private final String indicator;

	/**
	 * The groupName TextField for searching.
	 */
	private TextField groupName;

	/**
	 * The groupNo TextField for searching.
	 */
	private TextField groupNo;

	/**
	 * groupLookup TextField which opened the lookup.
	 */
	private final TextField groupLookup;

	/**
	 * The search result table.
	 */
	private GroupDTO selectedCustHierarchy;

	private final Button searchBtn = new Button(BTN_SEARCH.getConstant());
	private final Button resetBtn = new Button(BTN_RESET.getConstant());
	private GroupDTO selectedProdHierarchy;
	private List<String> itemsOrCompanySids;
	private List<String> filteredSids;
	private final GroupSearchLogic tableLogic = new GroupSearchLogic();
	private final ExtPagedTable results = new ExtPagedTable(tableLogic);

	/**
	 * Container for results table.
	 */
	private final BeanItemContainer<GroupDTO> resultBean = new BeanItemContainer<>(GroupDTO.class);
        private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProductGroupLookup.class);


	public CustomerProductGroupLookup(final String indicator, final String windowName, final TextField groupLookup,
			final String screenName) {
		super(indicator, windowName, screenName);
		this.indicator = indicator;
		this.groupLookup = groupLookup;
	}

	public CustomerProductGroupLookup(final String indicator, final String windowName, final TextField groupLookup,
			final List<String> itemsOrCompanySids, final String screenName) {
		super(indicator, windowName, screenName);
		this.setScreenName(screenName);
		this.indicator = indicator;
		this.groupLookup = groupLookup;
		this.itemsOrCompanySids = itemsOrCompanySids == null ? itemsOrCompanySids : new ArrayList<>(itemsOrCompanySids);
	}

	public void init() {
		initializeComponents();
		setContent(buildGroupLookup(groupName, groupNo, results, searchBtn, resetBtn, tableLogic));
		configureFields();
	}

	/**
	 * Initializes the components.
	 */
	private void initializeComponents() {
		setGroupName(UiUtils.addTextField());
		setGroupNo(UiUtils.addTextField());
		groupName.setImmediate(true);
		groupName.focus();
		tableLogic.setContainerDataSource(resultBean);
	}

	/**
	 * Configures all the components.
	 */
	private void configureFields() {
		// Configures all the components here
		center();
		setClosable(true);
		setModal(true);
		setWidth("980px");
		setHeight("850px");
		results.setPageLength(NumericConstants.TEN);
		tableLogic.setPageLength(NumericConstants.TEN);
		results.setSelectable(true);
		tableLogic.sinkItemPerPageWithPageLength(false);
		results.setFilterBarVisible(true);
		results.setFilterDecorator(new ExtDemoFilterDecorator());
		results.setStyleName(Constant.FILTER_TABLE);
		results.setSizeFull();
		results.markAsDirty();
		results.setImmediate(true);
	}

	/**
	 * Select button logic.
     * @throws com.stpl.portal.kernel.exception.SystemException
	 */
	protected void btnLookupSelectLogic1() throws PortalException {
		if (results != null && results.getValue() != null) {
			DataSelectionLogic logic = new DataSelectionLogic();
			List<String> sidsFromDetails;
			if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
				setSelectedCustHierarchy((GroupDTO) results.getValue());

				if (itemsOrCompanySids != null && !itemsOrCompanySids.isEmpty()) {
					try {
						List<String> finalCompanySids = new ArrayList<>(itemsOrCompanySids);
						sidsFromDetails = logic
								.getCustomerGroupDetails(Integer.parseInt(selectedCustHierarchy.getCustomerGroupSid()));
						finalCompanySids.retainAll(sidsFromDetails);
						setFilteredSids(finalCompanySids);
					} catch (SystemException | NumberFormatException ex) {
						LoggerFactory.getLogger(CustomerProductGroupLookup.class.getName()).error( StringUtils.EMPTY, ex);
					}
				} else {
					setFilteredSids(new ArrayList<String>());
				}
				groupLookup.setValue(String.valueOf(selectedCustHierarchy.getCustomerGroupName()));
			} else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
				setSelectedProdHierarchy((GroupDTO) results.getValue());
				if (itemsOrCompanySids != null && !itemsOrCompanySids.isEmpty()) {
					try {
						List<String> finalItemSids = new ArrayList<>(itemsOrCompanySids);
						sidsFromDetails = logic
								.getItemGroupDetails(Integer.parseInt(selectedProdHierarchy.getProductGroupSid()));
						finalItemSids.retainAll(sidsFromDetails);
						setFilteredSids(finalItemSids);
					} catch (SystemException | NumberFormatException ex) {
						LoggerFactory.getLogger(CustomerProductGroupLookup.class.getName()).error( StringUtils.EMPTY, ex);
					}
				} else {
					setFilteredSids(new ArrayList<String>());
				}
				groupLookup.setValue(String.valueOf(selectedProdHierarchy.getProductGroupName()));
			}
			close();

		} else {
			AbstractNotificationUtils.getErrorNotification("Confirm Selection",
					"You have not selected a group to select. Please click a group and try again.");
		}
	}

	/**
	 * Select button logic.
     * @throws com.liferay.portal.kernel.exception.PortalException
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
	 */
	@Override
	protected void btnLookupSelectLogic() {
		List<String> sidsFromDetails;
		if (results != null && results.getValue() != null) {
			DataSelectionLogic logic = new DataSelectionLogic();
			try {
				if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
					selectedCustHierarchy = (GroupDTO) results.getValue();
					sidsFromDetails = logic
							.getCustomerGroupDetails(Integer.parseInt(selectedCustHierarchy.getCustomerGroupSid()));
					setFilteredSids(sidsFromDetails);
					groupLookup.setValue(String.valueOf(selectedCustHierarchy.getCustomerGroupName()));
				} else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
					selectedProdHierarchy = (GroupDTO) results.getValue();
					sidsFromDetails = logic
							.getItemGroupDetails(Integer.parseInt(selectedProdHierarchy.getProductGroupSid()));
					setFilteredSids(sidsFromDetails);
					groupLookup.setValue(String.valueOf(selectedProdHierarchy.getProductGroupName()));
				}
				close();
			} catch (SystemException | Property.ReadOnlyException | NumberFormatException ex) {
				LoggerFactory.getLogger(CustomerProductGroupLookup.class.getName()).error( StringUtils.EMPTY, ex);
			}
		} else {
			AbstractNotificationUtils.getErrorNotification("Confirm Selection",
					"You have not selected a group to select. Please click a group and try again.");
		}

	}

	/**
	 * Overload and Customize search logic here.
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
	 */
	@Override
	protected void btnSearchLogic() {
		String noRecords = "No Records Found";
		if ((StringUtils.EMPTY.equals(groupName.getValue()) || Constant.NULL.equals(groupName.getValue()))
				&& (StringUtils.EMPTY.equals(groupNo.getValue()) || Constant.NULL.equals(groupNo.getValue()))) {
			resultBean.removeAllItems();
			if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
				AbstractNotificationUtils.getErrorNotification(noRecords,
						"There are no Customer Groups that match the search criteria.");
			} else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
				AbstractNotificationUtils.getErrorNotification(noRecords,
						"There are no Product Groups that match the search criteria.");
			}
		} else {
			if (!tableLogic.fireSetData(setGroupDetails(indicator),
					INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator), false)) {
				if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
					AbstractNotificationUtils.getErrorNotification(noRecords,
							"There are no Customer Groups that match the search criteria.");
				} else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
					AbstractNotificationUtils.getErrorNotification(noRecords,
							"There are no Product Groups that match the search criteria.");
				}
			}
		}
	}

	/**
	 * Gets the group name.
	 *
	 * @return the group name
	 */
	public TextField getGroupName() {
		return groupName;
	}

	/**
	 * Sets the group name.
	 *
	 * @param groupName
	 *            the group name
	 */
	public void setGroupName(final TextField groupName) {
		this.groupName = groupName;
	}

	/**
	 * Gets the group no.
	 *
	 * @return the group no
	 */
	public TextField getGroupNo() {
		return groupNo;
	}

	/**
	 * Sets the group no.
	 *
	 * @param groupNo
	 *            the group no
	 */
	public void setGroupNo(final TextField groupNo) {
		this.groupNo = groupNo;
	}

	public GroupDTO getSelectedCustHierarchy() {
		return selectedCustHierarchy;
	}

	public void setSelectedCustHierarchy(GroupDTO selectedCustHierarchy) {
		this.selectedCustHierarchy = selectedCustHierarchy;
	}

	public GroupDTO getSelectedProdHierarchy() {
		return selectedProdHierarchy;
	}

	public void setSelectedProdHierarchy(GroupDTO selectedProdHierarchy) {
		this.selectedProdHierarchy = selectedProdHierarchy;
	}

	@Override
	protected void btnResetLogic() {
		NotificationUtils notificationUtils = new NotificationUtils() {
			@Override
			public void yesMethod() {
				groupName.setValue(StringUtils.EMPTY);
				groupNo.setValue(StringUtils.EMPTY);
			}

			@Override
			public void noMethod() {
                            LOGGER.debug("Inside overriden method: Do nothing");
			}
		};
		notificationUtils.getConfirmationMessage("Confirm Reset",
				"Are you sure you want to reset the page to default values?");

	}

	@Override
	protected void btnCloseLogic() {
		close();
	}

	public List<String> getFilteredSids() {
		return filteredSids == null ? filteredSids : new ArrayList<>(filteredSids);
	}

	public void setFilteredSids(List<String> filteredSids) {
		this.filteredSids = filteredSids == null ? filteredSids : new ArrayList<>(filteredSids);
	}

	private GroupDTO setGroupDetails(final String indicator) {
		GroupDTO dto = new GroupDTO();
		String sids = CommonUtils.CollectionToString(itemsOrCompanySids, false);
		if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
			dto.setCustomerGroupName(groupName.getValue());
			dto.setCustomerGroupNo(groupNo.getValue());
			dto.setCustomerGroupSid(sids);
		} else {
			dto.setProductGroupName(groupName.getValue());
			dto.setProductGroupNo(groupNo.getValue());
			dto.setProductGroupSid(sids);
		}
		return dto;
	}

}
