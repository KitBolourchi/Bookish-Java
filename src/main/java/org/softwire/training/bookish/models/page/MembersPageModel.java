package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Members;
import org.softwire.training.bookish.models.database.Technology;

import java.util.List;


public class MembersPageModel {

    private List<Members> members;

    public void setMembers(List<Members> members) {
        this.members = members;
    }

    public List<Members> getMembers() {
        return members;
    }
}
