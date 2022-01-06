package com.example.testlibrarysong.datasourse.room.migrations

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlin.random.Random

object MigrationFrom1To2 : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE play_lists ADD COLUMN playlistRating REAL NOT NULL DEFAULT 0.0")
        val cursor = database.query("SELECT * FROM play_lists")

        if (cursor.moveToFirst()) {
            do {
                val values = ContentValues()
                values.put("playlistRating", randomRatingValue())

                val columnIndex = cursor.getColumnIndex("playlistId")
                database.update(
                    "play_lists",
                    SQLiteDatabase.CONFLICT_REPLACE,
                    values,
                    "playlistId = ?",
                    arrayOf(cursor.getInt(columnIndex))
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    private fun randomRatingValue(): Float {
        return Random.nextDouble(0.0, 10.1).toFloat()
    }
}

object MigrationFrom2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE users ADD COLUMN maleType INTEGER NOT NULL DEFAULT 1")
        val cursor = database.query("SELECT * FROM users")

        if (cursor.moveToFirst()) {
            do {
                val values = ContentValues()
                values.put("maleType", randomMailTypeValue())

                val columnIndex = cursor.getColumnIndex("userId")
                database.update(
                    "users",
                    SQLiteDatabase.CONFLICT_REPLACE,
                    values,
                    "userId = ?",
                    arrayOf(cursor.getInt(columnIndex))
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    private fun randomMailTypeValue(): Int {
        return Random.nextInt(0, 2)
    }
}