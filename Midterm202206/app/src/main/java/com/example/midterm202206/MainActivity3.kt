package com.example.midterm202206

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import java.lang.Exception


class MainActivity3 : AppCompatActivity() {
    private lateinit var dbrw: SQLiteDatabase

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var name2: TextView = findViewById(R.id.Name1)
        var password2 : TextView = findViewById(R.id.Password1)
        val btnlogout: Button = findViewById<Button>(R.id.button1)
        dbrw = MyDBHelper(this).writableDatabase

        btnlogout.setOnClickListener {
            if(name2.length()<1 || password2.length() <1)
                Toast.makeText(this,"請勿留空", Toast.LENGTH_SHORT).show()
            else
                try{
                    dbrw.execSQL("INSERT INTO myTable(username, password) VALUES(?,?)", arrayOf(name2.text.toString(),password2.text.toString()))
                    Toast.makeText(this,"新增USERNAME${name2.text}  密碼${password2.text}",Toast.LENGTH_SHORT).show()
                    name2.setText("")
                    password2.setText("")
                }catch (e: Exception){
                    Toast.makeText(this,"新增失敗:$e",Toast.LENGTH_LONG).show()
                }

            dbrw.close()
            finish()
        }


    }


}

