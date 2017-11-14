package com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value = "serializingService")
@Scope(value = "singleton")
public class GtnWsDualListBoxSerializingService {

	public Object deserialize(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
		} catch (IOException e) {

		} finally {
			if (ois != null) {
				ois.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return obj;
	}

	public void serialize(Object obj, String fileName) throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
		} catch (Exception e) {

		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {

			}
		}

	}
}
