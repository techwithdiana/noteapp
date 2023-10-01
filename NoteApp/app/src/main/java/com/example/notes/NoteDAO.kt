package com.example.notes

import android.content.Context
import androidx.room.*
import java.util.*

@Entity
data class Note (

    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String = "",
    @ColumnInfo(name="created_at") val created:Date = Date()
)
{
    @PrimaryKey(autoGenerate = true) var uid: Int =0
}


@Dao
interface NoteDAO {
    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note where uid = :id")
    fun getByID(id:Int):Note

    @Query("DELETE FROM note WHERE uid = :id")
    fun deleteById(id: Int)
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}

const val DATABASE_NAME:String = "notes-db"
@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao():NoteDAO
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries()
                .build()
        }
    }
}

