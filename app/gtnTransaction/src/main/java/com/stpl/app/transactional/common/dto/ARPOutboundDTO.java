/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author nandhakumar
 */
public class ARPOutboundDTO {

    private boolean check_Record;
    private String accountType = StringUtils.EMPTY;
    private String salesUnitsRate = StringUtils.EMPTY;
    private String arp_Id = StringUtils.EMPTY;

    private String arp_Name = StringUtils.EMPTY;
    private String company_Id = StringUtils.EMPTY;
    private String company_No = StringUtils.EMPTY;

    private String company_Name = StringUtils.EMPTY;
    private String business_Unit_Id = StringUtils.EMPTY;
    private String business_Unit_No = StringUtils.EMPTY;

    private String business_Unit_Name = StringUtils.EMPTY;
    private String item_Id = StringUtils.EMPTY;
    private String item_No = StringUtils.EMPTY;

    private String item_Name = StringUtils.EMPTY;
    private String brand_Id = StringUtils.EMPTY;
    private String brand_Name = StringUtils.EMPTY;

    private String account = StringUtils.EMPTY;
    private String account_Type = StringUtils.EMPTY;
    private String uom = StringUtils.EMPTY;
    private String account_category = StringUtils.EMPTY;
    private String account_group = StringUtils.EMPTY;
    private String category = StringUtils.EMPTY;

    private String type = StringUtils.EMPTY;
    private String program = StringUtils.EMPTY;

    private double current_Year_Jan;
    private double current_Year_Feb ;
    private double current_Year_Mar ;
    private double current_Year_Apr;
    private double current_Year_May ;
    private double current_Year_June;
    private double current_Year_July;
    private double current_Year_Aug ;
    private double current_Year_Sep ;
    private double current_Year_Oct ;
    private double current_Year_Nov ;
    private double Current_Year_Dec ;

    private double current_Year_1_Jan ;
    private double current_Year_1_Feb ;
    private double current_Year_1_Mar ;
    private double current_Year_1_Apr ;
    private double current_Year_1_May ;
    private double current_Year_1_June;
    private double current_Year_1_July;
    private double current_Year_1_Aug ;
    private double current_Year_1_Sep ;
    private double current_Year_1_Oct ;
    private double current_Year_1_Nov ;
    private double current_Year_1_Dec ;

    private double current_Year_2_Jan ;
    private double current_Year_2_Feb ;
    private double current_Year_2_Mar ;
    private double current_Year_2_Apr ;
    private double current_Year_2_May ;
    private double current_Year_2_June;
    private double current_Year_2_July;
    private double current_Year_2_Aug ;
    private double current_Year_2_Sep ;
    private double current_Year_2_Oct ;
    private double current_Year_2_Nov ;
    private double current_Year_2_Dec ;

    private Date arp_Creation_Date;
    private Date arp_Approval_Date;
    private String outbound_Status = StringUtils.EMPTY;
    private String original_Batch_ID = StringUtils.EMPTY;

  
    private String companyMasterSid= StringUtils.EMPTY;
    private String itemMasterSid= StringUtils.EMPTY;
    private String brandMasterSid= StringUtils.EMPTY;
    private String rsMoldelSid= StringUtils.EMPTY;
    private String arpDetailssid= StringUtils.EMPTY;
    private boolean etlCheckRecord=false;
    private String checkedValue=StringUtils.EMPTY;


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSalesUnitsRate() {
        return salesUnitsRate;
    }

    public void setSalesUnitsRate(String salesUnitsRate) {
        this.salesUnitsRate = salesUnitsRate;
    }

    public String getArp_Id() {
        return arp_Id;
    }

    public void setArp_Id(String arp_Id) {
        this.arp_Id = arp_Id;
    }

    public String getArp_Name() {
        return arp_Name;
    }

    public void setArp_Name(String arp_Name) {
        this.arp_Name = arp_Name;
    }

    public String getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(String company_Id) {
        this.company_Id = company_Id;
    }

