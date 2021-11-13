package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface RuleRepo extends CrudRepository<Rule, Integer> {
}
