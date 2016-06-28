package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the MParityLookup service. Represents a row in the &quot;M_PARITY_LOOKUP&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.MParityLookupModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.MParityLookupImpl}.
 * </p>
 *
 * @author
 * @see MParityLookup
 * @see com.stpl.app.model.impl.MParityLookupImpl
 * @see com.stpl.app.model.impl.MParityLookupModelImpl
 * @generated
 */
public interface MParityLookupModel extends BaseModel<MParityLookup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a m parity lookup model instance should use the {@link MParityLookup} interface instead.
     */

    /**
     * Returns the primary key of this m parity lookup.
     *
     * @return the primary key of this m parity lookup
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this m parity lookup.
     *
     * @param primaryKey the primary key of this m parity lookup
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the contract master sid of this m parity lookup.
     *
     * @return the contract master sid of this m parity lookup
     */
    public int getContractMasterSid();

    /**
     * Sets the contract master sid of this m parity lookup.
     *
     * @param contractMasterSid the contract master sid of this m parity lookup
     */
    public void setContractMasterSid(int contractMasterSid);

    /**
     * Returns the market type of this m parity lookup.
     *
     * @return the market type of this m parity lookup
     */
    @AutoEscape
    public String getMarketType();

    /**
     * Sets the market type of this m parity lookup.
     *
     * @param marketType the market type of this m parity lookup
     */
    public void setMarketType(String marketType);

    /**
     * Returns the item master sid of this m parity lookup.
     *
     * @return the item master sid of this m parity lookup
     */
    public int getItemMasterSid();

    /**
     * Sets the item master sid of this m parity lookup.
     *
     * @param itemMasterSid the item master sid of this m parity lookup
     */
    public void setItemMasterSid(int itemMasterSid);

    /**
     * Returns the m parity lookup sid of this m parity lookup.
     *
     * @return the m parity lookup sid of this m parity lookup
     */
    public int getMParityLookupSid();

    /**
     * Sets the m parity lookup sid of this m parity lookup.
     *
     * @param mParityLookupSid the m parity lookup sid of this m parity lookup
     */
    public void setMParityLookupSid(int mParityLookupSid);

    /**
     * Returns the projection details sid of this m parity lookup.
     *
     * @return the projection details sid of this m parity lookup
     */
    public int getProjectionDetailsSid();

    /**
     * Sets the projection details sid of this m parity lookup.
     *
     * @param projectionDetailsSid the projection details sid of this m parity lookup
     */
    public void setProjectionDetailsSid(int projectionDetailsSid);

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
    public int compareTo(MParityLookup mParityLookup);

    @Override
    public int hashCode();

    @Override
    public CacheModel<MParityLookup> toCacheModel();

    @Override
    public MParityLookup toEscapedModel();

    @Override
    public MParityLookup toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
