import com.techcreate.dao.PersonDAO;
import com.techcreate.entity.Person;
import com.techcreate.service.PersonService;
import com.techcreate.view.PersonView;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonDAO dao = new PersonDAO();
        List<Person> listPersons = dao.retrieveDataToList(null);

        PersonService service = new PersonService();
        service.analyzePersons(listPersons);

        PersonView view = new PersonView();
        System.out.println(view.produceOutput(listPersons));
    }
}