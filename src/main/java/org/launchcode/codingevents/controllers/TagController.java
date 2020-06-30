package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("tag")
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping()
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Tags");
        model.addAttribute("tags",tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model){
        model.addAttribute("title","Create Tag");
        model.addAttribute("tag",new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid Tag newTag,
                                                 Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Create Tag");
            return "tags/create";
        }
        model.addAttribute("tag",new Tag());
        tagRepository.save(newTag);
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Tag");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] tagIds) {

        if (tagIds != null) {
            for (int id : tagIds) {
                tagRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

}
