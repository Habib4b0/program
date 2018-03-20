package com.stpl.gtn.gtn2o.ws.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsAttachmentBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

@Service
@Scope("singleton")
public class GtnAttachmentDownloadService {
	public GtnAttachmentDownloadService() {
        super();
    }

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

    public GtnWsAttachmentBean getDownloadedFile(int attachmentSid) throws GtnFrameworkGeneralException
    {
       Session session = sessionFactory.openSession();
	   GtnWsAttachmentBean attachmentBean=new GtnWsAttachmentBean();
		List attachment;
			try {
				 attachment = session.createQuery("select fileData from Attachment where ATTACHMENT_SID = " + attachmentSid ).list();
				 attachmentBean.setFileData((byte[]) attachment.get(0));
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			return attachmentBean;
    }
}
