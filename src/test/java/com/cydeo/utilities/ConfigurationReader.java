package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties = new Properties();
    //I made it private so that it can only be reached from the class - I want to make it inaccessible outside the class
    //I made it static so that it is run before anything else

    //Before Java starts running anything, my static code static block runs before an
    //If my code doesn't read from configuration.properties what type of browser it is supposed to be opening, my code
    //won't work properly.
    //Static block helps us to run our logic before anything else, it's gonna be running once.
    static {
        try {
            //We need to open the file in Java memory: FileInputStream
            FileInputStream file = new FileInputStream("configuration.properties");

            //Load the properties using the FileInputStream object
            properties.load(file);

            //Close the file. It's better to close it after use. Rather than the garbage collector, you control it
            file.close();

        } catch (IOException e) {
            System.out.println("File not found in the ConfigurationReader class");
            e.printStackTrace();
        }
    }

    public static String getProperty(String keyword) {

        return properties.getProperty(keyword);
    }

}
