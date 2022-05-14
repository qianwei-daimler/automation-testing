package com.daimler.postsales.automation.ap.cases

import com.daimler.postsales.automation.ap.api.PaymentItemFeignClient
import com.daimler.postsales.automation.ap.dto.PaymentItemManuallyHandleRequest
import com.daimler.postsales.automation.ap.dto.PaymentItemPageRequestDto
import com.daimler.postsales.automation.config.CommonConfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PaymentItemCasesSpec extends Specification {
    @Autowired
    CommonConfig commonConfig;

    @Autowired
    PaymentItemFeignClient paymentItemFeignClient;

    def "get payment item list of system exception"() {
        given: "data preparation"
        def param=PaymentItemPageRequestDto.builder().status("SYSTEM_EXCEPTION").build();

        when: "api request"
        def  result = paymentItemFeignClient.getPagedPaymentItems(commonConfig.getLoginToken(),param);

        then: "verify result"
        result != null
    }

    def "retry payment item that is system exception"() {
        given: "data preparation"
        def param= PaymentItemManuallyHandleRequest.builder().itemIds(List.of("A2204060100006"))
        .comment("testig").build()

        when: "api request"
        paymentItemFeignClient.systemExceptionRetry(commonConfig.getLoginToken(),param);

        then: "verify result"
        println "retry success"
    }
}