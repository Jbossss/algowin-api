package org.market.hedge.bibox.dto;

import java.util.List;

/**
 * Result of batched calls (all POST requests)
 *
 * @param <T>
 * @author odrotleff
 */
public class BiboxMultipleResponses<T> extends BiboxResponse<List<BiboxResponse<T>>> {}
