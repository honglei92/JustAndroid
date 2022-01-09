package com.boco.whl.funddemo.module.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.*
import androidx.fragment.app.Fragment
import com.boco.whl.funddemo.R
import com.boco.whl.funddemo.module.activity.my.SearchActivity
import com.boco.whl.funddemo.module.activity.thirdlib.LoadImageActivity
import com.boco.whl.funddemo.module.activity.thirdlib.MarqueeActivity
import com.boco.whl.funddemo.module.activity.thirdlib.eventbus.EventBusTestActivity
import com.boco.whl.funddemo.module.activity.thirdlib.ffmpeg.HelloJni
import com.boco.whl.funddemo.module.activity.thirdlib.ffmpeg.PlayActivity
import com.boco.whl.funddemo.module.activity.thirdlib.glide.GlideTestActivity
import com.boco.whl.funddemo.module.activity.thirdlib.listview.ListTest
import com.boco.whl.funddemo.module.activity.thirdlib.mvp.view.UserActivity
import com.boco.whl.funddemo.module.activity.thirdlib.mvpV2.CustomerActivity
import com.boco.whl.funddemo.module.activity.thirdlib.retrofit.DownloadApkActivity
import com.boco.whl.funddemo.module.activity.thirdlib.rxjava.RxImageActivity
import com.boco.whl.funddemo.module.activity.thirdlib.rxjava.RxjavaTestActivity
import com.boco.whl.funddemo.module.adapter.CategoryItemAdapter
import kotlinx.android.synthetic.main.fragment_first.*
import java.util.*

/**
 * 首页
 *
 * @author Administrator
 */
