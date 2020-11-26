/*
 * Базовый абстрактный класс для элемента выражения (операнд/оператор)
 */
package com.splenectomy.calculator.calculator.expressionitems;

import com.splenectomy.calculator.calculator.enums.ExpressionItemType;

public abstract class ExpressionItem {
    protected ExpressionItemType expressionItemType = ExpressionItemType.NULL_ITEM_TYPE;
    protected String text;

    public ExpressionItemType getItemType() {
        return expressionItemType;
    }
    public String getText() { return text; }

    @Override
    public String toString() {
        return "{" +
                "expressionItemType=" + expressionItemType +
                ", text='" + text + '\'' +
                '}';
    }
}
