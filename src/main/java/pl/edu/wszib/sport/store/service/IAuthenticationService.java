package pl.edu.wszib.sport.store.service;

import pl.edu.wszib.sport.store.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}
