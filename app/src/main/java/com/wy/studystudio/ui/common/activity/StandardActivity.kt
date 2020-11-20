package com.wy.studystudio.ui.common.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.wy.studystudio.R
import com.wy.studystudio.extension.d
import com.wy.studystudio.ui.common.fragment.BaseFragment
import kotlinx.android.synthetic.main.activity_standard.*

class StandardActivity : AppCompatActivity() {

    lateinit var fragment: BaseFragment<*>

    companion object {
        const val PENDING_FRAGMENT_CLASS_NAME = "sa_1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standard)
        fragment = startFragment()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        val containerLP = (container.layoutParams as ConstraintLayout.LayoutParams)
        if (fragment.isToolBarOverlay()) {
            containerLP.topToTop = R.id.parent
        } else {
            containerLP.topToBottom = R.id.toolbar
        }
        toolbar.title = fragment.title()
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
    }

    private fun startFragment(): BaseFragment<*> {
        val fragmentCN = intent.getStringExtra(PENDING_FRAGMENT_CLASS_NAME)!!
        val args = intent.extras
        d("start fragment {} with argument {}", fragmentCN, args)

        val instance = Class.forName(fragmentCN).newInstance() as BaseFragment<*>
        instance.arguments = args

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, instance)
        transaction.commitNowAllowingStateLoss()

        return instance
    }
}

