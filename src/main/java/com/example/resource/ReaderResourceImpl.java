package com.example.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import libraryAPI.dao.ReaderDAO;
import libraryAPI.dao.ReaderDaoImpl;
import libraryAPI.model.Reader;

@Path("/reader")
public class ReaderResourceImpl implements ReaderResourceInterface {

    ReaderDAO readerDAO;

    public ReaderResourceImpl() {
        this.readerDAO = new ReaderDaoImpl();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Reader addReader(Reader reader) {
        try {
            return readerDAO.addReader(reader);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
