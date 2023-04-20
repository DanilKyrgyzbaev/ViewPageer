package com.test.viewpager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.map
import androidx.room.Room
import com.test.viewpager.model.Data
import com.test.viewpager.room.AppDatabase
import com.test.viewpager.room.FragmentEntity

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "app-database"
    ).build()
    private val fragmentDao = db.fragmentDao()

    val fragmentList = fragmentDao.getAllFragments().map { fragmentList ->
        val defaultFragment = MyFragment.newInstance(Data(0, "Danil Kyrgyzbaev", "Android developer Junnior"))
        if (fragmentList.isEmpty()) {
            listOf(defaultFragment)
        } else {
            fragmentList.map { fragmentEntity ->
                MyFragment.newInstance(Data(fragmentEntity.id, fragmentEntity.title, fragmentEntity.subTitle))
            } + defaultFragment
        }
    }

    suspend fun addFragment(id: Int, title: String, subTitle: String) {
        val fragmentEntity = FragmentEntity(id, title, subTitle)
        fragmentDao.addFragment(fragmentEntity)
    }
}
