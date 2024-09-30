package com.sebs.bodybuildinglegends.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sebs.bodybuildinglegends.data.db.model.LegendEntity
import com.sebs.bodybuildinglegends.util.Constants
import java.time.Instant

@Database(
    entities = [LegendEntity::class],
    version = 1,
    exportSchema = true

)


abstract class LegendDatabase: RoomDatabase() {

    //Here DAO

abstract fun LegendDAO(): LegendDAO

//patron singleton

companion object{

    @Volatile
    private var INSTANCE: LegendDatabase? = null

    fun getDatabase(context: Context): LegendDatabase{


        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                LegendDatabase::class.java,
                Constants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance

            instance
        }

    }

}


}