/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galforecasting.nationalassumptions.dto;

import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.NdcFilterContainer;
import com.stpl.app.galforecasting.nationalassumptions.ui.lazyLoad.NdcFilterCriteria;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.DESCRIPTION;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.vaadin.addons.lazycontainer.LazyContainer;

/**
 *  Filter Generator for the filters used in the National Assumptions Screen.
 * 
 * @author sibi
 */
public class NationalAssumptionsFilterGenerator implements ExtFilterGenerator{

    private final HelperDTO ndcFilterDto = new HelperDTO(0, Constant.SHOW_ALL);
    private final ProjectionSelectionDTO projectionSelectionDTO;
    private final String screenName;
    
    public NationalAssumptionsFilterGenerator(final ProjectionSelectionDTO projectionSelectionDTO,final String screenName){
        this.projectionSelectionDTO = projectionSelectionDTO;
        this.screenName = screenName;
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
            return null;
    }

    /**
     * Returns the Customized Filter columns for the selected columns.
     *
     * @param propertyId
     * @return
     */
    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        
        if (Constant.GROUP.equals(propertyId)) {
            LazyContainer ndcFilterContainer;
            ComboBox ndcFilterDdlb = new ComboBox();
            ndcFilterDdlb.setImmediate(true);
            ndcFilterDdlb.setPageLength(7);
            ndcFilterDdlb.setInputPrompt(Constant.SHOW_ALL);
            ndcFilterDdlb.setItemCaptionPropertyId(DESCRIPTION.getConstant());
            ndcFilterDdlb.markAsDirty();
            if ("Medicaid URA".equals(screenName)) {
                ndcFilterContainer = new LazyContainer(HelperDTO.class, new NdcFilterContainer(projectionSelectionDTO.getBrandSid(),
                        false, projectionSelectionDTO.getTherapeuticSid(),true), new NdcFilterCriteria());
            } else {
                ndcFilterContainer = new LazyContainer(HelperDTO.class, new NdcFilterContainer(projectionSelectionDTO.getBrandSid(),
                        true, projectionSelectionDTO.getTherapeuticSid(),true), new NdcFilterCriteria());
            }
            ndcFilterDdlb.addStyleName(Constant.FILTER_COMBOBOX);
            ndcFilterContainer.setMinFilterLength(0);
            ndcFilterDdlb.setContainerDataSource(ndcFilterContainer);   
            ndcFilterDdlb.setNullSelectionAllowed(false);
            return ndcFilterDdlb;
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {

    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }
    
}
