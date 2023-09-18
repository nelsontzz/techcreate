import com.techcreate.entity.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader("C:\\source\\techcreate\\src\\interviewFindRelationship.csv"));
        List<Person> listPersons = new ArrayList<Person>();
        String line = "";
        while((line = reader.readLine()) != null) {
            String[] lineSplitted = line.split(",");
            Person person = new Person();
            person.uuid = lineSplitted[0];
            person.name = lineSplitted[1];
            person.id = lineSplitted[2]+lineSplitted[3];
            person.dob = lineSplitted[4];
            listPersons.add(person);
        }

        int similarities = 0;
        for(int i=0; i<listPersons.size(); i++) {
            for(int j=i+1; j<listPersons.size(); j++) {
                Person p1 = listPersons.get(i), p2 = listPersons.get(j);
                if(p1.name.equals(p2.name)) similarities++;
                if(p1.id.equals(p2.id)) similarities++;
                if(p1.dob.equals(p2.dob)) similarities++;

                if(similarities >= 2) {
                    System.out.println("Record "+p1.uuid+" and "+p2.uuid+" are the same");
                }

                similarities = 0;
            }
        }
    }
}