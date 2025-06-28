package com.healconnect.repository;

import org.springframework.data.repository.CrudRepository;

public interface GenericRepository<T,ID> extends CrudRepository<T, ID> {
}
