package com.application.model


/*
Created by Mustafa Tekkılıç 
*/
data class CoronaNewsInformation(
    var result:ResultNews
)

data class ResultNews(
    var url:String,
    var description:String,
    var image:String,
    var name:String,
    var source:String,
    var date:String
)