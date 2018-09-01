package com.kunitskaya.base.utils.files;

import com.kunitskaya.test.Languages;
import com.kunitskaya.test.TranslationsElements;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.kunitskaya.test.Languages.ENGLISH;
import static com.kunitskaya.test.Languages.RUSSIAN;
import static com.kunitskaya.test.TranslationsElements.ELEMENT;

public class CsvFileReader implements FileReader {
    private static final String TRANSLATIONS_CSV = "src/main/resources/files/translations.csv";

    @Override
    public Map<String, String> readFileContent(Languages language) {
        Map translations = new HashMap();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(TRANSLATIONS_CSV));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader(ELEMENT.name(), RUSSIAN.name(), ENGLISH.name())
                        .withIgnoreHeaderCase()
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

    public String getTranslationForElement(Languages language, TranslationsElements element) {
        Map translations = readFileContent(language);
        return (String) translations.get(element.name());

    }
}
