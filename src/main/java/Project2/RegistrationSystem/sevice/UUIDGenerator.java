package Project2.RegistrationSystem.sevice;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDGenerator {

    public UUID generateRandomUUID(){
        return UUID.randomUUID();
    }
}
