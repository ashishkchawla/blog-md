package com.github.achawla.blog.service;

import com.github.achawla.blog.mapper.BlogDTOToMdFile;
import com.github.achawla.blog.mapper.MdFileToBlogDTO;
import com.github.achawla.blog.model.MdFile;
import com.github.achawla.blog.repository.MdFileRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.achawla.blog.generated.dto.BlogDTO;

@Service
public class BlogSevice {
    @Autowired
    private MdFileRepository mdFileRepository;

    public BlogDTO create(BlogDTO blogDTO){
        MdFile mdFile = BlogDTOToMdFile.MAPPER.map(blogDTO);

        mdFile = mdFileRepository.save(mdFile);

        return MdFileToBlogDTO.MAPPER.map(mdFile);
    }
}
