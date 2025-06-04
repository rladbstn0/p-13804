package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying save(WiseSaying wiseSaying) {
        if(wiseSaying.isNew()){
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }

        return wiseSaying;
    }

    public List<WiseSaying> findForList() {
        return wiseSayings.reversed();
    }

    public int findIndexById(int id) {

        return IntStream.range(0, wiseSayings.size())
                .filter(index -> wiseSayings.get(index).getId()==id)
                .findFirst()
                .orElse(-1);
    }

    public WiseSaying findById(int id) {
        int index = findIndexById(id);

        if(index == -1) return null;

        return wiseSayings.get(index);
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }

    public List<WiseSaying> findForListByContentContaining(String keyword) {
        return wiseSayings
                .stream()
                .filter(w -> w.getContent().contains(keyword))
                .collect(Collectors.toList())
                .reversed();
    }

    public List<WiseSaying> findForListByAuthorContaining(String keyword) {
        return wiseSayings
                .stream()
                .filter(w -> w.getAuthor().contains(keyword))
                .collect(Collectors.toList())
                .reversed();
    }

    public List<WiseSaying> findForListByContentContainingOrAuthorContaining(String keyword1, String keyword2) {
        return wiseSayings
                .stream()
                .filter(
                        w -> w.getContent().contains(keyword1) || w.getAuthor().contains(keyword2)
                )
                .collect(Collectors.toList())
                .reversed();
    }
}