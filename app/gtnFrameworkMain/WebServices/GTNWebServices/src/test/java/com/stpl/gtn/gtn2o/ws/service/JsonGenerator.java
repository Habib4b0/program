package com.stpl.gtn.gtn2o.ws.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Ignore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;

@Ignore
public class JsonGenerator {

	public static void main(String[] args) {
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		String randomNum = RandomStringUtils.randomNumeric(1);
		Object[] savedata = { 46124, randomNum, "0", "2018", 21 };
		gtnCMasterRequest.setSaveData(savedata);
		ObjectMapper as = new ObjectMapper();
		try {
			System.out.println("" + as.writeValueAsString(gtnCMasterRequest));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(JsonGenerator.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
