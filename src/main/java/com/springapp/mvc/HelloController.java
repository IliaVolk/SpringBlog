package com.springapp.mvc;

import com.springapp.entities.Spitter;
import com.springapp.services.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

//@Controller
//@RequestMapping("/")
//@Transactional(propagation = Propagation.SUPPORTS)
public class HelloController {
    //@Autowired
    private SpitterService spitterService;


    public void setSpitterService(SpitterService spitterService) {
        this.spitterService = spitterService;
    }



    //@RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) throws Throwable {
        StringBuilder stringToOutput = new StringBuilder("OK ");
        List<Spitter> spitters = spitterService.getSpittersByName("testSpitter");
        stringToOutput.append(spitters);
        model.addAttribute("message", stringToOutput.toString());
        return "hello";
    }
}