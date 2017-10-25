/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.ui.view;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.itemgroup.dto.ItemDetailsDTO;
import com.stpl.app.adminconsole.itemgroup.dto.ItemGroupDTO;
import com.stpl.app.adminconsole.itemgroup.logic.ItemGroupLogic;
import com.stpl.app.adminconsole.itemgroup.ui.form.ItemGroupInfo;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

/**
 * The Class ItemGroupView.
 *
 * @author vishalakshi
 */
public class ItemGroupView extends VerticalLayout implements View {

    private static final Logger LOGGER = Logger.getLogger(ItemGroupView.class);

    public static final String NAME = "ItemGroup";

    private ItemGroupDTO itemGroupDTO = new ItemGroupDTO();

    private ItemDetailsDTO itemDTO = new ItemDetailsDTO();

    private ErrorfulFieldGroup itemGroupBinder = new ErrorfulFieldGroup(new BeanItem<ItemGroupDTO>(itemGroupDTO));

    private ErrorfulFieldGroup itemBinder = new ErrorfulFieldGroup(new BeanItem<ItemDetailsDTO>(itemDTO));

    private BeanItemContainer<ItemDetailsDTO> selectedResultsBean = new BeanItemContainer<ItemDetailsDTO>(ItemDetailsDTO.class);
    private BeanItemContainer<ItemDetailsDTO> availableResultsBean = new BeanItemContainer<ItemDetailsDTO>(ItemDetailsDTO.class);

    private ItemGroupInfo itemGroupInfo;

    SessionDTO sessionDTO;

    public ItemGroupDTO getItemGroupDTO() {
        return itemGroupDTO;
    }

    public void setItemGroupDTO(final ItemGroupDTO itemGroupDTO) {
        this.itemGroupDTO = itemGroupDTO;
    }

    public ErrorfulFieldGroup getItemGroupBinder() {
        return itemGroupBinder;
    }

    public void setItemGroupBinder(final ErrorfulFieldGroup itemGroupBinder) {
        this.itemGroupBinder = itemGroupBinder;
    }

    public ErrorfulFieldGroup getItemBinder() {
        return itemBinder;
    }

    public void setItemBinder(final ErrorfulFieldGroup itemBinder) {
        this.itemBinder = itemBinder;
    }

    public ItemDetailsDTO getItemDTO() {
        return itemDTO;
    }

    public BeanItemContainer<ItemDetailsDTO> getSelectedResultsBean() {
        return selectedResultsBean;
    }

    public ItemGroupInfo getItemGroupInfo() {
        return itemGroupInfo;
    }

    public BeanItemContainer<ItemDetailsDTO> getAvailableResultsBean() {
        return availableResultsBean;
    }

    public void setAvailableResultsBean(BeanItemContainer<ItemDetailsDTO> availableResultsBean) {
        this.availableResultsBean = availableResultsBean;
    }

    /**
     * Instantiates a new item group view.
     *
     * @param sessionDTO
     * @throws Exception
     * @throws PortalException
     * @throws SystemException
     */
    public ItemGroupView(final SessionDTO sessionDTO) throws SystemException, PortalException {
        super();
        try {
            this.sessionDTO = sessionDTO;
            itemGroupInfo = new ItemGroupInfo(itemGroupDTO, itemGroupBinder, itemBinder, itemDTO, selectedResultsBean, availableResultsBean, sessionDTO);
            addComponent(itemGroupInfo);
            setSpacing(true);
            setStyleName("bootstrap");
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("enter ViewChangeListener Started");
        try {

            final ItemGroupLogic logic = new ItemGroupLogic();
            final int itemGroupSystemId = sessionDTO.getSystemId();
            Boolean flag = false;
            this.removeAllComponents();
            itemGroupBinder = new ErrorfulFieldGroup(new BeanItem<ItemGroupDTO>(itemGroupDTO));
            itemBinder = new ErrorfulFieldGroup(new BeanItem<ItemDetailsDTO>(itemDTO));
            itemGroupDTO = new ItemGroupDTO();
            itemDTO = new ItemDetailsDTO();
            itemGroupInfo = new ItemGroupInfo(itemGroupDTO, itemGroupBinder, itemBinder, itemDTO, selectedResultsBean, availableResultsBean, sessionDTO);
            addComponent(itemGroupInfo);
            setStyleName("bootstrap");
            if (itemGroupSystemId == ConstantsUtils.ZERO_NUM) {
                itemGroupBinder = new ErrorfulFieldGroup(new BeanItem<ItemGroupDTO>(itemGroupDTO));
                itemBinder = new ErrorfulFieldGroup(new BeanItem<ItemDetailsDTO>(itemDTO));
            } else {
                itemBinder = new ErrorfulFieldGroup(new BeanItem<ItemDetailsDTO>(itemDTO));

                selectedResultsBean.removeAllItems();
                availableResultsBean.removeAllItems();
                String pageName = sessionDTO.getLogic();
                List<ItemDetailsDTO> itemDetails = new ArrayList<ItemDetailsDTO>();
                List<ItemDetailsDTO> availableContainer = new ArrayList<ItemDetailsDTO>();
                if (ConstantsUtils.LOWERCASE_EDIT.equalsIgnoreCase(pageName) || ConstantsUtils.VIEW.equalsIgnoreCase(pageName) || ConstantsUtils.LOWERCASE_COPY.equalsIgnoreCase(pageName)) {

                    final int version = sessionDTO.getVersionNo();
                    itemGroupDTO = new ItemGroupLogic().getHistoryItemGroupInfo(version, sessionDTO);

                    if (!StringUtils.isEmpty(itemGroupDTO.getItemFilter())) {
                        List availableList = ItemGroupLocalServiceUtil.getAvailableSearchResults(itemGroupDTO.getItemFilter());
                        if (availableList.size() > 0) {
                            availableContainer = logic.getCustomizedItemResults(availableList);
                        }
                    }

                    itemDetails = new ItemGroupLogic().getSavedHistoryItemDetails(itemGroupSystemId, version);
                    List<String> list = new ArrayList<>();
                    for (ItemDetailsDTO dto : itemDetails) {
                        list.add(String.valueOf(dto.getItemSystemId()));
                    }
                    sessionDTO.setSelectedItems(list);
                    flag = true;
                }
                if (!ConstantsUtils.LOWERCASE_COPY.equalsIgnoreCase(pageName) && !availableContainer.isEmpty()) {
                        availableResultsBean.addAll(availableContainer);
                }
                if (!itemDetails.isEmpty()) {

                    selectedResultsBean.addAll(itemDetails);
                }

            }

            itemGroupInfo.entry(flag);
        } catch (SystemException e) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
        } catch (Exception e) {
            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
        }
        LOGGER.debug("enter ViewChangeListener Ended");
    }
}
