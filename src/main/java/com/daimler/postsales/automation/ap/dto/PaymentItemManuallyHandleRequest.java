package com.daimler.postsales.automation.ap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentItemManuallyHandleRequest {
    private List<String> itemIds;

    private String comment;
}
