package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Reader;
import com.example.demo.exception.UserEmailIsAlreadyBusy;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ReaderRepository;

@Service
@Transactional
public class ReaderService {

	private final ReaderRepository readerRepository;
	
    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }
    
    public List<Reader> findAllReaders(){
		return readerRepository.findAll();
    }
    
    public Optional<Reader> findByEmailAndPassword(String email, String password) {
		return readerRepository.findByEmailAndPassword(email, password);
    }
    
   public Optional<Reader> findById(long id) {
		return readerRepository.findById(id);	
    }
   
   public Reader createReader(Reader reader) {
	   Optional<Reader> checkReader=readerRepository.findByEmail(reader.getEmail());
	   if(checkReader.isPresent())
		   throw new UserEmailIsAlreadyBusy();
	   else
		   return readerRepository.save(reader);  
   }
   
   public Reader updateReader(Reader reader) {
	   return readerRepository.save(reader);
   }
   
   public void deleteReaderById(Long id) {
	   readerRepository.deleteById(id);
   }
   
}
