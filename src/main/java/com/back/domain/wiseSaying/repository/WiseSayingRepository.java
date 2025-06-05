package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.util.Optional;

public interface WiseSayingRepository {
    WiseSaying save(WiseSaying wiseSaying);


    Optional<WiseSaying> findById(int id);


    boolean delete(WiseSaying wiseSaying);


    Page<WiseSaying> findForList(Pageable pageable);


    Page<WiseSaying> findForListByContentContaining(String keyword, Pageable pageable);


    Page<WiseSaying> findForListByAuthorContaining(String keyword, Pageable pageable);


    Page<WiseSaying> findForListByContentContainingOrAuthorContaining(String keyword1, String keyword2, Pageable pageable);
}