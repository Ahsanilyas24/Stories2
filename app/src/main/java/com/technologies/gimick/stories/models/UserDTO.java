package com.technologies.gimick.stories.models;

/*
This is data model class for signup. This class is act like personal data type and only used for
variable declaration

 */
public class UserDTO {
    public static UserDTO instance;
    public String email;
    public int id;
    public String name;

    public UserDTO(String email, int id, String name) {
        this.email = email;
        this.id = id;
        this.name = name;
    }

    public UserDTO() {
        instance = new UserDTO();
    }

    public static UserDTO getInstance() {
        return instance;
    }
}
