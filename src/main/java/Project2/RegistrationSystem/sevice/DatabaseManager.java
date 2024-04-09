package Project2.RegistrationSystem.sevice;

import Project2.RegistrationSystem.model.DetailedUser;
import Project2.RegistrationSystem.model.EditingUserInfo;
import Project2.RegistrationSystem.model.User;
import Project2.RegistrationSystem.settings.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class DatabaseManager{
    Connection connection =  DriverManager.getConnection(Constants.DATABASE_URL, Constants.DATABASE_USER_NAME, Constants.DATABASE_PASSWORD);
    Statement statement = connection.createStatement();
    @Autowired
    UUIDGenerator uuidGenerator;

    public DatabaseManager() throws SQLException {
    }

    public void createNewUser(User user) throws Exception{
        if(!checkPersonId(user.getPersonId())){
            throw new Exception("Incorrect person id");
        }
        statement.execute("INSERT INTO userdata(Name, Surname, PersonID, Uuid) VALUES('"+
              user.getName() +"', '"+ user.getSurname() + "', '"+ user.getPersonId() + "', '"+
              uuidGenerator.generateRandomUUID() + "')");
    }

    public User getUserById(int id) throws SQLException{
        ResultSet resultSet= statement.executeQuery("SELECT * FROM userdata WHERE ID = '" + id + "'");
        resultSet.next();
        return new User(resultSet.getString("Name"), resultSet.getString("Surname"),
                resultSet.getString("PersonID"));
    }
    public DetailedUser getDetailedUserById(int id) throws SQLException{
        ResultSet resultSet= statement.executeQuery("SELECT * FROM userdata WHERE ID = '" + id + "'");
        resultSet.next();
        return new DetailedUser(resultSet.getString("Name"), resultSet.getString("Surname"),
                resultSet.getString("PersonID"), UUID.fromString(resultSet.getString("Uuid")),
                resultSet.getInt("ID"));
    }
    public List<Object> getAllUsers() throws SQLException{
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM userdata");
        while(resultSet.next()){
            userList.add(new User(resultSet.getString("Name"), resultSet.getString("Surname"),
                    resultSet.getString("PersonID")));
        }
        return Collections.singletonList(userList);
    }
    public List<Object> getAllDetailedUsers() throws SQLException{
        List<DetailedUser> detailedUserList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM userdata");
        while(resultSet.next()) {
            detailedUserList.add(new DetailedUser(resultSet.getString("Name"), resultSet.getString("Surname"),
                    resultSet.getString("PersonID"), UUID.fromString(resultSet.getString("Uuid")),
                    resultSet.getInt("ID")));
        }
        return Collections.singletonList(detailedUserList);
    }
    public void editUser(EditingUserInfo userInfo) throws SQLException{
        statement.execute("UPDATE userdata SET Name = '" + userInfo.getName() + "', Surname = '" +
                userInfo.getSurname() + "' WHERE ID = '" + userInfo.getId() + "'");
    }
    public void deleteUser(int id) throws SQLException{
        statement.execute("DELETE FROM userdata WHERE ID = '" + id + "'");
    }

    private boolean checkPersonId(String personId) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(Constants.PERSON_ID_FILE_PATH))){
            String correctPersonId;
            while((correctPersonId = br.readLine())!=null){
                if(personId.equals(correctPersonId)) return true;
            }
        }catch (Exception e){
            throw new Exception("Server error: couldn't check if person id is correct.");
        }
        return false;
    }
}
