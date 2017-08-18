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

    public User createUser(User newUser){
        //TODO: Definir logica para crear usuario.
        return newUser;
    }

    public User updateUser(User modifiedUser){
        //TODO: Definir logica para actualizar usuario.
        return modifiedUser;
    }

    public List<User> getAllUsers(){
        //TODO: Definir logica para obtener listado de usuarios.
        for(int i=0; i<10; ++i){
            User user = new User();
            user.setId(i+1);
            user.setFirstName("lorem "+ i);
            user.setLastName("ipsum " + i);
            findAllUsers.add(user);
        }
        return findAllUsers;
    }

    public int deleteUser(int id){
        //TODO: Definir logica para borrar usuario.
        return id;
    }
}