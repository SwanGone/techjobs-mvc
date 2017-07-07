package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.models.JobData.findByColumnAndValue;
import static org.launchcode.models.JobData.findByValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        ArrayList<HashMap<String, String>> searchResults = new ArrayList<>();
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchResults", searchResults);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "/results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> searchResults = new ArrayList<>();
        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> preResults = JobData.findByValue(searchTerm);
            for (HashMap<String, String> entry : preResults) {
                searchResults.add(entry);
            }
        } else {
            ArrayList<HashMap<String, String>> preResults = JobData.findByColumnAndValue(searchType, searchTerm);
            for (HashMap<String, String> entry : preResults) {
                searchResults.add(entry);
            }
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchResults", searchResults);


        return "search";
    }

}
