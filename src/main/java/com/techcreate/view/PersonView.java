package com.techcreate.view;

import com.techcreate.entity.Person;

import java.util.List;

public class PersonView {
    public void produceOutput(List<Person> listPersons) {
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
                System.out.println(new StringBuffer("Record "+p1.getUuid()).append(strBuff).append(" are the same"));
            }
        }
    }
}
