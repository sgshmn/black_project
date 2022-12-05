package com.example.naga0818;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private ArrayList<Calendarcell> cellList;
    private ArrayList<Date> dayList;
    private ArrayList<Do> data;
    private int idx;
    private ListView lv_do;
    private Context context;
    private DoAdapter adapter;
    public CalendarAdapter(ArrayList<Calendarcell> cellList, ArrayList<Date> dayList) {
        this.dayList = dayList;
        this.cellList = cellList;
    }

    @NonNull
    @Override      // 화면을 연결해주는 메서드
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        context = parent.getContext();
        lv_do = view.findViewById(R.id.lv_do);

//        // 리스트뷰 클릭 이벤트 설정
//        lv_do.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Do vo = (Do)adapterView.getAdapter().getItem(i);
//                Log.v("test",  vo.getName()+"");
//            }
//        });
        return new CalendarViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override         // 데이터를 연결해주는 메서드
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        //날짜 변수에 담기
        Date monthDate = dayList.get(position);
        // 달력 초기화
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        // 현재 년, 월, 일
        Date now = Calendar.getInstance().getTime();

        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        String currentYear = year.format(now);
        int nowYear = Integer.parseInt(currentYear);

        SimpleDateFormat month = new SimpleDateFormat("MM");
        String currentMonth = month.format(now);
        int nowMonth = Integer.parseInt(currentMonth);

        SimpleDateFormat day = new SimpleDateFormat("dd");
        String currentDay = day.format(now);
        int nowDay = Integer.parseInt(currentDay);
        // 달 변경시 적용될 현재 달
        int activeMonth = CalendarUtil.selectedDate.get(dateCalendar.MONTH)+1;
        // 넘어온 데이터
        int displayDay = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        // 텍스트 색상 지정
        if ((position +1) % 7 == 0) { //토요일 파란색 지정
            holder.dayText.setTextColor(Color.BLUE);
        } else if (position == 0 || position % 7 == 0) {  // 일요일 빨간색 지정
            holder.dayText.setTextColor(Color.RED);
        }
        if (displayMonth != activeMonth) {    // 비교해서 년, 월이 다르면 색상 변경
            holder.dayText.setTextColor(Color.parseColor("#BB82CAFF"));
        } else if (displayYear == nowYear && displayMonth == nowMonth && displayDay == nowDay) {  // 날짜까지 맞으면 색상 표시
            holder.dayText.setBackgroundColor(Color.YELLOW);
        }
        // 날짜 변수에 담기
        int dayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);
        holder.dayText.setText(String.valueOf(dayNo));
        //설정한 날짜들만 달력에 내용 출력하는 로직
        if(cellList.size() != idx) {
            String[] getDay = cellList.get(idx).getDay().split("/");

            int getYear = Integer.parseInt(getDay[0]);
            int getMonth = Integer.parseInt(getDay[1]);
            int getDate = Integer.parseInt(getDay[2]);

            if (displayYear == getYear && displayMonth == getMonth && displayDay == getDate) {
                Log.v("날짜", getYear + "/" + getMonth + "/" + getDate);
                adapter = new DoAdapter(context, R.layout.do_list, cellList.get(idx).getDolist());
                lv_do.setAdapter(adapter);
                idx ++;
            }
        }
        // 날짜 클릭 이벤트
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int iYear = dateCalendar.get(Calendar.YEAR);
                int iMonth = dateCalendar.get(Calendar.MONTH)+1;
                int iDay = dateCalendar.get(Calendar.DAY_OF_MONTH);
                String yearMonDay = iYear + "년" + iMonth + "월" + iDay + "일";
                Toast.makeText(holder.itemView.getContext(), yearMonDay, Toast.LENGTH_SHORT).show();
                if (iYear == 2022 && iMonth == 8 && iDay ==20) {
                    Intent intent = new Intent(holder.itemView.getContext(), TodoList.class);
                    intent.putExtra("checkDay",yearMonDay);
                    holder.itemView.getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(holder.itemView.getContext(), TodoList_content.class);
                    intent.putExtra("checkDay",yearMonDay);
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return dayList.size();
    }
    class CalendarViewHolder extends RecyclerView.ViewHolder {      // calendar_cell.xml의 변수 id..
        TextView dayText;
        ListView lv_do;
        View parentView;
        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.dayText);
            lv_do = itemView.findViewById(R.id.lv_do);
            parentView = itemView.findViewById(R.id.parentView);
        }
    }
}
