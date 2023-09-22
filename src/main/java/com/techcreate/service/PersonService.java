package com.techcreate.service;

import com.techcreate.entity.Person;
import com.techcreate.properties.PropertiesLoader;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

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
}
