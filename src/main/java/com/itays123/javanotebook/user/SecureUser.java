package com.itays123.javanotebook.user;

// the user returned when asking for individual note, excluding the email and password fields
public class SecureUser {

    private Long id;

    private String name;

    public static SecureUser fromUser(User user) {
        return new SecureUser(user.getId(), user.getName());
    }

    public SecureUser() {
    }

    public SecureUser(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
