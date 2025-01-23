package com.example.demo.domain.rental.cost.service;

import com.example.demo.domain.rental.cost.entity.CostPolicy;
import com.example.demo.domain.rental.cost.repository.CostPolicyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CostPolicyService {
    private final CostPolicyRepository cpRepository;
    private ConcurrentHashMap<Long, CostPolicy> policyMap;

    public CostPolicyService(CostPolicyRepository cpRepository) {
        this.cpRepository = cpRepository;
        policyMap = new ConcurrentHashMap<>();
    }

    @PostConstruct
    void init() {
        loadPolicies();
    }

    /**
     * 정책을 로드
     */
    public void loadPolicies() {
        var policies = cpRepository.findAll();

        var policyMap = new ConcurrentHashMap<Long, CostPolicy>();
        policies.forEach(p -> {
            policyMap.put(p.getVideoTypeId(), p);
        });

        this.policyMap = policyMap;
    }

    public CostPolicy findMatchedPolicy(long videoTypeId) {
        return policyMap.get(videoTypeId);
    }
}
