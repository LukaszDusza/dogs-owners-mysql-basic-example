package repositories;

import model.Dog;
import model.OwnerDto;

import java.sql.SQLException;
import java.util.List;

public interface DogRepository {

     List<Dog> getAllDogs() throws SQLException;

     List<Dog> getDogsByName(String name) throws SQLException;

     void addNewDog(Dog dog) throws SQLException;

     void updateDogName(String name, int id) throws SQLException;

     Dog getById(int id) throws SQLException;

     List<OwnerDto> getDogsWIthOwners() throws SQLException;


}
