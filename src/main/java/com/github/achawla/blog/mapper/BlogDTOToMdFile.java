package com.github.achawla.blog.mapper;

import com.github.achawla.blog.generated.dto.BlogDTO;
import com.github.achawla.blog.model.MdFile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogDTOToMdFile {

    BlogDTOToMdFile MAPPER = Mappers.getMapper(BlogDTOToMdFile.class);

    MdFile  map(BlogDTO blogDTO);


}
