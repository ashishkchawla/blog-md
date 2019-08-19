package com.github.achawla.blog.controller;

import com.github.achawla.blog.model.MdFile;
import com.github.achawla.blog.repository.MdFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/basic-blog")
public class MdFileController {

    @Autowired
    MdFileRepository mdFileRepository;

    @RequestMapping(value = "/mdfiles", method = RequestMethod.GET)
    public List<MdFile> getAllMdFiles() {
        return mdFileRepository.findAll();
    }

    @RequestMapping(value = "/mdfiles", method = RequestMethod.POST)
    public MdFile addMdFile(@RequestBody MdFile file){
        return mdFileRepository.save(file);

    }


}
