package com.example.webclass.repository;

import com.example.webclass.dto.ImageFace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageFaceRepository extends JpaRepository<ImageFace, Integer> {
}