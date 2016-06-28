package com.stpl.app.model;

import com.stpl.app.service.persistence.NmPpaProjectionPK;

import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the NmPpaProjection service. Represents a row in the &quot;NM_PPA_PROJECTION&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.NmPpaProjectionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.NmPpaProjectionImpl}.
 * </p>
 *
 * @author
 * @see NmPpaProjection
 * @see com.stpl.app.model.impl.NmPpaProjectionImpl
 * @see com.stpl.app.model.impl.NmPpaProjectionModelImpl
 * @generated
 */
public interface NmPpaProjectionModel extends BaseModel<NmPpaProjection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a nm ppa projection model instance should use the {@link NmPpaProjection} interface instead.
     */

    /**
     * Returns the primary key of this nm ppa projection.
     *
     * @return the primary key of this nm ppa projection
     */
    public NmPpaProjectionPK getPrimaryKey();

    /**
     * Sets the primary key of this nm ppa projection.
     *
     * @param primaryKey the primary key of this nm ppa projection
     */
    public void setPrimaryKey(NmPpaProjectionPK primaryKey);

    /**
     * Returns the period sid of this nm ppa projection.
     *
     * @return the period sid of this nm ppa projection
     */
    public int getPeriodSid();

    /**
     * Sets the period sid of this nm ppa projection.
     *
     * @param periodSid the period sid of this nm ppa projection
     */
    public void setPeriodSid(int periodSid);

    /**
     * Returns the projection rate of this nm ppa projection.
     *
     * @return the projection rate of this nm ppa projection
     */
    public double getProjectionRate();

    /**
     * Sets the projection rate of this nm ppa projection.
     *
     * @param projectionRate the projection rate of this nm ppa projection
     */
    public void setProjectionRate(double projectionRate);

    /**
     * Returns the projection details sid of this nm ppa projection.
     *
     * @return the projection details sid of this nm ppa projection
     */
    public int getProjectionDetailsSid();

    /**
     * Sets the projection details sid of this nm ppa projection.
     *
     * @param projectionDetailsSid the projection details sid of this nm ppa projection
     */
    public void setProjectionDetailsSid(int projectionDetailsSid);

    /**
     * Returns the price cap of this nm ppa projection.
     *
     * @return the price cap of this nm ppa projection
     */
    public double getPriceCap();

    /**
     * Sets the price cap of this nm ppa projection.
     *
     * @param priceCap the price cap of this nm ppa projection
     */
    public void setPriceCap(double priceCap);

    /**
     * Returns the projection discount units of this nm ppa projection.
     *
     * @return the projection discount units of this nm ppa projection
     */
    public double getProjectionDiscountUnits();

    /**
     * Sets the projection discount units of this nm ppa projection.
     *
     * @param projectionDiscountUnits the projection discount units of this nm ppa projection
     */
    public void setProjectionDiscountUnits(double projectionDiscountUnits);

    /**
     * Returns the projection discount dollar of this nm ppa projection.
     *
     * @return the projection discount dollar of this nm ppa projection
     */
    public double getProjectionDiscountDollar();

    /**
     * Sets the projection discount dollar of this nm ppa projection.
     *
     * @param projectionDiscountDollar the projection discount dollar of this nm ppa projection
     */
    public void setProjectionDiscountDollar(double projectionDiscountDollar);

    /**
     * Returns the reset of this nm ppa projection.
     *
     * @return the reset of this nm ppa projection
     */
    public boolean getReset();

    /**
     * Returns <code>true</code> if this nm ppa projection is reset.
     *
     * @return <code>true</code> if this nm ppa projection is reset; <code>false</code> otherwise
     */
    public boolean isReset();

    /**
     * Sets whether this nm ppa projection is reset.
     *
     * @param reset the reset of this nm ppa projection
     */
    public void setReset(boolean reset);

    /**
     * Returns the projection sales of this nm ppa projection.
     *
     * @return the projection sales of this nm ppa projection
     */
    public double getProjectionSales();

    /**
     * Sets the projection sales of this nm ppa projection.
     *
     * @param projectionSales the projection sales of this nm ppa projection
     */
    public void setProjectionSales(double projectionSales);

    /**
     * Returns the projection map of this nm ppa projection.
     *
     * @return the projection map of this nm ppa projection
     */
    public double getProjectionMap();

    /**
     * Sets the projection map of this nm ppa projection.
     *
     * @param projectionMap the projection map of this nm ppa projection
     */
    public void setProjectionMap(double projectionMap);

    /**
     * Returns the reset price cap of this nm ppa projection.
     *
     * @return the reset price cap of this nm ppa projection
     */
    public boolean getResetPriceCap();

    /**
     * Returns <code>true</code> if this nm ppa projection is reset price cap.
     *
     * @return <code>true</code> if this nm ppa projection is reset price cap; <code>false</code> otherwise
     */
    public boolean isResetPriceCap();

    /**
     * Sets whether this nm ppa projection is reset price cap.
     *
     * @param resetPriceCap the reset price cap of this nm ppa projection
     */
    public void setResetPriceCap(boolean resetPriceCap);

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
    public int compareTo(NmPpaProjection nmPpaProjection);

    @Override
    public int hashCode();

    @Override
    public CacheModel<NmPpaProjection> toCacheModel();

    @Override
    public NmPpaProjection toEscapedModel();

    @Override
    public NmPpaProjection toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
