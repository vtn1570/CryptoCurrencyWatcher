package com.example.currencyWatcher.repository;

import com.example.currencyWatcher.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

}
