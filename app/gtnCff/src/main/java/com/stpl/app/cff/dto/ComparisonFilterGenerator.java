/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.util.CommonUtils;
import static com.stpl.app.cff.util.ConstantsUtil.SELECT_ONE;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram
 */
public class ComparisonFilterGenerator implements ExtFilterGenerator {
    private CommonLogic commonLogic = new CommonLogic();
    private CommonUtils utils = new CommonUtils();
    private PVSelectionDTO pvSelectionDTO;
    private ProjectionSelectionDTO sprProjectionDTO;
    private static final String ZERO = "0";
    private ProjectionSelectionDTO psDTO;
    private boolean detailFlag;
    private boolean prFlag = false;
    private boolean sprFlag=false;
    private boolean pvFlag=false;
    private boolean mmDprFlag = false;
    public static final String CUSTOM = "Custom";
    public static final String PRODUCT = "Product";


    private String indicator = StringUtils.EMPTY;
    private int levelNo = 0;
    public static final Logger LOGGER = Logger.getLogger(ComparisonFilterGenerator.class);

    public ComparisonFilterGenerator( PVSelectionDTO projectionVarianceSelectionDTO,boolean detailFlag) {
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
            contractType.setPageLength(NumericConstants.SEVEN);
            return contractType;
        }


        if (StringConstantsUtil.RELATIONSHIP_LEVEL_NAME.equals(propertyId) && pvFlag) {

            if (PRODUCT.equals(pvSelectionDTO.getView())) {
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
            contractType.addStyleName(StringConstantsUtil.FILTER_COMBOBOX);
            if (detailFlag && !CUSTOM.equals(pvSelectionDTO.getView())) {
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
        

        if(StringConstantsUtil.RELATIONSHIP_LEVEL_NAME.equals(propertyId) && prFlag ){



            if (PRODUCT.equals(sprProjectionDTO.getView())) {
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
            contractType.addStyleName(StringConstantsUtil.FILTER_COMBOBOX);
            contractType.setValue(SELECT_ONE);
            if (!CUSTOM.equals(sprProjectionDTO.getView())) {
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
                contractType.addStyleName(StringConstantsUtil.FILTER_COMBOBOX);
                contractType.setValue(SELECT_ONE);
                mmDprFlag = false;
                return contractType;

            } catch (Exception e) {
               LOGGER.error(e);

            }

        }
        if (StringConstantsUtil.RELATIONSHIP_LEVEL_NAME.equals(propertyId) && sprFlag) {

             

            pvSelectionDTO = new PVSelectionDTO();
            final ComboBox filterBox = new ComboBox();
            filterBox.setNullSelectionAllowed(true);
            filterBox.setNullSelectionItemId(SELECT_ONE);
            filterBox.setImmediate(true);
            filterBox.addStyleName(StringConstantsUtil.FILTER_COMBOBOX);
            if (!CUSTOM.equals(sprProjectionDTO.getView())) {
                if (PRODUCT.equals(sprProjectionDTO.getView())) {
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
