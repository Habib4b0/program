package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AcBaseRateBaselineDetails in entity cache.
 *
 * @author
 * @see AcBaseRateBaselineDetails
 * @generated
 */
public class AcBaseRateBaselineDetailsCacheModel implements CacheModel<AcBaseRateBaselineDetails>,
    Externalizable {
    public double periodValue;
    public int periodSid;
    public boolean paymentsPeriod;
    public int acBrMethodologyDetailsSid;
    public boolean salesPeriod;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{periodValue=");
        sb.append(periodValue);
        sb.append(", periodSid=");
        sb.append(periodSid);
        sb.append(", paymentsPeriod=");
        sb.append(paymentsPeriod);
        sb.append(", acBrMethodologyDetailsSid=");
        sb.append(acBrMethodologyDetailsSid);
        sb.append(", salesPeriod=");
        sb.append(salesPeriod);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public AcBaseRateBaselineDetails toEntityModel() {
        AcBaseRateBaselineDetailsImpl acBaseRateBaselineDetailsImpl = new AcBaseRateBaselineDetailsImpl();

        acBaseRateBaselineDetailsImpl.setPeriodValue(periodValue);
        acBaseRateBaselineDetailsImpl.setPeriodSid(periodSid);
        acBaseRateBaselineDetailsImpl.setPaymentsPeriod(paymentsPeriod);
        acBaseRateBaselineDetailsImpl.setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
        acBaseRateBaselineDetailsImpl.setSalesPeriod(salesPeriod);

        acBaseRateBaselineDetailsImpl.resetOriginalValues();

        return acBaseRateBaselineDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        periodValue = objectInput.readDouble();
        periodSid = objectInput.readInt();
        paymentsPeriod = objectInput.readBoolean();
        acBrMethodologyDetailsSid = objectInput.readInt();
        salesPeriod = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(periodValue);
        objectOutput.writeInt(periodSid);
        objectOutput.writeBoolean(paymentsPeriod);
        objectOutput.writeInt(acBrMethodologyDetailsSid);
        objectOutput.writeBoolean(salesPeriod);
    }
}
