package com.example.demo.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUsernameUnique(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        return existingUser.isEmpty();
    }

    public boolean isEmailUnique(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        return existingUser.isEmpty();
    }

    public void removeDuplicateUsers() {
        // Fetch all users from the repository
        List<User> allUsers = userRepository.findAll();

        // Create a set to store unique usernames and emails
        Set<String> uniqueUsernames = new HashSet<>();
        Set<String> uniqueEmails = new HashSet<>();

        // Iterate over each user
        for (User user : allUsers) {
            // Check if the username is unique
            if (!uniqueUsernames.add(user.getUsername())) {
                userRepository.delete(user);
            }

            // Check if the email is unique
            if (!uniqueEmails.add(user.getEmail())) {
                userRepository.delete(user);
            }
        }
    }
}

