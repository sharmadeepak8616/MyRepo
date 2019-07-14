package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private String url;
    private String chromeDriverPath;
    private String sauceUsername;
    private String sauceKey;
    private String environment;
    private String sauceBrowser;
    private String platform;
    private String version;

    public ConfigReader() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.url = prop.getProperty("url");
            this.chromeDriverPath = prop.getProperty("chrome_driver_path");
            this.sauceUsername = prop.getProperty("sauce_username");
            this.sauceKey = prop.getProperty("sauce_key");
            this.environment = prop.getProperty("run_test");
            this.sauceBrowser = prop.getProperty("browser");
            this.platform = prop.getProperty("platform");
            this.version = prop.getProperty("version");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getUrl() {

        return url;
    }

    public String getChromeDriverPath() {

        return chromeDriverPath;
    }

    public String getSauceUsername() {
        return sauceUsername;
    }

    public String getSauceKey() {
        return sauceKey;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getSauceBrowser() {
        return sauceBrowser;
    }

    public String getPlatform() {
        return platform;
    }

    public String getVersion() {
        return version;
    }
}
