package com.kosmo.a22intent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //투표수를 저장할 정수형 배열
    int[] votes = new int[9];

    //ImageView 객체를 저장할 배열
    ImageView[] imageViews = new ImageView[9];

    //영화제목을 저장할 배열
    String[] titles = {"블랙팬서", "스파이더맨", "아이언맨", "인피니티워", "앤드맨&와스프",
                        "인크레더블헐크", "시빌워", "원터솔저", "토르라그나로크" };

    //ImageView의 리소스 아이디를 저장할 배열
    /*
    ImageView의 리소스 아이디를 저장하기 위한 배열로 안드로이드에서는
    리소스 아이디를 내부적으로 처리할때 int형으로 사용한다.
     */
    int[] resourceIds = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};


    @Override
    //여기 Bundle을 통해 intent를 전달한다.(즉, intent는 bundle을 전달하는 택배기사 역할이다.)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //앱 테이블 설정(보통 앱의 이름이 나오는데... 여기서 설정하면 됨)
        //앱의 타이틀 설정(메인액티비티에서만 적용된다)
        setTitle("당신이 좋아하는 영화는?");
        setContentView(R.layout.activity_main);

        //이미지뷰 위젯 얻어와서 반복문을 이용해 리스너 부착
        //배열크기만큼 반복하면서 포스터 이미지에 리스너 부착
        for(int i =0; i<votes.length; i++){
            final int index = i; //final을 붙여놓는 것이 좋다.
            imageViews[i] = findViewById(resourceIds[i]);
            imageViews[i].setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    //투표는 최대 5번까지만 가능하다.
                    if(votes[index]<5){
                        //5번 미만일때는 투표수를 1 증가시킨다.
                        votes[index]++;
                        Toast.makeText(MainActivity.this,
                                String.format("%s:%d표", titles[index], votes[index]),
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,
                                "5점이 최고 점수입니다.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //결과버튼 인탠트에 내용저장해서 보냄
            //투표 결과보기 버튼
            findViewById(R.id.btn_result).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //새로운 액티비티를 실행하기 위해 Intent 객체 생성
                    Intent intent = new Intent(v.getContext(),ResultActivity.class);

                    //부가데이터를 전달하기 위해 putExtra()메서드를 사용한다.
                    intent.putExtra("votes", votes);
                    intent.putExtra("titles", titles);

                    //액티비티실행
                    startActivity(intent);
                }
            });

        }
    }
}