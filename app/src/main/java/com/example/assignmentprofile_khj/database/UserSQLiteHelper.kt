package com.example.assignmentprofile_khj.database
/**
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, "User.db", null, 3)
{
    companion object
    {
        private const val USER_TABLE = "user"
        private const val USERNAME = "username"
        private const val CONTACT_INFO = "contactInfo"
        private const val PASSWORD = "password"
        private const val ROLE = "role"
        private const val COMPANY_NAME = "companyName"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + USER_TABLE + "(" + USERNAME +
                " TEXT PRIMARY KEY, " + CONTACT_INFO + " TEXT UNIQUE, " + PASSWORD +
                " TEXT, " + ROLE + " TEXT, " + COMPANY_NAME + " TEXT UNIQUE" + ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
    }

    fun insertUser(user: UserModel): Long
    {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(USERNAME, user.username)
        contentValues.put(CONTACT_INFO, user.contactInfo)
        contentValues.put(PASSWORD, user.password)
        contentValues.put(ROLE, user.role)
        contentValues.put(COMPANY_NAME, user.companyName)

        val result = db.insert(USER_TABLE, null, contentValues)
        db.close()

        return result
    }

    @SuppressLint("Range")
    fun getAllUser(): ArrayList<UserModel>
    {
        val userList: ArrayList<UserModel> = ArrayList()
        val query = "SELECT * FROM $USER_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try
        {
            cursor = db.rawQuery(query, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            db.execSQL(query)

            return ArrayList()
        }

        var username: String
        var contactInfo: String
        var password: String
        var role: String
        var companyName: String

        if (cursor.moveToFirst())
        {
            do
            {
                username = cursor.getString(cursor.getColumnIndex("username"))
                contactInfo = cursor.getString(cursor.getColumnIndex("contactInfo"))
                password = cursor.getString(cursor.getColumnIndex("password"))
                role = cursor.getString(cursor.getColumnIndex("role"))
                companyName = cursor.getString(cursor.getColumnIndex("companyName"))

                userList.add(
                    UserModel(username = username, contactInfo = contactInfo,
                        password = password, role = role, companyName = companyName)
                )
            }while (cursor.moveToNext())
        }

        return userList
    }

    @SuppressLint("Range")
    fun getAttribute(COLUMN_NAME: String): ArrayList<String>
    {
        val attributeList: ArrayList<String> = ArrayList()
        val query = "SELECT $COLUMN_NAME FROM $USER_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try
        {
            cursor = db.rawQuery(query, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            db.execSQL(query)

            return ArrayList()
        }

        var attribute: String

        if (cursor.moveToFirst())
        {
            do
            {
                attribute = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))

                attributeList.add(attribute)
            }while (cursor.moveToNext())
        }

        return attributeList
    }

    @SuppressLint("Range")
    fun conditionalGetAttribute(COLUMN_NAME: String, CONDITIONAL_COLUMN: String, CONDITIONAL_STRING: String): String
    {
        var attribute = ""
        val query = "SELECT $COLUMN_NAME FROM $USER_TABLE WHERE $CONDITIONAL_COLUMN = '$CONDITIONAL_STRING'"
        val db = this.readableDatabase

        val cursor: Cursor?

        try
        {
            cursor = db.rawQuery(query, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            db.execSQL(query)

            return ""
        }

        if (cursor.moveToFirst())
        {
            do
            {
                attribute = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                break;
            }while (cursor.moveToNext())
        }

        return attribute
    }

    @SuppressLint("Range")
    fun getRecord(USERNAME: String): UserModel?
    {
        val query = "SELECT * FROM $USER_TABLE WHERE username = '$USERNAME'"
        val db = this.readableDatabase
        var user: UserModel? = null

        val cursor: Cursor?

        try
        {
            cursor = db.rawQuery(query, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            db.execSQL(query)

            return UserModel(username = "", contactInfo = "",
                password = "", role = "", companyName = "")

        }

        if (cursor.moveToFirst())
        {
            do
            {
                val username = cursor.getString(cursor.getColumnIndex("username"))
                val contactInfo = cursor.getString(cursor.getColumnIndex("contactInfo"))
                val password = cursor.getString(cursor.getColumnIndex("password"))
                val role = cursor.getString(cursor.getColumnIndex("role"))
                val companyName = cursor.getString(cursor.getColumnIndex("companyName"))

                user = UserModel(username, contactInfo, password, role, companyName)
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return user
    }
}*/