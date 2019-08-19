package com.github.achawla.blog.repository;

import com.github.achawla.blog.model.MdFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MdFileRepository extends MongoRepository<MdFile, String> {
}
