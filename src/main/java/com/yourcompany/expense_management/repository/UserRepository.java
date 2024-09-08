package com.yourcompany.expense_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourcompany.expense_management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Método para buscar um usuário pelo username
    Optional<User> findByUsername(String username);

    // Método para buscar um usuário pelo email
    Optional<User> findByEmail(String email);

    // Método para verificar se um username ja existe
    boolean existsByUsername(String username);

    // Método par verificar se um email já existe
    boolean existsByEmail(String email);
}
