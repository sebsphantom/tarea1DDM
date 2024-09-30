package com.sebs.bodybuildinglegends.data

import com.sebs.bodybuildinglegends.data.db.LegendDAO
import com.sebs.bodybuildinglegends.data.db.model.LegendEntity

class LegendRepository(
    private val legendDAO: LegendDAO
) {
    suspend fun insertLegend(legend: LegendEntity){
        legendDAO.insertLegend(legend)
    }

    suspend fun getAllLegends(): MutableList<LegendEntity>{
        return legendDAO.getAllLegends()
    }

    suspend fun updatedLegend(legend: LegendEntity){
        legendDAO.updateLegend(legend)
    }


    suspend fun deleteLegend(legend: LegendEntity){
        legendDAO.deleteLegend(legend)


    }

}