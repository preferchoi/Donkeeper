package com.ssafy.moneykeeperbackend.statistics.controller;

import com.ssafy.moneykeeperbackend.statistics.dto.CompareWithRecentXDto;
import com.ssafy.moneykeeperbackend.statistics.dto.MSRCDto;
import com.ssafy.moneykeeperbackend.statistics.dto.MonthSpendingRecordDto;
import com.ssafy.moneykeeperbackend.statistics.dto.TotalAndComparedDto;
import com.ssafy.moneykeeperbackend.statistics.service.StatService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistics")
public class StatController {
    private final StatService statService;
    @GetMapping("/comparemonths/{months}")
    @ApiOperation(value = "test", notes = "test")
    public ResponseEntity<?> compareWithRecentXMonths(@PathVariable int months, @RequestParam String id) {
        // Map<String,double[]> map = statService.compareWithRecentXMonths(months, Long.parseLong(id));
        List<CompareWithRecentXDto> li = statService.compareWithRecentXMonths(months, Long.parseLong(id));
        return new ResponseEntity<List<CompareWithRecentXDto>>(li, HttpStatus.OK);
    }

    @GetMapping("/spending/{year}/{month}")
    public ResponseEntity<?> getMonthSpending(@PathVariable int year, @PathVariable int month, @RequestParam String id) {
        MonthSpendingRecordDto msr = statService.getMonthSpending(year,month,Long.valueOf(id));
        return new ResponseEntity<MonthSpendingRecordDto>(msr,HttpStatus.OK);
    }

    @GetMapping("/compareusers/{year}/{month}")
    public ResponseEntity<?> compareWithUsers(@PathVariable int year, @PathVariable int month, @RequestParam String id) {
        TotalAndComparedDto tcd = statService.compareWithUsers(year,month,Long.parseLong(id));
        if (tcd == null) {
            // for now
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<TotalAndComparedDto>(tcd, HttpStatus.OK);
    }

    @GetMapping("/monthlyspendingbycat/{year}/{month}")
    public ResponseEntity<?> thisMonthSpendingByCategory(@PathVariable int year, @PathVariable int month, @RequestParam String id) {
        List<MSRCDto> msrcDtoList = statService.thisMonthSpendingByCategory(year,month,Long.parseLong(id));
        if (msrcDtoList == null) {
            // for now
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<MSRCDto>>(msrcDtoList, HttpStatus.OK);
    }
}
