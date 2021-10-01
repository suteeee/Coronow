package com.kt.coronow.restApi.AllData

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class CoronaAllItems (
    @Element(name = "item")
    val item : List<CoronaAllItem>?
)
