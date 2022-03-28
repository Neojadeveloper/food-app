package com.example.pdp_meal.repository;

import com.example.pdp_meal.entity.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu,Integer> {

    @Transactional
    @Modifying
    @Query(value = "TRUNCATE TABLE daily_menu",  nativeQuery = true)
    void clearDailyMenu();
}
