package com.stpl.gtn.gtn2o.ws.util.xmlparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.bean.xmlbean.GtnWsBPIGeneratorIDs;

public class GtnWsXmlParserService {

	public GtnWsBPIGeneratorIDs xmlReader(String xmlPath) throws JAXBException {
		GtnWsBPIGeneratorIDs gtnWsBpiGeneratorIds;
		File file = GtnFileNameUtils.getFile(xmlPath);
		JAXBContext jaxbContext = JAXBContext.newInstance(GtnWsBPIGeneratorIDs.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		gtnWsBpiGeneratorIds = (GtnWsBPIGeneratorIDs) jaxbUnmarshaller.unmarshal(file);
		return gtnWsBpiGeneratorIds;
	}

	public void xmlWriter(GtnWsBPIGeneratorIDs gtnWsBPIGeneratorIDs, String xmlPath)
			throws FileNotFoundException, JAXBException {
		JAXBContext contextObj = JAXBContext.newInstance(GtnWsBPIGeneratorIDs.class);

		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshallerObj.marshal(gtnWsBPIGeneratorIDs, new FileOutputStream(xmlPath));
	}

}
