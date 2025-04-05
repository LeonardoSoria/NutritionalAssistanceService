package com.core.domain.abstracts;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IRepository<TEntity extends AggregateRoot> {

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the UUID of the entity
     * @param readOnly whether the operation is read-only
     * @return a CompletableFuture containing an Optional of TEntity, or an empty Optional if not found
     */
    CompletableFuture<Optional<TEntity>> getByIdAsync(UUID id, boolean readOnly);

    /**
     * Adds a new entity to the repository.
     *
     * @param entity the entity to add
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> addAsync(TEntity entity);
}
