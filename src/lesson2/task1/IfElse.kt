@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.abs

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if ((age % 100 > 9) && (age % 100 < 20))
        return "$age лет"
    else
        when {
            age % 10 == 1 -> return "$age год"
            age % 10 in 2..4 -> return "$age года"
            age % 10 in 5..9 -> return "$age лет"
            else -> return "$age лет"
        }
}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s = t1 * v1 + t2 * v2 + t3 * v3   //все расстояние
    if (t1 * v1 > s / 2.0) return (s / 2.0) / v1
    else
        if (t1 * v1 + t2 * v2 > s / 2.0) return t1 + (s / 2.0 - t1 * v1) / v2
        else return t1 + t2 + (s / 2.0 - t1 * v1 - t2 * v2) / v3
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val rook1X = rookX1 == kingX
    val rook1Y = rookY1 == kingY
    val rook2X = rookX2 == kingX
    val rook2Y = rookY2 == kingY
    when {
        (rook1X || rook1Y) && (rook2X || rook2Y) -> return 3
        rook1X || rook1Y -> return 1
        rook2X || rook2Y -> return 2
        else -> return 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val isThreatenedByRookX = rookX == kingX
    val isThreatenedByRookY = rookY==kingY
    val isThreatenedByBishop = abs(bishopX - kingX) == abs(bishopY - kingY)

    when {
        (isThreatenedByRookX || isThreatenedByRookY) && (isThreatenedByBishop) -> return 3
        isThreatenedByBishop -> return 2
        isThreatenedByRookX || isThreatenedByRookY -> return 1
        else -> return 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var cos = 0.0
    val sqrB = b*b
    val sqrA = a*a
    val sqrC = c*c
    if ((a + b > c) && (a + c > b) && (b + c > a)) {
        when {
            maxOf(a, b, c) == a -> cos = (sqrB + sqrC - sqrA) / (2 * b * c)
            maxOf(a, b, c) == b -> cos = (sqrC + sqrA - sqrB) / (2 * a * c)
            else -> cos = (sqrA + sqrB - sqrC) / (2 * a * b)
        }
        if (cos > 0) return 0 else if (cos < 0) return 2 else return 1
    } else return -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (!(c>b||a>d)) {
        when {
            c>=a&&d<=b -> return d-c
            a>=c&&b<=d -> return b-a
            a<=c -> return b-c
            else -> return d-a
        }
    }
    else return -1
}


