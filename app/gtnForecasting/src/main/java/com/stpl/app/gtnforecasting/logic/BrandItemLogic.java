/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.CustomerProductDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandItemLogic.
 *
 * @author lokeshwari
 */
public class BrandItemLogic {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandItemLogic.class);
    
    /** The data selection. */
    private static final DataSelectionDAO dataSelection = new DataSelectionDAOImpl();

    /**
     * Gets the brand.
     *
     * @param selectedProducts the selected products
     * @return the brand
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public static String getBrand(final List<CustomerProductDTO> selectedProducts) throws SystemException {
        String brandName = null;
        final Collection<Object> itemNos = new ArrayList<>();
        final Collection<Object> itemNames = new ArrayList<>();
        LOGGER.debug("Entering getBrand method with list size" + selectedProducts.size());

        for (int i = 0; i < selectedProducts.size(); i++) {
            itemNos.add(selectedProducts.get(i).getProductNo());
            itemNames.add(selectedProducts.get(i).getProductName());
        }
        final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.in(Constant.ITEM_NO, itemNos), RestrictionsFactoryUtil.in("itemName", itemNames)));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property(Constant.BRAND)));
        List<String> brands;

        brands = dataSelection.getItemMaster(dynamicQuery);

        if (brands != null && brands.size() > 1) {
            brandName = "MultiBrand";
        } else if (brands != null) {
            brandName = brands.get(0);
        }
        LOGGER.debug("End of getBrand method");
        return brandName;
    }
}
