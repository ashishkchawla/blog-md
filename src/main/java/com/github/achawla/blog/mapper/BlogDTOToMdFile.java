package com.github.achawla.blog.mapper;

import com.github.achawla.blog.generated.dto.BlogDTO;
import com.github.achawla.blog.model.MdFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogDTOToMdFile {

    BlogDTOToMdFile MAPPER = Mappers.getMapper(BlogDTOToMdFile.class);

    @Mappings({
            @Mapping(target = "timeCreated", expression = "java(blogDTO.getTimeCreated().toLocalDateTime())"),
            @Mapping(target = "timeUpdated", expression = "java(blogDTO.getTimeUpdated().toLocalDateTime())")
    })
    MdFile  map(BlogDTO blogDTO);


}
