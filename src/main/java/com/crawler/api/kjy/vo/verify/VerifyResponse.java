package com.crawler.api.kjy.vo.verify;

import lombok.Data;

import java.util.List;

@Data
public class VerifyResponse {

    private List<TicketPrice> ticketPrice;
    private List<SettlementPrice> settlementPrice;
}
