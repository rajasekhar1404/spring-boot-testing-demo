package com.testing.service;

import com.testing.entity.User;
import com.testing.repository.UserRepository;
import com.testing.util.PasswordEncryption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean addUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (!optionalUser.isPresent()) {
            user.setPassword(PasswordEncryption.encrypt(user.getPassword()));
            userRepository.save(user);
            return true;
        }

        return false;

    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new Exception("User not found.");
    }

    public User updateUser(User user) throws Exception {
        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent()) {
            User userToSave = optionalUser.get();
            if (user.getName() != null) {
                userToSave.setName(user.getName());
            }
            if(user.getAddress() != null) {
                userToSave.setAddress(user.getAddress());
            }
            if(user.getEmail() != null) {
                userToSave.setEmail(user.getEmail());
            }
            if(user.getPassword() != null) {
                userToSave.setPassword(user.getPassword());
            }

            return userRepository.save(userToSave);
        }
        throw new Exception("User not found.");
    }

    public Boolean deleteUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }


}