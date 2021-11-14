package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u from User u where u.username = ?1")
    User findUserByUsername(String name);
}