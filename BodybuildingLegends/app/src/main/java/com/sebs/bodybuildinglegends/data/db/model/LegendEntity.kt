package com.sebs.bodybuildinglegends.data.db.model

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebs.bodybuildinglegends.util.Constants

@Entity(tableName = Constants.DATABASE_LEGEND_TABLE)
data class LegendEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "legend_id")
    var id: Long = 0,
    @ColumnInfo(name = "legend_title")
    var title: String,
    @ColumnInfo(name = "legend_genre")
    var category: String,
    @ColumnInfo(name = "legend_developer", defaultValue = "Desconocido")
    var region: String
)


