package ardents.alexpolo.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.GravityCompat
import ardents.alexpolo.Fragment.AccountFragment
import ardents.alexpolo.Fragment.CartFragment
import ardents.alexpolo.Fragment.CategoryFragment
import ardents.alexpolo.Fragment.HomeFragment
import ardents.alexpolo.Fragment.NotificationFragment
import ardents.alexpolo.R
import ardents.alexpolo.databinding.ActivityMainBinding
import ardents.alexpolo.databinding.DrawerLayBinding
import ardents.alexpolo.databinding.FragmentAccountBinding
import ardents.alexpolo.utils.SharedPrefManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var drawerLayBinding: DrawerLayBinding
   lateinit var drawerProfile:LinearLayout
    lateinit var drawerCategories:LinearLayout
    lateinit var drawerAddress:LinearLayout
    lateinit var drawerLogout:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayBinding=DrawerLayBinding.inflate(LayoutInflater.from(applicationContext),null,false)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, HomeFragment())
            .commit()

        binding.drawerMenu.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START)

        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    binding.appbar.visibility=View.VISIBLE
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment()).commit()
                    true
                }

                R.id.category -> {
                    binding.appbar.visibility=View.GONE
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, CategoryFragment()).commit()
                    true
                }

                R.id.notification -> {
                    binding.appbar.visibility=View.GONE
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, NotificationFragment()).commit()
                    true
                }

                R.id.cart -> {
                    binding.appbar.visibility=View.GONE
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, CartFragment()).commit()
                    true
                }

                R.id.account -> {
                    binding.appbar.visibility=View.GONE
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, AccountFragment()).commit()
                    true
                }

                else -> false
            }

        }

        drawerProfile=findViewById(R.id.drawer_profile)
        drawerProfile.setOnClickListener {
           // startActivity(Intent(this,FragmentAccountBinding::class.java))
            binding.appbar.visibility=View.GONE
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, AccountFragment()).commit()
            binding.drawer.closeDrawer(GravityCompat.START)
        }

        drawerAddress=findViewById(R.id.drawer_address)
        drawerAddress.setOnClickListener {
            startActivity(Intent(this,SavedAddressActivity::class.java))
            binding.drawer.closeDrawer(GravityCompat.START)
        }

        drawerCategories=findViewById(R.id.drawer_category)
        drawerCategories.setOnClickListener {
            binding.appbar.visibility=View.GONE
            fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, CategoryFragment()).commit()
            binding.drawer.closeDrawer(GravityCompat.START)
        }

        drawerLogout=findViewById(R.id.drawer_logout)
        drawerLogout.setOnClickListener {
            SharedPrefManager.getInstance(this).logout()
            finish()
        }

    }
}
