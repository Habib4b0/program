/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.dao.DataSelectionDAO;
import com.stpl.app.galforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.galforecasting.dto.CustomerProductDTO;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandItemLogic.
 *
 * @author lokeshwari
 */
public class BrandItemLogic {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(BrandItemLogic.class);
    
    /** The data selection. */
    private static DataSelectionDAO dataSelection = new DataSelectionDAOImpl();

    /**
     * Gets the brand.
     *
     * @param selectedProducts the selected products
     * @return the brand
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public static String getBrand(final List<CustomerProductDTO> selectedProducts) throws SystemException, Exception {
        String brandName = null;
        final Collection<Object> itemNos = new ArrayList<Object>();
        final Collection<Object> itemNames = new ArrayList<Object>();
        LOGGER.info("Entering getBrand method with list size" + selectedProducts.size());

        for (int i = 0; i < selectedProducts.size(); i++) {
            itemNos.add(selectedProducts.get(i).getProductNo());
            itemNames.add(selectedProducts.get(i).getProductName());
        }
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.in(Constant.ITEM_NO, itemNos), RestrictionsFactoryUtil.in("itemName", itemNames)));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constant.BRAND)));
        List<String> brands;

        brands = dataSelection.getItemMaster(dynamicQuery);

        if (brands != null && brands.size() > 1) {
            brandName = "MultiBrand";
        } else if (brands != null) {
            brandName = brands.get(0);
        }
        LOGGER.info("End of getBrand method");
        return brandName;
    }
}
