package com.core.infrastructure.repository.nutritionalPlan;

import com.core.domain.models.nutritionalPlan.INutritionalPlanRepository;
import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import com.core.infrastructure.entity.NutritionalPlanEntity;
import com.core.infrastructure.mapper.NutritionalPlanPersistenceMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Primary
public class NutritionalPlanRepositoryImpl implements INutritionalPlanRepository {

    @Autowired
    private NutritionalPlanCrudRepository nutritionalPlanRepository;

    @Override
    public Optional<NutritionalPlan> findById(UUID id) {
        Optional<NutritionalPlanEntity> appointmentEntity = nutritionalPlanRepository.findById(id);
        return appointmentEntity.map(NutritionalPlanPersistenceMapper::toDomainModel);
    }

    @Override
    public List<NutritionalPlan> findByNutritionistId(UUID nutritionistId) {
        List<NutritionalPlanEntity> appointmentEntities = nutritionalPlanRepository.findByNutritionistId(nutritionistId);
        return appointmentEntities.stream()
                .map(NutritionalPlanPersistenceMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public NutritionalPlan update(NutritionalPlan nutritionalPlan) {
        NutritionalPlanEntity entity = NutritionalPlanPersistenceMapper.toEntity(nutritionalPlan);

        NutritionalPlanEntity savedEntity = nutritionalPlanRepository.save(entity);

        return NutritionalPlanPersistenceMapper.toDomainModel(savedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        if (!nutritionalPlanRepository.existsById(id)) {
            throw new EntityNotFoundException("Appointment not found with ID: " + id);
        }
        nutritionalPlanRepository.deleteById(id);
    }
}
