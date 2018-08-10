package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util;

public class GtnUIFrameworkNsfFormulaType {

    private String formulaTypeValue = null;
    private boolean changeAllowed = true;

    private GtnUIFrameworkNsfFormulaType() {

    }

    static class INNER {
        private static final GtnUIFrameworkNsfFormulaType INSTANCE = new GtnUIFrameworkNsfFormulaType();
    }

    public static GtnUIFrameworkNsfFormulaType getInstance() {
        return INNER.INSTANCE;
    }

    public String getFormulaTypeValue() {
        return formulaTypeValue;
    }

    public void setFormulaTypeValue(String formulaTypeValue) {
        this.formulaTypeValue = formulaTypeValue;
    }

    public boolean isChangeAllowed() {
        return changeAllowed;
    }

    public void setChangeAllowed(boolean changeAllowed) {
        this.changeAllowed = changeAllowed;
    }

}
