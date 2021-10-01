package com.kt.coronow.restApi.data

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class CoronaItems (
    @Element(name = "item")
    val item : List<CoronaItem>?
)