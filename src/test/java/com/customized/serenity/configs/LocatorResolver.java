package com.customized.serenity.configs;

public class LocatorResolver {

    /**
     * Resolve locator từ file YAML.
     * Trả về DynamicLocator → có thể dùng .of() hoặc dùng như String bình thường.
     */
    public static DynamicLocator resolve(String expr) {
        try {

            String[] parts = expr.split("\\.");

            if (parts.length != 2) {
                throw new RuntimeException("WRONG locator format: " + expr +
                        " → must be @fileName.key");
            }

            String fileName = parts[0] + ".yaml";
            String key = parts[1];

            String locator = YamlLocatorReader.getLocator(fileName, key);

            return new DynamicLocator(locator);

        } catch (Exception e) {
            throw new RuntimeException("Error resolving locator: " + expr, e);
        }
    }
}
