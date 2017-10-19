package fileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class XMLWriter {

	public <T> void xmlConverter(List<T> type, String outPutFile, String alias) {

		XStream xstream = new XStream();

		String customerFileOut = outPutFile;

		File xmlOutput = new File(customerFileOut);

		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");

		xstream.alias(alias, Object.class);
		for (Object aType : type) {
			// Use toXML method to convert Person object into a String
			String typeOutput = xstream.toXML(aType);
			xmlPrintWriter.write(typeOutput);
			// System.out.println(personOutput);
		}
		xmlPrintWriter.close();
	}

}
