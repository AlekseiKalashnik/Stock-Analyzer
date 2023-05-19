package com.app.stock.stockAnalyzer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "stock")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @Column
    private String symbol;
    @Column
    private BigInteger avgTotalVolume;
    @Column
    private String calculationPrice;
    @Column
    private Double change;
    @Column
    private Double changePercent;
    @Column
    private Double close;
    @Column
    private String closeSource;
    @Column
    private BigInteger closeTime;
    @Column
    private String companyName;
    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column
    private Double delayedPrice;
    @Column
    private BigInteger delayedPriceTime;
    @Column
    private Double extendedChange;
    @Column
    private Double extendedChangePercent;
    @Column
    private Double extendedPrice;
    @Column
    private BigInteger extendedPriceTime;
    @Column
    private Double high;
    @Column
    private String highSource;
    @Column
    private BigInteger highTime;
    @Column
    private BigInteger iexAskPrice;
    @Column
    private BigInteger iexAskSize;
    @Column
    private BigInteger iexBidPrice;
    @Column
    private BigInteger iexBidSize;
    @Column
    private Double iexClose;
    @Column
    private BigInteger iexCloseTime;
    @Column
    private BigInteger iexLastUpdated;
    @Column
    private Double iexMarketPercent;
    @Column
    private Double iexOpen;
    @Column
    private BigInteger iexOpenTime;
    @Column
    private Double iexRealtimePrice;
    @Column
    private BigInteger iexRealtimeSize;
    @Column
    private BigInteger iexVolume;
    @Column
    private BigInteger lastTradeTime;
    @Column
    private Double latestPrice;
    @Column
    private String latestSource;
    @Column
    private String latestTime;
    @Column
    private BigInteger latestUpdate;
    @Column
    private BigInteger latestVolume;
    @Column
    private Double low;
    @Column
    private String lowSource;
    @Column
    private BigInteger lowTime;
    @Column
    private BigInteger marketCap;
    @Column
    private Double oddLotDelayedPrice;
    @Column
    private BigInteger oddLotDelayedPriceTime;
    @Column
    private Double open;
    @Column
    private BigInteger openTime;
    @Column
    private String openSource;
    @Column
    private Double peRatio;
    @Column
    private Double previousClose;
    @Column
    private BigInteger previousVolume;
    @Column
    private String primaryExchange;
    @Column
    private BigInteger volume;
    @Column
    private Double week52High;
    @Column
    private Double week52Low;
    @Column
    private Double ytdChange;
    @Column
    private boolean isUSMarketOpen;

}
