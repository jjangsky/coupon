package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;

    }

    public void apply(Long userId){
//        long count = couponRepository.count();
        long count = couponCountRepository.increment();

        if(count > 100){
            return;
        }

//        couponRepository.save(new Coupon(userId));
        couponCreateProducer.create(userId); // 프로듀서를 사용해 카프카로 DB 저장
    }
}
