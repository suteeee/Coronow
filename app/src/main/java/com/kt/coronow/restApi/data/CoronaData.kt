package com.kt.coronow.restApi.data

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")

data class CoronaData(
    @Element
    val header: CoronaHeader?,

    @Element
    val body: CoronaBody?
)
