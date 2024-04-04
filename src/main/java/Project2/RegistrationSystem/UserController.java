package Project2.RegistrationSystem;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("api/v1/user")
    public void createUser(){

    }
}
