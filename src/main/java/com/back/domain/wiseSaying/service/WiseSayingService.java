package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

    public class WiseSayingService {
        private final List<WiseSaying> wiseSayings = new ArrayList<>();
        private int lastId = 0;

        public WiseSaying write(String content, String author) {
            int id = ++lastId;
            WiseSaying wiseSaying = new WiseSaying(id, content, author);
            wiseSayings.add(wiseSaying);

            return wiseSaying;
        }

        public List<WiseSaying> findForList() {
            return wiseSayings.reversed();
        }
    }
