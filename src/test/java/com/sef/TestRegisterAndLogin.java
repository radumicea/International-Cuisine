package com.sef;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import com.sef.backend.controllers.UserController;
import com.sef.session.UserSession;

import org.junit.Test;

public class TestRegisterAndLogin {

    private static final Random RANDOM = new Random();

    private final UserSession userSession = UserSession.getUserSession();
    private final UserController userController = new UserController();

    private String username;
    private String password;
    
    private void registerRandom() {
        username = String.valueOf(RANDOM.nextLong());
        password = String.valueOf(RANDOM.nextLong());

        userController.registerUser(username, password);
    }

    private void loginRandom() {
        userController.logUserIn(username, password);
    }

    @Test
    public void testRegisterLogin() {
        registerRandom();
        loginRandom();
        assertTrue(userSession.userId != null);
    }
}
