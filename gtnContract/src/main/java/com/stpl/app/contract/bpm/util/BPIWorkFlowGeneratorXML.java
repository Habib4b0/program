/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.bpm.util;

import com.stpl.app.contract.util.Constants;
import java.util.HashMap;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class BPIWorkFlowGeneratorXML {
    
    private static final Logger LOGGER = Logger.getLogger(BPIWorkFlowGeneratorXML.class);

    /**
     * Method to test Locally
     *
     * @param args
     */

    /**
     * Method to generate BPIWF_ID to be generated
     *
     * @param fileWithPath
     * @param moduleName
     * @return
     */
    public String generateId(String fileWithPath, String moduleName) {

        HashMap hmCounterAndDate = new HashMap();
        HashMap hmBPIConterAndUpdateValues = new HashMap();

        // Step 1
        hmCounterAndDate = readBPICounterXML(fileWithPath, moduleName);
        // Step 2
        hmBPIConterAndUpdateValues = getBPIWorkflowIDAndRequiredUpdates(
                hmCounterAndDate, moduleName);
        // Step 3
        updateBPICounterXML(hmBPIConterAndUpdateValues, fileWithPath,
                moduleName);
        return (String) hmBPIConterAndUpdateValues.get("bpiWKid");

    }

    /**
     * Method to Read BPI COunter from XML
     *
     * @param fileWithPath
     * @param modName
     * @return
     */
    private HashMap readBPICounterXML(String fileWithPath, String modName) {

		// Will open the XML and GET the Counter Values and Date Value present
        // in Inside the XML.
        // Input is - 1) modName
        // Output is a HashMap containing 1) counterValue 2)UpdateDate
        HashMap hm = new HashMap();

        try {
            File file = new File(fileWithPath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            String updateDate = "NA";
            String counterValue = "NA";

            NodeList nodeLstTop = doc.getElementsByTagName("BPIWorkflowID");
            for (int s1 = 0; s1 < nodeLstTop.getLength(); s1++) {

                NodeList nodeLst = doc.getElementsByTagName("row");

                for (int s = 0; s < nodeLst.getLength(); s++) {

                    Node fstNode = nodeLst.item(s);

                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element fstElmnt = (Element) fstNode;

                        NodeList fstAllocationLst = fstElmnt
                                .getElementsByTagName("moduleName");
                        Element fstAllocationElmnt = (Element) fstAllocationLst
                                .item(0);
                        NodeList fstAllocation = fstAllocationElmnt
                                .getChildNodes();
		
                        String moduleName = ((Node) fstAllocation.item(0))
                                .getNodeValue().toString();

                        if (moduleName.equals(modName)) {
                            NodeList fstNmElmntLst = fstElmnt
                                    .getElementsByTagName(Constants.COUNTER_VALUE);
                            Element fstNmElmnt = (Element) fstNmElmntLst
                                    .item(0);
                            NodeList fstNm = fstNmElmnt.getChildNodes();
			
                            counterValue = ((Node) fstNm.item(0))
                                    .getNodeValue().toString();

                            NodeList lstNmElmntLst = fstElmnt
                                    .getElementsByTagName("counterUpdatedDate");
                            Element lstNmElmnt = (Element) lstNmElmntLst
                                    .item(0);
                            NodeList lstNm = lstNmElmnt.getChildNodes();
			
                            updateDate = ((Node) lstNm.item(0)).getNodeValue()
                                    .toString();
                        }

                        hm.put(Constants.COUNTER_VALUE, counterValue);
                        hm.put("updateDate", updateDate);
                        //

                    }

                }

            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return hm;
    }

    /**
     * Method to take current Counter and Update Date values, create the
     * BPIWorklflowID and provide the values to be updated for Counter and
     * Dates.
     *
     * @param hmCounterAndDate
     * @param moduleName
     * @return
     */
    private HashMap getBPIWorkflowIDAndRequiredUpdates(
            HashMap hmCounterAndDate, String moduleName) {
        HashMap hm = new HashMap();

        String counterValue = hmCounterAndDate.get(Constants.COUNTER_VALUE).toString();
        String updateDate = hmCounterAndDate.get("updateDate").toString();

        int c = 0;
        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        String currentDate = sdf.format(c1.getTime());

        if (Integer.parseInt(updateDate) < Integer.parseInt(currentDate)) {

            updateDate = currentDate;

            counterValue = "1";

        } else {
            c = Integer.parseInt(counterValue) + 1;
            counterValue = (new Integer(c)).toString();

        }

        String fmt = "0000";
        DecimalFormat df = new DecimalFormat(fmt);
        if (c == 0) {
            c = 1;
        }
        String bpiIDCounter = df.format(c);

        String bpiWKid = moduleName + "F" + updateDate + bpiIDCounter;

        hm.put("dateToUpdate", updateDate);
        hm.put("counterToUpdate", counterValue);
        hm.put("bpiWKid", bpiWKid);

        return hm;
    }

    /**
     * Method to open the XML and Update the Counter Values and Date Value
     * Inside the XML.
     *
     * @param hmBPIConterAndUpdateValues - HashMap Input - 1) Date to Update 2)
     * Counter to Update, 3) Module Name
     * @param fileWithPath
     * @param modName
     * @return
     */
    private String updateBPICounterXML(HashMap hmBPIConterAndUpdateValues,
            String fileWithPath, String modName) {

        String updateDate = hmBPIConterAndUpdateValues.get("dateToUpdate")
                .toString();
        String counterValue = hmBPIConterAndUpdateValues.get("counterToUpdate")
                .toString();

        boolean updateflag = false;

        try {
            File file = new File(fileWithPath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeLstTop = doc.getElementsByTagName("BPIWorkflowID");
            for (int s1 = 0; s1 < nodeLstTop.getLength(); s1++) {

                NodeList nodeLst = doc.getElementsByTagName("row");

                for (int s = 0; s < nodeLst.getLength(); s++) {

                    Node fstNode = nodeLst.item(s);

                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element fstElmnt = (Element) fstNode;

                        NodeList fstAllocationLst = fstElmnt
                                .getElementsByTagName("moduleName");
                        Element fstAllocationElmnt = (Element) fstAllocationLst
                                .item(0);
                        NodeList fstAllocation = fstAllocationElmnt
                                .getChildNodes();
                        String moduleName = ((Node) fstAllocation.item(0))
                                .getNodeValue().toString();

                        if (moduleName.equals(modName)) {
                            NodeList fstNmElmntLst = fstElmnt
                                    .getElementsByTagName(Constants.COUNTER_VALUE);
                            Element fstNmElmnt = (Element) fstNmElmntLst
                                    .item(0);
                            NodeList fstNm = fstNmElmnt.getChildNodes();

                            NodeList lstNmElmntLst = fstElmnt
                                    .getElementsByTagName("counterUpdatedDate");
                            Element lstNmElmnt = (Element) lstNmElmntLst
                                    .item(0);
                            NodeList lstNm = lstNmElmnt.getChildNodes();

                            Text updateDateText = (Text) lstNm.item(0);
                            updateDateText.setData(updateDate);

                            Text counterText = (Text) fstNm.item(0);
                            counterText.setData(counterValue);

                            updateflag = true;
                        }

                    }

                }

                if (updateflag) {
                    Transformer xformer = TransformerFactory.newInstance()
                            .newTransformer();
                    xformer.transform(new DOMSource(doc), new StreamResult(
                            new File(fileWithPath)));
                }

            }
        } catch (Exception e) {
           LOGGER.error(e);
        }

        return null;
    }
}
