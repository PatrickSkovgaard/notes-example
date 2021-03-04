package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class NotesController {
    @PostMapping("/addNote")
    public String addNote(@RequestParam("note") String note, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //get the notes from request session
        ArrayList<String> notes = (ArrayList<String>) session.getAttribute("notes");
        //check if notes is present in session or not
        if (notes == null) {
            notes = new ArrayList<>();
            // if notes object is not present in session, set notes in the request session
            session.setAttribute("notes", notes);
        }
        notes.add(note);
        session.setAttribute("notes", notes);

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/invalidate/session")
    public String destroySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //invalidate the session
        session.invalidate();

        return "redirect:/home";
    }
}