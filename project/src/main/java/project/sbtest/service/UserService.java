package project.sbtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.sbtest.models.User;
import project.sbtest.scope.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        } else {
            user.setSignUpDate(new Timestamp(System.currentTimeMillis()));

            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            userRepository.save(user);
        }
    }



    public User updateUser(User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(updatedUser.getUserID());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
    public void deleteUser(Long userID) {
        userRepository.deleteById(userID);
    }

    public User authenticateUser(String email, String password) {
        // Log the email for debugging
        System.out.println("Email entered: " + email);

        // Retrieve user from the database
        User user = userRepository.findByEmail(email);

        if (user != null) {
            // Log the retrieved user for debugging
            System.out.println("Retrieved User: " + user.getEmail());

            // Check if the password matches
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Log successful authentication
                System.out.println("Authentication successful for user: " + user.getEmail());
                return user; // Return the authenticated user
            } else {
                // Log password mismatch
                System.out.println("Password mismatch for user: " + user.getEmail());
            }
        } else {
            // Log user not found
            System.out.println("User not found for email: " + email);
        }

        return null; // Return null if authentication fails
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
