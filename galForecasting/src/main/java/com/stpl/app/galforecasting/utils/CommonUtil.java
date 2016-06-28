/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.model.HelperTable;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 * The Class CommonUtil.
 *
 * @author pvinoth
 */
public class CommonUtil {

    /**
     * The object.
     */
    private static CommonUtil object;

    /**
     * The helper list util.
     */
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    private static Logger logger = Logger.getLogger(CommonUtil.class);

    /**
     * Instantiates a new common util.
     */
    private CommonUtil() {

    }

    /**
     * Gets the single instance of CommonUtil.
     *
     * @return single instance of CommonUtil
     */
    public static CommonUtil getInstance() {
        if (object == null) {
            object = new CommonUtil();
        }
        return object;
    }
    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter) throws Exception {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription(String.valueOf((select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription())));
            }
        });
        return select;
    }

    public ComboBox loadComboBoxWithInteger(final ComboBox select,
            String listName, boolean isFilter) throws Exception {
        select.removeAllItems();
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.setData(listName);
        select.addItem(0);

        select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        List<HelperDTO> list = helperListUtil.getListNameMap().get(listName);
        if ("PRICE_TOLERANCE_INTERVAL".equalsIgnoreCase(listName)) {
            getHelperDTOSortByInteger(list);
        }
        for (HelperDTO helperDTO : list) {
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        select.select(0);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));

        return select;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadYesNoDDLB(final ComboBox select,
            boolean isFilter) throws Exception {

        final String defaultValue = isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE;
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.addItem(defaultValue);
        select.addItem(ConstantsUtils.YES_VARIABLE);
        select.addItem(ConstantsUtils.NO_VARIABLE);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
            }
        });
        return select;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadActiveInactiveDDLB(final ComboBox select,
            boolean isFilter) throws Exception {

        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.DESCRIPTION, new Object[]{Constant.ACTIVE, Constant.INACTIVE}));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        List<HelperTable> list = helperListUtil.getDynamicQuery(dynamicQuery);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        resultContainer.addItem(defaultValue);
        for (Iterator<HelperTable> it = list.iterator(); it.hasNext();) {
            HelperTable helperTable = it.next();
            HelperDTO dto = new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription());
            resultContainer.addItem(dto);
        }
        select.setContainerDataSource(resultContainer);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.select(defaultValue);

        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription(String.valueOf((select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue())));
            }
        });
        return select;
    }

    public ComboBox loadActiveInactiveIntergerDDLB(final ComboBox select,
            boolean isFilter) throws Exception {

        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.DESCRIPTION, new Object[]{Constant.ACTIVE, Constant.INACTIVE}));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        List<HelperTable> list = helperListUtil.getDynamicQuery(dynamicQuery);
        select.setItemCaption(select.addItem(0), isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        for (Iterator<HelperTable> it = list.iterator(); it.hasNext();) {
            HelperTable helperTable = it.next();
            select.addItem(helperTable.getHelperTableSid());
            select.setItemCaption(helperTable.getHelperTableSid(), helperTable.getDescription());
        }
        select.select(0);
        return select;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param lazyContainer
     * @return the native select
     * @throws java.lang.Exception
     */
   public ComboBox loadLazyComboBox(final ComboBox select,
            LazyContainer lazyContainer, final Object defaultValue) throws Exception {
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);;
        lazyContainer.setMinFilterLength(Constant.ZERO);
        select.setContainerDataSource(lazyContainer);
        select.select(defaultValue);
        select.setPageLength(7);
        select.markAsDirty();

        return select;
    }

    public ComboBox loadIndexedComboBox(final ComboBox select,
            BeanItemContainer container, final Object defaultValue) throws Exception {
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);;
        select.setContainerDataSource(container);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.select(defaultValue);
        select.setPageLength(7);
        select.markAsDirty();
        return select;
    }

    public ComboBox loadIntegerComboBox(final ComboBox select, List<HelperDTO> list) throws Exception {
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);

        for (HelperDTO helperDTO : list) {
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        select.select(0);
        select.setPageLength(7);
        select.markAsDirty();
        return select;
    }
    /**
     * 
     * @param select
     * @param lazyContainer
     * @param defaultValue
     * @param propertyId
     * @return
     * @throws Exception 
     */
    public ComboBox loadLazyitemComboBox(final ComboBox select,
            LazyContainer lazyContainer, final Object defaultValue,String propertyId) throws Exception {
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(propertyId);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);;
        lazyContainer.setMinFilterLength(Constant.ZERO);
        select.setContainerDataSource(lazyContainer);
        select.select(defaultValue);
        select.setPageLength(7);
        select.markAsDirty();

        return select;
    }
    
    /**
     * Sort by Integer
     * @param list
     * @return 
     */
    public List<HelperDTO> getHelperDTOSortByInteger(List<HelperDTO> list) {
        Collections.sort(list, new Comparator<HelperDTO>() {
            @Override
            public int compare(final HelperDTO lhs, HelperDTO rhs) {
                if (Integer.valueOf(lhs.getDescription()) > Integer.valueOf(rhs.getDescription())) {
                    return 1;
                } else if (Integer.valueOf(lhs.getDescription()) < Integer.valueOf(rhs.getDescription())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return list;
    }
}
