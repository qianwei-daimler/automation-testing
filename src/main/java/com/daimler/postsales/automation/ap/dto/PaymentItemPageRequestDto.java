package com.daimler.postsales.automation.ap.dto;


import com.daimler.postsales.automation.dto.AbstractPageableRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentItemPageRequestDto extends AbstractPageableRequest {
    private String type;

    private String status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate receivedStartDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate receivedEndDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate paidStartDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate paidEndDate;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String itemId;

    private String payeeName;

    private String contractNumber;

    private String businessModelName;

    private String issueType;

    private String queueOrder;

    private String exportType;

    private List<String> queueHeaders;

    private List<String> queueFieldsName;
}
