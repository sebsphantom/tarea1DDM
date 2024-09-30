package com.sebs.bodybuildinglegends.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebs.bodybuildinglegends.application.BodybuildingLegendsDBApp
import com.sebs.bodybuildinglegends.data.LegendRepository
import com.sebs.bodybuildinglegends.data.db.model.LegendEntity
import com.sebs.bodybuildinglegends.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private var legends: MutableList<LegendEntity> = mutableListOf()
    private lateinit var repository: LegendRepository


    private lateinit var legendAdapter: LegendAdapter





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = (application as BodybuildingLegendsDBApp).repository

        legendAdapter = LegendAdapter()


        //recyclerview
        binding.rvLegends.layoutManager = LinearLayoutManager( this)
        binding.rvLegends.adapter = legendAdapter


        val legend = LegendEntity(
            title = "Sam Sulek",
            category = "Professional",
            region = "USA"
        )

        lifecycleScope.launch {
            repository.insertLegend(legend)
        }




        updateUI()

    }

    fun click(view: View) {


        val dialog = LegendDialog {
            updateUI()
        }

        dialog.show(supportFragmentManager, "dialog1")



    }

    private fun updateUI(){
        lifecycleScope.launch {
            legends = repository.getAllLegends()

            binding.tvSinRegistros.visibility =
                if(legends.isNotEmpty()) View.INVISIBLE else View.VISIBLE
            legendAdapter.updatedList(legends)



        }








    }

}



