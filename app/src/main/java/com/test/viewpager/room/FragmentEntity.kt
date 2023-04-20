package com.test.viewpager.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fragment_table")
data class FragmentEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val subTitle: String
)