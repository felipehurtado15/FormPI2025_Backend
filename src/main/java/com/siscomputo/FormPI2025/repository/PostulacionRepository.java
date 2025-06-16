package com.siscomputo.FormPI2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siscomputo.FormPI2025.entity.Postulacion;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {
}
