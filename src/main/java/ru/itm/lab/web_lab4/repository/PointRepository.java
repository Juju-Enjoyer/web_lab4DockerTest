package ru.itm.lab.web_lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itm.lab.web_lab4.entity.Point;

import java.util.List;
@Repository
public interface PointRepository extends JpaRepository<Point,Long> {
    List<Point> findAllByUserId(Long userId);
    void deleteById(Long id);
    void deleteAllByUserId(Long id);
}
