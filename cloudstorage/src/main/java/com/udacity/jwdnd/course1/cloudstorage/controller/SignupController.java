package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
/*
 * SignupController für das Mapping
 * */

public class SignupController {
    private final UserService userService;
    private RedirectAttributes redirectAttributes;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        String signupError = null;
        /*
         * User existiert schon
         * */
        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username is already exististing.";
        }
        /*
         * Fehler bei der Anmeldung
         * */
        if (signupError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was is an error during sign you up. Please try it again.";
            }

        }
        /*
         * Erfolgreiche Anmeldung
         * */
        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
            redirectAttributes.addFlashAttribute("SuccessMessage","Sign Up Successfully");
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }

}
