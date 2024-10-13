package com.thinkitdevit.perfsave.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is responsible for generating script data.
 * The file is genereted file data.sql in the src/main/resources directory of the project.
 * Each line is the form of INSERT INTO travel (id, destination, category) VALUES (1, 'destination1', 1);
 */
@Slf4j
public class ScriptDataGenerator {

    private static final String FILE_PATH = "src/main/resources/data.sql";

    private static final int NUMBER_OF_TRAVELS = 25000;

    public static void main(String[] args) {


        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {

            for(int i = 0; i < NUMBER_OF_TRAVELS; i++){
                int category = i % 2 + 1;
                writer.write("INSERT INTO travel (id, destination, category) VALUES ("
                        + i + ", 'Destination " + i + "', " + category + ");\n");
            }

        } catch (IOException e) {
            log.error("Error while writing to file", e);
        }

    }

}
