package com.github.achawla.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class MdFile {

    @Id
    private String id;
    private String name;
    private String mdContents;

    private MdFileType fileType;

}
