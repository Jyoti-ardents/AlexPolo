package ardents.alexpolo.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ardents.alexpolo.Fragment.AccountFragment
import ardents.alexpolo.Fragment.CartFragment
import ardents.alexpolo.Fragment.CategoryFragment
import ardents.alexpolo.Fragment.HomeFragment
import ardents.alexpolo.Fragment.NotificationFragment
import ardents.alexpolo.R
import ardents.alexpolo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, HomeFragment())
            .commit()

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment()).commit()
                    true
                }

                R.id.category -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, CategoryFragment()).commit()
                    true
                }

                R.id.notification -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, NotificationFragment()).commit()
                    true
                }

                R.id.cart -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, CartFragment()).commit()
                    true
                }

                R.id.account -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, AccountFragment()).commit()
                    true
                }

                else -> false
            }

        }
    }
}
