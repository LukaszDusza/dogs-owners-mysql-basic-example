import dao.DogDAO;
import model.Dog;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DogDAO dogDAO = new DogDAO();

        try {
          //  dogDAO.getDogsByName("Burek");
          //  dogDAO.addNewDog(new Dog("Testowyt pies", 10, "Kudladek"));
            //dogDAO.getAllDogs();

            dogDAO.getDogsWIthOwners();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
