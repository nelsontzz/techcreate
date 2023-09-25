package com.techcreate.view;

import com.techcreate.entity.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonView {
    public String produceOutput(List<Person> listPersons) {
        StringBuffer finalString = new StringBuffer();
        for(int i=0; i<listPersons.size(); i++) {
            StringBuffer strBuff = new StringBuffer();
            Person p1 = listPersons.get(i);
            if(p1.getUuidSimilar() != null) {
                continue;
            }
            for(int j=i+1; j<listPersons.size(); j++) {
                Person p2 = listPersons.get(j);
                if(p1.getUuid().equals(p2.getUuidSimilar())) {
                    strBuff.append(", "+p2.getUuid());
                }
            }
            if(strBuff.toString().length() > 0) {
                finalString.append(new StringBuffer("Record "+p1.getUuid()).append(strBuff).append(" are the same\n"));
            }
        }

        return finalString.toString();
    }

    @Test
    public void testProduceOutput() {
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
        p2.setUuidSimilar("1");

        Person p3 = new Person();
        p3.setUuid("3");
        p3.setName("Name");
        p3.setId("11");
        p3.setDob("2000/01/01");

        list.add(p1);
        list.add(p2);
        list.add(p3);

        String resultString = produceOutput(list);
        String expectedString = "Record 1, 2 are the same\n";

        assertEquals(expectedString, resultString);
    }
}
