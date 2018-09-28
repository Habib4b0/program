/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.utils;

import com.stpl.ifs.util.CommonUtil;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author Asha.Ravi
 */
public class BPIWorkFlowGeneratorXML {

    private static final Logger LOGGER = LoggerFactory.getLogger(BPIWorkFlowGeneratorXML.class);

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

        HashMap hmCounterAndDate;
        HashMap hmBPIConterAndUpdateValues;

        hmCounterAndDate = readBPICounterXML(fileWithPath, moduleName);

        hmBPIConterAndUpdateValues = getBPIWorkflowIDAndRequiredUpdates(
                hmCounterAndDate, moduleName);

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
            File files = com.stpl.ifs.util.CommonUtil.getFilePath(fileWithPath);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = builderFactory.newDocumentBuilder();
            Document doc = db.parse(files);
            doc.getDocumentElement().normalize();

            String updateDate = "NA";
            String counterValue = "NA";

            NodeList nodeLstTop = doc.getElementsByTagName("gtnWsBPIGeneratorIDs");
            for (int s1 = 0; s1 < nodeLstTop.getLength(); s1++) {

                NodeList nodeLst = doc.getElementsByTagName("row");

                for (int s = 0; s < nodeLst.getLength(); s++) {

                    Node fstNode = nodeLst.item(s);

                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element firstElment = (Element) fstNode;

                        NodeList firstAllocationLst = firstElment
                                .getElementsByTagName("moduleName");
                        Element firstAllocationElmnt = (Element) firstAllocationLst
                                .item(0);
                        NodeList firstAllocation = firstAllocationElmnt
                                .getChildNodes();

                        String moduleName = (firstAllocation.item(0))
                                .getNodeValue();

                        if (moduleName.equals(modName)) {
                            NodeList firstNmElmntLst = firstElment
                                    .getElementsByTagName(ARMUtils.COUNTER_VALUE);
                            Element firstNmElmnt = (Element) firstNmElmntLst
                                    .item(0);
                            NodeList firstNm = firstNmElmnt.getChildNodes();

                            counterValue = (firstNm.item(0))
                                    .getNodeValue();

                            NodeList lastNmElmntLst = firstElment
                                    .getElementsByTagName("counterUpdatedDate");
                            Element lastNmElmnt = (Element) lastNmElmntLst
                                    .item(0);
                            NodeList lastNm = lastNmElmnt.getChildNodes();

                            updateDate = (lastNm.item(0)).getNodeValue();
                        }

                        hm.put(ARMUtils.COUNTER_VALUE, counterValue);
                        hm.put("updateDate", updateDate);

                    }

                }

            }
        } catch (Exception e) {
            LOGGER.error("Error in readBPICounterXML :", e);
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

        String counterValue = hmCounterAndDate.get(ARMUtils.COUNTER_VALUE).toString();
        String updateDate = hmCounterAndDate.get("updateDate").toString();

        int c = 0;
        String dateFormat = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar c1 = Calendar.getInstance();
        String currentDate = sdf.format(c1.getTime());

        if (Integer.parseInt(updateDate) < Integer.parseInt(currentDate)) {

            updateDate = currentDate;

            counterValue = ARMUtils.STRING_ONE;

        } else {
            c = Integer.parseInt(counterValue) + 1;
            counterValue = String.valueOf(c);

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
    private String updateBPICounterXML(Map hmBPIConterAndUpdateValues,
            String fileWithPath, String modName) {

        String updateDate = hmBPIConterAndUpdateValues.get("dateToUpdate")
                .toString();
        String counterValue = hmBPIConterAndUpdateValues.get("counterToUpdate")
                .toString();

        boolean updateflag = false;

        try {
            File file = new CommonUtil().getFileName(fileWithPath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
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
                        String moduleName = (fstAllocation.item(0))
                                .getNodeValue();

                        if (moduleName.equals(modName)) {
                            NodeList fstNmElmntLst = fstElmnt
                                    .getElementsByTagName(ARMUtils.COUNTER_VALUE);
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
                    xformer.transform(new DOMSource(doc), new StreamResult(file));
                }

            }
        } catch (Exception e) {
            LOGGER.error("Error in updateBPICounterXML :", e);
        }

        return null;
    }

}
