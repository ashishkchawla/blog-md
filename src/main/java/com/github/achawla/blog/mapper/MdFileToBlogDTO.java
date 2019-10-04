package com.github.achawla.blog.mapper;

import com.github.achawla.blog.generated.dto.BlogDTO;
import com.github.achawla.blog.model.MdFile;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper//(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MdFileToBlogDTO {

    MdFileToBlogDTO MAPPER = Mappers.getMapper(MdFileToBlogDTO.class);

    @Mappings({
            @Mapping(target = "timeCreated", expression = "java(mdFile.getTimeCreated()!=null?mdFile.getTimeCreated().atOffset(java.time.ZoneId.systemDefault().getRules().getOffset(java.time.LocalDateTime.now())):null)"),
            @Mapping(target = "timeUpdated", expression = "java(mdFile.getTimeUpdated()!=null?mdFile.getTimeUpdated().atOffset(java.time.ZoneId.systemDefault().getRules().getOffset(java.time.LocalDateTime.now())):null)")
    })
    BlogDTO  map(MdFile mdFile);


/*    LocalDateTime local = LocalDateTime.now();

    final ZoneId zone = ZoneId.systemDefault();
    LocalDateTime localDateTime = LocalDateTime.now();
    ZoneOffset zoneOffSet = zone.getRules().getOffset(localDateTime);
    OffsetDateTime offsetDateTime = localDateTime.atOffset(zoneOffSet);

    //System.out.println(offsetDateTime); // 2019-08-08T09:54:10.761+02:00
   BlogDTO blog = new BlogDTO().timeCreated(offsetDateTime);*/

}
