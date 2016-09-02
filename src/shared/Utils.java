/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author josephawwal
 */
public class Utils {
    
    
    public static Properties initProperties(String propertyFile){
        
        Properties properties = new Properties();
        
        
        try (InputStream is = new FileInputStream(propertyFile)){
            
            properties.load(is);
            
        } catch (IOException ex){
            
            
            System.out.println(String.format("Could not locate the %1$s file", propertyFile));
        return null;
        
        }
        
        return properties;
    }
    
    public static void setLogFile(String logFile, String className){
        
        try {
            
            Logger log = Logger.getLogger(className);
            FileHandler fileTxt = new FileHandler(logFile);
            java.util.logging.Formatter formatTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatTxt);
            log.addHandler(fileTxt);
            
        } catch (IOException | SecurityException ex){
            
            Logger.getLogger(className).log(Level.SEVERE, null, ex);
            
        }
    }
}
