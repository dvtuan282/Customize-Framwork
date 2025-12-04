package com.customized.serenity.configs;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class DataResolver {

    private static final Logger log = LogManager.getLogger(DataResolver.class);
    private static Config config;

    /**
     * Load file config từ folder dataTest
     *
     * @param fileName tên file config, ví dụ: "testData.conf"
     */
    public static void load(String fileName) {
        String resourcePath = "dataTest/" + fileName;
        File file = new File("src/test/resources/" + resourcePath);

        if (!file.exists()) {
            throw new RuntimeException("[ERROR] Không tìm thấy file config: " + resourcePath);
        }

        log.info("Read file data test: {}", file.getAbsolutePath());
        Config newConfig = ConfigFactory.parseFile(file).resolve();
        mergeConfig(newConfig);
    }

    /**
     * Merge nhiều file config (load nhiều file liên tiếp)
     */
    private static void mergeConfig(Config newConfig) {
        if (config == null) {
            config = newConfig;
        } else {
            config = newConfig.withFallback(config).resolve();
        }
    }

    /**
     * Lấy giá trị từ config. Nếu bắt đầu bằng '$', sẽ đọc từ file config đã load.
     * Hỗ trợ nested key: $productAddToCard.productName
     *
     * @param input có thể là "$key" hoặc giá trị gốc
     * @return giá trị thực tế
     */
    public static String resolve(String input) {
        if (input == null) return null;

        // Bỏ dấu nháy kép nếu có
        input = input.replace("\"", "");

        // Nếu không bắt đầu bằng $ => trả về giá trị gốc
        if (!input.startsWith("$")) return input;

        if (config == null) {
            throw new RuntimeException("[ERROR] Chưa load file config nào! Gọi load(fileName) trước.");
        }

        // Remove $
        String key = input.substring(1);

        if (config.hasPath(key)) {
            return config.getString(key);
        }

        throw new RuntimeException("[ERROR] Không tìm thấy key trong config: " + key);
    }

    /**
     * Clear cache config (nếu muốn reload file khác)
     */
    public static void clear() {
        config = null;
    }
}
