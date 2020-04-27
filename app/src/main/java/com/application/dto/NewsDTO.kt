package com.application.dto

import com.application.model.IBaseModel

data class NewsDTO(
//    var url:String,
    var description:String,
    var image:String,
    var name:String
//    var source:String,
//    var date:String
):IBaseModel{
    override val type: Int
        get() = IBaseModel.TYPE_NEWS

}