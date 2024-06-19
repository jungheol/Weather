package com.example.zerobase.weather.repository;

import com.example.zerobase.weather.domain.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest() {
        // given
        Memo newmemo = new Memo(10, "this is jpa memo");
        // when
        jpaMemoRepository.save(newmemo);
        // then
        List<Memo> memos = jpaMemoRepository.findAll();
        assertFalse(memos.isEmpty());
    }

    @Test
    void findByIdTest() {
        // given
        Memo newmemo = new Memo(11, "jpa memo");
        // when
        Memo memo = jpaMemoRepository.save(newmemo);
        // then
        Optional<Memo> result = jpaMemoRepository.findById(memo.getId());
        assertEquals(result.get().getText(), "jpa memo");
    }
}
