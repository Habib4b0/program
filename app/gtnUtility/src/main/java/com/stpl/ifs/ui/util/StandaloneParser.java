package com.stpl.ifs.ui.util;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.stpl.ifs.util.constants.GlobalConstants;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author karthikeyans
 */
public class StandaloneParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StandaloneParser.class.getName());
    private String server;
    private String schema;
    private String user;
    private String password;
    private static StandaloneParser parser;
    private static final String USERNAME = "username";
    private static final String NAME = "name";
    private static final String VALUE = "value";
    private static final String SECURITY_DOMAIN = "security-domain";
    private static final String MODULE_OPTION = "module-option";
    private boolean isPasswordEncrypted = false;


    private StandaloneParser() {
        parseXML();
    }

    public static StandaloneParser getInstance() {
        if (parser == null) {
            parser = new StandaloneParser();
        }
        return parser;
    }

    private void parseXML() {
        try {
            String path = System.getProperty("jboss.server.config.dir") + File.separator + "standalone.xml";
            LOGGER.debug("Standalone path :" + path);
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.setXmlStandalone(true);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("datasource");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                NamedNodeMap att = nNode.getAttributes();
                if ("appDataPool".equals(att.getNamedItem("pool-name").getNodeValue())) {
                    NodeList cNodeList = nNode.getChildNodes();
                    for (int cn = 0; cn < cNodeList.getLength(); cn++) {
                        Node cNode = cNodeList.item(cn);
                        if (!"#text".equals(cNode.getNodeName())) {
                            if ("connection-url".equals(cNode.getNodeName())) {
                                String val = cNode.getTextContent();
                                server = val.substring(val.indexOf("://") + NumericConstants.THREE, val.indexOf(";"));
                                server = server.substring(0, server.indexOf(":"));
                                schema = val.substring(val.indexOf("=") + 1);
                            }
                            if ("security".equals(cNode.getNodeName())) {
                                NodeList up_NodeList = cNode.getChildNodes();
                                for (int up = 0; up < up_NodeList.getLength(); up++) {
                                    Node upNode = up_NodeList.item(up);
                                    if (!"#text".equals(upNode.getNodeName())) {
                                        if ("security-domain".equals(upNode.getNodeName())) {
                                            isPasswordEncrypted = true;
                                            getEncryptedDetails(doc, upNode.getTextContent());
                                        } else {
                                            if ("user-name".equals(upNode.getNodeName())) {
                                                user = upNode.getTextContent();
                                            }
                                            if ("password".equals(upNode.getNodeName())) {
                                                password = upNode.getTextContent();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
    
    /**
     * This method will be called only if the password is encrypted This method
     * gets the user name and password from the module-options tag from the
     * standalone file
     *
     * @param doc
     * @param textContent
     */
    private void getEncryptedDetails(Document doc, String textContent) {
        NodeList nList = doc.getElementsByTagName(MODULE_OPTION);
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Node parentNode = nNode.getParentNode().getParentNode().getParentNode();
            if (SECURITY_DOMAIN.equals(parentNode.getNodeName())
                    && parentNode.getAttributes().getNamedItem(NAME) != null
                    && textContent.equals(parentNode.getAttributes().getNamedItem(NAME).getNodeValue())) {
                if (USERNAME.equals(nNode.getAttributes().getNamedItem(NAME).getNodeValue())) {
                    user = nNode.getAttributes().getNamedItem(VALUE).getNodeValue();
                }
                if (GlobalConstants.getPassword().equals(nNode.getAttributes().getNamedItem(NAME).getNodeValue())) {
                    password = nNode.getAttributes().getNamedItem(VALUE).getNodeValue();
                }
            }
        }
    }
    public String getServer() {
        return server;
    }

    public String getSchema() {
        return schema;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsPasswordEncrypted() {
        return isPasswordEncrypted;
    }

}