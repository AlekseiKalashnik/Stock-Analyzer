package com.app.stock.stockAnalyzer.dto;

import com.app.stock.stockAnalyzer.entity.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    private String symbol;
    private BigInteger avgTotalVolume;
    private String calculationPrice;
    private Double change;
    private Double changePercent;
    private Double close;
    private String closeSource;
    private BigInteger closeTime;
    private String companyName;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double delayedPrice;
    private BigInteger delayedPriceTime;
    private Double extendedChange;
    private Double extendedChangePercent;
    private Double extendedPrice;
    private BigInteger extendedPriceTime;
    private Double high;
    private String highSource;
    private BigInteger highTime;
    private BigInteger iexAskPrice;
    private BigInteger iexAskSize;
    private BigInteger iexBidPrice;
    private BigInteger iexBidSize;
    private Double iexClose;
    private BigInteger iexCloseTime;
    private BigInteger iexLastUpdated;
    private Double iexMarketPercent;
    private Double iexOpen;
    private BigInteger iexOpenTime;
    private Double iexRealtimePrice;
    private BigInteger iexRealtimeSize;
    private BigInteger iexVolume;
    private BigInteger lastTradeTime;
    private Double latestPrice;
    private String latestSource;
    private String latestTime;
    private BigInteger latestUpdate;
    private BigInteger latestVolume;
    private Double low;
    private String lowSource;
    private BigInteger lowTime;
    private BigInteger marketCap;
    private Double oddLotDelayedPrice;
    private BigInteger oddLotDelayedPriceTime;
    private Double open;
    private BigInteger openTime;
    private String openSource;
    private Double peRatio;
    private Double previousClose;
    private BigInteger previousVolume;
    private String primaryExchange;
    private BigInteger volume;
    private Double week52High;
    private Double week52Low;
    private Double ytdChange;
    private boolean isUSMarketOpen;
}
