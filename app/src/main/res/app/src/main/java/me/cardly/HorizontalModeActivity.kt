package me.cardly
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import me.cardly.Fragments.Favourite
import me.cardly.Fragments.Home

class HorizontalModeActivity : AppCompatActivity(){
    val RC_SIGN_IN=123
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal)
//        if(FirebaseAuth.getInstance().currentUser!=null){
//            val user = FirebaseAuth.getInstance().currentUser
//            Log.d("User",user!!.displayName)
//            //load user...
//        }else{
//            val providers = arrayListOf(
//                AuthUI.IdpConfig.GoogleBuilder().build(),
//                AuthUI.IdpConfig.TwitterBuilder().build() )
//            startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//                    .setTheme(R.style.AppTheme)
//                    .build(),
//                RC_SIGN_IN)
//        }
        menu.setItemSelected(R.id.home)
        menu.setOnItemSelectedListener { id ->
            when (id) {
                R.id.home->{
                    val fragment = Home()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@setOnItemSelectedListener
                }
                R.id.favorites->{
                    val fragment = Favourite()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@setOnItemSelectedListener
                }
                else -> R.color.white to ""
            }

//            val color = ContextCompat.getColor(this@HorizontalModeActivity, option.first)
//            container.colorAnimation(lastColor, color)
//            lastColor = color
//
//            title.text = option.second
        }


        if (savedInstanceState == null) {
            menu.showBadge(R.id.home)
//            menu.showBadge(R.id.settings, 32)
            val fragment = Home()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == RC_SIGN_IN) {
//            val response = IdpResponse.fromResultIntent(data)
//            if (resultCode == Activity.RESULT_OK) {
//                // Successfully signed in
//                val user = FirebaseAuth.getInstance().currentUser
//                Log.d("User",user!!.displayName)
//                // ...
//            } else {
//                // Sign in failed. If response is null the user canceled the
//                // sign-in flow using the back button. Otherwise check
//                // response.getError().getErrorCode() and handle the error.
//                // ...
////                Log.d("DD", response!!.error.toString())
//            }
//        }
//    }
}
