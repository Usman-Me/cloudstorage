package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/files")
/*
 * FileController für das Mapping
 * */
public class FileController {
    private FileService fileService;
    private UserService userService;

    public FileController(FileService filesService, UserService userService) {
        this.fileService = filesService;
        this.userService = userService;
    }

    /*
     * File - Updaten
     * */
    @PostMapping("/upload")
    public String upload(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            User authUser = userService.getUserByUsername(authentication.getName());
            fileService.storeFile(fileUpload, authUser.getUserid());
            redirectAttributes.addFlashAttribute("message", String.format("You successfully uploaded the file \"%s\"", fileUpload.getOriginalFilename()));
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "danger");
        }
        return "redirect:/home";
    }
    /*
     * File - Löschen
     * */

    @GetMapping("/delete/{fileId}")
    public String delete(@PathVariable Integer fileId, RedirectAttributes redirectAttributes) {
        // debated point: should we 404 on an unknown fileId?
        // or should we just return a nice (200 or 204) in all cases?
        // we're doing the latter
        fileService.delete(fileId);
        redirectAttributes.addFlashAttribute("message", String.format("File is successfully deleted."));
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/home";
    }

    @GetMapping("/show/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> show(@PathVariable String fileName) {
        File file = fileService.getFileByFilename(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", file.getFileName()))
                .header(HttpHeaders.CONTENT_TYPE, file.getContenttype())
                .header(HttpHeaders.CONTENT_LENGTH, file.getFilesize())
                .body(file.getFiledata());
    }
}