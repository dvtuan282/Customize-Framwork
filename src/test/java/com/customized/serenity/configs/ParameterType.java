package com.customized.serenity.configs;

public class ParameterType {

    /**
     * Custom parameter:
     * - Nếu bắt đầu bằng '@' là xpath
     * - Nếu bắt đầu bằng '$' là data
     */
    @io.cucumber.java.ParameterType(".*")
    public String str(String value) {
        if (value.startsWith("@")) {
            return LocatorResolver.elementAs(value.substring(1));
        } else if (value.startsWith("$")) {
            return DataResolver.resolve(value);
        }
        // Loại bỏ dấu nháy nếu input bình thường: "abc"
        return value.replaceAll("^\"|\"$", "");
    }
}
