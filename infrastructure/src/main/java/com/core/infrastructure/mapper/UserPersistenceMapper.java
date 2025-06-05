package com.core.infrastructure.mapper;

import com.core.domain.models.user.User;
import com.core.domain.shared.DateValue;
import com.core.infrastructure.entity.UserEntity;

import java.time.LocalDate;

public class UserPersistenceMapper {

	public static User toDomainModel(UserEntity entity) {

		return new User(
			entity.getId(),
			entity.getUsername(),
			entity.getEmail(),
			entity.getFullName(),
			DateValue.from(LocalDate.parse(entity.getCreatedAt()))
		);
	}

	public static UserEntity toEntity(User domain) {
		String date = domain.getCreatedAt().toLocalDate().toString();

		return new UserEntity(
			domain.getId(),
			domain.getUsername(),
			domain.getEmail(),
			domain.getFullName(),
			date
		);
	}
}
