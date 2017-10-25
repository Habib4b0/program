/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.company.ui.view.CompanyAddView;
import com.stpl.app.global.company.util.FiledNameUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ResponsiveUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger; 
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author shyam.duraipandian
 */
public class CompanyAddressForm extends CustomComponent {

    /**
     * The Horizontal Layout.
     */
    @UiField("hLayout1")
    HorizontalLayout hlayout1;
    @UiField("hLayout2")
    HorizontalLayout hlayout2;
    @UiField("hLayout3")
    HorizontalLayout hlayout3;
    @UiField("hLayout4")
    HorizontalLayout hlayout4;
    @UiField("hLayout5")
    HorizontalLayout hlayout5;
    @UiField("hLayout6")
    HorizontalLayout hlayout6;
    @UiField("hLayout7")
    HorizontalLayout hlayout7;
    /**
     * The CsLayout Layout.
     */
    @UiField("cssLayout1")
    CssLayout cssLayout1;
    @UiField("cssLayout2")
    CssLayout cssLayout2;
    @UiField("cssLayout3")
    CssLayout cssLayout3;
    @UiField("cssLayout4")
    CssLayout cssLayout4;
    @UiField("cssLayout5")
    CssLayout cssLayout5;
    @UiField("cssLayout6")
    CssLayout cssLayout6;
    @UiField("cssLayout7")
    CssLayout cssLayout7;
    /**
     * The Error Msg.
     */
    @UiField("errorMsg")
    public ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The region code.
     */
    @UiField("regionCode")
    TextField regionCode;
    /**
     * The address1.
     */
    @UiField("address1")
    TextField address1;
    /**
     * The address2.
     */
    @UiField("address2")
    TextField address2;
    /**
     * The zip code.
     */
    @UiField("zipCode")
    TextField zipCode;
    /**
     * The city.
     */
    @UiField("city")
    TextField city;
    /**
     * The state.
     */
    @UiField("state")
    ComboBox state;
    /**
     * The country.
     */
    @UiField("country")
    ComboBox country;
    /**
     * The region code.
     */
    @UiField("labelRegionCode")
    Label labelRegionCode;
    /**
     * The address1.
     */
    @UiField("labelAddress1")
    Label labelAddress1;
    /**
     * The address2.
     */
    @UiField("labelAddress2")
    Label labelAddress2;
    /**
     * The zip code.
     */
    @UiField("labelZipCode")
    Label labelZipCode;
    /**
     * The city.
     */
    @UiField("labelCity")
    Label labelCity;
    /**
     * The state.
     */
    @UiField("labelState")
    Label labelState;
    /**
     * The country.
     */
    @UiField("labelCountry")
    Label labelCountry;
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();  
    /** The company DTO. */
    CompanyMasterDTO companyMasterDTO = new CompanyMasterDTO();
    /** The binder. */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<CompanyMasterDTO>(companyMasterDTO));
    private static final Logger LOGGER = Logger.getLogger(CompanyAddView.class);
    CommonUtil commmonUtil=CommonUtil.getInstance();
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    IFPLogic ifpLogic = new IFPLogic();
    SessionDTO sessionDTO;

    
    /**
     * Used add the components
     * 
     * @param binder
     * @return
     * @throws PortalException
     * @throws SystemException
     * @throws Exception 
     */
    public Component getContent(ErrorfulFieldGroup binder, SessionDTO sessionDTO) throws PortalException, SystemException {
        this.binder = binder;
        this.sessionDTO=sessionDTO;

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        final Map<String, AppPermission> fieldCompanyHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER+","+"Address",false);

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponent(Clara.create(getClass().getResourceAsStream("/clara/companyMaster/CompanyAddressForm.xml"), this));
     
        getResponsiveSecondTab(fieldCompanyHM);
        configureFields();
          getBinder();

        return vLayout;
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    /**
     * Used for configuring the fields
     */
    public void configureFields() {
        LOGGER.debug("configureFields");
        try {

            address1.setImmediate(true);
            address1.setValidationVisible(true);
            address1.setDescription(address1.getValue());
            address1.setData("maxlengthvalidationhundred,maxlengthvalidationaddress1,alphaNumericChars,alphaNumericCharsMessage");
            address1.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    address1.setDescription(address1.getValue());
                }
            });
            address2.setImmediate(true);
            address2.setValidationVisible(true);
            address2.setDescription(address2.getValue());
            address2.setData("maxlengthvalidationhundred,maxlengthvalidationaddress2,alphaNumericChars,alphaNumericCharsMessage");
            address2.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    address2.setDescription(address2.getValue());
                }
            });
            city.setImmediate(true);
            city.setValidationVisible(true);
            city.setData("maxlengthvalidationthirty,maxlengthvalidationcity,cityalphanumericvalid,cityalphanumeric");
            city.setDescription(city.getValue());
            city.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    city.setDescription(city.getValue());
                }
            });
            zipCode.setImmediate(true);
            zipCode.setValidationVisible(true);
            zipCode.setData("maxlenthtwenty,maxlengthvalidationzipcode,numericonly,zipvalid");
            zipCode.setDescription(zipCode.getValue());
            zipCode.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    zipCode.setDescription(zipCode.getValue());
                }
            });
            regionCode.setImmediate(true);
            regionCode.setValidationVisible(true);
            regionCode.setData("maxlengthvalidationfifty,maxlengthvalidationRegioncode,alphaNumericChars,alphaNumericCharsMessage");
            regionCode.setDescription(regionCode.getValue());
            regionCode.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    regionCode.setDescription(regionCode.getValue());
                }
            });

            commonUtil.loadComboBox(state, UIUtils.STATE, false);
            commonUtil.loadComboBox(country, UIUtils.COUNTRY, false);
        } catch (Exception ex) {
           LOGGER.error(ex);
        }
    }

    private void getResponsiveSecondTab(final Map<String, AppPermission> fieldCompanyHM) {

        ResponsiveUtils.addComponentInCsssLayout(cssLayout1, ResponsiveUtils.makeLabel(labelAddress1, false), address1,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.ADDRESS1)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.ADDRESS1)).isAddFlag());
        
        ResponsiveUtils.addComponentInCsssLayout(cssLayout2, ResponsiveUtils.makeLabel(labelCity, false), city,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.CITY)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.CITY)).isAddFlag());
                
        ResponsiveUtils.addComponentInCsssLayout(cssLayout3, ResponsiveUtils.makeLabel(labelZipCode, false), zipCode,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.ZIP_CODE)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.ZIP_CODE)).isAddFlag());
        
        ResponsiveUtils.addComponentInCsssLayout(cssLayout4, ResponsiveUtils.makeLabel(labelCountry, false), country,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.COUNTRY)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.COUNTRY)).isAddFlag());
       
        ResponsiveUtils.addComponentInCsssLayout(cssLayout5, ResponsiveUtils.makeLabel(labelAddress2, false), address2,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.ADDRESS2)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.ADDRESS2)).isAddFlag());
        
         ResponsiveUtils.addComponentInCsssLayout(cssLayout6, ResponsiveUtils.makeLabel(labelState, false), state,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.STATE)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.STATE)).isAddFlag());

                ResponsiveUtils.addComponentInCsssLayout(cssLayout7, ResponsiveUtils.makeLabel(labelRegionCode, false), regionCode,
                (((AppPermission) fieldCompanyHM.get(FiledNameUtils.REGION_CODE)) == null) ? false : ((AppPermission) fieldCompanyHM.get(FiledNameUtils.REGION_CODE)).isAddFlag());


        LOGGER.debug("Entering getResponsiveSecondTab");
        try {
        String mode = sessionDTO.getMode();
        
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_MASTER,"Address");
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout1, fieldCompanyHM, mode);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout2, fieldCompanyHM, mode);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout3, fieldCompanyHM, mode);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout4, fieldCompanyHM, mode);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout5, fieldCompanyHM, mode);
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout6, fieldCompanyHM, mode);
        
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending getResponsiveSecondTab");


    }
            }
