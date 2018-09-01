package com.kunitskaya.base.utils.files;

import com.kunitskaya.test.Languages;

import java.util.Map;

public interface FileReader {
    Map<String, String> readFileContent(Languages language);
}
