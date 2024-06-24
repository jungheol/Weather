package com.example.zerobase.weather.controller;

import com.example.zerobase.weather.domain.Diary;
import com.example.zerobase.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Operation(summary = "날씨와 일기텍스트를 이용해 DB에 일기 저장하기")
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "생성할 날짜를 입력해주세요", example = "2024-06-24") LocalDate date,
            @RequestBody String text
    ) {
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "선택한 날짜의 모든 일기 데이터를 가져오기")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "불러올 날짜를 입력해주세요", example = "2024-06-24") LocalDate date) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "선택한 기간의 모든 일기 데이터를 가져오기")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "처음 날짜를 입력해주세요", example = "2024-06-24") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "마지막 날짜를 입력해주세요", example = "2024-06-24") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @Operation(summary = "해당 날짜의 일기를 불러와서 수정하기")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "불러올 날짜를 입력해주세요", example = "2024-06-24") LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "해당 날짜의 일기를 삭제하기")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "삭제할 날짜를 입력해주세요", example = "2024-06-24") LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
