package com.hexaware.electronicgadgets.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    private static final String PROPERTY_FILE = "src/db.properties"; // adjust if needed

    public static String getPropertyString() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTY_FILE)) {
            props.load(fis);

            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String db = props.getProperty("dbname");
            String user = props.getProperty("username");
            String password = props.getProperty("password");

            return "jdbc:mysql://" + host + ":" + port + "/" + db +
                   "?user=" + user + "&password=" + password;
        } catch (IOException e) {
            System.out.println("Error loading DB properties: " + e.getMessage());
        }
        return null;
    }
}
