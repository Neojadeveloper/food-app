package com.example.pdp_meal.repository;


import com.example.pdp_meal.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {


    AuthUser findByUsername(String username);

    @Query(value = "from AuthUser a where a.chatId=:id")
    AuthUser findByChatId(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("update AuthUser u set u.state=:state where u.chatId=:chatId")
    void changeStatus(@Param("chatId") String chatId, @Param("state") String state);

    Optional<AuthUser> findById(Integer id);

    AuthUser findAuthUserByChatId(String chatId);
}
