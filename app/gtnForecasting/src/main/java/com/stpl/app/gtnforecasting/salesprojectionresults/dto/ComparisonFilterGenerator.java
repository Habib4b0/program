/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.dto;

import com.stpl.app.gtnforecasting.discountprojectionresults.logic.MMDPRLogic;
import com.stpl.app.gtnforecasting.discountprojectionresults.logic.tablelogic.DPRTableLogic;
import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.tablelogic.ProjectionResultsTableLogic;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.tablelogic.MSalesProjectionResultsTableLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TextField;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class ComparisonFilterGenerator implements ExtFilterGenerator {

    private SPRCommonLogic commonLogic = new SPRCommonLogic();
    private CommonUtils utils = new CommonUtils();
    private PVSelectionDTO pvSelectionDTO;
    private ProjectionSelectionDTO sprProjectionDTO;
   
    private DataSelectionLogic logic = new DataSelectionLogic();
    private static final String ZERO = "0";
    private MSalesProjectionResultsTableLogic sprTableLogic;
    private ComboBox filterBox;
    private ProjectionSelectionDTO psDTO;
    private boolean detailFlag;
    private boolean prFlag = false;
    private boolean sprFlag = false;
    private boolean pvFlag = false;
   
    private String indicator = StringUtils.EMPTY;
    private int levelNo = 0;
    public static final Logger LOGGER = Logger.getLogger(ComparisonFilterGenerator.class);

    public ComparisonFilterGenerator(ProjectionSelectionDTO projectionDTO, MSalesProjectionResultsTableLogic sprTableLogic) {
        this.sprProjectionDTO = projectionDTO;
        this.sprTableLogic = sprTableLogic;
        sprFlag = true;
    }

    public ComparisonFilterGenerator(ProjectionSelectionDTO projectionDTO, ProjectionResultsTableLogic prTableLogic) {
        this.sprProjectionDTO = projectionDTO;
        prFlag = true;
    }

    public ComparisonFilterGenerator(ProjectionSelectionDTO projectionDTO, DPRTableLogic mmDprLogic) {
        this.psDTO = projectionDTO;
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
        if (Constant.MARKET_TYPE.equals(propertyId)) {
            //PV Comparision
            ComboBox contractType = new ComboBox();
            utils.getNativeSelect(contractType, commonLogic.getDropDownList("CONTRACT_TYPE"));
            contractType.setImmediate(true);
            contractType.setNullSelectionAllowed(true);
            contractType.setNullSelectionItemId(DASH);
            contractType.setPageLength(NumericConstants.SEVEN);
            return contractType;
        }

        if (Constant.RELATIONSHIP_LEVEL_NAME1.equals(propertyId) && pvFlag) {
            //This block is for PROJECTION Variance

            if (Constant.PRODUCT_LABEL.equals(pvSelectionDTO.getView())) {
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
            contractType.addStyleName(Constant.FILTER_COMBOBOX);
            if (detailFlag && !Constant.CUSTOM_LABEL.equals(pvSelectionDTO.getView())) {

                contractType.addItem(ZERO);
                contractType.setItemCaption(ZERO, SELECT_ONE);
                List<Leveldto> list = SPRCommonLogic.getAllHierarchyLevels(levelNo, pvSelectionDTO.getProjectionId(), indicator);
                if (list != null && !list.isEmpty()) {
                    for (Leveldto dto : list) {
                        if ((pvSelectionDTO.getLevelName().toString()).contains(dto.getLevel().replaceAll("'", StringUtils.EMPTY))) {
                            contractType.addItem(dto.getHierarchyNo());
                            contractType.setItemCaption(dto.getHierarchyNo(), dto.getRelationshipLevelName());
                        }
                    }
                }

                contractType.select(ZERO);
                contractType.addValueChangeListener(new Property.ValueChangeListener() {

                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        // TODO Auto-generated method stub
                        if (event.getProperty().getValue() != null && !DASH.equals(event.getProperty().getValue().toString())) {
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

        if (Constant.RELATIONSHIP_LEVEL_NAME1.equals(propertyId) && prFlag) {

            if (Constant.PRODUCT_LABEL.equals(sprProjectionDTO.getView())) {
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
            contractType.addStyleName(Constant.FILTER_COMBOBOX);
            contractType.setValue(SELECT_ONE);
            if (!Constant.CUSTOM_LABEL.equals(sprProjectionDTO.getView())) {
                contractType.addItem(ZERO);
                contractType.setItemCaption(ZERO, SELECT_ONE);
                List<Leveldto> list = SPRCommonLogic.getAllHierarchyLevels(levelNo, sprProjectionDTO.getProjectionId(), indicator);

                if (list != null && !list.isEmpty()) {
                    for (Leveldto dto : list) {
                        if (sprProjectionDTO.getLevelName().contains(dto.getLevel())) {
                            contractType.addItem(dto.getHierarchyNo());
                            contractType.setItemCaption(dto.getHierarchyNo(), dto.getRelationshipLevelName());
                        }
                    }
                }
                contractType.select(ZERO);
            } else {
                contractType.setEnabled(false);
            }

            return contractType;
        }
        if (Constant.GROUP.equals(propertyId)) {
            final ComboBox contractType = new ComboBox();
            try {

                contractType.setNullSelectionAllowed(true);
                contractType.setNullSelectionItemId(SELECT_ONE);
                contractType.setImmediate(true);
                contractType.addStyleName(Constant.FILTER_COMBOBOX);
                contractType.setValue(SELECT_ONE);

                String str = logic.getCheckValue(String.valueOf(psDTO.getProjectionId()));
                if (str.length() > 0) {
                    MMDPRLogic mmLogic = new MMDPRLogic();
                    List list = mmLogic.loadCustomerDdlb(psDTO, str);
                    contractType.addItem(0);
                    contractType.setItemCaption(0, SELECT_ONE);
                    if (list != null && !list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            Object[] obj = (Object[]) list.get(i);

                            contractType.addItem(obj[0] == null ? StringUtils.EMPTY : obj[0].toString() + "~" + Constant.MANDATED_DISCOUNT);
                            contractType.setItemCaption(obj[0].toString() + "~" + Constant.MANDATED_DISCOUNT == null ? StringUtils.EMPTY : obj[0].toString() + "~" + Constant.MANDATED_DISCOUNT, obj[0] == null ? StringUtils.EMPTY
                                    : "Mandated Discount " + "-" + " " + obj[0].toString());
                            contractType.addItem(obj[0] == null ? StringUtils.EMPTY : obj[0].toString() + "~" + Constant.MANDATED_SUPPLEMENTAL);
                            contractType.setItemCaption(obj[0].toString() + "~" + Constant.MANDATED_SUPPLEMENTAL == null ? StringUtils.EMPTY : obj[0].toString() + "~" + Constant.MANDATED_SUPPLEMENTAL, obj[0] == null ? StringUtils.EMPTY
                                    : "Mandated Supplemental " + "-" + " " + obj[0].toString());
                        }
                    }
                }
                return contractType;

            } catch (Property.ReadOnlyException | UnsupportedOperationException e) {
                LOGGER.error(e);

            }

        }
        if (Constant.RELATIONSHIP_LEVEL_NAME1.equals(propertyId) && sprFlag) {

            //This block is for SALES PROJECTION RESULTS
            return getfilterBox();
        }

        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        return;
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

    private ComboBox getfilterBox() {
        if (filterBox == null) {
            pvSelectionDTO = new PVSelectionDTO();
            filterBox = new ComboBox();
            filterBox.setNullSelectionAllowed(true);
            filterBox.setNullSelectionItemId(SELECT_ONE);
            filterBox.setImmediate(true);
            filterBox.addStyleName(Constant.FILTER_COMBOBOX);
            if (!Constant.CUSTOM_LABEL.equals(sprProjectionDTO.getView())) {
                if (Constant.PRODUCT_LABEL.equals(sprProjectionDTO.getView())) {
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
                List<Leveldto> list = SPRCommonLogic.getAllHierarchyLevels(levelNo, sprProjectionDTO.getProjectionId(), indicator);
                if (list != null && !list.isEmpty()) {
                    for (Leveldto dto : list) {
                        if ((pvSelectionDTO.getLevelName().replaceAll("'", StringUtils.EMPTY)).equalsIgnoreCase(dto.getLevel())) {
                            filterBox.addItem(dto.getHierarchyNo());
                            filterBox.setItemCaption(dto.getHierarchyNo(), dto.getRelationshipLevelName());
                        }
                    }
                }
                filterBox.select(ZERO);
                filterBox.addValueChangeListener(new Property.ValueChangeListener() {

                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        // TODO Auto-generated method stub
                        if (event.getProperty().getValue() != null && !DASH.equals(event.getProperty().getValue().toString())) {
                            sprProjectionDTO.setFilterDdlb(true);
                            sprProjectionDTO.setHierarchyNo(event.getProperty().getValue().toString());
                            sprProjectionDTO.setIsFilter(false);
                            sprTableLogic.clearAll();
                            sprTableLogic.setProjectionResultsData(sprProjectionDTO, false, "SPR");
                        } else {
                            sprProjectionDTO.setFilterDdlb(false);
                            sprProjectionDTO.setIsFilter(false);
                            sprTableLogic.clearAll();
                            sprTableLogic.setProjectionResultsData(sprProjectionDTO, false, "SPR");
                        }
                    }
                });
            } else {
                filterBox.setEnabled(false);
            }
        }
        return filterBox;
    }

}
