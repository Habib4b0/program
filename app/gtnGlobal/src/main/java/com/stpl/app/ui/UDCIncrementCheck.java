package com.stpl.app.ui;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.domain.global.udccheck.UDCIncrementalCheckDAO;
import com.stpl.app.global.dao.impl.UDCIncrementalCheckDAOImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;

// TODO: Auto-generated Javadoc
/**
 * Class contains methods to modify the Helper Table.
 *
 * @author 
 */
public final class UDCIncrementCheck {

    /** The dao. */
    private static UDCIncrementalCheckDAO dao = new UDCIncrementalCheckDAOImpl();
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(UDCIncrementCheck.class);

    /**
     * Private Constructor to make the class as utility class.
     */
    private UDCIncrementCheck() {

    }

    /**
     * Modifies the helper table based on value,list name and reference count.
     *
     * @param value the value
     * @param listName - String
     */
    public static void increment(final String value,final String listName) {
        try{
         LOGGER.debug("Entering increment with DESCRIPTION value is   :::: "+value);
        final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION,
                value));
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME,
                listName));

        List<HelperTable> list;
      
            list = dao.getHelperTableList(helperDynamicQuery);
            if (list != null && !list.isEmpty()) {

                final HelperTable helper = list.get(0);
                if (helper.getRefCount() != -1) {
                    helper.setRefCount(helper.getRefCount() + 1);
                }
                dao.updateHelperTable(helper);
            }
            
            LOGGER.debug(" Ends increment  ::::  Update Sccess ");

        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    /**
     * Modifies the helper table based on value,list name and reference count.
     *
     * @param value the value
     * @param listName - String
     */
    public static void decrement(final String value,final String listName) {
        try{
         LOGGER.debug("Entering decrement with DESCRIPTION value is   :::: "+value);
        final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION,
                value));
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME,
                listName));

        List<HelperTable> list;
       
            list = dao.getHelperTableList(helperDynamicQuery);
            if (list != null &&  !list.isEmpty()) {
                final HelperTable helper = list.get(0);
                if (helper.getRefCount() != -1) {
                    if (helper.getRefCount() == Constants.ZERO) {
                        helper.setRefCount(0);
                    } else {
                        helper.setRefCount(helper.getRefCount() - 1);
                    }
                }
                dao.updateHelperTable(helper);
            }
          LOGGER.debug(" Ends decrement  ::::  Update Sccess ");
        } catch (Exception ex) {
              LOGGER.error(ex);
        }

    }
    
    /**
     * Modifies the helper table based on value,list name and reference count.
     *
     * @param value the value
     * @param listName - String
     */
    public static void increment(final int value,final String listName) {
        try{
         LOGGER.debug("Entering increment with DESCRIPTION value is   :::: "+value);
          LOGGER.debug("Entering increment with DESCRIPTION listName is   :::: "+listName);
        final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        helperDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HELPER_TABLE_SID,
                value));
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME,
                listName));

        List<HelperTable> list;
      
            list = dao.getHelperTableList(helperDynamicQuery);
            if (list != null && !list.isEmpty()) {

                final HelperTable helper = list.get(0);
                if (helper.getRefCount() != -1) {
                    helper.setRefCount(helper.getRefCount() + 1);
                }
                dao.updateHelperTable(helper);
            }
            
            LOGGER.debug(" Ends increment  ::::  Update Sccess ");

        } catch (Exception ex) {
              LOGGER.error(ex);
        }

    }

    /**
     * Modifies the helper table based on value,list name and reference count.
     *
     * @param value the value
     * @param listName - String
     */
    public static void decrement(final int value,final String listName) {
        try{
         LOGGER.debug("Entering decrement with DESCRIPTION value is   :::: "+value);
        final DynamicQuery helperDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        helperDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtils.HELPER_TABLE_SID,
                value));
        helperDynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME,
                listName));

        List<HelperTable> list;
       
            list = dao.getHelperTableList(helperDynamicQuery);
            if (list != null &&  !list.isEmpty()) {
                final HelperTable helper = list.get(0);
                if (helper.getRefCount() != -1) {
                    if (helper.getRefCount() == Constants.ZERO) {
                        helper.setRefCount(0);
                    } else {
                        helper.setRefCount(helper.getRefCount() - 1);
                    }
                }
                dao.updateHelperTable(helper);
            }
          LOGGER.debug(" Ends decrement  ::::  Update Sccess ");
        } catch (Exception ex) {
              LOGGER.error(ex);
        }

    }

}
