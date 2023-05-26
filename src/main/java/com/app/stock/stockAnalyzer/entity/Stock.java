package com.app.stock.stockAnalyzer.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Table(name = "stock")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

    @Id
    @Column
    @JsonProperty(value = "symbol")
    private String symbol;
    @Column
    @JsonProperty(value = "avgTotalVolume")
    private BigInteger avgTotalVolume;
    @Column
    @JsonProperty(value = "calculationPrice")
    private String calculationPrice;
    @Column
    @JsonProperty(value = "change")
    private Double change;
    @Column
    @JsonProperty(value = "changePercent")
    private Double changePercent;
    @Column
    @JsonProperty(value = "close")
    private Double close;
    @Column
    @JsonProperty(value = "closeSource")
    private String closeSource;
    @Column
    @JsonProperty(value = "closeTime")
    private BigInteger closeTime;
    @Column
    @JsonProperty(value = "companyName")
    private String companyName;
    @Column
    @JsonProperty(value = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column
    @JsonProperty(value = "delayedPrice")
    private Double delayedPrice;
    @Column
    @JsonProperty(value = "delayedPriceTime")
    private BigInteger delayedPriceTime;
    @Column
    @JsonProperty(value = "extendedChange")
    private Double extendedChange;
    @Column
    @JsonProperty(value = "extendedChangePercent")
    private Double extendedChangePercent;
    @Column
    @JsonProperty(value = "extendedPrice")
    private Double extendedPrice;
    @Column
    @JsonProperty(value = "extendedPriceTime")
    private BigInteger extendedPriceTime;
    @Column
    @JsonProperty(value = "high")
    private Double high;
    @Column
    @JsonProperty(value = "highSource")
    private String highSource;
    @Column
    @JsonProperty(value = "highTime")
    private BigInteger highTime;
    @Column
    @JsonProperty(value = "iexAskPrice")
    private BigInteger iexAskPrice;
    @Column
    @JsonProperty(value = "iexAskSize")
    private BigInteger iexAskSize;
    @Column
    @JsonProperty(value = "iexBidPrice")
    private BigInteger iexBidPrice;
    @Column
    @JsonProperty(value = "iexBidSize")
    private BigInteger iexBidSize;
    @Column
    @JsonProperty(value = "iexClose")
    private Double iexClose;
    @Column
    @JsonProperty(value = "iexCloseTime")
    private BigInteger iexCloseTime;
    @Column
    @JsonProperty(value = "iexLastUpdated")
    private BigInteger iexLastUpdated;
    @Column
    @JsonProperty(value = "iexMarketPercent")
    private Double iexMarketPercent;
    @Column
    @JsonProperty(value = "iexOpen")
    private Double iexOpen;
    @Column
    @JsonProperty(value = "iexOpenTime")
    private BigInteger iexOpenTime;
    @Column
    @JsonProperty(value = "iexRealtimePrice")
    private Double iexRealtimePrice;
    @Column
    @JsonProperty(value = "iexRealtimeSize")
    private BigInteger iexRealtimeSize;
    @Column
    @JsonProperty(value = "iexVolume")
    private BigInteger iexVolume;
    @Column
    @JsonProperty(value = "lastTradeTime")
    private BigInteger lastTradeTime;
    @Column
    @JsonProperty(value = "latestPrice")
    private Double latestPrice;
    @Column
    @JsonProperty(value = "latestSource")
    private String latestSource;
    @Column
    @JsonProperty(value = "latestTime")
    private String latestTime;
    @Column
    @JsonProperty(value = "latestUpdate")
    private BigInteger latestUpdate;
    @Column
    @JsonProperty(value = "latestVolume")
    private BigInteger latestVolume;
    @Column
    @JsonProperty(value = "low")
    private Double low;
    @Column
    @JsonProperty(value = "lowSource")
    private String lowSource;
    @Column
    @JsonProperty(value = "lowTime")
    private BigInteger lowTime;
    @Column
    @JsonProperty(value = "marketCap")
    private BigInteger marketCap;
    @Column
    @JsonProperty(value = "oddLotDelayedPrice")
    private Double oddLotDelayedPrice;
    @Column
    @JsonProperty(value = "oddLotDelayedPriceTime")
    private BigInteger oddLotDelayedPriceTime;
    @Column
    @JsonProperty(value = "open")
    private Double open;
    @Column
    @JsonProperty(value = "openTime")
    private BigInteger openTime;
    @Column
    @JsonProperty(value = "openSource")
    private String openSource;
    @Column
    @JsonProperty(value = "peRatio")
    private Double peRatio;
    @Column
    @JsonProperty(value = "previousClose")
    private Double previousClose;
    @Column
    @JsonProperty(value = "previousVolume")
    private BigInteger previousVolume;
    @Column
    @JsonProperty(value = "primaryExchange")
    private String primaryExchange;
    @Column
    @JsonProperty(value = "volume")
    private BigInteger volume;
    @Column
    @JsonProperty(value = "week52High")
    private Double week52High;
    @Column
    @JsonProperty(value = "week52Low")
    private Double week52Low;
    @Column
    @JsonProperty(value = "ytdChange")
    private Double ytdChange;
    @Column
    @JsonProperty(value = "isUSMarketOpen")
    private boolean isUSMarketOpen;
}
