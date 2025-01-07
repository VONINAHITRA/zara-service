package com.zara.zaraservice.repository;

import com.zara.zaraservice.dto.FeedbackDTO;
import com.zara.zaraservice.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
    List<FeedbackEntity> findByConsultantEntity_Id(Long id);
    List<FeedbackEntity> findByClientEntity_Id(Long id);
}
