package com.application.tutorials.repository;

import com.application.tutorials.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

//    Optional<User> findById(UUID user);

    Integer countUsersByEmailIsLike(String email);


}
