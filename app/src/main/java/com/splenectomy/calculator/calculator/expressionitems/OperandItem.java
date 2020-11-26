/*
 * Элемент выражения - операнд
 */
package com.splenectomy.calculator.calculator.expressionitems;

import com.splenectomy.calculator.calculator.enums.ExpressionItemType;

public class OperandItem extends ExpressionItem{
    private Double value = null;

    public OperandItem(String text) {
        this.text = text;
        expressionItemType = ExpressionItemType.OPERAND;
    }

    public Double getValue(){
        if (value == null) {
            value = Double.parseDouble(text);
        }
        return value;
    }
}
