package com.iprody.library.controller;

import com.iprody.library.entity.Reader;
import com.iprody.library.repository.ReaderRepository;

import java.util.List;

public class ReaderController {

    private final ReaderRepository readerRepository = new ReaderRepository();

    public String createReader(String name, String email, String phone) {
        Reader reader = new Reader(name, email, phone);
        readerRepository.save(reader);
        return "Читатель успешно сохранен!";
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public void printReaderDetails(Long id) {
        Reader reader = readerRepository.findById(id);
        if (reader != null) {
            System.out.println(reader);
        } else {
            System.out.println("Читатель с ID " + id + " не найдена.");
        }
    }

    public void removeReader(Long id) {
        readerRepository.delete(id);
        System.out.println("Читатель удален.");
    }
}
