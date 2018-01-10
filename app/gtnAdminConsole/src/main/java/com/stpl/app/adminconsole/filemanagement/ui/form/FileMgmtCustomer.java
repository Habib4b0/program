/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui.form;

import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.Window;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(FileMgmtCustomer.class);

    public FileMgmtCustomer() {
        LOGGER.debug("FileMgmtCustomer");
    }

    public void init(String systemId) {
        try {
            LOGGER.debug("init Method initiated");

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

            LOGGER.debug("init method Ended");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    public void setCustomerValues(String systemId) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        List customerList = logic.getCustomerValues(systemId);
        final Object[] obj = (Object[]) customerList.get(0);
        forecastdate.setValue(!"null".equals(String.valueOf(obj[0])) ? String.valueOf(formatter.format(obj[0])) : StringUtils.EMPTY);
        country.setValue(!"null".equals(String.valueOf(obj[1])) ? String.valueOf(obj[1]) : StringUtils.EMPTY);
        forecastName.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWO])) ? String.valueOf(obj[NumericConstants.TWO]) : StringUtils.EMPTY);
        batchId.setValue(!"null".equals(String.valueOf(obj[NumericConstants.THREE])) ? String.valueOf(obj[NumericConstants.THREE]) : StringUtils.EMPTY);
        brand.setValue(!"null".equals(String.valueOf(obj[NumericConstants.FOUR])) ? String.valueOf(obj[NumericConstants.FOUR]) : StringUtils.EMPTY);
        itemId.setValue(!"null".equals(String.valueOf(obj[NumericConstants.FIVE])) ? String.valueOf(obj[NumericConstants.FIVE]) : StringUtils.EMPTY);
        companyId.setValue(!"null".equals(String.valueOf(obj[NumericConstants.SIX])) ? String.valueOf(obj[NumericConstants.SIX]) : StringUtils.EMPTY);
        segment.setValue(!"null".equals(String.valueOf(obj[NumericConstants.SEVEN])) ? String.valueOf(obj[NumericConstants.SEVEN]) : StringUtils.EMPTY);
        deductionCategory.setValue(!"null".equals(String.valueOf(obj[NumericConstants.EIGHT])) ? String.valueOf(obj[NumericConstants.EIGHT]) : StringUtils.EMPTY);
        deductionType.setValue(!"null".equals(String.valueOf(obj[NumericConstants.NINE])) ? String.valueOf(obj[NumericConstants.NINE]) : StringUtils.EMPTY);
        deductionProgramType.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TEN])) ? String.valueOf(obj[NumericConstants.TEN]) : StringUtils.EMPTY);
        deductionId.setValue(!"null".equals(String.valueOf(obj[NumericConstants.ELEVEN])) ? String.valueOf(obj[NumericConstants.ELEVEN]) : StringUtils.EMPTY);
        units.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWELVE])) ? String.valueOf(obj[NumericConstants.TWELVE]) : StringUtils.EMPTY);
        price.setValue(!"null".equals(String.valueOf(obj[NumericConstants.THIRTEEN])) ? String.valueOf(obj[NumericConstants.THIRTEEN]) : StringUtils.EMPTY);
        salesAmount.setValue(!"null".equals(String.valueOf(obj[NumericConstants.FOURTEEN])) ? String.valueOf(obj[NumericConstants.FOURTEEN]) : StringUtils.EMPTY);
        salesInclusion.setValue(!"null".equals(String.valueOf(obj[NumericConstants.FIFTEEN])) ? String.valueOf(obj[NumericConstants.FIFTEEN]) : StringUtils.EMPTY);
        adjustmentCode.setValue(!"null".equals(String.valueOf(obj[NumericConstants.SIXTEEN])) ? String.valueOf(obj[NumericConstants.SIXTEEN]) : StringUtils.EMPTY);
        deductionRate.setValue(!"null".equals(String.valueOf(obj[NumericConstants.SEVENTEEN])) ? String.valueOf(obj[NumericConstants.SEVENTEEN]) : StringUtils.EMPTY);
        deductionAmount.setValue(!"null".equals(String.valueOf(obj[NumericConstants.EIGHTEEN])) ? String.valueOf(obj[NumericConstants.EIGHTEEN]) : StringUtils.EMPTY);
        deductionInclusion.setValue(!"null".equals(String.valueOf(obj[NumericConstants.NINETEEN])) ? String.valueOf(obj[NumericConstants.NINETEEN]) : StringUtils.EMPTY);
        priceType.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY])) ? String.valueOf(obj[NumericConstants.TWENTY]) : StringUtils.EMPTY);
        forecastValueType.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_ONE])) ? String.valueOf(obj[NumericConstants.TWENTY_ONE]) : StringUtils.EMPTY);
        forecastYear.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_TWO])) ? String.valueOf(obj[NumericConstants.TWENTY_TWO]) : StringUtils.EMPTY);
        forecastMonth.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_THREE])) ? String.valueOf(obj[NumericConstants.TWENTY_THREE]) : StringUtils.EMPTY);
        source.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_FOUR])) ? String.valueOf(obj[NumericConstants.TWENTY_FOUR]) : StringUtils.EMPTY);
        createdBy.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_FIVE])) ? String.valueOf(obj[NumericConstants.TWENTY_FIVE]) : StringUtils.EMPTY);
        createdDate.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_SIX])) ? String.valueOf(formatter.format(obj[NumericConstants.TWENTY_SIX])) : StringUtils.EMPTY);
        modifiedBy.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_SEVEN])) ? String.valueOf(obj[NumericConstants.TWENTY_SEVEN]) : StringUtils.EMPTY);
        modifiedDate.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_EIGHT])) ? String.valueOf(formatter.format(obj[NumericConstants.TWENTY_EIGHT])) : StringUtils.EMPTY);
        udc1.setValue(!"null".equals(String.valueOf(obj[NumericConstants.TWENTY_NINE])) ? String.valueOf(obj[NumericConstants.TWENTY_NINE]) : StringUtils.EMPTY);
        udc2.setValue(!"null".equals(String.valueOf(obj[NumericConstants.THIRTY])) ? String.valueOf(obj[NumericConstants.THIRTY]) : StringUtils.EMPTY);
        udc3.setValue(!"null".equals(String.valueOf(obj[NumericConstants.THIRTY_ONE])) ? String.valueOf(obj[NumericConstants.THIRTY_ONE]) : StringUtils.EMPTY);
        udc4.setValue(!"null".equals(String.valueOf(obj[NumericConstants.THIRTY_TWO])) ? String.valueOf(obj[NumericConstants.THIRTY_TWO]) : StringUtils.EMPTY);
        udc5.setValue(!"null".equals(String.valueOf(obj[NumericConstants.THIRTY_THREE])) ? String.valueOf(obj[NumericConstants.THIRTY_THREE]) : StringUtils.EMPTY);
        udc6.setValue(!"null".equals(String.valueOf(obj[NumericConstants.THIRTY_FOUR])) ? String.valueOf(obj[NumericConstants.THIRTY_FOUR]) : StringUtils.EMPTY);
        forecastInterfaceId.setValue(!"null".equals(String.valueOf(obj[NumericConstants.THIRTY_FIVE])) ? String.valueOf(obj[NumericConstants.THIRTY_FIVE]) : StringUtils.EMPTY);

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
