package Project2.RegistrationSystem.controller;

import Project2.RegistrationSystem.model.EditingUserInfo;
import Project2.RegistrationSystem.model.User;
import Project2.RegistrationSystem.sevice.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("api/v1/")
public class UserController{
    @Autowired
    DatabaseManager databaseManager;
    @PostMapping("user")
    public String createUser(@RequestBody User user){
        try {
            databaseManager.createNewUser(user);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
        return "Created new user";
    }
    @GetMapping("user/{id}")
    public Object getUser(
            @PathVariable("id") String idAsString,
            @RequestParam(value = "detail", required = false) boolean detailed
    ){
        int id = Integer.parseInt(idAsString.split("\\{")[1].split("}")[0]);
        try {
            if(detailed) return databaseManager.getDetailedUserById(id);
            else return databaseManager.getUserById(id);
        } catch (SQLException e) {
            return e.getLocalizedMessage();
        }
    }
    @GetMapping("users")
    public List<Object> getUsers(
            @RequestParam(value = "detail", required = false) boolean detailed
    ){
        try {
            if(detailed) return databaseManager.getAllDetailedUsers();
            else return databaseManager.getAllUsers();
        } catch (SQLException e) {
            return Collections.singletonList(e.getLocalizedMessage());
        }
    }
    @PutMapping("user")
    public String editUser(@RequestBody EditingUserInfo userInfo){
        try{
            databaseManager.editUser(userInfo);
        }catch (SQLException e){
            return e.getLocalizedMessage();
        }
        return "User info changed";
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable(value = "id", required = true) String idAsString){
        System.out.println(idAsString);
        int id = Integer.parseInt(idAsString.split("\\{")[1].split("}")[0]);
        System.out.println(idAsString + id);
        try {
            databaseManager.deleteUser(id);
        } catch (SQLException e) {
            return e.getLocalizedMessage();
        }
        return "User was deleted.";
    }

}
