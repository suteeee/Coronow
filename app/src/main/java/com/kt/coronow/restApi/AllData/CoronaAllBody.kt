package com.kt.coronow.restApi.AllData

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "body")
data class CoronaAllBody(
    @Element(name = "items")
    val items: CoronaAllItems?,

    @PropertyElement
    val numOfRows:Int?,

    @PropertyElement
    val pageNo:Int?,

    @PropertyElement
    val totalCount:Int?
)