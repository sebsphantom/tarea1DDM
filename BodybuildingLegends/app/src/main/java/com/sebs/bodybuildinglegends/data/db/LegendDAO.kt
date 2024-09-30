package com.sebs.bodybuildinglegends.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sebs.bodybuildinglegends.data.db.model.LegendEntity
import com.sebs.bodybuildinglegends.util.Constants

@Dao

interface LegendDAO {

    //FUNCIONES PARA INTERACTUAR CON LA BASE DE DATOS

    //CREATE
    @Insert
    suspend fun insertLegend(legend: LegendEntity)

    @Insert
    suspend fun insertLegend(legend: MutableList<LegendEntity>)

    //READ

    @Query("SELECT * FROM ${Constants.DATABASE_LEGEND_TABLE}")
    suspend fun getAllLegends(): MutableList<LegendEntity>

    //UPDATE

    @Update
    suspend fun updateLegend(legend: LegendEntity)

    //DELATE

    @Delete
    suspend fun deleteLegend(legend: LegendEntity)

}