package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.AppliedUserRepository;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    private  final AppliedUserRepository appliedUserRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer, AppliedUserRepository appliedUserRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;

    }

    public void apply(Long userId){
        // 쿠폰 인당 1개씩 발급 받기 위함
        Long apply = appliedUserRepository.add(userId);
        // SADD 사용 시, 추가 된 값이 있으면 그 값만큼 반환되지만
        // 중복으로 인해 추가가 안되면 0 반환
        if(apply != 1){
            return;
        }
//        long count = couponRepository.count();
        long count = couponCountRepository.increment();

        if(count > 100){
            return;
        }

//        couponRepository.save(new Coupon(userId));
        couponCreateProducer.create(userId); // 프로듀서를 사용해 카프카로 DB 저장
    }
}
