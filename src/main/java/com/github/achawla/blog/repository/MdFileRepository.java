package com.github.achawla.blog.repository;

import com.github.achawla.blog.model.MdFile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MdFileRepository extends MongoRepository<MdFile, String> {

    List<MdFile> findByName(String name);
}
