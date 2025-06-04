package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = new WiseSayingRepository();
    }

    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(content, author);

        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findForList(String keywordType, String keyword) {
        if (keyword.isBlank()) return wiseSayingRepository.findForList();

        return switch (keywordType) {
            case "content" -> wiseSayingRepository.findForListByContentContaining(keyword);
            case "author" -> wiseSayingRepository.findForListByAuthorContaining(keyword);
            default -> throw new IllegalStateException("Unexpected value: " + keywordType);
        };
    }

    public boolean delete(int id) {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);

        if(wiseSaying == null) return false;

        wiseSayingRepository.delete(wiseSaying);

        return true;
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepository.save(wiseSaying);
    }
}