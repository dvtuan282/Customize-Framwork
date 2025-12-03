package com.customized.serenity.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class YamlLocatorReader {
    private static final Logger log = LogManager.getLogger(YamlLocatorReader.class);

    private static final Map<String, Map<String, Object>> cache = new HashMap<>();
    // SỬ DỤNG YAMLFactory thay vì ObjectMapper mặc định
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static String getLocator(String fileName, String key) {

        try {
            Map<String, Object> yaml = loadYaml(fileName);

            Object value = yaml.get(key);
            if (value == null) {
                throw new RuntimeException("Key NOT found: " + key + " in file " + fileName);
            }

            String platform = System.getProperty("platform", "android");

            @SuppressWarnings("unchecked")
            String locator = ((Map<String, String>) value).get(platform);

            if (locator == null) {
                throw new RuntimeException("Platform: " + platform +
                        " not mapped for key: " + key + " in file " + fileName);
            }

            return locator;

        } catch (Exception e) {
            log.error("Error reading locator: {} from {}", key, fileName, e);
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> loadYaml(String fileName) {

        if (cache.containsKey(fileName)) {
            return cache.get(fileName);
        }

        try (InputStream is = YamlLocatorReader.class.getClassLoader()
                .getResourceAsStream("pages/" + fileName)) {

            if (is == null) {
                throw new RuntimeException("YAML file NOT found: " + fileName);
            }

            // Đọc YAML bằng ObjectMapper với YAMLFactory
            Map<String, Object> data = mapper.readValue(is, Map.class);

            cache.put(fileName, data);

            return data;

        } catch (Exception e) {
            log.error("Error loading YAML: {}", fileName, e);
            throw new RuntimeException(e);
        }
    }
}
