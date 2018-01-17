package com.stpl.app.gtnforecasting.nationalassumptions.ui.form;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 *
 * @author Nadhiya
 */
public class FederalNdcPopup extends CustomComponent {

    /**
     * The federal table.
     */
    @UiField("federalTable")
    private ExtFilterTable federalTable;
    /**
     * The federal Results bean.
     */

    private final BeanItemContainer<NewNdcDTO> federalBean = new BeanItemContainer<>(NewNdcDTO.class);
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

    @UiField("nonFamp")
    private TextField nonFamp;

    @UiField("populate")
    private Button populate;

    @UiField("fss")
    private TextField fss;

    /**
     * The reset.
     */
    @UiField("reset")
    private Button reset;

    private final Map<String, String> wACList;
    private final Map<String, String> nonFampList;
    private final Map<String, String> fssList;
    private Map<Integer, String> itemIdMap = new HashMap<>();
    private final List<String> listNDC;
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.0000");
    private boolean flag;
    private final NationalAssumptionLogic nationalAssumptionLogic = new NationalAssumptionLogic();
    private final List<NewNdcDTO> removedFederalNdc = new ArrayList<>();
    private final Map<Integer, Object> federalMap;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FederalNdcPopup.class);
    private final DataFormatConverter dollarFormat = new DataFormatConverter("#,##0.0000", DataFormatConverter.INDICATOR_DOLLAR);
    private final SessionDTO sessionDTO;

    public FederalNdcPopup(NewNdcDTO newNdcDTO, Map<Integer, Object> federalMap,SessionDTO sessionDTO) {
        this.sessionDTO=sessionDTO;
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
        federalTable.setVisibleColumns(CommonUiUtils.getFederalNdcColumns());
        federalTable.setColumnHeaders(CommonUiUtils.getFederalNdcHeader());
        federalTable.setFilterBarVisible(true);
        federalTable.setFilterDecorator(new ExtDemoFilterDecorator());
        federalTable.addStyleName(Constant.FILTER_TABLE);
        federalTable.addStyleName("table-header-center");
        federalTable.setColumnAlignment(CommonUiUtils.getFederalNdcColumns()[0], ExtFilterTable.Align.LEFT);
        int length = CommonUiUtils.getFederalNdcColumns().length;
        for (int i = 1; i < length; i++) {
            federalTable.setColumnAlignment(CommonUiUtils.getFederalNdcColumns()[i], ExtFilterTable.Align.RIGHT);
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
                } catch (Property.ReadOnlyException | NumberFormatException ex) {
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

                        double nonFampValue = Double.valueOf(wac.getValue().replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)) * NumericConstants.DOUBLE_NINTY_SIX;
                        nonFamp.setValue(String.valueOf(nonFampValue));
                        double fssValue = Double.valueOf(wac.getValue().replace(CommonUtils.DOLLAR, StringUtils.EMPTY).replace(",", StringUtils.EMPTY)) * NumericConstants.DOUBLE_SEVENTY_FOUR;
                        fss.setValue(String.valueOf(fssValue));

                    }
                } catch (Property.ReadOnlyException | NumberFormatException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        });
        wac.setReadOnly(true);
        wac.setImmediate(true);
        fss.setImmediate(true);

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = sessionDTO.getUserId();
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
        } catch (PortalException | SystemException portal) {
            LOGGER.error(StringUtils.EMPTY,portal);
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

    public int addLogic() throws SystemException, PortalException {
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
        for (NewNdcDTO ndcDTO : newNDCList) {
            String customSQL="SELECT * from ST_FEDERAL_NEW_NDC where ITEM_MASTER_SID="+ ndcDTO.getItemMasterSid();
            List list = HelperTableLocalServiceUtil.executeSelectQuery( QueryUtil.replaceTableNames(customSQL, sessionDTO.getCurrentTableNames()));
            nationalAssumptionLogic.saveFedralNdcPopUp(sessionDTO, list, ndcDTO);
        }

    }

    public boolean closeFlag() {
        return flag;
    }

    public void loadTable() {
        try {
            NationalAssumptionLogic logic = new NationalAssumptionLogic();
            List<NewNdcDTO> list = logic.getFederalTable(sessionDTO);
            federalBean.addAll(list);
        } catch (PortalException | SystemException e) {
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

    public void resetTableLogic() throws SystemException {
        NewNdcDTO projection = (NewNdcDTO) federalTable.getValue();

        String value;


        List listCount = nationalAssumptionLogic.federalListCount(projection.getItemMasterSid(), sessionDTO);
        if (listCount.isEmpty()) {

            federalBean.removeItem(projection);
            ndc.addItem(projection.getItemMasterSid());

            value = itemIdMap.get(projection.getItemMasterSid());

            ndc.setItemCaption(projection.getItemMasterSid(), value);
            projection.setNdcDescription(value);
            federalMap.put(projection.getItemMasterSid(), projection);
        } else {

            federalBean.removeItem(projection);
            nationalAssumptionLogic.federalDeleteLogic(projection.getItemMasterSid(),sessionDTO);
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
