package com.customized.serenity.configs;

public class ParameterType {
    /**
     * Custom parameter: nếu giá trị bắt đầu bằng '@', sẽ đọc từ file config.
     */
    @io.cucumber.java.ParameterType(".*")
    public String str(String value) {
        if (value.startsWith("@")) {
            return LocatorResolver.resolve(value);
        }
        return value;
    }
}
