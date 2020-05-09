package com.application.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.adapter.IRecyclerViewClickListener
import com.application.adapter.RecyclerViewAdapter

import com.application.covid_19.R
import com.application.dto.PreventionDTO
import com.application.dto.SymptomsDTO
import com.application.model.IBaseModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PreventionFragment : Fragment() {

    lateinit var recyclerViewSymptomsAdapter: RecyclerViewAdapter
    lateinit var recyclerViewSymptoms: RecyclerView
    private var symptomsListBaseModel = mutableListOf<IBaseModel>()
    lateinit private var symptomsListObject: SymptomsDTO

    lateinit var recyclerViewPreventionAdapter: RecyclerViewAdapter
    lateinit var recyclerViewPrevention: RecyclerView
    private var preventionListBaseModel = mutableListOf<IBaseModel>()
    lateinit private var preventionListObject: PreventionDTO

    private lateinit var getView:View


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

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        getView=inflater.inflate(R.layout.fragment_prevention, container, false)

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

        return getView
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
            getView.findViewById(R.id.recyclerViewSymptoms)
        recyclerViewSymptomsAdapter =
            RecyclerViewAdapter(symptomsListBaseModel, recyclerViewItemClickListener)
        recyclerViewSymptoms.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerViewSymptoms.adapter = recyclerViewSymptomsAdapter

        //Recyclerview Prevention

        recyclerViewPrevention = getView.findViewById(R.id.recyclerViewPrevention)
        recyclerViewPreventionAdapter =
            RecyclerViewAdapter(preventionListBaseModel, recyclerViewItemClickListener)
        recyclerViewPrevention.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewPrevention.adapter = recyclerViewPreventionAdapter
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PreventionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
