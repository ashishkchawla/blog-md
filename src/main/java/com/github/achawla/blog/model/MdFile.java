package com.github.achawla.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(value = "mdfile")
@Builder
public class MdFile {

    @Transient
    public static final String SEQUENCE_NAME = "mdfile_sequence";

    @Id
    private String id;

    private String name;
    private String mdContents;

    private String title;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;

    private MdFileType fileType;

}
