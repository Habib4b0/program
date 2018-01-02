/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import com.stpl.app.gtnforecasting.dto.CompanyDdlbDto;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;
import org.jboss.logging.Logger;

/**
 *
 * @author soundarrajan
 */
public class CompanyDdlbDao implements DAO<CompanyDdlbDto> {

    private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(CompanyDdlbDao.class);
    private DataSelectionLogic logic;
    private List<String> companySids;
    private CompanyDdlbDto companyDdlbDefault;
    private CompanyDdlbDto selectedCompanyDdlbDto;
    private String componentName = StringUtils.EMPTY;
    private CompanyDdlbDto selectedDiscount;

    public CompanyDdlbDao(final List<String> companySids, CompanyDdlbDto companyDdlbDefault, CompanyDdlbDto selectedCompanyDdlbDto) {
        this.companySids = companySids;
        this.companyDdlbDefault = companyDdlbDefault;
        this.selectedCompanyDdlbDto = selectedCompanyDdlbDto;
        logic = new DataSelectionLogic();
    }

    public CompanyDdlbDao(final String componentName, CompanyDdlbDto companyDdlbDefault,CompanyDdlbDto selectedDiscount) {
        this.componentName = componentName;
        this.companyDdlbDefault = companyDdlbDefault;
        this.selectedDiscount = selectedDiscount;
        logic = new DataSelectionLogic();
    }

    public CompanyDdlbDao(final DataSelectionLogic logic, final List<String> companySids, String selectedCompanySid) {
        this.companySids = companySids;
        this.logic = logic;
    }

    @Override
    public int count(SearchCriteria sc) {
        int count = 0;
        try {
            if (Constant.DISCOUNT_SMALL.equals(componentName)) {
                count = logic.getDiscountCount(sc.getFilter());
            } else {
                count = logic.getCompaniesCount(sc.getFilter(), companySids);
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        count = count + 1;
        return count;
    }

    @Override
    public List<CompanyDdlbDto> find(SearchCriteria sc, int startIndex, int offset, List<OrderByColumn> list) {
        List<CompanyDdlbDto> resultList = new ArrayList<>();
        try {
            if (Constant.DISCOUNT_SMALL.equals(componentName)) {
                resultList = logic.getDiscounts(startIndex, startIndex + offset, sc.getFilter(), companyDdlbDefault,selectedDiscount);
            } else {
                resultList = logic.getCompaniesLazy(startIndex, startIndex + offset, sc.getFilter(), companySids, companyDdlbDefault, selectedCompanyDdlbDto);
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

}
