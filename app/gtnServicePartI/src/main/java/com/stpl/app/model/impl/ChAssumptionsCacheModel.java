package com.stpl.app.model.impl;

import com.stpl.app.model.ChAssumptions;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ChAssumptions in entity cache.
 *
 * @author
 * @see ChAssumptions
 * @generated
 */
public class ChAssumptionsCacheModel implements CacheModel<ChAssumptions>,
    Externalizable {
    public boolean parent;
    public int projectionDetailsSid;
    public String commentary;
    public int quarter;
    public double totalDiscountPercentChange;
    public String reasonCodes;
    public int year;
    public double totalDiscountPercentProjected;
    public double totalDiscountPercentPrior;
    public int chAssumptionsSid;
    public double totalDiscountChange;
    public double totalDiscountProjected;
    public int camId;
    public double grossTradeSales;
    public double totalDiscountPrior;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{parent=");
        sb.append(parent);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", commentary=");
        sb.append(commentary);
        sb.append(", quarter=");
        sb.append(quarter);
        sb.append(", totalDiscountPercentChange=");
        sb.append(totalDiscountPercentChange);
        sb.append(", reasonCodes=");
        sb.append(reasonCodes);
        sb.append(", year=");
        sb.append(year);
        sb.append(", totalDiscountPercentProjected=");
        sb.append(totalDiscountPercentProjected);
        sb.append(", totalDiscountPercentPrior=");
        sb.append(totalDiscountPercentPrior);
        sb.append(", chAssumptionsSid=");
        sb.append(chAssumptionsSid);
        sb.append(", totalDiscountChange=");
        sb.append(totalDiscountChange);
        sb.append(", totalDiscountProjected=");
        sb.append(totalDiscountProjected);
        sb.append(", camId=");
        sb.append(camId);
        sb.append(", grossTradeSales=");
        sb.append(grossTradeSales);
        sb.append(", totalDiscountPrior=");
        sb.append(totalDiscountPrior);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ChAssumptions toEntityModel() {
        ChAssumptionsImpl chAssumptionsImpl = new ChAssumptionsImpl();

        chAssumptionsImpl.setParent(parent);
        chAssumptionsImpl.setProjectionDetailsSid(projectionDetailsSid);

        if (commentary == null) {
            chAssumptionsImpl.setCommentary(StringPool.BLANK);
        } else {
            chAssumptionsImpl.setCommentary(commentary);
        }

        chAssumptionsImpl.setQuarter(quarter);
        chAssumptionsImpl.setTotalDiscountPercentChange(totalDiscountPercentChange);

        if (reasonCodes == null) {
            chAssumptionsImpl.setReasonCodes(StringPool.BLANK);
        } else {
            chAssumptionsImpl.setReasonCodes(reasonCodes);
        }

        chAssumptionsImpl.setYear(year);
        chAssumptionsImpl.setTotalDiscountPercentProjected(totalDiscountPercentProjected);
        chAssumptionsImpl.setTotalDiscountPercentPrior(totalDiscountPercentPrior);
        chAssumptionsImpl.setChAssumptionsSid(chAssumptionsSid);
        chAssumptionsImpl.setTotalDiscountChange(totalDiscountChange);
        chAssumptionsImpl.setTotalDiscountProjected(totalDiscountProjected);
        chAssumptionsImpl.setCamId(camId);
        chAssumptionsImpl.setGrossTradeSales(grossTradeSales);
        chAssumptionsImpl.setTotalDiscountPrior(totalDiscountPrior);

        chAssumptionsImpl.resetOriginalValues();

        return chAssumptionsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        parent = objectInput.readBoolean();
        projectionDetailsSid = objectInput.readInt();
        commentary = objectInput.readUTF();
        quarter = objectInput.readInt();
        totalDiscountPercentChange = objectInput.readDouble();
        reasonCodes = objectInput.readUTF();
        year = objectInput.readInt();
        totalDiscountPercentProjected = objectInput.readDouble();
        totalDiscountPercentPrior = objectInput.readDouble();
        chAssumptionsSid = objectInput.readInt();
        totalDiscountChange = objectInput.readDouble();
        totalDiscountProjected = objectInput.readDouble();
        camId = objectInput.readInt();
        grossTradeSales = objectInput.readDouble();
        totalDiscountPrior = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeBoolean(parent);
        objectOutput.writeInt(projectionDetailsSid);

        if (commentary == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(commentary);
        }

        objectOutput.writeInt(quarter);
        objectOutput.writeDouble(totalDiscountPercentChange);

        if (reasonCodes == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonCodes);
        }

        objectOutput.writeInt(year);
        objectOutput.writeDouble(totalDiscountPercentProjected);
        objectOutput.writeDouble(totalDiscountPercentPrior);
        objectOutput.writeInt(chAssumptionsSid);
        objectOutput.writeDouble(totalDiscountChange);
        objectOutput.writeDouble(totalDiscountProjected);
        objectOutput.writeInt(camId);
        objectOutput.writeDouble(grossTradeSales);
        objectOutput.writeDouble(totalDiscountPrior);
    }
}
