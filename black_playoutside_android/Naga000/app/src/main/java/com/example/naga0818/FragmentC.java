package com.example.naga0818;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import androidx.fragment.app.Fragment;

public class FragmentC extends Fragment {

    private TextView yearMonthText;
    private ImageView btn_pre, btn_next;
    private RecyclerView recyclerView;
    private ArrayList<Calendarcell> cellList;
    private ArrayList<Do> data;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_c, container, false);
        // 초기화
        yearMonthText = view.findViewById(R.id.yearMonthText);
        btn_pre = view.findViewById(R.id.btn_pre);
        btn_next = view.findViewById(R.id.btn_next);
        recyclerView = view.findViewById(R.id.recyclerView);

        cellList = new ArrayList<>();

        data = new ArrayList<>();
        data.add(new Do("송산승마스쿨"));
        data.add(new Do("후기"));
        cellList.add(new Calendarcell("2022/8/2", data));

        data = new ArrayList<>();
        data.add(new Do("상하농원"));
        data.add(new Do("알람"));
        data.add(new Do("후기"));
        cellList.add(new Calendarcell("2022/8/6", data));

        data = new ArrayList<>();
        data.add(new Do("국립광주과학관"));
        data.add(new Do("알람"));
        data.add(new Do("후기"));
        cellList.add(new Calendarcell("2022/8/12", data));

        data = new ArrayList<>();
        data.add(new Do("무안갯벌생태공원"));
        data.add(new Do("후기"));
        cellList.add(new Calendarcell("2022/8/14", data));

        data = new ArrayList<>();
        data.add(new Do("광주서부청소년경찰학교"));
        data.add(new Do("알람"));
        cellList.add(new Calendarcell("2022/8/27", data));



        // 현재 날짜
        CalendarUtil.selectedDate = Calendar.getInstance();
        // 화면 설정
        setMonthView();
        // 이전 달 버튼 이벤트
        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtil.selectedDate.add(Calendar.MONTH, -1);
                setMonthView();
            }
        });
        // 다음 달 버튼 이벤트
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarUtil.selectedDate.add(Calendar.MONTH, 1);
                setMonthView();
            }
        });
        return view;
    } // onCreate
    // 날짜 타입 설정
    private String yearMonthFromDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        String yearMonth = year + "년" + month + "월";
        return yearMonth;
    }
    // 화면 설정, setMonthView : 화면에 날짜 보여주는 메서드
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        // 년월 텍스트뷰 셋팅
        yearMonthText.setText(yearMonthFromDate(CalendarUtil.selectedDate));
        // 해당 월 날짜 가져오기
        ArrayList<Date> dayList = daysInMonthArray();
        CalendarAdapter adapter = new CalendarAdapter(cellList, dayList);
        // 레이아웃 설정 (열 7개)
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 7);
        // 레이아웃 적용
        recyclerView.setLayoutManager(manager);
        // 어탭터 적용
        recyclerView.setAdapter(adapter);
    }
    // 날짜 생성 메서드
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<Date> daysInMonthArray(){
        ArrayList<Date> dayList = new ArrayList<>();
        // 날짜 복사해서 변수 생성
        Calendar monthCalendar = (Calendar) CalendarUtil.selectedDate.clone();
        // 1일로 셋팅 (예 4월1일)
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        // 요일 가져와서 -1 설정, (일1, 월2, 일7)
        int firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        // 날짜 셋팅 (-5일전)
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth);
        // 42일 전까지 반복
        while (dayList.size() < 42) {
            dayList.add(monthCalendar.getTime());                // 리스트에 날짜 등록
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);      // 1일씩 증가하는 날짜로 변경
        }
        return dayList;
    }
}  // MainActivity