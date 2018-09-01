package com.kunitskaya.base.utils.files;

import com.kunitskaya.test.selenium.Languages;

import java.util.Map;

/**
 * Reads from content from files
 */
public interface FileReader {
    Map<String, String> readFileContent(Languages language);
}
