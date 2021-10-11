package com.example.storingappsettings

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {

    var prefName = "perfs"
    val prefDark = "dark_theme"


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {

        val preferences = getSharedPreferences(prefName, MODE_PRIVATE)
        val useDarkTheme = preferences.getBoolean(prefDark, false)
        if (useDarkTheme) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle = findViewById<Switch>(R.id.switch1)
        toggle.isChecked = useDarkTheme
        toggle.setOnCheckedChangeListener {
                _, isChecked ->  toggleTheme(isChecked)
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun toggleTheme(darkTheme: Boolean) {
        val preferences = getSharedPreferences(prefName, MODE_PRIVATE).edit()

        preferences.apply {
            putBoolean(prefDark, darkTheme)
            apply()
        }

        val intent = intent
        finish()
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        val name = findViewById<EditText>(R.id.edit1)
        val pickClass = findViewById<EditText>(R.id.edit2)
        val pickNum = findViewById<EditText>(R.id.edit3)

        val sp = getSharedPreferences("MySharedPreferences", MODE_PRIVATE)

        val key1 = sp.getString("name", "")
        val key2 = sp.getString("pick_class", "")
        val key3 = sp.getInt("pick_num", 0)

        name!!.setText(key1)
        pickClass!!.setText(key2)
        pickNum!!.setText(key3.toString())
    }

    override fun onPause() {
        val name = findViewById<EditText>(R.id.edit1)
        val pickClass = findViewById<EditText>(R.id.edit2)
        val pickNum = findViewById<EditText>(R.id.edit3)

        super.onPause()

        val sp = getSharedPreferences("MySharedPreferences", MODE_PRIVATE)
        val myEdit = sp.edit()

        myEdit.putString("name", name!!.text.toString())
        myEdit.putString("pick_class", pickClass!!.text.toString())
        myEdit.putInt("pick_num", pickNum!!.text.toString().toInt())

        myEdit.apply()
    }
}