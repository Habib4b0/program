package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.NewNdcDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.NationalAssumptionLogic;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.gtnforecasting.nationalassumptions.util.DataFormatConverter;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextField;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * The Class NewNdcPopUp.
 *
 * @author Nadhiya
 */
public class MedicaidNdcPopUp extends CustomComponent {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -2231099764455133169L;

    /**
     * The reset.
     */
    @UiField("reset")
    private Button reset;

    /**
     * The ndc.
     */
    @UiField("ndc")
    private ComboBox ndc;

    /**
     * The wac.
     */
    @UiField("wac")
    private TextField wac;
    
    @UiField("baseAMP")
    private TextField baseAMP;
    
    @UiField("forecastAMP")
    private TextField forecastAMP;
    
    @UiField("baseYearCPI")
    private TextField baseYearCPI;
    
    @UiField("bestPrice")
    private TextField bestPrice;

    /**
     * The ndc table.
     */
    @UiField("ndcTable")
    private ExtFilterTable ndcTable;
    
    @UiField("populate")
    private Button populate;
    private final List<String> listNDC;
    private final Map<String, String> wACList;
    private final Map<String, String> cPIList;
    private final DecimalFormat dollar4decimal = new DecimalFormat("$#,##0.0000");
    /**
     * The ndc results bean.
     */
    private final BeanItemContainer<NewNdcDTO> nDCLineExtensionBean = new BeanItemContainer<>(NewNdcDTO.class);
    private Map<Integer, String> ndc9Map = new HashMap<>();
    private final DataFormatConverter dollarFormat = new DataFormatConverter("#,##0.0000", DataFormatConverter.INDICATOR_DOLLAR);
    private boolean flag;
    private String SelectedNDC = StringUtils.EMPTY;
    private final NewNdcDTO deletedItem = new NewNdcDTO();
    private final NationalAssumptionLogic nationalAssumptionLogic = new NationalAssumptionLogic();
    private final List<NewNdcDTO> removedMedicaidNdc = new ArrayList<>();
    private final Map<Integer, Object> medicaidMap;
    
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicaidNdcPopUp.class);
    private final SessionDTO sessionDTO;


    public MedicaidNdcPopUp(NewNdcDTO newNdcDto, Map<Integer, Object> medicaidMap,SessionDTO sessionDTO)  {
        this.sessionDTO=sessionDTO;
        this.listNDC = newNdcDto.getListNDC9();
        this.wACList = newNdcDto.getWacHashMap();
        this.cPIList = newNdcDto.getCpiHashMap();
        this.ndc9Map = newNdcDto.getNdc9Map();
        this.medicaidMap = medicaidMap;
        init();

    }

    /**
     * Inits the.
     */
    private void init()  {

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/MedicaidNdcPopup.xml"), this));

        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        configurefields();
        loadTable();

    }

   private void configurefields() {
        ndc.focus();
        ndc.setNullSelectionItemId(SELECT_ONE.getConstant());
        ndc.addItem(Constant.SELECT_ONE);

        wac.addStyleName(Constant.TXT_RIGHT_ALIGN);
        bestPrice.addStyleName(Constant.TXT_RIGHT_ALIGN);
        forecastAMP.addStyleName(Constant.TXT_RIGHT_ALIGN);
        baseAMP.addStyleName(Constant.TXT_RIGHT_ALIGN);
        baseYearCPI.addStyleName(Constant.TXT_RIGHT_ALIGN);

        ndcTable.markAsDirty();
        ndcTable.setContainerDataSource(nDCLineExtensionBean);
        ndcTable.setVisibleColumns(CommonUiUtils.getNewNdcColumns());
        ndcTable.setColumnHeaders(CommonUiUtils.getNewNdcHeader());
        ndcTable.setFilterBarVisible(true);
        ndcTable.setFilterDecorator(new ExtDemoFilterDecorator());
        ndcTable.addStyleName(Constant.FILTER_TABLE);
        ndcTable.addStyleName("table-header-center");
        ndcTable.setColumnAlignment(CommonUiUtils.getNewNdcColumns()[0], ExtFilterTable.Align.LEFT);
        int length = CommonUiUtils.getNewNdcColumns().length;
        for (int i = 1; i < length; i++) {
            ndcTable.setColumnAlignment(CommonUiUtils.getNewNdcColumns()[i], ExtFilterTable.Align.RIGHT);
        }
        loadNdcList();
        ndc.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    if (event.getProperty().getValue() != null) {
                        wac.setReadOnly(false);
                        bestPrice.setValue(StringUtils.EMPTY);
                        String wacValue = wACList.get(ndc.getValue().toString());
                        if (wacValue != null && !wacValue.equals(Constant.NULL)) {
                            double convertedWAC = Double.parseDouble(wacValue);
                            wac.setValue(dollar4decimal.format(convertedWAC));
                        } else {
                            NewNdcDTO dto = (NewNdcDTO) medicaidMap.get(ndc.getValue().toString());
                            if (dto != null && !dto.getWac().equalsIgnoreCase(Constant.NULL)) {
                                wac.setValue(dto.getWac());
                            } else {
                                wac.setValue(StringUtils.EMPTY);
                                bestPrice.setValue(StringUtils.EMPTY);
                                forecastAMP.setValue(StringUtils.EMPTY);
                                baseAMP.setValue(StringUtils.EMPTY);
                                baseYearCPI.setReadOnly(false);
                                baseYearCPI.setValue(StringUtils.EMPTY);
                                baseYearCPI.setReadOnly(true);
                            }
                        }
                        String cpiValue = cPIList.get(ndc.getValue().toString());
                        NewNdcDTO dto = (NewNdcDTO) medicaidMap.get(ndc.getValue().toString());
                        if (cpiValue != null && !cpiValue.equalsIgnoreCase(Constant.NULL)) {
                            baseYearCPI.setReadOnly(false);
                            double convertedCPI = Double.parseDouble(cpiValue);
                            baseYearCPI.setValue(dollar4decimal.format(convertedCPI));
                            baseYearCPI.setReadOnly(true);
                        } else if (dto != null && !dto.getBaseYearCPI().equalsIgnoreCase(Constant.NULL)) {
                            baseYearCPI.setReadOnly(false);
                            baseYearCPI.setValue(dto.getBaseYearCPI());
                            baseYearCPI.setReadOnly(true);
                        } else {
                            baseYearCPI.setReadOnly(false);
                            baseYearCPI.setValue(StringUtils.EMPTY);
                            baseYearCPI.setReadOnly(true);
                        }

                    } else {
                        
                        ndc.setValue(StringUtils.EMPTY);
                        wac.setValue(StringUtils.EMPTY);
                        wac.setReadOnly(true);
                        forecastAMP.setValue(StringUtils.EMPTY);
                        baseAMP.setValue(StringUtils.EMPTY);
                        baseYearCPI.setReadOnly(false);
                        baseYearCPI.setValue(StringUtils.EMPTY);
                        baseYearCPI.setReadOnly(true);
                        bestPrice.setValue(StringUtils.EMPTY);
                    }
                } catch (Property.ReadOnlyException | NumberFormatException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        wac.setImmediate(true);
        wac.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if (event.getProperty().getValue() != null && !StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    double ampValue = Double.parseDouble(wac.getValue().replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)) * NumericConstants.DOUBLE_NINTY_EIGHT;
                    String strValue = String.valueOf(ampValue);
                    baseAMP.setValue(strValue);
                    forecastAMP.setValue(strValue);
                }

            }
        });

        baseAMP.setReadOnly(false);
        baseAMP.setImmediate(true);
        baseAMP.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if (event.getProperty().getValue() != null && !StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    forecastAMP.setReadOnly(false);
                    forecastAMP.setValue(baseAMP.getValue());
                }
            }
        });
        forecastAMP.setImmediate(true);
        forecastAMP.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                if (event.getProperty().getValue() != null && !StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    baseAMP.setValue(forecastAMP.getValue());
                }

            }
        });

        baseYearCPI.setReadOnly(true);
        baseYearCPI.setImmediate(true);

        bestPrice.setImmediate(true);
        bestPrice.setConverter(dollarFormat);
        forecastAMP.setConverter(dollarFormat);
        baseAMP.setConverter(dollarFormat);
        wac.setConverter(dollarFormat);

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + "Medicaid FFS New NDC PopUp");
            if (tabItemHM.get("populate") != null && tabItemHM.get("populate").isFunctionFlag()) {
                populate.setVisible(true);
            } else {
                populate.setVisible(false);
            }
            if (tabItemHM.get("reset") != null && tabItemHM.get("reset").isFunctionFlag()) {
                reset.setVisible(true);
            } else {
                reset.setVisible(false);
            }
        } catch (PortalException | SystemException portal) {
            LOGGER.error(StringUtils.EMPTY,portal);
        }
    }

    /**
     * Reset.
     *
     * @param event the event
     */
    @UiHandler("reset")
    public void reset(Button.ClickEvent event) {
        resetLogic();

    }

    @UiHandler("populate")
    public void populate(Button.ClickEvent event) {
        if (baseAMP.getValue() != StringUtils.EMPTY && baseYearCPI.getValue() != StringUtils.EMPTY && ndc.getValue() != null && forecastAMP.getValue() != StringUtils.EMPTY && (wac.getValue() != StringUtils.EMPTY && bestPrice.getValue() != StringUtils.EMPTY)) {
            populateLogic();
        } else {
            AbstractNotificationUtils.getErrorNotification("Populate Error ", "All fields above must be complete before you can click the POPULATE command button.");
        }
    }

    private void populateLogic() {
        NewNdcDTO newNDC = new NewNdcDTO();
        newNDC.setBaseYearAMP(baseAMP.getValue());
        newNDC.setBaseYearCPI(baseYearCPI.getValue());

        String ndcNo = ndc.getItemCaption(Integer.valueOf(String.valueOf(ndc.getValue())));
        if (ndcNo.contains(",")) {
            String[] ndcNo1 = ndcNo.split(",");
            newNDC.setNdc9(ndcNo1[1].trim());
        } else {
            newNDC.setNdc9(String.valueOf(ndc.getValue()));
        }
        SelectedNDC = newNDC.getNdc9();

        newNDC.setItemMasterSid(Integer.parseInt(String.valueOf(ndc.getValue()).trim()));
        newNDC.setForecastAMP(forecastAMP.getValue());
        newNDC.setWac(wac.getValue());
        newNDC.setForecastBestPrice(bestPrice.getValue());

        nDCLineExtensionBean.addItem(newNDC);
        medicaidMap.put(newNDC.getItemMasterSid(), newNDC);
        List itemToBeRemoved = getItemSameNDC();
        deletedItem.setDeletedItems(itemToBeRemoved);
        if (!itemToBeRemoved.isEmpty()) {
            for (int i = 0; i < itemToBeRemoved.size(); i++) {
                ndc.removeItem(itemToBeRemoved.get(i));
            }
        } else {
            ndc.removeItem(newNDC.getItemMasterSid());
        }
    }

    private void resetLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {

                ndc.setValue(null);
                wac.setValue(StringUtils.EMPTY);
                forecastAMP.setValue(StringUtils.EMPTY);
                baseAMP.setValue(StringUtils.EMPTY);
                baseYearCPI.setReadOnly(false);
                baseYearCPI.setValue(StringUtils.EMPTY);
                baseYearCPI.setReadOnly(true);
                bestPrice.setValue(StringUtils.EMPTY);

            }

            @Override
            public void noMethod() {
                // TODO Auto-generated method stub
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(),
                "Are you sure you want to reset the page to default/previous values?");
    }

    public int addLogic() throws SystemException{
        if (!listNDC.isEmpty()) {
            int ndcItems = ndc.getItemIds().size();
            if (ndcItems == 1) {
                saveAll();
                flag = true;
                return 0;
            }

            if (ndcItems > 1) {
                return 1;
            }
        }
        return 0;
    }

    void saveAll() throws SystemException {
        try {
            final List<NewNdcDTO> newNDCList = nDCLineExtensionBean.getItemIds();

            for (NewNdcDTO newNdcDTO : newNDCList) {
            String customSQL="SELECT * from ST_MEDICAID_NEW_NDC where NDC9="+ newNdcDTO.getNdc9();
            List list = HelperTableLocalServiceUtil.executeSelectQuery( QueryUtil.replaceTableNames(customSQL, sessionDTO.getCurrentTableNames()));
            nationalAssumptionLogic.saveMedicaidNdcPopUp(sessionDTO, list, newNdcDTO);

            }
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void loadNdcList() {
        for (int key : ndc9Map.keySet()) {
            ndc.addItem(key);
            ndc.setItemCaption(key, ndc9Map.get(key));
        }
    }

    public void loadTable() {
        try {
            nDCLineExtensionBean.removeAllItems();
            NationalAssumptionLogic logic = new NationalAssumptionLogic();
            List<NewNdcDTO> list = logic.getNdcTable(sessionDTO);
            nDCLineExtensionBean.addAll(list);

        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public boolean closeFlag() {
        return flag;
    }

    public void resetTableLogic()  {
        NewNdcDTO projection = (NewNdcDTO) ndcTable.getValue();
        String value;
        List listCount = nationalAssumptionLogic.listCount(projection.getNdc9(), sessionDTO);
        if (listCount.isEmpty()) {
            nDCLineExtensionBean.removeItem(projection);
            if (!deletedItem.getDeletedItems().isEmpty()) {
                for (int i = 0; i < deletedItem.getDeletedItems().size(); i++) {
                    ndc.addItem(deletedItem.getDeletedItems().get(i));
                    value = ndc9Map.get(deletedItem.getDeletedItems().get(i));
                    ndc.setItemCaption(deletedItem.getDeletedItems().get(i), value);
                    projection.setNdcDescription(value);
                }
            } else {
                ndc.addItem(projection.getItemMasterSid());
                value = ndc9Map.get(projection.getItemMasterSid());
                ndc.setItemCaption(projection.getItemMasterSid(), value);
                projection.setNdcDescription(value);
            }
        } else {
            nDCLineExtensionBean.removeItem(projection);
            nationalAssumptionLogic.medicaidDeleteLogic(projection.getNdc9(),sessionDTO);
            removedMedicaidNdc.add(projection);
            if (!deletedItem.getDeletedItems().isEmpty()) {
                for (int i = 0; i < deletedItem.getDeletedItems().size(); i++) {
                    ndc.addItem(deletedItem.getDeletedItems().get(i));
                    value = ndc9Map.get(deletedItem.getDeletedItems().get(i));
                    ndc.setItemCaption(deletedItem.getDeletedItems().get(i), value);
                    projection.setNdcDescription(value);
                }
            } else {
                ndc.addItem(projection.getItemMasterSid());
                value = ndc9Map.get(projection.getItemMasterSid());
                ndc.setItemCaption(projection.getItemMasterSid(), value);
                projection.setNdcDescription(value);
            }
        }

    }

    public void deleteMedicaidNdc() {
        for (NewNdcDTO removed : removedMedicaidNdc) {
            nationalAssumptionLogic.medicaidMainDelete(removed.getNdc9());
        }
        removedMedicaidNdc.clear();
    }

    private List getItemSameNDC() {
        List itemSameNDC = new ArrayList();

        StringBuilder itemSid = new StringBuilder();
        for (int key : ndc9Map.keySet()) {
            String des = ndc9Map.get(key);
            String[] value = des.split(",");
            String NDC9 = value[1].trim();
            if (SelectedNDC.equals(NDC9)) {
                if (itemSid.length() == 0) {
                    itemSid.append("'" ).append( value[0].trim() ).append( '\'');
                } else {
                    itemSid.append(", '" ).append( value[0].trim() ).append( " '");
                }
            }
        }
        if (itemSid.length() > 0) {
            String query = "select ITEM_MASTER_SID from ITEM_MASTER where ITEM_NAME In (" + itemSid + ")";
            itemSameNDC = HelperTableLocalServiceUtil.executeSelectQuery(query);
        }
        return itemSameNDC;
    }
}
