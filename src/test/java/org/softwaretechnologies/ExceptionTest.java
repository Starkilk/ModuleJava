package org.softwaretechnologies;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"UnusedAssignment", "CaughtExceptionImmediatelyRethrown"})
public class ExceptionTest {
    @Test
    public void test1() {
        int x = 1000;
        try {
            throw new NullPointerException();//выбрасываем исключение
        } catch (NullPointerException e) {
            x += 1;
        } catch (Exception e) {//не выполниться так как ловим NullPointerException
            x += 20;
        } finally {//finally говорит о том, что код выполнится в любом случае
            x += 300;
        }
        assertEquals(1301, x);//проверка: равен ли x 1301у
    }

    @Test
    public void test2() {
        int x = 1000;
        try {
            throw new NullPointerException();//выбрасываем исключение
        } catch (NullPointerException  e) {
            x += 2;
        } catch (Exception e) {//не выполниться так как ловим NullPointerException
            x += 10;
        }
        assertEquals(1002, x);
    }

    @Test
    public void test3() {
        int x = 0;
        try {
            throw new FileNotFoundException();
        } catch (RuntimeException e) {
            x = 1;
        } catch (Exception e) {//выполнится это, тк FileNotFoundException является подтипом Exception
            x = 2;
        }
        assertEquals(2, x);
    }

    @Test
    public void test4() {
        int x = 0;
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {//вы
            x = 1;
        } catch (Exception e) {//NullPointerException не является подтипом Exception
            x = 2;
        }
        assertEquals(1, x);
    }

    @Test
    public void test5() {
        int x = 0;
        try {
            try {
                throw new Exception();
            } catch (Exception e) {//ловим ошибку, выполняем код
                x += 10;
            }
        }
        //2 catch не сработают, тк на из уровне не было ошибки
        catch (NullPointerException e) {
            x += 200;
        } catch (Exception e) {
            x += 3000;
        } finally {
            x += 400;//выполняется тк finally
        }
        assertEquals(410, x);
    }

    String s = "";
    void f() throws Exception {
        try {
            s += "a";//просто выполняется
            throw new Exception();
        } catch (Exception e) {
            throw e;
        } finally {
            s += "b";//выполняется тк finally
        }
    }

    @Test
    public void test6() {
        try {
            f();//функция сработала, записала в s "ab"
        } catch (Exception e) {//словило Exception в этой функции
            s += "c";//выполнило код
        }
        assertEquals("abc", s);
    }
}
