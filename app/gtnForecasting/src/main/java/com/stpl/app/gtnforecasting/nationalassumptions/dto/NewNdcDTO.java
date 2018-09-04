package com.stpl.app.gtnforecasting.nationalassumptions.dto;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;


/**
 * The Class NewNdcDTO.
 */
public class NewNdcDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
        
        private String ndc9;	
	private String wac;
	private String baseYearAMP;
	private String baseYearCPI;
	private String forecastAMP;
	private String forecastBestPrice;
       
        // Federal Ndc fields
        private String nonFamp;
        private String fssOGA;
        private String itemNo;
        private int itemMasterSid=0;
        private Map<String, String> federalWacMap = new HashMap<>();

        // New Ndc Setup
        private List<String> listNDC9 = new ArrayList<>();
        private List<Integer> deletedItems = new ArrayList();
        private Map<String, String> wacHashMap = new HashMap<>();
        private Map<String, String> cpiHashMap = new HashMap<>();

        private Map<Integer, String> ndc9Map = new HashMap<>();
        private Map<Integer, String> itemMasterSidMap = new HashMap<>();
        private Map<String, String> itemNoMap = new HashMap<>();
        private Map<String, String> ampHashMap = new HashMap<>();
        private List<String> listItemNo = new ArrayList<>();
        private Map<String, String> nonFampMap = new HashMap<>();
        private Map<String, String> fssMap = new HashMap<>();
        private String indicator=StringUtils.EMPTY;
        private boolean fssFlag=false;
        private boolean federalFlag=false;
        
        private String ndcDescription=StringUtils.EMPTY;

    public NewNdcDTO() {
        super();
    }

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

  
    public String getWac() {
        return wac;
    }

    public void setWac(String wac) {
        this.wac = wac;
    }

    public String getBaseYearAMP() {
        return baseYearAMP;
    }

    public void setBaseYearAMP(String baseYearAMP) {
        this.baseYearAMP = baseYearAMP;
    }

    public String getBaseYearCPI() {
        return baseYearCPI;
    }

    public void setBaseYearCPI(String baseYearCPI) {
        this.baseYearCPI = baseYearCPI;
    }

    public String getForecastAMP() {
        return forecastAMP;
    }

    public void setForecastAMP(String forecastAMP) {
        this.forecastAMP = forecastAMP;
    }

    public String getForecastBestPrice() {
        return forecastBestPrice;
    }

    public void setForecastBestPrice(String forecastBestPrice) {
        this.forecastBestPrice = forecastBestPrice;
    }

    public String getNonFamp() {
        return nonFamp;
    }

    public void setNonFamp(String nonFamp) {
        this.nonFamp = nonFamp;
    }

    public String getFssOGA() {
        return fssOGA;
    }

    public void setFssOGA(String fssOGA) {
        this.fssOGA = fssOGA;
    }

    public List<String> getListNDC9() {
        return listNDC9 == null ? listNDC9 : new ArrayList<>(listNDC9);
    }

    public void setListNDC9(List<String> listNDC9) {
        this.listNDC9 = listNDC9 == null ? listNDC9 : new ArrayList<>(listNDC9);
    }

    public Map<String, String> getWacHashMap() {
        return wacHashMap;
    }

    public void setWacHashMap(Map<String, String> wacHashMap) {
        this.wacHashMap = wacHashMap;
    }

    public Map<String, String> getCpiHashMap() {
        return cpiHashMap;
    }

    public void setCpiHashMap(Map<String, String> cpiHashMap) {
        this.cpiHashMap = cpiHashMap;
    }

    public Map<Integer, String> getNdc9Map() {
        return ndc9Map;
    }

    public void setNdc9Map(Map<Integer, String> ndc9Map) {
        this.ndc9Map = ndc9Map;
    }

    public Map<Integer, String> getItemMasterSidMap() {
        return itemMasterSidMap;
    }

    public void setItemMasterSidMap(Map<Integer, String> itemMasterSidMap) {
        this.itemMasterSidMap = itemMasterSidMap;
    }

    public Map<String, String> getItemNoMap() {
        return itemNoMap;
    }

    public void setItemNoMap(Map<String, String> itemNoMap) {
        this.itemNoMap = itemNoMap;
    }

    public Map<String, String> getAmpHashMap() {
        return ampHashMap;
    }

    public void setAmpHashMap(Map<String, String> ampHashMap) {
        this.ampHashMap = ampHashMap;
    }

    public Map<String, String> getNonFampMap() {
        return nonFampMap;
    }

    public void setNonFampMap(Map<String, String> nonFampMap) {
        this.nonFampMap = nonFampMap;
    }

    public Map<String, String> getFssMap() {
        return fssMap;
    }

    public void setFssMap(Map<String, String> fssMap) {
        this.fssMap = fssMap;
    }

    public List<String> getListItemNo() {
        return listItemNo == null ? listItemNo : new ArrayList<>(listItemNo);
    }

    public void setListItemNo(List<String> listItemNo) {
        this.listItemNo = listItemNo == null ? listItemNo : new ArrayList<>(listItemNo);
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public boolean isFssFlag() {
        return fssFlag;
    }

    public void setFssFlag(boolean fssFlag) {
        this.fssFlag = fssFlag;
    }

    public boolean isFederalFlag() {
        return federalFlag;
    }

    public void setFederalFlag(boolean federalFlag) {
        this.federalFlag = federalFlag;
    }

    public Map<String, String> getFederalWacMap() {
        return federalWacMap;
    }

    public void setFederalWacMap(Map<String, String> federalWacMap) {
        this.federalWacMap = federalWacMap;
    }

    public String getNdc9() {
        return ndc9;
    }

    public void setNdc9(String ndc9) {
        this.ndc9 = ndc9;
    }

    public String getNdcDescription() {
        return ndcDescription;
    }

    public void setNdcDescription(String ndcDescription) {
        this.ndcDescription = ndcDescription;
    }

    public List<Integer> getDeletedItems() {
        return deletedItems == null ? deletedItems : new ArrayList<>(deletedItems) ;
    }

    public void setDeletedItems(List<Integer> deletedItems) {
        this.deletedItems = deletedItems == null ? deletedItems : new ArrayList<>(deletedItems);
    }

    }
