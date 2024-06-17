package com.example.api.service;

import com.example.api.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class ApplyServiceTests {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void 한번만응보(){
        applyService.apply(1L);
        long count = couponRepository.count();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void 여러번응모() throws InterruptedException {
        int threadCount = 1000;
        //  정렬을 도와주는 라이브러리
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        // 모든 작업이 종료될때 까지 기다려줌
        CountDownLatch latch = new CountDownLatch(threadCount);
        for(int i = 0; i < threadCount; i++){
            long userId = i;
            executorService.submit(() -> {
                try{

                    applyService.apply(userId);
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long count = couponRepository.count();

        assertThat(count).isEqualTo(100);

    }


}