package org.market.hedge;

import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.Assert;
import org.market.hedge.core.TradingArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory to provide the following to {@link MHExchange}:
 *
 * <ul>
 *   <li>Manages the creation of specific Exchange implementations using runtime dependencies
 * </ul>
 */
public enum MHExchangeFactory {
  INSTANCE;

  // flags
  private final Logger log = LoggerFactory.getLogger(MHExchangeFactory.class);

  /** Constructor */
  MHExchangeFactory() {}


  /**
   * Create an Exchange object with default ExchangeSpecification
   *
   * <p>The factory is parameterized with the name of the exchange implementation class. This must
   * be a class extending {@link MHExchange}.
   *
   * @param exchangeClassName the fully-qualified class name of the exchange
   * @return a new exchange instance configured with the default {@link
   *     org.knowm.xchange.ExchangeSpecification}
   */
  public MHExchange createExchange(String exchangeClassName, TradingArea tradingArea) {

    return createExchange(exchangeClassName, null, null,tradingArea);
  }

  /**
   * Create an Exchange object with default ExchangeSpecification
   *
   * <p>The factory is parameterized with the name of the exchange implementation class. This must
   * be a class extending {@link MHExchange}.
   *
   * @param exchangeClass the class of the exchange
   * @return a new exchange instance configured with the default {@link
   *     org.knowm.xchange.ExchangeSpecification}
   */
  public <T extends MHExchange> T createExchange(Class<T> exchangeClass,TradingArea tradingArea) {

    return createExchange(exchangeClass, null, null,tradingArea);
  }

  /**
   * Create an Exchange object with default ExchangeSpecification with authentication info and API
   * keys provided through parameters
   *
   * <p>The factory is parameterized with the name of the exchange implementation class. This must
   * be a class extending {@link MHExchange}.
   *
   * @param exchangeClassName the fully-qualified class name of the exchange
   * @param apiKey the public API key
   * @param secretKey the secret API key
   * @return a new exchange instance configured with the default {@link
   *     MHExchangeSpecification}
   */
  public MHExchange createExchange(String exchangeClassName, String apiKey, String secretKey,TradingArea tradingArea) {

    Assert.notNull(exchangeClassName, "exchangeClassName cannot be null");

    log.debug("Creating default exchange from class name");

    MHExchange exchange = createExchangeWithoutSpecification(exchangeClassName);

    MHExchangeSpecification defaultExchangeSpecification = exchange.getDefaultExchangeSpecification(tradingArea);
    if (apiKey != null) defaultExchangeSpecification.setApiKey(apiKey);
    if (secretKey != null) defaultExchangeSpecification.setSecretKey(secretKey);
    exchange.applySpecification(defaultExchangeSpecification);

    return exchange;
  }

  /**
   * Create an Exchange object with default ExchangeSpecification with authentication info and API
   * keys provided through parameters
   *
   * <p>The factory is parameterized with the name of the exchange implementation class. This must
   * be a class extending {@link MHExchange}.
   *
   * @param exchangeClass the class of the exchange
   * @param apiKey the public API key
   * @param secretKey the secret API key
   * @return a new exchange instance configured with the default {@link
   *     MHExchangeSpecification}
   */
  public <T extends MHExchange> T createExchange(
      Class<T> exchangeClass, String apiKey, String secretKey,TradingArea tradingArea) {

    Assert.notNull(exchangeClass, "exchange cannot be null");

    log.debug("Creating default exchange from class name");

    T exchange = createExchangeWithoutSpecification(exchangeClass);

    MHExchangeSpecification defaultExchangeSpecification = exchange.getDefaultExchangeSpecification(tradingArea);
    if (apiKey != null) defaultExchangeSpecification.setApiKey(apiKey);
    if (secretKey != null) defaultExchangeSpecification.setSecretKey(secretKey);
    exchange.applySpecification(defaultExchangeSpecification);

    return exchange;
  }

  /**
   * Create an Exchange object default ExchangeSpecification
   *
   * @param exchangeSpecification the exchange specification
   * @return a new exchange instance configured with the provided {@link
   *     MHExchangeSpecification}
   */
  public MHExchange createExchange(MHExchangeSpecification exchangeSpecification) {

    Assert.notNull(exchangeSpecification, "exchangeSpecfication cannot be null");

    log.debug("Creating exchange from specification");

    String exchangeClassName = exchangeSpecification.getExchangeClassName();
    MHExchange exchange = createExchangeWithoutSpecification(exchangeClassName);
    exchange.applySpecification(exchangeSpecification);
    return exchange;
  }

  /**
   * Create an Exchange object without default ExchangeSpecification
   *
   * <p>The factory is parameterized with the name of the exchange implementation class. This must
   * be a class extending {@link MHExchange}.
   *
   * @param exchangeClassName the fully-qualified class name of the exchange
   * @return a new exchange instance configured with the default {@link
   *     MHExchangeSpecification}
   */
  public MHExchange createExchangeWithoutSpecification(String exchangeClassName) {

    Assert.notNull(exchangeClassName, "exchangeClassName cannot be null");

    log.debug("Creating default exchange from class name");
    // Attempt to create an instance of the exchange provider
    try {

      // Attempt to locate the exchange provider on the classpath

      Class exchangeProviderClass = Class.forName(exchangeClassName);

      // Test that the class implements Exchange
      if (MHExchange.class.isAssignableFrom(exchangeProviderClass)) {
        // Instantiate through the default constructor and use the default exchange specification
        return createExchangeWithoutSpecification(exchangeProviderClass);
      } else {
        throw new ExchangeException(
            "Class '" + exchangeClassName + "' does not implement Exchange");
      }
    } catch (ClassNotFoundException e) {
      throw new ExchangeException("Problem creating Exchange (class not found)", e);
    }
  }

  /**
   * Create an Exchange object without default ExchangeSpecification
   *
   * <p>The factory is parameterized with the name of the exchange implementation class. This must
   * be a class extending {@link MHExchange}.
   *
   * @param exchangeClass the class of the exchange
   * @return a new exchange instance configured with the default {@link
   *     MHExchangeSpecification}
   */
  public <T extends MHExchange> T createExchangeWithoutSpecification(Class<T> exchangeClass) {

    Assert.notNull(exchangeClass, "exchangeClassName cannot be null");

    log.debug("Creating default exchange from class name");

    // Attempt to create an instance of the exchange provider
    try {

      // Instantiate through the default constructor and use the default exchange specification
      return exchangeClass.newInstance();

    } catch (InstantiationException e) {
      throw new ExchangeException("Problem creating Exchange (instantiation)", e);
    } catch (IllegalAccessException e) {
      throw new ExchangeException("Problem creating Exchange (illegal access)", e);
    }
  }
}
