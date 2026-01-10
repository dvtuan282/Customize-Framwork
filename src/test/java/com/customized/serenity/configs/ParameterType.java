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
            return LocatorResolver.resolve(value.substring(1)).toString();
        }
        else if (value.startsWith("$")) {
            // Resolver dữ liệu
            return DataResolver.resolve(value);
        }

        // Bỏ dấu nháy nếu có
        return value.replaceAll("^\"|\"$", "");
    }

}
