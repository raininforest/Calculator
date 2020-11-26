/*
 * Приоритет оператора
 */
package com.splenectomy.calculator.calculator.enums;

public enum OperationPriority {
    LOW(1),
    HIGH(2),
    NULL_PRIORITY(0);

    private int rating;

    OperationPriority(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
