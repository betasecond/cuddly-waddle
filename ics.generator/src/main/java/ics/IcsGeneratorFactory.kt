package ics

import ics.impl.IcsGeneratorImpl
import ics.impl.IcsGeneratorImplKt
import java.time.LocalDate

object IcsGeneratorFactory {
    fun createIcsGenerator(date: LocalDate?): IcsGenerator {
        return createIcsGenerator_old(date)
    }

    fun createIcsGenerator_new(date: LocalDate?): IcsGenerator {
        return IcsGeneratorImplKt(date!!)
    }

    fun createIcsGenerator_old(date: LocalDate?): IcsGenerator {
        return object : IcsGeneratorImpl(date) {
        }
    }
}
