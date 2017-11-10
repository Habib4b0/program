/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author STPL
 */
@Service
@Scope("singleton")
public class GtnWsReturnsResourceService {

	private ResourceBundle getResourceBundle(String resourceName) {
		return ResourceBundle.getBundle(resourceName);
	}

	public Map<String, String> loadSelectedPropertiesInMap(String resourceName, String resourcesPath, String[] keys) {

		ResourceBundle resourceBundle = this.getResourceBundle(resourcesPath + resourceName);
		Map<String, String> map = new HashMap<>();
		for (String key : keys) {
			map.put(key, resourceBundle.getString(StringUtils.upperCase(key)));
		}
		return map;

	}

	public Map<String, String> loadAllPropertiesInMap(String resourceName, String resourcesPath) {

		ResourceBundle resourceBundle = this.getResourceBundle(resourcesPath + resourceName);
		Map<String, String> map = new HashMap<>();
		for (String key : resourceBundle.keySet()) {
			map.put(key, resourceBundle.getString(key));
		}
		return map;

	}

	public String resourceFileName(final String resourceName, final String resourcesPath, final String key) {
		ResourceBundle resourceBundle = this.getResourceBundle(resourcesPath + resourceName);
		return resourceBundle.getString(StringUtils.upperCase(key));
	}

	public boolean isPropertyExists(final String resourceName, final String resourcesPath, final String key) {
		return this.getResourceBundle(resourcesPath + resourceName).containsKey(key);
	}

	public String getPropertyValue(String resourceName, String resourcesPath, String key) {
		ResourceBundle resourceBundle = this.getResourceBundle(resourcesPath + resourceName);
		return resourceBundle.getString(key);
	}

}
