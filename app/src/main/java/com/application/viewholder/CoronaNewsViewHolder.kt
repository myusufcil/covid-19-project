package com.application.viewholder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.application.adapter.IRecyclerViewClickListener
import com.application.covid_19.R
import com.application.dto.NewsDTO
import com.application.model.IBaseModel
import com.squareup.picasso.Picasso
import com.ui.MainActivity
import com.ui.NewsDetailActivity
import kotlinx.android.synthetic.main.item_news_card.view.*


/*
Created by Mustafa Tekkılıç 
*/

class CoronaNewsViewHolder (view: View):BaseViewHolder(view){

    var name: TextView = view.findViewById(R.id.tv_news_name)
    var description:TextView= view.findViewById(R.id.tv_news_description)
    var image:ImageView= view.findViewById(R.id.iv_news_image)
    var rootView:ConstraintLayout=view.findViewById(R.id.newsCardViewRoot)

    override fun bindView(baseModel: IBaseModel, position: Int, click: IRecyclerViewClickListener) {
        val item = baseModel as NewsDTO
        item.let {
            name.text=item.name
            description.text=item.description
            Picasso.get().load(item.image).into(image)
        }

        rootView.setOnClickListener {


            var bundle= Bundle()

            val intent = Intent(rootView.context, NewsDetailActivity::class.java)

//            val i : Intent  by lazy { Intent(androidx.activity.ComponentActivity, MainActivity::class.java) }
            intent.putExtra("url", item.url)
            startActivity(rootView.context,intent,bundle)
        }
    }
}