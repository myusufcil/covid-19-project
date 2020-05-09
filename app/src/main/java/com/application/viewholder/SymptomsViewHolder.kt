package com.application.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.application.adapter.IRecyclerViewClickListener
import com.application.covid_19.R
import com.application.dto.SymptomsDTO
import com.application.model.IBaseModel
import com.squareup.picasso.Picasso


/*
Created by Mustafa Tekkılıç 
*/
class SymptomsViewHolder(view: View) : BaseViewHolder(view) {

    var imageView: ImageView = view.findViewById(R.id.iv_symptoms_card)
    var diseaseText: TextView = view.findViewById(R.id.tv_name_symptoms_card)

    override fun bindView(baseModel: IBaseModel, position: Int, click: IRecyclerViewClickListener) {

        val item = baseModel as SymptomsDTO
        item.let {
            diseaseText.text = item.nameSymptoms
            Picasso.get().load(item.picsSymptoms).into(imageView)
        }

    }
}