package com.example.vestwallet.utils

import com.example.vestwallet.models.pfis.ConversionStep
import com.example.vestwallet.models.pfis.Pfi

//val inputCurrency = "NGN"
//val outputCurrency = "MXN"



//fun findConversionPath(
//    pfiList: List<Pfi>,
//    fromCurrency: String,
//    toCurrency: String,
//    allowReverse: Boolean = true
//): List<Pair<Pfi, Boolean>> {
//    val queue = ArrayDeque<Triple<String, List<Pair<Pfi, Boolean>>, Boolean>>()
//    val visited = mutableSetOf<String>()
//
//    queue.add(Triple(fromCurrency, emptyList(), true))
//    visited.add(fromCurrency)
//
//    while (queue.isNotEmpty()) {
//        val (currentCurrency, path, isForward) = queue.removeFirst()
//
//        if (currentCurrency == toCurrency) {
//            return path
//        }
//
//        for (pfi in pfiList) {
//            for ((from, to) in pfi.conversions) {
//                if (isForward && from == currentCurrency && to !in visited) {
//                    queue.add(Triple(to, path + (pfi to true), true))
//                    visited.add(to)
//                } else if (allowReverse && !isForward && to == currentCurrency && from !in visited) {
//                    queue.add(Triple(from, path + (pfi to false), false))
//                    visited.add(from)
//                }
//            }
//            if (allowReverse) {
//                for ((from, to) in pfi.conversions) {
//                    if (isForward && to == currentCurrency && from !in visited) {
//                        queue.add(Triple(from, path + (pfi to false), false))
//                        visited.add(from)
//                    } else if (!isForward && from == currentCurrency && to !in visited) {
//                        queue.add(Triple(to, path + (pfi to true), true))
//                        visited.add(to)
//                    }
//                }
//            }
//        }
//    }
//
//    return emptyList() // No path found
//}

fun findDetailedConversionPath(pfiList: List<Pfi>, fromCurrency: String, toCurrency: String, allowReverse: Boolean = true): List<ConversionStep> {
    val queue = ArrayDeque<Triple<String, List<ConversionStep>, Boolean>>()
    val visited = mutableSetOf<String>()

    queue.add(Triple(fromCurrency, emptyList(), true))
    visited.add(fromCurrency)

    while (queue.isNotEmpty()) {
        val (currentCurrency, path, isForward) = queue.removeFirst()

        if (currentCurrency == toCurrency) {
            return path
        }

        for (pfi in pfiList) {
            for ((from, to) in pfi.conversions) {
                if (isForward && from == currentCurrency && to !in visited) {
                    val newStep = ConversionStep(pfi, from, to, true)
                    queue.add(Triple(to, path + newStep, true))
                    visited.add(to)
                } else if (allowReverse && !isForward && to == currentCurrency && from !in visited) {
                    val newStep = ConversionStep(pfi, to, from, false)
                    queue.add(Triple(from, path + newStep, false))
                    visited.add(from)
                }
            }
            if (allowReverse) {
                for ((from, to) in pfi.conversions) {
                    if (isForward && to == currentCurrency && from !in visited) {
                        val newStep = ConversionStep(pfi, to, from, false)
                        queue.add(Triple(from, path + newStep, false))
                        visited.add(from)
                    } else if (!isForward && from == currentCurrency && to !in visited) {
                        val newStep = ConversionStep(pfi, from, to, true)
                        queue.add(Triple(to, path + newStep, true))
                        visited.add(to)
                    }
                }
            }
        }
    }

    return emptyList() // No path found
}