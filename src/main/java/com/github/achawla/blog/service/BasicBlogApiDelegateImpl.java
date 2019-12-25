package com.github.achawla.blog.service;

import com.github.achawla.blog.exception.BlogNotFoundException;
import com.github.achawla.blog.generated.api.BasicBlogApiDelegate;
import com.github.achawla.blog.generated.dto.BlogDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
public class BasicBlogApiDelegateImpl implements BasicBlogApiDelegate {

    private BlogSevice blogSevice;

    @Override
    public ResponseEntity<Void> create(BlogDTO blogDTO) {

        blogSevice.create(blogDTO);

        ResponseEntity<Void> responseEntity = ResponseEntity.status(HttpStatus.CREATED).build();
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<BlogDTO>> list() {

        List<BlogDTO> blogDTOList = blogSevice.listAllBlogs();
        if(blogDTOList.isEmpty()){
            throw new NoSuchElementException();
        }
        ResponseEntity<List<BlogDTO>> responseEntity = ResponseEntity.ok(blogDTOList);
        return responseEntity;
    }

    @Override
    public ResponseEntity<BlogDTO> read(String name) {
        BlogDTO blogDTO = blogSevice.findBlogByName(name);

        if(blogDTO == null){
            throw new NoSuchElementException();
        }
        ResponseEntity<BlogDTO> responseEntity = ResponseEntity.ok(blogDTO);
        return responseEntity;
    }

    @Override
    public ResponseEntity<BlogDTO> update(String id, BlogDTO blogDTO) {

        blogDTO = blogSevice.update(id, blogDTO);

        if(blogDTO == null){
            throw new BlogNotFoundException("Blog not found with id- "+id);
        }
        return ResponseEntity.ok(blogDTO);
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        blogSevice.deleteBlog(id);

        return ResponseEntity.accepted().build();
    }
}
