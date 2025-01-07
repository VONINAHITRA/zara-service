package com.zara.zaraservice.repository;

import com.zara.zaraservice.entity.FormationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FormationRepository extends JpaRepository<FormationEntity, Long> {
    Collection<FormationEntity> findByConsultantEntity_Id(Long id);
}
