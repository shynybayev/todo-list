package com.example.firedetect.controller;

import com.example.firedetect.logs.Loggable;
import com.example.firedetect.model.Fire;
import com.example.firedetect.repo.FireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/fire-detect")
public class FireDetectController {

    @Autowired
    private FireRepository fireRepository;


    @Autowired
    public FireDetectController(FireRepository fireRepository) {
        this.fireRepository = fireRepository;
    }

    @GetMapping
    @Loggable
    public String getIndex(Model model){
        model.addAttribute("fires", fireRepository.findAll());
        model.addAttribute("newFire", new Fire());
        return "index";
    }


    @Loggable
    @PostMapping("/")
    public String createFire(Fire fire, Model model){
        try {
            fireRepository.save(fire);
            model.addAttribute("fire", new Fire());
        }catch (Exception e) {
            model.addAttribute("fire", fire);
            model.addAttribute("error", "failed");
        }
        return "redirect:/fire-detect";
    }


    @Loggable
    @GetMapping("/{id}/show")
    public String showById(@PathVariable("id") Long id, Model model){
        if(fireRepository.findById(id).isPresent()){
            model.addAttribute("fire", fireRepository.findById(id).get());
            return "show";
        }
        model.addAttribute("fire", new Fire());
        return "show";
    }

    @Loggable
    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id){
        fireRepository.deleteById(id);
        return "redirect:/todo";
    }


    @Loggable
    @GetMapping("/{id}/edit")
    public String editFormById(@PathVariable("id") Long id, Model model){

        if(fireRepository.findById(id).isPresent()){
            model.addAttribute("fire", fireRepository.findById(id).get());
            return "edit";
        }
        model.addAttribute("fire", new Fire());
        return "edit";
    }


    @Loggable
    @PostMapping("/{id}")
    public String updateFire(@PathVariable Long id, @Valid Fire fire, Errors errors){
        if (errors.hasErrors()){
            return "edit";
        }
        fireRepository.save(fire);
        return "redirect:/fire-detect";
    }

}
