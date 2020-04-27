package com.application.model

/*
  Created by Muhammed Yusuf ÇİL
  19.04.2020 - 14:45    
  myusufcl7@gmail.com
*/      
interface IBaseModel{
    val type :Int
    companion object{
        const val TYPE_CASE_UPDATE=1
        const val TYPE_NEWS=2
    }
}

