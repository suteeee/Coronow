package com.kt.coronow.restApi.AllData

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class CoronaAllData(
    @Element
    val header: CoronaAllHeader?,

    @Element
    val body: CoronaAllBody?


)
