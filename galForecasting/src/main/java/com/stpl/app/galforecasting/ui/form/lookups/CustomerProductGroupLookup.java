/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui.form.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.app.forecastabstract.lookups.AbstractGroupLookup;

import com.stpl.app.galforecasting.logic.DataSelectionLogic;
import com.stpl.app.galforecasting.logic.GroupSearchLogic;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.TextField;

import static com.stpl.app.utils.Constants.ButtonConstants.*;

import static com.stpl.app.utils.Constants.IndicatorConstants.*;

import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.Button;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 * The Class CustomerProductGroupLookup.
 *
 * @author soundarrajan
 */
public class CustomerProductGroupLookup extends AbstractGroupLookup {

    /**
     * Indicates whether it is customer lookup or product lookup.
     */
    private String indicator;

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
    private TextField groupLookup;

    /**
     * The search result table.
     */
    private GroupDTO selectedCustHierarchy;

    private Button searchBtn = new Button(BTN_SEARCH.getConstant());
    private Button resetBtn = new Button(BTN_RESET.getConstant());
    private GroupDTO selectedProdHierarchy;
    private List<String> itemsOrCompanySids;
    private List<String> filteredSids;
    private GroupSearchLogic tableLogic = new GroupSearchLogic();
    private ExtPagedTable results = new ExtPagedTable(tableLogic);

    /**
     * Container for results table.
     */
    private final BeanItemContainer<GroupDTO> resultBean = new BeanItemContainer<GroupDTO>(GroupDTO.class);

    /**
     * Constructor for CustomerProductGroupLookup.
     *
     * @param indicator Indicates whether it is customer lookup or product
     * lookup
     * @param windowName Name of the window
     * @param groupLookup TextField which opened the lookup
     * @param sidQuery
     */
    public CustomerProductGroupLookup(final String indicator, final String windowName,
            final TextField groupLookup, final String screenName) {
        super(indicator, windowName, screenName);
        this.indicator = indicator;
        this.groupLookup = groupLookup;
        initializeComponents();
        setContent(buildGroupLookup(groupName, groupNo, results, searchBtn, resetBtn));
        configureFields();
    }

    /**
     * Constructor for CustomerProductGroupLookup.
     *
     * @param indicator Indicates whether it is customer lookup or product
     * lookup
     * @param windowName Name of the window
     * @param groupLookup TextField which opened the lookup
     * @param sidQuery
     * @param selectedGroupDTO
     */
    public CustomerProductGroupLookup(final String indicator, final String windowName, final TextField groupLookup,
            final List<String> itemsOrCompanySids, final String screenName) {
        super(indicator, windowName, screenName);
        this.screenName = screenName;
        this.indicator = indicator;
        this.groupLookup = groupLookup;
        this.itemsOrCompanySids = itemsOrCompanySids;
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
        results.setPageLength(10);
        tableLogic.setPageLength(10);
        results.setSelectable(true);

        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setStyleName(Constant.FILTER_TABLE);
    }

    /**
     * Select button logic.
     */
    @Override
    protected void btnLookupSelectLogic() throws SystemException, PortalException {
        if (results != null && results.getValue() != null) {
            DataSelectionLogic logic = new DataSelectionLogic();
            List<String> sidsFromDetails;
            if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                setSelectedCustHierarchy((GroupDTO) results.getValue());

                if (itemsOrCompanySids != null && !itemsOrCompanySids.isEmpty()) {
                    try {
                        List<String> finalCompanySids = new ArrayList<String>(itemsOrCompanySids);
                        sidsFromDetails = logic.getCustomerGroupDetails(Integer.parseInt(selectedCustHierarchy.getCustomerGroupSid()));
                        finalCompanySids.retainAll(sidsFromDetails);
                        setFilteredSids(finalCompanySids);
                    } catch (Exception ex) {
                        Logger.getLogger(CustomerProductGroupLookup.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    setFilteredSids(new ArrayList<String>());
                }
                groupLookup.setValue(String.valueOf(selectedCustHierarchy.getCustomerGroupName()));
            } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                setSelectedProdHierarchy((GroupDTO) results.getValue());
                if (itemsOrCompanySids != null && !itemsOrCompanySids.isEmpty()) {
                    try {
                        List<String> finalItemSids = new ArrayList<String>(itemsOrCompanySids);
                        sidsFromDetails = logic.getItemGroupDetails(Integer.parseInt(selectedProdHierarchy.getProductGroupSid()));
                        finalItemSids.retainAll(sidsFromDetails);
                        setFilteredSids(finalItemSids);
                    } catch (Exception ex) {
                        Logger.getLogger(CustomerProductGroupLookup.class.getName()).log(Level.SEVERE, null, ex);
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
     * Overload and Customize search logic here.
     */
    @Override
    protected void btnSearchLogic() throws SystemException, PortalException {
        String noRecords = "No Records Found";
        if ((StringUtils.EMPTY.equals(groupName.getValue()) || Constant.NULL.equals(groupName.getValue()))
                && (StringUtils.EMPTY.equals(groupNo.getValue()) || Constant.NULL.equals(groupNo.getValue()))) {
            resultBean.removeAllItems();
            if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                AbstractNotificationUtils.getErrorNotification(noRecords, "There are no Customer Groups that match the search criteria.");
            } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                AbstractNotificationUtils.getErrorNotification(noRecords, "There are no Product Groups that match the search criteria.");
            }
        } else {
            if (!tableLogic.fireSetData(setGroupDetails(indicator),INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator),false)) {
                if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(indicator)) {
                    AbstractNotificationUtils.getErrorNotification(noRecords, "There are no Customer Groups that match the search criteria.");
                } else if (INDICATOR_PRODUCT_GROUP.getConstant().equals(indicator)) {
                    AbstractNotificationUtils.getErrorNotification(noRecords, "There are no Product Groups that match the search criteria.");
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
     * @param groupName the group name
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
     * @param groupNo the group no
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

            }
        };
        notificationUtils.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

    }

    @Override
    protected void btnCloseLogic() {
        close();
    }

    public List<String> getFilteredSids() {
        return filteredSids;
    }

    public void setFilteredSids(List<String> filteredSids) {
        this.filteredSids = filteredSids;
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