    public String getCompany_No() {
        return company_No;
    }

    public void setCompany_No(String company_No) {
        this.company_No = company_No;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public void setCompany_Name(String company_Name) {
        this.company_Name = company_Name;
    }

    public String getBusiness_Unit_Id() {
        return business_Unit_Id;
    }

    public void setBusiness_Unit_Id(String business_Unit_Id) {
        this.business_Unit_Id = business_Unit_Id;
    }

    public String getBusiness_Unit_No() {
        return business_Unit_No;
    }

    public void setBusiness_Unit_No(String business_Unit_No) {
        this.business_Unit_No = business_Unit_No;
    }

    public String getBusiness_Unit_Name() {
        return business_Unit_Name;
    }

    public void setBusiness_Unit_Name(String business_Unit_Name) {
        this.business_Unit_Name = business_Unit_Name;
    }

    public String getItem_Id() {
        return item_Id;
    }

    public void setItem_Id(String item_Id) {
        this.item_Id = item_Id;
    }

    public String getItem_No() {
        return item_No;
    }

    public void setItem_No(String item_No) {
        this.item_No = item_No;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public String getBrand_Id() {
        return brand_Id;
    }

    public void setBrand_Id(String brand_Id) {
        this.brand_Id = brand_Id;
    }

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        this.brand_Name = brand_Name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount_Type() {
        return account_Type;
    }

    public void setAccount_Type(String account_Type) {
        this.account_Type = account_Type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public double getCurrent_Year_Jan() {
        return current_Year_Jan;
    }

    public void setCurrent_Year_Jan(double current_Year_Jan) {
        this.current_Year_Jan = current_Year_Jan;
    }

    public double getCurrent_Year_Feb() {
        return current_Year_Feb;
    }

    public void setCurrent_Year_Feb(double current_Year_Feb) {
        this.current_Year_Feb = current_Year_Feb;
    }

    public double getCurrent_Year_Mar() {
        return current_Year_Mar;
    }

    public void setCurrent_Year_Mar(double current_Year_Mar) {
        this.current_Year_Mar = current_Year_Mar;
    }

    public double getCurrent_Year_Apr() {
        return current_Year_Apr;
    }

    public void setCurrent_Year_Apr(double current_Year_Apr) {
        this.current_Year_Apr = current_Year_Apr;
    }

    public double getCurrent_Year_May() {
        return current_Year_May;
    }

    public void setCurrent_Year_May(double current_Year_May) {
        this.current_Year_May = current_Year_May;
    }

    public double getCurrent_Year_June() {
        return current_Year_June;
    }

    public void setCurrent_Year_June(double current_Year_June) {
        this.current_Year_June = current_Year_June;
    }

    public double getCurrent_Year_July() {
        return current_Year_July;
    }

    public void setCurrent_Year_July(double current_Year_July) {
        this.current_Year_July = current_Year_July;
    }

    public double getCurrent_Year_Aug() {
        return current_Year_Aug;
    }

    public void setCurrent_Year_Aug(double current_Year_Aug) {
        this.current_Year_Aug = current_Year_Aug;
    }

    public double getCurrent_Year_Sep() {
        return current_Year_Sep;
    }

    public void setCurrent_Year_Sep(double current_Year_Sep) {
        this.current_Year_Sep = current_Year_Sep;
    }

    public double getCurrent_Year_Oct() {
        return current_Year_Oct;
    }

    public void setCurrent_Year_Oct(double current_Year_Oct) {
        this.current_Year_Oct = current_Year_Oct;
    }

    public double getCurrent_Year_Nov() {
        return current_Year_Nov;
    }

    public void setCurrent_Year_Nov(double current_Year_Nov) {
        this.current_Year_Nov = current_Year_Nov;
    }

    public double getCurrent_Year_Dec() {
        return Current_Year_Dec;
    }

    public void setCurrent_Year_Dec(double Current_Year_Dec) {
        this.Current_Year_Dec = Current_Year_Dec;
    }

    public double getCurrent_Year_1_Jan() {
        return current_Year_1_Jan;
    }

    public void setCurrent_Year_1_Jan(double current_Year_1_Jan) {
        this.current_Year_1_Jan = current_Year_1_Jan;
    }

    public double getCurrent_Year_1_Feb() {
        return current_Year_1_Feb;
    }

    public void setCurrent_Year_1_Feb(double current_Year_1_Feb) {
        this.current_Year_1_Feb = current_Year_1_Feb;
    }

    public double getCurrent_Year_1_Mar() {
        return current_Year_1_Mar;
    }

    public void setCurrent_Year_1_Mar(double current_Year_1_Mar) {
        this.current_Year_1_Mar = current_Year_1_Mar;
    }

    public double getCurrent_Year_1_Apr() {
        return current_Year_1_Apr;
    }

    public void setCurrent_Year_1_Apr(double current_Year_1_Apr) {
        this.current_Year_1_Apr = current_Year_1_Apr;
    }

    public double getCurrent_Year_1_May() {
        return current_Year_1_May;
    }

    public void setCurrent_Year_1_May(double current_Year_1_May) {
        this.current_Year_1_May = current_Year_1_May;
    }

    public double getCurrent_Year_1_June() {
        return current_Year_1_June;
    }

    public void setCurrent_Year_1_June(double current_Year_1_June) {
        this.current_Year_1_June = current_Year_1_June;
    }

    public double getCurrent_Year_1_July() {
        return current_Year_1_July;
    }

    public void setCurrent_Year_1_July(double current_Year_1_July) {
        this.current_Year_1_July = current_Year_1_July;
    }

    public double getCurrent_Year_1_Aug() {
        return current_Year_1_Aug;
    }

    public void setCurrent_Year_1_Aug(double current_Year_1_Aug) {
        this.current_Year_1_Aug = current_Year_1_Aug;
    }

    public double getCurrent_Year_1_Sep() {
        return current_Year_1_Sep;
    }

    public void setCurrent_Year_1_Sep(double current_Year_1_Sep) {
        this.current_Year_1_Sep = current_Year_1_Sep;
    }

    public double getCurrent_Year_1_Oct() {
        return current_Year_1_Oct;
    }

    public void setCurrent_Year_1_Oct(double current_Year_1_Oct) {
        this.current_Year_1_Oct = current_Year_1_Oct;
    }

    public double getCurrent_Year_1_Nov() {
        return current_Year_1_Nov;
    }

    public void setCurrent_Year_1_Nov(double current_Year_1_Nov) {
        this.current_Year_1_Nov = current_Year_1_Nov;
    }

    public double getCurrent_Year_1_Dec() {
        return current_Year_1_Dec;
    }

    public void setCurrent_Year_1_Dec(double current_Year_1_Dec) {
        this.current_Year_1_Dec = current_Year_1_Dec;
    }

    public double getCurrent_Year_2_Jan() {
        return current_Year_2_Jan;
    }

    public void setCurrent_Year_2_Jan(double current_Year_2_Jan) {
        this.current_Year_2_Jan = current_Year_2_Jan;
    }

    public double getCurrent_Year_2_Feb() {
        return current_Year_2_Feb;
    }

    public void setCurrent_Year_2_Feb(double current_Year_2_Feb) {
        this.current_Year_2_Feb = current_Year_2_Feb;
    }

    public double getCurrent_Year_2_Mar() {
        return current_Year_2_Mar;
    }

    public void setCurrent_Year_2_Mar(double current_Year_2_Mar) {
        this.current_Year_2_Mar = current_Year_2_Mar;
    }

    public double getCurrent_Year_2_Apr() {
        return current_Year_2_Apr;
    }

    public void setCurrent_Year_2_Apr(double current_Year_2_Apr) {
        this.current_Year_2_Apr = current_Year_2_Apr;
    }

    public double getCurrent_Year_2_May() {
        return current_Year_2_May;
    }

    public void setCurrent_Year_2_May(double current_Year_2_May) {
        this.current_Year_2_May = current_Year_2_May;
    }

    public double getCurrent_Year_2_June() {
        return current_Year_2_June;
    }

    public void setCurrent_Year_2_June(double current_Year_2_June) {
        this.current_Year_2_June = current_Year_2_June;
    }

    public double getCurrent_Year_2_July() {
        return current_Year_2_July;
    }

    public void setCurrent_Year_2_July(double current_Year_2_July) {
        this.current_Year_2_July = current_Year_2_July;
    }

    public double getCurrent_Year_2_Aug() {
        return current_Year_2_Aug;
    }

    public void setCurrent_Year_2_Aug(double current_Year_2_Aug) {
        this.current_Year_2_Aug = current_Year_2_Aug;
    }

    public double getCurrent_Year_2_Sep() {
        return current_Year_2_Sep;
    }

    public void setCurrent_Year_2_Sep(double current_Year_2_Sep) {
        this.current_Year_2_Sep = current_Year_2_Sep;
    }

    public double getCurrent_Year_2_Oct() {
        return current_Year_2_Oct;
    }

    public void setCurrent_Year_2_Oct(double current_Year_2_Oct) {
        this.current_Year_2_Oct = current_Year_2_Oct;
    }

    public double getCurrent_Year_2_Nov() {
        return current_Year_2_Nov;
    }

    public void setCurrent_Year_2_Nov(double current_Year_2_Nov) {
        this.current_Year_2_Nov = current_Year_2_Nov;
    }

    public double getCurrent_Year_2_Dec() {
        return current_Year_2_Dec;
    }

    public void setCurrent_Year_2_Dec(double current_Year_2_Dec) {
        this.current_Year_2_Dec = current_Year_2_Dec;
    }

   

    public String getOutbound_Status() {
        return outbound_Status;
    }

    public void setOutbound_Status(String outbound_Status) {
        this.outbound_Status = outbound_Status;
    }

    public String getOriginal_Batch_ID() {
        return original_Batch_ID;
    }

    public void setOriginal_Batch_ID(String original_Batch_ID) {
        this.original_Batch_ID = original_Batch_ID;
    }

    public boolean isCheck_Record() {
        return check_Record;
    }

    public void setCheck_Record(boolean check_Record) {
        this.check_Record = check_Record;
    }

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(String itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(String brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public String getRsMoldelSid() {
        return rsMoldelSid;
    }

    public void setRsMoldelSid(String rsMoldelSid) {
        this.rsMoldelSid = rsMoldelSid;
    }


    public String getArpDetailssid() {
        return arpDetailssid;
    }

    public void setArpDetailssid(String arpDetailssid) {
        this.arpDetailssid = arpDetailssid;
    }

    public boolean isEtlCheckRecord() {
        return etlCheckRecord;
    }

    public void setEtlCheckRecord(boolean etlCheckRecord) {
        this.etlCheckRecord = etlCheckRecord;
    }

    public String getCheckedValue() {
        return checkedValue;
    }

    public void setCheckedValue(String checkedValue) {
        this.checkedValue = checkedValue;
    }
    
    

    public Date getArp_Creation_Date() {
        return arp_Creation_Date;
    }

    public void setArp_Creation_Date(Date arp_Creation_Date) {
        this.arp_Creation_Date = arp_Creation_Date;
    }

    public Date getArp_Approval_Date() {
        return arp_Approval_Date;
    }

    public void setArp_Approval_Date(Date arp_Approval_Date) {
        this.arp_Approval_Date = arp_Approval_Date;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getAccount_category() {
        return account_category;
    }

    public void setAccount_category(String account_category) {
        this.account_category = account_category;
    }

    public String getAccount_group() {
        return account_group;
    }

    public void setAccount_group(String account_group) {
        this.account_group = account_group;
    }


}
