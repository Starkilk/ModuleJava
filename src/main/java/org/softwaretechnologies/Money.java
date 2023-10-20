package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;//если один и тоже объект
        if(o == null || getClass() != o.getClass())return false;//проверка является ли объект экземпляром класса Money

        Money object = (Money) o;//поняли, что объект это экземпляр Money и привели тип
        //получили float значения двух объектов
        float floatValueThis = this.amount.floatValue();
        float floatValueObject = object.amount.floatValue();

        //получили 4ую цифру поле запятой
        int digitThis = (int) (floatValueThis * 10000) % 10;
        int digitObject = (int) (floatValueObject * 10000) % 10;

        //объекты, в которые поместим округлённые значения
        BigDecimal thisMoney;
        BigDecimal objectMoney;

        //округляем в большую или в меньшую сторону
        if(digitThis >= 5){
            thisMoney = this.amount.setScale(4,RoundingMode.HALF_UP);
        }else{
            thisMoney = this.amount.setScale(4,RoundingMode.HALF_DOWN);
        }

        if(digitObject >= 5){
            objectMoney = this.amount.setScale(4,RoundingMode.HALF_UP);
        }else{
            objectMoney = this.amount.setScale(4,RoundingMode.HALF_DOWN);
        }

        return thisMoney.equals(objectMoney);
    }

    /**
     * Формула:
     * (Если amount null, то 10000, иначе количество денег окрукленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @return хеш код по указанной формуле
     */
    @Override
    public int hashCode() {
        // TODO: реализуйте вышеуказанную функцию


        Random random = new Random();
        return random.nextInt();
    }

    /**
     * Верните строку в формате
     * Тип_ВАЛЮТЫ: количество.XXXX
     * Тип_валюты: USD, EURO, RUB или KRONA
     * количество.XXXX - округленный amount до 4х знаков.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     * <p>
     * Если тип валюты null, то вернуть:
     * null: количество.XXXX
     * Если количество денег null, то вернуть:
     * Тип_ВАЛЮТЫ: null
     * Если и то и то null, то вернуть:
     * null: null
     *
     * @return приведение к строке по указанному формату.
     */
    @Override
    public String toString() {
        if(type == null && amount == null){
            return "null: null";
        }
        //получили float значение объекта
        float floatValue = amount.floatValue();
        //получили 4ую цифру после запятой
        int digit = (int)(floatValue * 10000) % 10;

        //если тип валюты равен ничему
        if(type == null){
            if(digit >= 5){
                return "null: " + amount.setScale(4, RoundingMode.HALF_UP);
            }else {
                return "null: " + amount.setScale(4, RoundingMode.HALF_DOWN);
            }
        }

        //если количество денег не указано
        if(amount == null){
            return type + ": null";
        }

        //если указан тип и количество
        String string;
        if(digit >= 5){
            string = type+": "+amount.setScale(4,RoundingMode.HALF_UP);
        }else {
            string = type+": "+amount.setScale(4,RoundingMode.HALF_DOWN);
        }
        return string;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public static void main(String[] args) {
        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.USD, BigDecimal.valueOf(10.5000));
        System.out.println(money1.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}
