package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonOrCSVDataReader {
    
    private Properties prop;
    String fl = ConfigReader.getInstance().getProperty("footer_links.csv.path");
    static String flJSON = ConfigReader.getInstance().getProperty("jsonFilePath");

    public void JsonOrCSVDataReaderToStore(Set<Object> list) throws IOException {
        try (FileWriter writer = new FileWriter(fl)) {
            writer.append("Link\n");
            for (Object href : list) {
                writer.append((CharSequence) href).append("\n");
            }
        }
    }

    // -----------------------------
    // New method to read JSON object with ticketsSlides and teamSlides
    public SlideData readSlidesFromJson() throws IOException {
        if (flJSON == null || flJSON.isEmpty()) {
            throw new IllegalArgumentException("JSON file path is not configured!");
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(flJSON), SlideData.class);
    }

    public static class SlideData {
        public List<String> ticketsSlides;
        public List<String> teamSlides;

        // Optional: getters
        public List<String> getTicketsSlides() { return ticketsSlides; }
        public List<String> getTeamSlides() { return teamSlides; }
    }
}
