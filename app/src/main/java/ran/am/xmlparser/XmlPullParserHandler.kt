package ran.am.xmlparser

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XmlPullParserHandler {
    private val studentdetails = ArrayList<Students>()
    private var student: Students? = null
    private var text: String? = null

    fun parse(inputStream: InputStream): List<Students> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagname = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagname.equals("student", ignoreCase = true)) {
                        // create a new instance of student
                       student = Students()
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagname.equals("student", ignoreCase = true)) {
                        // add student object to list
                       student?.let { studentdetails.add(it) }
                    } else if (tagname.equals("id", ignoreCase = true)) {
                       student!!.id = Integer.parseInt(text)
                    } else if (tagname.equals("name", ignoreCase = true)) {
                       student!!.name = text
                    } else if (tagname.equals("marks", ignoreCase = true)) {
                       student!!.marks = java.lang.Float.parseFloat(text)
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return studentdetails
    }
}  
