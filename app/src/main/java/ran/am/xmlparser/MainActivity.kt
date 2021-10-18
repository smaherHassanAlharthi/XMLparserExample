package ran.am.xmlparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv)

        var students: List<Students>? = null


        try {
            val parser = XmlPullParserHandler()
            val istream = assets.open("studentdetails.xml")
          students=  parser.parse(istream)

            recyclerView.adapter = MyAdapter(students)
            recyclerView.layoutManager = LinearLayoutManager(this)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}