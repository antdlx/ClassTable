package com.antdlx.classtable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private int gridWidth=0;
    private int gridHeight = 0;
    private RelativeLayout exLayout=null;
    private RelativeLayout layout = null;
    private final int CLASS_COUNT=8;
    private YScrollView yScrollView=null;
    private HorizontalScrollView top_horizontalScrollView=null;
    private ScrollView left_scrollview=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exLayout = (RelativeLayout) findViewById(R.id.mon);
        yScrollView = (YScrollView) findViewById(R.id.yscrollview);
        top_horizontalScrollView = (HorizontalScrollView) findViewById(R.id.top_horizontalscrollview);
        left_scrollview = (ScrollView) findViewById(R.id.left_scrollview);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        yScrollView.dispatchTouchEvent(ev);
        top_horizontalScrollView.dispatchTouchEvent(ev);
        left_scrollview.dispatchTouchEvent(ev);
        return false;
    }

    /**
     * 当activity获得或者失去焦点的时候可以使用这个。其他生命周期中的方法都不可以获得主按键的具体位置和宽高
     * 所以我们使用这个方法来获取
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        gridWidth = exLayout.getWidth();

        gridHeight = exLayout.getHeight()/CLASS_COUNT;

        InitContent();
    }

    /**
     *
     * @param start 开始的节数，比如第一节开始上课
     * @param end   结束的节数，比如第二节结束
     * @param text  课程设置的名称
     * @return      创建好的TextView
     */
    private TextView createTv(int start,int end,String text){
        TextView tv = new TextView(this);
        /*
         指定高度和宽度
         */
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridWidth,gridHeight*(end-start+1));
        /*
        指定位置，只需要指定Y即可，因为X已经在父布局RelativeLayout中隐式的指定好了
         */
        tv.setY(gridHeight*(start-1));
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        return tv;
    }

    /**
     *
     * @param i      星期 i，比如星期二
     * @param start  开始的节数，比如第一节开始上课
     * @param end    结束的节数，比如第二节结束
     * @param text   课程设置的名称
     */
    private void addView(int i,int start,int end,String text){
        TextView tv;
        switch (i){
            case 1:
                layout = (RelativeLayout) findViewById(R.id.mon);
                break;
            case 2:
                layout = (RelativeLayout) findViewById(R.id.tue);
                break;
            case 3:
                layout = (RelativeLayout) findViewById(R.id.wen);
                break;
            case 4:
                layout = (RelativeLayout) findViewById(R.id.thu);
                break;
            case 5:
                layout = (RelativeLayout) findViewById(R.id.fri);
                break;
            case 6:
                layout = (RelativeLayout) findViewById(R.id.sat);
                break;
            case 7:
                layout = (RelativeLayout) findViewById(R.id.sun);
                break;
        }
        tv= createTv(start,end,text);
        //自定义使用不同的颜色
        tv.setBackgroundColor(Color.argb(100, start * 10, (start + end) * 20, 0));
        layout.addView(tv);
    }

    public void InitContent(){
        String text="Android开发与实践@W3502";
        addView(1,1,2,text);
        addView(7,2,3,text);
        addView(5,9,10,text);
        addView(4,2,3,text);
        addView(3,5,5,text);
        addView(4,10,12,text);
    }

}