package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public class AccidentJdbcTemplate implements MemStore {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Accident save(Accident accident) {
        jdbc.update("insert into accident(name, text, address, acc_type_id) values(?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());
        return accident;
    }

    @Override
    public List<Accident> getAccidents() {
        return jdbc.query(
                "select * from accident acc join type t on acc.acc_type_id = t.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(AccidentType.of(rs.getInt(6), rs.getString(7)));
                    return accident;
                });
    }

    @Override
    public List<Rule> getRules() {
        return jdbc.query("select * from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    @Override
    public Accident getAccidentByID(int id) {
        return jdbc.queryForObject("select * from accident where id = " + id,
                new Accident[]{}, Accident.class);
    }

    @Override
    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("select * from type",
                (rs, row) -> {
                    AccidentType accType = new AccidentType();
                    accType.setId(rs.getInt("id"));
                    accType.setName(rs.getString("name"));
                    return accType;
                });
    }
}
