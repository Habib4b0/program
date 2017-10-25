/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.abstractsearch.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.dao.impl.ContractHeaderLogicDAOImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.domain.contract.contractheader.ContractHeaderDAO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;

/**
 * The Class CommonUtils.
 *
 * @author pvinoth
 */
public class CommonUtils {
    
    /** The dao. */
    private final ContractHeaderDAO dao = new ContractHeaderLogicDAOImpl();
    /** The helper list util. */
    HelperListUtil helperListUtil=HelperListUtil.getInstance();
     /** The object. */
    private static CommonUtils object;
    
     /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadComboBox(final ComboBox select,
            String listName) throws SystemException {
        
        final HelperDTO defaultValue=new HelperDTO(ConstantUtil.SELECT_ONE);
        select.setNullSelectionItemId(ConstantUtil.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantUtil.DESCRIPTION);
        BeanItemContainer<HelperDTO> resultContainer= new BeanItemContainer<HelperDTO>(HelperDTO.class);
        List<HelperDTO> helperList=getHelperResults(listName);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantUtil.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
            }
        });
        return select;
    }
     /**
     * Gets the single instance of CommonUtil.
     *
     * @return single instance of CommonUtil
     */
    public static CommonUtils getInstance() {
        if (object == null) {
            object = new CommonUtils();
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
            String listName, boolean isFilter) throws SystemException, PortalException {
        final HelperDTO defaultValue=new HelperDTO( 0, isFilter ? ConstantUtil.SHOW_ALL : ConstantUtil.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantUtil.DESCRIPTION);
        List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer= new BeanItemContainer<HelperDTO>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName)!=null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue()==null ? ConstantUtil.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantUtil.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
               select.setDescription((String) (select.getValue()==null ? ConstantUtil.SELECT_ONE : ((HelperDTO)select.getValue()).getDescription()));
            }
        });
        return select;
    }
    /**
     * Gets the item type.
     *
     * @param listType the list type
     * @return the item type
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public List<HelperDTO> getHelperResults(final String listType) throws SystemException {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.LIST_NAME,
                listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantUtil.DESCRIPTION));
        final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
        helperList.add(new HelperDTO(0, ConstantUtil.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }
        return helperList;
    }

}
