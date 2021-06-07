package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
/*
 * HomeController für das Mapping
 * */
public class HomeController {

    private NoteService noteService;
    private FileService fileService;
    private UserService userService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public HomeController(NoteService noteService, FileService fileService, UserService userService, CredentialService credentialService, EncryptionService encryptionService )
    {
        this.noteService = noteService;
        this.fileService = fileService;
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;

    }
    /*
  *  HomeController Aufruf
  * */
    @GetMapping()
    public String getHomePage(Authentication authentication, NoteForm noteForm, CredentialForm credentialForm, Model model)
    {
        User authUser = userService.getUserByUsername(authentication.getName());
        model.addAttribute("files", this.fileService.getAllFilesByUserId(authUser.getUserid()));
        model.addAttribute("notes", this.noteService.getAllNotes(authUser.getUserid()));
        model.addAttribute("credentials", this.credentialService.getAllCredentialsByUserId(authUser.getUserid()));
        model.addAttribute("encriptionService", encryptionService);
        return "home";

    }

}