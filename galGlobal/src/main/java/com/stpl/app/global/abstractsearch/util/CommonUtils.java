package com.stpl.app.global.abstractsearch.util;

import com.stpl.app.global.dao.impl.ItemSearchLogicDAOImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.domain.global.item.ItemDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.Role;
import com.stpl.portal.service.RoleLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 * The Class CommonUtils.
 */
public class CommonUtils {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
    /**
     * The Constant EMPTY.
     */
    public static final String EMPTY = "";
    /**
     * The Constant STRING_ASTERISK.
     */
    public static final String STRING_ASTERISK = "*";
    /**
     * The Constant MMDDYYYY.
     */
    public static final String MMDDYYYY = "MM/dd/yyyy";
    /**
     * The Constant MMDDYYYYHHMMMSS.
     */
    public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
    /**
     * The Constant STRING_NULL.
     */
    public static final String STRING_NULL = ConstantsUtils.NULL;
    /**
     * The Constant STRING_ZERO.
     */
    public static final String STRING_ZERO = "0";
    /**
     * The Constant STRING_NATIVE_SELECT.
     */
    public static final String STRING_NATIVE_SELECT = "return NativeSelect :";
    /**
     * The Constant NOT_IN.
     */
    public static final String NOT_IN = " not in (";
    /**
     * The Constant STRING_IN.
     */
    public static final String STRING_IN = " in (";
    /**
     * The Constant STRING_RETURN.
     */
    public static final String STRING_RETURN = "return returnValue  ";
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    /**
     * The Constant ZERO.
     */
    public static final int ZERO = 0;
    /**
     * The Constant ONE.
     */
    public static final int ONE = 1;
    /**
     * The Constant CONSTANT.
     */
    public static final int CONSTANT = 999;
    /**
     * Exception message.
     */
    public static final String EXCEPTION_MSG = "Exception in isValidValue -";
    /**
     * The Constant QUOTE.
     */
    public static final String QUOTE = "\"";
    /*
     * @param listTypeId- listType typeid of listtype table to get the description
     * @return String - The description for the specific listtype id
     */
    public static final CommonUtils commonUtils = new CommonUtils();
    private static final ItemDAO DAO = new ItemSearchLogicDAOImpl();
        
    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName
     * @return the native select
     * @throws java.lang.Exception
     */
    public ComboBox loadComboBox(final ComboBox select,
            String listName) throws Exception {
        
        final HelperDTO defaultValue=new HelperDTO(ConstantsUtils.SELECT_ONE);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        BeanItemContainer<HelperDTO> resultContainer= new BeanItemContainer<HelperDTO>(HelperDTO.class);
        List<HelperDTO> helperList=getHelperResults(listName);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
            }
        });
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
            LazyContainer lazyContainer ) throws Exception {
        final HelperDTO defaultValue=new HelperDTO(ConstantsUtils.SELECT_ONE);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        lazyContainer.setMinFilterLength(ZERO);
        select.setContainerDataSource(lazyContainer);
        select.select(defaultValue);
        select.markAsDirty();
        
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
            }
        });
        return select;
    }
    
    /**
     * Gets the item type.
     *
     * @param listType the list type
     * @return the item type
     */
    public List<HelperDTO> getHelperResults(final String listType) throws SystemException, Exception {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        helperList.add(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }
        return helperList;
    }
    public boolean checkETLUser(int userId) {
    
        try {
            return checkETL(String.valueOf(userId));
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return false;
    
    }
    public boolean checkETL(String userId) throws SystemException {
        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(Long.valueOf(userId));
        List roleList = new ArrayList();
        for (Role role : userRoles) {
            roleList.add(role.getRoleId());
        }
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(Role.class);
        query.add(RestrictionsFactoryUtil.eq("name", "ETL"));
        query.add(RestrictionsFactoryUtil.in("roleId", roleList));
        List<Role> userList = RoleLocalServiceUtil.dynamicQuery(query);
        if (!userList.isEmpty() && userList.size() > 0) {
            return true;
        }
        return false;
    }
  public ComboBox loadComboBoxForFilters(final ComboBox select,
            String listName,boolean isFilter) throws Exception {
        
       final HelperDTO defaultValue=new HelperDTO( 0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        BeanItemContainer<HelperDTO> resultContainer= new BeanItemContainer<HelperDTO>(HelperDTO.class);
        List<HelperDTO> helperList=getHelperResult(listName);
        helperList.add(defaultValue);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
            }
        });
        return select;
    }
          public List<HelperDTO> getHelperResult(final String listType) throws SystemException, Exception {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
        helperList.add(new HelperDTO(0, ConstantsUtils.SHOW_ALL));
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
