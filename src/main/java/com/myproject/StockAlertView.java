package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>();

    public StockAlertView(double highThreshold, double lowThreshold) {
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        double price = stockPrice.getAvgPrice();
        String stockCode = stockPrice.getCode();

        if (price >= alertThresholdHigh && (!lastAlertedPrices.containsKey(stockCode) || lastAlertedPrices.get(stockCode) != price)) {
            alertAbove(stockCode, price);
            lastAlertedPrices.put(stockCode, price);
        } else if (price <= alertThresholdLow && (!lastAlertedPrices.containsKey(stockCode) || lastAlertedPrices.get(stockCode) != price)) {
            alertBelow(stockCode, price);
            lastAlertedPrices.put(stockCode, price);
        } else {
            lastAlertedPrices.remove(stockCode);
        } 
    }

    private void alertAbove(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }
}
