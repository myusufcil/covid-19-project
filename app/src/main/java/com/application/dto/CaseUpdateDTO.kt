package com.application.dto

import com.application.model.IBaseModel

/*
  Created by Muhammed Yusuf ÇİL
  22.04.2020 - 22:24    
  myusufcl7@gmail.com
*/

data class CaseUpdateDTO(
    var totalDeaths: String,
    var totalRecovered: String,
    var totalCases: String,
    var country:String
) : IBaseModel {
    override val type: Int
        get() = IBaseModel.TYPE_CASE_UPDATE
}