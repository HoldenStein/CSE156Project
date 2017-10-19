package fileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter {
	// redefine to make generic
	public <T> void jsonConverter(List<T> type, String outputFileName) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File(outputFileName);

		PrintWriter jsonPrintWriter = null;

		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (Object aType : type) {
			String customerOutput = gson.toJson(aType);
			jsonPrintWriter.write(customerOutput + "\n");
		}

		jsonPrintWriter.close();
	}
}
