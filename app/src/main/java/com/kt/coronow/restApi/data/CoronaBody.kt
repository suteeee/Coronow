package com.kt.coronow.restApi.data

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "body")
data class CoronaBody(
    @Element(name = "items")
    val items: CoronaItems?,

    @PropertyElement
    val numOfRows:Int?,

    @PropertyElement
    val pageNo:Int?,

    @PropertyElement
    val totalCount:Int?
)
