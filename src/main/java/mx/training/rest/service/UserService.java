package mx.training.rest.service;

import java.util.ArrayList;
import java.util.List;

import mx.training.rest.model.User;

public class UserService {
    private static List<User> findAllUsers = new ArrayList<User>();

    public UserService(){}
    public User getUser(int id){
        User user = new User();
        user.setId(id);
        user.setFirstName("Angel");
        user.setLastName("Mendez");
        return user;
    }

    public int createUser(User newUser){
        //TODO: Definir logica para crear usuario.
        return newUser.getId();
    }

    public User updateUser(User modifiedUser){
        //TODO: Definir logica para actualizar usuario.
        return modifiedUser;
    }

    public List<User> getAllUsers(){
        return findAllUsers;
    }
}