
package com.example.electricity.usage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electricity.usage.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByNameAndPassword(String name, String password);
}
