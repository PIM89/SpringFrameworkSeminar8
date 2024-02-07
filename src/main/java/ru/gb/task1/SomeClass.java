package ru.gb.task1;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.gb.task2.RecoverException;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Data
@Component
@NoArgsConstructor
public class SomeClass {
    @Timetable
    @RecoverException(noRecoverFor = {ArithmeticException.class, NullPointerException.class})
    public Integer someMethod() {
        System.out.println("Метод вызван!");
        int randomNum = ThreadLocalRandom.current().nextInt(100, 2000 + 1);
        if (randomNum < 500) {
            throw new ArithmeticException("Случайное число меньше 500");
        }
        try {
            Thread.sleep(randomNum);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("Метод завершен!");
        return randomNum;
    }
}
