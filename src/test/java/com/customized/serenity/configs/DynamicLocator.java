package com.customized.serenity.configs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicLocator {

    private final String rawXpath;
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{(\\d+)\\}");

    public DynamicLocator(String rawXpath) {
        this.rawXpath = rawXpath;
    }

    /**
     * Replace số lượng tham số động theo placeholder {index}.
     * Nếu thiếu param => THROW exception rõ ràng.
     */
    public String of(Object... params) {

        // 1) Tìm tất cả placeholder trong xpath
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(rawXpath);

        int maxIndexFound = -1;

        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1));
            if (index > maxIndexFound) {
                maxIndexFound = index;
            }
        }

        // 2) Kiểm tra số lượng tham số có đủ không
        if (maxIndexFound >= params.length) {
            throw new IllegalArgumentException(
                    String.format("Missing parameter for locator '%s'. Expected at least %d params, but got %d",
                            rawXpath, (maxIndexFound + 1), params.length)
            );
        }

        // 3) Replace các placeholder
        String result = rawXpath;
        for (int i = 0; i < params.length; i++) {
            result = result.replace("{" + i + "}", String.valueOf(params[i]));
        }

        return result;
    }

    @Override
    public String toString() {
        return rawXpath;
    }
}
