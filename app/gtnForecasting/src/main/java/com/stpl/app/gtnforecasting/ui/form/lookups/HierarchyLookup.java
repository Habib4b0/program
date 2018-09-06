/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui.form.lookups;

import com.stpl.app.forecastabstract.lookups.AbstractHierarchyLookup;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.Converters;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_RESET;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_SEARCH;
import static com.stpl.app.utils.Constants.LabelConstants.PRIMARY;
import com.stpl.app.utils.DateToStringConverter;
import com.stpl.app.utils.UiUtils;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.TextFieldConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.TextField;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class HierarchyLookup.
 *
 * @author soundarrajan
 */
public class HierarchyLookup extends AbstractHierarchyLookup {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HierarchyLookup.class);
    /**
     * Indicates whether it is customer lookup or product lookup.
     */
    private String indicator;

    /**
     * The hierarchyName TextField for searching.
     */
    private TextField hierarchyName;

    /**
     * hierarchyLookup TextField.
     */
    private final TextField hrchyLookup;

    /**
     * The search result table.
     */
    private ExtFilterTable results;

    private final Button selectBtn = new Button(Constants.ButtonConstants.BTN_SELECT.getConstant());
    /**
     * Container for results table.
     */
    private OptionGroup hierarchyType;

    private final Button searchBtn = new Button(BTN_SEARCH.getConstant());
    private final Button resetBtn = new Button(BTN_RESET.getConstant());
    /**
     * Container for results table.
     */
    private final BeanItemContainer<HierarchyLookupDTO> resultBean = new BeanItemContainer<>(HierarchyLookupDTO.class);

    /**
     * Hierarchy DTO.
     */
    private HierarchyLookupDTO hierarchyDto;

    private String windowNameSelection;
    /**
     * Constructor for Hierarchy Lookup window.
     *
     * @param indicator Indicates whether it is customer lookup or product
     * @param windowName window name for lookup lookup
     * @param hierarchyLookup the textfield which opens this popup
     * @param hierarchyDto to return the selected hierarchy information
     */
    public HierarchyLookup(final String indicator, final String windowName, final TextField hierarchyLookup, HierarchyLookupDTO hierarchyDto) {
        super(windowName);
        this.windowNameSelection = windowName;
        this.indicator = indicator;
        this.hrchyLookup = hierarchyLookup;
        this.hierarchyDto = hierarchyDto;
        initializeComponents();
        setContent(buildHierarchyLookup(hierarchyName, hierarchyType, results, searchBtn, resetBtn, selectBtn));
        configureFields();
    }

    /**
     * Initializes the components.
     */
    private void initializeComponents() {
        setHierarchyName(UiUtils.addTextField());
        hierarchyName.setImmediate(true);
        hierarchyName.focus();
        hierarchyType = new OptionGroup();

        results = new ExtFilterTable();
        results.setContainerDataSource(resultBean);
        results.setSelectable(true);
        results.setFilterBarVisible(true);
        results.setFilterDecorator(new ExtDemoFilterDecorator());
        results.setStyleName(Constant.FILTER_TABLE);
        results.setPageLength(NumericConstants.TEN);
        selectBtn.setEnabled(false);

        results.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(results.getValue() != null) {
                    selectBtn.setEnabled(true);
                } else {
                    selectBtn.setEnabled(false);
                }
            }
        });
    }
    /**
     * Configures all the components.
     */
    private void configureFields() {
        // Fields to be configured over here
        results.addStyleName("custom-table-header-alignment");
        results.setColumnAlignments(ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT,
                ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT, ExtCustomTable.Align.LEFT);
        hierarchyName.setMaxLength(Constants.LengthConstants.LENGTH_200.getConstant());
        hierarchyName.setImmediate(true);
        hierarchyName.setConverter(new TextFieldConverter());
        results.setConverter("createdDateSearch", new DateToStringConverter());
        results.setConverter("modifiedDateSearch", new DateToStringConverter());
        results.refreshRowCache();
    }
    @Override
    protected void btnSearchLogic() {
        try {
            GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
            GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
            GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
            serviceRequest.setGtnWsSearchRequest(searchRequest);
            serviceRequest.getGtnWsSearchRequest().setCount(true);
            GtnServiceRegistryWsRequest serviceRegistryRequest = new GtnServiceRegistryWsRequest();
            GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
            serviceRegistryBean.setRegisteredWebContext("/GtnHierarchyAndRelaionshipWebService");
            serviceRegistryBean.setUrl("/gtnWsHierarchyRelationshipController/loadHierarchyResults");
            serviceRegistryBean.setModuleName("hierarchyRelationship");
            serviceRegistryRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);
            serviceRequest.setGtnServiceRegistryWsRequest(serviceRegistryRequest);
            List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<>();
            searchCriteriaList.add(generateSearchCriteriaList(String.valueOf(getHierarchyType().getId()),String.valueOf(getHierarchyType().getValue())));
            searchCriteriaList.add(generateSearchCriteriaList(String.valueOf(hierarchyName.getId()),String.valueOf(hierarchyName.getValue())));
            String customerOrProductSelection = getHierarchySelection();
            searchCriteriaList.add(generateSearchCriteriaList(customerOrProductSelection,customerOrProductSelection));
            
            serviceRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(searchCriteriaList);

            GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
					"/gtnServiceRegistry/serviceRegistryUIControllerMappingWs", "serviceRegistry", serviceRequest,
					RelationShipFilterLogic.getGsnWsSecurityToken());
                    List<HierarchyLookupDTO> resultList = new ArrayList<>();

            for (GtnUIFrameworkDataRow recordAddData : response.getGtnSerachResponse().getResultSet().getDataTable()) {
				setHirarchyDTO(recordAddData.getColList().toArray(),resultList);
            }
            
            
            //DataSelectionLogic logic = new DataSelectionLogic();
            //List<HierarchyLookupDTO> list = logic.getHierarchyGroup(String.valueOf(hierarchyName.getValue()), String.valueOf(getHierarchyType().getValue()), indicator, BTN_SEARCH.getConstant());
            if (resultList != null && !resultList.isEmpty()) {
                resultBean.removeAllItems();
                resultBean.addAll(resultList);
            } else {
                AbstractNotificationUtils.getErrorNotification("No Results Found", "There are no Hierarchies that match the search criteria.");
                resultBean.removeAllItems();
            }
        } catch (ParseException ex) {
            LOGGER.error(" Hierarchy search logic= {}",ex);
        }
    }
       private void setHirarchyDTO(Object[] obj, List<HierarchyLookupDTO> resultList) throws ParseException{
        HierarchyLookupDTO hierDto= new HierarchyLookupDTO();
            hierDto.setHierarchyId((int)obj[7]);
            hierDto.setHierarchyName(String.valueOf(obj[0].toString()));
            hierDto.setHighestLevel(String.valueOf(obj[1].toString()));
            hierDto.setLowestLevel(String.valueOf(obj[2].toString()));
            //hierDto.setCreatedDate(String.valueOf(obj[3].toString()));
           // hierDto.setCreatedDateSearch(Converters.parseDate(String.valueOf(obj[3].toString())));
            if (obj[4] != null) {
                hierDto.setModifiedDate(String.valueOf(obj[4].toString()));
                hierDto.setModifiedDateSearch(
                        Converters.parseDate(String.valueOf(obj[4].toString())));
            }
            hierDto.setVersionNo((int)obj[6]);
            hierDto.setHierarchyMap((Map<Integer, String>)obj[10]);
            hierDto.setRelationshipMap((Map<Integer, List<GtnWsRelationshipBuilderBean>>)obj[11]);
            resultList.add(hierDto);
    }
    private GtnWebServiceSearchCriteria generateSearchCriteriaList(String fieldId,String filterValue) {
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId(fieldId);
		searchCriteria.setFilterValue1(filterValue);
		searchCriteria.setFilter(true);
		searchCriteria.setExpression("EQUALS");
		return searchCriteria;

	}
    
    @Override
    protected void btnResetLogic() {
        NotificationUtils notificationUtils = new NotificationUtils() {
            @Override
            public void yesMethod() {
        hierarchyName.setValue(StringUtils.EMPTY);
        hierarchyType.select(PRIMARY.getConstant());
        selectBtn.setEnabled(false);
            }

            @Override
            public void noMethod() {
                LOGGER.debug("Inside overriden method: Do nothing");
            }
        };
        notificationUtils.getConfirmationMessage("Confirm Reset", "Are you sure you want to reset the page to default values?");

    }

    /**
     * Overload and customize select logic.
     */
    @Override
    protected void btnLookupSelectLogic() {
        try {
            if (results.getValue() != null) {
                hierarchyDto = (HierarchyLookupDTO) results.getValue();
                hrchyLookup.setValue(String.valueOf(hierarchyDto.getHierarchyName()).trim());
                close();
            }
        } catch (Exception ex) {
            LoggerFactory.getLogger(HierarchyLookup.class.getName()).error(StringUtils.EMPTY, ex);
        }
    }    
    /**
     * Gets the indicator.
     *
     * @return the indicator
     */
    public String getIndicator() {
        return indicator;
    }

    /**
     * Sets the indicator.
     *
     * @param indicator the new indicator
     */
    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    /**
     * Gets the hierarchy name.
     *
     * @return the hierarchy name
     */
    public TextField getHierarchyName() {
        return hierarchyName;
    }

    /**
     * Sets the hierarchy name.
     *
     * @param hierarchyName the new hierarchy name
     */
    public void setHierarchyName(TextField hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    /**
     * Gets the results.
     *
     * @return the results
     */
    public ExtFilterTable getResults() {
        return results;
    }

    /**
     * Sets the results.
     *
     * @param results the new results
     */
    public void setResults(ExtFilterTable results) {
        this.results = results;
    }

    public HierarchyLookupDTO getHierarchyDto() {
        return hierarchyDto;
    }

    public void setHierarchyDto(HierarchyLookupDTO hierarchyDto) {
        this.hierarchyDto = hierarchyDto;
    }


    @Override
    protected void btnCloseLogic() {
        close();
    }

    @Override
    protected void configureResultTable(ExtPagedTable results, String indicator) {
        LOGGER.debug("Inside Overriden method: do nothing");
    }

    private String getHierarchySelection() {
         if(windowNameSelection.contains("Product")){
            return "Product Hierarchy";
        }
         return "Customer Hierarchy";
    }

}
