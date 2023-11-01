package esi.classmate.user.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import esi.classmate.user.repository.EnrollmentRepository;
import esi.classmate.user.repository.UserRepository;
import esi.classmate.user.dto.CourseDto;
import esi.classmate.user.dto.EnrollNotificationDto;
import esi.classmate.user.dto.UserCourseDto;
import esi.classmate.user.dto.UserDto;
import esi.classmate.user.model.EnrollmentStatus;
import esi.classmate.user.model.NotificationType;
import esi.classmate.user.model.User;
import esi.classmate.user.model.UserCourse;
import esi.classmate.user.model.UserStatus;
import esi.classmate.user.model.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final KafkaTemplate<String, EnrollNotificationDto> kafkaTemplate;

    public List<UserDto> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users.stream().map(this::mapToUserDto).toList();
    }

    private UserDto mapToUserDto(User user) {
        log.info("User added Successfully: {}", user);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .roles(user.getRoles())
                .personal_code(user.getPersonal_code())
                .DOB(user.getDOB())
                .email(user.getEmail())
                .phone_no(user.getPhone_no())
                .password(user.getPassword())
                .status(user.getStatus())
                .userbalance(user.getUserbalance())
                .build();
    }

    public Optional<UserDto> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::mapToUserDto);
    }

    public User signup(UserDto userDto) {

        int intValue = 600;
        BigDecimal decimalValue = new BigDecimal(intValue);
        // userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .personal_code(userDto.getPersonal_code())
                .DOB(userDto.getDOB())
                .email(userDto.getEmail())
                .phone_no(userDto.getPhone_no())
                .password(userDto.getPassword())
                .status(UserStatus.ACTIVE)
                .roles(UserType.STUDENT)
                .userbalance(decimalValue)
                .build();

        // Setting the user status to ACTIVE
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(UserType.STUDENT);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the order to in its current state in the Database
        userRepository.save(user);

        // Setting the OrderDto status to ACTIVE
        userDto.setStatus(UserStatus.ACTIVE);
        userDto.setRoles(UserType.STUDENT);

        // // Push the userDto to the userCreatedTopic topic
        // kafkaTemplate.send("orderCreatedTopic", userDto);
        log.info("User added Successfully: {}", user);
        log.info("User added Successfully", user.getId());
        log.info("A new user  {} is added", user.toString());
        return user;
    }

    public void updateUser(Long id, UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .personal_code(userDto.getPersonal_code())
                .DOB(userDto.getDOB())
                .email(userDto.getEmail())
                .phone_no(userDto.getPhone_no())
                .password(userDto.getPassword())
                .status(UserStatus.ACTIVE)
                .roles(UserType.STUDENT)
                .build();

        // Setting the user status to ACTIVE
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(UserType.STUDENT);

        userRepository.save(user);
        // Setting the OrderDto status to ACTIVE
        userDto.setStatus(UserStatus.ACTIVE);
        userDto.setRoles(UserType.STUDENT);

        log.info("User {} is updated", user.getId());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.info("User {} is deleted", id);
    }

    // public List<UserCourseDto> getAllCourses() {
    // List<User> users = new ArrayList<>();
    // userRepository.findAll().forEach(users::add);
    // return users.stream().map(this::getAllCoursesDto).toList();
    // }

    public List<CourseDto> getAllCourses() {
        String url = "http://localhost:8081/api/courses";
        Mono<CourseDto[]> coursesMono = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(CourseDto[].class);

        CourseDto[] courses = coursesMono.share().block();
        return Arrays.asList(courses);
    }

    public void enrolltoACourse(UserCourseDto userCourseDto) {
        log.info("service method");
        // Save the order to in its current state in the Database
        UserCourse registered = enrollmentRepository.save(mapToUserCourse(userCourseDto));
        log.info("saved entity: {}", registered);
        // send notif

        sendEnrollmentNotif(registered);
    }

    private UserCourse mapToUserCourse(UserCourseDto course) {
        // return UserCourse.builder()
        UserCourse Ucourse = UserCourse.builder()
                .courseId(course.getCourseId())
                .userId(course.getUserId())
                .enrollment_date(LocalDate.now())
                .enrollment_status(EnrollmentStatus.ENROLLED)
                .build();
        return Ucourse;
    }

    public void sendEnrollmentNotif(UserCourse registered) {

        log.info("sendEnrollmentNotif ");
        String url = "http://localhost:8081/api/courses/{id}";
        CourseDto courseDto = webClientBuilder.build()
                .get()
                .uri(url, registered.getCourseId())
                .retrieve()
                .bodyToMono(CourseDto.class)
                .block();

        log.info("course dto: {}", courseDto);
        User user = userRepository.getReferenceById(registered.getUserId());
        log.info("user: {}", user);
        EnrollNotificationDto notificationDto = new EnrollNotificationDto();
        notificationDto.setType(NotificationType.ENROLL);
        notificationDto.setCourseName(courseDto.getCoursename());
        notificationDto.setUserId(registered.getUserId());
        notificationDto.setEmail(user.getEmail());

        log.info("sending the notif");
        kafkaTemplate.send("enrollmentTopic", notificationDto);
        log.info("sending the notif DONE");
    }

    public List<UserCourseDto> getCoursesByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

}
