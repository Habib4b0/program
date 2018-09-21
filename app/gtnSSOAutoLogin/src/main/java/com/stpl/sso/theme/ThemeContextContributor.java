package com.stpl.sso.theme;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.template.TemplateContextContributor;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
        "type=" + TemplateContextContributor.TYPE_THEME }, service = TemplateContextContributor.class)
public class ThemeContextContributor implements TemplateContextContributor {

    @Override
    public void prepare(Map<String, Object> contextObjects, HttpServletRequest request) {
        contextObjects.put("is_sso", "true");
    }
}