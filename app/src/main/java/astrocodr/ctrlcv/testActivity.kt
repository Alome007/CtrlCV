package astrocodr.ctrlcv

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.test.*

class testActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)
        changeButton(button)
    }
    fun changeButton(button:Button){
        button.text="Hello ${Math.PI}"
    }
}