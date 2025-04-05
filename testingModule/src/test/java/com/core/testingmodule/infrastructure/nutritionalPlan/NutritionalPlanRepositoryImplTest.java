package com.core.testingmodule.infrastructure.nutritionalPlan;

import com.core.domain.models.nutritionalPlan.NutritionalPlan;
import com.core.infrastructure.entity.NutritionalPlanEntity;
import com.core.infrastructure.mapper.NutritionalPlanPersistenceMapper;
import com.core.infrastructure.repository.nutritionalPlan.NutritionalPlanCrudRepository;
import com.core.infrastructure.repository.nutritionalPlan.NutritionalPlanRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class NutritionalPlanRepositoryImplTest {

    @Mock
    private NutritionalPlanCrudRepository nutritionalPlanRepository;

    @InjectMocks
    private NutritionalPlanRepositoryImpl nutritionalPlanRepositoryImpl;

    private UUID clientId;
    private UUID nutritionistId;
    private NutritionalPlanEntity nutritionalPlanEntity;
    private NutritionalPlan nutritionalPlan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientId = UUID.randomUUID();
        nutritionistId = UUID.randomUUID();

        // Initialize test data
        nutritionalPlan = new NutritionalPlan(clientId, nutritionistId, "Just 1000 kcal per day");
        nutritionalPlanEntity = new NutritionalPlanEntity();
        nutritionalPlanEntity.setId(nutritionalPlan.getId());
        nutritionalPlanEntity.setClientId(clientId);
        nutritionalPlanEntity.setNutritionistId(nutritionistId);
        nutritionalPlanEntity.setPlanDetails(nutritionalPlan.getPlanDetails());
        nutritionalPlanEntity.setAnalysisResults(new ArrayList<>());
        nutritionalPlanEntity.setDelivered(nutritionalPlan.isDelivered());
    }

    @Test
    void testFindById_Found() {
        when(nutritionalPlanRepository.findById(nutritionalPlan.getId())).thenReturn(Optional.of(nutritionalPlanEntity));

        try (MockedStatic<NutritionalPlanPersistenceMapper> mockedMapper = mockStatic(NutritionalPlanPersistenceMapper.class)) {
            mockedMapper.when(() -> NutritionalPlanPersistenceMapper.toDomainModel(nutritionalPlanEntity)).thenReturn(nutritionalPlan);

            Optional<NutritionalPlan> result = nutritionalPlanRepositoryImpl.findById(nutritionalPlan.getId());

            assertThat(result).isPresent();
            assertThat(result.get().getId()).isEqualTo(nutritionalPlan.getId());
            verify(nutritionalPlanRepository, times(1)).findById(nutritionalPlan.getId());
        }
    }

    @Test
    void testFindById_NotFound() {
        when(nutritionalPlanRepository.findById(nutritionalPlan.getId())).thenReturn(Optional.empty());

        Optional<NutritionalPlan> result = nutritionalPlanRepositoryImpl.findById(nutritionalPlan.getId());

        assertThat(result).isEmpty();
        verify(nutritionalPlanRepository, times(1)).findById(nutritionalPlan.getId());
    }

    @Test
    void testFindByClientId() {
        when(nutritionalPlanRepository.findByClientId(clientId)).thenReturn(List.of(nutritionalPlanEntity));

        try (MockedStatic<NutritionalPlanPersistenceMapper> mockedMapper = mockStatic(NutritionalPlanPersistenceMapper.class)) {
            mockedMapper.when(() -> NutritionalPlanPersistenceMapper.toDomainModel(nutritionalPlanEntity)).thenReturn(nutritionalPlan);

            List<NutritionalPlan> results = nutritionalPlanRepositoryImpl.findByClientId(clientId);

            assertThat(results).hasSize(1);
            assertThat(results.get(0).getId()).isEqualTo(nutritionalPlan.getId());
            verify(nutritionalPlanRepository, times(1)).findByClientId(clientId);
        }
    }

    @Test
    void testUpdate() {
        try (MockedStatic<NutritionalPlanPersistenceMapper> mockedMapper = mockStatic(NutritionalPlanPersistenceMapper.class)) {
            mockedMapper.when(() -> NutritionalPlanPersistenceMapper.toEntity(nutritionalPlan)).thenReturn(nutritionalPlanEntity);
            mockedMapper.when(() -> NutritionalPlanPersistenceMapper.toDomainModel(nutritionalPlanEntity)).thenReturn(nutritionalPlan);

            when(nutritionalPlanRepository.save(nutritionalPlanEntity)).thenReturn(nutritionalPlanEntity);

            NutritionalPlan result = nutritionalPlanRepositoryImpl.update(nutritionalPlan);

            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(nutritionalPlan.getId());
            verify(nutritionalPlanRepository, times(1)).save(nutritionalPlanEntity);
        }
    }

    @Test
    void testDeleteById_Success() {
        when(nutritionalPlanRepository.existsById(nutritionalPlan.getId())).thenReturn(true);

        nutritionalPlanRepositoryImpl.deleteById(nutritionalPlan.getId());

        verify(nutritionalPlanRepository, times(1)).existsById(nutritionalPlan.getId());
        verify(nutritionalPlanRepository, times(1)).deleteById(nutritionalPlan.getId());
    }

    @Test
    void testDeleteById_NotFound() {
        when(nutritionalPlanRepository.existsById(nutritionalPlan.getId())).thenReturn(false);

        assertThatThrownBy(() -> nutritionalPlanRepositoryImpl.deleteById(nutritionalPlan.getId()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Appointment not found with ID: " + nutritionalPlan.getId());

        verify(nutritionalPlanRepository, times(1)).existsById(nutritionalPlan.getId());
        verify(nutritionalPlanRepository, never()).deleteById(any());
    }
}
