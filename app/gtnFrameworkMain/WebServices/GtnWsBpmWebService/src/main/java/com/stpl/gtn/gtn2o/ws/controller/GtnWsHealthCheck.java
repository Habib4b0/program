package com.stpl.gtn.gtn2o.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/GtnWsHealthCheck")
public class GtnWsHealthCheck {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public boolean display() {

		return true;
	}

}
