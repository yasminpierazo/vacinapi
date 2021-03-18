package com.api.vacinas.repositories;
import com.api.vacinas.modelos.Vacinas;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VacinasRepository extends CrudRepository<Vacinas, Integer> {
}
