/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ppaprojection.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.PPAProjectionDao;
import com.stpl.app.gtnforecasting.dao.impl.PPAProjectionDaoImpl;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author karthikeyans
 */
public class PPAServiceSupport {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PPAServiceSupport.class.getName());
    /**
     * The id desc map.
     */
    private final Map<Integer, String> priceQualifierMap = new HashMap<>();

    private static final PPAProjectionDao DAO = new PPAProjectionDaoImpl();

    private static PPAServiceSupport object = null;

    private static final List<HelperDTO> priceResultList = new ArrayList<>();

    /**
     * Returns single instance of HelperListUtil.
     *
     *
     * @return
     */
    public static PPAServiceSupport getInstance() {
        synchronized (PPAServiceSupport.class) {
            if (object == null) {
                object = new PPAServiceSupport();
            }
            return object;
        }
    }

    public void getPriceTypeResults() {

        priceResultList.clear();

        final DynamicQuery cfpDynamicQuery = ItemPricingQualifierLocalServiceUtil.dynamicQuery();
        final ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        try {
            projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUALIFIER_SID));
            projectionList.add(ProjectionFactoryUtil.property(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
            cfpDynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(ConstantsUtils.ITEM_PRICING_QUAL_NAME, StringUtils.EMPTY)));
            cfpDynamicQuery.add(RestrictionsFactoryUtil.isNotNull(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
            cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.ITEM_PRICING_QUAL_NAME));
            cfpDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projectionList));
            final List<Object[]> returnList = DAO.getItemPricingTypeList(cfpDynamicQuery);
            HelperDTO helperTable = new HelperDTO(0,ConstantsUtils.SELECT_ONE);
            priceResultList.add(helperTable);
            priceQualifierMap.put(0, ConstantsUtils.SELECT_ONE);
            for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
                final Object[] value = iterator.next();
                helperTable = new HelperDTO();
                helperTable.setId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
                helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);

                if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {

                    priceResultList.add(helperTable);

                    priceQualifierMap.put(helperTable.getId(), helperTable.getDescription());
                }
            }
            LOGGER.debug("Ending getPriceTypeResults in PPA  return map size :" + +priceQualifierMap.size());
        } catch (SystemException | NumberFormatException e) {
           LOGGER.error(e.getMessage());
        }

    }

    public Map<Integer, String> getPriceQualifierMap() {
        return priceQualifierMap;
    }

    public List<HelperDTO> getPriceResultList() {
        return priceResultList;
    }
    public String getDescriptionByID(int id) {
        return id==0 ? StringUtils.EMPTY : priceQualifierMap.get(id);
    } 

}