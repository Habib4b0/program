package com.stpl.app.service.persistence;

public interface RsContractDetailsFinder {
    public java.lang.Boolean saveRsDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future);

    public void saveFormulaToContractRs(int contRsdSid, int rsdSid, int itemSid);
}
