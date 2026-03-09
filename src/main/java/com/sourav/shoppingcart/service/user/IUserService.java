package com.sourav.shoppingcart.service.user;

import com.sourav.shoppingcart.dto.UserDto;
import com.sourav.shoppingcart.model.User;
import com.sourav.shoppingcart.request.CreateUserRequest;
import com.sourav.shoppingcart.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request , Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();
}
