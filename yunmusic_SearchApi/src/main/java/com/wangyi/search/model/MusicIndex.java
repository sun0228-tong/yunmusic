package com.wangyi.search.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "yunmusic",type = "music")
public class MusicIndex {
    private int id;
    private String songName;
    private String singer;
    private String mvName;
    private String songList;
}
