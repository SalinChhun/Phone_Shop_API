package com.kosign.phone_shop_api.repository;

import com.kosign.phone_shop_api.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

//    @Modifying
//    @Query("DELETE FROM User u WHERE u.id = :userId")
//    void deleteUserById(@Param("userId") Integer userId);


    @Query("select u from User u order by u.id DESC")
    Page<User> getAllUser(Pageable pageable);

    @Query("select u from User u where u.isPinCodeEnable = ?1 and u.email = ?2")
    User findByIsPinCodeEnableAndEmail(Boolean isPinCodeEnable, String email);


}
