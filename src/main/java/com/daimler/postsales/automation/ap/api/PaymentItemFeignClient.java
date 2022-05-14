package com.daimler.postsales.automation.ap.api;

import com.daimler.postsales.automation.ap.dto.PaymentItemManuallyHandleRequest;
import com.daimler.postsales.automation.ap.dto.PaymentItemPageRequestDto;
import com.daimler.postsales.automation.ap.dto.PaymentItemResponseDto;
import com.daimler.postsales.automation.dto.PageResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ap-payment-client", url = "${automation.api.ap.url}")
public interface PaymentItemFeignClient {
    @GetMapping("/app/v1/paymentitemservice/payment-items")
    PageResponseData<PaymentItemResponseDto> getPagedPaymentItems(@RequestHeader("X-AUTH-TOKEN") String token,
                                                                  @SpringQueryMap PaymentItemPageRequestDto params);

    @PutMapping("/app/v1/paymentitemservice/payment-items/actions/retry/system-exception")
    void systemExceptionRetry(@RequestHeader("X-AUTH-TOKEN") String token,
                              @RequestBody PaymentItemManuallyHandleRequest manuallyHandleRequest);
}
