package com.sourav.shoppingcart.repository.imageRepository;

import com.sourav.shoppingcart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image , Long> {
    List<Image> findByProductId(Long id);
}
