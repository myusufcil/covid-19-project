package com.application.dto

import com.application.model.IBaseModel

data class SymptomsDTO(

    var picsSymptoms:Int,
    var nameSymptoms:String

):IBaseModel{
    override val type: Int
        get()= IBaseModel.TYPE_SYMPTOMS

}