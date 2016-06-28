package com.stpl.app.model;

import com.stpl.app.service.persistence.NmActualDiscountPK;

import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the NmActualDiscount service. Represents a row in the &quot;NM_ACTUAL_DISCOUNT&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.NmActualDiscountModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.NmActualDiscountImpl}.
 * </p>
 *
 * @author
 * @see NmActualDiscount
 * @see com.stpl.app.model.impl.NmActualDiscountImpl
 * @see com.stpl.app.model.impl.NmActualDiscountModelImpl
 * @generated
 */
public interface NmActualDiscountModel extends BaseModel<NmActualDiscount> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a nm actual discount model instance should use the {@link NmActualDiscount} interface instead.
     */

    /**
     * Returns the primary key of this nm actual discount.
     *
     * @return the primary key of this nm actual discount
     */
    public NmActualDiscountPK getPrimaryKey();

    /**
     * Sets the primary key of this nm actual discount.
     *
     * @param primaryKey the primary key of this nm actual discount
     */
    public void setPrimaryKey(NmActualDiscountPK primaryKey);

    /**
     * Returns the actual rate of this nm actual discount.
     *
     * @return the actual rate of this nm actual discount
     */
    public double getActualRate();

    /**
     * Sets the actual rate of this nm actual discount.
     *
     * @param actualRate the actual rate of this nm actual discount
     */
    public void setActualRate(double actualRate);

    /**
     * Returns the period sid of this nm actual discount.
     *
     * @return the period sid of this nm actual discount
     */
    public int getPeriodSid();

    /**
     * Sets the period sid of this nm actual discount.
     *
     * @param periodSid the period sid of this nm actual discount
     */
    public void setPeriodSid(int periodSid);

    /**
     * Returns the actual projection sales of this nm actual discount.
     *
     * @return the actual projection sales of this nm actual discount
     */
    public double getActualProjectionSales();

    /**
     * Sets the actual projection sales of this nm actual discount.
     *
     * @param actualProjectionSales the actual projection sales of this nm actual discount
     */
    public void setActualProjectionSales(double actualProjectionSales);

    /**
     * Returns the projection details sid of this nm actual discount.
     *
     * @return the projection details sid of this nm actual discount
     */
    public int getProjectionDetailsSid();

    /**
     * Sets the projection details sid of this nm actual discount.
     *
     * @param projectionDetailsSid the projection details sid of this nm actual discount
     */
    public void setProjectionDetailsSid(int projectionDetailsSid);

    /**
     * Returns the actual projection rate of this nm actual discount.
     *
     * @return the actual projection rate of this nm actual discount
     */
    public double getActualProjectionRate();

    /**
     * Sets the actual projection rate of this nm actual discount.
     *
     * @param actualProjectionRate the actual projection rate of this nm actual discount
     */
    public void setActualProjectionRate(double actualProjectionRate);

    /**
     * Returns the actual sales of this nm actual discount.
     *
     * @return the actual sales of this nm actual discount
     */
    public double getActualSales();

    /**
     * Sets the actual sales of this nm actual discount.
     *
     * @param actualSales the actual sales of this nm actual discount
     */
    public void setActualSales(double actualSales);

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
    public int compareTo(NmActualDiscount nmActualDiscount);

    @Override
    public int hashCode();

    @Override
    public CacheModel<NmActualDiscount> toCacheModel();

    @Override
    public NmActualDiscount toEscapedModel();

    @Override
    public NmActualDiscount toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
