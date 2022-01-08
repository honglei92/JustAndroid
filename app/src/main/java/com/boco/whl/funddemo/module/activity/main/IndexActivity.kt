package com.boco.whl.funddemo.module.activity.main

import android.net.Uri
import com.boco.whl.funddemo.base.BaseActivity
import com.boco.whl.funddemo.module.fragment.MainFragment
import com.boco.whl.funddemo.module.fragment.MyFragment
import com.boco.whl.funddemo.module.fragment.RegulationFragment
import com.boco.whl.funddemo.module.fragment.ComponentFragment
import com.boco.whl.funddemo.module.fragment.CustomerViewFragment
import butterknife.BindView
import com.boco.whl.funddemo.R
import android.widget.FrameLayout
import com.boco.whl.funddemo.widgets.MyRadioButton
import android.widget.RadioGroup
import android.widget.Toast
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.KeyEvent
import butterknife.ButterKnife
import cn.jpush.android.api.JPushInterface
import com.boco.whl.funddemo.utils.PermissionsUT
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentTransaction
import com.boco.whl.funddemo.module.activity.main.IndexActivity
import kotlinx.android.synthetic.main.activity_index.*

/**
 * 首页activity
 *
 * @author Administrator
 */
class IndexActivity : BaseActivity(), MainFragment.OnFragmentInteractionListener, MyFragment.OnFragmentInteractionListener, RegulationFragment.OnFragmentInteractionListener, ComponentFragment.OnFragmentInteractionListener, CustomerViewFragment.OnFragmentInteractionListener {

    private var mExitTime: Long = 0
    private val toast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        ButterKnife.bind(this)
        // 设置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(true)
        // 初始化 JPush
        JPushInterface.init(this)
        PermissionsUT.getInstance().checkPermissions(this, true)
        initView()
        Log.d("honglei-process", Process.myPid().toString() + "")
        Log.d("honglei-process-thread", Thread.currentThread().name
                + Thread.currentThread().id)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_index
    }

    private fun initView() {
        radio1!!.isSelected = true
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragment1 = MainFragment()
        transaction.add(R.id.contentfragment, fragment1)
        transaction.commit()
        radiogroup!!.setOnCheckedChangeListener { group, checkedId ->
            var manager0: androidx.fragment.app.FragmentManager? = getSupportFragmentManager()
            when (checkedId) {
                R.id.radio1 -> {
                    radio1!!.isSelected = true
                    radio2!!.isSelected = false
                    radio3!!.isSelected = false
                    radio4!!.isSelected = false
                    radio5!!.isSelected = false
                    val transaction0: FragmentTransaction = manager0!!.beginTransaction()
                    val fragment0 = MainFragment()
                    transaction0.replace(R.id.contentfragment, fragment0)
                    transaction0.commit()
                }
                R.id.radio2 -> {
                    radio1!!.isSelected = false
                    radio2!!.isSelected = true
                    radio3!!.isSelected = false
                    radio4!!.isSelected = false
                    radio5!!.isSelected = false
                    val transaction2 = manager.beginTransaction()
//                    val fragment2 = RegulationFragment()
                    val fragment2 = MainFragment()
                    transaction2.replace(R.id.contentfragment, fragment2)
                    transaction2.commit()
                }
                R.id.radio3 -> {
                    radio1!!.isSelected = false
                    radio2!!.isSelected = false
                    radio3!!.isSelected = true
                    radio4!!.isSelected = false
                    radio5!!.isSelected = false
                    val transaction3 = manager.beginTransaction()
//                    val fragment3 = ComponentFragment()
                    val fragment3 = MainFragment()
                    transaction3.replace(R.id.contentfragment, fragment3)
                    transaction3.commit()
                }
                R.id.radio4 -> {
                    radio1!!.isSelected = false
                    radio2!!.isSelected = false
                    radio3!!.isSelected = false
                    radio4!!.isSelected = true
                    radio5!!.isSelected = false
                    val transaction4 = manager.beginTransaction()
//                    val fragment4 = CustomerViewFragment()
                    val fragment4 = MainFragment()
                    transaction4.replace(R.id.contentfragment, fragment4)
                    transaction4.commit()
                }
                R.id.radio5 -> {
                    radio1!!.isSelected = false
                    radio2!!.isSelected = false
                    radio3!!.isSelected = false
                    radio4!!.isSelected = false
                    radio5!!.isSelected = true
                    val transaction5 = manager.beginTransaction()
//                    val fragment5 = MyFragment()
                    val fragment5 = MainFragment()
                    transaction5.replace(R.id.contentfragment, fragment5)
                    transaction5.commit()
                }
                else -> {
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > PRESS_BACK_INTERVAL) {
                showToast(toast, "再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                //小于2000则退出程序
                toast?.cancel()
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {
        private const val PRESS_BACK_INTERVAL: Long = 2000
    }

    override fun onFragmentInteraction(uri: Uri?) {

    }
}