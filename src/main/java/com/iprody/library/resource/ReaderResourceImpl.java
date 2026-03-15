package com.iprody.library.resource;

import com.iprody.library.entity.Reader;
import com.iprody.library.repository.ReaderRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class ReaderResourceImpl implements ReaderResourceInterface {

    ReaderRepository repo;

    public ReaderResourceImpl() {
        this.repo = new ReaderRepository();
    }

    @POST
    @Path("/addreader")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Reader addReader(Reader reader) {
        try {
            return repo.save(reader);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
