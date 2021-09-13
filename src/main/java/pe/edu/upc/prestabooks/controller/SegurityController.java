package pe.edu.upc.prestabooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SegurityController {
    @GetMapping("login")
    public String index(){
        return "security/login";
    }

    @GetMapping("access-denied")
    public String accessDenied(){
        return "security/access-denied";
    }
}
