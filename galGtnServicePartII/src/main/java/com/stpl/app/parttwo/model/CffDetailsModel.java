package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the CffDetails service. Represents a row in the &quot;CFF_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.parttwo.model.impl.CffDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.parttwo.model.impl.CffDetailsImpl}.
 * </p>
 *
 * @author
 * @see CffDetails
 * @see com.stpl.app.parttwo.model.impl.CffDetailsImpl
 * @see com.stpl.app.parttwo.model.impl.CffDetailsModelImpl
 * @generated
 */
public interface CffDetailsModel extends BaseModel<CffDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a cff details model instance should use the {@link CffDetails} interface instead.
     */

    /**
     * Returns the primary key of this cff details.
     *
     * @return the primary key of this cff details
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this cff details.
     *
     * @param primaryKey the primary key of this cff details
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the ccp details sid of this cff details.
     *
     * @return the ccp details sid of this cff details
     */
    public int getCcpDetailsSid();

    /**
     * Sets the ccp details sid of this cff details.
     *
     * @param ccpDetailsSid the ccp details sid of this cff details
     */
    public void setCcpDetailsSid(int ccpDetailsSid);

    /**
     * Returns the projection master sid of this cff details.
     *
     * @return the projection master sid of this cff details
     */
    public int getProjectionMasterSid();

    /**
     * Sets the projection master sid of this cff details.
     *
     * @param projectionMasterSid the projection master sid of this cff details
     */
    public void setProjectionMasterSid(int projectionMasterSid);

    /**
     * Returns the cff master sid of this cff details.
     *
     * @return the cff master sid of this cff details
     */
    public int getCffMasterSid();

    /**
     * Sets the cff master sid of this cff details.
     *
     * @param cffMasterSid the cff master sid of this cff details
     */
    public void setCffMasterSid(int cffMasterSid);

    /**
     * Returns the inbound status of this cff details.
     *
     * @return the inbound status of this cff details
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this cff details.
     *
     * @param inboundStatus the inbound status of this cff details
     */
    public void setInboundStatus(String inboundStatus);

    /**
     * Returns the cff details sid of this cff details.
     *
     * @return the cff details sid of this cff details
     */
    public int getCffDetailsSid();

    /**
     * Sets the cff details sid of this cff details.
     *
     * @param cffDetailsSid the cff details sid of this cff details
     */
    public void setCffDetailsSid(int cffDetailsSid);

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
    public int compareTo(CffDetails cffDetails);

    @Override
    public int hashCode();

    @Override
    public CacheModel<CffDetails> toCacheModel();

    @Override
    public CffDetails toEscapedModel();

    @Override
    public CffDetails toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
