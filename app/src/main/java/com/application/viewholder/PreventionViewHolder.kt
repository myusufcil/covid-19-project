package com.application.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.application.adapter.IRecyclerViewClickListener
import com.application.covid_19.R
import com.application.dto.PreventionDTO
import com.application.model.IBaseModel
import com.squareup.picasso.Picasso

class PreventionViewHolder(view: View) : BaseViewHolder(view) {

    var image:ImageView=view.findViewById(R.id.iv_prevention_card)
    var descriptionText:TextView=view.findViewById(R.id.tv_description_prevention_card)

    override fun bindView(baseModel: IBaseModel, position: Int, click: IRecyclerViewClickListener) {

        val item= baseModel as PreventionDTO
        item.let {
            descriptionText.text=item.descriptionPrevention
            Picasso.get().load(item.picsPrevention).into(image)
        }

    }


}