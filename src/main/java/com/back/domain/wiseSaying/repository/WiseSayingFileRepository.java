package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;
import com.back.standard.util.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository {

    public String getEntityFilePath(WiseSaying wiseSaying) {
        return getEntityFilePath(wiseSaying.getId());
    }

    public String getTableDirPath() {
        return "db/wiseSaying";
    }

    public String getEntityFilePath(int id) {
        return getTableDirPath() + "/%d.json".formatted(id);
    }

    public String getLastIdFilePath() {
        return getTableDirPath() + "/lastId.txt";
    }

    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            int newId = getLastId() + 1;
            wiseSaying.setId(newId);
            setLastId(newId);
        }

        Map<String, Object> wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJsonStr = Util.json.toString(wiseSayingMap);
        Util.file.set(getEntityFilePath(wiseSaying), wiseSayingJsonStr);
    }

    private void setLastId(int newId) {
        Util.file.set(getLastIdFilePath(), newId);
    }

    private int getLastId() {
        return Util.file.getAsInt(getLastIdFilePath(), 0);
    }

    public Optional<WiseSaying> findById(int id) {
        String wiseSayingJsonStr = Util.file.get(getEntityFilePath(id), "");

        if (wiseSayingJsonStr.isBlank()) return Optional.empty();

        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJsonStr);

        return Optional.of(new WiseSaying(wiseSayingMap));
    }

    public boolean delete(WiseSaying wiseSaying) {
        return Util.file.delete(getEntityFilePath(wiseSaying));

    }

    public void clear() {
        Util.file.rmdir(getTableDirPath());
    }

    public Page<WiseSaying> findForList(Pageable pageable) {
        List<WiseSaying> content = findAll();
        int totalCount = content.size();

        return new Page<>(
                totalCount,
                pageable.getPageNo(),
                pageable.getPageSize(),
                content
        );
    }

    public Page<WiseSaying> findForListByContentContaining(String keyword, Pageable pageable) {
        List<WiseSaying> content = findByContentContaining(keyword);
        int totalCount = content.size();

        return new Page<>(
                totalCount,
                pageable.getPageNo(),
                pageable.getPageSize(),
                content
        );
    }

    private List<WiseSaying> findAll() {
        return Util.file.walkRegularFiles(
                        getTableDirPath(),
                        "\\d+\\.json"
                )
                .map(path -> Util.file.get(path.toString(), ""))
                .map(Util.json::toMap)
                .map(WiseSaying::new)
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed()) // id 순 역순정렬
                .toList();
    }

    private List<WiseSaying> findByContentContaining(String keyword) {
        return Util.file.walkRegularFiles(
                        getTableDirPath(),
                        "\\d+\\.json"
                )
                .map(path -> Util.file.get(path.toString(), ""))
                .map(Util.json::toMap)
                .map(WiseSaying::new)
                .filter(w -> w.getContent().contains(keyword))
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed()) // id 순 역순정렬
                .toList();
    }
}