package com.kunitskaya.webservices.facades;

import com.kunitskaya.webservices.models.User;
import io.restassured.response.Response;
import org.springframework.http.ResponseEntity;

public class UsersWsFacade extends AbstractWsFacade {
    private String url = BASE_URL + configProvider.getWsUsersUri();
    private String uri = configProvider.getWsUsersUri();

    /**
     * Receives all users by making GET request
     * Uses RestAssured framework
     *
     * @return - response
     */
    public Response getUsersRA() {
        return get(uri);
    }

    /**
     * Receives all users by making GET request
     * Uses RestTemplate framework
     *
     * @return - response
     */
    public ResponseEntity<User[]> getUsersRT() {
        return get(url, User[].class);
    }

    /**
     * Deletes user by making DELETE request
     *
     * @param user - user to delete
     * @return - response
     */
    public Response deleteUser(User user) {
        String id = String.valueOf(user.getId());
        return delete(uri + SEPARATOR + id);
    }

}
