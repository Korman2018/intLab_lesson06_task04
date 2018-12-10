package task04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;

public class PhonesConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger("task04");
    private static final String ENCODING = "UTF-8";
    private static final String REGEXP = "\\+\\d\\(\\d{3}\\) \\d{3} \\d{2} \\d{2}";

    private PhonesConverter() {
    }

    public static void convert(File inputFile, File outputFile) {
        LOGGER.info("Start converting from file:{}", inputFile);
        String convertedString = convertDataString(getStringDataFromFile(inputFile));

        putStringDataToFile(convertedString, outputFile);
        LOGGER.info("End converting to file:{}", outputFile);
    }

    private static String getStringDataFromFile(File file) {
        try {
            return readFileToString(file, ENCODING);
        } catch (IOException e) {
            LOGGER.error("I/O exception file:{} method:get", file, e);
        }
        return "";
    }

    private static void putStringDataToFile(String data, File file) {
        try {
            writeStringToFile(file, data, ENCODING, false);
        } catch (IOException e) {
            LOGGER.error("I/O exception file:{} method:put", file, e);
        }
    }

    private static String convertDataString(String input) {
        Pattern pattern = Pattern.compile(REGEXP);
        Matcher matcher = pattern.matcher(input);

        StringBuilder convertedNumbers = new StringBuilder();

        while (matcher.find()) {
            String currentPhoneNumber = matcher.group().replaceAll("\\D", "");

            convertedNumbers.append(currentPhoneNumber).append("\n");
        }
        return convertedNumbers.toString();
    }
}
