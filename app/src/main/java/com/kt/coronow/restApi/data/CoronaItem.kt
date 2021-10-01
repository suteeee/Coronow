package com.kt.coronow.restApi.data

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class CoronaItem(
    @PropertyElement(name = "createDt")
    val createDt: String?,

    @PropertyElement(name = "deathCnt")
    val deathCnt: String?,

    @PropertyElement(name = "defCnt")
    val defCnt: String?,

    @PropertyElement(name = "gubun")
    val gubun: String?,

    @PropertyElement(name = "gubunCn")
    val gubunCn: String?,

    @PropertyElement(name = "gubunEn")
    val gubunEn: String?,

    @PropertyElement(name = "incDec")
    val incDec: String?,

    @PropertyElement(name = "isolClearCnt")
    val isolClearCnt: String?,

    @PropertyElement(name = "isolIngCnt")
    val isolIngCnt: String?,

    @PropertyElement(name = "localOccCnt")
    val localOccCnt: String?,

    @PropertyElement(name = "overFlowCnt")
    val overFlowCnt: String?,

    @PropertyElement(name = "qurRate")
    val qurRate: String?,

    @PropertyElement(name = "seq")
    val seq: String?,

    @PropertyElement(name = "stateDt")
    val stateDt: String?,

    @PropertyElement(name = "updateDt")
    val updateDt: String?
)