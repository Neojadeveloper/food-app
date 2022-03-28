package com.example.pdp_meal.repository;

import com.example.pdp_meal.dto.order.MealOrderCountDto;
import com.example.pdp_meal.entity.MealOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<MealOrder,Integer> {

//    @Query(value = "select meal_id as mealId, count('1') as countMeal from pdp_meal.meal_order group by meal_id", nativeQuery = true)
//    List<MealOrderCountDto> getAll();

    @Query("select t.name, count(o.id) from Meal t inner join MealOrder o on o.mealId = t.id and o.createDate = ?1 group by t.name")
    List<Object[]> ordersCount(String today);
}
