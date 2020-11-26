/*
 * Элемент выражения - оператор
 */
package com.splenectomy.calculator.calculator.expressionitems;

import com.splenectomy.calculator.calculator.enums.ExpressionItemType;
import com.splenectomy.calculator.calculator.enums.OperationPriority;

public class OperatorItem extends ExpressionItem{
    private OperationPriority priority = OperationPriority.NULL_PRIORITY;

    public OperatorItem(String text) {
        this.text = text;
        expressionItemType = ExpressionItemType.OPERATOR;
    }

    public OperationPriority getPriority() {
        if (priority == OperationPriority.NULL_PRIORITY) {
            if (!text.isEmpty()){
                switch (text) {
                    case "-" :
                    case "+" :
                        priority = OperationPriority.LOW; break;
                    case "x" :
                    case "/" :
                        priority = OperationPriority.HIGH; break;
                    default:
                        priority = OperationPriority.NULL_PRIORITY;
                }
            }
        }
        return priority;
    }


}
