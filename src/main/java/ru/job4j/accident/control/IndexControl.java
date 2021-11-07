package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.MemStore;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {
    private MemStore store = new AccidentMem();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", new AccidentService(store).getAccidents());
        return "index";
    }
}
