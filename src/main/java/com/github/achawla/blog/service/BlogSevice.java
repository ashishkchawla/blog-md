package com.github.achawla.blog.service;

import com.github.achawla.blog.generated.dto.BlogDTO;
import com.github.achawla.blog.mapper.BlogDTOToMdFile;
import com.github.achawla.blog.mapper.MdFileToBlogDTO;
import com.github.achawla.blog.model.MdFile;
import com.github.achawla.blog.repository.MdFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogSevice {
    @Autowired
    private MdFileRepository mdFileRepository;

    public BlogDTO create(BlogDTO blogDTO){
        MdFile mdFile = BlogDTOToMdFile.MAPPER.map(blogDTO);

        mdFile = mdFileRepository.save(mdFile);

        return MdFileToBlogDTO.MAPPER.map(mdFile);
    }

    public List<BlogDTO> listAllBlogs(){
        List<MdFile> listMdFile = mdFileRepository.findAll();
        List<BlogDTO> listBlogs = new ArrayList<>();
        listMdFile.stream().forEach(mdFile -> listBlogs.add(MdFileToBlogDTO.MAPPER.map(mdFile)));
        return listBlogs;
    }


    public BlogDTO readBlog(String id){
        Optional<MdFile> optionalMdFile = mdFileRepository.findById(id);

        if (optionalMdFile.isPresent()) {
            optionalMdFile.ifPresent(mdFile -> mdFile.setMdContents(MarkdownToHTML.convertMDToHTML(mdFile.getMdContents())));

            return MdFileToBlogDTO.MAPPER.map(optionalMdFile.get());
        } else {
            return null;
        }
    }



}
