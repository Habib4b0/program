/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.lazyLoad.VarianceTableLogic;
import com.stpl.app.cff.util.CommonUtils;
import static com.stpl.app.cff.util.ConstantsUtil.SELECT_ONE;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
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
 * @author Jayaram
 */
public class ComparisonFilterGenerator implements ExtFilterGenerator {
    CommonLogic commonLogic = new CommonLogic();
    CommonUtils utils = new CommonUtils();
    PVSelectionDTO pvSelectionDTO;
    ProjectionSelectionDTO sprProjectionDTO;
    private static final String ZERO = "0";
    ProjectionSelectionDTO psDTO;
    boolean detailFlag;
    boolean prFlag = false;
    boolean sprFlag=false;
    boolean pvFlag=false;
    boolean mmDprFlag = false;


    String indicator = StringUtils.EMPTY;
    int levelNo = 0;
    public static final Logger LOGGER = Logger.getLogger(ComparisonFilterGenerator.class);

    public ComparisonFilterGenerator( PVSelectionDTO projectionVarianceSelectionDTO, VarianceTableLogic tableLogic, boolean detailFlag) {
        this.pvSelectionDTO = projectionVarianceSelectionDTO;
        this.detailFlag = detailFlag;
        pvFlag = true;
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
        if ("marketType".equals(propertyId)) {
            //PV Comparision
            ComboBox contractType = new ComboBox();
            contractType.setImmediate(true);
            contractType.setNullSelectionAllowed(true);
            contractType.setNullSelectionItemId("0");
            contractType.setItemCaption("0", "Show All");
            contractType.setPageLength(7);
            return contractType;
        }


        if ("relationshipLevelName".equals(propertyId) && pvFlag) {

            if ("Product".equals(pvSelectionDTO.getView())) {
                indicator = "P";
                pvSelectionDTO.setLevelName("'Product','Ndc','NDC'");
                levelNo = pvSelectionDTO.getProductLevelNo();
            } else {
                indicator = "C";
                pvSelectionDTO.setLevelName("'" + pvSelectionDTO.getView() + "'");
                 levelNo = pvSelectionDTO.getCustomerLevelNo();
            }
            final ComboBox contractType = new ComboBox();
            contractType.setNullSelectionAllowed(true);
            contractType.setNullSelectionItemId(SELECT_ONE);
            contractType.setImmediate(true);
            contractType.addStyleName("filterCombobox");
            if (detailFlag && !"Custom".equals(pvSelectionDTO.getView())) {
                contractType.addItem(ZERO);
                contractType.setItemCaption(ZERO, SELECT_ONE);
                
                contractType.select(ZERO);
                contractType.addValueChangeListener(new Property.ValueChangeListener() {

                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        // TODO Auto-generated method stub
                        if (event.getProperty().getValue() != null && !"0".equals(event.getProperty().getValue().toString())) {
                            pvSelectionDTO.setIsCustomerDdlb(true);
                            pvSelectionDTO.setHierarchyNo(event.getProperty().getValue().toString());
                            pvSelectionDTO.setIslevelFiler(false);
                        } else {
                            pvSelectionDTO.setIsCustomerDdlb(false);
                            pvSelectionDTO.setIslevelFiler(false);
                        }
                    }
                });
            } else {
               contractType.setEnabled(false);
            }
            return contractType;
        } 
        

        if("relationshipLevelName".equals(propertyId) && prFlag ){



            if ("Product".equals(sprProjectionDTO.getView())) {
                indicator = "P";
                sprProjectionDTO.setLevelName("'Product','Ndc','NDC'");
                levelNo = sprProjectionDTO.getProductLevelNo();
            } else {
                indicator = "C";
                sprProjectionDTO.setLevelName("'" + sprProjectionDTO.getView() + "'");
                levelNo = sprProjectionDTO.getCustomerLevelNo();
            }
            final ComboBox contractType = new ComboBox();
            contractType.setNullSelectionAllowed(true);
            contractType.setNullSelectionItemId(SELECT_ONE);
            contractType.setImmediate(true);
            contractType.addStyleName("filterCombobox");
            contractType.setValue(SELECT_ONE);
            if (!"Custom".equals(sprProjectionDTO.getView())) {
            contractType.addItem(ZERO);
            contractType.setItemCaption(ZERO, SELECT_ONE);
                contractType.select(ZERO);
            } else {
                contractType.setEnabled(false);
            }
            return contractType;
        }
        if ("group".equals(propertyId) ) {
            final ComboBox contractType = new ComboBox();
            try {

                contractType.setNullSelectionAllowed(true);
                contractType.setNullSelectionItemId(SELECT_ONE);
                contractType.setImmediate(true);
                contractType.addStyleName("filterCombobox");
                contractType.setValue(SELECT_ONE);
                mmDprFlag = false;
                return contractType;

            } catch (Exception e) {
               LOGGER.error(e);

            }

        }
        if ("relationshipLevelName".equals(propertyId) && sprFlag) {

             

            pvSelectionDTO = new PVSelectionDTO();
            final ComboBox filterBox = new ComboBox();
            filterBox.setNullSelectionAllowed(true);
            filterBox.setNullSelectionItemId(SELECT_ONE);
            filterBox.setImmediate(true);
            filterBox.addStyleName("filterCombobox");
            if (!"Custom".equals(sprProjectionDTO.getView())) {
                if ("Product".equals(sprProjectionDTO.getView())) {
                    indicator = "P";
                    pvSelectionDTO.setLevelName("'Brand'");
                    sprProjectionDTO.setLevelName("'Brand'");
                    levelNo = sprProjectionDTO.getProductLevelNo();
                } else {
                    indicator = "C";
                    pvSelectionDTO.setLevelName("'" + sprProjectionDTO.getView() + "'");
                    sprProjectionDTO.setLevelName("'" + sprProjectionDTO.getView() + "'");
                    levelNo = sprProjectionDTO.getCustomerLevelNo();
                }

                pvSelectionDTO.setProjectionId(sprProjectionDTO.getProjectionId());
                pvSelectionDTO.setHierarchyIndicator(sprProjectionDTO.getHierarchyIndicator());
                filterBox.addItem(ZERO);
                filterBox.setItemCaption(ZERO, SELECT_ONE);
                filterBox.select(ZERO);
                filterBox.addValueChangeListener(new Property.ValueChangeListener() {

                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        // TODO Auto-generated method stub
                        if (event.getProperty().getValue() != null && !"0".equals(event.getProperty().getValue().toString())) {
                            sprProjectionDTO.setFilterDdlb(true);
                            sprProjectionDTO.setHierarchyNo(event.getProperty().getValue().toString());
                            sprProjectionDTO.setIsFilter(false);
                        } else {
                            sprProjectionDTO.setFilterDdlb(false);
                            sprProjectionDTO.setIsFilter(false);
                        }
                    }
                });
            } else {
                filterBox.setEnabled(false);
            }
            return filterBox;
        }
        
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

    
}
