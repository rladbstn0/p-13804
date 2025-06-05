package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.Map;

public class WiseSayingFileRepository {


    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(1);
        }

        Map<String, Object> wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJsonStr = Util.json.toString(wiseSayingMap);
        Util.file.set("db/wiseSaying/1.json", wiseSayingJsonStr);
    }

    public WiseSaying findById(int id) {
        String wiseSayingJsonStr = Util.file.get("db/wiseSaying/%d.json".formatted(id), "");

        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJsonStr);

        return new WiseSaying(wiseSayingMap);
    }


}
