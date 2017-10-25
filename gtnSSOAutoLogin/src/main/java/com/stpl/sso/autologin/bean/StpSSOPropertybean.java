/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.autologin.bean;

import com.stpl.sso.kerberos.spnego.bean.StplSpnegoPropertyBean;
import com.stpl.sso.saml.bean.StplSamlPropertyBean;

/**
 *
 * @author Abishek.Ram
 */
public class StpSSOPropertybean {

    private StplSamlPropertyBean samlPropertyBean;
    private StplSpnegoPropertyBean spnegoPropertyBean;
    private String ssoType;

    public StplSamlPropertyBean getSamlPropertyBean() {
        return samlPropertyBean;
    }

    public void setSamlPropertyBean(StplSamlPropertyBean samlPropertyBean) {
        this.samlPropertyBean = samlPropertyBean;
    }

    public StplSpnegoPropertyBean getSpnegoPropertyBean() {
        return spnegoPropertyBean;
    }

    public void setSpnegoPropertyBean(StplSpnegoPropertyBean spnegoPropertyBean) {
        this.spnegoPropertyBean = spnegoPropertyBean;
    }

	public String getSsoType() {
		return ssoType;
	}

	public void setSsoType(String ssoType) {
		this.ssoType = ssoType;
	}
    
    
}
