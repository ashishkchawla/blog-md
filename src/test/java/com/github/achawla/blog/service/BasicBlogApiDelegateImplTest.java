package com.github.achawla.blog.service;

import com.github.achawla.blog.exception.BlogNotFoundException;
import com.github.achawla.blog.generated.api.BasicBlogApiDelegate;
import com.github.achawla.blog.generated.dto.BlogDTO;
import com.github.achawla.blog.mapper.BlogDTOToMdFile;
import com.github.achawla.blog.model.MdFile;
import com.github.achawla.blog.model.MdFileType;
import com.github.achawla.blog.repository.MdFileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasicBlogApiDelegateImplTest {

    private BlogDTO blogDTO;
    private MdFile mdFile;

    @Mock
    private MdFileRepository mdFileRepository;

    @InjectMocks
    private BlogSevice blogSevice;

    private BasicBlogApiDelegate basicBlogApiDelegate;

    @BeforeEach
    void setUp() {

        basicBlogApiDelegate = new BasicBlogApiDelegateImpl(blogSevice);
        blogDTO = new BlogDTO().fileType(BlogDTO.FileTypeEnum.BLOG)
                .id("101")
                .mdContents("## this is sample text")
                .name("testing")
                .timeCreated(OffsetDateTime.now())
                .timeUpdated(OffsetDateTime.now());

        mdFile = MdFile.builder()
                .fileType(MdFileType.BLOG)
                .mdContents("## this is sample text")
                .id("101")
                .name("testing")
                .timeCreated(LocalDateTime.now())
                .timeUpdated(LocalDateTime.now())
                .build();



    }

    @Test
    void whenCallingListMethod_thenReturnValidListOfBlogDTO() {

        when(mdFileRepository.findAll()).thenReturn(Arrays.asList(mdFile));

        ResponseEntity<List<BlogDTO>> responseEntity = basicBlogApiDelegate.list();

        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        assertTrue(responseEntity.getBody().get(0).getName().equals("testing"));

    }

    @Test
    void givenExistingBlogNumber_whenCallingReadMethod_thenReturnValidBlogDTO(){

        when(mdFileRepository.findById("101")).thenReturn(Optional.of(mdFile));

        ResponseEntity<BlogDTO> responseEntity = basicBlogApiDelegate.read("101");
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        assertTrue(responseEntity.getBody().getName().equals("testing"));
        assertTrue(responseEntity.getBody().getMdContents().contains("<h2>this is sample text</h2>"));


    }

    @Test
    void givenValidBlogDTO_whenCallingCreateMethod_thenReturn201Created(){

        MdFile mdFile = BlogDTOToMdFile.MAPPER.map(blogDTO);
        when(mdFileRepository.save(mdFile)).thenReturn(mdFile);

        ResponseEntity<Void> createResponse = basicBlogApiDelegate.create(blogDTO);

        assertTrue(createResponse.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    void givenInValidBlogId_whenCallingUpdateMethod_thenReturn400InvalidRequest(){
        when(mdFileRepository.findById("101")).thenReturn(Optional.empty());

        assertThrows(BlogNotFoundException.class, () -> basicBlogApiDelegate.update("101", blogDTO));
    }

    @Test
    void givenValidBlogId_whenCallingUpdateMethod_thenReturn200BlogUpdated(){
        when(mdFileRepository.findById("101")).thenReturn(Optional.of(mdFile));

        ResponseEntity<BlogDTO> responseEntity = basicBlogApiDelegate.update("101", blogDTO);
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        assertTrue(responseEntity.getBody().getName().equals("testing"));
    }

    @Test
    void givenValidBlogId_whenCallingDeleteMethod_thenReturn202Deleted(){
        doNothing().when(mdFileRepository).deleteById("101");

        ResponseEntity<Void> responseEntity = basicBlogApiDelegate.delete("101");
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.ACCEPTED));

    }

}