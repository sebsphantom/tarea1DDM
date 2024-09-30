package com.sebs.bodybuildinglegends.application

import android.app.Application
import com.sebs.bodybuildinglegends.data.LegendRepository
import com.sebs.bodybuildinglegends.data.db.LegendDatabase


class BodybuildingLegendsDBApp: Application() {

    private val database by lazy{
        LegendDatabase.getDatabase(this@BodybuildingLegendsDBApp)
    }

    val repository by lazy {
        LegendRepository(database.LegendDAO())
    }



}


