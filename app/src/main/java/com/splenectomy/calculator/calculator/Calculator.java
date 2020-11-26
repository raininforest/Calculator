/*
 * Класс содержит статический метод для расчёта выражения в виде строки.
 * Вычисление производится с предварительным переводом выражения в постфиксную форму.
 * Выражение может содержать любое количество скобок, иметь унарные операции +,-.
 * При возникновении ошибок в результате возвращается ERROR.
 */
package com.splenectomy.calculator.calculator;

import com.splenectomy.calculator.calculator.enums.*;
import com.splenectomy.calculator.calculator.expressionitems.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Calculator {
    private static final String ERROR_MESSAGE = "ERROR";

    /**
     * Расчет выражения
     * @param expression - выражение, которое необходимо рассчитать
     * @return - результат выражения
     */
    public static String calculateExpression(String expression) {
        try {
            List<ExpressionItem> infixItemsList = parseInfixExpressionItems(expression);
            List<ExpressionItem> postfixItemsList = infixToPostfix(infixItemsList);
            String result = calculatePostfixExpression(postfixItemsList);
            return result;
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }
    }

    /**
     * Парсинг выражения
     * @param expression - строка с выражением
     * @return - список операндов и опреаторов
     */
    private static List<ExpressionItem> parseInfixExpressionItems(String expression) {
        List<ExpressionItem> expressionItems = new ArrayList<>();
        int charIndex = 0;
        while (charIndex < expression.length()) {
            //определяем текущий символ
            char currentChar = expression.charAt(charIndex);
            boolean isItUnaryMinusOrPlus = ((currentChar == '-') || (currentChar == '+')) &&
                    ((expressionItems.isEmpty()) || (expressionItems.get(expressionItems.size() - 1).getText().equals("(")));
            boolean isOperandBegins = (Character.isDigit(currentChar)) || (isItUnaryMinusOrPlus);
            boolean isItOperator = (currentChar == '-') ||
                    (currentChar == '+') ||
                    (currentChar == 'x') ||
                    (currentChar == '/') ||
                    (currentChar == '(') ||
                    (currentChar == ')');
            //если это начало операнда, то
            if (isOperandBegins) {
                StringBuilder operandBuilder = new StringBuilder();
                //начинаю читать операнд - символ за символом, пока символ не окажется оператором.
                boolean isOperandNotEnd = true;
                while (isOperandNotEnd) {
                    operandBuilder.append(currentChar);
                    charIndex++;
                    if (charIndex < expression.length()) {
                        currentChar = expression.charAt(charIndex);
                        isOperandNotEnd = ((Character.isDigit(currentChar)) ||
                                (currentChar == '.'));
                    } else {
                        isOperandNotEnd = false;
                    }
                }
                //когда сивмол - оператор, можно создавать операнд и добавлять его в список операторов/операндов
                //индекс не инкрементировать
                expressionItems.add(new OperandItem(operandBuilder.toString()));
            } else if (isItOperator) {
                //если текущий символ - оператор, то добавляем новый оператор в список оепраторов/операндов
                expressionItems.add(new OperatorItem(String.valueOf(currentChar)));
                //и инкрементируем индекс символа
                charIndex++;
            } else {
                expressionItems = null;
                break;
            }
        } //end of while
        return expressionItems;
    }

    /**
     * Преобразование списка в постфиксную форму
     * @param expressionItemListInInfixNotation - исходное выражение
     * @return - выражение (список) в постфиксной форме
     */
    private static List<ExpressionItem> infixToPostfix(List<ExpressionItem> expressionItemListInInfixNotation) {
        Deque<OperatorItem> operationStack = new ArrayDeque<>();
        List<ExpressionItem> outputList = new ArrayList<>();
        for (ExpressionItem expressionItem : expressionItemListInInfixNotation) {
            //если текущий элемент операнд, то добавляем его в выходной список
            if (expressionItem.getItemType() == ExpressionItemType.OPERAND) {
                outputList.add(expressionItem);
            }
            //иначе оператор...
            else {
                //если это открывающая скобка, то кладём ее в стек
                if (expressionItem.getText().equals("(")) {
                    operationStack.push((OperatorItem) expressionItem);
                }
                //или, если это закрывающая скобка, то выталкиваем операторы из стека в выходной список
                //пока не найдем закрывающую скобку
                else if (expressionItem.getText().equals(")")) {
                    while (!(operationStack.getFirst().getText().equals("("))) {
                        outputList.add(operationStack.pop());
                    }
                    if (operationStack.getFirst().getText().equals("(")) {
                        operationStack.pop();
                    }
                }
                //иначе это бинарная операция =, -, /, *
                else {
                    OperatorItem currenOperator = (OperatorItem) expressionItem;
                    //вытолкнем оператор из стека, если его приоритет равен или выше текущего
                    if (!operationStack.isEmpty()) {
                        if (operationStack.getFirst().getPriority().getRating() >= currenOperator.getPriority().getRating()) {
                            outputList.add(operationStack.pop());
                        }
                    }
                    //затем поместим текущий оператор в стек
                    operationStack.push(currenOperator);
                }
            }
        } //end of foreach
        if (!operationStack.isEmpty()) {
            for (ExpressionItem item : operationStack) {
                outputList.add(operationStack.pop());
            }
        }
        return outputList;
    }

    /**
     * Вычисление выражения
     * @param expressionItemListInPostfixNotation - выражение в постфиксной форме
     * @return - результат вычисления
     */
    private static String calculatePostfixExpression(List<ExpressionItem> expressionItemListInPostfixNotation) {
        Deque<Double> operandStack = new ArrayDeque<>();
        for (ExpressionItem expressionItem : expressionItemListInPostfixNotation) {
            if (expressionItem.getItemType() == ExpressionItemType.OPERAND) {
                operandStack.push(((OperandItem) expressionItem).getValue());
            } else if (expressionItem.getItemType() == ExpressionItemType.OPERATOR) {
                Double secondOperand = operandStack.pop();
                Double firstOperand = operandStack.pop();
                Double result;
                switch (expressionItem.getText()) {
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "x":
                        result = firstOperand * secondOperand;
                        break;
                    case "/": {
                        if (secondOperand != 0) {
                            try {
                                result = firstOperand / secondOperand;
                            } catch (Exception e) {
                                return ERROR_MESSAGE;
                            }
                        } else {
                            return ERROR_MESSAGE;
                        }
                    }
                    break;
                    default:
                        return ERROR_MESSAGE;
                }
                operandStack.push(result);
            }
        }
        String resultString = operandStack.pop().toString();
        return resultString;
    }
}
