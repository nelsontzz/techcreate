import com.techcreate.entity.Person;
import com.techcreate.properties.PropertiesLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {


    public static List<Person> convertToList(final String RELATIONSHIP_FILE) throws IOException {
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

    public static void analyzePersons(List<Person> listPersons, int MAX_SIMILARITY) {
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
        produceOutput(listPersons);
    }

    private static void produceOutput(List<Person> listPersons) {
        for(int i=0; i<listPersons.size(); i++) {
            StringBuffer strBuff = new StringBuffer();
            Person p1 = listPersons.get(i);
            if(p1.getUuidSimilar() != null) {
                continue;
            }
            strBuff.append("Record "+p1.getUuid());
            for(int j=i+1; j<listPersons.size(); j++) {
                Person p2 = listPersons.get(j);
                if(p1.getUuid().equals(p2.getUuidSimilar())) {
                    strBuff.append(", "+p2.getUuid());
                }
            }
            if(strBuff.toString().length() > 10) {
                strBuff.append(" are the same");
                System.out.println(strBuff.toString());
            }
        }
    }

    private static int checkSimilarities(Person p1, Person p2) {
        int similarities = 0;
        if(p1.getName().equals(p2.getName())) similarities++;
        if(p1.getId().equals(p2.getId())) similarities++;
        if(p1.getDob().equals(p2.getDob())) similarities++;

        return similarities;
    }
    public static void main(String[] args) throws Exception {
        Properties prop = PropertiesLoader.loadProperties();
        final String RELATIONSHIP_FILE = prop.getProperty("relationship.fileName");
        final int MAX_SIMILARITY = Integer.valueOf(prop.getProperty("similarity.threshold"));

        List<Person> listPersons = convertToList(RELATIONSHIP_FILE);
        analyzePersons(listPersons, MAX_SIMILARITY);
    }
}