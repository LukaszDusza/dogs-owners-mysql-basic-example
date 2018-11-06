package dao;

import connection.Connector;

import static constans.Constans.*;

import model.Dog;
import model.OwnerDto;
import repositories.DogRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DogDAO implements DogRepository {

    public List<Dog> getAllDogs() throws SQLException {

        Connection conn = Connector.getConnect(URL_REMOTE, "devlab", "devlab");
        //   Connection conn  = Connector.getConnect(URL_LOCALHOST, LOGIN, PASS);
        String query = "select * from dogs";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Dog> dogs = new ArrayList<>();


        while (resultSet.next()) {

            Dog dog = new Dog();

            //   System.out.println(resultSet.getString(2));

            dog.setId(resultSet.getInt(1));
            dog.setName(resultSet.getString(2));
            dog.setAge(resultSet.getInt(3));
            dog.setBreed(resultSet.getString(4));
            dog.setDate(resultSet.getDate(5));
            dog.setOwner_id(resultSet.getInt(6));

            dogs.add(dog);

//         dogs.add(new Dog(
//                 resultSet.getInt(1),
//                 resultSet.getString(2),
//                 resultSet.getInt(3),
//                 resultSet.getString(4),
//                 resultSet.getDate(5),
//                 resultSet.getInt(6)
//         ));

        }

//        for (Dog d: dogs) {
////            System.out.println(d); }

//            dogs.forEach(d -> {
//                String a = d.getName().toUpperCase();
//                System.out.println(a);
//            });
//

        dogs.forEach(System.out::println);

        conn.close();

        return dogs;
    }


    @Override //zapobiega pomylkom w impl. metody
    public List<Dog> getDogsByName(String name) throws SQLException {

        Connection conn = Connector.getConnect(URL_REMOTE, "devlab", "devlab");

        String query = "select * from dogs where name =" + "'" + name + "'";

        PreparedStatement preparedStatement = conn.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();

        List<Dog> dogs = new ArrayList<>();

        while (rs.next()) {

            Dog dog = new Dog();

            dog.setId(rs.getInt(1));
            dog.setName(rs.getString(2));
            dog.setAge(rs.getInt(3));
            dog.setBreed(rs.getString(4));
            dog.setDate(rs.getDate(5));
            dog.setOwner_id(rs.getInt(6));

            dogs.add(dog);

        }

        dogs.forEach(System.out::println);

        conn.close();

        return dogs;
    }

    @Override
    public void addNewDog(Dog dog) throws SQLException {

        Connection conn = Connector.getConnect(URL_REMOTE, "devlab", "devlab");

        String query = "insert into dogs(name, age, breed) values(?,?,?)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, dog.getName());
        preparedStatement.setInt(2, dog.getAge());
        preparedStatement.setString(3, dog.getBreed());

        conn.close();
    }

    public void updateDogName(String name, int id) throws SQLException {

        Connection conn = Connector.getConnect(URL_REMOTE, "devlab", "devlab");

        String query = "update dogs set name = ? where id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();

        conn.close();
    }

    public Dog getById(int id) throws SQLException {

        Connection conn = Connector.getConnect(URL_REMOTE, "devlab", "devlab");

        String query = "select * from dogs where id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        Dog dog = new Dog();

        dog.setId(resultSet.getInt(1));
        dog.setName(resultSet.getString(2));
        dog.setAge(resultSet.getInt(3));
        dog.setBreed(resultSet.getString(4));
        dog.setDate(resultSet.getDate(5));
        dog.setOwner_id(resultSet.getInt(6));

        conn.close();

        return dog;
    }

    public List<OwnerDto> getDogsWIthOwners() throws SQLException {

        Connection conn = Connector.getConnect(URL_REMOTE, "devlab", "devlab");

        final String QUERY_DOGS_NAME_BY_OWNER_ID =
                "select name from dogs where dogs.owner_id = (select id from owners where owners.name = ?)";

        final String QUERY_DISTINCT_OWNERS = "select distinct name from owners";

        PreparedStatement preparedStatement = conn.prepareStatement(QUERY_DISTINCT_OWNERS);

        ResultSet rs = preparedStatement.executeQuery();

        List<OwnerDto> owners = new ArrayList<>();

        while (rs.next()) {
            String owner = rs.getString(1);

            preparedStatement = conn.prepareStatement(QUERY_DOGS_NAME_BY_OWNER_ID);
            preparedStatement.setString(1, owner);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> dogsNames = new ArrayList<>();

            while (resultSet.next()) {
                dogsNames.add(resultSet.getString(1));
            }

            owners.add(new OwnerDto(owner, dogsNames));
        }

        owners.forEach(System.out::println);

        conn.close();

        return owners;
    }

    @Override
    public Dog findEldestDog() throws SQLException {

        Connection conn = Connector.getConnect(URL_REMOTE, "devlab", "devlab");

        String query = "select * from dogs where age = (select max(age) from dogs)";

        PreparedStatement preparedStatement = conn.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        Dog dog = new Dog();

        while (resultSet.next()) {

            dog.setId(resultSet.getInt(1));
            dog.setName(resultSet.getString(2));
            dog.setAge(resultSet.getInt(3));
        }

        conn.close();

        return dog;
    }

}

