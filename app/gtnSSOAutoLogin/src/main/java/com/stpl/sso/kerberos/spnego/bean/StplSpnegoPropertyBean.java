package com.stpl.sso.kerberos.spnego.bean;

public class StplSpnegoPropertyBean {

    private boolean allowBasic;
    private boolean allowLocalHost;
    private boolean allowUnsecureBasic;
    private String cliendModule;
    private String krbConf;
    private String loginConf;
    private String serverModule;
    private boolean promtNtlm;
    private int logLevel;
    private String userName;
    private String passWord;

    public boolean isAllowBasic() {
        return allowBasic;
    }

    public void setAllowBasic(boolean allowBasic) {
        this.allowBasic = allowBasic;
    }

    public boolean isAllowLocalHost() {
        return allowLocalHost;
    }

    public void setAllowLocalHost(boolean allowLocalHost) {
        this.allowLocalHost = allowLocalHost;
    }

    public boolean isAllowUnsecureBasic() {
        return allowUnsecureBasic;
    }

    public void setAllowUnsecureBasic(boolean allowUnsecureBasic) {
        this.allowUnsecureBasic = allowUnsecureBasic;
    }

    public String getCliendModule() {
        return cliendModule;
    }

    public void setCliendModule(String cliendModule) {
        this.cliendModule = cliendModule;
    }

    public String getKrbConf() {
        return krbConf;
    }

    public void setKrbConf(String krbConf) {
        this.krbConf = krbConf;
    }

    public String getLoginConf() {
        return loginConf;
    }

    public void setLoginConf(String loginConf) {
        this.loginConf = loginConf;
    }

    public String getServerModule() {
        return serverModule;
    }

    public void setServerModule(String serverModule) {
        this.serverModule = serverModule;
    }

    public boolean isPromtNtlm() {
        return promtNtlm;
    }

    public void setPromtNtlm(boolean promtNtlm) {
        this.promtNtlm = promtNtlm;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
    
    
    
}
