package com.techcreate.dao;

import com.techcreate.entity.Person;
import com.techcreate.properties.PropertiesLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDAO {

    public List<Person> retreveDataToList() throws IOException {
        Properties prop = PropertiesLoader.loadProperties();
        final String RELATIONSHIP_FILE = prop.getProperty("relationship.fileName");

        BufferedReader reader = new BufferedReader(new FileReader(RELATIONSHIP_FILE));
        List<Person> listPersons = new ArrayList<Person>();
        String line = "";
        while((line = reader.readLine()) != null) {
            String[] lineSplitted = line.split(",");
            Person person = new Person();
            person.setUuid(lineSplitted[0]);
            person.setName(lineSplitted[1]);
            person.setId(lineSplitted[2]+lineSplitted[3]);
            person.setDob(lineSplitted[4]);
            listPersons.add(person);
        }

        return listPersons;
    }
}
