package com.core.domain.models.user;

public interface IUserFactory {
    User create(String username, String password, String email, String fullName, String address);
}
