package com.loan.app.rest.controller;

import com.loan.app.rest.dto.TokenDtos.AuthRequestDto;
import com.loan.app.rest.dto.TokenDtos.AuthResponse;
import com.loan.app.util.JwtUtil;
import com.loan.app.service.impl.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.loan.app.service.AuthService;
import com.loan.app.rest.dto.TokenDtos.SignupRequest;
import com.loan.app.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;

import com.loan.app.rest.dto.TokenDtos.UserDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequest) {

        log.info("Login attempt for user: {}", authRequest.getUsername());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            log.info("User authenticated successfully: {}", authRequest.getUsername());

            // Load user details
            final UserDetails userDetails =
                    userDetailsService.loadUserByUsername(authRequest.getUsername());

            // Generate JWT
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            log.info("JWT token generated for user: {}", authRequest.getUsername());

            return ResponseEntity.ok(new AuthResponse(jwt));

        } catch (BadCredentialsException e) {

            log.warn("Invalid login attempt for user: {}", authRequest.getUsername());

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Incorrect username or password");

        } catch (Exception e) {

            log.error("Unexpected error during login for user: {}",
                    authRequest.getUsername(), e);

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }



    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {

        log.info("Signup attempt for user: {}", signupRequest.getUsername());

        try {

            if (signupRequest.getUsername() == null || signupRequest.getPassword() == null) {
                log.warn("Signup failed: Missing username or password");
                return ResponseEntity.badRequest()
                        .body("username and password must be provided");
            }

            authService.signup(
                    signupRequest.getUsername(),
                    signupRequest.getPassword()
            );

            log.info("User registered successfully: {}", signupRequest.getUsername());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("User created");

        } catch (UsernameAlreadyExistsException ex) {

            log.warn("Signup failed - username already exists: {}",
                    signupRequest.getUsername());

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());

        } catch (IllegalArgumentException ex) {

            log.warn("Signup validation error: {}", ex.getMessage());

            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());

        } catch (Exception ex) {

            log.error("Unexpected error during signup", ex);

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }




    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        log.info("Fetching all users");

        List<UserDto> users = authService.getAllUsers();

        log.info("Total users fetched: {}", users.size());

        return ResponseEntity.ok(users);
    }
}
//Get all user endpoint and the logic should in the service layer and the endpoint should be /auth/users
// and the response should be a list of user objects with id and username only
//this is protected endpoint and should be accessible only by authenticated users