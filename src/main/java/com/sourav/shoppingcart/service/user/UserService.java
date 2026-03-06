package com.sourav.shoppingcart.service.user;

import com.sourav.shoppingcart.Exception.AlreadyExistsException;
import com.sourav.shoppingcart.Exception.ResourceNotFoundException;
import com.sourav.shoppingcart.dto.UserDto;
import com.sourav.shoppingcart.model.User;
import com.sourav.shoppingcart.repository.userRepository.UserRepository;
import com.sourav.shoppingcart.request.CreateUserRequest;
import com.sourav.shoppingcart.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user Not Found !"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user->!userRepository.existsByEmail(request.getEmail()))
                .map(req->{
                        User user = new User();
                        user.setFirstName(request.getFirstName());
                        user.setLastName(request.getLastName());
                        user.setEmail(request.getEmail());
                        user.setPassword(request.getPassword());
                        return userRepository.save(user);
                }).orElseThrow(()->new AlreadyExistsException("Oops! "+request.getEmail() +" already exists!"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepository.findById(userId).map(exixtingUser ->{
            exixtingUser.setFirstName(request.getFirstName());
            exixtingUser.setLastName(request.getLastName());
            return userRepository.save(exixtingUser);
        }).orElseThrow(()->new ResourceNotFoundException("User Not Found !"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete,()-> {
            throw new ResourceNotFoundException("User Not Found !");
        }
        );
    }

    @Override
    public UserDto convertUserToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
}
