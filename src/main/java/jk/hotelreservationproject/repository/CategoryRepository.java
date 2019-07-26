package jk.hotelreservationproject.repository;

import jk.hotelreservationproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