class MainFragment : Fragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mListener: OnFragmentInteractionListener? = null
    private var intent: Intent? = null

    /**
     *
     */
    private var initTouchY = 0f
    private var metrics: DisplayMetrics? = null
    private var mScaling = false
    private val mMarqueeViews: MutableList<View> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategory()
        initScrollScale()
        initMarquee()
    }

    /**
     * 头条布局
     */
    private fun initMarquee() {
        val data: MutableList<String> = ArrayList()
        data.add("美团卓拙百词斩1614  技责精，github精")
        data.add("基础精,文档精，深步精，源码精，步骤精")
        data.add("实践精,log精，翻墙精，博客精，新敏精")
        mMarqueeViews.clear() //记得加这句话，不然可能会产生重影现象
        var i = 0
        while (i < data.size) {
            val position = i
            //设置滚动的单个布局
            val moreView = LayoutInflater.from(activity).inflate(R.layout.item_view, null) as LinearLayout
            //初始化布局的控件
            val tv1 = moreView.findViewById<View>(R.id.tv1) as TextView
            val tv2 = moreView.findViewById<View>(R.id.tv2) as TextView
            /**
             * 设置监听
             */
            moreView.findViewById<View>(R.id.rl).setOnClickListener { Toast.makeText(activity, position.toString() + "你点击了" + data[position], Toast.LENGTH_SHORT).show() }
            /**
             * 设置监听
             */
            moreView.findViewById<View>(R.id.rl2).setOnClickListener {
                if (data.size > position + 1) {
                    Toast.makeText(activity, position.toString() + "你点击了" + data[position + 1], Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, position.toString() + "你点击了" + data[0], Toast.LENGTH_SHORT).show()
                }
            }
            //进行对控件赋值
            tv1.text = data[i]
            if (data.size > i + 1) {
                //奇数条
                tv2.text = data[i + 1]
            } else {
                //偶数条
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                //moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
                //修改了最后一个没有 将第一个拼接到最后显示
                tv2.text = data[0]
            }

            //添加到循环滚动数组里面去
            mMarqueeViews.add(moreView)
            upView!!.setViews(mMarqueeViews)
            i = i + 2
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initScrollScale() {
        metrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(metrics)
        val lp = guide_iv!!.layoutParams
        lp.width = metrics!!.widthPixels
        lp.height = metrics!!.widthPixels * 237 / 421
        guide_iv!!.layoutParams = lp
        mainScrollView!!.setOnTouchListener { v: View, event: MotionEvent ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    mScaling = false
                    lp.width = metrics!!.widthPixels
                    lp.height = metrics!!.widthPixels * 237 / 421
                    guide_iv!!.layoutParams = lp
                }
                MotionEvent.ACTION_MOVE -> {
                    val upY = v.scrollY
                    val touchY = event.y
                    if (!mScaling) {
                        initTouchY = if (upY == 0) {
                            event.y
                        } else {
                            0f
                        }
                    }
                    val deltaY = touchY - initTouchY
                    if (deltaY < 0) {
                        0f
                    }

                    //缩放代码
                    mScaling = true
                    lp.width = (metrics!!.widthPixels + deltaY * 0.45).toInt()
                    lp.height = ((metrics!!.widthPixels + deltaY * 0.45) * 237 / 421).toInt()
                    guide_iv!!.layoutParams = lp
                    return@setOnTouchListener true
                }
                else -> {
                }
            }
            false
        }
        val observer = guide_iv!!.viewTreeObserver
        observer.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (guide_iv != null) {
                    guide_iv!!.viewTreeObserver.removeOnGlobalLayoutListener { onGlobalLayout() }
                    val imageHeight = guide_iv!!.height
                    //增加上滑显示操作
                    mainScrollView!!.setScrollViewListener { observableScrollView, x, y, oldX, oldY ->
                        if (y <= 0) {
                            tipLL!!.alpha = 0f
                        } else if (y < imageHeight) {
                            tipLL!!.alpha = y.toFloat() / imageHeight
                        } else {
                            tipLL!!.alpha = 1f
                        }
                    }
                }
            }
        })
        mainScrollView!!.scrollTo(0, 0)
    }

    private fun initCategory() {
        val titles = arrayOf("Glide", "RxJava", "EventBus", "RxImage", "RxOperator", "Retrofit", "OkHttp", "Baidu", "Map", "FusionChart", "MVP1", "MVP2", "kotlin", "listview",
                "imgCompress", "marquee", "loadImage", "JNI", "FFMPEG", "RetrofitDownload", "updating",
                "updating"
        )
        val adapter = CategoryItemAdapter(activity, titles)
        category?.let {
            category!!.adapter = adapter
            category!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView: AdapterView<*>?, view: View?, i: Int, l: Long ->
                when (i) {
                    0 -> intent = Intent(activity, GlideTestActivity::class.java)
                    1 ->                     //rxjava 广东接口
                        intent = Intent(activity, EventBusTestActivity::class.java)
                    2 -> intent = Intent(activity, EventBusTestActivity::class.java)
                    3 ->                     //rxjava 图片测试
                        intent = Intent(activity, RxImageActivity::class.java)
                    4 -> intent = Intent(activity, RxjavaTestActivity::class.java)
                    5 -> intent = Intent(activity, RxjavaTestActivity::class.java)
                    7 -> intent = Intent(activity, SearchActivity::class.java)
                    8 -> intent = Intent(activity, SearchActivity::class.java)
                    9 -> intent = Intent(activity, UserActivity::class.java)
                    10 -> intent = Intent(activity, UserActivity::class.java)
                    11 -> intent = Intent(activity, CustomerActivity::class.java)
                    12 -> {
                    }
                    13 -> intent = Intent(activity, ListTest::class.java)
                    14 -> intent = Intent(activity, PlayActivity::class.java)
                    15 -> intent = Intent(activity, MarqueeActivity::class.java)
                    16 -> intent = Intent(activity, LoadImageActivity::class.java)
                    17 -> intent = Intent(activity, HelloJni::class.java)
                    18 -> intent = Intent(activity, PlayActivity::class.java)
                    19 -> intent = Intent(activity, DownloadApkActivity::class.java)
                    else -> {
                    }
                }
                if (intent != null) {
                    startActivity(intent)
                }
            }
        }
    }

    fun onButtonPressed(uri: Uri?) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri?)
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(param1: String?, param2: String?): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}