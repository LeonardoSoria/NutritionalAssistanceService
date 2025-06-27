package com.core.infrastructure.repository.user;

import com.core.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserCrudRepository extends CrudRepository<UserEntity, UUID> {
    List<UserEntity> findByUsername(String username);

	List<UserEntity> findByEmail(String email);
}
