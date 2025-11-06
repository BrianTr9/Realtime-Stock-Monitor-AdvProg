package com.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    private StockFeeder() {}

    public static StockFeeder getInstance() {
        if (instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        if (stockList.stream().noneMatch(s -> s.getCode().equals(stock.getCode()))) {
            stockList.add(stock);
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        Optional<Stock> stock = stockList.stream().filter(s -> s.getCode().equals(code)).findFirst();
        if (!stock.isPresent()) {
            Logger.errorRegister(code);
            return;
        }

        List<StockViewer> stockViewers = viewers.computeIfAbsent(code, k -> new ArrayList<>());
        // Ensure only one viewer of each type is registered
        boolean viewerExists = stockViewers.stream().anyMatch(v -> v.getClass().equals(stockViewer.getClass()));
        if (viewerExists) {
            Logger.errorRegister(code);
        } else {
            stockViewers.add(stockViewer);
        }
    }

    public void unregisterViewer(String code, StockViewer stockViewer) {
        List<StockViewer> stockViewers = viewers.get(code);
        if (stockViewers == null || !stockViewers.remove(stockViewer)) {
            Logger.errorUnregister(code);
        }
    }

    public void notify(StockPrice stockPrice) {
        List<StockViewer> stockViewers = viewers.get(stockPrice.getCode());
        if (stockViewers != null) {
            for (StockViewer viewer : stockViewers) {
                viewer.onUpdate(stockPrice);
            }
        }
    }
}
