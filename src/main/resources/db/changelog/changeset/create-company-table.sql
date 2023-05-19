CREATE TABLE IF NOT EXISTS company
(
    id                  SERIAL PRIMARY KEY,
    symbol              VARCHAR(128),
    exchange            VARCHAR(128),
    exchangeSuffix      VARCHAR(128),
    exchangeName        VARCHAR(128),
    exchangeSegment     VARCHAR(128),
    exchangeSegmentName VARCHAR(128),
    company_name        VARCHAR(128),
    generated_date      DATE,
    stock_type          VARCHAR(128),
    iexId               VARCHAR(128),
    region              VARCHAR(128),
    currency            VARCHAR(128),
    isEnabled           BOOLEAN,
    figi                VARCHAR(128),
    cik                 VARCHAR(128),
    lei                 VARCHAR(128)
);