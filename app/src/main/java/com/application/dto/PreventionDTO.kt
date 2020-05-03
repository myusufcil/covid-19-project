package com.application.dto

import com.application.model.IBaseModel

data class PreventionDTO(
    var picsPrevention: Int,
    var descriptionPrevention: String
) : IBaseModel {
    override val type: Int
        get() = IBaseModel.TYPE_PREVENTION
}