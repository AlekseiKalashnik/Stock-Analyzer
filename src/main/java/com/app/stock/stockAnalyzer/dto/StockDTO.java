package com.app.stock.stockAnalyzer.dto;

import com.app.stock.stockAnalyzer.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    Integer avgTotalVolume;
    String calculationPrice;
    Double change;
    Double changePercent;
    Double close;
    String closeSource;
    Integer closeTime;
    String companyName;
    Currency currency;
    Double delayedPrice;
    Integer delayedPriceTime;
    Double extendedChange;
    Double extendedChangePercent;
    Double extendedPrice;
    Integer extendedPriceTime;
    Double high;
    String highSource;
    Integer highTime;
    Integer iexAskPrice;
    Integer iexAskSize;
    Integer iexBidPrice;
    Integer iexBidSize;
    Double iexClose;
    Integer iexCloseTime;
    Integer iexLastUpdated;
    Double iexMarketPercent;
    Double iexOpen;
    Integer iexOpenTime;
    Double iexRealtimePrice;
    Integer iexRealtimeSize;
    Integer iexVolume;
    Integer lastTradeTime;
    Double latestPrice;
    String latestSource;
    String latestTime;
    Integer latestUpdate;
    Integer latestVolume;
    Double low;
    String lowSource;
    Integer lowTime;
    Integer marketCap;
    Double oddLotDelayedPrice;
    Integer oddLotDelayedPriceTime;
    Double open;
    Integer openTime;
    String openSource;
    Double peRatio;
    Double previousClose;
    Integer previousVolume;
    String primaryExchange;
    String symbol;
    Integer volume;
    Double week52High;
    Double week52Low;
    Double ytdChange;
    boolean isUSMarketOpen;
}
