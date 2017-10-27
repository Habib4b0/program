package com.stpl.app.global.item.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.stpl.app.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.stpl.app.util.HelperUtils;

// TODO: Auto-generated Javadoc
/**
 * A DTO object which is used as a form object in create user and edit user
 * forms.
 * @author arulmurugan
 */

public class ItemMasterDTO implements Serializable  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8654350605119231823L;
	
	/** The item system id. */
	private String itemSystemId;
        
	/** The item id. */
	@NotBlank(message = "Item Id should  be present")
	private String itemId=StringUtils.EMPTY;
	
	/** The item no. */
	@NotBlank(message = "Item No should  be present")
	private String itemNo=StringUtils.EMPTY;
	
	/** The item code. */
	private String itemCode=StringUtils.EMPTY;
	
	/** The item name. */
	@NotBlank(message = "Item Name should  be present")
	private String itemName=StringUtils.EMPTY; 
	
	/** The item desc. */
	private String itemDesc=StringUtils.EMPTY;
	
	/** The package size. */
	private HelperDTO packageSize= new HelperDTO(0, Constants.SELECT_ONE);
        
        /** The package size. */
	private String displayPackageSize = StringUtils.EMPTY;
	
	/** The package size code. */
	private String packageSizeCode=StringUtils.EMPTY;
	
	/** The package size intro date. */
	private Date packageSizeIntroDate;
	
	/** The upps. */
	private String upps=StringUtils.EMPTY;
	
	/** The item start date. */
	@NotBlank(message = "Item Start Date should  be present")
	private Date itemStartDate;
	
	/** The item end date. */
	@NotBlank(message = "Item End Date should  be present")
	private Date itemEndDate;
	
	/** The item status. */
	private HelperDTO itemStatus = new HelperDTO(0, Constants.SELECT_ONE);

	/** The labeler code. */
	private String labelerCode=StringUtils.EMPTY;
	
	/** The organization key. */
	private int organizationKey;
	
	/** The acquisition date. */
	private Date acquisitionDate;
	
	/** The authorized generic. */
	private String authorizedGeneric;
	
	/** The authorized generic start date. */
	private Date authorizedGenericStartDate;
	
	/** The authorized generic end date. */
	private Date authorizedGenericEndDate;
	
	/** The form. */
	private HelperDTO form= new HelperDTO(0, Constants.SELECT_ONE);
        
        /** The displayForm. */
	private String displayForm = StringUtils.EMPTY;
        
        private String formStr = "";
	
	/** The strength. */
	private HelperDTO strength = new HelperDTO(0, Constants.SELECT_ONE);
	
	/** Therapeutic class. */
	private HelperDTO therapeuticClass= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The first sale date. */
	private Date firstSaleDate;
	
	/** The item type indication. */
	private HelperDTO itemTypeIndication= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The item class. */
	private HelperDTO itemClass= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The item type. */
	private HelperDTO itemType= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The market termination date. */
	private Date marketTerminationDate;
	
	/** The new formulation indicator. */
	private String newFormulationIndicator=StringUtils.EMPTY;
	
	/** The new formulation. */
	private String newFormulation =StringUtils.EMPTY;
	
	/** The new formulation start date. */
	private Date newFormulationStartDate;
	
	/** The new formulation end date. */
	private Date newFormulationEndDate;
	
	/** The pediatric exclusive indicator. */
	private String pediatricExclusiveIndicator;
	
	/** The pediatric exclusive start date. */
	private Date pediatricExclusiveStartDate;
	
	/** The pediatric exclusive end date. */
	private Date pediatricExclusiveEndDate;
	
	/** The clotting factor indicator. */
	private String clottingFactorIndicator;
	
	/** The clotting factor start date. */
	private Date clottingFactorStartDate;
	
	/** The clotting factor end date. */
	private Date clottingFactorEndDate;
	
	/** The primary uom. */
	private HelperDTO primaryUom= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The secondary uom. */
	private HelperDTO secondaryUom= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The shelf life. */
	private String shelfLife = StringUtils.EMPTY;
	
	/** The shelf life type. */
	private HelperDTO shelfLifeType= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The dual pricing indicator. */
	private String dualPricingIndicator;
	
	/** The item family id. */
	private String itemFamilyId;
	
	/** The udc1. */
	private HelperDTO udc1= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The item indicator. */
	private boolean itemIndicator;
	
	/** The udc2. */
	private HelperDTO udc2= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The udc3. */
	private HelperDTO udc3= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The udc4. */
	private HelperDTO udc4= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The udc5. */
	private HelperDTO udc5= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The udc6. */
	private HelperDTO udc6= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The display brand. */
	private String displayBrand;
	
	/** The item category. */
        private HelperDTO itemCategory= new HelperDTO(0, Constants.SELECT_ONE);
	
	/** The manufacturer id. */
