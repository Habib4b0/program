package com.stpl.app.galforecasting.nationalassumptions.ui.form;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.stpl.app.galforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.galforecasting.nationalassumptions.dto.NewNdcDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.CommonLogic;
import com.stpl.app.galforecasting.nationalassumptions.logic.NationalAssumptionLogic;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.galforecasting.nationalassumptions.util.DataFormatConverter;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.model.StMedicaidNewNdc;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.StMedicaidNewNdcLocalServiceUtil;
import com.stpl.app.service.impl.HelperTableLocalServiceImpl;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.jboss.logging.Logger;
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
    Button reset;

    /**
     * The ndc.
     */
    @UiField("ndc")
    ComboBox ndc;

    /**
     * The wac.
     */
    @UiField("wac")
    TextField wac;
    @UiField("baseAMP")
    TextField baseAMP;
    @UiField("forecastAMP")
    TextField forecastAMP;
    @UiField("baseYearCPI")
    TextField baseYearCPI;
    @UiField("bestPrice")
    TextField bestPrice;

    /**
     * The ndc table.
     */
    @UiField("ndcTable")
    ExtFilterTable ndcTable;
    @UiField("populate")
    Button populate;
    List<String> listNDC;
    Map<String, String> wACList;
    Map<String, String> cPIList;
    DecimalFormat dollar4decimal = new DecimalFormat("$#,##0.0000");
    /**
     * The ndc results bean.
     */
    BeanItemContainer<NewNdcDTO> nDCLineExtensionBean = new BeanItemContainer<NewNdcDTO>(NewNdcDTO.class);
    int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID);
    BeanItemContainer<String> availableNDCBean = new BeanItemContainer<String>(
            String.class);
    Map<Integer, String> ndc9Map = new HashMap<Integer, String>();
    public String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    CommonLogic commonLogic = new CommonLogic();
    DataFormatConverter dollarFormat = new DataFormatConverter("#,##0.0000", DataFormatConverter.INDICATOR_DOLLAR);
    NdcPopupForm ndcPopupForm = new NdcPopupForm();
    public boolean flag;
    public String SelectedNDC = StringUtils.EMPTY;
    public String ItemName = StringUtils.EMPTY;
    NewNdcDTO deletedItem = new NewNdcDTO();
    public NationalAssumptionLogic nationalAssumptionLogic = new NationalAssumptionLogic();
    public List<NewNdcDTO> removedMedicaidNdc = new ArrayList<NewNdcDTO>();
    Map<Integer, Object> medicaidMap;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MedicaidNdcPopUp.class);

    /**
     * Instantiates a new new ndc pop up.
     */
    public MedicaidNdcPopUp(NewNdcDTO newNdcDto, Map<Integer, Object> medicaidMap) throws Exception {
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
    private void init() throws SystemException {

        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/MedicaidNdcPopup.xml"), this));

        addStyleName(Constant.BOOTSTRAP_UI);
        addStyleName(Constant.BOOTSTRAP);
        addStyleName(Constant.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        configurefields();
        loadTable();

    }

    /**
     * Configurefields.
     */
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
        ndcTable.setVisibleColumns(CommonUiUtils.NEW_NDC_COLUMNS);
        ndcTable.setColumnHeaders(CommonUiUtils.NEW_NDC_HEADER);
        ndcTable.setFilterBarVisible(true);
        ndcTable.setFilterDecorator(new ExtDemoFilterDecorator());
        ndcTable.addStyleName(Constant.FILTER_TABLE);
        ndcTable.addStyleName("table-header-center");
        ndcTable.setColumnAlignment(CommonUiUtils.NEW_NDC_COLUMNS[0], ExtFilterTable.Align.LEFT);
        int length = CommonUiUtils.NEW_NDC_COLUMNS.length;
        for (int i = 1; i < length; i++) {
            ndcTable.setColumnAlignment(CommonUiUtils.NEW_NDC_COLUMNS[i], ExtFilterTable.Align.RIGHT);
        }
        loadNdcList();

        populate.setImmediate(true);
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
                            double convertedWAC = Double.valueOf(wacValue);
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
                            double convertedCPI = Double.valueOf(cpiValue);
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
                        wac.setReadOnly(false);
                        ndc.setValue(StringUtils.EMPTY);
                        wac.setValue(StringUtils.EMPTY);
                        forecastAMP.setValue(StringUtils.EMPTY);
                        baseAMP.setValue(StringUtils.EMPTY);
                        baseYearCPI.setReadOnly(false);
                        baseYearCPI.setValue(StringUtils.EMPTY);
                        baseYearCPI.setReadOnly(true);
                        bestPrice.setValue(StringUtils.EMPTY);
                    }
                    wac.setReadOnly(true);
                } catch (Exception ex) {
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
                    double ampValue = Double.valueOf(wac.getValue().replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)) * 0.98;
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
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
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
        } catch (PortalException portal) {
            LOGGER.error(portal.getMessage());
        } catch (SystemException system) {
            LOGGER.error(system.getMessage());
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
            ItemName = ndcNo1[0].trim();
        } else {
            newNDC.setNdc9(String.valueOf(ndc.getValue()));
        }
        SelectedNDC = newNDC.getNdc9();

        newNDC.setItemMasterSid(Integer.valueOf(String.valueOf(ndc.getValue()).trim()));
        newNDC.setForecastAMP(forecastAMP.getValue());
        newNDC.setWac(wac.getValue());
        newNDC.setForecastBestPrice(bestPrice.getValue());

        nDCLineExtensionBean.addItem(newNDC);
        medicaidMap.put(newNDC.getItemMasterSid(), newNDC);
        List itemToBeRemoved = getItemSameNDC();
        deletedItem.setDeletedItems(itemToBeRemoved);
        if (itemToBeRemoved.size() > 0) {
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

    public int addLogic() throws Exception {
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
            Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
            Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                    .getAttribute(Constant.USER_ID));

            for (NewNdcDTO newNdcDTO : newNDCList) {
                DynamicQuery naDynamicQuery = DynamicQueryFactoryUtil
                        .forClass(StMedicaidNewNdc.class);

                List<StMedicaidNewNdc> list = new ArrayList<StMedicaidNewNdc>();

                naDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.ndc9", newNdcDTO.getNdc9()));
                naDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.sessionId", sessionId));

                list = StMedicaidNewNdcLocalServiceUtil.dynamicQuery(naDynamicQuery);
                nationalAssumptionLogic.saveMedicaidNdcPopUp(userId, sessionId, list, newNdcDTO);

            }
        } catch (Exception e) {
            LOGGER.error(e);
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
            List<NewNdcDTO> list = logic.getNdcTable();
            nDCLineExtensionBean.addAll(list);

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public boolean closeFlag() {
        return flag;
    }

    public void resetTableLogic() throws SystemException, Exception {
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        NewNdcDTO projection = (NewNdcDTO) ndcTable.getValue();
        String value = StringUtils.EMPTY;
        List<StMedicaidNewNdc> listCount = nationalAssumptionLogic.listCount(projection.getNdc9(), sessionId);
        if (listCount.isEmpty()) {
            nDCLineExtensionBean.removeItem(projection);
            if (deletedItem.getDeletedItems().size() > 0) {
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
            nationalAssumptionLogic.medicaidDeleteLogic(projection.getNdc9());
            removedMedicaidNdc.add(projection);
            if (deletedItem.getDeletedItems().size() > 0) {
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
                    itemSid.append("'" + value[0].trim() + "'");
                } else {
                    itemSid.append(", '" + value[0].trim() + " '");
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
