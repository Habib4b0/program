/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.service;

import javax.ws.rs.core.Response;

/**
 *
 * @author Abishek.Ram
 */
import org.junit.Ignore;
import org.springframework.web.client.RestTemplate;

@Ignore
public class WebserviceRunner {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8084/GTNWebServices/test", Response.class);
	}
}
