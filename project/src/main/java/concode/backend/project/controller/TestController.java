package concode.backend.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "https://concode-ohjelmistoprojekti2.github.io"})

public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Backend works!";
    }
}