/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wittakarn.pure.client.oracle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Wittakarn
 */
public class WorkflowConfig {

    public static final String EJB_URL = "ejb.url";
    public static final String LEV_WSDL = "lev.wsdl";
    public static final String TRN_WSDL = "trn.wsdl";
    public static final String WLS_PASSWORD = "wls.password";
    protected static Properties workflowProperties;

    public static void init(String resourcePath) throws IOException {
        workflowProperties = new Properties();

        if (resourcePath != null) {

            FileInputStream sfis = new FileInputStream(resourcePath);
            workflowProperties.load(sfis);

            sfis.close();
        }
    }

    public static String getStringValue(String key) {
        if (workflowProperties != null) {
            if (workflowProperties.containsKey(key)) {
                return workflowProperties.getProperty(key);
            } else {
                return null;
            }
        } else {
            // throw new RuntimeException("Config Not Initial");
            return null;
        }
    }

    public static String getString(String key) {
        return getStringValue(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(getStringValue(key));
    }
}
