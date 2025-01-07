package com.zara.zaraservice.repository;

import com.zara.zaraservice.entity.ConsultantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsultantRepository extends JpaRepository<ConsultantEntity, Long> {
    @Query("SELECT c FROM ConsultantEntity c WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(c.specialization) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.userEntity.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.userEntity.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:location IS NULL OR :location = '' OR " +
            "LOWER(c.localisation) LIKE LOWER(CONCAT('%', :location, '%')))")
    List<ConsultantEntity> searchConsultants(
            @Param("keyword") String keyword,
            @Param("location") String location
    );

    @Query("SELECT c FROM ConsultantEntity c WHERE c.userEntity.id = :userId")
    Optional<ConsultantEntity> findByUserId(@Param("userId") Long userId);

}
