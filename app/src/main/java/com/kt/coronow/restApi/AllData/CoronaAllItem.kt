package com.kt.coronow.restApi.AllData

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class CoronaAllItem(
    @PropertyElement(name = "accDefRate")
    val accDefRate: String?,

    @PropertyElement(name = "accExamCnt")
    val accExamCnt: String?,

    @PropertyElement(name = "accExamCompCnt")
    val accExamCompCnt: String?,

    @PropertyElement(name = "careCnt")
    val careCnt: String?,

    @PropertyElement(name = "clearCnt")
    val clearCnt: String?,

    @PropertyElement(name = "createDt")
    val createDt: String?,

    @PropertyElement(name = "deathCnt")
    val deathCnt: String?,

    @PropertyElement(name = "decideCnt")
    val decideCnt: String?,

    @PropertyElement(name = "examCnt")
    val examCnt: String?,

    @PropertyElement(name = "resutlNegCnt")
    val resutlNegCnt: String?,

    @PropertyElement(name = "seq")
    val seq: String?,

    @PropertyElement(name = "stateDt")
    val stateDt: String?,

    @PropertyElement(name = "stateTime")
    val stateTime: String?,

    @PropertyElement(name = "updateDt")
    val updateDt: String?


)
