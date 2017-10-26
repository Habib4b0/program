/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.autologin.config;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.stpl.sso.autologin.bean.StplSpnegoPropertyBean;
import com.stpl.sso.kerberos.spnego.SpnegoHttpFilter.Constants;
import java.net.URL;
import org.jboss.logging.Logger;

/**
 *
 * @author Abishek.Ram
 */
public class StplSpnegoConfigReader {

    private static final StplSpnegoConfigReader _INSTANCE = new StplSpnegoConfigReader();
    private static final Logger LOGGER = Logger.getLogger(StplSpnegoConfigReader.class);
    private StplSpnegoPropertyBean propertyBean;

    private StplSpnegoConfigReader() {
        readFile();
    }

    public StplSpnegoPropertyBean getPropertyBean() {
        return propertyBean;
    }

    public void setPropertyBean(StplSpnegoPropertyBean propertyBean) {
        this.propertyBean = propertyBean;
    }

    public static StplSpnegoConfigReader getInstance() {
        return _INSTANCE;
    }

    private void readFile() {
        try {
//            LOGGER.info("Inside read Config");
            propertyBean = new StplSpnegoPropertyBean();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("spengo.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(input);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Spengo");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;
            propertyBean.setAllowBasic(Boolean.parseBoolean(getData(eElement, Constants.ALLOW_BASIC)));
            propertyBean.setAllowLocalHost(Boolean.parseBoolean(getData(eElement, Constants.ALLOW_LOCALHOST)));
            propertyBean.setAllowUnsecureBasic(Boolean.parseBoolean(getData(eElement, Constants.ALLOW_UNSEC_BASIC)));
            propertyBean.setCliendModule(getData(eElement, Constants.CLIENT_MODULE));
            propertyBean.setKrbConf(getData(eElement, Constants.KRB5_CONF));
            propertyBean.setLoginConf(getData(eElement, Constants.LOGIN_CONF));
            propertyBean.setServerModule(getData(eElement, Constants.SERVER_MODULE));
            propertyBean.setPromtNtlm(Boolean.parseBoolean(getData(eElement, Constants.PROMPT_NTLM)));
            propertyBean.setLogLevel(Integer.valueOf(getData(eElement, Constants.LOGGER_LEVEL)));
            propertyBean.setUserName(getData(eElement,Constants.PREAUTH_USERNAME ));
            propertyBean.setPassWord(getData(eElement,Constants.PREAUTH_PASSWORD ));

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            LOGGER.error(ex);
        }
    }

    private String getData(Element elem, String tag) {
        String value = elem.getElementsByTagName(tag).item(0).getTextContent();
        return value;
    }

}
