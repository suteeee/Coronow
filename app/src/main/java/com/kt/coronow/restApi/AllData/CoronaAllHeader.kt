package com.kt.coronow.restApi.AllData

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "header")
data class CoronaAllHeader (
    @PropertyElement
    val resultCode: Int?,

    @PropertyElement
    val resultMsg: String?
)