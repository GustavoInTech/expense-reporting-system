package com.yourcompany.expense_management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yourcompany.expense_management.dto.UserDTO;
import com.yourcompany.expense_management.entity.User;
import com.yourcompany.expense_management.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Converte User para UserDTO
    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    // Converte UserDTO para User
    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDto);
    }

    public UserDTO createUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("O email já está em uso.");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("O nome de usuário já está em uso.");
        }

        // Codifica a senha antes de salvar o usuário
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);

        return convertToDto(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());

            // Atualiza a senha apenas se ela foi fornecida
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }

            User updatedUser = userRepository.save(user);
            return convertToDto(updatedUser);
        }

        return null;
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + id);
        }
    }
}
