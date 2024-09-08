package com.yourcompany.expense_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcompany.expense_management.entity.User;
import com.yourcompany.expense_management.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
        // .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com
        // ID: " + id));
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("O email já está em uso.");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("O nome de usuário já está em uso.");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);

        // Verifica se o usuário foi encontrado
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Atualizando os detalhes do usuário
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());

            // Salvando o usuário atualizado no banco de dados
            return userRepository.save(user);
        }

        // Se o usuário não for encontrado, pode retornar nulo ou tratar de outra forma
        return null;
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            System.out.println("Usuário não encontrado com ID: " + id);
        }
    }
}