private String manufacturerId=StringUtils.EMPTY;
	
	/** The manufacturer no. */
	private String manufacturerNo= StringUtils.EMPTY;
	
	/** The manufacturer name. */
	private String manufacturerName= StringUtils.EMPTY;
	
	/** The acquired amp. */
	private String acquiredAmp = StringUtils.EMPTY;
	
	/** The acquired bamp. */
	private String acquiredBamp = StringUtils.EMPTY;
	
	/** The obra bamp. */
	private String obraBamp = StringUtils.EMPTY;
	
	/** The dra. */
	private String dra = StringUtils.EMPTY;
	
	
	/** The baseline amp. */
	private String baselineAmp = StringUtils.EMPTY;
	
	/** The base cpi period. */
	private Date baseCpiPeriod;
	
	/** The base cpi. */
	private String baseCpi = StringUtils.EMPTY;
	
	

	
	/** The created by. */
	private String createdBy= StringUtils.EMPTY;
	
	/** The created date. */
	private Date createdDate;
	
	/** The modified by. */
	private String modifiedBy= StringUtils.EMPTY;
	
	/** The modified date. */
	private Date modifiedDate;
	
	/** The batch id. */
	private String batchId;
	
	/** The brand. */
	private String brand;
	
	/** The doses per unit. */
	private String dosesPerUnit = StringUtils.EMPTY;
	
	/** The inbound status. */
	private Character inboundStatus;
	
	/** The item identifier list. */
	private List<ItemIrtIdentifierDTO> itemIdentifierList=new ArrayList<>();
	
	/** The pricing identifier list. */
	private List<ItemPricingDTO> pricingIdentifierList=new ArrayList<>();
	
	/** The record lock status. */
	private String recordLockStatus;
	
	/** The ndc8. */
	private String ndc8=StringUtils.EMPTY;
	
	/** The ndc9. */
	private String ndc9=StringUtils.EMPTY;
	
	/** The brand id. */
	private String brandId=StringUtils.EMPTY;
	private String displayItemStatus= StringUtils.EMPTY;
	
    /** The discontinuation date. */
    private Date discontinuationDate;
    
    /** The last lot expiration date. */
    private Date lastLotExpirationDate;
    
    /** The divestiture date. */
    private Date divestitureDate;
    
    private int baselineAmpPrecision;
      private int baseCpiPrecision;
    
    /** The non federal expiration date. */
    private Date nonFederalExpirationDate;
    
    /** The unique date. */
    private String uniqueDate;
	
	/** The item irt qualifier name. */
	private com.stpl.app.util.HelperDTO itemIrtQualifierName;
	
	/** The item identifier. */
	private String itemIdentifier=StringUtils.EMPTY;

    	/** The manufacturer id. */
