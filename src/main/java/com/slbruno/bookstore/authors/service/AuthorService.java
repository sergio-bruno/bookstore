package com.slbruno.bookstore.authors.service;

import com.slbruno.bookstore.authors.dto.AuthorDTO;
import com.slbruno.bookstore.authors.entity.Author;
import com.slbruno.bookstore.authors.exception.AuthorAlreadyExistsException;
import com.slbruno.bookstore.authors.exception.AuthorNotFoundException;
import com.slbruno.bookstore.authors.mapper.AuthorMapper;
import com.slbruno.bookstore.authors.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorService {

    private final static AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    private final AuthorRepository authorRepository;

    public AuthorDTO create(AuthorDTO authorDTO) {
        verifyIfExists(authorDTO.getName());
        Author authorToCreate = authorMapper.toModel(authorDTO);
        Author createdAuthor = authorRepository.save(authorToCreate);
        return authorMapper.toDTO(createdAuthor);
    }

    public AuthorDTO findByName(String name) {
        Author foundAuthor = authorRepository.findByName(name)
                .orElseThrow(() -> new AuthorNotFoundException(name));
        return authorMapper.toDTO(foundAuthor);
    }

    public List<AuthorDTO> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Author verifyAndGetIfExists(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public void delete(Long id) {
        verifyAndGetIfExists(id);
        authorRepository.deleteById(id);
    }

    private void verifyIfExists(String authorName) {
        authorRepository.findByName(authorName)
                .ifPresent(author -> {
                    throw new AuthorAlreadyExistsException(authorName);
                });
    }
}
