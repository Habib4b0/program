/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountprojectionresults.dto;

import com.stpl.app.galforecasting.discountprojectionresults.logic.MMDPRLogic;
import com.stpl.app.galforecasting.discountprojectionresults.logic.tablelogic.DPRTableLogic;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.DataSelectionLogic;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author gopinath
 */
public class ComparisonFilterGenerator implements ExtFilterGenerator {
    ProjectionSelectionDTO mmProjectionDTO;
    DataSelectionLogic logic = new DataSelectionLogic();
    DPRTableLogic mmDprLogic;
    public static final Logger LOGGER = Logger.getLogger(ComparisonFilterGenerator.class);

    public ComparisonFilterGenerator(ProjectionSelectionDTO mmProjectionDTO, DPRTableLogic mmDprLogic) {
        this.mmProjectionDTO = mmProjectionDTO;
        this.mmDprLogic = mmDprLogic;
    }


    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {

        if (originatingField instanceof ComboBox || originatingField instanceof TextField) {
            if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        if (Constant.GROUP.equals(propertyId)) {
            final ComboBox contractType = new ComboBox();
            try {

                contractType.setNullSelectionAllowed(true);
                contractType.setNullSelectionItemId(SELECT_ONE);
                contractType.setImmediate(true);
                contractType.addStyleName(Constant.FILTER_COMBOBOX);
                contractType.setValue(SELECT_ONE);

                String str = logic.getCheckValue(StringUtils.EMPTY, StringUtils.EMPTY, String.valueOf(mmProjectionDTO.getProjectionId()));
                if (str.length() > 0) {
                    MMDPRLogic mmLogic = new MMDPRLogic();
                    List list = mmLogic.loadCustomerDdlb(mmProjectionDTO, contractType, str);
                    contractType.addItem(0);
                    contractType.setItemCaption(0, SELECT_ONE);
                    contractType.setValue(SELECT_ONE);
                     contractType.setImmediate(true);
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            Object[] obj = (Object[]) list.get(i);
                            String str2 = String.valueOf(list.get(i));
                            contractType.addItem(obj[0] == null ? StringUtils.EMPTY : obj[0].toString() + "~" + Constant.MANDATED_DISCOUNT);
                            contractType.setItemCaption(obj[0].toString() + "~" + Constant.MANDATED_DISCOUNT == null ? StringUtils.EMPTY : obj[0].toString() + "~" + Constant.MANDATED_DISCOUNT, obj[0] == null ? StringUtils.EMPTY
                                    : "Mandated Discount " + "-"+ " " + obj[0].toString());
                            contractType.addItem(obj[0] == null ? StringUtils.EMPTY : obj[0].toString() + "~" + Constant.MANDATED_SUPPLEMENTAL);
                            contractType.setItemCaption(obj[0].toString() + "~" + Constant.MANDATED_SUPPLEMENTAL == null ? StringUtils.EMPTY : obj[0].toString() + "~" + Constant.MANDATED_SUPPLEMENTAL, obj[0] == null ? StringUtils.EMPTY
                                    : "Mandated Supplemental " +"-" + " " + obj[0].toString());
                        }
                    }
                }
                return contractType;

            } catch (Exception e) {
                LOGGER.error(e.getMessage());

            }

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
