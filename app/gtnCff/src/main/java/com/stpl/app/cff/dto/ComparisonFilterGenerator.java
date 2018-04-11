/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import static com.stpl.app.cff.util.ConstantsUtil.SELECT_ONE;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TextField;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram
 */
public class ComparisonFilterGenerator implements ExtFilterGenerator {
    private PVSelectionDTO pvSelectionDTO;
    private ProjectionSelectionDTO sprProjectionDTO;
    private static final String ZERO = "0";
    private final boolean detailFlag;
    private final boolean prFlag = false;
    private final boolean sprFlag=false;
    private boolean pvFlag=false;
    private boolean mmDprFlag = false;
    public static final String CUSTOM = "Custom";
    public static final String PRODUCT = "Product";


    private String indicator = StringUtils.EMPTY;
    private int levelNo = 0;
    public static final Logger LOGGER = LoggerFactory.getLogger(ComparisonFilterGenerator.class);

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


        if (pvFlag && StringConstantsUtil.RELATIONSHIP_LEVEL_NAME.equals(propertyId)) {

            if (PRODUCT.equals(pvSelectionDTO.getView())) {
                setIndicator("P");
                pvSelectionDTO.setLevelName("'Product','Ndc','NDC'");
                setLevelNo(pvSelectionDTO.getProductLevelNo());
            } else {
                setIndicator("C");
                pvSelectionDTO.setLevelName("'" + pvSelectionDTO.getView() + "'");
                 setLevelNo(pvSelectionDTO.getCustomerLevelNo());
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
                setIndicator("P");
                sprProjectionDTO.setLevelName("'Product','Ndc','NDC'");
                setLevelNo(sprProjectionDTO.getProductLevelNo());
            } else {
                setIndicator("C");
                sprProjectionDTO.setLevelName("'" + sprProjectionDTO.getView() + "'");
                setLevelNo(sprProjectionDTO.getCustomerLevelNo());
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
                setMmDprFlag(false);
                return contractType;

            } catch (Property.ReadOnlyException e) {
               LOGGER.error(e.getMessage());

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
                    setIndicator("P");
                    pvSelectionDTO.setLevelName("'Brand'");
                    sprProjectionDTO.setLevelName("'Brand'");
                    setLevelNo(sprProjectionDTO.getProductLevelNo());
                } else {
                    setIndicator("C");
                    pvSelectionDTO.setLevelName("'" + sprProjectionDTO.getView() + "'");
                    sprProjectionDTO.setLevelName("'" + sprProjectionDTO.getView() + "'");
                    setLevelNo(sprProjectionDTO.getCustomerLevelNo());
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


	public boolean isMmDprFlag() {
		return mmDprFlag;
	}


	public void setMmDprFlag(boolean mmDprFlag) {
		this.mmDprFlag = mmDprFlag;
	}


	public String getIndicator() {
		return indicator;
	}


	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}


	public int getLevelNo() {
		return levelNo;
	}


	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

    
}
