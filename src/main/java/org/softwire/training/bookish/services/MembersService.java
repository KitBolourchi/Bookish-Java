package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Members;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembersService extends DatabaseService {

    public List<Members> getAllMembers() {
        return jdbi.withHandle(handle -> handle
                .createQuery("SELECT FirstName FROM Members;")
                .mapToBean(Members.class)
                .list());
    }

    public void addMembers(Members member) {
        jdbi.useHandle(handle -> handle
                .createUpdate("INSERT INTO Members (FirstName, LastName, DateOfBirth) " +
                        "VALUES (:FirstName, :LastName, :DateOfBirth)")
                        .bind("FirstName", member.getFirstName())
                        .bind("LastName", member.getLastName())
                        .bind("DateOfBirth", member.getDateOfBirth())
                        .execute());
    }

    public void deleteMembers(int userID) {
        jdbi.useHandle(handle -> handle
                .createUpdate("DELETE FROM Members WHERE UserID = :userID")
                .bind("UserID", userID)
                .execute());
    }
}
