package com.hackerrank.sample.repository;

import com.hackerrank.sample.model.Model;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("modelRepository")
public interface ModelRepository extends JpaRepository<Model, String> {
    @Transactional
    void deleteById(String id);

    List<Model> findByCategory(String category);
}
