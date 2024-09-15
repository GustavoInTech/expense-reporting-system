package com.yourcompany.expense_management.dto;

public class UserDTO {

    private Long id;
    private String username;
    private String email;

    // Construtor vazio
    public UserDTO() {
    }

    // Construtor com parâmetros
    public UserDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
