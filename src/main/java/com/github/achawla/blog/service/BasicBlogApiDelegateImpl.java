package com.github.achawla.blog.service;

import com.github.achawla.blog.generated.api.BasicBlogApiDelegate;
import com.github.achawla.blog.generated.dto.BlogDTO;
import org.springframework.http.ResponseEntity;

public class BasicBlogApiDelegateImpl implements BasicBlogApiDelegate {

    @Override
    public ResponseEntity<Void> create(BlogDTO blogDTO) {
        return null;
    }
}
