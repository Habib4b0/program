package com.stpl.addons.vaadin.chartjs;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import com.vaadin.osgi.resources.OsgiVaadinResources;
import com.vaadin.osgi.resources.OsgiVaadinResources.ResourceBundleInactiveException;
import com.vaadin.osgi.resources.VaadinResourceService;

@Component(immediate = true)
public class VaadinChartJsResourcesContribution {
	private static final String[] RESOURCES = { 
			"chartjs/Chart.min.js", 
			"chartjs/chartjs-connector.js",
			"chartjs/chartjs-plugin-annotation.min.js",
			"chartjs/chartjs-plugin-zoom.min.js", 
			"chartjs/hammer.min.js"
			};
    private HttpService httpService;

    @Activate
    void startup() throws NamespaceException, ResourceBundleInactiveException {
        VaadinResourceService service = OsgiVaadinResources.getService();
        for (String resourceName : RESOURCES) {
            service.publishResource(resourceName, httpService);
        }
    }

    @Reference
    void setHttpService(HttpService service) {
        httpService = service;
    }

    void unsetHttpService(HttpService service) {
        httpService = null;
    }
}