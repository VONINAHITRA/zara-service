package com.zara.zaraservice.repository;

import com.zara.zaraservice.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository  extends JpaRepository<ClientEntity, Long> {
    @Query("SELECT c FROM ClientEntity c WHERE c.userEntity.id = :userId")
    Optional<ClientEntity> findByUserId(@Param("userId") Long userId);
}
