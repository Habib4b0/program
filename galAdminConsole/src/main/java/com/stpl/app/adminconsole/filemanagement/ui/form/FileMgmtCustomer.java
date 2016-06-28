/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.form;

import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

public class FileMgmtCustomer extends Window {

    @UiField("forecastdate")
    private TextField forecastdate;
    @UiField("country")
    private TextField country;
    @UiField("forecastName")
    private TextField forecastName;
    @UiField("batchId")
    private TextField batchId;
    @UiField("brand")
    private TextField brand;
    @UiField("itemId")
    private TextField itemId;
    @UiField("companyId")
    private TextField companyId;
    @UiField("segment")
    private TextField segment;
    @UiField("deductionCategory")
    private TextField deductionCategory;
    @UiField("deductionType")
    private TextField deductionType;
    @UiField("deductionProgramType")
    private TextField deductionProgramType;
    @UiField("deductionId")
    private TextField deductionId;
    @UiField("units")
    private TextField units;
    @UiField("price")
    private TextField price;
    @UiField("salesAmount")
    private TextField salesAmount;
    @UiField("salesInclusion")
    private TextField salesInclusion;
    @UiField("adjustmentCode")
    private TextField adjustmentCode;
    @UiField("deductionRate")
    private TextField deductionRate;
    @UiField("deductionAmount")
    private TextField deductionAmount;
    @UiField("deductionInclusion")
    private TextField deductionInclusion;
    @UiField("priceType")
    private TextField priceType;
    @UiField("forecastValueType")
    private TextField forecastValueType;
    @UiField("forecastYear")
    private TextField forecastYear;
    @UiField("forecastMonth")
    private TextField forecastMonth;
    @UiField("udc1")
    private TextField udc1;
    @UiField("udc4")
    private TextField udc4;
    @UiField("createdDate")
    private TextField createdDate;
    @UiField("createdBy")
    private TextField createdBy;
    @UiField("udc2")
    private TextField udc2;
    @UiField("udc5")
    private TextField udc5;
    @UiField("modifiedDate")
    private TextField modifiedDate;
    @UiField("modifiedBy")
    private TextField modifiedBy;
    @UiField("udc3")
    private TextField udc3;
    @UiField("udc6")
    private TextField udc6;
    @UiField("forecastInterfaceId")
    private TextField forecastInterfaceId;
    @UiField("source")
    private TextField source;
    @UiField("close")
    private Button close;
    FileManagementLogic logic = new FileManagementLogic();
    public static final Logger LOGGER = Logger.getLogger(FileMgmtCustomer.class);

    public FileMgmtCustomer() {
    }

