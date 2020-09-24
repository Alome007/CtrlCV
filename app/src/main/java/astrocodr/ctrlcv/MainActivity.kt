package astrocodr.ctrlcv

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.TranslateAnimation
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import astrocodr.ctrlcv.Adapters.mainAdapter
import astrocodr.ctrlcv.Model.mainModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_home.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val RC_SIGN_IN=123
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<RelativeLayout>
    private val arrayList = listOf(
        mainModel("Raising Arizona", Date()),
        mainModel("Raising Arizona", Date()),
        mainModel("Raising Arizona", Date()),
        mainModel("Raising Arizona", Date()),
        mainModel("Raising Arizona", Date()),
        mainModel("Raising Arizona", Date())
    )
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val text = intent
            .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
        // process the text
        // process the text

        if(text!=null){
            Toast.makeText(this,text,Toast.LENGTH_LONG).show()
        }
        val readonly = intent
            .getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        // RecyclerView node initialized here
        recycler.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(this@MainActivity)
            // set the custom adapter to the RecyclerView
            adapter = mainAdapter(arrayList)
        }

        val size=arrayList.size;
        if (size!=0){
            nothing_txt.visibility=View.GONE
        }
        window.statusBarColor=resources.getColor(R.color.colorPrimary)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
//
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide
            }
            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED ->{
                        add_new.visibility=View.GONE

                    }else->{
                    add_new.visibility=View.VISIBLE
                }
                }
            }
        })
        if(FirebaseAuth.getInstance().currentUser!=null){
            //load user
            val user = FirebaseAuth.getInstance().currentUser
            Log.d("User",user!!.displayName)
            val firstName=user.displayName!!.split(" ")[0]
            name.text="Hi $firstName,"
            Log.d("User",user.photoUrl.toString())
            if (user.photoUrl==null){

            }else{
                Picasso.get().load(user.photoUrl).into(profile)
            }
        }else{
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build())
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setTheme(R.style.AppTheme)
                    .build(),
                RC_SIGN_IN)
        }


        menu.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,menu)
            popupMenu.menuInflater.inflate(R.menu.pop_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.settings ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                    R.id.abt ->
                        Toast.makeText(this@MainActivity, "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                Log.d("User",user!!.displayName)
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
//                Log.d("DD", response!!.error.toString())
            }
        }
    }


    fun View.slideUp(duration: Int = 500) {
        visibility = View.VISIBLE
        val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
        animate.duration = duration.toLong()
        animate.fillAfter = true
        this.startAnimation(animate)
    }

    fun View.slideDown(duration: Int = 500) {
        visibility = View.VISIBLE
        val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
        animate.duration = duration.toLong()
        animate.fillAfter = true
        this.startAnimation(animate)
    }
}
