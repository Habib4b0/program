/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.item.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.ui.lazyload.BrandContainer;
import com.stpl.app.global.item.ui.lazyload.BrandCriteria;
import com.stpl.app.global.item.ui.lazyload.ManufactureIdCriteria;
import com.stpl.app.global.item.ui.lazyload.ManufacturerIdContainer;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.asi.ui.custommenubar.CustomMenuBar;

/**
 *
 * @author sooriya.lakshmanan
 */
public class ItemInformation extends CustomComponent implements View {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemInformation.class);
    final CommonUtils commonsUtil = new CommonUtils();
    @UiField("cssLayout")
    CssLayout cssLayout;
    @UiField("hLayout")
    HorizontalLayout hlayout;

    @UiField("itemIdLB")
    private Label itemIdlb;

    /**
     * The item id.
     */
    @UiField("itemId")
    private TextField itemId;

    @UiField("itemNoLB")
    private Label itemNolb;

    /**
     * The item no.
     */
    @UiField("itemNo")
    private TextField itemNo;

    @UiField("itemNameLB")
    private Label itemNameLB;

    /**
     * The item name.
     */
    @UiField("itemName")
    private TextField itemName;

    @UiField("itemDescLB")
    private Label itemDescLB;

    /**
     * The item desc.
     */
    @UiField("itemDesc")
    private TextField itemDesc;

    @UiField("itemStatusLB")
    private Label itemStatusLB;

    /**
     * The item status.
     */
    @UiField("itemStatus")
    private ComboBox itemStatus;

    @UiField("itemStartDateLB")
    private Label itemStartDateLB;

    /**
     * The item start date.
     */
    @UiField("itemStartDate")
    private PopupDateField itemStartDate;

    @UiField("itemEndDateLB")
    private Label itemEndDateLB;

    /**
     * The item end date.
     */
    @UiField("itemEndDate")
    private PopupDateField itemEndDate;

    @UiField("itemTypeLB")
    private Label itemTypeLB;
    /**
     * The item type.
     */
    @UiField("itemType")
    private ComboBox itemType;

    @UiField("therapeuticClassLB")
    private Label therapeuticClassLB;
    /**
     * Therapeutic class.
     */
    @UiField("therapeuticClass")
    private ComboBox therapeuticClass;

    @UiField("brandLB")
    private Label brandLB;
    /**
     * The brand.
     */
    @UiField("brand")
    private ComboBox brandDdlb;

    @UiField("brandIdLB")
    private Label brandIdLB;
    /**
     * The brand id.
     */
    @UiField("brandId")
    private TextField brandId;

    @UiField("displayBrandLB")
    private Label displayBrandLB;
    /**
     * The display brand.
     */
    @UiField("displayBrand")
    private TextField displayBrand;

    @UiField("itemClassLB")
    private Label itemClassLB;

    /**
     * The item class.
     */
    @UiField("itemClass")
    private ComboBox itemClass;

    @UiField("formLB")
    private Label formLB;

    /**
     * The form.
     */
    @UiField("form")
    private ComboBox form;

    @UiField("strengthLB")
    private Label strengthLB;

    /**
     * The strength.
     */
    @UiField("strength")
    private ComboBox strength;

    @UiField("firstSaleDateLB")
    private Label firstSaleDateLB;

    /**
     * The first sale date.
     */
    @UiField("firstSaleDate")
    private PopupDateField firstSaleDate;

    @UiField("ndc8LB")
    private Label ndc8LB;

    /**
     * The ndc8.
     */
    @UiField("ndc8")
    private TextField ndc8;

    @UiField("ndc9LB")
    private Label ndc9LB;

    /**
     * The ndc9.
     */
    @UiField("ndc9")
    private TextField ndc9;

    @UiField("primaryUomLB")
    private Label primaryUomLB;
    /**
     * The primary uom.
     */
    @UiField("primaryUom")
    private ComboBox primaryUom;

    @UiField("secondaryUomLB")
    private Label secondaryUomLB;
    /**
     * The secondary uom.
     */
    @UiField("secondaryUom")
    private ComboBox secondaryUom;

    @UiField("labelerCodeLB")
    private Label labelerCodeLB;

    /**
     * The labeler code.
     */
    @UiField("labelerCode")
    private TextField labelerCode;

    @UiField("itemCodeLB")
    private Label itemCodeLB;
    /**
     * The item code.
     */
    @UiField("itemCode")
    private TextField itemCode;

    @UiField("packageSizeLB")
    private Label packageSizeLB;

    /**
     * The package size.
     */
    @UiField("packageSize")
    private ComboBox packageSize;

    @UiField("packageSizeCodeLB")
    private Label packageSizeCodeLB;
    /**
     * The package size code.
     */
    @UiField("packageSizeCode")
    private TextField packageSizeCode;

    @UiField("itemTypeIndicationLB")
    private Label itemTypeIndicationLB;
    /**
     * The item type indication.
     */
    @UiField("itemTypeIndication")
    private ComboBox itemTypeIndication;

    @UiField("itemCategoryLB")
    private Label itemCategoryLB;

    /**
     * The item category.
     */
    @UiField("itemCategory")
    private ComboBox itemCategory;

    @UiField("uppsLB")
    private Label uppsLB;
    /**
     * The upps.
     */
    @UiField("upps")
    private TextField upps;

    @UiField("packageSizeIntroDateLB")
    private Label packageSizeIntroDateLB;

    /**
     * The package size intro date.
     */
    @UiField("packageSizeIntroDate")
    private PopupDateField packageSizeIntroDate;

    @UiField("manufacturerIdLB")
    private Label manufacturerIdLB;

    /**
     * The manufacturer id.
     */
    @UiField("manufacturerId")
    private ComboBox manufacturerIdDDLB;

    @UiField("udc1LB")
    private Label udc1LB;

    /**
     * The udc1.
     */
    @UiField("udc1")
    private ComboBox udc1;

    @UiField("udc2LB")
    private Label udc2LB;

    /**
     * The udc2.
     */
    @UiField("udc2")
    private ComboBox udc2;

    @UiField("udc3LB")
    private Label udc3LB;
    /**
     * The udc3.
     */
    @UiField("udc3")
    private ComboBox udc3;

    @UiField("udc4LB")
    private Label udc4LB;
    /**
     * The udc4.
     */
    @UiField("udc4")
    private ComboBox udc4;
    @UiField("udc5LB")
    private Label udc5LB;
    /**
     * The udc5.
     */
    @UiField("udc5")
    private ComboBox udc5;
    @UiField("udc6LB")
    private Label udc6LB;
    /**
     * The udc6.
     */
    @UiField("udc6")
    private ComboBox udc6;

    @UiField("manufacturerNameLB")
    private Label manufacturerNameLB;
    /**
     * The manufacturer name.
     */
    @UiField("manufacturerName")
    private TextField manufacturerName;

    @UiField("createdByLB")
    private Label createdByLB;

    /**
     * The created by.
     */
    @UiField("createdBy")
    private TextField createdBy;

    @UiField("createdDateLB")
    private Label createdDateLB;
    /**
     * The created date.
     */
    @UiField("createdDate")
    private PopupDateField createdDate;

    @UiField("modifiedByLB")
    private Label modifiedByLB;
    /**
     * The created by.
     */
    @UiField("modifiedBy")
    private TextField modifiedBy;
    @UiField("modifiedDateLB")
    private Label modifiedDateLB;
    /**
     * The created date.
     */
    @UiField("modifiedDate")
    private PopupDateField modifiedDate;
    @UiField("systemIdLB")
    private Label systemIdLB;
    /**
     * The systemId
     */
    @UiField("itemMasterSid")
    private TextField systemId;
    /**
     * The batchId
     */
    @UiField("batchId")
    private TextField itemBatchId;

    @UiField("organizationKeyLB")
    private Label organizationKeyLB;

    /**
     * The brand.
     */
    @UiField("organizationKey")
    private ComboBox organizationKey;

    /**
     * The item logic.
     */
    private final ItemSearchLogic itemLogic = new ItemSearchLogic();
    IFPLogic ifpLogic = new IFPLogic();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);
    ErrorfulFieldGroup binder;
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonUtil util = CommonUtil.getInstance();
    String mode;
    /**
     * The item master dto.
     */
    private ItemMasterDTO itemMasterDTO = new ItemMasterDTO();
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    @UiField("udcVariables")
    public CustomMenuBar udcVariables;
    @UiField("udcVariablesView")
    public TextField udcVariablesView;
    public CustomMenuBar.CustomMenuItem customMenuItem;
    int udcSid;

    public ItemInformation(final ErrorfulFieldGroup binder, final String mode, int udcSid) {
        this.binder = binder;
        this.mode = mode;
        this.udcSid = udcSid;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/itemmaster/iteminformation.xml"), this));
        init();

    }

    private void init() {

        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            if (ConstantsUtils.EDIT.equals(mode)
                    || ConstantsUtils.VIEW.equals(mode)) {
                configureFields();
                binder.bindMemberFields(this);

            } else {
                binder.bindMemberFields(this);
                configureFields();

            }
            final Map<String, AppPermission> fieldItemHM = stplSecurity
                    .getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_MASTER + "," + "Item Information", false);
            getResponsiveFirstTab(fieldItemHM);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }

    private void getResponsiveFirstTab(final Map<String, AppPermission> fieldItemHM) {
        LOGGER.debug("Entering getFirstTab1");
        try {

            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_MASTER, "Item Information");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending getFirstTab1");

    }

    private void configureFields() {

        try {
            firstSaleDate.setDescription(ConstantsUtils.DATE_DES);
            itemStartDate.setDescription(ConstantsUtils.DATE_DES);
            itemEndDate.setDescription(ConstantsUtils.DATE_DES);
            createdDate.setDescription(ConstantsUtils.DATE_DES);

            createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);

            packageSizeIntroDate.setDescription(ConstantsUtils.DATE_DES);

            itemId.setImmediate(true);
            itemId.focus();
            itemId.setValidationVisible(true);
            itemId.setData("maxlengthvalidation,maxlengthvalidationitemid,null,null");
            itemId.setDescription(itemId.getValue());
            itemId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemId, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    itemId.setDescription(itemId.getValue());
                }
            });

            itemNo.setImmediate(true);
            itemNo.setValidationVisible(true);
            itemNo.setData("maxlengthvalidationfifty,maxlengthvalidationitemno,null,null");
            itemNo.setDescription(itemNo.getValue());
            itemNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemNo, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    itemNo.setDescription(itemNo.getValue());
                }
            });

            itemName.setImmediate(true);
            itemName.setValidationVisible(true);
            itemName.setData("maxlengthvalidationhundred,maxlengthvalidationitemname,null,null");

            itemName.setDescription(itemName.getValue());
            itemName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemName, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    itemName.setDescription(itemName.getValue());
                }
            });

            itemDesc.setImmediate(true);
            itemDesc.setValidationVisible(true);
            itemDesc.setData("maxlengthvalidationtwofifty,maxlengthvalidationitemdesc,alphaNumericCharWithoutStarDesc,allowedSpwecialCharactersForItemDesc");
            itemDesc.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemDesc, function will be
                 * executed.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    itemDesc.setDescription(itemDesc.getValue());
                }
            });

            itemStartDate.setImmediate(true);
            itemStartDate.addValidator(new ItemInformation.DateValidatorItem(
                    ConstantsUtils.END_DATE_SHOULD_BE_GREATER));
            itemStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            itemStartDate.setId("itemStartDate");
            itemStartDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    itemStartDateYesmethod();
                }
            });

            itemEndDate.setValidationVisible(true);
            itemEndDate.setImmediate(true);
            itemEndDate.addValidator(new ItemInformation.DateValidatorItem(
                    ConstantsUtils.END_DATE_SHOULD_BE_GREATER));
            itemEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            itemEndDate.setId("itemEndDate");
            itemEndDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    itemEndDateYesMethod();
                }
            });
            if (CommonUtils.GALDERMA.equals(System.getProperty(CommonUtils.CLIENT_NAME))) {
                udc1.setVisible(false);
                udcVariables.setVisible(true);
                udcVariablesView.setVisible(false);
                customMenuItem = udcVariables.addItem("-Select one-", null);
                commonUtil.loadCustomMenu(customMenuItem, UIUtils.ARM_UDC1);
                commonUtil.loadComboBox(udc2, UIUtils.ARM_UDC2, false);
                if (ConstantsUtils.EDIT.equals(mode)) {
                    if (udcSid != 0) {
                        com.stpl.ifs.util.HelperDTO helper = HelperListUtil.getInstance().getIdHelperDTOMap().get(udcSid);
                        String desc = helper.getDescription();
                        String[] description = desc.split(",");
                        for (String des : description) {
                            int sid = HelperListUtil.getInstance().getIdByDesc(UIUtils.ARM_UDC1, des.trim());
                            CommonUtils.checkMenuBarItem(customMenuItem, sid);
                        }
                    }
                    udcVariablesView.setVisible(false);
                } else if (ConstantsUtils.VIEW.equals(mode)) {
                    udcVariablesView.setVisible(true);
                    String desc = StringUtils.EMPTY;
                    if (udcSid != 0) {
                        com.stpl.ifs.util.HelperDTO helper = HelperListUtil.getInstance().getIdHelperDTOMap().get(udcSid);
                        desc = helper.getDescription();
                        udcVariablesView.setValue(desc);
                    }
                    udcVariablesView.setValue(desc);
                    udcVariablesView.setReadOnly(true);
                    udcVariables.setVisible(false);

                }
            } else {
                udc1.setVisible(true);
                udcVariables.setVisible(false);
                udcVariablesView.setVisible(false);
                commonUtil.loadComboBox(udc2, UIUtils.UDC2, false);
            }
            commonUtil.loadComboBox(itemStatus, UIUtils.STATUS, false);
            commonUtil.loadComboBox(itemType, UIUtils.ITEM_TYPE, false);
            commonUtil.loadComboBox(therapeuticClass, UIUtils.THERAPEUTIC_CLASS, false);
            commonUtil.loadComboBox(udc1, UIUtils.UDC1, false);
            commonUtil.loadComboBox(udc3, UIUtils.UDC3, false);
            commonUtil.loadComboBox(udc4, UIUtils.UDC4, false);
            commonUtil.loadComboBox(udc5, UIUtils.UDC5, false);
            commonUtil.loadComboBox(udc6, UIUtils.UDC6, false);
            commonUtil.loadComboBox(itemClass, UIUtils.ITEM_CLASS, false);
            commonUtil.loadComboBox(form, UIUtils.FORM1, false);
            commonUtil.loadComboBox(strength, UIUtils.STRENGTH1, false);
            commonUtil.loadComboBox(primaryUom, UIUtils.UOM, false);
            commonUtil.loadComboBox(secondaryUom, ConstantsUtils.UOM, false);
            commonUtil.loadComboBox(packageSize, UIUtils.PACKAGE_SIZE, false);
            commonUtil.loadComboBox(itemTypeIndication, UIUtils.ITEM_TYPE_INDICATION, false);
            commonUtil.loadComboBox(itemCategory, UIUtils.ITEM_CATEGORY, false);

            brandId.setValue(StringUtils.EMPTY);
            displayBrand.setValue(StringUtils.EMPTY);
            displayBrand.setReadOnly(true);

            brandDdlb.setPageLength(NumericConstants.SEVEN);
            brandDdlb.setImmediate(true);
            brandDdlb.setNullSelectionAllowed(true);
            brandDdlb.setInputPrompt(ConstantsUtils.SELECT_ONE);
            brandDdlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
            brandDdlb.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
            brandDdlb.markAsDirty();
            itemMasterDTO = getBeanFromId(binder.getItemDataSource());
            final LazyContainer identifierTypeDescContainer = new LazyContainer(
                    HelperDTO.class, new BrandContainer(itemMasterDTO.getBrandDdlb()), new BrandCriteria());
            identifierTypeDescContainer.setMinFilterLength(0);
            brandDdlb.setContainerDataSource(identifierTypeDescContainer);
            brandDdlb.select(dto);
            brandDdlb.setDescription(brandDdlb.getValue().toString());
            brandDdlb
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in therapeuticClass,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            brandDdlbValueChangeListener();
                        }
                    });

            brandId.setValidationVisible(true);
            brandId.setEnabled(false);
            brandId.setImmediate(true);
            brandId.setDescription(brandId.getValue());
            brandId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in brandId, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    brandId.setDescription(brandId.getValue());
                }
            });

            firstSaleDate.setImmediate(true);
            firstSaleDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            firstSaleDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in firstSaleDate, function
                         * will be executed.
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            firstSaleDate.setDescription(CommonUIUtils
                                    .convert2DigitTo4DigitYear(firstSaleDate
                                            .getValue()));

                        }
                    });

            ndc8.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR,
                    ValidationUtils.SPECIAL_CHAR_MSG));
            ndc8.setImmediate(true);
            ndc8.setValidationVisible(true);
            ndc8.setRequired(true);
            ndc8.setData("maxlengthvalidation100,maxlengthvalidationndc8,alphaNumericCharsWithoutStar,specialcharvalidationndc8");
            ndc8.setDescription(ndc8.getValue());
            ndc8.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in ndc8, function will be executed.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    ndc8.setDescription(ndc8.getValue());
                }
            });

            ndc9.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR,
                    ValidationUtils.SPECIAL_CHAR_MSG));
            ndc9.setImmediate(true);
            ndc9.setValidationVisible(true);
            ndc9.setRequired(true);
            ndc9.setData("maxlengthvalidation9,maxlengthvalidationndc9,alphaNumericCharsWithoutStar,specialcharvalidationndc9");

            ndc9.setDescription(ndc9.getValue());
            ndc9.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in ndc9, function will be executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    ndc9.setDescription(ndc9.getValue());
                }
            });

            labelerCode.setImmediate(true);
            labelerCode.setValidationVisible(true);
            labelerCode.setData("maxlengthvalidationtwentyfive,maxlengthvalidationlabelercode,alphaNumericCharsWithoutStar,specialcharvalidationlabelercode");
            labelerCode.setDescription(labelerCode.getValue());
            labelerCode.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in labelerCode, function will be
                 * executed.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    labelerCode.setDescription(labelerCode.getValue());
                }
            });

            itemCode.setImmediate(true);
            itemCode.setValidationVisible(true);
            itemCode.setData("maxlengthvalidationtwentyfive,maxlengthvalidationitemcode,alphaNumericCharsWithoutStar,specialcharvalidationitemcode");
            itemCode.setDescription(itemCode.getValue());
            itemCode.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in itemCode, function will be
                 * executed.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    itemCode.setDescription(itemCode.getValue());
                }
            });

            packageSizeCode.setImmediate(true);
            packageSizeCode.setValidationVisible(true);
            packageSizeCode
                    .addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR,
                            ValidationUtils.SPECIAL_CHAR_MSG));
            packageSizeCode.addValidator(new StringLengthValidator(
                    "Package Size Code should be less than 25 characters", 0, NumericConstants.TWENTY_FIVE,
                    true));
            packageSizeCode.setDescription(packageSizeCode.getValue());
            packageSizeCode
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in packageSizeCode, function
                         * will be executed.
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            packageSizeCode.setDescription(packageSizeCode
                                    .getValue());
                        }
                    });

            itemTypeIndication
                    .addValueChangeListener(new Property.ValueChangeListener() {

                        public void valueChange(final Property.ValueChangeEvent event) {
                            itemTypeIndication.setDescription(String
                                    .valueOf(itemTypeIndication.getValue()));
                        }
                    });

            upps.setData("maxlengthvalidationfifty,maxlengthvalidationdra,pricevalidation,numvalidationupps");
            upps.setValidationVisible(true);
            upps.setImmediate(true);

            upps.setDescription(upps.getValue());
            upps.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in acquiredAmp text box, function
                 * will be executed.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    upps.setDescription(upps.getValue());
                }
            });

            packageSizeIntroDate.setImmediate(true);
            packageSizeIntroDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            packageSizeIntroDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in packageSizeIntroDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            packageSizeIntroDate.setDescription(CommonUIUtils
                                    .convert2DigitTo4DigitYear(packageSizeIntroDate
                                            .getValue()));

                        }
                    });

            organizationKey.setPageLength(NumericConstants.SEVEN);
            organizationKey.setImmediate(true);
            organizationKey.addItem(0);
            organizationKey.setItemCaption(0, ConstantsUtils.SELECT_ONE);
            organizationKey.setNullSelectionAllowed(false);
            organizationKey.setInputPrompt(ConstantsUtils.SELECT_ONE);
            organizationKey.markAsDirty();

            List<Object[]> list = itemLogic.getOrganizationKey(0);
            if (list != null && !list.isEmpty()) {
                for (Object[] object : list) {
                    organizationKey.addItem(object[0]);
                    organizationKey.setItemCaption(object[0], StringUtils.EMPTY + object[1]);
                }
            }

            manufacturerIdDDLB.setPageLength(NumericConstants.SEVEN);
            manufacturerIdDDLB.setImmediate(true);
            manufacturerIdDDLB.setNullSelectionAllowed(true);
            manufacturerIdDDLB.setInputPrompt(ConstantsUtils.SELECT_ONE);
            manufacturerIdDDLB.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
            manufacturerIdDDLB.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
            manufacturerIdDDLB.markAsDirty();

            final LazyContainer manufactureIdContainer = new LazyContainer(
                    HelperDTO.class, new ManufacturerIdContainer(null, false),
                    new ManufactureIdCriteria());
            manufactureIdContainer.setMinFilterLength(0);
            manufacturerIdDDLB.setContainerDataSource(manufactureIdContainer);
            manufacturerIdDDLB.select(dto);
            manufacturerIdDDLB.setDescription(manufacturerIdDDLB.getItemCaption(manufacturerIdDDLB.getValue()));

            manufacturerIdDDLB
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in manufacturerId, function
                         * will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            manufacturerIdDDLBValueChangeListener(event);
                        }
                    });

            manufacturerName.setReadOnly(true);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }

        if (!String.valueOf(mode).equals(ConstantsUtils.ADD)) {
            modifiedBy.setValue(IFPLogic.getUseName());
            modifiedDate.setValue(new Date());
        }

        createdBy.setValue(IFPLogic.getUseName());
        createdDate.setValue(new Date());

        createdBy.setImmediate(true);
        createdBy.setReadOnly(true);
        createdBy.setEnabled(false);

        createdDate.setImmediate(true);
        createdDate.setReadOnly(true);
        createdDate.setEnabled(false);

        modifiedBy.setImmediate(true);
        modifiedBy.setReadOnly(true);
        modifiedBy.setEnabled(false);

        modifiedDate.setImmediate(true);
        modifiedDate.setReadOnly(true);
        modifiedDate.setEnabled(false);
        systemId.setImmediate(true);
        systemId.setReadOnly(true);
        itemBatchId.setImmediate(true);
        itemBatchId.setReadOnly(true);
    }

    /**
     * validator for Date.
     */
    public class DateValidatorItem extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorItem(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validation.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value)
                 {
            if (itemStartDate.getValue() != null
                    && itemEndDate.getValue() != null) {
                if (itemStartDate.getValue().after(itemEndDate.getValue())) {
                    throw new Validator.InvalidValueException(
                            ConstantsUtils.END_DATE_SHOULD_BE_GREATER);
                } else if (itemStartDate.getValue().getTime() == itemEndDate
                        .getValue().getTime()) {

                    throw new Validator.InvalidValueException(
                            "Startdate and Enddate should not be equal");
                }
            }

        }

        /**
         * Checking the value is valid or not.
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (itemStartDate.getValue() != null
                    && itemEndDate.getValue() != null) {
                return itemStartDate.getValue().compareTo(
                        itemEndDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    public ItemMasterDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof ItemMasterDTO) {
            targetItem = new BeanItem<>(
                    (ItemMasterDTO) obj);
        }
        return (ItemMasterDTO) targetItem.getBean();
    }

    public void manufacturerIdDDLBValueChangeListener(final Property.ValueChangeEvent event) {
        try {
            if (event.getProperty().getValue() == null
                    || String.valueOf(
                            event.getProperty().getValue())
                    .equals(ConstantsUtils.SELECT_ONE)) {
                manufacturerName.setReadOnly(false);
                manufacturerName.setValue(StringUtils.EMPTY);
                manufacturerName.setReadOnly(true);
            } else {
                final CompanyMaster company = itemLogic.getManufacturerDetails(Integer
                        .valueOf(((HelperDTO) manufacturerIdDDLB
                                .getValue()).getId()));
                manufacturerName.setReadOnly(false);
                manufacturerName.setValue(company
                        .getCompanyName());
                manufacturerName.setReadOnly(true);

            }
            manufacturerIdDDLB
                    .setDescription(manufacturerIdDDLB
                            .getValue().toString());
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil
                    .getErrorMessage(ex);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing   
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (PortalException portException) {
            LOGGER.error(portException.getMessage());
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing   
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(portException);
        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing   
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);

        }
    }

    public void brandDdlbValueChangeListener() {
        try {
            brandDdlb.setValue(brandDdlb.getValue());
            brandDdlb.setItemCaption(((HelperDTO) brandDdlb.getValue()).getId(), String.valueOf(brandDdlb.getValue()));
            brandDdlb.setDescription(String.valueOf(brandDdlb.getValue()));
            if (brandDdlb.getValue() == null || ConstantsUtils.SELECT_ONE.equals(brandDdlb.getDescription())) {
                brandId.setReadOnly(false);
                displayBrand.setReadOnly(false);
                brandId.setValue(StringUtils.EMPTY);
                displayBrand.setValue(StringUtils.EMPTY);
                displayBrand.setReadOnly(true);
                brandId.setReadOnly(true);
            } else {
                brandId.setReadOnly(false);
                brandId.setValue(itemLogic.getBrandId(brandDdlb.getValue().toString()));
                brandId.setReadOnly(true);
                displayBrand.setReadOnly(false);
                displayBrand.setValue(itemLogic.getDisplayBrand(brandDdlb.getValue().toString()));
                displayBrand.setReadOnly(true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void itemStartDateYesmethod() {
        try {
            itemStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(itemStartDate.getValue()));
        } catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing        
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }

    public void itemEndDateYesMethod() {
        try {
            itemEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(itemEndDate.getValue()));
        } catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing    
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        }
    }

    public CustomMenuBar.CustomMenuItem getCustomMenuItem() {
        return customMenuItem;
    }
}
