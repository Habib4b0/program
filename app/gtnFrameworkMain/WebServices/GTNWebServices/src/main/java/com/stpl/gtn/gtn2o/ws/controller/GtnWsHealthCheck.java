package com.stpl.gtn.gtn2o.ws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GtnWsHealthCheck {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean display() {

		return true;
	}

}