    public void init(String systemId) {
        try {
            LOGGER.info("init Method initiated");

            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");
            addStyleName("fileMgmt");
            addStyleName("popupwinwidth");
            center();
            setClosable(true);
            setModal(true);
            setWidth("1000px");
            setHeight("750px");
            setContent(Clara.create(getClass().getResourceAsStream("/clara/fileMgmtCustomer.xml"), this));
            setCustomerValues(systemId);
            setReadOnly();

            LOGGER.info("init method Ended");
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public void setCustomerValues(String systemId) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        List customerList = logic.getCustomerValues(systemId);
        final Object[] obj = (Object[]) customerList.get(0);
        forecastdate.setValue(!"null".equals(String.valueOf(obj[0])) ? String.valueOf(formatter.format(obj[0])) : StringUtils.EMPTY);
        country.setValue(!"null".equals(String.valueOf(obj[1])) ? String.valueOf(obj[1]) : StringUtils.EMPTY);
        forecastName.setValue(!"null".equals(String.valueOf(obj[2])) ? String.valueOf(obj[2]) : StringUtils.EMPTY);
        batchId.setValue(!"null".equals(String.valueOf(obj[3])) ? String.valueOf(obj[3]) : StringUtils.EMPTY);
        brand.setValue(!"null".equals(String.valueOf(obj[4])) ? String.valueOf(obj[4]) : StringUtils.EMPTY);
        itemId.setValue(!"null".equals(String.valueOf(obj[5])) ? String.valueOf(obj[5]) : StringUtils.EMPTY);
        companyId.setValue(!"null".equals(String.valueOf(obj[6])) ? String.valueOf(obj[6]) : StringUtils.EMPTY);
        segment.setValue(!"null".equals(String.valueOf(obj[7])) ? String.valueOf(obj[7]) : StringUtils.EMPTY);
        deductionCategory.setValue(!"null".equals(String.valueOf(obj[8])) ? String.valueOf(obj[8]) : StringUtils.EMPTY);
        deductionType.setValue(!"null".equals(String.valueOf(obj[9])) ? String.valueOf(obj[9]) : StringUtils.EMPTY);
        deductionProgramType.setValue(!"null".equals(String.valueOf(obj[10])) ? String.valueOf(obj[10]) : StringUtils.EMPTY);
        deductionId.setValue(!"null".equals(String.valueOf(obj[11])) ? String.valueOf(obj[11]) : StringUtils.EMPTY);
        units.setValue(!"null".equals(String.valueOf(obj[12])) ? String.valueOf(obj[12]) : StringUtils.EMPTY);
        price.setValue(!"null".equals(String.valueOf(obj[13])) ? String.valueOf(obj[13]) : StringUtils.EMPTY);
        salesAmount.setValue(!"null".equals(String.valueOf(obj[14])) ? String.valueOf(obj[14]) : StringUtils.EMPTY);
        salesInclusion.setValue(!"null".equals(String.valueOf(obj[15])) ? String.valueOf(obj[15]) : StringUtils.EMPTY);
        adjustmentCode.setValue(!"null".equals(String.valueOf(obj[16])) ? String.valueOf(obj[16]) : StringUtils.EMPTY);
        deductionRate.setValue(!"null".equals(String.valueOf(obj[17])) ? String.valueOf(obj[17]) : StringUtils.EMPTY);
        deductionAmount.setValue(!"null".equals(String.valueOf(obj[18])) ? String.valueOf(obj[18]) : StringUtils.EMPTY);
        deductionInclusion.setValue(!"null".equals(String.valueOf(obj[19])) ? String.valueOf(obj[19]) : StringUtils.EMPTY);
        priceType.setValue(!"null".equals(String.valueOf(obj[20])) ? String.valueOf(obj[20]) : StringUtils.EMPTY);
        forecastValueType.setValue(!"null".equals(String.valueOf(obj[21])) ? String.valueOf(obj[21]) : StringUtils.EMPTY);
        forecastYear.setValue(!"null".equals(String.valueOf(obj[22])) ? String.valueOf(obj[22]) : StringUtils.EMPTY);
        forecastMonth.setValue(!"null".equals(String.valueOf(obj[23])) ? String.valueOf(obj[23]) : StringUtils.EMPTY);
        source.setValue(!"null".equals(String.valueOf(obj[24])) ? String.valueOf(obj[24]) : StringUtils.EMPTY);
        createdBy.setValue(!"null".equals(String.valueOf(obj[25])) ? String.valueOf(obj[25]) : StringUtils.EMPTY);
        createdDate.setValue(!"null".equals(String.valueOf(obj[26])) ? String.valueOf(formatter.format(obj[26])) : StringUtils.EMPTY);
        modifiedBy.setValue(!"null".equals(String.valueOf(obj[27])) ? String.valueOf(obj[27]) : StringUtils.EMPTY);
        modifiedDate.setValue(!"null".equals(String.valueOf(obj[28])) ? String.valueOf(formatter.format(obj[28])) : StringUtils.EMPTY);
        udc1.setValue(!"null".equals(String.valueOf(obj[29])) ? String.valueOf(obj[29]) : StringUtils.EMPTY);
        udc2.setValue(!"null".equals(String.valueOf(obj[30])) ? String.valueOf(obj[30]) : StringUtils.EMPTY);
        udc3.setValue(!"null".equals(String.valueOf(obj[31])) ? String.valueOf(obj[31]) : StringUtils.EMPTY);
        udc4.setValue(!"null".equals(String.valueOf(obj[32])) ? String.valueOf(obj[32]) : StringUtils.EMPTY);
        udc5.setValue(!"null".equals(String.valueOf(obj[33])) ? String.valueOf(obj[33]) : StringUtils.EMPTY);
        udc6.setValue(!"null".equals(String.valueOf(obj[34])) ? String.valueOf(obj[34]) : StringUtils.EMPTY);
        forecastInterfaceId.setValue(!"null".equals(String.valueOf(obj[35])) ? String.valueOf(obj[35]) : StringUtils.EMPTY);

    }

    @UiHandler("close")
    public void btncloseLogic(Button.ClickEvent event) {
        close();
    }

    private void setReadOnly() {
        forecastdate.setReadOnly(true);
        country.setReadOnly(true);
        forecastName.setReadOnly(true);
        batchId.setReadOnly(true);
        brand.setReadOnly(true);
        itemId.setReadOnly(true);
        segment.setReadOnly(true);
        deductionCategory.setReadOnly(true);
        deductionType.setReadOnly(true);
        deductionProgramType.setReadOnly(true);
        deductionId.setReadOnly(true);
        units.setReadOnly(true);
        price.setReadOnly(true);
        salesAmount.setReadOnly(true);
        salesInclusion.setReadOnly(true);
        adjustmentCode.setReadOnly(true);
        deductionRate.setReadOnly(true);
        deductionAmount.setReadOnly(true);
        deductionInclusion.setReadOnly(true);
        priceType.setReadOnly(true);
        forecastValueType.setReadOnly(true);
        forecastYear.setReadOnly(true);
        forecastMonth.setReadOnly(true);
        source.setReadOnly(true);
        createdBy.setReadOnly(true);
        createdDate.setReadOnly(true);
        modifiedBy.setReadOnly(true);
        modifiedDate.setReadOnly(true);
        udc1.setReadOnly(true);
        udc2.setReadOnly(true);
        udc3.setReadOnly(true);
        udc4.setReadOnly(true);
        udc5.setReadOnly(true);
        udc6.setReadOnly(true);
        companyId.setReadOnly(true);
        forecastInterfaceId.setReadOnly(true);
    }

}
