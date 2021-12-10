package org.market.hedge;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.market.hedge.service.account.MHAccountService;
import org.market.hedge.service.trade.MHTradeService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BaseMHExchange extends BaseExchange implements  MHExchange {

    protected MHExchangeSpecification mHexchangeSpecification;
    protected MHMarketDataService mHmarketDataService;
    protected MHTradeService mHtradeService;
    protected MHAccountService mHaccountService;
    protected StreamingParsing streamingParsing;

    public BaseMHExchange() {
    }

    @Override
    protected abstract void initServices();

    @Override
    public void applySpecification(MHExchangeSpecification exchangeSpecification) {
        MHExchangeSpecification defaultSpecification = this.getDefaultExchangeSpecification(exchangeSpecification.getTradingArea());
        if (exchangeSpecification == null) {
            this.mHexchangeSpecification = defaultSpecification;
        } else {
            if (exchangeSpecification.getExchangeName() == null) {
                exchangeSpecification.setExchangeName(defaultSpecification.getExchangeName());
            }

            if (exchangeSpecification.getExchangeDescription() == null) {
                exchangeSpecification.setExchangeDescription(defaultSpecification.getExchangeDescription());
            }

            if (exchangeSpecification.getSslUri() == null) {
                exchangeSpecification.setSslUri(defaultSpecification.getSslUri());
            }

            if (exchangeSpecification.getHost() == null) {
                exchangeSpecification.setHost(defaultSpecification.getHost());
            }

            if (exchangeSpecification.getPlainTextUri() == null) {
                exchangeSpecification.setPlainTextUri(defaultSpecification.getPlainTextUri());
            }

            if (exchangeSpecification.getExchangeSpecificParameters() == null) {
                exchangeSpecification.setExchangeSpecificParameters(defaultSpecification.getExchangeSpecificParameters());
            } else {
                Iterator var3 = defaultSpecification.getExchangeSpecificParameters().entrySet().iterator();

                while(var3.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry)var3.next();
                    if (exchangeSpecification.getExchangeSpecificParametersItem((String)entry.getKey()) == null) {
                        exchangeSpecification.setExchangeSpecificParametersItem((String)entry.getKey(), entry.getValue());
                    }
                }
            }

            this.mHexchangeSpecification = exchangeSpecification;
        }

        if (this.mHexchangeSpecification.getMetaDataJsonFileOverride() != null) {
            try {
                FileInputStream is = new FileInputStream(this.mHexchangeSpecification.getMetaDataJsonFileOverride());

                try {
                    this.loadExchangeMetaData(is);
                } catch (Throwable var9) {
                    try {
                        is.close();
                    } catch (Throwable var7) {
                        var9.addSuppressed(var7);
                    }

                    throw var9;
                }

                is.close();
            } catch (IOException var10) {
                throw new ExchangeException(var10);
            }
        } else if (this.mHexchangeSpecification.getExchangeName() != null) {
            try {
                InputStream is = BaseExchangeService.class.getClassLoader().getResourceAsStream(this.getMetaDataFileName(this.mHexchangeSpecification) + ".json");

                try {
                    this.loadExchangeMetaData(is);
                } catch (Throwable var11) {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Throwable var6) {
                            var11.addSuppressed(var6);
                        }
                    }

                    throw var11;
                }

                if (is != null) {
                    is.close();
                }
            } catch (IOException var12) {
                throw new ExchangeException(var12);
            }
        } else {
            this.logger.warn("No \"exchange name\" found in the ExchangeSpecification. The name is used to load the meta data file from the classpath and may lead to unexpected results.");
        }

        this.initServices();
        if (this.mHexchangeSpecification.isShouldLoadRemoteMetaData()) {
            try {
                //this.logger.info("Calling Remote Init...");
                this.remoteInit();
            } catch (IOException var8) {
                throw new ExchangeException(var8);
            }
        }

    }

    @Override
    public List<CurrencyPair> getExchangeSymbols() {
        return new ArrayList(this.getExchangeMetaData().getCurrencyPairs().keySet());
    }

    public String getMetaDataFileName(MHExchangeSpecification exchangeSpecification) {
        return exchangeSpecification.getExchangeName().toLowerCase().replace(" ", "").replace("-", "").replace(".", "");
    }

    @Override
    public MHExchangeSpecification getExchangeSpecification() {
        return this.mHexchangeSpecification;
    }

    @Override
    public MHMarketDataService getMarketDataService() {
        return this.mHmarketDataService;
    }

    @Override
    public MHTradeService getTradeService() {
        return this.mHtradeService;
    }

    @Override
    public MHAccountService getAccountService() {
        return this.mHaccountService;
    }


    @Override
    public StreamingParsing getStreamingParsing() {
        return this.streamingParsing;
    }

    @Override
    public ExchangeSpecification getDefaultExchangeSpecification() {
        return this.mHexchangeSpecification;
    }
}


