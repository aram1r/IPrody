package com.iprody.library.resource;

import com.iprody.library.entity.BorrowedBook;
import com.iprody.library.repository.BorrowedBookRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class BorrowedBookResourceImpl implements BorrowedBookResourceInterface {

    BorrowedBookRepository borrowedBookRepository;

    public BorrowedBookResourceImpl() {
        this.borrowedBookRepository = new BorrowedBookRepository();
    }

    @POST
    @Path("/addborrowedbook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public BorrowedBook addBorrowedBook(BorrowedBook borrowedBook) {
        try {
            return borrowedBookRepository.save(borrowedBook);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
