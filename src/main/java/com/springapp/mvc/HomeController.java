package com.springapp.mvc;


import com.springapp.services.SpitterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.Map;

@Controller
public class HomeController {
    public static final int DEFAULT_SPITTLES_PER_PAGE = 25;

    private SpitterService spitterService;

    @Inject
    public HomeController(SpitterService spitterService){
        this.spitterService = spitterService;
    }
    @RequestMapping({"/", "/home"})
    public String showHomePage(ModelMap model){
        model.addAttribute("spittles", spitterService.getRecentSpittles(
                DEFAULT_SPITTLES_PER_PAGE
        ));
        return "home";
    }
}
