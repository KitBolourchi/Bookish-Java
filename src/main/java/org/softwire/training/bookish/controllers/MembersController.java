package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Members;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.page.AboutPageModel;
import org.softwire.training.bookish.models.page.BookPageModel;
import org.softwire.training.bookish.models.page.MembersPageModel;
import org.softwire.training.bookish.services.BooksService;
import org.softwire.training.bookish.services.MembersService;
import org.softwire.training.bookish.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MembersController {

    private final MembersService membersService;

    @Autowired
    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @RequestMapping("")
    ModelAndView members() {

        List<Members> allMembers = membersService.getAllMembers();

        MembersPageModel membersPageModel = new MembersPageModel();
        membersPageModel.setMembers(allMembers);

        return new ModelAndView("members", "model", membersPageModel);
    }

    @RequestMapping("/add-members")
    RedirectView addMember(@ModelAttribute Members member) {

        membersService.addMembers(member);

        return new RedirectView("/members");
    }

    @RequestMapping("/delete-members")
    RedirectView deleteMember(@RequestParam int userID) {

        membersService.deleteMembers(userID);

        return new RedirectView("/members");
    }
}