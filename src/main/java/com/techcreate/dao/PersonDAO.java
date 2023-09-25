package com.techcreate.dao;

import com.techcreate.entity.Person;
import com.techcreate.properties.PropertiesLoader;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PersonDAO {

    public List<Person> retrieveDataToList(String fileName) throws IOException {
        if(fileName == null || fileName.length() == 0) {
            Properties prop = PropertiesLoader.loadProperties();
            fileName = prop.getProperty("relationship.fileName");
        }

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
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

    @Test
    public void testRetrieveDataToList() throws IOException {
        List<Person> list = retrieveDataToList("C:\\source\\techcreate\\src\\main\\resources\\interviewFindRelationship2.csv");
        List<Person> expectedList = new ArrayList<>();
        Person p = new Person();
        p.setDob("1920/02/28");
        p.setId("IC007");
        p.setName("James Bond");
        p.setUuid("001");
        expectedList.add(p);
        assertEquals(expectedList.toString(), list.toString());
    }
}
