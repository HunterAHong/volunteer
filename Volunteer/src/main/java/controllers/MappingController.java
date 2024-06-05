package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for url mappings. Returns the appropriate HTML
 * page in the /src/main/resources/templates folder.
 */
@RestController
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
}
