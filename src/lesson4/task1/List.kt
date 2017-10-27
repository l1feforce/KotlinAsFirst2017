@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.pow
import java.lang.Math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    val sqr = v.map {it*it}
    return sqrt(sqr.sum())
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    else {
        return list.sum()/list.size.toDouble()
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    else
    {
        val mid = mean(list)
        for (i in 0 until list.size)
        {
            list[i]-=mid
        }
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    val c = mutableListOf<Double>()
    for (i in 0..(b.size-1)) {
            c.add(a[i]*b[i])
        }
    return c.sum()
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    return p.foldIndexed(0.0)
    { index, previousResult, element -> previousResult + element * pow(x, index.toDouble()) }
}


/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty() || list.size == 1) return list
    var index = 0
    var sum = -list[1]
   var listCopy = list.map {
        sum+=list[(Math.abs(index-1))]
        index++
        it+sum
    }
    for (i in 0 until list.size)
    {
        list[i] = listCopy[i]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
  var number = n
    var k = 0
    var list = mutableListOf<Int>()
    while (number!=1)
    {
        for (i in 2..number)
        {
            if (number%i==0)
            {number /=i
            list.add(k , i)
                k+=1
                break
            }
        }
    }
    return list.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    var number = n
    var i = 1
    var k = 0
    var list = ""
    while (number!=1)
    {
        i+=1
            if (number%i==0) {
            number/=i
                k+=1
                if (k==1) {
                    list += "$i"
                    i = 2
                }
                else {
                    list += "*$i"
                    i = 2
                }
            }
        }
return list
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var list = listOf<Int>()
    var number = n
    while (number>=base)
    {
        list += number%base
        number/=base
    }
    list += number
    return list.asReversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var number = n
    var list = ""
    if (number<base) {
        if (number >= 10) {
            list += (number + 87).toChar()
            return list
        } else {
            list += number
            return list
        }
    }
    while (number >= base)
    {
        if (number%base>=10) list += (number%base+87).toChar()
        else list += number%base
        number /= base
    }
    if (number>=10) list += (number + 87).toChar()
    else
    list += number
    return list.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var sum = 0.0
    for (i in (digits.size-1) downTo 0)
    {
       sum += digits[i]* pow(base.toDouble(),(digits.size-1-i).toDouble())
    }
    return sum.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var sum= 0.0
    for (i in 0..(str.length-1))
    {
        if (str[i].toInt()<58)
        {
           sum += (str[i].toInt()-48)*pow(base.toDouble(),(str.length-1-i).toDouble())
        }
        else
        {
           sum += (str[i].toInt()-87)*pow(base.toDouble(),(str.length-1-i).toDouble())
        }
    }
    return sum.toInt()
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var str = ""
    val list = listOf("","один","два","три","четыре","пять","шесть","семь","восемь","девять")
    val list1 = listOf("","одна ","две ","три ","четыре ","пять ","шесть ","семь ","восемь ","девять ")
    val part = listOf(n/1000,n%1000)
    for (i in 0..1) {
            val n3 = part[i] / 100
            val n21 = part[i] % 100
            val n2 = n21 / 10
            val n1 = n21%10
            val k3 = list[n3]
            val k2 = list[n2]
            val k1 = list[n21 % 10]
            val k11 = list1[n21 % 10]
            when {
                n3 == 0 -> str += ""
                n3 == 1 -> str += "сто "
                n3 == 2 -> str += "двести "
                n3 == 3 -> str += "триста "
                n3 == 4 -> str += "четыреста "
                else -> str += "$k3" + "сот "
            }
        if (i==1) {
            when {
                n21 == 11 -> str += "одиннадцать"
                n21 == 12 -> str += "двенадцать"
                n21 == 13 -> str += "тринадцать"
                n21 == 14 -> str += "четырнадцать"
                n21 == 15 -> str += "пятнадцать"
                n21 == 16 -> str += "шестнадцать"
                n21 == 17 -> str += "семнадцать"
                n21 == 18 -> str += "восемнадцать"
                n21 == 19 -> str += "девятнадцать"
                n21 == 10 -> str += "десять"
                n2 in 2..3 -> str += "$k2" + "дцать " + k1
                n2 == 4 -> str += "сорок " + k1
                n2 in 5..8 -> str += "$k2" + "десят " + k1
                n2 == 9 -> str += "девяносто " + k1
                else -> str += k1
            }
        }
        if (i==0&&part[i]!=0)
        {
            when {
                n21 == 11 -> str += "одиннадцать "
                n21 == 12 -> str += "двенадцать "
                n21 == 13 -> str += "тринадцать "
                n21 == 14 -> str += "четырнадцать "
                n21 == 15 -> str += "пятнадцать "
                n21 == 16 -> str += "шестнадцать "
                n21 == 17 -> str += "семнадцать "
                n21 == 18 -> str += "восемнадцать "
                n21 == 19 -> str += "девятнадцать "
                n21 == 10 -> str += "десять "
                (n2 in 2..3) -> str += "$k2" + "дцать " + k11
                n2 == 4 -> str += "сорок " + k11
                n2 in 5..8 -> str += "$k2" + "десят " + k11
                n2 == 9 -> str += "девяносто " + k11
                else -> str += k11
            }
            when {
                n21 in 5..20 -> str+="тысяч"
                n1 == 1 -> str+="тысяча"
                n1 in 2..4 -> str+="тысячи"
                else -> str+="тысяч"
            }
            if (part[1]!=0) str+=" "
        }
        }
return str
}