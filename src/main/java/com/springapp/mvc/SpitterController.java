package com.springapp.mvc;


import com.springapp.entities.Spitter;
import com.springapp.entities.Spittle;
import com.springapp.services.SpitterService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private static class ImageUploadException extends RuntimeException{
        ImageUploadException(String message){
            super(message);
        }
    }
    private final SpitterService spitterService;

    @Inject
    public SpitterController(SpitterService spitterService) {
        this.spitterService = spitterService;
    }
    //handles GET-requests to URL /spitter/spittles
    @RequestMapping(value = "/spittles", method = RequestMethod.GET)
    public String listSpittlesForSpitter(
            @RequestParam("spitter") String username, ModelMap model) {
        Spitter spitter = spitterService.getSpittersByName(username).get(0);
        model.addAttribute(spitter);
        List<Spittle> spittleList = spitter.getSpittles();
        model.addAttribute(spittleList);
        return "spittles/list";
    }
    //url pattern: http://localhost:8080/spitter?new
    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createSpitterProfile(ModelMap model){
        model.addAttribute(new Spitter());
        return "spitters/edit";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addSpitterFromForm(HttpServletRequest request,
                                     @Valid Spitter spitter,
                                     BindingResult bindingResult,
                                     @RequestParam(value = "image", required = false)
                                     MultipartFile image){
        if (bindingResult.hasErrors()){//errors checking
            return "spitters/edit";
        }

        spitterService.saveSpitter(spitter);

        try {
            if (!image.isEmpty()){
                validateImage(image);
                saveImage(spitter.getId() + ".jpg", image, request);
            }
        }catch (ImageUploadException e){
            bindingResult.reject(e.getMessage());
            return "spitters/edit";
        }
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username,
                                     ModelMap model){
        model.addAttribute(spitterService.getSpittersByName(username).get(0));
        System.out.println("works");
        return "spitters/view";
    }
    private void validateImage(MultipartFile image){
        if (!image.getContentType().equals("image/jpeg")){
            throw new ImageUploadException("Only JPG image accepted");
        }
    }
    private void saveImage(String filename, MultipartFile image, HttpServletRequest request){
        try {
            String webRootPath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("Path: " + webRootPath);
            File file = new File(webRootPath + "/resources/" + filename);
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(image.getBytes());
        } catch (FileNotFoundException e) {
            throw new ImageUploadException("Cant open file(");
        } catch (IOException e) {
            throw new ImageUploadException("Cant write image to file");
        }
    }
}
