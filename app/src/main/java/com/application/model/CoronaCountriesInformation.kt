package com.application.model

import com.google.gson.annotations.SerializedName

/*
  Created by Muhammed Yusuf ÇİL
  19.04.2020 - 14:54    
  myusufcl7@gmail.com
*/

data class CoronaCountriesInformation(
  var result:List<Result>
)

data class Result(
    var country: String,
    var totalCases: String,
    var newCases: String,
    var totalDeaths: String,
    var newDeaths: String,
    var totalRecovered: String,
    var activeCases: String
)