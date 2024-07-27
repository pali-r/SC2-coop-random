package com.application.repository;

import com.application.model.CommanderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommanderRepo extends CrudRepository<CommanderEntity, Long> {
}
