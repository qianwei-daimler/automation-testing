package com.daimler.postsales.automation.ap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentItemResponseDto {

    private String itemId;

    private Integer companyId;

    private String businessType;

    private BigDecimal paymentAmount;

    private BigDecimal originalPaymentAmount;

    private BigDecimal deductionAmount;

    private String borrowerName;

    private String dealerCode;

    private String dealerName;

    private String payeeBankAccount;

    private String contractNumber;

    private String payeeBankName;

    private String comments;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime receivedTime;

    private String vin;

    private String payeeBankBranchName;

    private String dataSource;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime paidTime;

    private String issueDetail;

    private String payeeBankAccountName;

    private String postscript;

    private String issueType;

    private String channel;

    private String debitAccount;

    private String payeeBankType;

    private String bankSerialNo;

    private String businessModelName;

    private String refundReason;

    private String duration;

    public BigDecimal getOriginalPaymentAmount() {
        return Optional.ofNullable(deductionAmount)
                .map(amount -> paymentAmount.add(amount))
                .orElse(paymentAmount);
    }

    public String getPayeeBankType() {
        return StringUtils.equals("OTHER", this.payeeBankType)
                ? "Others" : this.payeeBankType;
    }

    public String getOriginalBankCode() {
        return Optional.ofNullable(debitAccount)
                .filter(StringUtils::isNotBlank)
                .map(account -> StringUtils.split(account, "-")[0])
                .orElse(debitAccount);
    }
}
