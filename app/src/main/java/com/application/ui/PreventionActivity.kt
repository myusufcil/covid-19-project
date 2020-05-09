package com.application.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.adapter.IRecyclerViewClickListener
import com.application.adapter.RecyclerViewAdapter
import com.application.covid_19.R
import com.application.dto.PreventionDTO
import com.application.dto.SymptomsDTO
import com.application.model.IBaseModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ps.*

class PreventionActivity : AppCompatActivity() {

    lateinit var recyclerViewSymptomsAdapter: RecyclerViewAdapter
    lateinit var recyclerViewSymptoms: RecyclerView
    private var symptomsListBaseModel = mutableListOf<IBaseModel>()
    lateinit private var symptomsListObject: SymptomsDTO

    lateinit var recyclerViewPreventionAdapter: RecyclerViewAdapter
    lateinit var recyclerViewPrevention: RecyclerView
    private var preventionListBaseModel = mutableListOf<IBaseModel>()
    lateinit private var preventionListObject: PreventionDTO


    var recyclerViewItemClickListener = object : IRecyclerViewClickListener {
        override fun onClickListener(position: Int, model: IBaseModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onLongClickListener(position: Int, model: IBaseModel) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    lateinit var symptomsPics: ArrayList<Int>
    var symptomsName = arrayOf("")

    lateinit var preventionPics: ArrayList<Int>
    var preventionDescription = arrayOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ps)

        readyToDataSource()
        getRecyclerViewAdapter()

        for (position in 0..symptomsPics.size - 1) {
            symptomsListObject = SymptomsDTO(
                picsSymptoms = symptomsPics[position],
                nameSymptoms = symptomsName[position]
            )
            symptomsListBaseModel.add(symptomsListObject)
            recyclerViewSymptomsAdapter.notifyDataSetChanged()
        }

        for (position in 0..preventionPics.size - 1) {
            preventionListObject = PreventionDTO(
                picsPrevention = preventionPics[position],
                descriptionPrevention = preventionDescription[position]
            )
            preventionListBaseModel.add(preventionListObject)
            recyclerViewPreventionAdapter.notifyDataSetChanged()
        }


        iv_intent_ps_menu.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.ps_slide_top, R.anim.ps_slide_bottom)

        }
    }
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun readyToDataSource() {
        symptomsPics = arrayListOf(
            R.drawable.ates64,
            R.drawable.oksuruk64,
            R.drawable.nefes_darligi64,
            R.drawable.ishal64,
            R.drawable.bulanti64,
            R.drawable.bas_agrisi64,
            R.drawable.burun_akintisi64,
            R.drawable.halsizlik64,
            R.drawable.vucut_agrisi64
        )

        symptomsName = resources.getStringArray(R.array.symptomsArray)


        preventionPics = arrayListOf(
            R.drawable.agzini_kapa_pre1,
            R.drawable.dezenfektan_kullan_pre2,
            R.drawable.el_sikisma_pre3,
            R.drawable.el_yikama_pre4,
            R.drawable.evde_kal_pre5,
            R.drawable.kalabalik_ortam_pre6,
            R.drawable.maske_kullan_pre7,
            R.drawable.ortami_temizle_pre8,
            R.drawable.sosyal_mesafe_pre9,
            R.drawable.supheli_durum_pre10,
            R.drawable.uyku_duzeni_pre11,
            R.drawable.yuzune_dokunma_pre12
        )

        preventionDescription = resources.getStringArray(R.array.preventionArray)


    }

    private fun getRecyclerViewAdapter() {
        //Recyclerview Symptoms
        recyclerViewSymptoms =
            findViewById(R.id.recyclerViewSymptoms)
        recyclerViewSymptomsAdapter =
            RecyclerViewAdapter(symptomsListBaseModel, recyclerViewItemClickListener)
        recyclerViewSymptoms.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerViewSymptoms.adapter = recyclerViewSymptomsAdapter

        //Recyclerview Prevention

        recyclerViewPrevention = findViewById(R.id.recyclerViewPrevention)
        recyclerViewPreventionAdapter =
            RecyclerViewAdapter(preventionListBaseModel, recyclerViewItemClickListener)
        recyclerViewPrevention.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerViewPrevention.adapter = recyclerViewPreventionAdapter
    }

}