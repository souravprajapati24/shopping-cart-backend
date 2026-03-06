package com.sourav.shoppingcart.data;

import com.sourav.shoppingcart.model.User;
import com.sourav.shoppingcart.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){
        createDefaultUserIfNotExist();
    }

    private void createDefaultUserIfNotExist() {
        for(int i=0; i<5; i++){
            String defaultEmail = "user"+i+"@gmail.com";
            if(userRepository.existsByEmail(defaultEmail)){
                continue;
            }
            User user =  new User();
            user.setFirstName("The user");
            user.setLastName("User"+i);
            user.setEmail(defaultEmail);
            user.setPassword("123456");
            userRepository.save(user);
            System.out.println("Default vet user "+i+" created successfully. ");
        }
    }
}
