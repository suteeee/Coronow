package com.kt.coronow.restApi.data

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "header")
data class CoronaHeader (
    @PropertyElement
    val resultCode: Int?,

    @PropertyElement
    val resultMsg: String?
)
