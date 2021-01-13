package com.slbruno.bookstore.authors.mapper;

import com.slbruno.bookstore.authors.dto.AuthorDTO;
import com.slbruno.bookstore.authors.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author toModel(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}
