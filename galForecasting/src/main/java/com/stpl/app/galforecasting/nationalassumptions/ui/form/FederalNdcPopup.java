package com.stpl.app.galforecasting.nationalassumptions.ui.form;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.stpl.app.galforecasting.nationalassumptions.dto.NewNdcDTO;
import com.stpl.app.galforecasting.nationalassumptions.logic.NationalAssumptionLogic;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUiUtils;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.WindowMessagesName.RESET_CONFIRMATION;
import com.stpl.app.galforecasting.nationalassumptions.util.DataFormatConverter;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.model.StFederalNewNdc;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.StFederalNewNdcLocalServiceUtil;
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
 *
 * @author Nadhiya
 */
public class FederalNdcPopup extends CustomComponent {

    /**
     * The federal table.
     */
    @UiField("federalTable")
    ExtFilterTable federalTable;
    /**
     * The federal Results bean.
     */

    BeanItemContainer<NewNdcDTO> federalBean = new BeanItemContainer<NewNdcDTO>(NewNdcDTO.class);
    NewNdcDTO newNdcDTO;
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

    @UiField("nonFamp")
    TextField nonFamp;

    @UiField("populate")
    Button populate;

    @UiField("fss")
    TextField fss;

    /**
     * The reset.
     */
    @UiField("reset")
    Button reset;

    Map<String, String> wACList;
    Map<String, String> nonFampList;
    Map<String, String> fssList;
    Map<Integer, String> itemIdMap = new HashMap<Integer, String>();
    List<String> listNDC;
    DecimalFormat decimalFormat = new DecimalFormat("#0.0000");
    NdcPopupForm ndcPopupForm = new NdcPopupForm();
    int projectionId = (Integer) VaadinSession.getCurrent().getAttribute(Constant.PROJECTION_ID);
    public boolean flag;
    public String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
    public NationalAssumptionLogic nationalAssumptionLogic = new NationalAssumptionLogic();
    public List<NewNdcDTO> removedFederalNdc = new ArrayList<NewNdcDTO>();
    Map<Integer, Object> federalMap;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(FederalNdcPopup.class);
    DataFormatConverter dollarFormat = new DataFormatConverter("#,##0.0000", DataFormatConverter.INDICATOR_DOLLAR);

    public FederalNdcPopup(NewNdcDTO newNdcDTO, Map<Integer, Object> federalMap) {
        this.newNdcDTO = newNdcDTO;
        this.listNDC = newNdcDTO.getListItemNo();
        this.wACList = newNdcDTO.getFederalWacMap();
        this.itemIdMap = newNdcDTO.getItemMasterSidMap();
        this.nonFampList = newNdcDTO.getNonFampMap();
        this.fssList = newNdcDTO.getFssMap();
        this.federalMap = federalMap;
        init();
    }

