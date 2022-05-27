package com.example.midterm202206

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.telephony.ims.ImsMmTelManager
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var dbrw: SQLiteDatabase

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val correct_username: String = "admin"
        val righttext = "OK!"
        val wrongtext = "Wrong username or password!"
        var toastmsg: String = "wrong"
        var username: EditText = findViewById(R.id.Name)
        var password: EditText = findViewById(R.id.Password)
        val btnlogin: Button = findViewById<Button>(R.id.button)
        var tvstatus : TextView = findViewById(R.id.tv_status)
        var btn_button2 : Button = findViewById<Button>(R.id.button2)
        dbrw = MyDBHelper(this).writableDatabase

        btnlogin.setOnClickListener {
            tvstatus.text = ""
            val c =dbrw.rawQuery(if(username.length()<1) "SELECT * FROM myTable" else "SELECT * FROM myTable WHERE username like'${username.text}'",null)
            c.moveToFirst()
            items.clear()
            Toast.makeText(this," 共有${c.count}筆資料",Toast.LENGTH_SHORT).show()
            if((username.text.toString() == c.getString(0).toString()) && (password.text.toString() == c.getString(1).toString())){
                Log.e("MainActivity", username.text.toString())
                username.text.clear()
                password.text.clear()

                val intent = Intent(this, MainActivity2::class.java)
                val bundle = Bundle()
                bundle.putInt("key1", 111)
                bundle.putString("key2", "admin")
                intent.putExtras(bundle)
                startActivity(intent)
                //Toast.makeText(this@MainActivity, righttext , Toast.LENGTH_LONG).show()
            }else{
                tvstatus.text = "wrong username or password"
                Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "wrong username or password")
            }
            c.close()
        }
        btn_button2.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbrw.close()
    }
}