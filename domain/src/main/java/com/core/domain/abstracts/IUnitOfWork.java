package com.core.domain.abstracts;

import java.util.concurrent.CompletableFuture;

public interface IUnitOfWork {

    /**
     * Commits all pending changes as a single transaction.
     *
     * @return a CompletableFuture representing the completion of the commit operation
     */
    CompletableFuture<Void> commitAsync();
}
