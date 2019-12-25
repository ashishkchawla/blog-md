package com.github.achawla.blog.service;

import com.github.achawla.blog.generated.dto.BlogDTO;
import com.github.achawla.blog.mapper.BlogDTOToMdFile;
import com.github.achawla.blog.mapper.MdFileToBlogDTO;
import com.github.achawla.blog.model.MdFile;
import com.github.achawla.blog.repository.MdFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogSevice {
    private MdFileRepository mdFileRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    public BlogDTO create(BlogDTO blogDTO){
        MdFile mdFile = BlogDTOToMdFile.MAPPER.map(blogDTO);

        mdFile.setId(String.valueOf(sequenceGeneratorService.generateSequence(MdFile.SEQUENCE_NAME)));

        mdFile = mdFileRepository.save(mdFile);

        return MdFileToBlogDTO.MAPPER.map(mdFile);
    }

    public List<BlogDTO> listAllBlogs(){
        List<MdFile> listMdFile = mdFileRepository.findAll();
        List<BlogDTO> listBlogs = new ArrayList<>();
        listMdFile.stream().forEach(mdFile -> listBlogs.add(
                new BlogDTO()
                        .title(mdFile.getTitle())
                        .name(mdFile.getName())
                        .id(mdFile.getId())
                        .timeUpdated((mdFile.getTimeUpdated()!=null)?mdFile.getTimeUpdated().atOffset(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())):null)));
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

    public BlogDTO findBlogByName(String name){
        List<MdFile> listMdFile = mdFileRepository.findByName(name);

        if (listMdFile.size()>0) {
            MdFile mdFile = listMdFile.get(0);
            mdFile.setMdContents(MarkdownToHTML.convertMDToHTML(mdFile.getMdContents()));

            return MdFileToBlogDTO.MAPPER.map(mdFile);
        } else {
            return null;
        }
    }

    public BlogDTO update(String id, BlogDTO blogDTO){
        Optional<MdFile> optionalMdFile = mdFileRepository.findById(id);

        if (optionalMdFile.isPresent()) {
            MdFile mdFile = BlogDTOToMdFile.MAPPER.map(blogDTO);

            mdFileRepository.save(mdFile);
            return MdFileToBlogDTO.MAPPER.map(mdFile);

        }

        return null;
    }

    public void deleteBlog(String id){
        mdFileRepository.deleteById(id);
    }



}
