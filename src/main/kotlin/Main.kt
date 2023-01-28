import kotlin.math.roundToInt
import kotlin.random.Random.Default.nextInt

fun main() {
    val randomInts = (List(20) { nextInt(260_000) }).sorted()
    for (secondsToFormat in randomInts) {
        println("""$secondsToFormat => ${agoToText(secondsToFormat)}""")
    }
}

fun agoToText(seconds: Int) : String {
    val secondsInDay = 24 * 60 * 60
    val secondsIn2Days = secondsInDay * 2
    val secondsIn3Days = secondsInDay * 3
    return when (true) {
        (seconds < 0) -> "ошибка"
        (seconds < 60) -> "только что"
        (seconds < 60 * 60) -> """${Math.floor(seconds.toDouble() / 60)} ${
            formatToPlural((seconds.toDouble() / 60).toInt(), arrayOf("минута", "минуты", "минут"))
        } назад"""
        (seconds < secondsInDay) -> """${Math.floor(seconds.toDouble() / 60 / 60).roundToInt()} ${
            formatToPlural((seconds.toDouble() / 60 / 60).toInt(), arrayOf("час", "часа", "часов"))
        } назад"""
        (seconds < secondsIn2Days) -> "вчера"
        (seconds < secondsIn3Days) -> "позавчера"
        else -> "давно"
    }
}

fun formatToPlural(count: Int, values: Array<String>): String {
    return when (true) {
        (count % 10 == 1) -> values[0]
        (count % 10 == 2) -> values[1]
        else -> values[2]
    }
}