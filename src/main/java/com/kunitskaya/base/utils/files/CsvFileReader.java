package com.kunitskaya.base.utils.files;

import com.kunitskaya.test.selenium.Languages;
import com.kunitskaya.test.selenium.TranslationsElements;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.kunitskaya.test.selenium.Languages.ENGLISH;
import static com.kunitskaya.test.selenium.Languages.RUSSIAN;
import static com.kunitskaya.test.selenium.TranslationsElements.ELEMENT;

/**
 * Reads from .csv files
 */
public class CsvFileReader implements FileReader {
    private static final String TRANSLATIONS_CSV = "src/main/resources/files/translations.csv";

    /**
     * Reads content from the specified file
     * "\" is used to escape a comma in a string from file
     *
     * @param language - language to get translation for
     * @return - map with element : translation
     */
    @Override
    public Map<String, String> readFileContent(Languages language) {
        Map translations = new HashMap();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(TRANSLATIONS_CSV));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader(ELEMENT.name(), ENGLISH.name(), RUSSIAN.name())
                        .withIgnoreHeaderCase()
                        .withEscape('\\')
                        .withTrim())
        ) {
            for (CSVRecord record : csvParser) {
                translations.put(record.get(ELEMENT.name()), record.get(language.name()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translations;
    }

    /**
     * Retrieves a single translation for the specified element
     *
     * @param language - language to get translation to
     * @param element  - element to get translation for
     * @return - element translation
     */
    public String getTranslationForElement(Languages language, TranslationsElements element) {
        Map translations = readFileContent(language);
        return (String) translations.get(element.name());
    }
}
