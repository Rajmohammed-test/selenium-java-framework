package com.company.framework.testdata;

import com.company.framework.constants.FrameworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reads simple key/value test data from {@code .properties} files under
 * {@code src/main/resources/testdata/} - a lighter alternative to
 * {@link ExcelUtils} for data that doesn't need Excel's row/column shape.
 *
 * <p>Use {@link ExcelUtils} when you have genuinely tabular, multi-row data
 * (a table of login credentials, a set of product/quantity/expected-total
 * rows for a {@code @DataProvider}). Use this class when you just need a
 * handful of named values for one test - a search term, a product name, an
 * expected price - and hand-editing an Excel file for that is more friction
 * than it's worth. Both read from the same {@code testdata/} folder
 * ({@link FrameworkConstants#TEST_DATA_PATH}); pick whichever file format
 * fits the shape of the data you actually have.
 *
 * <p>Each file is loaded once and cached in memory ({@link ConcurrentHashMap},
 * safe under parallel test execution) - repeated calls for the same file
 * across many tests don't re-hit disk.
 *
 * <p>Example file, {@code src/main/resources/testdata/cartdata.properties}:
 * <pre>{@code
 * product=Samsung
 * item=Vivo
 * expectedQuantity=1
 * }</pre>
 * Usage:
 * <pre>{@code
 * String item = TestDataUtils.getData("cartdata", "item");   // "Vivo"
 * }</pre>
 */
public final class TestDataUtils {

    private static final Logger log = LogManager.getLogger(TestDataUtils.class);
    private static final Map<String, Properties> FILE_CACHE = new ConcurrentHashMap<>();

    private TestDataUtils() {
        throw new UnsupportedOperationException("TestDataUtils is a static utility class and cannot be instantiated");
    }

    /**
     * Reads one value from a test data file.
     *
     * @param fileName file name with or without the {@code .properties} extension,
     *                  e.g. {@code "cartdata"} or {@code "cartdata.properties"} - both work
     * @param key       the property key to read
     * @throws IllegalArgumentException if the key isn't present in the file - fails fast rather
     *                                   than silently returning null into a test assertion
     */
    public static String getData(String fileName, String key) {
        Properties properties = loadFile(fileName);
        String value = properties.getProperty(key);
        if (value == null) {
            log.error("Key [{}] not found in test data file [{}]", key, fileName);
            throw new IllegalArgumentException(
                    "Key [" + key + "] was not found in test data file [" + normalizeFileName(fileName) + "]");
        }
        return value.trim();
    }

    /** Same as {@link #getData(String, String)} but returns {@code defaultValue} instead of throwing. */
    public static String getData(String fileName, String key, String defaultValue) {
        Properties properties = loadFile(fileName);
        return properties.getProperty(key, defaultValue).trim();
    }

    /**
     * Returns every key/value pair in the file - useful when a test wants to
     * iterate all entries rather than pull them out one at a time.
     */
    public static Map<String, String> getAllData(String fileName) {
        Properties properties = loadFile(fileName);
        Map<String, String> data = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            data.put(key, properties.getProperty(key).trim());
        }
        return Collections.unmodifiableMap(data);
    }

    /**
     * Clears the in-memory cache for one file (or call with no matching entry -
     * harmless no-op). Rarely needed in normal use; exists mainly for tests
     * that intentionally rewrite a data file mid-suite and need the next read
     * to pick up the change rather than the cached copy.
     */
    public static void clearCache(String fileName) {
        FILE_CACHE.remove(normalizeFileName(fileName));
    }

    private static Properties loadFile(String fileName) {
        String normalizedName = normalizeFileName(fileName);
        return FILE_CACHE.computeIfAbsent(normalizedName, name -> {
            String path = FrameworkConstants.TEST_DATA_PATH + name;
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream(path)) {
                properties.load(fis);
                log.info("Loaded test data file [{}] with [{}] entries", path, properties.size());
            } catch (IOException e) {
                log.error("Failed to load test data file [{}]", path, e);
                throw new IllegalStateException("Unable to load test data file: " + path
                        + " (expected under src/main/resources/testdata/)", e);
            }
            return properties;
        });
    }

    private static String normalizeFileName(String fileName) {
        return fileName.endsWith(".properties") ? fileName : fileName + ".properties";
    }
}
