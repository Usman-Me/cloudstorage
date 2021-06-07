package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notes")

/*
 * NoteController für das Mapping
 * */
public class NoteController {

    private NoteService notesService;
    private UserService userService;

    public NoteController(NoteService notesService, UserService userService) {
        this.notesService = notesService;
        this.userService = userService;
    }

    @PostMapping
    public String createOrUpdateNote(Authentication authentication, NoteForm noteForm, RedirectAttributes redirectAttributes) {
        User authUser = this.userService.getUserByUsername(authentication.getName());
        noteForm.setUserId(authUser.getUserid());

        try {
            /*
             * Note  erfolgreich geupdatet
             * */
            if (noteForm.getNoteId() == null) {
                this.notesService.createNote(noteForm);
                redirectAttributes.addFlashAttribute("message", String.format("Note \"%s\" is successfully saved.", noteForm.getNoteTitle()));
            }
            /*
             * Note  geupdatet
             * */

            else {
                this.notesService.updateNote(noteForm);
                redirectAttributes.addFlashAttribute("message", String.format("Note \"%s\" is successfully updated.", noteForm.getNoteTitle()));
            }
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "danger");
        }
        noteForm.setNoteTitle("");
        noteForm.setNoteDescription("");
        return "redirect:/home#nav-notes";
    }
    /*
     * Note löschen
     * */
    @GetMapping("delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, RedirectAttributes redirectAttributes) {
        this.notesService.deleteNote(noteId);
        redirectAttributes.addFlashAttribute("message", String.format("Note is successfully deleted."));
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/home#nav-notes";
    }
}
