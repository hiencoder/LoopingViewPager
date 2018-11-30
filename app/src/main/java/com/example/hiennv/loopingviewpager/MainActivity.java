package com.example.hiennv.loopingviewpager;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //@BindView(R.id.viewpager)
    LoopingViewPager viewPager;
    //@BindView(R.id.piv_image)
    PageIndicatorView pivImage;
    //@BindView(R.id.btn_switch_dataset)
    Button btnSwitchDataset;
    //@BindView(R.id.btn_1)
    Button btnPage1;
    // @BindView(R.id.btn_2)
    Button btnPage2;
    //@BindView(R.id.btn_3)
    Button btnPage3;
    //@BindView(R.id.btn_4)
    Button btnPage4;
    //@BindView(R.id.btn_5)
    Button btnPage5;
    //@BindView(R.id.btn_6)
    Button btnPage6;
    //@BindView(R.id.tv_change_program)
    TextView tvChangeStatus;

    private LoopingAdapter loopingAdapter;
    private int currentDataSet = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);
        initView();
        initData();
        initEvents();
    }

    private void initEvents() {
        btnPage1.setOnClickListener(this);
        btnPage2.setOnClickListener(this);
        btnPage3.setOnClickListener(this);
        btnPage4.setOnClickListener(this);
        btnPage5.setOnClickListener(this);
        btnPage6.setOnClickListener(this);
        btnSwitchDataset.setOnClickListener(this);
    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        pivImage = findViewById(R.id.piv_image);
        btnSwitchDataset = findViewById(R.id.btn_switch_dataset);
        btnPage1 = findViewById(R.id.btn_1);
        btnPage2 = findViewById(R.id.btn_2);
        btnPage3 = findViewById(R.id.btn_3);
        btnPage4 = findViewById(R.id.btn_4);
        btnPage5 = findViewById(R.id.btn_5);
        btnPage6 = findViewById(R.id.btn_6);
        tvChangeStatus = findViewById(R.id.tv_change_program);
    }

    private void initData() {
        loopingAdapter = new LoopingAdapter(this, createDummyData(), true);
        viewPager.setAdapter(loopingAdapter);

        //Custom bind indicator
        pivImage.setCount(viewPager.getIndicatorCount());
        //Event when scroll
        viewPager.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                pivImage.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
                pivImage.setSelection(newIndicatorPosition);
            }
        });
    }

    private List<Integer> createDummyData() {
        List<Integer> list = new ArrayList<>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3, 4);
        list.add(4, 5);
        list.add(5, 6);
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.resumeAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.pauseAutoScroll();
    }
    /*@OnClick({R.id.btn_switch_dataset, R.id.btn_1, R.id.btn_2, R.id.btn_3,
            R.id.btn_4, R.id.btn_5, R.id.btn_6})
    void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_switch_dataset:
                //Change data set
                switchChangeDataSet();
                break;
            case R.id.btn_1:
                //Set selected 1

                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
            case R.id.btn_6:
                break;
        }
    }*/

    private void switchChangeDataSet() {
        if (currentDataSet == 1) {
            //Add list to adapter
            loopingAdapter.setItemList(createSecondDummiesData());
            currentDataSet++;
            toggleSixButtons(false);
        } else if (currentDataSet == 2) {
            loopingAdapter.setItemList(createThirdDummiesData());
            currentDataSet++;
            toggleSixButtons(false);
        } else {
            loopingAdapter.setItemList(createDummyData());
            currentDataSet = 1;
            toggleSixButtons(true);
        }
        pivImage.setCount(viewPager.getIndicatorCount());
        viewPager.reset();
    }

    private void toggleSixButtons(boolean toggle) {
        //show/hide button
        int isVisible = toggle ? View.VISIBLE : View.INVISIBLE;
        tvChangeStatus.setVisibility(isVisible);
        btnPage1.setVisibility(isVisible);
        btnPage2.setVisibility(isVisible);
        btnPage3.setVisibility(isVisible);
        btnPage4.setVisibility(isVisible);
        btnPage5.setVisibility(isVisible);
        btnPage6.setVisibility(isVisible);
    }

    private List<Integer> createThirdDummiesData() {
        List<Integer> list = new ArrayList<>();
        list.add(0, 1);
        return list;
    }

    private List<Integer> createSecondDummiesData() {
        List<Integer> list = new ArrayList<>();
        list.add(0, 1);
        list.add(1, 2);
        return list;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_switch_dataset:
                switchChangeDataSet();
                break;
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
                Integer number = Integer.valueOf(((Button) v).getText().toString());
                viewPager.setCurrentItem(number);
                break;
        }
    }
}