    /**
     * Inits the.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/nationalassumption/FederalNdcPopup.xml"), this));
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
        loadNdcList();

        federalTable.markAsDirty();
        federalTable.setContainerDataSource(federalBean);
        federalTable.setVisibleColumns(CommonUiUtils.FEDERAL_NDC_COLUMNS);
        federalTable.setColumnHeaders(CommonUiUtils.FEDERAL_NDC_HEADER);
        federalTable.setFilterBarVisible(true);
        federalTable.setFilterDecorator(new ExtDemoFilterDecorator());
        federalTable.addStyleName(Constant.FILTER_TABLE);
        federalTable.addStyleName("table-header-center");
        federalTable.setColumnAlignment(CommonUiUtils.FEDERAL_NDC_COLUMNS[0], ExtFilterTable.Align.LEFT);
        int length = CommonUiUtils.FEDERAL_NDC_COLUMNS.length;
        for (int i = 1; i < length; i++) {
            federalTable.setColumnAlignment(CommonUiUtils.FEDERAL_NDC_COLUMNS[i], ExtFilterTable.Align.RIGHT);
        }

        ndc.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    if (event.getProperty().getValue() != null) {
                        String wacValue = wACList.get(ndc.getValue().toString());
                        if (wacValue != null && !wacValue.equals(Constant.NULL)) {
                            double convertedWAC = Double.valueOf(wacValue);
                            wac.setReadOnly(false);
                            wac.setValue(CommonUtils.DOLLAR + decimalFormat.format(convertedWAC));
                            wac.setReadOnly(true);
                        } else {
                            NewNdcDTO dto = (NewNdcDTO) federalMap.get(Integer.parseInt(String.valueOf(ndc.getValue())));
                            if (dto != null && !dto.equals(Constant.NULL)) {
                                wac.setReadOnly(false);
                                wac.setValue(dto.getWac());
                                wac.setReadOnly(true);
                            } else {
                                wac.setReadOnly(false);

                                wac.setValue(StringUtils.EMPTY);
                                nonFamp.setValue(StringUtils.EMPTY);
                                fss.setValue(StringUtils.EMPTY);
                                wac.setReadOnly(true);

                            }
                        }
                        String nonFampValue = nonFampList.get(ndc.getValue().toString());
                        if (nonFampValue != null) {

                            double convertedFamp = Double.valueOf(nonFampValue);

                            nonFamp.setValue(CommonUtils.DOLLAR + decimalFormat.format(convertedFamp));

                        } else {
                            NewNdcDTO dto = (NewNdcDTO) federalMap.get(Integer.parseInt(String.valueOf(ndc.getValue())));
                            if (dto != null) {

                                nonFamp.setValue(dto.getNonFamp());

                            }
                        }
                        String fssValue = fssList.get(ndc.getValue().toString());
                        if (fssValue != null) {

                            double convertedFSS = Double.valueOf(fssValue);

                            fss.setValue(CommonUtils.DOLLAR + decimalFormat.format(convertedFSS));

                        } else {
                            NewNdcDTO dto = (NewNdcDTO) federalMap.get(Integer.parseInt(String.valueOf(ndc.getValue())));
                            if (dto != null) {

                                fss.setValue(dto.getFssOGA());

                            }
                        }

                    } else {
                        ndc.setValue(StringUtils.EMPTY);
                        wac.setReadOnly(false);

                        wac.setValue(StringUtils.EMPTY);
                        nonFamp.setValue(StringUtils.EMPTY);
                        fss.setValue(StringUtils.EMPTY);
                        wac.setReadOnly(true);

                    }
                    wac.setReadOnly(true);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        wac.setConverter(dollarFormat);
        nonFamp.setConverter(dollarFormat);
        fss.setConverter(dollarFormat);
        wac.setImmediate(true);
        wac.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {

                try {
                    if (event.getProperty().getValue() != null && !StringUtils.EMPTY.equals(event.getProperty().getValue())) {

                        double nonFampValue = Double.valueOf(wac.getValue().replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)) * 0.96;
                        nonFamp.setValue(String.valueOf(nonFampValue));
                        double fssValue = Double.valueOf(wac.getValue().replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)) * 0.74;
                        fss.setValue(String.valueOf(fssValue));

                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        wac.setReadOnly(true);
        wac.setImmediate(true);
        fss.setImmediate(true);

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(USER_ID.getConstant()));
        try {
            final Map<String, AppPermission> tabItemHM = stplSecurity.getBusinessFunctionPermission(userId, NATIONAL_ASSUMPTIONS.getConstant() + "," + "Federal New NDC PopUp");
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

    @UiHandler("populate")
    public void populate(Button.ClickEvent event) {
        if (!wac.getValue().isEmpty() && !nonFamp.getValue().isEmpty() && ndc.getValue() != null && !fss.getValue().isEmpty()) {
            populateLogic();
        } else {
            AbstractNotificationUtils.getErrorNotification("Populate Error ", "All fields above must be complete before you can click the POPULATE command button.");

        }
    }

    private void loadNdcList() {
        for (int key : itemIdMap.keySet()) {
            ndc.addItem(key);
            ndc.setItemCaption(key, itemIdMap.get(key));
        }
    }

    private void populateLogic() {

        NewNdcDTO newNDC = new NewNdcDTO();
        newNDC.setNonFamp(nonFamp.getValue());
        newNDC.setFssOGA(fss.getValue());

        String ndcNo = ndc.getItemCaption(Integer.parseInt(String.valueOf(ndc.getValue())));
        if (ndcNo.contains(",")) {
            String[] ndcNo1 = ndcNo.split(",");
            newNDC.setItemNo(ndc.getItemCaption(ndcNo1[1].trim()));
        } else {
            newNDC.setItemNo(ndc.getItemCaption(ndc.getValue()));
        }
        newNDC.setItemMasterSid(Integer.parseInt(String.valueOf(ndc.getValue())));
        newNDC.setWac(wac.getValue());

        federalBean.addItem(newNDC);
        federalMap.put(newNDC.getItemMasterSid(), newNDC);
        ndc.removeItem(newNDC.getItemMasterSid());

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

    void saveAll() throws SystemException, PortalException {
        final List<NewNdcDTO> newNDCList = federalBean.getItemIds();
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        Long userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID));
        for (NewNdcDTO newNdcDTO : newNDCList) {
            DynamicQuery naDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(StFederalNewNdc.class);
            List<StFederalNewNdc> list = new ArrayList<StFederalNewNdc>();

            naDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.itemMasterSid", newNdcDTO.getItemMasterSid()));
            naDynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey.sessionId", sessionId));
            list = StFederalNewNdcLocalServiceUtil.dynamicQuery(naDynamicQuery);
            nationalAssumptionLogic.saveFedralNdcPopUp(userId, sessionId, list, newNdcDTO);

        }

    }

    public boolean closeFlag() {
        return flag;
    }

    public void loadTable() {
        try {
            NationalAssumptionLogic logic = new NationalAssumptionLogic();
            List<NewNdcDTO> list = logic.getFederalTable();
            federalBean.addAll(list);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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

    private void resetLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {

                ndc.setValue(null);
                wac.setValue(StringUtils.EMPTY);
                nonFamp.setValue(StringUtils.EMPTY);
                fss.setValue(StringUtils.EMPTY);

            }

            @Override
            public void noMethod() {
                // TODO Auto-generated method stub
            }
        }.getConfirmationMessage(RESET_CONFIRMATION.getConstant(),
                "Are you sure you want to reset the page to default/previous values?");
    }

    public void resetTableLogic() throws SystemException, Exception {
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
        NewNdcDTO projection = (NewNdcDTO) federalTable.getValue();

        String value = StringUtils.EMPTY;


        List<StFederalNewNdc> listCount = nationalAssumptionLogic.federalListCount(projection.getItemMasterSid(), sessionId);
        if (listCount.isEmpty()) {

            federalBean.removeItem(projection);
            ndc.addItem(projection.getItemMasterSid());

            value = itemIdMap.get(projection.getItemMasterSid());

            ndc.setItemCaption(projection.getItemMasterSid(), value);
            projection.setNdcDescription(value);
            federalMap.put(projection.getItemMasterSid(), projection);
        } else {

            federalBean.removeItem(projection);
            nationalAssumptionLogic.federalDeleteLogic(projection.getItemMasterSid());
            value = itemIdMap.get(projection.getItemMasterSid());
            removedFederalNdc.add(projection);
            ndc.addItem(projection.getItemMasterSid());
            ndc.setItemCaption(projection.getItemMasterSid(), value);
            projection.setNdcDescription(value);
            federalMap.put(projection.getItemMasterSid(), projection);
        }

    }

    public void deleteFederalNdc() {
        for (NewNdcDTO removed : removedFederalNdc) {
            nationalAssumptionLogic.federalMainDelete(removed.getItemMasterSid());
        }
        removedFederalNdc.clear();
    }
}
