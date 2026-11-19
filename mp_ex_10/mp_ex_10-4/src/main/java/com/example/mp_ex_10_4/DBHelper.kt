package com.example.mp_ex_10_4

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context,"HKBook.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql: String = "CREATE TABLE IF NOT EXISTS HKBook( " +
                "SEQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CATEGORY STRING, " +
                "PURPOSE STRING," +
                "DATE STRING, " +
                "MONEY INTEGER," +
                "MEMO STRING ) "
        db?.execSQL(sql)
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }
    fun insert(vo: HouseKeeping) {
        var sql = " INSERT INTO HKBook(CATEGORY,PURPOSE,DATE,MONEY,MEMO) " +
                " VALUES('${vo.category}','${vo.purpose}','${vo.date}',${vo.money},'${vo.memo}')"
        var db = this.writableDatabase
        db.execSQL(sql)
    }
    fun findTime(fromDate: String, toDate: String): ArrayList<HouseKeeping> {
        var sql = " SELECT SEQ, CATEGORY, PURPOSE, DATE, MONEY, MEMO FROM HKBook WHERE DATE BETWEEN " +
                " '${fromDate}' AND '${toDate}'"
        var db = this.writableDatabase
        var result = db.rawQuery(sql, null)
        var list = ArrayList<HouseKeeping>()
        while (result.moveToNext()) {
            var seq = result.getInt(0)
            var category = result.getString(1)
            var purpose = result.getString(2)
            var date = result.getString(3)
            var money = result.getInt(4)
            var memo = result.getString(5)
            list.add(HouseKeeping(seq, category, purpose, date, money, memo))
        }
        return list
    }
}