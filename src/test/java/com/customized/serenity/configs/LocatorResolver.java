package com.customized.serenity.configs;

public class LocatorResolver {
    public static String elementAs(String expr) {
        try {

            String[] parts = expr.split("\\.");

            if (parts.length != 2) {
                throw new RuntimeException("WRONG locator format: " + expr +
                        " → must be @fileName.key");
            }

            String fileName = parts[0] + ".yaml";
            String key = parts[1];

            return YamlLocatorReader.getLocator(fileName, key);

        } catch (Exception e) {
            throw new RuntimeException("Error resolving locator: " + expr, e);
        }
    }
}
