package esi.classmate.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esi.classmate.user.dto.CourseDto;
import esi.classmate.user.dto.UserCourseDto;
import esi.classmate.user.dto.UserDto;
import esi.classmate.user.jwt.JwtService;
import esi.classmate.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
// @CrossOrigin(origins = "*")
@CrossOrigin(origins = { "http://localhost:8082" })
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    // a new end-point that allows users to authenticate themselves and generate the
    // jwt token
    // This endpoint will receive the userDto, authenticate her/him with existing
    // users in the database, then if authenticated, it will create the jwt
    // @PostMapping("/authenticate")
    // public String authenticateAndGetToken(@RequestBody UserDto userDto) {

    // // authenticationManager.authenticate attempts to authenticate the passed
    // // Authentication object, returning a fully populated Authentication object
    // // (including granted authorities) if successful.
    // // UsernamePasswordAuthenticationToken can be used by the
    // authenticationManager
    // // and we are passing the user name and password to it.
    // // To use the authenticationManager, you need to define a Bean for it, check
    // // SecurityConfig.java, it is defined there.
    // // Note that verifying the user is a required before generating the token,
    // // otherwise, we will be generating tokens for users that we cannot
    // authenticate

    // Authentication authentication = authenticationManager
    // .authenticate(new UsernamePasswordAuthenticationToken(userDto.getName(),
    // userDto.getPassword()));
    // // If the user is authenticated we generate the token, otherwise, we throw an
    // // exception
    // log.info("authentication.isAuthenticated() {} ", authentication);

    // if (authentication.isAuthenticated()) {
    // log.info("jwtService.generateToken(authRequest.getName()) {} ",
    // jwtService.generateToken(userDto.getName()).toString());
    // return jwtService.generateToken(userDto.getName());
    // } else {
    // throw new UsernameNotFoundException("The user cannot be authenticated");
    // }
    // }

    @PostMapping("/login")
    public String logInAndGetToken(@RequestBody UserDto userDto) {

        if (userDto.getName() == null || userDto.getPassword() == null) {
            throw new UsernameNotFoundException("UserName or Password is Empty");
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword()));
        // If the user is authenticated we generate the token, otherwise, we throw an
        // exception
        // log.info("authentication.isAuthenticated() {} ", authentication);

        if (authentication.isAuthenticated()) {
            log.info("jwtService.generateToken(authRequest.getName())  {} ",
                    jwtService.generateToken(userDto.getName()).toString());
            return jwtService.generateToken(userDto.getName());
        } else {
            throw new UsernameNotFoundException("The user cannot be authinticated!");
        }
    }

    @GetMapping("/authenticate")
    public Boolean authenticate(@RequestHeader("Authorization") String header) {
        String token = header.replace("Bearer ", "");
        log.info(" authenticate - token {} ", token);
        return jwtService.validateToken(token);
    }

    @GetMapping("/admin")
    public String adminAPI() {
        return "Protected endpoint - only admins are allowed";
    }

    @GetMapping("/USER")
    public String StudentAPI() {
        return "Protected endpoint - only students are allowed";
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<UserDto> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/users")
    public String signUp(@RequestBody UserDto userDto) {
        userService.signup(userDto);
        String jwtToken = jwtService.generateToken(userDto.getName());
        return jwtToken;

    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/allcourses")
    public List<CourseDto> getAllCourses() {
        return userService.getAllCourses();
    }

    @PostMapping(value = "users/enroll")
    public ResponseEntity<String> enrolltoACourse(@RequestBody UserCourseDto userCourseDto) {
        log.info("enrolltoACourse: {}", userCourseDto);
        userService.enrolltoACourse(userCourseDto);
        return ResponseEntity.ok("Enrolled Successfully");

    }

    @GetMapping("/users/courses/{id}")
    public List<UserCourseDto> getCoursesByUserId(@PathVariable Long id) {
        return userService.getCoursesByUserId(id);
    }

}
