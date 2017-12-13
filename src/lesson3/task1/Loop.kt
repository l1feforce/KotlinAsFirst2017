@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.numberRevert
import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var counter = 1
    var num = n
    while (abs(num) > 9) {
        counter += 1
        num /= 10
    }
    return counter
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n < 3) return 1
    var a1 = 1
    var a2 = 1
    var a3 = 0
    var k = 0
    for (i in 3..n) {
        a3 = a1 + a2
        a1 = a2
        a2 = a3
    }
    return a3
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var max = maxOf(m, n)
    var min = minOf(m, n)
    var t = 0
    while (max % min != 0) {
        max %= min
        t = min
        min = max
        max = t
    }
    return m * n / min
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k = 2
    while (n % k != 0) k += 1
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var k = n - 1
    while (n % k != 0) k -= 1
    return k
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (m == 1 || n == 1) return true
    var k = minOf(m, n).toDouble()
    val sqrtOfMin = floor(sqrt(k)).toInt()
    for (i in 2..sqrtOfMin + 1) {
        if (m % i == 0 && n % i == 0) return false
    }
    return maxOf(m, n) % minOf(m, n) != 0

}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean { //если в промежутке sqrt(m)..sqrt(n) есть целое число, то есть и квадрат в соот-ем промежутке
    val n1 = n.toDouble()
    val m1 = m.toDouble()
    return ceil(sqrt(n1)) - ceil(sqrt(m1)) >= 1 ||
            (sqrt(n1) == sqrt(m1) && sqrt(n1) == ceil(sqrt(n1)))
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double { //скоро переделаю
    var fac = 2*3
    var k = -1
    var part = x
    var t = 3
    var i = x*x*x
    var sum = part
    while (part>=abs(eps))
    {
        part = k*(i/fac)
        if (part<abs(eps)) break
        sum+=part
        k*=-1
        i*=x*x
        t+=1
        fac*=t*(t+1)
    }
    return sin(sum)
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */


fun cos(x: Double, eps: Double): Double {
    if (cos(x)<0) return -sqrt(1-sin(x,eps)*sin(x,eps)) else return sqrt(1-sin(x,eps)*sin(x,eps))
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var num = n
    var k = 0
    while (num > 0) {
        k = k * 10 + num % 10
        num /= 10
    }
    return k
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    return n == revert(n)
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = n
    val id = num % 10
    num /= 10
    while (num > 0) {
        if (num % 10 != id) return true
        num /= 10
    }
    return false
}

fun numberLength(n: Int): Int {
    var num = n
    var t = 0
    while (num > 0) {
        t += 1
        num /= 10
    }
    return t
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 1 4 9 16 25 36 49 64 81 100 121 144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var k = 0
    var num = 0
    while (k < n) {
        num += 1
        var numSqr = num * num
        k += numberLength(numSqr)
    }
    var numSqr = num * num
    if (k == n) return numSqr % 10 else
        return (numSqr / pow(10.0, (k - n).toDouble())).toInt() % 10
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var k = 0
    var num = 0
    while (k < n) {
        num += 1
        var numFib = fib(num)
        k += numberLength(numFib)
    }
    var numFib = fib(num)
    if (k == n) return numFib % 10 else
        return (numFib / pow(10.0, (k - n).toDouble())).toInt() % 10
}
