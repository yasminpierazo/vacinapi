package com.api.vacinas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import com.api.vacinas.modelos.Usuario;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Query("SELECT email FROM Usuario WHERE email = :email")
    Optional<Usuario> findByEmail(@Param("email") String email);
}

