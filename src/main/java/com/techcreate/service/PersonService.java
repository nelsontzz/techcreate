package com.techcreate.service;

import com.techcreate.entity.Person;
import com.techcreate.properties.PropertiesLoader;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PersonService {

    public void analyzePersons(List<Person> listPersons) throws IOException {
        Properties prop = PropertiesLoader.loadProperties();
        final int MAX_SIMILARITY = Integer.valueOf(prop.getProperty("similarity.threshold"));

        int similarities = 0;
        for(int i=0; i<listPersons.size(); i++) {
            Person p1 = listPersons.get(i);
            if(p1.getUuidSimilar() != null) {
                continue;
            }
            for(int j=i+1; j<listPersons.size(); j++) {
                Person p2 = listPersons.get(j);
                similarities = checkSimilarities(p1, p2);

                if(similarities >= MAX_SIMILARITY) {
                    //System.out.println("Record "+p1.getUuid()+" and "+p2.getUuid()+" are the same");
                    p2.setUuidSimilar(p1.getUuid());
                }
            }
        }
    }

    private int checkSimilarities(Person p1, Person p2) {
        int similarities = 0;
        if(p1.getName().equals(p2.getName())) similarities++;
        if(p1.getId().equals(p2.getId())) similarities++;
        if(p1.getDob().equals(p2.getDob())) similarities++;

        return similarities;
    }

    @Test
    public void testCheckSimilarities() {
        Person p1 = new Person();
        p1.setUuid("1");
        p1.setName("Name1");
        p1.setId("1111");
        p1.setDob("2000/01/01");

        Person p2 = new Person();
        p2.setUuid("2");
        p2.setName("Name1");
        p2.setId("1111");
        p2.setDob("2000/01/01");

        Person p3 = new Person();
        p3.setUuid("3");
        p3.setName("Name");
        p3.setId("11");
        p3.setDob("2000/01/01");

        int p1Vsp2 = checkSimilarities(p1,p2);
        int p1Vsp3 = checkSimilarities(p1,p3);
        int p2Vsp3 = checkSimilarities(p2,p3);

        assertEquals(3, p1Vsp2);
        assertEquals(1, p1Vsp3);
        assertEquals(1, p2Vsp3);
    }

    @Test
    public void testAnalyzePersons() throws IOException {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person();
        p1.setUuid("1");
        p1.setName("Name1");
        p1.setId("1111");
        p1.setDob("2000/01/01");

        Person p2 = new Person();
        p2.setUuid("2");
        p2.setName("Name1");
        p2.setId("1111");
        p2.setDob("2000/01/01");

        Person p3 = new Person();
        p3.setUuid("3");
        p3.setName("Name");
        p3.setId("11");
        p3.setDob("2000/01/01");

        list.add(p1);
        list.add(p2);
        list.add(p3);

        List<Person> expectedList = new ArrayList<>();
        Person testp1 = new Person();
        testp1.setUuid("1");
        testp1.setName("Name1");
        testp1.setId("1111");
        testp1.setDob("2000/01/01");

        Person testp2 = new Person();
        testp2.setUuid("2");
        testp2.setName("Name1");
        testp2.setId("1111");
        testp2.setDob("2000/01/01");
        testp2.setUuidSimilar("1");

        Person testp3 = new Person();
        testp3.setUuid("3");
        testp3.setName("Name");
        testp3.setId("11");
        testp3.setDob("2000/01/01");

        expectedList.add(testp1);
        expectedList.add(testp2);
        expectedList.add(testp3);

        analyzePersons(list);
        assertEquals(expectedList.toString(), list.toString());
    }
}
