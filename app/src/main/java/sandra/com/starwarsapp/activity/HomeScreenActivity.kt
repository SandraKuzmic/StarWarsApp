package sandra.com.starwarsapp.activity

import android.content.Intent
import android.os.Bundle
import android.sandra.com.starwarsapp.R
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import sandra.com.starwarsapp.CATEGORY_BUNDLE_KEY

class HomeScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val listView = findViewById<ListView>(R.id.lv_home_screen)
        val swCategories = resources.getStringArray(R.array.sw_categories)

        val adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_selectable_list_item,
                android.R.id.text1,
                swCategories)

        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra(CATEGORY_BUNDLE_KEY, position)

            startActivity(intent)
        }
    }
}