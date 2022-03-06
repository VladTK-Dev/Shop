package pl.edu.wszib.sport.store.database;

import pl.edu.wszib.sport.store.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
}