private com.stpl.app.util.HelperDTO manufacturerIdDDLB;

        /** The brand. */
	private com.stpl.app.util.HelperDTO brandDdlb;
        
        /**
     * The company system id.
     */
    private String SystemId = HelperUtils.EMPTY;

           /**
     * The internal notes.
     */
    private String internalNotes = StringUtils.EMPTY;
    private String itemBatchId = StringUtils.EMPTY;

    public com.stpl.app.util.HelperDTO getItemIrtQualifierName() {
        return itemIrtQualifierName;
    }

    public void setItemIrtQualifierName(com.stpl.app.util.HelperDTO itemIrtQualifierName) {
        this.itemIrtQualifierName = itemIrtQualifierName;
    }
        
     
	
	/**
	 * Gets the item identifier.
	 *
	 * @return the item identifier
	 */
	public String getItemIdentifier() {
		return itemIdentifier;
	}
	
	/**
	 * Sets the item identifier.
	 *
	 * @param itemIdentifier the item identifier
	 */
	public void setItemIdentifier(final String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}
	
	/**
	 * Gets the manufacturer no.
	 *
	 * @return the manufacturer no
	 */
	public String getManufacturerNo() {
		return manufacturerNo;
	}
	
	/**
	 * Sets the manufacturer no.
	 *
	 * @param manufacturerNo the manufacturer no
	 */
	public void setManufacturerNo(final String manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
	}
	
	/**
	 * Gets the manufacturer name.
	 *
	 * @return the manufacturer name
	 */
	public String getManufacturerName() {
		return manufacturerName;
	}
	
	/**
	 * Sets the manufacturer name.
	 *
	 * @param manufacturerName the manufacturer name
	 */
	public void setManufacturerName(final String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	
	/**
	 * Gets the unique date.
	 *
	 * @return the unique date
	 */
	public String getUniqueDate() {
		return uniqueDate;
	}
	
	/**
	 * Sets the unique date.
	 *
	 * @param uniqueDate the unique date
	 */
	public void setUniqueDate(final String uniqueDate) {
		this.uniqueDate = uniqueDate;
	}
	
	/**
	 * Gets the package size code.
	 *
	 * @return the package size code
	 */
	public String getPackageSizeCode() {
		return packageSizeCode;
	}
	
	/**
	 * Sets the package size code.
	 *
	 * @param packageSizeCode the package size code
	 */
	public void setPackageSizeCode(final String packageSizeCode) {
		this.packageSizeCode = packageSizeCode;
	}
	/**
	 * Gets the baseline amp.
	 *
	 * @return the baseline amp
	 */
	public String getBaselineAmp() {
		return baselineAmp;
	}
	
	/**
	 * Sets the baseline amp.
	 *
	 * @param baselineAmp the baseline amp
	 */
	public void setBaselineAmp(final String baselineAmp) {
		this.baselineAmp = baselineAmp;
	}
	
	/**
	 * Gets the base cpi period.
	 *
	 * @return the base cpi period
	 */
	public Date getBaseCpiPeriod() {
		return baseCpiPeriod;
	}
	
	/**
	 * Sets the base cpi period.
	 *
	 * @param baseCpiPeriod the base cpi period
	 */
	public void setBaseCpiPeriod(final Date baseCpiPeriod) {
		this.baseCpiPeriod = baseCpiPeriod;
	}
	
	/**
	 * Gets the base cpi.
	 *
	 * @return the base cpi
	 */
	public String getBaseCpi() {
		return baseCpi;
	}
	
	/**
	 * Sets the base cpi.
	 *
	 * @param baseCpi the base cpi
	 */
	public void setBaseCpi(final String baseCpi) {
		this.baseCpi = baseCpi;
	}
	
	/**
	 * Gets the non federal expiration date.
	 *
	 * @return the non federal expiration date
	 */
	public Date getNonFederalExpirationDate() {
		return nonFederalExpirationDate;
	}
	
	/**
	 * Sets the non federal expiration date.
	 *
	 * @param nonFederalExpirationDate the non federal expiration date
	 */
	public void setNonFederalExpirationDate(final Date nonFederalExpirationDate) {
		this.nonFederalExpirationDate = nonFederalExpirationDate;
	}
	
	/**
	 * Gets the divestiture date.
	 *
	 * @return the divestiture date
	 */
	public Date getDivestitureDate() {
		return divestitureDate;
	}
	
	/**
	 * Sets the divestiture date.
	 *
	 * @param divestitureDate the divestiture date
	 */
	public void setDivestitureDate(final Date divestitureDate) {
		this.divestitureDate = divestitureDate;
	}
	
	/**
	 * Gets the last lot expiration date.
	 *
	 * @return the last lot expiration date
	 */
	public Date getLastLotExpirationDate() {
		return lastLotExpirationDate;
	}
	
	/**
	 * Sets the last lot expiration date.
	 *
	 * @param lastLotExpirationDate the last lot expiration date
	 */
	public void setLastLotExpirationDate(final Date lastLotExpirationDate) {
		this.lastLotExpirationDate = lastLotExpirationDate;
	}
	
	/**
	 * Gets the discontinuation date.
	 *
	 * @return the discontinuation date
	 */
	public Date getDiscontinuationDate() {
		return discontinuationDate;
	}
	
	/**
	 * Sets the discontinuation date.
	 *
	 * @param discontinuationDate the discontinuation date
	 */
	public void setDiscontinuationDate(final Date discontinuationDate) {
		this.discontinuationDate = discontinuationDate;
	}
	
	/**
	 * Gets the display brand.
	 *
	 * @return the display brand
	 */
	public String getDisplayBrand() {
		return displayBrand;
	}
	
	/**
	 * Sets the display brand.
	 *
	 * @param displayBrand the display brand
	 */
	public void setDisplayBrand(final String displayBrand) {
		this.displayBrand = displayBrand;
	}
	
		
	/**
	 * Gets the acquired amp.
	 *
	 * @return the acquired amp
	 */
	public String getAcquiredAmp() {
			return acquiredAmp;
		}
	
	/**
	 * Sets the acquired amp.
	 *
	 * @param acquiredAmp the acquired amp
	 */
	public void setAcquiredAmp(final String acquiredAmp) {
		this.acquiredAmp = acquiredAmp;
	}
	
	/**
	 * Gets the acquired bamp.
	 *
	 * @return the acquired bamp
	 */
	public String getAcquiredBamp() {
		return acquiredBamp;
	}
	
	/**
	 * Sets the acquired bamp.
	 *
	 * @param acquiredBamp the acquired bamp
	 */
	public void setAcquiredBamp(final String acquiredBamp) {
		this.acquiredBamp = acquiredBamp;
	}
	
	/**
	 * Gets the obra bamp.
	 *
	 * @return the obra bamp
	 */
	public String getObraBamp() {
		return obraBamp;
	}
	
	/**
	 * Sets the obra bamp.
	 *
	 * @param obraBamp the obra bamp
	 */
	public void setObraBamp(final String obraBamp) {
		this.obraBamp = obraBamp;
	}
	
	/**
	 * Gets the dra.
	 *
	 * @return the dra
	 */
	public String getDra() {
		return dra;
	}
	
	/**
	 * Sets the dra.
	 *
	 * @param dra the dra
	 */
	public void setDra(final String dra) {
		this.dra = dra;
	}
	
	/**
	 * Gets the item system id.
	 *
	 * @return the item system id
	 */
	public String getItemSystemId() {
		return itemSystemId;
	}
	
	/**
	 * Sets the item system id.
	 *
	 * @param itemSystemId the item system id
	 */
	public void setItemSystemId(final String itemSystemId) {
		this.itemSystemId = itemSystemId;
	}
	
	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public String getItemId() {
		return itemId;
	}
	
	/**
	 * Sets the item id.
	 *
	 * @param itemId the item id
	 */
	public void setItemId(final String itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * Gets the item no.
	 *
	 * @return the item no
	 */
	public String getItemNo() {
		return itemNo;
	}
	
	/**
	 * Sets the item no.
	 *
	 * @param itemNo the item no
	 */
	public void setItemNo(final String itemNo) {
		this.itemNo = itemNo;
	}
	
	/**
	 * Gets the item code.
	 *
	 * @return the item code
	 */
	public String getItemCode() {
		return itemCode;
	}
	
	/**
	 * Sets the item code.
	 *
	 * @param itemCode the item code
	 */
	public void setItemCode(final String itemCode) {
		this.itemCode = itemCode;
	}
	
	/**
	 * Gets the item name.
	 *
	 * @return the item name
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Sets the item name.
	 *
	 * @param itemName the item name
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * Gets the item desc.
	 *
	 * @return the item desc
	 */
	public String getItemDesc() {
		return itemDesc;
	}
	
	/**
	 * Sets the item desc.
	 *
	 * @param itemDesc the item desc
	 */
	public void setItemDesc(final String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
		
	/**
	 * Gets the package size intro date.
	 *
	 * @return the package size intro date
	 */
	public Date getPackageSizeIntroDate() {
		return packageSizeIntroDate;
	}
	
	/**
	 * Sets the package size intro date.
	 *
	 * @param packageSizeIntroDate the package size intro date
	 */
	public void setPackageSizeIntroDate(final Date packageSizeIntroDate) {
		this.packageSizeIntroDate = packageSizeIntroDate;
	}
	
	
	/**
	 * Gets the upps.
	 *
	 * @return the upps
	 */
	public String getUpps() {
		return upps;
	}
	
	/**
	 * Sets the upps.
	 *
	 * @param upps the upps
	 */
	public void setUpps(final String upps) {
		this.upps = upps;
	}
	
	/**
	 * Gets the item start date.
	 *
	 * @return the item start date
	 */
	public Date getItemStartDate() {
		return itemStartDate;
	}
	
	/**
	 * Sets the item start date.
	 *
	 * @param itemStartDate the item start date
	 */
	public void setItemStartDate(final Date itemStartDate) {
		this.itemStartDate = itemStartDate;
	}
	
	/**
	 * Gets the item end date.
	 *
	 * @return the item end date
	 */
	public Date getItemEndDate() {
		return itemEndDate;
	}
	
	/**
	 * Sets the item end date.
	 *
	 * @param itemEndDate the item end date
	 */
	public void setItemEndDate(final Date itemEndDate) {
		this.itemEndDate = itemEndDate;
	}
	

	/**
	 * Gets the manufacturer id.
	 *
	 * @return the manufacturer id
	 */
	public String getManufacturerId() {
		return manufacturerId;
	}
	
	/**
	 * Sets the manufacturer id.
	 *
	 * @param manufacturerId the manufacturer id
	 */
	public void setManufacturerId(final String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	
	/**
	 * Gets the labeler code.
	 *
	 * @return the labeler code
	 */
	public String getLabelerCode() {
		return labelerCode;
	}
	
	/**
	 * Sets the labeler code.
	 *
	 * @param labelerCode the labeler code
	 */
	public void setLabelerCode(final String labelerCode) {
		this.labelerCode = labelerCode;
	}
	
	/**
	 * Gets the organization key.
	 *
	 * @return the organization key
	 */
	public int getOrganizationKey() {
		return organizationKey;
	}
	
	/**
	 * Sets the organization key.
	 *
	 * @param organizationKey the organization key
	 */
	public void setOrganizationKey(final int organizationKey) {
		this.organizationKey = organizationKey;
	}
	
	/**
	 * Gets the acquisition date.
	 *
	 * @return the acquisition date
	 */
	public Date getAcquisitionDate() {
		return acquisitionDate;
	}
	
	/**
	 * Sets the acquisition date.
	 *
	 * @param acquisitionDate the acquisition date
	 */
	public void setAcquisitionDate(final Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
	
	/**
	 * Gets the authorized generic.
	 *
	 * @return the authorized generic
	 */
	public String getAuthorizedGeneric() {
		return authorizedGeneric;
	}
	
	/**
	 * Sets the authorized generic.
	 *
	 * @param authorizedGeneric the authorized generic
	 */
	public void setAuthorizedGeneric(final String authorizedGeneric) {
		this.authorizedGeneric = authorizedGeneric;
	}
	
	/**
	 * Gets the authorized generic start date.
	 *
	 * @return the authorized generic start date
	 */
	public Date getAuthorizedGenericStartDate() {
		return authorizedGenericStartDate;
	}
	
	/**
	 * Sets the authorized generic start date.
	 *
	 * @param authorizedGenericStartDate the authorized generic start date
	 */
	public void setAuthorizedGenericStartDate(final Date authorizedGenericStartDate) {
		this.authorizedGenericStartDate = authorizedGenericStartDate;
	}
	
	/**
	 * Gets the authorized generic end date.
	 *
	 * @return the authorized generic end date
	 */
	public Date getAuthorizedGenericEndDate() {
		return authorizedGenericEndDate;
	}
	
	/**
	 * Sets the authorized generic end date.
	 *
	 * @param authorizedGenericEndDate the authorized generic end date
	 */
	public void setAuthorizedGenericEndDate(final Date authorizedGenericEndDate) {
		this.authorizedGenericEndDate = authorizedGenericEndDate;
	}
	
	
	/**
	 * Gets the first sale date.
	 *
	 * @return the first sale date
	 */
	public Date getFirstSaleDate() {
		return firstSaleDate;
	}
	
	/**
	 * Sets the first sale date.
	 *
	 * @param firstSaleDate the first sale date
	 */
	public void setFirstSaleDate(final Date firstSaleDate) {
		this.firstSaleDate = firstSaleDate;
	}
		
	/**
	 * Gets the market termination date.
	 *
	 * @return the market termination date
	 */
	public Date getMarketTerminationDate() {
		return marketTerminationDate;
	}
	
	/**
	 * Sets the market termination date.
	 *
	 * @param marketTerminationDate the market termination date
	 */
	public void setMarketTerminationDate(final Date marketTerminationDate) {
		this.marketTerminationDate = marketTerminationDate;
	}
	
	/**
	 * Gets the new formulation indicator.
	 *
	 * @return the new formulation indicator
	 */
	public String getNewFormulationIndicator() {
		return newFormulationIndicator;
	}
	
	/**
	 * Sets the new formulation indicator.
	 *
	 * @param newFormulationIndicator the new formulation indicator
	 */
	public void setNewFormulationIndicator(final String newFormulationIndicator) {
		this.newFormulationIndicator = newFormulationIndicator;
	}
	
	/**
	 * Gets the new formulation.
	 *
	 * @return the new formulation
	 */
	public String getNewFormulation() {
		return newFormulation;
	}
	
	/**
	 * Sets the new formulation.
	 *
	 * @param newFormulation the new formulation
	 */
	public void setNewFormulation(final String newFormulation) {
		this.newFormulation = newFormulation;
	}
	
	/**
	 * Gets the new formulation start date.
	 *
	 * @return the new formulation start date
	 */
	public Date getNewFormulationStartDate() {
		return newFormulationStartDate;
	}
	
	/**
	 * Sets the new formulation start date.
	 *
	 * @param newFormulationStartDate the new formulation start date
	 */
	public void setNewFormulationStartDate(final Date newFormulationStartDate) {
		this.newFormulationStartDate = newFormulationStartDate;
	}
	
	/**
	 * Gets the new formulation end date.
	 *
	 * @return the new formulation end date
	 */
	public Date getNewFormulationEndDate() {
		return newFormulationEndDate;
	}
	
	/**
	 * Sets the new formulation end date.
	 *
	 * @param newFormulationEndDate the new formulation end date
	 */
	public void setNewFormulationEndDate(final Date newFormulationEndDate) {
		this.newFormulationEndDate = newFormulationEndDate;
	}
	
	/**
	 * Gets the pediatric exclusive indicator.
	 *
	 * @return the pediatric exclusive indicator
	 */
	public String getPediatricExclusiveIndicator() {
		return pediatricExclusiveIndicator;
	}
	
	/**
	 * Sets the pediatric exclusive indicator.
	 *
	 * @param pediatricExclusiveIndicator the pediatric exclusive indicator
	 */
	public void setPediatricExclusiveIndicator(final String pediatricExclusiveIndicator) {
		this.pediatricExclusiveIndicator = pediatricExclusiveIndicator;
	}
	
	/**
	 * Gets the clotting factor indicator.
	 *
	 * @return the clotting factor indicator
	 */
	public String getClottingFactorIndicator() {
		return clottingFactorIndicator;
	}
	
	/**
	 * Sets the clotting factor indicator.
	 *
	 * @param clottingFactorIndicator the clotting factor indicator
	 */
	public void setClottingFactorIndicator(final String clottingFactorIndicator) {
		this.clottingFactorIndicator = clottingFactorIndicator;
	}
	
	/**
	 * Gets the shelf life.
	 *
	 * @return the shelf life
	 */
	public String getShelfLife() {
		return shelfLife;
	}
	
	/**
	 * Sets the shelf life.
	 *
	 * @param shelfLife the shelf life
	 */
	public void setShelfLife(final String shelfLife) {
		this.shelfLife = shelfLife;
	}
			
	/**
	 * Gets the dual pricing indicator.
	 *
	 * @return the dual pricing indicator
	 */
	public String getDualPricingIndicator() {
		return dualPricingIndicator;
	}
	
	/**
	 * Sets the dual pricing indicator.
	 *
	 * @param dualPricingIndicator the dual pricing indicator
	 */
	public void setDualPricingIndicator(final String dualPricingIndicator) {
		this.dualPricingIndicator = dualPricingIndicator;
	}
	
	/**
	 * Gets the item family id.
	 *
	 * @return the item family id
	 */
	public String getItemFamilyId() {
		return itemFamilyId;
	}
	
	/**
	 * Sets the item family id.
	 *
	 * @param itemFamilyId the item family id
	 */
	public void setItemFamilyId(final String itemFamilyId) {
		this.itemFamilyId = itemFamilyId;
	}
	
	
	/**
	 * Checks if is item indicator.
	 *
	 * @return true, if checks if is item indicator
	 */
	public boolean isItemIndicator() {
		return itemIndicator;
	}
	
	/**
	 * Sets the item indicator.
	 *
	 * @param itemIndicator the item indicator
	 */
	public void setItemIndicator(final boolean itemIndicator) {
		this.itemIndicator = itemIndicator;
	}
			
	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param createdBy the created by
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * Sets the created date.
	 *
	 * @param createdDate the created date
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the modified by
	 */
	public void setModifiedBy(final String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate the modified date
	 */
	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * Gets the batch id.
	 *
	 * @return the batch id
	 */
	public String getBatchId() {
		return batchId;
	}
	
	/**
	 * Sets the batch id.
	 *
	 * @param batchId the batch id
	 */
	public void setBatchId(final String batchId) {
		this.batchId = batchId;
	}
	
	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * Sets the brand.
	 *
	 * @param brand the brand
	 */
	public void setBrand(final String brand) {
		this.brand = brand;
	}
	
	/**
	 * Gets the doses per unit.
	 *
	 * @return the doses per unit
	 */
	public String getDosesPerUnit() {
		return dosesPerUnit;
	}
	
	/**
	 * Sets the doses per unit.
	 *
	 * @param dosesPerUnit the doses per unit
	 */
	public void setDosesPerUnit(final String dosesPerUnit) {
		this.dosesPerUnit = dosesPerUnit;
	}
	
	/**
	 * Gets the inbound status.
	 *
	 * @return the inbound status
	 */
	public Character getInboundStatus() {
		return inboundStatus;
	}
	
	/**
	 * Sets the inbound status.
	 *
	 * @param inboundStatus the inbound status
	 */
	public void setInboundStatus(final Character inboundStatus) {
		this.inboundStatus = inboundStatus;
	}
	
	/**
	 * Gets the item identifier list.
	 *
	 * @return the item identifier list
	 */
	public List<ItemIrtIdentifierDTO> getItemIdentifierList() {
		return itemIdentifierList;
	}
	
	/**
	 * Sets the item identifier list.
	 *
	 * @param itemIdentifierList the item identifier list
	 */
	public void setItemIdentifierList(final List<ItemIrtIdentifierDTO> itemIdentifierList) {
		this.itemIdentifierList = itemIdentifierList;
	}
	
	/**
	 * Gets the pricing identifier list.
	 *
	 * @return the pricing identifier list
	 */
	public List<ItemPricingDTO> getPricingIdentifierList() {
		return pricingIdentifierList;
	}
	
	/**
	 * Sets the pricing identifier list.
	 *
	 * @param pricingIdentifierList the pricing identifier list
	 */
	public void setPricingIdentifierList(final List<ItemPricingDTO> pricingIdentifierList) {
		this.pricingIdentifierList = pricingIdentifierList;
	}
	
	/**
	 * Gets the record lock status.
	 *
	 * @return the record lock status
	 */
	public String getRecordLockStatus() {
		return recordLockStatus;
	}
	
	/**
	 * Sets the record lock status.
	 *
	 * @param recordLockStatus the record lock status
	 */
	public void setRecordLockStatus(final String recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}
	
	/**
	 * Gets the pediatric exclusive start date.
	 *
	 * @return the pediatric exclusive start date
	 */
	public Date getPediatricExclusiveStartDate() {
		return pediatricExclusiveStartDate;
	}
	
	/**
	 * Sets the pediatric exclusive start date.
	 *
	 * @param pediatricExclusiveStartDate the pediatric exclusive start date
	 */
	public void setPediatricExclusiveStartDate(final Date pediatricExclusiveStartDate) {
		this.pediatricExclusiveStartDate = pediatricExclusiveStartDate;
	}
	
	/**
	 * Gets the pediatric exclusive end date.
	 *
	 * @return the pediatric exclusive end date
	 */
	public Date getPediatricExclusiveEndDate() {
		return pediatricExclusiveEndDate;
	}
	
	/**
	 * Sets the pediatric exclusive end date.
	 *
	 * @param pediatricExclusiveEndDate the pediatric exclusive end date
	 */
	public void setPediatricExclusiveEndDate(final Date pediatricExclusiveEndDate) {
		this.pediatricExclusiveEndDate = pediatricExclusiveEndDate;
	}
	
	/**
	 * Gets the clotting factor start date.
	 *
	 * @return the clotting factor start date
	 */
	public Date getClottingFactorStartDate() {
		return clottingFactorStartDate;
	}
	
	/**
	 * Sets the clotting factor start date.
	 *
	 * @param clottingFactorStartDate the clotting factor start date
	 */
	public void setClottingFactorStartDate(final Date clottingFactorStartDate) {
		this.clottingFactorStartDate = clottingFactorStartDate;
	}
	
	/**
	 * Gets the clotting factor end date.
	 *
	 * @return the clotting factor end date
	 */
	public Date getClottingFactorEndDate() {
		return clottingFactorEndDate;
	}
	
	/**
	 * Sets the clotting factor end date.
	 *
	 * @param clottingFactorEndDate the clotting factor end date
	 */
	public void setClottingFactorEndDate(final Date clottingFactorEndDate) {
		this.clottingFactorEndDate = clottingFactorEndDate;
	}
	
	/**
	 * Gets the ndc8.
	 *
	 * @return the ndc8
	 */
	public String getNdc8() {
		return ndc8;
	}
	
	/**
	 * Sets the ndc8.
	 *
	 * @param ndc8 the ndc8
	 */
	public void setNdc8(final String ndc8) {
		this.ndc8 = ndc8;
	}
	
	/**
	 * Gets the ndc9.
	 *
	 * @return the ndc9
	 */
	public String getNdc9() {
		return ndc9;
	}
	
	/**
	 * Sets the ndc9.
	 *
	 * @param ndc9 the ndc9
	 */
	public void setNdc9(final String ndc9) {
		this.ndc9 = ndc9;
	}
	
	/**
	 * Gets the brand id.
	 *
	 * @return the brand id
	 */
	public String getBrandId() {
		return brandId;
	}
	
	/**
	 * Sets the brand id.
	 *
	 * @param brandId the brand id
	 */
	public void setBrandId(final String brandId) {
		this.brandId = brandId;
	}

 
    public String getFormStr() {
        return formStr;
    }

    public void setFormStr(String formStr) {
        this.formStr = formStr;
    }

    public String getDisplayItemStatus() {
        return displayItemStatus;
    }

    public void setDisplayItemStatus(String displayItemStatus) {
        this.displayItemStatus = displayItemStatus;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public String getDisplayPackageSize() {
        return displayPackageSize;
    }

    public void setDisplayPackageSize(String displayPackageSize) {
        this.displayPackageSize = displayPackageSize;
    }

    public String getDisplayForm() {
        return displayForm;
    }

    public void setDisplayForm(String displayForm) {
        this.displayForm = displayForm;
    }

    public String getSystemId() {
        return SystemId;
    }

    public void setSystemId(String SystemId) {
        this.SystemId = SystemId;
    }

    public HelperDTO getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(HelperDTO packageSize) {
        this.packageSize = packageSize;
    }

    public HelperDTO getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(HelperDTO itemStatus) {
        this.itemStatus = itemStatus;
    }

    public HelperDTO getForm() {
        return form;
    }

    public void setForm(HelperDTO form) {
        this.form = form;
    }

    public HelperDTO getStrength() {
        return strength;
    }

    public void setStrength(HelperDTO strength) {
        this.strength = strength;
    }

    public HelperDTO getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(HelperDTO therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public HelperDTO getItemTypeIndication() {
        return itemTypeIndication;
    }

    public void setItemTypeIndication(HelperDTO itemTypeIndication) {
        this.itemTypeIndication = itemTypeIndication;
    }

    public HelperDTO getItemClass() {
        return itemClass;
    }

    public void setItemClass(HelperDTO itemClass) {
        this.itemClass = itemClass;
    }

    public HelperDTO getItemType() {
        return itemType;
    }

    public void setItemType(HelperDTO itemType) {
        this.itemType = itemType;
    }

    public HelperDTO getPrimaryUom() {
        return primaryUom;
    }

    public void setPrimaryUom(HelperDTO primaryUom) {
        this.primaryUom = primaryUom;
    }

    public HelperDTO getSecondaryUom() {
        return secondaryUom;
    }

    public void setSecondaryUom(HelperDTO secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    public HelperDTO getShelfLifeType() {
        return shelfLifeType;
    }

    public void setShelfLifeType(HelperDTO shelfLifeType) {
        this.shelfLifeType = shelfLifeType;
    }

    public HelperDTO getUdc1() {
        return udc1;
    }

    public void setUdc1(HelperDTO udc1) {
        this.udc1 = udc1;
    }

    public HelperDTO getUdc2() {
        return udc2;
    }

    public void setUdc2(HelperDTO udc2) {
        this.udc2 = udc2;
    }

    public HelperDTO getUdc3() {
        return udc3;
    }

    public void setUdc3(HelperDTO udc3) {
        this.udc3 = udc3;
    }

    public HelperDTO getUdc4() {
        return udc4;
    }

    public void setUdc4(HelperDTO udc4) {
        this.udc4 = udc4;
    }

    public HelperDTO getUdc5() {
        return udc5;
    }

    public void setUdc5(HelperDTO udc5) {
        this.udc5 = udc5;
    }

    public HelperDTO getUdc6() {
        return udc6;
    }

    public void setUdc6(HelperDTO udc6) {
        this.udc6 = udc6;
    }

    public com.stpl.app.util.HelperDTO getManufacturerIdDDLB() {
        return manufacturerIdDDLB;
    }

    public void setManufacturerIdDDLB(com.stpl.app.util.HelperDTO manufacturerIdDDLB) {
        this.manufacturerIdDDLB = manufacturerIdDDLB;
    }

    public com.stpl.app.util.HelperDTO getBrandDdlb() {
        return brandDdlb;
    }

    public void setBrandDdlb(com.stpl.app.util.HelperDTO brandDdlb) {
        this.brandDdlb = brandDdlb;
    }

    public HelperDTO getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(HelperDTO itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemBatchId() {
        return itemBatchId;
    }

    public void setItemBatchId(String itemBatchId) {
        this.itemBatchId = itemBatchId;
    }

    public int getBaselineAmpPrecision() {
        return baselineAmpPrecision;
    }

    public void setBaselineAmpPrecision(int baselineAmpPrecision) {
        this.baselineAmpPrecision = baselineAmpPrecision;
    }

    public int getBaseCpiPrecision() {
        return baseCpiPrecision;
    }

    public void setBaseCpiPrecision(int baseCpiPrecision) {
        this.baseCpiPrecision = baseCpiPrecision;
    }

    }
