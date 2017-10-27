package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the VwItemMaster service. Represents a row in the &quot;VW_ITEM_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.parttwo.model.impl.VwItemMasterImpl}.
 * </p>
 *
 * @author
 * @see VwItemMaster
 * @see com.stpl.app.parttwo.model.impl.VwItemMasterImpl
 * @see com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl
 * @generated
 */
public interface VwItemMasterModel extends BaseModel<VwItemMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a vw item master model instance should use the {@link VwItemMaster} interface instead.
     */

    /**
     * Returns the primary key of this vw item master.
     *
     * @return the primary key of this vw item master
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this vw item master.
     *
     * @param primaryKey the primary key of this vw item master
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the item status of this vw item master.
     *
     * @return the item status of this vw item master
     */
    @AutoEscape
    public String getItemStatus();

    /**
     * Sets the item status of this vw item master.
     *
     * @param itemStatus the item status of this vw item master
     */
    public void setItemStatus(String itemStatus);

    /**
     * Returns the item desc of this vw item master.
     *
     * @return the item desc of this vw item master
     */
    @AutoEscape
    public String getItemDesc();

    /**
     * Sets the item desc of this vw item master.
     *
     * @param itemDesc the item desc of this vw item master
     */
    public void setItemDesc(String itemDesc);

    /**
     * Returns the acquired amp of this vw item master.
     *
     * @return the acquired amp of this vw item master
     */
    @AutoEscape
    public String getAcquiredAmp();

    /**
     * Sets the acquired amp of this vw item master.
     *
     * @param acquiredAmp the acquired amp of this vw item master
     */
    public void setAcquiredAmp(String acquiredAmp);

    /**
     * Returns the authorized generic start date of this vw item master.
     *
     * @return the authorized generic start date of this vw item master
     */
    public Date getAuthorizedGenericStartDate();

    /**
     * Sets the authorized generic start date of this vw item master.
     *
     * @param authorizedGenericStartDate the authorized generic start date of this vw item master
     */
    public void setAuthorizedGenericStartDate(Date authorizedGenericStartDate);

    /**
     * Returns the new formulation start date of this vw item master.
     *
     * @return the new formulation start date of this vw item master
     */
    public Date getNewFormulationStartDate();

    /**
     * Sets the new formulation start date of this vw item master.
     *
     * @param newFormulationStartDate the new formulation start date of this vw item master
     */
    public void setNewFormulationStartDate(Date newFormulationStartDate);

    /**
     * Returns the market termination date of this vw item master.
     *
     * @return the market termination date of this vw item master
     */
    public Date getMarketTerminationDate();

    /**
     * Sets the market termination date of this vw item master.
     *
     * @param marketTerminationDate the market termination date of this vw item master
     */
    public void setMarketTerminationDate(Date marketTerminationDate);

    /**
     * Returns the obra bamp of this vw item master.
     *
     * @return the obra bamp of this vw item master
     */
    @AutoEscape
    public String getObraBamp();

    /**
     * Sets the obra bamp of this vw item master.
     *
     * @param obraBamp the obra bamp of this vw item master
     */
    public void setObraBamp(String obraBamp);

    /**
     * Returns the brand of this vw item master.
     *
     * @return the brand of this vw item master
     */
    @AutoEscape
    public String getBrand();

    /**
     * Sets the brand of this vw item master.
     *
     * @param brand the brand of this vw item master
     */
    public void setBrand(String brand);

    /**
     * Returns the manufacturer no of this vw item master.
     *
     * @return the manufacturer no of this vw item master
     */
    @AutoEscape
    public String getManufacturerNo();

    /**
     * Sets the manufacturer no of this vw item master.
     *
     * @param manufacturerNo the manufacturer no of this vw item master
     */
    public void setManufacturerNo(String manufacturerNo);

    /**
     * Returns the modified date of this vw item master.
     *
     * @return the modified date of this vw item master
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this vw item master.
     *
     * @param modifiedDate the modified date of this vw item master
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the therapeutic class of this vw item master.
     *
     * @return the therapeutic class of this vw item master
     */
    @AutoEscape
    public String getTherapeuticClass();

    /**
     * Sets the therapeutic class of this vw item master.
     *
     * @param therapeuticClass the therapeutic class of this vw item master
     */
    public void setTherapeuticClass(String therapeuticClass);

    /**
     * Returns the organization key of this vw item master.
     *
     * @return the organization key of this vw item master
     */
    @AutoEscape
    public String getOrganizationKey();

    /**
     * Sets the organization key of this vw item master.
     *
     * @param organizationKey the organization key of this vw item master
     */
    public void setOrganizationKey(String organizationKey);

    /**
     * Returns the acquired bamp of this vw item master.
     *
     * @return the acquired bamp of this vw item master
     */
    @AutoEscape
    public String getAcquiredBamp();

    /**
     * Sets the acquired bamp of this vw item master.
     *
     * @param acquiredBamp the acquired bamp of this vw item master
     */
    public void setAcquiredBamp(String acquiredBamp);

    /**
     * Returns the pediatric exclusive end date of this vw item master.
     *
     * @return the pediatric exclusive end date of this vw item master
     */
    public Date getPediatricExclusiveEndDate();

    /**
     * Sets the pediatric exclusive end date of this vw item master.
     *
     * @param pediatricExclusiveEndDate the pediatric exclusive end date of this vw item master
     */
    public void setPediatricExclusiveEndDate(Date pediatricExclusiveEndDate);

    /**
     * Returns the source of this vw item master.
     *
     * @return the source of this vw item master
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this vw item master.
     *
     * @param source the source of this vw item master
     */
    public void setSource(String source);

    /**
     * Returns the new formulation of this vw item master.
     *
     * @return the new formulation of this vw item master
     */
    @AutoEscape
    public String getNewFormulation();

    /**
     * Sets the new formulation of this vw item master.
     *
     * @param newFormulation the new formulation of this vw item master
     */
    public void setNewFormulation(String newFormulation);

    /**
     * Returns the add chg del indicator of this vw item master.
     *
     * @return the add chg del indicator of this vw item master
     */
    @AutoEscape
    public String getAddChgDelIndicator();

    /**
     * Sets the add chg del indicator of this vw item master.
     *
     * @param addChgDelIndicator the add chg del indicator of this vw item master
     */
    public void setAddChgDelIndicator(String addChgDelIndicator);

    /**
     * Returns the divestiture date of this vw item master.
     *
     * @return the divestiture date of this vw item master
     */
    public Date getDivestitureDate();

    /**
     * Sets the divestiture date of this vw item master.
     *
     * @param divestitureDate the divestiture date of this vw item master
     */
    public void setDivestitureDate(Date divestitureDate);

    /**
     * Returns the shelf life of this vw item master.
     *
     * @return the shelf life of this vw item master
     */
    @AutoEscape
    public String getShelfLife();

    /**
     * Sets the shelf life of this vw item master.
     *
     * @param shelfLife the shelf life of this vw item master
     */
    public void setShelfLife(String shelfLife);

    /**
     * Returns the primary uom of this vw item master.
     *
     * @return the primary uom of this vw item master
     */
    @AutoEscape
    public String getPrimaryUom();

    /**
     * Sets the primary uom of this vw item master.
     *
     * @param primaryUom the primary uom of this vw item master
     */
    public void setPrimaryUom(String primaryUom);

    /**
     * Returns the new formulation end date of this vw item master.
     *
     * @return the new formulation end date of this vw item master
     */
    public Date getNewFormulationEndDate();

    /**
     * Sets the new formulation end date of this vw item master.
     *
     * @param newFormulationEndDate the new formulation end date of this vw item master
     */
    public void setNewFormulationEndDate(Date newFormulationEndDate);

    /**
     * Returns the modified by of this vw item master.
     *
     * @return the modified by of this vw item master
     */
    @AutoEscape
    public String getModifiedBy();

    /**
     * Sets the modified by of this vw item master.
     *
     * @param modifiedBy the modified by of this vw item master
     */
    public void setModifiedBy(String modifiedBy);

    /**
     * Returns the package size code of this vw item master.
     *
     * @return the package size code of this vw item master
     */
    @AutoEscape
    public String getPackageSizeCode();

    /**
     * Sets the package size code of this vw item master.
     *
     * @param packageSizeCode the package size code of this vw item master
     */
    public void setPackageSizeCode(String packageSizeCode);

    /**
     * Returns the secondary uom of this vw item master.
     *
     * @return the secondary uom of this vw item master
     */
    @AutoEscape
    public String getSecondaryUom();

    /**
     * Sets the secondary uom of this vw item master.
     *
     * @param secondaryUom the secondary uom of this vw item master
     */
    public void setSecondaryUom(String secondaryUom);

    /**
     * Returns the udc6 of this vw item master.
     *
     * @return the udc6 of this vw item master
     */
    @AutoEscape
    public String getUdc6();

    /**
     * Sets the udc6 of this vw item master.
     *
     * @param udc6 the udc6 of this vw item master
     */
    public void setUdc6(String udc6);

    /**
     * Returns the udc5 of this vw item master.
     *
     * @return the udc5 of this vw item master
     */
    @AutoEscape
    public String getUdc5();

    /**
     * Sets the udc5 of this vw item master.
     *
     * @param udc5 the udc5 of this vw item master
     */
    public void setUdc5(String udc5);

    /**
     * Returns the discontinuation date of this vw item master.
     *
     * @return the discontinuation date of this vw item master
     */
    public Date getDiscontinuationDate();

    /**
     * Sets the discontinuation date of this vw item master.
     *
     * @param discontinuationDate the discontinuation date of this vw item master
     */
    public void setDiscontinuationDate(Date discontinuationDate);

    /**
     * Returns the udc4 of this vw item master.
     *
     * @return the udc4 of this vw item master
     */
    @AutoEscape
    public String getUdc4();

    /**
     * Sets the udc4 of this vw item master.
     *
     * @param udc4 the udc4 of this vw item master
     */
    public void setUdc4(String udc4);

    /**
     * Returns the udc1 of this vw item master.
     *
     * @return the udc1 of this vw item master
     */
    @AutoEscape
    public String getUdc1();

    /**
     * Sets the udc1 of this vw item master.
     *
     * @param udc1 the udc1 of this vw item master
     */
    public void setUdc1(String udc1);

    /**
     * Returns the udc2 of this vw item master.
     *
     * @return the udc2 of this vw item master
     */
    @AutoEscape
    public String getUdc2();

    /**
     * Sets the udc2 of this vw item master.
     *
     * @param udc2 the udc2 of this vw item master
     */
    public void setUdc2(String udc2);

    /**
     * Returns the package size intro date of this vw item master.
     *
     * @return the package size intro date of this vw item master
     */
    public Date getPackageSizeIntroDate();

    /**
     * Sets the package size intro date of this vw item master.
     *
     * @param packageSizeIntroDate the package size intro date of this vw item master
     */
    public void setPackageSizeIntroDate(Date packageSizeIntroDate);

    /**
     * Returns the udc3 of this vw item master.
     *
     * @return the udc3 of this vw item master
     */
    @AutoEscape
    public String getUdc3();

    /**
     * Sets the udc3 of this vw item master.
     *
     * @param udc3 the udc3 of this vw item master
     */
    public void setUdc3(String udc3);

    /**
     * Returns the item end date of this vw item master.
     *
     * @return the item end date of this vw item master
     */
    public Date getItemEndDate();

    /**
     * Sets the item end date of this vw item master.
     *
     * @param itemEndDate the item end date of this vw item master
     */
    public void setItemEndDate(Date itemEndDate);

    /**
     * Returns the manufacturer ID of this vw item master.
     *
     * @return the manufacturer ID of this vw item master
     */
    @AutoEscape
    public String getManufacturerId();

    /**
     * Sets the manufacturer ID of this vw item master.
     *
     * @param manufacturerId the manufacturer ID of this vw item master
     */
    public void setManufacturerId(String manufacturerId);

    /**
     * Returns the item family ID of this vw item master.
     *
     * @return the item family ID of this vw item master
     */
    @AutoEscape
    public String getItemFamilyId();

    /**
     * Sets the item family ID of this vw item master.
     *
     * @param itemFamilyId the item family ID of this vw item master
     */
    public void setItemFamilyId(String itemFamilyId);

    /**
     * Returns the strength of this vw item master.
     *
     * @return the strength of this vw item master
     */
    @AutoEscape
    public String getStrength();

    /**
     * Sets the strength of this vw item master.
     *
     * @param strength the strength of this vw item master
     */
    public void setStrength(String strength);

    /**
     * Returns the item category of this vw item master.
     *
     * @return the item category of this vw item master
     */
    @AutoEscape
    public String getItemCategory();

    /**
     * Sets the item category of this vw item master.
     *
     * @param itemCategory the item category of this vw item master
     */
    public void setItemCategory(String itemCategory);

    /**
     * Returns the upps of this vw item master.
     *
     * @return the upps of this vw item master
     */
    public double getUpps();

    /**
     * Sets the upps of this vw item master.
     *
     * @param upps the upps of this vw item master
     */
    public void setUpps(double upps);

    /**
     * Returns the shelf life type of this vw item master.
     *
     * @return the shelf life type of this vw item master
     */
    @AutoEscape
    public String getShelfLifeType();

    /**
     * Sets the shelf life type of this vw item master.
     *
     * @param shelfLifeType the shelf life type of this vw item master
     */
    public void setShelfLifeType(String shelfLifeType);

    /**
     * Returns the pediatric exclusive indicator of this vw item master.
     *
     * @return the pediatric exclusive indicator of this vw item master
     */
    @AutoEscape
    public String getPediatricExclusiveIndicator();

    /**
     * Sets the pediatric exclusive indicator of this vw item master.
     *
     * @param pediatricExclusiveIndicator the pediatric exclusive indicator of this vw item master
     */
    public void setPediatricExclusiveIndicator(
        String pediatricExclusiveIndicator);

    /**
     * Returns the item type indication of this vw item master.
     *
     * @return the item type indication of this vw item master
     */
    @AutoEscape
    public String getItemTypeIndication();

    /**
     * Sets the item type indication of this vw item master.
     *
     * @param itemTypeIndication the item type indication of this vw item master
     */
    public void setItemTypeIndication(String itemTypeIndication);

    /**
     * Returns the acquisition date of this vw item master.
     *
     * @return the acquisition date of this vw item master
     */
    public Date getAcquisitionDate();

    /**
     * Sets the acquisition date of this vw item master.
     *
     * @param acquisitionDate the acquisition date of this vw item master
     */
    public void setAcquisitionDate(Date acquisitionDate);

    /**
     * Returns the clotting factor indicator of this vw item master.
     *
     * @return the clotting factor indicator of this vw item master
     */
    @AutoEscape
    public String getClottingFactorIndicator();

    /**
     * Sets the clotting factor indicator of this vw item master.
     *
     * @param clottingFactorIndicator the clotting factor indicator of this vw item master
     */
    public void setClottingFactorIndicator(String clottingFactorIndicator);

    /**
     * Returns the form of this vw item master.
     *
     * @return the form of this vw item master
     */
    @AutoEscape
    public String getForm();

    /**
     * Sets the form of this vw item master.
     *
     * @param form the form of this vw item master
     */
    public void setForm(String form);

    /**
     * Returns the item name of this vw item master.
     *
     * @return the item name of this vw item master
     */
    @AutoEscape
    public String getItemName();

    /**
     * Sets the item name of this vw item master.
     *
     * @param itemName the item name of this vw item master
     */
    public void setItemName(String itemName);

    /**
     * Returns the manufacturer name of this vw item master.
     *
     * @return the manufacturer name of this vw item master
     */
    @AutoEscape
    public String getManufacturerName();

    /**
     * Sets the manufacturer name of this vw item master.
     *
     * @param manufacturerName the manufacturer name of this vw item master
     */
    public void setManufacturerName(String manufacturerName);

    /**
     * Returns the pediatric exclusive start date of this vw item master.
     *
     * @return the pediatric exclusive start date of this vw item master
     */
    public Date getPediatricExclusiveStartDate();

    /**
     * Sets the pediatric exclusive start date of this vw item master.
     *
     * @param pediatricExclusiveStartDate the pediatric exclusive start date of this vw item master
     */
    public void setPediatricExclusiveStartDate(Date pediatricExclusiveStartDate);

    /**
     * Returns the first sale date of this vw item master.
     *
     * @return the first sale date of this vw item master
     */
    public Date getFirstSaleDate();

    /**
     * Sets the first sale date of this vw item master.
     *
     * @param firstSaleDate the first sale date of this vw item master
     */
    public void setFirstSaleDate(Date firstSaleDate);

    /**
     * Returns the item master sid of this vw item master.
     *
     * @return the item master sid of this vw item master
     */
    public int getItemMasterSid();

    /**
     * Sets the item master sid of this vw item master.
     *
     * @param itemMasterSid the item master sid of this vw item master
     */
    public void setItemMasterSid(int itemMasterSid);

    /**
     * Returns the item type of this vw item master.
     *
     * @return the item type of this vw item master
     */
    @AutoEscape
    public String getItemType();

    /**
     * Sets the item type of this vw item master.
     *
     * @param itemType the item type of this vw item master
     */
    public void setItemType(String itemType);

    /**
     * Returns the item ID of this vw item master.
     *
     * @return the item ID of this vw item master
     */
    @AutoEscape
    public String getItemId();

    /**
     * Sets the item ID of this vw item master.
     *
     * @param itemId the item ID of this vw item master
     */
    public void setItemId(String itemId);

    /**
     * Returns the baseline amp of this vw item master.
     *
     * @return the baseline amp of this vw item master
     */
    @AutoEscape
    public String getBaselineAmp();

    /**
     * Sets the baseline amp of this vw item master.
     *
     * @param baselineAmp the baseline amp of this vw item master
     */
    public void setBaselineAmp(String baselineAmp);

    /**
     * Returns the doses per unit of this vw item master.
     *
     * @return the doses per unit of this vw item master
     */
    @AutoEscape
    public String getDosesPerUnit();

    /**
     * Sets the doses per unit of this vw item master.
     *
     * @param dosesPerUnit the doses per unit of this vw item master
     */
    public void setDosesPerUnit(String dosesPerUnit);

    /**
     * Returns the dual pricing indicator of this vw item master.
     *
     * @return the dual pricing indicator of this vw item master
     */
    @AutoEscape
    public String getDualPricingIndicator();

    /**
     * Sets the dual pricing indicator of this vw item master.
     *
     * @param dualPricingIndicator the dual pricing indicator of this vw item master
     */
    public void setDualPricingIndicator(String dualPricingIndicator);

    /**
     * Returns the base cpi of this vw item master.
     *
     * @return the base cpi of this vw item master
     */
    @AutoEscape
    public String getBaseCpi();

    /**
     * Sets the base cpi of this vw item master.
     *
     * @param baseCpi the base cpi of this vw item master
     */
    public void setBaseCpi(String baseCpi);

    /**
     * Returns the created date of this vw item master.
     *
     * @return the created date of this vw item master
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this vw item master.
     *
     * @param createdDate the created date of this vw item master
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this vw item master.
     *
     * @return the created by of this vw item master
     */
    @AutoEscape
    public String getCreatedBy();

    /**
     * Sets the created by of this vw item master.
     *
     * @param createdBy the created by of this vw item master
     */
    public void setCreatedBy(String createdBy);

    /**
     * Returns the item start date of this vw item master.
     *
     * @return the item start date of this vw item master
     */
    public Date getItemStartDate();

    /**
     * Sets the item start date of this vw item master.
     *
     * @param itemStartDate the item start date of this vw item master
     */
    public void setItemStartDate(Date itemStartDate);

    /**
     * Returns the authorized generic of this vw item master.
     *
     * @return the authorized generic of this vw item master
     */
    @AutoEscape
    public String getAuthorizedGeneric();

    /**
     * Sets the authorized generic of this vw item master.
     *
     * @param authorizedGeneric the authorized generic of this vw item master
     */
    public void setAuthorizedGeneric(String authorizedGeneric);

    /**
     * Returns the ndc9 of this vw item master.
     *
     * @return the ndc9 of this vw item master
     */
    @AutoEscape
    public String getNdc9();

    /**
     * Sets the ndc9 of this vw item master.
     *
     * @param ndc9 the ndc9 of this vw item master
     */
    public void setNdc9(String ndc9);

    /**
     * Returns the authorized generic end date of this vw item master.
     *
     * @return the authorized generic end date of this vw item master
     */
    public Date getAuthorizedGenericEndDate();

    /**
     * Sets the authorized generic end date of this vw item master.
     *
     * @param authorizedGenericEndDate the authorized generic end date of this vw item master
     */
    public void setAuthorizedGenericEndDate(Date authorizedGenericEndDate);

    /**
     * Returns the item no of this vw item master.
     *
     * @return the item no of this vw item master
     */
    @AutoEscape
    public String getItemNo();

    /**
     * Sets the item no of this vw item master.
     *
     * @param itemNo the item no of this vw item master
     */
    public void setItemNo(String itemNo);

    /**
     * Returns the package size of this vw item master.
     *
     * @return the package size of this vw item master
     */
    @AutoEscape
    public String getPackageSize();

    /**
     * Sets the package size of this vw item master.
     *
     * @param packageSize the package size of this vw item master
     */
    public void setPackageSize(String packageSize);

    /**
     * Returns the ndc8 of this vw item master.
     *
     * @return the ndc8 of this vw item master
     */
    @AutoEscape
    public String getNdc8();

    /**
     * Sets the ndc8 of this vw item master.
     *
     * @param ndc8 the ndc8 of this vw item master
     */
    public void setNdc8(String ndc8);

    /**
     * Returns the item class of this vw item master.
     *
     * @return the item class of this vw item master
     */
    @AutoEscape
    public String getItemClass();

    /**
     * Sets the item class of this vw item master.
     *
     * @param itemClass the item class of this vw item master
     */
    public void setItemClass(String itemClass);

    /**
     * Returns the labeler code of this vw item master.
     *
     * @return the labeler code of this vw item master
     */
    @AutoEscape
    public String getLabelerCode();

    /**
     * Sets the labeler code of this vw item master.
     *
     * @param labelerCode the labeler code of this vw item master
     */
    public void setLabelerCode(String labelerCode);

    /**
     * Returns the display brand of this vw item master.
     *
     * @return the display brand of this vw item master
     */
    @AutoEscape
    public String getDisplayBrand();

    /**
     * Sets the display brand of this vw item master.
     *
     * @param displayBrand the display brand of this vw item master
     */
    public void setDisplayBrand(String displayBrand);

    /**
     * Returns the clotting factor end date of this vw item master.
     *
     * @return the clotting factor end date of this vw item master
     */
    public Date getClottingFactorEndDate();

    /**
     * Sets the clotting factor end date of this vw item master.
     *
     * @param clottingFactorEndDate the clotting factor end date of this vw item master
     */
    public void setClottingFactorEndDate(Date clottingFactorEndDate);

    /**
     * Returns the dra of this vw item master.
     *
     * @return the dra of this vw item master
     */
    @AutoEscape
    public String getDra();

    /**
     * Sets the dra of this vw item master.
     *
     * @param dra the dra of this vw item master
     */
    public void setDra(String dra);

    /**
     * Returns the brand ID of this vw item master.
     *
     * @return the brand ID of this vw item master
     */
    @AutoEscape
    public String getBrandId();

    /**
     * Sets the brand ID of this vw item master.
     *
     * @param brandId the brand ID of this vw item master
     */
    public void setBrandId(String brandId);

    /**
     * Returns the base cpi period of this vw item master.
     *
     * @return the base cpi period of this vw item master
     */
    public Date getBaseCpiPeriod();

    /**
     * Sets the base cpi period of this vw item master.
     *
     * @param baseCpiPeriod the base cpi period of this vw item master
     */
    public void setBaseCpiPeriod(Date baseCpiPeriod);

    /**
     * Returns the new formulation indicator of this vw item master.
     *
     * @return the new formulation indicator of this vw item master
     */
    @AutoEscape
    public String getNewFormulationIndicator();

    /**
     * Sets the new formulation indicator of this vw item master.
     *
     * @param newFormulationIndicator the new formulation indicator of this vw item master
     */
    public void setNewFormulationIndicator(String newFormulationIndicator);

    /**
     * Returns the last lot expiration date of this vw item master.
     *
     * @return the last lot expiration date of this vw item master
     */
    public Date getLastLotExpirationDate();

    /**
     * Sets the last lot expiration date of this vw item master.
     *
     * @param lastLotExpirationDate the last lot expiration date of this vw item master
     */
    public void setLastLotExpirationDate(Date lastLotExpirationDate);

    /**
     * Returns the batch ID of this vw item master.
     *
     * @return the batch ID of this vw item master
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this vw item master.
     *
     * @param batchId the batch ID of this vw item master
     */
    public void setBatchId(String batchId);

    /**
     * Returns the item code of this vw item master.
     *
     * @return the item code of this vw item master
     */
    @AutoEscape
    public String getItemCode();

    /**
     * Sets the item code of this vw item master.
     *
     * @param itemCode the item code of this vw item master
     */
    public void setItemCode(String itemCode);

    /**
     * Returns the clotting factor start date of this vw item master.
     *
     * @return the clotting factor start date of this vw item master
     */
    public Date getClottingFactorStartDate();

    /**
     * Sets the clotting factor start date of this vw item master.
     *
     * @param clottingFactorStartDate the clotting factor start date of this vw item master
     */
    public void setClottingFactorStartDate(Date clottingFactorStartDate);

    /**
     * Returns the non federal expiration date of this vw item master.
     *
     * @return the non federal expiration date of this vw item master
     */
    public Date getNonFederalExpirationDate();

    /**
     * Sets the non federal expiration date of this vw item master.
     *
     * @param nonFederalExpirationDate the non federal expiration date of this vw item master
     */
    public void setNonFederalExpirationDate(Date nonFederalExpirationDate);

    /**
     * Returns the base cpi precision of this vw item master.
     *
     * @return the base cpi precision of this vw item master
     */
    public int getBaseCpiPrecision();

    /**
     * Sets the base cpi precision of this vw item master.
     *
     * @param baseCpiPrecision the base cpi precision of this vw item master
     */
    public void setBaseCpiPrecision(int baseCpiPrecision);

    /**
     * Returns the baseline amp precision of this vw item master.
     *
     * @return the baseline amp precision of this vw item master
     */
    public int getBaselineAmpPrecision();

    /**
     * Sets the baseline amp precision of this vw item master.
     *
     * @param baselineAmpPrecision the baseline amp precision of this vw item master
     */
    public void setBaselineAmpPrecision(int baselineAmpPrecision);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(VwItemMaster vwItemMaster);

    @Override
    public int hashCode();

    @Override
    public CacheModel<VwItemMaster> toCacheModel();

    @Override
    public VwItemMaster toEscapedModel();

    @Override
    public VwItemMaster toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
