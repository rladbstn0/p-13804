package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.global.app.AppConfig;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;
import com.back.standard.util.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class WiseSayingFileRepository implements WiseSayingRepository{
    public String getEntityFilePath(WiseSaying wiseSaying) {
        return getEntityFilePath(wiseSaying.getId());
    }

    public String getTableDirPath() {
        return AppConfig.getMode() + "Db/wiseSaying";
    }

    public String getEntityFilePath(int id) {
        return getTableDirPath() + "/%d.json".formatted(id);
    }

    public String getLastIdFilePath() {
        return getTableDirPath() + "/lastId.txt";
    }

    public WiseSaying save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            int newId = getLastId() + 1;
            wiseSaying.setId(newId);
            setLastId(newId);
        }

        Map<String, Object> wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJsonStr = Util.json.toString(wiseSayingMap);
        Util.file.set(getEntityFilePath(wiseSaying), wiseSayingJsonStr);

        return wiseSaying;
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
        List<WiseSaying> filtered = findAll();
        return createPage(filtered, pageable);
    }

    public Page<WiseSaying> findForListByContentContaining(String keyword, Pageable pageable) {
        List<WiseSaying> filtered = findByContentContaining(keyword);
        return createPage(filtered, pageable);
    }

    public Page<WiseSaying> findForListByAuthorContaining(String keyword, Pageable pageable) {
        List<WiseSaying> filtered = findByAuthorContaining(keyword);
        return createPage(filtered, pageable);
    }

    public Page<WiseSaying> findForListByContentContainingOrAuthorContaining(String keyword1, String keyword2, Pageable pageable) {
        List<WiseSaying> filtered = findByContentContainingOrAuthorContaining(keyword1, keyword2);
        return createPage(filtered, pageable);
    }

    private Page<WiseSaying> createPage(List<WiseSaying> wiseSayings, Pageable pageable) {
        int totalCount = wiseSayings.size();

        List<WiseSaying> content = wiseSayings
                .stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(
                totalCount,
                pageable.getPageNo(),
                pageable.getPageSize(),
                content
        );
    }

    public List<WiseSaying> findAll() {
        return loadAllWiseSayings()
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed())
                .toList();
    }

    private List<WiseSaying> findByContentContaining(String keyword) {
        return loadAllWiseSayings()
                .filter(w -> w.getContent().contains(keyword))
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed())
                .toList();
    }

    private List<WiseSaying> findByAuthorContaining(String keyword) {
        return loadAllWiseSayings()
                .filter(w -> w.getAuthor().contains(keyword))
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed())
                .toList();
    }

    private List<WiseSaying> findByContentContainingOrAuthorContaining(String keyword1, String keyword2) {
        return loadAllWiseSayings()
                .filter(w -> w.getContent().contains(keyword1) || w.getAuthor().contains(keyword2))
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed())
                .toList();
    }

    private Stream<WiseSaying> loadAllWiseSayings() {
        return Util.file.walkRegularFiles(
                        getTableDirPath(),
                        "\\d+\\.json"
                )
                .map(path -> Util.file.get(path.toString(), ""))
                .map(Util.json::toMap)
                .map(WiseSaying::new);
    }
}