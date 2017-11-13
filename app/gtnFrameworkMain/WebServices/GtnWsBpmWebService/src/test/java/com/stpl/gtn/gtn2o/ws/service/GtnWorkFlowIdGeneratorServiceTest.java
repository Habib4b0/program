package com.stpl.gtn.gtn2o.ws.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.service.workflow.GtnWorkFlowIdGeneratorService;

@Ignore
public class GtnWorkFlowIdGeneratorServiceTest {
	private String xmlPath = "src/test/resources/BPIGeneratorIDs.xml";

	@Test
	public void readBPIXML() {
		GtnWorkFlowIdGeneratorService workflowIdGeneratorService = new GtnWorkFlowIdGeneratorService();
		try {
			Map<String, String> hm = workflowIdGeneratorService.readBPIXML(xmlPath, "C");
			Set<Map.Entry<String, String>> entrySet = hm.entrySet();

			Assert.assertNotNull(entrySet);

		} catch (JAXBException e) {
			Assert.fail("Exception while reading the xml.");
		}

	}

	@Test
	public void writeBPIXml() {
		GtnWorkFlowIdGeneratorService workflowIdGeneratorService = new GtnWorkFlowIdGeneratorService();
		Map<String, String> hmBPIConterAndUpdateValues = new HashMap<>();
		hmBPIConterAndUpdateValues.put("dateToUpdate", "20170709");
		hmBPIConterAndUpdateValues.put("counterToUpdate", "3");
		try {
			workflowIdGeneratorService.writeBPIXML(hmBPIConterAndUpdateValues, xmlPath, "C");
		} catch (FileNotFoundException | JAXBException e) {
			Assert.fail("Exception while writting the xml.");
		}
	}

	@Test
	public void generateId() {
		GtnWorkFlowIdGeneratorService workflowIdGeneratorService = new GtnWorkFlowIdGeneratorService();

		try {
			String workflowId = workflowIdGeneratorService.generateId(xmlPath, "C");
			Assert.assertEquals("CF201709070003", workflowId);
		} catch (FileNotFoundException | JAXBException e) {
			Assert.fail("Exception while generating the id.");
		}

	}

}
