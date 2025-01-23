package com.example.demo.domain.rental.cost.service;

import com.example.demo.domain.rental.cost.entity.CostPolicy;
import com.example.demo.domain.rental.cost.repository.CostPolicyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CostPolicyServiceTest {
    private CostPolicyService service;

    @BeforeEach
    void beforeEach() {
        var repo = mock(CostPolicyRepository.class);
        when(repo.findAll()).thenReturn(List.of(
                CostPolicy.builder().videoTypeId(1L).build(),
                CostPolicy.builder().videoTypeId(2L).build())
        );
        service = new CostPolicyService(repo);
        service.loadPolicies();
    }

    @Test
    @DisplayName("findMatchedPolicy: 매칭되는 정책이 있으면 반환")
    void findMatchedPolicy_return_policy_if_matched() {
        var existVideoId = 1L;

        var result = service.findMatchedPolicy(existVideoId);

        assertThat(result).isNotNull();
    }


    @Test
    @DisplayName("findMatchedPolicy: 매칭되는 정책이 없으면 null")
    void findMatchedPolicy_return_null_if_no_matched() {
        var notExistVideoId = 100L;

        var result = service.findMatchedPolicy(notExistVideoId);

        assertThat(result).isNull();
    }
}