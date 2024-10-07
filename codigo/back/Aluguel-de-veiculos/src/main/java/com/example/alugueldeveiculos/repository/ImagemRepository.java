package com.example.alugueldeveiculos.repository;

import com.example.alugueldeveiculos.model.ImagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<ImagemEntity, Long> {
}
