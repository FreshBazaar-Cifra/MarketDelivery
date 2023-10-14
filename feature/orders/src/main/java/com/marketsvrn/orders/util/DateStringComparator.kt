package com.marketsvrn.orders.util

object DateStringComparator : Comparator<String> {
    override fun compare(first: String, second: String): Int {
        val firstYear = first.substringAfter(" ").toInt()
        val firstMonth = MapperDefaults.MONTHS_STRING_MAP[first.substringBefore(" ")]!!
        val secondYear = second.substringAfter(" ").toInt()
        val secondMonth = MapperDefaults.MONTHS_STRING_MAP[second.substringBefore(" ")]!!
        if (firstYear < secondYear) {
            return 1
        } else if (firstYear > secondYear) {
            return -1
        }
        if (firstMonth < secondMonth) {
            return 1
        } else if (firstMonth > secondMonth) {
            return -1
        }
        return 0
    }
}