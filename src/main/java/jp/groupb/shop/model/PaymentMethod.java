package jp.groupb.shop.model;
public enum PaymentMethod {
    CREDIT_CARD("クレジットカード"), BANK_TRANSFER("銀行振込"), CASH_ON_DELIVERY("代金引換");
    private final String label;
    PaymentMethod(String label) { this.label = label; }
    public String getLabel() { return label; }
}

