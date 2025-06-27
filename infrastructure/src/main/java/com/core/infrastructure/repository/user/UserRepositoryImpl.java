package com.core.infrastructure.repository.user;

import com.core.domain.models.user.IUserRepository;
import com.core.domain.models.user.User;
import com.core.infrastructure.entity.UserEntity;
import com.core.infrastructure.mapper.UserPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Primary
public class UserRepositoryImpl implements IUserRepository {

	@Autowired
	private UserCrudRepository userCrudRepository;

	@Override
	public Optional<User> findById(UUID id) {
		Optional<UserEntity> userEntity = userCrudRepository.findById(id);
		return userEntity.map(UserPersistenceMapper::toDomainModel);
	}

	@Override
	public List<User> findByUsername(String username) {
		List<UserEntity> userEntities = userCrudRepository.findByUsername(username);
		return userEntities.stream()
			.map(UserPersistenceMapper::toDomainModel)
			.collect(Collectors.toList());
	}

	@Override
	public User login(String email, String password) {
		User user = userCrudRepository.findByEmail(email)
			.stream()
			.findFirst()
			.map(UserPersistenceMapper::toDomainModel)
			.orElse(null);
		if (user != null && !user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}

	@Override
	public User upsert(User user) {
		UserEntity userEntity = UserPersistenceMapper.toEntity(user);
		UserEntity savedEntity = userCrudRepository.save(userEntity);
		return UserPersistenceMapper.toDomainModel(savedEntity);
	}
}
