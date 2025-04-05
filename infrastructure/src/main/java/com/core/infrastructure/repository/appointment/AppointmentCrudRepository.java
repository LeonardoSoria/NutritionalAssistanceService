package com.core.infrastructure.repository.appointment;

import com.core.infrastructure.entity.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentCrudRepository extends CrudRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findByClientId(UUID clientId);
}
