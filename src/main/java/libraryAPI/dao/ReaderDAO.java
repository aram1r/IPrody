package libraryAPI.dao;

import libraryAPI.model.Reader;

public interface ReaderDAO {
    Reader addReader(Reader reader);
    Reader updateReader(Reader reader);
    Boolean readerExists(Integer readerId);

}
