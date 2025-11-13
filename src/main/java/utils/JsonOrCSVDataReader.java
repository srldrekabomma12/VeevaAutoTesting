package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class JsonOrCSVDataReader {
    private Properties prop;
    String fl = ConfigReader.getProperty("footer_links.csv.path");

    public void JsonOrCSVDataReaderToStore(Set<Object> list) throws IOException {
        try (FileWriter writer = new FileWriter(fl)) {
            writer.append("Link\n");
            for (Object href : list) {
                writer.append((CharSequence) href).append("\n");
            }
        }
    }
}
