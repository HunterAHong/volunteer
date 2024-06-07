package com.hhong.Volunteer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for url mappings. Returns the appropriate HTML
 * page in the /src/main/resources/templates folder.
 */
@Controller
public class MappingController {
    /**
     * On a GET request to /index, the IndexController will return
     * /src/main/resources/templates/index.html.
     *
     * @param model underlying UI model
     * @return contents of the page
     */
    @GetMapping({ "/index", "/index.html", "/" })
    public String index(final Model model) {
        return "index";
    }

    @GetMapping({"/find"})
    public String findForm(final Model model){
        return "find";
    }

    @GetMapping({"/profile"})
    public String profileForm (final Model model) {
        return "profile";
    }

    @GetMapping({"/matches"})
    public String matchesForm (final Model model) {
        return "matches";
    }

    @GetMapping({"/events"})
    public String eventsForm (final Model model) {
        return "events";
    }

    @GetMapping({"/chats"})
    public String chatsForm (final Model model) {
        return "chats";
    }
}
