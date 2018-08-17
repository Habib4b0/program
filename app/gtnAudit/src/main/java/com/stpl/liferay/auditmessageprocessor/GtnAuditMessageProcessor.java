package com.stpl.liferay.auditmessageprocessor;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.audit.AuditMessageProcessor;
import com.liferay.portal.security.audit.configuration.AuditConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Antonio Musarra
 */
@Component(configurationPid = "com.liferay.portal.security.audit.configuration.AuditConfiguration", configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true, property = "eventTypes=*", service = AuditMessageProcessor.class)
public class GtnAuditMessageProcessor implements AuditMessageProcessor {

	@Override
	public void process(AuditMessage auditMessage) {
		try {
			doProcess(auditMessage);
		} catch (Exception e) {
			_log.fatal("Unable to process audit message " + auditMessage, e);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_auditConfiguration = ConfigurableUtil.createConfigurable(AuditConfiguration.class, properties);

		if (_log.isInfoEnabled()) {
			_log.info("Liferay Portal Security Audit enabled : " + _auditConfiguration.enabled());
		}
	}

	protected void doProcess(AuditMessage auditMessage) throws Exception {
		if (_auditConfiguration.enabled()) {
			if (_log.isInfoEnabled()) {
				_log.info("GTN Audit Log => " + auditMessage.getClassName() +" : "+ auditMessage.getMessage());
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(GtnAuditMessageProcessor.class);

	private volatile AuditConfiguration _auditConfiguration;
}