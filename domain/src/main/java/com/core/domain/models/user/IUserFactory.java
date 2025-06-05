package com.core.domain.models.user;

public interface IUserFactory {
    User create(String username, String email, String fullName);
}
