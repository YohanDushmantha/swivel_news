package com.swivel.local.base.di

import android.content.Context
import androidx.room.Room
import com.swivel.local.dao.PostDao
import com.swivel.local.db.BaseRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class RoomModule
 *
 * Provides room module related dependencies for dagger
 */
@Module
class RoomModule {

    /**
     * provides room BaseRoomDatabase object
     * @param context application context
     * @return BaseRoomDatabase room database object
     */
    @Provides
    @Singleton
    fun getBaseDatabase(context: Context) : BaseRoomDatabase{
        return Room.databaseBuilder(context.applicationContext,
            BaseRoomDatabase::class.java,
            "base_database").build()
    }

    /**
     * provides postDao object for manage post data
     * @param roomDatabase BaseRoomDatabase object
     * @return PostDao post dao object
     */
    @Provides
    fun providePostDao(roomDatabase: BaseRoomDatabase) : PostDao{
        return roomDatabase.postDao()
    }
}