package com.sourav.shoppingcart.controller;


import com.sourav.shoppingcart.model.Role;
import com.sourav.shoppingcart.model.User;
import com.sourav.shoppingcart.repository.userRepository.UserRepository;
import com.sourav.shoppingcart.request.LoginRequest;
import com.sourav.shoppingcart.request.RegisterRequest;
import com.sourav.shoppingcart.response.ApiResponse;
import com.sourav.shoppingcart.response.JwtResponse;
import com.sourav.shoppingcart.security.jwt.JwtUtils;
import com.sourav.shoppingcart.security.user.MyUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request){
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateTokeForUser(authentication);
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

            JwtResponse jwtResponse = new JwtResponse(userDetails.getId(),jwt);

            return ResponseEntity.ok(new ApiResponse("Login Successful" , jwtResponse));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request){
        try {
            if(!userRepository.existsByEmail(request.getEmail())){
                Role userRole = new Role("ROLE_USER");
                User user = new User();
                user.setFirstName(request.getFirstName());
                user.setLastName(request.getLastName());
                user.setEmail(request.getEmail());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setRoles(Set.of(userRole));
                userRepository.save(user);
                return ResponseEntity.ok(new ApiResponse("Registration Successful",null));
            }
            else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("User Already Registered !",null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error Occurred "+e.getMessage(),null));
        }

    }
}
