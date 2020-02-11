package com.swivel.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
public class Post(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "post_id") var postId : Long,
    @ColumnInfo(name = "body_text") var body: String,
    @ColumnInfo(name = "server_id") var id: Int,
    var title: String,
    var userId: Int
)